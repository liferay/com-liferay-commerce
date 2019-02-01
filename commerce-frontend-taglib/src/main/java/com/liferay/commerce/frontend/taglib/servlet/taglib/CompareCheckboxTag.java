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

import com.liferay.commerce.frontend.taglib.internal.js.loader.modules.extender.npm.NPMResolverProvider;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag;
import com.liferay.petra.string.StringPool;

/**
 * @author Fabio Diego Mastrorilli
 */
public class CompareCheckboxTag extends ComponentRendererTag {

	@Override
	public int doStartTag() {
		putValue("checkboxVisible", true);
		putValue("compareAvailable", true);
		putValue("inCompare", false);
		putValue("labelVisible", true);
		putValue(
			"pictureUrl",
			"/o/commerce-media/products/45626/coil-spring-rear/45645" +
				"/Minium_ProductImage_13.png?download=false");
		putValue("productId", 45625);

		setTemplateNamespace("CompareCheckbox.render");

		return super.doStartTag();
	}

	@Override
	public String getModule() {
		NPMResolver npmResolver = NPMResolverProvider.getNPMResolver();

		if (npmResolver == null) {
			return StringPool.BLANK;
		}

		return npmResolver.resolveModuleName(
			"commerce-frontend-taglib/compare_checkbox/CompareCheckbox.es");
	}

}