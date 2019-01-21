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

package com.liferay.commerce.admin.web.internal.portlet;

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.admin.constants.CommerceAdminPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.settings.ModifiableSettings;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.SettingsFactory;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PropertiesParamUtil;

import java.util.Map;

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
		"javax.portlet.name=" + CommerceAdminPortletKeys.COMMERCE_ADMIN,
		"mvc.command.name=editCommerceAccountSettings"
	},
	service = MVCActionCommand.class
)
public class EditCommerceAccountSettingsMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			Map<String, String> parameterMap =
				PropertiesParamUtil.getProperties(actionRequest, "settings--");

			Settings settings = _settingsFactory.getSettings(
				new GroupServiceSettingsLocator(
					_portal.getScopeGroupId(actionRequest),
					CommerceAccountConstants.SERVICE_NAME));

			ModifiableSettings modifiableSettings =
				settings.getModifiableSettings();

			for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
				modifiableSettings.setValue(entry.getKey(), entry.getValue());
			}

			modifiableSettings.store();
		}
		catch (PrincipalException pe) {
			hideDefaultErrorMessage(actionRequest);
			hideDefaultSuccessMessage(actionRequest);

			SessionErrors.add(actionRequest, pe.getClass());

			String redirect = ParamUtil.getString(actionRequest, "redirect");

			sendRedirect(actionRequest, actionResponse, redirect);
		}
	}

	@Reference
	private Portal _portal;

	@Reference
	private SettingsFactory _settingsFactory;

}