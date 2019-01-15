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

import com.liferay.commerce.model.CommerceWarehouse;
import com.liferay.commerce.model.impl.CommerceWarehouseImpl;
import com.liferay.commerce.service.persistence.CommerceWarehouseFinder;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Iterator;
import java.util.List;

/**
 * @author Andrea Di Giorgi
 */
public class CommerceWarehouseFinderImpl
	extends CommerceWarehouseFinderBaseImpl implements CommerceWarehouseFinder {

	public static final String COUNT_BY_G_N_D_S_C_Z_C =
		CommerceWarehouseFinder.class.getName() + ".countByG_N_D_S_C_Z_C";

	public static final String FIND_BY_G_N_D_S_C_Z_C =
		CommerceWarehouseFinder.class.getName() + ".findByG_N_D_S_C_Z_C";

	@Override
	public int countByKeywords(
		long groupId, String keywords, Boolean active, long commerceCountryId) {

		String[] names = null;
		String[] descriptions = null;
		String[] streets = null;
		String[] cities = null;
		String[] zips = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = _customSQL.keywords(keywords);
			descriptions = _customSQL.keywords(keywords, false);
			streets = _customSQL.keywords(keywords);
			cities = _customSQL.keywords(keywords);
			zips = _customSQL.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return countByG_N_D_S_C_Z_C(
			groupId, names, descriptions, streets, cities, zips, active,
			commerceCountryId, andOperator);
	}

	@Override
	public int countByG_N_D_S_C_Z_C(
		long groupId, String name, String description, String street,
		String city, String zip, Boolean active, long commerceCountryId,
		boolean andOperator) {

		String[] names = _customSQL.keywords(name);
		String[] descriptions = _customSQL.keywords(description);
		String[] streets = _customSQL.keywords(street);
		String[] cities = _customSQL.keywords(city);
		String[] zips = _customSQL.keywords(zip);

		return countByG_N_D_S_C_Z_C(
			groupId, names, descriptions, streets, cities, zips, active,
			commerceCountryId, andOperator);
	}

	@Override
	public int countByG_N_D_S_C_Z_C(
		long groupId, String[] names, String[] descriptions, String[] streets,
		String[] cities, String[] zips, Boolean active, long commerceCountryId,
		boolean andOperator) {

		names = _customSQL.keywords(names);
		descriptions = _customSQL.keywords(descriptions, false);
		streets = _customSQL.keywords(streets);
		cities = _customSQL.keywords(cities);
		zips = _customSQL.keywords(zips);

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), COUNT_BY_G_N_D_S_C_Z_C);

			sql = _customSQL.replaceKeywords(
				sql, "lower(CommerceWarehouse.name)", StringPool.LIKE, false,
				names);
			sql = _customSQL.replaceKeywords(
				sql, "CommerceWarehouse.description", StringPool.LIKE, false,
				descriptions);
			sql = _customSQL.replaceKeywords(
				sql, "lower(CommerceWarehouse.street1)", StringPool.LIKE, true,
				streets);
			sql = _customSQL.replaceKeywords(
				sql, "lower(CommerceWarehouse.street2)", StringPool.LIKE, true,
				streets);
			sql = _customSQL.replaceKeywords(
				sql, "lower(CommerceWarehouse.street3)", StringPool.LIKE, true,
				streets);
			sql = _customSQL.replaceKeywords(
				sql, "lower(CommerceWarehouse.cities)", StringPool.LIKE, false,
				cities);
			sql = _customSQL.replaceKeywords(
				sql, "lower(CommerceWarehouse.zips)", StringPool.LIKE, false,
				zips);

			if (active == null) {
				sql = StringUtil.replace(sql, _ALL_SQL, StringPool.BLANK);
			}
			else {
				sql = StringUtil.replace(sql, _ALL_SQL, _ACTIVE_SQL);
			}

			if (commerceCountryId < 0) {
				sql = StringUtil.replace(
					sql, _COMMERCE_COUNTRY_ID_SQL, StringPool.BLANK);
			}

			sql = _customSQL.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(names, 2);
			qPos.add(descriptions, 2);
			qPos.add(streets, 6);
			qPos.add(cities, 2);
			qPos.add(zips, 2);

			if (commerceCountryId >= 0) {
				qPos.add(commerceCountryId);
			}

			if (active != null) {
				qPos.add(active);
			}

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
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

	@Override
	public List<CommerceWarehouse> findByKeywords(
		long groupId, String keywords, Boolean active, long commerceCountryId,
		int start, int end,
		OrderByComparator<CommerceWarehouse> orderByComparator) {

		String[] names = null;
		String[] descriptions = null;
		String[] streets = null;
		String[] cities = null;
		String[] zips = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = _customSQL.keywords(keywords);
			descriptions = _customSQL.keywords(keywords, false);
			streets = _customSQL.keywords(keywords);
			cities = _customSQL.keywords(keywords);
			zips = _customSQL.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return findByG_N_D_S_C_Z_C(
			groupId, names, descriptions, streets, cities, zips, active,
			commerceCountryId, andOperator, start, end, orderByComparator);
	}

	@Override
	public List<CommerceWarehouse> findByG_N_D_S_C_Z_C(
		long groupId, String name, String description, String street,
		String city, String zip, Boolean active, long commerceCountryId,
		boolean andOperator, int start, int end,
		OrderByComparator<CommerceWarehouse> orderByComparator) {

		String[] names = _customSQL.keywords(name);
		String[] descriptions = _customSQL.keywords(description);
		String[] streets = _customSQL.keywords(street);
		String[] cities = _customSQL.keywords(city);
		String[] zips = _customSQL.keywords(zip);

		return findByG_N_D_S_C_Z_C(
			groupId, names, descriptions, streets, cities, zips, active,
			commerceCountryId, andOperator, start, end, orderByComparator);
	}

	@Override
	public List<CommerceWarehouse> findByG_N_D_S_C_Z_C(
		long groupId, String[] names, String[] descriptions, String[] streets,
		String[] cities, String[] zips, Boolean active, long commerceCountryId,
		boolean andOperator, int start, int end,
		OrderByComparator<CommerceWarehouse> orderByComparator) {

		names = _customSQL.keywords(names);
		descriptions = _customSQL.keywords(descriptions, false);
		streets = _customSQL.keywords(streets);
		cities = _customSQL.keywords(cities);
		zips = _customSQL.keywords(zips);

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_G_N_D_S_C_Z_C);

			sql = _customSQL.replaceKeywords(
				sql, "lower(CommerceWarehouse.name)", StringPool.LIKE, false,
				names);
			sql = _customSQL.replaceKeywords(
				sql, "CommerceWarehouse.description", StringPool.LIKE, false,
				descriptions);
			sql = _customSQL.replaceKeywords(
				sql, "lower(CommerceWarehouse.street1)", StringPool.LIKE, true,
				streets);
			sql = _customSQL.replaceKeywords(
				sql, "lower(CommerceWarehouse.street2)", StringPool.LIKE, true,
				streets);
			sql = _customSQL.replaceKeywords(
				sql, "lower(CommerceWarehouse.street3)", StringPool.LIKE, true,
				streets);
			sql = _customSQL.replaceKeywords(
				sql, "lower(CommerceWarehouse.cities)", StringPool.LIKE, false,
				cities);
			sql = _customSQL.replaceKeywords(
				sql, "lower(CommerceWarehouse.zips)", StringPool.LIKE, false,
				zips);

			if (active == null) {
				sql = StringUtil.replace(sql, _ALL_SQL, StringPool.BLANK);
			}
			else {
				sql = StringUtil.replace(sql, _ALL_SQL, _ACTIVE_SQL);
			}

			if (commerceCountryId < 0) {
				sql = StringUtil.replace(
					sql, _COMMERCE_COUNTRY_ID_SQL, StringPool.BLANK);
			}

			sql = _customSQL.replaceAndOperator(sql, andOperator);
			sql = _customSQL.replaceOrderBy(sql, orderByComparator);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("CommerceWarehouse", CommerceWarehouseImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(names, 2);
			qPos.add(descriptions, 2);
			qPos.add(streets, 6);
			qPos.add(cities, 2);
			qPos.add(zips, 2);

			if (commerceCountryId >= 0) {
				qPos.add(commerceCountryId);
			}

			if (active != null) {
				qPos.add(active);
			}

			return (List<CommerceWarehouse>)QueryUtil.list(
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
		"AND (CommerceWarehouse.active_ = ?)";

	private static final String _ALL_SQL = "[$ALL$]";

	private static final String _COMMERCE_COUNTRY_ID_SQL =
		"AND (CommerceWarehouse.commerceCountryId = ?)";

	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;

}