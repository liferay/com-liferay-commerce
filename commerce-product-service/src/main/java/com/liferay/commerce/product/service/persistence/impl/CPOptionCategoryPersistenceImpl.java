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

import com.liferay.commerce.product.exception.NoSuchCPOptionCategoryException;
import com.liferay.commerce.product.model.CPOptionCategory;
import com.liferay.commerce.product.model.impl.CPOptionCategoryImpl;
import com.liferay.commerce.product.model.impl.CPOptionCategoryModelImpl;
import com.liferay.commerce.product.service.persistence.CPOptionCategoryPersistence;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
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
 * The persistence implementation for the cp option category service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @generated
 */
@ProviderType
public class CPOptionCategoryPersistenceImpl
	extends BasePersistenceImpl<CPOptionCategory>
	implements CPOptionCategoryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CPOptionCategoryUtil</code> to access the cp option category persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CPOptionCategoryImpl.class.getName();

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
	 * Returns all the cp option categories where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cp option categories
	 */
	@Override
	public List<CPOptionCategory> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp option categories where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp option categories
	 * @param end the upper bound of the range of cp option categories (not inclusive)
	 * @return the range of matching cp option categories
	 */
	@Override
	public List<CPOptionCategory> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp option categories where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp option categories
	 * @param end the upper bound of the range of cp option categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp option categories
	 */
	@Override
	public List<CPOptionCategory> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPOptionCategory> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp option categories where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp option categories
	 * @param end the upper bound of the range of cp option categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp option categories
	 */
	@Override
	public List<CPOptionCategory> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPOptionCategory> orderByComparator,
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

		List<CPOptionCategory> list = null;

		if (retrieveFromCache) {
			list = (List<CPOptionCategory>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPOptionCategory cpOptionCategory : list) {
					if (!uuid.equals(cpOptionCategory.getUuid())) {
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

			query.append(_SQL_SELECT_CPOPTIONCATEGORY_WHERE);

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
				query.append(CPOptionCategoryModelImpl.ORDER_BY_JPQL);
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
					list = (List<CPOptionCategory>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPOptionCategory>)QueryUtil.list(
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
	 * Returns the first cp option category in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp option category
	 * @throws NoSuchCPOptionCategoryException if a matching cp option category could not be found
	 */
	@Override
	public CPOptionCategory findByUuid_First(
			String uuid, OrderByComparator<CPOptionCategory> orderByComparator)
		throws NoSuchCPOptionCategoryException {

		CPOptionCategory cpOptionCategory = fetchByUuid_First(
			uuid, orderByComparator);

		if (cpOptionCategory != null) {
			return cpOptionCategory;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCPOptionCategoryException(msg.toString());
	}

	/**
	 * Returns the first cp option category in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp option category, or <code>null</code> if a matching cp option category could not be found
	 */
	@Override
	public CPOptionCategory fetchByUuid_First(
		String uuid, OrderByComparator<CPOptionCategory> orderByComparator) {

		List<CPOptionCategory> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp option category in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp option category
	 * @throws NoSuchCPOptionCategoryException if a matching cp option category could not be found
	 */
	@Override
	public CPOptionCategory findByUuid_Last(
			String uuid, OrderByComparator<CPOptionCategory> orderByComparator)
		throws NoSuchCPOptionCategoryException {

		CPOptionCategory cpOptionCategory = fetchByUuid_Last(
			uuid, orderByComparator);

		if (cpOptionCategory != null) {
			return cpOptionCategory;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCPOptionCategoryException(msg.toString());
	}

	/**
	 * Returns the last cp option category in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp option category, or <code>null</code> if a matching cp option category could not be found
	 */
	@Override
	public CPOptionCategory fetchByUuid_Last(
		String uuid, OrderByComparator<CPOptionCategory> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CPOptionCategory> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp option categories before and after the current cp option category in the ordered set where uuid = &#63;.
	 *
	 * @param CPOptionCategoryId the primary key of the current cp option category
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp option category
	 * @throws NoSuchCPOptionCategoryException if a cp option category with the primary key could not be found
	 */
	@Override
	public CPOptionCategory[] findByUuid_PrevAndNext(
			long CPOptionCategoryId, String uuid,
			OrderByComparator<CPOptionCategory> orderByComparator)
		throws NoSuchCPOptionCategoryException {

		uuid = Objects.toString(uuid, "");

		CPOptionCategory cpOptionCategory = findByPrimaryKey(
			CPOptionCategoryId);

		Session session = null;

		try {
			session = openSession();

			CPOptionCategory[] array = new CPOptionCategoryImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, cpOptionCategory, uuid, orderByComparator, true);

			array[1] = cpOptionCategory;

			array[2] = getByUuid_PrevAndNext(
				session, cpOptionCategory, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPOptionCategory getByUuid_PrevAndNext(
		Session session, CPOptionCategory cpOptionCategory, String uuid,
		OrderByComparator<CPOptionCategory> orderByComparator,
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

		query.append(_SQL_SELECT_CPOPTIONCATEGORY_WHERE);

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
			query.append(CPOptionCategoryModelImpl.ORDER_BY_JPQL);
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
						cpOptionCategory)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPOptionCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the cp option categories that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cp option categories that the user has permission to view
	 */
	@Override
	public List<CPOptionCategory> filterFindByUuid(String uuid) {
		return filterFindByUuid(
			uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp option categories that the user has permission to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp option categories
	 * @param end the upper bound of the range of cp option categories (not inclusive)
	 * @return the range of matching cp option categories that the user has permission to view
	 */
	@Override
	public List<CPOptionCategory> filterFindByUuid(
		String uuid, int start, int end) {

		return filterFindByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp option categories that the user has permissions to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp option categories
	 * @param end the upper bound of the range of cp option categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp option categories that the user has permission to view
	 */
	@Override
	public List<CPOptionCategory> filterFindByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPOptionCategory> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUuid(uuid, start, end, orderByComparator);
		}

		uuid = Objects.toString(uuid, "");

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				3 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CPOPTIONCATEGORY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_CPOPTIONCATEGORY_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2_SQL);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_CPOPTIONCATEGORY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CPOptionCategoryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CPOptionCategoryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPOptionCategory.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, CPOptionCategoryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, CPOptionCategoryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindUuid) {
				qPos.add(uuid);
			}

			return (List<CPOptionCategory>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the cp option categories before and after the current cp option category in the ordered set of cp option categories that the user has permission to view where uuid = &#63;.
	 *
	 * @param CPOptionCategoryId the primary key of the current cp option category
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp option category
	 * @throws NoSuchCPOptionCategoryException if a cp option category with the primary key could not be found
	 */
	@Override
	public CPOptionCategory[] filterFindByUuid_PrevAndNext(
			long CPOptionCategoryId, String uuid,
			OrderByComparator<CPOptionCategory> orderByComparator)
		throws NoSuchCPOptionCategoryException {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUuid_PrevAndNext(
				CPOptionCategoryId, uuid, orderByComparator);
		}

		uuid = Objects.toString(uuid, "");

		CPOptionCategory cpOptionCategory = findByPrimaryKey(
			CPOptionCategoryId);

		Session session = null;

		try {
			session = openSession();

			CPOptionCategory[] array = new CPOptionCategoryImpl[3];

			array[0] = filterGetByUuid_PrevAndNext(
				session, cpOptionCategory, uuid, orderByComparator, true);

			array[1] = cpOptionCategory;

			array[2] = filterGetByUuid_PrevAndNext(
				session, cpOptionCategory, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPOptionCategory filterGetByUuid_PrevAndNext(
		Session session, CPOptionCategory cpOptionCategory, String uuid,
		OrderByComparator<CPOptionCategory> orderByComparator,
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

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CPOPTIONCATEGORY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_CPOPTIONCATEGORY_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2_SQL);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_CPOPTIONCATEGORY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CPOptionCategoryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CPOptionCategoryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPOptionCategory.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CPOptionCategoryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CPOptionCategoryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpOptionCategory)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPOptionCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp option categories where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CPOptionCategory cpOptionCategory :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cpOptionCategory);
		}
	}

	/**
	 * Returns the number of cp option categories where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cp option categories
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPOPTIONCATEGORY_WHERE);

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

	/**
	 * Returns the number of cp option categories that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cp option categories that the user has permission to view
	 */
	@Override
	public int filterCountByUuid(String uuid) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByUuid(uuid);
		}

		uuid = Objects.toString(uuid, "");

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_CPOPTIONCATEGORY_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2_SQL);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPOptionCategory.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindUuid) {
				qPos.add(uuid);
			}

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"cpOptionCategory.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(cpOptionCategory.uuid IS NULL OR cpOptionCategory.uuid = '')";

	private static final String _FINDER_COLUMN_UUID_UUID_2_SQL =
		"cpOptionCategory.uuid_ = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3_SQL =
		"(cpOptionCategory.uuid_ IS NULL OR cpOptionCategory.uuid_ = '')";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the cp option categories where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching cp option categories
	 */
	@Override
	public List<CPOptionCategory> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp option categories where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp option categories
	 * @param end the upper bound of the range of cp option categories (not inclusive)
	 * @return the range of matching cp option categories
	 */
	@Override
	public List<CPOptionCategory> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp option categories where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp option categories
	 * @param end the upper bound of the range of cp option categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp option categories
	 */
	@Override
	public List<CPOptionCategory> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPOptionCategory> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp option categories where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp option categories
	 * @param end the upper bound of the range of cp option categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp option categories
	 */
	@Override
	public List<CPOptionCategory> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPOptionCategory> orderByComparator,
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

		List<CPOptionCategory> list = null;

		if (retrieveFromCache) {
			list = (List<CPOptionCategory>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPOptionCategory cpOptionCategory : list) {
					if (!uuid.equals(cpOptionCategory.getUuid()) ||
						(companyId != cpOptionCategory.getCompanyId())) {

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

			query.append(_SQL_SELECT_CPOPTIONCATEGORY_WHERE);

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
				query.append(CPOptionCategoryModelImpl.ORDER_BY_JPQL);
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
					list = (List<CPOptionCategory>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPOptionCategory>)QueryUtil.list(
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
	 * Returns the first cp option category in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp option category
	 * @throws NoSuchCPOptionCategoryException if a matching cp option category could not be found
	 */
	@Override
	public CPOptionCategory findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CPOptionCategory> orderByComparator)
		throws NoSuchCPOptionCategoryException {

		CPOptionCategory cpOptionCategory = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (cpOptionCategory != null) {
			return cpOptionCategory;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPOptionCategoryException(msg.toString());
	}

	/**
	 * Returns the first cp option category in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp option category, or <code>null</code> if a matching cp option category could not be found
	 */
	@Override
	public CPOptionCategory fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CPOptionCategory> orderByComparator) {

		List<CPOptionCategory> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp option category in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp option category
	 * @throws NoSuchCPOptionCategoryException if a matching cp option category could not be found
	 */
	@Override
	public CPOptionCategory findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CPOptionCategory> orderByComparator)
		throws NoSuchCPOptionCategoryException {

		CPOptionCategory cpOptionCategory = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (cpOptionCategory != null) {
			return cpOptionCategory;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPOptionCategoryException(msg.toString());
	}

	/**
	 * Returns the last cp option category in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp option category, or <code>null</code> if a matching cp option category could not be found
	 */
	@Override
	public CPOptionCategory fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CPOptionCategory> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CPOptionCategory> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp option categories before and after the current cp option category in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param CPOptionCategoryId the primary key of the current cp option category
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp option category
	 * @throws NoSuchCPOptionCategoryException if a cp option category with the primary key could not be found
	 */
	@Override
	public CPOptionCategory[] findByUuid_C_PrevAndNext(
			long CPOptionCategoryId, String uuid, long companyId,
			OrderByComparator<CPOptionCategory> orderByComparator)
		throws NoSuchCPOptionCategoryException {

		uuid = Objects.toString(uuid, "");

		CPOptionCategory cpOptionCategory = findByPrimaryKey(
			CPOptionCategoryId);

		Session session = null;

		try {
			session = openSession();

			CPOptionCategory[] array = new CPOptionCategoryImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, cpOptionCategory, uuid, companyId, orderByComparator,
				true);

			array[1] = cpOptionCategory;

			array[2] = getByUuid_C_PrevAndNext(
				session, cpOptionCategory, uuid, companyId, orderByComparator,
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

	protected CPOptionCategory getByUuid_C_PrevAndNext(
		Session session, CPOptionCategory cpOptionCategory, String uuid,
		long companyId, OrderByComparator<CPOptionCategory> orderByComparator,
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

		query.append(_SQL_SELECT_CPOPTIONCATEGORY_WHERE);

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
			query.append(CPOptionCategoryModelImpl.ORDER_BY_JPQL);
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
						cpOptionCategory)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPOptionCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the cp option categories that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching cp option categories that the user has permission to view
	 */
	@Override
	public List<CPOptionCategory> filterFindByUuid_C(
		String uuid, long companyId) {

		return filterFindByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp option categories that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp option categories
	 * @param end the upper bound of the range of cp option categories (not inclusive)
	 * @return the range of matching cp option categories that the user has permission to view
	 */
	@Override
	public List<CPOptionCategory> filterFindByUuid_C(
		String uuid, long companyId, int start, int end) {

		return filterFindByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp option categories that the user has permissions to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp option categories
	 * @param end the upper bound of the range of cp option categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp option categories that the user has permission to view
	 */
	@Override
	public List<CPOptionCategory> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPOptionCategory> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByUuid_C(uuid, companyId, start, end, orderByComparator);
		}

		uuid = Objects.toString(uuid, "");

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CPOPTIONCATEGORY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_CPOPTIONCATEGORY_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2_SQL);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_CPOPTIONCATEGORY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CPOptionCategoryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CPOptionCategoryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPOptionCategory.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, CPOptionCategoryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, CPOptionCategoryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindUuid) {
				qPos.add(uuid);
			}

			qPos.add(companyId);

			return (List<CPOptionCategory>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the cp option categories before and after the current cp option category in the ordered set of cp option categories that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param CPOptionCategoryId the primary key of the current cp option category
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp option category
	 * @throws NoSuchCPOptionCategoryException if a cp option category with the primary key could not be found
	 */
	@Override
	public CPOptionCategory[] filterFindByUuid_C_PrevAndNext(
			long CPOptionCategoryId, String uuid, long companyId,
			OrderByComparator<CPOptionCategory> orderByComparator)
		throws NoSuchCPOptionCategoryException {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByUuid_C_PrevAndNext(
				CPOptionCategoryId, uuid, companyId, orderByComparator);
		}

		uuid = Objects.toString(uuid, "");

		CPOptionCategory cpOptionCategory = findByPrimaryKey(
			CPOptionCategoryId);

		Session session = null;

		try {
			session = openSession();

			CPOptionCategory[] array = new CPOptionCategoryImpl[3];

			array[0] = filterGetByUuid_C_PrevAndNext(
				session, cpOptionCategory, uuid, companyId, orderByComparator,
				true);

			array[1] = cpOptionCategory;

			array[2] = filterGetByUuid_C_PrevAndNext(
				session, cpOptionCategory, uuid, companyId, orderByComparator,
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

	protected CPOptionCategory filterGetByUuid_C_PrevAndNext(
		Session session, CPOptionCategory cpOptionCategory, String uuid,
		long companyId, OrderByComparator<CPOptionCategory> orderByComparator,
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

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CPOPTIONCATEGORY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_CPOPTIONCATEGORY_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2_SQL);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_CPOPTIONCATEGORY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CPOptionCategoryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CPOptionCategoryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPOptionCategory.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CPOptionCategoryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CPOptionCategoryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpOptionCategory)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPOptionCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp option categories where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CPOptionCategory cpOptionCategory :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(cpOptionCategory);
		}
	}

	/**
	 * Returns the number of cp option categories where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching cp option categories
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPOPTIONCATEGORY_WHERE);

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

	/**
	 * Returns the number of cp option categories that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching cp option categories that the user has permission to view
	 */
	@Override
	public int filterCountByUuid_C(String uuid, long companyId) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return countByUuid_C(uuid, companyId);
		}

		uuid = Objects.toString(uuid, "");

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_CPOPTIONCATEGORY_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2_SQL);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPOptionCategory.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindUuid) {
				qPos.add(uuid);
			}

			qPos.add(companyId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"cpOptionCategory.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(cpOptionCategory.uuid IS NULL OR cpOptionCategory.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_2_SQL =
		"cpOptionCategory.uuid_ = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3_SQL =
		"(cpOptionCategory.uuid_ IS NULL OR cpOptionCategory.uuid_ = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"cpOptionCategory.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the cp option categories where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching cp option categories
	 */
	@Override
	public List<CPOptionCategory> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp option categories where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp option categories
	 * @param end the upper bound of the range of cp option categories (not inclusive)
	 * @return the range of matching cp option categories
	 */
	@Override
	public List<CPOptionCategory> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp option categories where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp option categories
	 * @param end the upper bound of the range of cp option categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp option categories
	 */
	@Override
	public List<CPOptionCategory> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CPOptionCategory> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp option categories where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp option categories
	 * @param end the upper bound of the range of cp option categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp option categories
	 */
	@Override
	public List<CPOptionCategory> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CPOptionCategory> orderByComparator,
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

		List<CPOptionCategory> list = null;

		if (retrieveFromCache) {
			list = (List<CPOptionCategory>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPOptionCategory cpOptionCategory : list) {
					if ((companyId != cpOptionCategory.getCompanyId())) {
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

			query.append(_SQL_SELECT_CPOPTIONCATEGORY_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CPOptionCategoryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<CPOptionCategory>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPOptionCategory>)QueryUtil.list(
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
	 * Returns the first cp option category in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp option category
	 * @throws NoSuchCPOptionCategoryException if a matching cp option category could not be found
	 */
	@Override
	public CPOptionCategory findByCompanyId_First(
			long companyId,
			OrderByComparator<CPOptionCategory> orderByComparator)
		throws NoSuchCPOptionCategoryException {

		CPOptionCategory cpOptionCategory = fetchByCompanyId_First(
			companyId, orderByComparator);

		if (cpOptionCategory != null) {
			return cpOptionCategory;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPOptionCategoryException(msg.toString());
	}

	/**
	 * Returns the first cp option category in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp option category, or <code>null</code> if a matching cp option category could not be found
	 */
	@Override
	public CPOptionCategory fetchByCompanyId_First(
		long companyId, OrderByComparator<CPOptionCategory> orderByComparator) {

		List<CPOptionCategory> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp option category in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp option category
	 * @throws NoSuchCPOptionCategoryException if a matching cp option category could not be found
	 */
	@Override
	public CPOptionCategory findByCompanyId_Last(
			long companyId,
			OrderByComparator<CPOptionCategory> orderByComparator)
		throws NoSuchCPOptionCategoryException {

		CPOptionCategory cpOptionCategory = fetchByCompanyId_Last(
			companyId, orderByComparator);

		if (cpOptionCategory != null) {
			return cpOptionCategory;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPOptionCategoryException(msg.toString());
	}

	/**
	 * Returns the last cp option category in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp option category, or <code>null</code> if a matching cp option category could not be found
	 */
	@Override
	public CPOptionCategory fetchByCompanyId_Last(
		long companyId, OrderByComparator<CPOptionCategory> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<CPOptionCategory> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp option categories before and after the current cp option category in the ordered set where companyId = &#63;.
	 *
	 * @param CPOptionCategoryId the primary key of the current cp option category
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp option category
	 * @throws NoSuchCPOptionCategoryException if a cp option category with the primary key could not be found
	 */
	@Override
	public CPOptionCategory[] findByCompanyId_PrevAndNext(
			long CPOptionCategoryId, long companyId,
			OrderByComparator<CPOptionCategory> orderByComparator)
		throws NoSuchCPOptionCategoryException {

		CPOptionCategory cpOptionCategory = findByPrimaryKey(
			CPOptionCategoryId);

		Session session = null;

		try {
			session = openSession();

			CPOptionCategory[] array = new CPOptionCategoryImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, cpOptionCategory, companyId, orderByComparator, true);

			array[1] = cpOptionCategory;

			array[2] = getByCompanyId_PrevAndNext(
				session, cpOptionCategory, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPOptionCategory getByCompanyId_PrevAndNext(
		Session session, CPOptionCategory cpOptionCategory, long companyId,
		OrderByComparator<CPOptionCategory> orderByComparator,
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

		query.append(_SQL_SELECT_CPOPTIONCATEGORY_WHERE);

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
			query.append(CPOptionCategoryModelImpl.ORDER_BY_JPQL);
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
						cpOptionCategory)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPOptionCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the cp option categories that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching cp option categories that the user has permission to view
	 */
	@Override
	public List<CPOptionCategory> filterFindByCompanyId(long companyId) {
		return filterFindByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp option categories that the user has permission to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp option categories
	 * @param end the upper bound of the range of cp option categories (not inclusive)
	 * @return the range of matching cp option categories that the user has permission to view
	 */
	@Override
	public List<CPOptionCategory> filterFindByCompanyId(
		long companyId, int start, int end) {

		return filterFindByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp option categories that the user has permissions to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp option categories
	 * @param end the upper bound of the range of cp option categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp option categories that the user has permission to view
	 */
	@Override
	public List<CPOptionCategory> filterFindByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CPOptionCategory> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByCompanyId(companyId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				3 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CPOPTIONCATEGORY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_CPOPTIONCATEGORY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_CPOPTIONCATEGORY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CPOptionCategoryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CPOptionCategoryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPOptionCategory.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, CPOptionCategoryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, CPOptionCategoryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			return (List<CPOptionCategory>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the cp option categories before and after the current cp option category in the ordered set of cp option categories that the user has permission to view where companyId = &#63;.
	 *
	 * @param CPOptionCategoryId the primary key of the current cp option category
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp option category
	 * @throws NoSuchCPOptionCategoryException if a cp option category with the primary key could not be found
	 */
	@Override
	public CPOptionCategory[] filterFindByCompanyId_PrevAndNext(
			long CPOptionCategoryId, long companyId,
			OrderByComparator<CPOptionCategory> orderByComparator)
		throws NoSuchCPOptionCategoryException {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByCompanyId_PrevAndNext(
				CPOptionCategoryId, companyId, orderByComparator);
		}

		CPOptionCategory cpOptionCategory = findByPrimaryKey(
			CPOptionCategoryId);

		Session session = null;

		try {
			session = openSession();

			CPOptionCategory[] array = new CPOptionCategoryImpl[3];

			array[0] = filterGetByCompanyId_PrevAndNext(
				session, cpOptionCategory, companyId, orderByComparator, true);

			array[1] = cpOptionCategory;

			array[2] = filterGetByCompanyId_PrevAndNext(
				session, cpOptionCategory, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPOptionCategory filterGetByCompanyId_PrevAndNext(
		Session session, CPOptionCategory cpOptionCategory, long companyId,
		OrderByComparator<CPOptionCategory> orderByComparator,
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

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CPOPTIONCATEGORY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_CPOPTIONCATEGORY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_CPOPTIONCATEGORY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CPOptionCategoryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CPOptionCategoryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPOptionCategory.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CPOptionCategoryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CPOptionCategoryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpOptionCategory)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPOptionCategory> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp option categories where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (CPOptionCategory cpOptionCategory :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cpOptionCategory);
		}
	}

	/**
	 * Returns the number of cp option categories where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching cp option categories
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] {companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPOPTIONCATEGORY_WHERE);

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

	/**
	 * Returns the number of cp option categories that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching cp option categories that the user has permission to view
	 */
	@Override
	public int filterCountByCompanyId(long companyId) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return countByCompanyId(companyId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_CPOPTIONCATEGORY_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPOptionCategory.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 =
		"cpOptionCategory.companyId = ?";

	private FinderPath _finderPathFetchByC_K;
	private FinderPath _finderPathCountByC_K;

	/**
	 * Returns the cp option category where companyId = &#63; and key = &#63; or throws a <code>NoSuchCPOptionCategoryException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param key the key
	 * @return the matching cp option category
	 * @throws NoSuchCPOptionCategoryException if a matching cp option category could not be found
	 */
	@Override
	public CPOptionCategory findByC_K(long companyId, String key)
		throws NoSuchCPOptionCategoryException {

		CPOptionCategory cpOptionCategory = fetchByC_K(companyId, key);

		if (cpOptionCategory == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", key=");
			msg.append(key);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCPOptionCategoryException(msg.toString());
		}

		return cpOptionCategory;
	}

	/**
	 * Returns the cp option category where companyId = &#63; and key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param key the key
	 * @return the matching cp option category, or <code>null</code> if a matching cp option category could not be found
	 */
	@Override
	public CPOptionCategory fetchByC_K(long companyId, String key) {
		return fetchByC_K(companyId, key, true);
	}

	/**
	 * Returns the cp option category where companyId = &#63; and key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param key the key
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching cp option category, or <code>null</code> if a matching cp option category could not be found
	 */
	@Override
	public CPOptionCategory fetchByC_K(
		long companyId, String key, boolean retrieveFromCache) {

		key = Objects.toString(key, "");

		Object[] finderArgs = new Object[] {companyId, key};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_K, finderArgs, this);
		}

		if (result instanceof CPOptionCategory) {
			CPOptionCategory cpOptionCategory = (CPOptionCategory)result;

			if ((companyId != cpOptionCategory.getCompanyId()) ||
				!Objects.equals(key, cpOptionCategory.getKey())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CPOPTIONCATEGORY_WHERE);

			query.append(_FINDER_COLUMN_C_K_COMPANYID_2);

			boolean bindKey = false;

			if (key.isEmpty()) {
				query.append(_FINDER_COLUMN_C_K_KEY_3);
			}
			else {
				bindKey = true;

				query.append(_FINDER_COLUMN_C_K_KEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindKey) {
					qPos.add(key);
				}

				List<CPOptionCategory> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByC_K, finderArgs, list);
				}
				else {
					CPOptionCategory cpOptionCategory = list.get(0);

					result = cpOptionCategory;

					cacheResult(cpOptionCategory);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByC_K, finderArgs);

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
			return (CPOptionCategory)result;
		}
	}

	/**
	 * Removes the cp option category where companyId = &#63; and key = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param key the key
	 * @return the cp option category that was removed
	 */
	@Override
	public CPOptionCategory removeByC_K(long companyId, String key)
		throws NoSuchCPOptionCategoryException {

		CPOptionCategory cpOptionCategory = findByC_K(companyId, key);

		return remove(cpOptionCategory);
	}

	/**
	 * Returns the number of cp option categories where companyId = &#63; and key = &#63;.
	 *
	 * @param companyId the company ID
	 * @param key the key
	 * @return the number of matching cp option categories
	 */
	@Override
	public int countByC_K(long companyId, String key) {
		key = Objects.toString(key, "");

		FinderPath finderPath = _finderPathCountByC_K;

		Object[] finderArgs = new Object[] {companyId, key};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPOPTIONCATEGORY_WHERE);

			query.append(_FINDER_COLUMN_C_K_COMPANYID_2);

			boolean bindKey = false;

			if (key.isEmpty()) {
				query.append(_FINDER_COLUMN_C_K_KEY_3);
			}
			else {
				bindKey = true;

				query.append(_FINDER_COLUMN_C_K_KEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindKey) {
					qPos.add(key);
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

	private static final String _FINDER_COLUMN_C_K_COMPANYID_2 =
		"cpOptionCategory.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_K_KEY_2 =
		"cpOptionCategory.key = ?";

	private static final String _FINDER_COLUMN_C_K_KEY_3 =
		"(cpOptionCategory.key IS NULL OR cpOptionCategory.key = '')";

	public CPOptionCategoryPersistenceImpl() {
		setModelClass(CPOptionCategory.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("key", "key_");

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
	 * Caches the cp option category in the entity cache if it is enabled.
	 *
	 * @param cpOptionCategory the cp option category
	 */
	@Override
	public void cacheResult(CPOptionCategory cpOptionCategory) {
		entityCache.putResult(
			CPOptionCategoryModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionCategoryImpl.class, cpOptionCategory.getPrimaryKey(),
			cpOptionCategory);

		finderCache.putResult(
			_finderPathFetchByC_K,
			new Object[] {
				cpOptionCategory.getCompanyId(), cpOptionCategory.getKey()
			},
			cpOptionCategory);

		cpOptionCategory.resetOriginalValues();
	}

	/**
	 * Caches the cp option categories in the entity cache if it is enabled.
	 *
	 * @param cpOptionCategories the cp option categories
	 */
	@Override
	public void cacheResult(List<CPOptionCategory> cpOptionCategories) {
		for (CPOptionCategory cpOptionCategory : cpOptionCategories) {
			if (entityCache.getResult(
					CPOptionCategoryModelImpl.ENTITY_CACHE_ENABLED,
					CPOptionCategoryImpl.class,
					cpOptionCategory.getPrimaryKey()) == null) {

				cacheResult(cpOptionCategory);
			}
			else {
				cpOptionCategory.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cp option categories.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CPOptionCategoryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cp option category.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CPOptionCategory cpOptionCategory) {
		entityCache.removeResult(
			CPOptionCategoryModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionCategoryImpl.class, cpOptionCategory.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(CPOptionCategoryModelImpl)cpOptionCategory, true);
	}

	@Override
	public void clearCache(List<CPOptionCategory> cpOptionCategories) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CPOptionCategory cpOptionCategory : cpOptionCategories) {
			entityCache.removeResult(
				CPOptionCategoryModelImpl.ENTITY_CACHE_ENABLED,
				CPOptionCategoryImpl.class, cpOptionCategory.getPrimaryKey());

			clearUniqueFindersCache(
				(CPOptionCategoryModelImpl)cpOptionCategory, true);
		}
	}

	protected void cacheUniqueFindersCache(
		CPOptionCategoryModelImpl cpOptionCategoryModelImpl) {

		Object[] args = new Object[] {
			cpOptionCategoryModelImpl.getCompanyId(),
			cpOptionCategoryModelImpl.getKey()
		};

		finderCache.putResult(
			_finderPathCountByC_K, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_K, args, cpOptionCategoryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CPOptionCategoryModelImpl cpOptionCategoryModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				cpOptionCategoryModelImpl.getCompanyId(),
				cpOptionCategoryModelImpl.getKey()
			};

			finderCache.removeResult(_finderPathCountByC_K, args);
			finderCache.removeResult(_finderPathFetchByC_K, args);
		}

		if ((cpOptionCategoryModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_K.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				cpOptionCategoryModelImpl.getOriginalCompanyId(),
				cpOptionCategoryModelImpl.getOriginalKey()
			};

			finderCache.removeResult(_finderPathCountByC_K, args);
			finderCache.removeResult(_finderPathFetchByC_K, args);
		}
	}

	/**
	 * Creates a new cp option category with the primary key. Does not add the cp option category to the database.
	 *
	 * @param CPOptionCategoryId the primary key for the new cp option category
	 * @return the new cp option category
	 */
	@Override
	public CPOptionCategory create(long CPOptionCategoryId) {
		CPOptionCategory cpOptionCategory = new CPOptionCategoryImpl();

		cpOptionCategory.setNew(true);
		cpOptionCategory.setPrimaryKey(CPOptionCategoryId);

		String uuid = PortalUUIDUtil.generate();

		cpOptionCategory.setUuid(uuid);

		cpOptionCategory.setCompanyId(CompanyThreadLocal.getCompanyId());

		return cpOptionCategory;
	}

	/**
	 * Removes the cp option category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPOptionCategoryId the primary key of the cp option category
	 * @return the cp option category that was removed
	 * @throws NoSuchCPOptionCategoryException if a cp option category with the primary key could not be found
	 */
	@Override
	public CPOptionCategory remove(long CPOptionCategoryId)
		throws NoSuchCPOptionCategoryException {

		return remove((Serializable)CPOptionCategoryId);
	}

	/**
	 * Removes the cp option category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cp option category
	 * @return the cp option category that was removed
	 * @throws NoSuchCPOptionCategoryException if a cp option category with the primary key could not be found
	 */
	@Override
	public CPOptionCategory remove(Serializable primaryKey)
		throws NoSuchCPOptionCategoryException {

		Session session = null;

		try {
			session = openSession();

			CPOptionCategory cpOptionCategory = (CPOptionCategory)session.get(
				CPOptionCategoryImpl.class, primaryKey);

			if (cpOptionCategory == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCPOptionCategoryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(cpOptionCategory);
		}
		catch (NoSuchCPOptionCategoryException nsee) {
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
	protected CPOptionCategory removeImpl(CPOptionCategory cpOptionCategory) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cpOptionCategory)) {
				cpOptionCategory = (CPOptionCategory)session.get(
					CPOptionCategoryImpl.class,
					cpOptionCategory.getPrimaryKeyObj());
			}

			if (cpOptionCategory != null) {
				session.delete(cpOptionCategory);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cpOptionCategory != null) {
			clearCache(cpOptionCategory);
		}

		return cpOptionCategory;
	}

	@Override
	public CPOptionCategory updateImpl(CPOptionCategory cpOptionCategory) {
		boolean isNew = cpOptionCategory.isNew();

		if (!(cpOptionCategory instanceof CPOptionCategoryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(cpOptionCategory.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					cpOptionCategory);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in cpOptionCategory proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CPOptionCategory implementation " +
					cpOptionCategory.getClass());
		}

		CPOptionCategoryModelImpl cpOptionCategoryModelImpl =
			(CPOptionCategoryModelImpl)cpOptionCategory;

		if (Validator.isNull(cpOptionCategory.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			cpOptionCategory.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (cpOptionCategory.getCreateDate() == null)) {
			if (serviceContext == null) {
				cpOptionCategory.setCreateDate(now);
			}
			else {
				cpOptionCategory.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!cpOptionCategoryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				cpOptionCategory.setModifiedDate(now);
			}
			else {
				cpOptionCategory.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (cpOptionCategory.isNew()) {
				session.save(cpOptionCategory);

				cpOptionCategory.setNew(false);
			}
			else {
				cpOptionCategory = (CPOptionCategory)session.merge(
					cpOptionCategory);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CPOptionCategoryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {cpOptionCategoryModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				cpOptionCategoryModelImpl.getUuid(),
				cpOptionCategoryModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {cpOptionCategoryModelImpl.getCompanyId()};

			finderCache.removeResult(_finderPathCountByCompanyId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCompanyId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((cpOptionCategoryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpOptionCategoryModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {cpOptionCategoryModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((cpOptionCategoryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpOptionCategoryModelImpl.getOriginalUuid(),
					cpOptionCategoryModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					cpOptionCategoryModelImpl.getUuid(),
					cpOptionCategoryModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((cpOptionCategoryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCompanyId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					cpOptionCategoryModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);

				args = new Object[] {cpOptionCategoryModelImpl.getCompanyId()};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);
			}
		}

		entityCache.putResult(
			CPOptionCategoryModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionCategoryImpl.class, cpOptionCategory.getPrimaryKey(),
			cpOptionCategory, false);

		clearUniqueFindersCache(cpOptionCategoryModelImpl, false);
		cacheUniqueFindersCache(cpOptionCategoryModelImpl);

		cpOptionCategory.resetOriginalValues();

		return cpOptionCategory;
	}

	/**
	 * Returns the cp option category with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp option category
	 * @return the cp option category
	 * @throws NoSuchCPOptionCategoryException if a cp option category with the primary key could not be found
	 */
	@Override
	public CPOptionCategory findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCPOptionCategoryException {

		CPOptionCategory cpOptionCategory = fetchByPrimaryKey(primaryKey);

		if (cpOptionCategory == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCPOptionCategoryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return cpOptionCategory;
	}

	/**
	 * Returns the cp option category with the primary key or throws a <code>NoSuchCPOptionCategoryException</code> if it could not be found.
	 *
	 * @param CPOptionCategoryId the primary key of the cp option category
	 * @return the cp option category
	 * @throws NoSuchCPOptionCategoryException if a cp option category with the primary key could not be found
	 */
	@Override
	public CPOptionCategory findByPrimaryKey(long CPOptionCategoryId)
		throws NoSuchCPOptionCategoryException {

		return findByPrimaryKey((Serializable)CPOptionCategoryId);
	}

	/**
	 * Returns the cp option category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp option category
	 * @return the cp option category, or <code>null</code> if a cp option category with the primary key could not be found
	 */
	@Override
	public CPOptionCategory fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			CPOptionCategoryModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionCategoryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CPOptionCategory cpOptionCategory = (CPOptionCategory)serializable;

		if (cpOptionCategory == null) {
			Session session = null;

			try {
				session = openSession();

				cpOptionCategory = (CPOptionCategory)session.get(
					CPOptionCategoryImpl.class, primaryKey);

				if (cpOptionCategory != null) {
					cacheResult(cpOptionCategory);
				}
				else {
					entityCache.putResult(
						CPOptionCategoryModelImpl.ENTITY_CACHE_ENABLED,
						CPOptionCategoryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CPOptionCategoryModelImpl.ENTITY_CACHE_ENABLED,
					CPOptionCategoryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cpOptionCategory;
	}

	/**
	 * Returns the cp option category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param CPOptionCategoryId the primary key of the cp option category
	 * @return the cp option category, or <code>null</code> if a cp option category with the primary key could not be found
	 */
	@Override
	public CPOptionCategory fetchByPrimaryKey(long CPOptionCategoryId) {
		return fetchByPrimaryKey((Serializable)CPOptionCategoryId);
	}

	@Override
	public Map<Serializable, CPOptionCategory> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CPOptionCategory> map =
			new HashMap<Serializable, CPOptionCategory>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CPOptionCategory cpOptionCategory = fetchByPrimaryKey(primaryKey);

			if (cpOptionCategory != null) {
				map.put(primaryKey, cpOptionCategory);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CPOptionCategoryModelImpl.ENTITY_CACHE_ENABLED,
				CPOptionCategoryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CPOptionCategory)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_CPOPTIONCATEGORY_WHERE_PKS_IN);

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

			for (CPOptionCategory cpOptionCategory :
					(List<CPOptionCategory>)q.list()) {

				map.put(cpOptionCategory.getPrimaryKeyObj(), cpOptionCategory);

				cacheResult(cpOptionCategory);

				uncachedPrimaryKeys.remove(cpOptionCategory.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CPOptionCategoryModelImpl.ENTITY_CACHE_ENABLED,
					CPOptionCategoryImpl.class, primaryKey, nullModel);
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
	 * Returns all the cp option categories.
	 *
	 * @return the cp option categories
	 */
	@Override
	public List<CPOptionCategory> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp option categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp option categories
	 * @param end the upper bound of the range of cp option categories (not inclusive)
	 * @return the range of cp option categories
	 */
	@Override
	public List<CPOptionCategory> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp option categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp option categories
	 * @param end the upper bound of the range of cp option categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cp option categories
	 */
	@Override
	public List<CPOptionCategory> findAll(
		int start, int end,
		OrderByComparator<CPOptionCategory> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp option categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp option categories
	 * @param end the upper bound of the range of cp option categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cp option categories
	 */
	@Override
	public List<CPOptionCategory> findAll(
		int start, int end,
		OrderByComparator<CPOptionCategory> orderByComparator,
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

		List<CPOptionCategory> list = null;

		if (retrieveFromCache) {
			list = (List<CPOptionCategory>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CPOPTIONCATEGORY);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CPOPTIONCATEGORY;

				if (pagination) {
					sql = sql.concat(CPOptionCategoryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CPOptionCategory>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPOptionCategory>)QueryUtil.list(
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
	 * Removes all the cp option categories from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CPOptionCategory cpOptionCategory : findAll()) {
			remove(cpOptionCategory);
		}
	}

	/**
	 * Returns the number of cp option categories.
	 *
	 * @return the number of cp option categories
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CPOPTIONCATEGORY);

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
		return CPOptionCategoryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cp option category persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CPOptionCategoryModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionCategoryModelImpl.FINDER_CACHE_ENABLED,
			CPOptionCategoryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CPOptionCategoryModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionCategoryModelImpl.FINDER_CACHE_ENABLED,
			CPOptionCategoryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CPOptionCategoryModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			CPOptionCategoryModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionCategoryModelImpl.FINDER_CACHE_ENABLED,
			CPOptionCategoryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			CPOptionCategoryModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionCategoryModelImpl.FINDER_CACHE_ENABLED,
			CPOptionCategoryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			CPOptionCategoryModelImpl.UUID_COLUMN_BITMASK |
			CPOptionCategoryModelImpl.TITLE_COLUMN_BITMASK |
			CPOptionCategoryModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			CPOptionCategoryModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			CPOptionCategoryModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionCategoryModelImpl.FINDER_CACHE_ENABLED,
			CPOptionCategoryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			CPOptionCategoryModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionCategoryModelImpl.FINDER_CACHE_ENABLED,
			CPOptionCategoryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			CPOptionCategoryModelImpl.UUID_COLUMN_BITMASK |
			CPOptionCategoryModelImpl.COMPANYID_COLUMN_BITMASK |
			CPOptionCategoryModelImpl.TITLE_COLUMN_BITMASK |
			CPOptionCategoryModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			CPOptionCategoryModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByCompanyId = new FinderPath(
			CPOptionCategoryModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionCategoryModelImpl.FINDER_CACHE_ENABLED,
			CPOptionCategoryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCompanyId = new FinderPath(
			CPOptionCategoryModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionCategoryModelImpl.FINDER_CACHE_ENABLED,
			CPOptionCategoryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] {Long.class.getName()},
			CPOptionCategoryModelImpl.COMPANYID_COLUMN_BITMASK |
			CPOptionCategoryModelImpl.TITLE_COLUMN_BITMASK |
			CPOptionCategoryModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByCompanyId = new FinderPath(
			CPOptionCategoryModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] {Long.class.getName()});

		_finderPathFetchByC_K = new FinderPath(
			CPOptionCategoryModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionCategoryModelImpl.FINDER_CACHE_ENABLED,
			CPOptionCategoryImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByC_K",
			new String[] {Long.class.getName(), String.class.getName()},
			CPOptionCategoryModelImpl.COMPANYID_COLUMN_BITMASK |
			CPOptionCategoryModelImpl.KEY_COLUMN_BITMASK);

		_finderPathCountByC_K = new FinderPath(
			CPOptionCategoryModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_K",
			new String[] {Long.class.getName(), String.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(CPOptionCategoryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_CPOPTIONCATEGORY =
		"SELECT cpOptionCategory FROM CPOptionCategory cpOptionCategory";

	private static final String _SQL_SELECT_CPOPTIONCATEGORY_WHERE_PKS_IN =
		"SELECT cpOptionCategory FROM CPOptionCategory cpOptionCategory WHERE CPOptionCategoryId IN (";

	private static final String _SQL_SELECT_CPOPTIONCATEGORY_WHERE =
		"SELECT cpOptionCategory FROM CPOptionCategory cpOptionCategory WHERE ";

	private static final String _SQL_COUNT_CPOPTIONCATEGORY =
		"SELECT COUNT(cpOptionCategory) FROM CPOptionCategory cpOptionCategory";

	private static final String _SQL_COUNT_CPOPTIONCATEGORY_WHERE =
		"SELECT COUNT(cpOptionCategory) FROM CPOptionCategory cpOptionCategory WHERE ";

	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN =
		"cpOptionCategory.CPOptionCategoryId";

	private static final String _FILTER_SQL_SELECT_CPOPTIONCATEGORY_WHERE =
		"SELECT DISTINCT {cpOptionCategory.*} FROM CPOptionCategory cpOptionCategory WHERE ";

	private static final String
		_FILTER_SQL_SELECT_CPOPTIONCATEGORY_NO_INLINE_DISTINCT_WHERE_1 =
			"SELECT {CPOptionCategory.*} FROM (SELECT DISTINCT cpOptionCategory.CPOptionCategoryId FROM CPOptionCategory cpOptionCategory WHERE ";

	private static final String
		_FILTER_SQL_SELECT_CPOPTIONCATEGORY_NO_INLINE_DISTINCT_WHERE_2 =
			") TEMP_TABLE INNER JOIN CPOptionCategory ON TEMP_TABLE.CPOptionCategoryId = CPOptionCategory.CPOptionCategoryId";

	private static final String _FILTER_SQL_COUNT_CPOPTIONCATEGORY_WHERE =
		"SELECT COUNT(DISTINCT cpOptionCategory.CPOptionCategoryId) AS COUNT_VALUE FROM CPOptionCategory cpOptionCategory WHERE ";

	private static final String _FILTER_ENTITY_ALIAS = "cpOptionCategory";

	private static final String _FILTER_ENTITY_TABLE = "CPOptionCategory";

	private static final String _ORDER_BY_ENTITY_ALIAS = "cpOptionCategory.";

	private static final String _ORDER_BY_ENTITY_TABLE = "CPOptionCategory.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CPOptionCategory exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CPOptionCategory exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CPOptionCategoryPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "key"});

}