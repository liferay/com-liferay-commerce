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

package com.liferay.commerce.discount.model.impl;

import com.liferay.commerce.discount.model.CommerceDiscountCommerceAccountGroupRel;
import com.liferay.commerce.discount.service.CommerceDiscountCommerceAccountGroupRelLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;

import java.util.List;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceDiscountImpl extends CommerceDiscountBaseImpl {

	public CommerceDiscountImpl() {
	}

	@Override
	public List<CommerceDiscountCommerceAccountGroupRel>
		getCommerceDiscountCommerceAccountGroupRels() {

		return CommerceDiscountCommerceAccountGroupRelLocalServiceUtil.
			getCommerceDiscountCommerceAccountGroupRels(
				getCommerceDiscountId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null);
	}

}