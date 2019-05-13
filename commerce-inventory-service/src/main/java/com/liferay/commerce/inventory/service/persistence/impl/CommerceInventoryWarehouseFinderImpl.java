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

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseImpl;
import com.liferay.commerce.inventory.service.persistence.CommerceInventoryWarehouseFinder;
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
 * @author Luca Pellizzon
 */
public class CommerceInventoryWarehouseFinderImpl
	extends CommerceInventoryWarehouseFinderBaseImpl
	implements CommerceInventoryWarehouseFinder {

	public static final String COUNT_BY_G_N_D_S_C_Z_C =
		CommerceInventoryWarehouseFinder.class.getName() +
			".countByG_N_D_S_C_Z_C";

	public static final String FIND_BY_G_N_D_S_C_Z_C =
		CommerceInventoryWarehouseFinder.class.getName() +
			".findByG_N_D_S_C_Z_C";

	public static final String FIND_BY_GROUP_ID =
		CommerceInventoryWarehouseFinder.class.getName() +
			".findWarehousesByGroupId";

	public static final String FIND_BY_GROUP_ID_AND_ACTIVE =
		CommerceInventoryWarehouseFinder.class.getName() +
			".findWarehousesByGroupId";

	public static final String FIND_BY_GROUP_ID_AND_ACTIVE_AND_ACOUNTRYISOCODE =
		CommerceInventoryWarehouseFinder.class.getName() +
			".findWarehousesByGroupId";

	public static final String FIND_BY_GROUP_ID_AND_SKU =
		CommerceInventoryWarehouseFinder.class.getName() +
			".findWarehousesByGroupId";

	@Override
	public int countByKeywords(
		long companyId, long groupId, String keywords, Boolean active,
		String commerceCountryCode) {

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
			companyId, groupId, names, descriptions, streets, cities, zips,
			active, commerceCountryCode, andOperator);
	}

	@Override
	public int countByG_A(long companyId, long groupId, Boolean active) {
		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), COUNT_BY_G_N_D_S_C_Z_C);

			if (active == null) {
				sql = StringUtil.replace(sql, _ALL_SQL, StringPool.BLANK);
			}
			else {
				sql = StringUtil.replace(sql, _ALL_SQL, _ACTIVE_SQL);
			}

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupId);

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
	public int countByG_A_C(
		long companyId, long groupId, Boolean active,
		String commerceCountryCode) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), COUNT_BY_G_N_D_S_C_Z_C);

			if (active == null) {
				sql = StringUtil.replace(sql, _ALL_SQL, StringPool.BLANK);
			}
			else {
				sql = StringUtil.replace(sql, _ALL_SQL, _ACTIVE_SQL);
			}

			if (commerceCountryCode == null) {
				sql = StringUtil.replace(
					sql, _COMMERCE_COUNTRY_ID_SQL, StringPool.BLANK);
			}

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupId);

			if (active != null) {
				qPos.add(active);
			}

			if (commerceCountryCode != null) {
				qPos.add(commerceCountryCode);
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
	public int countByG_N_D_S_C_Z_C(
		long companyId, long groupId, String[] names, String[] descriptions,
		String[] streets, String[] cities, String[] zips, Boolean active,
		String commerceCountryCode, boolean andOperator) {

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
				sql, "lower(CommerceInventoryWarehouse.name)", StringPool.LIKE,
				false, names);
			sql = _customSQL.replaceKeywords(
				sql, "CommerceInventoryWarehouse.description", StringPool.LIKE,
				false, descriptions);
			sql = _customSQL.replaceKeywords(
				sql, "lower(CommerceInventoryWarehouse.street1)",
				StringPool.LIKE, true, streets);
			sql = _customSQL.replaceKeywords(
				sql, "lower(CommerceInventoryWarehouse.street2)",
				StringPool.LIKE, true, streets);
			sql = _customSQL.replaceKeywords(
				sql, "lower(CommerceInventoryWarehouse.street3)",
				StringPool.LIKE, true, streets);
			sql = _customSQL.replaceKeywords(
				sql, "lower(CommerceInventoryWarehouse.cities)",
				StringPool.LIKE, false, cities);
			sql = _customSQL.replaceKeywords(
				sql, "lower(CommerceInventoryWarehouse.zips)", StringPool.LIKE,
				false, zips);

			if (active == null) {
				sql = StringUtil.replace(sql, _ALL_SQL, StringPool.BLANK);
			}
			else {
				sql = StringUtil.replace(sql, _ALL_SQL, _ACTIVE_SQL);
			}

			if (commerceCountryCode == null) {
				sql = StringUtil.replace(
					sql, _COMMERCE_COUNTRY_ID_SQL, StringPool.BLANK);
			}

			sql = _customSQL.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupId);
			qPos.add(names, 2);
			qPos.add(descriptions, 2);
			qPos.add(streets, 6);
			qPos.add(cities, 2);
			qPos.add(zips, 2);

			if (commerceCountryCode != null) {
				qPos.add(commerceCountryCode);
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
	public List<CommerceInventoryWarehouse> findByKeywords(
		long companyId, long groupId, String keywords, Boolean active,
		String commerceCountryCode, int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {

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
			companyId, groupId, names, descriptions, streets, cities, zips,
			active, commerceCountryCode, andOperator, start, end,
			orderByComparator);
	}

	@Override
	public List<CommerceInventoryWarehouse> findByG_N_D_S_C_Z_C(
		long companyId, long groupId, String[] names, String[] descriptions,
		String[] streets, String[] cities, String[] zips, Boolean active,
		String commerceCountryCode, boolean andOperator, int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {

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
				sql, "lower(CIWarehouse.name)", StringPool.LIKE, false, names);
			sql = _customSQL.replaceKeywords(
				sql, "CIWarehouse.description", StringPool.LIKE, false,
				descriptions);
			sql = _customSQL.replaceKeywords(
				sql, "lower(CIWarehouse.street1)", StringPool.LIKE, true,
				streets);
			sql = _customSQL.replaceKeywords(
				sql, "lower(CIWarehouse.street2)", StringPool.LIKE, true,
				streets);
			sql = _customSQL.replaceKeywords(
				sql, "lower(CIWarehouse.street3)", StringPool.LIKE, true,
				streets);
			sql = _customSQL.replaceKeywords(
				sql, "lower(CIWarehouse.cities)", StringPool.LIKE, false,
				cities);
			sql = _customSQL.replaceKeywords(
				sql, "lower(CIWarehouse.zips)", StringPool.LIKE, false, zips);

			if (active == null) {
				sql = StringUtil.replace(sql, _ALL_SQL, StringPool.BLANK);
			}
			else {
				sql = StringUtil.replace(sql, _ALL_SQL, _ACTIVE_SQL);
			}

			if (commerceCountryCode == null) {
				sql = StringUtil.replace(
					sql, _COMMERCE_COUNTRY_ID_SQL, StringPool.BLANK);
			}

			sql = _customSQL.replaceAndOperator(sql, andOperator);
			sql = _customSQL.replaceOrderBy(sql, orderByComparator);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("CIWarehouse", CommerceInventoryWarehouseImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupId);
			qPos.add(names, 2);
			qPos.add(descriptions, 2);
			qPos.add(streets, 6);
			qPos.add(cities, 2);
			qPos.add(zips, 2);

			if (commerceCountryCode != null) {
				qPos.add(commerceCountryCode);
			}

			if (active != null) {
				qPos.add(active);
			}

			return (List<CommerceInventoryWarehouse>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<CommerceInventoryWarehouse> findWarehousesByGroupId(
		long companyId, long groupId) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_GROUP_ID);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("CIWarehouse", CommerceInventoryWarehouseImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupId);

			return (List<CommerceInventoryWarehouse>)QueryUtil.list(
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
	public List<CommerceInventoryWarehouse> findWarehousesByGroupIdAndActive(
		long companyId, long groupId, boolean active) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(
				getClass(), FIND_BY_GROUP_ID_AND_ACTIVE);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupId);
			qPos.add(active);

			return (List<CommerceInventoryWarehouse>)QueryUtil.list(
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
	public List<CommerceInventoryWarehouse>
		findWarehousesByGroupIdAndActiveAndCountryISOCode(
			long companyId, long groupId, boolean active, String countryCode) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(
				getClass(), FIND_BY_GROUP_ID_AND_ACTIVE_AND_ACOUNTRYISOCODE);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupId);
			qPos.add(active);
			qPos.add(countryCode);

			return (List<CommerceInventoryWarehouse>)QueryUtil.list(
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
	public List<CommerceInventoryWarehouse> findWarehousesByGroupIdAndSku(
		long companyId, long groupId, String sku) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_GROUP_ID_AND_SKU);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupId);
			qPos.add(sku);

			return (List<CommerceInventoryWarehouse>)QueryUtil.list(
				q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _ACTIVE_SQL = "AND (CIWarehouse.active_ = ?)";

	private static final String _ALL_SQL = "[$ALL$]";

	private static final String _COMMERCE_COUNTRY_ID_SQL =
		"AND (CIWarehouse.countryTwoLettersISOCode = ?)";

	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;

}