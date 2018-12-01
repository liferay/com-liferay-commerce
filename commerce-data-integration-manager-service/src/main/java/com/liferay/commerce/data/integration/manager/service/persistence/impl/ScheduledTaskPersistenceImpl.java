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

package com.liferay.commerce.data.integration.manager.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.data.integration.manager.service.persistence.ScheduledTaskUtil;
import com.liferay.commerce.data.integration.manager.exception.NoSuchScheduledTaskException;
import com.liferay.commerce.data.integration.manager.model.ScheduledTask;
import com.liferay.commerce.data.integration.manager.model.impl.ScheduledTaskImpl;
import com.liferay.commerce.data.integration.manager.model.impl.ScheduledTaskModelImpl;
import com.liferay.commerce.data.integration.manager.service.persistence.ScheduledTaskPersistence;

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
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
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
 * The persistence implementation for the scheduled task service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ScheduledTaskPersistence
 * @see ScheduledTaskUtil
 * @generated
 */
@ProviderType
public class ScheduledTaskPersistenceImpl extends BasePersistenceImpl<ScheduledTask>
	implements ScheduledTaskPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ScheduledTaskUtil} to access the scheduled task persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ScheduledTaskImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED,
			ScheduledTaskImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED,
			ScheduledTaskImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED,
			ScheduledTaskImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED,
			ScheduledTaskImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] { String.class.getName() },
			ScheduledTaskModelImpl.UUID_COLUMN_BITMASK |
			ScheduledTaskModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the scheduled tasks where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the scheduled tasks where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @return the range of matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the scheduled tasks where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByUuid(String uuid, int start, int end,
		OrderByComparator<ScheduledTask> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the scheduled tasks where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByUuid(String uuid, int start, int end,
		OrderByComparator<ScheduledTask> orderByComparator,
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

		List<ScheduledTask> list = null;

		if (retrieveFromCache) {
			list = (List<ScheduledTask>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScheduledTask scheduledTask : list) {
					if (!Objects.equals(uuid, scheduledTask.getUuid())) {
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

			query.append(_SQL_SELECT_SCHEDULEDTASK_WHERE);

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
				query.append(ScheduledTaskModelImpl.ORDER_BY_JPQL);
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
					list = (List<ScheduledTask>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScheduledTask>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first scheduled task in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching scheduled task
	 * @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask findByUuid_First(String uuid,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = fetchByUuid_First(uuid, orderByComparator);

		if (scheduledTask != null) {
			return scheduledTask;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchScheduledTaskException(msg.toString());
	}

	/**
	 * Returns the first scheduled task in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask fetchByUuid_First(String uuid,
		OrderByComparator<ScheduledTask> orderByComparator) {
		List<ScheduledTask> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last scheduled task in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching scheduled task
	 * @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask findByUuid_Last(String uuid,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = fetchByUuid_Last(uuid, orderByComparator);

		if (scheduledTask != null) {
			return scheduledTask;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchScheduledTaskException(msg.toString());
	}

	/**
	 * Returns the last scheduled task in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask fetchByUuid_Last(String uuid,
		OrderByComparator<ScheduledTask> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ScheduledTask> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the scheduled tasks before and after the current scheduled task in the ordered set where uuid = &#63;.
	 *
	 * @param scheduledTaskId the primary key of the current scheduled task
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next scheduled task
	 * @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	 */
	@Override
	public ScheduledTask[] findByUuid_PrevAndNext(long scheduledTaskId,
		String uuid, OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = findByPrimaryKey(scheduledTaskId);

		Session session = null;

		try {
			session = openSession();

			ScheduledTask[] array = new ScheduledTaskImpl[3];

			array[0] = getByUuid_PrevAndNext(session, scheduledTask, uuid,
					orderByComparator, true);

			array[1] = scheduledTask;

			array[2] = getByUuid_PrevAndNext(session, scheduledTask, uuid,
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

	protected ScheduledTask getByUuid_PrevAndNext(Session session,
		ScheduledTask scheduledTask, String uuid,
		OrderByComparator<ScheduledTask> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SCHEDULEDTASK_WHERE);

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
			query.append(ScheduledTaskModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(scheduledTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScheduledTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the scheduled tasks where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ScheduledTask scheduledTask : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(scheduledTask);
		}
	}

	/**
	 * Returns the number of scheduled tasks where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching scheduled tasks
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SCHEDULEDTASK_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "scheduledTask.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "scheduledTask.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(scheduledTask.uuid IS NULL OR scheduledTask.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED,
			ScheduledTaskImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ScheduledTaskModelImpl.UUID_COLUMN_BITMASK |
			ScheduledTaskModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the scheduled task where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchScheduledTaskException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching scheduled task
	 * @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask findByUUID_G(String uuid, long groupId)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = fetchByUUID_G(uuid, groupId);

		if (scheduledTask == null) {
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

			throw new NoSuchScheduledTaskException(msg.toString());
		}

		return scheduledTask;
	}

	/**
	 * Returns the scheduled task where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the scheduled task where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof ScheduledTask) {
			ScheduledTask scheduledTask = (ScheduledTask)result;

			if (!Objects.equals(uuid, scheduledTask.getUuid()) ||
					(groupId != scheduledTask.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SCHEDULEDTASK_WHERE);

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

				List<ScheduledTask> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					ScheduledTask scheduledTask = list.get(0);

					result = scheduledTask;

					cacheResult(scheduledTask);
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
			return (ScheduledTask)result;
		}
	}

	/**
	 * Removes the scheduled task where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the scheduled task that was removed
	 */
	@Override
	public ScheduledTask removeByUUID_G(String uuid, long groupId)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = findByUUID_G(uuid, groupId);

		return remove(scheduledTask);
	}

	/**
	 * Returns the number of scheduled tasks where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching scheduled tasks
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SCHEDULEDTASK_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "scheduledTask.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "scheduledTask.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(scheduledTask.uuid IS NULL OR scheduledTask.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "scheduledTask.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED,
			ScheduledTaskImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED,
			ScheduledTaskImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ScheduledTaskModelImpl.UUID_COLUMN_BITMASK |
			ScheduledTaskModelImpl.COMPANYID_COLUMN_BITMASK |
			ScheduledTaskModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the scheduled tasks where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the scheduled tasks where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @return the range of matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the scheduled tasks where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ScheduledTask> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the scheduled tasks where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ScheduledTask> orderByComparator,
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

		List<ScheduledTask> list = null;

		if (retrieveFromCache) {
			list = (List<ScheduledTask>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScheduledTask scheduledTask : list) {
					if (!Objects.equals(uuid, scheduledTask.getUuid()) ||
							(companyId != scheduledTask.getCompanyId())) {
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

			query.append(_SQL_SELECT_SCHEDULEDTASK_WHERE);

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
				query.append(ScheduledTaskModelImpl.ORDER_BY_JPQL);
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
					list = (List<ScheduledTask>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScheduledTask>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first scheduled task in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching scheduled task
	 * @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (scheduledTask != null) {
			return scheduledTask;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchScheduledTaskException(msg.toString());
	}

	/**
	 * Returns the first scheduled task in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ScheduledTask> orderByComparator) {
		List<ScheduledTask> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last scheduled task in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching scheduled task
	 * @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (scheduledTask != null) {
			return scheduledTask;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchScheduledTaskException(msg.toString());
	}

	/**
	 * Returns the last scheduled task in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ScheduledTask> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ScheduledTask> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the scheduled tasks before and after the current scheduled task in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param scheduledTaskId the primary key of the current scheduled task
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next scheduled task
	 * @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	 */
	@Override
	public ScheduledTask[] findByUuid_C_PrevAndNext(long scheduledTaskId,
		String uuid, long companyId,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = findByPrimaryKey(scheduledTaskId);

		Session session = null;

		try {
			session = openSession();

			ScheduledTask[] array = new ScheduledTaskImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, scheduledTask, uuid,
					companyId, orderByComparator, true);

			array[1] = scheduledTask;

			array[2] = getByUuid_C_PrevAndNext(session, scheduledTask, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ScheduledTask getByUuid_C_PrevAndNext(Session session,
		ScheduledTask scheduledTask, String uuid, long companyId,
		OrderByComparator<ScheduledTask> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SCHEDULEDTASK_WHERE);

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
			query.append(ScheduledTaskModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(scheduledTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScheduledTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the scheduled tasks where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ScheduledTask scheduledTask : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scheduledTask);
		}
	}

	/**
	 * Returns the number of scheduled tasks where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching scheduled tasks
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SCHEDULEDTASK_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "scheduledTask.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "scheduledTask.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(scheduledTask.uuid IS NULL OR scheduledTask.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "scheduledTask.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ENABLED = new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED,
			ScheduledTaskImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByEnabled",
			new String[] {
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENABLED =
		new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED,
			ScheduledTaskImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByEnabled", new String[] { Boolean.class.getName() },
			ScheduledTaskModelImpl.ENABLED_COLUMN_BITMASK |
			ScheduledTaskModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ENABLED = new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEnabled",
			new String[] { Boolean.class.getName() });

	/**
	 * Returns all the scheduled tasks where enabled = &#63;.
	 *
	 * @param enabled the enabled
	 * @return the matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByEnabled(boolean enabled) {
		return findByEnabled(enabled, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the scheduled tasks where enabled = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param enabled the enabled
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @return the range of matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByEnabled(boolean enabled, int start, int end) {
		return findByEnabled(enabled, start, end, null);
	}

	/**
	 * Returns an ordered range of all the scheduled tasks where enabled = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param enabled the enabled
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByEnabled(boolean enabled, int start,
		int end, OrderByComparator<ScheduledTask> orderByComparator) {
		return findByEnabled(enabled, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the scheduled tasks where enabled = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param enabled the enabled
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByEnabled(boolean enabled, int start,
		int end, OrderByComparator<ScheduledTask> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENABLED;
			finderArgs = new Object[] { enabled };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ENABLED;
			finderArgs = new Object[] { enabled, start, end, orderByComparator };
		}

		List<ScheduledTask> list = null;

		if (retrieveFromCache) {
			list = (List<ScheduledTask>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScheduledTask scheduledTask : list) {
					if ((enabled != scheduledTask.isEnabled())) {
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

			query.append(_SQL_SELECT_SCHEDULEDTASK_WHERE);

			query.append(_FINDER_COLUMN_ENABLED_ENABLED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScheduledTaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(enabled);

				if (!pagination) {
					list = (List<ScheduledTask>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScheduledTask>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first scheduled task in the ordered set where enabled = &#63;.
	 *
	 * @param enabled the enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching scheduled task
	 * @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask findByEnabled_First(boolean enabled,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = fetchByEnabled_First(enabled,
				orderByComparator);

		if (scheduledTask != null) {
			return scheduledTask;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("enabled=");
		msg.append(enabled);

		msg.append("}");

		throw new NoSuchScheduledTaskException(msg.toString());
	}

	/**
	 * Returns the first scheduled task in the ordered set where enabled = &#63;.
	 *
	 * @param enabled the enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask fetchByEnabled_First(boolean enabled,
		OrderByComparator<ScheduledTask> orderByComparator) {
		List<ScheduledTask> list = findByEnabled(enabled, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last scheduled task in the ordered set where enabled = &#63;.
	 *
	 * @param enabled the enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching scheduled task
	 * @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask findByEnabled_Last(boolean enabled,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = fetchByEnabled_Last(enabled,
				orderByComparator);

		if (scheduledTask != null) {
			return scheduledTask;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("enabled=");
		msg.append(enabled);

		msg.append("}");

		throw new NoSuchScheduledTaskException(msg.toString());
	}

	/**
	 * Returns the last scheduled task in the ordered set where enabled = &#63;.
	 *
	 * @param enabled the enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask fetchByEnabled_Last(boolean enabled,
		OrderByComparator<ScheduledTask> orderByComparator) {
		int count = countByEnabled(enabled);

		if (count == 0) {
			return null;
		}

		List<ScheduledTask> list = findByEnabled(enabled, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the scheduled tasks before and after the current scheduled task in the ordered set where enabled = &#63;.
	 *
	 * @param scheduledTaskId the primary key of the current scheduled task
	 * @param enabled the enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next scheduled task
	 * @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	 */
	@Override
	public ScheduledTask[] findByEnabled_PrevAndNext(long scheduledTaskId,
		boolean enabled, OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = findByPrimaryKey(scheduledTaskId);

		Session session = null;

		try {
			session = openSession();

			ScheduledTask[] array = new ScheduledTaskImpl[3];

			array[0] = getByEnabled_PrevAndNext(session, scheduledTask,
					enabled, orderByComparator, true);

			array[1] = scheduledTask;

			array[2] = getByEnabled_PrevAndNext(session, scheduledTask,
					enabled, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ScheduledTask getByEnabled_PrevAndNext(Session session,
		ScheduledTask scheduledTask, boolean enabled,
		OrderByComparator<ScheduledTask> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SCHEDULEDTASK_WHERE);

		query.append(_FINDER_COLUMN_ENABLED_ENABLED_2);

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
			query.append(ScheduledTaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(enabled);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scheduledTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScheduledTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the scheduled tasks where enabled = &#63; from the database.
	 *
	 * @param enabled the enabled
	 */
	@Override
	public void removeByEnabled(boolean enabled) {
		for (ScheduledTask scheduledTask : findByEnabled(enabled,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scheduledTask);
		}
	}

	/**
	 * Returns the number of scheduled tasks where enabled = &#63;.
	 *
	 * @param enabled the enabled
	 * @return the number of matching scheduled tasks
	 */
	@Override
	public int countByEnabled(boolean enabled) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ENABLED;

		Object[] finderArgs = new Object[] { enabled };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SCHEDULEDTASK_WHERE);

			query.append(_FINDER_COLUMN_ENABLED_ENABLED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(enabled);

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

	private static final String _FINDER_COLUMN_ENABLED_ENABLED_2 = "scheduledTask.enabled = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVE = new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED,
			ScheduledTaskImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByActive",
			new String[] {
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE =
		new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED,
			ScheduledTaskImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByActive", new String[] { Boolean.class.getName() },
			ScheduledTaskModelImpl.ACTIVE_COLUMN_BITMASK |
			ScheduledTaskModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVE = new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActive",
			new String[] { Boolean.class.getName() });

	/**
	 * Returns all the scheduled tasks where active = &#63;.
	 *
	 * @param active the active
	 * @return the matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByActive(boolean active) {
		return findByActive(active, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the scheduled tasks where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param active the active
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @return the range of matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByActive(boolean active, int start, int end) {
		return findByActive(active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the scheduled tasks where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param active the active
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByActive(boolean active, int start, int end,
		OrderByComparator<ScheduledTask> orderByComparator) {
		return findByActive(active, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the scheduled tasks where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param active the active
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByActive(boolean active, int start, int end,
		OrderByComparator<ScheduledTask> orderByComparator,
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

		List<ScheduledTask> list = null;

		if (retrieveFromCache) {
			list = (List<ScheduledTask>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScheduledTask scheduledTask : list) {
					if ((active != scheduledTask.isActive())) {
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

			query.append(_SQL_SELECT_SCHEDULEDTASK_WHERE);

			query.append(_FINDER_COLUMN_ACTIVE_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScheduledTaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(active);

				if (!pagination) {
					list = (List<ScheduledTask>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScheduledTask>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first scheduled task in the ordered set where active = &#63;.
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching scheduled task
	 * @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask findByActive_First(boolean active,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = fetchByActive_First(active,
				orderByComparator);

		if (scheduledTask != null) {
			return scheduledTask;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("active=");
		msg.append(active);

		msg.append("}");

		throw new NoSuchScheduledTaskException(msg.toString());
	}

	/**
	 * Returns the first scheduled task in the ordered set where active = &#63;.
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask fetchByActive_First(boolean active,
		OrderByComparator<ScheduledTask> orderByComparator) {
		List<ScheduledTask> list = findByActive(active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last scheduled task in the ordered set where active = &#63;.
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching scheduled task
	 * @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask findByActive_Last(boolean active,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = fetchByActive_Last(active,
				orderByComparator);

		if (scheduledTask != null) {
			return scheduledTask;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("active=");
		msg.append(active);

		msg.append("}");

		throw new NoSuchScheduledTaskException(msg.toString());
	}

	/**
	 * Returns the last scheduled task in the ordered set where active = &#63;.
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask fetchByActive_Last(boolean active,
		OrderByComparator<ScheduledTask> orderByComparator) {
		int count = countByActive(active);

		if (count == 0) {
			return null;
		}

		List<ScheduledTask> list = findByActive(active, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the scheduled tasks before and after the current scheduled task in the ordered set where active = &#63;.
	 *
	 * @param scheduledTaskId the primary key of the current scheduled task
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next scheduled task
	 * @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	 */
	@Override
	public ScheduledTask[] findByActive_PrevAndNext(long scheduledTaskId,
		boolean active, OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = findByPrimaryKey(scheduledTaskId);

		Session session = null;

		try {
			session = openSession();

			ScheduledTask[] array = new ScheduledTaskImpl[3];

			array[0] = getByActive_PrevAndNext(session, scheduledTask, active,
					orderByComparator, true);

			array[1] = scheduledTask;

			array[2] = getByActive_PrevAndNext(session, scheduledTask, active,
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

	protected ScheduledTask getByActive_PrevAndNext(Session session,
		ScheduledTask scheduledTask, boolean active,
		OrderByComparator<ScheduledTask> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SCHEDULEDTASK_WHERE);

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
			query.append(ScheduledTaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scheduledTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScheduledTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the scheduled tasks where active = &#63; from the database.
	 *
	 * @param active the active
	 */
	@Override
	public void removeByActive(boolean active) {
		for (ScheduledTask scheduledTask : findByActive(active,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scheduledTask);
		}
	}

	/**
	 * Returns the number of scheduled tasks where active = &#63;.
	 *
	 * @param active the active
	 * @return the number of matching scheduled tasks
	 */
	@Override
	public int countByActive(boolean active) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVE;

		Object[] finderArgs = new Object[] { active };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SCHEDULEDTASK_WHERE);

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

	private static final String _FINDER_COLUMN_ACTIVE_ACTIVE_2 = "scheduledTask.active = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED,
			ScheduledTaskImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED,
			ScheduledTaskImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupId", new String[] { Long.class.getName() },
			ScheduledTaskModelImpl.GROUPID_COLUMN_BITMASK |
			ScheduledTaskModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the scheduled tasks where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the scheduled tasks where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @return the range of matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the scheduled tasks where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByGroupId(long groupId, int start, int end,
		OrderByComparator<ScheduledTask> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the scheduled tasks where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByGroupId(long groupId, int start, int end,
		OrderByComparator<ScheduledTask> orderByComparator,
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

		List<ScheduledTask> list = null;

		if (retrieveFromCache) {
			list = (List<ScheduledTask>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScheduledTask scheduledTask : list) {
					if ((groupId != scheduledTask.getGroupId())) {
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

			query.append(_SQL_SELECT_SCHEDULEDTASK_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScheduledTaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<ScheduledTask>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScheduledTask>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first scheduled task in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching scheduled task
	 * @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask findByGroupId_First(long groupId,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = fetchByGroupId_First(groupId,
				orderByComparator);

		if (scheduledTask != null) {
			return scheduledTask;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchScheduledTaskException(msg.toString());
	}

	/**
	 * Returns the first scheduled task in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask fetchByGroupId_First(long groupId,
		OrderByComparator<ScheduledTask> orderByComparator) {
		List<ScheduledTask> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last scheduled task in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching scheduled task
	 * @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask findByGroupId_Last(long groupId,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (scheduledTask != null) {
			return scheduledTask;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchScheduledTaskException(msg.toString());
	}

	/**
	 * Returns the last scheduled task in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask fetchByGroupId_Last(long groupId,
		OrderByComparator<ScheduledTask> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<ScheduledTask> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the scheduled tasks before and after the current scheduled task in the ordered set where groupId = &#63;.
	 *
	 * @param scheduledTaskId the primary key of the current scheduled task
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next scheduled task
	 * @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	 */
	@Override
	public ScheduledTask[] findByGroupId_PrevAndNext(long scheduledTaskId,
		long groupId, OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = findByPrimaryKey(scheduledTaskId);

		Session session = null;

		try {
			session = openSession();

			ScheduledTask[] array = new ScheduledTaskImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, scheduledTask,
					groupId, orderByComparator, true);

			array[1] = scheduledTask;

			array[2] = getByGroupId_PrevAndNext(session, scheduledTask,
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

	protected ScheduledTask getByGroupId_PrevAndNext(Session session,
		ScheduledTask scheduledTask, long groupId,
		OrderByComparator<ScheduledTask> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SCHEDULEDTASK_WHERE);

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
			query.append(ScheduledTaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scheduledTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScheduledTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the scheduled tasks that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching scheduled tasks that the user has permission to view
	 */
	@Override
	public List<ScheduledTask> filterFindByGroupId(long groupId) {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the scheduled tasks that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @return the range of matching scheduled tasks that the user has permission to view
	 */
	@Override
	public List<ScheduledTask> filterFindByGroupId(long groupId, int start,
		int end) {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the scheduled tasks that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching scheduled tasks that the user has permission to view
	 */
	@Override
	public List<ScheduledTask> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator<ScheduledTask> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId(groupId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_SCHEDULEDTASK_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_SCHEDULEDTASK_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_SCHEDULEDTASK_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ScheduledTaskModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ScheduledTaskModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ScheduledTask.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ScheduledTaskImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ScheduledTaskImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<ScheduledTask>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the scheduled tasks before and after the current scheduled task in the ordered set of scheduled tasks that the user has permission to view where groupId = &#63;.
	 *
	 * @param scheduledTaskId the primary key of the current scheduled task
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next scheduled task
	 * @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	 */
	@Override
	public ScheduledTask[] filterFindByGroupId_PrevAndNext(
		long scheduledTaskId, long groupId,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(scheduledTaskId, groupId,
				orderByComparator);
		}

		ScheduledTask scheduledTask = findByPrimaryKey(scheduledTaskId);

		Session session = null;

		try {
			session = openSession();

			ScheduledTask[] array = new ScheduledTaskImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session, scheduledTask,
					groupId, orderByComparator, true);

			array[1] = scheduledTask;

			array[2] = filterGetByGroupId_PrevAndNext(session, scheduledTask,
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

	protected ScheduledTask filterGetByGroupId_PrevAndNext(Session session,
		ScheduledTask scheduledTask, long groupId,
		OrderByComparator<ScheduledTask> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_SCHEDULEDTASK_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_SCHEDULEDTASK_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_SCHEDULEDTASK_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ScheduledTaskModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ScheduledTaskModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ScheduledTask.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ScheduledTaskImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ScheduledTaskImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scheduledTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScheduledTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the scheduled tasks where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (ScheduledTask scheduledTask : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scheduledTask);
		}
	}

	/**
	 * Returns the number of scheduled tasks where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching scheduled tasks
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SCHEDULEDTASK_WHERE);

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

	/**
	 * Returns the number of scheduled tasks that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching scheduled tasks that the user has permission to view
	 */
	@Override
	public int filterCountByGroupId(long groupId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_SCHEDULEDTASK_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ScheduledTask.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "scheduledTask.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID_ACTIVE =
		new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED,
			ScheduledTaskImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId_Active",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID_ACTIVE =
		new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED,
			ScheduledTaskImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupId_Active",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			ScheduledTaskModelImpl.GROUPID_COLUMN_BITMASK |
			ScheduledTaskModelImpl.ACTIVE_COLUMN_BITMASK |
			ScheduledTaskModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID_ACTIVE = new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId_Active",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the scheduled tasks where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @return the matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByGroupId_Active(long groupId, boolean active) {
		return findByGroupId_Active(groupId, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the scheduled tasks where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @return the range of matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByGroupId_Active(long groupId,
		boolean active, int start, int end) {
		return findByGroupId_Active(groupId, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the scheduled tasks where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByGroupId_Active(long groupId,
		boolean active, int start, int end,
		OrderByComparator<ScheduledTask> orderByComparator) {
		return findByGroupId_Active(groupId, active, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the scheduled tasks where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByGroupId_Active(long groupId,
		boolean active, int start, int end,
		OrderByComparator<ScheduledTask> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID_ACTIVE;
			finderArgs = new Object[] { groupId, active };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID_ACTIVE;
			finderArgs = new Object[] {
					groupId, active,
					
					start, end, orderByComparator
				};
		}

		List<ScheduledTask> list = null;

		if (retrieveFromCache) {
			list = (List<ScheduledTask>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScheduledTask scheduledTask : list) {
					if ((groupId != scheduledTask.getGroupId()) ||
							(active != scheduledTask.isActive())) {
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

			query.append(_SQL_SELECT_SCHEDULEDTASK_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_ACTIVE_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPID_ACTIVE_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScheduledTaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(active);

				if (!pagination) {
					list = (List<ScheduledTask>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScheduledTask>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first scheduled task in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching scheduled task
	 * @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask findByGroupId_Active_First(long groupId,
		boolean active, OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = fetchByGroupId_Active_First(groupId,
				active, orderByComparator);

		if (scheduledTask != null) {
			return scheduledTask;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", active=");
		msg.append(active);

		msg.append("}");

		throw new NoSuchScheduledTaskException(msg.toString());
	}

	/**
	 * Returns the first scheduled task in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask fetchByGroupId_Active_First(long groupId,
		boolean active, OrderByComparator<ScheduledTask> orderByComparator) {
		List<ScheduledTask> list = findByGroupId_Active(groupId, active, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last scheduled task in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching scheduled task
	 * @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask findByGroupId_Active_Last(long groupId,
		boolean active, OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = fetchByGroupId_Active_Last(groupId,
				active, orderByComparator);

		if (scheduledTask != null) {
			return scheduledTask;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", active=");
		msg.append(active);

		msg.append("}");

		throw new NoSuchScheduledTaskException(msg.toString());
	}

	/**
	 * Returns the last scheduled task in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask fetchByGroupId_Active_Last(long groupId,
		boolean active, OrderByComparator<ScheduledTask> orderByComparator) {
		int count = countByGroupId_Active(groupId, active);

		if (count == 0) {
			return null;
		}

		List<ScheduledTask> list = findByGroupId_Active(groupId, active,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the scheduled tasks before and after the current scheduled task in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param scheduledTaskId the primary key of the current scheduled task
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next scheduled task
	 * @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	 */
	@Override
	public ScheduledTask[] findByGroupId_Active_PrevAndNext(
		long scheduledTaskId, long groupId, boolean active,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = findByPrimaryKey(scheduledTaskId);

		Session session = null;

		try {
			session = openSession();

			ScheduledTask[] array = new ScheduledTaskImpl[3];

			array[0] = getByGroupId_Active_PrevAndNext(session, scheduledTask,
					groupId, active, orderByComparator, true);

			array[1] = scheduledTask;

			array[2] = getByGroupId_Active_PrevAndNext(session, scheduledTask,
					groupId, active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ScheduledTask getByGroupId_Active_PrevAndNext(Session session,
		ScheduledTask scheduledTask, long groupId, boolean active,
		OrderByComparator<ScheduledTask> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SCHEDULEDTASK_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_ACTIVE_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPID_ACTIVE_ACTIVE_2);

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
			query.append(ScheduledTaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scheduledTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScheduledTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the scheduled tasks that the user has permission to view where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @return the matching scheduled tasks that the user has permission to view
	 */
	@Override
	public List<ScheduledTask> filterFindByGroupId_Active(long groupId,
		boolean active) {
		return filterFindByGroupId_Active(groupId, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the scheduled tasks that the user has permission to view where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @return the range of matching scheduled tasks that the user has permission to view
	 */
	@Override
	public List<ScheduledTask> filterFindByGroupId_Active(long groupId,
		boolean active, int start, int end) {
		return filterFindByGroupId_Active(groupId, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the scheduled tasks that the user has permissions to view where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching scheduled tasks that the user has permission to view
	 */
	@Override
	public List<ScheduledTask> filterFindByGroupId_Active(long groupId,
		boolean active, int start, int end,
		OrderByComparator<ScheduledTask> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_Active(groupId, active, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_SCHEDULEDTASK_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_SCHEDULEDTASK_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_ACTIVE_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPID_ACTIVE_ACTIVE_2_SQL);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_SCHEDULEDTASK_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ScheduledTaskModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ScheduledTaskModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ScheduledTask.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ScheduledTaskImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ScheduledTaskImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(active);

			return (List<ScheduledTask>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the scheduled tasks before and after the current scheduled task in the ordered set of scheduled tasks that the user has permission to view where groupId = &#63; and active = &#63;.
	 *
	 * @param scheduledTaskId the primary key of the current scheduled task
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next scheduled task
	 * @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	 */
	@Override
	public ScheduledTask[] filterFindByGroupId_Active_PrevAndNext(
		long scheduledTaskId, long groupId, boolean active,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_Active_PrevAndNext(scheduledTaskId, groupId,
				active, orderByComparator);
		}

		ScheduledTask scheduledTask = findByPrimaryKey(scheduledTaskId);

		Session session = null;

		try {
			session = openSession();

			ScheduledTask[] array = new ScheduledTaskImpl[3];

			array[0] = filterGetByGroupId_Active_PrevAndNext(session,
					scheduledTask, groupId, active, orderByComparator, true);

			array[1] = scheduledTask;

			array[2] = filterGetByGroupId_Active_PrevAndNext(session,
					scheduledTask, groupId, active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ScheduledTask filterGetByGroupId_Active_PrevAndNext(
		Session session, ScheduledTask scheduledTask, long groupId,
		boolean active, OrderByComparator<ScheduledTask> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_SCHEDULEDTASK_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_SCHEDULEDTASK_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_ACTIVE_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPID_ACTIVE_ACTIVE_2_SQL);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_SCHEDULEDTASK_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ScheduledTaskModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ScheduledTaskModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ScheduledTask.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ScheduledTaskImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ScheduledTaskImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scheduledTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScheduledTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the scheduled tasks where groupId = &#63; and active = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 */
	@Override
	public void removeByGroupId_Active(long groupId, boolean active) {
		for (ScheduledTask scheduledTask : findByGroupId_Active(groupId,
				active, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scheduledTask);
		}
	}

	/**
	 * Returns the number of scheduled tasks where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @return the number of matching scheduled tasks
	 */
	@Override
	public int countByGroupId_Active(long groupId, boolean active) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID_ACTIVE;

		Object[] finderArgs = new Object[] { groupId, active };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SCHEDULEDTASK_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_ACTIVE_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPID_ACTIVE_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	/**
	 * Returns the number of scheduled tasks that the user has permission to view where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @return the number of matching scheduled tasks that the user has permission to view
	 */
	@Override
	public int filterCountByGroupId_Active(long groupId, boolean active) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId_Active(groupId, active);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_SCHEDULEDTASK_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_ACTIVE_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPID_ACTIVE_ACTIVE_2_SQL);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ScheduledTask.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(active);

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

	private static final String _FINDER_COLUMN_GROUPID_ACTIVE_GROUPID_2 = "scheduledTask.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPID_ACTIVE_ACTIVE_2 = "scheduledTask.active = ?";
	private static final String _FINDER_COLUMN_GROUPID_ACTIVE_ACTIVE_2_SQL = "scheduledTask.active_ = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID_ENABLED =
		new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED,
			ScheduledTaskImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId_Enabled",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID_ENABLED =
		new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED,
			ScheduledTaskImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupId_Enabled",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			ScheduledTaskModelImpl.GROUPID_COLUMN_BITMASK |
			ScheduledTaskModelImpl.ENABLED_COLUMN_BITMASK |
			ScheduledTaskModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID_ENABLED = new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupId_Enabled",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the scheduled tasks where groupId = &#63; and enabled = &#63;.
	 *
	 * @param groupId the group ID
	 * @param enabled the enabled
	 * @return the matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByGroupId_Enabled(long groupId,
		boolean enabled) {
		return findByGroupId_Enabled(groupId, enabled, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the scheduled tasks where groupId = &#63; and enabled = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param enabled the enabled
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @return the range of matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByGroupId_Enabled(long groupId,
		boolean enabled, int start, int end) {
		return findByGroupId_Enabled(groupId, enabled, start, end, null);
	}

	/**
	 * Returns an ordered range of all the scheduled tasks where groupId = &#63; and enabled = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param enabled the enabled
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByGroupId_Enabled(long groupId,
		boolean enabled, int start, int end,
		OrderByComparator<ScheduledTask> orderByComparator) {
		return findByGroupId_Enabled(groupId, enabled, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the scheduled tasks where groupId = &#63; and enabled = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param enabled the enabled
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByGroupId_Enabled(long groupId,
		boolean enabled, int start, int end,
		OrderByComparator<ScheduledTask> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID_ENABLED;
			finderArgs = new Object[] { groupId, enabled };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID_ENABLED;
			finderArgs = new Object[] {
					groupId, enabled,
					
					start, end, orderByComparator
				};
		}

		List<ScheduledTask> list = null;

		if (retrieveFromCache) {
			list = (List<ScheduledTask>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScheduledTask scheduledTask : list) {
					if ((groupId != scheduledTask.getGroupId()) ||
							(enabled != scheduledTask.isEnabled())) {
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

			query.append(_SQL_SELECT_SCHEDULEDTASK_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_ENABLED_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPID_ENABLED_ENABLED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScheduledTaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(enabled);

				if (!pagination) {
					list = (List<ScheduledTask>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScheduledTask>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first scheduled task in the ordered set where groupId = &#63; and enabled = &#63;.
	 *
	 * @param groupId the group ID
	 * @param enabled the enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching scheduled task
	 * @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask findByGroupId_Enabled_First(long groupId,
		boolean enabled, OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = fetchByGroupId_Enabled_First(groupId,
				enabled, orderByComparator);

		if (scheduledTask != null) {
			return scheduledTask;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", enabled=");
		msg.append(enabled);

		msg.append("}");

		throw new NoSuchScheduledTaskException(msg.toString());
	}

	/**
	 * Returns the first scheduled task in the ordered set where groupId = &#63; and enabled = &#63;.
	 *
	 * @param groupId the group ID
	 * @param enabled the enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask fetchByGroupId_Enabled_First(long groupId,
		boolean enabled, OrderByComparator<ScheduledTask> orderByComparator) {
		List<ScheduledTask> list = findByGroupId_Enabled(groupId, enabled, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last scheduled task in the ordered set where groupId = &#63; and enabled = &#63;.
	 *
	 * @param groupId the group ID
	 * @param enabled the enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching scheduled task
	 * @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask findByGroupId_Enabled_Last(long groupId,
		boolean enabled, OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = fetchByGroupId_Enabled_Last(groupId,
				enabled, orderByComparator);

		if (scheduledTask != null) {
			return scheduledTask;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", enabled=");
		msg.append(enabled);

		msg.append("}");

		throw new NoSuchScheduledTaskException(msg.toString());
	}

	/**
	 * Returns the last scheduled task in the ordered set where groupId = &#63; and enabled = &#63;.
	 *
	 * @param groupId the group ID
	 * @param enabled the enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask fetchByGroupId_Enabled_Last(long groupId,
		boolean enabled, OrderByComparator<ScheduledTask> orderByComparator) {
		int count = countByGroupId_Enabled(groupId, enabled);

		if (count == 0) {
			return null;
		}

		List<ScheduledTask> list = findByGroupId_Enabled(groupId, enabled,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the scheduled tasks before and after the current scheduled task in the ordered set where groupId = &#63; and enabled = &#63;.
	 *
	 * @param scheduledTaskId the primary key of the current scheduled task
	 * @param groupId the group ID
	 * @param enabled the enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next scheduled task
	 * @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	 */
	@Override
	public ScheduledTask[] findByGroupId_Enabled_PrevAndNext(
		long scheduledTaskId, long groupId, boolean enabled,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = findByPrimaryKey(scheduledTaskId);

		Session session = null;

		try {
			session = openSession();

			ScheduledTask[] array = new ScheduledTaskImpl[3];

			array[0] = getByGroupId_Enabled_PrevAndNext(session, scheduledTask,
					groupId, enabled, orderByComparator, true);

			array[1] = scheduledTask;

			array[2] = getByGroupId_Enabled_PrevAndNext(session, scheduledTask,
					groupId, enabled, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ScheduledTask getByGroupId_Enabled_PrevAndNext(Session session,
		ScheduledTask scheduledTask, long groupId, boolean enabled,
		OrderByComparator<ScheduledTask> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SCHEDULEDTASK_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_ENABLED_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPID_ENABLED_ENABLED_2);

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
			query.append(ScheduledTaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(enabled);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scheduledTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScheduledTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the scheduled tasks that the user has permission to view where groupId = &#63; and enabled = &#63;.
	 *
	 * @param groupId the group ID
	 * @param enabled the enabled
	 * @return the matching scheduled tasks that the user has permission to view
	 */
	@Override
	public List<ScheduledTask> filterFindByGroupId_Enabled(long groupId,
		boolean enabled) {
		return filterFindByGroupId_Enabled(groupId, enabled, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the scheduled tasks that the user has permission to view where groupId = &#63; and enabled = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param enabled the enabled
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @return the range of matching scheduled tasks that the user has permission to view
	 */
	@Override
	public List<ScheduledTask> filterFindByGroupId_Enabled(long groupId,
		boolean enabled, int start, int end) {
		return filterFindByGroupId_Enabled(groupId, enabled, start, end, null);
	}

	/**
	 * Returns an ordered range of all the scheduled tasks that the user has permissions to view where groupId = &#63; and enabled = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param enabled the enabled
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching scheduled tasks that the user has permission to view
	 */
	@Override
	public List<ScheduledTask> filterFindByGroupId_Enabled(long groupId,
		boolean enabled, int start, int end,
		OrderByComparator<ScheduledTask> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_Enabled(groupId, enabled, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_SCHEDULEDTASK_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_SCHEDULEDTASK_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_ENABLED_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPID_ENABLED_ENABLED_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_SCHEDULEDTASK_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ScheduledTaskModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ScheduledTaskModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ScheduledTask.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ScheduledTaskImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ScheduledTaskImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(enabled);

			return (List<ScheduledTask>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the scheduled tasks before and after the current scheduled task in the ordered set of scheduled tasks that the user has permission to view where groupId = &#63; and enabled = &#63;.
	 *
	 * @param scheduledTaskId the primary key of the current scheduled task
	 * @param groupId the group ID
	 * @param enabled the enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next scheduled task
	 * @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	 */
	@Override
	public ScheduledTask[] filterFindByGroupId_Enabled_PrevAndNext(
		long scheduledTaskId, long groupId, boolean enabled,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_Enabled_PrevAndNext(scheduledTaskId, groupId,
				enabled, orderByComparator);
		}

		ScheduledTask scheduledTask = findByPrimaryKey(scheduledTaskId);

		Session session = null;

		try {
			session = openSession();

			ScheduledTask[] array = new ScheduledTaskImpl[3];

			array[0] = filterGetByGroupId_Enabled_PrevAndNext(session,
					scheduledTask, groupId, enabled, orderByComparator, true);

			array[1] = scheduledTask;

			array[2] = filterGetByGroupId_Enabled_PrevAndNext(session,
					scheduledTask, groupId, enabled, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ScheduledTask filterGetByGroupId_Enabled_PrevAndNext(
		Session session, ScheduledTask scheduledTask, long groupId,
		boolean enabled, OrderByComparator<ScheduledTask> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_SCHEDULEDTASK_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_SCHEDULEDTASK_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_ENABLED_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPID_ENABLED_ENABLED_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_SCHEDULEDTASK_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ScheduledTaskModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ScheduledTaskModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ScheduledTask.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ScheduledTaskImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ScheduledTaskImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(enabled);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scheduledTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScheduledTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the scheduled tasks where groupId = &#63; and enabled = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param enabled the enabled
	 */
	@Override
	public void removeByGroupId_Enabled(long groupId, boolean enabled) {
		for (ScheduledTask scheduledTask : findByGroupId_Enabled(groupId,
				enabled, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scheduledTask);
		}
	}

	/**
	 * Returns the number of scheduled tasks where groupId = &#63; and enabled = &#63;.
	 *
	 * @param groupId the group ID
	 * @param enabled the enabled
	 * @return the number of matching scheduled tasks
	 */
	@Override
	public int countByGroupId_Enabled(long groupId, boolean enabled) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID_ENABLED;

		Object[] finderArgs = new Object[] { groupId, enabled };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SCHEDULEDTASK_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_ENABLED_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPID_ENABLED_ENABLED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(enabled);

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
	 * Returns the number of scheduled tasks that the user has permission to view where groupId = &#63; and enabled = &#63;.
	 *
	 * @param groupId the group ID
	 * @param enabled the enabled
	 * @return the number of matching scheduled tasks that the user has permission to view
	 */
	@Override
	public int filterCountByGroupId_Enabled(long groupId, boolean enabled) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId_Enabled(groupId, enabled);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_SCHEDULEDTASK_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_ENABLED_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPID_ENABLED_ENABLED_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ScheduledTask.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(enabled);

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

	private static final String _FINDER_COLUMN_GROUPID_ENABLED_GROUPID_2 = "scheduledTask.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPID_ENABLED_ENABLED_2 = "scheduledTask.enabled = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME = new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED,
			ScheduledTaskImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByName",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME = new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED,
			ScheduledTaskImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByName",
			new String[] { Long.class.getName(), String.class.getName() },
			ScheduledTaskModelImpl.COMPANYID_COLUMN_BITMASK |
			ScheduledTaskModelImpl.NAME_COLUMN_BITMASK |
			ScheduledTaskModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the scheduled tasks where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByName(long companyId, String name) {
		return findByName(companyId, name, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the scheduled tasks where companyId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @return the range of matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByName(long companyId, String name,
		int start, int end) {
		return findByName(companyId, name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the scheduled tasks where companyId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByName(long companyId, String name,
		int start, int end, OrderByComparator<ScheduledTask> orderByComparator) {
		return findByName(companyId, name, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the scheduled tasks where companyId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findByName(long companyId, String name,
		int start, int end, OrderByComparator<ScheduledTask> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME;
			finderArgs = new Object[] { companyId, name };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME;
			finderArgs = new Object[] {
					companyId, name,
					
					start, end, orderByComparator
				};
		}

		List<ScheduledTask> list = null;

		if (retrieveFromCache) {
			list = (List<ScheduledTask>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ScheduledTask scheduledTask : list) {
					if ((companyId != scheduledTask.getCompanyId()) ||
							!Objects.equals(name, scheduledTask.getName())) {
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

			query.append(_SQL_SELECT_SCHEDULEDTASK_WHERE);

			query.append(_FINDER_COLUMN_NAME_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals("")) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScheduledTaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				if (!pagination) {
					list = (List<ScheduledTask>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScheduledTask>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first scheduled task in the ordered set where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching scheduled task
	 * @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask findByName_First(long companyId, String name,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = fetchByName_First(companyId, name,
				orderByComparator);

		if (scheduledTask != null) {
			return scheduledTask;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", name=");
		msg.append(name);

		msg.append("}");

		throw new NoSuchScheduledTaskException(msg.toString());
	}

	/**
	 * Returns the first scheduled task in the ordered set where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask fetchByName_First(long companyId, String name,
		OrderByComparator<ScheduledTask> orderByComparator) {
		List<ScheduledTask> list = findByName(companyId, name, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last scheduled task in the ordered set where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching scheduled task
	 * @throws NoSuchScheduledTaskException if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask findByName_Last(long companyId, String name,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = fetchByName_Last(companyId, name,
				orderByComparator);

		if (scheduledTask != null) {
			return scheduledTask;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", name=");
		msg.append(name);

		msg.append("}");

		throw new NoSuchScheduledTaskException(msg.toString());
	}

	/**
	 * Returns the last scheduled task in the ordered set where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching scheduled task, or <code>null</code> if a matching scheduled task could not be found
	 */
	@Override
	public ScheduledTask fetchByName_Last(long companyId, String name,
		OrderByComparator<ScheduledTask> orderByComparator) {
		int count = countByName(companyId, name);

		if (count == 0) {
			return null;
		}

		List<ScheduledTask> list = findByName(companyId, name, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the scheduled tasks before and after the current scheduled task in the ordered set where companyId = &#63; and name = &#63;.
	 *
	 * @param scheduledTaskId the primary key of the current scheduled task
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next scheduled task
	 * @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	 */
	@Override
	public ScheduledTask[] findByName_PrevAndNext(long scheduledTaskId,
		long companyId, String name,
		OrderByComparator<ScheduledTask> orderByComparator)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = findByPrimaryKey(scheduledTaskId);

		Session session = null;

		try {
			session = openSession();

			ScheduledTask[] array = new ScheduledTaskImpl[3];

			array[0] = getByName_PrevAndNext(session, scheduledTask, companyId,
					name, orderByComparator, true);

			array[1] = scheduledTask;

			array[2] = getByName_PrevAndNext(session, scheduledTask, companyId,
					name, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ScheduledTask getByName_PrevAndNext(Session session,
		ScheduledTask scheduledTask, long companyId, String name,
		OrderByComparator<ScheduledTask> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SCHEDULEDTASK_WHERE);

		query.append(_FINDER_COLUMN_NAME_COMPANYID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_NAME_NAME_1);
		}
		else if (name.equals("")) {
			query.append(_FINDER_COLUMN_NAME_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_NAME_NAME_2);
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
			query.append(ScheduledTaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (bindName) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scheduledTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ScheduledTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the scheduled tasks where companyId = &#63; and name = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 */
	@Override
	public void removeByName(long companyId, String name) {
		for (ScheduledTask scheduledTask : findByName(companyId, name,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scheduledTask);
		}
	}

	/**
	 * Returns the number of scheduled tasks where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the number of matching scheduled tasks
	 */
	@Override
	public int countByName(long companyId, String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAME;

		Object[] finderArgs = new Object[] { companyId, name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SCHEDULEDTASK_WHERE);

			query.append(_FINDER_COLUMN_NAME_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals("")) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
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

	private static final String _FINDER_COLUMN_NAME_COMPANYID_2 = "scheduledTask.companyId = ? AND ";
	private static final String _FINDER_COLUMN_NAME_NAME_1 = "scheduledTask.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "scheduledTask.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(scheduledTask.name IS NULL OR scheduledTask.name = '')";

	public ScheduledTaskPersistenceImpl() {
		setModelClass(ScheduledTask.class);

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
	 * Caches the scheduled task in the entity cache if it is enabled.
	 *
	 * @param scheduledTask the scheduled task
	 */
	@Override
	public void cacheResult(ScheduledTask scheduledTask) {
		entityCache.putResult(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskImpl.class, scheduledTask.getPrimaryKey(),
			scheduledTask);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { scheduledTask.getUuid(), scheduledTask.getGroupId() },
			scheduledTask);

		scheduledTask.resetOriginalValues();
	}

	/**
	 * Caches the scheduled tasks in the entity cache if it is enabled.
	 *
	 * @param scheduledTasks the scheduled tasks
	 */
	@Override
	public void cacheResult(List<ScheduledTask> scheduledTasks) {
		for (ScheduledTask scheduledTask : scheduledTasks) {
			if (entityCache.getResult(
						ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
						ScheduledTaskImpl.class, scheduledTask.getPrimaryKey()) == null) {
				cacheResult(scheduledTask);
			}
			else {
				scheduledTask.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all scheduled tasks.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ScheduledTaskImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the scheduled task.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ScheduledTask scheduledTask) {
		entityCache.removeResult(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskImpl.class, scheduledTask.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ScheduledTaskModelImpl)scheduledTask, true);
	}

	@Override
	public void clearCache(List<ScheduledTask> scheduledTasks) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ScheduledTask scheduledTask : scheduledTasks) {
			entityCache.removeResult(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
				ScheduledTaskImpl.class, scheduledTask.getPrimaryKey());

			clearUniqueFindersCache((ScheduledTaskModelImpl)scheduledTask, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ScheduledTaskModelImpl scheduledTaskModelImpl) {
		Object[] args = new Object[] {
				scheduledTaskModelImpl.getUuid(),
				scheduledTaskModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			scheduledTaskModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ScheduledTaskModelImpl scheduledTaskModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					scheduledTaskModelImpl.getUuid(),
					scheduledTaskModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((scheduledTaskModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					scheduledTaskModelImpl.getOriginalUuid(),
					scheduledTaskModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new scheduled task with the primary key. Does not add the scheduled task to the database.
	 *
	 * @param scheduledTaskId the primary key for the new scheduled task
	 * @return the new scheduled task
	 */
	@Override
	public ScheduledTask create(long scheduledTaskId) {
		ScheduledTask scheduledTask = new ScheduledTaskImpl();

		scheduledTask.setNew(true);
		scheduledTask.setPrimaryKey(scheduledTaskId);

		String uuid = PortalUUIDUtil.generate();

		scheduledTask.setUuid(uuid);

		scheduledTask.setCompanyId(companyProvider.getCompanyId());

		return scheduledTask;
	}

	/**
	 * Removes the scheduled task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param scheduledTaskId the primary key of the scheduled task
	 * @return the scheduled task that was removed
	 * @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	 */
	@Override
	public ScheduledTask remove(long scheduledTaskId)
		throws NoSuchScheduledTaskException {
		return remove((Serializable)scheduledTaskId);
	}

	/**
	 * Removes the scheduled task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the scheduled task
	 * @return the scheduled task that was removed
	 * @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	 */
	@Override
	public ScheduledTask remove(Serializable primaryKey)
		throws NoSuchScheduledTaskException {
		Session session = null;

		try {
			session = openSession();

			ScheduledTask scheduledTask = (ScheduledTask)session.get(ScheduledTaskImpl.class,
					primaryKey);

			if (scheduledTask == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchScheduledTaskException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(scheduledTask);
		}
		catch (NoSuchScheduledTaskException nsee) {
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
	protected ScheduledTask removeImpl(ScheduledTask scheduledTask) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(scheduledTask)) {
				scheduledTask = (ScheduledTask)session.get(ScheduledTaskImpl.class,
						scheduledTask.getPrimaryKeyObj());
			}

			if (scheduledTask != null) {
				session.delete(scheduledTask);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (scheduledTask != null) {
			clearCache(scheduledTask);
		}

		return scheduledTask;
	}

	@Override
	public ScheduledTask updateImpl(ScheduledTask scheduledTask) {
		boolean isNew = scheduledTask.isNew();

		if (!(scheduledTask instanceof ScheduledTaskModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(scheduledTask.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(scheduledTask);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in scheduledTask proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ScheduledTask implementation " +
				scheduledTask.getClass());
		}

		ScheduledTaskModelImpl scheduledTaskModelImpl = (ScheduledTaskModelImpl)scheduledTask;

		if (Validator.isNull(scheduledTask.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			scheduledTask.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (scheduledTask.getCreateDate() == null)) {
			if (serviceContext == null) {
				scheduledTask.setCreateDate(now);
			}
			else {
				scheduledTask.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!scheduledTaskModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				scheduledTask.setModifiedDate(now);
			}
			else {
				scheduledTask.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (scheduledTask.isNew()) {
				session.save(scheduledTask);

				scheduledTask.setNew(false);
			}
			else {
				scheduledTask = (ScheduledTask)session.merge(scheduledTask);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ScheduledTaskModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { scheduledTaskModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					scheduledTaskModelImpl.getUuid(),
					scheduledTaskModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { scheduledTaskModelImpl.isEnabled() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ENABLED, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENABLED,
				args);

			args = new Object[] { scheduledTaskModelImpl.isActive() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIVE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE,
				args);

			args = new Object[] { scheduledTaskModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] {
					scheduledTaskModelImpl.getGroupId(),
					scheduledTaskModelImpl.isActive()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID_ACTIVE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID_ACTIVE,
				args);

			args = new Object[] {
					scheduledTaskModelImpl.getGroupId(),
					scheduledTaskModelImpl.isEnabled()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID_ENABLED, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID_ENABLED,
				args);

			args = new Object[] {
					scheduledTaskModelImpl.getCompanyId(),
					scheduledTaskModelImpl.getName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((scheduledTaskModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scheduledTaskModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { scheduledTaskModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((scheduledTaskModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scheduledTaskModelImpl.getOriginalUuid(),
						scheduledTaskModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						scheduledTaskModelImpl.getUuid(),
						scheduledTaskModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((scheduledTaskModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENABLED.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scheduledTaskModelImpl.getOriginalEnabled()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ENABLED, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENABLED,
					args);

				args = new Object[] { scheduledTaskModelImpl.isEnabled() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ENABLED, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENABLED,
					args);
			}

			if ((scheduledTaskModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scheduledTaskModelImpl.getOriginalActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIVE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE,
					args);

				args = new Object[] { scheduledTaskModelImpl.isActive() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIVE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE,
					args);
			}

			if ((scheduledTaskModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scheduledTaskModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { scheduledTaskModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((scheduledTaskModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID_ACTIVE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scheduledTaskModelImpl.getOriginalGroupId(),
						scheduledTaskModelImpl.getOriginalActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID_ACTIVE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID_ACTIVE,
					args);

				args = new Object[] {
						scheduledTaskModelImpl.getGroupId(),
						scheduledTaskModelImpl.isActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID_ACTIVE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID_ACTIVE,
					args);
			}

			if ((scheduledTaskModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID_ENABLED.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scheduledTaskModelImpl.getOriginalGroupId(),
						scheduledTaskModelImpl.getOriginalEnabled()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID_ENABLED,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID_ENABLED,
					args);

				args = new Object[] {
						scheduledTaskModelImpl.getGroupId(),
						scheduledTaskModelImpl.isEnabled()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID_ENABLED,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID_ENABLED,
					args);
			}

			if ((scheduledTaskModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scheduledTaskModelImpl.getOriginalCompanyId(),
						scheduledTaskModelImpl.getOriginalName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);

				args = new Object[] {
						scheduledTaskModelImpl.getCompanyId(),
						scheduledTaskModelImpl.getName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);
			}
		}

		entityCache.putResult(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
			ScheduledTaskImpl.class, scheduledTask.getPrimaryKey(),
			scheduledTask, false);

		clearUniqueFindersCache(scheduledTaskModelImpl, false);
		cacheUniqueFindersCache(scheduledTaskModelImpl);

		scheduledTask.resetOriginalValues();

		return scheduledTask;
	}

	/**
	 * Returns the scheduled task with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the scheduled task
	 * @return the scheduled task
	 * @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	 */
	@Override
	public ScheduledTask findByPrimaryKey(Serializable primaryKey)
		throws NoSuchScheduledTaskException {
		ScheduledTask scheduledTask = fetchByPrimaryKey(primaryKey);

		if (scheduledTask == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchScheduledTaskException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return scheduledTask;
	}

	/**
	 * Returns the scheduled task with the primary key or throws a {@link NoSuchScheduledTaskException} if it could not be found.
	 *
	 * @param scheduledTaskId the primary key of the scheduled task
	 * @return the scheduled task
	 * @throws NoSuchScheduledTaskException if a scheduled task with the primary key could not be found
	 */
	@Override
	public ScheduledTask findByPrimaryKey(long scheduledTaskId)
		throws NoSuchScheduledTaskException {
		return findByPrimaryKey((Serializable)scheduledTaskId);
	}

	/**
	 * Returns the scheduled task with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the scheduled task
	 * @return the scheduled task, or <code>null</code> if a scheduled task with the primary key could not be found
	 */
	@Override
	public ScheduledTask fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
				ScheduledTaskImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ScheduledTask scheduledTask = (ScheduledTask)serializable;

		if (scheduledTask == null) {
			Session session = null;

			try {
				session = openSession();

				scheduledTask = (ScheduledTask)session.get(ScheduledTaskImpl.class,
						primaryKey);

				if (scheduledTask != null) {
					cacheResult(scheduledTask);
				}
				else {
					entityCache.putResult(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
						ScheduledTaskImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
					ScheduledTaskImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return scheduledTask;
	}

	/**
	 * Returns the scheduled task with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param scheduledTaskId the primary key of the scheduled task
	 * @return the scheduled task, or <code>null</code> if a scheduled task with the primary key could not be found
	 */
	@Override
	public ScheduledTask fetchByPrimaryKey(long scheduledTaskId) {
		return fetchByPrimaryKey((Serializable)scheduledTaskId);
	}

	@Override
	public Map<Serializable, ScheduledTask> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ScheduledTask> map = new HashMap<Serializable, ScheduledTask>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ScheduledTask scheduledTask = fetchByPrimaryKey(primaryKey);

			if (scheduledTask != null) {
				map.put(primaryKey, scheduledTask);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
					ScheduledTaskImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ScheduledTask)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SCHEDULEDTASK_WHERE_PKS_IN);

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

			for (ScheduledTask scheduledTask : (List<ScheduledTask>)q.list()) {
				map.put(scheduledTask.getPrimaryKeyObj(), scheduledTask);

				cacheResult(scheduledTask);

				uncachedPrimaryKeys.remove(scheduledTask.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ScheduledTaskModelImpl.ENTITY_CACHE_ENABLED,
					ScheduledTaskImpl.class, primaryKey, nullModel);
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
	 * Returns all the scheduled tasks.
	 *
	 * @return the scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the scheduled tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @return the range of scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the scheduled tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findAll(int start, int end,
		OrderByComparator<ScheduledTask> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the scheduled tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduledTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of scheduled tasks
	 * @param end the upper bound of the range of scheduled tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of scheduled tasks
	 */
	@Override
	public List<ScheduledTask> findAll(int start, int end,
		OrderByComparator<ScheduledTask> orderByComparator,
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

		List<ScheduledTask> list = null;

		if (retrieveFromCache) {
			list = (List<ScheduledTask>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SCHEDULEDTASK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SCHEDULEDTASK;

				if (pagination) {
					sql = sql.concat(ScheduledTaskModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ScheduledTask>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ScheduledTask>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the scheduled tasks from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ScheduledTask scheduledTask : findAll()) {
			remove(scheduledTask);
		}
	}

	/**
	 * Returns the number of scheduled tasks.
	 *
	 * @return the number of scheduled tasks
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SCHEDULEDTASK);

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
		return ScheduledTaskModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the scheduled task persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ScheduledTaskImpl.class.getName());
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
	private static final String _SQL_SELECT_SCHEDULEDTASK = "SELECT scheduledTask FROM ScheduledTask scheduledTask";
	private static final String _SQL_SELECT_SCHEDULEDTASK_WHERE_PKS_IN = "SELECT scheduledTask FROM ScheduledTask scheduledTask WHERE scheduledTaskId IN (";
	private static final String _SQL_SELECT_SCHEDULEDTASK_WHERE = "SELECT scheduledTask FROM ScheduledTask scheduledTask WHERE ";
	private static final String _SQL_COUNT_SCHEDULEDTASK = "SELECT COUNT(scheduledTask) FROM ScheduledTask scheduledTask";
	private static final String _SQL_COUNT_SCHEDULEDTASK_WHERE = "SELECT COUNT(scheduledTask) FROM ScheduledTask scheduledTask WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "scheduledTask.scheduledTaskId";
	private static final String _FILTER_SQL_SELECT_SCHEDULEDTASK_WHERE = "SELECT DISTINCT {scheduledTask.*} FROM ScheduledTask scheduledTask WHERE ";
	private static final String _FILTER_SQL_SELECT_SCHEDULEDTASK_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {ScheduledTask.*} FROM (SELECT DISTINCT scheduledTask.scheduledTaskId FROM ScheduledTask scheduledTask WHERE ";
	private static final String _FILTER_SQL_SELECT_SCHEDULEDTASK_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN ScheduledTask ON TEMP_TABLE.scheduledTaskId = ScheduledTask.scheduledTaskId";
	private static final String _FILTER_SQL_COUNT_SCHEDULEDTASK_WHERE = "SELECT COUNT(DISTINCT scheduledTask.scheduledTaskId) AS COUNT_VALUE FROM ScheduledTask scheduledTask WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "scheduledTask";
	private static final String _FILTER_ENTITY_TABLE = "ScheduledTask";
	private static final String _ORDER_BY_ENTITY_ALIAS = "scheduledTask.";
	private static final String _ORDER_BY_ENTITY_TABLE = "ScheduledTask.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ScheduledTask exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ScheduledTask exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ScheduledTaskPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "active"
			});
}