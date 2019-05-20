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

package com.liferay.commerce.order.content.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Alessio Antonio Rendina
 */
@ExtendedObjectClassDefinition(
	category = "orders",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
	id = "com.liferay.commerce.order.content.web.internal.configuration.CommerceOrderDetailPortletInstanceConfiguration",
	localization = "content/Language",
	name = "commerce-order-detail-portlet-instance-configuration-name"
)
public interface CommerceOrderDetailPortletInstanceConfiguration {

	@Meta.AD(
		name = "pending-commerce-order-detail-renderer-key", required = false
	)
	public String pendingCommerceOrderDetailRendererKey();

	@Meta.AD(
		name = "placed-commerce-order-detail-renderer-key", required = false
	)
	public String placedCommerceOrderDetailRendererKey();

}