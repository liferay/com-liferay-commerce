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

package com.liferay.commerce.warehouse.web.internal.servlet.taglib.ui;

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.BaseJSPFormNavigatorEntry;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;

import java.util.Locale;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Di Giorgi
 */
@Component(
	property = "form.navigator.entry.order:Integer=10",
	service = FormNavigatorEntry.class
)
public class CommerceWarehouseGeolocationFormNavigatorEntry
	extends BaseJSPFormNavigatorEntry<CommerceInventoryWarehouse> {

	@Override
	public String getCategoryKey() {
		return CommerceWarehouseFormNavigatorConstants.
			CATEGORY_KEY_COMMERCE_WAREHOUSE_GENERAL;
	}

	@Override
	public String getFormNavigatorId() {
		return CommerceWarehouseFormNavigatorConstants.
			FORM_NAVIGATOR_ID_COMMERCE_WAREHOUSE;
	}

	@Override
	public String getKey() {
		return "geolocation";
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, getKey());
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.warehouse.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	@Override
	protected String getJspPath() {
		return "/warehouse/geolocation.jsp";
	}

}