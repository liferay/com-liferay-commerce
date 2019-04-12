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

package com.liferay.commerce.frontend.taglib.servlet.taglib;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.frontend.taglib.internal.js.loader.modules.extender.npm.NPMResolverProvider;
import com.liferay.commerce.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.CommerceOrderHttpHelper;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Collections;

import javax.portlet.PortletURL;

import javax.servlet.jsp.PageContext;
import java.util.Map;

/**
 * @author Fabio Mastorilli
 */
public class DatalistTag extends ComponentRendererTag {

	@Override
	public int doStartTag() {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		Map<String, Object> context = getContext();
		String name = GetterUtil.getString(
			context.get("name"));
		String additionalClasses = GetterUtil.getString(
			context.get("additionalClasses"));
		String label = GetterUtil.getString(
			context.get("label"));
		boolean multiselect = GetterUtil.getBoolean(
			context.get("multiselect"));

		String id = GetterUtil.getString(context.get("id"));

		putValue( "name", name);
		putValue( "additionalClasses", additionalClasses);
		putValue( "label", label);
		putValue( "multiselect", multiselect);
		putValue( "spritemap", themeDisplay.getPathThemeImages() + "/commerce-icons.svg");

		setWrapper(false);
		setComponentId(id);

		setTemplateNamespace("Datalist.render");
		return super.doStartTag();
	}

	@Override
	public String getModule() {
		NPMResolver npmResolver = NPMResolverProvider.getNPMResolver();

		if (npmResolver == null) {
			return StringPool.BLANK;
		}

		return npmResolver.resolveModuleName(
			"commerce-frontend-taglib/datalist/Datalist.es");
	}

	public void setName(String name) {
		putValue("name", name);
	}

	public void setId(String id) {
		putValue("id", id);
	}

	public void setLabel(String label) {
		putValue("label", label);
	}

	public void setAdditionalClasses(String additionalClasses) {
		putValue("additionalClasses", additionalClasses);
	}

	public void setMultiselect(boolean multiselect) {
		putValue("multiselect", multiselect);
	}

	private static final Log _log = LogFactoryUtil.getLog(DatalistTag.class);

	private CommerceOrderHttpHelper _commerceOrderHttpHelper;

}