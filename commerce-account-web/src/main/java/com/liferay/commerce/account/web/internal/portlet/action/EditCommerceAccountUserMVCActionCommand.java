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

import com.liferay.commerce.account.constants.CommerceAccountPortletKeys;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.portal.kernel.exception.ContactBirthdayException;
import com.liferay.portal.kernel.exception.ContactNameException;
import com.liferay.portal.kernel.exception.GroupFriendlyURLException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.UserEmailAddressException;
import com.liferay.portal.kernel.exception.UserFieldException;
import com.liferay.portal.kernel.exception.UserIdException;
import com.liferay.portal.kernel.exception.UserPasswordException;
import com.liferay.portal.kernel.exception.UserReminderQueryException;
import com.liferay.portal.kernel.exception.UserScreenNameException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.PasswordPolicy;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Callable;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CommerceAccountPortletKeys.COMMERCE_ACCOUNT,
		"mvc.command.name=editCommerceAccountUser"
	},
	service = MVCActionCommand.class
)
public class EditCommerceAccountUserMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.UPDATE)) {
				Callable<User> userCallable = new UserCallable(actionRequest);

				TransactionInvokerUtil.invoke(_transactionConfig, userCallable);
			}
			else if (cmd.equals("EDIT_ROLES")) {
				Callable<User> userCallable = new EditRoleCallable(
					actionRequest);

				TransactionInvokerUtil.invoke(_transactionConfig, userCallable);
			}
		}
		catch (Throwable t) {
			if (t instanceof NoSuchUserException ||
				t instanceof PrincipalException) {

				SessionErrors.add(actionRequest, t.getClass());

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");
			}
			else if (t instanceof ContactBirthdayException ||
					 t instanceof ContactNameException ||
					 t instanceof GroupFriendlyURLException ||
					 t instanceof UserEmailAddressException ||
					 t instanceof UserFieldException ||
					 t instanceof UserIdException ||
					 t instanceof UserPasswordException ||
					 t instanceof UserReminderQueryException ||
					 t instanceof UserScreenNameException) {

				SessionErrors.add(actionRequest, t.getClass(), t);
			}
			else {
				_log.error(t, t);
			}
		}

		hideDefaultSuccessMessage(actionRequest);
	}

	protected void editUserRoles(ActionRequest actionRequest)
		throws PortalException {

		long commerceAccountId = ParamUtil.getLong(
			actionRequest, "commerceAccountId");
		long userId = ParamUtil.getLong(actionRequest, "userId");
		long[] selectedRoleIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "selectedRoleIds"), 0L);

		CommerceAccount commerceAccount =
			_commerceAccountService.getCommerceAccount(commerceAccountId);

		_userGroupRoleLocalService.deleteUserGroupRoles(
			userId, new long[] {commerceAccount.getCommerceAccountGroupId()});

		_userGroupRoleLocalService.addUserGroupRoles(
			userId, commerceAccount.getCommerceAccountGroupId(),
			selectedRoleIds);
	}

	protected void updatePassword(User user, ActionRequest actionRequest)
		throws PortalException {

		String newPassword1 = actionRequest.getParameter("password1");
		String newPassword2 = actionRequest.getParameter("password2");

		boolean passwordReset = ParamUtil.getBoolean(
			actionRequest, "passwordReset");

		PasswordPolicy passwordPolicy = user.getPasswordPolicy();

		if ((user.getLastLoginDate() == null) &&
			((passwordPolicy == null) ||
			 (passwordPolicy.isChangeable() &&
			  passwordPolicy.isChangeRequired()))) {

			passwordReset = true;
		}

		if (Validator.isNotNull(newPassword1) ||
			Validator.isNotNull(newPassword2)) {

			_userService.updatePassword(
				user.getUserId(), newPassword1, newPassword2, passwordReset);
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if ((user.getUserId() == themeDisplay.getUserId()) &&
			PropsValues.SESSION_STORE_PASSWORD &&
			Validator.isNotNull(newPassword1)) {

			PortletSession portletSession = actionRequest.getPortletSession();

			portletSession.setAttribute(
				WebKeys.USER_PASSWORD, newPassword1,
				PortletSession.APPLICATION_SCOPE);
		}
	}

	protected void updateUser(ActionRequest actionRequest) throws Exception {
		long userId = ParamUtil.getLong(actionRequest, "userId");

		String screenName = ParamUtil.getString(actionRequest, "screenName");
		String emailAddress = ParamUtil.getString(
			actionRequest, "emailAddress");

		boolean deleteLogo = ParamUtil.getBoolean(actionRequest, "deleteLogo");

		byte[] portraitBytes = null;

		long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");

		if (fileEntryId > 0) {
			FileEntry fileEntry = _dlAppLocalService.getFileEntry(fileEntryId);

			portraitBytes = FileUtil.getBytes(fileEntry.getContentStream());
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			User.class.getName(), actionRequest);

		User user = _userLocalService.getUser(userId);

		Date birthday = user.getBirthday();

		Calendar birthdayCal = CalendarFactoryUtil.getCalendar(
			birthday.getTime());

		// Update user

		_userService.updateUser(
			userId, user.getPassword(), null, null, false,
			user.getReminderQueryQuestion(), user.getReminderQueryAnswer(),
			screenName, emailAddress, user.getFacebookId(), user.getOpenId(),
			!deleteLogo, portraitBytes, user.getLanguageId(),
			user.getTimeZoneId(), user.getGreeting(), user.getComments(),
			user.getFirstName(), user.getMiddleName(), user.getLastName(), 0, 0,
			user.isMale(), birthdayCal.get(Calendar.MONTH),
			birthdayCal.get(Calendar.DAY_OF_MONTH),
			birthdayCal.get(Calendar.YEAR), null, null, null, null, null,
			user.getJobTitle(), user.getGroupIds(), user.getOrganizationIds(),
			user.getRoleIds(), null, user.getUserGroupIds(),
			user.getAddresses(), user.getEmailAddresses(), user.getPhones(),
			user.getWebsites(), null, serviceContext);

		// Update user password

		updatePassword(user, actionRequest);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditCommerceAccountUserMVCActionCommand.class);

	private static final TransactionConfig _transactionConfig =
		TransactionConfig.Factory.create(
			Propagation.REQUIRED, new Class<?>[] {Exception.class});

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private UserGroupRoleLocalService _userGroupRoleLocalService;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private UserService _userService;

	private class EditRoleCallable implements Callable<User> {

		@Override
		public User call() throws Exception {
			editUserRoles(_actionRequest);

			return null;
		}

		private EditRoleCallable(ActionRequest actionRequest) {
			_actionRequest = actionRequest;
		}

		private final ActionRequest _actionRequest;

	}

	private class UserCallable implements Callable<User> {

		@Override
		public User call() throws Exception {
			updateUser(_actionRequest);

			return null;
		}

		private UserCallable(ActionRequest actionRequest) {
			_actionRequest = actionRequest;
		}

		private final ActionRequest _actionRequest;

	}

}