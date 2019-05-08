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

import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.frontend.model.PriceModel;
import com.liferay.commerce.frontend.model.ProductSettingsModel;
import com.liferay.commerce.frontend.taglib.internal.js.loader.modules.extender.npm.NPMResolverProvider;
import com.liferay.commerce.frontend.taglib.internal.util.ProductHelperProvider;
import com.liferay.commerce.frontend.util.ProductHelper;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Map;
import java.util.List;

import javax.servlet.jsp.PageContext;

/**
 * @author Fabio Mastrorilli
 */
public class GalleryTag extends ComponentRendererTag {

	@Override
	public int doStartTag() {
			Map<String, Object> context = getContext();

			putValue("images", context.get("images"));
			putValue("selected", context.get("selected"));

			setTemplateNamespace("Gallery.render");

		return super.doStartTag();
	}

	@Override
	public String getModule() {
		NPMResolver npmResolver = NPMResolverProvider.getNPMResolver();

		if (npmResolver == null) {
			return StringPool.BLANK;
		}

		return npmResolver.resolveModuleName(
			"commerce-frontend-taglib/gallery/Gallery.es");
	}
	
	public void setImages(List images) {
		putValue("images", images);
	}

	public void setSelected(int selected) {
		putValue("selected", selected);
	}

	public void setId(String id) {
		putValue("id", id);
	}

	private static final Log _log = LogFactoryUtil.getLog(GalleryTag.class);

}