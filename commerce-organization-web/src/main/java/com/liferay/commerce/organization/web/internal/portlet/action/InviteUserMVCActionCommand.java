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

package com.liferay.commerce.organization.web.internal.portlet.action;

import com.liferay.commerce.account.exception.NoSuchAccountUserRelException;
import com.liferay.commerce.organization.constants.CommerceOrganizationPortletKeys;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.UserEmailAddressException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CommerceOrganizationPortletKeys.COMMERCE_ORGANIZATION,
		"mvc.command.name=inviteUser"
	},
	service = MVCActionCommand.class
)
public class InviteUserMVCActionCommand extends BaseMVCActionCommand {

	protected void assignUser(ActionRequest actionRequest)
		throws PortalException {

		long organizationId = ParamUtil.getLong(
			actionRequest, "organizationId");

		long[] userIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "userIds"), 0L);
		String[] emailAddresses = ParamUtil.getStringValues(
			actionRequest, "emailAddresses");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			User.class.getName(), actionRequest);

		userIds = ArrayUtil.append(
			userIds,
			_getUserIdsFromEmailAddresses(emailAddresses, serviceContext));

		_userService.addOrganizationUsers(organizationId, userIds);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.REMOVE)) {
				removeUsers(actionRequest);
			}
			else if (cmd.equals(Constants.ASSIGN)) {
				Callable<User> userCallable = new UserCallable(actionRequest);

				TransactionInvokerUtil.invoke(_transactionConfig, userCallable);
			}
		}
		catch (Throwable t) {
			if (t instanceof NoSuchAccountUserRelException ||
				t instanceof PrincipalException) {

				SessionErrors.add(actionRequest, t.getClass());

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");
			}
			else if (t instanceof UserEmailAddressException) {
				hideDefaultErrorMessage(actionRequest);

				SessionErrors.add(actionRequest, t.getClass());

				String redirect = _portal.getCurrentURL(actionRequest);

				sendRedirect(actionRequest, actionResponse, redirect);
			}
		}
	}

	protected void removeUsers(ActionRequest actionRequest)
		throws PortalException {

		long[] removeUserIds = null;

		long organizationId = ParamUtil.getLong(
			actionRequest, "organizationId");

		long userId = ParamUtil.getLong(actionRequest, "userId");

		if (userId > 0) {
			removeUserIds = new long[] {userId};
		}
		else {
			removeUserIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "removeUserIds"), 0L);
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			User.class.getName(), actionRequest);

		for (long removeUserId : removeUserIds) {
			User user = _userService.getUserById(removeUserId);

			long[] organizationIds = user.getOrganizationIds();

			organizationIds = ArrayUtil.remove(organizationIds, organizationId);

			_userService.updateOrganizations(
				user.getUserId(), organizationIds, serviceContext);
		}
	}

	private long[] _getUserIdsFromEmailAddresses(
			String[] emailAddresses, ServiceContext serviceContext)
		throws PortalException {

		List<Long> userIds = new ArrayList<>();

		if (emailAddresses != null) {
			for (String emailAddress : emailAddresses) {
				User user = _userService.addUserWithWorkflow(
					serviceContext.getCompanyId(), true, StringPool.BLANK,
					StringPool.BLANK, true, StringPool.BLANK, emailAddress, 0,
					StringPool.BLANK, serviceContext.getLocale(), emailAddress,
					StringPool.BLANK, emailAddress, 0, 0, true, 1, 1, 1970,
					StringPool.BLANK,
					new long[] {serviceContext.getScopeGroupId()}, null, null,
					null, true, serviceContext);

				userIds.add(user.getUserId());
			}
		}

		return ArrayUtil.toLongArray(userIds);
	}

	private static final TransactionConfig _transactionConfig =
		TransactionConfig.Factory.create(
			Propagation.REQUIRED, new Class<?>[] {Exception.class});

	@Reference
	private Portal _portal;

	@Reference
	private UserService _userService;

	private class UserCallable implements Callable<User> {

		@Override
		public User call() throws Exception {
			assignUser(_actionRequest);

			return null;
		}

		private UserCallable(ActionRequest actionRequest) {
			_actionRequest = actionRequest;
		}

		private final ActionRequest _actionRequest;

	}

}