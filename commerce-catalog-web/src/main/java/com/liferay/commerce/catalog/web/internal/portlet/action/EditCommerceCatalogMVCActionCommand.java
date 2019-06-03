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
import com.liferay.commerce.product.exception.CommerceCatalogSystemException;
import com.liferay.commerce.product.exception.NoSuchCatalogException;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogService;
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

				updateCommerceCatalog(actionRequest);
			}
		}
		catch (Exception e) {
			if (e instanceof CommerceCatalogSystemException) {
				hideDefaultErrorMessage(actionRequest);

				SessionErrors.add(actionRequest, e.getClass());

				String redirect = _portal.getCurrentURL(actionRequest);

				sendRedirect(actionRequest, actionResponse, redirect);
			}
			else if (e instanceof NoSuchCatalogException ||
					 e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass());

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");
			}
		}
	}

	protected CommerceCatalog updateCommerceCatalog(ActionRequest actionRequest)
		throws Exception {

		long commerceCatalogId = ParamUtil.getLong(
			actionRequest, "commerceCatalogId");

		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "name");
		String commerceCurrencyCode = ParamUtil.getString(
			actionRequest, "commerceCurrencyCode");
		String catalogDefaultLanguageId = ParamUtil.getString(
			actionRequest, "catalogDefaultLanguageId");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CommerceCatalog.class.getName(), actionRequest);

		if (commerceCatalogId <= 0) {
			return _commerceCatalogService.addCommerceCatalog(
				nameMap, commerceCurrencyCode, catalogDefaultLanguageId, null,
				serviceContext);
		}

		return _commerceCatalogService.updateCommerceCatalog(
			commerceCatalogId, nameMap, commerceCurrencyCode,
			catalogDefaultLanguageId);
	}

	@Reference
	private CommerceCatalogService _commerceCatalogService;

	@Reference
	private Portal _portal;

}