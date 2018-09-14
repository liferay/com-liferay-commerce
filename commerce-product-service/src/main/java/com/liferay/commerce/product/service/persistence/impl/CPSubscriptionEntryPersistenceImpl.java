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

import com.liferay.commerce.product.exception.NoSuchCPSubscriptionEntryException;
import com.liferay.commerce.product.model.CPSubscriptionEntry;
import com.liferay.commerce.product.model.impl.CPSubscriptionEntryImpl;
import com.liferay.commerce.product.model.impl.CPSubscriptionEntryModelImpl;
import com.liferay.commerce.product.service.persistence.CPSubscriptionEntryPersistence;

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
 * The persistence implementation for the cp subscription entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CPSubscriptionEntryPersistence
 * @see com.liferay.commerce.product.service.persistence.CPSubscriptionEntryUtil
 * @generated
 */
@ProviderType
public class CPSubscriptionEntryPersistenceImpl extends BasePersistenceImpl<CPSubscriptionEntry>
	implements CPSubscriptionEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CPSubscriptionEntryUtil} to access the cp subscription entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CPSubscriptionEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			CPSubscriptionEntryModelImpl.UUID_COLUMN_BITMASK |
			CPSubscriptionEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the cp subscription entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp subscription entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp subscription entries
	 * @param end the upper bound of the range of cp subscription entries (not inclusive)
	 * @return the range of matching cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp subscription entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp subscription entries
	 * @param end the upper bound of the range of cp subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findByUuid(String uuid, int start,
		int end, OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp subscription entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp subscription entries
	 * @param end the upper bound of the range of cp subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findByUuid(String uuid, int start,
		int end, OrderByComparator<CPSubscriptionEntry> orderByComparator,
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

		List<CPSubscriptionEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CPSubscriptionEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPSubscriptionEntry cpSubscriptionEntry : list) {
					if (!Objects.equals(uuid, cpSubscriptionEntry.getUuid())) {
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

			query.append(_SQL_SELECT_CPSUBSCRIPTIONENTRY_WHERE);

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
				query.append(CPSubscriptionEntryModelImpl.ORDER_BY_JPQL);
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
					list = (List<CPSubscriptionEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPSubscriptionEntry>)QueryUtil.list(q,
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
	 * Returns the first cp subscription entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp subscription entry
	 * @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry findByUuid_First(String uuid,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException {
		CPSubscriptionEntry cpSubscriptionEntry = fetchByUuid_First(uuid,
				orderByComparator);

		if (cpSubscriptionEntry != null) {
			return cpSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCPSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the first cp subscription entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry fetchByUuid_First(String uuid,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		List<CPSubscriptionEntry> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp subscription entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp subscription entry
	 * @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry findByUuid_Last(String uuid,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException {
		CPSubscriptionEntry cpSubscriptionEntry = fetchByUuid_Last(uuid,
				orderByComparator);

		if (cpSubscriptionEntry != null) {
			return cpSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCPSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the last cp subscription entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry fetchByUuid_Last(String uuid,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CPSubscriptionEntry> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp subscription entries before and after the current cp subscription entry in the ordered set where uuid = &#63;.
	 *
	 * @param CPSubscriptionEntryId the primary key of the current cp subscription entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp subscription entry
	 * @throws NoSuchCPSubscriptionEntryException if a cp subscription entry with the primary key could not be found
	 */
	@Override
	public CPSubscriptionEntry[] findByUuid_PrevAndNext(
		long CPSubscriptionEntryId, String uuid,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException {
		CPSubscriptionEntry cpSubscriptionEntry = findByPrimaryKey(CPSubscriptionEntryId);

		Session session = null;

		try {
			session = openSession();

			CPSubscriptionEntry[] array = new CPSubscriptionEntryImpl[3];

			array[0] = getByUuid_PrevAndNext(session, cpSubscriptionEntry,
					uuid, orderByComparator, true);

			array[1] = cpSubscriptionEntry;

			array[2] = getByUuid_PrevAndNext(session, cpSubscriptionEntry,
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

	protected CPSubscriptionEntry getByUuid_PrevAndNext(Session session,
		CPSubscriptionEntry cpSubscriptionEntry, String uuid,
		OrderByComparator<CPSubscriptionEntry> orderByComparator,
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

		query.append(_SQL_SELECT_CPSUBSCRIPTIONENTRY_WHERE);

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
			query.append(CPSubscriptionEntryModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(cpSubscriptionEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CPSubscriptionEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp subscription entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CPSubscriptionEntry cpSubscriptionEntry : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(cpSubscriptionEntry);
		}
	}

	/**
	 * Returns the number of cp subscription entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cp subscription entries
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPSUBSCRIPTIONENTRY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "cpSubscriptionEntry.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "cpSubscriptionEntry.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(cpSubscriptionEntry.uuid IS NULL OR cpSubscriptionEntry.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			CPSubscriptionEntryModelImpl.UUID_COLUMN_BITMASK |
			CPSubscriptionEntryModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the cp subscription entry where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCPSubscriptionEntryException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp subscription entry
	 * @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry findByUUID_G(String uuid, long groupId)
		throws NoSuchCPSubscriptionEntryException {
		CPSubscriptionEntry cpSubscriptionEntry = fetchByUUID_G(uuid, groupId);

		if (cpSubscriptionEntry == null) {
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

			throw new NoSuchCPSubscriptionEntryException(msg.toString());
		}

		return cpSubscriptionEntry;
	}

	/**
	 * Returns the cp subscription entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the cp subscription entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof CPSubscriptionEntry) {
			CPSubscriptionEntry cpSubscriptionEntry = (CPSubscriptionEntry)result;

			if (!Objects.equals(uuid, cpSubscriptionEntry.getUuid()) ||
					(groupId != cpSubscriptionEntry.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CPSUBSCRIPTIONENTRY_WHERE);

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

				List<CPSubscriptionEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					CPSubscriptionEntry cpSubscriptionEntry = list.get(0);

					result = cpSubscriptionEntry;

					cacheResult(cpSubscriptionEntry);
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
			return (CPSubscriptionEntry)result;
		}
	}

	/**
	 * Removes the cp subscription entry where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the cp subscription entry that was removed
	 */
	@Override
	public CPSubscriptionEntry removeByUUID_G(String uuid, long groupId)
		throws NoSuchCPSubscriptionEntryException {
		CPSubscriptionEntry cpSubscriptionEntry = findByUUID_G(uuid, groupId);

		return remove(cpSubscriptionEntry);
	}

	/**
	 * Returns the number of cp subscription entries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching cp subscription entries
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPSUBSCRIPTIONENTRY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "cpSubscriptionEntry.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "cpSubscriptionEntry.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(cpSubscriptionEntry.uuid IS NULL OR cpSubscriptionEntry.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "cpSubscriptionEntry.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			CPSubscriptionEntryModelImpl.UUID_COLUMN_BITMASK |
			CPSubscriptionEntryModelImpl.COMPANYID_COLUMN_BITMASK |
			CPSubscriptionEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the cp subscription entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp subscription entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp subscription entries
	 * @param end the upper bound of the range of cp subscription entries (not inclusive)
	 * @return the range of matching cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp subscription entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp subscription entries
	 * @param end the upper bound of the range of cp subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp subscription entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp subscription entries
	 * @param end the upper bound of the range of cp subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<CPSubscriptionEntry> orderByComparator,
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

		List<CPSubscriptionEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CPSubscriptionEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPSubscriptionEntry cpSubscriptionEntry : list) {
					if (!Objects.equals(uuid, cpSubscriptionEntry.getUuid()) ||
							(companyId != cpSubscriptionEntry.getCompanyId())) {
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

			query.append(_SQL_SELECT_CPSUBSCRIPTIONENTRY_WHERE);

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
				query.append(CPSubscriptionEntryModelImpl.ORDER_BY_JPQL);
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
					list = (List<CPSubscriptionEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPSubscriptionEntry>)QueryUtil.list(q,
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
	 * Returns the first cp subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp subscription entry
	 * @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException {
		CPSubscriptionEntry cpSubscriptionEntry = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (cpSubscriptionEntry != null) {
			return cpSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the first cp subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		List<CPSubscriptionEntry> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp subscription entry
	 * @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException {
		CPSubscriptionEntry cpSubscriptionEntry = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (cpSubscriptionEntry != null) {
			return cpSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the last cp subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CPSubscriptionEntry> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp subscription entries before and after the current cp subscription entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param CPSubscriptionEntryId the primary key of the current cp subscription entry
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp subscription entry
	 * @throws NoSuchCPSubscriptionEntryException if a cp subscription entry with the primary key could not be found
	 */
	@Override
	public CPSubscriptionEntry[] findByUuid_C_PrevAndNext(
		long CPSubscriptionEntryId, String uuid, long companyId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException {
		CPSubscriptionEntry cpSubscriptionEntry = findByPrimaryKey(CPSubscriptionEntryId);

		Session session = null;

		try {
			session = openSession();

			CPSubscriptionEntry[] array = new CPSubscriptionEntryImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, cpSubscriptionEntry,
					uuid, companyId, orderByComparator, true);

			array[1] = cpSubscriptionEntry;

			array[2] = getByUuid_C_PrevAndNext(session, cpSubscriptionEntry,
					uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPSubscriptionEntry getByUuid_C_PrevAndNext(Session session,
		CPSubscriptionEntry cpSubscriptionEntry, String uuid, long companyId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator,
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

		query.append(_SQL_SELECT_CPSUBSCRIPTIONENTRY_WHERE);

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
			query.append(CPSubscriptionEntryModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(cpSubscriptionEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CPSubscriptionEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp subscription entries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CPSubscriptionEntry cpSubscriptionEntry : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(cpSubscriptionEntry);
		}
	}

	/**
	 * Returns the number of cp subscription entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching cp subscription entries
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPSUBSCRIPTIONENTRY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "cpSubscriptionEntry.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "cpSubscriptionEntry.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(cpSubscriptionEntry.uuid IS NULL OR cpSubscriptionEntry.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "cpSubscriptionEntry.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			CPSubscriptionEntryModelImpl.GROUPID_COLUMN_BITMASK |
			CPSubscriptionEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the cp subscription entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp subscription entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cp subscription entries
	 * @param end the upper bound of the range of cp subscription entries (not inclusive)
	 * @return the range of matching cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findByGroupId(long groupId, int start,
		int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp subscription entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cp subscription entries
	 * @param end the upper bound of the range of cp subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findByGroupId(long groupId, int start,
		int end, OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp subscription entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cp subscription entries
	 * @param end the upper bound of the range of cp subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findByGroupId(long groupId, int start,
		int end, OrderByComparator<CPSubscriptionEntry> orderByComparator,
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

		List<CPSubscriptionEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CPSubscriptionEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPSubscriptionEntry cpSubscriptionEntry : list) {
					if ((groupId != cpSubscriptionEntry.getGroupId())) {
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

			query.append(_SQL_SELECT_CPSUBSCRIPTIONENTRY_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CPSubscriptionEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<CPSubscriptionEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPSubscriptionEntry>)QueryUtil.list(q,
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
	 * Returns the first cp subscription entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp subscription entry
	 * @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry findByGroupId_First(long groupId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException {
		CPSubscriptionEntry cpSubscriptionEntry = fetchByGroupId_First(groupId,
				orderByComparator);

		if (cpSubscriptionEntry != null) {
			return cpSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchCPSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the first cp subscription entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry fetchByGroupId_First(long groupId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		List<CPSubscriptionEntry> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp subscription entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp subscription entry
	 * @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry findByGroupId_Last(long groupId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException {
		CPSubscriptionEntry cpSubscriptionEntry = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (cpSubscriptionEntry != null) {
			return cpSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchCPSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the last cp subscription entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry fetchByGroupId_Last(long groupId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<CPSubscriptionEntry> list = findByGroupId(groupId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp subscription entries before and after the current cp subscription entry in the ordered set where groupId = &#63;.
	 *
	 * @param CPSubscriptionEntryId the primary key of the current cp subscription entry
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp subscription entry
	 * @throws NoSuchCPSubscriptionEntryException if a cp subscription entry with the primary key could not be found
	 */
	@Override
	public CPSubscriptionEntry[] findByGroupId_PrevAndNext(
		long CPSubscriptionEntryId, long groupId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException {
		CPSubscriptionEntry cpSubscriptionEntry = findByPrimaryKey(CPSubscriptionEntryId);

		Session session = null;

		try {
			session = openSession();

			CPSubscriptionEntry[] array = new CPSubscriptionEntryImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, cpSubscriptionEntry,
					groupId, orderByComparator, true);

			array[1] = cpSubscriptionEntry;

			array[2] = getByGroupId_PrevAndNext(session, cpSubscriptionEntry,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPSubscriptionEntry getByGroupId_PrevAndNext(Session session,
		CPSubscriptionEntry cpSubscriptionEntry, long groupId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator,
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

		query.append(_SQL_SELECT_CPSUBSCRIPTIONENTRY_WHERE);

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
			query.append(CPSubscriptionEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cpSubscriptionEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CPSubscriptionEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp subscription entries where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (CPSubscriptionEntry cpSubscriptionEntry : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(cpSubscriptionEntry);
		}
	}

	/**
	 * Returns the number of cp subscription entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching cp subscription entries
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPSUBSCRIPTIONENTRY_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "cpSubscriptionEntry.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CPINSTANCEID =
		new FinderPath(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCPInstanceId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPINSTANCEID =
		new FinderPath(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCPInstanceId",
			new String[] { Long.class.getName() },
			CPSubscriptionEntryModelImpl.CPINSTANCEID_COLUMN_BITMASK |
			CPSubscriptionEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CPINSTANCEID = new FinderPath(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCPInstanceId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the cp subscription entries where CPInstanceId = &#63;.
	 *
	 * @param CPInstanceId the cp instance ID
	 * @return the matching cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findByCPInstanceId(long CPInstanceId) {
		return findByCPInstanceId(CPInstanceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp subscription entries where CPInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPInstanceId the cp instance ID
	 * @param start the lower bound of the range of cp subscription entries
	 * @param end the upper bound of the range of cp subscription entries (not inclusive)
	 * @return the range of matching cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findByCPInstanceId(long CPInstanceId,
		int start, int end) {
		return findByCPInstanceId(CPInstanceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp subscription entries where CPInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPInstanceId the cp instance ID
	 * @param start the lower bound of the range of cp subscription entries
	 * @param end the upper bound of the range of cp subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findByCPInstanceId(long CPInstanceId,
		int start, int end,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		return findByCPInstanceId(CPInstanceId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the cp subscription entries where CPInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPInstanceId the cp instance ID
	 * @param start the lower bound of the range of cp subscription entries
	 * @param end the upper bound of the range of cp subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findByCPInstanceId(long CPInstanceId,
		int start, int end,
		OrderByComparator<CPSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPINSTANCEID;
			finderArgs = new Object[] { CPInstanceId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CPINSTANCEID;
			finderArgs = new Object[] {
					CPInstanceId,
					
					start, end, orderByComparator
				};
		}

		List<CPSubscriptionEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CPSubscriptionEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPSubscriptionEntry cpSubscriptionEntry : list) {
					if ((CPInstanceId != cpSubscriptionEntry.getCPInstanceId())) {
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

			query.append(_SQL_SELECT_CPSUBSCRIPTIONENTRY_WHERE);

			query.append(_FINDER_COLUMN_CPINSTANCEID_CPINSTANCEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CPSubscriptionEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPInstanceId);

				if (!pagination) {
					list = (List<CPSubscriptionEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPSubscriptionEntry>)QueryUtil.list(q,
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
	 * Returns the first cp subscription entry in the ordered set where CPInstanceId = &#63;.
	 *
	 * @param CPInstanceId the cp instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp subscription entry
	 * @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry findByCPInstanceId_First(long CPInstanceId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException {
		CPSubscriptionEntry cpSubscriptionEntry = fetchByCPInstanceId_First(CPInstanceId,
				orderByComparator);

		if (cpSubscriptionEntry != null) {
			return cpSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPInstanceId=");
		msg.append(CPInstanceId);

		msg.append("}");

		throw new NoSuchCPSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the first cp subscription entry in the ordered set where CPInstanceId = &#63;.
	 *
	 * @param CPInstanceId the cp instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry fetchByCPInstanceId_First(long CPInstanceId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		List<CPSubscriptionEntry> list = findByCPInstanceId(CPInstanceId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp subscription entry in the ordered set where CPInstanceId = &#63;.
	 *
	 * @param CPInstanceId the cp instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp subscription entry
	 * @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry findByCPInstanceId_Last(long CPInstanceId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException {
		CPSubscriptionEntry cpSubscriptionEntry = fetchByCPInstanceId_Last(CPInstanceId,
				orderByComparator);

		if (cpSubscriptionEntry != null) {
			return cpSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPInstanceId=");
		msg.append(CPInstanceId);

		msg.append("}");

		throw new NoSuchCPSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the last cp subscription entry in the ordered set where CPInstanceId = &#63;.
	 *
	 * @param CPInstanceId the cp instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry fetchByCPInstanceId_Last(long CPInstanceId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		int count = countByCPInstanceId(CPInstanceId);

		if (count == 0) {
			return null;
		}

		List<CPSubscriptionEntry> list = findByCPInstanceId(CPInstanceId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp subscription entries before and after the current cp subscription entry in the ordered set where CPInstanceId = &#63;.
	 *
	 * @param CPSubscriptionEntryId the primary key of the current cp subscription entry
	 * @param CPInstanceId the cp instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp subscription entry
	 * @throws NoSuchCPSubscriptionEntryException if a cp subscription entry with the primary key could not be found
	 */
	@Override
	public CPSubscriptionEntry[] findByCPInstanceId_PrevAndNext(
		long CPSubscriptionEntryId, long CPInstanceId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException {
		CPSubscriptionEntry cpSubscriptionEntry = findByPrimaryKey(CPSubscriptionEntryId);

		Session session = null;

		try {
			session = openSession();

			CPSubscriptionEntry[] array = new CPSubscriptionEntryImpl[3];

			array[0] = getByCPInstanceId_PrevAndNext(session,
					cpSubscriptionEntry, CPInstanceId, orderByComparator, true);

			array[1] = cpSubscriptionEntry;

			array[2] = getByCPInstanceId_PrevAndNext(session,
					cpSubscriptionEntry, CPInstanceId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPSubscriptionEntry getByCPInstanceId_PrevAndNext(
		Session session, CPSubscriptionEntry cpSubscriptionEntry,
		long CPInstanceId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator,
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

		query.append(_SQL_SELECT_CPSUBSCRIPTIONENTRY_WHERE);

		query.append(_FINDER_COLUMN_CPINSTANCEID_CPINSTANCEID_2);

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
			query.append(CPSubscriptionEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(CPInstanceId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cpSubscriptionEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CPSubscriptionEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp subscription entries where CPInstanceId = &#63; from the database.
	 *
	 * @param CPInstanceId the cp instance ID
	 */
	@Override
	public void removeByCPInstanceId(long CPInstanceId) {
		for (CPSubscriptionEntry cpSubscriptionEntry : findByCPInstanceId(
				CPInstanceId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(cpSubscriptionEntry);
		}
	}

	/**
	 * Returns the number of cp subscription entries where CPInstanceId = &#63;.
	 *
	 * @param CPInstanceId the cp instance ID
	 * @return the number of matching cp subscription entries
	 */
	@Override
	public int countByCPInstanceId(long CPInstanceId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CPINSTANCEID;

		Object[] finderArgs = new Object[] { CPInstanceId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPSUBSCRIPTIONENTRY_WHERE);

			query.append(_FINDER_COLUMN_CPINSTANCEID_CPINSTANCEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPInstanceId);

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

	private static final String _FINDER_COLUMN_CPINSTANCEID_CPINSTANCEID_2 = "cpSubscriptionEntry.CPInstanceId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U = new FinderPath(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U = new FinderPath(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_U",
			new String[] { Long.class.getName(), Long.class.getName() },
			CPSubscriptionEntryModelImpl.GROUPID_COLUMN_BITMASK |
			CPSubscriptionEntryModelImpl.USERID_COLUMN_BITMASK |
			CPSubscriptionEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_U = new FinderPath(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_U",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the cp subscription entries where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findByG_U(long groupId, long userId) {
		return findByG_U(groupId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the cp subscription entries where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of cp subscription entries
	 * @param end the upper bound of the range of cp subscription entries (not inclusive)
	 * @return the range of matching cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findByG_U(long groupId, long userId,
		int start, int end) {
		return findByG_U(groupId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp subscription entries where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of cp subscription entries
	 * @param end the upper bound of the range of cp subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findByG_U(long groupId, long userId,
		int start, int end,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		return findByG_U(groupId, userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp subscription entries where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of cp subscription entries
	 * @param end the upper bound of the range of cp subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findByG_U(long groupId, long userId,
		int start, int end,
		OrderByComparator<CPSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U;
			finderArgs = new Object[] { groupId, userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U;
			finderArgs = new Object[] {
					groupId, userId,
					
					start, end, orderByComparator
				};
		}

		List<CPSubscriptionEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CPSubscriptionEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPSubscriptionEntry cpSubscriptionEntry : list) {
					if ((groupId != cpSubscriptionEntry.getGroupId()) ||
							(userId != cpSubscriptionEntry.getUserId())) {
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

			query.append(_SQL_SELECT_CPSUBSCRIPTIONENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CPSubscriptionEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				if (!pagination) {
					list = (List<CPSubscriptionEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPSubscriptionEntry>)QueryUtil.list(q,
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
	 * Returns the first cp subscription entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp subscription entry
	 * @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry findByG_U_First(long groupId, long userId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException {
		CPSubscriptionEntry cpSubscriptionEntry = fetchByG_U_First(groupId,
				userId, orderByComparator);

		if (cpSubscriptionEntry != null) {
			return cpSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchCPSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the first cp subscription entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry fetchByG_U_First(long groupId, long userId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		List<CPSubscriptionEntry> list = findByG_U(groupId, userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp subscription entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp subscription entry
	 * @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry findByG_U_Last(long groupId, long userId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException {
		CPSubscriptionEntry cpSubscriptionEntry = fetchByG_U_Last(groupId,
				userId, orderByComparator);

		if (cpSubscriptionEntry != null) {
			return cpSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchCPSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the last cp subscription entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry fetchByG_U_Last(long groupId, long userId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		int count = countByG_U(groupId, userId);

		if (count == 0) {
			return null;
		}

		List<CPSubscriptionEntry> list = findByG_U(groupId, userId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp subscription entries before and after the current cp subscription entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param CPSubscriptionEntryId the primary key of the current cp subscription entry
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp subscription entry
	 * @throws NoSuchCPSubscriptionEntryException if a cp subscription entry with the primary key could not be found
	 */
	@Override
	public CPSubscriptionEntry[] findByG_U_PrevAndNext(
		long CPSubscriptionEntryId, long groupId, long userId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException {
		CPSubscriptionEntry cpSubscriptionEntry = findByPrimaryKey(CPSubscriptionEntryId);

		Session session = null;

		try {
			session = openSession();

			CPSubscriptionEntry[] array = new CPSubscriptionEntryImpl[3];

			array[0] = getByG_U_PrevAndNext(session, cpSubscriptionEntry,
					groupId, userId, orderByComparator, true);

			array[1] = cpSubscriptionEntry;

			array[2] = getByG_U_PrevAndNext(session, cpSubscriptionEntry,
					groupId, userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPSubscriptionEntry getByG_U_PrevAndNext(Session session,
		CPSubscriptionEntry cpSubscriptionEntry, long groupId, long userId,
		OrderByComparator<CPSubscriptionEntry> orderByComparator,
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

		query.append(_SQL_SELECT_CPSUBSCRIPTIONENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_USERID_2);

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
			query.append(CPSubscriptionEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cpSubscriptionEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CPSubscriptionEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp subscription entries where groupId = &#63; and userId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 */
	@Override
	public void removeByG_U(long groupId, long userId) {
		for (CPSubscriptionEntry cpSubscriptionEntry : findByG_U(groupId,
				userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(cpSubscriptionEntry);
		}
	}

	/**
	 * Returns the number of cp subscription entries where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching cp subscription entries
	 */
	@Override
	public int countByG_U(long groupId, long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_U;

		Object[] finderArgs = new Object[] { groupId, userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPSUBSCRIPTIONENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_G_U_GROUPID_2 = "cpSubscriptionEntry.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_USERID_2 = "cpSubscriptionEntry.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVE = new FinderPath(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByactive",
			new String[] {
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE =
		new FinderPath(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED,
			CPSubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByactive",
			new String[] { Boolean.class.getName() },
			CPSubscriptionEntryModelImpl.ACTIVE_COLUMN_BITMASK |
			CPSubscriptionEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVE = new FinderPath(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByactive",
			new String[] { Boolean.class.getName() });

	/**
	 * Returns all the cp subscription entries where active = &#63;.
	 *
	 * @param active the active
	 * @return the matching cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findByactive(boolean active) {
		return findByactive(active, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp subscription entries where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param active the active
	 * @param start the lower bound of the range of cp subscription entries
	 * @param end the upper bound of the range of cp subscription entries (not inclusive)
	 * @return the range of matching cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findByactive(boolean active, int start,
		int end) {
		return findByactive(active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp subscription entries where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param active the active
	 * @param start the lower bound of the range of cp subscription entries
	 * @param end the upper bound of the range of cp subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findByactive(boolean active, int start,
		int end, OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		return findByactive(active, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp subscription entries where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param active the active
	 * @param start the lower bound of the range of cp subscription entries
	 * @param end the upper bound of the range of cp subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findByactive(boolean active, int start,
		int end, OrderByComparator<CPSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE;
			finderArgs = new Object[] { active };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVE;
			finderArgs = new Object[] { active, start, end, orderByComparator };
		}

		List<CPSubscriptionEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CPSubscriptionEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPSubscriptionEntry cpSubscriptionEntry : list) {
					if ((active != cpSubscriptionEntry.isActive())) {
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

			query.append(_SQL_SELECT_CPSUBSCRIPTIONENTRY_WHERE);

			query.append(_FINDER_COLUMN_ACTIVE_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CPSubscriptionEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(active);

				if (!pagination) {
					list = (List<CPSubscriptionEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPSubscriptionEntry>)QueryUtil.list(q,
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
	 * Returns the first cp subscription entry in the ordered set where active = &#63;.
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp subscription entry
	 * @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry findByactive_First(boolean active,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException {
		CPSubscriptionEntry cpSubscriptionEntry = fetchByactive_First(active,
				orderByComparator);

		if (cpSubscriptionEntry != null) {
			return cpSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("active=");
		msg.append(active);

		msg.append("}");

		throw new NoSuchCPSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the first cp subscription entry in the ordered set where active = &#63;.
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry fetchByactive_First(boolean active,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		List<CPSubscriptionEntry> list = findByactive(active, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp subscription entry in the ordered set where active = &#63;.
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp subscription entry
	 * @throws NoSuchCPSubscriptionEntryException if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry findByactive_Last(boolean active,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException {
		CPSubscriptionEntry cpSubscriptionEntry = fetchByactive_Last(active,
				orderByComparator);

		if (cpSubscriptionEntry != null) {
			return cpSubscriptionEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("active=");
		msg.append(active);

		msg.append("}");

		throw new NoSuchCPSubscriptionEntryException(msg.toString());
	}

	/**
	 * Returns the last cp subscription entry in the ordered set where active = &#63;.
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp subscription entry, or <code>null</code> if a matching cp subscription entry could not be found
	 */
	@Override
	public CPSubscriptionEntry fetchByactive_Last(boolean active,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		int count = countByactive(active);

		if (count == 0) {
			return null;
		}

		List<CPSubscriptionEntry> list = findByactive(active, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp subscription entries before and after the current cp subscription entry in the ordered set where active = &#63;.
	 *
	 * @param CPSubscriptionEntryId the primary key of the current cp subscription entry
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp subscription entry
	 * @throws NoSuchCPSubscriptionEntryException if a cp subscription entry with the primary key could not be found
	 */
	@Override
	public CPSubscriptionEntry[] findByactive_PrevAndNext(
		long CPSubscriptionEntryId, boolean active,
		OrderByComparator<CPSubscriptionEntry> orderByComparator)
		throws NoSuchCPSubscriptionEntryException {
		CPSubscriptionEntry cpSubscriptionEntry = findByPrimaryKey(CPSubscriptionEntryId);

		Session session = null;

		try {
			session = openSession();

			CPSubscriptionEntry[] array = new CPSubscriptionEntryImpl[3];

			array[0] = getByactive_PrevAndNext(session, cpSubscriptionEntry,
					active, orderByComparator, true);

			array[1] = cpSubscriptionEntry;

			array[2] = getByactive_PrevAndNext(session, cpSubscriptionEntry,
					active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPSubscriptionEntry getByactive_PrevAndNext(Session session,
		CPSubscriptionEntry cpSubscriptionEntry, boolean active,
		OrderByComparator<CPSubscriptionEntry> orderByComparator,
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

		query.append(_SQL_SELECT_CPSUBSCRIPTIONENTRY_WHERE);

		query.append(_FINDER_COLUMN_ACTIVE_ACTIVE_2);

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
			query.append(CPSubscriptionEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cpSubscriptionEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CPSubscriptionEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp subscription entries where active = &#63; from the database.
	 *
	 * @param active the active
	 */
	@Override
	public void removeByactive(boolean active) {
		for (CPSubscriptionEntry cpSubscriptionEntry : findByactive(active,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(cpSubscriptionEntry);
		}
	}

	/**
	 * Returns the number of cp subscription entries where active = &#63;.
	 *
	 * @param active the active
	 * @return the number of matching cp subscription entries
	 */
	@Override
	public int countByactive(boolean active) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVE;

		Object[] finderArgs = new Object[] { active };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPSUBSCRIPTIONENTRY_WHERE);

			query.append(_FINDER_COLUMN_ACTIVE_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(active);

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

	private static final String _FINDER_COLUMN_ACTIVE_ACTIVE_2 = "cpSubscriptionEntry.active = ?";

	public CPSubscriptionEntryPersistenceImpl() {
		setModelClass(CPSubscriptionEntry.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("active", "active_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the cp subscription entry in the entity cache if it is enabled.
	 *
	 * @param cpSubscriptionEntry the cp subscription entry
	 */
	@Override
	public void cacheResult(CPSubscriptionEntry cpSubscriptionEntry) {
		entityCache.putResult(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryImpl.class, cpSubscriptionEntry.getPrimaryKey(),
			cpSubscriptionEntry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				cpSubscriptionEntry.getUuid(), cpSubscriptionEntry.getGroupId()
			}, cpSubscriptionEntry);

		cpSubscriptionEntry.resetOriginalValues();
	}

	/**
	 * Caches the cp subscription entries in the entity cache if it is enabled.
	 *
	 * @param cpSubscriptionEntries the cp subscription entries
	 */
	@Override
	public void cacheResult(List<CPSubscriptionEntry> cpSubscriptionEntries) {
		for (CPSubscriptionEntry cpSubscriptionEntry : cpSubscriptionEntries) {
			if (entityCache.getResult(
						CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
						CPSubscriptionEntryImpl.class,
						cpSubscriptionEntry.getPrimaryKey()) == null) {
				cacheResult(cpSubscriptionEntry);
			}
			else {
				cpSubscriptionEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cp subscription entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CPSubscriptionEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cp subscription entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CPSubscriptionEntry cpSubscriptionEntry) {
		entityCache.removeResult(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryImpl.class, cpSubscriptionEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CPSubscriptionEntryModelImpl)cpSubscriptionEntry,
			true);
	}

	@Override
	public void clearCache(List<CPSubscriptionEntry> cpSubscriptionEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CPSubscriptionEntry cpSubscriptionEntry : cpSubscriptionEntries) {
			entityCache.removeResult(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
				CPSubscriptionEntryImpl.class,
				cpSubscriptionEntry.getPrimaryKey());

			clearUniqueFindersCache((CPSubscriptionEntryModelImpl)cpSubscriptionEntry,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		CPSubscriptionEntryModelImpl cpSubscriptionEntryModelImpl) {
		Object[] args = new Object[] {
				cpSubscriptionEntryModelImpl.getUuid(),
				cpSubscriptionEntryModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			cpSubscriptionEntryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CPSubscriptionEntryModelImpl cpSubscriptionEntryModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					cpSubscriptionEntryModelImpl.getUuid(),
					cpSubscriptionEntryModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((cpSubscriptionEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					cpSubscriptionEntryModelImpl.getOriginalUuid(),
					cpSubscriptionEntryModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new cp subscription entry with the primary key. Does not add the cp subscription entry to the database.
	 *
	 * @param CPSubscriptionEntryId the primary key for the new cp subscription entry
	 * @return the new cp subscription entry
	 */
	@Override
	public CPSubscriptionEntry create(long CPSubscriptionEntryId) {
		CPSubscriptionEntry cpSubscriptionEntry = new CPSubscriptionEntryImpl();

		cpSubscriptionEntry.setNew(true);
		cpSubscriptionEntry.setPrimaryKey(CPSubscriptionEntryId);

		String uuid = PortalUUIDUtil.generate();

		cpSubscriptionEntry.setUuid(uuid);

		cpSubscriptionEntry.setCompanyId(companyProvider.getCompanyId());

		return cpSubscriptionEntry;
	}

	/**
	 * Removes the cp subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPSubscriptionEntryId the primary key of the cp subscription entry
	 * @return the cp subscription entry that was removed
	 * @throws NoSuchCPSubscriptionEntryException if a cp subscription entry with the primary key could not be found
	 */
	@Override
	public CPSubscriptionEntry remove(long CPSubscriptionEntryId)
		throws NoSuchCPSubscriptionEntryException {
		return remove((Serializable)CPSubscriptionEntryId);
	}

	/**
	 * Removes the cp subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cp subscription entry
	 * @return the cp subscription entry that was removed
	 * @throws NoSuchCPSubscriptionEntryException if a cp subscription entry with the primary key could not be found
	 */
	@Override
	public CPSubscriptionEntry remove(Serializable primaryKey)
		throws NoSuchCPSubscriptionEntryException {
		Session session = null;

		try {
			session = openSession();

			CPSubscriptionEntry cpSubscriptionEntry = (CPSubscriptionEntry)session.get(CPSubscriptionEntryImpl.class,
					primaryKey);

			if (cpSubscriptionEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCPSubscriptionEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(cpSubscriptionEntry);
		}
		catch (NoSuchCPSubscriptionEntryException nsee) {
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
	protected CPSubscriptionEntry removeImpl(
		CPSubscriptionEntry cpSubscriptionEntry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cpSubscriptionEntry)) {
				cpSubscriptionEntry = (CPSubscriptionEntry)session.get(CPSubscriptionEntryImpl.class,
						cpSubscriptionEntry.getPrimaryKeyObj());
			}

			if (cpSubscriptionEntry != null) {
				session.delete(cpSubscriptionEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cpSubscriptionEntry != null) {
			clearCache(cpSubscriptionEntry);
		}

		return cpSubscriptionEntry;
	}

	@Override
	public CPSubscriptionEntry updateImpl(
		CPSubscriptionEntry cpSubscriptionEntry) {
		boolean isNew = cpSubscriptionEntry.isNew();

		if (!(cpSubscriptionEntry instanceof CPSubscriptionEntryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(cpSubscriptionEntry.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(cpSubscriptionEntry);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in cpSubscriptionEntry proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CPSubscriptionEntry implementation " +
				cpSubscriptionEntry.getClass());
		}

		CPSubscriptionEntryModelImpl cpSubscriptionEntryModelImpl = (CPSubscriptionEntryModelImpl)cpSubscriptionEntry;

		if (Validator.isNull(cpSubscriptionEntry.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			cpSubscriptionEntry.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (cpSubscriptionEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				cpSubscriptionEntry.setCreateDate(now);
			}
			else {
				cpSubscriptionEntry.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!cpSubscriptionEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				cpSubscriptionEntry.setModifiedDate(now);
			}
			else {
				cpSubscriptionEntry.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (cpSubscriptionEntry.isNew()) {
				session.save(cpSubscriptionEntry);

				cpSubscriptionEntry.setNew(false);
			}
			else {
				cpSubscriptionEntry = (CPSubscriptionEntry)session.merge(cpSubscriptionEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CPSubscriptionEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { cpSubscriptionEntryModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					cpSubscriptionEntryModelImpl.getUuid(),
					cpSubscriptionEntryModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { cpSubscriptionEntryModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] { cpSubscriptionEntryModelImpl.getCPInstanceId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CPINSTANCEID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPINSTANCEID,
				args);

			args = new Object[] {
					cpSubscriptionEntryModelImpl.getGroupId(),
					cpSubscriptionEntryModelImpl.getUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_U, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U,
				args);

			args = new Object[] { cpSubscriptionEntryModelImpl.isActive() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIVE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((cpSubscriptionEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cpSubscriptionEntryModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { cpSubscriptionEntryModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((cpSubscriptionEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cpSubscriptionEntryModelImpl.getOriginalUuid(),
						cpSubscriptionEntryModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						cpSubscriptionEntryModelImpl.getUuid(),
						cpSubscriptionEntryModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((cpSubscriptionEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cpSubscriptionEntryModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { cpSubscriptionEntryModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((cpSubscriptionEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPINSTANCEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cpSubscriptionEntryModelImpl.getOriginalCPInstanceId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CPINSTANCEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPINSTANCEID,
					args);

				args = new Object[] {
						cpSubscriptionEntryModelImpl.getCPInstanceId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CPINSTANCEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPINSTANCEID,
					args);
			}

			if ((cpSubscriptionEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cpSubscriptionEntryModelImpl.getOriginalGroupId(),
						cpSubscriptionEntryModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_U, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U,
					args);

				args = new Object[] {
						cpSubscriptionEntryModelImpl.getGroupId(),
						cpSubscriptionEntryModelImpl.getUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_U, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U,
					args);
			}

			if ((cpSubscriptionEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cpSubscriptionEntryModelImpl.getOriginalActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIVE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE,
					args);

				args = new Object[] { cpSubscriptionEntryModelImpl.isActive() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIVE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE,
					args);
			}
		}

		entityCache.putResult(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
			CPSubscriptionEntryImpl.class, cpSubscriptionEntry.getPrimaryKey(),
			cpSubscriptionEntry, false);

		clearUniqueFindersCache(cpSubscriptionEntryModelImpl, false);
		cacheUniqueFindersCache(cpSubscriptionEntryModelImpl);

		cpSubscriptionEntry.resetOriginalValues();

		return cpSubscriptionEntry;
	}

	/**
	 * Returns the cp subscription entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp subscription entry
	 * @return the cp subscription entry
	 * @throws NoSuchCPSubscriptionEntryException if a cp subscription entry with the primary key could not be found
	 */
	@Override
	public CPSubscriptionEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCPSubscriptionEntryException {
		CPSubscriptionEntry cpSubscriptionEntry = fetchByPrimaryKey(primaryKey);

		if (cpSubscriptionEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCPSubscriptionEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return cpSubscriptionEntry;
	}

	/**
	 * Returns the cp subscription entry with the primary key or throws a {@link NoSuchCPSubscriptionEntryException} if it could not be found.
	 *
	 * @param CPSubscriptionEntryId the primary key of the cp subscription entry
	 * @return the cp subscription entry
	 * @throws NoSuchCPSubscriptionEntryException if a cp subscription entry with the primary key could not be found
	 */
	@Override
	public CPSubscriptionEntry findByPrimaryKey(long CPSubscriptionEntryId)
		throws NoSuchCPSubscriptionEntryException {
		return findByPrimaryKey((Serializable)CPSubscriptionEntryId);
	}

	/**
	 * Returns the cp subscription entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp subscription entry
	 * @return the cp subscription entry, or <code>null</code> if a cp subscription entry with the primary key could not be found
	 */
	@Override
	public CPSubscriptionEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
				CPSubscriptionEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CPSubscriptionEntry cpSubscriptionEntry = (CPSubscriptionEntry)serializable;

		if (cpSubscriptionEntry == null) {
			Session session = null;

			try {
				session = openSession();

				cpSubscriptionEntry = (CPSubscriptionEntry)session.get(CPSubscriptionEntryImpl.class,
						primaryKey);

				if (cpSubscriptionEntry != null) {
					cacheResult(cpSubscriptionEntry);
				}
				else {
					entityCache.putResult(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
						CPSubscriptionEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
					CPSubscriptionEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cpSubscriptionEntry;
	}

	/**
	 * Returns the cp subscription entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param CPSubscriptionEntryId the primary key of the cp subscription entry
	 * @return the cp subscription entry, or <code>null</code> if a cp subscription entry with the primary key could not be found
	 */
	@Override
	public CPSubscriptionEntry fetchByPrimaryKey(long CPSubscriptionEntryId) {
		return fetchByPrimaryKey((Serializable)CPSubscriptionEntryId);
	}

	@Override
	public Map<Serializable, CPSubscriptionEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CPSubscriptionEntry> map = new HashMap<Serializable, CPSubscriptionEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CPSubscriptionEntry cpSubscriptionEntry = fetchByPrimaryKey(primaryKey);

			if (cpSubscriptionEntry != null) {
				map.put(primaryKey, cpSubscriptionEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
					CPSubscriptionEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CPSubscriptionEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CPSUBSCRIPTIONENTRY_WHERE_PKS_IN);

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

			for (CPSubscriptionEntry cpSubscriptionEntry : (List<CPSubscriptionEntry>)q.list()) {
				map.put(cpSubscriptionEntry.getPrimaryKeyObj(),
					cpSubscriptionEntry);

				cacheResult(cpSubscriptionEntry);

				uncachedPrimaryKeys.remove(cpSubscriptionEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CPSubscriptionEntryModelImpl.ENTITY_CACHE_ENABLED,
					CPSubscriptionEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the cp subscription entries.
	 *
	 * @return the cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp subscription entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp subscription entries
	 * @param end the upper bound of the range of cp subscription entries (not inclusive)
	 * @return the range of cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp subscription entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp subscription entries
	 * @param end the upper bound of the range of cp subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findAll(int start, int end,
		OrderByComparator<CPSubscriptionEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp subscription entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp subscription entries
	 * @param end the upper bound of the range of cp subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cp subscription entries
	 */
	@Override
	public List<CPSubscriptionEntry> findAll(int start, int end,
		OrderByComparator<CPSubscriptionEntry> orderByComparator,
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

		List<CPSubscriptionEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CPSubscriptionEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CPSUBSCRIPTIONENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CPSUBSCRIPTIONENTRY;

				if (pagination) {
					sql = sql.concat(CPSubscriptionEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CPSubscriptionEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPSubscriptionEntry>)QueryUtil.list(q,
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
	 * Removes all the cp subscription entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CPSubscriptionEntry cpSubscriptionEntry : findAll()) {
			remove(cpSubscriptionEntry);
		}
	}

	/**
	 * Returns the number of cp subscription entries.
	 *
	 * @return the number of cp subscription entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CPSUBSCRIPTIONENTRY);

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
		return CPSubscriptionEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cp subscription entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CPSubscriptionEntryImpl.class.getName());
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
	private static final String _SQL_SELECT_CPSUBSCRIPTIONENTRY = "SELECT cpSubscriptionEntry FROM CPSubscriptionEntry cpSubscriptionEntry";
	private static final String _SQL_SELECT_CPSUBSCRIPTIONENTRY_WHERE_PKS_IN = "SELECT cpSubscriptionEntry FROM CPSubscriptionEntry cpSubscriptionEntry WHERE CPSubscriptionEntryId IN (";
	private static final String _SQL_SELECT_CPSUBSCRIPTIONENTRY_WHERE = "SELECT cpSubscriptionEntry FROM CPSubscriptionEntry cpSubscriptionEntry WHERE ";
	private static final String _SQL_COUNT_CPSUBSCRIPTIONENTRY = "SELECT COUNT(cpSubscriptionEntry) FROM CPSubscriptionEntry cpSubscriptionEntry";
	private static final String _SQL_COUNT_CPSUBSCRIPTIONENTRY_WHERE = "SELECT COUNT(cpSubscriptionEntry) FROM CPSubscriptionEntry cpSubscriptionEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "cpSubscriptionEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CPSubscriptionEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CPSubscriptionEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CPSubscriptionEntryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "active"
			});
}