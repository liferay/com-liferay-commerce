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

package com.liferay.commerce.account.group.admin.web.internal.portlet;

import com.liferay.commerce.account.constants.CommerceAccountPortletKeys;
import com.liferay.commerce.account.group.admin.web.internal.display.context.CommerceAccountGroupAdminDisplayContext;
import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.service.CommerceAccountGroupCommerceAccountRelService;
import com.liferay.commerce.account.service.CommerceAccountGroupService;
import com.liferay.item.selector.ItemSelector;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=portlet-commerce-account-group-admin",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.layout-cacheable=true",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.preferences-unique-per-layout=false",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.scopeable=true",
		"javax.portlet.display-name=Account Groups",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CommerceAccountPortletKeys.COMMERCE_ACCOUNT_GROUP_ADMIN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = {CommerceAccountGroupAdminPortlet.class, Portlet.class}
)
public class CommerceAccountGroupAdminPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		CommerceAccountGroupAdminDisplayContext
			commerceAccountGroupAdminDisplayContext =
				new CommerceAccountGroupAdminDisplayContext(
					_commerceAccountGroupCommerceAccountRelService,
					_commerceAccountGroupModelResourcePermission,
					_commerceAccountGroupService, _itemSelector, renderRequest);

		renderRequest.setAttribute(
			WebKeys.PORTLET_DISPLAY_CONTEXT,
			commerceAccountGroupAdminDisplayContext);

		super.render(renderRequest, renderResponse);
	}

	@Reference
	private CommerceAccountGroupCommerceAccountRelService
		_commerceAccountGroupCommerceAccountRelService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.account.model.CommerceAccountGroup)"
	)
	private ModelResourcePermission<CommerceAccountGroup>
		_commerceAccountGroupModelResourcePermission;

	@Reference
	private CommerceAccountGroupService _commerceAccountGroupService;

	@Reference
	private ItemSelector _itemSelector;

}