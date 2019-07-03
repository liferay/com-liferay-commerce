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

import com.liferay.commerce.product.exception.NoSuchCPDefinitionLinkException;
import com.liferay.commerce.product.model.CPDefinitionLink;
import com.liferay.commerce.product.model.impl.CPDefinitionLinkImpl;
import com.liferay.commerce.product.model.impl.CPDefinitionLinkModelImpl;
import com.liferay.commerce.product.service.persistence.CPDefinitionLinkPersistence;
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
 * The persistence implementation for the cp definition link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @generated
 */
@ProviderType
public class CPDefinitionLinkPersistenceImpl
	extends BasePersistenceImpl<CPDefinitionLink>
	implements CPDefinitionLinkPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CPDefinitionLinkUtil</code> to access the cp definition link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CPDefinitionLinkImpl.class.getName();

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
	 * Returns all the cp definition links where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp definition links where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp definition links
	 * @param end the upper bound of the range of cp definition links (not inclusive)
	 * @return the range of matching cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp definition links where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp definition links
	 * @param end the upper bound of the range of cp definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPDefinitionLink> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp definition links where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp definition links
	 * @param end the upper bound of the range of cp definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPDefinitionLink> orderByComparator,
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

		List<CPDefinitionLink> list = null;

		if (retrieveFromCache) {
			list = (List<CPDefinitionLink>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPDefinitionLink cpDefinitionLink : list) {
					if (!uuid.equals(cpDefinitionLink.getUuid())) {
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

			query.append(_SQL_SELECT_CPDEFINITIONLINK_WHERE);

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
				query.append(CPDefinitionLinkModelImpl.ORDER_BY_JPQL);
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
					list = (List<CPDefinitionLink>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPDefinitionLink>)QueryUtil.list(
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
	 * Returns the first cp definition link in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition link
	 * @throws NoSuchCPDefinitionLinkException if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink findByUuid_First(
			String uuid, OrderByComparator<CPDefinitionLink> orderByComparator)
		throws NoSuchCPDefinitionLinkException {

		CPDefinitionLink cpDefinitionLink = fetchByUuid_First(
			uuid, orderByComparator);

		if (cpDefinitionLink != null) {
			return cpDefinitionLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCPDefinitionLinkException(msg.toString());
	}

	/**
	 * Returns the first cp definition link in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition link, or <code>null</code> if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink fetchByUuid_First(
		String uuid, OrderByComparator<CPDefinitionLink> orderByComparator) {

		List<CPDefinitionLink> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp definition link in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition link
	 * @throws NoSuchCPDefinitionLinkException if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink findByUuid_Last(
			String uuid, OrderByComparator<CPDefinitionLink> orderByComparator)
		throws NoSuchCPDefinitionLinkException {

		CPDefinitionLink cpDefinitionLink = fetchByUuid_Last(
			uuid, orderByComparator);

		if (cpDefinitionLink != null) {
			return cpDefinitionLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCPDefinitionLinkException(msg.toString());
	}

	/**
	 * Returns the last cp definition link in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition link, or <code>null</code> if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink fetchByUuid_Last(
		String uuid, OrderByComparator<CPDefinitionLink> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CPDefinitionLink> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp definition links before and after the current cp definition link in the ordered set where uuid = &#63;.
	 *
	 * @param CPDefinitionLinkId the primary key of the current cp definition link
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp definition link
	 * @throws NoSuchCPDefinitionLinkException if a cp definition link with the primary key could not be found
	 */
	@Override
	public CPDefinitionLink[] findByUuid_PrevAndNext(
			long CPDefinitionLinkId, String uuid,
			OrderByComparator<CPDefinitionLink> orderByComparator)
		throws NoSuchCPDefinitionLinkException {

		uuid = Objects.toString(uuid, "");

		CPDefinitionLink cpDefinitionLink = findByPrimaryKey(
			CPDefinitionLinkId);

		Session session = null;

		try {
			session = openSession();

			CPDefinitionLink[] array = new CPDefinitionLinkImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, cpDefinitionLink, uuid, orderByComparator, true);

			array[1] = cpDefinitionLink;

			array[2] = getByUuid_PrevAndNext(
				session, cpDefinitionLink, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPDefinitionLink getByUuid_PrevAndNext(
		Session session, CPDefinitionLink cpDefinitionLink, String uuid,
		OrderByComparator<CPDefinitionLink> orderByComparator,
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

		query.append(_SQL_SELECT_CPDEFINITIONLINK_WHERE);

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
			query.append(CPDefinitionLinkModelImpl.ORDER_BY_JPQL);
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
						cpDefinitionLink)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPDefinitionLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp definition links where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CPDefinitionLink cpDefinitionLink :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cpDefinitionLink);
		}
	}

	/**
	 * Returns the number of cp definition links where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cp definition links
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPDEFINITIONLINK_WHERE);

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
		"cpDefinitionLink.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(cpDefinitionLink.uuid IS NULL OR cpDefinitionLink.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the cp definition link where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchCPDefinitionLinkException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp definition link
	 * @throws NoSuchCPDefinitionLinkException if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink findByUUID_G(String uuid, long groupId)
		throws NoSuchCPDefinitionLinkException {

		CPDefinitionLink cpDefinitionLink = fetchByUUID_G(uuid, groupId);

		if (cpDefinitionLink == null) {
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

			throw new NoSuchCPDefinitionLinkException(msg.toString());
		}

		return cpDefinitionLink;
	}

	/**
	 * Returns the cp definition link where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp definition link, or <code>null</code> if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the cp definition link where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching cp definition link, or <code>null</code> if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = new Object[] {uuid, groupId};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof CPDefinitionLink) {
			CPDefinitionLink cpDefinitionLink = (CPDefinitionLink)result;

			if (!Objects.equals(uuid, cpDefinitionLink.getUuid()) ||
				(groupId != cpDefinitionLink.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CPDEFINITIONLINK_WHERE);

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

				List<CPDefinitionLink> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByUUID_G, finderArgs, list);
				}
				else {
					CPDefinitionLink cpDefinitionLink = list.get(0);

					result = cpDefinitionLink;

					cacheResult(cpDefinitionLink);
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
			return (CPDefinitionLink)result;
		}
	}

	/**
	 * Removes the cp definition link where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the cp definition link that was removed
	 */
	@Override
	public CPDefinitionLink removeByUUID_G(String uuid, long groupId)
		throws NoSuchCPDefinitionLinkException {

		CPDefinitionLink cpDefinitionLink = findByUUID_G(uuid, groupId);

		return remove(cpDefinitionLink);
	}

	/**
	 * Returns the number of cp definition links where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching cp definition links
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPDEFINITIONLINK_WHERE);

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
		"cpDefinitionLink.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(cpDefinitionLink.uuid IS NULL OR cpDefinitionLink.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"cpDefinitionLink.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the cp definition links where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp definition links where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp definition links
	 * @param end the upper bound of the range of cp definition links (not inclusive)
	 * @return the range of matching cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp definition links where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp definition links
	 * @param end the upper bound of the range of cp definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPDefinitionLink> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp definition links where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp definition links
	 * @param end the upper bound of the range of cp definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPDefinitionLink> orderByComparator,
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

		List<CPDefinitionLink> list = null;

		if (retrieveFromCache) {
			list = (List<CPDefinitionLink>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPDefinitionLink cpDefinitionLink : list) {
					if (!uuid.equals(cpDefinitionLink.getUuid()) ||
						(companyId != cpDefinitionLink.getCompanyId())) {

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

			query.append(_SQL_SELECT_CPDEFINITIONLINK_WHERE);

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
				query.append(CPDefinitionLinkModelImpl.ORDER_BY_JPQL);
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
					list = (List<CPDefinitionLink>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPDefinitionLink>)QueryUtil.list(
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
	 * Returns the first cp definition link in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition link
	 * @throws NoSuchCPDefinitionLinkException if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CPDefinitionLink> orderByComparator)
		throws NoSuchCPDefinitionLinkException {

		CPDefinitionLink cpDefinitionLink = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (cpDefinitionLink != null) {
			return cpDefinitionLink;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPDefinitionLinkException(msg.toString());
	}

	/**
	 * Returns the first cp definition link in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition link, or <code>null</code> if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CPDefinitionLink> orderByComparator) {

		List<CPDefinitionLink> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp definition link in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition link
	 * @throws NoSuchCPDefinitionLinkException if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CPDefinitionLink> orderByComparator)
		throws NoSuchCPDefinitionLinkException {

		CPDefinitionLink cpDefinitionLink = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (cpDefinitionLink != null) {
			return cpDefinitionLink;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPDefinitionLinkException(msg.toString());
	}

	/**
	 * Returns the last cp definition link in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition link, or <code>null</code> if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CPDefinitionLink> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CPDefinitionLink> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp definition links before and after the current cp definition link in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param CPDefinitionLinkId the primary key of the current cp definition link
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp definition link
	 * @throws NoSuchCPDefinitionLinkException if a cp definition link with the primary key could not be found
	 */
	@Override
	public CPDefinitionLink[] findByUuid_C_PrevAndNext(
			long CPDefinitionLinkId, String uuid, long companyId,
			OrderByComparator<CPDefinitionLink> orderByComparator)
		throws NoSuchCPDefinitionLinkException {

		uuid = Objects.toString(uuid, "");

		CPDefinitionLink cpDefinitionLink = findByPrimaryKey(
			CPDefinitionLinkId);

		Session session = null;

		try {
			session = openSession();

			CPDefinitionLink[] array = new CPDefinitionLinkImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, cpDefinitionLink, uuid, companyId, orderByComparator,
				true);

			array[1] = cpDefinitionLink;

			array[2] = getByUuid_C_PrevAndNext(
				session, cpDefinitionLink, uuid, companyId, orderByComparator,
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

	protected CPDefinitionLink getByUuid_C_PrevAndNext(
		Session session, CPDefinitionLink cpDefinitionLink, String uuid,
		long companyId, OrderByComparator<CPDefinitionLink> orderByComparator,
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

		query.append(_SQL_SELECT_CPDEFINITIONLINK_WHERE);

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
			query.append(CPDefinitionLinkModelImpl.ORDER_BY_JPQL);
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
						cpDefinitionLink)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPDefinitionLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp definition links where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CPDefinitionLink cpDefinitionLink :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(cpDefinitionLink);
		}
	}

	/**
	 * Returns the number of cp definition links where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching cp definition links
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPDEFINITIONLINK_WHERE);

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
		"cpDefinitionLink.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(cpDefinitionLink.uuid IS NULL OR cpDefinitionLink.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"cpDefinitionLink.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByCPDefinitionId;
	private FinderPath _finderPathWithoutPaginationFindByCPDefinitionId;
	private FinderPath _finderPathCountByCPDefinitionId;

	/**
	 * Returns all the cp definition links where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @return the matching cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findByCPDefinitionId(long CPDefinitionId) {
		return findByCPDefinitionId(
			CPDefinitionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp definition links where CPDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param start the lower bound of the range of cp definition links
	 * @param end the upper bound of the range of cp definition links (not inclusive)
	 * @return the range of matching cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findByCPDefinitionId(
		long CPDefinitionId, int start, int end) {

		return findByCPDefinitionId(CPDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp definition links where CPDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param start the lower bound of the range of cp definition links
	 * @param end the upper bound of the range of cp definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findByCPDefinitionId(
		long CPDefinitionId, int start, int end,
		OrderByComparator<CPDefinitionLink> orderByComparator) {

		return findByCPDefinitionId(
			CPDefinitionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp definition links where CPDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param start the lower bound of the range of cp definition links
	 * @param end the upper bound of the range of cp definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findByCPDefinitionId(
		long CPDefinitionId, int start, int end,
		OrderByComparator<CPDefinitionLink> orderByComparator,
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

		List<CPDefinitionLink> list = null;

		if (retrieveFromCache) {
			list = (List<CPDefinitionLink>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPDefinitionLink cpDefinitionLink : list) {
					if ((CPDefinitionId !=
							cpDefinitionLink.getCPDefinitionId())) {

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

			query.append(_SQL_SELECT_CPDEFINITIONLINK_WHERE);

			query.append(_FINDER_COLUMN_CPDEFINITIONID_CPDEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CPDefinitionLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPDefinitionId);

				if (!pagination) {
					list = (List<CPDefinitionLink>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPDefinitionLink>)QueryUtil.list(
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
	 * Returns the first cp definition link in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition link
	 * @throws NoSuchCPDefinitionLinkException if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink findByCPDefinitionId_First(
			long CPDefinitionId,
			OrderByComparator<CPDefinitionLink> orderByComparator)
		throws NoSuchCPDefinitionLinkException {

		CPDefinitionLink cpDefinitionLink = fetchByCPDefinitionId_First(
			CPDefinitionId, orderByComparator);

		if (cpDefinitionLink != null) {
			return cpDefinitionLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPDefinitionId=");
		msg.append(CPDefinitionId);

		msg.append("}");

		throw new NoSuchCPDefinitionLinkException(msg.toString());
	}

	/**
	 * Returns the first cp definition link in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition link, or <code>null</code> if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink fetchByCPDefinitionId_First(
		long CPDefinitionId,
		OrderByComparator<CPDefinitionLink> orderByComparator) {

		List<CPDefinitionLink> list = findByCPDefinitionId(
			CPDefinitionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp definition link in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition link
	 * @throws NoSuchCPDefinitionLinkException if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink findByCPDefinitionId_Last(
			long CPDefinitionId,
			OrderByComparator<CPDefinitionLink> orderByComparator)
		throws NoSuchCPDefinitionLinkException {

		CPDefinitionLink cpDefinitionLink = fetchByCPDefinitionId_Last(
			CPDefinitionId, orderByComparator);

		if (cpDefinitionLink != null) {
			return cpDefinitionLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPDefinitionId=");
		msg.append(CPDefinitionId);

		msg.append("}");

		throw new NoSuchCPDefinitionLinkException(msg.toString());
	}

	/**
	 * Returns the last cp definition link in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition link, or <code>null</code> if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink fetchByCPDefinitionId_Last(
		long CPDefinitionId,
		OrderByComparator<CPDefinitionLink> orderByComparator) {

		int count = countByCPDefinitionId(CPDefinitionId);

		if (count == 0) {
			return null;
		}

		List<CPDefinitionLink> list = findByCPDefinitionId(
			CPDefinitionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp definition links before and after the current cp definition link in the ordered set where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionLinkId the primary key of the current cp definition link
	 * @param CPDefinitionId the cp definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp definition link
	 * @throws NoSuchCPDefinitionLinkException if a cp definition link with the primary key could not be found
	 */
	@Override
	public CPDefinitionLink[] findByCPDefinitionId_PrevAndNext(
			long CPDefinitionLinkId, long CPDefinitionId,
			OrderByComparator<CPDefinitionLink> orderByComparator)
		throws NoSuchCPDefinitionLinkException {

		CPDefinitionLink cpDefinitionLink = findByPrimaryKey(
			CPDefinitionLinkId);

		Session session = null;

		try {
			session = openSession();

			CPDefinitionLink[] array = new CPDefinitionLinkImpl[3];

			array[0] = getByCPDefinitionId_PrevAndNext(
				session, cpDefinitionLink, CPDefinitionId, orderByComparator,
				true);

			array[1] = cpDefinitionLink;

			array[2] = getByCPDefinitionId_PrevAndNext(
				session, cpDefinitionLink, CPDefinitionId, orderByComparator,
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

	protected CPDefinitionLink getByCPDefinitionId_PrevAndNext(
		Session session, CPDefinitionLink cpDefinitionLink, long CPDefinitionId,
		OrderByComparator<CPDefinitionLink> orderByComparator,
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

		query.append(_SQL_SELECT_CPDEFINITIONLINK_WHERE);

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
			query.append(CPDefinitionLinkModelImpl.ORDER_BY_JPQL);
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
						cpDefinitionLink)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPDefinitionLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp definition links where CPDefinitionId = &#63; from the database.
	 *
	 * @param CPDefinitionId the cp definition ID
	 */
	@Override
	public void removeByCPDefinitionId(long CPDefinitionId) {
		for (CPDefinitionLink cpDefinitionLink :
				findByCPDefinitionId(
					CPDefinitionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(cpDefinitionLink);
		}
	}

	/**
	 * Returns the number of cp definition links where CPDefinitionId = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @return the number of matching cp definition links
	 */
	@Override
	public int countByCPDefinitionId(long CPDefinitionId) {
		FinderPath finderPath = _finderPathCountByCPDefinitionId;

		Object[] finderArgs = new Object[] {CPDefinitionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPDEFINITIONLINK_WHERE);

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
		"cpDefinitionLink.CPDefinitionId = ?";

	private FinderPath _finderPathWithPaginationFindByCProductId;
	private FinderPath _finderPathWithoutPaginationFindByCProductId;
	private FinderPath _finderPathCountByCProductId;

	/**
	 * Returns all the cp definition links where CProductId = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @return the matching cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findByCProductId(long CProductId) {
		return findByCProductId(
			CProductId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp definition links where CProductId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CProductId the c product ID
	 * @param start the lower bound of the range of cp definition links
	 * @param end the upper bound of the range of cp definition links (not inclusive)
	 * @return the range of matching cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findByCProductId(
		long CProductId, int start, int end) {

		return findByCProductId(CProductId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp definition links where CProductId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CProductId the c product ID
	 * @param start the lower bound of the range of cp definition links
	 * @param end the upper bound of the range of cp definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findByCProductId(
		long CProductId, int start, int end,
		OrderByComparator<CPDefinitionLink> orderByComparator) {

		return findByCProductId(
			CProductId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp definition links where CProductId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CProductId the c product ID
	 * @param start the lower bound of the range of cp definition links
	 * @param end the upper bound of the range of cp definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findByCProductId(
		long CProductId, int start, int end,
		OrderByComparator<CPDefinitionLink> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByCProductId;
			finderArgs = new Object[] {CProductId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByCProductId;
			finderArgs = new Object[] {
				CProductId, start, end, orderByComparator
			};
		}

		List<CPDefinitionLink> list = null;

		if (retrieveFromCache) {
			list = (List<CPDefinitionLink>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPDefinitionLink cpDefinitionLink : list) {
					if ((CProductId != cpDefinitionLink.getCProductId())) {
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

			query.append(_SQL_SELECT_CPDEFINITIONLINK_WHERE);

			query.append(_FINDER_COLUMN_CPRODUCTID_CPRODUCTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CPDefinitionLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CProductId);

				if (!pagination) {
					list = (List<CPDefinitionLink>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPDefinitionLink>)QueryUtil.list(
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
	 * Returns the first cp definition link in the ordered set where CProductId = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition link
	 * @throws NoSuchCPDefinitionLinkException if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink findByCProductId_First(
			long CProductId,
			OrderByComparator<CPDefinitionLink> orderByComparator)
		throws NoSuchCPDefinitionLinkException {

		CPDefinitionLink cpDefinitionLink = fetchByCProductId_First(
			CProductId, orderByComparator);

		if (cpDefinitionLink != null) {
			return cpDefinitionLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CProductId=");
		msg.append(CProductId);

		msg.append("}");

		throw new NoSuchCPDefinitionLinkException(msg.toString());
	}

	/**
	 * Returns the first cp definition link in the ordered set where CProductId = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition link, or <code>null</code> if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink fetchByCProductId_First(
		long CProductId,
		OrderByComparator<CPDefinitionLink> orderByComparator) {

		List<CPDefinitionLink> list = findByCProductId(
			CProductId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp definition link in the ordered set where CProductId = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition link
	 * @throws NoSuchCPDefinitionLinkException if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink findByCProductId_Last(
			long CProductId,
			OrderByComparator<CPDefinitionLink> orderByComparator)
		throws NoSuchCPDefinitionLinkException {

		CPDefinitionLink cpDefinitionLink = fetchByCProductId_Last(
			CProductId, orderByComparator);

		if (cpDefinitionLink != null) {
			return cpDefinitionLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CProductId=");
		msg.append(CProductId);

		msg.append("}");

		throw new NoSuchCPDefinitionLinkException(msg.toString());
	}

	/**
	 * Returns the last cp definition link in the ordered set where CProductId = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition link, or <code>null</code> if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink fetchByCProductId_Last(
		long CProductId,
		OrderByComparator<CPDefinitionLink> orderByComparator) {

		int count = countByCProductId(CProductId);

		if (count == 0) {
			return null;
		}

		List<CPDefinitionLink> list = findByCProductId(
			CProductId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp definition links before and after the current cp definition link in the ordered set where CProductId = &#63;.
	 *
	 * @param CPDefinitionLinkId the primary key of the current cp definition link
	 * @param CProductId the c product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp definition link
	 * @throws NoSuchCPDefinitionLinkException if a cp definition link with the primary key could not be found
	 */
	@Override
	public CPDefinitionLink[] findByCProductId_PrevAndNext(
			long CPDefinitionLinkId, long CProductId,
			OrderByComparator<CPDefinitionLink> orderByComparator)
		throws NoSuchCPDefinitionLinkException {

		CPDefinitionLink cpDefinitionLink = findByPrimaryKey(
			CPDefinitionLinkId);

		Session session = null;

		try {
			session = openSession();

			CPDefinitionLink[] array = new CPDefinitionLinkImpl[3];

			array[0] = getByCProductId_PrevAndNext(
				session, cpDefinitionLink, CProductId, orderByComparator, true);

			array[1] = cpDefinitionLink;

			array[2] = getByCProductId_PrevAndNext(
				session, cpDefinitionLink, CProductId, orderByComparator,
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

	protected CPDefinitionLink getByCProductId_PrevAndNext(
		Session session, CPDefinitionLink cpDefinitionLink, long CProductId,
		OrderByComparator<CPDefinitionLink> orderByComparator,
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

		query.append(_SQL_SELECT_CPDEFINITIONLINK_WHERE);

		query.append(_FINDER_COLUMN_CPRODUCTID_CPRODUCTID_2);

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
			query.append(CPDefinitionLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(CProductId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpDefinitionLink)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPDefinitionLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp definition links where CProductId = &#63; from the database.
	 *
	 * @param CProductId the c product ID
	 */
	@Override
	public void removeByCProductId(long CProductId) {
		for (CPDefinitionLink cpDefinitionLink :
				findByCProductId(
					CProductId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cpDefinitionLink);
		}
	}

	/**
	 * Returns the number of cp definition links where CProductId = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @return the number of matching cp definition links
	 */
	@Override
	public int countByCProductId(long CProductId) {
		FinderPath finderPath = _finderPathCountByCProductId;

		Object[] finderArgs = new Object[] {CProductId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPDEFINITIONLINK_WHERE);

			query.append(_FINDER_COLUMN_CPRODUCTID_CPRODUCTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CProductId);

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

	private static final String _FINDER_COLUMN_CPRODUCTID_CPRODUCTID_2 =
		"cpDefinitionLink.CProductId = ?";

	private FinderPath _finderPathWithPaginationFindByCPD_T;
	private FinderPath _finderPathWithoutPaginationFindByCPD_T;
	private FinderPath _finderPathCountByCPD_T;

	/**
	 * Returns all the cp definition links where CPDefinitionId = &#63; and type = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param type the type
	 * @return the matching cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findByCPD_T(
		long CPDefinitionId, String type) {

		return findByCPD_T(
			CPDefinitionId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp definition links where CPDefinitionId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param type the type
	 * @param start the lower bound of the range of cp definition links
	 * @param end the upper bound of the range of cp definition links (not inclusive)
	 * @return the range of matching cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findByCPD_T(
		long CPDefinitionId, String type, int start, int end) {

		return findByCPD_T(CPDefinitionId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp definition links where CPDefinitionId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param type the type
	 * @param start the lower bound of the range of cp definition links
	 * @param end the upper bound of the range of cp definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findByCPD_T(
		long CPDefinitionId, String type, int start, int end,
		OrderByComparator<CPDefinitionLink> orderByComparator) {

		return findByCPD_T(
			CPDefinitionId, type, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp definition links where CPDefinitionId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param type the type
	 * @param start the lower bound of the range of cp definition links
	 * @param end the upper bound of the range of cp definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findByCPD_T(
		long CPDefinitionId, String type, int start, int end,
		OrderByComparator<CPDefinitionLink> orderByComparator,
		boolean retrieveFromCache) {

		type = Objects.toString(type, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByCPD_T;
			finderArgs = new Object[] {CPDefinitionId, type};
		}
		else {
			finderPath = _finderPathWithPaginationFindByCPD_T;
			finderArgs = new Object[] {
				CPDefinitionId, type, start, end, orderByComparator
			};
		}

		List<CPDefinitionLink> list = null;

		if (retrieveFromCache) {
			list = (List<CPDefinitionLink>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPDefinitionLink cpDefinitionLink : list) {
					if ((CPDefinitionId !=
							cpDefinitionLink.getCPDefinitionId()) ||
						!type.equals(cpDefinitionLink.getType())) {

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

			query.append(_SQL_SELECT_CPDEFINITIONLINK_WHERE);

			query.append(_FINDER_COLUMN_CPD_T_CPDEFINITIONID_2);

			boolean bindType = false;

			if (type.isEmpty()) {
				query.append(_FINDER_COLUMN_CPD_T_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_CPD_T_TYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CPDefinitionLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPDefinitionId);

				if (bindType) {
					qPos.add(type);
				}

				if (!pagination) {
					list = (List<CPDefinitionLink>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPDefinitionLink>)QueryUtil.list(
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
	 * Returns the first cp definition link in the ordered set where CPDefinitionId = &#63; and type = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition link
	 * @throws NoSuchCPDefinitionLinkException if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink findByCPD_T_First(
			long CPDefinitionId, String type,
			OrderByComparator<CPDefinitionLink> orderByComparator)
		throws NoSuchCPDefinitionLinkException {

		CPDefinitionLink cpDefinitionLink = fetchByCPD_T_First(
			CPDefinitionId, type, orderByComparator);

		if (cpDefinitionLink != null) {
			return cpDefinitionLink;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPDefinitionId=");
		msg.append(CPDefinitionId);

		msg.append(", type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchCPDefinitionLinkException(msg.toString());
	}

	/**
	 * Returns the first cp definition link in the ordered set where CPDefinitionId = &#63; and type = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition link, or <code>null</code> if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink fetchByCPD_T_First(
		long CPDefinitionId, String type,
		OrderByComparator<CPDefinitionLink> orderByComparator) {

		List<CPDefinitionLink> list = findByCPD_T(
			CPDefinitionId, type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp definition link in the ordered set where CPDefinitionId = &#63; and type = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition link
	 * @throws NoSuchCPDefinitionLinkException if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink findByCPD_T_Last(
			long CPDefinitionId, String type,
			OrderByComparator<CPDefinitionLink> orderByComparator)
		throws NoSuchCPDefinitionLinkException {

		CPDefinitionLink cpDefinitionLink = fetchByCPD_T_Last(
			CPDefinitionId, type, orderByComparator);

		if (cpDefinitionLink != null) {
			return cpDefinitionLink;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPDefinitionId=");
		msg.append(CPDefinitionId);

		msg.append(", type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchCPDefinitionLinkException(msg.toString());
	}

	/**
	 * Returns the last cp definition link in the ordered set where CPDefinitionId = &#63; and type = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition link, or <code>null</code> if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink fetchByCPD_T_Last(
		long CPDefinitionId, String type,
		OrderByComparator<CPDefinitionLink> orderByComparator) {

		int count = countByCPD_T(CPDefinitionId, type);

		if (count == 0) {
			return null;
		}

		List<CPDefinitionLink> list = findByCPD_T(
			CPDefinitionId, type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp definition links before and after the current cp definition link in the ordered set where CPDefinitionId = &#63; and type = &#63;.
	 *
	 * @param CPDefinitionLinkId the primary key of the current cp definition link
	 * @param CPDefinitionId the cp definition ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp definition link
	 * @throws NoSuchCPDefinitionLinkException if a cp definition link with the primary key could not be found
	 */
	@Override
	public CPDefinitionLink[] findByCPD_T_PrevAndNext(
			long CPDefinitionLinkId, long CPDefinitionId, String type,
			OrderByComparator<CPDefinitionLink> orderByComparator)
		throws NoSuchCPDefinitionLinkException {

		type = Objects.toString(type, "");

		CPDefinitionLink cpDefinitionLink = findByPrimaryKey(
			CPDefinitionLinkId);

		Session session = null;

		try {
			session = openSession();

			CPDefinitionLink[] array = new CPDefinitionLinkImpl[3];

			array[0] = getByCPD_T_PrevAndNext(
				session, cpDefinitionLink, CPDefinitionId, type,
				orderByComparator, true);

			array[1] = cpDefinitionLink;

			array[2] = getByCPD_T_PrevAndNext(
				session, cpDefinitionLink, CPDefinitionId, type,
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

	protected CPDefinitionLink getByCPD_T_PrevAndNext(
		Session session, CPDefinitionLink cpDefinitionLink, long CPDefinitionId,
		String type, OrderByComparator<CPDefinitionLink> orderByComparator,
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

		query.append(_SQL_SELECT_CPDEFINITIONLINK_WHERE);

		query.append(_FINDER_COLUMN_CPD_T_CPDEFINITIONID_2);

		boolean bindType = false;

		if (type.isEmpty()) {
			query.append(_FINDER_COLUMN_CPD_T_TYPE_3);
		}
		else {
			bindType = true;

			query.append(_FINDER_COLUMN_CPD_T_TYPE_2);
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
			query.append(CPDefinitionLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(CPDefinitionId);

		if (bindType) {
			qPos.add(type);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpDefinitionLink)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPDefinitionLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp definition links where CPDefinitionId = &#63; and type = &#63; from the database.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param type the type
	 */
	@Override
	public void removeByCPD_T(long CPDefinitionId, String type) {
		for (CPDefinitionLink cpDefinitionLink :
				findByCPD_T(
					CPDefinitionId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(cpDefinitionLink);
		}
	}

	/**
	 * Returns the number of cp definition links where CPDefinitionId = &#63; and type = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param type the type
	 * @return the number of matching cp definition links
	 */
	@Override
	public int countByCPD_T(long CPDefinitionId, String type) {
		type = Objects.toString(type, "");

		FinderPath finderPath = _finderPathCountByCPD_T;

		Object[] finderArgs = new Object[] {CPDefinitionId, type};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPDEFINITIONLINK_WHERE);

			query.append(_FINDER_COLUMN_CPD_T_CPDEFINITIONID_2);

			boolean bindType = false;

			if (type.isEmpty()) {
				query.append(_FINDER_COLUMN_CPD_T_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_CPD_T_TYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPDefinitionId);

				if (bindType) {
					qPos.add(type);
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

	private static final String _FINDER_COLUMN_CPD_T_CPDEFINITIONID_2 =
		"cpDefinitionLink.CPDefinitionId = ? AND ";

	private static final String _FINDER_COLUMN_CPD_T_TYPE_2 =
		"cpDefinitionLink.type = ?";

	private static final String _FINDER_COLUMN_CPD_T_TYPE_3 =
		"(cpDefinitionLink.type IS NULL OR cpDefinitionLink.type = '')";

	private FinderPath _finderPathWithPaginationFindByCP_T;
	private FinderPath _finderPathWithoutPaginationFindByCP_T;
	private FinderPath _finderPathCountByCP_T;

	/**
	 * Returns all the cp definition links where CProductId = &#63; and type = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @param type the type
	 * @return the matching cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findByCP_T(long CProductId, String type) {
		return findByCP_T(
			CProductId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp definition links where CProductId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CProductId the c product ID
	 * @param type the type
	 * @param start the lower bound of the range of cp definition links
	 * @param end the upper bound of the range of cp definition links (not inclusive)
	 * @return the range of matching cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findByCP_T(
		long CProductId, String type, int start, int end) {

		return findByCP_T(CProductId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp definition links where CProductId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CProductId the c product ID
	 * @param type the type
	 * @param start the lower bound of the range of cp definition links
	 * @param end the upper bound of the range of cp definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findByCP_T(
		long CProductId, String type, int start, int end,
		OrderByComparator<CPDefinitionLink> orderByComparator) {

		return findByCP_T(
			CProductId, type, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp definition links where CProductId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CProductId the c product ID
	 * @param type the type
	 * @param start the lower bound of the range of cp definition links
	 * @param end the upper bound of the range of cp definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findByCP_T(
		long CProductId, String type, int start, int end,
		OrderByComparator<CPDefinitionLink> orderByComparator,
		boolean retrieveFromCache) {

		type = Objects.toString(type, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByCP_T;
			finderArgs = new Object[] {CProductId, type};
		}
		else {
			finderPath = _finderPathWithPaginationFindByCP_T;
			finderArgs = new Object[] {
				CProductId, type, start, end, orderByComparator
			};
		}

		List<CPDefinitionLink> list = null;

		if (retrieveFromCache) {
			list = (List<CPDefinitionLink>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPDefinitionLink cpDefinitionLink : list) {
					if ((CProductId != cpDefinitionLink.getCProductId()) ||
						!type.equals(cpDefinitionLink.getType())) {

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

			query.append(_SQL_SELECT_CPDEFINITIONLINK_WHERE);

			query.append(_FINDER_COLUMN_CP_T_CPRODUCTID_2);

			boolean bindType = false;

			if (type.isEmpty()) {
				query.append(_FINDER_COLUMN_CP_T_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_CP_T_TYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CPDefinitionLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CProductId);

				if (bindType) {
					qPos.add(type);
				}

				if (!pagination) {
					list = (List<CPDefinitionLink>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPDefinitionLink>)QueryUtil.list(
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
	 * Returns the first cp definition link in the ordered set where CProductId = &#63; and type = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition link
	 * @throws NoSuchCPDefinitionLinkException if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink findByCP_T_First(
			long CProductId, String type,
			OrderByComparator<CPDefinitionLink> orderByComparator)
		throws NoSuchCPDefinitionLinkException {

		CPDefinitionLink cpDefinitionLink = fetchByCP_T_First(
			CProductId, type, orderByComparator);

		if (cpDefinitionLink != null) {
			return cpDefinitionLink;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CProductId=");
		msg.append(CProductId);

		msg.append(", type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchCPDefinitionLinkException(msg.toString());
	}

	/**
	 * Returns the first cp definition link in the ordered set where CProductId = &#63; and type = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition link, or <code>null</code> if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink fetchByCP_T_First(
		long CProductId, String type,
		OrderByComparator<CPDefinitionLink> orderByComparator) {

		List<CPDefinitionLink> list = findByCP_T(
			CProductId, type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp definition link in the ordered set where CProductId = &#63; and type = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition link
	 * @throws NoSuchCPDefinitionLinkException if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink findByCP_T_Last(
			long CProductId, String type,
			OrderByComparator<CPDefinitionLink> orderByComparator)
		throws NoSuchCPDefinitionLinkException {

		CPDefinitionLink cpDefinitionLink = fetchByCP_T_Last(
			CProductId, type, orderByComparator);

		if (cpDefinitionLink != null) {
			return cpDefinitionLink;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CProductId=");
		msg.append(CProductId);

		msg.append(", type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchCPDefinitionLinkException(msg.toString());
	}

	/**
	 * Returns the last cp definition link in the ordered set where CProductId = &#63; and type = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition link, or <code>null</code> if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink fetchByCP_T_Last(
		long CProductId, String type,
		OrderByComparator<CPDefinitionLink> orderByComparator) {

		int count = countByCP_T(CProductId, type);

		if (count == 0) {
			return null;
		}

		List<CPDefinitionLink> list = findByCP_T(
			CProductId, type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp definition links before and after the current cp definition link in the ordered set where CProductId = &#63; and type = &#63;.
	 *
	 * @param CPDefinitionLinkId the primary key of the current cp definition link
	 * @param CProductId the c product ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp definition link
	 * @throws NoSuchCPDefinitionLinkException if a cp definition link with the primary key could not be found
	 */
	@Override
	public CPDefinitionLink[] findByCP_T_PrevAndNext(
			long CPDefinitionLinkId, long CProductId, String type,
			OrderByComparator<CPDefinitionLink> orderByComparator)
		throws NoSuchCPDefinitionLinkException {

		type = Objects.toString(type, "");

		CPDefinitionLink cpDefinitionLink = findByPrimaryKey(
			CPDefinitionLinkId);

		Session session = null;

		try {
			session = openSession();

			CPDefinitionLink[] array = new CPDefinitionLinkImpl[3];

			array[0] = getByCP_T_PrevAndNext(
				session, cpDefinitionLink, CProductId, type, orderByComparator,
				true);

			array[1] = cpDefinitionLink;

			array[2] = getByCP_T_PrevAndNext(
				session, cpDefinitionLink, CProductId, type, orderByComparator,
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

	protected CPDefinitionLink getByCP_T_PrevAndNext(
		Session session, CPDefinitionLink cpDefinitionLink, long CProductId,
		String type, OrderByComparator<CPDefinitionLink> orderByComparator,
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

		query.append(_SQL_SELECT_CPDEFINITIONLINK_WHERE);

		query.append(_FINDER_COLUMN_CP_T_CPRODUCTID_2);

		boolean bindType = false;

		if (type.isEmpty()) {
			query.append(_FINDER_COLUMN_CP_T_TYPE_3);
		}
		else {
			bindType = true;

			query.append(_FINDER_COLUMN_CP_T_TYPE_2);
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
			query.append(CPDefinitionLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(CProductId);

		if (bindType) {
			qPos.add(type);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpDefinitionLink)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPDefinitionLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp definition links where CProductId = &#63; and type = &#63; from the database.
	 *
	 * @param CProductId the c product ID
	 * @param type the type
	 */
	@Override
	public void removeByCP_T(long CProductId, String type) {
		for (CPDefinitionLink cpDefinitionLink :
				findByCP_T(
					CProductId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(cpDefinitionLink);
		}
	}

	/**
	 * Returns the number of cp definition links where CProductId = &#63; and type = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @param type the type
	 * @return the number of matching cp definition links
	 */
	@Override
	public int countByCP_T(long CProductId, String type) {
		type = Objects.toString(type, "");

		FinderPath finderPath = _finderPathCountByCP_T;

		Object[] finderArgs = new Object[] {CProductId, type};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPDEFINITIONLINK_WHERE);

			query.append(_FINDER_COLUMN_CP_T_CPRODUCTID_2);

			boolean bindType = false;

			if (type.isEmpty()) {
				query.append(_FINDER_COLUMN_CP_T_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_CP_T_TYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CProductId);

				if (bindType) {
					qPos.add(type);
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

	private static final String _FINDER_COLUMN_CP_T_CPRODUCTID_2 =
		"cpDefinitionLink.CProductId = ? AND ";

	private static final String _FINDER_COLUMN_CP_T_TYPE_2 =
		"cpDefinitionLink.type = ?";

	private static final String _FINDER_COLUMN_CP_T_TYPE_3 =
		"(cpDefinitionLink.type IS NULL OR cpDefinitionLink.type = '')";

	private FinderPath _finderPathFetchByC_C_T;
	private FinderPath _finderPathCountByC_C_T;

	/**
	 * Returns the cp definition link where CPDefinitionId = &#63; and CProductId = &#63; and type = &#63; or throws a <code>NoSuchCPDefinitionLinkException</code> if it could not be found.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CProductId the c product ID
	 * @param type the type
	 * @return the matching cp definition link
	 * @throws NoSuchCPDefinitionLinkException if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink findByC_C_T(
			long CPDefinitionId, long CProductId, String type)
		throws NoSuchCPDefinitionLinkException {

		CPDefinitionLink cpDefinitionLink = fetchByC_C_T(
			CPDefinitionId, CProductId, type);

		if (cpDefinitionLink == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("CPDefinitionId=");
			msg.append(CPDefinitionId);

			msg.append(", CProductId=");
			msg.append(CProductId);

			msg.append(", type=");
			msg.append(type);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCPDefinitionLinkException(msg.toString());
		}

		return cpDefinitionLink;
	}

	/**
	 * Returns the cp definition link where CPDefinitionId = &#63; and CProductId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CProductId the c product ID
	 * @param type the type
	 * @return the matching cp definition link, or <code>null</code> if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink fetchByC_C_T(
		long CPDefinitionId, long CProductId, String type) {

		return fetchByC_C_T(CPDefinitionId, CProductId, type, true);
	}

	/**
	 * Returns the cp definition link where CPDefinitionId = &#63; and CProductId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CProductId the c product ID
	 * @param type the type
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching cp definition link, or <code>null</code> if a matching cp definition link could not be found
	 */
	@Override
	public CPDefinitionLink fetchByC_C_T(
		long CPDefinitionId, long CProductId, String type,
		boolean retrieveFromCache) {

		type = Objects.toString(type, "");

		Object[] finderArgs = new Object[] {CPDefinitionId, CProductId, type};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_C_T, finderArgs, this);
		}

		if (result instanceof CPDefinitionLink) {
			CPDefinitionLink cpDefinitionLink = (CPDefinitionLink)result;

			if ((CPDefinitionId != cpDefinitionLink.getCPDefinitionId()) ||
				(CProductId != cpDefinitionLink.getCProductId()) ||
				!Objects.equals(type, cpDefinitionLink.getType())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_CPDEFINITIONLINK_WHERE);

			query.append(_FINDER_COLUMN_C_C_T_CPDEFINITIONID_2);

			query.append(_FINDER_COLUMN_C_C_T_CPRODUCTID_2);

			boolean bindType = false;

			if (type.isEmpty()) {
				query.append(_FINDER_COLUMN_C_C_T_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_C_C_T_TYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPDefinitionId);

				qPos.add(CProductId);

				if (bindType) {
					qPos.add(type);
				}

				List<CPDefinitionLink> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByC_C_T, finderArgs, list);
				}
				else {
					CPDefinitionLink cpDefinitionLink = list.get(0);

					result = cpDefinitionLink;

					cacheResult(cpDefinitionLink);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByC_C_T, finderArgs);

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
			return (CPDefinitionLink)result;
		}
	}

	/**
	 * Removes the cp definition link where CPDefinitionId = &#63; and CProductId = &#63; and type = &#63; from the database.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CProductId the c product ID
	 * @param type the type
	 * @return the cp definition link that was removed
	 */
	@Override
	public CPDefinitionLink removeByC_C_T(
			long CPDefinitionId, long CProductId, String type)
		throws NoSuchCPDefinitionLinkException {

		CPDefinitionLink cpDefinitionLink = findByC_C_T(
			CPDefinitionId, CProductId, type);

		return remove(cpDefinitionLink);
	}

	/**
	 * Returns the number of cp definition links where CPDefinitionId = &#63; and CProductId = &#63; and type = &#63;.
	 *
	 * @param CPDefinitionId the cp definition ID
	 * @param CProductId the c product ID
	 * @param type the type
	 * @return the number of matching cp definition links
	 */
	@Override
	public int countByC_C_T(long CPDefinitionId, long CProductId, String type) {
		type = Objects.toString(type, "");

		FinderPath finderPath = _finderPathCountByC_C_T;

		Object[] finderArgs = new Object[] {CPDefinitionId, CProductId, type};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CPDEFINITIONLINK_WHERE);

			query.append(_FINDER_COLUMN_C_C_T_CPDEFINITIONID_2);

			query.append(_FINDER_COLUMN_C_C_T_CPRODUCTID_2);

			boolean bindType = false;

			if (type.isEmpty()) {
				query.append(_FINDER_COLUMN_C_C_T_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_C_C_T_TYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPDefinitionId);

				qPos.add(CProductId);

				if (bindType) {
					qPos.add(type);
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

	private static final String _FINDER_COLUMN_C_C_T_CPDEFINITIONID_2 =
		"cpDefinitionLink.CPDefinitionId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_T_CPRODUCTID_2 =
		"cpDefinitionLink.CProductId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_T_TYPE_2 =
		"cpDefinitionLink.type = ?";

	private static final String _FINDER_COLUMN_C_C_T_TYPE_3 =
		"(cpDefinitionLink.type IS NULL OR cpDefinitionLink.type = '')";

	public CPDefinitionLinkPersistenceImpl() {
		setModelClass(CPDefinitionLink.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
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
	 * Caches the cp definition link in the entity cache if it is enabled.
	 *
	 * @param cpDefinitionLink the cp definition link
	 */
	@Override
	public void cacheResult(CPDefinitionLink cpDefinitionLink) {
		entityCache.putResult(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkImpl.class, cpDefinitionLink.getPrimaryKey(),
			cpDefinitionLink);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				cpDefinitionLink.getUuid(), cpDefinitionLink.getGroupId()
			},
			cpDefinitionLink);

		finderCache.putResult(
			_finderPathFetchByC_C_T,
			new Object[] {
				cpDefinitionLink.getCPDefinitionId(),
				cpDefinitionLink.getCProductId(), cpDefinitionLink.getType()
			},
			cpDefinitionLink);

		cpDefinitionLink.resetOriginalValues();
	}

	/**
	 * Caches the cp definition links in the entity cache if it is enabled.
	 *
	 * @param cpDefinitionLinks the cp definition links
	 */
	@Override
	public void cacheResult(List<CPDefinitionLink> cpDefinitionLinks) {
		for (CPDefinitionLink cpDefinitionLink : cpDefinitionLinks) {
			if (entityCache.getResult(
					CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
					CPDefinitionLinkImpl.class,
					cpDefinitionLink.getPrimaryKey()) == null) {

				cacheResult(cpDefinitionLink);
			}
			else {
				cpDefinitionLink.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cp definition links.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CPDefinitionLinkImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cp definition link.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CPDefinitionLink cpDefinitionLink) {
		entityCache.removeResult(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkImpl.class, cpDefinitionLink.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(CPDefinitionLinkModelImpl)cpDefinitionLink, true);
	}

	@Override
	public void clearCache(List<CPDefinitionLink> cpDefinitionLinks) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CPDefinitionLink cpDefinitionLink : cpDefinitionLinks) {
			entityCache.removeResult(
				CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
				CPDefinitionLinkImpl.class, cpDefinitionLink.getPrimaryKey());

			clearUniqueFindersCache(
				(CPDefinitionLinkModelImpl)cpDefinitionLink, true);
		}
	}

	protected void cacheUniqueFindersCache(
		CPDefinitionLinkModelImpl cpDefinitionLinkModelImpl) {

		Object[] args = new Object[] {
			cpDefinitionLinkModelImpl.getUuid(),
			cpDefinitionLinkModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, cpDefinitionLinkModelImpl, false);

		args = new Object[] {
			cpDefinitionLinkModelImpl.getCPDefinitionId(),
			cpDefinitionLinkModelImpl.getCProductId(),
			cpDefinitionLinkModelImpl.getType()
		};

		finderCache.putResult(
			_finderPathCountByC_C_T, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_C_T, args, cpDefinitionLinkModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CPDefinitionLinkModelImpl cpDefinitionLinkModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				cpDefinitionLinkModelImpl.getUuid(),
				cpDefinitionLinkModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((cpDefinitionLinkModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				cpDefinitionLinkModelImpl.getOriginalUuid(),
				cpDefinitionLinkModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
				cpDefinitionLinkModelImpl.getCPDefinitionId(),
				cpDefinitionLinkModelImpl.getCProductId(),
				cpDefinitionLinkModelImpl.getType()
			};

			finderCache.removeResult(_finderPathCountByC_C_T, args);
			finderCache.removeResult(_finderPathFetchByC_C_T, args);
		}

		if ((cpDefinitionLinkModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_C_T.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				cpDefinitionLinkModelImpl.getOriginalCPDefinitionId(),
				cpDefinitionLinkModelImpl.getOriginalCProductId(),
				cpDefinitionLinkModelImpl.getOriginalType()
			};

			finderCache.removeResult(_finderPathCountByC_C_T, args);
			finderCache.removeResult(_finderPathFetchByC_C_T, args);
		}
	}

	/**
	 * Creates a new cp definition link with the primary key. Does not add the cp definition link to the database.
	 *
	 * @param CPDefinitionLinkId the primary key for the new cp definition link
	 * @return the new cp definition link
	 */
	@Override
	public CPDefinitionLink create(long CPDefinitionLinkId) {
		CPDefinitionLink cpDefinitionLink = new CPDefinitionLinkImpl();

		cpDefinitionLink.setNew(true);
		cpDefinitionLink.setPrimaryKey(CPDefinitionLinkId);

		String uuid = PortalUUIDUtil.generate();

		cpDefinitionLink.setUuid(uuid);

		cpDefinitionLink.setCompanyId(CompanyThreadLocal.getCompanyId());

		return cpDefinitionLink;
	}

	/**
	 * Removes the cp definition link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPDefinitionLinkId the primary key of the cp definition link
	 * @return the cp definition link that was removed
	 * @throws NoSuchCPDefinitionLinkException if a cp definition link with the primary key could not be found
	 */
	@Override
	public CPDefinitionLink remove(long CPDefinitionLinkId)
		throws NoSuchCPDefinitionLinkException {

		return remove((Serializable)CPDefinitionLinkId);
	}

	/**
	 * Removes the cp definition link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cp definition link
	 * @return the cp definition link that was removed
	 * @throws NoSuchCPDefinitionLinkException if a cp definition link with the primary key could not be found
	 */
	@Override
	public CPDefinitionLink remove(Serializable primaryKey)
		throws NoSuchCPDefinitionLinkException {

		Session session = null;

		try {
			session = openSession();

			CPDefinitionLink cpDefinitionLink = (CPDefinitionLink)session.get(
				CPDefinitionLinkImpl.class, primaryKey);

			if (cpDefinitionLink == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCPDefinitionLinkException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(cpDefinitionLink);
		}
		catch (NoSuchCPDefinitionLinkException nsee) {
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
	protected CPDefinitionLink removeImpl(CPDefinitionLink cpDefinitionLink) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cpDefinitionLink)) {
				cpDefinitionLink = (CPDefinitionLink)session.get(
					CPDefinitionLinkImpl.class,
					cpDefinitionLink.getPrimaryKeyObj());
			}

			if (cpDefinitionLink != null) {
				session.delete(cpDefinitionLink);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cpDefinitionLink != null) {
			clearCache(cpDefinitionLink);
		}

		return cpDefinitionLink;
	}

	@Override
	public CPDefinitionLink updateImpl(CPDefinitionLink cpDefinitionLink) {
		boolean isNew = cpDefinitionLink.isNew();

		if (!(cpDefinitionLink instanceof CPDefinitionLinkModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(cpDefinitionLink.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					cpDefinitionLink);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in cpDefinitionLink proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CPDefinitionLink implementation " +
					cpDefinitionLink.getClass());
		}

		CPDefinitionLinkModelImpl cpDefinitionLinkModelImpl =
			(CPDefinitionLinkModelImpl)cpDefinitionLink;

		if (Validator.isNull(cpDefinitionLink.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			cpDefinitionLink.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (cpDefinitionLink.getCreateDate() == null)) {
			if (serviceContext == null) {
				cpDefinitionLink.setCreateDate(now);
			}
			else {
				cpDefinitionLink.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!cpDefinitionLinkModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				cpDefinitionLink.setModifiedDate(now);
			}
			else {
				cpDefinitionLink.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (cpDefinitionLink.isNew()) {
				session.save(cpDefinitionLink);

				cpDefinitionLink.setNew(false);
			}
			else {
				cpDefinitionLink = (CPDefinitionLink)session.merge(
					cpDefinitionLink);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CPDefinitionLinkModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {cpDefinitionLinkModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				cpDefinitionLinkModelImpl.getUuid(),
				cpDefinitionLinkModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {cpDefinitionLinkModelImpl.getCPDefinitionId()};

			finderCache.removeResult(_finderPathCountByCPDefinitionId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCPDefinitionId, args);

			args = new Object[] {cpDefinitionLinkModelImpl.getCProductId()};

			finderCache.removeResult(_finderPathCountByCProductId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCProductId, args);

			args = new Object[] {
				cpDefinitionLinkModelImpl.getCPDefinitionId(),
				cpDefinitionLinkModelImpl.getType()
			};

			finderCache.removeResult(_finderPathCountByCPD_T, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCPD_T, args);

			args = new Object[] {
				cpDefinitionLinkModelImpl.getCProductId(),
				cpDefinitionLinkModelImpl.getType()
			};

			finderCache.removeResult(_finderPathCountByCP_T, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCP_T, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((cpDefinitionLinkModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpDefinitionLinkModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {cpDefinitionLinkModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((cpDefinitionLinkModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpDefinitionLinkModelImpl.getOriginalUuid(),
					cpDefinitionLinkModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					cpDefinitionLinkModelImpl.getUuid(),
					cpDefinitionLinkModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((cpDefinitionLinkModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCPDefinitionId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					cpDefinitionLinkModelImpl.getOriginalCPDefinitionId()
				};

				finderCache.removeResult(
					_finderPathCountByCPDefinitionId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCPDefinitionId, args);

				args = new Object[] {
					cpDefinitionLinkModelImpl.getCPDefinitionId()
				};

				finderCache.removeResult(
					_finderPathCountByCPDefinitionId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCPDefinitionId, args);
			}

			if ((cpDefinitionLinkModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCProductId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					cpDefinitionLinkModelImpl.getOriginalCProductId()
				};

				finderCache.removeResult(_finderPathCountByCProductId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCProductId, args);

				args = new Object[] {cpDefinitionLinkModelImpl.getCProductId()};

				finderCache.removeResult(_finderPathCountByCProductId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCProductId, args);
			}

			if ((cpDefinitionLinkModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCPD_T.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpDefinitionLinkModelImpl.getOriginalCPDefinitionId(),
					cpDefinitionLinkModelImpl.getOriginalType()
				};

				finderCache.removeResult(_finderPathCountByCPD_T, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCPD_T, args);

				args = new Object[] {
					cpDefinitionLinkModelImpl.getCPDefinitionId(),
					cpDefinitionLinkModelImpl.getType()
				};

				finderCache.removeResult(_finderPathCountByCPD_T, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCPD_T, args);
			}

			if ((cpDefinitionLinkModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCP_T.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpDefinitionLinkModelImpl.getOriginalCProductId(),
					cpDefinitionLinkModelImpl.getOriginalType()
				};

				finderCache.removeResult(_finderPathCountByCP_T, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCP_T, args);

				args = new Object[] {
					cpDefinitionLinkModelImpl.getCProductId(),
					cpDefinitionLinkModelImpl.getType()
				};

				finderCache.removeResult(_finderPathCountByCP_T, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCP_T, args);
			}
		}

		entityCache.putResult(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkImpl.class, cpDefinitionLink.getPrimaryKey(),
			cpDefinitionLink, false);

		clearUniqueFindersCache(cpDefinitionLinkModelImpl, false);
		cacheUniqueFindersCache(cpDefinitionLinkModelImpl);

		cpDefinitionLink.resetOriginalValues();

		return cpDefinitionLink;
	}

	/**
	 * Returns the cp definition link with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp definition link
	 * @return the cp definition link
	 * @throws NoSuchCPDefinitionLinkException if a cp definition link with the primary key could not be found
	 */
	@Override
	public CPDefinitionLink findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCPDefinitionLinkException {

		CPDefinitionLink cpDefinitionLink = fetchByPrimaryKey(primaryKey);

		if (cpDefinitionLink == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCPDefinitionLinkException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return cpDefinitionLink;
	}

	/**
	 * Returns the cp definition link with the primary key or throws a <code>NoSuchCPDefinitionLinkException</code> if it could not be found.
	 *
	 * @param CPDefinitionLinkId the primary key of the cp definition link
	 * @return the cp definition link
	 * @throws NoSuchCPDefinitionLinkException if a cp definition link with the primary key could not be found
	 */
	@Override
	public CPDefinitionLink findByPrimaryKey(long CPDefinitionLinkId)
		throws NoSuchCPDefinitionLinkException {

		return findByPrimaryKey((Serializable)CPDefinitionLinkId);
	}

	/**
	 * Returns the cp definition link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp definition link
	 * @return the cp definition link, or <code>null</code> if a cp definition link with the primary key could not be found
	 */
	@Override
	public CPDefinitionLink fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CPDefinitionLink cpDefinitionLink = (CPDefinitionLink)serializable;

		if (cpDefinitionLink == null) {
			Session session = null;

			try {
				session = openSession();

				cpDefinitionLink = (CPDefinitionLink)session.get(
					CPDefinitionLinkImpl.class, primaryKey);

				if (cpDefinitionLink != null) {
					cacheResult(cpDefinitionLink);
				}
				else {
					entityCache.putResult(
						CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
						CPDefinitionLinkImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
					CPDefinitionLinkImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cpDefinitionLink;
	}

	/**
	 * Returns the cp definition link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param CPDefinitionLinkId the primary key of the cp definition link
	 * @return the cp definition link, or <code>null</code> if a cp definition link with the primary key could not be found
	 */
	@Override
	public CPDefinitionLink fetchByPrimaryKey(long CPDefinitionLinkId) {
		return fetchByPrimaryKey((Serializable)CPDefinitionLinkId);
	}

	@Override
	public Map<Serializable, CPDefinitionLink> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CPDefinitionLink> map =
			new HashMap<Serializable, CPDefinitionLink>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CPDefinitionLink cpDefinitionLink = fetchByPrimaryKey(primaryKey);

			if (cpDefinitionLink != null) {
				map.put(primaryKey, cpDefinitionLink);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
				CPDefinitionLinkImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CPDefinitionLink)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_CPDEFINITIONLINK_WHERE_PKS_IN);

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

			for (CPDefinitionLink cpDefinitionLink :
					(List<CPDefinitionLink>)q.list()) {

				map.put(cpDefinitionLink.getPrimaryKeyObj(), cpDefinitionLink);

				cacheResult(cpDefinitionLink);

				uncachedPrimaryKeys.remove(cpDefinitionLink.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
					CPDefinitionLinkImpl.class, primaryKey, nullModel);
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
	 * Returns all the cp definition links.
	 *
	 * @return the cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp definition links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp definition links
	 * @param end the upper bound of the range of cp definition links (not inclusive)
	 * @return the range of cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp definition links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp definition links
	 * @param end the upper bound of the range of cp definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findAll(
		int start, int end,
		OrderByComparator<CPDefinitionLink> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp definition links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionLinkModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp definition links
	 * @param end the upper bound of the range of cp definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cp definition links
	 */
	@Override
	public List<CPDefinitionLink> findAll(
		int start, int end,
		OrderByComparator<CPDefinitionLink> orderByComparator,
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

		List<CPDefinitionLink> list = null;

		if (retrieveFromCache) {
			list = (List<CPDefinitionLink>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CPDEFINITIONLINK);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CPDEFINITIONLINK;

				if (pagination) {
					sql = sql.concat(CPDefinitionLinkModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CPDefinitionLink>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPDefinitionLink>)QueryUtil.list(
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
	 * Removes all the cp definition links from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CPDefinitionLink cpDefinitionLink : findAll()) {
			remove(cpDefinitionLink);
		}
	}

	/**
	 * Returns the number of cp definition links.
	 *
	 * @return the number of cp definition links
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CPDEFINITIONLINK);

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
		return CPDefinitionLinkModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cp definition link persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionLinkImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionLinkImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			CPDefinitionLinkModelImpl.UUID_COLUMN_BITMASK |
			CPDefinitionLinkModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionLinkImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			CPDefinitionLinkModelImpl.UUID_COLUMN_BITMASK |
			CPDefinitionLinkModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionLinkImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			CPDefinitionLinkModelImpl.UUID_COLUMN_BITMASK |
			CPDefinitionLinkModelImpl.COMPANYID_COLUMN_BITMASK |
			CPDefinitionLinkModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByCPDefinitionId = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionLinkImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCPDefinitionId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCPDefinitionId = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCPDefinitionId",
			new String[] {Long.class.getName()},
			CPDefinitionLinkModelImpl.CPDEFINITIONID_COLUMN_BITMASK |
			CPDefinitionLinkModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByCPDefinitionId = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCPDefinitionId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByCProductId = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionLinkImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCProductId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCProductId = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCProductId",
			new String[] {Long.class.getName()},
			CPDefinitionLinkModelImpl.CPRODUCTID_COLUMN_BITMASK |
			CPDefinitionLinkModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByCProductId = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCProductId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByCPD_T = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionLinkImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCPD_T",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCPD_T = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCPD_T",
			new String[] {Long.class.getName(), String.class.getName()},
			CPDefinitionLinkModelImpl.CPDEFINITIONID_COLUMN_BITMASK |
			CPDefinitionLinkModelImpl.TYPE_COLUMN_BITMASK |
			CPDefinitionLinkModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByCPD_T = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCPD_T",
			new String[] {Long.class.getName(), String.class.getName()});

		_finderPathWithPaginationFindByCP_T = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionLinkImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCP_T",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCP_T = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCP_T",
			new String[] {Long.class.getName(), String.class.getName()},
			CPDefinitionLinkModelImpl.CPRODUCTID_COLUMN_BITMASK |
			CPDefinitionLinkModelImpl.TYPE_COLUMN_BITMASK |
			CPDefinitionLinkModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByCP_T = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCP_T",
			new String[] {Long.class.getName(), String.class.getName()});

		_finderPathFetchByC_C_T = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionLinkImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			CPDefinitionLinkModelImpl.CPDEFINITIONID_COLUMN_BITMASK |
			CPDefinitionLinkModelImpl.CPRODUCTID_COLUMN_BITMASK |
			CPDefinitionLinkModelImpl.TYPE_COLUMN_BITMASK);

		_finderPathCountByC_C_T = new FinderPath(
			CPDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});
	}

	public void destroy() {
		entityCache.removeCache(CPDefinitionLinkImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_CPDEFINITIONLINK =
		"SELECT cpDefinitionLink FROM CPDefinitionLink cpDefinitionLink";

	private static final String _SQL_SELECT_CPDEFINITIONLINK_WHERE_PKS_IN =
		"SELECT cpDefinitionLink FROM CPDefinitionLink cpDefinitionLink WHERE CPDefinitionLinkId IN (";

	private static final String _SQL_SELECT_CPDEFINITIONLINK_WHERE =
		"SELECT cpDefinitionLink FROM CPDefinitionLink cpDefinitionLink WHERE ";

	private static final String _SQL_COUNT_CPDEFINITIONLINK =
		"SELECT COUNT(cpDefinitionLink) FROM CPDefinitionLink cpDefinitionLink";

	private static final String _SQL_COUNT_CPDEFINITIONLINK_WHERE =
		"SELECT COUNT(cpDefinitionLink) FROM CPDefinitionLink cpDefinitionLink WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "cpDefinitionLink.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CPDefinitionLink exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CPDefinitionLink exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CPDefinitionLinkPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "type"});

}