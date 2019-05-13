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

package com.liferay.commerce.inventory.service.persistence.impl;

import com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity;
import com.liferay.commerce.inventory.service.persistence.CommerceInventoryBookedQuantityPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Luca Pellizzon
 * @generated
 */
public class CommerceInventoryBookedQuantityFinderBaseImpl
	extends BasePersistenceImpl<CommerceInventoryBookedQuantity> {
	public CommerceInventoryBookedQuantityFinderBaseImpl() {
		setModelClass(CommerceInventoryBookedQuantity.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("commerceInventoryBookedQuantityId",
				"CIBookedQuantityId");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	@Override
	public Set<String> getBadColumnNames() {
		return getCommerceInventoryBookedQuantityPersistence()
				   .getBadColumnNames();
	}

	/**
	 * Returns the commerce inventory booked quantity persistence.
	 *
	 * @return the commerce inventory booked quantity persistence
	 */
	public CommerceInventoryBookedQuantityPersistence getCommerceInventoryBookedQuantityPersistence() {
		return commerceInventoryBookedQuantityPersistence;
	}

	/**
	 * Sets the commerce inventory booked quantity persistence.
	 *
	 * @param commerceInventoryBookedQuantityPersistence the commerce inventory booked quantity persistence
	 */
	public void setCommerceInventoryBookedQuantityPersistence(
		CommerceInventoryBookedQuantityPersistence commerceInventoryBookedQuantityPersistence) {
		this.commerceInventoryBookedQuantityPersistence = commerceInventoryBookedQuantityPersistence;
	}

	@BeanReference(type = CommerceInventoryBookedQuantityPersistence.class)
	protected CommerceInventoryBookedQuantityPersistence commerceInventoryBookedQuantityPersistence;
	private static final Log _log = LogFactoryUtil.getLog(CommerceInventoryBookedQuantityFinderBaseImpl.class);
}