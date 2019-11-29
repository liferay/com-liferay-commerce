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

package com.liferay.commerce.frontend.fragment.renderer;

import com.liferay.fragment.renderer.FragmentRenderer;
import com.liferay.fragment.renderer.FragmentRendererContext;
import com.liferay.info.item.renderer.InfoItemRenderer;
import com.liferay.info.item.renderer.InfoItemRendererTracker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Gianmarco Brunialti Masera
 */
@Component(service = FragmentRenderer.class)
public class GalleryFragmentRenderer extends BaseCommerceFragmentRenderer {

	@Override
	public void render(
		FragmentRendererContext fragmentRendererContext,
		HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute(
			"fragmentRendererContext", fragmentRendererContext);

		InfoItemRenderer infoItemRenderer =
			_infoItemRendererTracker.getInfoItemRenderer(
				INFO_ITEM_RENDERER_PACKAGE);
	}

	@Override
	protected String getFragmentName() {
		return FRAGMENT_NAME;
	}

	private static final String FRAGMENT_NAME = "gallery";

	@Reference
	private InfoItemRendererTracker _infoItemRendererTracker;

}