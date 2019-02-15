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

package com.liferay.commerce.user.segment.item.selector.web.internal.search;

import com.liferay.commerce.user.segment.model.CommerceUserSegmentEntry;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.util.SetUtil;

import java.util.Set;

import javax.portlet.RenderResponse;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceUserSegmentEntryItemSelectorChecker
	extends EmptyOnClickRowChecker {

	public CommerceUserSegmentEntryItemSelectorChecker(
		RenderResponse renderResponse,
		long[] checkedCommerceUserSegmentEntryIds) {

		super(renderResponse);

		_checkedCommerceUserSegmentEntryIds = SetUtil.fromArray(
			checkedCommerceUserSegmentEntryIds);
	}

	@Override
	public boolean isChecked(Object obj) {
		CommerceUserSegmentEntry commerceUserSegmentEntry =
			(CommerceUserSegmentEntry)obj;

		return _checkedCommerceUserSegmentEntryIds.contains(
			commerceUserSegmentEntry.getCommerceUserSegmentEntryId());
	}

	@Override
	public boolean isDisabled(Object obj) {
		return isChecked(obj);
	}

	private final Set<Long> _checkedCommerceUserSegmentEntryIds;

}