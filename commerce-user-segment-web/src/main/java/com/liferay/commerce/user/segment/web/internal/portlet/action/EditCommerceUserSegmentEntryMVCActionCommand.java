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

package com.liferay.commerce.user.segment.web.internal.portlet.action;

import com.liferay.commerce.user.segment.constants.CommerceUserSegmentPortletKeys;
import com.liferay.commerce.user.segment.exception.CommerceUserSegmentEntryKeyException;
import com.liferay.commerce.user.segment.exception.CommerceUserSegmentEntryNameException;
import com.liferay.commerce.user.segment.exception.CommerceUserSegmentEntrySystemException;
import com.liferay.commerce.user.segment.exception.NoSuchUserSegmentEntryException;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentEntry;
import com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CommerceUserSegmentPortletKeys.COMMERCE_USER_SEGMENT,
		"mvc.command.name=editCommerceUserSegmentEntry"
	},
	service = MVCActionCommand.class
)
public class EditCommerceUserSegmentEntryMVCActionCommand
	extends BaseMVCActionCommand {

	protected void addCommerceUserSegmentEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String name = ParamUtil.getString(actionRequest, "name");

		String key = ParamUtil.getString(actionRequest, "key", name);

		boolean active = ParamUtil.getBoolean(actionRequest, "active", true);
		double priority = ParamUtil.getDouble(actionRequest, "priority");

		Map<Locale, String> nameMap = new HashMap<>();

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CommerceUserSegmentEntry.class.getName(), actionRequest);

		nameMap.put(_portal.getLocale(actionRequest), name);

		JSONObject jsonObject = _jsonFactory.createJSONObject();

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		jsonObject.put("redirectURL", redirect);

		try {
			_commerceUserSegmentEntryService.addCommerceUserSegmentEntry(
				nameMap, key, active, false, priority, serviceContext);
		}
		catch (Exception e) {
			Locale locale = _portal.getLocale(actionRequest);

			ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
				"content.Language", locale, getClass());

			if (e instanceof CommerceUserSegmentEntryKeyException) {
				jsonObject.put(
					"error",
					LanguageUtil.get(
						resourceBundle, "that-key-is-already-used"));
			}
			else if (e instanceof CommerceUserSegmentEntryNameException) {
				jsonObject.put(
					"error",
					LanguageUtil.get(locale, "please-enter-a-valid-name"));
			}
			else {
				_log.error(e, e);

				jsonObject.put(
					"error",
					LanguageUtil.get(
						resourceBundle, "an-unexpected-error-occurred"));
			}
		}

		hideDefaultSuccessMessage(actionRequest);

		JSONPortletResponseUtil.writeJSON(
			actionRequest, actionResponse, jsonObject);
	}

	protected void deleteCommerceUserSegmentEntries(ActionRequest actionRequest)
		throws PortalException {

		long[] deleteCommerceUserSegmentEntryIds = null;

		long commerceUserSegmentEntryId = ParamUtil.getLong(
			actionRequest, "commerceUserSegmentEntryId");

		if (commerceUserSegmentEntryId > 0) {
			deleteCommerceUserSegmentEntryIds = new long[] {
				commerceUserSegmentEntryId
			};
		}
		else {
			deleteCommerceUserSegmentEntryIds = StringUtil.split(
				ParamUtil.getString(
					actionRequest, "deleteCommerceUserSegmentEntryIds"),
				0L);
		}

		for (long deleteCommerceUserSegmentEntryId :
				deleteCommerceUserSegmentEntryIds) {

			_commerceUserSegmentEntryService.deleteCommerceUserSegmentEntry(
				deleteCommerceUserSegmentEntryId);
		}
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.ADD)) {
				addCommerceUserSegmentEntry(actionRequest, actionResponse);
			}
			else if (cmd.equals(Constants.UPDATE)) {
				updateCommerceUserSegmentEntry(actionRequest);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteCommerceUserSegmentEntries(actionRequest);
			}
		}
		catch (Exception e) {
			if (e instanceof CommerceUserSegmentEntryKeyException ||
				e instanceof CommerceUserSegmentEntrySystemException ||
				e instanceof NoSuchUserSegmentEntryException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass());

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");
			}
			else {
				throw e;
			}
		}
	}

	protected void updateCommerceUserSegmentEntry(ActionRequest actionRequest)
		throws Exception {

		long commerceUserSegmentEntryId = ParamUtil.getLong(
			actionRequest, "commerceUserSegmentEntryId");

		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "name");

		String key = ParamUtil.getString(actionRequest, "key");
		boolean active = ParamUtil.getBoolean(actionRequest, "active", true);
		double priority = ParamUtil.getDouble(actionRequest, "priority");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CommerceUserSegmentEntry.class.getName(), actionRequest);

		_commerceUserSegmentEntryService.updateCommerceUserSegmentEntry(
			commerceUserSegmentEntryId, nameMap, key, active, priority,
			serviceContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditCommerceUserSegmentEntryMVCActionCommand.class);

	@Reference
	private CommerceUserSegmentEntryService _commerceUserSegmentEntryService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Portal _portal;

}