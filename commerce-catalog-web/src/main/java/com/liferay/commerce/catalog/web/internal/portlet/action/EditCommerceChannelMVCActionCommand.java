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

package com.liferay.commerce.catalog.web.internal.portlet.action;

import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.constants.CommerceChannelConstants;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CPPortletKeys.COMMERCE_CHANNELS,
		"mvc.command.name=editCommerceChannel"
	},
	service = MVCActionCommand.class
)
public class EditCommerceChannelMVCActionCommand extends BaseMVCActionCommand {

	protected void deleteCommerceChannel(ActionRequest actionRequest)
		throws Exception {

		long[] commerceChannelIds = null;

		long commerceChannelId = ParamUtil.getLong(
			actionRequest, "commerceChannelId");

		if (commerceChannelId > 0) {
			commerceChannelIds = new long[] {commerceChannelId};
		}
		else {
			commerceChannelIds = ParamUtil.getLongValues(
				actionRequest, "commerceChannelIds");
		}

		for (long deleteCommerceChannelId : commerceChannelIds) {
			_commerceChannelService.deleteCommerceChannel(
				deleteCommerceChannelId);
		}
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.DELETE)) {
				deleteCommerceChannel(actionRequest);
			}
			else if (cmd.equals(Constants.ADD) ||
					 cmd.equals(Constants.UPDATE)) {

				String redirect = getSaveAndContinueRedirect(
					actionRequest, updateCommerceChannel(actionRequest));

				sendRedirect(actionRequest, actionResponse, redirect);
			}
		}
		catch (PrincipalException pe) {
			SessionErrors.add(actionRequest, pe.getClass());

			actionResponse.setRenderParameter("mvcPath", "/error.jsp");
		}
	}

	protected String getSaveAndContinueRedirect(
			ActionRequest actionRequest, CommerceChannel commerceChannel)
		throws PortalException {

		PortletURL portletURL = _portal.getControlPanelPortletURL(
			actionRequest, CPPortletKeys.COMMERCE_CHANNELS,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
			"commerceChannelId",
			String.valueOf(commerceChannel.getCommerceChannelId()));
		portletURL.setParameter("mvcRenderCommandName", "editCommerceChannel");

		return portletURL.toString();
	}

	protected String getTypeSettings(
			long commerceChannelId, String type, ActionRequest actionRequest)
		throws PortalException {

		String typeSettings = ParamUtil.getString(
			actionRequest, "typeSettings");

		if (Validator.isNotNull(typeSettings)) {
			return typeSettings;
		}

		CommerceChannel commerceChannel = null;

		if (commerceChannelId > 0) {
			commerceChannel = _commerceChannelService.getCommerceChannels(
				-1, -1
			).get(
				0
			);
		}

		if ((commerceChannel != null) &&
			type.equals(commerceChannel.getType())) {

			typeSettings = commerceChannel.getTypeSettings();
		}

		String[] typeSettingsArray = StringUtil.split(typeSettings);

		String[] addTypeSettings = ParamUtil.getStringValues(
			actionRequest, "addTypeSettings");

		String[] deleteTypeSettings = ParamUtil.getStringValues(
			actionRequest, "deleteTypeSettings");

		if (deleteTypeSettings.length > 0) {
			for (String deleteTypeSetting : deleteTypeSettings) {
				typeSettingsArray = ArrayUtil.remove(
					typeSettingsArray, deleteTypeSetting);
			}
		}

		if (addTypeSettings.length > 0) {
			typeSettingsArray = ArrayUtil.append(
				typeSettingsArray, addTypeSettings);
		}

		return StringUtil.merge(ArrayUtil.unique(typeSettingsArray));
	}

	protected CommerceChannel updateCommerceChannel(ActionRequest actionRequest)
		throws Exception {

		long commerceChannelId = ParamUtil.getLong(
			actionRequest, "commerceChannelId");
		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "name");
		String type = ParamUtil.getString(actionRequest, "type");

		String typeSettings = getTypeSettings(
			commerceChannelId, type, actionRequest);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CommerceChannel.class.getName(), actionRequest);

		if (commerceChannelId <= 0) {
			return _commerceChannelService.addCommerceChannel(
				nameMap, CommerceChannelConstants.FILTER_TYPE_AND, type,
				typeSettings, serviceContext);
		}

		return _commerceChannelService.updateCommerceChannel(
			commerceChannelId, nameMap,
			CommerceChannelConstants.FILTER_TYPE_AND, type, typeSettings,
			serviceContext);
	}

	@Reference
	private CommerceChannelService _commerceChannelService;

	@Reference
	private Portal _portal;

}