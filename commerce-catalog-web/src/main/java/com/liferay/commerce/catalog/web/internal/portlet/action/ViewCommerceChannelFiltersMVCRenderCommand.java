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

package com.liferay.commerce.catalog.web.internal.portlet.action;

import com.liferay.commerce.catalog.web.internal.display.context.CPCatalogRuleDisplayContext;
import com.liferay.commerce.product.catalog.rule.CPRuleTypeJSPContributorRegistry;
import com.liferay.commerce.product.catalog.rule.CPRuleTypeRegistry;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.service.CPRuleAssetCategoryRelService;
import com.liferay.commerce.product.service.CPRuleService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CPPortletKeys.COMMERCE_CHANNELS,
		"mvc.command.name=viewCommerceChannelFilters"
	},
	service = MVCRenderCommand.class
)
public class ViewCommerceChannelFiltersMVCRenderCommand
	implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			HttpServletRequest httpServletRequest =
				_portal.getHttpServletRequest(renderRequest);

			CPCatalogRuleDisplayContext cpCatalogRuleDisplayContext =
				new CPCatalogRuleDisplayContext(
					_cpRuleAssetCategoryRelService, _cpRuleService,
					_cpRuleTypeJSPContributorRegistry, _cpRuleTypeRegistry,
					httpServletRequest);

			renderRequest.setAttribute(
				WebKeys.PORTLET_DISPLAY_CONTEXT, cpCatalogRuleDisplayContext);
		}
		catch (Exception e) {
			throw new PortletException(e);
		}

		return "/channel/filters.jsp";
	}

	@Reference
	private CPRuleAssetCategoryRelService _cpRuleAssetCategoryRelService;

	@Reference
	private CPRuleService _cpRuleService;

	@Reference
	private CPRuleTypeJSPContributorRegistry _cpRuleTypeJSPContributorRegistry;

	@Reference
	private CPRuleTypeRegistry _cpRuleTypeRegistry;

	@Reference
	private Portal _portal;

}