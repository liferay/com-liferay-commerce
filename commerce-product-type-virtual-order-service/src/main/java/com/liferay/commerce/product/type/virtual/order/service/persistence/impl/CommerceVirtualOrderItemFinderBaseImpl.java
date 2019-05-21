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

package com.liferay.commerce.product.type.virtual.order.service.persistence.impl;

import com.liferay.commerce.product.type.virtual.order.model.CommerceVirtualOrderItem;
import com.liferay.commerce.product.type.virtual.order.service.persistence.CommerceVirtualOrderItemPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Alessio Antonio Rendina
 * @generated
 */
public class CommerceVirtualOrderItemFinderBaseImpl
	extends BasePersistenceImpl<CommerceVirtualOrderItem> {

	public CommerceVirtualOrderItemFinderBaseImpl() {
		setModelClass(CommerceVirtualOrderItem.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("active", "active_");

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
				"_dbColumnNames");

			field.setAccessible(true);

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
		return getCommerceVirtualOrderItemPersistence().getBadColumnNames();
	}

	/**
	 * Returns the commerce virtual order item persistence.
	 *
	 * @return the commerce virtual order item persistence
	 */
	public CommerceVirtualOrderItemPersistence
		getCommerceVirtualOrderItemPersistence() {

		return commerceVirtualOrderItemPersistence;
	}

	/**
	 * Sets the commerce virtual order item persistence.
	 *
	 * @param commerceVirtualOrderItemPersistence the commerce virtual order item persistence
	 */
	public void setCommerceVirtualOrderItemPersistence(
		CommerceVirtualOrderItemPersistence
			commerceVirtualOrderItemPersistence) {

		this.commerceVirtualOrderItemPersistence =
			commerceVirtualOrderItemPersistence;
	}

	@BeanReference(type = CommerceVirtualOrderItemPersistence.class)
	protected CommerceVirtualOrderItemPersistence
		commerceVirtualOrderItemPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceVirtualOrderItemFinderBaseImpl.class);

}