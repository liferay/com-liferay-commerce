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

package com.liferay.commerce.product.definitions.web.internal.admin;

import com.liferay.commerce.admin.CommerceAdminModule;
import com.liferay.commerce.admin.constants.CommerceAdminConstants;
import com.liferay.commerce.product.definitions.web.internal.display.context.CPDefinitionDisplayLayoutDisplayContext;
import com.liferay.commerce.product.definitions.web.portlet.action.ActionHelper;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.commerce.product.service.CPDisplayLayoutService;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.item.selector.ItemSelector;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.Locale;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "commerce.admin.module.key=" + ProductDisplayLayoutsCommerceAdminModule.KEY,
	service = CommerceAdminModule.class
)
public class ProductDisplayLayoutsCommerceAdminModule
	implements CommerceAdminModule {

	public static final String KEY = "product-display-pages";

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, KEY);
	}

	@Override
	public PortletURL getSearchURL(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		return null;
	}

	@Override
	public int getType() {
		return CommerceAdminConstants.COMMERCE_ADMIN_TYPE_GROUP_INSTANCE;
	}

	@Override
	public boolean isVisible(long groupId) throws PortalException {
		return GroupPermissionUtil.contains(
			PermissionThreadLocal.getPermissionChecker(), groupId,
			ActionKeys.ADD_LAYOUT);
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		setCPDefinitionDisplayLayoutDisplayContext(httpServletRequest);

		_jspRenderer.renderJSP(
			_servletContext, httpServletRequest, httpServletResponse,
			"/display_layout/view.jsp");
	}

	protected CPDefinitionDisplayLayoutDisplayContext
		setCPDefinitionDisplayLayoutDisplayContext(
			HttpServletRequest httpServletRequest) {

		CPDefinitionDisplayLayoutDisplayContext
			cpDefinitionDisplayLayoutDisplayContext =
				(CPDefinitionDisplayLayoutDisplayContext)
					httpServletRequest.getAttribute(
						WebKeys.PORTLET_DISPLAY_CONTEXT);

		if (cpDefinitionDisplayLayoutDisplayContext == null) {
			cpDefinitionDisplayLayoutDisplayContext =
				new CPDefinitionDisplayLayoutDisplayContext(
					_actionHelper, httpServletRequest, _commerceCatalogService,
					_cpDefinitionService, _cpDisplayLayoutService,
					_itemSelector);

			httpServletRequest.setAttribute(
				WebKeys.PORTLET_DISPLAY_CONTEXT,
				cpDefinitionDisplayLayoutDisplayContext);
		}

		return cpDefinitionDisplayLayoutDisplayContext;
	}

	@Reference
	private ActionHelper _actionHelper;

	@Reference
	private CommerceCatalogService _commerceCatalogService;

	@Reference
	private CPDefinitionService _cpDefinitionService;

	@Reference
	private CPDisplayLayoutService _cpDisplayLayoutService;

	@Reference
	private ItemSelector _itemSelector;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.product.definitions.web)"
	)
	private ServletContext _servletContext;

}