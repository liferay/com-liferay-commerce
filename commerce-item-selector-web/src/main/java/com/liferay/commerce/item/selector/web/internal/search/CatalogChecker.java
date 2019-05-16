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

package com.liferay.commerce.item.selector.web.internal.search;

import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.util.SetUtil;

import java.util.Set;

import javax.portlet.PortletResponse;

/**
 * @author Alec Sloan
 */
public class CatalogChecker extends EmptyOnClickRowChecker {

	public CatalogChecker(
		PortletResponse portletResponse, long[] checkedCatalogIds,
		long[] disabledCatalogIds) {

		super(portletResponse);

		_checkedCatalogIds = SetUtil.fromArray(checkedCatalogIds);
		_disableCatalogIds = SetUtil.fromArray(disabledCatalogIds);
	}

	@Override
	public boolean isChecked(Object obj) {
		CommerceCatalog commerceCatalog = (CommerceCatalog)obj;

		return _checkedCatalogIds.contains(
			commerceCatalog.getCommerceCatalogId());
	}

	@Override
	public boolean isDisabled(Object obj) {
		CommerceCatalog commerceCatalog = (CommerceCatalog)obj;

		return _disableCatalogIds.contains(
			commerceCatalog.getCommerceCatalogId());
	}

	private final Set<Long> _checkedCatalogIds;
	private final Set<Long> _disableCatalogIds;

}