/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.account.web.internal.portlet.action;

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.constants.CommerceAccountPortletKeys;
import com.liferay.commerce.account.exception.CommerceAccountNameException;
import com.liferay.commerce.account.exception.DuplicateCommerceAccountException;
import com.liferay.commerce.account.exception.NoSuchAccountException;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.exception.NoSuchAddressException;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.PortletQName;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.concurrent.Callable;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CommerceAccountPortletKeys.COMMERCE_ACCOUNT,
		"mvc.command.name=editCommerceAccount"
	},
	service = MVCActionCommand.class
)
public class EditCommerceAccountMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		try {
			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				Callable<CommerceAccount> commerceAccountCallable =
					new CommerceAccountCallable(actionRequest);

				CommerceAccount commerceAccount = TransactionInvokerUtil.invoke(
					_transactionConfig, commerceAccountCallable);

				redirect = getSaveAndContinueRedirect(
					actionRequest, commerceAccount);
			}
			else if (cmd.equals("setActive")) {
				setActive(actionRequest);
			}
		}
		catch (Throwable t) {
			if (t instanceof NoSuchAccountException ||
				t instanceof NoSuchAddressException ||
				t instanceof PrincipalException) {

				SessionErrors.add(actionRequest, t.getClass());

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");
			}
			else if (t instanceof CommerceAccountNameException ||
					 t instanceof DuplicateCommerceAccountException) {

				hideDefaultErrorMessage(actionRequest);

				SessionErrors.add(actionRequest, t.getClass());
			}
			else {
				_log.error(t, t);
			}
		}

		sendRedirect(actionRequest, actionResponse, redirect);
	}

	protected String getSaveAndContinueRedirect(
			ActionRequest actionRequest, CommerceAccount commerceAccount)
		throws PortalException {

		PortletURL managePortletURL = PortletProviderUtil.getPortletURL(
			actionRequest, CommerceAccount.class.getName(),
			PortletProvider.Action.MANAGE);

		PortletURL portletURL = PortletProviderUtil.getPortletURL(
			actionRequest, CommerceAccount.class.getName(),
			PortletProvider.Action.VIEW);

		portletURL.setParameter(
			"commerceAccountId",
			String.valueOf(commerceAccount.getCommerceAccountId()));

		portletURL.setParameter(
			PortletQName.PUBLIC_RENDER_PARAMETER_NAMESPACE + "backURL",
			managePortletURL.toString());

		return portletURL.toString();
	}

	protected void setActive(ActionRequest actionRequest)
		throws PortalException {

		long commerceAccountId = ParamUtil.getLong(
			actionRequest, "commerceAccountId");

		CommerceAccount commerceAccount =
			_commerceAccountService.getCommerceAccount(commerceAccountId);

		_commerceAccountService.setActive(
			commerceAccountId, !commerceAccount.isActive());
	}

	protected CommerceAccount updateCommerceAccount(ActionRequest actionRequest)
		throws Exception {

		long commerceAccountId = ParamUtil.getLong(
			actionRequest, "commerceAccountId");

		String name = ParamUtil.getString(actionRequest, "name");
		boolean deleteLogo = ParamUtil.getBoolean(actionRequest, "deleteLogo");
		String email = ParamUtil.getString(actionRequest, "email");
		String taxId = ParamUtil.getString(actionRequest, "taxId");
		boolean active = ParamUtil.getBoolean(actionRequest, "active");
		String externalReferenceCode = ParamUtil.getString(
			actionRequest, "externalReferenceCode");
		long[] userIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "userIds"), 0L);
		String[] emailAddresses = ParamUtil.getStringValues(
			actionRequest, "emailAddresses");

		byte[] logoBytes = null;

		long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");

		if (fileEntryId > 0) {
			FileEntry fileEntry = _dlAppLocalService.getFileEntry(fileEntryId);

			logoBytes = FileUtil.getBytes(fileEntry.getContentStream());
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CommerceAccount.class.getName(), actionRequest);

		CommerceAccount commerceAccount;

		if (commerceAccountId > 0) {
			long defaultBillingAddressId = ParamUtil.getLong(
				actionRequest, "defaultBillingAddressId");
			long defaultShippingAddressId = ParamUtil.getLong(
				actionRequest, "defaultShippingAddressId");

			commerceAccount = _commerceAccountService.updateCommerceAccount(
				commerceAccountId, name, !deleteLogo, logoBytes, email, taxId,
				true, defaultBillingAddressId, defaultShippingAddressId,
				serviceContext);
		}
		else {
			commerceAccount =
				_commerceAccountService.addBusinessCommerceAccount(
					name, CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID,
					email, taxId, active, externalReferenceCode, userIds,
					emailAddresses, serviceContext);
		}

		return commerceAccount;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditCommerceAccountMVCActionCommand.class);

	private static final TransactionConfig _transactionConfig =
		TransactionConfig.Factory.create(
			Propagation.REQUIRED, new Class<?>[] {Exception.class});

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private CommerceAddressService _commerceAddressService;

	@Reference
	private DLAppLocalService _dlAppLocalService;

	private class CommerceAccountCallable implements Callable<CommerceAccount> {

		@Override
		public CommerceAccount call() throws Exception {
			return updateCommerceAccount(_actionRequest);
		}

		private CommerceAccountCallable(ActionRequest actionRequest) {
			_actionRequest = actionRequest;
		}

		private final ActionRequest _actionRequest;

	}

}