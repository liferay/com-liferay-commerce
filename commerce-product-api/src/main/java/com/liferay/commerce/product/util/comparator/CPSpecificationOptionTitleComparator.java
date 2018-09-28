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

package com.liferay.commerce.product.util.comparator;

import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Andrea Di Giorgi
 */
public class CPSpecificationOptionTitleComparator
	extends OrderByComparator<CPSpecificationOption> {

	public static final String ORDER_BY_ASC = "CPSpecificationOption.title ASC";

	public static final String ORDER_BY_DESC =
		"CPSpecificationOption.title DESC";

	public static final String[] ORDER_BY_FIELDS = {"title"};

	public CPSpecificationOptionTitleComparator() {
		this(false);
	}

	public CPSpecificationOptionTitleComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(
		CPSpecificationOption cpSpecificationOption1,
		CPSpecificationOption cpSpecificationOption2) {

		String title1 = StringUtil.toLowerCase(
			cpSpecificationOption1.getTitle());
		String title2 = StringUtil.toLowerCase(
			cpSpecificationOption2.getTitle());

		int value = title1.compareTo(title2);

		if (_ascending) {
			return value;
		}

		return Math.negateExact(value);
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}

		return ORDER_BY_DESC;
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private final boolean _ascending;

}