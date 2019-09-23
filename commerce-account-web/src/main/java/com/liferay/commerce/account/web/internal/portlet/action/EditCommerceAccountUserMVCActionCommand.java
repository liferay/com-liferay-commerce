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

import com.liferay.commerce.account.configuration.CommerceAccountGroupServiceConfiguration;
import com.liferay.commerce.account.constants.CommerceAccountActionKeys;
import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.constants.CommerceAccountPortletKeys;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.permission.CommerceAccountPermission;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.account.web.internal.servlet.taglib.ui.CommerceAccountScreenNavigationConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.portal.kernel.bean.BeanParamUtil;
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
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.PasswordPolicy;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.PortletQName;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.auth.session.AuthenticatedSessionManager;
import com.liferay.portal.kernel.security.ldap.LDAPSettingsUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.users.admin.kernel.util.UsersAdmin;

import java.util.Calendar;
import java.util.Date;
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
				Callable<User> userCallable = new UserCallable(
					actionRequest, actionResponse);

				TransactionInvokerUtil.invoke(_transactionConfig, userCallable);
			}
			else if (cmd.equals("EDIT_ROLES")) {
				Callable<User> userCallable = new EditRoleCallable(
					actionRequest);

				TransactionInvokerUtil.invoke(_transactionConfig, userCallable);
			}

			CommerceAccountGroupServiceConfiguration
				commerceAccountGroupServiceConfiguration =
					_configurationProvider.getConfiguration(
						CommerceAccountGroupServiceConfiguration.class,
						new GroupServiceSettingsLocator(
							_portal.getScopeGroupId(actionRequest),
							CommerceAccountConstants.SERVICE_NAME));

			if (commerceAccountGroupServiceConfiguration.commerceSiteType() !=
					CommerceAccountConstants.SITE_TYPE_B2C) {

				sendRedirect(
					actionRequest, actionResponse,
					getSaveAndContinueRedirect(actionRequest));
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

	protected String getSaveAndContinueRedirect(ActionRequest actionRequest)
		throws Exception {

		PortletURL portletURL = PortletProviderUtil.getPortletURL(
			actionRequest, CommerceAccount.class.getName(),
			PortletProvider.Action.VIEW);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long commerceAccountId = ParamUtil.getLong(
			actionRequest, "commerceAccountId");

		PortletURL backPortletURL = PortletProviderUtil.getPortletURL(
			actionRequest, CommerceAccount.class.getName(),
			PortletProvider.Action.MANAGE);

		if (_commerceAccountPermission.contains(
				themeDisplay.getPermissionChecker(), commerceAccountId,
				CommerceAccountActionKeys.MANAGE_MEMBERS)) {

			backPortletURL.setParameter(
				"mvcRenderCommandName", "viewCommerceAccount");

			backPortletURL.setParameter(
				"screenNavigationCategoryKey",
				CommerceAccountScreenNavigationConstants.
					ENTRY_KEY_ACCOUNT_MEMBERS);
		}

		portletURL.setParameter(
			PortletQName.PUBLIC_RENDER_PARAMETER_NAMESPACE + "backURL",
			backPortletURL.toString());

		portletURL.setParameter(
			"mvcRenderCommandName", "editCommerceAccountUser");

		portletURL.setParameter(
			"userId", ParamUtil.getString(actionRequest, "userId"));

		return portletURL.toString();
	}

	protected void updatePassword(
			User user, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String newPassword1 = actionRequest.getParameter("password1");
		String newPassword2 = actionRequest.getParameter("password2");

		boolean passwordReset = ParamUtil.getBoolean(
			actionRequest, "passwordReset");

		PasswordPolicy passwordPolicy = user.getPasswordPolicy();

		boolean ldapPasswordPolicyEnabled =
			LDAPSettingsUtil.isPasswordPolicyEnabled(user.getCompanyId());

		if ((user.getLastLoginDate() == null) &&
			(((passwordPolicy == null) && !ldapPasswordPolicyEnabled) ||
			 ((passwordPolicy != null) && passwordPolicy.isChangeable() &&
			  passwordPolicy.isChangeRequired()))) {

			passwordReset = true;
		}

		String reminderQueryQuestion = BeanParamUtil.getString(
			user, actionRequest, "reminderQueryQuestion");

		if (reminderQueryQuestion.equals(UsersAdmin.CUSTOM_QUESTION)) {
			reminderQueryQuestion = BeanParamUtil.getStringSilent(
				user, actionRequest, "reminderQueryCustomQuestion");
		}

		String reminderQueryAnswer = BeanParamUtil.getString(
			user, actionRequest, "reminderQueryAnswer");

		boolean passwordModified = false;

		if (Validator.isNotNull(newPassword1) ||
			Validator.isNotNull(newPassword2)) {

			_userLocalService.updatePassword(
				user.getUserId(), newPassword1, newPassword2, passwordReset);

			passwordModified = true;
		}

		_userLocalService.updatePasswordReset(user.getUserId(), passwordReset);

		if (Validator.isNotNull(reminderQueryQuestion) &&
			Validator.isNotNull(reminderQueryAnswer)) {

			_userLocalService.updateReminderQuery(
				user.getUserId(), reminderQueryQuestion, reminderQueryAnswer);
		}

		if ((user.getUserId() == themeDisplay.getUserId()) &&
			passwordModified) {

			String login = null;

			Company company = themeDisplay.getCompany();

			String authType = company.getAuthType();

			if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
				login = user.getEmailAddress();
			}
			else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
				login = user.getScreenName();
			}
			else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
				login = String.valueOf(user.getUserId());
			}

			_authenticatedSessionManager.login(
				_portal.getOriginalServletRequest(
					_portal.getHttpServletRequest(actionRequest)),
				_portal.getHttpServletResponse(actionResponse), login,
				newPassword1, false, null);
		}
	}

	protected void updateUser(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			long commerceAccountId = ParamUtil.getLong(
				actionRequest, "commerceAccountId");

			_commerceAccountPermission.check(
				themeDisplay.getPermissionChecker(), commerceAccountId,
				CommerceAccountActionKeys.MANAGE_MEMBERS);

			_commerceAccountPermission.check(
				themeDisplay.getPermissionChecker(), commerceAccountId,
				ActionKeys.UPDATE);

			long userId = ParamUtil.getLong(actionRequest, "userId");

			String screenName = ParamUtil.getString(
				actionRequest, "screenName");
			String emailAddress = ParamUtil.getString(
				actionRequest, "emailAddress");
			String firstName = ParamUtil.getString(actionRequest, "firstName");
			String lastName = ParamUtil.getString(actionRequest, "lastName");

			boolean deleteLogo = ParamUtil.getBoolean(
				actionRequest, "deleteLogo");

			byte[] portraitBytes = null;

			long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");

			if (fileEntryId > 0) {
				FileEntry fileEntry = _dlAppLocalService.getFileEntry(
					fileEntryId);

				portraitBytes = FileUtil.getBytes(fileEntry.getContentStream());
			}

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				User.class.getName(), actionRequest);

			User user = _userLocalService.getUser(userId);

			Date birthday = user.getBirthday();

			Calendar birthdayCal = CalendarFactoryUtil.getCalendar(
				birthday.getTime());

			// Update user

			_userLocalService.updateUser(
				userId, user.getPassword(), null, null, false,
				user.getReminderQueryQuestion(), user.getReminderQueryAnswer(),
				screenName, emailAddress, user.getFacebookId(),
				user.getOpenId(), !deleteLogo, portraitBytes,
				user.getLanguageId(), user.getTimeZoneId(), user.getGreeting(),
				user.getComments(), firstName, user.getMiddleName(), lastName,
				0, 0, user.isMale(), birthdayCal.get(Calendar.MONTH),
				birthdayCal.get(Calendar.DAY_OF_MONTH),
				birthdayCal.get(Calendar.YEAR), null, null, null, null, null,
				user.getJobTitle(), user.getGroupIds(),
				user.getOrganizationIds(), user.getRoleIds(), null,
				user.getUserGroupIds(), serviceContext);

			// Update user password

			updatePassword(user, actionRequest, actionResponse);
		}
		catch (Exception e) {
			if (e instanceof NoSuchUserException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass());

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");
			}
			else if (e instanceof UserPasswordException) {
				SessionErrors.add(actionRequest, e.getClass(), e);

				String redirect = _portal.escapeRedirect(
					ParamUtil.getString(actionRequest, "redirect"));

				if (Validator.isNotNull(redirect)) {
					sendRedirect(actionRequest, actionResponse, redirect);
				}
			}
			else {
				throw e;
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditCommerceAccountUserMVCActionCommand.class);

	private static final TransactionConfig _transactionConfig =
		TransactionConfig.Factory.create(
			Propagation.REQUIRED, new Class<?>[] {Exception.class});

	@Reference
	private AuthenticatedSessionManager _authenticatedSessionManager;

	@Reference
	private CommerceAccountPermission _commerceAccountPermission;

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private Portal _portal;

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
			updateUser(_actionRequest, _actionResponse);

			return null;
		}

		private UserCallable(
			ActionRequest actionRequest, ActionResponse actionResponse) {

			_actionRequest = actionRequest;
			_actionResponse = actionResponse;
		}

		private final ActionRequest _actionRequest;
		private final ActionResponse _actionResponse;

	}

}