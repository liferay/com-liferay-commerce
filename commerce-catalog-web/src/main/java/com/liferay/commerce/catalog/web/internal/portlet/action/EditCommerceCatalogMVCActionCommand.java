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
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.portal.kernel.exception.PortalException;
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
		"javax.portlet.name=" + CPPortletKeys.COMMERCE_CATALOGS,
		"mvc.command.name=editCommerceCatalog"
	},
	service = MVCActionCommand.class
)
public class EditCommerceCatalogMVCActionCommand extends BaseMVCActionCommand {

	protected void deleteCommerceCatalog(ActionRequest actionRequest)
		throws Exception {

		long[] commerceCatalogIds = null;

		long commerceCatalogId = ParamUtil.getLong(
			actionRequest, "commerceCatalogId");

		if (commerceCatalogId > 0) {
			commerceCatalogIds = new long[] {commerceCatalogId};
		}
		else {
			commerceCatalogIds = ParamUtil.getLongValues(
				actionRequest, "commerceCatalogIds");
		}

		for (long deleteCommerceCatalogId : commerceCatalogIds) {
			_commerceCatalogService.deleteCommerceCatalog(
				deleteCommerceCatalogId);
		}
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.DELETE)) {
				deleteCommerceCatalog(actionRequest);
			}
			else if (cmd.equals(Constants.ADD) ||
					 cmd.equals(Constants.UPDATE)) {

				String redirect = getSaveAndContinueRedirect(
					actionRequest, updateCommerceCatalog(actionRequest));

				sendRedirect(actionRequest, actionResponse, redirect);
			}
		}
		catch (PrincipalException pe) {
			SessionErrors.add(actionRequest, pe.getClass());

			actionResponse.setRenderParameter("mvcPath", "/error.jsp");
		}
	}

	protected String getSaveAndContinueRedirect(
			ActionRequest actionRequest, CommerceCatalog commerceCatalog)
		throws PortalException {

		PortletURL portletURL = _portal.getControlPanelPortletURL(
			actionRequest, CPPortletKeys.COMMERCE_CATALOGS,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcRenderCommandName", "editCommerceCatalog");

		portletURL.setParameter(
			"commerceCatalogId",
			String.valueOf(commerceCatalog.getCommerceCatalogId()));

		return portletURL.toString();
	}

	protected CommerceCatalog updateCommerceCatalog(ActionRequest actionRequest)
		throws Exception {

		String catalogDefaultLanguageId = ParamUtil.getString(
			actionRequest, "catalogDefaultLanguageId");
		long commerceCatalogId = ParamUtil.getLong(
			actionRequest, "commerceCatalogId");
		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "name");
		long parentCatalogId = ParamUtil.getLong(
			actionRequest, "parentCatalogId");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CommerceCatalog.class.getName(), actionRequest);

		if (commerceCatalogId <= 0) {
			return _commerceCatalogService.addCommerceCatalog(
				parentCatalogId, nameMap, catalogDefaultLanguageId,
				serviceContext);
		}

		return _commerceCatalogService.updateCommerceCatalog(
			commerceCatalogId, parentCatalogId, catalogDefaultLanguageId,
			nameMap, serviceContext);
	}

	@Reference
	private CommerceCatalogService _commerceCatalogService;

	@Reference
	private Portal _portal;

}