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

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.exception.NoSuchOrderException;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.impl.CommerceOrderImpl;
import com.liferay.commerce.model.impl.CommerceOrderModelImpl;
import com.liferay.commerce.service.persistence.CommerceOrderPersistence;
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
 * The persistence implementation for the commerce order service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
@ProviderType
public class CommerceOrderPersistenceImpl
	extends BasePersistenceImpl<CommerceOrder>
	implements CommerceOrderPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommerceOrderUtil</code> to access the commerce order persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommerceOrderImpl.class.getName();

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
	 * Returns all the commerce orders where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce orders where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @return the range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce orders where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce orders where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator,
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

		List<CommerceOrder> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceOrder>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceOrder commerceOrder : list) {
					if (!uuid.equals(commerceOrder.getUuid())) {
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

			query.append(_SQL_SELECT_COMMERCEORDER_WHERE);

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
				query.append(CommerceOrderModelImpl.ORDER_BY_JPQL);
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
					list = (List<CommerceOrder>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceOrder>)QueryUtil.list(
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
	 * Returns the first commerce order in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder findByUuid_First(
			String uuid, OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = fetchByUuid_First(
			uuid, orderByComparator);

		if (commerceOrder != null) {
			return commerceOrder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchOrderException(msg.toString());
	}

	/**
	 * Returns the first commerce order in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder fetchByUuid_First(
		String uuid, OrderByComparator<CommerceOrder> orderByComparator) {

		List<CommerceOrder> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce order in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder findByUuid_Last(
			String uuid, OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = fetchByUuid_Last(uuid, orderByComparator);

		if (commerceOrder != null) {
			return commerceOrder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchOrderException(msg.toString());
	}

	/**
	 * Returns the last commerce order in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder fetchByUuid_Last(
		String uuid, OrderByComparator<CommerceOrder> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CommerceOrder> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce orders before and after the current commerce order in the ordered set where uuid = &#63;.
	 *
	 * @param commerceOrderId the primary key of the current commerce order
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	@Override
	public CommerceOrder[] findByUuid_PrevAndNext(
			long commerceOrderId, String uuid,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		uuid = Objects.toString(uuid, "");

		CommerceOrder commerceOrder = findByPrimaryKey(commerceOrderId);

		Session session = null;

		try {
			session = openSession();

			CommerceOrder[] array = new CommerceOrderImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, commerceOrder, uuid, orderByComparator, true);

			array[1] = commerceOrder;

			array[2] = getByUuid_PrevAndNext(
				session, commerceOrder, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceOrder getByUuid_PrevAndNext(
		Session session, CommerceOrder commerceOrder, String uuid,
		OrderByComparator<CommerceOrder> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COMMERCEORDER_WHERE);

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
			query.append(CommerceOrderModelImpl.ORDER_BY_JPQL);
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
						commerceOrder)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceOrder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce orders where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CommerceOrder commerceOrder :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commerceOrder);
		}
	}

	/**
	 * Returns the number of commerce orders where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching commerce orders
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEORDER_WHERE);

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
		"commerceOrder.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(commerceOrder.uuid IS NULL OR commerceOrder.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the commerce order where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchOrderException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder findByUUID_G(String uuid, long groupId)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = fetchByUUID_G(uuid, groupId);

		if (commerceOrder == null) {
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

			throw new NoSuchOrderException(msg.toString());
		}

		return commerceOrder;
	}

	/**
	 * Returns the commerce order where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the commerce order where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = new Object[] {uuid, groupId};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof CommerceOrder) {
			CommerceOrder commerceOrder = (CommerceOrder)result;

			if (!Objects.equals(uuid, commerceOrder.getUuid()) ||
				(groupId != commerceOrder.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COMMERCEORDER_WHERE);

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

				List<CommerceOrder> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByUUID_G, finderArgs, list);
				}
				else {
					CommerceOrder commerceOrder = list.get(0);

					result = commerceOrder;

					cacheResult(commerceOrder);
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
			return (CommerceOrder)result;
		}
	}

	/**
	 * Removes the commerce order where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the commerce order that was removed
	 */
	@Override
	public CommerceOrder removeByUUID_G(String uuid, long groupId)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = findByUUID_G(uuid, groupId);

		return remove(commerceOrder);
	}

	/**
	 * Returns the number of commerce orders where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching commerce orders
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEORDER_WHERE);

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
		"commerceOrder.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(commerceOrder.uuid IS NULL OR commerceOrder.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"commerceOrder.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the commerce orders where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce orders where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @return the range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce orders where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce orders where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator,
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

		List<CommerceOrder> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceOrder>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceOrder commerceOrder : list) {
					if (!uuid.equals(commerceOrder.getUuid()) ||
						(companyId != commerceOrder.getCompanyId())) {

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

			query.append(_SQL_SELECT_COMMERCEORDER_WHERE);

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
				query.append(CommerceOrderModelImpl.ORDER_BY_JPQL);
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
					list = (List<CommerceOrder>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceOrder>)QueryUtil.list(
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
	 * Returns the first commerce order in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (commerceOrder != null) {
			return commerceOrder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchOrderException(msg.toString());
	}

	/**
	 * Returns the first commerce order in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CommerceOrder> orderByComparator) {

		List<CommerceOrder> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce order in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (commerceOrder != null) {
			return commerceOrder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchOrderException(msg.toString());
	}

	/**
	 * Returns the last commerce order in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CommerceOrder> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CommerceOrder> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce orders before and after the current commerce order in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param commerceOrderId the primary key of the current commerce order
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	@Override
	public CommerceOrder[] findByUuid_C_PrevAndNext(
			long commerceOrderId, String uuid, long companyId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		uuid = Objects.toString(uuid, "");

		CommerceOrder commerceOrder = findByPrimaryKey(commerceOrderId);

		Session session = null;

		try {
			session = openSession();

			CommerceOrder[] array = new CommerceOrderImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, commerceOrder, uuid, companyId, orderByComparator,
				true);

			array[1] = commerceOrder;

			array[2] = getByUuid_C_PrevAndNext(
				session, commerceOrder, uuid, companyId, orderByComparator,
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

	protected CommerceOrder getByUuid_C_PrevAndNext(
		Session session, CommerceOrder commerceOrder, String uuid,
		long companyId, OrderByComparator<CommerceOrder> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEORDER_WHERE);

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
			query.append(CommerceOrderModelImpl.ORDER_BY_JPQL);
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
						commerceOrder)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceOrder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce orders where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CommerceOrder commerceOrder :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceOrder);
		}
	}

	/**
	 * Returns the number of commerce orders where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching commerce orders
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEORDER_WHERE);

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
		"commerceOrder.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(commerceOrder.uuid IS NULL OR commerceOrder.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"commerceOrder.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the commerce orders where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce orders where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @return the range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce orders where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce orders where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator,
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

		List<CommerceOrder> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceOrder>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceOrder commerceOrder : list) {
					if ((groupId != commerceOrder.getGroupId())) {
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

			query.append(_SQL_SELECT_COMMERCEORDER_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceOrderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<CommerceOrder>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceOrder>)QueryUtil.list(
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
	 * Returns the first commerce order in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder findByGroupId_First(
			long groupId, OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = fetchByGroupId_First(
			groupId, orderByComparator);

		if (commerceOrder != null) {
			return commerceOrder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchOrderException(msg.toString());
	}

	/**
	 * Returns the first commerce order in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder fetchByGroupId_First(
		long groupId, OrderByComparator<CommerceOrder> orderByComparator) {

		List<CommerceOrder> list = findByGroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce order in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder findByGroupId_Last(
			long groupId, OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = fetchByGroupId_Last(
			groupId, orderByComparator);

		if (commerceOrder != null) {
			return commerceOrder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchOrderException(msg.toString());
	}

	/**
	 * Returns the last commerce order in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder fetchByGroupId_Last(
		long groupId, OrderByComparator<CommerceOrder> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<CommerceOrder> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce orders before and after the current commerce order in the ordered set where groupId = &#63;.
	 *
	 * @param commerceOrderId the primary key of the current commerce order
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	@Override
	public CommerceOrder[] findByGroupId_PrevAndNext(
			long commerceOrderId, long groupId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = findByPrimaryKey(commerceOrderId);

		Session session = null;

		try {
			session = openSession();

			CommerceOrder[] array = new CommerceOrderImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, commerceOrder, groupId, orderByComparator, true);

			array[1] = commerceOrder;

			array[2] = getByGroupId_PrevAndNext(
				session, commerceOrder, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceOrder getByGroupId_PrevAndNext(
		Session session, CommerceOrder commerceOrder, long groupId,
		OrderByComparator<CommerceOrder> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COMMERCEORDER_WHERE);

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
			query.append(CommerceOrderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceOrder)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceOrder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce orders where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (CommerceOrder commerceOrder :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commerceOrder);
		}
	}

	/**
	 * Returns the number of commerce orders where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching commerce orders
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEORDER_WHERE);

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
		"commerceOrder.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUserId;
	private FinderPath _finderPathWithoutPaginationFindByUserId;
	private FinderPath _finderPathCountByUserId;

	/**
	 * Returns all the commerce orders where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce orders where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @return the range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce orders where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByUserId(
		long userId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce orders where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByUserId(
		long userId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUserId;
			finderArgs = new Object[] {userId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByUserId;
			finderArgs = new Object[] {userId, start, end, orderByComparator};
		}

		List<CommerceOrder> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceOrder>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceOrder commerceOrder : list) {
					if ((userId != commerceOrder.getUserId())) {
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

			query.append(_SQL_SELECT_COMMERCEORDER_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceOrderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<CommerceOrder>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceOrder>)QueryUtil.list(
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
	 * Returns the first commerce order in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder findByUserId_First(
			long userId, OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = fetchByUserId_First(
			userId, orderByComparator);

		if (commerceOrder != null) {
			return commerceOrder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchOrderException(msg.toString());
	}

	/**
	 * Returns the first commerce order in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder fetchByUserId_First(
		long userId, OrderByComparator<CommerceOrder> orderByComparator) {

		List<CommerceOrder> list = findByUserId(
			userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce order in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder findByUserId_Last(
			long userId, OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = fetchByUserId_Last(
			userId, orderByComparator);

		if (commerceOrder != null) {
			return commerceOrder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchOrderException(msg.toString());
	}

	/**
	 * Returns the last commerce order in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder fetchByUserId_Last(
		long userId, OrderByComparator<CommerceOrder> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<CommerceOrder> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce orders before and after the current commerce order in the ordered set where userId = &#63;.
	 *
	 * @param commerceOrderId the primary key of the current commerce order
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	@Override
	public CommerceOrder[] findByUserId_PrevAndNext(
			long commerceOrderId, long userId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = findByPrimaryKey(commerceOrderId);

		Session session = null;

		try {
			session = openSession();

			CommerceOrder[] array = new CommerceOrderImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, commerceOrder, userId, orderByComparator, true);

			array[1] = commerceOrder;

			array[2] = getByUserId_PrevAndNext(
				session, commerceOrder, userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceOrder getByUserId_PrevAndNext(
		Session session, CommerceOrder commerceOrder, long userId,
		OrderByComparator<CommerceOrder> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COMMERCEORDER_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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
			query.append(CommerceOrderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceOrder)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceOrder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce orders where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (CommerceOrder commerceOrder :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commerceOrder);
		}
	}

	/**
	 * Returns the number of commerce orders where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching commerce orders
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEORDER_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 =
		"commerceOrder.userId = ?";

	private FinderPath _finderPathWithPaginationFindByBillingAddressId;
	private FinderPath _finderPathWithoutPaginationFindByBillingAddressId;
	private FinderPath _finderPathCountByBillingAddressId;

	/**
	 * Returns all the commerce orders where billingAddressId = &#63;.
	 *
	 * @param billingAddressId the billing address ID
	 * @return the matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByBillingAddressId(long billingAddressId) {
		return findByBillingAddressId(
			billingAddressId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce orders where billingAddressId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param billingAddressId the billing address ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @return the range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByBillingAddressId(
		long billingAddressId, int start, int end) {

		return findByBillingAddressId(billingAddressId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce orders where billingAddressId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param billingAddressId the billing address ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByBillingAddressId(
		long billingAddressId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return findByBillingAddressId(
			billingAddressId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce orders where billingAddressId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param billingAddressId the billing address ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByBillingAddressId(
		long billingAddressId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByBillingAddressId;
			finderArgs = new Object[] {billingAddressId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByBillingAddressId;
			finderArgs = new Object[] {
				billingAddressId, start, end, orderByComparator
			};
		}

		List<CommerceOrder> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceOrder>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceOrder commerceOrder : list) {
					if ((billingAddressId !=
							commerceOrder.getBillingAddressId())) {

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

			query.append(_SQL_SELECT_COMMERCEORDER_WHERE);

			query.append(_FINDER_COLUMN_BILLINGADDRESSID_BILLINGADDRESSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceOrderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(billingAddressId);

				if (!pagination) {
					list = (List<CommerceOrder>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceOrder>)QueryUtil.list(
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
	 * Returns the first commerce order in the ordered set where billingAddressId = &#63;.
	 *
	 * @param billingAddressId the billing address ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder findByBillingAddressId_First(
			long billingAddressId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = fetchByBillingAddressId_First(
			billingAddressId, orderByComparator);

		if (commerceOrder != null) {
			return commerceOrder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("billingAddressId=");
		msg.append(billingAddressId);

		msg.append("}");

		throw new NoSuchOrderException(msg.toString());
	}

	/**
	 * Returns the first commerce order in the ordered set where billingAddressId = &#63;.
	 *
	 * @param billingAddressId the billing address ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder fetchByBillingAddressId_First(
		long billingAddressId,
		OrderByComparator<CommerceOrder> orderByComparator) {

		List<CommerceOrder> list = findByBillingAddressId(
			billingAddressId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce order in the ordered set where billingAddressId = &#63;.
	 *
	 * @param billingAddressId the billing address ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder findByBillingAddressId_Last(
			long billingAddressId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = fetchByBillingAddressId_Last(
			billingAddressId, orderByComparator);

		if (commerceOrder != null) {
			return commerceOrder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("billingAddressId=");
		msg.append(billingAddressId);

		msg.append("}");

		throw new NoSuchOrderException(msg.toString());
	}

	/**
	 * Returns the last commerce order in the ordered set where billingAddressId = &#63;.
	 *
	 * @param billingAddressId the billing address ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder fetchByBillingAddressId_Last(
		long billingAddressId,
		OrderByComparator<CommerceOrder> orderByComparator) {

		int count = countByBillingAddressId(billingAddressId);

		if (count == 0) {
			return null;
		}

		List<CommerceOrder> list = findByBillingAddressId(
			billingAddressId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce orders before and after the current commerce order in the ordered set where billingAddressId = &#63;.
	 *
	 * @param commerceOrderId the primary key of the current commerce order
	 * @param billingAddressId the billing address ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	@Override
	public CommerceOrder[] findByBillingAddressId_PrevAndNext(
			long commerceOrderId, long billingAddressId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = findByPrimaryKey(commerceOrderId);

		Session session = null;

		try {
			session = openSession();

			CommerceOrder[] array = new CommerceOrderImpl[3];

			array[0] = getByBillingAddressId_PrevAndNext(
				session, commerceOrder, billingAddressId, orderByComparator,
				true);

			array[1] = commerceOrder;

			array[2] = getByBillingAddressId_PrevAndNext(
				session, commerceOrder, billingAddressId, orderByComparator,
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

	protected CommerceOrder getByBillingAddressId_PrevAndNext(
		Session session, CommerceOrder commerceOrder, long billingAddressId,
		OrderByComparator<CommerceOrder> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COMMERCEORDER_WHERE);

		query.append(_FINDER_COLUMN_BILLINGADDRESSID_BILLINGADDRESSID_2);

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
			query.append(CommerceOrderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(billingAddressId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceOrder)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceOrder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce orders where billingAddressId = &#63; from the database.
	 *
	 * @param billingAddressId the billing address ID
	 */
	@Override
	public void removeByBillingAddressId(long billingAddressId) {
		for (CommerceOrder commerceOrder :
				findByBillingAddressId(
					billingAddressId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceOrder);
		}
	}

	/**
	 * Returns the number of commerce orders where billingAddressId = &#63;.
	 *
	 * @param billingAddressId the billing address ID
	 * @return the number of matching commerce orders
	 */
	@Override
	public int countByBillingAddressId(long billingAddressId) {
		FinderPath finderPath = _finderPathCountByBillingAddressId;

		Object[] finderArgs = new Object[] {billingAddressId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEORDER_WHERE);

			query.append(_FINDER_COLUMN_BILLINGADDRESSID_BILLINGADDRESSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(billingAddressId);

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

	private static final String
		_FINDER_COLUMN_BILLINGADDRESSID_BILLINGADDRESSID_2 =
			"commerceOrder.billingAddressId = ?";

	private FinderPath _finderPathWithPaginationFindByShippingAddressId;
	private FinderPath _finderPathWithoutPaginationFindByShippingAddressId;
	private FinderPath _finderPathCountByShippingAddressId;

	/**
	 * Returns all the commerce orders where shippingAddressId = &#63;.
	 *
	 * @param shippingAddressId the shipping address ID
	 * @return the matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByShippingAddressId(long shippingAddressId) {
		return findByShippingAddressId(
			shippingAddressId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce orders where shippingAddressId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param shippingAddressId the shipping address ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @return the range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByShippingAddressId(
		long shippingAddressId, int start, int end) {

		return findByShippingAddressId(shippingAddressId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce orders where shippingAddressId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param shippingAddressId the shipping address ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByShippingAddressId(
		long shippingAddressId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return findByShippingAddressId(
			shippingAddressId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce orders where shippingAddressId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param shippingAddressId the shipping address ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByShippingAddressId(
		long shippingAddressId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByShippingAddressId;
			finderArgs = new Object[] {shippingAddressId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByShippingAddressId;
			finderArgs = new Object[] {
				shippingAddressId, start, end, orderByComparator
			};
		}

		List<CommerceOrder> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceOrder>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceOrder commerceOrder : list) {
					if ((shippingAddressId !=
							commerceOrder.getShippingAddressId())) {

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

			query.append(_SQL_SELECT_COMMERCEORDER_WHERE);

			query.append(_FINDER_COLUMN_SHIPPINGADDRESSID_SHIPPINGADDRESSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceOrderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(shippingAddressId);

				if (!pagination) {
					list = (List<CommerceOrder>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceOrder>)QueryUtil.list(
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
	 * Returns the first commerce order in the ordered set where shippingAddressId = &#63;.
	 *
	 * @param shippingAddressId the shipping address ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder findByShippingAddressId_First(
			long shippingAddressId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = fetchByShippingAddressId_First(
			shippingAddressId, orderByComparator);

		if (commerceOrder != null) {
			return commerceOrder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shippingAddressId=");
		msg.append(shippingAddressId);

		msg.append("}");

		throw new NoSuchOrderException(msg.toString());
	}

	/**
	 * Returns the first commerce order in the ordered set where shippingAddressId = &#63;.
	 *
	 * @param shippingAddressId the shipping address ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder fetchByShippingAddressId_First(
		long shippingAddressId,
		OrderByComparator<CommerceOrder> orderByComparator) {

		List<CommerceOrder> list = findByShippingAddressId(
			shippingAddressId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce order in the ordered set where shippingAddressId = &#63;.
	 *
	 * @param shippingAddressId the shipping address ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder findByShippingAddressId_Last(
			long shippingAddressId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = fetchByShippingAddressId_Last(
			shippingAddressId, orderByComparator);

		if (commerceOrder != null) {
			return commerceOrder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shippingAddressId=");
		msg.append(shippingAddressId);

		msg.append("}");

		throw new NoSuchOrderException(msg.toString());
	}

	/**
	 * Returns the last commerce order in the ordered set where shippingAddressId = &#63;.
	 *
	 * @param shippingAddressId the shipping address ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder fetchByShippingAddressId_Last(
		long shippingAddressId,
		OrderByComparator<CommerceOrder> orderByComparator) {

		int count = countByShippingAddressId(shippingAddressId);

		if (count == 0) {
			return null;
		}

		List<CommerceOrder> list = findByShippingAddressId(
			shippingAddressId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce orders before and after the current commerce order in the ordered set where shippingAddressId = &#63;.
	 *
	 * @param commerceOrderId the primary key of the current commerce order
	 * @param shippingAddressId the shipping address ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	@Override
	public CommerceOrder[] findByShippingAddressId_PrevAndNext(
			long commerceOrderId, long shippingAddressId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = findByPrimaryKey(commerceOrderId);

		Session session = null;

		try {
			session = openSession();

			CommerceOrder[] array = new CommerceOrderImpl[3];

			array[0] = getByShippingAddressId_PrevAndNext(
				session, commerceOrder, shippingAddressId, orderByComparator,
				true);

			array[1] = commerceOrder;

			array[2] = getByShippingAddressId_PrevAndNext(
				session, commerceOrder, shippingAddressId, orderByComparator,
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

	protected CommerceOrder getByShippingAddressId_PrevAndNext(
		Session session, CommerceOrder commerceOrder, long shippingAddressId,
		OrderByComparator<CommerceOrder> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COMMERCEORDER_WHERE);

		query.append(_FINDER_COLUMN_SHIPPINGADDRESSID_SHIPPINGADDRESSID_2);

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
			query.append(CommerceOrderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(shippingAddressId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceOrder)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceOrder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce orders where shippingAddressId = &#63; from the database.
	 *
	 * @param shippingAddressId the shipping address ID
	 */
	@Override
	public void removeByShippingAddressId(long shippingAddressId) {
		for (CommerceOrder commerceOrder :
				findByShippingAddressId(
					shippingAddressId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceOrder);
		}
	}

	/**
	 * Returns the number of commerce orders where shippingAddressId = &#63;.
	 *
	 * @param shippingAddressId the shipping address ID
	 * @return the number of matching commerce orders
	 */
	@Override
	public int countByShippingAddressId(long shippingAddressId) {
		FinderPath finderPath = _finderPathCountByShippingAddressId;

		Object[] finderArgs = new Object[] {shippingAddressId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEORDER_WHERE);

			query.append(_FINDER_COLUMN_SHIPPINGADDRESSID_SHIPPINGADDRESSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(shippingAddressId);

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

	private static final String
		_FINDER_COLUMN_SHIPPINGADDRESSID_SHIPPINGADDRESSID_2 =
			"commerceOrder.shippingAddressId = ?";

	private FinderPath _finderPathWithPaginationFindByG_C;
	private FinderPath _finderPathWithoutPaginationFindByG_C;
	private FinderPath _finderPathCountByG_C;

	/**
	 * Returns all the commerce orders where groupId = &#63; and commerceAccountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @return the matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByG_C(long groupId, long commerceAccountId) {
		return findByG_C(
			groupId, commerceAccountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the commerce orders where groupId = &#63; and commerceAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @return the range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByG_C(
		long groupId, long commerceAccountId, int start, int end) {

		return findByG_C(groupId, commerceAccountId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce orders where groupId = &#63; and commerceAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByG_C(
		long groupId, long commerceAccountId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return findByG_C(
			groupId, commerceAccountId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce orders where groupId = &#63; and commerceAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByG_C(
		long groupId, long commerceAccountId, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByG_C;
			finderArgs = new Object[] {groupId, commerceAccountId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByG_C;
			finderArgs = new Object[] {
				groupId, commerceAccountId, start, end, orderByComparator
			};
		}

		List<CommerceOrder> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceOrder>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceOrder commerceOrder : list) {
					if ((groupId != commerceOrder.getGroupId()) ||
						(commerceAccountId !=
							commerceOrder.getCommerceAccountId())) {

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

			query.append(_SQL_SELECT_COMMERCEORDER_WHERE);

			query.append(_FINDER_COLUMN_G_C_GROUPID_2);

			query.append(_FINDER_COLUMN_G_C_COMMERCEACCOUNTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceOrderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(commerceAccountId);

				if (!pagination) {
					list = (List<CommerceOrder>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceOrder>)QueryUtil.list(
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
	 * Returns the first commerce order in the ordered set where groupId = &#63; and commerceAccountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder findByG_C_First(
			long groupId, long commerceAccountId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = fetchByG_C_First(
			groupId, commerceAccountId, orderByComparator);

		if (commerceOrder != null) {
			return commerceOrder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", commerceAccountId=");
		msg.append(commerceAccountId);

		msg.append("}");

		throw new NoSuchOrderException(msg.toString());
	}

	/**
	 * Returns the first commerce order in the ordered set where groupId = &#63; and commerceAccountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder fetchByG_C_First(
		long groupId, long commerceAccountId,
		OrderByComparator<CommerceOrder> orderByComparator) {

		List<CommerceOrder> list = findByG_C(
			groupId, commerceAccountId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce order in the ordered set where groupId = &#63; and commerceAccountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder findByG_C_Last(
			long groupId, long commerceAccountId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = fetchByG_C_Last(
			groupId, commerceAccountId, orderByComparator);

		if (commerceOrder != null) {
			return commerceOrder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", commerceAccountId=");
		msg.append(commerceAccountId);

		msg.append("}");

		throw new NoSuchOrderException(msg.toString());
	}

	/**
	 * Returns the last commerce order in the ordered set where groupId = &#63; and commerceAccountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder fetchByG_C_Last(
		long groupId, long commerceAccountId,
		OrderByComparator<CommerceOrder> orderByComparator) {

		int count = countByG_C(groupId, commerceAccountId);

		if (count == 0) {
			return null;
		}

		List<CommerceOrder> list = findByG_C(
			groupId, commerceAccountId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce orders before and after the current commerce order in the ordered set where groupId = &#63; and commerceAccountId = &#63;.
	 *
	 * @param commerceOrderId the primary key of the current commerce order
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	@Override
	public CommerceOrder[] findByG_C_PrevAndNext(
			long commerceOrderId, long groupId, long commerceAccountId,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = findByPrimaryKey(commerceOrderId);

		Session session = null;

		try {
			session = openSession();

			CommerceOrder[] array = new CommerceOrderImpl[3];

			array[0] = getByG_C_PrevAndNext(
				session, commerceOrder, groupId, commerceAccountId,
				orderByComparator, true);

			array[1] = commerceOrder;

			array[2] = getByG_C_PrevAndNext(
				session, commerceOrder, groupId, commerceAccountId,
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

	protected CommerceOrder getByG_C_PrevAndNext(
		Session session, CommerceOrder commerceOrder, long groupId,
		long commerceAccountId,
		OrderByComparator<CommerceOrder> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_COMMERCEORDER_WHERE);

		query.append(_FINDER_COLUMN_G_C_GROUPID_2);

		query.append(_FINDER_COLUMN_G_C_COMMERCEACCOUNTID_2);

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
			query.append(CommerceOrderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(commerceAccountId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceOrder)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceOrder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce orders where groupId = &#63; and commerceAccountId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 */
	@Override
	public void removeByG_C(long groupId, long commerceAccountId) {
		for (CommerceOrder commerceOrder :
				findByG_C(
					groupId, commerceAccountId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(commerceOrder);
		}
	}

	/**
	 * Returns the number of commerce orders where groupId = &#63; and commerceAccountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @return the number of matching commerce orders
	 */
	@Override
	public int countByG_C(long groupId, long commerceAccountId) {
		FinderPath finderPath = _finderPathCountByG_C;

		Object[] finderArgs = new Object[] {groupId, commerceAccountId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEORDER_WHERE);

			query.append(_FINDER_COLUMN_G_C_GROUPID_2);

			query.append(_FINDER_COLUMN_G_C_COMMERCEACCOUNTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(commerceAccountId);

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

	private static final String _FINDER_COLUMN_G_C_GROUPID_2 =
		"commerceOrder.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_C_COMMERCEACCOUNTID_2 =
		"commerceOrder.commerceAccountId = ?";

	private FinderPath _finderPathWithPaginationFindByG_U_O;
	private FinderPath _finderPathWithoutPaginationFindByG_U_O;
	private FinderPath _finderPathCountByG_U_O;

	/**
	 * Returns all the commerce orders where groupId = &#63; and userId = &#63; and orderStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderStatus the order status
	 * @return the matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByG_U_O(
		long groupId, long userId, int orderStatus) {

		return findByG_U_O(
			groupId, userId, orderStatus, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the commerce orders where groupId = &#63; and userId = &#63; and orderStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderStatus the order status
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @return the range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByG_U_O(
		long groupId, long userId, int orderStatus, int start, int end) {

		return findByG_U_O(groupId, userId, orderStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce orders where groupId = &#63; and userId = &#63; and orderStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderStatus the order status
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByG_U_O(
		long groupId, long userId, int orderStatus, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return findByG_U_O(
			groupId, userId, orderStatus, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce orders where groupId = &#63; and userId = &#63; and orderStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderStatus the order status
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByG_U_O(
		long groupId, long userId, int orderStatus, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByG_U_O;
			finderArgs = new Object[] {groupId, userId, orderStatus};
		}
		else {
			finderPath = _finderPathWithPaginationFindByG_U_O;
			finderArgs = new Object[] {
				groupId, userId, orderStatus, start, end, orderByComparator
			};
		}

		List<CommerceOrder> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceOrder>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceOrder commerceOrder : list) {
					if ((groupId != commerceOrder.getGroupId()) ||
						(userId != commerceOrder.getUserId()) ||
						(orderStatus != commerceOrder.getOrderStatus())) {

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

			query.append(_SQL_SELECT_COMMERCEORDER_WHERE);

			query.append(_FINDER_COLUMN_G_U_O_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_O_USERID_2);

			query.append(_FINDER_COLUMN_G_U_O_ORDERSTATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceOrderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(orderStatus);

				if (!pagination) {
					list = (List<CommerceOrder>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceOrder>)QueryUtil.list(
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
	 * Returns the first commerce order in the ordered set where groupId = &#63; and userId = &#63; and orderStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder findByG_U_O_First(
			long groupId, long userId, int orderStatus,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = fetchByG_U_O_First(
			groupId, userId, orderStatus, orderByComparator);

		if (commerceOrder != null) {
			return commerceOrder;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", orderStatus=");
		msg.append(orderStatus);

		msg.append("}");

		throw new NoSuchOrderException(msg.toString());
	}

	/**
	 * Returns the first commerce order in the ordered set where groupId = &#63; and userId = &#63; and orderStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder fetchByG_U_O_First(
		long groupId, long userId, int orderStatus,
		OrderByComparator<CommerceOrder> orderByComparator) {

		List<CommerceOrder> list = findByG_U_O(
			groupId, userId, orderStatus, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce order in the ordered set where groupId = &#63; and userId = &#63; and orderStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder findByG_U_O_Last(
			long groupId, long userId, int orderStatus,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = fetchByG_U_O_Last(
			groupId, userId, orderStatus, orderByComparator);

		if (commerceOrder != null) {
			return commerceOrder;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", orderStatus=");
		msg.append(orderStatus);

		msg.append("}");

		throw new NoSuchOrderException(msg.toString());
	}

	/**
	 * Returns the last commerce order in the ordered set where groupId = &#63; and userId = &#63; and orderStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder fetchByG_U_O_Last(
		long groupId, long userId, int orderStatus,
		OrderByComparator<CommerceOrder> orderByComparator) {

		int count = countByG_U_O(groupId, userId, orderStatus);

		if (count == 0) {
			return null;
		}

		List<CommerceOrder> list = findByG_U_O(
			groupId, userId, orderStatus, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce orders before and after the current commerce order in the ordered set where groupId = &#63; and userId = &#63; and orderStatus = &#63;.
	 *
	 * @param commerceOrderId the primary key of the current commerce order
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	@Override
	public CommerceOrder[] findByG_U_O_PrevAndNext(
			long commerceOrderId, long groupId, long userId, int orderStatus,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = findByPrimaryKey(commerceOrderId);

		Session session = null;

		try {
			session = openSession();

			CommerceOrder[] array = new CommerceOrderImpl[3];

			array[0] = getByG_U_O_PrevAndNext(
				session, commerceOrder, groupId, userId, orderStatus,
				orderByComparator, true);

			array[1] = commerceOrder;

			array[2] = getByG_U_O_PrevAndNext(
				session, commerceOrder, groupId, userId, orderStatus,
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

	protected CommerceOrder getByG_U_O_PrevAndNext(
		Session session, CommerceOrder commerceOrder, long groupId, long userId,
		int orderStatus, OrderByComparator<CommerceOrder> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEORDER_WHERE);

		query.append(_FINDER_COLUMN_G_U_O_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_O_USERID_2);

		query.append(_FINDER_COLUMN_G_U_O_ORDERSTATUS_2);

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
			query.append(CommerceOrderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		qPos.add(orderStatus);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceOrder)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceOrder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce orders where groupId = &#63; and userId = &#63; and orderStatus = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderStatus the order status
	 */
	@Override
	public void removeByG_U_O(long groupId, long userId, int orderStatus) {
		for (CommerceOrder commerceOrder :
				findByG_U_O(
					groupId, userId, orderStatus, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(commerceOrder);
		}
	}

	/**
	 * Returns the number of commerce orders where groupId = &#63; and userId = &#63; and orderStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderStatus the order status
	 * @return the number of matching commerce orders
	 */
	@Override
	public int countByG_U_O(long groupId, long userId, int orderStatus) {
		FinderPath finderPath = _finderPathCountByG_U_O;

		Object[] finderArgs = new Object[] {groupId, userId, orderStatus};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_COMMERCEORDER_WHERE);

			query.append(_FINDER_COLUMN_G_U_O_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_O_USERID_2);

			query.append(_FINDER_COLUMN_G_U_O_ORDERSTATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(orderStatus);

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

	private static final String _FINDER_COLUMN_G_U_O_GROUPID_2 =
		"commerceOrder.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_U_O_USERID_2 =
		"commerceOrder.userId = ? AND ";

	private static final String _FINDER_COLUMN_G_U_O_ORDERSTATUS_2 =
		"commerceOrder.orderStatus = ?";

	private FinderPath _finderPathWithPaginationFindByG_C_O;
	private FinderPath _finderPathWithoutPaginationFindByG_C_O;
	private FinderPath _finderPathCountByG_C_O;

	/**
	 * Returns all the commerce orders where groupId = &#63; and commerceAccountId = &#63; and orderStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderStatus the order status
	 * @return the matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByG_C_O(
		long groupId, long commerceAccountId, int orderStatus) {

		return findByG_C_O(
			groupId, commerceAccountId, orderStatus, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce orders where groupId = &#63; and commerceAccountId = &#63; and orderStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderStatus the order status
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @return the range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByG_C_O(
		long groupId, long commerceAccountId, int orderStatus, int start,
		int end) {

		return findByG_C_O(
			groupId, commerceAccountId, orderStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce orders where groupId = &#63; and commerceAccountId = &#63; and orderStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderStatus the order status
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByG_C_O(
		long groupId, long commerceAccountId, int orderStatus, int start,
		int end, OrderByComparator<CommerceOrder> orderByComparator) {

		return findByG_C_O(
			groupId, commerceAccountId, orderStatus, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce orders where groupId = &#63; and commerceAccountId = &#63; and orderStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderStatus the order status
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByG_C_O(
		long groupId, long commerceAccountId, int orderStatus, int start,
		int end, OrderByComparator<CommerceOrder> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByG_C_O;
			finderArgs = new Object[] {groupId, commerceAccountId, orderStatus};
		}
		else {
			finderPath = _finderPathWithPaginationFindByG_C_O;
			finderArgs = new Object[] {
				groupId, commerceAccountId, orderStatus, start, end,
				orderByComparator
			};
		}

		List<CommerceOrder> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceOrder>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceOrder commerceOrder : list) {
					if ((groupId != commerceOrder.getGroupId()) ||
						(commerceAccountId !=
							commerceOrder.getCommerceAccountId()) ||
						(orderStatus != commerceOrder.getOrderStatus())) {

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

			query.append(_SQL_SELECT_COMMERCEORDER_WHERE);

			query.append(_FINDER_COLUMN_G_C_O_GROUPID_2);

			query.append(_FINDER_COLUMN_G_C_O_COMMERCEACCOUNTID_2);

			query.append(_FINDER_COLUMN_G_C_O_ORDERSTATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceOrderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(commerceAccountId);

				qPos.add(orderStatus);

				if (!pagination) {
					list = (List<CommerceOrder>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceOrder>)QueryUtil.list(
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
	 * Returns the first commerce order in the ordered set where groupId = &#63; and commerceAccountId = &#63; and orderStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder findByG_C_O_First(
			long groupId, long commerceAccountId, int orderStatus,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = fetchByG_C_O_First(
			groupId, commerceAccountId, orderStatus, orderByComparator);

		if (commerceOrder != null) {
			return commerceOrder;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", commerceAccountId=");
		msg.append(commerceAccountId);

		msg.append(", orderStatus=");
		msg.append(orderStatus);

		msg.append("}");

		throw new NoSuchOrderException(msg.toString());
	}

	/**
	 * Returns the first commerce order in the ordered set where groupId = &#63; and commerceAccountId = &#63; and orderStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder fetchByG_C_O_First(
		long groupId, long commerceAccountId, int orderStatus,
		OrderByComparator<CommerceOrder> orderByComparator) {

		List<CommerceOrder> list = findByG_C_O(
			groupId, commerceAccountId, orderStatus, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce order in the ordered set where groupId = &#63; and commerceAccountId = &#63; and orderStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder findByG_C_O_Last(
			long groupId, long commerceAccountId, int orderStatus,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = fetchByG_C_O_Last(
			groupId, commerceAccountId, orderStatus, orderByComparator);

		if (commerceOrder != null) {
			return commerceOrder;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", commerceAccountId=");
		msg.append(commerceAccountId);

		msg.append(", orderStatus=");
		msg.append(orderStatus);

		msg.append("}");

		throw new NoSuchOrderException(msg.toString());
	}

	/**
	 * Returns the last commerce order in the ordered set where groupId = &#63; and commerceAccountId = &#63; and orderStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder fetchByG_C_O_Last(
		long groupId, long commerceAccountId, int orderStatus,
		OrderByComparator<CommerceOrder> orderByComparator) {

		int count = countByG_C_O(groupId, commerceAccountId, orderStatus);

		if (count == 0) {
			return null;
		}

		List<CommerceOrder> list = findByG_C_O(
			groupId, commerceAccountId, orderStatus, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce orders before and after the current commerce order in the ordered set where groupId = &#63; and commerceAccountId = &#63; and orderStatus = &#63;.
	 *
	 * @param commerceOrderId the primary key of the current commerce order
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	@Override
	public CommerceOrder[] findByG_C_O_PrevAndNext(
			long commerceOrderId, long groupId, long commerceAccountId,
			int orderStatus, OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = findByPrimaryKey(commerceOrderId);

		Session session = null;

		try {
			session = openSession();

			CommerceOrder[] array = new CommerceOrderImpl[3];

			array[0] = getByG_C_O_PrevAndNext(
				session, commerceOrder, groupId, commerceAccountId, orderStatus,
				orderByComparator, true);

			array[1] = commerceOrder;

			array[2] = getByG_C_O_PrevAndNext(
				session, commerceOrder, groupId, commerceAccountId, orderStatus,
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

	protected CommerceOrder getByG_C_O_PrevAndNext(
		Session session, CommerceOrder commerceOrder, long groupId,
		long commerceAccountId, int orderStatus,
		OrderByComparator<CommerceOrder> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_COMMERCEORDER_WHERE);

		query.append(_FINDER_COLUMN_G_C_O_GROUPID_2);

		query.append(_FINDER_COLUMN_G_C_O_COMMERCEACCOUNTID_2);

		query.append(_FINDER_COLUMN_G_C_O_ORDERSTATUS_2);

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
			query.append(CommerceOrderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(commerceAccountId);

		qPos.add(orderStatus);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceOrder)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceOrder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce orders where groupId = &#63; and commerceAccountId = &#63; and orderStatus = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderStatus the order status
	 */
	@Override
	public void removeByG_C_O(
		long groupId, long commerceAccountId, int orderStatus) {

		for (CommerceOrder commerceOrder :
				findByG_C_O(
					groupId, commerceAccountId, orderStatus, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(commerceOrder);
		}
	}

	/**
	 * Returns the number of commerce orders where groupId = &#63; and commerceAccountId = &#63; and orderStatus = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceAccountId the commerce account ID
	 * @param orderStatus the order status
	 * @return the number of matching commerce orders
	 */
	@Override
	public int countByG_C_O(
		long groupId, long commerceAccountId, int orderStatus) {

		FinderPath finderPath = _finderPathCountByG_C_O;

		Object[] finderArgs = new Object[] {
			groupId, commerceAccountId, orderStatus
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_COMMERCEORDER_WHERE);

			query.append(_FINDER_COLUMN_G_C_O_GROUPID_2);

			query.append(_FINDER_COLUMN_G_C_O_COMMERCEACCOUNTID_2);

			query.append(_FINDER_COLUMN_G_C_O_ORDERSTATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(commerceAccountId);

				qPos.add(orderStatus);

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

	private static final String _FINDER_COLUMN_G_C_O_GROUPID_2 =
		"commerceOrder.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_C_O_COMMERCEACCOUNTID_2 =
		"commerceOrder.commerceAccountId = ? AND ";

	private static final String _FINDER_COLUMN_G_C_O_ORDERSTATUS_2 =
		"commerceOrder.orderStatus = ?";

	private FinderPath _finderPathWithPaginationFindByU_LtC_O;
	private FinderPath _finderPathWithPaginationCountByU_LtC_O;

	/**
	 * Returns all the commerce orders where userId = &#63; and createDate &lt; &#63; and orderStatus = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderStatus the order status
	 * @return the matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByU_LtC_O(
		long userId, Date createDate, int orderStatus) {

		return findByU_LtC_O(
			userId, createDate, orderStatus, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce orders where userId = &#63; and createDate &lt; &#63; and orderStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderStatus the order status
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @return the range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByU_LtC_O(
		long userId, Date createDate, int orderStatus, int start, int end) {

		return findByU_LtC_O(userId, createDate, orderStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce orders where userId = &#63; and createDate &lt; &#63; and orderStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderStatus the order status
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByU_LtC_O(
		long userId, Date createDate, int orderStatus, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return findByU_LtC_O(
			userId, createDate, orderStatus, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the commerce orders where userId = &#63; and createDate &lt; &#63; and orderStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderStatus the order status
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce orders
	 */
	@Override
	public List<CommerceOrder> findByU_LtC_O(
		long userId, Date createDate, int orderStatus, int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByU_LtC_O;
		finderArgs = new Object[] {
			userId, _getTime(createDate), orderStatus, start, end,
			orderByComparator
		};

		List<CommerceOrder> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceOrder>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceOrder commerceOrder : list) {
					if ((userId != commerceOrder.getUserId()) ||
						(createDate.getTime() <=
							commerceOrder.getCreateDate().getTime()) ||
						(orderStatus != commerceOrder.getOrderStatus())) {

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

			query.append(_SQL_SELECT_COMMERCEORDER_WHERE);

			query.append(_FINDER_COLUMN_U_LTC_O_USERID_2);

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_U_LTC_O_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_U_LTC_O_CREATEDATE_2);
			}

			query.append(_FINDER_COLUMN_U_LTC_O_ORDERSTATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceOrderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
				}

				qPos.add(orderStatus);

				if (!pagination) {
					list = (List<CommerceOrder>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceOrder>)QueryUtil.list(
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
	 * Returns the first commerce order in the ordered set where userId = &#63; and createDate &lt; &#63; and orderStatus = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder findByU_LtC_O_First(
			long userId, Date createDate, int orderStatus,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = fetchByU_LtC_O_First(
			userId, createDate, orderStatus, orderByComparator);

		if (commerceOrder != null) {
			return commerceOrder;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", createDate=");
		msg.append(createDate);

		msg.append(", orderStatus=");
		msg.append(orderStatus);

		msg.append("}");

		throw new NoSuchOrderException(msg.toString());
	}

	/**
	 * Returns the first commerce order in the ordered set where userId = &#63; and createDate &lt; &#63; and orderStatus = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder fetchByU_LtC_O_First(
		long userId, Date createDate, int orderStatus,
		OrderByComparator<CommerceOrder> orderByComparator) {

		List<CommerceOrder> list = findByU_LtC_O(
			userId, createDate, orderStatus, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce order in the ordered set where userId = &#63; and createDate &lt; &#63; and orderStatus = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder findByU_LtC_O_Last(
			long userId, Date createDate, int orderStatus,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = fetchByU_LtC_O_Last(
			userId, createDate, orderStatus, orderByComparator);

		if (commerceOrder != null) {
			return commerceOrder;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", createDate=");
		msg.append(createDate);

		msg.append(", orderStatus=");
		msg.append(orderStatus);

		msg.append("}");

		throw new NoSuchOrderException(msg.toString());
	}

	/**
	 * Returns the last commerce order in the ordered set where userId = &#63; and createDate &lt; &#63; and orderStatus = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder fetchByU_LtC_O_Last(
		long userId, Date createDate, int orderStatus,
		OrderByComparator<CommerceOrder> orderByComparator) {

		int count = countByU_LtC_O(userId, createDate, orderStatus);

		if (count == 0) {
			return null;
		}

		List<CommerceOrder> list = findByU_LtC_O(
			userId, createDate, orderStatus, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce orders before and after the current commerce order in the ordered set where userId = &#63; and createDate &lt; &#63; and orderStatus = &#63;.
	 *
	 * @param commerceOrderId the primary key of the current commerce order
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderStatus the order status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	@Override
	public CommerceOrder[] findByU_LtC_O_PrevAndNext(
			long commerceOrderId, long userId, Date createDate, int orderStatus,
			OrderByComparator<CommerceOrder> orderByComparator)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = findByPrimaryKey(commerceOrderId);

		Session session = null;

		try {
			session = openSession();

			CommerceOrder[] array = new CommerceOrderImpl[3];

			array[0] = getByU_LtC_O_PrevAndNext(
				session, commerceOrder, userId, createDate, orderStatus,
				orderByComparator, true);

			array[1] = commerceOrder;

			array[2] = getByU_LtC_O_PrevAndNext(
				session, commerceOrder, userId, createDate, orderStatus,
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

	protected CommerceOrder getByU_LtC_O_PrevAndNext(
		Session session, CommerceOrder commerceOrder, long userId,
		Date createDate, int orderStatus,
		OrderByComparator<CommerceOrder> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_COMMERCEORDER_WHERE);

		query.append(_FINDER_COLUMN_U_LTC_O_USERID_2);

		boolean bindCreateDate = false;

		if (createDate == null) {
			query.append(_FINDER_COLUMN_U_LTC_O_CREATEDATE_1);
		}
		else {
			bindCreateDate = true;

			query.append(_FINDER_COLUMN_U_LTC_O_CREATEDATE_2);
		}

		query.append(_FINDER_COLUMN_U_LTC_O_ORDERSTATUS_2);

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
			query.append(CommerceOrderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (bindCreateDate) {
			qPos.add(new Timestamp(createDate.getTime()));
		}

		qPos.add(orderStatus);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceOrder)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceOrder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce orders where userId = &#63; and createDate &lt; &#63; and orderStatus = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderStatus the order status
	 */
	@Override
	public void removeByU_LtC_O(long userId, Date createDate, int orderStatus) {
		for (CommerceOrder commerceOrder :
				findByU_LtC_O(
					userId, createDate, orderStatus, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(commerceOrder);
		}
	}

	/**
	 * Returns the number of commerce orders where userId = &#63; and createDate &lt; &#63; and orderStatus = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param orderStatus the order status
	 * @return the number of matching commerce orders
	 */
	@Override
	public int countByU_LtC_O(long userId, Date createDate, int orderStatus) {
		FinderPath finderPath = _finderPathWithPaginationCountByU_LtC_O;

		Object[] finderArgs = new Object[] {
			userId, _getTime(createDate), orderStatus
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_COMMERCEORDER_WHERE);

			query.append(_FINDER_COLUMN_U_LTC_O_USERID_2);

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_U_LTC_O_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_U_LTC_O_CREATEDATE_2);
			}

			query.append(_FINDER_COLUMN_U_LTC_O_ORDERSTATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
				}

				qPos.add(orderStatus);

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

	private static final String _FINDER_COLUMN_U_LTC_O_USERID_2 =
		"commerceOrder.userId = ? AND ";

	private static final String _FINDER_COLUMN_U_LTC_O_CREATEDATE_1 =
		"commerceOrder.createDate IS NULL AND ";

	private static final String _FINDER_COLUMN_U_LTC_O_CREATEDATE_2 =
		"commerceOrder.createDate < ? AND ";

	private static final String _FINDER_COLUMN_U_LTC_O_ORDERSTATUS_2 =
		"commerceOrder.orderStatus = ?";

	private FinderPath _finderPathFetchByC_ERC;
	private FinderPath _finderPathCountByC_ERC;

	/**
	 * Returns the commerce order where companyId = &#63; and externalReferenceCode = &#63; or throws a <code>NoSuchOrderException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce order
	 * @throws NoSuchOrderException if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder findByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = fetchByC_ERC(
			companyId, externalReferenceCode);

		if (commerceOrder == null) {
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

			throw new NoSuchOrderException(msg.toString());
		}

		return commerceOrder;
	}

	/**
	 * Returns the commerce order where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder fetchByC_ERC(
		long companyId, String externalReferenceCode) {

		return fetchByC_ERC(companyId, externalReferenceCode, true);
	}

	/**
	 * Returns the commerce order where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public CommerceOrder fetchByC_ERC(
		long companyId, String externalReferenceCode,
		boolean retrieveFromCache) {

		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		Object[] finderArgs = new Object[] {companyId, externalReferenceCode};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_ERC, finderArgs, this);
		}

		if (result instanceof CommerceOrder) {
			CommerceOrder commerceOrder = (CommerceOrder)result;

			if ((companyId != commerceOrder.getCompanyId()) ||
				!Objects.equals(
					externalReferenceCode,
					commerceOrder.getExternalReferenceCode())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COMMERCEORDER_WHERE);

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

				List<CommerceOrder> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByC_ERC, finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"CommerceOrderPersistenceImpl.fetchByC_ERC(long, String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CommerceOrder commerceOrder = list.get(0);

					result = commerceOrder;

					cacheResult(commerceOrder);
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
			return (CommerceOrder)result;
		}
	}

	/**
	 * Removes the commerce order where companyId = &#63; and externalReferenceCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the commerce order that was removed
	 */
	@Override
	public CommerceOrder removeByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = findByC_ERC(
			companyId, externalReferenceCode);

		return remove(commerceOrder);
	}

	/**
	 * Returns the number of commerce orders where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the number of matching commerce orders
	 */
	@Override
	public int countByC_ERC(long companyId, String externalReferenceCode) {
		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		FinderPath finderPath = _finderPathCountByC_ERC;

		Object[] finderArgs = new Object[] {companyId, externalReferenceCode};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEORDER_WHERE);

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
		"commerceOrder.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2 =
		"commerceOrder.externalReferenceCode = ?";

	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3 =
		"(commerceOrder.externalReferenceCode IS NULL OR commerceOrder.externalReferenceCode = '')";

	public CommerceOrderPersistenceImpl() {
		setModelClass(CommerceOrder.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put(
			"subtotalDiscountPercentageLevel1",
			"subtotalDiscountPercentLevel1");
		dbColumnNames.put(
			"subtotalDiscountPercentageLevel2",
			"subtotalDiscountPercentLevel2");
		dbColumnNames.put(
			"subtotalDiscountPercentageLevel3",
			"subtotalDiscountPercentLevel3");
		dbColumnNames.put(
			"subtotalDiscountPercentageLevel4",
			"subtotalDiscountPercentLevel4");
		dbColumnNames.put(
			"shippingDiscountPercentageLevel1",
			"shippingDiscountPercentLevel1");
		dbColumnNames.put(
			"shippingDiscountPercentageLevel2",
			"shippingDiscountPercentLevel2");
		dbColumnNames.put(
			"shippingDiscountPercentageLevel3",
			"shippingDiscountPercentLevel3");
		dbColumnNames.put(
			"shippingDiscountPercentageLevel4",
			"shippingDiscountPercentLevel4");

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
	 * Caches the commerce order in the entity cache if it is enabled.
	 *
	 * @param commerceOrder the commerce order
	 */
	@Override
	public void cacheResult(CommerceOrder commerceOrder) {
		entityCache.putResult(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderImpl.class, commerceOrder.getPrimaryKey(),
			commerceOrder);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {commerceOrder.getUuid(), commerceOrder.getGroupId()},
			commerceOrder);

		finderCache.putResult(
			_finderPathFetchByC_ERC,
			new Object[] {
				commerceOrder.getCompanyId(),
				commerceOrder.getExternalReferenceCode()
			},
			commerceOrder);

		commerceOrder.resetOriginalValues();
	}

	/**
	 * Caches the commerce orders in the entity cache if it is enabled.
	 *
	 * @param commerceOrders the commerce orders
	 */
	@Override
	public void cacheResult(List<CommerceOrder> commerceOrders) {
		for (CommerceOrder commerceOrder : commerceOrders) {
			if (entityCache.getResult(
					CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
					CommerceOrderImpl.class, commerceOrder.getPrimaryKey()) ==
						null) {

				cacheResult(commerceOrder);
			}
			else {
				commerceOrder.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce orders.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceOrderImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce order.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceOrder commerceOrder) {
		entityCache.removeResult(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderImpl.class, commerceOrder.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CommerceOrderModelImpl)commerceOrder, true);
	}

	@Override
	public void clearCache(List<CommerceOrder> commerceOrders) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceOrder commerceOrder : commerceOrders) {
			entityCache.removeResult(
				CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
				CommerceOrderImpl.class, commerceOrder.getPrimaryKey());

			clearUniqueFindersCache(
				(CommerceOrderModelImpl)commerceOrder, true);
		}
	}

	protected void cacheUniqueFindersCache(
		CommerceOrderModelImpl commerceOrderModelImpl) {

		Object[] args = new Object[] {
			commerceOrderModelImpl.getUuid(),
			commerceOrderModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, commerceOrderModelImpl, false);

		args = new Object[] {
			commerceOrderModelImpl.getCompanyId(),
			commerceOrderModelImpl.getExternalReferenceCode()
		};

		finderCache.putResult(
			_finderPathCountByC_ERC, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_ERC, args, commerceOrderModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CommerceOrderModelImpl commerceOrderModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				commerceOrderModelImpl.getUuid(),
				commerceOrderModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((commerceOrderModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				commerceOrderModelImpl.getOriginalUuid(),
				commerceOrderModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
				commerceOrderModelImpl.getCompanyId(),
				commerceOrderModelImpl.getExternalReferenceCode()
			};

			finderCache.removeResult(_finderPathCountByC_ERC, args);
			finderCache.removeResult(_finderPathFetchByC_ERC, args);
		}

		if ((commerceOrderModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_ERC.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				commerceOrderModelImpl.getOriginalCompanyId(),
				commerceOrderModelImpl.getOriginalExternalReferenceCode()
			};

			finderCache.removeResult(_finderPathCountByC_ERC, args);
			finderCache.removeResult(_finderPathFetchByC_ERC, args);
		}
	}

	/**
	 * Creates a new commerce order with the primary key. Does not add the commerce order to the database.
	 *
	 * @param commerceOrderId the primary key for the new commerce order
	 * @return the new commerce order
	 */
	@Override
	public CommerceOrder create(long commerceOrderId) {
		CommerceOrder commerceOrder = new CommerceOrderImpl();

		commerceOrder.setNew(true);
		commerceOrder.setPrimaryKey(commerceOrderId);

		String uuid = PortalUUIDUtil.generate();

		commerceOrder.setUuid(uuid);

		commerceOrder.setCompanyId(CompanyThreadLocal.getCompanyId());

		return commerceOrder;
	}

	/**
	 * Removes the commerce order with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceOrderId the primary key of the commerce order
	 * @return the commerce order that was removed
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	@Override
	public CommerceOrder remove(long commerceOrderId)
		throws NoSuchOrderException {

		return remove((Serializable)commerceOrderId);
	}

	/**
	 * Removes the commerce order with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce order
	 * @return the commerce order that was removed
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	@Override
	public CommerceOrder remove(Serializable primaryKey)
		throws NoSuchOrderException {

		Session session = null;

		try {
			session = openSession();

			CommerceOrder commerceOrder = (CommerceOrder)session.get(
				CommerceOrderImpl.class, primaryKey);

			if (commerceOrder == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOrderException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commerceOrder);
		}
		catch (NoSuchOrderException nsee) {
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
	protected CommerceOrder removeImpl(CommerceOrder commerceOrder) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceOrder)) {
				commerceOrder = (CommerceOrder)session.get(
					CommerceOrderImpl.class, commerceOrder.getPrimaryKeyObj());
			}

			if (commerceOrder != null) {
				session.delete(commerceOrder);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceOrder != null) {
			clearCache(commerceOrder);
		}

		return commerceOrder;
	}

	@Override
	public CommerceOrder updateImpl(CommerceOrder commerceOrder) {
		boolean isNew = commerceOrder.isNew();

		if (!(commerceOrder instanceof CommerceOrderModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceOrder.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					commerceOrder);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceOrder proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceOrder implementation " +
					commerceOrder.getClass());
		}

		CommerceOrderModelImpl commerceOrderModelImpl =
			(CommerceOrderModelImpl)commerceOrder;

		if (Validator.isNull(commerceOrder.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			commerceOrder.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceOrder.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceOrder.setCreateDate(now);
			}
			else {
				commerceOrder.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!commerceOrderModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceOrder.setModifiedDate(now);
			}
			else {
				commerceOrder.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceOrder.isNew()) {
				session.save(commerceOrder);

				commerceOrder.setNew(false);
			}
			else {
				commerceOrder = (CommerceOrder)session.merge(commerceOrder);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceOrderModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {commerceOrderModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				commerceOrderModelImpl.getUuid(),
				commerceOrderModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {commerceOrderModelImpl.getGroupId()};

			finderCache.removeResult(_finderPathCountByGroupId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByGroupId, args);

			args = new Object[] {commerceOrderModelImpl.getUserId()};

			finderCache.removeResult(_finderPathCountByUserId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUserId, args);

			args = new Object[] {commerceOrderModelImpl.getBillingAddressId()};

			finderCache.removeResult(_finderPathCountByBillingAddressId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByBillingAddressId, args);

			args = new Object[] {commerceOrderModelImpl.getShippingAddressId()};

			finderCache.removeResult(_finderPathCountByShippingAddressId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByShippingAddressId, args);

			args = new Object[] {
				commerceOrderModelImpl.getGroupId(),
				commerceOrderModelImpl.getCommerceAccountId()
			};

			finderCache.removeResult(_finderPathCountByG_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByG_C, args);

			args = new Object[] {
				commerceOrderModelImpl.getGroupId(),
				commerceOrderModelImpl.getUserId(),
				commerceOrderModelImpl.getOrderStatus()
			};

			finderCache.removeResult(_finderPathCountByG_U_O, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByG_U_O, args);

			args = new Object[] {
				commerceOrderModelImpl.getGroupId(),
				commerceOrderModelImpl.getCommerceAccountId(),
				commerceOrderModelImpl.getOrderStatus()
			};

			finderCache.removeResult(_finderPathCountByG_C_O, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByG_C_O, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((commerceOrderModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceOrderModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {commerceOrderModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((commerceOrderModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceOrderModelImpl.getOriginalUuid(),
					commerceOrderModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					commerceOrderModelImpl.getUuid(),
					commerceOrderModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((commerceOrderModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByGroupId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceOrderModelImpl.getOriginalGroupId()
				};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);

				args = new Object[] {commerceOrderModelImpl.getGroupId()};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);
			}

			if ((commerceOrderModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUserId.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceOrderModelImpl.getOriginalUserId()
				};

				finderCache.removeResult(_finderPathCountByUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUserId, args);

				args = new Object[] {commerceOrderModelImpl.getUserId()};

				finderCache.removeResult(_finderPathCountByUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUserId, args);
			}

			if ((commerceOrderModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByBillingAddressId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceOrderModelImpl.getOriginalBillingAddressId()
				};

				finderCache.removeResult(
					_finderPathCountByBillingAddressId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByBillingAddressId, args);

				args = new Object[] {
					commerceOrderModelImpl.getBillingAddressId()
				};

				finderCache.removeResult(
					_finderPathCountByBillingAddressId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByBillingAddressId, args);
			}

			if ((commerceOrderModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByShippingAddressId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceOrderModelImpl.getOriginalShippingAddressId()
				};

				finderCache.removeResult(
					_finderPathCountByShippingAddressId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByShippingAddressId, args);

				args = new Object[] {
					commerceOrderModelImpl.getShippingAddressId()
				};

				finderCache.removeResult(
					_finderPathCountByShippingAddressId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByShippingAddressId, args);
			}

			if ((commerceOrderModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByG_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceOrderModelImpl.getOriginalGroupId(),
					commerceOrderModelImpl.getOriginalCommerceAccountId()
				};

				finderCache.removeResult(_finderPathCountByG_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_C, args);

				args = new Object[] {
					commerceOrderModelImpl.getGroupId(),
					commerceOrderModelImpl.getCommerceAccountId()
				};

				finderCache.removeResult(_finderPathCountByG_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_C, args);
			}

			if ((commerceOrderModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByG_U_O.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceOrderModelImpl.getOriginalGroupId(),
					commerceOrderModelImpl.getOriginalUserId(),
					commerceOrderModelImpl.getOriginalOrderStatus()
				};

				finderCache.removeResult(_finderPathCountByG_U_O, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_U_O, args);

				args = new Object[] {
					commerceOrderModelImpl.getGroupId(),
					commerceOrderModelImpl.getUserId(),
					commerceOrderModelImpl.getOrderStatus()
				};

				finderCache.removeResult(_finderPathCountByG_U_O, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_U_O, args);
			}

			if ((commerceOrderModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByG_C_O.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceOrderModelImpl.getOriginalGroupId(),
					commerceOrderModelImpl.getOriginalCommerceAccountId(),
					commerceOrderModelImpl.getOriginalOrderStatus()
				};

				finderCache.removeResult(_finderPathCountByG_C_O, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_C_O, args);

				args = new Object[] {
					commerceOrderModelImpl.getGroupId(),
					commerceOrderModelImpl.getCommerceAccountId(),
					commerceOrderModelImpl.getOrderStatus()
				};

				finderCache.removeResult(_finderPathCountByG_C_O, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_C_O, args);
			}
		}

		entityCache.putResult(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderImpl.class, commerceOrder.getPrimaryKey(),
			commerceOrder, false);

		clearUniqueFindersCache(commerceOrderModelImpl, false);
		cacheUniqueFindersCache(commerceOrderModelImpl);

		commerceOrder.resetOriginalValues();

		return commerceOrder;
	}

	/**
	 * Returns the commerce order with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce order
	 * @return the commerce order
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	@Override
	public CommerceOrder findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOrderException {

		CommerceOrder commerceOrder = fetchByPrimaryKey(primaryKey);

		if (commerceOrder == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOrderException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commerceOrder;
	}

	/**
	 * Returns the commerce order with the primary key or throws a <code>NoSuchOrderException</code> if it could not be found.
	 *
	 * @param commerceOrderId the primary key of the commerce order
	 * @return the commerce order
	 * @throws NoSuchOrderException if a commerce order with the primary key could not be found
	 */
	@Override
	public CommerceOrder findByPrimaryKey(long commerceOrderId)
		throws NoSuchOrderException {

		return findByPrimaryKey((Serializable)commerceOrderId);
	}

	/**
	 * Returns the commerce order with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce order
	 * @return the commerce order, or <code>null</code> if a commerce order with the primary key could not be found
	 */
	@Override
	public CommerceOrder fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceOrder commerceOrder = (CommerceOrder)serializable;

		if (commerceOrder == null) {
			Session session = null;

			try {
				session = openSession();

				commerceOrder = (CommerceOrder)session.get(
					CommerceOrderImpl.class, primaryKey);

				if (commerceOrder != null) {
					cacheResult(commerceOrder);
				}
				else {
					entityCache.putResult(
						CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
						CommerceOrderImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
					CommerceOrderImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceOrder;
	}

	/**
	 * Returns the commerce order with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceOrderId the primary key of the commerce order
	 * @return the commerce order, or <code>null</code> if a commerce order with the primary key could not be found
	 */
	@Override
	public CommerceOrder fetchByPrimaryKey(long commerceOrderId) {
		return fetchByPrimaryKey((Serializable)commerceOrderId);
	}

	@Override
	public Map<Serializable, CommerceOrder> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceOrder> map =
			new HashMap<Serializable, CommerceOrder>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceOrder commerceOrder = fetchByPrimaryKey(primaryKey);

			if (commerceOrder != null) {
				map.put(primaryKey, commerceOrder);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
				CommerceOrderImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceOrder)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_COMMERCEORDER_WHERE_PKS_IN);

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

			for (CommerceOrder commerceOrder : (List<CommerceOrder>)q.list()) {
				map.put(commerceOrder.getPrimaryKeyObj(), commerceOrder);

				cacheResult(commerceOrder);

				uncachedPrimaryKeys.remove(commerceOrder.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
					CommerceOrderImpl.class, primaryKey, nullModel);
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
	 * Returns all the commerce orders.
	 *
	 * @return the commerce orders
	 */
	@Override
	public List<CommerceOrder> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce orders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @return the range of commerce orders
	 */
	@Override
	public List<CommerceOrder> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce orders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce orders
	 */
	@Override
	public List<CommerceOrder> findAll(
		int start, int end,
		OrderByComparator<CommerceOrder> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce orders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce orders
	 */
	@Override
	public List<CommerceOrder> findAll(
		int start, int end, OrderByComparator<CommerceOrder> orderByComparator,
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

		List<CommerceOrder> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceOrder>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEORDER);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEORDER;

				if (pagination) {
					sql = sql.concat(CommerceOrderModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceOrder>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceOrder>)QueryUtil.list(
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
	 * Removes all the commerce orders from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceOrder commerceOrder : findAll()) {
			remove(commerceOrder);
		}
	}

	/**
	 * Returns the number of commerce orders.
	 *
	 * @return the number of commerce orders
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCEORDER);

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
		return CommerceOrderModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce order persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);

		_finderPathCountAll = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] {String.class.getName()},
			CommerceOrderModelImpl.UUID_COLUMN_BITMASK |
			CommerceOrderModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			CommerceOrderModelImpl.UUID_COLUMN_BITMASK |
			CommerceOrderModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			CommerceOrderModelImpl.UUID_COLUMN_BITMASK |
			CommerceOrderModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceOrderModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupId", new String[] {Long.class.getName()},
			CommerceOrderModelImpl.GROUPID_COLUMN_BITMASK |
			CommerceOrderModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByGroupId = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByUserId = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUserId = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUserId", new String[] {Long.class.getName()},
			CommerceOrderModelImpl.USERID_COLUMN_BITMASK |
			CommerceOrderModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByUserId = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByBillingAddressId = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByBillingAddressId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByBillingAddressId = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByBillingAddressId", new String[] {Long.class.getName()},
			CommerceOrderModelImpl.BILLINGADDRESSID_COLUMN_BITMASK |
			CommerceOrderModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByBillingAddressId = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByBillingAddressId", new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByShippingAddressId = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByShippingAddressId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByShippingAddressId = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByShippingAddressId", new String[] {Long.class.getName()},
			CommerceOrderModelImpl.SHIPPINGADDRESSID_COLUMN_BITMASK |
			CommerceOrderModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByShippingAddressId = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByShippingAddressId", new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByG_C = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByG_C = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByG_C",
			new String[] {Long.class.getName(), Long.class.getName()},
			CommerceOrderModelImpl.GROUPID_COLUMN_BITMASK |
			CommerceOrderModelImpl.COMMERCEACCOUNTID_COLUMN_BITMASK |
			CommerceOrderModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByG_C = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_C",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByG_U_O = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_U_O",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByG_U_O = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByG_U_O",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			CommerceOrderModelImpl.GROUPID_COLUMN_BITMASK |
			CommerceOrderModelImpl.USERID_COLUMN_BITMASK |
			CommerceOrderModelImpl.ORDERSTATUS_COLUMN_BITMASK |
			CommerceOrderModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByG_U_O = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_U_O",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

		_finderPathWithPaginationFindByG_C_O = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_C_O",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByG_C_O = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByG_C_O",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			CommerceOrderModelImpl.GROUPID_COLUMN_BITMASK |
			CommerceOrderModelImpl.COMMERCEACCOUNTID_COLUMN_BITMASK |
			CommerceOrderModelImpl.ORDERSTATUS_COLUMN_BITMASK |
			CommerceOrderModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByG_C_O = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_C_O",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

		_finderPathWithPaginationFindByU_LtC_O = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_LtC_O",
			new String[] {
				Long.class.getName(), Date.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByU_LtC_O = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByU_LtC_O",
			new String[] {
				Long.class.getName(), Date.class.getName(),
				Integer.class.getName()
			});

		_finderPathFetchByC_ERC = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByC_ERC",
			new String[] {Long.class.getName(), String.class.getName()},
			CommerceOrderModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceOrderModelImpl.EXTERNALREFERENCECODE_COLUMN_BITMASK);

		_finderPathCountByC_ERC = new FinderPath(
			CommerceOrderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_ERC",
			new String[] {Long.class.getName(), String.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(CommerceOrderImpl.class.getName());
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

	private static final String _SQL_SELECT_COMMERCEORDER =
		"SELECT commerceOrder FROM CommerceOrder commerceOrder";

	private static final String _SQL_SELECT_COMMERCEORDER_WHERE_PKS_IN =
		"SELECT commerceOrder FROM CommerceOrder commerceOrder WHERE commerceOrderId IN (";

	private static final String _SQL_SELECT_COMMERCEORDER_WHERE =
		"SELECT commerceOrder FROM CommerceOrder commerceOrder WHERE ";

	private static final String _SQL_COUNT_COMMERCEORDER =
		"SELECT COUNT(commerceOrder) FROM CommerceOrder commerceOrder";

	private static final String _SQL_COUNT_COMMERCEORDER_WHERE =
		"SELECT COUNT(commerceOrder) FROM CommerceOrder commerceOrder WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceOrder.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommerceOrder exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommerceOrder exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceOrderPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {
			"uuid", "subtotalDiscountPercentageLevel1",
			"subtotalDiscountPercentageLevel2",
			"subtotalDiscountPercentageLevel3",
			"subtotalDiscountPercentageLevel4",
			"shippingDiscountPercentageLevel1",
			"shippingDiscountPercentageLevel2",
			"shippingDiscountPercentageLevel3",
			"shippingDiscountPercentageLevel4"
		});

}