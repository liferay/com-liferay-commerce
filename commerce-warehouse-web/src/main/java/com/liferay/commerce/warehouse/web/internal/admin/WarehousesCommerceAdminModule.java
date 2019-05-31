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

package com.liferay.commerce.warehouse.web.internal.admin;

import com.liferay.commerce.admin.CommerceAdminModule;
import com.liferay.commerce.configuration.CommerceShippingGroupServiceConfiguration;
import com.liferay.commerce.constants.CommerceConstants;
import com.liferay.commerce.inventory.constants.CommerceInventoryActionKeys;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseLocalService;
import com.liferay.commerce.service.CommerceCountryService;
import com.liferay.commerce.warehouse.web.internal.display.context.CommerceWarehousesDisplayContext;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Di Giorgi
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "commerce.admin.module.key=" + WarehousesCommerceAdminModule.KEY,
	service = CommerceAdminModule.class
)
public class WarehousesCommerceAdminModule implements CommerceAdminModule {

	public static final String KEY = "warehouses";

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "warehouses");
	}

	@Override
	public PortletURL getSearchURL(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		HttpServletRequest httpServletRequest = _portal.getHttpServletRequest(
			renderRequest);

		CommerceWarehousesDisplayContext commerceWarehousesDisplayContext =
			setCommerceWarehousesDisplayContext(httpServletRequest);

		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("commerceAdminModuleKey", KEY);
		portletURL.setParameter(
			"commerceCountryId",
			String.valueOf(
				commerceWarehousesDisplayContext.getCommerceCountryId()));

		return portletURL;
	}

	@Override
	public boolean isVisible(long groupId) throws PortalException {
		CommerceShippingGroupServiceConfiguration
			commerceShippingGroupServiceConfiguration =
				_configurationProvider.getConfiguration(
					CommerceShippingGroupServiceConfiguration.class,
					new GroupServiceSettingsLocator(
						groupId, CommerceConstants.SHIPPING_SERVICE_NAME));

		String commerceShippingOriginLocatorKey =
			commerceShippingGroupServiceConfiguration.
				commerceShippingOriginLocatorKey();

		if (commerceShippingOriginLocatorKey.equals("address")) {
			return false;
		}

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		return PortalPermissionUtil.contains(
			permissionChecker, CommerceInventoryActionKeys.MANAGE_INVENTORY);
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException {

		HttpServletRequest httpServletRequest = _portal.getHttpServletRequest(
			renderRequest);
		HttpServletResponse httpServletResponse =
			_portal.getHttpServletResponse(renderResponse);

		setCommerceWarehousesDisplayContext(httpServletRequest);

		_jspRenderer.renderJSP(
			_servletContext, httpServletRequest, httpServletResponse,
			"/view.jsp");
	}

	protected CommerceWarehousesDisplayContext
		setCommerceWarehousesDisplayContext(
			HttpServletRequest httpServletRequest) {

		CommerceWarehousesDisplayContext commerceWarehousesDisplayContext =
			(CommerceWarehousesDisplayContext)httpServletRequest.getAttribute(
				WebKeys.PORTLET_DISPLAY_CONTEXT);

		if (commerceWarehousesDisplayContext == null) {
			commerceWarehousesDisplayContext =
				new CommerceWarehousesDisplayContext(
					_commerceCountryService, _commerceWarehouseLocalService,
					httpServletRequest);

			httpServletRequest.setAttribute(
				WebKeys.PORTLET_DISPLAY_CONTEXT,
				commerceWarehousesDisplayContext);
		}

		return commerceWarehousesDisplayContext;
	}

	@Reference
	private CommerceCountryService _commerceCountryService;

	@Reference
	private CommerceInventoryWarehouseLocalService
		_commerceWarehouseLocalService;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.warehouse.web)"
	)
	private ServletContext _servletContext;

}