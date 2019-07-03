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

import com.liferay.commerce.product.exception.NoSuchCPInstanceException;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.impl.CPInstanceImpl;
import com.liferay.commerce.product.model.impl.CPInstanceModelImpl;
import com.liferay.commerce.product.service.persistence.CPInstancePersistence;
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

import java.sql.Timestamp;

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
 * The persistence implementation for the cp instance service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @generated
 */
@ProviderType
public class CPInstancePersistenceImpl
	extends BasePersistenceImpl<CPInstance> implements CPInstancePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CPInstanceUtil</code> to access the cp instance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CPInstanceImpl.class.getName();

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
	 * Returns all the cp instances where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cp instances
	 */
	@Override
	public List<CPInstance> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp instances where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @return the range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp instances where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPInstance> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp instances where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPInstance> orderByComparator,
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

		List<CPInstance> list = null;

		if (retrieveFromCache) {
			list = (List<CPInstance>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPInstance cpInstance : list) {
					if (!uuid.equals(cpInstance.getUuid())) {
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

			query.append(_SQL_SELECT_CPINSTANCE_WHERE);

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
				query.append(CPInstanceModelImpl.ORDER_BY_JPQL);
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
					list = (List<CPInstance>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPInstance>)QueryUtil.list(
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
	 * Returns the first cp instance in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	@Override
	public CPInstance findByUuid_First(
			String uuid, OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByUuid_First(uuid, orderByComparator);

		if (cpInstance != null) {
			return cpInstance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCPInstanceException(msg.toString());
	}

	/**
	 * Returns the first cp instance in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByUuid_First(
		String uuid, OrderByComparator<CPInstance> orderByComparator) {

		List<CPInstance> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp instance in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	@Override
	public CPInstance findByUuid_Last(
			String uuid, OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByUuid_Last(uuid, orderByComparator);

		if (cpInstance != null) {
			return cpInstance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCPInstanceException(msg.toString());
	}

	/**
	 * Returns the last cp instance in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByUuid_Last(
		String uuid, OrderByComparator<CPInstance> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CPInstance> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp instances before and after the current cp instance in the ordered set where uuid = &#63;.
	 *
	 * @param CPInstanceId the primary key of the current cp instance
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp instance
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	@Override
	public CPInstance[] findByUuid_PrevAndNext(
			long CPInstanceId, String uuid,
			OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		uuid = Objects.toString(uuid, "");

		CPInstance cpInstance = findByPrimaryKey(CPInstanceId);

		Session session = null;

		try {
			session = openSession();

			CPInstance[] array = new CPInstanceImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, cpInstance, uuid, orderByComparator, true);

			array[1] = cpInstance;

			array[2] = getByUuid_PrevAndNext(
				session, cpInstance, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPInstance getByUuid_PrevAndNext(
		Session session, CPInstance cpInstance, String uuid,
		OrderByComparator<CPInstance> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CPINSTANCE_WHERE);

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
			query.append(CPInstanceModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(cpInstance)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp instances where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CPInstance cpInstance :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cpInstance);
		}
	}

	/**
	 * Returns the number of cp instances where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cp instances
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPINSTANCE_WHERE);

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
		"cpInstance.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(cpInstance.uuid IS NULL OR cpInstance.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the cp instance where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchCPInstanceException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	@Override
	public CPInstance findByUUID_G(String uuid, long groupId)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByUUID_G(uuid, groupId);

		if (cpInstance == null) {
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

			throw new NoSuchCPInstanceException(msg.toString());
		}

		return cpInstance;
	}

	/**
	 * Returns the cp instance where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the cp instance where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = new Object[] {uuid, groupId};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof CPInstance) {
			CPInstance cpInstance = (CPInstance)result;

			if (!Objects.equals(uuid, cpInstance.getUuid()) ||
				(groupId != cpInstance.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CPINSTANCE_WHERE);

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

				List<CPInstance> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByUUID_G, finderArgs, list);
				}
				else {
					CPInstance cpInstance = list.get(0);

					result = cpInstance;

					cacheResult(cpInstance);
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
			return (CPInstance)result;
		}
	}

	/**
	 * Removes the cp instance where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the cp instance that was removed
	 */
	@Override
	public CPInstance removeByUUID_G(String uuid, long groupId)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = findByUUID_G(uuid, groupId);

		return remove(cpInstance);
	}

	/**
	 * Returns the number of cp instances where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching cp instances
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPINSTANCE_WHERE);

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
		"cpInstance.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(cpInstance.uuid IS NULL OR cpInstance.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"cpInstance.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the cp instances where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching cp instances
	 */
	@Override
	public List<CPInstance> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp instances where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @return the range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp instances where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPInstance> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp instances where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPInstance> orderByComparator,
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

		List<CPInstance> list = null;

		if (retrieveFromCache) {
			list = (List<CPInstance>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPInstance cpInstance : list) {
					if (!uuid.equals(cpInstance.getUuid()) ||
						(companyId != cpInstance.getCompanyId())) {

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

			query.append(_SQL_SELECT_CPINSTANCE_WHERE);

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
				query.append(CPInstanceModelImpl.ORDER_BY_JPQL);
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
					list = (List<CPInstance>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPInstance>)QueryUtil.list(
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
	 * Returns the first cp instance in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	@Override
	public CPInstance findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (cpInstance != null) {
			return cpInstance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPInstanceException(msg.toString());
	}

	/**
	 * Returns the first cp instance in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CPInstance> orderByComparator) {

		List<CPInstance> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp instance in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	@Override
	public CPInstance findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (cpInstance != null) {
			return cpInstance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPInstanceException(msg.toString());
	}

	/**
	 * Returns the last cp instance in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CPInstance> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CPInstance> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp instances before and after the current cp instance in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param CPInstanceId the primary key of the current cp instance
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp instance
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	@Override
	public CPInstance[] findByUuid_C_PrevAndNext(
			long CPInstanceId, String uuid, long companyId,
			OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		uuid = Objects.toString(uuid, "");

		CPInstance cpInstance = findByPrimaryKey(CPInstanceId);

		Session session = null;

		try {
			session = openSession();

			CPInstance[] array = new CPInstanceImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, cpInstance, uuid, companyId, orderByComparator, true);

			array[1] = cpInstance;

			array[2] = getByUuid_C_PrevAndNext(
				session, cpInstance, uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPInstance getByUuid_C_PrevAndNext(
		Session session, CPInstance cpInstance, String uuid, long companyId,
		OrderByComparator<CPInstance> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_CPINSTANCE_WHERE);

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
			query.append(CPInstanceModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(cpInstance)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp instances where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CPInstance cpInstance :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(cpInstance);
		}
	}

	/**
	 * Returns the number of cp instances where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching cp instances
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPINSTANCE_WHERE);

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
		"cpInstance.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(cpInstance.uuid IS NULL OR cpInstance.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"cpInstance.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the cp instances where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching cp instances
	 */
	@Override
	public List<CPInstance> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp instances where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @return the range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp instances where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<CPInstance> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp instances where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<CPInstance> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByGroupId;
			finderArgs = new Object[] {groupId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByGroupId;
			finderArgs = new Object[] {groupId, start, end, orderByComparator};
		}

		List<CPInstance> list = null;

		if (retrieveFromCache) {
			list = (List<CPInstance>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPInstance cpInstance : list) {
					if ((groupId != cpInstance.getGroupId())) {
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

			query.append(_SQL_SELECT_CPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CPInstanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<CPInstance>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPInstance>)QueryUtil.list(
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
	 * Returns the first cp instance in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	@Override
	public CPInstance findByGroupId_First(
			long groupId, OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByGroupId_First(
			groupId, orderByComparator);

		if (cpInstance != null) {
			return cpInstance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchCPInstanceException(msg.toString());
	}

	/**
	 * Returns the first cp instance in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByGroupId_First(
		long groupId, OrderByComparator<CPInstance> orderByComparator) {

		List<CPInstance> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp instance in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	@Override
	public CPInstance findByGroupId_Last(
			long groupId, OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByGroupId_Last(groupId, orderByComparator);

		if (cpInstance != null) {
			return cpInstance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchCPInstanceException(msg.toString());
	}

	/**
	 * Returns the last cp instance in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByGroupId_Last(
		long groupId, OrderByComparator<CPInstance> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<CPInstance> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp instances before and after the current cp instance in the ordered set where groupId = &#63;.
	 *
	 * @param CPInstanceId the primary key of the current cp instance
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp instance
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	@Override
	public CPInstance[] findByGroupId_PrevAndNext(
			long CPInstanceId, long groupId,
			OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = findByPrimaryKey(CPInstanceId);

		Session session = null;

		try {
			session = openSession();

			CPInstance[] array = new CPInstanceImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, cpInstance, groupId, orderByComparator, true);

			array[1] = cpInstance;

			array[2] = getByGroupId_PrevAndNext(
				session, cpInstance, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPInstance getByGroupId_PrevAndNext(
		Session session, CPInstance cpInstance, long groupId,
		OrderByComparator<CPInstance> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CPINSTANCE_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			query.append(CPInstanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(cpInstance)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp instances where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (CPInstance cpInstance :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cpInstance);
		}
	}

	/**
	 * Returns the number of cp instances where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching cp instances
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 =
		"cpInstance.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the cp instances where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching cp instances
	 */
	@Override
	public List<CPInstance> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp instances where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @return the range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp instances where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CPInstance> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp instances where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CPInstance> orderByComparator,
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

		List<CPInstance> list = null;

		if (retrieveFromCache) {
			list = (List<CPInstance>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPInstance cpInstance : list) {
					if ((companyId != cpInstance.getCompanyId())) {
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

			query.append(_SQL_SELECT_CPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CPInstanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<CPInstance>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPInstance>)QueryUtil.list(
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
	 * Returns the first cp instance in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	@Override
	public CPInstance findByCompanyId_First(
			long companyId, OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByCompanyId_First(
			companyId, orderByComparator);

		if (cpInstance != null) {
			return cpInstance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPInstanceException(msg.toString());
	}

	/**
	 * Returns the first cp instance in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByCompanyId_First(
		long companyId, OrderByComparator<CPInstance> orderByComparator) {

		List<CPInstance> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp instance in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	@Override
	public CPInstance findByCompanyId_Last(
			long companyId, OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByCompanyId_Last(
			companyId, orderByComparator);

		if (cpInstance != null) {
			return cpInstance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPInstanceException(msg.toString());
	}

	/**
	 * Returns the last cp instance in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByCompanyId_Last(
		long companyId, OrderByComparator<CPInstance> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<CPInstance> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp instances before and after the current cp instance in the ordered set where companyId = &#63;.
	 *
	 * @param CPInstanceId the primary key of the current cp instance
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp instance
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	@Override
	public CPInstance[] findByCompanyId_PrevAndNext(
			long CPInstanceId, long companyId,
			OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = findByPrimaryKey(CPInstanceId);

		Session session = null;

		try {
			session = openSession();

			CPInstance[] array = new CPInstanceImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, cpInstance, companyId, orderByComparator, true);

			array[1] = cpInstance;

			array[2] = getByCompanyId_PrevAndNext(
				session, cpInstance, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPInstance getByCompanyId_PrevAndNext(
		Session session, CPInstance cpInstance, long companyId,
		OrderByComparator<CPInstance> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CPINSTANCE_WHERE);

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
			query.append(CPInstanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(cpInstance)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp instances where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (CPInstance cpInstance :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cpInstance);
		}
	}

	/**
	 * Returns the number of cp instances where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching cp instances
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] {companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPINSTANCE_WHERE);

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
		"cpInstance.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByCPDefinitionId;
	private FinderPath _finderPathWithoutPaginationFindByCPDefinitionId;
	private FinderPath _finderPathCountByCPDefinitionId;

	/**
	 * Returns all the cp instances where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @return the matching cp instances
	 */
	@Override
	public List<CPInstance> findByCPDefinitionId(long CPDefinitionId) {
		return findByCPDefinitionId(
			CPDefinitionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp instances where CPDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @return the range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByCPDefinitionId(
		long CPDefinitionId, int start, int end) {

		return findByCPDefinitionId(CPDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp instances where CPDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByCPDefinitionId(
		long CPDefinitionId, int start, int end,
		OrderByComparator<CPInstance> orderByComparator) {

		return findByCPDefinitionId(
			CPDefinitionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp instances where CPDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByCPDefinitionId(
		long CPDefinitionId, int start, int end,
		OrderByComparator<CPInstance> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByCPDefinitionId;
			finderArgs = new Object[] {CPDefinitionId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByCPDefinitionId;
			finderArgs = new Object[] {
				CPDefinitionId, start, end, orderByComparator
			};
		}

		List<CPInstance> list = null;

		if (retrieveFromCache) {
			list = (List<CPInstance>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPInstance cpInstance : list) {
					if ((CPDefinitionId != cpInstance.getCPDefinitionId())) {
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

			query.append(_SQL_SELECT_CPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_CPDEFINITIONID_CPDEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CPInstanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPDefinitionId);

				if (!pagination) {
					list = (List<CPInstance>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPInstance>)QueryUtil.list(
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
	 * Returns the first cp instance in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	@Override
	public CPInstance findByCPDefinitionId_First(
			long CPDefinitionId,
			OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByCPDefinitionId_First(
			CPDefinitionId, orderByComparator);

		if (cpInstance != null) {
			return cpInstance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPDefinitionId=");
		msg.append(CPDefinitionId);

		msg.append("}");

		throw new NoSuchCPInstanceException(msg.toString());
	}

	/**
	 * Returns the first cp instance in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByCPDefinitionId_First(
		long CPDefinitionId, OrderByComparator<CPInstance> orderByComparator) {

		List<CPInstance> list = findByCPDefinitionId(
			CPDefinitionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp instance in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	@Override
	public CPInstance findByCPDefinitionId_Last(
			long CPDefinitionId,
			OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByCPDefinitionId_Last(
			CPDefinitionId, orderByComparator);

		if (cpInstance != null) {
			return cpInstance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPDefinitionId=");
		msg.append(CPDefinitionId);

		msg.append("}");

		throw new NoSuchCPInstanceException(msg.toString());
	}

	/**
	 * Returns the last cp instance in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByCPDefinitionId_Last(
		long CPDefinitionId, OrderByComparator<CPInstance> orderByComparator) {

		int count = countByCPDefinitionId(CPDefinitionId);

		if (count == 0) {
			return null;
		}

		List<CPInstance> list = findByCPDefinitionId(
			CPDefinitionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp instances before and after the current cp instance in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPInstanceId the primary key of the current cp instance
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp instance
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	@Override
	public CPInstance[] findByCPDefinitionId_PrevAndNext(
			long CPInstanceId, long CPDefinitionId,
			OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = findByPrimaryKey(CPInstanceId);

		Session session = null;

		try {
			session = openSession();

			CPInstance[] array = new CPInstanceImpl[3];

			array[0] = getByCPDefinitionId_PrevAndNext(
				session, cpInstance, CPDefinitionId, orderByComparator, true);

			array[1] = cpInstance;

			array[2] = getByCPDefinitionId_PrevAndNext(
				session, cpInstance, CPDefinitionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPInstance getByCPDefinitionId_PrevAndNext(
		Session session, CPInstance cpInstance, long CPDefinitionId,
		OrderByComparator<CPInstance> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CPINSTANCE_WHERE);

		query.append(_FINDER_COLUMN_CPDEFINITIONID_CPDEFINITIONID_2);

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
			query.append(CPInstanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(CPDefinitionId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(cpInstance)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp instances where CPDefinitionId = &#63; from the database.
	 *
	 * @param CPDefinitionId the cp definition ID
	 */
	@Override
	public void removeByCPDefinitionId(long CPDefinitionId) {
		for (CPInstance cpInstance :
				findByCPDefinitionId(
					CPDefinitionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(cpInstance);
		}
	}

	/**
	 * Returns the number of cp instances where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @return the number of matching cp instances
	 */
	@Override
	public int countByCPDefinitionId(long CPDefinitionId) {
		FinderPath finderPath = _finderPathCountByCPDefinitionId;

		Object[] finderArgs = new Object[] {CPDefinitionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_CPDEFINITIONID_CPDEFINITIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPDefinitionId);

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

	private static final String _FINDER_COLUMN_CPDEFINITIONID_CPDEFINITIONID_2 =
		"cpInstance.CPDefinitionId = ?";

	private FinderPath _finderPathWithPaginationFindByCPInstanceUuid;
	private FinderPath _finderPathWithoutPaginationFindByCPInstanceUuid;
	private FinderPath _finderPathCountByCPInstanceUuid;

	/**
	 * Returns all the cp instances where CPInstanceUuid = &#63;.
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 * @return the matching cp instances
	 */
	@Override
	public List<CPInstance> findByCPInstanceUuid(String CPInstanceUuid) {
		return findByCPInstanceUuid(
			CPInstanceUuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp instances where CPInstanceUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @return the range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByCPInstanceUuid(
		String CPInstanceUuid, int start, int end) {

		return findByCPInstanceUuid(CPInstanceUuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp instances where CPInstanceUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByCPInstanceUuid(
		String CPInstanceUuid, int start, int end,
		OrderByComparator<CPInstance> orderByComparator) {

		return findByCPInstanceUuid(
			CPInstanceUuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp instances where CPInstanceUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByCPInstanceUuid(
		String CPInstanceUuid, int start, int end,
		OrderByComparator<CPInstance> orderByComparator,
		boolean retrieveFromCache) {

		CPInstanceUuid = Objects.toString(CPInstanceUuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByCPInstanceUuid;
			finderArgs = new Object[] {CPInstanceUuid};
		}
		else {
			finderPath = _finderPathWithPaginationFindByCPInstanceUuid;
			finderArgs = new Object[] {
				CPInstanceUuid, start, end, orderByComparator
			};
		}

		List<CPInstance> list = null;

		if (retrieveFromCache) {
			list = (List<CPInstance>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPInstance cpInstance : list) {
					if (!CPInstanceUuid.equals(
							cpInstance.getCPInstanceUuid())) {

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

			query.append(_SQL_SELECT_CPINSTANCE_WHERE);

			boolean bindCPInstanceUuid = false;

			if (CPInstanceUuid.isEmpty()) {
				query.append(_FINDER_COLUMN_CPINSTANCEUUID_CPINSTANCEUUID_3);
			}
			else {
				bindCPInstanceUuid = true;

				query.append(_FINDER_COLUMN_CPINSTANCEUUID_CPINSTANCEUUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CPInstanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCPInstanceUuid) {
					qPos.add(CPInstanceUuid);
				}

				if (!pagination) {
					list = (List<CPInstance>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPInstance>)QueryUtil.list(
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
	 * Returns the first cp instance in the ordered set where CPInstanceUuid = &#63;.
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	@Override
	public CPInstance findByCPInstanceUuid_First(
			String CPInstanceUuid,
			OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByCPInstanceUuid_First(
			CPInstanceUuid, orderByComparator);

		if (cpInstance != null) {
			return cpInstance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPInstanceUuid=");
		msg.append(CPInstanceUuid);

		msg.append("}");

		throw new NoSuchCPInstanceException(msg.toString());
	}

	/**
	 * Returns the first cp instance in the ordered set where CPInstanceUuid = &#63;.
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByCPInstanceUuid_First(
		String CPInstanceUuid,
		OrderByComparator<CPInstance> orderByComparator) {

		List<CPInstance> list = findByCPInstanceUuid(
			CPInstanceUuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp instance in the ordered set where CPInstanceUuid = &#63;.
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	@Override
	public CPInstance findByCPInstanceUuid_Last(
			String CPInstanceUuid,
			OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByCPInstanceUuid_Last(
			CPInstanceUuid, orderByComparator);

		if (cpInstance != null) {
			return cpInstance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPInstanceUuid=");
		msg.append(CPInstanceUuid);

		msg.append("}");

		throw new NoSuchCPInstanceException(msg.toString());
	}

	/**
	 * Returns the last cp instance in the ordered set where CPInstanceUuid = &#63;.
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByCPInstanceUuid_Last(
		String CPInstanceUuid,
		OrderByComparator<CPInstance> orderByComparator) {

		int count = countByCPInstanceUuid(CPInstanceUuid);

		if (count == 0) {
			return null;
		}

		List<CPInstance> list = findByCPInstanceUuid(
			CPInstanceUuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp instances before and after the current cp instance in the ordered set where CPInstanceUuid = &#63;.
	 *
	 * @param CPInstanceId the primary key of the current cp instance
	 * @param CPInstanceUuid the cp instance uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp instance
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	@Override
	public CPInstance[] findByCPInstanceUuid_PrevAndNext(
			long CPInstanceId, String CPInstanceUuid,
			OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstanceUuid = Objects.toString(CPInstanceUuid, "");

		CPInstance cpInstance = findByPrimaryKey(CPInstanceId);

		Session session = null;

		try {
			session = openSession();

			CPInstance[] array = new CPInstanceImpl[3];

			array[0] = getByCPInstanceUuid_PrevAndNext(
				session, cpInstance, CPInstanceUuid, orderByComparator, true);

			array[1] = cpInstance;

			array[2] = getByCPInstanceUuid_PrevAndNext(
				session, cpInstance, CPInstanceUuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPInstance getByCPInstanceUuid_PrevAndNext(
		Session session, CPInstance cpInstance, String CPInstanceUuid,
		OrderByComparator<CPInstance> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CPINSTANCE_WHERE);

		boolean bindCPInstanceUuid = false;

		if (CPInstanceUuid.isEmpty()) {
			query.append(_FINDER_COLUMN_CPINSTANCEUUID_CPINSTANCEUUID_3);
		}
		else {
			bindCPInstanceUuid = true;

			query.append(_FINDER_COLUMN_CPINSTANCEUUID_CPINSTANCEUUID_2);
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
			query.append(CPInstanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindCPInstanceUuid) {
			qPos.add(CPInstanceUuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(cpInstance)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp instances where CPInstanceUuid = &#63; from the database.
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 */
	@Override
	public void removeByCPInstanceUuid(String CPInstanceUuid) {
		for (CPInstance cpInstance :
				findByCPInstanceUuid(
					CPInstanceUuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(cpInstance);
		}
	}

	/**
	 * Returns the number of cp instances where CPInstanceUuid = &#63;.
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 * @return the number of matching cp instances
	 */
	@Override
	public int countByCPInstanceUuid(String CPInstanceUuid) {
		CPInstanceUuid = Objects.toString(CPInstanceUuid, "");

		FinderPath finderPath = _finderPathCountByCPInstanceUuid;

		Object[] finderArgs = new Object[] {CPInstanceUuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPINSTANCE_WHERE);

			boolean bindCPInstanceUuid = false;

			if (CPInstanceUuid.isEmpty()) {
				query.append(_FINDER_COLUMN_CPINSTANCEUUID_CPINSTANCEUUID_3);
			}
			else {
				bindCPInstanceUuid = true;

				query.append(_FINDER_COLUMN_CPINSTANCEUUID_CPINSTANCEUUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCPInstanceUuid) {
					qPos.add(CPInstanceUuid);
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

	private static final String _FINDER_COLUMN_CPINSTANCEUUID_CPINSTANCEUUID_2 =
		"cpInstance.CPInstanceUuid = ?";

	private static final String _FINDER_COLUMN_CPINSTANCEUUID_CPINSTANCEUUID_3 =
		"(cpInstance.CPInstanceUuid IS NULL OR cpInstance.CPInstanceUuid = '')";

	private FinderPath _finderPathWithPaginationFindByG_ST;
	private FinderPath _finderPathWithoutPaginationFindByG_ST;
	private FinderPath _finderPathCountByG_ST;

	/**
	 * Returns all the cp instances where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching cp instances
	 */
	@Override
	public List<CPInstance> findByG_ST(long groupId, int status) {
		return findByG_ST(
			groupId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp instances where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @return the range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByG_ST(
		long groupId, int status, int start, int end) {

		return findByG_ST(groupId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp instances where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByG_ST(
		long groupId, int status, int start, int end,
		OrderByComparator<CPInstance> orderByComparator) {

		return findByG_ST(groupId, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp instances where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByG_ST(
		long groupId, int status, int start, int end,
		OrderByComparator<CPInstance> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByG_ST;
			finderArgs = new Object[] {groupId, status};
		}
		else {
			finderPath = _finderPathWithPaginationFindByG_ST;
			finderArgs = new Object[] {
				groupId, status, start, end, orderByComparator
			};
		}

		List<CPInstance> list = null;

		if (retrieveFromCache) {
			list = (List<CPInstance>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPInstance cpInstance : list) {
					if ((groupId != cpInstance.getGroupId()) ||
						(status != cpInstance.getStatus())) {

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

			query.append(_SQL_SELECT_CPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_G_ST_GROUPID_2);

			query.append(_FINDER_COLUMN_G_ST_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CPInstanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(status);

				if (!pagination) {
					list = (List<CPInstance>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPInstance>)QueryUtil.list(
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
	 * Returns the first cp instance in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	@Override
	public CPInstance findByG_ST_First(
			long groupId, int status,
			OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByG_ST_First(
			groupId, status, orderByComparator);

		if (cpInstance != null) {
			return cpInstance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchCPInstanceException(msg.toString());
	}

	/**
	 * Returns the first cp instance in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByG_ST_First(
		long groupId, int status,
		OrderByComparator<CPInstance> orderByComparator) {

		List<CPInstance> list = findByG_ST(
			groupId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp instance in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	@Override
	public CPInstance findByG_ST_Last(
			long groupId, int status,
			OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByG_ST_Last(
			groupId, status, orderByComparator);

		if (cpInstance != null) {
			return cpInstance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchCPInstanceException(msg.toString());
	}

	/**
	 * Returns the last cp instance in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByG_ST_Last(
		long groupId, int status,
		OrderByComparator<CPInstance> orderByComparator) {

		int count = countByG_ST(groupId, status);

		if (count == 0) {
			return null;
		}

		List<CPInstance> list = findByG_ST(
			groupId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp instances before and after the current cp instance in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param CPInstanceId the primary key of the current cp instance
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp instance
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	@Override
	public CPInstance[] findByG_ST_PrevAndNext(
			long CPInstanceId, long groupId, int status,
			OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = findByPrimaryKey(CPInstanceId);

		Session session = null;

		try {
			session = openSession();

			CPInstance[] array = new CPInstanceImpl[3];

			array[0] = getByG_ST_PrevAndNext(
				session, cpInstance, groupId, status, orderByComparator, true);

			array[1] = cpInstance;

			array[2] = getByG_ST_PrevAndNext(
				session, cpInstance, groupId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPInstance getByG_ST_PrevAndNext(
		Session session, CPInstance cpInstance, long groupId, int status,
		OrderByComparator<CPInstance> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_CPINSTANCE_WHERE);

		query.append(_FINDER_COLUMN_G_ST_GROUPID_2);

		query.append(_FINDER_COLUMN_G_ST_STATUS_2);

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
			query.append(CPInstanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(cpInstance)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp instances where groupId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 */
	@Override
	public void removeByG_ST(long groupId, int status) {
		for (CPInstance cpInstance :
				findByG_ST(
					groupId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(cpInstance);
		}
	}

	/**
	 * Returns the number of cp instances where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching cp instances
	 */
	@Override
	public int countByG_ST(long groupId, int status) {
		FinderPath finderPath = _finderPathCountByG_ST;

		Object[] finderArgs = new Object[] {groupId, status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_G_ST_GROUPID_2);

			query.append(_FINDER_COLUMN_G_ST_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_G_ST_GROUPID_2 =
		"cpInstance.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_ST_STATUS_2 =
		"cpInstance.status = ?";

	private FinderPath _finderPathFetchByC_C;
	private FinderPath _finderPathCountByC_C;

	/**
	 * Returns the cp instance where CPDefinitionId = &#63; and CPInstanceUuid = &#63; or throws a <code>NoSuchCPInstanceException</code> if it could not be found.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @return the matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	@Override
	public CPInstance findByC_C(long CPDefinitionId, String CPInstanceUuid)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByC_C(CPDefinitionId, CPInstanceUuid);

		if (cpInstance == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("CPDefinitionId=");
			msg.append(CPDefinitionId);

			msg.append(", CPInstanceUuid=");
			msg.append(CPInstanceUuid);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCPInstanceException(msg.toString());
		}

		return cpInstance;
	}

	/**
	 * Returns the cp instance where CPDefinitionId = &#63; and CPInstanceUuid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @return the matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByC_C(long CPDefinitionId, String CPInstanceUuid) {
		return fetchByC_C(CPDefinitionId, CPInstanceUuid, true);
	}

	/**
	 * Returns the cp instance where CPDefinitionId = &#63; and CPInstanceUuid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByC_C(
		long CPDefinitionId, String CPInstanceUuid, boolean retrieveFromCache) {

		CPInstanceUuid = Objects.toString(CPInstanceUuid, "");

		Object[] finderArgs = new Object[] {CPDefinitionId, CPInstanceUuid};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_C, finderArgs, this);
		}

		if (result instanceof CPInstance) {
			CPInstance cpInstance = (CPInstance)result;

			if ((CPDefinitionId != cpInstance.getCPDefinitionId()) ||
				!Objects.equals(
					CPInstanceUuid, cpInstance.getCPInstanceUuid())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_C_C_CPDEFINITIONID_2);

			boolean bindCPInstanceUuid = false;

			if (CPInstanceUuid.isEmpty()) {
				query.append(_FINDER_COLUMN_C_C_CPINSTANCEUUID_3);
			}
			else {
				bindCPInstanceUuid = true;

				query.append(_FINDER_COLUMN_C_C_CPINSTANCEUUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPDefinitionId);

				if (bindCPInstanceUuid) {
					qPos.add(CPInstanceUuid);
				}

				List<CPInstance> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByC_C, finderArgs, list);
				}
				else {
					CPInstance cpInstance = list.get(0);

					result = cpInstance;

					cacheResult(cpInstance);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByC_C, finderArgs);

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
			return (CPInstance)result;
		}
	}

	/**
	 * Removes the cp instance where CPDefinitionId = &#63; and CPInstanceUuid = &#63; from the database.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @return the cp instance that was removed
	 */
	@Override
	public CPInstance removeByC_C(long CPDefinitionId, String CPInstanceUuid)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = findByC_C(CPDefinitionId, CPInstanceUuid);

		return remove(cpInstance);
	}

	/**
	 * Returns the number of cp instances where CPDefinitionId = &#63; and CPInstanceUuid = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @return the number of matching cp instances
	 */
	@Override
	public int countByC_C(long CPDefinitionId, String CPInstanceUuid) {
		CPInstanceUuid = Objects.toString(CPInstanceUuid, "");

		FinderPath finderPath = _finderPathCountByC_C;

		Object[] finderArgs = new Object[] {CPDefinitionId, CPInstanceUuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_C_C_CPDEFINITIONID_2);

			boolean bindCPInstanceUuid = false;

			if (CPInstanceUuid.isEmpty()) {
				query.append(_FINDER_COLUMN_C_C_CPINSTANCEUUID_3);
			}
			else {
				bindCPInstanceUuid = true;

				query.append(_FINDER_COLUMN_C_C_CPINSTANCEUUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPDefinitionId);

				if (bindCPInstanceUuid) {
					qPos.add(CPInstanceUuid);
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

	private static final String _FINDER_COLUMN_C_C_CPDEFINITIONID_2 =
		"cpInstance.CPDefinitionId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_CPINSTANCEUUID_2 =
		"cpInstance.CPInstanceUuid = ?";

	private static final String _FINDER_COLUMN_C_C_CPINSTANCEUUID_3 =
		"(cpInstance.CPInstanceUuid IS NULL OR cpInstance.CPInstanceUuid = '')";

	private FinderPath _finderPathFetchByC_S;
	private FinderPath _finderPathCountByC_S;

	/**
	 * Returns the cp instance where CPDefinitionId = &#63; and sku = &#63; or throws a <code>NoSuchCPInstanceException</code> if it could not be found.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param sku the sku
	 * @return the matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	@Override
	public CPInstance findByC_S(long CPDefinitionId, String sku)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByC_S(CPDefinitionId, sku);

		if (cpInstance == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("CPDefinitionId=");
			msg.append(CPDefinitionId);

			msg.append(", sku=");
			msg.append(sku);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCPInstanceException(msg.toString());
		}

		return cpInstance;
	}

	/**
	 * Returns the cp instance where CPDefinitionId = &#63; and sku = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param sku the sku
	 * @return the matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByC_S(long CPDefinitionId, String sku) {
		return fetchByC_S(CPDefinitionId, sku, true);
	}

	/**
	 * Returns the cp instance where CPDefinitionId = &#63; and sku = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param sku the sku
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByC_S(
		long CPDefinitionId, String sku, boolean retrieveFromCache) {

		sku = Objects.toString(sku, "");

		Object[] finderArgs = new Object[] {CPDefinitionId, sku};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_S, finderArgs, this);
		}

		if (result instanceof CPInstance) {
			CPInstance cpInstance = (CPInstance)result;

			if ((CPDefinitionId != cpInstance.getCPDefinitionId()) ||
				!Objects.equals(sku, cpInstance.getSku())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_C_S_CPDEFINITIONID_2);

			boolean bindSku = false;

			if (sku.isEmpty()) {
				query.append(_FINDER_COLUMN_C_S_SKU_3);
			}
			else {
				bindSku = true;

				query.append(_FINDER_COLUMN_C_S_SKU_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPDefinitionId);

				if (bindSku) {
					qPos.add(sku);
				}

				List<CPInstance> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByC_S, finderArgs, list);
				}
				else {
					CPInstance cpInstance = list.get(0);

					result = cpInstance;

					cacheResult(cpInstance);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByC_S, finderArgs);

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
			return (CPInstance)result;
		}
	}

	/**
	 * Removes the cp instance where CPDefinitionId = &#63; and sku = &#63; from the database.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param sku the sku
	 * @return the cp instance that was removed
	 */
	@Override
	public CPInstance removeByC_S(long CPDefinitionId, String sku)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = findByC_S(CPDefinitionId, sku);

		return remove(cpInstance);
	}

	/**
	 * Returns the number of cp instances where CPDefinitionId = &#63; and sku = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param sku the sku
	 * @return the number of matching cp instances
	 */
	@Override
	public int countByC_S(long CPDefinitionId, String sku) {
		sku = Objects.toString(sku, "");

		FinderPath finderPath = _finderPathCountByC_S;

		Object[] finderArgs = new Object[] {CPDefinitionId, sku};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_C_S_CPDEFINITIONID_2);

			boolean bindSku = false;

			if (sku.isEmpty()) {
				query.append(_FINDER_COLUMN_C_S_SKU_3);
			}
			else {
				bindSku = true;

				query.append(_FINDER_COLUMN_C_S_SKU_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPDefinitionId);

				if (bindSku) {
					qPos.add(sku);
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

	private static final String _FINDER_COLUMN_C_S_CPDEFINITIONID_2 =
		"cpInstance.CPDefinitionId = ? AND ";

	private static final String _FINDER_COLUMN_C_S_SKU_2 = "cpInstance.sku = ?";

	private static final String _FINDER_COLUMN_C_S_SKU_3 =
		"(cpInstance.sku IS NULL OR cpInstance.sku = '')";

	private FinderPath _finderPathWithPaginationFindByC_ST;
	private FinderPath _finderPathWithoutPaginationFindByC_ST;
	private FinderPath _finderPathCountByC_ST;

	/**
	 * Returns all the cp instances where CPDefinitionId = &#63; and status = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param status the status
	 * @return the matching cp instances
	 */
	@Override
	public List<CPInstance> findByC_ST(long CPDefinitionId, int status) {
		return findByC_ST(
			CPDefinitionId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp instances where CPDefinitionId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param status the status
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @return the range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByC_ST(
		long CPDefinitionId, int status, int start, int end) {

		return findByC_ST(CPDefinitionId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp instances where CPDefinitionId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param status the status
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByC_ST(
		long CPDefinitionId, int status, int start, int end,
		OrderByComparator<CPInstance> orderByComparator) {

		return findByC_ST(
			CPDefinitionId, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp instances where CPDefinitionId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param status the status
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByC_ST(
		long CPDefinitionId, int status, int start, int end,
		OrderByComparator<CPInstance> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByC_ST;
			finderArgs = new Object[] {CPDefinitionId, status};
		}
		else {
			finderPath = _finderPathWithPaginationFindByC_ST;
			finderArgs = new Object[] {
				CPDefinitionId, status, start, end, orderByComparator
			};
		}

		List<CPInstance> list = null;

		if (retrieveFromCache) {
			list = (List<CPInstance>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPInstance cpInstance : list) {
					if ((CPDefinitionId != cpInstance.getCPDefinitionId()) ||
						(status != cpInstance.getStatus())) {

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

			query.append(_SQL_SELECT_CPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_C_ST_CPDEFINITIONID_2);

			query.append(_FINDER_COLUMN_C_ST_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CPInstanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPDefinitionId);

				qPos.add(status);

				if (!pagination) {
					list = (List<CPInstance>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPInstance>)QueryUtil.list(
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
	 * Returns the first cp instance in the ordered set where CPDefinitionId = &#63; and status = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	@Override
	public CPInstance findByC_ST_First(
			long CPDefinitionId, int status,
			OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByC_ST_First(
			CPDefinitionId, status, orderByComparator);

		if (cpInstance != null) {
			return cpInstance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPDefinitionId=");
		msg.append(CPDefinitionId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchCPInstanceException(msg.toString());
	}

	/**
	 * Returns the first cp instance in the ordered set where CPDefinitionId = &#63; and status = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByC_ST_First(
		long CPDefinitionId, int status,
		OrderByComparator<CPInstance> orderByComparator) {

		List<CPInstance> list = findByC_ST(
			CPDefinitionId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp instance in the ordered set where CPDefinitionId = &#63; and status = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	@Override
	public CPInstance findByC_ST_Last(
			long CPDefinitionId, int status,
			OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByC_ST_Last(
			CPDefinitionId, status, orderByComparator);

		if (cpInstance != null) {
			return cpInstance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPDefinitionId=");
		msg.append(CPDefinitionId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchCPInstanceException(msg.toString());
	}

	/**
	 * Returns the last cp instance in the ordered set where CPDefinitionId = &#63; and status = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByC_ST_Last(
		long CPDefinitionId, int status,
		OrderByComparator<CPInstance> orderByComparator) {

		int count = countByC_ST(CPDefinitionId, status);

		if (count == 0) {
			return null;
		}

		List<CPInstance> list = findByC_ST(
			CPDefinitionId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp instances before and after the current cp instance in the ordered set where CPDefinitionId = &#63; and status = &#63;.
	 *
	 * @param CPInstanceId the primary key of the current cp instance
	 * @param CPDefinitionId the cp definition ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp instance
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	@Override
	public CPInstance[] findByC_ST_PrevAndNext(
			long CPInstanceId, long CPDefinitionId, int status,
			OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = findByPrimaryKey(CPInstanceId);

		Session session = null;

		try {
			session = openSession();

			CPInstance[] array = new CPInstanceImpl[3];

			array[0] = getByC_ST_PrevAndNext(
				session, cpInstance, CPDefinitionId, status, orderByComparator,
				true);

			array[1] = cpInstance;

			array[2] = getByC_ST_PrevAndNext(
				session, cpInstance, CPDefinitionId, status, orderByComparator,
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

	protected CPInstance getByC_ST_PrevAndNext(
		Session session, CPInstance cpInstance, long CPDefinitionId, int status,
		OrderByComparator<CPInstance> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_CPINSTANCE_WHERE);

		query.append(_FINDER_COLUMN_C_ST_CPDEFINITIONID_2);

		query.append(_FINDER_COLUMN_C_ST_STATUS_2);

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
			query.append(CPInstanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(CPDefinitionId);

		qPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(cpInstance)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp instances where CPDefinitionId = &#63; and status = &#63; from the database.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param status the status
	 */
	@Override
	public void removeByC_ST(long CPDefinitionId, int status) {
		for (CPInstance cpInstance :
				findByC_ST(
					CPDefinitionId, status, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(cpInstance);
		}
	}

	/**
	 * Returns the number of cp instances where CPDefinitionId = &#63; and status = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param status the status
	 * @return the number of matching cp instances
	 */
	@Override
	public int countByC_ST(long CPDefinitionId, int status) {
		FinderPath finderPath = _finderPathCountByC_ST;

		Object[] finderArgs = new Object[] {CPDefinitionId, status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_C_ST_CPDEFINITIONID_2);

			query.append(_FINDER_COLUMN_C_ST_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPDefinitionId);

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_C_ST_CPDEFINITIONID_2 =
		"cpInstance.CPDefinitionId = ? AND ";

	private static final String _FINDER_COLUMN_C_ST_STATUS_2 =
		"cpInstance.status = ?";

	private FinderPath _finderPathWithPaginationFindByLtD_S;
	private FinderPath _finderPathWithPaginationCountByLtD_S;

	/**
	 * Returns all the cp instances where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @return the matching cp instances
	 */
	@Override
	public List<CPInstance> findByLtD_S(Date displayDate, int status) {
		return findByLtD_S(
			displayDate, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp instances where displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @return the range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByLtD_S(
		Date displayDate, int status, int start, int end) {

		return findByLtD_S(displayDate, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp instances where displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByLtD_S(
		Date displayDate, int status, int start, int end,
		OrderByComparator<CPInstance> orderByComparator) {

		return findByLtD_S(
			displayDate, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp instances where displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByLtD_S(
		Date displayDate, int status, int start, int end,
		OrderByComparator<CPInstance> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByLtD_S;
		finderArgs = new Object[] {
			_getTime(displayDate), status, start, end, orderByComparator
		};

		List<CPInstance> list = null;

		if (retrieveFromCache) {
			list = (List<CPInstance>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPInstance cpInstance : list) {
					if ((displayDate.getTime() <=
							cpInstance.getDisplayDate().getTime()) ||
						(status != cpInstance.getStatus())) {

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

			query.append(_SQL_SELECT_CPINSTANCE_WHERE);

			boolean bindDisplayDate = false;

			if (displayDate == null) {
				query.append(_FINDER_COLUMN_LTD_S_DISPLAYDATE_1);
			}
			else {
				bindDisplayDate = true;

				query.append(_FINDER_COLUMN_LTD_S_DISPLAYDATE_2);
			}

			query.append(_FINDER_COLUMN_LTD_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CPInstanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDisplayDate) {
					qPos.add(new Timestamp(displayDate.getTime()));
				}

				qPos.add(status);

				if (!pagination) {
					list = (List<CPInstance>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPInstance>)QueryUtil.list(
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
	 * Returns the first cp instance in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	@Override
	public CPInstance findByLtD_S_First(
			Date displayDate, int status,
			OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByLtD_S_First(
			displayDate, status, orderByComparator);

		if (cpInstance != null) {
			return cpInstance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("displayDate=");
		msg.append(displayDate);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchCPInstanceException(msg.toString());
	}

	/**
	 * Returns the first cp instance in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByLtD_S_First(
		Date displayDate, int status,
		OrderByComparator<CPInstance> orderByComparator) {

		List<CPInstance> list = findByLtD_S(
			displayDate, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp instance in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	@Override
	public CPInstance findByLtD_S_Last(
			Date displayDate, int status,
			OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByLtD_S_Last(
			displayDate, status, orderByComparator);

		if (cpInstance != null) {
			return cpInstance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("displayDate=");
		msg.append(displayDate);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchCPInstanceException(msg.toString());
	}

	/**
	 * Returns the last cp instance in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByLtD_S_Last(
		Date displayDate, int status,
		OrderByComparator<CPInstance> orderByComparator) {

		int count = countByLtD_S(displayDate, status);

		if (count == 0) {
			return null;
		}

		List<CPInstance> list = findByLtD_S(
			displayDate, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp instances before and after the current cp instance in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param CPInstanceId the primary key of the current cp instance
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp instance
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	@Override
	public CPInstance[] findByLtD_S_PrevAndNext(
			long CPInstanceId, Date displayDate, int status,
			OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = findByPrimaryKey(CPInstanceId);

		Session session = null;

		try {
			session = openSession();

			CPInstance[] array = new CPInstanceImpl[3];

			array[0] = getByLtD_S_PrevAndNext(
				session, cpInstance, displayDate, status, orderByComparator,
				true);

			array[1] = cpInstance;

			array[2] = getByLtD_S_PrevAndNext(
				session, cpInstance, displayDate, status, orderByComparator,
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

	protected CPInstance getByLtD_S_PrevAndNext(
		Session session, CPInstance cpInstance, Date displayDate, int status,
		OrderByComparator<CPInstance> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_CPINSTANCE_WHERE);

		boolean bindDisplayDate = false;

		if (displayDate == null) {
			query.append(_FINDER_COLUMN_LTD_S_DISPLAYDATE_1);
		}
		else {
			bindDisplayDate = true;

			query.append(_FINDER_COLUMN_LTD_S_DISPLAYDATE_2);
		}

		query.append(_FINDER_COLUMN_LTD_S_STATUS_2);

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
			query.append(CPInstanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindDisplayDate) {
			qPos.add(new Timestamp(displayDate.getTime()));
		}

		qPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(cpInstance)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp instances where displayDate &lt; &#63; and status = &#63; from the database.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 */
	@Override
	public void removeByLtD_S(Date displayDate, int status) {
		for (CPInstance cpInstance :
				findByLtD_S(
					displayDate, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(cpInstance);
		}
	}

	/**
	 * Returns the number of cp instances where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @return the number of matching cp instances
	 */
	@Override
	public int countByLtD_S(Date displayDate, int status) {
		FinderPath finderPath = _finderPathWithPaginationCountByLtD_S;

		Object[] finderArgs = new Object[] {_getTime(displayDate), status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPINSTANCE_WHERE);

			boolean bindDisplayDate = false;

			if (displayDate == null) {
				query.append(_FINDER_COLUMN_LTD_S_DISPLAYDATE_1);
			}
			else {
				bindDisplayDate = true;

				query.append(_FINDER_COLUMN_LTD_S_DISPLAYDATE_2);
			}

			query.append(_FINDER_COLUMN_LTD_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDisplayDate) {
					qPos.add(new Timestamp(displayDate.getTime()));
				}

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_LTD_S_DISPLAYDATE_1 =
		"cpInstance.displayDate IS NULL AND ";

	private static final String _FINDER_COLUMN_LTD_S_DISPLAYDATE_2 =
		"cpInstance.displayDate < ? AND ";

	private static final String _FINDER_COLUMN_LTD_S_STATUS_2 =
		"cpInstance.status = ?";

	private FinderPath _finderPathWithPaginationFindByC_LtD_S;
	private FinderPath _finderPathWithPaginationCountByC_LtD_S;

	/**
	 * Returns all the cp instances where CPDefinitionId = &#63; and displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param displayDate the display date
	 * @param status the status
	 * @return the matching cp instances
	 */
	@Override
	public List<CPInstance> findByC_LtD_S(
		long CPDefinitionId, Date displayDate, int status) {

		return findByC_LtD_S(
			CPDefinitionId, displayDate, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp instances where CPDefinitionId = &#63; and displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param displayDate the display date
	 * @param status the status
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @return the range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByC_LtD_S(
		long CPDefinitionId, Date displayDate, int status, int start, int end) {

		return findByC_LtD_S(
			CPDefinitionId, displayDate, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp instances where CPDefinitionId = &#63; and displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param displayDate the display date
	 * @param status the status
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByC_LtD_S(
		long CPDefinitionId, Date displayDate, int status, int start, int end,
		OrderByComparator<CPInstance> orderByComparator) {

		return findByC_LtD_S(
			CPDefinitionId, displayDate, status, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the cp instances where CPDefinitionId = &#63; and displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param displayDate the display date
	 * @param status the status
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp instances
	 */
	@Override
	public List<CPInstance> findByC_LtD_S(
		long CPDefinitionId, Date displayDate, int status, int start, int end,
		OrderByComparator<CPInstance> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByC_LtD_S;
		finderArgs = new Object[] {
			CPDefinitionId, _getTime(displayDate), status, start, end,
			orderByComparator
		};

		List<CPInstance> list = null;

		if (retrieveFromCache) {
			list = (List<CPInstance>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPInstance cpInstance : list) {
					if ((CPDefinitionId != cpInstance.getCPDefinitionId()) ||
						(displayDate.getTime() <=
							cpInstance.getDisplayDate().getTime()) ||
						(status != cpInstance.getStatus())) {

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

			query.append(_SQL_SELECT_CPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_C_LTD_S_CPDEFINITIONID_2);

			boolean bindDisplayDate = false;

			if (displayDate == null) {
				query.append(_FINDER_COLUMN_C_LTD_S_DISPLAYDATE_1);
			}
			else {
				bindDisplayDate = true;

				query.append(_FINDER_COLUMN_C_LTD_S_DISPLAYDATE_2);
			}

			query.append(_FINDER_COLUMN_C_LTD_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CPInstanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPDefinitionId);

				if (bindDisplayDate) {
					qPos.add(new Timestamp(displayDate.getTime()));
				}

				qPos.add(status);

				if (!pagination) {
					list = (List<CPInstance>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPInstance>)QueryUtil.list(
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
	 * Returns the first cp instance in the ordered set where CPDefinitionId = &#63; and displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	@Override
	public CPInstance findByC_LtD_S_First(
			long CPDefinitionId, Date displayDate, int status,
			OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByC_LtD_S_First(
			CPDefinitionId, displayDate, status, orderByComparator);

		if (cpInstance != null) {
			return cpInstance;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPDefinitionId=");
		msg.append(CPDefinitionId);

		msg.append(", displayDate=");
		msg.append(displayDate);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchCPInstanceException(msg.toString());
	}

	/**
	 * Returns the first cp instance in the ordered set where CPDefinitionId = &#63; and displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByC_LtD_S_First(
		long CPDefinitionId, Date displayDate, int status,
		OrderByComparator<CPInstance> orderByComparator) {

		List<CPInstance> list = findByC_LtD_S(
			CPDefinitionId, displayDate, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp instance in the ordered set where CPDefinitionId = &#63; and displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	@Override
	public CPInstance findByC_LtD_S_Last(
			long CPDefinitionId, Date displayDate, int status,
			OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByC_LtD_S_Last(
			CPDefinitionId, displayDate, status, orderByComparator);

		if (cpInstance != null) {
			return cpInstance;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPDefinitionId=");
		msg.append(CPDefinitionId);

		msg.append(", displayDate=");
		msg.append(displayDate);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchCPInstanceException(msg.toString());
	}

	/**
	 * Returns the last cp instance in the ordered set where CPDefinitionId = &#63; and displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByC_LtD_S_Last(
		long CPDefinitionId, Date displayDate, int status,
		OrderByComparator<CPInstance> orderByComparator) {

		int count = countByC_LtD_S(CPDefinitionId, displayDate, status);

		if (count == 0) {
			return null;
		}

		List<CPInstance> list = findByC_LtD_S(
			CPDefinitionId, displayDate, status, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp instances before and after the current cp instance in the ordered set where CPDefinitionId = &#63; and displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param CPInstanceId the primary key of the current cp instance
	 * @param CPDefinitionId the cp definition ID
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp instance
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	@Override
	public CPInstance[] findByC_LtD_S_PrevAndNext(
			long CPInstanceId, long CPDefinitionId, Date displayDate,
			int status, OrderByComparator<CPInstance> orderByComparator)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = findByPrimaryKey(CPInstanceId);

		Session session = null;

		try {
			session = openSession();

			CPInstance[] array = new CPInstanceImpl[3];

			array[0] = getByC_LtD_S_PrevAndNext(
				session, cpInstance, CPDefinitionId, displayDate, status,
				orderByComparator, true);

			array[1] = cpInstance;

			array[2] = getByC_LtD_S_PrevAndNext(
				session, cpInstance, CPDefinitionId, displayDate, status,
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

	protected CPInstance getByC_LtD_S_PrevAndNext(
		Session session, CPInstance cpInstance, long CPDefinitionId,
		Date displayDate, int status,
		OrderByComparator<CPInstance> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_CPINSTANCE_WHERE);

		query.append(_FINDER_COLUMN_C_LTD_S_CPDEFINITIONID_2);

		boolean bindDisplayDate = false;

		if (displayDate == null) {
			query.append(_FINDER_COLUMN_C_LTD_S_DISPLAYDATE_1);
		}
		else {
			bindDisplayDate = true;

			query.append(_FINDER_COLUMN_C_LTD_S_DISPLAYDATE_2);
		}

		query.append(_FINDER_COLUMN_C_LTD_S_STATUS_2);

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
			query.append(CPInstanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(CPDefinitionId);

		if (bindDisplayDate) {
			qPos.add(new Timestamp(displayDate.getTime()));
		}

		qPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(cpInstance)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp instances where CPDefinitionId = &#63; and displayDate &lt; &#63; and status = &#63; from the database.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param displayDate the display date
	 * @param status the status
	 */
	@Override
	public void removeByC_LtD_S(
		long CPDefinitionId, Date displayDate, int status) {

		for (CPInstance cpInstance :
				findByC_LtD_S(
					CPDefinitionId, displayDate, status, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(cpInstance);
		}
	}

	/**
	 * Returns the number of cp instances where CPDefinitionId = &#63; and displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param displayDate the display date
	 * @param status the status
	 * @return the number of matching cp instances
	 */
	@Override
	public int countByC_LtD_S(
		long CPDefinitionId, Date displayDate, int status) {

		FinderPath finderPath = _finderPathWithPaginationCountByC_LtD_S;

		Object[] finderArgs = new Object[] {
			CPDefinitionId, _getTime(displayDate), status
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_C_LTD_S_CPDEFINITIONID_2);

			boolean bindDisplayDate = false;

			if (displayDate == null) {
				query.append(_FINDER_COLUMN_C_LTD_S_DISPLAYDATE_1);
			}
			else {
				bindDisplayDate = true;

				query.append(_FINDER_COLUMN_C_LTD_S_DISPLAYDATE_2);
			}

			query.append(_FINDER_COLUMN_C_LTD_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPDefinitionId);

				if (bindDisplayDate) {
					qPos.add(new Timestamp(displayDate.getTime()));
				}

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_C_LTD_S_CPDEFINITIONID_2 =
		"cpInstance.CPDefinitionId = ? AND ";

	private static final String _FINDER_COLUMN_C_LTD_S_DISPLAYDATE_1 =
		"cpInstance.displayDate IS NULL AND ";

	private static final String _FINDER_COLUMN_C_LTD_S_DISPLAYDATE_2 =
		"cpInstance.displayDate < ? AND ";

	private static final String _FINDER_COLUMN_C_LTD_S_STATUS_2 =
		"cpInstance.status = ?";

	private FinderPath _finderPathFetchByC_ERC;
	private FinderPath _finderPathCountByC_ERC;

	/**
	 * Returns the cp instance where companyId = &#63; and externalReferenceCode = &#63; or throws a <code>NoSuchCPInstanceException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching cp instance
	 * @throws NoSuchCPInstanceException if a matching cp instance could not be found
	 */
	@Override
	public CPInstance findByC_ERC(long companyId, String externalReferenceCode)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByC_ERC(companyId, externalReferenceCode);

		if (cpInstance == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", externalReferenceCode=");
			msg.append(externalReferenceCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCPInstanceException(msg.toString());
		}

		return cpInstance;
	}

	/**
	 * Returns the cp instance where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByC_ERC(
		long companyId, String externalReferenceCode) {

		return fetchByC_ERC(companyId, externalReferenceCode, true);
	}

	/**
	 * Returns the cp instance where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching cp instance, or <code>null</code> if a matching cp instance could not be found
	 */
	@Override
	public CPInstance fetchByC_ERC(
		long companyId, String externalReferenceCode,
		boolean retrieveFromCache) {

		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		Object[] finderArgs = new Object[] {companyId, externalReferenceCode};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_ERC, finderArgs, this);
		}

		if (result instanceof CPInstance) {
			CPInstance cpInstance = (CPInstance)result;

			if ((companyId != cpInstance.getCompanyId()) ||
				!Objects.equals(
					externalReferenceCode,
					cpInstance.getExternalReferenceCode())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_C_ERC_COMPANYID_2);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode.isEmpty()) {
				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3);
			}
			else {
				bindExternalReferenceCode = true;

				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindExternalReferenceCode) {
					qPos.add(externalReferenceCode);
				}

				List<CPInstance> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByC_ERC, finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"CPInstancePersistenceImpl.fetchByC_ERC(long, String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CPInstance cpInstance = list.get(0);

					result = cpInstance;

					cacheResult(cpInstance);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByC_ERC, finderArgs);

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
			return (CPInstance)result;
		}
	}

	/**
	 * Removes the cp instance where companyId = &#63; and externalReferenceCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the cp instance that was removed
	 */
	@Override
	public CPInstance removeByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = findByC_ERC(companyId, externalReferenceCode);

		return remove(cpInstance);
	}

	/**
	 * Returns the number of cp instances where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the number of matching cp instances
	 */
	@Override
	public int countByC_ERC(long companyId, String externalReferenceCode) {
		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		FinderPath finderPath = _finderPathCountByC_ERC;

		Object[] finderArgs = new Object[] {companyId, externalReferenceCode};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_C_ERC_COMPANYID_2);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode.isEmpty()) {
				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3);
			}
			else {
				bindExternalReferenceCode = true;

				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindExternalReferenceCode) {
					qPos.add(externalReferenceCode);
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

	private static final String _FINDER_COLUMN_C_ERC_COMPANYID_2 =
		"cpInstance.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2 =
		"cpInstance.externalReferenceCode = ?";

	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3 =
		"(cpInstance.externalReferenceCode IS NULL OR cpInstance.externalReferenceCode = '')";

	public CPInstancePersistenceImpl() {
		setModelClass(CPInstance.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

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
	 * Caches the cp instance in the entity cache if it is enabled.
	 *
	 * @param cpInstance the cp instance
	 */
	@Override
	public void cacheResult(CPInstance cpInstance) {
		entityCache.putResult(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED, CPInstanceImpl.class,
			cpInstance.getPrimaryKey(), cpInstance);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {cpInstance.getUuid(), cpInstance.getGroupId()},
			cpInstance);

		finderCache.putResult(
			_finderPathFetchByC_C,
			new Object[] {
				cpInstance.getCPDefinitionId(), cpInstance.getCPInstanceUuid()
			},
			cpInstance);

		finderCache.putResult(
			_finderPathFetchByC_S,
			new Object[] {cpInstance.getCPDefinitionId(), cpInstance.getSku()},
			cpInstance);

		finderCache.putResult(
			_finderPathFetchByC_ERC,
			new Object[] {
				cpInstance.getCompanyId(), cpInstance.getExternalReferenceCode()
			},
			cpInstance);

		cpInstance.resetOriginalValues();
	}

	/**
	 * Caches the cp instances in the entity cache if it is enabled.
	 *
	 * @param cpInstances the cp instances
	 */
	@Override
	public void cacheResult(List<CPInstance> cpInstances) {
		for (CPInstance cpInstance : cpInstances) {
			if (entityCache.getResult(
					CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
					CPInstanceImpl.class, cpInstance.getPrimaryKey()) == null) {

				cacheResult(cpInstance);
			}
			else {
				cpInstance.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cp instances.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CPInstanceImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cp instance.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CPInstance cpInstance) {
		entityCache.removeResult(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED, CPInstanceImpl.class,
			cpInstance.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CPInstanceModelImpl)cpInstance, true);
	}

	@Override
	public void clearCache(List<CPInstance> cpInstances) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CPInstance cpInstance : cpInstances) {
			entityCache.removeResult(
				CPInstanceModelImpl.ENTITY_CACHE_ENABLED, CPInstanceImpl.class,
				cpInstance.getPrimaryKey());

			clearUniqueFindersCache((CPInstanceModelImpl)cpInstance, true);
		}
	}

	protected void cacheUniqueFindersCache(
		CPInstanceModelImpl cpInstanceModelImpl) {

		Object[] args = new Object[] {
			cpInstanceModelImpl.getUuid(), cpInstanceModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, cpInstanceModelImpl, false);

		args = new Object[] {
			cpInstanceModelImpl.getCPDefinitionId(),
			cpInstanceModelImpl.getCPInstanceUuid()
		};

		finderCache.putResult(
			_finderPathCountByC_C, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_C, args, cpInstanceModelImpl, false);

		args = new Object[] {
			cpInstanceModelImpl.getCPDefinitionId(),
			cpInstanceModelImpl.getSku()
		};

		finderCache.putResult(
			_finderPathCountByC_S, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_S, args, cpInstanceModelImpl, false);

		args = new Object[] {
			cpInstanceModelImpl.getCompanyId(),
			cpInstanceModelImpl.getExternalReferenceCode()
		};

		finderCache.putResult(
			_finderPathCountByC_ERC, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_ERC, args, cpInstanceModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CPInstanceModelImpl cpInstanceModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				cpInstanceModelImpl.getUuid(), cpInstanceModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((cpInstanceModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				cpInstanceModelImpl.getOriginalUuid(),
				cpInstanceModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
				cpInstanceModelImpl.getCPDefinitionId(),
				cpInstanceModelImpl.getCPInstanceUuid()
			};

			finderCache.removeResult(_finderPathCountByC_C, args);
			finderCache.removeResult(_finderPathFetchByC_C, args);
		}

		if ((cpInstanceModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_C.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				cpInstanceModelImpl.getOriginalCPDefinitionId(),
				cpInstanceModelImpl.getOriginalCPInstanceUuid()
			};

			finderCache.removeResult(_finderPathCountByC_C, args);
			finderCache.removeResult(_finderPathFetchByC_C, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
				cpInstanceModelImpl.getCPDefinitionId(),
				cpInstanceModelImpl.getSku()
			};

			finderCache.removeResult(_finderPathCountByC_S, args);
			finderCache.removeResult(_finderPathFetchByC_S, args);
		}

		if ((cpInstanceModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_S.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				cpInstanceModelImpl.getOriginalCPDefinitionId(),
				cpInstanceModelImpl.getOriginalSku()
			};

			finderCache.removeResult(_finderPathCountByC_S, args);
			finderCache.removeResult(_finderPathFetchByC_S, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
				cpInstanceModelImpl.getCompanyId(),
				cpInstanceModelImpl.getExternalReferenceCode()
			};

			finderCache.removeResult(_finderPathCountByC_ERC, args);
			finderCache.removeResult(_finderPathFetchByC_ERC, args);
		}

		if ((cpInstanceModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_ERC.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				cpInstanceModelImpl.getOriginalCompanyId(),
				cpInstanceModelImpl.getOriginalExternalReferenceCode()
			};

			finderCache.removeResult(_finderPathCountByC_ERC, args);
			finderCache.removeResult(_finderPathFetchByC_ERC, args);
		}
	}

	/**
	 * Creates a new cp instance with the primary key. Does not add the cp instance to the database.
	 *
	 * @param CPInstanceId the primary key for the new cp instance
	 * @return the new cp instance
	 */
	@Override
	public CPInstance create(long CPInstanceId) {
		CPInstance cpInstance = new CPInstanceImpl();

		cpInstance.setNew(true);
		cpInstance.setPrimaryKey(CPInstanceId);

		String uuid = PortalUUIDUtil.generate();

		cpInstance.setUuid(uuid);

		cpInstance.setCompanyId(CompanyThreadLocal.getCompanyId());

		return cpInstance;
	}

	/**
	 * Removes the cp instance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPInstanceId the primary key of the cp instance
	 * @return the cp instance that was removed
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	@Override
	public CPInstance remove(long CPInstanceId)
		throws NoSuchCPInstanceException {

		return remove((Serializable)CPInstanceId);
	}

	/**
	 * Removes the cp instance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cp instance
	 * @return the cp instance that was removed
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	@Override
	public CPInstance remove(Serializable primaryKey)
		throws NoSuchCPInstanceException {

		Session session = null;

		try {
			session = openSession();

			CPInstance cpInstance = (CPInstance)session.get(
				CPInstanceImpl.class, primaryKey);

			if (cpInstance == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCPInstanceException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(cpInstance);
		}
		catch (NoSuchCPInstanceException nsee) {
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
	protected CPInstance removeImpl(CPInstance cpInstance) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cpInstance)) {
				cpInstance = (CPInstance)session.get(
					CPInstanceImpl.class, cpInstance.getPrimaryKeyObj());
			}

			if (cpInstance != null) {
				session.delete(cpInstance);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cpInstance != null) {
			clearCache(cpInstance);
		}

		return cpInstance;
	}

	@Override
	public CPInstance updateImpl(CPInstance cpInstance) {
		boolean isNew = cpInstance.isNew();

		if (!(cpInstance instanceof CPInstanceModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(cpInstance.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(cpInstance);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in cpInstance proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CPInstance implementation " +
					cpInstance.getClass());
		}

		CPInstanceModelImpl cpInstanceModelImpl =
			(CPInstanceModelImpl)cpInstance;

		if (Validator.isNull(cpInstance.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			cpInstance.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (cpInstance.getCreateDate() == null)) {
			if (serviceContext == null) {
				cpInstance.setCreateDate(now);
			}
			else {
				cpInstance.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!cpInstanceModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				cpInstance.setModifiedDate(now);
			}
			else {
				cpInstance.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (cpInstance.isNew()) {
				session.save(cpInstance);

				cpInstance.setNew(false);
			}
			else {
				cpInstance = (CPInstance)session.merge(cpInstance);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CPInstanceModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {cpInstanceModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				cpInstanceModelImpl.getUuid(),
				cpInstanceModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {cpInstanceModelImpl.getGroupId()};

			finderCache.removeResult(_finderPathCountByGroupId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByGroupId, args);

			args = new Object[] {cpInstanceModelImpl.getCompanyId()};

			finderCache.removeResult(_finderPathCountByCompanyId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCompanyId, args);

			args = new Object[] {cpInstanceModelImpl.getCPDefinitionId()};

			finderCache.removeResult(_finderPathCountByCPDefinitionId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCPDefinitionId, args);

			args = new Object[] {cpInstanceModelImpl.getCPInstanceUuid()};

			finderCache.removeResult(_finderPathCountByCPInstanceUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCPInstanceUuid, args);

			args = new Object[] {
				cpInstanceModelImpl.getGroupId(),
				cpInstanceModelImpl.getStatus()
			};

			finderCache.removeResult(_finderPathCountByG_ST, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByG_ST, args);

			args = new Object[] {
				cpInstanceModelImpl.getCPDefinitionId(),
				cpInstanceModelImpl.getStatus()
			};

			finderCache.removeResult(_finderPathCountByC_ST, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_ST, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((cpInstanceModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpInstanceModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {cpInstanceModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((cpInstanceModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpInstanceModelImpl.getOriginalUuid(),
					cpInstanceModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					cpInstanceModelImpl.getUuid(),
					cpInstanceModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((cpInstanceModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByGroupId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					cpInstanceModelImpl.getOriginalGroupId()
				};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);

				args = new Object[] {cpInstanceModelImpl.getGroupId()};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);
			}

			if ((cpInstanceModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCompanyId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					cpInstanceModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);

				args = new Object[] {cpInstanceModelImpl.getCompanyId()};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);
			}

			if ((cpInstanceModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCPDefinitionId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					cpInstanceModelImpl.getOriginalCPDefinitionId()
				};

				finderCache.removeResult(
					_finderPathCountByCPDefinitionId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCPDefinitionId, args);

				args = new Object[] {cpInstanceModelImpl.getCPDefinitionId()};

				finderCache.removeResult(
					_finderPathCountByCPDefinitionId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCPDefinitionId, args);
			}

			if ((cpInstanceModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCPInstanceUuid.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					cpInstanceModelImpl.getOriginalCPInstanceUuid()
				};

				finderCache.removeResult(
					_finderPathCountByCPInstanceUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCPInstanceUuid, args);

				args = new Object[] {cpInstanceModelImpl.getCPInstanceUuid()};

				finderCache.removeResult(
					_finderPathCountByCPInstanceUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCPInstanceUuid, args);
			}

			if ((cpInstanceModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByG_ST.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpInstanceModelImpl.getOriginalGroupId(),
					cpInstanceModelImpl.getOriginalStatus()
				};

				finderCache.removeResult(_finderPathCountByG_ST, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_ST, args);

				args = new Object[] {
					cpInstanceModelImpl.getGroupId(),
					cpInstanceModelImpl.getStatus()
				};

				finderCache.removeResult(_finderPathCountByG_ST, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_ST, args);
			}

			if ((cpInstanceModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_ST.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpInstanceModelImpl.getOriginalCPDefinitionId(),
					cpInstanceModelImpl.getOriginalStatus()
				};

				finderCache.removeResult(_finderPathCountByC_ST, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_ST, args);

				args = new Object[] {
					cpInstanceModelImpl.getCPDefinitionId(),
					cpInstanceModelImpl.getStatus()
				};

				finderCache.removeResult(_finderPathCountByC_ST, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_ST, args);
			}
		}

		entityCache.putResult(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED, CPInstanceImpl.class,
			cpInstance.getPrimaryKey(), cpInstance, false);

		clearUniqueFindersCache(cpInstanceModelImpl, false);
		cacheUniqueFindersCache(cpInstanceModelImpl);

		cpInstance.resetOriginalValues();

		return cpInstance;
	}

	/**
	 * Returns the cp instance with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp instance
	 * @return the cp instance
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	@Override
	public CPInstance findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCPInstanceException {

		CPInstance cpInstance = fetchByPrimaryKey(primaryKey);

		if (cpInstance == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCPInstanceException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return cpInstance;
	}

	/**
	 * Returns the cp instance with the primary key or throws a <code>NoSuchCPInstanceException</code> if it could not be found.
	 *
	 * @param CPInstanceId the primary key of the cp instance
	 * @return the cp instance
	 * @throws NoSuchCPInstanceException if a cp instance with the primary key could not be found
	 */
	@Override
	public CPInstance findByPrimaryKey(long CPInstanceId)
		throws NoSuchCPInstanceException {

		return findByPrimaryKey((Serializable)CPInstanceId);
	}

	/**
	 * Returns the cp instance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp instance
	 * @return the cp instance, or <code>null</code> if a cp instance with the primary key could not be found
	 */
	@Override
	public CPInstance fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED, CPInstanceImpl.class,
			primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CPInstance cpInstance = (CPInstance)serializable;

		if (cpInstance == null) {
			Session session = null;

			try {
				session = openSession();

				cpInstance = (CPInstance)session.get(
					CPInstanceImpl.class, primaryKey);

				if (cpInstance != null) {
					cacheResult(cpInstance);
				}
				else {
					entityCache.putResult(
						CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
						CPInstanceImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
					CPInstanceImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cpInstance;
	}

	/**
	 * Returns the cp instance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param CPInstanceId the primary key of the cp instance
	 * @return the cp instance, or <code>null</code> if a cp instance with the primary key could not be found
	 */
	@Override
	public CPInstance fetchByPrimaryKey(long CPInstanceId) {
		return fetchByPrimaryKey((Serializable)CPInstanceId);
	}

	@Override
	public Map<Serializable, CPInstance> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CPInstance> map =
			new HashMap<Serializable, CPInstance>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CPInstance cpInstance = fetchByPrimaryKey(primaryKey);

			if (cpInstance != null) {
				map.put(primaryKey, cpInstance);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CPInstanceModelImpl.ENTITY_CACHE_ENABLED, CPInstanceImpl.class,
				primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CPInstance)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_CPINSTANCE_WHERE_PKS_IN);

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

			for (CPInstance cpInstance : (List<CPInstance>)q.list()) {
				map.put(cpInstance.getPrimaryKeyObj(), cpInstance);

				cacheResult(cpInstance);

				uncachedPrimaryKeys.remove(cpInstance.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
					CPInstanceImpl.class, primaryKey, nullModel);
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
	 * Returns all the cp instances.
	 *
	 * @return the cp instances
	 */
	@Override
	public List<CPInstance> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp instances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @return the range of cp instances
	 */
	@Override
	public List<CPInstance> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp instances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cp instances
	 */
	@Override
	public List<CPInstance> findAll(
		int start, int end, OrderByComparator<CPInstance> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp instances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPInstanceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp instances
	 * @param end the upper bound of the range of cp instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cp instances
	 */
	@Override
	public List<CPInstance> findAll(
		int start, int end, OrderByComparator<CPInstance> orderByComparator,
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

		List<CPInstance> list = null;

		if (retrieveFromCache) {
			list = (List<CPInstance>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CPINSTANCE);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CPINSTANCE;

				if (pagination) {
					sql = sql.concat(CPInstanceModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CPInstance>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPInstance>)QueryUtil.list(
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
	 * Removes all the cp instances from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CPInstance cpInstance : findAll()) {
			remove(cpInstance);
		}
	}

	/**
	 * Returns the number of cp instances.
	 *
	 * @return the number of cp instances
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CPINSTANCE);

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
		return CPInstanceModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cp instance persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, CPInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, CPInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, CPInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, CPInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			CPInstanceModelImpl.UUID_COLUMN_BITMASK |
			CPInstanceModelImpl.DISPLAYDATE_COLUMN_BITMASK |
			CPInstanceModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, CPInstanceImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			CPInstanceModelImpl.UUID_COLUMN_BITMASK |
			CPInstanceModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, CPInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, CPInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			CPInstanceModelImpl.UUID_COLUMN_BITMASK |
			CPInstanceModelImpl.COMPANYID_COLUMN_BITMASK |
			CPInstanceModelImpl.DISPLAYDATE_COLUMN_BITMASK |
			CPInstanceModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, CPInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, CPInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()},
			CPInstanceModelImpl.GROUPID_COLUMN_BITMASK |
			CPInstanceModelImpl.DISPLAYDATE_COLUMN_BITMASK |
			CPInstanceModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByGroupId = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByCompanyId = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, CPInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCompanyId = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, CPInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] {Long.class.getName()},
			CPInstanceModelImpl.COMPANYID_COLUMN_BITMASK |
			CPInstanceModelImpl.DISPLAYDATE_COLUMN_BITMASK |
			CPInstanceModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByCompanyId = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByCPDefinitionId = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, CPInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCPDefinitionId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCPDefinitionId = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, CPInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCPDefinitionId",
			new String[] {Long.class.getName()},
			CPInstanceModelImpl.CPDEFINITIONID_COLUMN_BITMASK |
			CPInstanceModelImpl.DISPLAYDATE_COLUMN_BITMASK |
			CPInstanceModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByCPDefinitionId = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCPDefinitionId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByCPInstanceUuid = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, CPInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCPInstanceUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCPInstanceUuid = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, CPInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCPInstanceUuid",
			new String[] {String.class.getName()},
			CPInstanceModelImpl.CPINSTANCEUUID_COLUMN_BITMASK |
			CPInstanceModelImpl.DISPLAYDATE_COLUMN_BITMASK |
			CPInstanceModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByCPInstanceUuid = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCPInstanceUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByG_ST = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, CPInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_ST",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByG_ST = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, CPInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_ST",
			new String[] {Long.class.getName(), Integer.class.getName()},
			CPInstanceModelImpl.GROUPID_COLUMN_BITMASK |
			CPInstanceModelImpl.STATUS_COLUMN_BITMASK |
			CPInstanceModelImpl.DISPLAYDATE_COLUMN_BITMASK |
			CPInstanceModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByG_ST = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_ST",
			new String[] {Long.class.getName(), Integer.class.getName()});

		_finderPathFetchByC_C = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, CPInstanceImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByC_C",
			new String[] {Long.class.getName(), String.class.getName()},
			CPInstanceModelImpl.CPDEFINITIONID_COLUMN_BITMASK |
			CPInstanceModelImpl.CPINSTANCEUUID_COLUMN_BITMASK);

		_finderPathCountByC_C = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] {Long.class.getName(), String.class.getName()});

		_finderPathFetchByC_S = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, CPInstanceImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByC_S",
			new String[] {Long.class.getName(), String.class.getName()},
			CPInstanceModelImpl.CPDEFINITIONID_COLUMN_BITMASK |
			CPInstanceModelImpl.SKU_COLUMN_BITMASK);

		_finderPathCountByC_S = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_S",
			new String[] {Long.class.getName(), String.class.getName()});

		_finderPathWithPaginationFindByC_ST = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, CPInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_ST",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_ST = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, CPInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_ST",
			new String[] {Long.class.getName(), Integer.class.getName()},
			CPInstanceModelImpl.CPDEFINITIONID_COLUMN_BITMASK |
			CPInstanceModelImpl.STATUS_COLUMN_BITMASK |
			CPInstanceModelImpl.DISPLAYDATE_COLUMN_BITMASK |
			CPInstanceModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByC_ST = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_ST",
			new String[] {Long.class.getName(), Integer.class.getName()});

		_finderPathWithPaginationFindByLtD_S = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, CPInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLtD_S",
			new String[] {
				Date.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByLtD_S = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByLtD_S",
			new String[] {Date.class.getName(), Integer.class.getName()});

		_finderPathWithPaginationFindByC_LtD_S = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, CPInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_LtD_S",
			new String[] {
				Long.class.getName(), Date.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByC_LtD_S = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByC_LtD_S",
			new String[] {
				Long.class.getName(), Date.class.getName(),
				Integer.class.getName()
			});

		_finderPathFetchByC_ERC = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, CPInstanceImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByC_ERC",
			new String[] {Long.class.getName(), String.class.getName()},
			CPInstanceModelImpl.COMPANYID_COLUMN_BITMASK |
			CPInstanceModelImpl.EXTERNALREFERENCECODE_COLUMN_BITMASK);

		_finderPathCountByC_ERC = new FinderPath(
			CPInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CPInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_ERC",
			new String[] {Long.class.getName(), String.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(CPInstanceImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private Long _getTime(Date date) {
		if (date == null) {
			return null;
		}

		return date.getTime();
	}

	private static final String _SQL_SELECT_CPINSTANCE =
		"SELECT cpInstance FROM CPInstance cpInstance";

	private static final String _SQL_SELECT_CPINSTANCE_WHERE_PKS_IN =
		"SELECT cpInstance FROM CPInstance cpInstance WHERE CPInstanceId IN (";

	private static final String _SQL_SELECT_CPINSTANCE_WHERE =
		"SELECT cpInstance FROM CPInstance cpInstance WHERE ";

	private static final String _SQL_COUNT_CPINSTANCE =
		"SELECT COUNT(cpInstance) FROM CPInstance cpInstance";

	private static final String _SQL_COUNT_CPINSTANCE_WHERE =
		"SELECT COUNT(cpInstance) FROM CPInstance cpInstance WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "cpInstance.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CPInstance exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CPInstance exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CPInstancePersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}