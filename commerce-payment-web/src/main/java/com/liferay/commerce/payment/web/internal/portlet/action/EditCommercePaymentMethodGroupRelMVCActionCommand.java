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

package com.liferay.commerce.payment.web.internal.portlet.action;

import com.liferay.commerce.admin.constants.CommerceAdminPortletKeys;
import com.liferay.commerce.exception.NoSuchPaymentMethodException;
import com.liferay.commerce.payment.exception.CommercePaymentMethodGroupRelNameException;
import com.liferay.commerce.payment.method.CommercePaymentMethod;
import com.liferay.commerce.payment.method.CommercePaymentMethodRegistry;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PropertiesParamUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.File;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Di Giorgi
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CommerceAdminPortletKeys.COMMERCE_ADMIN_GROUP_INSTANCE,
		"mvc.command.name=editCommercePaymentMethodGroupRel"
	},
	service = MVCActionCommand.class
)
public class EditCommercePaymentMethodGroupRelMVCActionCommand
	extends BaseMVCActionCommand {

	protected CommercePaymentMethodGroupRel createCommercePaymentMethodGroupRel(
			ActionRequest actionRequest)
		throws PortalException {

		return createCommercePaymentMethodGroupRel(actionRequest, false);
	}

	protected CommercePaymentMethodGroupRel createCommercePaymentMethodGroupRel(
			ActionRequest actionRequest, boolean active)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Locale siteDefaultLocale = themeDisplay.getSiteDefaultLocale();

		String engineKey = ParamUtil.getString(actionRequest, "engineKey");

		CommercePaymentMethod commercePaymentMethod =
			_commercePaymentMethodRegistry.getCommercePaymentMethod(engineKey);

		Map<Locale, String> nameMap = new HashMap<>();
		Map<Locale, String> descriptionMap = new HashMap<>();

		nameMap.put(
			siteDefaultLocale,
			commercePaymentMethod.getName(siteDefaultLocale));
		descriptionMap.put(
			siteDefaultLocale,
			commercePaymentMethod.getDescription(siteDefaultLocale));

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CommercePaymentMethodGroupRel.class.getName(), actionRequest);

		return _commercePaymentMethodGroupRelService.
			addCommercePaymentMethodGroupRel(
				nameMap, descriptionMap, null, engineKey,
				new HashMap<String, String>(), 0, active, serviceContext);
	}

	protected void deleteCommercePaymentMethodGroupRel(
			ActionRequest actionRequest)
		throws PortalException {

		long commercePaymentMethodGroupRelId = ParamUtil.getLong(
			actionRequest, "commercePaymentMethodGroupRelId");

		_commercePaymentMethodGroupRelService.
			deleteCommercePaymentMethodGroupRel(
				commercePaymentMethodGroupRelId);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.DELETE)) {
				deleteCommercePaymentMethodGroupRel(actionRequest);
			}
			else if (cmd.equals(Constants.EDIT)) {
				editCommercePaymentMethodGroupRel(
					actionRequest, actionResponse);

				hideDefaultErrorMessage(actionRequest);
				hideDefaultSuccessMessage(actionRequest);
			}
			else if (cmd.equals(Constants.ADD) ||
					 cmd.equals(Constants.UPDATE)) {

				updateCommercePaymentMethodGroupRel(actionRequest);
			}
			else if (cmd.equals("setActive")) {
				setActive(actionRequest);
			}
			else if (cmd.equals("viewRestrictions")) {
				viewRestrictions(actionRequest, actionResponse);

				hideDefaultErrorMessage(actionRequest);
				hideDefaultSuccessMessage(actionRequest);
			}
		}
		catch (Exception e) {
			if (e instanceof NoSuchPaymentMethodException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass());

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");
			}
			else if (e instanceof CommercePaymentMethodGroupRelNameException) {
				hideDefaultErrorMessage(actionRequest);
				hideDefaultSuccessMessage(actionRequest);

				SessionErrors.add(actionRequest, e.getClass());

				actionResponse.setRenderParameter(
					"mvcRenderCommandName",
					"editCommercePaymentMethodGroupRel");
			}
			else {
				throw e;
			}
		}
	}

	protected void editCommercePaymentMethodGroupRel(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String redirect = null;

		long commercePaymentMethodGroupRelId = ParamUtil.getLong(
			actionRequest, "commercePaymentMethodGroupRelId");

		if (commercePaymentMethodGroupRelId > 0) {
			redirect = getRedirectURL(
				actionRequest, commercePaymentMethodGroupRelId,
				"editCommercePaymentMethodGroupRel");
		}
		else {
			CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
				createCommercePaymentMethodGroupRel(actionRequest);

			redirect = getRedirectURL(
				actionRequest,
				commercePaymentMethodGroupRel.
					getCommercePaymentMethodGroupRelId(),
				"editCommercePaymentMethodGroupRel");
		}

		sendRedirect(actionRequest, actionResponse, redirect);
	}

	protected String getRedirectURL(
		ActionRequest actionRequest, long commercePaymentMethodGroupRelId,
		String mvcRenderCommandName) {

		PortletURL portletURL = _portal.getControlPanelPortletURL(
			actionRequest,
			CommerceAdminPortletKeys.COMMERCE_ADMIN_GROUP_INSTANCE,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcRenderCommandName", mvcRenderCommandName);
		portletURL.setParameter(
			"commercePaymentMethodGroupRelId",
			String.valueOf(commercePaymentMethodGroupRelId));

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		if (Validator.isNotNull(redirect)) {
			portletURL.setParameter("redirect", redirect);
		}

		String engineKey = ParamUtil.getString(actionRequest, "engineKey");

		if (Validator.isNotNull(engineKey)) {
			portletURL.setParameter("engineKey", engineKey);
		}

		return portletURL.toString();
	}

	protected void setActive(ActionRequest actionRequest) throws Exception {
		long commercePaymentMethodGroupRelId = ParamUtil.getLong(
			actionRequest, "commercePaymentMethodGroupRelId");

		boolean active = ParamUtil.getBoolean(actionRequest, "active");

		if (commercePaymentMethodGroupRelId > 0) {
			_commercePaymentMethodGroupRelService.setActive(
				commercePaymentMethodGroupRelId, active);
		}
		else {
			createCommercePaymentMethodGroupRel(actionRequest, active);
		}
	}

	protected CommercePaymentMethodGroupRel updateCommercePaymentMethodGroupRel(
			ActionRequest actionRequest)
		throws PortalException {

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel = null;

		UploadPortletRequest uploadPortletRequest =
			_portal.getUploadPortletRequest(actionRequest);

		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "name");
		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(actionRequest, "description");
		File imageFile = uploadPortletRequest.getFile("imageFile");
		String engineKey = ParamUtil.getString(actionRequest, "engineKey");
		UnicodeProperties engineParameterMap =
			PropertiesParamUtil.getProperties(actionRequest, "settings--");
		double priority = ParamUtil.getDouble(actionRequest, "priority");
		boolean active = ParamUtil.getBoolean(actionRequest, "active");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CommercePaymentMethodGroupRel.class.getName(), actionRequest);

		long commercePaymentMethodGroupRelId = ParamUtil.getLong(
			actionRequest, "commercePaymentMethodGroupRelId");

		if (commercePaymentMethodGroupRelId <= 0) {
			commercePaymentMethodGroupRel =
				_commercePaymentMethodGroupRelService.
					addCommercePaymentMethodGroupRel(
						nameMap, descriptionMap, imageFile, engineKey,
						engineParameterMap, priority, active, serviceContext);
		}
		else {
			commercePaymentMethodGroupRel =
				_commercePaymentMethodGroupRelService.
					updateCommercePaymentMethodGroupRel(
						commercePaymentMethodGroupRelId, nameMap,
						descriptionMap, imageFile, engineParameterMap, priority,
						active, serviceContext);
		}

		return commercePaymentMethodGroupRel;
	}

	protected void viewRestrictions(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String redirect = null;

		long commercePaymentMethodGroupRelId = ParamUtil.getLong(
			actionRequest, "commercePaymentMethodGroupRelId");

		if (commercePaymentMethodGroupRelId > 0) {
			redirect = getRedirectURL(
				actionRequest, commercePaymentMethodGroupRelId,
				"viewCommercePaymentMethodGroupRelAddressRestriction");
		}
		else {
			CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
				createCommercePaymentMethodGroupRel(actionRequest);

			redirect = getRedirectURL(
				actionRequest,
				commercePaymentMethodGroupRel.
					getCommercePaymentMethodGroupRelId(),
				"viewCommercePaymentMethodGroupRelAddressRestriction");
		}

		sendRedirect(actionRequest, actionResponse, redirect);
	}

	@Reference
	private CommercePaymentMethodGroupRelService
		_commercePaymentMethodGroupRelService;

	@Reference
	private CommercePaymentMethodRegistry _commercePaymentMethodRegistry;

	@Reference
	private Portal _portal;

}