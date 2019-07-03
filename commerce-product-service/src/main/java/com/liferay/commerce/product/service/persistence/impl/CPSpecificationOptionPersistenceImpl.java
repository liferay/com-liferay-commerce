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

import com.liferay.commerce.product.exception.NoSuchCPSpecificationOptionException;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.model.impl.CPSpecificationOptionImpl;
import com.liferay.commerce.product.model.impl.CPSpecificationOptionModelImpl;
import com.liferay.commerce.product.service.persistence.CPSpecificationOptionPersistence;
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
 * The persistence implementation for the cp specification option service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @generated
 */
@ProviderType
public class CPSpecificationOptionPersistenceImpl
	extends BasePersistenceImpl<CPSpecificationOption>
	implements CPSpecificationOptionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CPSpecificationOptionUtil</code> to access the cp specification option persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CPSpecificationOptionImpl.class.getName();

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
	 * Returns all the cp specification options where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cp specification options
	 */
	@Override
	public List<CPSpecificationOption> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp specification options where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPSpecificationOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp specification options
	 * @param end the upper bound of the range of cp specification options (not inclusive)
	 * @return the range of matching cp specification options
	 */
	@Override
	public List<CPSpecificationOption> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp specification options where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPSpecificationOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp specification options
	 * @param end the upper bound of the range of cp specification options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp specification options
	 */
	@Override
	public List<CPSpecificationOption> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPSpecificationOption> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp specification options where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPSpecificationOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp specification options
	 * @param end the upper bound of the range of cp specification options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp specification options
	 */
	@Override
	public List<CPSpecificationOption> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPSpecificationOption> orderByComparator,
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

		List<CPSpecificationOption> list = null;

		if (retrieveFromCache) {
			list = (List<CPSpecificationOption>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPSpecificationOption cpSpecificationOption : list) {
					if (!uuid.equals(cpSpecificationOption.getUuid())) {
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

			query.append(_SQL_SELECT_CPSPECIFICATIONOPTION_WHERE);

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
				query.append(CPSpecificationOptionModelImpl.ORDER_BY_JPQL);
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
					list = (List<CPSpecificationOption>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPSpecificationOption>)QueryUtil.list(
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
	 * Returns the first cp specification option in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp specification option
	 * @throws NoSuchCPSpecificationOptionException if a matching cp specification option could not be found
	 */
	@Override
	public CPSpecificationOption findByUuid_First(
			String uuid,
			OrderByComparator<CPSpecificationOption> orderByComparator)
		throws NoSuchCPSpecificationOptionException {

		CPSpecificationOption cpSpecificationOption = fetchByUuid_First(
			uuid, orderByComparator);

		if (cpSpecificationOption != null) {
			return cpSpecificationOption;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCPSpecificationOptionException(msg.toString());
	}

	/**
	 * Returns the first cp specification option in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp specification option, or <code>null</code> if a matching cp specification option could not be found
	 */
	@Override
	public CPSpecificationOption fetchByUuid_First(
		String uuid,
		OrderByComparator<CPSpecificationOption> orderByComparator) {

		List<CPSpecificationOption> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp specification option in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp specification option
	 * @throws NoSuchCPSpecificationOptionException if a matching cp specification option could not be found
	 */
	@Override
	public CPSpecificationOption findByUuid_Last(
			String uuid,
			OrderByComparator<CPSpecificationOption> orderByComparator)
		throws NoSuchCPSpecificationOptionException {

		CPSpecificationOption cpSpecificationOption = fetchByUuid_Last(
			uuid, orderByComparator);

		if (cpSpecificationOption != null) {
			return cpSpecificationOption;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCPSpecificationOptionException(msg.toString());
	}

	/**
	 * Returns the last cp specification option in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp specification option, or <code>null</code> if a matching cp specification option could not be found
	 */
	@Override
	public CPSpecificationOption fetchByUuid_Last(
		String uuid,
		OrderByComparator<CPSpecificationOption> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CPSpecificationOption> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp specification options before and after the current cp specification option in the ordered set where uuid = &#63;.
	 *
	 * @param CPSpecificationOptionId the primary key of the current cp specification option
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp specification option
	 * @throws NoSuchCPSpecificationOptionException if a cp specification option with the primary key could not be found
	 */
	@Override
	public CPSpecificationOption[] findByUuid_PrevAndNext(
			long CPSpecificationOptionId, String uuid,
			OrderByComparator<CPSpecificationOption> orderByComparator)
		throws NoSuchCPSpecificationOptionException {

		uuid = Objects.toString(uuid, "");

		CPSpecificationOption cpSpecificationOption = findByPrimaryKey(
			CPSpecificationOptionId);

		Session session = null;

		try {
			session = openSession();

			CPSpecificationOption[] array = new CPSpecificationOptionImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, cpSpecificationOption, uuid, orderByComparator, true);

			array[1] = cpSpecificationOption;

			array[2] = getByUuid_PrevAndNext(
				session, cpSpecificationOption, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPSpecificationOption getByUuid_PrevAndNext(
		Session session, CPSpecificationOption cpSpecificationOption,
		String uuid, OrderByComparator<CPSpecificationOption> orderByComparator,
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

		query.append(_SQL_SELECT_CPSPECIFICATIONOPTION_WHERE);

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
			query.append(CPSpecificationOptionModelImpl.ORDER_BY_JPQL);
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
						cpSpecificationOption)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPSpecificationOption> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the cp specification options that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cp specification options that the user has permission to view
	 */
	@Override
	public List<CPSpecificationOption> filterFindByUuid(String uuid) {
		return filterFindByUuid(
			uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp specification options that the user has permission to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPSpecificationOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp specification options
	 * @param end the upper bound of the range of cp specification options (not inclusive)
	 * @return the range of matching cp specification options that the user has permission to view
	 */
	@Override
	public List<CPSpecificationOption> filterFindByUuid(
		String uuid, int start, int end) {

		return filterFindByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp specification options that the user has permissions to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPSpecificationOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp specification options
	 * @param end the upper bound of the range of cp specification options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp specification options that the user has permission to view
	 */
	@Override
	public List<CPSpecificationOption> filterFindByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPSpecificationOption> orderByComparator) {

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
			query.append(_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_NO_INLINE_DISTINCT_WHERE_1);
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
				_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CPSpecificationOptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CPSpecificationOptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPSpecificationOption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(
					_FILTER_ENTITY_ALIAS, CPSpecificationOptionImpl.class);
			}
			else {
				q.addEntity(
					_FILTER_ENTITY_TABLE, CPSpecificationOptionImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindUuid) {
				qPos.add(uuid);
			}

			return (List<CPSpecificationOption>)QueryUtil.list(
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
	 * Returns the cp specification options before and after the current cp specification option in the ordered set of cp specification options that the user has permission to view where uuid = &#63;.
	 *
	 * @param CPSpecificationOptionId the primary key of the current cp specification option
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp specification option
	 * @throws NoSuchCPSpecificationOptionException if a cp specification option with the primary key could not be found
	 */
	@Override
	public CPSpecificationOption[] filterFindByUuid_PrevAndNext(
			long CPSpecificationOptionId, String uuid,
			OrderByComparator<CPSpecificationOption> orderByComparator)
		throws NoSuchCPSpecificationOptionException {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUuid_PrevAndNext(
				CPSpecificationOptionId, uuid, orderByComparator);
		}

		uuid = Objects.toString(uuid, "");

		CPSpecificationOption cpSpecificationOption = findByPrimaryKey(
			CPSpecificationOptionId);

		Session session = null;

		try {
			session = openSession();

			CPSpecificationOption[] array = new CPSpecificationOptionImpl[3];

			array[0] = filterGetByUuid_PrevAndNext(
				session, cpSpecificationOption, uuid, orderByComparator, true);

			array[1] = cpSpecificationOption;

			array[2] = filterGetByUuid_PrevAndNext(
				session, cpSpecificationOption, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPSpecificationOption filterGetByUuid_PrevAndNext(
		Session session, CPSpecificationOption cpSpecificationOption,
		String uuid, OrderByComparator<CPSpecificationOption> orderByComparator,
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
			query.append(_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_NO_INLINE_DISTINCT_WHERE_1);
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
				_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CPSpecificationOptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CPSpecificationOptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPSpecificationOption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CPSpecificationOptionImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CPSpecificationOptionImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpSpecificationOption)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPSpecificationOption> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp specification options where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CPSpecificationOption cpSpecificationOption :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cpSpecificationOption);
		}
	}

	/**
	 * Returns the number of cp specification options where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cp specification options
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPSPECIFICATIONOPTION_WHERE);

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
	 * Returns the number of cp specification options that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cp specification options that the user has permission to view
	 */
	@Override
	public int filterCountByUuid(String uuid) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByUuid(uuid);
		}

		uuid = Objects.toString(uuid, "");

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_CPSPECIFICATIONOPTION_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2_SQL);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPSpecificationOption.class.getName(),
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
		"cpSpecificationOption.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(cpSpecificationOption.uuid IS NULL OR cpSpecificationOption.uuid = '')";

	private static final String _FINDER_COLUMN_UUID_UUID_2_SQL =
		"cpSpecificationOption.uuid_ = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3_SQL =
		"(cpSpecificationOption.uuid_ IS NULL OR cpSpecificationOption.uuid_ = '')";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the cp specification options where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching cp specification options
	 */
	@Override
	public List<CPSpecificationOption> findByUuid_C(
		String uuid, long companyId) {

		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp specification options where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPSpecificationOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp specification options
	 * @param end the upper bound of the range of cp specification options (not inclusive)
	 * @return the range of matching cp specification options
	 */
	@Override
	public List<CPSpecificationOption> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp specification options where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPSpecificationOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp specification options
	 * @param end the upper bound of the range of cp specification options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp specification options
	 */
	@Override
	public List<CPSpecificationOption> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPSpecificationOption> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp specification options where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPSpecificationOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp specification options
	 * @param end the upper bound of the range of cp specification options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp specification options
	 */
	@Override
	public List<CPSpecificationOption> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPSpecificationOption> orderByComparator,
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

		List<CPSpecificationOption> list = null;

		if (retrieveFromCache) {
			list = (List<CPSpecificationOption>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPSpecificationOption cpSpecificationOption : list) {
					if (!uuid.equals(cpSpecificationOption.getUuid()) ||
						(companyId != cpSpecificationOption.getCompanyId())) {

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

			query.append(_SQL_SELECT_CPSPECIFICATIONOPTION_WHERE);

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
				query.append(CPSpecificationOptionModelImpl.ORDER_BY_JPQL);
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
					list = (List<CPSpecificationOption>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPSpecificationOption>)QueryUtil.list(
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
	 * Returns the first cp specification option in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp specification option
	 * @throws NoSuchCPSpecificationOptionException if a matching cp specification option could not be found
	 */
	@Override
	public CPSpecificationOption findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CPSpecificationOption> orderByComparator)
		throws NoSuchCPSpecificationOptionException {

		CPSpecificationOption cpSpecificationOption = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (cpSpecificationOption != null) {
			return cpSpecificationOption;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPSpecificationOptionException(msg.toString());
	}

	/**
	 * Returns the first cp specification option in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp specification option, or <code>null</code> if a matching cp specification option could not be found
	 */
	@Override
	public CPSpecificationOption fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CPSpecificationOption> orderByComparator) {

		List<CPSpecificationOption> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp specification option in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp specification option
	 * @throws NoSuchCPSpecificationOptionException if a matching cp specification option could not be found
	 */
	@Override
	public CPSpecificationOption findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CPSpecificationOption> orderByComparator)
		throws NoSuchCPSpecificationOptionException {

		CPSpecificationOption cpSpecificationOption = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (cpSpecificationOption != null) {
			return cpSpecificationOption;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPSpecificationOptionException(msg.toString());
	}

	/**
	 * Returns the last cp specification option in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp specification option, or <code>null</code> if a matching cp specification option could not be found
	 */
	@Override
	public CPSpecificationOption fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CPSpecificationOption> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CPSpecificationOption> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp specification options before and after the current cp specification option in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param CPSpecificationOptionId the primary key of the current cp specification option
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp specification option
	 * @throws NoSuchCPSpecificationOptionException if a cp specification option with the primary key could not be found
	 */
	@Override
	public CPSpecificationOption[] findByUuid_C_PrevAndNext(
			long CPSpecificationOptionId, String uuid, long companyId,
			OrderByComparator<CPSpecificationOption> orderByComparator)
		throws NoSuchCPSpecificationOptionException {

		uuid = Objects.toString(uuid, "");

		CPSpecificationOption cpSpecificationOption = findByPrimaryKey(
			CPSpecificationOptionId);

		Session session = null;

		try {
			session = openSession();

			CPSpecificationOption[] array = new CPSpecificationOptionImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, cpSpecificationOption, uuid, companyId,
				orderByComparator, true);

			array[1] = cpSpecificationOption;

			array[2] = getByUuid_C_PrevAndNext(
				session, cpSpecificationOption, uuid, companyId,
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

	protected CPSpecificationOption getByUuid_C_PrevAndNext(
		Session session, CPSpecificationOption cpSpecificationOption,
		String uuid, long companyId,
		OrderByComparator<CPSpecificationOption> orderByComparator,
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

		query.append(_SQL_SELECT_CPSPECIFICATIONOPTION_WHERE);

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
			query.append(CPSpecificationOptionModelImpl.ORDER_BY_JPQL);
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
						cpSpecificationOption)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPSpecificationOption> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the cp specification options that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching cp specification options that the user has permission to view
	 */
	@Override
	public List<CPSpecificationOption> filterFindByUuid_C(
		String uuid, long companyId) {

		return filterFindByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp specification options that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPSpecificationOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp specification options
	 * @param end the upper bound of the range of cp specification options (not inclusive)
	 * @return the range of matching cp specification options that the user has permission to view
	 */
	@Override
	public List<CPSpecificationOption> filterFindByUuid_C(
		String uuid, long companyId, int start, int end) {

		return filterFindByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp specification options that the user has permissions to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPSpecificationOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp specification options
	 * @param end the upper bound of the range of cp specification options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp specification options that the user has permission to view
	 */
	@Override
	public List<CPSpecificationOption> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPSpecificationOption> orderByComparator) {

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
			query.append(_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_NO_INLINE_DISTINCT_WHERE_1);
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
				_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CPSpecificationOptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CPSpecificationOptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPSpecificationOption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(
					_FILTER_ENTITY_ALIAS, CPSpecificationOptionImpl.class);
			}
			else {
				q.addEntity(
					_FILTER_ENTITY_TABLE, CPSpecificationOptionImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindUuid) {
				qPos.add(uuid);
			}

			qPos.add(companyId);

			return (List<CPSpecificationOption>)QueryUtil.list(
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
	 * Returns the cp specification options before and after the current cp specification option in the ordered set of cp specification options that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param CPSpecificationOptionId the primary key of the current cp specification option
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp specification option
	 * @throws NoSuchCPSpecificationOptionException if a cp specification option with the primary key could not be found
	 */
	@Override
	public CPSpecificationOption[] filterFindByUuid_C_PrevAndNext(
			long CPSpecificationOptionId, String uuid, long companyId,
			OrderByComparator<CPSpecificationOption> orderByComparator)
		throws NoSuchCPSpecificationOptionException {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByUuid_C_PrevAndNext(
				CPSpecificationOptionId, uuid, companyId, orderByComparator);
		}

		uuid = Objects.toString(uuid, "");

		CPSpecificationOption cpSpecificationOption = findByPrimaryKey(
			CPSpecificationOptionId);

		Session session = null;

		try {
			session = openSession();

			CPSpecificationOption[] array = new CPSpecificationOptionImpl[3];

			array[0] = filterGetByUuid_C_PrevAndNext(
				session, cpSpecificationOption, uuid, companyId,
				orderByComparator, true);

			array[1] = cpSpecificationOption;

			array[2] = filterGetByUuid_C_PrevAndNext(
				session, cpSpecificationOption, uuid, companyId,
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

	protected CPSpecificationOption filterGetByUuid_C_PrevAndNext(
		Session session, CPSpecificationOption cpSpecificationOption,
		String uuid, long companyId,
		OrderByComparator<CPSpecificationOption> orderByComparator,
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
			query.append(_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_NO_INLINE_DISTINCT_WHERE_1);
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
				_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CPSpecificationOptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CPSpecificationOptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPSpecificationOption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CPSpecificationOptionImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CPSpecificationOptionImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpSpecificationOption)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPSpecificationOption> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp specification options where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CPSpecificationOption cpSpecificationOption :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(cpSpecificationOption);
		}
	}

	/**
	 * Returns the number of cp specification options where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching cp specification options
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPSPECIFICATIONOPTION_WHERE);

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
	 * Returns the number of cp specification options that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching cp specification options that the user has permission to view
	 */
	@Override
	public int filterCountByUuid_C(String uuid, long companyId) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return countByUuid_C(uuid, companyId);
		}

		uuid = Objects.toString(uuid, "");

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_CPSPECIFICATIONOPTION_WHERE);

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
			query.toString(), CPSpecificationOption.class.getName(),
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
		"cpSpecificationOption.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(cpSpecificationOption.uuid IS NULL OR cpSpecificationOption.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_2_SQL =
		"cpSpecificationOption.uuid_ = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3_SQL =
		"(cpSpecificationOption.uuid_ IS NULL OR cpSpecificationOption.uuid_ = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"cpSpecificationOption.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the cp specification options where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching cp specification options
	 */
	@Override
	public List<CPSpecificationOption> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp specification options where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPSpecificationOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp specification options
	 * @param end the upper bound of the range of cp specification options (not inclusive)
	 * @return the range of matching cp specification options
	 */
	@Override
	public List<CPSpecificationOption> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp specification options where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPSpecificationOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp specification options
	 * @param end the upper bound of the range of cp specification options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp specification options
	 */
	@Override
	public List<CPSpecificationOption> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CPSpecificationOption> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp specification options where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPSpecificationOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp specification options
	 * @param end the upper bound of the range of cp specification options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp specification options
	 */
	@Override
	public List<CPSpecificationOption> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CPSpecificationOption> orderByComparator,
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

		List<CPSpecificationOption> list = null;

		if (retrieveFromCache) {
			list = (List<CPSpecificationOption>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPSpecificationOption cpSpecificationOption : list) {
					if ((companyId != cpSpecificationOption.getCompanyId())) {
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

			query.append(_SQL_SELECT_CPSPECIFICATIONOPTION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CPSpecificationOptionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<CPSpecificationOption>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPSpecificationOption>)QueryUtil.list(
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
	 * Returns the first cp specification option in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp specification option
	 * @throws NoSuchCPSpecificationOptionException if a matching cp specification option could not be found
	 */
	@Override
	public CPSpecificationOption findByCompanyId_First(
			long companyId,
			OrderByComparator<CPSpecificationOption> orderByComparator)
		throws NoSuchCPSpecificationOptionException {

		CPSpecificationOption cpSpecificationOption = fetchByCompanyId_First(
			companyId, orderByComparator);

		if (cpSpecificationOption != null) {
			return cpSpecificationOption;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPSpecificationOptionException(msg.toString());
	}

	/**
	 * Returns the first cp specification option in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp specification option, or <code>null</code> if a matching cp specification option could not be found
	 */
	@Override
	public CPSpecificationOption fetchByCompanyId_First(
		long companyId,
		OrderByComparator<CPSpecificationOption> orderByComparator) {

		List<CPSpecificationOption> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp specification option in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp specification option
	 * @throws NoSuchCPSpecificationOptionException if a matching cp specification option could not be found
	 */
	@Override
	public CPSpecificationOption findByCompanyId_Last(
			long companyId,
			OrderByComparator<CPSpecificationOption> orderByComparator)
		throws NoSuchCPSpecificationOptionException {

		CPSpecificationOption cpSpecificationOption = fetchByCompanyId_Last(
			companyId, orderByComparator);

		if (cpSpecificationOption != null) {
			return cpSpecificationOption;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPSpecificationOptionException(msg.toString());
	}

	/**
	 * Returns the last cp specification option in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp specification option, or <code>null</code> if a matching cp specification option could not be found
	 */
	@Override
	public CPSpecificationOption fetchByCompanyId_Last(
		long companyId,
		OrderByComparator<CPSpecificationOption> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<CPSpecificationOption> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp specification options before and after the current cp specification option in the ordered set where companyId = &#63;.
	 *
	 * @param CPSpecificationOptionId the primary key of the current cp specification option
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp specification option
	 * @throws NoSuchCPSpecificationOptionException if a cp specification option with the primary key could not be found
	 */
	@Override
	public CPSpecificationOption[] findByCompanyId_PrevAndNext(
			long CPSpecificationOptionId, long companyId,
			OrderByComparator<CPSpecificationOption> orderByComparator)
		throws NoSuchCPSpecificationOptionException {

		CPSpecificationOption cpSpecificationOption = findByPrimaryKey(
			CPSpecificationOptionId);

		Session session = null;

		try {
			session = openSession();

			CPSpecificationOption[] array = new CPSpecificationOptionImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, cpSpecificationOption, companyId, orderByComparator,
				true);

			array[1] = cpSpecificationOption;

			array[2] = getByCompanyId_PrevAndNext(
				session, cpSpecificationOption, companyId, orderByComparator,
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

	protected CPSpecificationOption getByCompanyId_PrevAndNext(
		Session session, CPSpecificationOption cpSpecificationOption,
		long companyId,
		OrderByComparator<CPSpecificationOption> orderByComparator,
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

		query.append(_SQL_SELECT_CPSPECIFICATIONOPTION_WHERE);

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
			query.append(CPSpecificationOptionModelImpl.ORDER_BY_JPQL);
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
						cpSpecificationOption)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPSpecificationOption> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the cp specification options that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching cp specification options that the user has permission to view
	 */
	@Override
	public List<CPSpecificationOption> filterFindByCompanyId(long companyId) {
		return filterFindByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp specification options that the user has permission to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPSpecificationOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp specification options
	 * @param end the upper bound of the range of cp specification options (not inclusive)
	 * @return the range of matching cp specification options that the user has permission to view
	 */
	@Override
	public List<CPSpecificationOption> filterFindByCompanyId(
		long companyId, int start, int end) {

		return filterFindByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp specification options that the user has permissions to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPSpecificationOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp specification options
	 * @param end the upper bound of the range of cp specification options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp specification options that the user has permission to view
	 */
	@Override
	public List<CPSpecificationOption> filterFindByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CPSpecificationOption> orderByComparator) {

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
			query.append(_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CPSpecificationOptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CPSpecificationOptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPSpecificationOption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(
					_FILTER_ENTITY_ALIAS, CPSpecificationOptionImpl.class);
			}
			else {
				q.addEntity(
					_FILTER_ENTITY_TABLE, CPSpecificationOptionImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			return (List<CPSpecificationOption>)QueryUtil.list(
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
	 * Returns the cp specification options before and after the current cp specification option in the ordered set of cp specification options that the user has permission to view where companyId = &#63;.
	 *
	 * @param CPSpecificationOptionId the primary key of the current cp specification option
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp specification option
	 * @throws NoSuchCPSpecificationOptionException if a cp specification option with the primary key could not be found
	 */
	@Override
	public CPSpecificationOption[] filterFindByCompanyId_PrevAndNext(
			long CPSpecificationOptionId, long companyId,
			OrderByComparator<CPSpecificationOption> orderByComparator)
		throws NoSuchCPSpecificationOptionException {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByCompanyId_PrevAndNext(
				CPSpecificationOptionId, companyId, orderByComparator);
		}

		CPSpecificationOption cpSpecificationOption = findByPrimaryKey(
			CPSpecificationOptionId);

		Session session = null;

		try {
			session = openSession();

			CPSpecificationOption[] array = new CPSpecificationOptionImpl[3];

			array[0] = filterGetByCompanyId_PrevAndNext(
				session, cpSpecificationOption, companyId, orderByComparator,
				true);

			array[1] = cpSpecificationOption;

			array[2] = filterGetByCompanyId_PrevAndNext(
				session, cpSpecificationOption, companyId, orderByComparator,
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

	protected CPSpecificationOption filterGetByCompanyId_PrevAndNext(
		Session session, CPSpecificationOption cpSpecificationOption,
		long companyId,
		OrderByComparator<CPSpecificationOption> orderByComparator,
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
			query.append(_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CPSpecificationOptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CPSpecificationOptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPSpecificationOption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CPSpecificationOptionImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CPSpecificationOptionImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpSpecificationOption)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPSpecificationOption> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp specification options where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (CPSpecificationOption cpSpecificationOption :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cpSpecificationOption);
		}
	}

	/**
	 * Returns the number of cp specification options where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching cp specification options
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] {companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPSPECIFICATIONOPTION_WHERE);

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
	 * Returns the number of cp specification options that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching cp specification options that the user has permission to view
	 */
	@Override
	public int filterCountByCompanyId(long companyId) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return countByCompanyId(companyId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_CPSPECIFICATIONOPTION_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPSpecificationOption.class.getName(),
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
		"cpSpecificationOption.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByCPOptionCategoryId;
	private FinderPath _finderPathWithoutPaginationFindByCPOptionCategoryId;
	private FinderPath _finderPathCountByCPOptionCategoryId;

	/**
	 * Returns all the cp specification options where CPOptionCategoryId = &#63;.
	 *
	 * @param CPOptionCategoryId the cp option category ID
	 * @return the matching cp specification options
	 */
	@Override
	public List<CPSpecificationOption> findByCPOptionCategoryId(
		long CPOptionCategoryId) {

		return findByCPOptionCategoryId(
			CPOptionCategoryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp specification options where CPOptionCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPSpecificationOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPOptionCategoryId the cp option category ID
	 * @param start the lower bound of the range of cp specification options
	 * @param end the upper bound of the range of cp specification options (not inclusive)
	 * @return the range of matching cp specification options
	 */
	@Override
	public List<CPSpecificationOption> findByCPOptionCategoryId(
		long CPOptionCategoryId, int start, int end) {

		return findByCPOptionCategoryId(CPOptionCategoryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp specification options where CPOptionCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPSpecificationOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPOptionCategoryId the cp option category ID
	 * @param start the lower bound of the range of cp specification options
	 * @param end the upper bound of the range of cp specification options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp specification options
	 */
	@Override
	public List<CPSpecificationOption> findByCPOptionCategoryId(
		long CPOptionCategoryId, int start, int end,
		OrderByComparator<CPSpecificationOption> orderByComparator) {

		return findByCPOptionCategoryId(
			CPOptionCategoryId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp specification options where CPOptionCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPSpecificationOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPOptionCategoryId the cp option category ID
	 * @param start the lower bound of the range of cp specification options
	 * @param end the upper bound of the range of cp specification options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp specification options
	 */
	@Override
	public List<CPSpecificationOption> findByCPOptionCategoryId(
		long CPOptionCategoryId, int start, int end,
		OrderByComparator<CPSpecificationOption> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByCPOptionCategoryId;
			finderArgs = new Object[] {CPOptionCategoryId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByCPOptionCategoryId;
			finderArgs = new Object[] {
				CPOptionCategoryId, start, end, orderByComparator
			};
		}

		List<CPSpecificationOption> list = null;

		if (retrieveFromCache) {
			list = (List<CPSpecificationOption>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPSpecificationOption cpSpecificationOption : list) {
					if ((CPOptionCategoryId !=
							cpSpecificationOption.getCPOptionCategoryId())) {

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

			query.append(_SQL_SELECT_CPSPECIFICATIONOPTION_WHERE);

			query.append(
				_FINDER_COLUMN_CPOPTIONCATEGORYID_CPOPTIONCATEGORYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CPSpecificationOptionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPOptionCategoryId);

				if (!pagination) {
					list = (List<CPSpecificationOption>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPSpecificationOption>)QueryUtil.list(
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
	 * Returns the first cp specification option in the ordered set where CPOptionCategoryId = &#63;.
	 *
	 * @param CPOptionCategoryId the cp option category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp specification option
	 * @throws NoSuchCPSpecificationOptionException if a matching cp specification option could not be found
	 */
	@Override
	public CPSpecificationOption findByCPOptionCategoryId_First(
			long CPOptionCategoryId,
			OrderByComparator<CPSpecificationOption> orderByComparator)
		throws NoSuchCPSpecificationOptionException {

		CPSpecificationOption cpSpecificationOption =
			fetchByCPOptionCategoryId_First(
				CPOptionCategoryId, orderByComparator);

		if (cpSpecificationOption != null) {
			return cpSpecificationOption;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPOptionCategoryId=");
		msg.append(CPOptionCategoryId);

		msg.append("}");

		throw new NoSuchCPSpecificationOptionException(msg.toString());
	}

	/**
	 * Returns the first cp specification option in the ordered set where CPOptionCategoryId = &#63;.
	 *
	 * @param CPOptionCategoryId the cp option category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp specification option, or <code>null</code> if a matching cp specification option could not be found
	 */
	@Override
	public CPSpecificationOption fetchByCPOptionCategoryId_First(
		long CPOptionCategoryId,
		OrderByComparator<CPSpecificationOption> orderByComparator) {

		List<CPSpecificationOption> list = findByCPOptionCategoryId(
			CPOptionCategoryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp specification option in the ordered set where CPOptionCategoryId = &#63;.
	 *
	 * @param CPOptionCategoryId the cp option category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp specification option
	 * @throws NoSuchCPSpecificationOptionException if a matching cp specification option could not be found
	 */
	@Override
	public CPSpecificationOption findByCPOptionCategoryId_Last(
			long CPOptionCategoryId,
			OrderByComparator<CPSpecificationOption> orderByComparator)
		throws NoSuchCPSpecificationOptionException {

		CPSpecificationOption cpSpecificationOption =
			fetchByCPOptionCategoryId_Last(
				CPOptionCategoryId, orderByComparator);

		if (cpSpecificationOption != null) {
			return cpSpecificationOption;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPOptionCategoryId=");
		msg.append(CPOptionCategoryId);

		msg.append("}");

		throw new NoSuchCPSpecificationOptionException(msg.toString());
	}

	/**
	 * Returns the last cp specification option in the ordered set where CPOptionCategoryId = &#63;.
	 *
	 * @param CPOptionCategoryId the cp option category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp specification option, or <code>null</code> if a matching cp specification option could not be found
	 */
	@Override
	public CPSpecificationOption fetchByCPOptionCategoryId_Last(
		long CPOptionCategoryId,
		OrderByComparator<CPSpecificationOption> orderByComparator) {

		int count = countByCPOptionCategoryId(CPOptionCategoryId);

		if (count == 0) {
			return null;
		}

		List<CPSpecificationOption> list = findByCPOptionCategoryId(
			CPOptionCategoryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp specification options before and after the current cp specification option in the ordered set where CPOptionCategoryId = &#63;.
	 *
	 * @param CPSpecificationOptionId the primary key of the current cp specification option
	 * @param CPOptionCategoryId the cp option category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp specification option
	 * @throws NoSuchCPSpecificationOptionException if a cp specification option with the primary key could not be found
	 */
	@Override
	public CPSpecificationOption[] findByCPOptionCategoryId_PrevAndNext(
			long CPSpecificationOptionId, long CPOptionCategoryId,
			OrderByComparator<CPSpecificationOption> orderByComparator)
		throws NoSuchCPSpecificationOptionException {

		CPSpecificationOption cpSpecificationOption = findByPrimaryKey(
			CPSpecificationOptionId);

		Session session = null;

		try {
			session = openSession();

			CPSpecificationOption[] array = new CPSpecificationOptionImpl[3];

			array[0] = getByCPOptionCategoryId_PrevAndNext(
				session, cpSpecificationOption, CPOptionCategoryId,
				orderByComparator, true);

			array[1] = cpSpecificationOption;

			array[2] = getByCPOptionCategoryId_PrevAndNext(
				session, cpSpecificationOption, CPOptionCategoryId,
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

	protected CPSpecificationOption getByCPOptionCategoryId_PrevAndNext(
		Session session, CPSpecificationOption cpSpecificationOption,
		long CPOptionCategoryId,
		OrderByComparator<CPSpecificationOption> orderByComparator,
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

		query.append(_SQL_SELECT_CPSPECIFICATIONOPTION_WHERE);

		query.append(_FINDER_COLUMN_CPOPTIONCATEGORYID_CPOPTIONCATEGORYID_2);

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
			query.append(CPSpecificationOptionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(CPOptionCategoryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpSpecificationOption)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPSpecificationOption> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the cp specification options that the user has permission to view where CPOptionCategoryId = &#63;.
	 *
	 * @param CPOptionCategoryId the cp option category ID
	 * @return the matching cp specification options that the user has permission to view
	 */
	@Override
	public List<CPSpecificationOption> filterFindByCPOptionCategoryId(
		long CPOptionCategoryId) {

		return filterFindByCPOptionCategoryId(
			CPOptionCategoryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp specification options that the user has permission to view where CPOptionCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPSpecificationOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPOptionCategoryId the cp option category ID
	 * @param start the lower bound of the range of cp specification options
	 * @param end the upper bound of the range of cp specification options (not inclusive)
	 * @return the range of matching cp specification options that the user has permission to view
	 */
	@Override
	public List<CPSpecificationOption> filterFindByCPOptionCategoryId(
		long CPOptionCategoryId, int start, int end) {

		return filterFindByCPOptionCategoryId(
			CPOptionCategoryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp specification options that the user has permissions to view where CPOptionCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPSpecificationOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPOptionCategoryId the cp option category ID
	 * @param start the lower bound of the range of cp specification options
	 * @param end the upper bound of the range of cp specification options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp specification options that the user has permission to view
	 */
	@Override
	public List<CPSpecificationOption> filterFindByCPOptionCategoryId(
		long CPOptionCategoryId, int start, int end,
		OrderByComparator<CPSpecificationOption> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByCPOptionCategoryId(
				CPOptionCategoryId, start, end, orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_CPOPTIONCATEGORYID_CPOPTIONCATEGORYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CPSpecificationOptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CPSpecificationOptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPSpecificationOption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(
					_FILTER_ENTITY_ALIAS, CPSpecificationOptionImpl.class);
			}
			else {
				q.addEntity(
					_FILTER_ENTITY_TABLE, CPSpecificationOptionImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(CPOptionCategoryId);

			return (List<CPSpecificationOption>)QueryUtil.list(
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
	 * Returns the cp specification options before and after the current cp specification option in the ordered set of cp specification options that the user has permission to view where CPOptionCategoryId = &#63;.
	 *
	 * @param CPSpecificationOptionId the primary key of the current cp specification option
	 * @param CPOptionCategoryId the cp option category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp specification option
	 * @throws NoSuchCPSpecificationOptionException if a cp specification option with the primary key could not be found
	 */
	@Override
	public CPSpecificationOption[] filterFindByCPOptionCategoryId_PrevAndNext(
			long CPSpecificationOptionId, long CPOptionCategoryId,
			OrderByComparator<CPSpecificationOption> orderByComparator)
		throws NoSuchCPSpecificationOptionException {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByCPOptionCategoryId_PrevAndNext(
				CPSpecificationOptionId, CPOptionCategoryId, orderByComparator);
		}

		CPSpecificationOption cpSpecificationOption = findByPrimaryKey(
			CPSpecificationOptionId);

		Session session = null;

		try {
			session = openSession();

			CPSpecificationOption[] array = new CPSpecificationOptionImpl[3];

			array[0] = filterGetByCPOptionCategoryId_PrevAndNext(
				session, cpSpecificationOption, CPOptionCategoryId,
				orderByComparator, true);

			array[1] = cpSpecificationOption;

			array[2] = filterGetByCPOptionCategoryId_PrevAndNext(
				session, cpSpecificationOption, CPOptionCategoryId,
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

	protected CPSpecificationOption filterGetByCPOptionCategoryId_PrevAndNext(
		Session session, CPSpecificationOption cpSpecificationOption,
		long CPOptionCategoryId,
		OrderByComparator<CPSpecificationOption> orderByComparator,
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
			query.append(_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_CPOPTIONCATEGORYID_CPOPTIONCATEGORYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CPSpecificationOptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CPSpecificationOptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPSpecificationOption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CPSpecificationOptionImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CPSpecificationOptionImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(CPOptionCategoryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpSpecificationOption)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPSpecificationOption> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp specification options where CPOptionCategoryId = &#63; from the database.
	 *
	 * @param CPOptionCategoryId the cp option category ID
	 */
	@Override
	public void removeByCPOptionCategoryId(long CPOptionCategoryId) {
		for (CPSpecificationOption cpSpecificationOption :
				findByCPOptionCategoryId(
					CPOptionCategoryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(cpSpecificationOption);
		}
	}

	/**
	 * Returns the number of cp specification options where CPOptionCategoryId = &#63;.
	 *
	 * @param CPOptionCategoryId the cp option category ID
	 * @return the number of matching cp specification options
	 */
	@Override
	public int countByCPOptionCategoryId(long CPOptionCategoryId) {
		FinderPath finderPath = _finderPathCountByCPOptionCategoryId;

		Object[] finderArgs = new Object[] {CPOptionCategoryId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPSPECIFICATIONOPTION_WHERE);

			query.append(
				_FINDER_COLUMN_CPOPTIONCATEGORYID_CPOPTIONCATEGORYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPOptionCategoryId);

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
	 * Returns the number of cp specification options that the user has permission to view where CPOptionCategoryId = &#63;.
	 *
	 * @param CPOptionCategoryId the cp option category ID
	 * @return the number of matching cp specification options that the user has permission to view
	 */
	@Override
	public int filterCountByCPOptionCategoryId(long CPOptionCategoryId) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByCPOptionCategoryId(CPOptionCategoryId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_CPSPECIFICATIONOPTION_WHERE);

		query.append(_FINDER_COLUMN_CPOPTIONCATEGORYID_CPOPTIONCATEGORYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPSpecificationOption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(CPOptionCategoryId);

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

	private static final String
		_FINDER_COLUMN_CPOPTIONCATEGORYID_CPOPTIONCATEGORYID_2 =
			"cpSpecificationOption.CPOptionCategoryId = ?";

	private FinderPath _finderPathFetchByC_K;
	private FinderPath _finderPathCountByC_K;

	/**
	 * Returns the cp specification option where companyId = &#63; and key = &#63; or throws a <code>NoSuchCPSpecificationOptionException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param key the key
	 * @return the matching cp specification option
	 * @throws NoSuchCPSpecificationOptionException if a matching cp specification option could not be found
	 */
	@Override
	public CPSpecificationOption findByC_K(long companyId, String key)
		throws NoSuchCPSpecificationOptionException {

		CPSpecificationOption cpSpecificationOption = fetchByC_K(
			companyId, key);

		if (cpSpecificationOption == null) {
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

			throw new NoSuchCPSpecificationOptionException(msg.toString());
		}

		return cpSpecificationOption;
	}

	/**
	 * Returns the cp specification option where companyId = &#63; and key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param key the key
	 * @return the matching cp specification option, or <code>null</code> if a matching cp specification option could not be found
	 */
	@Override
	public CPSpecificationOption fetchByC_K(long companyId, String key) {
		return fetchByC_K(companyId, key, true);
	}

	/**
	 * Returns the cp specification option where companyId = &#63; and key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param key the key
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching cp specification option, or <code>null</code> if a matching cp specification option could not be found
	 */
	@Override
	public CPSpecificationOption fetchByC_K(
		long companyId, String key, boolean retrieveFromCache) {

		key = Objects.toString(key, "");

		Object[] finderArgs = new Object[] {companyId, key};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_K, finderArgs, this);
		}

		if (result instanceof CPSpecificationOption) {
			CPSpecificationOption cpSpecificationOption =
				(CPSpecificationOption)result;

			if ((companyId != cpSpecificationOption.getCompanyId()) ||
				!Objects.equals(key, cpSpecificationOption.getKey())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CPSPECIFICATIONOPTION_WHERE);

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

				List<CPSpecificationOption> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByC_K, finderArgs, list);
				}
				else {
					CPSpecificationOption cpSpecificationOption = list.get(0);

					result = cpSpecificationOption;

					cacheResult(cpSpecificationOption);
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
			return (CPSpecificationOption)result;
		}
	}

	/**
	 * Removes the cp specification option where companyId = &#63; and key = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param key the key
	 * @return the cp specification option that was removed
	 */
	@Override
	public CPSpecificationOption removeByC_K(long companyId, String key)
		throws NoSuchCPSpecificationOptionException {

		CPSpecificationOption cpSpecificationOption = findByC_K(companyId, key);

		return remove(cpSpecificationOption);
	}

	/**
	 * Returns the number of cp specification options where companyId = &#63; and key = &#63;.
	 *
	 * @param companyId the company ID
	 * @param key the key
	 * @return the number of matching cp specification options
	 */
	@Override
	public int countByC_K(long companyId, String key) {
		key = Objects.toString(key, "");

		FinderPath finderPath = _finderPathCountByC_K;

		Object[] finderArgs = new Object[] {companyId, key};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPSPECIFICATIONOPTION_WHERE);

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
		"cpSpecificationOption.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_K_KEY_2 =
		"cpSpecificationOption.key = ?";

	private static final String _FINDER_COLUMN_C_K_KEY_3 =
		"(cpSpecificationOption.key IS NULL OR cpSpecificationOption.key = '')";

	public CPSpecificationOptionPersistenceImpl() {
		setModelClass(CPSpecificationOption.class);

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
	 * Caches the cp specification option in the entity cache if it is enabled.
	 *
	 * @param cpSpecificationOption the cp specification option
	 */
	@Override
	public void cacheResult(CPSpecificationOption cpSpecificationOption) {
		entityCache.putResult(
			CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPSpecificationOptionImpl.class,
			cpSpecificationOption.getPrimaryKey(), cpSpecificationOption);

		finderCache.putResult(
			_finderPathFetchByC_K,
			new Object[] {
				cpSpecificationOption.getCompanyId(),
				cpSpecificationOption.getKey()
			},
			cpSpecificationOption);

		cpSpecificationOption.resetOriginalValues();
	}

	/**
	 * Caches the cp specification options in the entity cache if it is enabled.
	 *
	 * @param cpSpecificationOptions the cp specification options
	 */
	@Override
	public void cacheResult(
		List<CPSpecificationOption> cpSpecificationOptions) {

		for (CPSpecificationOption cpSpecificationOption :
				cpSpecificationOptions) {

			if (entityCache.getResult(
					CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
					CPSpecificationOptionImpl.class,
					cpSpecificationOption.getPrimaryKey()) == null) {

				cacheResult(cpSpecificationOption);
			}
			else {
				cpSpecificationOption.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cp specification options.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CPSpecificationOptionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cp specification option.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CPSpecificationOption cpSpecificationOption) {
		entityCache.removeResult(
			CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPSpecificationOptionImpl.class,
			cpSpecificationOption.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(CPSpecificationOptionModelImpl)cpSpecificationOption, true);
	}

	@Override
	public void clearCache(List<CPSpecificationOption> cpSpecificationOptions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CPSpecificationOption cpSpecificationOption :
				cpSpecificationOptions) {

			entityCache.removeResult(
				CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
				CPSpecificationOptionImpl.class,
				cpSpecificationOption.getPrimaryKey());

			clearUniqueFindersCache(
				(CPSpecificationOptionModelImpl)cpSpecificationOption, true);
		}
	}

	protected void cacheUniqueFindersCache(
		CPSpecificationOptionModelImpl cpSpecificationOptionModelImpl) {

		Object[] args = new Object[] {
			cpSpecificationOptionModelImpl.getCompanyId(),
			cpSpecificationOptionModelImpl.getKey()
		};

		finderCache.putResult(
			_finderPathCountByC_K, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_K, args, cpSpecificationOptionModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CPSpecificationOptionModelImpl cpSpecificationOptionModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				cpSpecificationOptionModelImpl.getCompanyId(),
				cpSpecificationOptionModelImpl.getKey()
			};

			finderCache.removeResult(_finderPathCountByC_K, args);
			finderCache.removeResult(_finderPathFetchByC_K, args);
		}

		if ((cpSpecificationOptionModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_K.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				cpSpecificationOptionModelImpl.getOriginalCompanyId(),
				cpSpecificationOptionModelImpl.getOriginalKey()
			};

			finderCache.removeResult(_finderPathCountByC_K, args);
			finderCache.removeResult(_finderPathFetchByC_K, args);
		}
	}

	/**
	 * Creates a new cp specification option with the primary key. Does not add the cp specification option to the database.
	 *
	 * @param CPSpecificationOptionId the primary key for the new cp specification option
	 * @return the new cp specification option
	 */
	@Override
	public CPSpecificationOption create(long CPSpecificationOptionId) {
		CPSpecificationOption cpSpecificationOption =
			new CPSpecificationOptionImpl();

		cpSpecificationOption.setNew(true);
		cpSpecificationOption.setPrimaryKey(CPSpecificationOptionId);

		String uuid = PortalUUIDUtil.generate();

		cpSpecificationOption.setUuid(uuid);

		cpSpecificationOption.setCompanyId(CompanyThreadLocal.getCompanyId());

		return cpSpecificationOption;
	}

	/**
	 * Removes the cp specification option with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPSpecificationOptionId the primary key of the cp specification option
	 * @return the cp specification option that was removed
	 * @throws NoSuchCPSpecificationOptionException if a cp specification option with the primary key could not be found
	 */
	@Override
	public CPSpecificationOption remove(long CPSpecificationOptionId)
		throws NoSuchCPSpecificationOptionException {

		return remove((Serializable)CPSpecificationOptionId);
	}

	/**
	 * Removes the cp specification option with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cp specification option
	 * @return the cp specification option that was removed
	 * @throws NoSuchCPSpecificationOptionException if a cp specification option with the primary key could not be found
	 */
	@Override
	public CPSpecificationOption remove(Serializable primaryKey)
		throws NoSuchCPSpecificationOptionException {

		Session session = null;

		try {
			session = openSession();

			CPSpecificationOption cpSpecificationOption =
				(CPSpecificationOption)session.get(
					CPSpecificationOptionImpl.class, primaryKey);

			if (cpSpecificationOption == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCPSpecificationOptionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(cpSpecificationOption);
		}
		catch (NoSuchCPSpecificationOptionException nsee) {
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
	protected CPSpecificationOption removeImpl(
		CPSpecificationOption cpSpecificationOption) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cpSpecificationOption)) {
				cpSpecificationOption = (CPSpecificationOption)session.get(
					CPSpecificationOptionImpl.class,
					cpSpecificationOption.getPrimaryKeyObj());
			}

			if (cpSpecificationOption != null) {
				session.delete(cpSpecificationOption);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cpSpecificationOption != null) {
			clearCache(cpSpecificationOption);
		}

		return cpSpecificationOption;
	}

	@Override
	public CPSpecificationOption updateImpl(
		CPSpecificationOption cpSpecificationOption) {

		boolean isNew = cpSpecificationOption.isNew();

		if (!(cpSpecificationOption instanceof
				CPSpecificationOptionModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(cpSpecificationOption.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					cpSpecificationOption);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in cpSpecificationOption proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CPSpecificationOption implementation " +
					cpSpecificationOption.getClass());
		}

		CPSpecificationOptionModelImpl cpSpecificationOptionModelImpl =
			(CPSpecificationOptionModelImpl)cpSpecificationOption;

		if (Validator.isNull(cpSpecificationOption.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			cpSpecificationOption.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (cpSpecificationOption.getCreateDate() == null)) {
			if (serviceContext == null) {
				cpSpecificationOption.setCreateDate(now);
			}
			else {
				cpSpecificationOption.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!cpSpecificationOptionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				cpSpecificationOption.setModifiedDate(now);
			}
			else {
				cpSpecificationOption.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (cpSpecificationOption.isNew()) {
				session.save(cpSpecificationOption);

				cpSpecificationOption.setNew(false);
			}
			else {
				cpSpecificationOption = (CPSpecificationOption)session.merge(
					cpSpecificationOption);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CPSpecificationOptionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				cpSpecificationOptionModelImpl.getUuid()
			};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				cpSpecificationOptionModelImpl.getUuid(),
				cpSpecificationOptionModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {cpSpecificationOptionModelImpl.getCompanyId()};

			finderCache.removeResult(_finderPathCountByCompanyId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCompanyId, args);

			args = new Object[] {
				cpSpecificationOptionModelImpl.getCPOptionCategoryId()
			};

			finderCache.removeResult(
				_finderPathCountByCPOptionCategoryId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCPOptionCategoryId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((cpSpecificationOptionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpSpecificationOptionModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {cpSpecificationOptionModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((cpSpecificationOptionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpSpecificationOptionModelImpl.getOriginalUuid(),
					cpSpecificationOptionModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					cpSpecificationOptionModelImpl.getUuid(),
					cpSpecificationOptionModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((cpSpecificationOptionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCompanyId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					cpSpecificationOptionModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);

				args = new Object[] {
					cpSpecificationOptionModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);
			}

			if ((cpSpecificationOptionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCPOptionCategoryId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					cpSpecificationOptionModelImpl.
						getOriginalCPOptionCategoryId()
				};

				finderCache.removeResult(
					_finderPathCountByCPOptionCategoryId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCPOptionCategoryId, args);

				args = new Object[] {
					cpSpecificationOptionModelImpl.getCPOptionCategoryId()
				};

				finderCache.removeResult(
					_finderPathCountByCPOptionCategoryId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCPOptionCategoryId, args);
			}
		}

		entityCache.putResult(
			CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPSpecificationOptionImpl.class,
			cpSpecificationOption.getPrimaryKey(), cpSpecificationOption,
			false);

		clearUniqueFindersCache(cpSpecificationOptionModelImpl, false);
		cacheUniqueFindersCache(cpSpecificationOptionModelImpl);

		cpSpecificationOption.resetOriginalValues();

		return cpSpecificationOption;
	}

	/**
	 * Returns the cp specification option with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp specification option
	 * @return the cp specification option
	 * @throws NoSuchCPSpecificationOptionException if a cp specification option with the primary key could not be found
	 */
	@Override
	public CPSpecificationOption findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCPSpecificationOptionException {

		CPSpecificationOption cpSpecificationOption = fetchByPrimaryKey(
			primaryKey);

		if (cpSpecificationOption == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCPSpecificationOptionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return cpSpecificationOption;
	}

	/**
	 * Returns the cp specification option with the primary key or throws a <code>NoSuchCPSpecificationOptionException</code> if it could not be found.
	 *
	 * @param CPSpecificationOptionId the primary key of the cp specification option
	 * @return the cp specification option
	 * @throws NoSuchCPSpecificationOptionException if a cp specification option with the primary key could not be found
	 */
	@Override
	public CPSpecificationOption findByPrimaryKey(long CPSpecificationOptionId)
		throws NoSuchCPSpecificationOptionException {

		return findByPrimaryKey((Serializable)CPSpecificationOptionId);
	}

	/**
	 * Returns the cp specification option with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp specification option
	 * @return the cp specification option, or <code>null</code> if a cp specification option with the primary key could not be found
	 */
	@Override
	public CPSpecificationOption fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPSpecificationOptionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CPSpecificationOption cpSpecificationOption =
			(CPSpecificationOption)serializable;

		if (cpSpecificationOption == null) {
			Session session = null;

			try {
				session = openSession();

				cpSpecificationOption = (CPSpecificationOption)session.get(
					CPSpecificationOptionImpl.class, primaryKey);

				if (cpSpecificationOption != null) {
					cacheResult(cpSpecificationOption);
				}
				else {
					entityCache.putResult(
						CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
						CPSpecificationOptionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
					CPSpecificationOptionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cpSpecificationOption;
	}

	/**
	 * Returns the cp specification option with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param CPSpecificationOptionId the primary key of the cp specification option
	 * @return the cp specification option, or <code>null</code> if a cp specification option with the primary key could not be found
	 */
	@Override
	public CPSpecificationOption fetchByPrimaryKey(
		long CPSpecificationOptionId) {

		return fetchByPrimaryKey((Serializable)CPSpecificationOptionId);
	}

	@Override
	public Map<Serializable, CPSpecificationOption> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CPSpecificationOption> map =
			new HashMap<Serializable, CPSpecificationOption>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CPSpecificationOption cpSpecificationOption = fetchByPrimaryKey(
				primaryKey);

			if (cpSpecificationOption != null) {
				map.put(primaryKey, cpSpecificationOption);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
				CPSpecificationOptionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CPSpecificationOption)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_CPSPECIFICATIONOPTION_WHERE_PKS_IN);

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

			for (CPSpecificationOption cpSpecificationOption :
					(List<CPSpecificationOption>)q.list()) {

				map.put(
					cpSpecificationOption.getPrimaryKeyObj(),
					cpSpecificationOption);

				cacheResult(cpSpecificationOption);

				uncachedPrimaryKeys.remove(
					cpSpecificationOption.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
					CPSpecificationOptionImpl.class, primaryKey, nullModel);
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
	 * Returns all the cp specification options.
	 *
	 * @return the cp specification options
	 */
	@Override
	public List<CPSpecificationOption> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp specification options.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPSpecificationOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp specification options
	 * @param end the upper bound of the range of cp specification options (not inclusive)
	 * @return the range of cp specification options
	 */
	@Override
	public List<CPSpecificationOption> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp specification options.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPSpecificationOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp specification options
	 * @param end the upper bound of the range of cp specification options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cp specification options
	 */
	@Override
	public List<CPSpecificationOption> findAll(
		int start, int end,
		OrderByComparator<CPSpecificationOption> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp specification options.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPSpecificationOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp specification options
	 * @param end the upper bound of the range of cp specification options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cp specification options
	 */
	@Override
	public List<CPSpecificationOption> findAll(
		int start, int end,
		OrderByComparator<CPSpecificationOption> orderByComparator,
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

		List<CPSpecificationOption> list = null;

		if (retrieveFromCache) {
			list = (List<CPSpecificationOption>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CPSPECIFICATIONOPTION);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CPSPECIFICATIONOPTION;

				if (pagination) {
					sql = sql.concat(
						CPSpecificationOptionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CPSpecificationOption>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPSpecificationOption>)QueryUtil.list(
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
	 * Removes all the cp specification options from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CPSpecificationOption cpSpecificationOption : findAll()) {
			remove(cpSpecificationOption);
		}
	}

	/**
	 * Returns the number of cp specification options.
	 *
	 * @return the number of cp specification options
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CPSPECIFICATIONOPTION);

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
		return CPSpecificationOptionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cp specification option persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPSpecificationOptionModelImpl.FINDER_CACHE_ENABLED,
			CPSpecificationOptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPSpecificationOptionModelImpl.FINDER_CACHE_ENABLED,
			CPSpecificationOptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPSpecificationOptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPSpecificationOptionModelImpl.FINDER_CACHE_ENABLED,
			CPSpecificationOptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPSpecificationOptionModelImpl.FINDER_CACHE_ENABLED,
			CPSpecificationOptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			CPSpecificationOptionModelImpl.UUID_COLUMN_BITMASK |
			CPSpecificationOptionModelImpl.TITLE_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPSpecificationOptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPSpecificationOptionModelImpl.FINDER_CACHE_ENABLED,
			CPSpecificationOptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPSpecificationOptionModelImpl.FINDER_CACHE_ENABLED,
			CPSpecificationOptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			CPSpecificationOptionModelImpl.UUID_COLUMN_BITMASK |
			CPSpecificationOptionModelImpl.COMPANYID_COLUMN_BITMASK |
			CPSpecificationOptionModelImpl.TITLE_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPSpecificationOptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByCompanyId = new FinderPath(
			CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPSpecificationOptionModelImpl.FINDER_CACHE_ENABLED,
			CPSpecificationOptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCompanyId = new FinderPath(
			CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPSpecificationOptionModelImpl.FINDER_CACHE_ENABLED,
			CPSpecificationOptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] {Long.class.getName()},
			CPSpecificationOptionModelImpl.COMPANYID_COLUMN_BITMASK |
			CPSpecificationOptionModelImpl.TITLE_COLUMN_BITMASK);

		_finderPathCountByCompanyId = new FinderPath(
			CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPSpecificationOptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByCPOptionCategoryId = new FinderPath(
			CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPSpecificationOptionModelImpl.FINDER_CACHE_ENABLED,
			CPSpecificationOptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCPOptionCategoryId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCPOptionCategoryId = new FinderPath(
			CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPSpecificationOptionModelImpl.FINDER_CACHE_ENABLED,
			CPSpecificationOptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCPOptionCategoryId", new String[] {Long.class.getName()},
			CPSpecificationOptionModelImpl.CPOPTIONCATEGORYID_COLUMN_BITMASK |
			CPSpecificationOptionModelImpl.TITLE_COLUMN_BITMASK);

		_finderPathCountByCPOptionCategoryId = new FinderPath(
			CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPSpecificationOptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCPOptionCategoryId", new String[] {Long.class.getName()});

		_finderPathFetchByC_K = new FinderPath(
			CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPSpecificationOptionModelImpl.FINDER_CACHE_ENABLED,
			CPSpecificationOptionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_K",
			new String[] {Long.class.getName(), String.class.getName()},
			CPSpecificationOptionModelImpl.COMPANYID_COLUMN_BITMASK |
			CPSpecificationOptionModelImpl.KEY_COLUMN_BITMASK);

		_finderPathCountByC_K = new FinderPath(
			CPSpecificationOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPSpecificationOptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_K",
			new String[] {Long.class.getName(), String.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(CPSpecificationOptionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_CPSPECIFICATIONOPTION =
		"SELECT cpSpecificationOption FROM CPSpecificationOption cpSpecificationOption";

	private static final String _SQL_SELECT_CPSPECIFICATIONOPTION_WHERE_PKS_IN =
		"SELECT cpSpecificationOption FROM CPSpecificationOption cpSpecificationOption WHERE CPSpecificationOptionId IN (";

	private static final String _SQL_SELECT_CPSPECIFICATIONOPTION_WHERE =
		"SELECT cpSpecificationOption FROM CPSpecificationOption cpSpecificationOption WHERE ";

	private static final String _SQL_COUNT_CPSPECIFICATIONOPTION =
		"SELECT COUNT(cpSpecificationOption) FROM CPSpecificationOption cpSpecificationOption";

	private static final String _SQL_COUNT_CPSPECIFICATIONOPTION_WHERE =
		"SELECT COUNT(cpSpecificationOption) FROM CPSpecificationOption cpSpecificationOption WHERE ";

	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN =
		"cpSpecificationOption.CPSpecificationOptionId";

	private static final String _FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_WHERE =
		"SELECT DISTINCT {cpSpecificationOption.*} FROM CPSpecificationOption cpSpecificationOption WHERE ";

	private static final String
		_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_NO_INLINE_DISTINCT_WHERE_1 =
			"SELECT {CPSpecificationOption.*} FROM (SELECT DISTINCT cpSpecificationOption.CPSpecificationOptionId FROM CPSpecificationOption cpSpecificationOption WHERE ";

	private static final String
		_FILTER_SQL_SELECT_CPSPECIFICATIONOPTION_NO_INLINE_DISTINCT_WHERE_2 =
			") TEMP_TABLE INNER JOIN CPSpecificationOption ON TEMP_TABLE.CPSpecificationOptionId = CPSpecificationOption.CPSpecificationOptionId";

	private static final String _FILTER_SQL_COUNT_CPSPECIFICATIONOPTION_WHERE =
		"SELECT COUNT(DISTINCT cpSpecificationOption.CPSpecificationOptionId) AS COUNT_VALUE FROM CPSpecificationOption cpSpecificationOption WHERE ";

	private static final String _FILTER_ENTITY_ALIAS = "cpSpecificationOption";

	private static final String _FILTER_ENTITY_TABLE = "CPSpecificationOption";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"cpSpecificationOption.";

	private static final String _ORDER_BY_ENTITY_TABLE =
		"CPSpecificationOption.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CPSpecificationOption exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CPSpecificationOption exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CPSpecificationOptionPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "key"});

}