<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect", themeDisplay.getPathFriendlyURLPrivateGroup() + themeDisplay.getScopeGroup().getFriendlyURL());
%>

<c:choose>
	<c:when test="<%= themeDisplay.isSignedIn() %>">
		<aui:script>
			window.location.replace("<%= redirect %>");
		</aui:script>
	</c:when>
	<c:otherwise>

		<%
		String formName = "loginForm";

		if (windowState.equals(LiferayWindowState.EXCLUSIVE)) {
			formName += "Modal";
		}

		String login = (String)SessionErrors.get(renderRequest, "login");

		if (Validator.isNull(login)) {
			login = LoginUtil.getLogin(request, "login", company);
		}

		String password = StringPool.BLANK;
		boolean rememberMe = ParamUtil.getBoolean(request, "rememberMe");

		if (Validator.isNull(authType)) {
			authType = company.getAuthType();
		}
		%>

		<div class="login-component login-component-signin">
			<div class="login-container">
				<portlet:actionURL name="/login/login" secure="<%= PropsValues.COMPANY_SECURITY_AUTH_REQUIRES_HTTPS || request.isSecure() %>" var="loginURL">
					<portlet:param name="mvcRenderCommandName" value="/login/login" />
				</portlet:actionURL>

				<div class="login-header">
					<img class="raylife-logo" src="<%= themeDisplay.getPathThemeImages() %>/minium-logo.svg" />
				</div>

				<div class="login-body">
					<h1 class="login-title">Login to start!</h1>

					<aui:form action="<%= loginURL %>" autocomplete='<%= PropsValues.COMPANY_SECURITY_LOGIN_FORM_AUTOCOMPLETE ? "on" : "off" %>' cssClass="sign-in-form" method="post" name="<%= formName %>" onSubmit="event.preventDefault();" validateOnBlur="<%= false %>">
						<aui:input name="saveLastPath" type="hidden" value="<%= false %>" />
						<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
						<aui:input name="doActionAfterLogin" type="hidden" value="<%= portletName.equals(PortletKeys.FAST_LOGIN) ? true : false %>" />

						<div class="inline-alert-container lfr-alert-container"></div>

						<liferay-util:dynamic-include key="com.liferay.login.web#/login.jsp#alertPre" />

						<c:choose>
							<c:when test='<%= SessionMessages.contains(request, "passwordSent") %>'>
								<div class="alert alert-success">
									<liferay-ui:message key="An-email-has-been-sent-to-the-provided-email-address" />
								</div>
							</c:when>
							<c:when test='<%= SessionMessages.contains(request, "userAdded") %>'>

								<%
								String userEmailAddress = (String)SessionMessages.get(request, "userAdded");
								String userPassword = (String)SessionMessages.get(request, "userAddedPassword");
								%>

								<div class="alert alert-success">
									<c:choose>
										<c:when test="<%= company.isStrangersVerify() || Validator.isNull(userPassword) %>">
											<liferay-ui:message key="thank-you-for-creating-an-account" />

											<c:if test="<%= company.isStrangersVerify() %>">
												<liferay-ui:message arguments="<%= HtmlUtil.escape(userEmailAddress) %>" key="your-email-verification-code-was-sent-to-x" translateArguments="<%= false %>" />
											</c:if>
										</c:when>
										<c:otherwise>
											<liferay-ui:message arguments="<%= HtmlUtil.escape(userPassword) %>" key="thank-you-for-creating-an-account.-your-password-is-x" translateArguments="<%= false %>" />
										</c:otherwise>
									</c:choose>

									<c:if test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.ADMIN_EMAIL_USER_ADDED_ENABLED) %>">
										<liferay-ui:message arguments="<%= HtmlUtil.escape(userEmailAddress) %>" key="your-password-was-sent-to-x" translateArguments="<%= false %>" />
									</c:if>
								</div>
							</c:when>
							<c:when test='<%= SessionMessages.contains(request, "userPending") %>'>

								<%
								String userEmailAddress = (String)SessionMessages.get(request, "userPending");
								%>

								<div class="alert alert-success">
									<liferay-ui:message arguments="<%= HtmlUtil.escape(userEmailAddress) %>" key="thank-you-for-creating-an-account.-you-will-be-notified-via-email-at-x-when-your-account-has-been-approved" translateArguments="<%= false %>" />
								</div>
							</c:when>
						</c:choose>

						<liferay-ui:error exception="<%= AuthException.class %>" message="authentication-failed" />
						<liferay-ui:error exception="<%= CompanyMaxUsersException.class %>" message="unable-to-log-in-because-the-maximum-number-of-users-has-been-reached" />
						<liferay-ui:error exception="<%= CookieNotSupportedException.class %>" message="authentication-failed-please-enable-browser-cookies" />
						<liferay-ui:error exception="<%= NoSuchUserException.class %>" message="authentication-failed" />
						<liferay-ui:error exception="<%= PasswordExpiredException.class %>" message="your-password-has-expired" />
						<liferay-ui:error exception="<%= UserEmailAddressException.MustNotBeNull.class %>" message="please-enter-an-email-address" />
						<liferay-ui:error exception="<%= UserLockoutException.LDAPLockout.class %>" message="this-account-is-locked" />

						<liferay-ui:error exception="<%= UserLockoutException.PasswordPolicyLockout.class %>">

							<%
							UserLockoutException.PasswordPolicyLockout ule = (UserLockoutException.PasswordPolicyLockout)errorException;
							%>

							<c:choose>
								<c:when test="<%= ule.passwordPolicy.isRequireUnlock() %>">
									<liferay-ui:message key="this-account-is-locked" />
								</c:when>
								<c:otherwise>
									<liferay-ui:message arguments="<%= ule.user.getUnlockDate() %>" key="this-account-is-locked-until-x" translateArguments="<%= false %>" />
								</c:otherwise>
							</c:choose>
						</liferay-ui:error>

						<liferay-ui:error exception="<%= UserPasswordException.class %>" message="authentication-failed" />
						<liferay-ui:error exception="<%= UserScreenNameException.MustNotBeNull.class %>" message="the-screen-name-cannot-be-blank" />

						<liferay-util:dynamic-include key="com.liferay.login.web#/login.jsp#alertPost" />

						<aui:fieldset cssClass="gsdc-form-group">

							<%
							String loginLabel = null;

							if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
								loginLabel = "email-address";
							}
							else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
								loginLabel = "screen-name";
							}
							else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
								loginLabel = "id";
							}
							%>

							<aui:input autoFocus="<%= windowState.equals(LiferayWindowState.EXCLUSIVE) || windowState.equals(WindowState.MAXIMIZED) %>" cssClass="clearable" label="<%= loginLabel %>" name="login" showRequiredLabel="<%= false %>" type="text" value="<%= login %>">
								<aui:validator name="required" />
							</aui:input>

							<aui:input name="password" showRequiredLabel="<%= false %>" type="password" value="<%= password %>">
								<aui:validator name="required" />
							</aui:input>

							<span id="<portlet:namespace />passwordCapsLockSpan" style="display: none;"><liferay-ui:message key="caps-lock-is-on" /></span>

							<c:if test="<%= company.isAutoLogin() && !PropsValues.SESSION_DISABLED %>">
								<portlet:renderURL var="forgotPasswordURL">
									<portlet:param name="mvcRenderCommandName" value="/login/forgot_password" />
								</portlet:renderURL>

								<div class="gsdc-form-group form-group row ml-0 mr-0">
									<div class="col-xs-6 p-0">
										<aui:input checked="<%= rememberMe %>" label="remember-me" name="<portlet:namespace />rememberMe" type="checkbox" />
									</div>
									<div class="col-xs-6 p-0 text-right">
										<a class="forgot-password" href="<%= forgotPasswordURL %>">
											<liferay-ui:message key="forgot-password" />
										</a>
									</div>
								</div>
							</c:if>

							<portlet:renderURL var="registerURL">
								<portlet:param name="mvcRenderCommandName" value="/login/create_account" />
							</portlet:renderURL>


							<div class="gsdc-form-group form-group">
								<button class="btn btn-primary btn-block gsdc-btn-primary" type="submit">
									<liferay-ui:message key="login" />
								</button>
								<p class="login-or-register">or</p>
								<a href="<%= registerURL %>">
									<span class="btn btn-block btn-default">
										<liferay-ui:message key="create-account" />
									</span>
								</a>
							</div>

						</aui:fieldset>
					</aui:form>
				</div>

				<div class="login-footer">
				</div>
			</div>

			<div class="login-splash login-splash-create-account"></div>
		</div>

		<aui:script sandbox="<%= true %>">
			var form = document.getElementById('<portlet:namespace /><%= formName %>');

			if (form) {
				form.addEventListener(
					'submit',
					function(event) {
						<c:if test="<%= Validator.isNotNull(redirect) %>">
							var redirect = form.querySelector('#<portlet:namespace />redirect');

							if (redirect) {
								var redirectVal = redirect.getAttribute('value');

								redirect.setAttribute('value', redirectVal + window.location.hash);
							}
						</c:if>

						submitForm(form);
					}
				);

				var password = form.querySelector('#<portlet:namespace />password');

				if (password) {
					password.addEventListener(
						'keypress',
						function(event) {
							Liferay.Util.showCapsLock(event, '<portlet:namespace />passwordCapsLockSpan');
						}
					);
				}
			}
		</aui:script>
	</c:otherwise>
</c:choose>