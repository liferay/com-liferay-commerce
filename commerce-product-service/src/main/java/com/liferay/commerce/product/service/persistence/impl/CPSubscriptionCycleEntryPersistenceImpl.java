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

import com.liferay.commerce.product.exception.NoSuchCPSubscriptionCycleEntryException;
import com.liferay.commerce.product.model.CPSubscriptionCycleEntry;
import com.liferay.commerce.product.model.impl.CPSubscriptionCycleEntryImpl;
import com.liferay.commerce.product.model.impl.CPSubscriptionCycleEntryModelImpl;
import com.liferay.commerce.product.service.persistence.CPSubscriptionCycleEntryPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
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
 * The persistence implementation for the cp subscription cycle entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CPSubscriptionCycleEntryPersistence
 * @see com.liferay.commerce.product.service.persistence.CPSubscriptionCycleEntryUtil
 * @generated
 */
@ProviderType
public class CPSubscriptionCycleEntryPersistenceImpl extends BasePersistenceImpl<CPSubscriptionCycleEntry>
	implements CPSubscriptionCycleEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CPSubscriptionCycleEntryUtil} to access the cp subscription cycle entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CPSubscriptionCycleEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionCycleEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionCycleEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionCycleEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionCycleEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			CPSubscriptionCycleEntryModelImpl.UUID_COLUMN_BITMASK |
			CPSubscriptionCycleEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the cp subscription cycle entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cp subscription cycle entries
	 */
	@Override
	public List<CPSubscriptionCycleEntry> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp subscription cycle entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp subscription cycle entries
	 * @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	 * @return the range of matching cp subscription cycle entries
	 */
	@Override
	public List<CPSubscriptionCycleEntry> findByUuid(String uuid, int start,
		int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp subscription cycle entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp subscription cycle entries
	 * @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp subscription cycle entries
	 */
	@Override
	public List<CPSubscriptionCycleEntry> findByUuid(String uuid, int start,
		int end, OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp subscription cycle entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp subscription cycle entries
	 * @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp subscription cycle entries
	 */
	@Override
	public List<CPSubscriptionCycleEntry> findByUuid(String uuid, int start,
		int end, OrderByComparator<CPSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<CPSubscriptionCycleEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CPSubscriptionCycleEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPSubscriptionCycleEntry cpSubscriptionCycleEntry : list) {
					if (!Objects.equals(uuid, cpSubscriptionCycleEntry.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CPSUBSCRIPTIONCYCLEENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CPSubscriptionCycleEntryModelImpl.ORDER_BY_JPQL);
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
					list = (List<CPSubscriptionCycleEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPSubscriptionCycleEntry>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first cp subscription cycle entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp subscription cycle entry
	 * @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry findByUuid_First(String uuid,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws NoSuchCPSubscriptionCycleEntryException {
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry = fetchByUuid_First(uuid,
				orderByComparator);

		if (cpSubscriptionCycleEntry != null) {
			return cpSubscriptionCycleEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCPSubscriptionCycleEntryException(msg.toString());
	}

	/**
	 * Returns the first cp subscription cycle entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry fetchByUuid_First(String uuid,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		List<CPSubscriptionCycleEntry> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp subscription cycle entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp subscription cycle entry
	 * @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry findByUuid_Last(String uuid,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws NoSuchCPSubscriptionCycleEntryException {
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry = fetchByUuid_Last(uuid,
				orderByComparator);

		if (cpSubscriptionCycleEntry != null) {
			return cpSubscriptionCycleEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCPSubscriptionCycleEntryException(msg.toString());
	}

	/**
	 * Returns the last cp subscription cycle entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry fetchByUuid_Last(String uuid,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CPSubscriptionCycleEntry> list = findByUuid(uuid, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp subscription cycle entries before and after the current cp subscription cycle entry in the ordered set where uuid = &#63;.
	 *
	 * @param CPSubscriptionCycleEntryId the primary key of the current cp subscription cycle entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp subscription cycle entry
	 * @throws NoSuchCPSubscriptionCycleEntryException if a cp subscription cycle entry with the primary key could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry[] findByUuid_PrevAndNext(
		long CPSubscriptionCycleEntryId, String uuid,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws NoSuchCPSubscriptionCycleEntryException {
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry = findByPrimaryKey(CPSubscriptionCycleEntryId);

		Session session = null;

		try {
			session = openSession();

			CPSubscriptionCycleEntry[] array = new CPSubscriptionCycleEntryImpl[3];

			array[0] = getByUuid_PrevAndNext(session, cpSubscriptionCycleEntry,
					uuid, orderByComparator, true);

			array[1] = cpSubscriptionCycleEntry;

			array[2] = getByUuid_PrevAndNext(session, cpSubscriptionCycleEntry,
					uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPSubscriptionCycleEntry getByUuid_PrevAndNext(Session session,
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry, String uuid,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CPSUBSCRIPTIONCYCLEENTRY_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals("")) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

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
			query.append(CPSubscriptionCycleEntryModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(cpSubscriptionCycleEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CPSubscriptionCycleEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp subscription cycle entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CPSubscriptionCycleEntry cpSubscriptionCycleEntry : findByUuid(
				uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(cpSubscriptionCycleEntry);
		}
	}

	/**
	 * Returns the number of cp subscription cycle entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cp subscription cycle entries
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPSUBSCRIPTIONCYCLEENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals("")) {
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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "cpSubscriptionCycleEntry.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "cpSubscriptionCycleEntry.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(cpSubscriptionCycleEntry.uuid IS NULL OR cpSubscriptionCycleEntry.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionCycleEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			CPSubscriptionCycleEntryModelImpl.UUID_COLUMN_BITMASK |
			CPSubscriptionCycleEntryModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the cp subscription cycle entry where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCPSubscriptionCycleEntryException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp subscription cycle entry
	 * @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry findByUUID_G(String uuid, long groupId)
		throws NoSuchCPSubscriptionCycleEntryException {
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry = fetchByUUID_G(uuid,
				groupId);

		if (cpSubscriptionCycleEntry == null) {
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

			throw new NoSuchCPSubscriptionCycleEntryException(msg.toString());
		}

		return cpSubscriptionCycleEntry;
	}

	/**
	 * Returns the cp subscription cycle entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the cp subscription cycle entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof CPSubscriptionCycleEntry) {
			CPSubscriptionCycleEntry cpSubscriptionCycleEntry = (CPSubscriptionCycleEntry)result;

			if (!Objects.equals(uuid, cpSubscriptionCycleEntry.getUuid()) ||
					(groupId != cpSubscriptionCycleEntry.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CPSUBSCRIPTIONCYCLEENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals("")) {
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

				List<CPSubscriptionCycleEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					CPSubscriptionCycleEntry cpSubscriptionCycleEntry = list.get(0);

					result = cpSubscriptionCycleEntry;

					cacheResult(cpSubscriptionCycleEntry);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, finderArgs);

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
			return (CPSubscriptionCycleEntry)result;
		}
	}

	/**
	 * Removes the cp subscription cycle entry where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the cp subscription cycle entry that was removed
	 */
	@Override
	public CPSubscriptionCycleEntry removeByUUID_G(String uuid, long groupId)
		throws NoSuchCPSubscriptionCycleEntryException {
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry = findByUUID_G(uuid,
				groupId);

		return remove(cpSubscriptionCycleEntry);
	}

	/**
	 * Returns the number of cp subscription cycle entries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching cp subscription cycle entries
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPSUBSCRIPTIONCYCLEENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals("")) {
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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "cpSubscriptionCycleEntry.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "cpSubscriptionCycleEntry.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(cpSubscriptionCycleEntry.uuid IS NULL OR cpSubscriptionCycleEntry.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "cpSubscriptionCycleEntry.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionCycleEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionCycleEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			CPSubscriptionCycleEntryModelImpl.UUID_COLUMN_BITMASK |
			CPSubscriptionCycleEntryModelImpl.COMPANYID_COLUMN_BITMASK |
			CPSubscriptionCycleEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the cp subscription cycle entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching cp subscription cycle entries
	 */
	@Override
	public List<CPSubscriptionCycleEntry> findByUuid_C(String uuid,
		long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp subscription cycle entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp subscription cycle entries
	 * @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	 * @return the range of matching cp subscription cycle entries
	 */
	@Override
	public List<CPSubscriptionCycleEntry> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp subscription cycle entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp subscription cycle entries
	 * @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp subscription cycle entries
	 */
	@Override
	public List<CPSubscriptionCycleEntry> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp subscription cycle entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp subscription cycle entries
	 * @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp subscription cycle entries
	 */
	@Override
	public List<CPSubscriptionCycleEntry> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<CPSubscriptionCycleEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CPSubscriptionCycleEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPSubscriptionCycleEntry cpSubscriptionCycleEntry : list) {
					if (!Objects.equals(uuid, cpSubscriptionCycleEntry.getUuid()) ||
							(companyId != cpSubscriptionCycleEntry.getCompanyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_CPSUBSCRIPTIONCYCLEENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CPSubscriptionCycleEntryModelImpl.ORDER_BY_JPQL);
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
					list = (List<CPSubscriptionCycleEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPSubscriptionCycleEntry>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first cp subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp subscription cycle entry
	 * @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws NoSuchCPSubscriptionCycleEntryException {
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (cpSubscriptionCycleEntry != null) {
			return cpSubscriptionCycleEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPSubscriptionCycleEntryException(msg.toString());
	}

	/**
	 * Returns the first cp subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		List<CPSubscriptionCycleEntry> list = findByUuid_C(uuid, companyId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp subscription cycle entry
	 * @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws NoSuchCPSubscriptionCycleEntryException {
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (cpSubscriptionCycleEntry != null) {
			return cpSubscriptionCycleEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPSubscriptionCycleEntryException(msg.toString());
	}

	/**
	 * Returns the last cp subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CPSubscriptionCycleEntry> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp subscription cycle entries before and after the current cp subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param CPSubscriptionCycleEntryId the primary key of the current cp subscription cycle entry
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp subscription cycle entry
	 * @throws NoSuchCPSubscriptionCycleEntryException if a cp subscription cycle entry with the primary key could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry[] findByUuid_C_PrevAndNext(
		long CPSubscriptionCycleEntryId, String uuid, long companyId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws NoSuchCPSubscriptionCycleEntryException {
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry = findByPrimaryKey(CPSubscriptionCycleEntryId);

		Session session = null;

		try {
			session = openSession();

			CPSubscriptionCycleEntry[] array = new CPSubscriptionCycleEntryImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session,
					cpSubscriptionCycleEntry, uuid, companyId,
					orderByComparator, true);

			array[1] = cpSubscriptionCycleEntry;

			array[2] = getByUuid_C_PrevAndNext(session,
					cpSubscriptionCycleEntry, uuid, companyId,
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

	protected CPSubscriptionCycleEntry getByUuid_C_PrevAndNext(
		Session session, CPSubscriptionCycleEntry cpSubscriptionCycleEntry,
		String uuid, long companyId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_CPSUBSCRIPTIONCYCLEENTRY_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals("")) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

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
			query.append(CPSubscriptionCycleEntryModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(cpSubscriptionCycleEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CPSubscriptionCycleEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp subscription cycle entries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CPSubscriptionCycleEntry cpSubscriptionCycleEntry : findByUuid_C(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(cpSubscriptionCycleEntry);
		}
	}

	/**
	 * Returns the number of cp subscription cycle entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching cp subscription cycle entries
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPSUBSCRIPTIONCYCLEENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals("")) {
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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "cpSubscriptionCycleEntry.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "cpSubscriptionCycleEntry.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(cpSubscriptionCycleEntry.uuid IS NULL OR cpSubscriptionCycleEntry.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "cpSubscriptionCycleEntry.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionCycleEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionCycleEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			CPSubscriptionCycleEntryModelImpl.GROUPID_COLUMN_BITMASK |
			CPSubscriptionCycleEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the cp subscription cycle entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching cp subscription cycle entries
	 */
	@Override
	public List<CPSubscriptionCycleEntry> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp subscription cycle entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cp subscription cycle entries
	 * @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	 * @return the range of matching cp subscription cycle entries
	 */
	@Override
	public List<CPSubscriptionCycleEntry> findByGroupId(long groupId,
		int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp subscription cycle entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cp subscription cycle entries
	 * @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp subscription cycle entries
	 */
	@Override
	public List<CPSubscriptionCycleEntry> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp subscription cycle entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cp subscription cycle entries
	 * @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp subscription cycle entries
	 */
	@Override
	public List<CPSubscriptionCycleEntry> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<CPSubscriptionCycleEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CPSubscriptionCycleEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPSubscriptionCycleEntry cpSubscriptionCycleEntry : list) {
					if ((groupId != cpSubscriptionCycleEntry.getGroupId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CPSUBSCRIPTIONCYCLEENTRY_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CPSubscriptionCycleEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<CPSubscriptionCycleEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPSubscriptionCycleEntry>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first cp subscription cycle entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp subscription cycle entry
	 * @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry findByGroupId_First(long groupId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws NoSuchCPSubscriptionCycleEntryException {
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry = fetchByGroupId_First(groupId,
				orderByComparator);

		if (cpSubscriptionCycleEntry != null) {
			return cpSubscriptionCycleEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchCPSubscriptionCycleEntryException(msg.toString());
	}

	/**
	 * Returns the first cp subscription cycle entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry fetchByGroupId_First(long groupId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		List<CPSubscriptionCycleEntry> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp subscription cycle entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp subscription cycle entry
	 * @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry findByGroupId_Last(long groupId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws NoSuchCPSubscriptionCycleEntryException {
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (cpSubscriptionCycleEntry != null) {
			return cpSubscriptionCycleEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchCPSubscriptionCycleEntryException(msg.toString());
	}

	/**
	 * Returns the last cp subscription cycle entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry fetchByGroupId_Last(long groupId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<CPSubscriptionCycleEntry> list = findByGroupId(groupId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp subscription cycle entries before and after the current cp subscription cycle entry in the ordered set where groupId = &#63;.
	 *
	 * @param CPSubscriptionCycleEntryId the primary key of the current cp subscription cycle entry
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp subscription cycle entry
	 * @throws NoSuchCPSubscriptionCycleEntryException if a cp subscription cycle entry with the primary key could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry[] findByGroupId_PrevAndNext(
		long CPSubscriptionCycleEntryId, long groupId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws NoSuchCPSubscriptionCycleEntryException {
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry = findByPrimaryKey(CPSubscriptionCycleEntryId);

		Session session = null;

		try {
			session = openSession();

			CPSubscriptionCycleEntry[] array = new CPSubscriptionCycleEntryImpl[3];

			array[0] = getByGroupId_PrevAndNext(session,
					cpSubscriptionCycleEntry, groupId, orderByComparator, true);

			array[1] = cpSubscriptionCycleEntry;

			array[2] = getByGroupId_PrevAndNext(session,
					cpSubscriptionCycleEntry, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPSubscriptionCycleEntry getByGroupId_PrevAndNext(
		Session session, CPSubscriptionCycleEntry cpSubscriptionCycleEntry,
		long groupId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CPSUBSCRIPTIONCYCLEENTRY_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

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
			query.append(CPSubscriptionCycleEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cpSubscriptionCycleEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CPSubscriptionCycleEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp subscription cycle entries where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (CPSubscriptionCycleEntry cpSubscriptionCycleEntry : findByGroupId(
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(cpSubscriptionCycleEntry);
		}
	}

	/**
	 * Returns the number of cp subscription cycle entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching cp subscription cycle entries
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPSUBSCRIPTIONCYCLEENTRY_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "cpSubscriptionCycleEntry.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CPSUBSCRIPTIONENTRYID =
		new FinderPath(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionCycleEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCPSubscriptionEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPSUBSCRIPTIONENTRYID =
		new FinderPath(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionCycleEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCPSubscriptionEntryId",
			new String[] { Long.class.getName() },
			CPSubscriptionCycleEntryModelImpl.CPSUBSCRIPTIONENTRYID_COLUMN_BITMASK |
			CPSubscriptionCycleEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CPSUBSCRIPTIONENTRYID = new FinderPath(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCPSubscriptionEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the cp subscription cycle entries where CPSubscriptionEntryId = &#63;.
	 *
	 * @param CPSubscriptionEntryId the cp subscription entry ID
	 * @return the matching cp subscription cycle entries
	 */
	@Override
	public List<CPSubscriptionCycleEntry> findByCPSubscriptionEntryId(
		long CPSubscriptionEntryId) {
		return findByCPSubscriptionEntryId(CPSubscriptionEntryId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp subscription cycle entries where CPSubscriptionEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPSubscriptionEntryId the cp subscription entry ID
	 * @param start the lower bound of the range of cp subscription cycle entries
	 * @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	 * @return the range of matching cp subscription cycle entries
	 */
	@Override
	public List<CPSubscriptionCycleEntry> findByCPSubscriptionEntryId(
		long CPSubscriptionEntryId, int start, int end) {
		return findByCPSubscriptionEntryId(CPSubscriptionEntryId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the cp subscription cycle entries where CPSubscriptionEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPSubscriptionEntryId the cp subscription entry ID
	 * @param start the lower bound of the range of cp subscription cycle entries
	 * @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp subscription cycle entries
	 */
	@Override
	public List<CPSubscriptionCycleEntry> findByCPSubscriptionEntryId(
		long CPSubscriptionEntryId, int start, int end,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		return findByCPSubscriptionEntryId(CPSubscriptionEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp subscription cycle entries where CPSubscriptionEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPSubscriptionEntryId the cp subscription entry ID
	 * @param start the lower bound of the range of cp subscription cycle entries
	 * @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp subscription cycle entries
	 */
	@Override
	public List<CPSubscriptionCycleEntry> findByCPSubscriptionEntryId(
		long CPSubscriptionEntryId, int start, int end,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPSUBSCRIPTIONENTRYID;
			finderArgs = new Object[] { CPSubscriptionEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CPSUBSCRIPTIONENTRYID;
			finderArgs = new Object[] {
					CPSubscriptionEntryId,
					
					start, end, orderByComparator
				};
		}

		List<CPSubscriptionCycleEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CPSubscriptionCycleEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPSubscriptionCycleEntry cpSubscriptionCycleEntry : list) {
					if ((CPSubscriptionEntryId != cpSubscriptionCycleEntry.getCPSubscriptionEntryId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CPSUBSCRIPTIONCYCLEENTRY_WHERE);

			query.append(_FINDER_COLUMN_CPSUBSCRIPTIONENTRYID_CPSUBSCRIPTIONENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CPSubscriptionCycleEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPSubscriptionEntryId);

				if (!pagination) {
					list = (List<CPSubscriptionCycleEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPSubscriptionCycleEntry>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first cp subscription cycle entry in the ordered set where CPSubscriptionEntryId = &#63;.
	 *
	 * @param CPSubscriptionEntryId the cp subscription entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp subscription cycle entry
	 * @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry findByCPSubscriptionEntryId_First(
		long CPSubscriptionEntryId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws NoSuchCPSubscriptionCycleEntryException {
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry = fetchByCPSubscriptionEntryId_First(CPSubscriptionEntryId,
				orderByComparator);

		if (cpSubscriptionCycleEntry != null) {
			return cpSubscriptionCycleEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPSubscriptionEntryId=");
		msg.append(CPSubscriptionEntryId);

		msg.append("}");

		throw new NoSuchCPSubscriptionCycleEntryException(msg.toString());
	}

	/**
	 * Returns the first cp subscription cycle entry in the ordered set where CPSubscriptionEntryId = &#63;.
	 *
	 * @param CPSubscriptionEntryId the cp subscription entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry fetchByCPSubscriptionEntryId_First(
		long CPSubscriptionEntryId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		List<CPSubscriptionCycleEntry> list = findByCPSubscriptionEntryId(CPSubscriptionEntryId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp subscription cycle entry in the ordered set where CPSubscriptionEntryId = &#63;.
	 *
	 * @param CPSubscriptionEntryId the cp subscription entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp subscription cycle entry
	 * @throws NoSuchCPSubscriptionCycleEntryException if a matching cp subscription cycle entry could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry findByCPSubscriptionEntryId_Last(
		long CPSubscriptionEntryId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws NoSuchCPSubscriptionCycleEntryException {
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry = fetchByCPSubscriptionEntryId_Last(CPSubscriptionEntryId,
				orderByComparator);

		if (cpSubscriptionCycleEntry != null) {
			return cpSubscriptionCycleEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPSubscriptionEntryId=");
		msg.append(CPSubscriptionEntryId);

		msg.append("}");

		throw new NoSuchCPSubscriptionCycleEntryException(msg.toString());
	}

	/**
	 * Returns the last cp subscription cycle entry in the ordered set where CPSubscriptionEntryId = &#63;.
	 *
	 * @param CPSubscriptionEntryId the cp subscription entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp subscription cycle entry, or <code>null</code> if a matching cp subscription cycle entry could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry fetchByCPSubscriptionEntryId_Last(
		long CPSubscriptionEntryId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		int count = countByCPSubscriptionEntryId(CPSubscriptionEntryId);

		if (count == 0) {
			return null;
		}

		List<CPSubscriptionCycleEntry> list = findByCPSubscriptionEntryId(CPSubscriptionEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp subscription cycle entries before and after the current cp subscription cycle entry in the ordered set where CPSubscriptionEntryId = &#63;.
	 *
	 * @param CPSubscriptionCycleEntryId the primary key of the current cp subscription cycle entry
	 * @param CPSubscriptionEntryId the cp subscription entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp subscription cycle entry
	 * @throws NoSuchCPSubscriptionCycleEntryException if a cp subscription cycle entry with the primary key could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry[] findByCPSubscriptionEntryId_PrevAndNext(
		long CPSubscriptionCycleEntryId, long CPSubscriptionEntryId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator)
		throws NoSuchCPSubscriptionCycleEntryException {
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry = findByPrimaryKey(CPSubscriptionCycleEntryId);

		Session session = null;

		try {
			session = openSession();

			CPSubscriptionCycleEntry[] array = new CPSubscriptionCycleEntryImpl[3];

			array[0] = getByCPSubscriptionEntryId_PrevAndNext(session,
					cpSubscriptionCycleEntry, CPSubscriptionEntryId,
					orderByComparator, true);

			array[1] = cpSubscriptionCycleEntry;

			array[2] = getByCPSubscriptionEntryId_PrevAndNext(session,
					cpSubscriptionCycleEntry, CPSubscriptionEntryId,
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

	protected CPSubscriptionCycleEntry getByCPSubscriptionEntryId_PrevAndNext(
		Session session, CPSubscriptionCycleEntry cpSubscriptionCycleEntry,
		long CPSubscriptionEntryId,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CPSUBSCRIPTIONCYCLEENTRY_WHERE);

		query.append(_FINDER_COLUMN_CPSUBSCRIPTIONENTRYID_CPSUBSCRIPTIONENTRYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

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
			query.append(CPSubscriptionCycleEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(CPSubscriptionEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cpSubscriptionCycleEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CPSubscriptionCycleEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp subscription cycle entries where CPSubscriptionEntryId = &#63; from the database.
	 *
	 * @param CPSubscriptionEntryId the cp subscription entry ID
	 */
	@Override
	public void removeByCPSubscriptionEntryId(long CPSubscriptionEntryId) {
		for (CPSubscriptionCycleEntry cpSubscriptionCycleEntry : findByCPSubscriptionEntryId(
				CPSubscriptionEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(cpSubscriptionCycleEntry);
		}
	}

	/**
	 * Returns the number of cp subscription cycle entries where CPSubscriptionEntryId = &#63;.
	 *
	 * @param CPSubscriptionEntryId the cp subscription entry ID
	 * @return the number of matching cp subscription cycle entries
	 */
	@Override
	public int countByCPSubscriptionEntryId(long CPSubscriptionEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CPSUBSCRIPTIONENTRYID;

		Object[] finderArgs = new Object[] { CPSubscriptionEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPSUBSCRIPTIONCYCLEENTRY_WHERE);

			query.append(_FINDER_COLUMN_CPSUBSCRIPTIONENTRYID_CPSUBSCRIPTIONENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPSubscriptionEntryId);

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

	private static final String _FINDER_COLUMN_CPSUBSCRIPTIONENTRYID_CPSUBSCRIPTIONENTRYID_2 =
		"cpSubscriptionCycleEntry.CPSubscriptionEntryId = ?";

	public CPSubscriptionCycleEntryPersistenceImpl() {
		setModelClass(CPSubscriptionCycleEntry.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the cp subscription cycle entry in the entity cache if it is enabled.
	 *
	 * @param cpSubscriptionCycleEntry the cp subscription cycle entry
	 */
	@Override
	public void cacheResult(CPSubscriptionCycleEntry cpSubscriptionCycleEntry) {
		entityCache.putResult(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionCycleEntryImpl.class,
			cpSubscriptionCycleEntry.getPrimaryKey(), cpSubscriptionCycleEntry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				cpSubscriptionCycleEntry.getUuid(),
				cpSubscriptionCycleEntry.getGroupId()
			}, cpSubscriptionCycleEntry);

		cpSubscriptionCycleEntry.resetOriginalValues();
	}

	/**
	 * Caches the cp subscription cycle entries in the entity cache if it is enabled.
	 *
	 * @param cpSubscriptionCycleEntries the cp subscription cycle entries
	 */
	@Override
	public void cacheResult(
		List<CPSubscriptionCycleEntry> cpSubscriptionCycleEntries) {
		for (CPSubscriptionCycleEntry cpSubscriptionCycleEntry : cpSubscriptionCycleEntries) {
			if (entityCache.getResult(
						CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
						CPSubscriptionCycleEntryImpl.class,
						cpSubscriptionCycleEntry.getPrimaryKey()) == null) {
				cacheResult(cpSubscriptionCycleEntry);
			}
			else {
				cpSubscriptionCycleEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cp subscription cycle entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CPSubscriptionCycleEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cp subscription cycle entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CPSubscriptionCycleEntry cpSubscriptionCycleEntry) {
		entityCache.removeResult(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionCycleEntryImpl.class,
			cpSubscriptionCycleEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CPSubscriptionCycleEntryModelImpl)cpSubscriptionCycleEntry,
			true);
	}

	@Override
	public void clearCache(
		List<CPSubscriptionCycleEntry> cpSubscriptionCycleEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CPSubscriptionCycleEntry cpSubscriptionCycleEntry : cpSubscriptionCycleEntries) {
			entityCache.removeResult(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
				CPSubscriptionCycleEntryImpl.class,
				cpSubscriptionCycleEntry.getPrimaryKey());

			clearUniqueFindersCache((CPSubscriptionCycleEntryModelImpl)cpSubscriptionCycleEntry,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		CPSubscriptionCycleEntryModelImpl cpSubscriptionCycleEntryModelImpl) {
		Object[] args = new Object[] {
				cpSubscriptionCycleEntryModelImpl.getUuid(),
				cpSubscriptionCycleEntryModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			cpSubscriptionCycleEntryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CPSubscriptionCycleEntryModelImpl cpSubscriptionCycleEntryModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					cpSubscriptionCycleEntryModelImpl.getUuid(),
					cpSubscriptionCycleEntryModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((cpSubscriptionCycleEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					cpSubscriptionCycleEntryModelImpl.getOriginalUuid(),
					cpSubscriptionCycleEntryModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new cp subscription cycle entry with the primary key. Does not add the cp subscription cycle entry to the database.
	 *
	 * @param CPSubscriptionCycleEntryId the primary key for the new cp subscription cycle entry
	 * @return the new cp subscription cycle entry
	 */
	@Override
	public CPSubscriptionCycleEntry create(long CPSubscriptionCycleEntryId) {
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry = new CPSubscriptionCycleEntryImpl();

		cpSubscriptionCycleEntry.setNew(true);
		cpSubscriptionCycleEntry.setPrimaryKey(CPSubscriptionCycleEntryId);

		String uuid = PortalUUIDUtil.generate();

		cpSubscriptionCycleEntry.setUuid(uuid);

		cpSubscriptionCycleEntry.setCompanyId(companyProvider.getCompanyId());

		return cpSubscriptionCycleEntry;
	}

	/**
	 * Removes the cp subscription cycle entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPSubscriptionCycleEntryId the primary key of the cp subscription cycle entry
	 * @return the cp subscription cycle entry that was removed
	 * @throws NoSuchCPSubscriptionCycleEntryException if a cp subscription cycle entry with the primary key could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry remove(long CPSubscriptionCycleEntryId)
		throws NoSuchCPSubscriptionCycleEntryException {
		return remove((Serializable)CPSubscriptionCycleEntryId);
	}

	/**
	 * Removes the cp subscription cycle entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cp subscription cycle entry
	 * @return the cp subscription cycle entry that was removed
	 * @throws NoSuchCPSubscriptionCycleEntryException if a cp subscription cycle entry with the primary key could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry remove(Serializable primaryKey)
		throws NoSuchCPSubscriptionCycleEntryException {
		Session session = null;

		try {
			session = openSession();

			CPSubscriptionCycleEntry cpSubscriptionCycleEntry = (CPSubscriptionCycleEntry)session.get(CPSubscriptionCycleEntryImpl.class,
					primaryKey);

			if (cpSubscriptionCycleEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCPSubscriptionCycleEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(cpSubscriptionCycleEntry);
		}
		catch (NoSuchCPSubscriptionCycleEntryException nsee) {
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
	protected CPSubscriptionCycleEntry removeImpl(
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cpSubscriptionCycleEntry)) {
				cpSubscriptionCycleEntry = (CPSubscriptionCycleEntry)session.get(CPSubscriptionCycleEntryImpl.class,
						cpSubscriptionCycleEntry.getPrimaryKeyObj());
			}

			if (cpSubscriptionCycleEntry != null) {
				session.delete(cpSubscriptionCycleEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cpSubscriptionCycleEntry != null) {
			clearCache(cpSubscriptionCycleEntry);
		}

		return cpSubscriptionCycleEntry;
	}

	@Override
	public CPSubscriptionCycleEntry updateImpl(
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry) {
		boolean isNew = cpSubscriptionCycleEntry.isNew();

		if (!(cpSubscriptionCycleEntry instanceof CPSubscriptionCycleEntryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(cpSubscriptionCycleEntry.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(cpSubscriptionCycleEntry);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in cpSubscriptionCycleEntry proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CPSubscriptionCycleEntry implementation " +
				cpSubscriptionCycleEntry.getClass());
		}

		CPSubscriptionCycleEntryModelImpl cpSubscriptionCycleEntryModelImpl = (CPSubscriptionCycleEntryModelImpl)cpSubscriptionCycleEntry;

		if (Validator.isNull(cpSubscriptionCycleEntry.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			cpSubscriptionCycleEntry.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (cpSubscriptionCycleEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				cpSubscriptionCycleEntry.setCreateDate(now);
			}
			else {
				cpSubscriptionCycleEntry.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!cpSubscriptionCycleEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				cpSubscriptionCycleEntry.setModifiedDate(now);
			}
			else {
				cpSubscriptionCycleEntry.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (cpSubscriptionCycleEntry.isNew()) {
				session.save(cpSubscriptionCycleEntry);

				cpSubscriptionCycleEntry.setNew(false);
			}
			else {
				cpSubscriptionCycleEntry = (CPSubscriptionCycleEntry)session.merge(cpSubscriptionCycleEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CPSubscriptionCycleEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					cpSubscriptionCycleEntryModelImpl.getUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					cpSubscriptionCycleEntryModelImpl.getUuid(),
					cpSubscriptionCycleEntryModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { cpSubscriptionCycleEntryModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] {
					cpSubscriptionCycleEntryModelImpl.getCPSubscriptionEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CPSUBSCRIPTIONENTRYID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPSUBSCRIPTIONENTRYID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((cpSubscriptionCycleEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cpSubscriptionCycleEntryModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { cpSubscriptionCycleEntryModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((cpSubscriptionCycleEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cpSubscriptionCycleEntryModelImpl.getOriginalUuid(),
						cpSubscriptionCycleEntryModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						cpSubscriptionCycleEntryModelImpl.getUuid(),
						cpSubscriptionCycleEntryModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((cpSubscriptionCycleEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cpSubscriptionCycleEntryModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] {
						cpSubscriptionCycleEntryModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((cpSubscriptionCycleEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPSUBSCRIPTIONENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cpSubscriptionCycleEntryModelImpl.getOriginalCPSubscriptionEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CPSUBSCRIPTIONENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPSUBSCRIPTIONENTRYID,
					args);

				args = new Object[] {
						cpSubscriptionCycleEntryModelImpl.getCPSubscriptionEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CPSUBSCRIPTIONENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPSUBSCRIPTIONENTRYID,
					args);
			}
		}

		entityCache.putResult(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionCycleEntryImpl.class,
			cpSubscriptionCycleEntry.getPrimaryKey(), cpSubscriptionCycleEntry,
			false);

		clearUniqueFindersCache(cpSubscriptionCycleEntryModelImpl, false);
		cacheUniqueFindersCache(cpSubscriptionCycleEntryModelImpl);

		cpSubscriptionCycleEntry.resetOriginalValues();

		return cpSubscriptionCycleEntry;
	}

	/**
	 * Returns the cp subscription cycle entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp subscription cycle entry
	 * @return the cp subscription cycle entry
	 * @throws NoSuchCPSubscriptionCycleEntryException if a cp subscription cycle entry with the primary key could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCPSubscriptionCycleEntryException {
		CPSubscriptionCycleEntry cpSubscriptionCycleEntry = fetchByPrimaryKey(primaryKey);

		if (cpSubscriptionCycleEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCPSubscriptionCycleEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return cpSubscriptionCycleEntry;
	}

	/**
	 * Returns the cp subscription cycle entry with the primary key or throws a {@link NoSuchCPSubscriptionCycleEntryException} if it could not be found.
	 *
	 * @param CPSubscriptionCycleEntryId the primary key of the cp subscription cycle entry
	 * @return the cp subscription cycle entry
	 * @throws NoSuchCPSubscriptionCycleEntryException if a cp subscription cycle entry with the primary key could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry findByPrimaryKey(
		long CPSubscriptionCycleEntryId)
		throws NoSuchCPSubscriptionCycleEntryException {
		return findByPrimaryKey((Serializable)CPSubscriptionCycleEntryId);
	}

	/**
	 * Returns the cp subscription cycle entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp subscription cycle entry
	 * @return the cp subscription cycle entry, or <code>null</code> if a cp subscription cycle entry with the primary key could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
				CPSubscriptionCycleEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CPSubscriptionCycleEntry cpSubscriptionCycleEntry = (CPSubscriptionCycleEntry)serializable;

		if (cpSubscriptionCycleEntry == null) {
			Session session = null;

			try {
				session = openSession();

				cpSubscriptionCycleEntry = (CPSubscriptionCycleEntry)session.get(CPSubscriptionCycleEntryImpl.class,
						primaryKey);

				if (cpSubscriptionCycleEntry != null) {
					cacheResult(cpSubscriptionCycleEntry);
				}
				else {
					entityCache.putResult(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
						CPSubscriptionCycleEntryImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
					CPSubscriptionCycleEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cpSubscriptionCycleEntry;
	}

	/**
	 * Returns the cp subscription cycle entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param CPSubscriptionCycleEntryId the primary key of the cp subscription cycle entry
	 * @return the cp subscription cycle entry, or <code>null</code> if a cp subscription cycle entry with the primary key could not be found
	 */
	@Override
	public CPSubscriptionCycleEntry fetchByPrimaryKey(
		long CPSubscriptionCycleEntryId) {
		return fetchByPrimaryKey((Serializable)CPSubscriptionCycleEntryId);
	}

	@Override
	public Map<Serializable, CPSubscriptionCycleEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CPSubscriptionCycleEntry> map = new HashMap<Serializable, CPSubscriptionCycleEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CPSubscriptionCycleEntry cpSubscriptionCycleEntry = fetchByPrimaryKey(primaryKey);

			if (cpSubscriptionCycleEntry != null) {
				map.put(primaryKey, cpSubscriptionCycleEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
					CPSubscriptionCycleEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CPSubscriptionCycleEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CPSUBSCRIPTIONCYCLEENTRY_WHERE_PKS_IN);

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

			for (CPSubscriptionCycleEntry cpSubscriptionCycleEntry : (List<CPSubscriptionCycleEntry>)q.list()) {
				map.put(cpSubscriptionCycleEntry.getPrimaryKeyObj(),
					cpSubscriptionCycleEntry);

				cacheResult(cpSubscriptionCycleEntry);

				uncachedPrimaryKeys.remove(cpSubscriptionCycleEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CPSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
					CPSubscriptionCycleEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the cp subscription cycle entries.
	 *
	 * @return the cp subscription cycle entries
	 */
	@Override
	public List<CPSubscriptionCycleEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp subscription cycle entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp subscription cycle entries
	 * @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	 * @return the range of cp subscription cycle entries
	 */
	@Override
	public List<CPSubscriptionCycleEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp subscription cycle entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp subscription cycle entries
	 * @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cp subscription cycle entries
	 */
	@Override
	public List<CPSubscriptionCycleEntry> findAll(int start, int end,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp subscription cycle entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp subscription cycle entries
	 * @param end the upper bound of the range of cp subscription cycle entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cp subscription cycle entries
	 */
	@Override
	public List<CPSubscriptionCycleEntry> findAll(int start, int end,
		OrderByComparator<CPSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<CPSubscriptionCycleEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CPSubscriptionCycleEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CPSUBSCRIPTIONCYCLEENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CPSUBSCRIPTIONCYCLEENTRY;

				if (pagination) {
					sql = sql.concat(CPSubscriptionCycleEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CPSubscriptionCycleEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPSubscriptionCycleEntry>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the cp subscription cycle entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CPSubscriptionCycleEntry cpSubscriptionCycleEntry : findAll()) {
			remove(cpSubscriptionCycleEntry);
		}
	}

	/**
	 * Returns the number of cp subscription cycle entries.
	 *
	 * @return the number of cp subscription cycle entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CPSUBSCRIPTIONCYCLEENTRY);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

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
		return CPSubscriptionCycleEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cp subscription cycle entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CPSubscriptionCycleEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CPSUBSCRIPTIONCYCLEENTRY = "SELECT cpSubscriptionCycleEntry FROM CPSubscriptionCycleEntry cpSubscriptionCycleEntry";
	private static final String _SQL_SELECT_CPSUBSCRIPTIONCYCLEENTRY_WHERE_PKS_IN =
		"SELECT cpSubscriptionCycleEntry FROM CPSubscriptionCycleEntry cpSubscriptionCycleEntry WHERE CPSubscriptionCycleEntryId IN (";
	private static final String _SQL_SELECT_CPSUBSCRIPTIONCYCLEENTRY_WHERE = "SELECT cpSubscriptionCycleEntry FROM CPSubscriptionCycleEntry cpSubscriptionCycleEntry WHERE ";
	private static final String _SQL_COUNT_CPSUBSCRIPTIONCYCLEENTRY = "SELECT COUNT(cpSubscriptionCycleEntry) FROM CPSubscriptionCycleEntry cpSubscriptionCycleEntry";
	private static final String _SQL_COUNT_CPSUBSCRIPTIONCYCLEENTRY_WHERE = "SELECT COUNT(cpSubscriptionCycleEntry) FROM CPSubscriptionCycleEntry cpSubscriptionCycleEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "cpSubscriptionCycleEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CPSubscriptionCycleEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CPSubscriptionCycleEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CPSubscriptionCycleEntryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}