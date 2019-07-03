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

import com.liferay.commerce.product.exception.NoSuchCPDefinitionSpecificationOptionValueException;
import com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue;
import com.liferay.commerce.product.model.impl.CPDefinitionSpecificationOptionValueImpl;
import com.liferay.commerce.product.model.impl.CPDefinitionSpecificationOptionValueModelImpl;
import com.liferay.commerce.product.service.persistence.CPDefinitionSpecificationOptionValuePersistence;
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
 * The persistence implementation for the cp definition specification option value service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @generated
 */
@ProviderType
public class CPDefinitionSpecificationOptionValuePersistenceImpl
	extends BasePersistenceImpl<CPDefinitionSpecificationOptionValue>
	implements CPDefinitionSpecificationOptionValuePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CPDefinitionSpecificationOptionValueUtil</code> to access the cp definition specification option value persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CPDefinitionSpecificationOptionValueImpl.class.getName();

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
	 * Returns all the cp definition specification option values where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp definition specification option values where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @return the range of matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp definition specification option values where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp definition specification option values where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator,
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

		List<CPDefinitionSpecificationOptionValue> list = null;

		if (retrieveFromCache) {
			list =
				(List<CPDefinitionSpecificationOptionValue>)
					finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPDefinitionSpecificationOptionValue
						cpDefinitionSpecificationOptionValue : list) {

					if (!uuid.equals(
							cpDefinitionSpecificationOptionValue.getUuid())) {

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

			query.append(
				_SQL_SELECT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

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
				query.append(
					CPDefinitionSpecificationOptionValueModelImpl.
						ORDER_BY_JPQL);
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
					list =
						(List<CPDefinitionSpecificationOptionValue>)
							QueryUtil.list(q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list =
						(List<CPDefinitionSpecificationOptionValue>)
							QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first cp definition specification option value in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue findByUuid_First(
			String uuid,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = fetchByUuid_First(
				uuid, orderByComparator);

		if (cpDefinitionSpecificationOptionValue != null) {
			return cpDefinitionSpecificationOptionValue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCPDefinitionSpecificationOptionValueException(
			msg.toString());
	}

	/**
	 * Returns the first cp definition specification option value in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition specification option value, or <code>null</code> if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue fetchByUuid_First(
		String uuid,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator) {

		List<CPDefinitionSpecificationOptionValue> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp definition specification option value in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue findByUuid_Last(
			String uuid,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = fetchByUuid_Last(
				uuid, orderByComparator);

		if (cpDefinitionSpecificationOptionValue != null) {
			return cpDefinitionSpecificationOptionValue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCPDefinitionSpecificationOptionValueException(
			msg.toString());
	}

	/**
	 * Returns the last cp definition specification option value in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition specification option value, or <code>null</code> if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue fetchByUuid_Last(
		String uuid,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CPDefinitionSpecificationOptionValue> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp definition specification option values before and after the current cp definition specification option value in the ordered set where uuid = &#63;.
	 *
	 * @param CPDefinitionSpecificationOptionValueId the primary key of the current cp definition specification option value
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a cp definition specification option value with the primary key could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue[] findByUuid_PrevAndNext(
			long CPDefinitionSpecificationOptionValueId, String uuid,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		uuid = Objects.toString(uuid, "");

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = findByPrimaryKey(
				CPDefinitionSpecificationOptionValueId);

		Session session = null;

		try {
			session = openSession();

			CPDefinitionSpecificationOptionValue[] array =
				new CPDefinitionSpecificationOptionValueImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, cpDefinitionSpecificationOptionValue, uuid,
				orderByComparator, true);

			array[1] = cpDefinitionSpecificationOptionValue;

			array[2] = getByUuid_PrevAndNext(
				session, cpDefinitionSpecificationOptionValue, uuid,
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

	protected CPDefinitionSpecificationOptionValue getByUuid_PrevAndNext(
		Session session,
		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue,
		String uuid,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator,
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

		query.append(_SQL_SELECT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

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
			query.append(
				CPDefinitionSpecificationOptionValueModelImpl.ORDER_BY_JPQL);
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
						cpDefinitionSpecificationOptionValue)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPDefinitionSpecificationOptionValue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp definition specification option values where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue :
					findByUuid(
						uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cpDefinitionSpecificationOptionValue);
		}
	}

	/**
	 * Returns the number of cp definition specification option values where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cp definition specification option values
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

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
		"cpDefinitionSpecificationOptionValue.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(cpDefinitionSpecificationOptionValue.uuid IS NULL OR cpDefinitionSpecificationOptionValue.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the cp definition specification option value where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchCPDefinitionSpecificationOptionValueException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue findByUUID_G(
			String uuid, long groupId)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = fetchByUUID_G(uuid, groupId);

		if (cpDefinitionSpecificationOptionValue == null) {
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

			throw new NoSuchCPDefinitionSpecificationOptionValueException(
				msg.toString());
		}

		return cpDefinitionSpecificationOptionValue;
	}

	/**
	 * Returns the cp definition specification option value where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp definition specification option value, or <code>null</code> if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue fetchByUUID_G(
		String uuid, long groupId) {

		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the cp definition specification option value where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching cp definition specification option value, or <code>null</code> if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = new Object[] {uuid, groupId};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof CPDefinitionSpecificationOptionValue) {
			CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue =
					(CPDefinitionSpecificationOptionValue)result;

			if (!Objects.equals(
					uuid, cpDefinitionSpecificationOptionValue.getUuid()) ||
				(groupId !=
					cpDefinitionSpecificationOptionValue.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(
				_SQL_SELECT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

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

				List<CPDefinitionSpecificationOptionValue> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByUUID_G, finderArgs, list);
				}
				else {
					CPDefinitionSpecificationOptionValue
						cpDefinitionSpecificationOptionValue = list.get(0);

					result = cpDefinitionSpecificationOptionValue;

					cacheResult(cpDefinitionSpecificationOptionValue);
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
			return (CPDefinitionSpecificationOptionValue)result;
		}
	}

	/**
	 * Removes the cp definition specification option value where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the cp definition specification option value that was removed
	 */
	@Override
	public CPDefinitionSpecificationOptionValue removeByUUID_G(
			String uuid, long groupId)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = findByUUID_G(uuid, groupId);

		return remove(cpDefinitionSpecificationOptionValue);
	}

	/**
	 * Returns the number of cp definition specification option values where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching cp definition specification option values
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

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
		"cpDefinitionSpecificationOptionValue.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(cpDefinitionSpecificationOptionValue.uuid IS NULL OR cpDefinitionSpecificationOptionValue.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"cpDefinitionSpecificationOptionValue.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the cp definition specification option values where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByUuid_C(
		String uuid, long companyId) {

		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp definition specification option values where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @return the range of matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp definition specification option values where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp definition specification option values where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator,
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

		List<CPDefinitionSpecificationOptionValue> list = null;

		if (retrieveFromCache) {
			list =
				(List<CPDefinitionSpecificationOptionValue>)
					finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPDefinitionSpecificationOptionValue
						cpDefinitionSpecificationOptionValue : list) {

					if (!uuid.equals(
							cpDefinitionSpecificationOptionValue.getUuid()) ||
						(companyId !=
							cpDefinitionSpecificationOptionValue.
								getCompanyId())) {

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

			query.append(
				_SQL_SELECT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

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
				query.append(
					CPDefinitionSpecificationOptionValueModelImpl.
						ORDER_BY_JPQL);
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
					list =
						(List<CPDefinitionSpecificationOptionValue>)
							QueryUtil.list(q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list =
						(List<CPDefinitionSpecificationOptionValue>)
							QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first cp definition specification option value in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = fetchByUuid_C_First(
				uuid, companyId, orderByComparator);

		if (cpDefinitionSpecificationOptionValue != null) {
			return cpDefinitionSpecificationOptionValue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPDefinitionSpecificationOptionValueException(
			msg.toString());
	}

	/**
	 * Returns the first cp definition specification option value in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition specification option value, or <code>null</code> if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator) {

		List<CPDefinitionSpecificationOptionValue> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp definition specification option value in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = fetchByUuid_C_Last(
				uuid, companyId, orderByComparator);

		if (cpDefinitionSpecificationOptionValue != null) {
			return cpDefinitionSpecificationOptionValue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPDefinitionSpecificationOptionValueException(
			msg.toString());
	}

	/**
	 * Returns the last cp definition specification option value in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition specification option value, or <code>null</code> if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CPDefinitionSpecificationOptionValue> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp definition specification option values before and after the current cp definition specification option value in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param CPDefinitionSpecificationOptionValueId the primary key of the current cp definition specification option value
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a cp definition specification option value with the primary key could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue[] findByUuid_C_PrevAndNext(
			long CPDefinitionSpecificationOptionValueId, String uuid,
			long companyId,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		uuid = Objects.toString(uuid, "");

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = findByPrimaryKey(
				CPDefinitionSpecificationOptionValueId);

		Session session = null;

		try {
			session = openSession();

			CPDefinitionSpecificationOptionValue[] array =
				new CPDefinitionSpecificationOptionValueImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, cpDefinitionSpecificationOptionValue, uuid, companyId,
				orderByComparator, true);

			array[1] = cpDefinitionSpecificationOptionValue;

			array[2] = getByUuid_C_PrevAndNext(
				session, cpDefinitionSpecificationOptionValue, uuid, companyId,
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

	protected CPDefinitionSpecificationOptionValue getByUuid_C_PrevAndNext(
		Session session,
		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue,
		String uuid, long companyId,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator,
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

		query.append(_SQL_SELECT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

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
			query.append(
				CPDefinitionSpecificationOptionValueModelImpl.ORDER_BY_JPQL);
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
						cpDefinitionSpecificationOptionValue)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPDefinitionSpecificationOptionValue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp definition specification option values where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue :
					findByUuid_C(
						uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
						null)) {

			remove(cpDefinitionSpecificationOptionValue);
		}
	}

	/**
	 * Returns the number of cp definition specification option values where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching cp definition specification option values
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

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
		"cpDefinitionSpecificationOptionValue.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(cpDefinitionSpecificationOptionValue.uuid IS NULL OR cpDefinitionSpecificationOptionValue.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"cpDefinitionSpecificationOptionValue.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the cp definition specification option values where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByGroupId(
		long groupId) {

		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp definition specification option values where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @return the range of matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByGroupId(
		long groupId, int start, int end) {

		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp definition specification option values where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp definition specification option values where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator,
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

		List<CPDefinitionSpecificationOptionValue> list = null;

		if (retrieveFromCache) {
			list =
				(List<CPDefinitionSpecificationOptionValue>)
					finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPDefinitionSpecificationOptionValue
						cpDefinitionSpecificationOptionValue : list) {

					if ((groupId !=
							cpDefinitionSpecificationOptionValue.
								getGroupId())) {

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

			query.append(
				_SQL_SELECT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(
					CPDefinitionSpecificationOptionValueModelImpl.
						ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list =
						(List<CPDefinitionSpecificationOptionValue>)
							QueryUtil.list(q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list =
						(List<CPDefinitionSpecificationOptionValue>)
							QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first cp definition specification option value in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue findByGroupId_First(
			long groupId,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = fetchByGroupId_First(
				groupId, orderByComparator);

		if (cpDefinitionSpecificationOptionValue != null) {
			return cpDefinitionSpecificationOptionValue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchCPDefinitionSpecificationOptionValueException(
			msg.toString());
	}

	/**
	 * Returns the first cp definition specification option value in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition specification option value, or <code>null</code> if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue fetchByGroupId_First(
		long groupId,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator) {

		List<CPDefinitionSpecificationOptionValue> list = findByGroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp definition specification option value in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue findByGroupId_Last(
			long groupId,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = fetchByGroupId_Last(
				groupId, orderByComparator);

		if (cpDefinitionSpecificationOptionValue != null) {
			return cpDefinitionSpecificationOptionValue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchCPDefinitionSpecificationOptionValueException(
			msg.toString());
	}

	/**
	 * Returns the last cp definition specification option value in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition specification option value, or <code>null</code> if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue fetchByGroupId_Last(
		long groupId,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<CPDefinitionSpecificationOptionValue> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp definition specification option values before and after the current cp definition specification option value in the ordered set where groupId = &#63;.
	 *
	 * @param CPDefinitionSpecificationOptionValueId the primary key of the current cp definition specification option value
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a cp definition specification option value with the primary key could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue[] findByGroupId_PrevAndNext(
			long CPDefinitionSpecificationOptionValueId, long groupId,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = findByPrimaryKey(
				CPDefinitionSpecificationOptionValueId);

		Session session = null;

		try {
			session = openSession();

			CPDefinitionSpecificationOptionValue[] array =
				new CPDefinitionSpecificationOptionValueImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, cpDefinitionSpecificationOptionValue, groupId,
				orderByComparator, true);

			array[1] = cpDefinitionSpecificationOptionValue;

			array[2] = getByGroupId_PrevAndNext(
				session, cpDefinitionSpecificationOptionValue, groupId,
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

	protected CPDefinitionSpecificationOptionValue getByGroupId_PrevAndNext(
		Session session,
		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue,
		long groupId,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator,
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

		query.append(_SQL_SELECT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

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
			query.append(
				CPDefinitionSpecificationOptionValueModelImpl.ORDER_BY_JPQL);
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
						cpDefinitionSpecificationOptionValue)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPDefinitionSpecificationOptionValue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp definition specification option values where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue :
					findByGroupId(
						groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cpDefinitionSpecificationOptionValue);
		}
	}

	/**
	 * Returns the number of cp definition specification option values where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching cp definition specification option values
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

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
		"cpDefinitionSpecificationOptionValue.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByCPDefinitionId;
	private FinderPath _finderPathWithoutPaginationFindByCPDefinitionId;
	private FinderPath _finderPathCountByCPDefinitionId;

	/**
	 * Returns all the cp definition specification option values where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @return the matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByCPDefinitionId(
		long CPDefinitionId) {

		return findByCPDefinitionId(
			CPDefinitionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp definition specification option values where CPDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @return the range of matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByCPDefinitionId(
		long CPDefinitionId, int start, int end) {

		return findByCPDefinitionId(CPDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp definition specification option values where CPDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByCPDefinitionId(
		long CPDefinitionId, int start, int end,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator) {

		return findByCPDefinitionId(
			CPDefinitionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp definition specification option values where CPDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByCPDefinitionId(
		long CPDefinitionId, int start, int end,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator,
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

		List<CPDefinitionSpecificationOptionValue> list = null;

		if (retrieveFromCache) {
			list =
				(List<CPDefinitionSpecificationOptionValue>)
					finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPDefinitionSpecificationOptionValue
						cpDefinitionSpecificationOptionValue : list) {

					if ((CPDefinitionId !=
							cpDefinitionSpecificationOptionValue.
								getCPDefinitionId())) {

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

			query.append(
				_SQL_SELECT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

			query.append(_FINDER_COLUMN_CPDEFINITIONID_CPDEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(
					CPDefinitionSpecificationOptionValueModelImpl.
						ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPDefinitionId);

				if (!pagination) {
					list =
						(List<CPDefinitionSpecificationOptionValue>)
							QueryUtil.list(q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list =
						(List<CPDefinitionSpecificationOptionValue>)
							QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first cp definition specification option value in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue findByCPDefinitionId_First(
			long CPDefinitionId,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = fetchByCPDefinitionId_First(
				CPDefinitionId, orderByComparator);

		if (cpDefinitionSpecificationOptionValue != null) {
			return cpDefinitionSpecificationOptionValue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPDefinitionId=");
		msg.append(CPDefinitionId);

		msg.append("}");

		throw new NoSuchCPDefinitionSpecificationOptionValueException(
			msg.toString());
	}

	/**
	 * Returns the first cp definition specification option value in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition specification option value, or <code>null</code> if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue fetchByCPDefinitionId_First(
		long CPDefinitionId,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator) {

		List<CPDefinitionSpecificationOptionValue> list = findByCPDefinitionId(
			CPDefinitionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp definition specification option value in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue findByCPDefinitionId_Last(
			long CPDefinitionId,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = fetchByCPDefinitionId_Last(
				CPDefinitionId, orderByComparator);

		if (cpDefinitionSpecificationOptionValue != null) {
			return cpDefinitionSpecificationOptionValue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPDefinitionId=");
		msg.append(CPDefinitionId);

		msg.append("}");

		throw new NoSuchCPDefinitionSpecificationOptionValueException(
			msg.toString());
	}

	/**
	 * Returns the last cp definition specification option value in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition specification option value, or <code>null</code> if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue fetchByCPDefinitionId_Last(
		long CPDefinitionId,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator) {

		int count = countByCPDefinitionId(CPDefinitionId);

		if (count == 0) {
			return null;
		}

		List<CPDefinitionSpecificationOptionValue> list = findByCPDefinitionId(
			CPDefinitionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp definition specification option values before and after the current cp definition specification option value in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionSpecificationOptionValueId the primary key of the current cp definition specification option value
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a cp definition specification option value with the primary key could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue[]
			findByCPDefinitionId_PrevAndNext(
				long CPDefinitionSpecificationOptionValueId,
				long CPDefinitionId,
				OrderByComparator<CPDefinitionSpecificationOptionValue>
					orderByComparator)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = findByPrimaryKey(
				CPDefinitionSpecificationOptionValueId);

		Session session = null;

		try {
			session = openSession();

			CPDefinitionSpecificationOptionValue[] array =
				new CPDefinitionSpecificationOptionValueImpl[3];

			array[0] = getByCPDefinitionId_PrevAndNext(
				session, cpDefinitionSpecificationOptionValue, CPDefinitionId,
				orderByComparator, true);

			array[1] = cpDefinitionSpecificationOptionValue;

			array[2] = getByCPDefinitionId_PrevAndNext(
				session, cpDefinitionSpecificationOptionValue, CPDefinitionId,
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

	protected CPDefinitionSpecificationOptionValue
		getByCPDefinitionId_PrevAndNext(
			Session session,
			CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue,
			long CPDefinitionId,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator,
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

		query.append(_SQL_SELECT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

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
			query.append(
				CPDefinitionSpecificationOptionValueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(CPDefinitionId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpDefinitionSpecificationOptionValue)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPDefinitionSpecificationOptionValue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp definition specification option values where CPDefinitionId = &#63; from the database.
	 *
	 * @param CPDefinitionId the cp definition ID
	 */
	@Override
	public void removeByCPDefinitionId(long CPDefinitionId) {
		for (CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue :
					findByCPDefinitionId(
						CPDefinitionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
						null)) {

			remove(cpDefinitionSpecificationOptionValue);
		}
	}

	/**
	 * Returns the number of cp definition specification option values where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @return the number of matching cp definition specification option values
	 */
	@Override
	public int countByCPDefinitionId(long CPDefinitionId) {
		FinderPath finderPath = _finderPathCountByCPDefinitionId;

		Object[] finderArgs = new Object[] {CPDefinitionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

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
		"cpDefinitionSpecificationOptionValue.CPDefinitionId = ?";

	private FinderPath _finderPathWithPaginationFindByCPSpecificationOptionId;
	private FinderPath
		_finderPathWithoutPaginationFindByCPSpecificationOptionId;
	private FinderPath _finderPathCountByCPSpecificationOptionId;

	/**
	 * Returns all the cp definition specification option values where CPSpecificationOptionId = &#63;.
	 *
	 * @param CPSpecificationOptionId the cp specification option ID
	 * @return the matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue>
		findByCPSpecificationOptionId(long CPSpecificationOptionId) {

		return findByCPSpecificationOptionId(
			CPSpecificationOptionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the cp definition specification option values where CPSpecificationOptionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPSpecificationOptionId the cp specification option ID
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @return the range of matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue>
		findByCPSpecificationOptionId(
			long CPSpecificationOptionId, int start, int end) {

		return findByCPSpecificationOptionId(
			CPSpecificationOptionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp definition specification option values where CPSpecificationOptionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPSpecificationOptionId the cp specification option ID
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue>
		findByCPSpecificationOptionId(
			long CPSpecificationOptionId, int start, int end,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator) {

		return findByCPSpecificationOptionId(
			CPSpecificationOptionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp definition specification option values where CPSpecificationOptionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPSpecificationOptionId the cp specification option ID
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue>
		findByCPSpecificationOptionId(
			long CPSpecificationOptionId, int start, int end,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator,
			boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath =
				_finderPathWithoutPaginationFindByCPSpecificationOptionId;
			finderArgs = new Object[] {CPSpecificationOptionId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByCPSpecificationOptionId;
			finderArgs = new Object[] {
				CPSpecificationOptionId, start, end, orderByComparator
			};
		}

		List<CPDefinitionSpecificationOptionValue> list = null;

		if (retrieveFromCache) {
			list =
				(List<CPDefinitionSpecificationOptionValue>)
					finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPDefinitionSpecificationOptionValue
						cpDefinitionSpecificationOptionValue : list) {

					if ((CPSpecificationOptionId !=
							cpDefinitionSpecificationOptionValue.
								getCPSpecificationOptionId())) {

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

			query.append(
				_SQL_SELECT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

			query.append(
				_FINDER_COLUMN_CPSPECIFICATIONOPTIONID_CPSPECIFICATIONOPTIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(
					CPDefinitionSpecificationOptionValueModelImpl.
						ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPSpecificationOptionId);

				if (!pagination) {
					list =
						(List<CPDefinitionSpecificationOptionValue>)
							QueryUtil.list(q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list =
						(List<CPDefinitionSpecificationOptionValue>)
							QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first cp definition specification option value in the ordered set where CPSpecificationOptionId = &#63;.
	 *
	 * @param CPSpecificationOptionId the cp specification option ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue
			findByCPSpecificationOptionId_First(
				long CPSpecificationOptionId,
				OrderByComparator<CPDefinitionSpecificationOptionValue>
					orderByComparator)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue =
				fetchByCPSpecificationOptionId_First(
					CPSpecificationOptionId, orderByComparator);

		if (cpDefinitionSpecificationOptionValue != null) {
			return cpDefinitionSpecificationOptionValue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPSpecificationOptionId=");
		msg.append(CPSpecificationOptionId);

		msg.append("}");

		throw new NoSuchCPDefinitionSpecificationOptionValueException(
			msg.toString());
	}

	/**
	 * Returns the first cp definition specification option value in the ordered set where CPSpecificationOptionId = &#63;.
	 *
	 * @param CPSpecificationOptionId the cp specification option ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition specification option value, or <code>null</code> if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue
		fetchByCPSpecificationOptionId_First(
			long CPSpecificationOptionId,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator) {

		List<CPDefinitionSpecificationOptionValue> list =
			findByCPSpecificationOptionId(
				CPSpecificationOptionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp definition specification option value in the ordered set where CPSpecificationOptionId = &#63;.
	 *
	 * @param CPSpecificationOptionId the cp specification option ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue
			findByCPSpecificationOptionId_Last(
				long CPSpecificationOptionId,
				OrderByComparator<CPDefinitionSpecificationOptionValue>
					orderByComparator)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue =
				fetchByCPSpecificationOptionId_Last(
					CPSpecificationOptionId, orderByComparator);

		if (cpDefinitionSpecificationOptionValue != null) {
			return cpDefinitionSpecificationOptionValue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPSpecificationOptionId=");
		msg.append(CPSpecificationOptionId);

		msg.append("}");

		throw new NoSuchCPDefinitionSpecificationOptionValueException(
			msg.toString());
	}

	/**
	 * Returns the last cp definition specification option value in the ordered set where CPSpecificationOptionId = &#63;.
	 *
	 * @param CPSpecificationOptionId the cp specification option ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition specification option value, or <code>null</code> if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue
		fetchByCPSpecificationOptionId_Last(
			long CPSpecificationOptionId,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator) {

		int count = countByCPSpecificationOptionId(CPSpecificationOptionId);

		if (count == 0) {
			return null;
		}

		List<CPDefinitionSpecificationOptionValue> list =
			findByCPSpecificationOptionId(
				CPSpecificationOptionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp definition specification option values before and after the current cp definition specification option value in the ordered set where CPSpecificationOptionId = &#63;.
	 *
	 * @param CPDefinitionSpecificationOptionValueId the primary key of the current cp definition specification option value
	 * @param CPSpecificationOptionId the cp specification option ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a cp definition specification option value with the primary key could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue[]
			findByCPSpecificationOptionId_PrevAndNext(
				long CPDefinitionSpecificationOptionValueId,
				long CPSpecificationOptionId,
				OrderByComparator<CPDefinitionSpecificationOptionValue>
					orderByComparator)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = findByPrimaryKey(
				CPDefinitionSpecificationOptionValueId);

		Session session = null;

		try {
			session = openSession();

			CPDefinitionSpecificationOptionValue[] array =
				new CPDefinitionSpecificationOptionValueImpl[3];

			array[0] = getByCPSpecificationOptionId_PrevAndNext(
				session, cpDefinitionSpecificationOptionValue,
				CPSpecificationOptionId, orderByComparator, true);

			array[1] = cpDefinitionSpecificationOptionValue;

			array[2] = getByCPSpecificationOptionId_PrevAndNext(
				session, cpDefinitionSpecificationOptionValue,
				CPSpecificationOptionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPDefinitionSpecificationOptionValue
		getByCPSpecificationOptionId_PrevAndNext(
			Session session,
			CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue,
			long CPSpecificationOptionId,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator,
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

		query.append(_SQL_SELECT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

		query.append(
			_FINDER_COLUMN_CPSPECIFICATIONOPTIONID_CPSPECIFICATIONOPTIONID_2);

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
			query.append(
				CPDefinitionSpecificationOptionValueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(CPSpecificationOptionId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpDefinitionSpecificationOptionValue)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPDefinitionSpecificationOptionValue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp definition specification option values where CPSpecificationOptionId = &#63; from the database.
	 *
	 * @param CPSpecificationOptionId the cp specification option ID
	 */
	@Override
	public void removeByCPSpecificationOptionId(long CPSpecificationOptionId) {
		for (CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue :
					findByCPSpecificationOptionId(
						CPSpecificationOptionId, QueryUtil.ALL_POS,
						QueryUtil.ALL_POS, null)) {

			remove(cpDefinitionSpecificationOptionValue);
		}
	}

	/**
	 * Returns the number of cp definition specification option values where CPSpecificationOptionId = &#63;.
	 *
	 * @param CPSpecificationOptionId the cp specification option ID
	 * @return the number of matching cp definition specification option values
	 */
	@Override
	public int countByCPSpecificationOptionId(long CPSpecificationOptionId) {
		FinderPath finderPath = _finderPathCountByCPSpecificationOptionId;

		Object[] finderArgs = new Object[] {CPSpecificationOptionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

			query.append(
				_FINDER_COLUMN_CPSPECIFICATIONOPTIONID_CPSPECIFICATIONOPTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPSpecificationOptionId);

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
		_FINDER_COLUMN_CPSPECIFICATIONOPTIONID_CPSPECIFICATIONOPTIONID_2 =
			"cpDefinitionSpecificationOptionValue.CPSpecificationOptionId = ?";

	private FinderPath _finderPathWithPaginationFindByCPOptionCategoryId;
	private FinderPath _finderPathWithoutPaginationFindByCPOptionCategoryId;
	private FinderPath _finderPathCountByCPOptionCategoryId;

	/**
	 * Returns all the cp definition specification option values where CPOptionCategoryId = &#63;.
	 *
	 * @param CPOptionCategoryId the cp option category ID
	 * @return the matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByCPOptionCategoryId(
		long CPOptionCategoryId) {

		return findByCPOptionCategoryId(
			CPOptionCategoryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp definition specification option values where CPOptionCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPOptionCategoryId the cp option category ID
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @return the range of matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByCPOptionCategoryId(
		long CPOptionCategoryId, int start, int end) {

		return findByCPOptionCategoryId(CPOptionCategoryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp definition specification option values where CPOptionCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPOptionCategoryId the cp option category ID
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByCPOptionCategoryId(
		long CPOptionCategoryId, int start, int end,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator) {

		return findByCPOptionCategoryId(
			CPOptionCategoryId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp definition specification option values where CPOptionCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPOptionCategoryId the cp option category ID
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByCPOptionCategoryId(
		long CPOptionCategoryId, int start, int end,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator,
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

		List<CPDefinitionSpecificationOptionValue> list = null;

		if (retrieveFromCache) {
			list =
				(List<CPDefinitionSpecificationOptionValue>)
					finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPDefinitionSpecificationOptionValue
						cpDefinitionSpecificationOptionValue : list) {

					if ((CPOptionCategoryId !=
							cpDefinitionSpecificationOptionValue.
								getCPOptionCategoryId())) {

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

			query.append(
				_SQL_SELECT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

			query.append(
				_FINDER_COLUMN_CPOPTIONCATEGORYID_CPOPTIONCATEGORYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(
					CPDefinitionSpecificationOptionValueModelImpl.
						ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPOptionCategoryId);

				if (!pagination) {
					list =
						(List<CPDefinitionSpecificationOptionValue>)
							QueryUtil.list(q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list =
						(List<CPDefinitionSpecificationOptionValue>)
							QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first cp definition specification option value in the ordered set where CPOptionCategoryId = &#63;.
	 *
	 * @param CPOptionCategoryId the cp option category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue findByCPOptionCategoryId_First(
			long CPOptionCategoryId,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue =
				fetchByCPOptionCategoryId_First(
					CPOptionCategoryId, orderByComparator);

		if (cpDefinitionSpecificationOptionValue != null) {
			return cpDefinitionSpecificationOptionValue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPOptionCategoryId=");
		msg.append(CPOptionCategoryId);

		msg.append("}");

		throw new NoSuchCPDefinitionSpecificationOptionValueException(
			msg.toString());
	}

	/**
	 * Returns the first cp definition specification option value in the ordered set where CPOptionCategoryId = &#63;.
	 *
	 * @param CPOptionCategoryId the cp option category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition specification option value, or <code>null</code> if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue fetchByCPOptionCategoryId_First(
		long CPOptionCategoryId,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator) {

		List<CPDefinitionSpecificationOptionValue> list =
			findByCPOptionCategoryId(
				CPOptionCategoryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp definition specification option value in the ordered set where CPOptionCategoryId = &#63;.
	 *
	 * @param CPOptionCategoryId the cp option category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue findByCPOptionCategoryId_Last(
			long CPOptionCategoryId,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue =
				fetchByCPOptionCategoryId_Last(
					CPOptionCategoryId, orderByComparator);

		if (cpDefinitionSpecificationOptionValue != null) {
			return cpDefinitionSpecificationOptionValue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPOptionCategoryId=");
		msg.append(CPOptionCategoryId);

		msg.append("}");

		throw new NoSuchCPDefinitionSpecificationOptionValueException(
			msg.toString());
	}

	/**
	 * Returns the last cp definition specification option value in the ordered set where CPOptionCategoryId = &#63;.
	 *
	 * @param CPOptionCategoryId the cp option category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition specification option value, or <code>null</code> if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue fetchByCPOptionCategoryId_Last(
		long CPOptionCategoryId,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator) {

		int count = countByCPOptionCategoryId(CPOptionCategoryId);

		if (count == 0) {
			return null;
		}

		List<CPDefinitionSpecificationOptionValue> list =
			findByCPOptionCategoryId(
				CPOptionCategoryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp definition specification option values before and after the current cp definition specification option value in the ordered set where CPOptionCategoryId = &#63;.
	 *
	 * @param CPDefinitionSpecificationOptionValueId the primary key of the current cp definition specification option value
	 * @param CPOptionCategoryId the cp option category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a cp definition specification option value with the primary key could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue[]
			findByCPOptionCategoryId_PrevAndNext(
				long CPDefinitionSpecificationOptionValueId,
				long CPOptionCategoryId,
				OrderByComparator<CPDefinitionSpecificationOptionValue>
					orderByComparator)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = findByPrimaryKey(
				CPDefinitionSpecificationOptionValueId);

		Session session = null;

		try {
			session = openSession();

			CPDefinitionSpecificationOptionValue[] array =
				new CPDefinitionSpecificationOptionValueImpl[3];

			array[0] = getByCPOptionCategoryId_PrevAndNext(
				session, cpDefinitionSpecificationOptionValue,
				CPOptionCategoryId, orderByComparator, true);

			array[1] = cpDefinitionSpecificationOptionValue;

			array[2] = getByCPOptionCategoryId_PrevAndNext(
				session, cpDefinitionSpecificationOptionValue,
				CPOptionCategoryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPDefinitionSpecificationOptionValue
		getByCPOptionCategoryId_PrevAndNext(
			Session session,
			CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue,
			long CPOptionCategoryId,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator,
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

		query.append(_SQL_SELECT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

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
			query.append(
				CPDefinitionSpecificationOptionValueModelImpl.ORDER_BY_JPQL);
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
						cpDefinitionSpecificationOptionValue)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPDefinitionSpecificationOptionValue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp definition specification option values where CPOptionCategoryId = &#63; from the database.
	 *
	 * @param CPOptionCategoryId the cp option category ID
	 */
	@Override
	public void removeByCPOptionCategoryId(long CPOptionCategoryId) {
		for (CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue :
					findByCPOptionCategoryId(
						CPOptionCategoryId, QueryUtil.ALL_POS,
						QueryUtil.ALL_POS, null)) {

			remove(cpDefinitionSpecificationOptionValue);
		}
	}

	/**
	 * Returns the number of cp definition specification option values where CPOptionCategoryId = &#63;.
	 *
	 * @param CPOptionCategoryId the cp option category ID
	 * @return the number of matching cp definition specification option values
	 */
	@Override
	public int countByCPOptionCategoryId(long CPOptionCategoryId) {
		FinderPath finderPath = _finderPathCountByCPOptionCategoryId;

		Object[] finderArgs = new Object[] {CPOptionCategoryId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

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

	private static final String
		_FINDER_COLUMN_CPOPTIONCATEGORYID_CPOPTIONCATEGORYID_2 =
			"cpDefinitionSpecificationOptionValue.CPOptionCategoryId = ?";

	private FinderPath _finderPathWithPaginationFindByC_COC;
	private FinderPath _finderPathWithoutPaginationFindByC_COC;
	private FinderPath _finderPathCountByC_COC;

	/**
	 * Returns all the cp definition specification option values where CPDefinitionId = &#63; and CPOptionCategoryId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPOptionCategoryId the cp option category ID
	 * @return the matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByC_COC(
		long CPDefinitionId, long CPOptionCategoryId) {

		return findByC_COC(
			CPDefinitionId, CPOptionCategoryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp definition specification option values where CPDefinitionId = &#63; and CPOptionCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPOptionCategoryId the cp option category ID
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @return the range of matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByC_COC(
		long CPDefinitionId, long CPOptionCategoryId, int start, int end) {

		return findByC_COC(
			CPDefinitionId, CPOptionCategoryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp definition specification option values where CPDefinitionId = &#63; and CPOptionCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPOptionCategoryId the cp option category ID
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByC_COC(
		long CPDefinitionId, long CPOptionCategoryId, int start, int end,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator) {

		return findByC_COC(
			CPDefinitionId, CPOptionCategoryId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the cp definition specification option values where CPDefinitionId = &#63; and CPOptionCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPOptionCategoryId the cp option category ID
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByC_COC(
		long CPDefinitionId, long CPOptionCategoryId, int start, int end,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByC_COC;
			finderArgs = new Object[] {CPDefinitionId, CPOptionCategoryId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByC_COC;
			finderArgs = new Object[] {
				CPDefinitionId, CPOptionCategoryId, start, end,
				orderByComparator
			};
		}

		List<CPDefinitionSpecificationOptionValue> list = null;

		if (retrieveFromCache) {
			list =
				(List<CPDefinitionSpecificationOptionValue>)
					finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPDefinitionSpecificationOptionValue
						cpDefinitionSpecificationOptionValue : list) {

					if ((CPDefinitionId !=
							cpDefinitionSpecificationOptionValue.
								getCPDefinitionId()) ||
						(CPOptionCategoryId !=
							cpDefinitionSpecificationOptionValue.
								getCPOptionCategoryId())) {

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

			query.append(
				_SQL_SELECT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

			query.append(_FINDER_COLUMN_C_COC_CPDEFINITIONID_2);

			query.append(_FINDER_COLUMN_C_COC_CPOPTIONCATEGORYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(
					CPDefinitionSpecificationOptionValueModelImpl.
						ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPDefinitionId);

				qPos.add(CPOptionCategoryId);

				if (!pagination) {
					list =
						(List<CPDefinitionSpecificationOptionValue>)
							QueryUtil.list(q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list =
						(List<CPDefinitionSpecificationOptionValue>)
							QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first cp definition specification option value in the ordered set where CPDefinitionId = &#63; and CPOptionCategoryId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPOptionCategoryId the cp option category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue findByC_COC_First(
			long CPDefinitionId, long CPOptionCategoryId,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = fetchByC_COC_First(
				CPDefinitionId, CPOptionCategoryId, orderByComparator);

		if (cpDefinitionSpecificationOptionValue != null) {
			return cpDefinitionSpecificationOptionValue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPDefinitionId=");
		msg.append(CPDefinitionId);

		msg.append(", CPOptionCategoryId=");
		msg.append(CPOptionCategoryId);

		msg.append("}");

		throw new NoSuchCPDefinitionSpecificationOptionValueException(
			msg.toString());
	}

	/**
	 * Returns the first cp definition specification option value in the ordered set where CPDefinitionId = &#63; and CPOptionCategoryId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPOptionCategoryId the cp option category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition specification option value, or <code>null</code> if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue fetchByC_COC_First(
		long CPDefinitionId, long CPOptionCategoryId,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator) {

		List<CPDefinitionSpecificationOptionValue> list = findByC_COC(
			CPDefinitionId, CPOptionCategoryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp definition specification option value in the ordered set where CPDefinitionId = &#63; and CPOptionCategoryId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPOptionCategoryId the cp option category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue findByC_COC_Last(
			long CPDefinitionId, long CPOptionCategoryId,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = fetchByC_COC_Last(
				CPDefinitionId, CPOptionCategoryId, orderByComparator);

		if (cpDefinitionSpecificationOptionValue != null) {
			return cpDefinitionSpecificationOptionValue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPDefinitionId=");
		msg.append(CPDefinitionId);

		msg.append(", CPOptionCategoryId=");
		msg.append(CPOptionCategoryId);

		msg.append("}");

		throw new NoSuchCPDefinitionSpecificationOptionValueException(
			msg.toString());
	}

	/**
	 * Returns the last cp definition specification option value in the ordered set where CPDefinitionId = &#63; and CPOptionCategoryId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPOptionCategoryId the cp option category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition specification option value, or <code>null</code> if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue fetchByC_COC_Last(
		long CPDefinitionId, long CPOptionCategoryId,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator) {

		int count = countByC_COC(CPDefinitionId, CPOptionCategoryId);

		if (count == 0) {
			return null;
		}

		List<CPDefinitionSpecificationOptionValue> list = findByC_COC(
			CPDefinitionId, CPOptionCategoryId, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp definition specification option values before and after the current cp definition specification option value in the ordered set where CPDefinitionId = &#63; and CPOptionCategoryId = &#63;.
	 *
	 * @param CPDefinitionSpecificationOptionValueId the primary key of the current cp definition specification option value
	 * @param CPDefinitionId the cp definition ID
	 * @param CPOptionCategoryId the cp option category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a cp definition specification option value with the primary key could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue[] findByC_COC_PrevAndNext(
			long CPDefinitionSpecificationOptionValueId, long CPDefinitionId,
			long CPOptionCategoryId,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = findByPrimaryKey(
				CPDefinitionSpecificationOptionValueId);

		Session session = null;

		try {
			session = openSession();

			CPDefinitionSpecificationOptionValue[] array =
				new CPDefinitionSpecificationOptionValueImpl[3];

			array[0] = getByC_COC_PrevAndNext(
				session, cpDefinitionSpecificationOptionValue, CPDefinitionId,
				CPOptionCategoryId, orderByComparator, true);

			array[1] = cpDefinitionSpecificationOptionValue;

			array[2] = getByC_COC_PrevAndNext(
				session, cpDefinitionSpecificationOptionValue, CPDefinitionId,
				CPOptionCategoryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPDefinitionSpecificationOptionValue getByC_COC_PrevAndNext(
		Session session,
		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue,
		long CPDefinitionId, long CPOptionCategoryId,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator,
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

		query.append(_SQL_SELECT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

		query.append(_FINDER_COLUMN_C_COC_CPDEFINITIONID_2);

		query.append(_FINDER_COLUMN_C_COC_CPOPTIONCATEGORYID_2);

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
			query.append(
				CPDefinitionSpecificationOptionValueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(CPDefinitionId);

		qPos.add(CPOptionCategoryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpDefinitionSpecificationOptionValue)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPDefinitionSpecificationOptionValue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp definition specification option values where CPDefinitionId = &#63; and CPOptionCategoryId = &#63; from the database.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPOptionCategoryId the cp option category ID
	 */
	@Override
	public void removeByC_COC(long CPDefinitionId, long CPOptionCategoryId) {
		for (CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue :
					findByC_COC(
						CPDefinitionId, CPOptionCategoryId, QueryUtil.ALL_POS,
						QueryUtil.ALL_POS, null)) {

			remove(cpDefinitionSpecificationOptionValue);
		}
	}

	/**
	 * Returns the number of cp definition specification option values where CPDefinitionId = &#63; and CPOptionCategoryId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPOptionCategoryId the cp option category ID
	 * @return the number of matching cp definition specification option values
	 */
	@Override
	public int countByC_COC(long CPDefinitionId, long CPOptionCategoryId) {
		FinderPath finderPath = _finderPathCountByC_COC;

		Object[] finderArgs = new Object[] {CPDefinitionId, CPOptionCategoryId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

			query.append(_FINDER_COLUMN_C_COC_CPDEFINITIONID_2);

			query.append(_FINDER_COLUMN_C_COC_CPOPTIONCATEGORYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPDefinitionId);

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

	private static final String _FINDER_COLUMN_C_COC_CPDEFINITIONID_2 =
		"cpDefinitionSpecificationOptionValue.CPDefinitionId = ? AND ";

	private static final String _FINDER_COLUMN_C_COC_CPOPTIONCATEGORYID_2 =
		"cpDefinitionSpecificationOptionValue.CPOptionCategoryId = ?";

	private FinderPath _finderPathWithPaginationFindByC_CSO;
	private FinderPath _finderPathWithoutPaginationFindByC_CSO;
	private FinderPath _finderPathCountByC_CSO;

	/**
	 * Returns all the cp definition specification option values where CPDefinitionId = &#63; and CPSpecificationOptionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPSpecificationOptionId the cp specification option ID
	 * @return the matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByC_CSO(
		long CPDefinitionId, long CPSpecificationOptionId) {

		return findByC_CSO(
			CPDefinitionId, CPSpecificationOptionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp definition specification option values where CPDefinitionId = &#63; and CPSpecificationOptionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPSpecificationOptionId the cp specification option ID
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @return the range of matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByC_CSO(
		long CPDefinitionId, long CPSpecificationOptionId, int start, int end) {

		return findByC_CSO(
			CPDefinitionId, CPSpecificationOptionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp definition specification option values where CPDefinitionId = &#63; and CPSpecificationOptionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPSpecificationOptionId the cp specification option ID
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByC_CSO(
		long CPDefinitionId, long CPSpecificationOptionId, int start, int end,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator) {

		return findByC_CSO(
			CPDefinitionId, CPSpecificationOptionId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp definition specification option values where CPDefinitionId = &#63; and CPSpecificationOptionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPSpecificationOptionId the cp specification option ID
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findByC_CSO(
		long CPDefinitionId, long CPSpecificationOptionId, int start, int end,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByC_CSO;
			finderArgs = new Object[] {CPDefinitionId, CPSpecificationOptionId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByC_CSO;
			finderArgs = new Object[] {
				CPDefinitionId, CPSpecificationOptionId, start, end,
				orderByComparator
			};
		}

		List<CPDefinitionSpecificationOptionValue> list = null;

		if (retrieveFromCache) {
			list =
				(List<CPDefinitionSpecificationOptionValue>)
					finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPDefinitionSpecificationOptionValue
						cpDefinitionSpecificationOptionValue : list) {

					if ((CPDefinitionId !=
							cpDefinitionSpecificationOptionValue.
								getCPDefinitionId()) ||
						(CPSpecificationOptionId !=
							cpDefinitionSpecificationOptionValue.
								getCPSpecificationOptionId())) {

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

			query.append(
				_SQL_SELECT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

			query.append(_FINDER_COLUMN_C_CSO_CPDEFINITIONID_2);

			query.append(_FINDER_COLUMN_C_CSO_CPSPECIFICATIONOPTIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(
					CPDefinitionSpecificationOptionValueModelImpl.
						ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPDefinitionId);

				qPos.add(CPSpecificationOptionId);

				if (!pagination) {
					list =
						(List<CPDefinitionSpecificationOptionValue>)
							QueryUtil.list(q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list =
						(List<CPDefinitionSpecificationOptionValue>)
							QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first cp definition specification option value in the ordered set where CPDefinitionId = &#63; and CPSpecificationOptionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPSpecificationOptionId the cp specification option ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue findByC_CSO_First(
			long CPDefinitionId, long CPSpecificationOptionId,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = fetchByC_CSO_First(
				CPDefinitionId, CPSpecificationOptionId, orderByComparator);

		if (cpDefinitionSpecificationOptionValue != null) {
			return cpDefinitionSpecificationOptionValue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPDefinitionId=");
		msg.append(CPDefinitionId);

		msg.append(", CPSpecificationOptionId=");
		msg.append(CPSpecificationOptionId);

		msg.append("}");

		throw new NoSuchCPDefinitionSpecificationOptionValueException(
			msg.toString());
	}

	/**
	 * Returns the first cp definition specification option value in the ordered set where CPDefinitionId = &#63; and CPSpecificationOptionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPSpecificationOptionId the cp specification option ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition specification option value, or <code>null</code> if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue fetchByC_CSO_First(
		long CPDefinitionId, long CPSpecificationOptionId,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator) {

		List<CPDefinitionSpecificationOptionValue> list = findByC_CSO(
			CPDefinitionId, CPSpecificationOptionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp definition specification option value in the ordered set where CPDefinitionId = &#63; and CPSpecificationOptionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPSpecificationOptionId the cp specification option ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue findByC_CSO_Last(
			long CPDefinitionId, long CPSpecificationOptionId,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = fetchByC_CSO_Last(
				CPDefinitionId, CPSpecificationOptionId, orderByComparator);

		if (cpDefinitionSpecificationOptionValue != null) {
			return cpDefinitionSpecificationOptionValue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPDefinitionId=");
		msg.append(CPDefinitionId);

		msg.append(", CPSpecificationOptionId=");
		msg.append(CPSpecificationOptionId);

		msg.append("}");

		throw new NoSuchCPDefinitionSpecificationOptionValueException(
			msg.toString());
	}

	/**
	 * Returns the last cp definition specification option value in the ordered set where CPDefinitionId = &#63; and CPSpecificationOptionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPSpecificationOptionId the cp specification option ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition specification option value, or <code>null</code> if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue fetchByC_CSO_Last(
		long CPDefinitionId, long CPSpecificationOptionId,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator) {

		int count = countByC_CSO(CPDefinitionId, CPSpecificationOptionId);

		if (count == 0) {
			return null;
		}

		List<CPDefinitionSpecificationOptionValue> list = findByC_CSO(
			CPDefinitionId, CPSpecificationOptionId, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp definition specification option values before and after the current cp definition specification option value in the ordered set where CPDefinitionId = &#63; and CPSpecificationOptionId = &#63;.
	 *
	 * @param CPDefinitionSpecificationOptionValueId the primary key of the current cp definition specification option value
	 * @param CPDefinitionId the cp definition ID
	 * @param CPSpecificationOptionId the cp specification option ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a cp definition specification option value with the primary key could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue[] findByC_CSO_PrevAndNext(
			long CPDefinitionSpecificationOptionValueId, long CPDefinitionId,
			long CPSpecificationOptionId,
			OrderByComparator<CPDefinitionSpecificationOptionValue>
				orderByComparator)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = findByPrimaryKey(
				CPDefinitionSpecificationOptionValueId);

		Session session = null;

		try {
			session = openSession();

			CPDefinitionSpecificationOptionValue[] array =
				new CPDefinitionSpecificationOptionValueImpl[3];

			array[0] = getByC_CSO_PrevAndNext(
				session, cpDefinitionSpecificationOptionValue, CPDefinitionId,
				CPSpecificationOptionId, orderByComparator, true);

			array[1] = cpDefinitionSpecificationOptionValue;

			array[2] = getByC_CSO_PrevAndNext(
				session, cpDefinitionSpecificationOptionValue, CPDefinitionId,
				CPSpecificationOptionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPDefinitionSpecificationOptionValue getByC_CSO_PrevAndNext(
		Session session,
		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue,
		long CPDefinitionId, long CPSpecificationOptionId,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator,
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

		query.append(_SQL_SELECT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

		query.append(_FINDER_COLUMN_C_CSO_CPDEFINITIONID_2);

		query.append(_FINDER_COLUMN_C_CSO_CPSPECIFICATIONOPTIONID_2);

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
			query.append(
				CPDefinitionSpecificationOptionValueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(CPDefinitionId);

		qPos.add(CPSpecificationOptionId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpDefinitionSpecificationOptionValue)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPDefinitionSpecificationOptionValue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp definition specification option values where CPDefinitionId = &#63; and CPSpecificationOptionId = &#63; from the database.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPSpecificationOptionId the cp specification option ID
	 */
	@Override
	public void removeByC_CSO(
		long CPDefinitionId, long CPSpecificationOptionId) {

		for (CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue :
					findByC_CSO(
						CPDefinitionId, CPSpecificationOptionId,
						QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cpDefinitionSpecificationOptionValue);
		}
	}

	/**
	 * Returns the number of cp definition specification option values where CPDefinitionId = &#63; and CPSpecificationOptionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPSpecificationOptionId the cp specification option ID
	 * @return the number of matching cp definition specification option values
	 */
	@Override
	public int countByC_CSO(long CPDefinitionId, long CPSpecificationOptionId) {
		FinderPath finderPath = _finderPathCountByC_CSO;

		Object[] finderArgs = new Object[] {
			CPDefinitionId, CPSpecificationOptionId
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

			query.append(_FINDER_COLUMN_C_CSO_CPDEFINITIONID_2);

			query.append(_FINDER_COLUMN_C_CSO_CPSPECIFICATIONOPTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPDefinitionId);

				qPos.add(CPSpecificationOptionId);

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

	private static final String _FINDER_COLUMN_C_CSO_CPDEFINITIONID_2 =
		"cpDefinitionSpecificationOptionValue.CPDefinitionId = ? AND ";

	private static final String _FINDER_COLUMN_C_CSO_CPSPECIFICATIONOPTIONID_2 =
		"cpDefinitionSpecificationOptionValue.CPSpecificationOptionId = ?";

	private FinderPath _finderPathFetchByC_CSOVI;
	private FinderPath _finderPathCountByC_CSOVI;

	/**
	 * Returns the cp definition specification option value where CPDefinitionId = &#63; and CPDefinitionSpecificationOptionValueId = &#63; or throws a <code>NoSuchCPDefinitionSpecificationOptionValueException</code> if it could not be found.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPDefinitionSpecificationOptionValueId the cp definition specification option value ID
	 * @return the matching cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue findByC_CSOVI(
			long CPDefinitionId, long CPDefinitionSpecificationOptionValueId)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = fetchByC_CSOVI(
				CPDefinitionId, CPDefinitionSpecificationOptionValueId);

		if (cpDefinitionSpecificationOptionValue == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("CPDefinitionId=");
			msg.append(CPDefinitionId);

			msg.append(", CPDefinitionSpecificationOptionValueId=");
			msg.append(CPDefinitionSpecificationOptionValueId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCPDefinitionSpecificationOptionValueException(
				msg.toString());
		}

		return cpDefinitionSpecificationOptionValue;
	}

	/**
	 * Returns the cp definition specification option value where CPDefinitionId = &#63; and CPDefinitionSpecificationOptionValueId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPDefinitionSpecificationOptionValueId the cp definition specification option value ID
	 * @return the matching cp definition specification option value, or <code>null</code> if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue fetchByC_CSOVI(
		long CPDefinitionId, long CPDefinitionSpecificationOptionValueId) {

		return fetchByC_CSOVI(
			CPDefinitionId, CPDefinitionSpecificationOptionValueId, true);
	}

	/**
	 * Returns the cp definition specification option value where CPDefinitionId = &#63; and CPDefinitionSpecificationOptionValueId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPDefinitionSpecificationOptionValueId the cp definition specification option value ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching cp definition specification option value, or <code>null</code> if a matching cp definition specification option value could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue fetchByC_CSOVI(
		long CPDefinitionId, long CPDefinitionSpecificationOptionValueId,
		boolean retrieveFromCache) {

		Object[] finderArgs = new Object[] {
			CPDefinitionId, CPDefinitionSpecificationOptionValueId
		};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_CSOVI, finderArgs, this);
		}

		if (result instanceof CPDefinitionSpecificationOptionValue) {
			CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue =
					(CPDefinitionSpecificationOptionValue)result;

			if ((CPDefinitionId !=
					cpDefinitionSpecificationOptionValue.getCPDefinitionId()) ||
				(CPDefinitionSpecificationOptionValueId !=
					cpDefinitionSpecificationOptionValue.
						getCPDefinitionSpecificationOptionValueId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(
				_SQL_SELECT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

			query.append(_FINDER_COLUMN_C_CSOVI_CPDEFINITIONID_2);

			query.append(
				_FINDER_COLUMN_C_CSOVI_CPDEFINITIONSPECIFICATIONOPTIONVALUEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPDefinitionId);

				qPos.add(CPDefinitionSpecificationOptionValueId);

				List<CPDefinitionSpecificationOptionValue> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByC_CSOVI, finderArgs, list);
				}
				else {
					CPDefinitionSpecificationOptionValue
						cpDefinitionSpecificationOptionValue = list.get(0);

					result = cpDefinitionSpecificationOptionValue;

					cacheResult(cpDefinitionSpecificationOptionValue);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByC_CSOVI, finderArgs);

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
			return (CPDefinitionSpecificationOptionValue)result;
		}
	}

	/**
	 * Removes the cp definition specification option value where CPDefinitionId = &#63; and CPDefinitionSpecificationOptionValueId = &#63; from the database.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPDefinitionSpecificationOptionValueId the cp definition specification option value ID
	 * @return the cp definition specification option value that was removed
	 */
	@Override
	public CPDefinitionSpecificationOptionValue removeByC_CSOVI(
			long CPDefinitionId, long CPDefinitionSpecificationOptionValueId)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = findByC_CSOVI(
				CPDefinitionId, CPDefinitionSpecificationOptionValueId);

		return remove(cpDefinitionSpecificationOptionValue);
	}

	/**
	 * Returns the number of cp definition specification option values where CPDefinitionId = &#63; and CPDefinitionSpecificationOptionValueId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CPDefinitionSpecificationOptionValueId the cp definition specification option value ID
	 * @return the number of matching cp definition specification option values
	 */
	@Override
	public int countByC_CSOVI(
		long CPDefinitionId, long CPDefinitionSpecificationOptionValueId) {

		FinderPath finderPath = _finderPathCountByC_CSOVI;

		Object[] finderArgs = new Object[] {
			CPDefinitionId, CPDefinitionSpecificationOptionValueId
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE);

			query.append(_FINDER_COLUMN_C_CSOVI_CPDEFINITIONID_2);

			query.append(
				_FINDER_COLUMN_C_CSOVI_CPDEFINITIONSPECIFICATIONOPTIONVALUEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPDefinitionId);

				qPos.add(CPDefinitionSpecificationOptionValueId);

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

	private static final String _FINDER_COLUMN_C_CSOVI_CPDEFINITIONID_2 =
		"cpDefinitionSpecificationOptionValue.CPDefinitionId = ? AND ";

	private static final String
		_FINDER_COLUMN_C_CSOVI_CPDEFINITIONSPECIFICATIONOPTIONVALUEID_2 =
			"cpDefinitionSpecificationOptionValue.CPDefinitionSpecificationOptionValueId = ?";

	public CPDefinitionSpecificationOptionValuePersistenceImpl() {
		setModelClass(CPDefinitionSpecificationOptionValue.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put(
			"CPDefinitionSpecificationOptionValueId",
			"CPDSpecificationOptionValueId");

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
	 * Caches the cp definition specification option value in the entity cache if it is enabled.
	 *
	 * @param cpDefinitionSpecificationOptionValue the cp definition specification option value
	 */
	@Override
	public void cacheResult(
		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue) {

		entityCache.putResult(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueImpl.class,
			cpDefinitionSpecificationOptionValue.getPrimaryKey(),
			cpDefinitionSpecificationOptionValue);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				cpDefinitionSpecificationOptionValue.getUuid(),
				cpDefinitionSpecificationOptionValue.getGroupId()
			},
			cpDefinitionSpecificationOptionValue);

		finderCache.putResult(
			_finderPathFetchByC_CSOVI,
			new Object[] {
				cpDefinitionSpecificationOptionValue.getCPDefinitionId(),
				cpDefinitionSpecificationOptionValue.
					getCPDefinitionSpecificationOptionValueId()
			},
			cpDefinitionSpecificationOptionValue);

		cpDefinitionSpecificationOptionValue.resetOriginalValues();
	}

	/**
	 * Caches the cp definition specification option values in the entity cache if it is enabled.
	 *
	 * @param cpDefinitionSpecificationOptionValues the cp definition specification option values
	 */
	@Override
	public void cacheResult(
		List<CPDefinitionSpecificationOptionValue>
			cpDefinitionSpecificationOptionValues) {

		for (CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue :
					cpDefinitionSpecificationOptionValues) {

			if (entityCache.getResult(
					CPDefinitionSpecificationOptionValueModelImpl.
						ENTITY_CACHE_ENABLED,
					CPDefinitionSpecificationOptionValueImpl.class,
					cpDefinitionSpecificationOptionValue.getPrimaryKey()) ==
						null) {

				cacheResult(cpDefinitionSpecificationOptionValue);
			}
			else {
				cpDefinitionSpecificationOptionValue.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cp definition specification option values.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CPDefinitionSpecificationOptionValueImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cp definition specification option value.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue) {

		entityCache.removeResult(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueImpl.class,
			cpDefinitionSpecificationOptionValue.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(CPDefinitionSpecificationOptionValueModelImpl)
				cpDefinitionSpecificationOptionValue,
			true);
	}

	@Override
	public void clearCache(
		List<CPDefinitionSpecificationOptionValue>
			cpDefinitionSpecificationOptionValues) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue :
					cpDefinitionSpecificationOptionValues) {

			entityCache.removeResult(
				CPDefinitionSpecificationOptionValueModelImpl.
					ENTITY_CACHE_ENABLED,
				CPDefinitionSpecificationOptionValueImpl.class,
				cpDefinitionSpecificationOptionValue.getPrimaryKey());

			clearUniqueFindersCache(
				(CPDefinitionSpecificationOptionValueModelImpl)
					cpDefinitionSpecificationOptionValue,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		CPDefinitionSpecificationOptionValueModelImpl
			cpDefinitionSpecificationOptionValueModelImpl) {

		Object[] args = new Object[] {
			cpDefinitionSpecificationOptionValueModelImpl.getUuid(),
			cpDefinitionSpecificationOptionValueModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args,
			cpDefinitionSpecificationOptionValueModelImpl, false);

		args = new Object[] {
			cpDefinitionSpecificationOptionValueModelImpl.getCPDefinitionId(),
			cpDefinitionSpecificationOptionValueModelImpl.
				getCPDefinitionSpecificationOptionValueId()
		};

		finderCache.putResult(
			_finderPathCountByC_CSOVI, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_CSOVI, args,
			cpDefinitionSpecificationOptionValueModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CPDefinitionSpecificationOptionValueModelImpl
			cpDefinitionSpecificationOptionValueModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				cpDefinitionSpecificationOptionValueModelImpl.getUuid(),
				cpDefinitionSpecificationOptionValueModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((cpDefinitionSpecificationOptionValueModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				cpDefinitionSpecificationOptionValueModelImpl.getOriginalUuid(),
				cpDefinitionSpecificationOptionValueModelImpl.
					getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
				cpDefinitionSpecificationOptionValueModelImpl.
					getCPDefinitionId(),
				cpDefinitionSpecificationOptionValueModelImpl.
					getCPDefinitionSpecificationOptionValueId()
			};

			finderCache.removeResult(_finderPathCountByC_CSOVI, args);
			finderCache.removeResult(_finderPathFetchByC_CSOVI, args);
		}

		if ((cpDefinitionSpecificationOptionValueModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_CSOVI.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				cpDefinitionSpecificationOptionValueModelImpl.
					getOriginalCPDefinitionId(),
				cpDefinitionSpecificationOptionValueModelImpl.
					getOriginalCPDefinitionSpecificationOptionValueId()
			};

			finderCache.removeResult(_finderPathCountByC_CSOVI, args);
			finderCache.removeResult(_finderPathFetchByC_CSOVI, args);
		}
	}

	/**
	 * Creates a new cp definition specification option value with the primary key. Does not add the cp definition specification option value to the database.
	 *
	 * @param CPDefinitionSpecificationOptionValueId the primary key for the new cp definition specification option value
	 * @return the new cp definition specification option value
	 */
	@Override
	public CPDefinitionSpecificationOptionValue create(
		long CPDefinitionSpecificationOptionValueId) {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue =
				new CPDefinitionSpecificationOptionValueImpl();

		cpDefinitionSpecificationOptionValue.setNew(true);
		cpDefinitionSpecificationOptionValue.setPrimaryKey(
			CPDefinitionSpecificationOptionValueId);

		String uuid = PortalUUIDUtil.generate();

		cpDefinitionSpecificationOptionValue.setUuid(uuid);

		cpDefinitionSpecificationOptionValue.setCompanyId(
			CompanyThreadLocal.getCompanyId());

		return cpDefinitionSpecificationOptionValue;
	}

	/**
	 * Removes the cp definition specification option value with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPDefinitionSpecificationOptionValueId the primary key of the cp definition specification option value
	 * @return the cp definition specification option value that was removed
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a cp definition specification option value with the primary key could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue remove(
			long CPDefinitionSpecificationOptionValueId)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		return remove((Serializable)CPDefinitionSpecificationOptionValueId);
	}

	/**
	 * Removes the cp definition specification option value with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cp definition specification option value
	 * @return the cp definition specification option value that was removed
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a cp definition specification option value with the primary key could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue remove(Serializable primaryKey)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		Session session = null;

		try {
			session = openSession();

			CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue =
					(CPDefinitionSpecificationOptionValue)session.get(
						CPDefinitionSpecificationOptionValueImpl.class,
						primaryKey);

			if (cpDefinitionSpecificationOptionValue == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCPDefinitionSpecificationOptionValueException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(cpDefinitionSpecificationOptionValue);
		}
		catch (NoSuchCPDefinitionSpecificationOptionValueException nsee) {
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
	protected CPDefinitionSpecificationOptionValue removeImpl(
		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cpDefinitionSpecificationOptionValue)) {
				cpDefinitionSpecificationOptionValue =
					(CPDefinitionSpecificationOptionValue)session.get(
						CPDefinitionSpecificationOptionValueImpl.class,
						cpDefinitionSpecificationOptionValue.
							getPrimaryKeyObj());
			}

			if (cpDefinitionSpecificationOptionValue != null) {
				session.delete(cpDefinitionSpecificationOptionValue);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cpDefinitionSpecificationOptionValue != null) {
			clearCache(cpDefinitionSpecificationOptionValue);
		}

		return cpDefinitionSpecificationOptionValue;
	}

	@Override
	public CPDefinitionSpecificationOptionValue updateImpl(
		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue) {

		boolean isNew = cpDefinitionSpecificationOptionValue.isNew();

		if (!(cpDefinitionSpecificationOptionValue instanceof
				CPDefinitionSpecificationOptionValueModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(
					cpDefinitionSpecificationOptionValue.getClass())) {

				invocationHandler = ProxyUtil.getInvocationHandler(
					cpDefinitionSpecificationOptionValue);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in cpDefinitionSpecificationOptionValue proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CPDefinitionSpecificationOptionValue implementation " +
					cpDefinitionSpecificationOptionValue.getClass());
		}

		CPDefinitionSpecificationOptionValueModelImpl
			cpDefinitionSpecificationOptionValueModelImpl =
				(CPDefinitionSpecificationOptionValueModelImpl)
					cpDefinitionSpecificationOptionValue;

		if (Validator.isNull(cpDefinitionSpecificationOptionValue.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			cpDefinitionSpecificationOptionValue.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew &&
			(cpDefinitionSpecificationOptionValue.getCreateDate() == null)) {

			if (serviceContext == null) {
				cpDefinitionSpecificationOptionValue.setCreateDate(now);
			}
			else {
				cpDefinitionSpecificationOptionValue.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!cpDefinitionSpecificationOptionValueModelImpl.
				hasSetModifiedDate()) {

			if (serviceContext == null) {
				cpDefinitionSpecificationOptionValue.setModifiedDate(now);
			}
			else {
				cpDefinitionSpecificationOptionValue.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (cpDefinitionSpecificationOptionValue.isNew()) {
				session.save(cpDefinitionSpecificationOptionValue);

				cpDefinitionSpecificationOptionValue.setNew(false);
			}
			else {
				cpDefinitionSpecificationOptionValue =
					(CPDefinitionSpecificationOptionValue)session.merge(
						cpDefinitionSpecificationOptionValue);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CPDefinitionSpecificationOptionValueModelImpl.
				COLUMN_BITMASK_ENABLED) {

			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				cpDefinitionSpecificationOptionValueModelImpl.getUuid()
			};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				cpDefinitionSpecificationOptionValueModelImpl.getUuid(),
				cpDefinitionSpecificationOptionValueModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {
				cpDefinitionSpecificationOptionValueModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByGroupId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByGroupId, args);

			args = new Object[] {
				cpDefinitionSpecificationOptionValueModelImpl.
					getCPDefinitionId()
			};

			finderCache.removeResult(_finderPathCountByCPDefinitionId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCPDefinitionId, args);

			args = new Object[] {
				cpDefinitionSpecificationOptionValueModelImpl.
					getCPSpecificationOptionId()
			};

			finderCache.removeResult(
				_finderPathCountByCPSpecificationOptionId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCPSpecificationOptionId,
				args);

			args = new Object[] {
				cpDefinitionSpecificationOptionValueModelImpl.
					getCPOptionCategoryId()
			};

			finderCache.removeResult(
				_finderPathCountByCPOptionCategoryId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCPOptionCategoryId, args);

			args = new Object[] {
				cpDefinitionSpecificationOptionValueModelImpl.
					getCPDefinitionId(),
				cpDefinitionSpecificationOptionValueModelImpl.
					getCPOptionCategoryId()
			};

			finderCache.removeResult(_finderPathCountByC_COC, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_COC, args);

			args = new Object[] {
				cpDefinitionSpecificationOptionValueModelImpl.
					getCPDefinitionId(),
				cpDefinitionSpecificationOptionValueModelImpl.
					getCPSpecificationOptionId()
			};

			finderCache.removeResult(_finderPathCountByC_CSO, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_CSO, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((cpDefinitionSpecificationOptionValueModelImpl.
					getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpDefinitionSpecificationOptionValueModelImpl.
						getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {
					cpDefinitionSpecificationOptionValueModelImpl.getUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((cpDefinitionSpecificationOptionValueModelImpl.
					getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpDefinitionSpecificationOptionValueModelImpl.
						getOriginalUuid(),
					cpDefinitionSpecificationOptionValueModelImpl.
						getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					cpDefinitionSpecificationOptionValueModelImpl.getUuid(),
					cpDefinitionSpecificationOptionValueModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((cpDefinitionSpecificationOptionValueModelImpl.
					getColumnBitmask() &
				 _finderPathWithoutPaginationFindByGroupId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					cpDefinitionSpecificationOptionValueModelImpl.
						getOriginalGroupId()
				};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);

				args = new Object[] {
					cpDefinitionSpecificationOptionValueModelImpl.getGroupId()
				};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);
			}

			if ((cpDefinitionSpecificationOptionValueModelImpl.
					getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCPDefinitionId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					cpDefinitionSpecificationOptionValueModelImpl.
						getOriginalCPDefinitionId()
				};

				finderCache.removeResult(
					_finderPathCountByCPDefinitionId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCPDefinitionId, args);

				args = new Object[] {
					cpDefinitionSpecificationOptionValueModelImpl.
						getCPDefinitionId()
				};

				finderCache.removeResult(
					_finderPathCountByCPDefinitionId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCPDefinitionId, args);
			}

			if ((cpDefinitionSpecificationOptionValueModelImpl.
					getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCPSpecificationOptionId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					cpDefinitionSpecificationOptionValueModelImpl.
						getOriginalCPSpecificationOptionId()
				};

				finderCache.removeResult(
					_finderPathCountByCPSpecificationOptionId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCPSpecificationOptionId,
					args);

				args = new Object[] {
					cpDefinitionSpecificationOptionValueModelImpl.
						getCPSpecificationOptionId()
				};

				finderCache.removeResult(
					_finderPathCountByCPSpecificationOptionId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCPSpecificationOptionId,
					args);
			}

			if ((cpDefinitionSpecificationOptionValueModelImpl.
					getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCPOptionCategoryId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					cpDefinitionSpecificationOptionValueModelImpl.
						getOriginalCPOptionCategoryId()
				};

				finderCache.removeResult(
					_finderPathCountByCPOptionCategoryId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCPOptionCategoryId, args);

				args = new Object[] {
					cpDefinitionSpecificationOptionValueModelImpl.
						getCPOptionCategoryId()
				};

				finderCache.removeResult(
					_finderPathCountByCPOptionCategoryId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCPOptionCategoryId, args);
			}

			if ((cpDefinitionSpecificationOptionValueModelImpl.
					getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_COC.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpDefinitionSpecificationOptionValueModelImpl.
						getOriginalCPDefinitionId(),
					cpDefinitionSpecificationOptionValueModelImpl.
						getOriginalCPOptionCategoryId()
				};

				finderCache.removeResult(_finderPathCountByC_COC, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_COC, args);

				args = new Object[] {
					cpDefinitionSpecificationOptionValueModelImpl.
						getCPDefinitionId(),
					cpDefinitionSpecificationOptionValueModelImpl.
						getCPOptionCategoryId()
				};

				finderCache.removeResult(_finderPathCountByC_COC, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_COC, args);
			}

			if ((cpDefinitionSpecificationOptionValueModelImpl.
					getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_CSO.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpDefinitionSpecificationOptionValueModelImpl.
						getOriginalCPDefinitionId(),
					cpDefinitionSpecificationOptionValueModelImpl.
						getOriginalCPSpecificationOptionId()
				};

				finderCache.removeResult(_finderPathCountByC_CSO, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_CSO, args);

				args = new Object[] {
					cpDefinitionSpecificationOptionValueModelImpl.
						getCPDefinitionId(),
					cpDefinitionSpecificationOptionValueModelImpl.
						getCPSpecificationOptionId()
				};

				finderCache.removeResult(_finderPathCountByC_CSO, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_CSO, args);
			}
		}

		entityCache.putResult(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueImpl.class,
			cpDefinitionSpecificationOptionValue.getPrimaryKey(),
			cpDefinitionSpecificationOptionValue, false);

		clearUniqueFindersCache(
			cpDefinitionSpecificationOptionValueModelImpl, false);
		cacheUniqueFindersCache(cpDefinitionSpecificationOptionValueModelImpl);

		cpDefinitionSpecificationOptionValue.resetOriginalValues();

		return cpDefinitionSpecificationOptionValue;
	}

	/**
	 * Returns the cp definition specification option value with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp definition specification option value
	 * @return the cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a cp definition specification option value with the primary key could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue findByPrimaryKey(
			Serializable primaryKey)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue = fetchByPrimaryKey(
				primaryKey);

		if (cpDefinitionSpecificationOptionValue == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCPDefinitionSpecificationOptionValueException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return cpDefinitionSpecificationOptionValue;
	}

	/**
	 * Returns the cp definition specification option value with the primary key or throws a <code>NoSuchCPDefinitionSpecificationOptionValueException</code> if it could not be found.
	 *
	 * @param CPDefinitionSpecificationOptionValueId the primary key of the cp definition specification option value
	 * @return the cp definition specification option value
	 * @throws NoSuchCPDefinitionSpecificationOptionValueException if a cp definition specification option value with the primary key could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue findByPrimaryKey(
			long CPDefinitionSpecificationOptionValueId)
		throws NoSuchCPDefinitionSpecificationOptionValueException {

		return findByPrimaryKey(
			(Serializable)CPDefinitionSpecificationOptionValueId);
	}

	/**
	 * Returns the cp definition specification option value with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp definition specification option value
	 * @return the cp definition specification option value, or <code>null</code> if a cp definition specification option value with the primary key could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue fetchByPrimaryKey(
		Serializable primaryKey) {

		Serializable serializable = entityCache.getResult(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue =
				(CPDefinitionSpecificationOptionValue)serializable;

		if (cpDefinitionSpecificationOptionValue == null) {
			Session session = null;

			try {
				session = openSession();

				cpDefinitionSpecificationOptionValue =
					(CPDefinitionSpecificationOptionValue)session.get(
						CPDefinitionSpecificationOptionValueImpl.class,
						primaryKey);

				if (cpDefinitionSpecificationOptionValue != null) {
					cacheResult(cpDefinitionSpecificationOptionValue);
				}
				else {
					entityCache.putResult(
						CPDefinitionSpecificationOptionValueModelImpl.
							ENTITY_CACHE_ENABLED,
						CPDefinitionSpecificationOptionValueImpl.class,
						primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CPDefinitionSpecificationOptionValueModelImpl.
						ENTITY_CACHE_ENABLED,
					CPDefinitionSpecificationOptionValueImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cpDefinitionSpecificationOptionValue;
	}

	/**
	 * Returns the cp definition specification option value with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param CPDefinitionSpecificationOptionValueId the primary key of the cp definition specification option value
	 * @return the cp definition specification option value, or <code>null</code> if a cp definition specification option value with the primary key could not be found
	 */
	@Override
	public CPDefinitionSpecificationOptionValue fetchByPrimaryKey(
		long CPDefinitionSpecificationOptionValueId) {

		return fetchByPrimaryKey(
			(Serializable)CPDefinitionSpecificationOptionValueId);
	}

	@Override
	public Map<Serializable, CPDefinitionSpecificationOptionValue>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CPDefinitionSpecificationOptionValue> map =
			new HashMap<Serializable, CPDefinitionSpecificationOptionValue>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue = fetchByPrimaryKey(
					primaryKey);

			if (cpDefinitionSpecificationOptionValue != null) {
				map.put(primaryKey, cpDefinitionSpecificationOptionValue);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CPDefinitionSpecificationOptionValueModelImpl.
					ENTITY_CACHE_ENABLED,
				CPDefinitionSpecificationOptionValueImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(
						primaryKey,
						(CPDefinitionSpecificationOptionValue)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(
			_SQL_SELECT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE_PKS_IN);

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

			for (CPDefinitionSpecificationOptionValue
					cpDefinitionSpecificationOptionValue :
						(List<CPDefinitionSpecificationOptionValue>)q.list()) {

				map.put(
					cpDefinitionSpecificationOptionValue.getPrimaryKeyObj(),
					cpDefinitionSpecificationOptionValue);

				cacheResult(cpDefinitionSpecificationOptionValue);

				uncachedPrimaryKeys.remove(
					cpDefinitionSpecificationOptionValue.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CPDefinitionSpecificationOptionValueModelImpl.
						ENTITY_CACHE_ENABLED,
					CPDefinitionSpecificationOptionValueImpl.class, primaryKey,
					nullModel);
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
	 * Returns all the cp definition specification option values.
	 *
	 * @return the cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp definition specification option values.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @return the range of cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findAll(
		int start, int end) {

		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp definition specification option values.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findAll(
		int start, int end,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp definition specification option values.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionSpecificationOptionValueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp definition specification option values
	 * @param end the upper bound of the range of cp definition specification option values (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cp definition specification option values
	 */
	@Override
	public List<CPDefinitionSpecificationOptionValue> findAll(
		int start, int end,
		OrderByComparator<CPDefinitionSpecificationOptionValue>
			orderByComparator,
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

		List<CPDefinitionSpecificationOptionValue> list = null;

		if (retrieveFromCache) {
			list =
				(List<CPDefinitionSpecificationOptionValue>)
					finderCache.getResult(finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CPDEFINITIONSPECIFICATIONOPTIONVALUE);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CPDEFINITIONSPECIFICATIONOPTIONVALUE;

				if (pagination) {
					sql = sql.concat(
						CPDefinitionSpecificationOptionValueModelImpl.
							ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list =
						(List<CPDefinitionSpecificationOptionValue>)
							QueryUtil.list(q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list =
						(List<CPDefinitionSpecificationOptionValue>)
							QueryUtil.list(q, getDialect(), start, end);
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
	 * Removes all the cp definition specification option values from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue : findAll()) {

			remove(cpDefinitionSpecificationOptionValue);
		}
	}

	/**
	 * Returns the number of cp definition specification option values.
	 *
	 * @return the number of cp definition specification option values
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(
					_SQL_COUNT_CPDEFINITIONSPECIFICATIONOPTIONVALUE);

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
		return CPDefinitionSpecificationOptionValueModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cp definition specification option value persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			CPDefinitionSpecificationOptionValueModelImpl.UUID_COLUMN_BITMASK |
			CPDefinitionSpecificationOptionValueModelImpl.
				PRIORITY_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUuid", new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			CPDefinitionSpecificationOptionValueModelImpl.UUID_COLUMN_BITMASK |
			CPDefinitionSpecificationOptionValueModelImpl.
				GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			CPDefinitionSpecificationOptionValueModelImpl.UUID_COLUMN_BITMASK |
			CPDefinitionSpecificationOptionValueModelImpl.
				COMPANYID_COLUMN_BITMASK |
			CPDefinitionSpecificationOptionValueModelImpl.
				PRIORITY_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()},
			CPDefinitionSpecificationOptionValueModelImpl.
				GROUPID_COLUMN_BITMASK |
			CPDefinitionSpecificationOptionValueModelImpl.
				PRIORITY_COLUMN_BITMASK);

		_finderPathCountByGroupId = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupId", new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByCPDefinitionId = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCPDefinitionId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCPDefinitionId = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCPDefinitionId",
			new String[] {Long.class.getName()},
			CPDefinitionSpecificationOptionValueModelImpl.
				CPDEFINITIONID_COLUMN_BITMASK |
			CPDefinitionSpecificationOptionValueModelImpl.
				PRIORITY_COLUMN_BITMASK);

		_finderPathCountByCPDefinitionId = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCPDefinitionId", new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByCPSpecificationOptionId = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCPSpecificationOptionId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCPSpecificationOptionId =
			new FinderPath(
				CPDefinitionSpecificationOptionValueModelImpl.
					ENTITY_CACHE_ENABLED,
				CPDefinitionSpecificationOptionValueModelImpl.
					FINDER_CACHE_ENABLED,
				CPDefinitionSpecificationOptionValueImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByCPSpecificationOptionId",
				new String[] {Long.class.getName()},
				CPDefinitionSpecificationOptionValueModelImpl.
					CPSPECIFICATIONOPTIONID_COLUMN_BITMASK |
				CPDefinitionSpecificationOptionValueModelImpl.
					PRIORITY_COLUMN_BITMASK);

		_finderPathCountByCPSpecificationOptionId = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCPSpecificationOptionId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByCPOptionCategoryId = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCPOptionCategoryId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCPOptionCategoryId = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCPOptionCategoryId", new String[] {Long.class.getName()},
			CPDefinitionSpecificationOptionValueModelImpl.
				CPOPTIONCATEGORYID_COLUMN_BITMASK |
			CPDefinitionSpecificationOptionValueModelImpl.
				PRIORITY_COLUMN_BITMASK);

		_finderPathCountByCPOptionCategoryId = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCPOptionCategoryId", new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByC_COC = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_COC",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_COC = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_COC",
			new String[] {Long.class.getName(), Long.class.getName()},
			CPDefinitionSpecificationOptionValueModelImpl.
				CPDEFINITIONID_COLUMN_BITMASK |
			CPDefinitionSpecificationOptionValueModelImpl.
				CPOPTIONCATEGORYID_COLUMN_BITMASK |
			CPDefinitionSpecificationOptionValueModelImpl.
				PRIORITY_COLUMN_BITMASK);

		_finderPathCountByC_COC = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByC_COC",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByC_CSO = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_CSO",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_CSO = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_CSO",
			new String[] {Long.class.getName(), Long.class.getName()},
			CPDefinitionSpecificationOptionValueModelImpl.
				CPDEFINITIONID_COLUMN_BITMASK |
			CPDefinitionSpecificationOptionValueModelImpl.
				CPSPECIFICATIONOPTIONID_COLUMN_BITMASK |
			CPDefinitionSpecificationOptionValueModelImpl.
				PRIORITY_COLUMN_BITMASK);

		_finderPathCountByC_CSO = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByC_CSO",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathFetchByC_CSOVI = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByC_CSOVI",
			new String[] {Long.class.getName(), Long.class.getName()},
			CPDefinitionSpecificationOptionValueModelImpl.
				CPDEFINITIONID_COLUMN_BITMASK |
			CPDefinitionSpecificationOptionValueModelImpl.
				CPDEFINITIONSPECIFICATIONOPTIONVALUEID_COLUMN_BITMASK);

		_finderPathCountByC_CSOVI = new FinderPath(
			CPDefinitionSpecificationOptionValueModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionSpecificationOptionValueModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByC_CSOVI",
			new String[] {Long.class.getName(), Long.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(
			CPDefinitionSpecificationOptionValueImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String
		_SQL_SELECT_CPDEFINITIONSPECIFICATIONOPTIONVALUE =
			"SELECT cpDefinitionSpecificationOptionValue FROM CPDefinitionSpecificationOptionValue cpDefinitionSpecificationOptionValue";

	private static final String
		_SQL_SELECT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE_PKS_IN =
			"SELECT cpDefinitionSpecificationOptionValue FROM CPDefinitionSpecificationOptionValue cpDefinitionSpecificationOptionValue WHERE CPDSpecificationOptionValueId IN (";

	private static final String
		_SQL_SELECT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE =
			"SELECT cpDefinitionSpecificationOptionValue FROM CPDefinitionSpecificationOptionValue cpDefinitionSpecificationOptionValue WHERE ";

	private static final String
		_SQL_COUNT_CPDEFINITIONSPECIFICATIONOPTIONVALUE =
			"SELECT COUNT(cpDefinitionSpecificationOptionValue) FROM CPDefinitionSpecificationOptionValue cpDefinitionSpecificationOptionValue";

	private static final String
		_SQL_COUNT_CPDEFINITIONSPECIFICATIONOPTIONVALUE_WHERE =
			"SELECT COUNT(cpDefinitionSpecificationOptionValue) FROM CPDefinitionSpecificationOptionValue cpDefinitionSpecificationOptionValue WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"cpDefinitionSpecificationOptionValue.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CPDefinitionSpecificationOptionValue exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CPDefinitionSpecificationOptionValue exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CPDefinitionSpecificationOptionValuePersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "CPDefinitionSpecificationOptionValueId"});

}