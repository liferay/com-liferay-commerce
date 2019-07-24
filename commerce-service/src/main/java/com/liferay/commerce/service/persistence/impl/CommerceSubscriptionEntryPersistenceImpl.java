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

import com.liferay.commerce.exception.NoSuchSubscriptionEntryException;
import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.model.impl.CommerceSubscriptionEntryImpl;
import com.liferay.commerce.model.impl.CommerceSubscriptionEntryModelImpl;
import com.liferay.commerce.service.persistence.CommerceSubscriptionEntryPersistence;
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
 * The persistence implementation for the commerce subscription entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
@ProviderType
public class CommerceSubscriptionEntryPersistenceImpl
	extends BasePersistenceImpl<CommerceSubscriptionEntry>
	implements CommerceSubscriptionEntryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommerceSubscriptionEntryUtil</code> to access the commerce subscription entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommerceSubscriptionEntryImpl.class.getName();

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
	 * Returns all the commerce subscription entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce subscription entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceSubscriptionEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce subscription entries
	 * @param end the upper bound of the range of commerce subscription entries (not inclusive)
	 * @return the range of matching commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce subscription entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceSubscriptionEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce subscription entries
	 * @param end the upper bound of the range of commerce subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce subscription entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceSubscriptionEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce subscription entries
	 * @param end the upper bound of the range of commerce subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator,
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

		List<CommerceSubscriptionEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceSubscriptionEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceSubscriptionEntry commerceSubscriptionEntry :
						list) {

					if (!uuid.equals(commerceSubscriptionEntry.getUuid())) {
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

			query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONENTRY_WHERE);

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
				query.append(CommerceSubscriptionEntryModelImpl.ORDER_BY_JPQL);
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
					list = (List<CommerceSubscriptionEntry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceSubscriptionEntry>)QueryUtil.list(
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
	 * Returns the first commerce subscription entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry findByUuid_First(
			String uuid,
			OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		CommerceSubscriptionEntry commerceSubscriptionEntry = fetchByUuid_First(
			uuid, orderByComparator);

		if (commerceSubscriptionEntry != null) {
			return commerceSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the first commerce subscription entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry fetchByUuid_First(
		String uuid,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator) {

		List<CommerceSubscriptionEntry> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce subscription entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry findByUuid_Last(
			String uuid,
			OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		CommerceSubscriptionEntry commerceSubscriptionEntry = fetchByUuid_Last(
			uuid, orderByComparator);

		if (commerceSubscriptionEntry != null) {
			return commerceSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the last commerce subscription entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry fetchByUuid_Last(
		String uuid,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CommerceSubscriptionEntry> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce subscription entries before and after the current commerce subscription entry in the ordered set where uuid = &#63;.
	 *
	 * @param commerceSubscriptionEntryId the primary key of the current commerce subscription entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce subscription entry
	 * @throws NoSuchSubscriptionEntryException if a commerce subscription entry with the primary key could not be found
	 */
	@Override
	public CommerceSubscriptionEntry[] findByUuid_PrevAndNext(
			long commerceSubscriptionEntryId, String uuid,
			OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		uuid = Objects.toString(uuid, "");

		CommerceSubscriptionEntry commerceSubscriptionEntry = findByPrimaryKey(
			commerceSubscriptionEntryId);

		Session session = null;

		try {
			session = openSession();

			CommerceSubscriptionEntry[] array =
				new CommerceSubscriptionEntryImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, commerceSubscriptionEntry, uuid, orderByComparator,
				true);

			array[1] = commerceSubscriptionEntry;

			array[2] = getByUuid_PrevAndNext(
				session, commerceSubscriptionEntry, uuid, orderByComparator,
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

	protected CommerceSubscriptionEntry getByUuid_PrevAndNext(
		Session session, CommerceSubscriptionEntry commerceSubscriptionEntry,
		String uuid,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONENTRY_WHERE);

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
			query.append(CommerceSubscriptionEntryModelImpl.ORDER_BY_JPQL);
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
						commerceSubscriptionEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceSubscriptionEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce subscription entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CommerceSubscriptionEntry commerceSubscriptionEntry :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commerceSubscriptionEntry);
		}
	}

	/**
	 * Returns the number of commerce subscription entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching commerce subscription entries
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCESUBSCRIPTIONENTRY_WHERE);

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
		"commerceSubscriptionEntry.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(commerceSubscriptionEntry.uuid IS NULL OR commerceSubscriptionEntry.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the commerce subscription entry where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchSubscriptionEntryException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching commerce subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry findByUUID_G(String uuid, long groupId)
		throws NoSuchSubscriptionEntryException {

		CommerceSubscriptionEntry commerceSubscriptionEntry = fetchByUUID_G(
			uuid, groupId);

		if (commerceSubscriptionEntry == null) {
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

			throw new NoSuchSubscriptionEntryException(msg.toString());
		}

		return commerceSubscriptionEntry;
	}

	/**
	 * Returns the commerce subscription entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the commerce subscription entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = new Object[] {uuid, groupId};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof CommerceSubscriptionEntry) {
			CommerceSubscriptionEntry commerceSubscriptionEntry =
				(CommerceSubscriptionEntry)result;

			if (!Objects.equals(uuid, commerceSubscriptionEntry.getUuid()) ||
				(groupId != commerceSubscriptionEntry.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONENTRY_WHERE);

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

				List<CommerceSubscriptionEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByUUID_G, finderArgs, list);
				}
				else {
					CommerceSubscriptionEntry commerceSubscriptionEntry =
						list.get(0);

					result = commerceSubscriptionEntry;

					cacheResult(commerceSubscriptionEntry);
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
			return (CommerceSubscriptionEntry)result;
		}
	}

	/**
	 * Removes the commerce subscription entry where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the commerce subscription entry that was removed
	 */
	@Override
	public CommerceSubscriptionEntry removeByUUID_G(String uuid, long groupId)
		throws NoSuchSubscriptionEntryException {

		CommerceSubscriptionEntry commerceSubscriptionEntry = findByUUID_G(
			uuid, groupId);

		return remove(commerceSubscriptionEntry);
	}

	/**
	 * Returns the number of commerce subscription entries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching commerce subscription entries
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCESUBSCRIPTIONENTRY_WHERE);

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
		"commerceSubscriptionEntry.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(commerceSubscriptionEntry.uuid IS NULL OR commerceSubscriptionEntry.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"commerceSubscriptionEntry.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the commerce subscription entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findByUuid_C(
		String uuid, long companyId) {

		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce subscription entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceSubscriptionEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce subscription entries
	 * @param end the upper bound of the range of commerce subscription entries (not inclusive)
	 * @return the range of matching commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce subscription entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceSubscriptionEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce subscription entries
	 * @param end the upper bound of the range of commerce subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce subscription entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceSubscriptionEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce subscription entries
	 * @param end the upper bound of the range of commerce subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator,
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

		List<CommerceSubscriptionEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceSubscriptionEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceSubscriptionEntry commerceSubscriptionEntry :
						list) {

					if (!uuid.equals(commerceSubscriptionEntry.getUuid()) ||
						(companyId !=
							commerceSubscriptionEntry.getCompanyId())) {

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

			query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONENTRY_WHERE);

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
				query.append(CommerceSubscriptionEntryModelImpl.ORDER_BY_JPQL);
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
					list = (List<CommerceSubscriptionEntry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceSubscriptionEntry>)QueryUtil.list(
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
	 * Returns the first commerce subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (commerceSubscriptionEntry != null) {
			return commerceSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the first commerce subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator) {

		List<CommerceSubscriptionEntry> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (commerceSubscriptionEntry != null) {
			return commerceSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the last commerce subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CommerceSubscriptionEntry> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce subscription entries before and after the current commerce subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param commerceSubscriptionEntryId the primary key of the current commerce subscription entry
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce subscription entry
	 * @throws NoSuchSubscriptionEntryException if a commerce subscription entry with the primary key could not be found
	 */
	@Override
	public CommerceSubscriptionEntry[] findByUuid_C_PrevAndNext(
			long commerceSubscriptionEntryId, String uuid, long companyId,
			OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		uuid = Objects.toString(uuid, "");

		CommerceSubscriptionEntry commerceSubscriptionEntry = findByPrimaryKey(
			commerceSubscriptionEntryId);

		Session session = null;

		try {
			session = openSession();

			CommerceSubscriptionEntry[] array =
				new CommerceSubscriptionEntryImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, commerceSubscriptionEntry, uuid, companyId,
				orderByComparator, true);

			array[1] = commerceSubscriptionEntry;

			array[2] = getByUuid_C_PrevAndNext(
				session, commerceSubscriptionEntry, uuid, companyId,
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

	protected CommerceSubscriptionEntry getByUuid_C_PrevAndNext(
		Session session, CommerceSubscriptionEntry commerceSubscriptionEntry,
		String uuid, long companyId,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONENTRY_WHERE);

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
			query.append(CommerceSubscriptionEntryModelImpl.ORDER_BY_JPQL);
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
						commerceSubscriptionEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceSubscriptionEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce subscription entries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CommerceSubscriptionEntry commerceSubscriptionEntry :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceSubscriptionEntry);
		}
	}

	/**
	 * Returns the number of commerce subscription entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching commerce subscription entries
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCESUBSCRIPTIONENTRY_WHERE);

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
		"commerceSubscriptionEntry.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(commerceSubscriptionEntry.uuid IS NULL OR commerceSubscriptionEntry.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"commerceSubscriptionEntry.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the commerce subscription entries where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce subscription entries where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceSubscriptionEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce subscription entries
	 * @param end the upper bound of the range of commerce subscription entries (not inclusive)
	 * @return the range of matching commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce subscription entries where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceSubscriptionEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce subscription entries
	 * @param end the upper bound of the range of commerce subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce subscription entries where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceSubscriptionEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce subscription entries
	 * @param end the upper bound of the range of commerce subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator,
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

		List<CommerceSubscriptionEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceSubscriptionEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceSubscriptionEntry commerceSubscriptionEntry :
						list) {

					if ((companyId !=
							commerceSubscriptionEntry.getCompanyId())) {

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

			query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONENTRY_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceSubscriptionEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<CommerceSubscriptionEntry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceSubscriptionEntry>)QueryUtil.list(
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
	 * Returns the first commerce subscription entry in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry findByCompanyId_First(
			long companyId,
			OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			fetchByCompanyId_First(companyId, orderByComparator);

		if (commerceSubscriptionEntry != null) {
			return commerceSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the first commerce subscription entry in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry fetchByCompanyId_First(
		long companyId,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator) {

		List<CommerceSubscriptionEntry> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce subscription entry in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry findByCompanyId_Last(
			long companyId,
			OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			fetchByCompanyId_Last(companyId, orderByComparator);

		if (commerceSubscriptionEntry != null) {
			return commerceSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the last commerce subscription entry in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry fetchByCompanyId_Last(
		long companyId,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<CommerceSubscriptionEntry> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce subscription entries before and after the current commerce subscription entry in the ordered set where companyId = &#63;.
	 *
	 * @param commerceSubscriptionEntryId the primary key of the current commerce subscription entry
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce subscription entry
	 * @throws NoSuchSubscriptionEntryException if a commerce subscription entry with the primary key could not be found
	 */
	@Override
	public CommerceSubscriptionEntry[] findByCompanyId_PrevAndNext(
			long commerceSubscriptionEntryId, long companyId,
			OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		CommerceSubscriptionEntry commerceSubscriptionEntry = findByPrimaryKey(
			commerceSubscriptionEntryId);

		Session session = null;

		try {
			session = openSession();

			CommerceSubscriptionEntry[] array =
				new CommerceSubscriptionEntryImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, commerceSubscriptionEntry, companyId,
				orderByComparator, true);

			array[1] = commerceSubscriptionEntry;

			array[2] = getByCompanyId_PrevAndNext(
				session, commerceSubscriptionEntry, companyId,
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

	protected CommerceSubscriptionEntry getByCompanyId_PrevAndNext(
		Session session, CommerceSubscriptionEntry commerceSubscriptionEntry,
		long companyId,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONENTRY_WHERE);

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
			query.append(CommerceSubscriptionEntryModelImpl.ORDER_BY_JPQL);
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
						commerceSubscriptionEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceSubscriptionEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce subscription entries where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (CommerceSubscriptionEntry commerceSubscriptionEntry :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commerceSubscriptionEntry);
		}
	}

	/**
	 * Returns the number of commerce subscription entries where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce subscription entries
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] {companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCESUBSCRIPTIONENTRY_WHERE);

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
		"commerceSubscriptionEntry.companyId = ?";

	private FinderPath _finderPathWithPaginationFindBySubscriptionStatus;
	private FinderPath _finderPathWithoutPaginationFindBySubscriptionStatus;
	private FinderPath _finderPathCountBySubscriptionStatus;

	/**
	 * Returns all the commerce subscription entries where subscriptionStatus = &#63;.
	 *
	 * @param subscriptionStatus the subscription status
	 * @return the matching commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findBySubscriptionStatus(
		int subscriptionStatus) {

		return findBySubscriptionStatus(
			subscriptionStatus, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce subscription entries where subscriptionStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceSubscriptionEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param subscriptionStatus the subscription status
	 * @param start the lower bound of the range of commerce subscription entries
	 * @param end the upper bound of the range of commerce subscription entries (not inclusive)
	 * @return the range of matching commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findBySubscriptionStatus(
		int subscriptionStatus, int start, int end) {

		return findBySubscriptionStatus(subscriptionStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce subscription entries where subscriptionStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceSubscriptionEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param subscriptionStatus the subscription status
	 * @param start the lower bound of the range of commerce subscription entries
	 * @param end the upper bound of the range of commerce subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findBySubscriptionStatus(
		int subscriptionStatus, int start, int end,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator) {

		return findBySubscriptionStatus(
			subscriptionStatus, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce subscription entries where subscriptionStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceSubscriptionEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param subscriptionStatus the subscription status
	 * @param start the lower bound of the range of commerce subscription entries
	 * @param end the upper bound of the range of commerce subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findBySubscriptionStatus(
		int subscriptionStatus, int start, int end,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindBySubscriptionStatus;
			finderArgs = new Object[] {subscriptionStatus};
		}
		else {
			finderPath = _finderPathWithPaginationFindBySubscriptionStatus;
			finderArgs = new Object[] {
				subscriptionStatus, start, end, orderByComparator
			};
		}

		List<CommerceSubscriptionEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceSubscriptionEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceSubscriptionEntry commerceSubscriptionEntry :
						list) {

					if ((subscriptionStatus !=
							commerceSubscriptionEntry.
								getSubscriptionStatus())) {

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

			query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONENTRY_WHERE);

			query.append(
				_FINDER_COLUMN_SUBSCRIPTIONSTATUS_SUBSCRIPTIONSTATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceSubscriptionEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(subscriptionStatus);

				if (!pagination) {
					list = (List<CommerceSubscriptionEntry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceSubscriptionEntry>)QueryUtil.list(
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
	 * Returns the first commerce subscription entry in the ordered set where subscriptionStatus = &#63;.
	 *
	 * @param subscriptionStatus the subscription status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry findBySubscriptionStatus_First(
			int subscriptionStatus,
			OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			fetchBySubscriptionStatus_First(
				subscriptionStatus, orderByComparator);

		if (commerceSubscriptionEntry != null) {
			return commerceSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("subscriptionStatus=");
		msg.append(subscriptionStatus);

		msg.append("}");

		throw new NoSuchSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the first commerce subscription entry in the ordered set where subscriptionStatus = &#63;.
	 *
	 * @param subscriptionStatus the subscription status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry fetchBySubscriptionStatus_First(
		int subscriptionStatus,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator) {

		List<CommerceSubscriptionEntry> list = findBySubscriptionStatus(
			subscriptionStatus, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce subscription entry in the ordered set where subscriptionStatus = &#63;.
	 *
	 * @param subscriptionStatus the subscription status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry findBySubscriptionStatus_Last(
			int subscriptionStatus,
			OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			fetchBySubscriptionStatus_Last(
				subscriptionStatus, orderByComparator);

		if (commerceSubscriptionEntry != null) {
			return commerceSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("subscriptionStatus=");
		msg.append(subscriptionStatus);

		msg.append("}");

		throw new NoSuchSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the last commerce subscription entry in the ordered set where subscriptionStatus = &#63;.
	 *
	 * @param subscriptionStatus the subscription status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry fetchBySubscriptionStatus_Last(
		int subscriptionStatus,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator) {

		int count = countBySubscriptionStatus(subscriptionStatus);

		if (count == 0) {
			return null;
		}

		List<CommerceSubscriptionEntry> list = findBySubscriptionStatus(
			subscriptionStatus, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce subscription entries before and after the current commerce subscription entry in the ordered set where subscriptionStatus = &#63;.
	 *
	 * @param commerceSubscriptionEntryId the primary key of the current commerce subscription entry
	 * @param subscriptionStatus the subscription status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce subscription entry
	 * @throws NoSuchSubscriptionEntryException if a commerce subscription entry with the primary key could not be found
	 */
	@Override
	public CommerceSubscriptionEntry[] findBySubscriptionStatus_PrevAndNext(
			long commerceSubscriptionEntryId, int subscriptionStatus,
			OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		CommerceSubscriptionEntry commerceSubscriptionEntry = findByPrimaryKey(
			commerceSubscriptionEntryId);

		Session session = null;

		try {
			session = openSession();

			CommerceSubscriptionEntry[] array =
				new CommerceSubscriptionEntryImpl[3];

			array[0] = getBySubscriptionStatus_PrevAndNext(
				session, commerceSubscriptionEntry, subscriptionStatus,
				orderByComparator, true);

			array[1] = commerceSubscriptionEntry;

			array[2] = getBySubscriptionStatus_PrevAndNext(
				session, commerceSubscriptionEntry, subscriptionStatus,
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

	protected CommerceSubscriptionEntry getBySubscriptionStatus_PrevAndNext(
		Session session, CommerceSubscriptionEntry commerceSubscriptionEntry,
		int subscriptionStatus,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONENTRY_WHERE);

		query.append(_FINDER_COLUMN_SUBSCRIPTIONSTATUS_SUBSCRIPTIONSTATUS_2);

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
			query.append(CommerceSubscriptionEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(subscriptionStatus);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceSubscriptionEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceSubscriptionEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce subscription entries where subscriptionStatus = &#63; from the database.
	 *
	 * @param subscriptionStatus the subscription status
	 */
	@Override
	public void removeBySubscriptionStatus(int subscriptionStatus) {
		for (CommerceSubscriptionEntry commerceSubscriptionEntry :
				findBySubscriptionStatus(
					subscriptionStatus, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceSubscriptionEntry);
		}
	}

	/**
	 * Returns the number of commerce subscription entries where subscriptionStatus = &#63;.
	 *
	 * @param subscriptionStatus the subscription status
	 * @return the number of matching commerce subscription entries
	 */
	@Override
	public int countBySubscriptionStatus(int subscriptionStatus) {
		FinderPath finderPath = _finderPathCountBySubscriptionStatus;

		Object[] finderArgs = new Object[] {subscriptionStatus};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCESUBSCRIPTIONENTRY_WHERE);

			query.append(
				_FINDER_COLUMN_SUBSCRIPTIONSTATUS_SUBSCRIPTIONSTATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(subscriptionStatus);

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
		_FINDER_COLUMN_SUBSCRIPTIONSTATUS_SUBSCRIPTIONSTATUS_2 =
			"commerceSubscriptionEntry.subscriptionStatus = ?";

	private FinderPath _finderPathWithPaginationFindByC_U;
	private FinderPath _finderPathWithoutPaginationFindByC_U;
	private FinderPath _finderPathCountByC_U;

	/**
	 * Returns all the commerce subscription entries where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @return the matching commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findByC_U(
		long companyId, long userId) {

		return findByC_U(
			companyId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce subscription entries where companyId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceSubscriptionEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of commerce subscription entries
	 * @param end the upper bound of the range of commerce subscription entries (not inclusive)
	 * @return the range of matching commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findByC_U(
		long companyId, long userId, int start, int end) {

		return findByC_U(companyId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce subscription entries where companyId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceSubscriptionEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of commerce subscription entries
	 * @param end the upper bound of the range of commerce subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findByC_U(
		long companyId, long userId, int start, int end,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator) {

		return findByC_U(
			companyId, userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce subscription entries where companyId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceSubscriptionEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of commerce subscription entries
	 * @param end the upper bound of the range of commerce subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findByC_U(
		long companyId, long userId, int start, int end,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByC_U;
			finderArgs = new Object[] {companyId, userId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByC_U;
			finderArgs = new Object[] {
				companyId, userId, start, end, orderByComparator
			};
		}

		List<CommerceSubscriptionEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceSubscriptionEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceSubscriptionEntry commerceSubscriptionEntry :
						list) {

					if ((companyId !=
							commerceSubscriptionEntry.getCompanyId()) ||
						(userId != commerceSubscriptionEntry.getUserId())) {

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

			query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_U_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_U_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceSubscriptionEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(userId);

				if (!pagination) {
					list = (List<CommerceSubscriptionEntry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceSubscriptionEntry>)QueryUtil.list(
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
	 * Returns the first commerce subscription entry in the ordered set where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry findByC_U_First(
			long companyId, long userId,
			OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		CommerceSubscriptionEntry commerceSubscriptionEntry = fetchByC_U_First(
			companyId, userId, orderByComparator);

		if (commerceSubscriptionEntry != null) {
			return commerceSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the first commerce subscription entry in the ordered set where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry fetchByC_U_First(
		long companyId, long userId,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator) {

		List<CommerceSubscriptionEntry> list = findByC_U(
			companyId, userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce subscription entry in the ordered set where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry findByC_U_Last(
			long companyId, long userId,
			OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		CommerceSubscriptionEntry commerceSubscriptionEntry = fetchByC_U_Last(
			companyId, userId, orderByComparator);

		if (commerceSubscriptionEntry != null) {
			return commerceSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the last commerce subscription entry in the ordered set where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry fetchByC_U_Last(
		long companyId, long userId,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator) {

		int count = countByC_U(companyId, userId);

		if (count == 0) {
			return null;
		}

		List<CommerceSubscriptionEntry> list = findByC_U(
			companyId, userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce subscription entries before and after the current commerce subscription entry in the ordered set where companyId = &#63; and userId = &#63;.
	 *
	 * @param commerceSubscriptionEntryId the primary key of the current commerce subscription entry
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce subscription entry
	 * @throws NoSuchSubscriptionEntryException if a commerce subscription entry with the primary key could not be found
	 */
	@Override
	public CommerceSubscriptionEntry[] findByC_U_PrevAndNext(
			long commerceSubscriptionEntryId, long companyId, long userId,
			OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		CommerceSubscriptionEntry commerceSubscriptionEntry = findByPrimaryKey(
			commerceSubscriptionEntryId);

		Session session = null;

		try {
			session = openSession();

			CommerceSubscriptionEntry[] array =
				new CommerceSubscriptionEntryImpl[3];

			array[0] = getByC_U_PrevAndNext(
				session, commerceSubscriptionEntry, companyId, userId,
				orderByComparator, true);

			array[1] = commerceSubscriptionEntry;

			array[2] = getByC_U_PrevAndNext(
				session, commerceSubscriptionEntry, companyId, userId,
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

	protected CommerceSubscriptionEntry getByC_U_PrevAndNext(
		Session session, CommerceSubscriptionEntry commerceSubscriptionEntry,
		long companyId, long userId,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONENTRY_WHERE);

		query.append(_FINDER_COLUMN_C_U_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_U_USERID_2);

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
			query.append(CommerceSubscriptionEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceSubscriptionEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceSubscriptionEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce subscription entries where companyId = &#63; and userId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 */
	@Override
	public void removeByC_U(long companyId, long userId) {
		for (CommerceSubscriptionEntry commerceSubscriptionEntry :
				findByC_U(
					companyId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceSubscriptionEntry);
		}
	}

	/**
	 * Returns the number of commerce subscription entries where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @return the number of matching commerce subscription entries
	 */
	@Override
	public int countByC_U(long companyId, long userId) {
		FinderPath finderPath = _finderPathCountByC_U;

		Object[] finderArgs = new Object[] {companyId, userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCESUBSCRIPTIONENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_U_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_C_U_COMPANYID_2 =
		"commerceSubscriptionEntry.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_U_USERID_2 =
		"commerceSubscriptionEntry.userId = ?";

	private FinderPath _finderPathWithPaginationFindByG_C_U;
	private FinderPath _finderPathWithoutPaginationFindByG_C_U;
	private FinderPath _finderPathCountByG_C_U;

	/**
	 * Returns all the commerce subscription entries where groupId = &#63; and companyId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @return the matching commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findByG_C_U(
		long groupId, long companyId, long userId) {

		return findByG_C_U(
			groupId, companyId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the commerce subscription entries where groupId = &#63; and companyId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceSubscriptionEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of commerce subscription entries
	 * @param end the upper bound of the range of commerce subscription entries (not inclusive)
	 * @return the range of matching commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findByG_C_U(
		long groupId, long companyId, long userId, int start, int end) {

		return findByG_C_U(groupId, companyId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce subscription entries where groupId = &#63; and companyId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceSubscriptionEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of commerce subscription entries
	 * @param end the upper bound of the range of commerce subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findByG_C_U(
		long groupId, long companyId, long userId, int start, int end,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator) {

		return findByG_C_U(
			groupId, companyId, userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce subscription entries where groupId = &#63; and companyId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceSubscriptionEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of commerce subscription entries
	 * @param end the upper bound of the range of commerce subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findByG_C_U(
		long groupId, long companyId, long userId, int start, int end,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByG_C_U;
			finderArgs = new Object[] {groupId, companyId, userId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByG_C_U;
			finderArgs = new Object[] {
				groupId, companyId, userId, start, end, orderByComparator
			};
		}

		List<CommerceSubscriptionEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceSubscriptionEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceSubscriptionEntry commerceSubscriptionEntry :
						list) {

					if ((groupId != commerceSubscriptionEntry.getGroupId()) ||
						(companyId !=
							commerceSubscriptionEntry.getCompanyId()) ||
						(userId != commerceSubscriptionEntry.getUserId())) {

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

			query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_C_U_GROUPID_2);

			query.append(_FINDER_COLUMN_G_C_U_COMPANYID_2);

			query.append(_FINDER_COLUMN_G_C_U_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceSubscriptionEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(userId);

				if (!pagination) {
					list = (List<CommerceSubscriptionEntry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceSubscriptionEntry>)QueryUtil.list(
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
	 * Returns the first commerce subscription entry in the ordered set where groupId = &#63; and companyId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry findByG_C_U_First(
			long groupId, long companyId, long userId,
			OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			fetchByG_C_U_First(groupId, companyId, userId, orderByComparator);

		if (commerceSubscriptionEntry != null) {
			return commerceSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the first commerce subscription entry in the ordered set where groupId = &#63; and companyId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry fetchByG_C_U_First(
		long groupId, long companyId, long userId,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator) {

		List<CommerceSubscriptionEntry> list = findByG_C_U(
			groupId, companyId, userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce subscription entry in the ordered set where groupId = &#63; and companyId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry findByG_C_U_Last(
			long groupId, long companyId, long userId,
			OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		CommerceSubscriptionEntry commerceSubscriptionEntry = fetchByG_C_U_Last(
			groupId, companyId, userId, orderByComparator);

		if (commerceSubscriptionEntry != null) {
			return commerceSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the last commerce subscription entry in the ordered set where groupId = &#63; and companyId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry fetchByG_C_U_Last(
		long groupId, long companyId, long userId,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator) {

		int count = countByG_C_U(groupId, companyId, userId);

		if (count == 0) {
			return null;
		}

		List<CommerceSubscriptionEntry> list = findByG_C_U(
			groupId, companyId, userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce subscription entries before and after the current commerce subscription entry in the ordered set where groupId = &#63; and companyId = &#63; and userId = &#63;.
	 *
	 * @param commerceSubscriptionEntryId the primary key of the current commerce subscription entry
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce subscription entry
	 * @throws NoSuchSubscriptionEntryException if a commerce subscription entry with the primary key could not be found
	 */
	@Override
	public CommerceSubscriptionEntry[] findByG_C_U_PrevAndNext(
			long commerceSubscriptionEntryId, long groupId, long companyId,
			long userId,
			OrderByComparator<CommerceSubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		CommerceSubscriptionEntry commerceSubscriptionEntry = findByPrimaryKey(
			commerceSubscriptionEntryId);

		Session session = null;

		try {
			session = openSession();

			CommerceSubscriptionEntry[] array =
				new CommerceSubscriptionEntryImpl[3];

			array[0] = getByG_C_U_PrevAndNext(
				session, commerceSubscriptionEntry, groupId, companyId, userId,
				orderByComparator, true);

			array[1] = commerceSubscriptionEntry;

			array[2] = getByG_C_U_PrevAndNext(
				session, commerceSubscriptionEntry, groupId, companyId, userId,
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

	protected CommerceSubscriptionEntry getByG_C_U_PrevAndNext(
		Session session, CommerceSubscriptionEntry commerceSubscriptionEntry,
		long groupId, long companyId, long userId,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_C_U_GROUPID_2);

		query.append(_FINDER_COLUMN_G_C_U_COMPANYID_2);

		query.append(_FINDER_COLUMN_G_C_U_USERID_2);

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
			query.append(CommerceSubscriptionEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(companyId);

		qPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceSubscriptionEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceSubscriptionEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce subscription entries where groupId = &#63; and companyId = &#63; and userId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param userId the user ID
	 */
	@Override
	public void removeByG_C_U(long groupId, long companyId, long userId) {
		for (CommerceSubscriptionEntry commerceSubscriptionEntry :
				findByG_C_U(
					groupId, companyId, userId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(commerceSubscriptionEntry);
		}
	}

	/**
	 * Returns the number of commerce subscription entries where groupId = &#63; and companyId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @return the number of matching commerce subscription entries
	 */
	@Override
	public int countByG_C_U(long groupId, long companyId, long userId) {
		FinderPath finderPath = _finderPathCountByG_C_U;

		Object[] finderArgs = new Object[] {groupId, companyId, userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_COMMERCESUBSCRIPTIONENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_C_U_GROUPID_2);

			query.append(_FINDER_COLUMN_G_C_U_COMPANYID_2);

			query.append(_FINDER_COLUMN_G_C_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_G_C_U_GROUPID_2 =
		"commerceSubscriptionEntry.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_C_U_COMPANYID_2 =
		"commerceSubscriptionEntry.companyId = ? AND ";

	private static final String _FINDER_COLUMN_G_C_U_USERID_2 =
		"commerceSubscriptionEntry.userId = ?";

	private FinderPath _finderPathFetchByC_C_C;
	private FinderPath _finderPathCountByC_C_C;

	/**
	 * Returns the commerce subscription entry where CPInstanceUuid = &#63; and CProductId = &#63; and commerceOrderItemId = &#63; or throws a <code>NoSuchSubscriptionEntryException</code> if it could not be found.
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 * @param CProductId the c product ID
	 * @param commerceOrderItemId the commerce order item ID
	 * @return the matching commerce subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry findByC_C_C(
			String CPInstanceUuid, long CProductId, long commerceOrderItemId)
		throws NoSuchSubscriptionEntryException {

		CommerceSubscriptionEntry commerceSubscriptionEntry = fetchByC_C_C(
			CPInstanceUuid, CProductId, commerceOrderItemId);

		if (commerceSubscriptionEntry == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("CPInstanceUuid=");
			msg.append(CPInstanceUuid);

			msg.append(", CProductId=");
			msg.append(CProductId);

			msg.append(", commerceOrderItemId=");
			msg.append(commerceOrderItemId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchSubscriptionEntryException(msg.toString());
		}

		return commerceSubscriptionEntry;
	}

	/**
	 * Returns the commerce subscription entry where CPInstanceUuid = &#63; and CProductId = &#63; and commerceOrderItemId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 * @param CProductId the c product ID
	 * @param commerceOrderItemId the commerce order item ID
	 * @return the matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry fetchByC_C_C(
		String CPInstanceUuid, long CProductId, long commerceOrderItemId) {

		return fetchByC_C_C(
			CPInstanceUuid, CProductId, commerceOrderItemId, true);
	}

	/**
	 * Returns the commerce subscription entry where CPInstanceUuid = &#63; and CProductId = &#63; and commerceOrderItemId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 * @param CProductId the c product ID
	 * @param commerceOrderItemId the commerce order item ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching commerce subscription entry, or <code>null</code> if a matching commerce subscription entry could not be found
	 */
	@Override
	public CommerceSubscriptionEntry fetchByC_C_C(
		String CPInstanceUuid, long CProductId, long commerceOrderItemId,
		boolean retrieveFromCache) {

		CPInstanceUuid = Objects.toString(CPInstanceUuid, "");

		Object[] finderArgs = new Object[] {
			CPInstanceUuid, CProductId, commerceOrderItemId
		};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_C_C, finderArgs, this);
		}

		if (result instanceof CommerceSubscriptionEntry) {
			CommerceSubscriptionEntry commerceSubscriptionEntry =
				(CommerceSubscriptionEntry)result;

			if (!Objects.equals(
					CPInstanceUuid,
					commerceSubscriptionEntry.getCPInstanceUuid()) ||
				(CProductId != commerceSubscriptionEntry.getCProductId()) ||
				(commerceOrderItemId !=
					commerceSubscriptionEntry.getCommerceOrderItemId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONENTRY_WHERE);

			boolean bindCPInstanceUuid = false;

			if (CPInstanceUuid.isEmpty()) {
				query.append(_FINDER_COLUMN_C_C_C_CPINSTANCEUUID_3);
			}
			else {
				bindCPInstanceUuid = true;

				query.append(_FINDER_COLUMN_C_C_C_CPINSTANCEUUID_2);
			}

			query.append(_FINDER_COLUMN_C_C_C_CPRODUCTID_2);

			query.append(_FINDER_COLUMN_C_C_C_COMMERCEORDERITEMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCPInstanceUuid) {
					qPos.add(CPInstanceUuid);
				}

				qPos.add(CProductId);

				qPos.add(commerceOrderItemId);

				List<CommerceSubscriptionEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByC_C_C, finderArgs, list);
				}
				else {
					CommerceSubscriptionEntry commerceSubscriptionEntry =
						list.get(0);

					result = commerceSubscriptionEntry;

					cacheResult(commerceSubscriptionEntry);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByC_C_C, finderArgs);

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
			return (CommerceSubscriptionEntry)result;
		}
	}

	/**
	 * Removes the commerce subscription entry where CPInstanceUuid = &#63; and CProductId = &#63; and commerceOrderItemId = &#63; from the database.
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 * @param CProductId the c product ID
	 * @param commerceOrderItemId the commerce order item ID
	 * @return the commerce subscription entry that was removed
	 */
	@Override
	public CommerceSubscriptionEntry removeByC_C_C(
			String CPInstanceUuid, long CProductId, long commerceOrderItemId)
		throws NoSuchSubscriptionEntryException {

		CommerceSubscriptionEntry commerceSubscriptionEntry = findByC_C_C(
			CPInstanceUuid, CProductId, commerceOrderItemId);

		return remove(commerceSubscriptionEntry);
	}

	/**
	 * Returns the number of commerce subscription entries where CPInstanceUuid = &#63; and CProductId = &#63; and commerceOrderItemId = &#63;.
	 *
	 * @param CPInstanceUuid the cp instance uuid
	 * @param CProductId the c product ID
	 * @param commerceOrderItemId the commerce order item ID
	 * @return the number of matching commerce subscription entries
	 */
	@Override
	public int countByC_C_C(
		String CPInstanceUuid, long CProductId, long commerceOrderItemId) {

		CPInstanceUuid = Objects.toString(CPInstanceUuid, "");

		FinderPath finderPath = _finderPathCountByC_C_C;

		Object[] finderArgs = new Object[] {
			CPInstanceUuid, CProductId, commerceOrderItemId
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_COMMERCESUBSCRIPTIONENTRY_WHERE);

			boolean bindCPInstanceUuid = false;

			if (CPInstanceUuid.isEmpty()) {
				query.append(_FINDER_COLUMN_C_C_C_CPINSTANCEUUID_3);
			}
			else {
				bindCPInstanceUuid = true;

				query.append(_FINDER_COLUMN_C_C_C_CPINSTANCEUUID_2);
			}

			query.append(_FINDER_COLUMN_C_C_C_CPRODUCTID_2);

			query.append(_FINDER_COLUMN_C_C_C_COMMERCEORDERITEMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCPInstanceUuid) {
					qPos.add(CPInstanceUuid);
				}

				qPos.add(CProductId);

				qPos.add(commerceOrderItemId);

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

	private static final String _FINDER_COLUMN_C_C_C_CPINSTANCEUUID_2 =
		"commerceSubscriptionEntry.CPInstanceUuid = ? AND ";

	private static final String _FINDER_COLUMN_C_C_C_CPINSTANCEUUID_3 =
		"(commerceSubscriptionEntry.CPInstanceUuid IS NULL OR commerceSubscriptionEntry.CPInstanceUuid = '') AND ";

	private static final String _FINDER_COLUMN_C_C_C_CPRODUCTID_2 =
		"commerceSubscriptionEntry.CProductId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_C_COMMERCEORDERITEMID_2 =
		"commerceSubscriptionEntry.commerceOrderItemId = ?";

	public CommerceSubscriptionEntryPersistenceImpl() {
		setModelClass(CommerceSubscriptionEntry.class);

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
	 * Caches the commerce subscription entry in the entity cache if it is enabled.
	 *
	 * @param commerceSubscriptionEntry the commerce subscription entry
	 */
	@Override
	public void cacheResult(
		CommerceSubscriptionEntry commerceSubscriptionEntry) {

		entityCache.putResult(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryImpl.class,
			commerceSubscriptionEntry.getPrimaryKey(),
			commerceSubscriptionEntry);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				commerceSubscriptionEntry.getUuid(),
				commerceSubscriptionEntry.getGroupId()
			},
			commerceSubscriptionEntry);

		finderCache.putResult(
			_finderPathFetchByC_C_C,
			new Object[] {
				commerceSubscriptionEntry.getCPInstanceUuid(),
				commerceSubscriptionEntry.getCProductId(),
				commerceSubscriptionEntry.getCommerceOrderItemId()
			},
			commerceSubscriptionEntry);

		commerceSubscriptionEntry.resetOriginalValues();
	}

	/**
	 * Caches the commerce subscription entries in the entity cache if it is enabled.
	 *
	 * @param commerceSubscriptionEntries the commerce subscription entries
	 */
	@Override
	public void cacheResult(
		List<CommerceSubscriptionEntry> commerceSubscriptionEntries) {

		for (CommerceSubscriptionEntry commerceSubscriptionEntry :
				commerceSubscriptionEntries) {

			if (entityCache.getResult(
					CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
					CommerceSubscriptionEntryImpl.class,
					commerceSubscriptionEntry.getPrimaryKey()) == null) {

				cacheResult(commerceSubscriptionEntry);
			}
			else {
				commerceSubscriptionEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce subscription entries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceSubscriptionEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce subscription entry.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CommerceSubscriptionEntry commerceSubscriptionEntry) {

		entityCache.removeResult(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryImpl.class,
			commerceSubscriptionEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(CommerceSubscriptionEntryModelImpl)commerceSubscriptionEntry,
			true);
	}

	@Override
	public void clearCache(
		List<CommerceSubscriptionEntry> commerceSubscriptionEntries) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceSubscriptionEntry commerceSubscriptionEntry :
				commerceSubscriptionEntries) {

			entityCache.removeResult(
				CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
				CommerceSubscriptionEntryImpl.class,
				commerceSubscriptionEntry.getPrimaryKey());

			clearUniqueFindersCache(
				(CommerceSubscriptionEntryModelImpl)commerceSubscriptionEntry,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		CommerceSubscriptionEntryModelImpl commerceSubscriptionEntryModelImpl) {

		Object[] args = new Object[] {
			commerceSubscriptionEntryModelImpl.getUuid(),
			commerceSubscriptionEntryModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, commerceSubscriptionEntryModelImpl,
			false);

		args = new Object[] {
			commerceSubscriptionEntryModelImpl.getCPInstanceUuid(),
			commerceSubscriptionEntryModelImpl.getCProductId(),
			commerceSubscriptionEntryModelImpl.getCommerceOrderItemId()
		};

		finderCache.putResult(
			_finderPathCountByC_C_C, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_C_C, args, commerceSubscriptionEntryModelImpl,
			false);
	}

	protected void clearUniqueFindersCache(
		CommerceSubscriptionEntryModelImpl commerceSubscriptionEntryModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				commerceSubscriptionEntryModelImpl.getUuid(),
				commerceSubscriptionEntryModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((commerceSubscriptionEntryModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				commerceSubscriptionEntryModelImpl.getOriginalUuid(),
				commerceSubscriptionEntryModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
				commerceSubscriptionEntryModelImpl.getCPInstanceUuid(),
				commerceSubscriptionEntryModelImpl.getCProductId(),
				commerceSubscriptionEntryModelImpl.getCommerceOrderItemId()
			};

			finderCache.removeResult(_finderPathCountByC_C_C, args);
			finderCache.removeResult(_finderPathFetchByC_C_C, args);
		}

		if ((commerceSubscriptionEntryModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_C_C.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				commerceSubscriptionEntryModelImpl.getOriginalCPInstanceUuid(),
				commerceSubscriptionEntryModelImpl.getOriginalCProductId(),
				commerceSubscriptionEntryModelImpl.
					getOriginalCommerceOrderItemId()
			};

			finderCache.removeResult(_finderPathCountByC_C_C, args);
			finderCache.removeResult(_finderPathFetchByC_C_C, args);
		}
	}

	/**
	 * Creates a new commerce subscription entry with the primary key. Does not add the commerce subscription entry to the database.
	 *
	 * @param commerceSubscriptionEntryId the primary key for the new commerce subscription entry
	 * @return the new commerce subscription entry
	 */
	@Override
	public CommerceSubscriptionEntry create(long commerceSubscriptionEntryId) {
		CommerceSubscriptionEntry commerceSubscriptionEntry =
			new CommerceSubscriptionEntryImpl();

		commerceSubscriptionEntry.setNew(true);
		commerceSubscriptionEntry.setPrimaryKey(commerceSubscriptionEntryId);

		String uuid = PortalUUIDUtil.generate();

		commerceSubscriptionEntry.setUuid(uuid);

		commerceSubscriptionEntry.setCompanyId(
			CompanyThreadLocal.getCompanyId());

		return commerceSubscriptionEntry;
	}

	/**
	 * Removes the commerce subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceSubscriptionEntryId the primary key of the commerce subscription entry
	 * @return the commerce subscription entry that was removed
	 * @throws NoSuchSubscriptionEntryException if a commerce subscription entry with the primary key could not be found
	 */
	@Override
	public CommerceSubscriptionEntry remove(long commerceSubscriptionEntryId)
		throws NoSuchSubscriptionEntryException {

		return remove((Serializable)commerceSubscriptionEntryId);
	}

	/**
	 * Removes the commerce subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce subscription entry
	 * @return the commerce subscription entry that was removed
	 * @throws NoSuchSubscriptionEntryException if a commerce subscription entry with the primary key could not be found
	 */
	@Override
	public CommerceSubscriptionEntry remove(Serializable primaryKey)
		throws NoSuchSubscriptionEntryException {

		Session session = null;

		try {
			session = openSession();

			CommerceSubscriptionEntry commerceSubscriptionEntry =
				(CommerceSubscriptionEntry)session.get(
					CommerceSubscriptionEntryImpl.class, primaryKey);

			if (commerceSubscriptionEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSubscriptionEntryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commerceSubscriptionEntry);
		}
		catch (NoSuchSubscriptionEntryException nsee) {
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
	protected CommerceSubscriptionEntry removeImpl(
		CommerceSubscriptionEntry commerceSubscriptionEntry) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceSubscriptionEntry)) {
				commerceSubscriptionEntry =
					(CommerceSubscriptionEntry)session.get(
						CommerceSubscriptionEntryImpl.class,
						commerceSubscriptionEntry.getPrimaryKeyObj());
			}

			if (commerceSubscriptionEntry != null) {
				session.delete(commerceSubscriptionEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceSubscriptionEntry != null) {
			clearCache(commerceSubscriptionEntry);
		}

		return commerceSubscriptionEntry;
	}

	@Override
	public CommerceSubscriptionEntry updateImpl(
		CommerceSubscriptionEntry commerceSubscriptionEntry) {

		boolean isNew = commerceSubscriptionEntry.isNew();

		if (!(commerceSubscriptionEntry instanceof
				CommerceSubscriptionEntryModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceSubscriptionEntry.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					commerceSubscriptionEntry);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceSubscriptionEntry proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceSubscriptionEntry implementation " +
					commerceSubscriptionEntry.getClass());
		}

		CommerceSubscriptionEntryModelImpl commerceSubscriptionEntryModelImpl =
			(CommerceSubscriptionEntryModelImpl)commerceSubscriptionEntry;

		if (Validator.isNull(commerceSubscriptionEntry.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			commerceSubscriptionEntry.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceSubscriptionEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceSubscriptionEntry.setCreateDate(now);
			}
			else {
				commerceSubscriptionEntry.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!commerceSubscriptionEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceSubscriptionEntry.setModifiedDate(now);
			}
			else {
				commerceSubscriptionEntry.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceSubscriptionEntry.isNew()) {
				session.save(commerceSubscriptionEntry);

				commerceSubscriptionEntry.setNew(false);
			}
			else {
				commerceSubscriptionEntry =
					(CommerceSubscriptionEntry)session.merge(
						commerceSubscriptionEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceSubscriptionEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				commerceSubscriptionEntryModelImpl.getUuid()
			};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				commerceSubscriptionEntryModelImpl.getUuid(),
				commerceSubscriptionEntryModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {
				commerceSubscriptionEntryModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByCompanyId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCompanyId, args);

			args = new Object[] {
				commerceSubscriptionEntryModelImpl.getSubscriptionStatus()
			};

			finderCache.removeResult(
				_finderPathCountBySubscriptionStatus, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindBySubscriptionStatus, args);

			args = new Object[] {
				commerceSubscriptionEntryModelImpl.getCompanyId(),
				commerceSubscriptionEntryModelImpl.getUserId()
			};

			finderCache.removeResult(_finderPathCountByC_U, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_U, args);

			args = new Object[] {
				commerceSubscriptionEntryModelImpl.getGroupId(),
				commerceSubscriptionEntryModelImpl.getCompanyId(),
				commerceSubscriptionEntryModelImpl.getUserId()
			};

			finderCache.removeResult(_finderPathCountByG_C_U, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByG_C_U, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((commerceSubscriptionEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceSubscriptionEntryModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {
					commerceSubscriptionEntryModelImpl.getUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((commerceSubscriptionEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceSubscriptionEntryModelImpl.getOriginalUuid(),
					commerceSubscriptionEntryModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					commerceSubscriptionEntryModelImpl.getUuid(),
					commerceSubscriptionEntryModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((commerceSubscriptionEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCompanyId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceSubscriptionEntryModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);

				args = new Object[] {
					commerceSubscriptionEntryModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);
			}

			if ((commerceSubscriptionEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindBySubscriptionStatus.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceSubscriptionEntryModelImpl.
						getOriginalSubscriptionStatus()
				};

				finderCache.removeResult(
					_finderPathCountBySubscriptionStatus, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindBySubscriptionStatus, args);

				args = new Object[] {
					commerceSubscriptionEntryModelImpl.getSubscriptionStatus()
				};

				finderCache.removeResult(
					_finderPathCountBySubscriptionStatus, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindBySubscriptionStatus, args);
			}

			if ((commerceSubscriptionEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_U.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceSubscriptionEntryModelImpl.getOriginalCompanyId(),
					commerceSubscriptionEntryModelImpl.getOriginalUserId()
				};

				finderCache.removeResult(_finderPathCountByC_U, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_U, args);

				args = new Object[] {
					commerceSubscriptionEntryModelImpl.getCompanyId(),
					commerceSubscriptionEntryModelImpl.getUserId()
				};

				finderCache.removeResult(_finderPathCountByC_U, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_U, args);
			}

			if ((commerceSubscriptionEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByG_C_U.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceSubscriptionEntryModelImpl.getOriginalGroupId(),
					commerceSubscriptionEntryModelImpl.getOriginalCompanyId(),
					commerceSubscriptionEntryModelImpl.getOriginalUserId()
				};

				finderCache.removeResult(_finderPathCountByG_C_U, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_C_U, args);

				args = new Object[] {
					commerceSubscriptionEntryModelImpl.getGroupId(),
					commerceSubscriptionEntryModelImpl.getCompanyId(),
					commerceSubscriptionEntryModelImpl.getUserId()
				};

				finderCache.removeResult(_finderPathCountByG_C_U, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_C_U, args);
			}
		}

		entityCache.putResult(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryImpl.class,
			commerceSubscriptionEntry.getPrimaryKey(),
			commerceSubscriptionEntry, false);

		clearUniqueFindersCache(commerceSubscriptionEntryModelImpl, false);
		cacheUniqueFindersCache(commerceSubscriptionEntryModelImpl);

		commerceSubscriptionEntry.resetOriginalValues();

		return commerceSubscriptionEntry;
	}

	/**
	 * Returns the commerce subscription entry with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce subscription entry
	 * @return the commerce subscription entry
	 * @throws NoSuchSubscriptionEntryException if a commerce subscription entry with the primary key could not be found
	 */
	@Override
	public CommerceSubscriptionEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSubscriptionEntryException {

		CommerceSubscriptionEntry commerceSubscriptionEntry = fetchByPrimaryKey(
			primaryKey);

		if (commerceSubscriptionEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSubscriptionEntryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commerceSubscriptionEntry;
	}

	/**
	 * Returns the commerce subscription entry with the primary key or throws a <code>NoSuchSubscriptionEntryException</code> if it could not be found.
	 *
	 * @param commerceSubscriptionEntryId the primary key of the commerce subscription entry
	 * @return the commerce subscription entry
	 * @throws NoSuchSubscriptionEntryException if a commerce subscription entry with the primary key could not be found
	 */
	@Override
	public CommerceSubscriptionEntry findByPrimaryKey(
			long commerceSubscriptionEntryId)
		throws NoSuchSubscriptionEntryException {

		return findByPrimaryKey((Serializable)commerceSubscriptionEntryId);
	}

	/**
	 * Returns the commerce subscription entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce subscription entry
	 * @return the commerce subscription entry, or <code>null</code> if a commerce subscription entry with the primary key could not be found
	 */
	@Override
	public CommerceSubscriptionEntry fetchByPrimaryKey(
		Serializable primaryKey) {

		Serializable serializable = entityCache.getResult(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			(CommerceSubscriptionEntry)serializable;

		if (commerceSubscriptionEntry == null) {
			Session session = null;

			try {
				session = openSession();

				commerceSubscriptionEntry =
					(CommerceSubscriptionEntry)session.get(
						CommerceSubscriptionEntryImpl.class, primaryKey);

				if (commerceSubscriptionEntry != null) {
					cacheResult(commerceSubscriptionEntry);
				}
				else {
					entityCache.putResult(
						CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
						CommerceSubscriptionEntryImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
					CommerceSubscriptionEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceSubscriptionEntry;
	}

	/**
	 * Returns the commerce subscription entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceSubscriptionEntryId the primary key of the commerce subscription entry
	 * @return the commerce subscription entry, or <code>null</code> if a commerce subscription entry with the primary key could not be found
	 */
	@Override
	public CommerceSubscriptionEntry fetchByPrimaryKey(
		long commerceSubscriptionEntryId) {

		return fetchByPrimaryKey((Serializable)commerceSubscriptionEntryId);
	}

	@Override
	public Map<Serializable, CommerceSubscriptionEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceSubscriptionEntry> map =
			new HashMap<Serializable, CommerceSubscriptionEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceSubscriptionEntry commerceSubscriptionEntry =
				fetchByPrimaryKey(primaryKey);

			if (commerceSubscriptionEntry != null) {
				map.put(primaryKey, commerceSubscriptionEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
				CommerceSubscriptionEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(
						primaryKey, (CommerceSubscriptionEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONENTRY_WHERE_PKS_IN);

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

			for (CommerceSubscriptionEntry commerceSubscriptionEntry :
					(List<CommerceSubscriptionEntry>)q.list()) {

				map.put(
					commerceSubscriptionEntry.getPrimaryKeyObj(),
					commerceSubscriptionEntry);

				cacheResult(commerceSubscriptionEntry);

				uncachedPrimaryKeys.remove(
					commerceSubscriptionEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
					CommerceSubscriptionEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the commerce subscription entries.
	 *
	 * @return the commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce subscription entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceSubscriptionEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce subscription entries
	 * @param end the upper bound of the range of commerce subscription entries (not inclusive)
	 * @return the range of commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce subscription entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceSubscriptionEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce subscription entries
	 * @param end the upper bound of the range of commerce subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findAll(
		int start, int end,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce subscription entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceSubscriptionEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce subscription entries
	 * @param end the upper bound of the range of commerce subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce subscription entries
	 */
	@Override
	public List<CommerceSubscriptionEntry> findAll(
		int start, int end,
		OrderByComparator<CommerceSubscriptionEntry> orderByComparator,
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

		List<CommerceSubscriptionEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceSubscriptionEntry>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONENTRY);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCESUBSCRIPTIONENTRY;

				if (pagination) {
					sql = sql.concat(
						CommerceSubscriptionEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceSubscriptionEntry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceSubscriptionEntry>)QueryUtil.list(
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
	 * Removes all the commerce subscription entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceSubscriptionEntry commerceSubscriptionEntry : findAll()) {
			remove(commerceSubscriptionEntry);
		}
	}

	/**
	 * Returns the number of commerce subscription entries.
	 *
	 * @return the number of commerce subscription entries
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
					_SQL_COUNT_COMMERCESUBSCRIPTIONENTRY);

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
		return CommerceSubscriptionEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce subscription entry persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			CommerceSubscriptionEntryModelImpl.UUID_COLUMN_BITMASK |
			CommerceSubscriptionEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			CommerceSubscriptionEntryModelImpl.UUID_COLUMN_BITMASK |
			CommerceSubscriptionEntryModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			CommerceSubscriptionEntryModelImpl.UUID_COLUMN_BITMASK |
			CommerceSubscriptionEntryModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceSubscriptionEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByCompanyId = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCompanyId = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] {Long.class.getName()},
			CommerceSubscriptionEntryModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceSubscriptionEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByCompanyId = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindBySubscriptionStatus = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySubscriptionStatus",
			new String[] {
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindBySubscriptionStatus = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBySubscriptionStatus", new String[] {Integer.class.getName()},
			CommerceSubscriptionEntryModelImpl.
				SUBSCRIPTIONSTATUS_COLUMN_BITMASK |
			CommerceSubscriptionEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountBySubscriptionStatus = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySubscriptionStatus",
			new String[] {Integer.class.getName()});

		_finderPathWithPaginationFindByC_U = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_U = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_U",
			new String[] {Long.class.getName(), Long.class.getName()},
			CommerceSubscriptionEntryModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceSubscriptionEntryModelImpl.USERID_COLUMN_BITMASK |
			CommerceSubscriptionEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByC_U = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_U",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByG_C_U = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_C_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByG_C_U = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_C_U",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			CommerceSubscriptionEntryModelImpl.GROUPID_COLUMN_BITMASK |
			CommerceSubscriptionEntryModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceSubscriptionEntryModelImpl.USERID_COLUMN_BITMASK |
			CommerceSubscriptionEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByG_C_U = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_C_U",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

		_finderPathFetchByC_C_C = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_C_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Long.class.getName()
			},
			CommerceSubscriptionEntryModelImpl.CPINSTANCEUUID_COLUMN_BITMASK |
			CommerceSubscriptionEntryModelImpl.CPRODUCTID_COLUMN_BITMASK |
			CommerceSubscriptionEntryModelImpl.
				COMMERCEORDERITEMID_COLUMN_BITMASK);

		_finderPathCountByC_C_C = new FinderPath(
			CommerceSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Long.class.getName()
			});
	}

	public void destroy() {
		entityCache.removeCache(CommerceSubscriptionEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_COMMERCESUBSCRIPTIONENTRY =
		"SELECT commerceSubscriptionEntry FROM CommerceSubscriptionEntry commerceSubscriptionEntry";

	private static final String
		_SQL_SELECT_COMMERCESUBSCRIPTIONENTRY_WHERE_PKS_IN =
			"SELECT commerceSubscriptionEntry FROM CommerceSubscriptionEntry commerceSubscriptionEntry WHERE commerceSubscriptionEntryId IN (";

	private static final String _SQL_SELECT_COMMERCESUBSCRIPTIONENTRY_WHERE =
		"SELECT commerceSubscriptionEntry FROM CommerceSubscriptionEntry commerceSubscriptionEntry WHERE ";

	private static final String _SQL_COUNT_COMMERCESUBSCRIPTIONENTRY =
		"SELECT COUNT(commerceSubscriptionEntry) FROM CommerceSubscriptionEntry commerceSubscriptionEntry";

	private static final String _SQL_COUNT_COMMERCESUBSCRIPTIONENTRY_WHERE =
		"SELECT COUNT(commerceSubscriptionEntry) FROM CommerceSubscriptionEntry commerceSubscriptionEntry WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"commerceSubscriptionEntry.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommerceSubscriptionEntry exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommerceSubscriptionEntry exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceSubscriptionEntryPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}