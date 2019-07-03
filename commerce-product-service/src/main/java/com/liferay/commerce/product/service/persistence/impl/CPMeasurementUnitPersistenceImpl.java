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

package com.liferay.commerce.product.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.exception.NoSuchCPMeasurementUnitException;
import com.liferay.commerce.product.model.CPMeasurementUnit;
import com.liferay.commerce.product.model.impl.CPMeasurementUnitImpl;
import com.liferay.commerce.product.model.impl.CPMeasurementUnitModelImpl;
import com.liferay.commerce.product.service.persistence.CPMeasurementUnitPersistence;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the cp measurement unit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @generated
 */
@ProviderType
public class CPMeasurementUnitPersistenceImpl
	extends BasePersistenceImpl<CPMeasurementUnit>
	implements CPMeasurementUnitPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CPMeasurementUnitUtil</code> to access the cp measurement unit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CPMeasurementUnitImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the cp measurement units where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cp measurement units
	 */
	@Override
	public List<CPMeasurementUnit> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp measurement units where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPMeasurementUnitModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp measurement units
	 * @param end the upper bound of the range of cp measurement units (not inclusive)
	 * @return the range of matching cp measurement units
	 */
	@Override
	public List<CPMeasurementUnit> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp measurement units where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPMeasurementUnitModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp measurement units
	 * @param end the upper bound of the range of cp measurement units (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp measurement units
	 */
	@Override
	public List<CPMeasurementUnit> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPMeasurementUnit> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp measurement units where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPMeasurementUnitModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp measurement units
	 * @param end the upper bound of the range of cp measurement units (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp measurement units
	 */
	@Override
	public List<CPMeasurementUnit> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPMeasurementUnit> orderByComparator,
		boolean retrieveFromCache) {

		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUuid;
			finderArgs = new Object[] {uuid};
		}
		else {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<CPMeasurementUnit> list = null;

		if (retrieveFromCache) {
			list = (List<CPMeasurementUnit>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPMeasurementUnit cpMeasurementUnit : list) {
					if (!uuid.equals(cpMeasurementUnit.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CPMEASUREMENTUNIT_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CPMeasurementUnitModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<CPMeasurementUnit>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPMeasurementUnit>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first cp measurement unit in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp measurement unit
	 * @throws NoSuchCPMeasurementUnitException if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit findByUuid_First(
			String uuid, OrderByComparator<CPMeasurementUnit> orderByComparator)
		throws NoSuchCPMeasurementUnitException {

		CPMeasurementUnit cpMeasurementUnit = fetchByUuid_First(
			uuid, orderByComparator);

		if (cpMeasurementUnit != null) {
			return cpMeasurementUnit;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCPMeasurementUnitException(msg.toString());
	}

	/**
	 * Returns the first cp measurement unit in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp measurement unit, or <code>null</code> if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit fetchByUuid_First(
		String uuid, OrderByComparator<CPMeasurementUnit> orderByComparator) {

		List<CPMeasurementUnit> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp measurement unit in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp measurement unit
	 * @throws NoSuchCPMeasurementUnitException if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit findByUuid_Last(
			String uuid, OrderByComparator<CPMeasurementUnit> orderByComparator)
		throws NoSuchCPMeasurementUnitException {

		CPMeasurementUnit cpMeasurementUnit = fetchByUuid_Last(
			uuid, orderByComparator);

		if (cpMeasurementUnit != null) {
			return cpMeasurementUnit;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCPMeasurementUnitException(msg.toString());
	}

	/**
	 * Returns the last cp measurement unit in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp measurement unit, or <code>null</code> if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit fetchByUuid_Last(
		String uuid, OrderByComparator<CPMeasurementUnit> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CPMeasurementUnit> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp measurement units before and after the current cp measurement unit in the ordered set where uuid = &#63;.
	 *
	 * @param CPMeasurementUnitId the primary key of the current cp measurement unit
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp measurement unit
	 * @throws NoSuchCPMeasurementUnitException if a cp measurement unit with the primary key could not be found
	 */
	@Override
	public CPMeasurementUnit[] findByUuid_PrevAndNext(
			long CPMeasurementUnitId, String uuid,
			OrderByComparator<CPMeasurementUnit> orderByComparator)
		throws NoSuchCPMeasurementUnitException {

		uuid = Objects.toString(uuid, "");

		CPMeasurementUnit cpMeasurementUnit = findByPrimaryKey(
			CPMeasurementUnitId);

		Session session = null;

		try {
			session = openSession();

			CPMeasurementUnit[] array = new CPMeasurementUnitImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, cpMeasurementUnit, uuid, orderByComparator, true);

			array[1] = cpMeasurementUnit;

			array[2] = getByUuid_PrevAndNext(
				session, cpMeasurementUnit, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPMeasurementUnit getByUuid_PrevAndNext(
		Session session, CPMeasurementUnit cpMeasurementUnit, String uuid,
		OrderByComparator<CPMeasurementUnit> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CPMEASUREMENTUNIT_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CPMeasurementUnitModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpMeasurementUnit)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPMeasurementUnit> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp measurement units where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CPMeasurementUnit cpMeasurementUnit :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cpMeasurementUnit);
		}
	}

	/**
	 * Returns the number of cp measurement units where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cp measurement units
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPMEASUREMENTUNIT_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"cpMeasurementUnit.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(cpMeasurementUnit.uuid IS NULL OR cpMeasurementUnit.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the cp measurement unit where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchCPMeasurementUnitException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp measurement unit
	 * @throws NoSuchCPMeasurementUnitException if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit findByUUID_G(String uuid, long groupId)
		throws NoSuchCPMeasurementUnitException {

		CPMeasurementUnit cpMeasurementUnit = fetchByUUID_G(uuid, groupId);

		if (cpMeasurementUnit == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCPMeasurementUnitException(msg.toString());
		}

		return cpMeasurementUnit;
	}

	/**
	 * Returns the cp measurement unit where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp measurement unit, or <code>null</code> if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the cp measurement unit where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching cp measurement unit, or <code>null</code> if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = new Object[] {uuid, groupId};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof CPMeasurementUnit) {
			CPMeasurementUnit cpMeasurementUnit = (CPMeasurementUnit)result;

			if (!Objects.equals(uuid, cpMeasurementUnit.getUuid()) ||
				(groupId != cpMeasurementUnit.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CPMEASUREMENTUNIT_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<CPMeasurementUnit> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByUUID_G, finderArgs, list);
				}
				else {
					CPMeasurementUnit cpMeasurementUnit = list.get(0);

					result = cpMeasurementUnit;

					cacheResult(cpMeasurementUnit);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByUUID_G, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (CPMeasurementUnit)result;
		}
	}

	/**
	 * Removes the cp measurement unit where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the cp measurement unit that was removed
	 */
	@Override
	public CPMeasurementUnit removeByUUID_G(String uuid, long groupId)
		throws NoSuchCPMeasurementUnitException {

		CPMeasurementUnit cpMeasurementUnit = findByUUID_G(uuid, groupId);

		return remove(cpMeasurementUnit);
	}

	/**
	 * Returns the number of cp measurement units where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching cp measurement units
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPMEASUREMENTUNIT_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"cpMeasurementUnit.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(cpMeasurementUnit.uuid IS NULL OR cpMeasurementUnit.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"cpMeasurementUnit.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the cp measurement units where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching cp measurement units
	 */
	@Override
	public List<CPMeasurementUnit> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp measurement units where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPMeasurementUnitModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp measurement units
	 * @param end the upper bound of the range of cp measurement units (not inclusive)
	 * @return the range of matching cp measurement units
	 */
	@Override
	public List<CPMeasurementUnit> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp measurement units where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPMeasurementUnitModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp measurement units
	 * @param end the upper bound of the range of cp measurement units (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp measurement units
	 */
	@Override
	public List<CPMeasurementUnit> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPMeasurementUnit> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp measurement units where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPMeasurementUnitModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp measurement units
	 * @param end the upper bound of the range of cp measurement units (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp measurement units
	 */
	@Override
	public List<CPMeasurementUnit> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPMeasurementUnit> orderByComparator,
		boolean retrieveFromCache) {

		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUuid_C;
			finderArgs = new Object[] {uuid, companyId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<CPMeasurementUnit> list = null;

		if (retrieveFromCache) {
			list = (List<CPMeasurementUnit>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPMeasurementUnit cpMeasurementUnit : list) {
					if (!uuid.equals(cpMeasurementUnit.getUuid()) ||
						(companyId != cpMeasurementUnit.getCompanyId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_CPMEASUREMENTUNIT_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CPMeasurementUnitModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<CPMeasurementUnit>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPMeasurementUnit>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first cp measurement unit in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp measurement unit
	 * @throws NoSuchCPMeasurementUnitException if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CPMeasurementUnit> orderByComparator)
		throws NoSuchCPMeasurementUnitException {

		CPMeasurementUnit cpMeasurementUnit = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (cpMeasurementUnit != null) {
			return cpMeasurementUnit;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPMeasurementUnitException(msg.toString());
	}

	/**
	 * Returns the first cp measurement unit in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp measurement unit, or <code>null</code> if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CPMeasurementUnit> orderByComparator) {

		List<CPMeasurementUnit> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp measurement unit in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp measurement unit
	 * @throws NoSuchCPMeasurementUnitException if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CPMeasurementUnit> orderByComparator)
		throws NoSuchCPMeasurementUnitException {

		CPMeasurementUnit cpMeasurementUnit = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (cpMeasurementUnit != null) {
			return cpMeasurementUnit;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPMeasurementUnitException(msg.toString());
	}

	/**
	 * Returns the last cp measurement unit in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp measurement unit, or <code>null</code> if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CPMeasurementUnit> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CPMeasurementUnit> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp measurement units before and after the current cp measurement unit in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param CPMeasurementUnitId the primary key of the current cp measurement unit
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp measurement unit
	 * @throws NoSuchCPMeasurementUnitException if a cp measurement unit with the primary key could not be found
	 */
	@Override
	public CPMeasurementUnit[] findByUuid_C_PrevAndNext(
			long CPMeasurementUnitId, String uuid, long companyId,
			OrderByComparator<CPMeasurementUnit> orderByComparator)
		throws NoSuchCPMeasurementUnitException {

		uuid = Objects.toString(uuid, "");

		CPMeasurementUnit cpMeasurementUnit = findByPrimaryKey(
			CPMeasurementUnitId);

		Session session = null;

		try {
			session = openSession();

			CPMeasurementUnit[] array = new CPMeasurementUnitImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, cpMeasurementUnit, uuid, companyId, orderByComparator,
				true);

			array[1] = cpMeasurementUnit;

			array[2] = getByUuid_C_PrevAndNext(
				session, cpMeasurementUnit, uuid, companyId, orderByComparator,
				false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPMeasurementUnit getByUuid_C_PrevAndNext(
		Session session, CPMeasurementUnit cpMeasurementUnit, String uuid,
		long companyId, OrderByComparator<CPMeasurementUnit> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_CPMEASUREMENTUNIT_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CPMeasurementUnitModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpMeasurementUnit)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPMeasurementUnit> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp measurement units where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CPMeasurementUnit cpMeasurementUnit :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(cpMeasurementUnit);
		}
	}

	/**
	 * Returns the number of cp measurement units where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching cp measurement units
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPMEASUREMENTUNIT_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"cpMeasurementUnit.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(cpMeasurementUnit.uuid IS NULL OR cpMeasurementUnit.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"cpMeasurementUnit.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the cp measurement units where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching cp measurement units
	 */
	@Override
	public List<CPMeasurementUnit> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp measurement units where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPMeasurementUnitModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp measurement units
	 * @param end the upper bound of the range of cp measurement units (not inclusive)
	 * @return the range of matching cp measurement units
	 */
	@Override
	public List<CPMeasurementUnit> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp measurement units where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPMeasurementUnitModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp measurement units
	 * @param end the upper bound of the range of cp measurement units (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp measurement units
	 */
	@Override
	public List<CPMeasurementUnit> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CPMeasurementUnit> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp measurement units where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPMeasurementUnitModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp measurement units
	 * @param end the upper bound of the range of cp measurement units (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp measurement units
	 */
	@Override
	public List<CPMeasurementUnit> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CPMeasurementUnit> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByCompanyId;
			finderArgs = new Object[] {companyId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByCompanyId;
			finderArgs = new Object[] {
				companyId, start, end, orderByComparator
			};
		}

		List<CPMeasurementUnit> list = null;

		if (retrieveFromCache) {
			list = (List<CPMeasurementUnit>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPMeasurementUnit cpMeasurementUnit : list) {
					if ((companyId != cpMeasurementUnit.getCompanyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CPMEASUREMENTUNIT_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CPMeasurementUnitModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<CPMeasurementUnit>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPMeasurementUnit>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first cp measurement unit in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp measurement unit
	 * @throws NoSuchCPMeasurementUnitException if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit findByCompanyId_First(
			long companyId,
			OrderByComparator<CPMeasurementUnit> orderByComparator)
		throws NoSuchCPMeasurementUnitException {

		CPMeasurementUnit cpMeasurementUnit = fetchByCompanyId_First(
			companyId, orderByComparator);

		if (cpMeasurementUnit != null) {
			return cpMeasurementUnit;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPMeasurementUnitException(msg.toString());
	}

	/**
	 * Returns the first cp measurement unit in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp measurement unit, or <code>null</code> if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit fetchByCompanyId_First(
		long companyId,
		OrderByComparator<CPMeasurementUnit> orderByComparator) {

		List<CPMeasurementUnit> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp measurement unit in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp measurement unit
	 * @throws NoSuchCPMeasurementUnitException if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit findByCompanyId_Last(
			long companyId,
			OrderByComparator<CPMeasurementUnit> orderByComparator)
		throws NoSuchCPMeasurementUnitException {

		CPMeasurementUnit cpMeasurementUnit = fetchByCompanyId_Last(
			companyId, orderByComparator);

		if (cpMeasurementUnit != null) {
			return cpMeasurementUnit;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPMeasurementUnitException(msg.toString());
	}

	/**
	 * Returns the last cp measurement unit in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp measurement unit, or <code>null</code> if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit fetchByCompanyId_Last(
		long companyId,
		OrderByComparator<CPMeasurementUnit> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<CPMeasurementUnit> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp measurement units before and after the current cp measurement unit in the ordered set where companyId = &#63;.
	 *
	 * @param CPMeasurementUnitId the primary key of the current cp measurement unit
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp measurement unit
	 * @throws NoSuchCPMeasurementUnitException if a cp measurement unit with the primary key could not be found
	 */
	@Override
	public CPMeasurementUnit[] findByCompanyId_PrevAndNext(
			long CPMeasurementUnitId, long companyId,
			OrderByComparator<CPMeasurementUnit> orderByComparator)
		throws NoSuchCPMeasurementUnitException {

		CPMeasurementUnit cpMeasurementUnit = findByPrimaryKey(
			CPMeasurementUnitId);

		Session session = null;

		try {
			session = openSession();

			CPMeasurementUnit[] array = new CPMeasurementUnitImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, cpMeasurementUnit, companyId, orderByComparator, true);

			array[1] = cpMeasurementUnit;

			array[2] = getByCompanyId_PrevAndNext(
				session, cpMeasurementUnit, companyId, orderByComparator,
				false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPMeasurementUnit getByCompanyId_PrevAndNext(
		Session session, CPMeasurementUnit cpMeasurementUnit, long companyId,
		OrderByComparator<CPMeasurementUnit> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CPMEASUREMENTUNIT_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CPMeasurementUnitModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpMeasurementUnit)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPMeasurementUnit> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp measurement units where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (CPMeasurementUnit cpMeasurementUnit :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cpMeasurementUnit);
		}
	}

	/**
	 * Returns the number of cp measurement units where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching cp measurement units
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] {companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPMEASUREMENTUNIT_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 =
		"cpMeasurementUnit.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByC_T;
	private FinderPath _finderPathWithoutPaginationFindByC_T;
	private FinderPath _finderPathCountByC_T;

	/**
	 * Returns all the cp measurement units where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @return the matching cp measurement units
	 */
	@Override
	public List<CPMeasurementUnit> findByC_T(long companyId, int type) {
		return findByC_T(
			companyId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp measurement units where companyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPMeasurementUnitModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param start the lower bound of the range of cp measurement units
	 * @param end the upper bound of the range of cp measurement units (not inclusive)
	 * @return the range of matching cp measurement units
	 */
	@Override
	public List<CPMeasurementUnit> findByC_T(
		long companyId, int type, int start, int end) {

		return findByC_T(companyId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp measurement units where companyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPMeasurementUnitModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param start the lower bound of the range of cp measurement units
	 * @param end the upper bound of the range of cp measurement units (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp measurement units
	 */
	@Override
	public List<CPMeasurementUnit> findByC_T(
		long companyId, int type, int start, int end,
		OrderByComparator<CPMeasurementUnit> orderByComparator) {

		return findByC_T(companyId, type, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp measurement units where companyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPMeasurementUnitModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param start the lower bound of the range of cp measurement units
	 * @param end the upper bound of the range of cp measurement units (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp measurement units
	 */
	@Override
	public List<CPMeasurementUnit> findByC_T(
		long companyId, int type, int start, int end,
		OrderByComparator<CPMeasurementUnit> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByC_T;
			finderArgs = new Object[] {companyId, type};
		}
		else {
			finderPath = _finderPathWithPaginationFindByC_T;
			finderArgs = new Object[] {
				companyId, type, start, end, orderByComparator
			};
		}

		List<CPMeasurementUnit> list = null;

		if (retrieveFromCache) {
			list = (List<CPMeasurementUnit>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPMeasurementUnit cpMeasurementUnit : list) {
					if ((companyId != cpMeasurementUnit.getCompanyId()) ||
						(type != cpMeasurementUnit.getType())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_CPMEASUREMENTUNIT_WHERE);

			query.append(_FINDER_COLUMN_C_T_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CPMeasurementUnitModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(type);

				if (!pagination) {
					list = (List<CPMeasurementUnit>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPMeasurementUnit>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first cp measurement unit in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp measurement unit
	 * @throws NoSuchCPMeasurementUnitException if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit findByC_T_First(
			long companyId, int type,
			OrderByComparator<CPMeasurementUnit> orderByComparator)
		throws NoSuchCPMeasurementUnitException {

		CPMeasurementUnit cpMeasurementUnit = fetchByC_T_First(
			companyId, type, orderByComparator);

		if (cpMeasurementUnit != null) {
			return cpMeasurementUnit;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchCPMeasurementUnitException(msg.toString());
	}

	/**
	 * Returns the first cp measurement unit in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp measurement unit, or <code>null</code> if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit fetchByC_T_First(
		long companyId, int type,
		OrderByComparator<CPMeasurementUnit> orderByComparator) {

		List<CPMeasurementUnit> list = findByC_T(
			companyId, type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp measurement unit in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp measurement unit
	 * @throws NoSuchCPMeasurementUnitException if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit findByC_T_Last(
			long companyId, int type,
			OrderByComparator<CPMeasurementUnit> orderByComparator)
		throws NoSuchCPMeasurementUnitException {

		CPMeasurementUnit cpMeasurementUnit = fetchByC_T_Last(
			companyId, type, orderByComparator);

		if (cpMeasurementUnit != null) {
			return cpMeasurementUnit;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchCPMeasurementUnitException(msg.toString());
	}

	/**
	 * Returns the last cp measurement unit in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp measurement unit, or <code>null</code> if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit fetchByC_T_Last(
		long companyId, int type,
		OrderByComparator<CPMeasurementUnit> orderByComparator) {

		int count = countByC_T(companyId, type);

		if (count == 0) {
			return null;
		}

		List<CPMeasurementUnit> list = findByC_T(
			companyId, type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp measurement units before and after the current cp measurement unit in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param CPMeasurementUnitId the primary key of the current cp measurement unit
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp measurement unit
	 * @throws NoSuchCPMeasurementUnitException if a cp measurement unit with the primary key could not be found
	 */
	@Override
	public CPMeasurementUnit[] findByC_T_PrevAndNext(
			long CPMeasurementUnitId, long companyId, int type,
			OrderByComparator<CPMeasurementUnit> orderByComparator)
		throws NoSuchCPMeasurementUnitException {

		CPMeasurementUnit cpMeasurementUnit = findByPrimaryKey(
			CPMeasurementUnitId);

		Session session = null;

		try {
			session = openSession();

			CPMeasurementUnit[] array = new CPMeasurementUnitImpl[3];

			array[0] = getByC_T_PrevAndNext(
				session, cpMeasurementUnit, companyId, type, orderByComparator,
				true);

			array[1] = cpMeasurementUnit;

			array[2] = getByC_T_PrevAndNext(
				session, cpMeasurementUnit, companyId, type, orderByComparator,
				false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPMeasurementUnit getByC_T_PrevAndNext(
		Session session, CPMeasurementUnit cpMeasurementUnit, long companyId,
		int type, OrderByComparator<CPMeasurementUnit> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_CPMEASUREMENTUNIT_WHERE);

		query.append(_FINDER_COLUMN_C_T_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_T_TYPE_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CPMeasurementUnitModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(type);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpMeasurementUnit)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPMeasurementUnit> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp measurement units where companyId = &#63; and type = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 */
	@Override
	public void removeByC_T(long companyId, int type) {
		for (CPMeasurementUnit cpMeasurementUnit :
				findByC_T(
					companyId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(cpMeasurementUnit);
		}
	}

	/**
	 * Returns the number of cp measurement units where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @return the number of matching cp measurement units
	 */
	@Override
	public int countByC_T(long companyId, int type) {
		FinderPath finderPath = _finderPathCountByC_T;

		Object[] finderArgs = new Object[] {companyId, type};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPMEASUREMENTUNIT_WHERE);

			query.append(_FINDER_COLUMN_C_T_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(type);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_T_COMPANYID_2 =
		"cpMeasurementUnit.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_T_TYPE_2 =
		"cpMeasurementUnit.type = ?";

	private FinderPath _finderPathFetchByC_K_T;
	private FinderPath _finderPathCountByC_K_T;

	/**
	 * Returns the cp measurement unit where companyId = &#63; and key = &#63; and type = &#63; or throws a <code>NoSuchCPMeasurementUnitException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param key the key
	 * @param type the type
	 * @return the matching cp measurement unit
	 * @throws NoSuchCPMeasurementUnitException if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit findByC_K_T(long companyId, String key, int type)
		throws NoSuchCPMeasurementUnitException {

		CPMeasurementUnit cpMeasurementUnit = fetchByC_K_T(
			companyId, key, type);

		if (cpMeasurementUnit == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", key=");
			msg.append(key);

			msg.append(", type=");
			msg.append(type);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCPMeasurementUnitException(msg.toString());
		}

		return cpMeasurementUnit;
	}

	/**
	 * Returns the cp measurement unit where companyId = &#63; and key = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param key the key
	 * @param type the type
	 * @return the matching cp measurement unit, or <code>null</code> if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit fetchByC_K_T(
		long companyId, String key, int type) {

		return fetchByC_K_T(companyId, key, type, true);
	}

	/**
	 * Returns the cp measurement unit where companyId = &#63; and key = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param key the key
	 * @param type the type
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching cp measurement unit, or <code>null</code> if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit fetchByC_K_T(
		long companyId, String key, int type, boolean retrieveFromCache) {

		key = Objects.toString(key, "");

		Object[] finderArgs = new Object[] {companyId, key, type};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_K_T, finderArgs, this);
		}

		if (result instanceof CPMeasurementUnit) {
			CPMeasurementUnit cpMeasurementUnit = (CPMeasurementUnit)result;

			if ((companyId != cpMeasurementUnit.getCompanyId()) ||
				!Objects.equals(key, cpMeasurementUnit.getKey()) ||
				(type != cpMeasurementUnit.getType())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_CPMEASUREMENTUNIT_WHERE);

			query.append(_FINDER_COLUMN_C_K_T_COMPANYID_2);

			boolean bindKey = false;

			if (key.isEmpty()) {
				query.append(_FINDER_COLUMN_C_K_T_KEY_3);
			}
			else {
				bindKey = true;

				query.append(_FINDER_COLUMN_C_K_T_KEY_2);
			}

			query.append(_FINDER_COLUMN_C_K_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindKey) {
					qPos.add(StringUtil.toLowerCase(key));
				}

				qPos.add(type);

				List<CPMeasurementUnit> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByC_K_T, finderArgs, list);
				}
				else {
					CPMeasurementUnit cpMeasurementUnit = list.get(0);

					result = cpMeasurementUnit;

					cacheResult(cpMeasurementUnit);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByC_K_T, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (CPMeasurementUnit)result;
		}
	}

	/**
	 * Removes the cp measurement unit where companyId = &#63; and key = &#63; and type = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param key the key
	 * @param type the type
	 * @return the cp measurement unit that was removed
	 */
	@Override
	public CPMeasurementUnit removeByC_K_T(long companyId, String key, int type)
		throws NoSuchCPMeasurementUnitException {

		CPMeasurementUnit cpMeasurementUnit = findByC_K_T(companyId, key, type);

		return remove(cpMeasurementUnit);
	}

	/**
	 * Returns the number of cp measurement units where companyId = &#63; and key = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param key the key
	 * @param type the type
	 * @return the number of matching cp measurement units
	 */
	@Override
	public int countByC_K_T(long companyId, String key, int type) {
		key = Objects.toString(key, "");

		FinderPath finderPath = _finderPathCountByC_K_T;

		Object[] finderArgs = new Object[] {companyId, key, type};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CPMEASUREMENTUNIT_WHERE);

			query.append(_FINDER_COLUMN_C_K_T_COMPANYID_2);

			boolean bindKey = false;

			if (key.isEmpty()) {
				query.append(_FINDER_COLUMN_C_K_T_KEY_3);
			}
			else {
				bindKey = true;

				query.append(_FINDER_COLUMN_C_K_T_KEY_2);
			}

			query.append(_FINDER_COLUMN_C_K_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindKey) {
					qPos.add(StringUtil.toLowerCase(key));
				}

				qPos.add(type);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_K_T_COMPANYID_2 =
		"cpMeasurementUnit.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_K_T_KEY_2 =
		"lower(cpMeasurementUnit.key) = ? AND ";

	private static final String _FINDER_COLUMN_C_K_T_KEY_3 =
		"(cpMeasurementUnit.key IS NULL OR cpMeasurementUnit.key = '') AND ";

	private static final String _FINDER_COLUMN_C_K_T_TYPE_2 =
		"cpMeasurementUnit.type = ?";

	private FinderPath _finderPathWithPaginationFindByC_P_T;
	private FinderPath _finderPathWithoutPaginationFindByC_P_T;
	private FinderPath _finderPathCountByC_P_T;

	/**
	 * Returns all the cp measurement units where companyId = &#63; and primary = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param type the type
	 * @return the matching cp measurement units
	 */
	@Override
	public List<CPMeasurementUnit> findByC_P_T(
		long companyId, boolean primary, int type) {

		return findByC_P_T(
			companyId, primary, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the cp measurement units where companyId = &#63; and primary = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPMeasurementUnitModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param type the type
	 * @param start the lower bound of the range of cp measurement units
	 * @param end the upper bound of the range of cp measurement units (not inclusive)
	 * @return the range of matching cp measurement units
	 */
	@Override
	public List<CPMeasurementUnit> findByC_P_T(
		long companyId, boolean primary, int type, int start, int end) {

		return findByC_P_T(companyId, primary, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp measurement units where companyId = &#63; and primary = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPMeasurementUnitModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param type the type
	 * @param start the lower bound of the range of cp measurement units
	 * @param end the upper bound of the range of cp measurement units (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp measurement units
	 */
	@Override
	public List<CPMeasurementUnit> findByC_P_T(
		long companyId, boolean primary, int type, int start, int end,
		OrderByComparator<CPMeasurementUnit> orderByComparator) {

		return findByC_P_T(
			companyId, primary, type, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp measurement units where companyId = &#63; and primary = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPMeasurementUnitModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param type the type
	 * @param start the lower bound of the range of cp measurement units
	 * @param end the upper bound of the range of cp measurement units (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp measurement units
	 */
	@Override
	public List<CPMeasurementUnit> findByC_P_T(
		long companyId, boolean primary, int type, int start, int end,
		OrderByComparator<CPMeasurementUnit> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByC_P_T;
			finderArgs = new Object[] {companyId, primary, type};
		}
		else {
			finderPath = _finderPathWithPaginationFindByC_P_T;
			finderArgs = new Object[] {
				companyId, primary, type, start, end, orderByComparator
			};
		}

		List<CPMeasurementUnit> list = null;

		if (retrieveFromCache) {
			list = (List<CPMeasurementUnit>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPMeasurementUnit cpMeasurementUnit : list) {
					if ((companyId != cpMeasurementUnit.getCompanyId()) ||
						(primary != cpMeasurementUnit.isPrimary()) ||
						(type != cpMeasurementUnit.getType())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					5 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_CPMEASUREMENTUNIT_WHERE);

			query.append(_FINDER_COLUMN_C_P_T_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_P_T_PRIMARY_2);

			query.append(_FINDER_COLUMN_C_P_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CPMeasurementUnitModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(primary);

				qPos.add(type);

				if (!pagination) {
					list = (List<CPMeasurementUnit>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPMeasurementUnit>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first cp measurement unit in the ordered set where companyId = &#63; and primary = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp measurement unit
	 * @throws NoSuchCPMeasurementUnitException if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit findByC_P_T_First(
			long companyId, boolean primary, int type,
			OrderByComparator<CPMeasurementUnit> orderByComparator)
		throws NoSuchCPMeasurementUnitException {

		CPMeasurementUnit cpMeasurementUnit = fetchByC_P_T_First(
			companyId, primary, type, orderByComparator);

		if (cpMeasurementUnit != null) {
			return cpMeasurementUnit;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", primary=");
		msg.append(primary);

		msg.append(", type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchCPMeasurementUnitException(msg.toString());
	}

	/**
	 * Returns the first cp measurement unit in the ordered set where companyId = &#63; and primary = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp measurement unit, or <code>null</code> if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit fetchByC_P_T_First(
		long companyId, boolean primary, int type,
		OrderByComparator<CPMeasurementUnit> orderByComparator) {

		List<CPMeasurementUnit> list = findByC_P_T(
			companyId, primary, type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp measurement unit in the ordered set where companyId = &#63; and primary = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp measurement unit
	 * @throws NoSuchCPMeasurementUnitException if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit findByC_P_T_Last(
			long companyId, boolean primary, int type,
			OrderByComparator<CPMeasurementUnit> orderByComparator)
		throws NoSuchCPMeasurementUnitException {

		CPMeasurementUnit cpMeasurementUnit = fetchByC_P_T_Last(
			companyId, primary, type, orderByComparator);

		if (cpMeasurementUnit != null) {
			return cpMeasurementUnit;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", primary=");
		msg.append(primary);

		msg.append(", type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchCPMeasurementUnitException(msg.toString());
	}

	/**
	 * Returns the last cp measurement unit in the ordered set where companyId = &#63; and primary = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp measurement unit, or <code>null</code> if a matching cp measurement unit could not be found
	 */
	@Override
	public CPMeasurementUnit fetchByC_P_T_Last(
		long companyId, boolean primary, int type,
		OrderByComparator<CPMeasurementUnit> orderByComparator) {

		int count = countByC_P_T(companyId, primary, type);

		if (count == 0) {
			return null;
		}

		List<CPMeasurementUnit> list = findByC_P_T(
			companyId, primary, type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp measurement units before and after the current cp measurement unit in the ordered set where companyId = &#63; and primary = &#63; and type = &#63;.
	 *
	 * @param CPMeasurementUnitId the primary key of the current cp measurement unit
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp measurement unit
	 * @throws NoSuchCPMeasurementUnitException if a cp measurement unit with the primary key could not be found
	 */
	@Override
	public CPMeasurementUnit[] findByC_P_T_PrevAndNext(
			long CPMeasurementUnitId, long companyId, boolean primary, int type,
			OrderByComparator<CPMeasurementUnit> orderByComparator)
		throws NoSuchCPMeasurementUnitException {

		CPMeasurementUnit cpMeasurementUnit = findByPrimaryKey(
			CPMeasurementUnitId);

		Session session = null;

		try {
			session = openSession();

			CPMeasurementUnit[] array = new CPMeasurementUnitImpl[3];

			array[0] = getByC_P_T_PrevAndNext(
				session, cpMeasurementUnit, companyId, primary, type,
				orderByComparator, true);

			array[1] = cpMeasurementUnit;

			array[2] = getByC_P_T_PrevAndNext(
				session, cpMeasurementUnit, companyId, primary, type,
				orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPMeasurementUnit getByC_P_T_PrevAndNext(
		Session session, CPMeasurementUnit cpMeasurementUnit, long companyId,
		boolean primary, int type,
		OrderByComparator<CPMeasurementUnit> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_CPMEASUREMENTUNIT_WHERE);

		query.append(_FINDER_COLUMN_C_P_T_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_P_T_PRIMARY_2);

		query.append(_FINDER_COLUMN_C_P_T_TYPE_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CPMeasurementUnitModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(primary);

		qPos.add(type);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpMeasurementUnit)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPMeasurementUnit> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp measurement units where companyId = &#63; and primary = &#63; and type = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param type the type
	 */
	@Override
	public void removeByC_P_T(long companyId, boolean primary, int type) {
		for (CPMeasurementUnit cpMeasurementUnit :
				findByC_P_T(
					companyId, primary, type, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(cpMeasurementUnit);
		}
	}

	/**
	 * Returns the number of cp measurement units where companyId = &#63; and primary = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param type the type
	 * @return the number of matching cp measurement units
	 */
	@Override
	public int countByC_P_T(long companyId, boolean primary, int type) {
		FinderPath finderPath = _finderPathCountByC_P_T;

		Object[] finderArgs = new Object[] {companyId, primary, type};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CPMEASUREMENTUNIT_WHERE);

			query.append(_FINDER_COLUMN_C_P_T_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_P_T_PRIMARY_2);

			query.append(_FINDER_COLUMN_C_P_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(primary);

				qPos.add(type);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_P_T_COMPANYID_2 =
		"cpMeasurementUnit.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_P_T_PRIMARY_2 =
		"cpMeasurementUnit.primary = ? AND ";

	private static final String _FINDER_COLUMN_C_P_T_TYPE_2 =
		"cpMeasurementUnit.type = ?";

	public CPMeasurementUnitPersistenceImpl() {
		setModelClass(CPMeasurementUnit.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("key", "key_");
		dbColumnNames.put("primary", "primary_");
		dbColumnNames.put("type", "type_");

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

	/**
	 * Caches the cp measurement unit in the entity cache if it is enabled.
	 *
	 * @param cpMeasurementUnit the cp measurement unit
	 */
	@Override
	public void cacheResult(CPMeasurementUnit cpMeasurementUnit) {
		entityCache.putResult(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitImpl.class, cpMeasurementUnit.getPrimaryKey(),
			cpMeasurementUnit);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				cpMeasurementUnit.getUuid(), cpMeasurementUnit.getGroupId()
			},
			cpMeasurementUnit);

		finderCache.putResult(
			_finderPathFetchByC_K_T,
			new Object[] {
				cpMeasurementUnit.getCompanyId(), cpMeasurementUnit.getKey(),
				cpMeasurementUnit.getType()
			},
			cpMeasurementUnit);

		cpMeasurementUnit.resetOriginalValues();
	}

	/**
	 * Caches the cp measurement units in the entity cache if it is enabled.
	 *
	 * @param cpMeasurementUnits the cp measurement units
	 */
	@Override
	public void cacheResult(List<CPMeasurementUnit> cpMeasurementUnits) {
		for (CPMeasurementUnit cpMeasurementUnit : cpMeasurementUnits) {
			if (entityCache.getResult(
					CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
					CPMeasurementUnitImpl.class,
					cpMeasurementUnit.getPrimaryKey()) == null) {

				cacheResult(cpMeasurementUnit);
			}
			else {
				cpMeasurementUnit.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cp measurement units.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CPMeasurementUnitImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cp measurement unit.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CPMeasurementUnit cpMeasurementUnit) {
		entityCache.removeResult(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitImpl.class, cpMeasurementUnit.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(CPMeasurementUnitModelImpl)cpMeasurementUnit, true);
	}

	@Override
	public void clearCache(List<CPMeasurementUnit> cpMeasurementUnits) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CPMeasurementUnit cpMeasurementUnit : cpMeasurementUnits) {
			entityCache.removeResult(
				CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
				CPMeasurementUnitImpl.class, cpMeasurementUnit.getPrimaryKey());

			clearUniqueFindersCache(
				(CPMeasurementUnitModelImpl)cpMeasurementUnit, true);
		}
	}

	protected void cacheUniqueFindersCache(
		CPMeasurementUnitModelImpl cpMeasurementUnitModelImpl) {

		Object[] args = new Object[] {
			cpMeasurementUnitModelImpl.getUuid(),
			cpMeasurementUnitModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, cpMeasurementUnitModelImpl, false);

		args = new Object[] {
			cpMeasurementUnitModelImpl.getCompanyId(),
			cpMeasurementUnitModelImpl.getKey(),
			cpMeasurementUnitModelImpl.getType()
		};

		finderCache.putResult(
			_finderPathCountByC_K_T, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_K_T, args, cpMeasurementUnitModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CPMeasurementUnitModelImpl cpMeasurementUnitModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				cpMeasurementUnitModelImpl.getUuid(),
				cpMeasurementUnitModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((cpMeasurementUnitModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				cpMeasurementUnitModelImpl.getOriginalUuid(),
				cpMeasurementUnitModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
				cpMeasurementUnitModelImpl.getCompanyId(),
				cpMeasurementUnitModelImpl.getKey(),
				cpMeasurementUnitModelImpl.getType()
			};

			finderCache.removeResult(_finderPathCountByC_K_T, args);
			finderCache.removeResult(_finderPathFetchByC_K_T, args);
		}

		if ((cpMeasurementUnitModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_K_T.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				cpMeasurementUnitModelImpl.getOriginalCompanyId(),
				cpMeasurementUnitModelImpl.getOriginalKey(),
				cpMeasurementUnitModelImpl.getOriginalType()
			};

			finderCache.removeResult(_finderPathCountByC_K_T, args);
			finderCache.removeResult(_finderPathFetchByC_K_T, args);
		}
	}

	/**
	 * Creates a new cp measurement unit with the primary key. Does not add the cp measurement unit to the database.
	 *
	 * @param CPMeasurementUnitId the primary key for the new cp measurement unit
	 * @return the new cp measurement unit
	 */
	@Override
	public CPMeasurementUnit create(long CPMeasurementUnitId) {
		CPMeasurementUnit cpMeasurementUnit = new CPMeasurementUnitImpl();

		cpMeasurementUnit.setNew(true);
		cpMeasurementUnit.setPrimaryKey(CPMeasurementUnitId);

		String uuid = PortalUUIDUtil.generate();

		cpMeasurementUnit.setUuid(uuid);

		cpMeasurementUnit.setCompanyId(CompanyThreadLocal.getCompanyId());

		return cpMeasurementUnit;
	}

	/**
	 * Removes the cp measurement unit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPMeasurementUnitId the primary key of the cp measurement unit
	 * @return the cp measurement unit that was removed
	 * @throws NoSuchCPMeasurementUnitException if a cp measurement unit with the primary key could not be found
	 */
	@Override
	public CPMeasurementUnit remove(long CPMeasurementUnitId)
		throws NoSuchCPMeasurementUnitException {

		return remove((Serializable)CPMeasurementUnitId);
	}

	/**
	 * Removes the cp measurement unit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cp measurement unit
	 * @return the cp measurement unit that was removed
	 * @throws NoSuchCPMeasurementUnitException if a cp measurement unit with the primary key could not be found
	 */
	@Override
	public CPMeasurementUnit remove(Serializable primaryKey)
		throws NoSuchCPMeasurementUnitException {

		Session session = null;

		try {
			session = openSession();

			CPMeasurementUnit cpMeasurementUnit =
				(CPMeasurementUnit)session.get(
					CPMeasurementUnitImpl.class, primaryKey);

			if (cpMeasurementUnit == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCPMeasurementUnitException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(cpMeasurementUnit);
		}
		catch (NoSuchCPMeasurementUnitException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected CPMeasurementUnit removeImpl(
		CPMeasurementUnit cpMeasurementUnit) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cpMeasurementUnit)) {
				cpMeasurementUnit = (CPMeasurementUnit)session.get(
					CPMeasurementUnitImpl.class,
					cpMeasurementUnit.getPrimaryKeyObj());
			}

			if (cpMeasurementUnit != null) {
				session.delete(cpMeasurementUnit);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cpMeasurementUnit != null) {
			clearCache(cpMeasurementUnit);
		}

		return cpMeasurementUnit;
	}

	@Override
	public CPMeasurementUnit updateImpl(CPMeasurementUnit cpMeasurementUnit) {
		boolean isNew = cpMeasurementUnit.isNew();

		if (!(cpMeasurementUnit instanceof CPMeasurementUnitModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(cpMeasurementUnit.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					cpMeasurementUnit);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in cpMeasurementUnit proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CPMeasurementUnit implementation " +
					cpMeasurementUnit.getClass());
		}

		CPMeasurementUnitModelImpl cpMeasurementUnitModelImpl =
			(CPMeasurementUnitModelImpl)cpMeasurementUnit;

		if (Validator.isNull(cpMeasurementUnit.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			cpMeasurementUnit.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (cpMeasurementUnit.getCreateDate() == null)) {
			if (serviceContext == null) {
				cpMeasurementUnit.setCreateDate(now);
			}
			else {
				cpMeasurementUnit.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!cpMeasurementUnitModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				cpMeasurementUnit.setModifiedDate(now);
			}
			else {
				cpMeasurementUnit.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (cpMeasurementUnit.isNew()) {
				session.save(cpMeasurementUnit);

				cpMeasurementUnit.setNew(false);
			}
			else {
				cpMeasurementUnit = (CPMeasurementUnit)session.merge(
					cpMeasurementUnit);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CPMeasurementUnitModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {cpMeasurementUnitModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				cpMeasurementUnitModelImpl.getUuid(),
				cpMeasurementUnitModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {cpMeasurementUnitModelImpl.getCompanyId()};

			finderCache.removeResult(_finderPathCountByCompanyId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCompanyId, args);

			args = new Object[] {
				cpMeasurementUnitModelImpl.getCompanyId(),
				cpMeasurementUnitModelImpl.getType()
			};

			finderCache.removeResult(_finderPathCountByC_T, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_T, args);

			args = new Object[] {
				cpMeasurementUnitModelImpl.getCompanyId(),
				cpMeasurementUnitModelImpl.isPrimary(),
				cpMeasurementUnitModelImpl.getType()
			};

			finderCache.removeResult(_finderPathCountByC_P_T, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_P_T, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((cpMeasurementUnitModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpMeasurementUnitModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {cpMeasurementUnitModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((cpMeasurementUnitModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpMeasurementUnitModelImpl.getOriginalUuid(),
					cpMeasurementUnitModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					cpMeasurementUnitModelImpl.getUuid(),
					cpMeasurementUnitModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((cpMeasurementUnitModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCompanyId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					cpMeasurementUnitModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);

				args = new Object[] {cpMeasurementUnitModelImpl.getCompanyId()};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);
			}

			if ((cpMeasurementUnitModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_T.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpMeasurementUnitModelImpl.getOriginalCompanyId(),
					cpMeasurementUnitModelImpl.getOriginalType()
				};

				finderCache.removeResult(_finderPathCountByC_T, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_T, args);

				args = new Object[] {
					cpMeasurementUnitModelImpl.getCompanyId(),
					cpMeasurementUnitModelImpl.getType()
				};

				finderCache.removeResult(_finderPathCountByC_T, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_T, args);
			}

			if ((cpMeasurementUnitModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_P_T.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpMeasurementUnitModelImpl.getOriginalCompanyId(),
					cpMeasurementUnitModelImpl.getOriginalPrimary(),
					cpMeasurementUnitModelImpl.getOriginalType()
				};

				finderCache.removeResult(_finderPathCountByC_P_T, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_P_T, args);

				args = new Object[] {
					cpMeasurementUnitModelImpl.getCompanyId(),
					cpMeasurementUnitModelImpl.isPrimary(),
					cpMeasurementUnitModelImpl.getType()
				};

				finderCache.removeResult(_finderPathCountByC_P_T, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_P_T, args);
			}
		}

		entityCache.putResult(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitImpl.class, cpMeasurementUnit.getPrimaryKey(),
			cpMeasurementUnit, false);

		clearUniqueFindersCache(cpMeasurementUnitModelImpl, false);
		cacheUniqueFindersCache(cpMeasurementUnitModelImpl);

		cpMeasurementUnit.resetOriginalValues();

		return cpMeasurementUnit;
	}

	/**
	 * Returns the cp measurement unit with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp measurement unit
	 * @return the cp measurement unit
	 * @throws NoSuchCPMeasurementUnitException if a cp measurement unit with the primary key could not be found
	 */
	@Override
	public CPMeasurementUnit findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCPMeasurementUnitException {

		CPMeasurementUnit cpMeasurementUnit = fetchByPrimaryKey(primaryKey);

		if (cpMeasurementUnit == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCPMeasurementUnitException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return cpMeasurementUnit;
	}

	/**
	 * Returns the cp measurement unit with the primary key or throws a <code>NoSuchCPMeasurementUnitException</code> if it could not be found.
	 *
	 * @param CPMeasurementUnitId the primary key of the cp measurement unit
	 * @return the cp measurement unit
	 * @throws NoSuchCPMeasurementUnitException if a cp measurement unit with the primary key could not be found
	 */
	@Override
	public CPMeasurementUnit findByPrimaryKey(long CPMeasurementUnitId)
		throws NoSuchCPMeasurementUnitException {

		return findByPrimaryKey((Serializable)CPMeasurementUnitId);
	}

	/**
	 * Returns the cp measurement unit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp measurement unit
	 * @return the cp measurement unit, or <code>null</code> if a cp measurement unit with the primary key could not be found
	 */
	@Override
	public CPMeasurementUnit fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CPMeasurementUnit cpMeasurementUnit = (CPMeasurementUnit)serializable;

		if (cpMeasurementUnit == null) {
			Session session = null;

			try {
				session = openSession();

				cpMeasurementUnit = (CPMeasurementUnit)session.get(
					CPMeasurementUnitImpl.class, primaryKey);

				if (cpMeasurementUnit != null) {
					cacheResult(cpMeasurementUnit);
				}
				else {
					entityCache.putResult(
						CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
						CPMeasurementUnitImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
					CPMeasurementUnitImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cpMeasurementUnit;
	}

	/**
	 * Returns the cp measurement unit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param CPMeasurementUnitId the primary key of the cp measurement unit
	 * @return the cp measurement unit, or <code>null</code> if a cp measurement unit with the primary key could not be found
	 */
	@Override
	public CPMeasurementUnit fetchByPrimaryKey(long CPMeasurementUnitId) {
		return fetchByPrimaryKey((Serializable)CPMeasurementUnitId);
	}

	@Override
	public Map<Serializable, CPMeasurementUnit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CPMeasurementUnit> map =
			new HashMap<Serializable, CPMeasurementUnit>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CPMeasurementUnit cpMeasurementUnit = fetchByPrimaryKey(primaryKey);

			if (cpMeasurementUnit != null) {
				map.put(primaryKey, cpMeasurementUnit);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
				CPMeasurementUnitImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CPMeasurementUnit)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_CPMEASUREMENTUNIT_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (CPMeasurementUnit cpMeasurementUnit :
					(List<CPMeasurementUnit>)q.list()) {

				map.put(
					cpMeasurementUnit.getPrimaryKeyObj(), cpMeasurementUnit);

				cacheResult(cpMeasurementUnit);

				uncachedPrimaryKeys.remove(
					cpMeasurementUnit.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
					CPMeasurementUnitImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the cp measurement units.
	 *
	 * @return the cp measurement units
	 */
	@Override
	public List<CPMeasurementUnit> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp measurement units.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPMeasurementUnitModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp measurement units
	 * @param end the upper bound of the range of cp measurement units (not inclusive)
	 * @return the range of cp measurement units
	 */
	@Override
	public List<CPMeasurementUnit> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp measurement units.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPMeasurementUnitModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp measurement units
	 * @param end the upper bound of the range of cp measurement units (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cp measurement units
	 */
	@Override
	public List<CPMeasurementUnit> findAll(
		int start, int end,
		OrderByComparator<CPMeasurementUnit> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp measurement units.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPMeasurementUnitModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp measurement units
	 * @param end the upper bound of the range of cp measurement units (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cp measurement units
	 */
	@Override
	public List<CPMeasurementUnit> findAll(
		int start, int end,
		OrderByComparator<CPMeasurementUnit> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindAll;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<CPMeasurementUnit> list = null;

		if (retrieveFromCache) {
			list = (List<CPMeasurementUnit>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CPMEASUREMENTUNIT);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CPMEASUREMENTUNIT;

				if (pagination) {
					sql = sql.concat(CPMeasurementUnitModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CPMeasurementUnit>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPMeasurementUnit>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the cp measurement units from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CPMeasurementUnit cpMeasurementUnit : findAll()) {
			remove(cpMeasurementUnit);
		}
	}

	/**
	 * Returns the number of cp measurement units.
	 *
	 * @return the number of cp measurement units
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CPMEASUREMENTUNIT);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CPMeasurementUnitModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cp measurement unit persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitModelImpl.FINDER_CACHE_ENABLED,
			CPMeasurementUnitImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitModelImpl.FINDER_CACHE_ENABLED,
			CPMeasurementUnitImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitModelImpl.FINDER_CACHE_ENABLED,
			CPMeasurementUnitImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitModelImpl.FINDER_CACHE_ENABLED,
			CPMeasurementUnitImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			CPMeasurementUnitModelImpl.UUID_COLUMN_BITMASK |
			CPMeasurementUnitModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitModelImpl.FINDER_CACHE_ENABLED,
			CPMeasurementUnitImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			CPMeasurementUnitModelImpl.UUID_COLUMN_BITMASK |
			CPMeasurementUnitModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitModelImpl.FINDER_CACHE_ENABLED,
			CPMeasurementUnitImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitModelImpl.FINDER_CACHE_ENABLED,
			CPMeasurementUnitImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			CPMeasurementUnitModelImpl.UUID_COLUMN_BITMASK |
			CPMeasurementUnitModelImpl.COMPANYID_COLUMN_BITMASK |
			CPMeasurementUnitModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByCompanyId = new FinderPath(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitModelImpl.FINDER_CACHE_ENABLED,
			CPMeasurementUnitImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCompanyId = new FinderPath(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitModelImpl.FINDER_CACHE_ENABLED,
			CPMeasurementUnitImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] {Long.class.getName()},
			CPMeasurementUnitModelImpl.COMPANYID_COLUMN_BITMASK |
			CPMeasurementUnitModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByCompanyId = new FinderPath(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByC_T = new FinderPath(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitModelImpl.FINDER_CACHE_ENABLED,
			CPMeasurementUnitImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_T",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_T = new FinderPath(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitModelImpl.FINDER_CACHE_ENABLED,
			CPMeasurementUnitImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_T",
			new String[] {Long.class.getName(), Integer.class.getName()},
			CPMeasurementUnitModelImpl.COMPANYID_COLUMN_BITMASK |
			CPMeasurementUnitModelImpl.TYPE_COLUMN_BITMASK |
			CPMeasurementUnitModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByC_T = new FinderPath(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_T",
			new String[] {Long.class.getName(), Integer.class.getName()});

		_finderPathFetchByC_K_T = new FinderPath(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitModelImpl.FINDER_CACHE_ENABLED,
			CPMeasurementUnitImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_K_T",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			},
			CPMeasurementUnitModelImpl.COMPANYID_COLUMN_BITMASK |
			CPMeasurementUnitModelImpl.KEY_COLUMN_BITMASK |
			CPMeasurementUnitModelImpl.TYPE_COLUMN_BITMASK);

		_finderPathCountByC_K_T = new FinderPath(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_K_T",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			});

		_finderPathWithPaginationFindByC_P_T = new FinderPath(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitModelImpl.FINDER_CACHE_ENABLED,
			CPMeasurementUnitImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_P_T",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_P_T = new FinderPath(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitModelImpl.FINDER_CACHE_ENABLED,
			CPMeasurementUnitImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_P_T",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Integer.class.getName()
			},
			CPMeasurementUnitModelImpl.COMPANYID_COLUMN_BITMASK |
			CPMeasurementUnitModelImpl.PRIMARY_COLUMN_BITMASK |
			CPMeasurementUnitModelImpl.TYPE_COLUMN_BITMASK |
			CPMeasurementUnitModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByC_P_T = new FinderPath(
			CPMeasurementUnitModelImpl.ENTITY_CACHE_ENABLED,
			CPMeasurementUnitModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_P_T",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Integer.class.getName()
			});
	}

	public void destroy() {
		entityCache.removeCache(CPMeasurementUnitImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_CPMEASUREMENTUNIT =
		"SELECT cpMeasurementUnit FROM CPMeasurementUnit cpMeasurementUnit";

	private static final String _SQL_SELECT_CPMEASUREMENTUNIT_WHERE_PKS_IN =
		"SELECT cpMeasurementUnit FROM CPMeasurementUnit cpMeasurementUnit WHERE CPMeasurementUnitId IN (";

	private static final String _SQL_SELECT_CPMEASUREMENTUNIT_WHERE =
		"SELECT cpMeasurementUnit FROM CPMeasurementUnit cpMeasurementUnit WHERE ";

	private static final String _SQL_COUNT_CPMEASUREMENTUNIT =
		"SELECT COUNT(cpMeasurementUnit) FROM CPMeasurementUnit cpMeasurementUnit";

	private static final String _SQL_COUNT_CPMEASUREMENTUNIT_WHERE =
		"SELECT COUNT(cpMeasurementUnit) FROM CPMeasurementUnit cpMeasurementUnit WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "cpMeasurementUnit.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CPMeasurementUnit exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CPMeasurementUnit exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CPMeasurementUnitPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "key", "primary", "type"});

}