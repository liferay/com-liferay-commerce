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

import com.liferay.commerce.inventory.service.persistence.CommerceInventoryWarehouseItemFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Iterator;

/**
 * @author Luca Pellizzon
 */
public class CommerceInventoryWarehouseItemFinderImpl
	extends CommerceInventoryWarehouseItemFinderBaseImpl
	implements CommerceInventoryWarehouseItemFinder {

	public static final String GET_STOCK_QUANTITY =
		CommerceInventoryWarehouseItemFinder.class.getName() +
			".findStockQuantityByGroupIdAndSku";

	@Override
	public int findStockQuantityByGroupIdAndSku(
		long companyId, long groupId, String sku) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), GET_STOCK_QUANTITY);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(_SUM_VALUE, Type.INTEGER);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupId);
			qPos.add(sku);

			Iterator<Integer> itr = q.iterate();

			if (itr.hasNext()) {
				Integer sum = itr.next();

				if (sum != null) {
					return sum.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _SUM_VALUE = "SUM_VALUE";

	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;

}