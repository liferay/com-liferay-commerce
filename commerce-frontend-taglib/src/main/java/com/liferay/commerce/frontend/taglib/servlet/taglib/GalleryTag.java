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

import com.liferay.commerce.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.commerce.product.catalog.CPMedia;
import com.liferay.commerce.product.content.util.CPHttpContentHelper;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.PageContext;

/**
 * @author Fabio Mastrorilli
 */
public class GalleryTag extends ComponentRendererTag {

	@Override
	public int doStartTag() {
		Map<String, Object> context = getContext();

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String id = GetterUtil.getString(context.get("id"));
		long cpDefinitionId = GetterUtil.getLong(context.get("cpDefinitionId"));

		List<CPMedia> productImages = Collections.emptyList();

		try {
			productImages = _cpHttpContentHelper.getImages(
				cpDefinitionId, themeDisplay);
		}
		catch (PortalException pe) {
			_log.error(pe, pe);
		}

		putValue("images", productImages);
		putValue("selected", 0);

		if (Validator.isNotNull(id)) {
			setComponentId(id);
		}

		setTemplateNamespace("Gallery.render");

		return super.doStartTag();
	}

	@Override
	public String getModule() {
		NPMResolver npmResolver = ServletContextUtil.getNPMResolver();

		if (npmResolver == null) {
			return StringPool.BLANK;
		}

		return npmResolver.resolveModuleName(
			"commerce-frontend-taglib/gallery/Gallery.es");
	}

	public void setCPDefinitionId(long cpDefinitionId) {
		putValue("cpDefinitionId", cpDefinitionId);
	}

	public void setId(String id) {
		putValue("id", id);
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		_cpHttpContentHelper = ServletContextUtil.getCPHttpContentHelper();
	}

	private static final Log _log = LogFactoryUtil.getLog(GalleryTag.class);

	private CPHttpContentHelper _cpHttpContentHelper;

}