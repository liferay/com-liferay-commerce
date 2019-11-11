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

package com.liferay.commerce.machine.learning.forecast.alert.web.internal.portlet;

import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.machine.learning.forecast.alert.constants.CommerceMLForecastAlertConstants;
import com.liferay.commerce.machine.learning.forecast.alert.constants.CommerceMLForecastAlertPortletKeys;
import com.liferay.commerce.machine.learning.forecast.alert.service.CommerceMLForecastAlertEntryService;
import com.liferay.commerce.machine.learning.forecast.alert.web.internal.display.context.CommerceMLForecastAlertEntryListDisplayContext;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=portlet-commerce-machine-learning-forecast-alert",
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.layout-cacheable=true",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.preferences-unique-per-layout=false",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.scopeable=true",
		"javax.portlet.display-name=Commerce Machine Learning Forecast Alert",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CommerceMLForecastAlertPortletKeys.COMMERCE_ML_FORECAST_ALERT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = {CommerceMLForecastAlertPortlet.class, Portlet.class}
)
public class CommerceMLForecastAlertPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			CommerceMLForecastAlertEntryListDisplayContext
				commerceMLForecastAlertEntryListDisplayContext =
					new CommerceMLForecastAlertEntryListDisplayContext(
						_commerceAccountLocalService,
						_commerceMLForecastAlertEntryService,
						_portletResourcePermission, renderRequest);

			renderRequest.setAttribute(
				WebKeys.PORTLET_DISPLAY_CONTEXT,
				commerceMLForecastAlertEntryListDisplayContext);
		}
		catch (Exception e) {
			if (e instanceof PrincipalException) {
				hideDefaultErrorMessage(renderRequest);

				SessionErrors.add(renderRequest, "principalExceptionView");
			}
		}

		super.render(renderRequest, renderResponse);
	}

	@Reference
	private CommerceAccountLocalService _commerceAccountLocalService;

	@Reference
	private CommerceMLForecastAlertEntryService
		_commerceMLForecastAlertEntryService;

	@Reference(
		target = "(resource.name=" + CommerceMLForecastAlertConstants.RESOURCE_NAME + ")"
	)
	private PortletResourcePermission _portletResourcePermission;

}