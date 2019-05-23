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

import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.util.SetUtil;

import java.util.Set;

import javax.portlet.PortletResponse;

/**
 * @author Alec Sloan
 */
public class SimpleSiteChecker extends EmptyOnClickRowChecker {

	public SimpleSiteChecker(
		PortletResponse portletResponse, long[] checkedGroupIds) {

		super(portletResponse);

		_checkedGroupIds = SetUtil.fromArray(checkedGroupIds);
	}

	@Override
	public boolean isChecked(Object obj) {
		Group group = (Group)obj;

		return _checkedGroupIds.contains(group.getGroupId());
	}

	@Override
	public boolean isDisabled(Object obj) {
		Group group = (Group)obj;

		return _checkedGroupIds.contains(group.getGroupId());
	}

	private final Set<Long> _checkedGroupIds;

}