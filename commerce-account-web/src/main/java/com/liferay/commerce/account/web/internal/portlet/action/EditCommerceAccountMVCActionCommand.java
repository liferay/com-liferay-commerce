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
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.petra.string.StringPool;
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

		try {
			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				Callable<CommerceAccount> commerceAccountCallable =
					new CommerceAccountCallable(actionRequest);

				CommerceAccount commerceAccount = TransactionInvokerUtil.invoke(
					_transactionConfig, commerceAccountCallable);

				String redirect = getSaveAndContinueRedirect(
					actionRequest, commerceAccount);

				sendRedirect(actionRequest, actionResponse, redirect);
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

		hideDefaultSuccessMessage(actionRequest);
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
			commerceAccount = _commerceAccountService.updateCommerceAccount(
				commerceAccountId, name, !deleteLogo, logoBytes, email, taxId,
				true, serviceContext);

			// Update commerce address

			updateCommerceAddress(commerceAccount, actionRequest);
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

	protected void updateCommerceAddress(
			CommerceAccount commerceAccount, ActionRequest actionRequest)
		throws PortalException {

		long commerceAddressId = ParamUtil.getLong(
			actionRequest, "commerceAddressId");

		CommerceAddress commerceAddress =
			_commerceAddressService.fetchCommerceAddress(commerceAddressId);

		String street1 = ParamUtil.getString(actionRequest, "street1");
		String city = ParamUtil.getString(actionRequest, "city");
		String zip = ParamUtil.getString(actionRequest, "zip");
		long commerceCountryId = ParamUtil.getLong(
			actionRequest, "commerceCountryId");
		long commerceRegionId = ParamUtil.getLong(
			actionRequest, "commerceRegionId");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CommerceAddress.class.getName(), actionRequest);

		if (commerceAddress == null) {
			_commerceAddressService.addCommerceAddress(
				commerceAccount.getModelClassName(),
				commerceAccount.getCommerceAccountId(),
				commerceAccount.getName(), StringPool.BLANK, street1,
				StringPool.BLANK, StringPool.BLANK, city, zip, commerceRegionId,
				commerceCountryId, StringPool.BLANK, true, false,
				serviceContext);
		}
		else {
			_commerceAddressService.updateCommerceAddress(
				commerceAddress.getCommerceAddressId(),
				commerceAddress.getName(), commerceAddress.getDescription(),
				street1, commerceAddress.getStreet2(),
				commerceAddress.getStreet3(), city, zip, commerceRegionId,
				commerceCountryId, commerceAddress.getPhoneNumber(), true,
				commerceAddress.isDefaultShipping(), serviceContext);
		}
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