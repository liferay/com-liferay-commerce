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

package com.liferay.commerce.subscription.web.internal.portlet;

import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.util.CPDefinitionHelper;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.commerce.service.CommerceSubscriptionEntryService;
import com.liferay.commerce.subscription.web.internal.display.context.CommerceSubscriptionContentDisplayContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=portlet-commerce-subscription-content",
		"com.liferay.portlet.display-category=commerce",
		"com.liferay.portlet.layout-cacheable=true",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.scopeable=true",
		"javax.portlet.display-name=My Subscriptions",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.view-template=/subscriptions/view.jsp",
		"javax.portlet.name=" + CPPortletKeys.COMMERCE_SUBSCRIPTION_CONTENT_WEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = {CommerceSubscriptionContentPortlet.class, Portlet.class}
)
public class CommerceSubscriptionContentPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			HttpServletRequest httpServletRequest =
				_portal.getHttpServletRequest(renderRequest);

			CommerceSubscriptionContentDisplayContext
				commerceSubscriptionContentDisplayContext =
					new CommerceSubscriptionContentDisplayContext(
						_cpDefinitionHelper, _cpInstanceHelper,
						_commerceSubscriptionEntryService, httpServletRequest);

			httpServletRequest.setAttribute(
				WebKeys.PORTLET_DISPLAY_CONTEXT,
				commerceSubscriptionContentDisplayContext);
		}
		catch (PortalException pe) {
			_log.error(pe, pe);
		}

		super.render(renderRequest, renderResponse);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceSubscriptionContentPortlet.class);

	@Reference
	private CommerceSubscriptionEntryService _commerceSubscriptionEntryService;

	@Reference
	private CPDefinitionHelper _cpDefinitionHelper;

	@Reference
	private CPInstanceHelper _cpInstanceHelper;

	@Reference
	private Portal _portal;

}