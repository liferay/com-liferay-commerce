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

package com.liferay.commerce.address.web.internal.portlet.action;

import com.liferay.commerce.admin.constants.CommerceAdminPortletKeys;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.starter.CommerceRegionsStarter;
import com.liferay.commerce.starter.CommerceRegionsStarterRegistry;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CommerceAdminPortletKeys.COMMERCE_ADMIN_VIRTUAL_INSTANCE,
		"mvc.command.name=importCommerceRegions"
	},
	service = MVCActionCommand.class
)
public class ImportCommerceRegionsMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		JSONObject jsonObject = _jsonFactory.createJSONObject();

		HttpServletRequest httpServletRequest = _portal.getHttpServletRequest(
			actionRequest);
		HttpServletResponse httpServletResponse =
			_portal.getHttpServletResponse(actionResponse);

		String key = ParamUtil.getString(actionRequest, "key");

		try {
			CommerceRegionsStarter commerceRegionsStarter =
				_commerceRegionsStarterRegistry.getCommerceRegionsStarter(key);

			if (commerceRegionsStarter != null) {
				ServiceContext serviceContext =
					ServiceContextFactory.getInstance(
						CommerceRegion.class.getName(), httpServletRequest);

				commerceRegionsStarter.start(serviceContext);

				jsonObject.put("success", true);
			}
		}
		catch (Exception e) {
			hideDefaultErrorMessage(actionRequest);

			_log.error(e, e);

			jsonObject.put("error", e.getMessage());
			jsonObject.put("success", false);
		}

		httpServletResponse.setContentType(ContentTypes.APPLICATION_JSON);

		writeJSON(actionResponse, jsonObject);

		hideDefaultSuccessMessage(actionRequest);
	}

	protected void writeJSON(ActionResponse actionResponse, Object jsonObj)
		throws IOException {

		HttpServletResponse httpServletResponse =
			_portal.getHttpServletResponse(actionResponse);

		httpServletResponse.setContentType(ContentTypes.APPLICATION_JSON);

		ServletResponseUtil.write(httpServletResponse, jsonObj.toString());

		httpServletResponse.flushBuffer();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ImportCommerceRegionsMVCActionCommand.class);

	@Reference
	private CommerceRegionsStarterRegistry _commerceRegionsStarterRegistry;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Portal _portal;

}