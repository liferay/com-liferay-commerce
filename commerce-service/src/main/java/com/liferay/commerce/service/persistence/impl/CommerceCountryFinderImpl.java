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

package com.liferay.commerce.service.persistence.impl;

import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.impl.CommerceCountryImpl;
import com.liferay.commerce.product.model.impl.CommerceChannelRelImpl;
import com.liferay.commerce.service.persistence.CommerceCountryFinder;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

/**
 * @author Andrea Di Giorgi
 */
public class CommerceCountryFinderImpl
	extends CommerceCountryFinderBaseImpl implements CommerceCountryFinder {

	public static final String FIND_BY_COMMERCE_WAREHOUSES =
		CommerceCountryFinder.class.getName() +
			".findByCommerceInventoryWarehouses";

	public static final String FIND_BY_COMMERCE_CHANNEL =
		CommerceCountryFinder.class.getName() + ".findByCommerceChannel";

	@Override
	public List<CommerceCountry> findByCommerceInventoryWarehouses(
		long companyId, boolean all) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(
				getClass(), FIND_BY_COMMERCE_WAREHOUSES);

			if (all) {
				sql = StringUtil.replace(sql, _ALL_SQL, StringPool.BLANK);
			}
			else {
				sql = StringUtil.replace(sql, _ALL_SQL, _ACTIVE_SQL);
			}

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("CommerceCountry", CommerceCountryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			return (List<CommerceCountry>)QueryUtil.list(
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
	public List<CommerceCountry> findByCommerceChannel(
		long commerceChannelId, boolean shippingAllowed, boolean billingAllowed,
		int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_COMMERCE_CHANNEL);

			if (billingAllowed) {
				sql = StringUtil.replace(sql, _BILLING_SQL, StringPool.BLANK);
			}
			else {
				sql = StringUtil.replace(
					sql, _BILLING_SQL, _BILLING_ALLOWED_SQL);
			}

			if (shippingAllowed) {
				sql = StringUtil.replace(sql, _SHIPPING_SQL, StringPool.BLANK);
			}
			else {
				sql = StringUtil.replace(
					sql, _SHIPPING_SQL, _SHIPPING_ALLOWED_SQL);
			}

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("CommerceCountry", CommerceCountryImpl.class);
			q.addEntity("CommerceChannelRel", CommerceChannelRelImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(
				ClassNameLocalServiceUtil.getClassNameId(
					CommerceCountry.class));
			qPos.add(commerceChannelId);

			return (List<CommerceCountry>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _ACTIVE_SQL =
		"AND (CIWarehouse.active_ = true)";

	private static final String _ALL_SQL = "[$ALL$]";

	private static final String _BILLING_ALLOWED_SQL =
		"AND (CommerceCountry.billingAllowed = true)";

	private static final String _BILLING_SQL = "[$BILLING]";

	private static final String _SHIPPING_ALLOWED_SQL =
		"AND (CommerceCountry.shippingAllowed = true)";

	private static final String _SHIPPING_SQL = "[$SHIPPING$]";

	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;

}