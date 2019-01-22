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

package com.liferay.commerce.product.content.search.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
@ExtendedObjectClassDefinition(
	category = "catalog",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
	id = "com.liferay.commerce.product.content.search.web.internal.configuration.CPSearchResultsPortletInstanceConfiguration",
	localization = "content/Language",
	name = "commerce-product-content-search-web-portlet-instance-configuration-name"
)
public interface CPSearchResultsPortletInstanceConfiguration {

	@Meta.AD(name = "display-style", required = false)
	public String displayStyle();

	@Meta.AD(deflt = "0", name = "display-style-group-id", required = false)
	public long displayStyleGroupId();

	@Meta.AD(deflt = StringPool.TRUE, name = "paginate", required = false)
	public boolean paginate();

	@Meta.AD(deflt = "15", name = "pagination-delta", required = false)
	public int paginationDelta();

	@Meta.AD(deflt = "more", name = "pagination-type", required = false)
	public String paginationType();

	@Meta.AD(deflt = "custom", name = "selection-style", required = false)
	public String selectionStyle();

}