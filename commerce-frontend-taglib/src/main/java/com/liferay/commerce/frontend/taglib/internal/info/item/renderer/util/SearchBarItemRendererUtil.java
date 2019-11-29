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

package com.liferay.commerce.frontend.taglib.internal.info.item.renderer.util;

import com.liferay.commerce.frontend.taglib.internal.info.item.renderer.SearchBarItemRenderer;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Gianmarco Brunialti Masera
 */
@Component(immediate = true, service = SearchBarItemRendererUtil.class)
public class SearchBarItemRendererUtil {

	public static final SearchBarItemRenderer getRenderer() {
		return _searchBarItemRendererUtil._getRenderer();
	}

	@Activate
	protected void activate() {
		_searchBarItemRendererUtil = this;
	}

	@Deactivate
	protected void deactivate() {
		_searchBarItemRendererUtil = null;
	}

	@Reference(unbind = "-")
	protected void setSearchBarItemRenderer(
		SearchBarItemRenderer searchBarItemRenderer) {

		_searchBarItemRenderer = searchBarItemRenderer;
	}

	private SearchBarItemRenderer _getRenderer() {
		return _searchBarItemRenderer;
	}

	private static SearchBarItemRendererUtil _searchBarItemRendererUtil;

	private SearchBarItemRenderer _searchBarItemRenderer;

}