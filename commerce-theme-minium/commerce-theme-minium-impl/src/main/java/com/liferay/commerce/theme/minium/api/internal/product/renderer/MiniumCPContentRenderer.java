/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.theme.minium.api.internal.product.renderer;

import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.content.render.CPContentRenderer;
import com.liferay.commerce.product.type.grouped.util.GroupedCPTypeHelper;
import com.liferay.commerce.product.type.virtual.util.VirtualCPTypeHelper;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(
	immediate = true,
	property = {
		"commerce.product.content.renderer.key=" + MiniumCPContentRenderer.KEY,
		"commerce.product.content.renderer.type=grouped",
		"commerce.product.content.renderer.type=simple",
		"commerce.product.content.renderer.type=virtual"
	},
	service = CPContentRenderer.class
)
public class MiniumCPContentRenderer implements CPContentRenderer {

	public static final String KEY = "minium";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, KEY);
	}

	@Override
	public void render(
			CPCatalogEntry cpCatalogEntry,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		httpServletRequest.setAttribute(
			"groupedCPTypeHelper", _groupedCPTypeHelper);
		httpServletRequest.setAttribute(
			"virtualCPTypeHelper", _virtualCPTypeHelper);

		_jspRenderer.renderJSP(
			_servletContext, httpServletRequest, httpServletResponse,
			"/render/view.jsp");
	}

	@Reference
	private GroupedCPTypeHelper _groupedCPTypeHelper;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.theme.minium.impl)"
	)
	private ServletContext _servletContext;

	@Reference
	private VirtualCPTypeHelper _virtualCPTypeHelper;

}