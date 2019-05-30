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
import com.liferay.commerce.inventory.model.impl.CommerceInventoryBookedQuantityImpl;
import com.liferay.commerce.inventory.service.persistence.CommerceInventoryBookedQuantityFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

/**
 * @author Luca Pellizzon
 */
public class CommerceInventoryBookedQuantityFinderImpl
	extends CommerceInventoryBookedQuantityFinderBaseImpl
	implements CommerceInventoryBookedQuantityFinder {

	public static final String FIND_OLD_TEMP_BOOKED_QUANTITIES =
		CommerceInventoryBookedQuantityFinder.class.getName() +
			".findOldTempBookedQuantities";

	@Override
	public List<CommerceInventoryBookedQuantity> findOldTempBookedQuantities() {
		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(
				getClass(), FIND_OLD_TEMP_BOOKED_QUANTITIES);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity(
				"CIBookedQuantity", CommerceInventoryBookedQuantityImpl.class);

			return (List<CommerceInventoryBookedQuantity>)QueryUtil.list(
				q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;

}