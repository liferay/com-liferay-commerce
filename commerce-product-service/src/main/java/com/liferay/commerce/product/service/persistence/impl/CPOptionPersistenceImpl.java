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

import com.liferay.commerce.product.exception.NoSuchCPOptionException;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.model.impl.CPOptionImpl;
import com.liferay.commerce.product.model.impl.CPOptionModelImpl;
import com.liferay.commerce.product.service.persistence.CPOptionPersistence;
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
 * The persistence implementation for the cp option service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @generated
 */
@ProviderType
public class CPOptionPersistenceImpl
	extends BasePersistenceImpl<CPOption> implements CPOptionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CPOptionUtil</code> to access the cp option persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CPOptionImpl.class.getName();

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
	 * Returns all the cp options where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cp options
	 */
	@Override
	public List<CPOption> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp options where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp options
	 * @param end the upper bound of the range of cp options (not inclusive)
	 * @return the range of matching cp options
	 */
	@Override
	public List<CPOption> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp options where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp options
	 * @param end the upper bound of the range of cp options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp options
	 */
	@Override
	public List<CPOption> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPOption> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp options where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp options
	 * @param end the upper bound of the range of cp options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp options
	 */
	@Override
	public List<CPOption> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPOption> orderByComparator,
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

		List<CPOption> list = null;

		if (retrieveFromCache) {
			list = (List<CPOption>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPOption cpOption : list) {
					if (!uuid.equals(cpOption.getUuid())) {
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

			query.append(_SQL_SELECT_CPOPTION_WHERE);

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
				query.append(CPOptionModelImpl.ORDER_BY_JPQL);
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
					list = (List<CPOption>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPOption>)QueryUtil.list(
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
	 * Returns the first cp option in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp option
	 * @throws NoSuchCPOptionException if a matching cp option could not be found
	 */
	@Override
	public CPOption findByUuid_First(
			String uuid, OrderByComparator<CPOption> orderByComparator)
		throws NoSuchCPOptionException {

		CPOption cpOption = fetchByUuid_First(uuid, orderByComparator);

		if (cpOption != null) {
			return cpOption;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCPOptionException(msg.toString());
	}

	/**
	 * Returns the first cp option in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp option, or <code>null</code> if a matching cp option could not be found
	 */
	@Override
	public CPOption fetchByUuid_First(
		String uuid, OrderByComparator<CPOption> orderByComparator) {

		List<CPOption> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp option in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp option
	 * @throws NoSuchCPOptionException if a matching cp option could not be found
	 */
	@Override
	public CPOption findByUuid_Last(
			String uuid, OrderByComparator<CPOption> orderByComparator)
		throws NoSuchCPOptionException {

		CPOption cpOption = fetchByUuid_Last(uuid, orderByComparator);

		if (cpOption != null) {
			return cpOption;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCPOptionException(msg.toString());
	}

	/**
	 * Returns the last cp option in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp option, or <code>null</code> if a matching cp option could not be found
	 */
	@Override
	public CPOption fetchByUuid_Last(
		String uuid, OrderByComparator<CPOption> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CPOption> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp options before and after the current cp option in the ordered set where uuid = &#63;.
	 *
	 * @param CPOptionId the primary key of the current cp option
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp option
	 * @throws NoSuchCPOptionException if a cp option with the primary key could not be found
	 */
	@Override
	public CPOption[] findByUuid_PrevAndNext(
			long CPOptionId, String uuid,
			OrderByComparator<CPOption> orderByComparator)
		throws NoSuchCPOptionException {

		uuid = Objects.toString(uuid, "");

		CPOption cpOption = findByPrimaryKey(CPOptionId);

		Session session = null;

		try {
			session = openSession();

			CPOption[] array = new CPOptionImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, cpOption, uuid, orderByComparator, true);

			array[1] = cpOption;

			array[2] = getByUuid_PrevAndNext(
				session, cpOption, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPOption getByUuid_PrevAndNext(
		Session session, CPOption cpOption, String uuid,
		OrderByComparator<CPOption> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CPOPTION_WHERE);

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
			query.append(CPOptionModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(cpOption)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPOption> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the cp options that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cp options that the user has permission to view
	 */
	@Override
	public List<CPOption> filterFindByUuid(String uuid) {
		return filterFindByUuid(
			uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp options that the user has permission to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp options
	 * @param end the upper bound of the range of cp options (not inclusive)
	 * @return the range of matching cp options that the user has permission to view
	 */
	@Override
	public List<CPOption> filterFindByUuid(String uuid, int start, int end) {
		return filterFindByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp options that the user has permissions to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp options
	 * @param end the upper bound of the range of cp options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp options that the user has permission to view
	 */
	@Override
	public List<CPOption> filterFindByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPOption> orderByComparator) {

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
			query.append(_FILTER_SQL_SELECT_CPOPTION_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_CPOPTION_NO_INLINE_DISTINCT_WHERE_1);
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
				_FILTER_SQL_SELECT_CPOPTION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CPOptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CPOptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPOption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, CPOptionImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, CPOptionImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindUuid) {
				qPos.add(uuid);
			}

			return (List<CPOption>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the cp options before and after the current cp option in the ordered set of cp options that the user has permission to view where uuid = &#63;.
	 *
	 * @param CPOptionId the primary key of the current cp option
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp option
	 * @throws NoSuchCPOptionException if a cp option with the primary key could not be found
	 */
	@Override
	public CPOption[] filterFindByUuid_PrevAndNext(
			long CPOptionId, String uuid,
			OrderByComparator<CPOption> orderByComparator)
		throws NoSuchCPOptionException {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUuid_PrevAndNext(CPOptionId, uuid, orderByComparator);
		}

		uuid = Objects.toString(uuid, "");

		CPOption cpOption = findByPrimaryKey(CPOptionId);

		Session session = null;

		try {
			session = openSession();

			CPOption[] array = new CPOptionImpl[3];

			array[0] = filterGetByUuid_PrevAndNext(
				session, cpOption, uuid, orderByComparator, true);

			array[1] = cpOption;

			array[2] = filterGetByUuid_PrevAndNext(
				session, cpOption, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPOption filterGetByUuid_PrevAndNext(
		Session session, CPOption cpOption, String uuid,
		OrderByComparator<CPOption> orderByComparator, boolean previous) {

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
			query.append(_FILTER_SQL_SELECT_CPOPTION_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_CPOPTION_NO_INLINE_DISTINCT_WHERE_1);
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
				_FILTER_SQL_SELECT_CPOPTION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CPOptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CPOptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPOption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CPOptionImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CPOptionImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(cpOption)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPOption> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp options where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CPOption cpOption :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cpOption);
		}
	}

	/**
	 * Returns the number of cp options where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cp options
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPOPTION_WHERE);

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
	 * Returns the number of cp options that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cp options that the user has permission to view
	 */
	@Override
	public int filterCountByUuid(String uuid) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByUuid(uuid);
		}

		uuid = Objects.toString(uuid, "");

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_CPOPTION_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2_SQL);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPOption.class.getName(),
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
		"cpOption.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(cpOption.uuid IS NULL OR cpOption.uuid = '')";

	private static final String _FINDER_COLUMN_UUID_UUID_2_SQL =
		"cpOption.uuid_ = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3_SQL =
		"(cpOption.uuid_ IS NULL OR cpOption.uuid_ = '')";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the cp options where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching cp options
	 */
	@Override
	public List<CPOption> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp options where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp options
	 * @param end the upper bound of the range of cp options (not inclusive)
	 * @return the range of matching cp options
	 */
	@Override
	public List<CPOption> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp options where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp options
	 * @param end the upper bound of the range of cp options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp options
	 */
	@Override
	public List<CPOption> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPOption> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp options where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp options
	 * @param end the upper bound of the range of cp options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp options
	 */
	@Override
	public List<CPOption> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPOption> orderByComparator,
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

		List<CPOption> list = null;

		if (retrieveFromCache) {
			list = (List<CPOption>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPOption cpOption : list) {
					if (!uuid.equals(cpOption.getUuid()) ||
						(companyId != cpOption.getCompanyId())) {

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

			query.append(_SQL_SELECT_CPOPTION_WHERE);

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
				query.append(CPOptionModelImpl.ORDER_BY_JPQL);
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
					list = (List<CPOption>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPOption>)QueryUtil.list(
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
	 * Returns the first cp option in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp option
	 * @throws NoSuchCPOptionException if a matching cp option could not be found
	 */
	@Override
	public CPOption findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CPOption> orderByComparator)
		throws NoSuchCPOptionException {

		CPOption cpOption = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (cpOption != null) {
			return cpOption;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPOptionException(msg.toString());
	}

	/**
	 * Returns the first cp option in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp option, or <code>null</code> if a matching cp option could not be found
	 */
	@Override
	public CPOption fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CPOption> orderByComparator) {

		List<CPOption> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp option in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp option
	 * @throws NoSuchCPOptionException if a matching cp option could not be found
	 */
	@Override
	public CPOption findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CPOption> orderByComparator)
		throws NoSuchCPOptionException {

		CPOption cpOption = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (cpOption != null) {
			return cpOption;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPOptionException(msg.toString());
	}

	/**
	 * Returns the last cp option in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp option, or <code>null</code> if a matching cp option could not be found
	 */
	@Override
	public CPOption fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CPOption> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CPOption> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp options before and after the current cp option in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param CPOptionId the primary key of the current cp option
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp option
	 * @throws NoSuchCPOptionException if a cp option with the primary key could not be found
	 */
	@Override
	public CPOption[] findByUuid_C_PrevAndNext(
			long CPOptionId, String uuid, long companyId,
			OrderByComparator<CPOption> orderByComparator)
		throws NoSuchCPOptionException {

		uuid = Objects.toString(uuid, "");

		CPOption cpOption = findByPrimaryKey(CPOptionId);

		Session session = null;

		try {
			session = openSession();

			CPOption[] array = new CPOptionImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, cpOption, uuid, companyId, orderByComparator, true);

			array[1] = cpOption;

			array[2] = getByUuid_C_PrevAndNext(
				session, cpOption, uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPOption getByUuid_C_PrevAndNext(
		Session session, CPOption cpOption, String uuid, long companyId,
		OrderByComparator<CPOption> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_CPOPTION_WHERE);

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
			query.append(CPOptionModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(cpOption)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPOption> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the cp options that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching cp options that the user has permission to view
	 */
	@Override
	public List<CPOption> filterFindByUuid_C(String uuid, long companyId) {
		return filterFindByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp options that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp options
	 * @param end the upper bound of the range of cp options (not inclusive)
	 * @return the range of matching cp options that the user has permission to view
	 */
	@Override
	public List<CPOption> filterFindByUuid_C(
		String uuid, long companyId, int start, int end) {

		return filterFindByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp options that the user has permissions to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp options
	 * @param end the upper bound of the range of cp options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp options that the user has permission to view
	 */
	@Override
	public List<CPOption> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPOption> orderByComparator) {

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
			query.append(_FILTER_SQL_SELECT_CPOPTION_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_CPOPTION_NO_INLINE_DISTINCT_WHERE_1);
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
				_FILTER_SQL_SELECT_CPOPTION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CPOptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CPOptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPOption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, CPOptionImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, CPOptionImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindUuid) {
				qPos.add(uuid);
			}

			qPos.add(companyId);

			return (List<CPOption>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the cp options before and after the current cp option in the ordered set of cp options that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param CPOptionId the primary key of the current cp option
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp option
	 * @throws NoSuchCPOptionException if a cp option with the primary key could not be found
	 */
	@Override
	public CPOption[] filterFindByUuid_C_PrevAndNext(
			long CPOptionId, String uuid, long companyId,
			OrderByComparator<CPOption> orderByComparator)
		throws NoSuchCPOptionException {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByUuid_C_PrevAndNext(
				CPOptionId, uuid, companyId, orderByComparator);
		}

		uuid = Objects.toString(uuid, "");

		CPOption cpOption = findByPrimaryKey(CPOptionId);

		Session session = null;

		try {
			session = openSession();

			CPOption[] array = new CPOptionImpl[3];

			array[0] = filterGetByUuid_C_PrevAndNext(
				session, cpOption, uuid, companyId, orderByComparator, true);

			array[1] = cpOption;

			array[2] = filterGetByUuid_C_PrevAndNext(
				session, cpOption, uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPOption filterGetByUuid_C_PrevAndNext(
		Session session, CPOption cpOption, String uuid, long companyId,
		OrderByComparator<CPOption> orderByComparator, boolean previous) {

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
			query.append(_FILTER_SQL_SELECT_CPOPTION_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_CPOPTION_NO_INLINE_DISTINCT_WHERE_1);
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
				_FILTER_SQL_SELECT_CPOPTION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CPOptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CPOptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPOption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CPOptionImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CPOptionImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(cpOption)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPOption> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp options where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CPOption cpOption :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(cpOption);
		}
	}

	/**
	 * Returns the number of cp options where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching cp options
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPOPTION_WHERE);

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
	 * Returns the number of cp options that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching cp options that the user has permission to view
	 */
	@Override
	public int filterCountByUuid_C(String uuid, long companyId) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return countByUuid_C(uuid, companyId);
		}

		uuid = Objects.toString(uuid, "");

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_CPOPTION_WHERE);

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
			query.toString(), CPOption.class.getName(),
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
		"cpOption.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(cpOption.uuid IS NULL OR cpOption.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_2_SQL =
		"cpOption.uuid_ = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3_SQL =
		"(cpOption.uuid_ IS NULL OR cpOption.uuid_ = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"cpOption.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the cp options where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching cp options
	 */
	@Override
	public List<CPOption> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp options where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp options
	 * @param end the upper bound of the range of cp options (not inclusive)
	 * @return the range of matching cp options
	 */
	@Override
	public List<CPOption> findByCompanyId(long companyId, int start, int end) {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp options where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp options
	 * @param end the upper bound of the range of cp options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp options
	 */
	@Override
	public List<CPOption> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CPOption> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp options where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp options
	 * @param end the upper bound of the range of cp options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp options
	 */
	@Override
	public List<CPOption> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CPOption> orderByComparator,
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

		List<CPOption> list = null;

		if (retrieveFromCache) {
			list = (List<CPOption>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPOption cpOption : list) {
					if ((companyId != cpOption.getCompanyId())) {
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

			query.append(_SQL_SELECT_CPOPTION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CPOptionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<CPOption>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPOption>)QueryUtil.list(
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
	 * Returns the first cp option in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp option
	 * @throws NoSuchCPOptionException if a matching cp option could not be found
	 */
	@Override
	public CPOption findByCompanyId_First(
			long companyId, OrderByComparator<CPOption> orderByComparator)
		throws NoSuchCPOptionException {

		CPOption cpOption = fetchByCompanyId_First(
			companyId, orderByComparator);

		if (cpOption != null) {
			return cpOption;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPOptionException(msg.toString());
	}

	/**
	 * Returns the first cp option in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp option, or <code>null</code> if a matching cp option could not be found
	 */
	@Override
	public CPOption fetchByCompanyId_First(
		long companyId, OrderByComparator<CPOption> orderByComparator) {

		List<CPOption> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp option in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp option
	 * @throws NoSuchCPOptionException if a matching cp option could not be found
	 */
	@Override
	public CPOption findByCompanyId_Last(
			long companyId, OrderByComparator<CPOption> orderByComparator)
		throws NoSuchCPOptionException {

		CPOption cpOption = fetchByCompanyId_Last(companyId, orderByComparator);

		if (cpOption != null) {
			return cpOption;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPOptionException(msg.toString());
	}

	/**
	 * Returns the last cp option in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp option, or <code>null</code> if a matching cp option could not be found
	 */
	@Override
	public CPOption fetchByCompanyId_Last(
		long companyId, OrderByComparator<CPOption> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<CPOption> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp options before and after the current cp option in the ordered set where companyId = &#63;.
	 *
	 * @param CPOptionId the primary key of the current cp option
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp option
	 * @throws NoSuchCPOptionException if a cp option with the primary key could not be found
	 */
	@Override
	public CPOption[] findByCompanyId_PrevAndNext(
			long CPOptionId, long companyId,
			OrderByComparator<CPOption> orderByComparator)
		throws NoSuchCPOptionException {

		CPOption cpOption = findByPrimaryKey(CPOptionId);

		Session session = null;

		try {
			session = openSession();

			CPOption[] array = new CPOptionImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, cpOption, companyId, orderByComparator, true);

			array[1] = cpOption;

			array[2] = getByCompanyId_PrevAndNext(
				session, cpOption, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPOption getByCompanyId_PrevAndNext(
		Session session, CPOption cpOption, long companyId,
		OrderByComparator<CPOption> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CPOPTION_WHERE);

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
			query.append(CPOptionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(cpOption)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPOption> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the cp options that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching cp options that the user has permission to view
	 */
	@Override
	public List<CPOption> filterFindByCompanyId(long companyId) {
		return filterFindByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp options that the user has permission to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp options
	 * @param end the upper bound of the range of cp options (not inclusive)
	 * @return the range of matching cp options that the user has permission to view
	 */
	@Override
	public List<CPOption> filterFindByCompanyId(
		long companyId, int start, int end) {

		return filterFindByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp options that the user has permissions to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp options
	 * @param end the upper bound of the range of cp options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp options that the user has permission to view
	 */
	@Override
	public List<CPOption> filterFindByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CPOption> orderByComparator) {

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
			query.append(_FILTER_SQL_SELECT_CPOPTION_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_CPOPTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_CPOPTION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CPOptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CPOptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPOption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, CPOptionImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, CPOptionImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			return (List<CPOption>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the cp options before and after the current cp option in the ordered set of cp options that the user has permission to view where companyId = &#63;.
	 *
	 * @param CPOptionId the primary key of the current cp option
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp option
	 * @throws NoSuchCPOptionException if a cp option with the primary key could not be found
	 */
	@Override
	public CPOption[] filterFindByCompanyId_PrevAndNext(
			long CPOptionId, long companyId,
			OrderByComparator<CPOption> orderByComparator)
		throws NoSuchCPOptionException {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByCompanyId_PrevAndNext(
				CPOptionId, companyId, orderByComparator);
		}

		CPOption cpOption = findByPrimaryKey(CPOptionId);

		Session session = null;

		try {
			session = openSession();

			CPOption[] array = new CPOptionImpl[3];

			array[0] = filterGetByCompanyId_PrevAndNext(
				session, cpOption, companyId, orderByComparator, true);

			array[1] = cpOption;

			array[2] = filterGetByCompanyId_PrevAndNext(
				session, cpOption, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPOption filterGetByCompanyId_PrevAndNext(
		Session session, CPOption cpOption, long companyId,
		OrderByComparator<CPOption> orderByComparator, boolean previous) {

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
			query.append(_FILTER_SQL_SELECT_CPOPTION_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_CPOPTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_CPOPTION_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CPOptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CPOptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPOption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CPOptionImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CPOptionImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(cpOption)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPOption> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp options where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (CPOption cpOption :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cpOption);
		}
	}

	/**
	 * Returns the number of cp options where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching cp options
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] {companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPOPTION_WHERE);

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
	 * Returns the number of cp options that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching cp options that the user has permission to view
	 */
	@Override
	public int filterCountByCompanyId(long companyId) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return countByCompanyId(companyId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_CPOPTION_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CPOption.class.getName(),
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
		"cpOption.companyId = ?";

	private FinderPath _finderPathFetchByC_K;
	private FinderPath _finderPathCountByC_K;

	/**
	 * Returns the cp option where companyId = &#63; and key = &#63; or throws a <code>NoSuchCPOptionException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param key the key
	 * @return the matching cp option
	 * @throws NoSuchCPOptionException if a matching cp option could not be found
	 */
	@Override
	public CPOption findByC_K(long companyId, String key)
		throws NoSuchCPOptionException {

		CPOption cpOption = fetchByC_K(companyId, key);

		if (cpOption == null) {
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

			throw new NoSuchCPOptionException(msg.toString());
		}

		return cpOption;
	}

	/**
	 * Returns the cp option where companyId = &#63; and key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param key the key
	 * @return the matching cp option, or <code>null</code> if a matching cp option could not be found
	 */
	@Override
	public CPOption fetchByC_K(long companyId, String key) {
		return fetchByC_K(companyId, key, true);
	}

	/**
	 * Returns the cp option where companyId = &#63; and key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param key the key
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching cp option, or <code>null</code> if a matching cp option could not be found
	 */
	@Override
	public CPOption fetchByC_K(
		long companyId, String key, boolean retrieveFromCache) {

		key = Objects.toString(key, "");

		Object[] finderArgs = new Object[] {companyId, key};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_K, finderArgs, this);
		}

		if (result instanceof CPOption) {
			CPOption cpOption = (CPOption)result;

			if ((companyId != cpOption.getCompanyId()) ||
				!Objects.equals(key, cpOption.getKey())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CPOPTION_WHERE);

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

				List<CPOption> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByC_K, finderArgs, list);
				}
				else {
					CPOption cpOption = list.get(0);

					result = cpOption;

					cacheResult(cpOption);
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
			return (CPOption)result;
		}
	}

	/**
	 * Removes the cp option where companyId = &#63; and key = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param key the key
	 * @return the cp option that was removed
	 */
	@Override
	public CPOption removeByC_K(long companyId, String key)
		throws NoSuchCPOptionException {

		CPOption cpOption = findByC_K(companyId, key);

		return remove(cpOption);
	}

	/**
	 * Returns the number of cp options where companyId = &#63; and key = &#63;.
	 *
	 * @param companyId the company ID
	 * @param key the key
	 * @return the number of matching cp options
	 */
	@Override
	public int countByC_K(long companyId, String key) {
		key = Objects.toString(key, "");

		FinderPath finderPath = _finderPathCountByC_K;

		Object[] finderArgs = new Object[] {companyId, key};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPOPTION_WHERE);

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
		"cpOption.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_K_KEY_2 = "cpOption.key = ?";

	private static final String _FINDER_COLUMN_C_K_KEY_3 =
		"(cpOption.key IS NULL OR cpOption.key = '')";

	private FinderPath _finderPathFetchByC_ERC;
	private FinderPath _finderPathCountByC_ERC;

	/**
	 * Returns the cp option where companyId = &#63; and externalReferenceCode = &#63; or throws a <code>NoSuchCPOptionException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching cp option
	 * @throws NoSuchCPOptionException if a matching cp option could not be found
	 */
	@Override
	public CPOption findByC_ERC(long companyId, String externalReferenceCode)
		throws NoSuchCPOptionException {

		CPOption cpOption = fetchByC_ERC(companyId, externalReferenceCode);

		if (cpOption == null) {
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

			throw new NoSuchCPOptionException(msg.toString());
		}

		return cpOption;
	}

	/**
	 * Returns the cp option where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching cp option, or <code>null</code> if a matching cp option could not be found
	 */
	@Override
	public CPOption fetchByC_ERC(long companyId, String externalReferenceCode) {
		return fetchByC_ERC(companyId, externalReferenceCode, true);
	}

	/**
	 * Returns the cp option where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching cp option, or <code>null</code> if a matching cp option could not be found
	 */
	@Override
	public CPOption fetchByC_ERC(
		long companyId, String externalReferenceCode,
		boolean retrieveFromCache) {

		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		Object[] finderArgs = new Object[] {companyId, externalReferenceCode};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_ERC, finderArgs, this);
		}

		if (result instanceof CPOption) {
			CPOption cpOption = (CPOption)result;

			if ((companyId != cpOption.getCompanyId()) ||
				!Objects.equals(
					externalReferenceCode,
					cpOption.getExternalReferenceCode())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CPOPTION_WHERE);

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

				List<CPOption> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByC_ERC, finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"CPOptionPersistenceImpl.fetchByC_ERC(long, String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CPOption cpOption = list.get(0);

					result = cpOption;

					cacheResult(cpOption);
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
			return (CPOption)result;
		}
	}

	/**
	 * Removes the cp option where companyId = &#63; and externalReferenceCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the cp option that was removed
	 */
	@Override
	public CPOption removeByC_ERC(long companyId, String externalReferenceCode)
		throws NoSuchCPOptionException {

		CPOption cpOption = findByC_ERC(companyId, externalReferenceCode);

		return remove(cpOption);
	}

	/**
	 * Returns the number of cp options where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the number of matching cp options
	 */
	@Override
	public int countByC_ERC(long companyId, String externalReferenceCode) {
		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		FinderPath finderPath = _finderPathCountByC_ERC;

		Object[] finderArgs = new Object[] {companyId, externalReferenceCode};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPOPTION_WHERE);

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
		"cpOption.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2 =
		"cpOption.externalReferenceCode = ?";

	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3 =
		"(cpOption.externalReferenceCode IS NULL OR cpOption.externalReferenceCode = '')";

	public CPOptionPersistenceImpl() {
		setModelClass(CPOption.class);

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
	 * Caches the cp option in the entity cache if it is enabled.
	 *
	 * @param cpOption the cp option
	 */
	@Override
	public void cacheResult(CPOption cpOption) {
		entityCache.putResult(
			CPOptionModelImpl.ENTITY_CACHE_ENABLED, CPOptionImpl.class,
			cpOption.getPrimaryKey(), cpOption);

		finderCache.putResult(
			_finderPathFetchByC_K,
			new Object[] {cpOption.getCompanyId(), cpOption.getKey()},
			cpOption);

		finderCache.putResult(
			_finderPathFetchByC_ERC,
			new Object[] {
				cpOption.getCompanyId(), cpOption.getExternalReferenceCode()
			},
			cpOption);

		cpOption.resetOriginalValues();
	}

	/**
	 * Caches the cp options in the entity cache if it is enabled.
	 *
	 * @param cpOptions the cp options
	 */
	@Override
	public void cacheResult(List<CPOption> cpOptions) {
		for (CPOption cpOption : cpOptions) {
			if (entityCache.getResult(
					CPOptionModelImpl.ENTITY_CACHE_ENABLED, CPOptionImpl.class,
					cpOption.getPrimaryKey()) == null) {

				cacheResult(cpOption);
			}
			else {
				cpOption.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cp options.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CPOptionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cp option.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CPOption cpOption) {
		entityCache.removeResult(
			CPOptionModelImpl.ENTITY_CACHE_ENABLED, CPOptionImpl.class,
			cpOption.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CPOptionModelImpl)cpOption, true);
	}

	@Override
	public void clearCache(List<CPOption> cpOptions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CPOption cpOption : cpOptions) {
			entityCache.removeResult(
				CPOptionModelImpl.ENTITY_CACHE_ENABLED, CPOptionImpl.class,
				cpOption.getPrimaryKey());

			clearUniqueFindersCache((CPOptionModelImpl)cpOption, true);
		}
	}

	protected void cacheUniqueFindersCache(
		CPOptionModelImpl cpOptionModelImpl) {

		Object[] args = new Object[] {
			cpOptionModelImpl.getCompanyId(), cpOptionModelImpl.getKey()
		};

		finderCache.putResult(
			_finderPathCountByC_K, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_K, args, cpOptionModelImpl, false);

		args = new Object[] {
			cpOptionModelImpl.getCompanyId(),
			cpOptionModelImpl.getExternalReferenceCode()
		};

		finderCache.putResult(
			_finderPathCountByC_ERC, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_ERC, args, cpOptionModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CPOptionModelImpl cpOptionModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				cpOptionModelImpl.getCompanyId(), cpOptionModelImpl.getKey()
			};

			finderCache.removeResult(_finderPathCountByC_K, args);
			finderCache.removeResult(_finderPathFetchByC_K, args);
		}

		if ((cpOptionModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_K.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				cpOptionModelImpl.getOriginalCompanyId(),
				cpOptionModelImpl.getOriginalKey()
			};

			finderCache.removeResult(_finderPathCountByC_K, args);
			finderCache.removeResult(_finderPathFetchByC_K, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
				cpOptionModelImpl.getCompanyId(),
				cpOptionModelImpl.getExternalReferenceCode()
			};

			finderCache.removeResult(_finderPathCountByC_ERC, args);
			finderCache.removeResult(_finderPathFetchByC_ERC, args);
		}

		if ((cpOptionModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_ERC.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				cpOptionModelImpl.getOriginalCompanyId(),
				cpOptionModelImpl.getOriginalExternalReferenceCode()
			};

			finderCache.removeResult(_finderPathCountByC_ERC, args);
			finderCache.removeResult(_finderPathFetchByC_ERC, args);
		}
	}

	/**
	 * Creates a new cp option with the primary key. Does not add the cp option to the database.
	 *
	 * @param CPOptionId the primary key for the new cp option
	 * @return the new cp option
	 */
	@Override
	public CPOption create(long CPOptionId) {
		CPOption cpOption = new CPOptionImpl();

		cpOption.setNew(true);
		cpOption.setPrimaryKey(CPOptionId);

		String uuid = PortalUUIDUtil.generate();

		cpOption.setUuid(uuid);

		cpOption.setCompanyId(CompanyThreadLocal.getCompanyId());

		return cpOption;
	}

	/**
	 * Removes the cp option with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPOptionId the primary key of the cp option
	 * @return the cp option that was removed
	 * @throws NoSuchCPOptionException if a cp option with the primary key could not be found
	 */
	@Override
	public CPOption remove(long CPOptionId) throws NoSuchCPOptionException {
		return remove((Serializable)CPOptionId);
	}

	/**
	 * Removes the cp option with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cp option
	 * @return the cp option that was removed
	 * @throws NoSuchCPOptionException if a cp option with the primary key could not be found
	 */
	@Override
	public CPOption remove(Serializable primaryKey)
		throws NoSuchCPOptionException {

		Session session = null;

		try {
			session = openSession();

			CPOption cpOption = (CPOption)session.get(
				CPOptionImpl.class, primaryKey);

			if (cpOption == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCPOptionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(cpOption);
		}
		catch (NoSuchCPOptionException nsee) {
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
	protected CPOption removeImpl(CPOption cpOption) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cpOption)) {
				cpOption = (CPOption)session.get(
					CPOptionImpl.class, cpOption.getPrimaryKeyObj());
			}

			if (cpOption != null) {
				session.delete(cpOption);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cpOption != null) {
			clearCache(cpOption);
		}

		return cpOption;
	}

	@Override
	public CPOption updateImpl(CPOption cpOption) {
		boolean isNew = cpOption.isNew();

		if (!(cpOption instanceof CPOptionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(cpOption.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(cpOption);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in cpOption proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CPOption implementation " +
					cpOption.getClass());
		}

		CPOptionModelImpl cpOptionModelImpl = (CPOptionModelImpl)cpOption;

		if (Validator.isNull(cpOption.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			cpOption.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (cpOption.getCreateDate() == null)) {
			if (serviceContext == null) {
				cpOption.setCreateDate(now);
			}
			else {
				cpOption.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!cpOptionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				cpOption.setModifiedDate(now);
			}
			else {
				cpOption.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (cpOption.isNew()) {
				session.save(cpOption);

				cpOption.setNew(false);
			}
			else {
				cpOption = (CPOption)session.merge(cpOption);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CPOptionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {cpOptionModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				cpOptionModelImpl.getUuid(), cpOptionModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {cpOptionModelImpl.getCompanyId()};

			finderCache.removeResult(_finderPathCountByCompanyId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCompanyId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((cpOptionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpOptionModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {cpOptionModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((cpOptionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpOptionModelImpl.getOriginalUuid(),
					cpOptionModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					cpOptionModelImpl.getUuid(),
					cpOptionModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((cpOptionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCompanyId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					cpOptionModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);

				args = new Object[] {cpOptionModelImpl.getCompanyId()};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);
			}
		}

		entityCache.putResult(
			CPOptionModelImpl.ENTITY_CACHE_ENABLED, CPOptionImpl.class,
			cpOption.getPrimaryKey(), cpOption, false);

		clearUniqueFindersCache(cpOptionModelImpl, false);
		cacheUniqueFindersCache(cpOptionModelImpl);

		cpOption.resetOriginalValues();

		return cpOption;
	}

	/**
	 * Returns the cp option with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp option
	 * @return the cp option
	 * @throws NoSuchCPOptionException if a cp option with the primary key could not be found
	 */
	@Override
	public CPOption findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCPOptionException {

		CPOption cpOption = fetchByPrimaryKey(primaryKey);

		if (cpOption == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCPOptionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return cpOption;
	}

	/**
	 * Returns the cp option with the primary key or throws a <code>NoSuchCPOptionException</code> if it could not be found.
	 *
	 * @param CPOptionId the primary key of the cp option
	 * @return the cp option
	 * @throws NoSuchCPOptionException if a cp option with the primary key could not be found
	 */
	@Override
	public CPOption findByPrimaryKey(long CPOptionId)
		throws NoSuchCPOptionException {

		return findByPrimaryKey((Serializable)CPOptionId);
	}

	/**
	 * Returns the cp option with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp option
	 * @return the cp option, or <code>null</code> if a cp option with the primary key could not be found
	 */
	@Override
	public CPOption fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			CPOptionModelImpl.ENTITY_CACHE_ENABLED, CPOptionImpl.class,
			primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CPOption cpOption = (CPOption)serializable;

		if (cpOption == null) {
			Session session = null;

			try {
				session = openSession();

				cpOption = (CPOption)session.get(
					CPOptionImpl.class, primaryKey);

				if (cpOption != null) {
					cacheResult(cpOption);
				}
				else {
					entityCache.putResult(
						CPOptionModelImpl.ENTITY_CACHE_ENABLED,
						CPOptionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CPOptionModelImpl.ENTITY_CACHE_ENABLED, CPOptionImpl.class,
					primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cpOption;
	}

	/**
	 * Returns the cp option with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param CPOptionId the primary key of the cp option
	 * @return the cp option, or <code>null</code> if a cp option with the primary key could not be found
	 */
	@Override
	public CPOption fetchByPrimaryKey(long CPOptionId) {
		return fetchByPrimaryKey((Serializable)CPOptionId);
	}

	@Override
	public Map<Serializable, CPOption> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CPOption> map = new HashMap<Serializable, CPOption>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CPOption cpOption = fetchByPrimaryKey(primaryKey);

			if (cpOption != null) {
				map.put(primaryKey, cpOption);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CPOptionModelImpl.ENTITY_CACHE_ENABLED, CPOptionImpl.class,
				primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CPOption)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_CPOPTION_WHERE_PKS_IN);

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

			for (CPOption cpOption : (List<CPOption>)q.list()) {
				map.put(cpOption.getPrimaryKeyObj(), cpOption);

				cacheResult(cpOption);

				uncachedPrimaryKeys.remove(cpOption.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CPOptionModelImpl.ENTITY_CACHE_ENABLED, CPOptionImpl.class,
					primaryKey, nullModel);
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
	 * Returns all the cp options.
	 *
	 * @return the cp options
	 */
	@Override
	public List<CPOption> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp options.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp options
	 * @param end the upper bound of the range of cp options (not inclusive)
	 * @return the range of cp options
	 */
	@Override
	public List<CPOption> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp options.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp options
	 * @param end the upper bound of the range of cp options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cp options
	 */
	@Override
	public List<CPOption> findAll(
		int start, int end, OrderByComparator<CPOption> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp options.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPOptionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp options
	 * @param end the upper bound of the range of cp options (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cp options
	 */
	@Override
	public List<CPOption> findAll(
		int start, int end, OrderByComparator<CPOption> orderByComparator,
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

		List<CPOption> list = null;

		if (retrieveFromCache) {
			list = (List<CPOption>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CPOPTION);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CPOPTION;

				if (pagination) {
					sql = sql.concat(CPOptionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CPOption>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPOption>)QueryUtil.list(
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
	 * Removes all the cp options from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CPOption cpOption : findAll()) {
			remove(cpOption);
		}
	}

	/**
	 * Returns the number of cp options.
	 *
	 * @return the number of cp options
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CPOPTION);

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
		return CPOptionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cp option persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CPOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionModelImpl.FINDER_CACHE_ENABLED, CPOptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CPOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionModelImpl.FINDER_CACHE_ENABLED, CPOptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CPOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			CPOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionModelImpl.FINDER_CACHE_ENABLED, CPOptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			CPOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionModelImpl.FINDER_CACHE_ENABLED, CPOptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			CPOptionModelImpl.UUID_COLUMN_BITMASK |
			CPOptionModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			CPOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			CPOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionModelImpl.FINDER_CACHE_ENABLED, CPOptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			CPOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionModelImpl.FINDER_CACHE_ENABLED, CPOptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			CPOptionModelImpl.UUID_COLUMN_BITMASK |
			CPOptionModelImpl.COMPANYID_COLUMN_BITMASK |
			CPOptionModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			CPOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByCompanyId = new FinderPath(
			CPOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionModelImpl.FINDER_CACHE_ENABLED, CPOptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCompanyId = new FinderPath(
			CPOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionModelImpl.FINDER_CACHE_ENABLED, CPOptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] {Long.class.getName()},
			CPOptionModelImpl.COMPANYID_COLUMN_BITMASK |
			CPOptionModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByCompanyId = new FinderPath(
			CPOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] {Long.class.getName()});

		_finderPathFetchByC_K = new FinderPath(
			CPOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionModelImpl.FINDER_CACHE_ENABLED, CPOptionImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByC_K",
			new String[] {Long.class.getName(), String.class.getName()},
			CPOptionModelImpl.COMPANYID_COLUMN_BITMASK |
			CPOptionModelImpl.KEY_COLUMN_BITMASK);

		_finderPathCountByC_K = new FinderPath(
			CPOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_K",
			new String[] {Long.class.getName(), String.class.getName()});

		_finderPathFetchByC_ERC = new FinderPath(
			CPOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionModelImpl.FINDER_CACHE_ENABLED, CPOptionImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByC_ERC",
			new String[] {Long.class.getName(), String.class.getName()},
			CPOptionModelImpl.COMPANYID_COLUMN_BITMASK |
			CPOptionModelImpl.EXTERNALREFERENCECODE_COLUMN_BITMASK);

		_finderPathCountByC_ERC = new FinderPath(
			CPOptionModelImpl.ENTITY_CACHE_ENABLED,
			CPOptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_ERC",
			new String[] {Long.class.getName(), String.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(CPOptionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_CPOPTION =
		"SELECT cpOption FROM CPOption cpOption";

	private static final String _SQL_SELECT_CPOPTION_WHERE_PKS_IN =
		"SELECT cpOption FROM CPOption cpOption WHERE CPOptionId IN (";

	private static final String _SQL_SELECT_CPOPTION_WHERE =
		"SELECT cpOption FROM CPOption cpOption WHERE ";

	private static final String _SQL_COUNT_CPOPTION =
		"SELECT COUNT(cpOption) FROM CPOption cpOption";

	private static final String _SQL_COUNT_CPOPTION_WHERE =
		"SELECT COUNT(cpOption) FROM CPOption cpOption WHERE ";

	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN =
		"cpOption.CPOptionId";

	private static final String _FILTER_SQL_SELECT_CPOPTION_WHERE =
		"SELECT DISTINCT {cpOption.*} FROM CPOption cpOption WHERE ";

	private static final String
		_FILTER_SQL_SELECT_CPOPTION_NO_INLINE_DISTINCT_WHERE_1 =
			"SELECT {CPOption.*} FROM (SELECT DISTINCT cpOption.CPOptionId FROM CPOption cpOption WHERE ";

	private static final String
		_FILTER_SQL_SELECT_CPOPTION_NO_INLINE_DISTINCT_WHERE_2 =
			") TEMP_TABLE INNER JOIN CPOption ON TEMP_TABLE.CPOptionId = CPOption.CPOptionId";

	private static final String _FILTER_SQL_COUNT_CPOPTION_WHERE =
		"SELECT COUNT(DISTINCT cpOption.CPOptionId) AS COUNT_VALUE FROM CPOption cpOption WHERE ";

	private static final String _FILTER_ENTITY_ALIAS = "cpOption";

	private static final String _FILTER_ENTITY_TABLE = "CPOption";

	private static final String _ORDER_BY_ENTITY_ALIAS = "cpOption.";

	private static final String _ORDER_BY_ENTITY_TABLE = "CPOption.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CPOption exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CPOption exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CPOptionPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "key"});

}