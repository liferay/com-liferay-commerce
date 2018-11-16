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
import com.liferay.commerce.product.type.virtual.order.model.impl.CommerceVirtualOrderItemImpl;
import com.liferay.commerce.product.type.virtual.order.service.persistence.CommerceVirtualOrderItemFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Date;
import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceVirtualOrderItemFinderImpl
	extends CommerceVirtualOrderItemFinderBaseImpl
	implements CommerceVirtualOrderItemFinder {

	public static final String FIND_BY_END_DATE =
		CommerceVirtualOrderItemFinder.class.getName() + ".findByEndDate";

	public static final String FIND_BY_G_O_A =
		CommerceVirtualOrderItemFinder.class.getName() + ".findByG_O_A";

	public static final String FIND_BY_G_U_A =
		CommerceVirtualOrderItemFinder.class.getName() + ".findByG_U_A";

	@Override
	public List<CommerceVirtualOrderItem> findByEndDate(Date endDate) {
		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_END_DATE);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity(
				"CommerceVirtualOrderItem", CommerceVirtualOrderItemImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(endDate);

			return (List<CommerceVirtualOrderItem>)QueryUtil.list(
				q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<CommerceVirtualOrderItem> findByG_O(
		long groupId, long organizationId, int start, int end,
		OrderByComparator<CommerceVirtualOrderItem> orderByComparator) {

		return _doFindByGroupIdAndCustomerId(
			FIND_BY_G_O_A, groupId, organizationId, start, end,
			orderByComparator);
	}

	@Override
	public List<CommerceVirtualOrderItem> findByG_U(
		long groupId, long userId, int start, int end,
		OrderByComparator<CommerceVirtualOrderItem> orderByComparator) {

		return _doFindByGroupIdAndCustomerId(
			FIND_BY_G_U_A, groupId, userId, start, end, orderByComparator);
	}

	private List<CommerceVirtualOrderItem> _doFindByGroupIdAndCustomerId(
		String customSQLId, long groupId, long customerId, int start, int end,
		OrderByComparator<CommerceVirtualOrderItem> orderByComparator) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), customSQLId);

			sql = _customSQL.replaceOrderBy(sql, orderByComparator);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity(
				"CommerceVirtualOrderItem", CommerceVirtualOrderItemImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(customerId);

			return (List<CommerceVirtualOrderItem>)QueryUtil.list(
				q, getDialect(), start, end);
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