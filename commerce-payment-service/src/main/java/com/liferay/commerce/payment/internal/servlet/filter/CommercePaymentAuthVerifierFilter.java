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

package com.liferay.commerce.payment.internal.servlet.filter;

import com.liferay.commerce.constants.CommercePaymentConstants;
import com.liferay.portal.servlet.filters.authverifier.AuthVerifierFilter;

import javax.servlet.Filter;

import org.osgi.service.component.annotations.Component;

/**
 * @author Luca Pellizzon
 */
@Component(
	immediate = true,
	property = {
		"filter.init.auth.verifier.PortalSessionAuthVerifier.urls.includes=/" + CommercePaymentConstants.SERVLET_PATH + "/*",
		"osgi.http.whiteboard.filter.name=com.liferay.commerce.payment.internal.servlet.filter.CommercePaymentAuthVerifierFilter",
		"osgi.http.whiteboard.servlet.pattern=/" + CommercePaymentConstants.SERVLET_PATH + "/*"
	},
	service = Filter.class
)
public class CommercePaymentAuthVerifierFilter extends AuthVerifierFilter {
}