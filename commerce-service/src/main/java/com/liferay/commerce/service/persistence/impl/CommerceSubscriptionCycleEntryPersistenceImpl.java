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

import com.liferay.commerce.exception.NoSuchSubscriptionCycleEntryException;
import com.liferay.commerce.model.CommerceSubscriptionCycleEntry;
import com.liferay.commerce.model.impl.CommerceSubscriptionCycleEntryImpl;
import com.liferay.commerce.model.impl.CommerceSubscriptionCycleEntryModelImpl;
import com.liferay.commerce.service.persistence.CommerceSubscriptionCycleEntryPersistence;

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
 * The persistence implementation for the commerce subscription cycle entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceSubscriptionCycleEntryPersistence
 * @see com.liferay.commerce.service.persistence.CommerceSubscriptionCycleEntryUtil
 * @generated
 */
@ProviderType
public class CommerceSubscriptionCycleEntryPersistenceImpl
	extends BasePersistenceImpl<CommerceSubscriptionCycleEntry>
	implements CommerceSubscriptionCycleEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommerceSubscriptionCycleEntryUtil} to access the commerce subscription cycle entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommerceSubscriptionCycleEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			CommerceSubscriptionCycleEntryModelImpl.UUID_COLUMN_BITMASK |
			CommerceSubscriptionCycleEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUuid", new String[] { String.class.getName() });

	/**
	 * Returns all the commerce subscription cycle entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching commerce subscription cycle entries
	 */
	@Override
	public List<CommerceSubscriptionCycleEntry> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce subscription cycle entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce subscription cycle entries
	 * @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	 * @return the range of matching commerce subscription cycle entries
	 */
	@Override
	public List<CommerceSubscriptionCycleEntry> findByUuid(String uuid,
		int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce subscription cycle entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce subscription cycle entries
	 * @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce subscription cycle entries
	 */
	@Override
	public List<CommerceSubscriptionCycleEntry> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce subscription cycle entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce subscription cycle entries
	 * @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce subscription cycle entries
	 */
	@Override
	public List<CommerceSubscriptionCycleEntry> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator,
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

		List<CommerceSubscriptionCycleEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceSubscriptionCycleEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry : list) {
					if (!Objects.equals(uuid,
								commerceSubscriptionCycleEntry.getUuid())) {
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

			query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONCYCLEENTRY_WHERE);

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
				query.append(CommerceSubscriptionCycleEntryModelImpl.ORDER_BY_JPQL);
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
					list = (List<CommerceSubscriptionCycleEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceSubscriptionCycleEntry>)QueryUtil.list(q,
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
	 * Returns the first commerce subscription cycle entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce subscription cycle entry
	 * @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry findByUuid_First(String uuid,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws NoSuchSubscriptionCycleEntryException {
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = fetchByUuid_First(uuid,
				orderByComparator);

		if (commerceSubscriptionCycleEntry != null) {
			return commerceSubscriptionCycleEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchSubscriptionCycleEntryException(msg.toString());
	}

	/**
	 * Returns the first commerce subscription cycle entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry fetchByUuid_First(String uuid,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		List<CommerceSubscriptionCycleEntry> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce subscription cycle entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce subscription cycle entry
	 * @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry findByUuid_Last(String uuid,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws NoSuchSubscriptionCycleEntryException {
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = fetchByUuid_Last(uuid,
				orderByComparator);

		if (commerceSubscriptionCycleEntry != null) {
			return commerceSubscriptionCycleEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchSubscriptionCycleEntryException(msg.toString());
	}

	/**
	 * Returns the last commerce subscription cycle entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry fetchByUuid_Last(String uuid,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CommerceSubscriptionCycleEntry> list = findByUuid(uuid, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce subscription cycle entries before and after the current commerce subscription cycle entry in the ordered set where uuid = &#63;.
	 *
	 * @param commerceSubscriptionCycleEntryId the primary key of the current commerce subscription cycle entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce subscription cycle entry
	 * @throws NoSuchSubscriptionCycleEntryException if a commerce subscription cycle entry with the primary key could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry[] findByUuid_PrevAndNext(
		long commerceSubscriptionCycleEntryId, String uuid,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws NoSuchSubscriptionCycleEntryException {
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = findByPrimaryKey(commerceSubscriptionCycleEntryId);

		Session session = null;

		try {
			session = openSession();

			CommerceSubscriptionCycleEntry[] array = new CommerceSubscriptionCycleEntryImpl[3];

			array[0] = getByUuid_PrevAndNext(session,
					commerceSubscriptionCycleEntry, uuid, orderByComparator,
					true);

			array[1] = commerceSubscriptionCycleEntry;

			array[2] = getByUuid_PrevAndNext(session,
					commerceSubscriptionCycleEntry, uuid, orderByComparator,
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

	protected CommerceSubscriptionCycleEntry getByUuid_PrevAndNext(
		Session session,
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry,
		String uuid,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONCYCLEENTRY_WHERE);

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
			query.append(CommerceSubscriptionCycleEntryModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(commerceSubscriptionCycleEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceSubscriptionCycleEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce subscription cycle entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry : findByUuid(
				uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(commerceSubscriptionCycleEntry);
		}
	}

	/**
	 * Returns the number of commerce subscription cycle entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching commerce subscription cycle entries
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCESUBSCRIPTIONCYCLEENTRY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "commerceSubscriptionCycleEntry.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "commerceSubscriptionCycleEntry.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(commerceSubscriptionCycleEntry.uuid IS NULL OR commerceSubscriptionCycleEntry.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			CommerceSubscriptionCycleEntryModelImpl.UUID_COLUMN_BITMASK |
			CommerceSubscriptionCycleEntryModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the commerce subscription cycle entry where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSubscriptionCycleEntryException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching commerce subscription cycle entry
	 * @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry findByUUID_G(String uuid, long groupId)
		throws NoSuchSubscriptionCycleEntryException {
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = fetchByUUID_G(uuid,
				groupId);

		if (commerceSubscriptionCycleEntry == null) {
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

			throw new NoSuchSubscriptionCycleEntryException(msg.toString());
		}

		return commerceSubscriptionCycleEntry;
	}

	/**
	 * Returns the commerce subscription cycle entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry fetchByUUID_G(String uuid,
		long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the commerce subscription cycle entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry fetchByUUID_G(String uuid,
		long groupId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof CommerceSubscriptionCycleEntry) {
			CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = (CommerceSubscriptionCycleEntry)result;

			if (!Objects.equals(uuid, commerceSubscriptionCycleEntry.getUuid()) ||
					(groupId != commerceSubscriptionCycleEntry.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONCYCLEENTRY_WHERE);

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

				List<CommerceSubscriptionCycleEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry =
						list.get(0);

					result = commerceSubscriptionCycleEntry;

					cacheResult(commerceSubscriptionCycleEntry);
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
			return (CommerceSubscriptionCycleEntry)result;
		}
	}

	/**
	 * Removes the commerce subscription cycle entry where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the commerce subscription cycle entry that was removed
	 */
	@Override
	public CommerceSubscriptionCycleEntry removeByUUID_G(String uuid,
		long groupId) throws NoSuchSubscriptionCycleEntryException {
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = findByUUID_G(uuid,
				groupId);

		return remove(commerceSubscriptionCycleEntry);
	}

	/**
	 * Returns the number of commerce subscription cycle entries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching commerce subscription cycle entries
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCESUBSCRIPTIONCYCLEENTRY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "commerceSubscriptionCycleEntry.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "commerceSubscriptionCycleEntry.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(commerceSubscriptionCycleEntry.uuid IS NULL OR commerceSubscriptionCycleEntry.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "commerceSubscriptionCycleEntry.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			CommerceSubscriptionCycleEntryModelImpl.UUID_COLUMN_BITMASK |
			CommerceSubscriptionCycleEntryModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceSubscriptionCycleEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the commerce subscription cycle entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching commerce subscription cycle entries
	 */
	@Override
	public List<CommerceSubscriptionCycleEntry> findByUuid_C(String uuid,
		long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce subscription cycle entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce subscription cycle entries
	 * @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	 * @return the range of matching commerce subscription cycle entries
	 */
	@Override
	public List<CommerceSubscriptionCycleEntry> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce subscription cycle entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce subscription cycle entries
	 * @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce subscription cycle entries
	 */
	@Override
	public List<CommerceSubscriptionCycleEntry> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce subscription cycle entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce subscription cycle entries
	 * @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce subscription cycle entries
	 */
	@Override
	public List<CommerceSubscriptionCycleEntry> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator,
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

		List<CommerceSubscriptionCycleEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceSubscriptionCycleEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry : list) {
					if (!Objects.equals(uuid,
								commerceSubscriptionCycleEntry.getUuid()) ||
							(companyId != commerceSubscriptionCycleEntry.getCompanyId())) {
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

			query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONCYCLEENTRY_WHERE);

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
				query.append(CommerceSubscriptionCycleEntryModelImpl.ORDER_BY_JPQL);
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
					list = (List<CommerceSubscriptionCycleEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceSubscriptionCycleEntry>)QueryUtil.list(q,
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
	 * Returns the first commerce subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce subscription cycle entry
	 * @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws NoSuchSubscriptionCycleEntryException {
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (commerceSubscriptionCycleEntry != null) {
			return commerceSubscriptionCycleEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchSubscriptionCycleEntryException(msg.toString());
	}

	/**
	 * Returns the first commerce subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		List<CommerceSubscriptionCycleEntry> list = findByUuid_C(uuid,
				companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce subscription cycle entry
	 * @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws NoSuchSubscriptionCycleEntryException {
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (commerceSubscriptionCycleEntry != null) {
			return commerceSubscriptionCycleEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchSubscriptionCycleEntryException(msg.toString());
	}

	/**
	 * Returns the last commerce subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CommerceSubscriptionCycleEntry> list = findByUuid_C(uuid,
				companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce subscription cycle entries before and after the current commerce subscription cycle entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param commerceSubscriptionCycleEntryId the primary key of the current commerce subscription cycle entry
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce subscription cycle entry
	 * @throws NoSuchSubscriptionCycleEntryException if a commerce subscription cycle entry with the primary key could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry[] findByUuid_C_PrevAndNext(
		long commerceSubscriptionCycleEntryId, String uuid, long companyId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws NoSuchSubscriptionCycleEntryException {
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = findByPrimaryKey(commerceSubscriptionCycleEntryId);

		Session session = null;

		try {
			session = openSession();

			CommerceSubscriptionCycleEntry[] array = new CommerceSubscriptionCycleEntryImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session,
					commerceSubscriptionCycleEntry, uuid, companyId,
					orderByComparator, true);

			array[1] = commerceSubscriptionCycleEntry;

			array[2] = getByUuid_C_PrevAndNext(session,
					commerceSubscriptionCycleEntry, uuid, companyId,
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

	protected CommerceSubscriptionCycleEntry getByUuid_C_PrevAndNext(
		Session session,
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry,
		String uuid, long companyId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONCYCLEENTRY_WHERE);

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
			query.append(CommerceSubscriptionCycleEntryModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(commerceSubscriptionCycleEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceSubscriptionCycleEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce subscription cycle entries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry : findByUuid_C(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(commerceSubscriptionCycleEntry);
		}
	}

	/**
	 * Returns the number of commerce subscription cycle entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching commerce subscription cycle entries
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCESUBSCRIPTIONCYCLEENTRY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "commerceSubscriptionCycleEntry.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "commerceSubscriptionCycleEntry.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(commerceSubscriptionCycleEntry.uuid IS NULL OR commerceSubscriptionCycleEntry.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "commerceSubscriptionCycleEntry.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			CommerceSubscriptionCycleEntryModelImpl.GROUPID_COLUMN_BITMASK |
			CommerceSubscriptionCycleEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupId", new String[] { Long.class.getName() });

	/**
	 * Returns all the commerce subscription cycle entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching commerce subscription cycle entries
	 */
	@Override
	public List<CommerceSubscriptionCycleEntry> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce subscription cycle entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce subscription cycle entries
	 * @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	 * @return the range of matching commerce subscription cycle entries
	 */
	@Override
	public List<CommerceSubscriptionCycleEntry> findByGroupId(long groupId,
		int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce subscription cycle entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce subscription cycle entries
	 * @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce subscription cycle entries
	 */
	@Override
	public List<CommerceSubscriptionCycleEntry> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce subscription cycle entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce subscription cycle entries
	 * @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce subscription cycle entries
	 */
	@Override
	public List<CommerceSubscriptionCycleEntry> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator,
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

		List<CommerceSubscriptionCycleEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceSubscriptionCycleEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry : list) {
					if ((groupId != commerceSubscriptionCycleEntry.getGroupId())) {
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

			query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONCYCLEENTRY_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceSubscriptionCycleEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<CommerceSubscriptionCycleEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceSubscriptionCycleEntry>)QueryUtil.list(q,
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
	 * Returns the first commerce subscription cycle entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce subscription cycle entry
	 * @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry findByGroupId_First(long groupId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws NoSuchSubscriptionCycleEntryException {
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = fetchByGroupId_First(groupId,
				orderByComparator);

		if (commerceSubscriptionCycleEntry != null) {
			return commerceSubscriptionCycleEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchSubscriptionCycleEntryException(msg.toString());
	}

	/**
	 * Returns the first commerce subscription cycle entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry fetchByGroupId_First(long groupId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		List<CommerceSubscriptionCycleEntry> list = findByGroupId(groupId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce subscription cycle entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce subscription cycle entry
	 * @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry findByGroupId_Last(long groupId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws NoSuchSubscriptionCycleEntryException {
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (commerceSubscriptionCycleEntry != null) {
			return commerceSubscriptionCycleEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchSubscriptionCycleEntryException(msg.toString());
	}

	/**
	 * Returns the last commerce subscription cycle entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry fetchByGroupId_Last(long groupId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<CommerceSubscriptionCycleEntry> list = findByGroupId(groupId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce subscription cycle entries before and after the current commerce subscription cycle entry in the ordered set where groupId = &#63;.
	 *
	 * @param commerceSubscriptionCycleEntryId the primary key of the current commerce subscription cycle entry
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce subscription cycle entry
	 * @throws NoSuchSubscriptionCycleEntryException if a commerce subscription cycle entry with the primary key could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry[] findByGroupId_PrevAndNext(
		long commerceSubscriptionCycleEntryId, long groupId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws NoSuchSubscriptionCycleEntryException {
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = findByPrimaryKey(commerceSubscriptionCycleEntryId);

		Session session = null;

		try {
			session = openSession();

			CommerceSubscriptionCycleEntry[] array = new CommerceSubscriptionCycleEntryImpl[3];

			array[0] = getByGroupId_PrevAndNext(session,
					commerceSubscriptionCycleEntry, groupId, orderByComparator,
					true);

			array[1] = commerceSubscriptionCycleEntry;

			array[2] = getByGroupId_PrevAndNext(session,
					commerceSubscriptionCycleEntry, groupId, orderByComparator,
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

	protected CommerceSubscriptionCycleEntry getByGroupId_PrevAndNext(
		Session session,
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry,
		long groupId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONCYCLEENTRY_WHERE);

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
			query.append(CommerceSubscriptionCycleEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceSubscriptionCycleEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceSubscriptionCycleEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce subscription cycle entries where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry : findByGroupId(
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(commerceSubscriptionCycleEntry);
		}
	}

	/**
	 * Returns the number of commerce subscription cycle entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching commerce subscription cycle entries
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCESUBSCRIPTIONCYCLEENTRY_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "commerceSubscriptionCycleEntry.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_COMMERCEORDERITEMID = new FinderPath(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByCommerceOrderItemId",
			new String[] { Long.class.getName() },
			CommerceSubscriptionCycleEntryModelImpl.COMMERCEORDERITEMID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMMERCEORDERITEMID = new FinderPath(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceOrderItemId", new String[] { Long.class.getName() });

	/**
	 * Returns the commerce subscription cycle entry where commerceOrderItemId = &#63; or throws a {@link NoSuchSubscriptionCycleEntryException} if it could not be found.
	 *
	 * @param commerceOrderItemId the commerce order item ID
	 * @return the matching commerce subscription cycle entry
	 * @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry findByCommerceOrderItemId(
		long commerceOrderItemId) throws NoSuchSubscriptionCycleEntryException {
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = fetchByCommerceOrderItemId(commerceOrderItemId);

		if (commerceSubscriptionCycleEntry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("commerceOrderItemId=");
			msg.append(commerceOrderItemId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchSubscriptionCycleEntryException(msg.toString());
		}

		return commerceSubscriptionCycleEntry;
	}

	/**
	 * Returns the commerce subscription cycle entry where commerceOrderItemId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param commerceOrderItemId the commerce order item ID
	 * @return the matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry fetchByCommerceOrderItemId(
		long commerceOrderItemId) {
		return fetchByCommerceOrderItemId(commerceOrderItemId, true);
	}

	/**
	 * Returns the commerce subscription cycle entry where commerceOrderItemId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param commerceOrderItemId the commerce order item ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry fetchByCommerceOrderItemId(
		long commerceOrderItemId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { commerceOrderItemId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_COMMERCEORDERITEMID,
					finderArgs, this);
		}

		if (result instanceof CommerceSubscriptionCycleEntry) {
			CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = (CommerceSubscriptionCycleEntry)result;

			if ((commerceOrderItemId != commerceSubscriptionCycleEntry.getCommerceOrderItemId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONCYCLEENTRY_WHERE);

			query.append(_FINDER_COLUMN_COMMERCEORDERITEMID_COMMERCEORDERITEMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceOrderItemId);

				List<CommerceSubscriptionCycleEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_COMMERCEORDERITEMID,
						finderArgs, list);
				}
				else {
					CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry =
						list.get(0);

					result = commerceSubscriptionCycleEntry;

					cacheResult(commerceSubscriptionCycleEntry);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_COMMERCEORDERITEMID,
					finderArgs);

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
			return (CommerceSubscriptionCycleEntry)result;
		}
	}

	/**
	 * Removes the commerce subscription cycle entry where commerceOrderItemId = &#63; from the database.
	 *
	 * @param commerceOrderItemId the commerce order item ID
	 * @return the commerce subscription cycle entry that was removed
	 */
	@Override
	public CommerceSubscriptionCycleEntry removeByCommerceOrderItemId(
		long commerceOrderItemId) throws NoSuchSubscriptionCycleEntryException {
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = findByCommerceOrderItemId(commerceOrderItemId);

		return remove(commerceSubscriptionCycleEntry);
	}

	/**
	 * Returns the number of commerce subscription cycle entries where commerceOrderItemId = &#63;.
	 *
	 * @param commerceOrderItemId the commerce order item ID
	 * @return the number of matching commerce subscription cycle entries
	 */
	@Override
	public int countByCommerceOrderItemId(long commerceOrderItemId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMMERCEORDERITEMID;

		Object[] finderArgs = new Object[] { commerceOrderItemId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCESUBSCRIPTIONCYCLEENTRY_WHERE);

			query.append(_FINDER_COLUMN_COMMERCEORDERITEMID_COMMERCEORDERITEMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_COMMERCEORDERITEMID_COMMERCEORDERITEMID_2 =
		"commerceSubscriptionCycleEntry.commerceOrderItemId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMMERCESUBSCRIPTIONENTRYID =
		new FinderPath(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCommerceSubscriptionEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCESUBSCRIPTIONENTRYID =
		new FinderPath(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCommerceSubscriptionEntryId",
			new String[] { Long.class.getName() },
			CommerceSubscriptionCycleEntryModelImpl.COMMERCESUBSCRIPTIONENTRYID_COLUMN_BITMASK |
			CommerceSubscriptionCycleEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMMERCESUBSCRIPTIONENTRYID =
		new FinderPath(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceSubscriptionEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the commerce subscription cycle entries where commerceSubscriptionEntryId = &#63;.
	 *
	 * @param commerceSubscriptionEntryId the commerce subscription entry ID
	 * @return the matching commerce subscription cycle entries
	 */
	@Override
	public List<CommerceSubscriptionCycleEntry> findByCommerceSubscriptionEntryId(
		long commerceSubscriptionEntryId) {
		return findByCommerceSubscriptionEntryId(commerceSubscriptionEntryId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce subscription cycle entries where commerceSubscriptionEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceSubscriptionEntryId the commerce subscription entry ID
	 * @param start the lower bound of the range of commerce subscription cycle entries
	 * @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	 * @return the range of matching commerce subscription cycle entries
	 */
	@Override
	public List<CommerceSubscriptionCycleEntry> findByCommerceSubscriptionEntryId(
		long commerceSubscriptionEntryId, int start, int end) {
		return findByCommerceSubscriptionEntryId(commerceSubscriptionEntryId,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce subscription cycle entries where commerceSubscriptionEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceSubscriptionEntryId the commerce subscription entry ID
	 * @param start the lower bound of the range of commerce subscription cycle entries
	 * @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce subscription cycle entries
	 */
	@Override
	public List<CommerceSubscriptionCycleEntry> findByCommerceSubscriptionEntryId(
		long commerceSubscriptionEntryId, int start, int end,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		return findByCommerceSubscriptionEntryId(commerceSubscriptionEntryId,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce subscription cycle entries where commerceSubscriptionEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceSubscriptionEntryId the commerce subscription entry ID
	 * @param start the lower bound of the range of commerce subscription cycle entries
	 * @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce subscription cycle entries
	 */
	@Override
	public List<CommerceSubscriptionCycleEntry> findByCommerceSubscriptionEntryId(
		long commerceSubscriptionEntryId, int start, int end,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCESUBSCRIPTIONENTRYID;
			finderArgs = new Object[] { commerceSubscriptionEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMMERCESUBSCRIPTIONENTRYID;
			finderArgs = new Object[] {
					commerceSubscriptionEntryId,
					
					start, end, orderByComparator
				};
		}

		List<CommerceSubscriptionCycleEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceSubscriptionCycleEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry : list) {
					if ((commerceSubscriptionEntryId != commerceSubscriptionCycleEntry.getCommerceSubscriptionEntryId())) {
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

			query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONCYCLEENTRY_WHERE);

			query.append(_FINDER_COLUMN_COMMERCESUBSCRIPTIONENTRYID_COMMERCESUBSCRIPTIONENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceSubscriptionCycleEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceSubscriptionEntryId);

				if (!pagination) {
					list = (List<CommerceSubscriptionCycleEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceSubscriptionCycleEntry>)QueryUtil.list(q,
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
	 * Returns the first commerce subscription cycle entry in the ordered set where commerceSubscriptionEntryId = &#63;.
	 *
	 * @param commerceSubscriptionEntryId the commerce subscription entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce subscription cycle entry
	 * @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry findByCommerceSubscriptionEntryId_First(
		long commerceSubscriptionEntryId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws NoSuchSubscriptionCycleEntryException {
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = fetchByCommerceSubscriptionEntryId_First(commerceSubscriptionEntryId,
				orderByComparator);

		if (commerceSubscriptionCycleEntry != null) {
			return commerceSubscriptionCycleEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceSubscriptionEntryId=");
		msg.append(commerceSubscriptionEntryId);

		msg.append("}");

		throw new NoSuchSubscriptionCycleEntryException(msg.toString());
	}

	/**
	 * Returns the first commerce subscription cycle entry in the ordered set where commerceSubscriptionEntryId = &#63;.
	 *
	 * @param commerceSubscriptionEntryId the commerce subscription entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry fetchByCommerceSubscriptionEntryId_First(
		long commerceSubscriptionEntryId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		List<CommerceSubscriptionCycleEntry> list = findByCommerceSubscriptionEntryId(commerceSubscriptionEntryId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce subscription cycle entry in the ordered set where commerceSubscriptionEntryId = &#63;.
	 *
	 * @param commerceSubscriptionEntryId the commerce subscription entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce subscription cycle entry
	 * @throws NoSuchSubscriptionCycleEntryException if a matching commerce subscription cycle entry could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry findByCommerceSubscriptionEntryId_Last(
		long commerceSubscriptionEntryId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws NoSuchSubscriptionCycleEntryException {
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = fetchByCommerceSubscriptionEntryId_Last(commerceSubscriptionEntryId,
				orderByComparator);

		if (commerceSubscriptionCycleEntry != null) {
			return commerceSubscriptionCycleEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceSubscriptionEntryId=");
		msg.append(commerceSubscriptionEntryId);

		msg.append("}");

		throw new NoSuchSubscriptionCycleEntryException(msg.toString());
	}

	/**
	 * Returns the last commerce subscription cycle entry in the ordered set where commerceSubscriptionEntryId = &#63;.
	 *
	 * @param commerceSubscriptionEntryId the commerce subscription entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce subscription cycle entry, or <code>null</code> if a matching commerce subscription cycle entry could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry fetchByCommerceSubscriptionEntryId_Last(
		long commerceSubscriptionEntryId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		int count = countByCommerceSubscriptionEntryId(commerceSubscriptionEntryId);

		if (count == 0) {
			return null;
		}

		List<CommerceSubscriptionCycleEntry> list = findByCommerceSubscriptionEntryId(commerceSubscriptionEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce subscription cycle entries before and after the current commerce subscription cycle entry in the ordered set where commerceSubscriptionEntryId = &#63;.
	 *
	 * @param commerceSubscriptionCycleEntryId the primary key of the current commerce subscription cycle entry
	 * @param commerceSubscriptionEntryId the commerce subscription entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce subscription cycle entry
	 * @throws NoSuchSubscriptionCycleEntryException if a commerce subscription cycle entry with the primary key could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry[] findByCommerceSubscriptionEntryId_PrevAndNext(
		long commerceSubscriptionCycleEntryId,
		long commerceSubscriptionEntryId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator)
		throws NoSuchSubscriptionCycleEntryException {
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = findByPrimaryKey(commerceSubscriptionCycleEntryId);

		Session session = null;

		try {
			session = openSession();

			CommerceSubscriptionCycleEntry[] array = new CommerceSubscriptionCycleEntryImpl[3];

			array[0] = getByCommerceSubscriptionEntryId_PrevAndNext(session,
					commerceSubscriptionCycleEntry,
					commerceSubscriptionEntryId, orderByComparator, true);

			array[1] = commerceSubscriptionCycleEntry;

			array[2] = getByCommerceSubscriptionEntryId_PrevAndNext(session,
					commerceSubscriptionCycleEntry,
					commerceSubscriptionEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceSubscriptionCycleEntry getByCommerceSubscriptionEntryId_PrevAndNext(
		Session session,
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry,
		long commerceSubscriptionEntryId,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONCYCLEENTRY_WHERE);

		query.append(_FINDER_COLUMN_COMMERCESUBSCRIPTIONENTRYID_COMMERCESUBSCRIPTIONENTRYID_2);

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
			query.append(CommerceSubscriptionCycleEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceSubscriptionEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceSubscriptionCycleEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceSubscriptionCycleEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce subscription cycle entries where commerceSubscriptionEntryId = &#63; from the database.
	 *
	 * @param commerceSubscriptionEntryId the commerce subscription entry ID
	 */
	@Override
	public void removeByCommerceSubscriptionEntryId(
		long commerceSubscriptionEntryId) {
		for (CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry : findByCommerceSubscriptionEntryId(
				commerceSubscriptionEntryId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(commerceSubscriptionCycleEntry);
		}
	}

	/**
	 * Returns the number of commerce subscription cycle entries where commerceSubscriptionEntryId = &#63;.
	 *
	 * @param commerceSubscriptionEntryId the commerce subscription entry ID
	 * @return the number of matching commerce subscription cycle entries
	 */
	@Override
	public int countByCommerceSubscriptionEntryId(
		long commerceSubscriptionEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMMERCESUBSCRIPTIONENTRYID;

		Object[] finderArgs = new Object[] { commerceSubscriptionEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCESUBSCRIPTIONCYCLEENTRY_WHERE);

			query.append(_FINDER_COLUMN_COMMERCESUBSCRIPTIONENTRYID_COMMERCESUBSCRIPTIONENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceSubscriptionEntryId);

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

	private static final String _FINDER_COLUMN_COMMERCESUBSCRIPTIONENTRYID_COMMERCESUBSCRIPTIONENTRYID_2 =
		"commerceSubscriptionCycleEntry.commerceSubscriptionEntryId = ?";

	public CommerceSubscriptionCycleEntryPersistenceImpl() {
		setModelClass(CommerceSubscriptionCycleEntry.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("commerceSubscriptionCycleEntryId",
				"CSubscriptionCycleEntryId");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the commerce subscription cycle entry in the entity cache if it is enabled.
	 *
	 * @param commerceSubscriptionCycleEntry the commerce subscription cycle entry
	 */
	@Override
	public void cacheResult(
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry) {
		entityCache.putResult(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryImpl.class,
			commerceSubscriptionCycleEntry.getPrimaryKey(),
			commerceSubscriptionCycleEntry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				commerceSubscriptionCycleEntry.getUuid(),
				commerceSubscriptionCycleEntry.getGroupId()
			}, commerceSubscriptionCycleEntry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_COMMERCEORDERITEMID,
			new Object[] { commerceSubscriptionCycleEntry.getCommerceOrderItemId() },
			commerceSubscriptionCycleEntry);

		commerceSubscriptionCycleEntry.resetOriginalValues();
	}

	/**
	 * Caches the commerce subscription cycle entries in the entity cache if it is enabled.
	 *
	 * @param commerceSubscriptionCycleEntries the commerce subscription cycle entries
	 */
	@Override
	public void cacheResult(
		List<CommerceSubscriptionCycleEntry> commerceSubscriptionCycleEntries) {
		for (CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry : commerceSubscriptionCycleEntries) {
			if (entityCache.getResult(
						CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
						CommerceSubscriptionCycleEntryImpl.class,
						commerceSubscriptionCycleEntry.getPrimaryKey()) == null) {
				cacheResult(commerceSubscriptionCycleEntry);
			}
			else {
				commerceSubscriptionCycleEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce subscription cycle entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceSubscriptionCycleEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce subscription cycle entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry) {
		entityCache.removeResult(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryImpl.class,
			commerceSubscriptionCycleEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CommerceSubscriptionCycleEntryModelImpl)commerceSubscriptionCycleEntry,
			true);
	}

	@Override
	public void clearCache(
		List<CommerceSubscriptionCycleEntry> commerceSubscriptionCycleEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry : commerceSubscriptionCycleEntries) {
			entityCache.removeResult(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
				CommerceSubscriptionCycleEntryImpl.class,
				commerceSubscriptionCycleEntry.getPrimaryKey());

			clearUniqueFindersCache((CommerceSubscriptionCycleEntryModelImpl)commerceSubscriptionCycleEntry,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		CommerceSubscriptionCycleEntryModelImpl commerceSubscriptionCycleEntryModelImpl) {
		Object[] args = new Object[] {
				commerceSubscriptionCycleEntryModelImpl.getUuid(),
				commerceSubscriptionCycleEntryModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			commerceSubscriptionCycleEntryModelImpl, false);

		args = new Object[] {
				commerceSubscriptionCycleEntryModelImpl.getCommerceOrderItemId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_COMMERCEORDERITEMID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_COMMERCEORDERITEMID, args,
			commerceSubscriptionCycleEntryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CommerceSubscriptionCycleEntryModelImpl commerceSubscriptionCycleEntryModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					commerceSubscriptionCycleEntryModelImpl.getUuid(),
					commerceSubscriptionCycleEntryModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((commerceSubscriptionCycleEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					commerceSubscriptionCycleEntryModelImpl.getOriginalUuid(),
					commerceSubscriptionCycleEntryModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					commerceSubscriptionCycleEntryModelImpl.getCommerceOrderItemId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEORDERITEMID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_COMMERCEORDERITEMID,
				args);
		}

		if ((commerceSubscriptionCycleEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_COMMERCEORDERITEMID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					commerceSubscriptionCycleEntryModelImpl.getOriginalCommerceOrderItemId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEORDERITEMID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_COMMERCEORDERITEMID,
				args);
		}
	}

	/**
	 * Creates a new commerce subscription cycle entry with the primary key. Does not add the commerce subscription cycle entry to the database.
	 *
	 * @param commerceSubscriptionCycleEntryId the primary key for the new commerce subscription cycle entry
	 * @return the new commerce subscription cycle entry
	 */
	@Override
	public CommerceSubscriptionCycleEntry create(
		long commerceSubscriptionCycleEntryId) {
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = new CommerceSubscriptionCycleEntryImpl();

		commerceSubscriptionCycleEntry.setNew(true);
		commerceSubscriptionCycleEntry.setPrimaryKey(commerceSubscriptionCycleEntryId);

		String uuid = PortalUUIDUtil.generate();

		commerceSubscriptionCycleEntry.setUuid(uuid);

		commerceSubscriptionCycleEntry.setCompanyId(companyProvider.getCompanyId());

		return commerceSubscriptionCycleEntry;
	}

	/**
	 * Removes the commerce subscription cycle entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceSubscriptionCycleEntryId the primary key of the commerce subscription cycle entry
	 * @return the commerce subscription cycle entry that was removed
	 * @throws NoSuchSubscriptionCycleEntryException if a commerce subscription cycle entry with the primary key could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry remove(
		long commerceSubscriptionCycleEntryId)
		throws NoSuchSubscriptionCycleEntryException {
		return remove((Serializable)commerceSubscriptionCycleEntryId);
	}

	/**
	 * Removes the commerce subscription cycle entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce subscription cycle entry
	 * @return the commerce subscription cycle entry that was removed
	 * @throws NoSuchSubscriptionCycleEntryException if a commerce subscription cycle entry with the primary key could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry remove(Serializable primaryKey)
		throws NoSuchSubscriptionCycleEntryException {
		Session session = null;

		try {
			session = openSession();

			CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = (CommerceSubscriptionCycleEntry)session.get(CommerceSubscriptionCycleEntryImpl.class,
					primaryKey);

			if (commerceSubscriptionCycleEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSubscriptionCycleEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commerceSubscriptionCycleEntry);
		}
		catch (NoSuchSubscriptionCycleEntryException nsee) {
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
	protected CommerceSubscriptionCycleEntry removeImpl(
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceSubscriptionCycleEntry)) {
				commerceSubscriptionCycleEntry = (CommerceSubscriptionCycleEntry)session.get(CommerceSubscriptionCycleEntryImpl.class,
						commerceSubscriptionCycleEntry.getPrimaryKeyObj());
			}

			if (commerceSubscriptionCycleEntry != null) {
				session.delete(commerceSubscriptionCycleEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceSubscriptionCycleEntry != null) {
			clearCache(commerceSubscriptionCycleEntry);
		}

		return commerceSubscriptionCycleEntry;
	}

	@Override
	public CommerceSubscriptionCycleEntry updateImpl(
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry) {
		boolean isNew = commerceSubscriptionCycleEntry.isNew();

		if (!(commerceSubscriptionCycleEntry instanceof CommerceSubscriptionCycleEntryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(
						commerceSubscriptionCycleEntry.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(commerceSubscriptionCycleEntry);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceSubscriptionCycleEntry proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceSubscriptionCycleEntry implementation " +
				commerceSubscriptionCycleEntry.getClass());
		}

		CommerceSubscriptionCycleEntryModelImpl commerceSubscriptionCycleEntryModelImpl =
			(CommerceSubscriptionCycleEntryModelImpl)commerceSubscriptionCycleEntry;

		if (Validator.isNull(commerceSubscriptionCycleEntry.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			commerceSubscriptionCycleEntry.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceSubscriptionCycleEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceSubscriptionCycleEntry.setCreateDate(now);
			}
			else {
				commerceSubscriptionCycleEntry.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!commerceSubscriptionCycleEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceSubscriptionCycleEntry.setModifiedDate(now);
			}
			else {
				commerceSubscriptionCycleEntry.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceSubscriptionCycleEntry.isNew()) {
				session.save(commerceSubscriptionCycleEntry);

				commerceSubscriptionCycleEntry.setNew(false);
			}
			else {
				commerceSubscriptionCycleEntry = (CommerceSubscriptionCycleEntry)session.merge(commerceSubscriptionCycleEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceSubscriptionCycleEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					commerceSubscriptionCycleEntryModelImpl.getUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					commerceSubscriptionCycleEntryModelImpl.getUuid(),
					commerceSubscriptionCycleEntryModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					commerceSubscriptionCycleEntryModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] {
					commerceSubscriptionCycleEntryModelImpl.getCommerceSubscriptionEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCESUBSCRIPTIONENTRYID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCESUBSCRIPTIONENTRYID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((commerceSubscriptionCycleEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceSubscriptionCycleEntryModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] {
						commerceSubscriptionCycleEntryModelImpl.getUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((commerceSubscriptionCycleEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceSubscriptionCycleEntryModelImpl.getOriginalUuid(),
						commerceSubscriptionCycleEntryModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						commerceSubscriptionCycleEntryModelImpl.getUuid(),
						commerceSubscriptionCycleEntryModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((commerceSubscriptionCycleEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceSubscriptionCycleEntryModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] {
						commerceSubscriptionCycleEntryModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((commerceSubscriptionCycleEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCESUBSCRIPTIONENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceSubscriptionCycleEntryModelImpl.getOriginalCommerceSubscriptionEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCESUBSCRIPTIONENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCESUBSCRIPTIONENTRYID,
					args);

				args = new Object[] {
						commerceSubscriptionCycleEntryModelImpl.getCommerceSubscriptionEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCESUBSCRIPTIONENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCESUBSCRIPTIONENTRYID,
					args);
			}
		}

		entityCache.putResult(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceSubscriptionCycleEntryImpl.class,
			commerceSubscriptionCycleEntry.getPrimaryKey(),
			commerceSubscriptionCycleEntry, false);

		clearUniqueFindersCache(commerceSubscriptionCycleEntryModelImpl, false);
		cacheUniqueFindersCache(commerceSubscriptionCycleEntryModelImpl);

		commerceSubscriptionCycleEntry.resetOriginalValues();

		return commerceSubscriptionCycleEntry;
	}

	/**
	 * Returns the commerce subscription cycle entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce subscription cycle entry
	 * @return the commerce subscription cycle entry
	 * @throws NoSuchSubscriptionCycleEntryException if a commerce subscription cycle entry with the primary key could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry findByPrimaryKey(
		Serializable primaryKey) throws NoSuchSubscriptionCycleEntryException {
		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = fetchByPrimaryKey(primaryKey);

		if (commerceSubscriptionCycleEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSubscriptionCycleEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commerceSubscriptionCycleEntry;
	}

	/**
	 * Returns the commerce subscription cycle entry with the primary key or throws a {@link NoSuchSubscriptionCycleEntryException} if it could not be found.
	 *
	 * @param commerceSubscriptionCycleEntryId the primary key of the commerce subscription cycle entry
	 * @return the commerce subscription cycle entry
	 * @throws NoSuchSubscriptionCycleEntryException if a commerce subscription cycle entry with the primary key could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry findByPrimaryKey(
		long commerceSubscriptionCycleEntryId)
		throws NoSuchSubscriptionCycleEntryException {
		return findByPrimaryKey((Serializable)commerceSubscriptionCycleEntryId);
	}

	/**
	 * Returns the commerce subscription cycle entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce subscription cycle entry
	 * @return the commerce subscription cycle entry, or <code>null</code> if a commerce subscription cycle entry with the primary key could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry fetchByPrimaryKey(
		Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
				CommerceSubscriptionCycleEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = (CommerceSubscriptionCycleEntry)serializable;

		if (commerceSubscriptionCycleEntry == null) {
			Session session = null;

			try {
				session = openSession();

				commerceSubscriptionCycleEntry = (CommerceSubscriptionCycleEntry)session.get(CommerceSubscriptionCycleEntryImpl.class,
						primaryKey);

				if (commerceSubscriptionCycleEntry != null) {
					cacheResult(commerceSubscriptionCycleEntry);
				}
				else {
					entityCache.putResult(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
						CommerceSubscriptionCycleEntryImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
					CommerceSubscriptionCycleEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceSubscriptionCycleEntry;
	}

	/**
	 * Returns the commerce subscription cycle entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceSubscriptionCycleEntryId the primary key of the commerce subscription cycle entry
	 * @return the commerce subscription cycle entry, or <code>null</code> if a commerce subscription cycle entry with the primary key could not be found
	 */
	@Override
	public CommerceSubscriptionCycleEntry fetchByPrimaryKey(
		long commerceSubscriptionCycleEntryId) {
		return fetchByPrimaryKey((Serializable)commerceSubscriptionCycleEntryId);
	}

	@Override
	public Map<Serializable, CommerceSubscriptionCycleEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceSubscriptionCycleEntry> map = new HashMap<Serializable, CommerceSubscriptionCycleEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = fetchByPrimaryKey(primaryKey);

			if (commerceSubscriptionCycleEntry != null) {
				map.put(primaryKey, commerceSubscriptionCycleEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
					CommerceSubscriptionCycleEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey,
						(CommerceSubscriptionCycleEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONCYCLEENTRY_WHERE_PKS_IN);

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

			for (CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry : (List<CommerceSubscriptionCycleEntry>)q.list()) {
				map.put(commerceSubscriptionCycleEntry.getPrimaryKeyObj(),
					commerceSubscriptionCycleEntry);

				cacheResult(commerceSubscriptionCycleEntry);

				uncachedPrimaryKeys.remove(commerceSubscriptionCycleEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CommerceSubscriptionCycleEntryModelImpl.ENTITY_CACHE_ENABLED,
					CommerceSubscriptionCycleEntryImpl.class, primaryKey,
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
	 * Returns all the commerce subscription cycle entries.
	 *
	 * @return the commerce subscription cycle entries
	 */
	@Override
	public List<CommerceSubscriptionCycleEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce subscription cycle entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce subscription cycle entries
	 * @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	 * @return the range of commerce subscription cycle entries
	 */
	@Override
	public List<CommerceSubscriptionCycleEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce subscription cycle entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce subscription cycle entries
	 * @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce subscription cycle entries
	 */
	@Override
	public List<CommerceSubscriptionCycleEntry> findAll(int start, int end,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce subscription cycle entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceSubscriptionCycleEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce subscription cycle entries
	 * @param end the upper bound of the range of commerce subscription cycle entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce subscription cycle entries
	 */
	@Override
	public List<CommerceSubscriptionCycleEntry> findAll(int start, int end,
		OrderByComparator<CommerceSubscriptionCycleEntry> orderByComparator,
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

		List<CommerceSubscriptionCycleEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceSubscriptionCycleEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCESUBSCRIPTIONCYCLEENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCESUBSCRIPTIONCYCLEENTRY;

				if (pagination) {
					sql = sql.concat(CommerceSubscriptionCycleEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceSubscriptionCycleEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceSubscriptionCycleEntry>)QueryUtil.list(q,
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
	 * Removes all the commerce subscription cycle entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry : findAll()) {
			remove(commerceSubscriptionCycleEntry);
		}
	}

	/**
	 * Returns the number of commerce subscription cycle entries.
	 *
	 * @return the number of commerce subscription cycle entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCESUBSCRIPTIONCYCLEENTRY);

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
		return CommerceSubscriptionCycleEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce subscription cycle entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CommerceSubscriptionCycleEntryImpl.class.getName());
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
	private static final String _SQL_SELECT_COMMERCESUBSCRIPTIONCYCLEENTRY = "SELECT commerceSubscriptionCycleEntry FROM CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry";
	private static final String _SQL_SELECT_COMMERCESUBSCRIPTIONCYCLEENTRY_WHERE_PKS_IN =
		"SELECT commerceSubscriptionCycleEntry FROM CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry WHERE CSubscriptionCycleEntryId IN (";
	private static final String _SQL_SELECT_COMMERCESUBSCRIPTIONCYCLEENTRY_WHERE =
		"SELECT commerceSubscriptionCycleEntry FROM CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry WHERE ";
	private static final String _SQL_COUNT_COMMERCESUBSCRIPTIONCYCLEENTRY = "SELECT COUNT(commerceSubscriptionCycleEntry) FROM CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry";
	private static final String _SQL_COUNT_COMMERCESUBSCRIPTIONCYCLEENTRY_WHERE = "SELECT COUNT(commerceSubscriptionCycleEntry) FROM CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceSubscriptionCycleEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommerceSubscriptionCycleEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CommerceSubscriptionCycleEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CommerceSubscriptionCycleEntryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "commerceSubscriptionCycleEntryId"
			});
}