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

package com.liferay.commerce.inventory.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseImpl;
import com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseModelImpl;
import com.liferay.commerce.inventory.service.persistence.CommerceInventoryWarehousePersistence;

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
import com.liferay.portal.kernel.util.StringUtil;
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
 * The persistence implementation for the commerce inventory warehouse service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehousePersistence
 * @see com.liferay.commerce.inventory.service.persistence.CommerceInventoryWarehouseUtil
 * @generated
 */
@ProviderType
public class CommerceInventoryWarehousePersistenceImpl
	extends BasePersistenceImpl<CommerceInventoryWarehouse>
	implements CommerceInventoryWarehousePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommerceInventoryWarehouseUtil} to access the commerce inventory warehouse persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommerceInventoryWarehouseImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommerceInventoryWarehouseModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryWarehouseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommerceInventoryWarehouseModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryWarehouseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommerceInventoryWarehouseModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVE = new FinderPath(CommerceInventoryWarehouseModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryWarehouseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByactive",
			new String[] {
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE =
		new FinderPath(CommerceInventoryWarehouseModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryWarehouseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByactive",
			new String[] { Boolean.class.getName() },
			CommerceInventoryWarehouseModelImpl.ACTIVE_COLUMN_BITMASK |
			CommerceInventoryWarehouseModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVE = new FinderPath(CommerceInventoryWarehouseModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByactive", new String[] { Boolean.class.getName() });

	/**
	 * Returns all the commerce inventory warehouses where active = &#63;.
	 *
	 * @param active the active
	 * @return the matching commerce inventory warehouses
	 */
	@Override
	public List<CommerceInventoryWarehouse> findByactive(boolean active) {
		return findByactive(active, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory warehouses where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param active the active
	 * @param start the lower bound of the range of commerce inventory warehouses
	 * @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	 * @return the range of matching commerce inventory warehouses
	 */
	@Override
	public List<CommerceInventoryWarehouse> findByactive(boolean active,
		int start, int end) {
		return findByactive(active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouses where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param active the active
	 * @param start the lower bound of the range of commerce inventory warehouses
	 * @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory warehouses
	 */
	@Override
	public List<CommerceInventoryWarehouse> findByactive(boolean active,
		int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		return findByactive(active, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouses where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param active the active
	 * @param start the lower bound of the range of commerce inventory warehouses
	 * @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce inventory warehouses
	 */
	@Override
	public List<CommerceInventoryWarehouse> findByactive(boolean active,
		int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator,
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

		List<CommerceInventoryWarehouse> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceInventoryWarehouse>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceInventoryWarehouse commerceInventoryWarehouse : list) {
					if ((active != commerceInventoryWarehouse.isActive())) {
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

			query.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_WHERE);

			query.append(_FINDER_COLUMN_ACTIVE_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceInventoryWarehouseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(active);

				if (!pagination) {
					list = (List<CommerceInventoryWarehouse>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceInventoryWarehouse>)QueryUtil.list(q,
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
	 * Returns the first commerce inventory warehouse in the ordered set where active = &#63;.
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse
	 * @throws NoSuchInventoryWarehouseException if a matching commerce inventory warehouse could not be found
	 */
	@Override
	public CommerceInventoryWarehouse findByactive_First(boolean active,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws NoSuchInventoryWarehouseException {
		CommerceInventoryWarehouse commerceInventoryWarehouse = fetchByactive_First(active,
				orderByComparator);

		if (commerceInventoryWarehouse != null) {
			return commerceInventoryWarehouse;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("active=");
		msg.append(active);

		msg.append("}");

		throw new NoSuchInventoryWarehouseException(msg.toString());
	}

	/**
	 * Returns the first commerce inventory warehouse in the ordered set where active = &#63;.
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	 */
	@Override
	public CommerceInventoryWarehouse fetchByactive_First(boolean active,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		List<CommerceInventoryWarehouse> list = findByactive(active, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce inventory warehouse in the ordered set where active = &#63;.
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse
	 * @throws NoSuchInventoryWarehouseException if a matching commerce inventory warehouse could not be found
	 */
	@Override
	public CommerceInventoryWarehouse findByactive_Last(boolean active,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws NoSuchInventoryWarehouseException {
		CommerceInventoryWarehouse commerceInventoryWarehouse = fetchByactive_Last(active,
				orderByComparator);

		if (commerceInventoryWarehouse != null) {
			return commerceInventoryWarehouse;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("active=");
		msg.append(active);

		msg.append("}");

		throw new NoSuchInventoryWarehouseException(msg.toString());
	}

	/**
	 * Returns the last commerce inventory warehouse in the ordered set where active = &#63;.
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	 */
	@Override
	public CommerceInventoryWarehouse fetchByactive_Last(boolean active,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		int count = countByactive(active);

		if (count == 0) {
			return null;
		}

		List<CommerceInventoryWarehouse> list = findByactive(active, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce inventory warehouses before and after the current commerce inventory warehouse in the ordered set where active = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the primary key of the current commerce inventory warehouse
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory warehouse
	 * @throws NoSuchInventoryWarehouseException if a commerce inventory warehouse with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouse[] findByactive_PrevAndNext(
		long commerceInventoryWarehouseId, boolean active,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws NoSuchInventoryWarehouseException {
		CommerceInventoryWarehouse commerceInventoryWarehouse = findByPrimaryKey(commerceInventoryWarehouseId);

		Session session = null;

		try {
			session = openSession();

			CommerceInventoryWarehouse[] array = new CommerceInventoryWarehouseImpl[3];

			array[0] = getByactive_PrevAndNext(session,
					commerceInventoryWarehouse, active, orderByComparator, true);

			array[1] = commerceInventoryWarehouse;

			array[2] = getByactive_PrevAndNext(session,
					commerceInventoryWarehouse, active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceInventoryWarehouse getByactive_PrevAndNext(
		Session session, CommerceInventoryWarehouse commerceInventoryWarehouse,
		boolean active,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_WHERE);

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
			query.append(CommerceInventoryWarehouseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceInventoryWarehouse);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceInventoryWarehouse> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the commerce inventory warehouses that the user has permission to view where active = &#63;.
	 *
	 * @param active the active
	 * @return the matching commerce inventory warehouses that the user has permission to view
	 */
	@Override
	public List<CommerceInventoryWarehouse> filterFindByactive(boolean active) {
		return filterFindByactive(active, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the commerce inventory warehouses that the user has permission to view where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param active the active
	 * @param start the lower bound of the range of commerce inventory warehouses
	 * @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	 * @return the range of matching commerce inventory warehouses that the user has permission to view
	 */
	@Override
	public List<CommerceInventoryWarehouse> filterFindByactive(boolean active,
		int start, int end) {
		return filterFindByactive(active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouses that the user has permissions to view where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param active the active
	 * @param start the lower bound of the range of commerce inventory warehouses
	 * @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory warehouses that the user has permission to view
	 */
	@Override
	public List<CommerceInventoryWarehouse> filterFindByactive(boolean active,
		int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByactive(active, start, end, orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_ACTIVE_ACTIVE_2_SQL);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CommerceInventoryWarehouseModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceInventoryWarehouseModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceInventoryWarehouse.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS,
					CommerceInventoryWarehouseImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE,
					CommerceInventoryWarehouseImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(active);

			return (List<CommerceInventoryWarehouse>)QueryUtil.list(q,
				getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the commerce inventory warehouses before and after the current commerce inventory warehouse in the ordered set of commerce inventory warehouses that the user has permission to view where active = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the primary key of the current commerce inventory warehouse
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory warehouse
	 * @throws NoSuchInventoryWarehouseException if a commerce inventory warehouse with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouse[] filterFindByactive_PrevAndNext(
		long commerceInventoryWarehouseId, boolean active,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws NoSuchInventoryWarehouseException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByactive_PrevAndNext(commerceInventoryWarehouseId,
				active, orderByComparator);
		}

		CommerceInventoryWarehouse commerceInventoryWarehouse = findByPrimaryKey(commerceInventoryWarehouseId);

		Session session = null;

		try {
			session = openSession();

			CommerceInventoryWarehouse[] array = new CommerceInventoryWarehouseImpl[3];

			array[0] = filterGetByactive_PrevAndNext(session,
					commerceInventoryWarehouse, active, orderByComparator, true);

			array[1] = commerceInventoryWarehouse;

			array[2] = filterGetByactive_PrevAndNext(session,
					commerceInventoryWarehouse, active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceInventoryWarehouse filterGetByactive_PrevAndNext(
		Session session, CommerceInventoryWarehouse commerceInventoryWarehouse,
		boolean active,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator,
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

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_ACTIVE_ACTIVE_2_SQL);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CommerceInventoryWarehouseModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceInventoryWarehouseModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceInventoryWarehouse.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS,
				CommerceInventoryWarehouseImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE,
				CommerceInventoryWarehouseImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceInventoryWarehouse);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceInventoryWarehouse> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce inventory warehouses where active = &#63; from the database.
	 *
	 * @param active the active
	 */
	@Override
	public void removeByactive(boolean active) {
		for (CommerceInventoryWarehouse commerceInventoryWarehouse : findByactive(
				active, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(commerceInventoryWarehouse);
		}
	}

	/**
	 * Returns the number of commerce inventory warehouses where active = &#63;.
	 *
	 * @param active the active
	 * @return the number of matching commerce inventory warehouses
	 */
	@Override
	public int countByactive(boolean active) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVE;

		Object[] finderArgs = new Object[] { active };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEINVENTORYWAREHOUSE_WHERE);

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

	/**
	 * Returns the number of commerce inventory warehouses that the user has permission to view where active = &#63;.
	 *
	 * @param active the active
	 * @return the number of matching commerce inventory warehouses that the user has permission to view
	 */
	@Override
	public int filterCountByactive(boolean active) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByactive(active);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_COMMERCEINVENTORYWAREHOUSE_WHERE);

		query.append(_FINDER_COLUMN_ACTIVE_ACTIVE_2_SQL);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceInventoryWarehouse.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_ACTIVE_ACTIVE_2 = "commerceInventoryWarehouse.active = ?";
	private static final String _FINDER_COLUMN_ACTIVE_ACTIVE_2_SQL = "commerceInventoryWarehouse.active_ = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COUNTRYTWOLETTERSISOCODE =
		new FinderPath(CommerceInventoryWarehouseModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryWarehouseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBycountryTwoLettersISOCode",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COUNTRYTWOLETTERSISOCODE =
		new FinderPath(CommerceInventoryWarehouseModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryWarehouseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBycountryTwoLettersISOCode",
			new String[] { String.class.getName() },
			CommerceInventoryWarehouseModelImpl.COUNTRYTWOLETTERSISOCODE_COLUMN_BITMASK |
			CommerceInventoryWarehouseModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COUNTRYTWOLETTERSISOCODE =
		new FinderPath(CommerceInventoryWarehouseModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBycountryTwoLettersISOCode",
			new String[] { String.class.getName() });

	/**
	 * Returns all the commerce inventory warehouses where countryTwoLettersISOCode = &#63;.
	 *
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @return the matching commerce inventory warehouses
	 */
	@Override
	public List<CommerceInventoryWarehouse> findBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode) {
		return findBycountryTwoLettersISOCode(countryTwoLettersISOCode,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory warehouses where countryTwoLettersISOCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @param start the lower bound of the range of commerce inventory warehouses
	 * @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	 * @return the range of matching commerce inventory warehouses
	 */
	@Override
	public List<CommerceInventoryWarehouse> findBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode, int start, int end) {
		return findBycountryTwoLettersISOCode(countryTwoLettersISOCode, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouses where countryTwoLettersISOCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @param start the lower bound of the range of commerce inventory warehouses
	 * @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory warehouses
	 */
	@Override
	public List<CommerceInventoryWarehouse> findBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode, int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		return findBycountryTwoLettersISOCode(countryTwoLettersISOCode, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouses where countryTwoLettersISOCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @param start the lower bound of the range of commerce inventory warehouses
	 * @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce inventory warehouses
	 */
	@Override
	public List<CommerceInventoryWarehouse> findBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode, int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COUNTRYTWOLETTERSISOCODE;
			finderArgs = new Object[] { countryTwoLettersISOCode };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COUNTRYTWOLETTERSISOCODE;
			finderArgs = new Object[] {
					countryTwoLettersISOCode,
					
					start, end, orderByComparator
				};
		}

		List<CommerceInventoryWarehouse> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceInventoryWarehouse>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceInventoryWarehouse commerceInventoryWarehouse : list) {
					if (!Objects.equals(countryTwoLettersISOCode,
								commerceInventoryWarehouse.getCountryTwoLettersISOCode())) {
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

			query.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_WHERE);

			boolean bindCountryTwoLettersISOCode = false;

			if (countryTwoLettersISOCode == null) {
				query.append(_FINDER_COLUMN_COUNTRYTWOLETTERSISOCODE_COUNTRYTWOLETTERSISOCODE_1);
			}
			else if (countryTwoLettersISOCode.equals("")) {
				query.append(_FINDER_COLUMN_COUNTRYTWOLETTERSISOCODE_COUNTRYTWOLETTERSISOCODE_3);
			}
			else {
				bindCountryTwoLettersISOCode = true;

				query.append(_FINDER_COLUMN_COUNTRYTWOLETTERSISOCODE_COUNTRYTWOLETTERSISOCODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceInventoryWarehouseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCountryTwoLettersISOCode) {
					qPos.add(countryTwoLettersISOCode);
				}

				if (!pagination) {
					list = (List<CommerceInventoryWarehouse>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceInventoryWarehouse>)QueryUtil.list(q,
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
	 * Returns the first commerce inventory warehouse in the ordered set where countryTwoLettersISOCode = &#63;.
	 *
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse
	 * @throws NoSuchInventoryWarehouseException if a matching commerce inventory warehouse could not be found
	 */
	@Override
	public CommerceInventoryWarehouse findBycountryTwoLettersISOCode_First(
		String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws NoSuchInventoryWarehouseException {
		CommerceInventoryWarehouse commerceInventoryWarehouse = fetchBycountryTwoLettersISOCode_First(countryTwoLettersISOCode,
				orderByComparator);

		if (commerceInventoryWarehouse != null) {
			return commerceInventoryWarehouse;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("countryTwoLettersISOCode=");
		msg.append(countryTwoLettersISOCode);

		msg.append("}");

		throw new NoSuchInventoryWarehouseException(msg.toString());
	}

	/**
	 * Returns the first commerce inventory warehouse in the ordered set where countryTwoLettersISOCode = &#63;.
	 *
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	 */
	@Override
	public CommerceInventoryWarehouse fetchBycountryTwoLettersISOCode_First(
		String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		List<CommerceInventoryWarehouse> list = findBycountryTwoLettersISOCode(countryTwoLettersISOCode,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce inventory warehouse in the ordered set where countryTwoLettersISOCode = &#63;.
	 *
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse
	 * @throws NoSuchInventoryWarehouseException if a matching commerce inventory warehouse could not be found
	 */
	@Override
	public CommerceInventoryWarehouse findBycountryTwoLettersISOCode_Last(
		String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws NoSuchInventoryWarehouseException {
		CommerceInventoryWarehouse commerceInventoryWarehouse = fetchBycountryTwoLettersISOCode_Last(countryTwoLettersISOCode,
				orderByComparator);

		if (commerceInventoryWarehouse != null) {
			return commerceInventoryWarehouse;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("countryTwoLettersISOCode=");
		msg.append(countryTwoLettersISOCode);

		msg.append("}");

		throw new NoSuchInventoryWarehouseException(msg.toString());
	}

	/**
	 * Returns the last commerce inventory warehouse in the ordered set where countryTwoLettersISOCode = &#63;.
	 *
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	 */
	@Override
	public CommerceInventoryWarehouse fetchBycountryTwoLettersISOCode_Last(
		String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		int count = countBycountryTwoLettersISOCode(countryTwoLettersISOCode);

		if (count == 0) {
			return null;
		}

		List<CommerceInventoryWarehouse> list = findBycountryTwoLettersISOCode(countryTwoLettersISOCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce inventory warehouses before and after the current commerce inventory warehouse in the ordered set where countryTwoLettersISOCode = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the primary key of the current commerce inventory warehouse
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory warehouse
	 * @throws NoSuchInventoryWarehouseException if a commerce inventory warehouse with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouse[] findBycountryTwoLettersISOCode_PrevAndNext(
		long commerceInventoryWarehouseId, String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws NoSuchInventoryWarehouseException {
		CommerceInventoryWarehouse commerceInventoryWarehouse = findByPrimaryKey(commerceInventoryWarehouseId);

		Session session = null;

		try {
			session = openSession();

			CommerceInventoryWarehouse[] array = new CommerceInventoryWarehouseImpl[3];

			array[0] = getBycountryTwoLettersISOCode_PrevAndNext(session,
					commerceInventoryWarehouse, countryTwoLettersISOCode,
					orderByComparator, true);

			array[1] = commerceInventoryWarehouse;

			array[2] = getBycountryTwoLettersISOCode_PrevAndNext(session,
					commerceInventoryWarehouse, countryTwoLettersISOCode,
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

	protected CommerceInventoryWarehouse getBycountryTwoLettersISOCode_PrevAndNext(
		Session session, CommerceInventoryWarehouse commerceInventoryWarehouse,
		String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_WHERE);

		boolean bindCountryTwoLettersISOCode = false;

		if (countryTwoLettersISOCode == null) {
			query.append(_FINDER_COLUMN_COUNTRYTWOLETTERSISOCODE_COUNTRYTWOLETTERSISOCODE_1);
		}
		else if (countryTwoLettersISOCode.equals("")) {
			query.append(_FINDER_COLUMN_COUNTRYTWOLETTERSISOCODE_COUNTRYTWOLETTERSISOCODE_3);
		}
		else {
			bindCountryTwoLettersISOCode = true;

			query.append(_FINDER_COLUMN_COUNTRYTWOLETTERSISOCODE_COUNTRYTWOLETTERSISOCODE_2);
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
			query.append(CommerceInventoryWarehouseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindCountryTwoLettersISOCode) {
			qPos.add(countryTwoLettersISOCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceInventoryWarehouse);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceInventoryWarehouse> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the commerce inventory warehouses that the user has permission to view where countryTwoLettersISOCode = &#63;.
	 *
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @return the matching commerce inventory warehouses that the user has permission to view
	 */
	@Override
	public List<CommerceInventoryWarehouse> filterFindBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode) {
		return filterFindBycountryTwoLettersISOCode(countryTwoLettersISOCode,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory warehouses that the user has permission to view where countryTwoLettersISOCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @param start the lower bound of the range of commerce inventory warehouses
	 * @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	 * @return the range of matching commerce inventory warehouses that the user has permission to view
	 */
	@Override
	public List<CommerceInventoryWarehouse> filterFindBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode, int start, int end) {
		return filterFindBycountryTwoLettersISOCode(countryTwoLettersISOCode,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouses that the user has permissions to view where countryTwoLettersISOCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @param start the lower bound of the range of commerce inventory warehouses
	 * @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory warehouses that the user has permission to view
	 */
	@Override
	public List<CommerceInventoryWarehouse> filterFindBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode, int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findBycountryTwoLettersISOCode(countryTwoLettersISOCode,
				start, end, orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindCountryTwoLettersISOCode = false;

		if (countryTwoLettersISOCode == null) {
			query.append(_FINDER_COLUMN_COUNTRYTWOLETTERSISOCODE_COUNTRYTWOLETTERSISOCODE_1);
		}
		else if (countryTwoLettersISOCode.equals("")) {
			query.append(_FINDER_COLUMN_COUNTRYTWOLETTERSISOCODE_COUNTRYTWOLETTERSISOCODE_3);
		}
		else {
			bindCountryTwoLettersISOCode = true;

			query.append(_FINDER_COLUMN_COUNTRYTWOLETTERSISOCODE_COUNTRYTWOLETTERSISOCODE_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CommerceInventoryWarehouseModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceInventoryWarehouseModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceInventoryWarehouse.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS,
					CommerceInventoryWarehouseImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE,
					CommerceInventoryWarehouseImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindCountryTwoLettersISOCode) {
				qPos.add(countryTwoLettersISOCode);
			}

			return (List<CommerceInventoryWarehouse>)QueryUtil.list(q,
				getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the commerce inventory warehouses before and after the current commerce inventory warehouse in the ordered set of commerce inventory warehouses that the user has permission to view where countryTwoLettersISOCode = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the primary key of the current commerce inventory warehouse
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory warehouse
	 * @throws NoSuchInventoryWarehouseException if a commerce inventory warehouse with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouse[] filterFindBycountryTwoLettersISOCode_PrevAndNext(
		long commerceInventoryWarehouseId, String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws NoSuchInventoryWarehouseException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findBycountryTwoLettersISOCode_PrevAndNext(commerceInventoryWarehouseId,
				countryTwoLettersISOCode, orderByComparator);
		}

		CommerceInventoryWarehouse commerceInventoryWarehouse = findByPrimaryKey(commerceInventoryWarehouseId);

		Session session = null;

		try {
			session = openSession();

			CommerceInventoryWarehouse[] array = new CommerceInventoryWarehouseImpl[3];

			array[0] = filterGetBycountryTwoLettersISOCode_PrevAndNext(session,
					commerceInventoryWarehouse, countryTwoLettersISOCode,
					orderByComparator, true);

			array[1] = commerceInventoryWarehouse;

			array[2] = filterGetBycountryTwoLettersISOCode_PrevAndNext(session,
					commerceInventoryWarehouse, countryTwoLettersISOCode,
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

	protected CommerceInventoryWarehouse filterGetBycountryTwoLettersISOCode_PrevAndNext(
		Session session, CommerceInventoryWarehouse commerceInventoryWarehouse,
		String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator,
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

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindCountryTwoLettersISOCode = false;

		if (countryTwoLettersISOCode == null) {
			query.append(_FINDER_COLUMN_COUNTRYTWOLETTERSISOCODE_COUNTRYTWOLETTERSISOCODE_1);
		}
		else if (countryTwoLettersISOCode.equals("")) {
			query.append(_FINDER_COLUMN_COUNTRYTWOLETTERSISOCODE_COUNTRYTWOLETTERSISOCODE_3);
		}
		else {
			bindCountryTwoLettersISOCode = true;

			query.append(_FINDER_COLUMN_COUNTRYTWOLETTERSISOCODE_COUNTRYTWOLETTERSISOCODE_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CommerceInventoryWarehouseModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceInventoryWarehouseModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceInventoryWarehouse.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS,
				CommerceInventoryWarehouseImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE,
				CommerceInventoryWarehouseImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindCountryTwoLettersISOCode) {
			qPos.add(countryTwoLettersISOCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceInventoryWarehouse);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceInventoryWarehouse> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce inventory warehouses where countryTwoLettersISOCode = &#63; from the database.
	 *
	 * @param countryTwoLettersISOCode the country two letters iso code
	 */
	@Override
	public void removeBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode) {
		for (CommerceInventoryWarehouse commerceInventoryWarehouse : findBycountryTwoLettersISOCode(
				countryTwoLettersISOCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(commerceInventoryWarehouse);
		}
	}

	/**
	 * Returns the number of commerce inventory warehouses where countryTwoLettersISOCode = &#63;.
	 *
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @return the number of matching commerce inventory warehouses
	 */
	@Override
	public int countBycountryTwoLettersISOCode(String countryTwoLettersISOCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COUNTRYTWOLETTERSISOCODE;

		Object[] finderArgs = new Object[] { countryTwoLettersISOCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEINVENTORYWAREHOUSE_WHERE);

			boolean bindCountryTwoLettersISOCode = false;

			if (countryTwoLettersISOCode == null) {
				query.append(_FINDER_COLUMN_COUNTRYTWOLETTERSISOCODE_COUNTRYTWOLETTERSISOCODE_1);
			}
			else if (countryTwoLettersISOCode.equals("")) {
				query.append(_FINDER_COLUMN_COUNTRYTWOLETTERSISOCODE_COUNTRYTWOLETTERSISOCODE_3);
			}
			else {
				bindCountryTwoLettersISOCode = true;

				query.append(_FINDER_COLUMN_COUNTRYTWOLETTERSISOCODE_COUNTRYTWOLETTERSISOCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCountryTwoLettersISOCode) {
					qPos.add(countryTwoLettersISOCode);
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
	 * Returns the number of commerce inventory warehouses that the user has permission to view where countryTwoLettersISOCode = &#63;.
	 *
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @return the number of matching commerce inventory warehouses that the user has permission to view
	 */
	@Override
	public int filterCountBycountryTwoLettersISOCode(
		String countryTwoLettersISOCode) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countBycountryTwoLettersISOCode(countryTwoLettersISOCode);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_COMMERCEINVENTORYWAREHOUSE_WHERE);

		boolean bindCountryTwoLettersISOCode = false;

		if (countryTwoLettersISOCode == null) {
			query.append(_FINDER_COLUMN_COUNTRYTWOLETTERSISOCODE_COUNTRYTWOLETTERSISOCODE_1);
		}
		else if (countryTwoLettersISOCode.equals("")) {
			query.append(_FINDER_COLUMN_COUNTRYTWOLETTERSISOCODE_COUNTRYTWOLETTERSISOCODE_3);
		}
		else {
			bindCountryTwoLettersISOCode = true;

			query.append(_FINDER_COLUMN_COUNTRYTWOLETTERSISOCODE_COUNTRYTWOLETTERSISOCODE_2);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceInventoryWarehouse.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindCountryTwoLettersISOCode) {
				qPos.add(countryTwoLettersISOCode);
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

	private static final String _FINDER_COLUMN_COUNTRYTWOLETTERSISOCODE_COUNTRYTWOLETTERSISOCODE_1 =
		"commerceInventoryWarehouse.countryTwoLettersISOCode IS NULL";
	private static final String _FINDER_COLUMN_COUNTRYTWOLETTERSISOCODE_COUNTRYTWOLETTERSISOCODE_2 =
		"commerceInventoryWarehouse.countryTwoLettersISOCode = ?";
	private static final String _FINDER_COLUMN_COUNTRYTWOLETTERSISOCODE_COUNTRYTWOLETTERSISOCODE_3 =
		"(commerceInventoryWarehouse.countryTwoLettersISOCode IS NULL OR commerceInventoryWarehouse.countryTwoLettersISOCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_A_C = new FinderPath(CommerceInventoryWarehouseModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryWarehouseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByA_C",
			new String[] {
				Boolean.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_C = new FinderPath(CommerceInventoryWarehouseModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryWarehouseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByA_C",
			new String[] { Boolean.class.getName(), String.class.getName() },
			CommerceInventoryWarehouseModelImpl.ACTIVE_COLUMN_BITMASK |
			CommerceInventoryWarehouseModelImpl.COUNTRYTWOLETTERSISOCODE_COLUMN_BITMASK |
			CommerceInventoryWarehouseModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_A_C = new FinderPath(CommerceInventoryWarehouseModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByA_C",
			new String[] { Boolean.class.getName(), String.class.getName() });

	/**
	 * Returns all the commerce inventory warehouses where active = &#63; and countryTwoLettersISOCode = &#63;.
	 *
	 * @param active the active
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @return the matching commerce inventory warehouses
	 */
	@Override
	public List<CommerceInventoryWarehouse> findByA_C(boolean active,
		String countryTwoLettersISOCode) {
		return findByA_C(active, countryTwoLettersISOCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory warehouses where active = &#63; and countryTwoLettersISOCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param active the active
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @param start the lower bound of the range of commerce inventory warehouses
	 * @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	 * @return the range of matching commerce inventory warehouses
	 */
	@Override
	public List<CommerceInventoryWarehouse> findByA_C(boolean active,
		String countryTwoLettersISOCode, int start, int end) {
		return findByA_C(active, countryTwoLettersISOCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouses where active = &#63; and countryTwoLettersISOCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param active the active
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @param start the lower bound of the range of commerce inventory warehouses
	 * @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory warehouses
	 */
	@Override
	public List<CommerceInventoryWarehouse> findByA_C(boolean active,
		String countryTwoLettersISOCode, int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		return findByA_C(active, countryTwoLettersISOCode, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouses where active = &#63; and countryTwoLettersISOCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param active the active
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @param start the lower bound of the range of commerce inventory warehouses
	 * @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce inventory warehouses
	 */
	@Override
	public List<CommerceInventoryWarehouse> findByA_C(boolean active,
		String countryTwoLettersISOCode, int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_C;
			finderArgs = new Object[] { active, countryTwoLettersISOCode };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_A_C;
			finderArgs = new Object[] {
					active, countryTwoLettersISOCode,
					
					start, end, orderByComparator
				};
		}

		List<CommerceInventoryWarehouse> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceInventoryWarehouse>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceInventoryWarehouse commerceInventoryWarehouse : list) {
					if ((active != commerceInventoryWarehouse.isActive()) ||
							!Objects.equals(countryTwoLettersISOCode,
								commerceInventoryWarehouse.getCountryTwoLettersISOCode())) {
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

			query.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_WHERE);

			query.append(_FINDER_COLUMN_A_C_ACTIVE_2);

			boolean bindCountryTwoLettersISOCode = false;

			if (countryTwoLettersISOCode == null) {
				query.append(_FINDER_COLUMN_A_C_COUNTRYTWOLETTERSISOCODE_1);
			}
			else if (countryTwoLettersISOCode.equals("")) {
				query.append(_FINDER_COLUMN_A_C_COUNTRYTWOLETTERSISOCODE_3);
			}
			else {
				bindCountryTwoLettersISOCode = true;

				query.append(_FINDER_COLUMN_A_C_COUNTRYTWOLETTERSISOCODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceInventoryWarehouseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(active);

				if (bindCountryTwoLettersISOCode) {
					qPos.add(countryTwoLettersISOCode);
				}

				if (!pagination) {
					list = (List<CommerceInventoryWarehouse>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceInventoryWarehouse>)QueryUtil.list(q,
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
	 * Returns the first commerce inventory warehouse in the ordered set where active = &#63; and countryTwoLettersISOCode = &#63;.
	 *
	 * @param active the active
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse
	 * @throws NoSuchInventoryWarehouseException if a matching commerce inventory warehouse could not be found
	 */
	@Override
	public CommerceInventoryWarehouse findByA_C_First(boolean active,
		String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws NoSuchInventoryWarehouseException {
		CommerceInventoryWarehouse commerceInventoryWarehouse = fetchByA_C_First(active,
				countryTwoLettersISOCode, orderByComparator);

		if (commerceInventoryWarehouse != null) {
			return commerceInventoryWarehouse;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("active=");
		msg.append(active);

		msg.append(", countryTwoLettersISOCode=");
		msg.append(countryTwoLettersISOCode);

		msg.append("}");

		throw new NoSuchInventoryWarehouseException(msg.toString());
	}

	/**
	 * Returns the first commerce inventory warehouse in the ordered set where active = &#63; and countryTwoLettersISOCode = &#63;.
	 *
	 * @param active the active
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	 */
	@Override
	public CommerceInventoryWarehouse fetchByA_C_First(boolean active,
		String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		List<CommerceInventoryWarehouse> list = findByA_C(active,
				countryTwoLettersISOCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce inventory warehouse in the ordered set where active = &#63; and countryTwoLettersISOCode = &#63;.
	 *
	 * @param active the active
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse
	 * @throws NoSuchInventoryWarehouseException if a matching commerce inventory warehouse could not be found
	 */
	@Override
	public CommerceInventoryWarehouse findByA_C_Last(boolean active,
		String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws NoSuchInventoryWarehouseException {
		CommerceInventoryWarehouse commerceInventoryWarehouse = fetchByA_C_Last(active,
				countryTwoLettersISOCode, orderByComparator);

		if (commerceInventoryWarehouse != null) {
			return commerceInventoryWarehouse;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("active=");
		msg.append(active);

		msg.append(", countryTwoLettersISOCode=");
		msg.append(countryTwoLettersISOCode);

		msg.append("}");

		throw new NoSuchInventoryWarehouseException(msg.toString());
	}

	/**
	 * Returns the last commerce inventory warehouse in the ordered set where active = &#63; and countryTwoLettersISOCode = &#63;.
	 *
	 * @param active the active
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	 */
	@Override
	public CommerceInventoryWarehouse fetchByA_C_Last(boolean active,
		String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		int count = countByA_C(active, countryTwoLettersISOCode);

		if (count == 0) {
			return null;
		}

		List<CommerceInventoryWarehouse> list = findByA_C(active,
				countryTwoLettersISOCode, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce inventory warehouses before and after the current commerce inventory warehouse in the ordered set where active = &#63; and countryTwoLettersISOCode = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the primary key of the current commerce inventory warehouse
	 * @param active the active
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory warehouse
	 * @throws NoSuchInventoryWarehouseException if a commerce inventory warehouse with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouse[] findByA_C_PrevAndNext(
		long commerceInventoryWarehouseId, boolean active,
		String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws NoSuchInventoryWarehouseException {
		CommerceInventoryWarehouse commerceInventoryWarehouse = findByPrimaryKey(commerceInventoryWarehouseId);

		Session session = null;

		try {
			session = openSession();

			CommerceInventoryWarehouse[] array = new CommerceInventoryWarehouseImpl[3];

			array[0] = getByA_C_PrevAndNext(session,
					commerceInventoryWarehouse, active,
					countryTwoLettersISOCode, orderByComparator, true);

			array[1] = commerceInventoryWarehouse;

			array[2] = getByA_C_PrevAndNext(session,
					commerceInventoryWarehouse, active,
					countryTwoLettersISOCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceInventoryWarehouse getByA_C_PrevAndNext(Session session,
		CommerceInventoryWarehouse commerceInventoryWarehouse, boolean active,
		String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_WHERE);

		query.append(_FINDER_COLUMN_A_C_ACTIVE_2);

		boolean bindCountryTwoLettersISOCode = false;

		if (countryTwoLettersISOCode == null) {
			query.append(_FINDER_COLUMN_A_C_COUNTRYTWOLETTERSISOCODE_1);
		}
		else if (countryTwoLettersISOCode.equals("")) {
			query.append(_FINDER_COLUMN_A_C_COUNTRYTWOLETTERSISOCODE_3);
		}
		else {
			bindCountryTwoLettersISOCode = true;

			query.append(_FINDER_COLUMN_A_C_COUNTRYTWOLETTERSISOCODE_2);
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
			query.append(CommerceInventoryWarehouseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(active);

		if (bindCountryTwoLettersISOCode) {
			qPos.add(countryTwoLettersISOCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceInventoryWarehouse);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceInventoryWarehouse> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the commerce inventory warehouses that the user has permission to view where active = &#63; and countryTwoLettersISOCode = &#63;.
	 *
	 * @param active the active
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @return the matching commerce inventory warehouses that the user has permission to view
	 */
	@Override
	public List<CommerceInventoryWarehouse> filterFindByA_C(boolean active,
		String countryTwoLettersISOCode) {
		return filterFindByA_C(active, countryTwoLettersISOCode,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory warehouses that the user has permission to view where active = &#63; and countryTwoLettersISOCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param active the active
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @param start the lower bound of the range of commerce inventory warehouses
	 * @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	 * @return the range of matching commerce inventory warehouses that the user has permission to view
	 */
	@Override
	public List<CommerceInventoryWarehouse> filterFindByA_C(boolean active,
		String countryTwoLettersISOCode, int start, int end) {
		return filterFindByA_C(active, countryTwoLettersISOCode, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouses that the user has permissions to view where active = &#63; and countryTwoLettersISOCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param active the active
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @param start the lower bound of the range of commerce inventory warehouses
	 * @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory warehouses that the user has permission to view
	 */
	@Override
	public List<CommerceInventoryWarehouse> filterFindByA_C(boolean active,
		String countryTwoLettersISOCode, int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByA_C(active, countryTwoLettersISOCode, start, end,
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
			query.append(_FILTER_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_A_C_ACTIVE_2_SQL);

		boolean bindCountryTwoLettersISOCode = false;

		if (countryTwoLettersISOCode == null) {
			query.append(_FINDER_COLUMN_A_C_COUNTRYTWOLETTERSISOCODE_1);
		}
		else if (countryTwoLettersISOCode.equals("")) {
			query.append(_FINDER_COLUMN_A_C_COUNTRYTWOLETTERSISOCODE_3);
		}
		else {
			bindCountryTwoLettersISOCode = true;

			query.append(_FINDER_COLUMN_A_C_COUNTRYTWOLETTERSISOCODE_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CommerceInventoryWarehouseModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceInventoryWarehouseModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceInventoryWarehouse.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS,
					CommerceInventoryWarehouseImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE,
					CommerceInventoryWarehouseImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(active);

			if (bindCountryTwoLettersISOCode) {
				qPos.add(countryTwoLettersISOCode);
			}

			return (List<CommerceInventoryWarehouse>)QueryUtil.list(q,
				getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the commerce inventory warehouses before and after the current commerce inventory warehouse in the ordered set of commerce inventory warehouses that the user has permission to view where active = &#63; and countryTwoLettersISOCode = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the primary key of the current commerce inventory warehouse
	 * @param active the active
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory warehouse
	 * @throws NoSuchInventoryWarehouseException if a commerce inventory warehouse with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouse[] filterFindByA_C_PrevAndNext(
		long commerceInventoryWarehouseId, boolean active,
		String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator)
		throws NoSuchInventoryWarehouseException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByA_C_PrevAndNext(commerceInventoryWarehouseId, active,
				countryTwoLettersISOCode, orderByComparator);
		}

		CommerceInventoryWarehouse commerceInventoryWarehouse = findByPrimaryKey(commerceInventoryWarehouseId);

		Session session = null;

		try {
			session = openSession();

			CommerceInventoryWarehouse[] array = new CommerceInventoryWarehouseImpl[3];

			array[0] = filterGetByA_C_PrevAndNext(session,
					commerceInventoryWarehouse, active,
					countryTwoLettersISOCode, orderByComparator, true);

			array[1] = commerceInventoryWarehouse;

			array[2] = filterGetByA_C_PrevAndNext(session,
					commerceInventoryWarehouse, active,
					countryTwoLettersISOCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceInventoryWarehouse filterGetByA_C_PrevAndNext(
		Session session, CommerceInventoryWarehouse commerceInventoryWarehouse,
		boolean active, String countryTwoLettersISOCode,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator,
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
			query.append(_FILTER_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_A_C_ACTIVE_2_SQL);

		boolean bindCountryTwoLettersISOCode = false;

		if (countryTwoLettersISOCode == null) {
			query.append(_FINDER_COLUMN_A_C_COUNTRYTWOLETTERSISOCODE_1);
		}
		else if (countryTwoLettersISOCode.equals("")) {
			query.append(_FINDER_COLUMN_A_C_COUNTRYTWOLETTERSISOCODE_3);
		}
		else {
			bindCountryTwoLettersISOCode = true;

			query.append(_FINDER_COLUMN_A_C_COUNTRYTWOLETTERSISOCODE_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CommerceInventoryWarehouseModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceInventoryWarehouseModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceInventoryWarehouse.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS,
				CommerceInventoryWarehouseImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE,
				CommerceInventoryWarehouseImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(active);

		if (bindCountryTwoLettersISOCode) {
			qPos.add(countryTwoLettersISOCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceInventoryWarehouse);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceInventoryWarehouse> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce inventory warehouses where active = &#63; and countryTwoLettersISOCode = &#63; from the database.
	 *
	 * @param active the active
	 * @param countryTwoLettersISOCode the country two letters iso code
	 */
	@Override
	public void removeByA_C(boolean active, String countryTwoLettersISOCode) {
		for (CommerceInventoryWarehouse commerceInventoryWarehouse : findByA_C(
				active, countryTwoLettersISOCode, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(commerceInventoryWarehouse);
		}
	}

	/**
	 * Returns the number of commerce inventory warehouses where active = &#63; and countryTwoLettersISOCode = &#63;.
	 *
	 * @param active the active
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @return the number of matching commerce inventory warehouses
	 */
	@Override
	public int countByA_C(boolean active, String countryTwoLettersISOCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_A_C;

		Object[] finderArgs = new Object[] { active, countryTwoLettersISOCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEINVENTORYWAREHOUSE_WHERE);

			query.append(_FINDER_COLUMN_A_C_ACTIVE_2);

			boolean bindCountryTwoLettersISOCode = false;

			if (countryTwoLettersISOCode == null) {
				query.append(_FINDER_COLUMN_A_C_COUNTRYTWOLETTERSISOCODE_1);
			}
			else if (countryTwoLettersISOCode.equals("")) {
				query.append(_FINDER_COLUMN_A_C_COUNTRYTWOLETTERSISOCODE_3);
			}
			else {
				bindCountryTwoLettersISOCode = true;

				query.append(_FINDER_COLUMN_A_C_COUNTRYTWOLETTERSISOCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(active);

				if (bindCountryTwoLettersISOCode) {
					qPos.add(countryTwoLettersISOCode);
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
	 * Returns the number of commerce inventory warehouses that the user has permission to view where active = &#63; and countryTwoLettersISOCode = &#63;.
	 *
	 * @param active the active
	 * @param countryTwoLettersISOCode the country two letters iso code
	 * @return the number of matching commerce inventory warehouses that the user has permission to view
	 */
	@Override
	public int filterCountByA_C(boolean active, String countryTwoLettersISOCode) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByA_C(active, countryTwoLettersISOCode);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_COMMERCEINVENTORYWAREHOUSE_WHERE);

		query.append(_FINDER_COLUMN_A_C_ACTIVE_2_SQL);

		boolean bindCountryTwoLettersISOCode = false;

		if (countryTwoLettersISOCode == null) {
			query.append(_FINDER_COLUMN_A_C_COUNTRYTWOLETTERSISOCODE_1);
		}
		else if (countryTwoLettersISOCode.equals("")) {
			query.append(_FINDER_COLUMN_A_C_COUNTRYTWOLETTERSISOCODE_3);
		}
		else {
			bindCountryTwoLettersISOCode = true;

			query.append(_FINDER_COLUMN_A_C_COUNTRYTWOLETTERSISOCODE_2);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceInventoryWarehouse.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(active);

			if (bindCountryTwoLettersISOCode) {
				qPos.add(countryTwoLettersISOCode);
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

	private static final String _FINDER_COLUMN_A_C_ACTIVE_2 = "commerceInventoryWarehouse.active = ? AND ";
	private static final String _FINDER_COLUMN_A_C_ACTIVE_2_SQL = "commerceInventoryWarehouse.active_ = ? AND ";
	private static final String _FINDER_COLUMN_A_C_COUNTRYTWOLETTERSISOCODE_1 = "commerceInventoryWarehouse.countryTwoLettersISOCode IS NULL";
	private static final String _FINDER_COLUMN_A_C_COUNTRYTWOLETTERSISOCODE_2 = "commerceInventoryWarehouse.countryTwoLettersISOCode = ?";
	private static final String _FINDER_COLUMN_A_C_COUNTRYTWOLETTERSISOCODE_3 = "(commerceInventoryWarehouse.countryTwoLettersISOCode IS NULL OR commerceInventoryWarehouse.countryTwoLettersISOCode = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_C_ERC = new FinderPath(CommerceInventoryWarehouseModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryWarehouseImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_ERC",
			new String[] { Long.class.getName(), String.class.getName() },
			CommerceInventoryWarehouseModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceInventoryWarehouseModelImpl.EXTERNALREFERENCECODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_ERC = new FinderPath(CommerceInventoryWarehouseModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByC_ERC",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the commerce inventory warehouse where companyId = &#63; and externalReferenceCode = &#63; or throws a {@link NoSuchInventoryWarehouseException} if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce inventory warehouse
	 * @throws NoSuchInventoryWarehouseException if a matching commerce inventory warehouse could not be found
	 */
	@Override
	public CommerceInventoryWarehouse findByC_ERC(long companyId,
		String externalReferenceCode) throws NoSuchInventoryWarehouseException {
		CommerceInventoryWarehouse commerceInventoryWarehouse = fetchByC_ERC(companyId,
				externalReferenceCode);

		if (commerceInventoryWarehouse == null) {
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

			throw new NoSuchInventoryWarehouseException(msg.toString());
		}

		return commerceInventoryWarehouse;
	}

	/**
	 * Returns the commerce inventory warehouse where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	 */
	@Override
	public CommerceInventoryWarehouse fetchByC_ERC(long companyId,
		String externalReferenceCode) {
		return fetchByC_ERC(companyId, externalReferenceCode, true);
	}

	/**
	 * Returns the commerce inventory warehouse where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching commerce inventory warehouse, or <code>null</code> if a matching commerce inventory warehouse could not be found
	 */
	@Override
	public CommerceInventoryWarehouse fetchByC_ERC(long companyId,
		String externalReferenceCode, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { companyId, externalReferenceCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_C_ERC,
					finderArgs, this);
		}

		if (result instanceof CommerceInventoryWarehouse) {
			CommerceInventoryWarehouse commerceInventoryWarehouse = (CommerceInventoryWarehouse)result;

			if ((companyId != commerceInventoryWarehouse.getCompanyId()) ||
					!Objects.equals(externalReferenceCode,
						commerceInventoryWarehouse.getExternalReferenceCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_WHERE);

			query.append(_FINDER_COLUMN_C_ERC_COMPANYID_2);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode == null) {
				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_1);
			}
			else if (externalReferenceCode.equals("")) {
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

				List<CommerceInventoryWarehouse> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_C_ERC,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"CommerceInventoryWarehousePersistenceImpl.fetchByC_ERC(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CommerceInventoryWarehouse commerceInventoryWarehouse = list.get(0);

					result = commerceInventoryWarehouse;

					cacheResult(commerceInventoryWarehouse);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_C_ERC, finderArgs);

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
			return (CommerceInventoryWarehouse)result;
		}
	}

	/**
	 * Removes the commerce inventory warehouse where companyId = &#63; and externalReferenceCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the commerce inventory warehouse that was removed
	 */
	@Override
	public CommerceInventoryWarehouse removeByC_ERC(long companyId,
		String externalReferenceCode) throws NoSuchInventoryWarehouseException {
		CommerceInventoryWarehouse commerceInventoryWarehouse = findByC_ERC(companyId,
				externalReferenceCode);

		return remove(commerceInventoryWarehouse);
	}

	/**
	 * Returns the number of commerce inventory warehouses where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the number of matching commerce inventory warehouses
	 */
	@Override
	public int countByC_ERC(long companyId, String externalReferenceCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_ERC;

		Object[] finderArgs = new Object[] { companyId, externalReferenceCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEINVENTORYWAREHOUSE_WHERE);

			query.append(_FINDER_COLUMN_C_ERC_COMPANYID_2);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode == null) {
				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_1);
			}
			else if (externalReferenceCode.equals("")) {
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

	private static final String _FINDER_COLUMN_C_ERC_COMPANYID_2 = "commerceInventoryWarehouse.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_1 = "commerceInventoryWarehouse.externalReferenceCode IS NULL";
	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2 = "commerceInventoryWarehouse.externalReferenceCode = ?";
	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3 = "(commerceInventoryWarehouse.externalReferenceCode IS NULL OR commerceInventoryWarehouse.externalReferenceCode = '')";

	public CommerceInventoryWarehousePersistenceImpl() {
		setModelClass(CommerceInventoryWarehouse.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("commerceInventoryWarehouseId", "CIWarehouseId");
			dbColumnNames.put("active", "active_");
			dbColumnNames.put("type", "type_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the commerce inventory warehouse in the entity cache if it is enabled.
	 *
	 * @param commerceInventoryWarehouse the commerce inventory warehouse
	 */
	@Override
	public void cacheResult(
		CommerceInventoryWarehouse commerceInventoryWarehouse) {
		entityCache.putResult(CommerceInventoryWarehouseModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseImpl.class,
			commerceInventoryWarehouse.getPrimaryKey(),
			commerceInventoryWarehouse);

		finderCache.putResult(FINDER_PATH_FETCH_BY_C_ERC,
			new Object[] {
				commerceInventoryWarehouse.getCompanyId(),
				commerceInventoryWarehouse.getExternalReferenceCode()
			}, commerceInventoryWarehouse);

		commerceInventoryWarehouse.resetOriginalValues();
	}

	/**
	 * Caches the commerce inventory warehouses in the entity cache if it is enabled.
	 *
	 * @param commerceInventoryWarehouses the commerce inventory warehouses
	 */
	@Override
	public void cacheResult(
		List<CommerceInventoryWarehouse> commerceInventoryWarehouses) {
		for (CommerceInventoryWarehouse commerceInventoryWarehouse : commerceInventoryWarehouses) {
			if (entityCache.getResult(
						CommerceInventoryWarehouseModelImpl.ENTITY_CACHE_ENABLED,
						CommerceInventoryWarehouseImpl.class,
						commerceInventoryWarehouse.getPrimaryKey()) == null) {
				cacheResult(commerceInventoryWarehouse);
			}
			else {
				commerceInventoryWarehouse.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce inventory warehouses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceInventoryWarehouseImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce inventory warehouse.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CommerceInventoryWarehouse commerceInventoryWarehouse) {
		entityCache.removeResult(CommerceInventoryWarehouseModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseImpl.class,
			commerceInventoryWarehouse.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CommerceInventoryWarehouseModelImpl)commerceInventoryWarehouse,
			true);
	}

	@Override
	public void clearCache(
		List<CommerceInventoryWarehouse> commerceInventoryWarehouses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceInventoryWarehouse commerceInventoryWarehouse : commerceInventoryWarehouses) {
			entityCache.removeResult(CommerceInventoryWarehouseModelImpl.ENTITY_CACHE_ENABLED,
				CommerceInventoryWarehouseImpl.class,
				commerceInventoryWarehouse.getPrimaryKey());

			clearUniqueFindersCache((CommerceInventoryWarehouseModelImpl)commerceInventoryWarehouse,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		CommerceInventoryWarehouseModelImpl commerceInventoryWarehouseModelImpl) {
		Object[] args = new Object[] {
				commerceInventoryWarehouseModelImpl.getCompanyId(),
				commerceInventoryWarehouseModelImpl.getExternalReferenceCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_C_ERC, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_C_ERC, args,
			commerceInventoryWarehouseModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CommerceInventoryWarehouseModelImpl commerceInventoryWarehouseModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					commerceInventoryWarehouseModelImpl.getCompanyId(),
					commerceInventoryWarehouseModelImpl.getExternalReferenceCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_ERC, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_ERC, args);
		}

		if ((commerceInventoryWarehouseModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_ERC.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					commerceInventoryWarehouseModelImpl.getOriginalCompanyId(),
					commerceInventoryWarehouseModelImpl.getOriginalExternalReferenceCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_ERC, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_ERC, args);
		}
	}

	/**
	 * Creates a new commerce inventory warehouse with the primary key. Does not add the commerce inventory warehouse to the database.
	 *
	 * @param commerceInventoryWarehouseId the primary key for the new commerce inventory warehouse
	 * @return the new commerce inventory warehouse
	 */
	@Override
	public CommerceInventoryWarehouse create(long commerceInventoryWarehouseId) {
		CommerceInventoryWarehouse commerceInventoryWarehouse = new CommerceInventoryWarehouseImpl();

		commerceInventoryWarehouse.setNew(true);
		commerceInventoryWarehouse.setPrimaryKey(commerceInventoryWarehouseId);

		commerceInventoryWarehouse.setCompanyId(companyProvider.getCompanyId());

		return commerceInventoryWarehouse;
	}

	/**
	 * Removes the commerce inventory warehouse with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceInventoryWarehouseId the primary key of the commerce inventory warehouse
	 * @return the commerce inventory warehouse that was removed
	 * @throws NoSuchInventoryWarehouseException if a commerce inventory warehouse with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouse remove(long commerceInventoryWarehouseId)
		throws NoSuchInventoryWarehouseException {
		return remove((Serializable)commerceInventoryWarehouseId);
	}

	/**
	 * Removes the commerce inventory warehouse with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce inventory warehouse
	 * @return the commerce inventory warehouse that was removed
	 * @throws NoSuchInventoryWarehouseException if a commerce inventory warehouse with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouse remove(Serializable primaryKey)
		throws NoSuchInventoryWarehouseException {
		Session session = null;

		try {
			session = openSession();

			CommerceInventoryWarehouse commerceInventoryWarehouse = (CommerceInventoryWarehouse)session.get(CommerceInventoryWarehouseImpl.class,
					primaryKey);

			if (commerceInventoryWarehouse == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchInventoryWarehouseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commerceInventoryWarehouse);
		}
		catch (NoSuchInventoryWarehouseException nsee) {
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
	protected CommerceInventoryWarehouse removeImpl(
		CommerceInventoryWarehouse commerceInventoryWarehouse) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceInventoryWarehouse)) {
				commerceInventoryWarehouse = (CommerceInventoryWarehouse)session.get(CommerceInventoryWarehouseImpl.class,
						commerceInventoryWarehouse.getPrimaryKeyObj());
			}

			if (commerceInventoryWarehouse != null) {
				session.delete(commerceInventoryWarehouse);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceInventoryWarehouse != null) {
			clearCache(commerceInventoryWarehouse);
		}

		return commerceInventoryWarehouse;
	}

	@Override
	public CommerceInventoryWarehouse updateImpl(
		CommerceInventoryWarehouse commerceInventoryWarehouse) {
		boolean isNew = commerceInventoryWarehouse.isNew();

		if (!(commerceInventoryWarehouse instanceof CommerceInventoryWarehouseModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceInventoryWarehouse.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(commerceInventoryWarehouse);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceInventoryWarehouse proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceInventoryWarehouse implementation " +
				commerceInventoryWarehouse.getClass());
		}

		CommerceInventoryWarehouseModelImpl commerceInventoryWarehouseModelImpl = (CommerceInventoryWarehouseModelImpl)commerceInventoryWarehouse;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceInventoryWarehouse.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceInventoryWarehouse.setCreateDate(now);
			}
			else {
				commerceInventoryWarehouse.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!commerceInventoryWarehouseModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceInventoryWarehouse.setModifiedDate(now);
			}
			else {
				commerceInventoryWarehouse.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceInventoryWarehouse.isNew()) {
				session.save(commerceInventoryWarehouse);

				commerceInventoryWarehouse.setNew(false);
			}
			else {
				commerceInventoryWarehouse = (CommerceInventoryWarehouse)session.merge(commerceInventoryWarehouse);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceInventoryWarehouseModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					commerceInventoryWarehouseModelImpl.isActive()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIVE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE,
				args);

			args = new Object[] {
					commerceInventoryWarehouseModelImpl.getCountryTwoLettersISOCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COUNTRYTWOLETTERSISOCODE,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COUNTRYTWOLETTERSISOCODE,
				args);

			args = new Object[] {
					commerceInventoryWarehouseModelImpl.isActive(),
					commerceInventoryWarehouseModelImpl.getCountryTwoLettersISOCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_A_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_C,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((commerceInventoryWarehouseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceInventoryWarehouseModelImpl.getOriginalActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIVE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE,
					args);

				args = new Object[] {
						commerceInventoryWarehouseModelImpl.isActive()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIVE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE,
					args);
			}

			if ((commerceInventoryWarehouseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COUNTRYTWOLETTERSISOCODE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceInventoryWarehouseModelImpl.getOriginalCountryTwoLettersISOCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COUNTRYTWOLETTERSISOCODE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COUNTRYTWOLETTERSISOCODE,
					args);

				args = new Object[] {
						commerceInventoryWarehouseModelImpl.getCountryTwoLettersISOCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COUNTRYTWOLETTERSISOCODE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COUNTRYTWOLETTERSISOCODE,
					args);
			}

			if ((commerceInventoryWarehouseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceInventoryWarehouseModelImpl.getOriginalActive(),
						commerceInventoryWarehouseModelImpl.getOriginalCountryTwoLettersISOCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_A_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_C,
					args);

				args = new Object[] {
						commerceInventoryWarehouseModelImpl.isActive(),
						commerceInventoryWarehouseModelImpl.getCountryTwoLettersISOCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_A_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_C,
					args);
			}
		}

		entityCache.putResult(CommerceInventoryWarehouseModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseImpl.class,
			commerceInventoryWarehouse.getPrimaryKey(),
			commerceInventoryWarehouse, false);

		clearUniqueFindersCache(commerceInventoryWarehouseModelImpl, false);
		cacheUniqueFindersCache(commerceInventoryWarehouseModelImpl);

		commerceInventoryWarehouse.resetOriginalValues();

		return commerceInventoryWarehouse;
	}

	/**
	 * Returns the commerce inventory warehouse with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce inventory warehouse
	 * @return the commerce inventory warehouse
	 * @throws NoSuchInventoryWarehouseException if a commerce inventory warehouse with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouse findByPrimaryKey(Serializable primaryKey)
		throws NoSuchInventoryWarehouseException {
		CommerceInventoryWarehouse commerceInventoryWarehouse = fetchByPrimaryKey(primaryKey);

		if (commerceInventoryWarehouse == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchInventoryWarehouseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commerceInventoryWarehouse;
	}

	/**
	 * Returns the commerce inventory warehouse with the primary key or throws a {@link NoSuchInventoryWarehouseException} if it could not be found.
	 *
	 * @param commerceInventoryWarehouseId the primary key of the commerce inventory warehouse
	 * @return the commerce inventory warehouse
	 * @throws NoSuchInventoryWarehouseException if a commerce inventory warehouse with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouse findByPrimaryKey(
		long commerceInventoryWarehouseId)
		throws NoSuchInventoryWarehouseException {
		return findByPrimaryKey((Serializable)commerceInventoryWarehouseId);
	}

	/**
	 * Returns the commerce inventory warehouse with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce inventory warehouse
	 * @return the commerce inventory warehouse, or <code>null</code> if a commerce inventory warehouse with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouse fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CommerceInventoryWarehouseModelImpl.ENTITY_CACHE_ENABLED,
				CommerceInventoryWarehouseImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceInventoryWarehouse commerceInventoryWarehouse = (CommerceInventoryWarehouse)serializable;

		if (commerceInventoryWarehouse == null) {
			Session session = null;

			try {
				session = openSession();

				commerceInventoryWarehouse = (CommerceInventoryWarehouse)session.get(CommerceInventoryWarehouseImpl.class,
						primaryKey);

				if (commerceInventoryWarehouse != null) {
					cacheResult(commerceInventoryWarehouse);
				}
				else {
					entityCache.putResult(CommerceInventoryWarehouseModelImpl.ENTITY_CACHE_ENABLED,
						CommerceInventoryWarehouseImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommerceInventoryWarehouseModelImpl.ENTITY_CACHE_ENABLED,
					CommerceInventoryWarehouseImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceInventoryWarehouse;
	}

	/**
	 * Returns the commerce inventory warehouse with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceInventoryWarehouseId the primary key of the commerce inventory warehouse
	 * @return the commerce inventory warehouse, or <code>null</code> if a commerce inventory warehouse with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouse fetchByPrimaryKey(
		long commerceInventoryWarehouseId) {
		return fetchByPrimaryKey((Serializable)commerceInventoryWarehouseId);
	}

	@Override
	public Map<Serializable, CommerceInventoryWarehouse> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceInventoryWarehouse> map = new HashMap<Serializable, CommerceInventoryWarehouse>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceInventoryWarehouse commerceInventoryWarehouse = fetchByPrimaryKey(primaryKey);

			if (commerceInventoryWarehouse != null) {
				map.put(primaryKey, commerceInventoryWarehouse);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CommerceInventoryWarehouseModelImpl.ENTITY_CACHE_ENABLED,
					CommerceInventoryWarehouseImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceInventoryWarehouse)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_WHERE_PKS_IN);

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

			for (CommerceInventoryWarehouse commerceInventoryWarehouse : (List<CommerceInventoryWarehouse>)q.list()) {
				map.put(commerceInventoryWarehouse.getPrimaryKeyObj(),
					commerceInventoryWarehouse);

				cacheResult(commerceInventoryWarehouse);

				uncachedPrimaryKeys.remove(commerceInventoryWarehouse.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CommerceInventoryWarehouseModelImpl.ENTITY_CACHE_ENABLED,
					CommerceInventoryWarehouseImpl.class, primaryKey, nullModel);
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
	 * Returns all the commerce inventory warehouses.
	 *
	 * @return the commerce inventory warehouses
	 */
	@Override
	public List<CommerceInventoryWarehouse> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory warehouses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory warehouses
	 * @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	 * @return the range of commerce inventory warehouses
	 */
	@Override
	public List<CommerceInventoryWarehouse> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory warehouses
	 * @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce inventory warehouses
	 */
	@Override
	public List<CommerceInventoryWarehouse> findAll(int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory warehouses
	 * @param end the upper bound of the range of commerce inventory warehouses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce inventory warehouses
	 */
	@Override
	public List<CommerceInventoryWarehouse> findAll(int start, int end,
		OrderByComparator<CommerceInventoryWarehouse> orderByComparator,
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

		List<CommerceInventoryWarehouse> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceInventoryWarehouse>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEINVENTORYWAREHOUSE;

				if (pagination) {
					sql = sql.concat(CommerceInventoryWarehouseModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceInventoryWarehouse>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceInventoryWarehouse>)QueryUtil.list(q,
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
	 * Removes all the commerce inventory warehouses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceInventoryWarehouse commerceInventoryWarehouse : findAll()) {
			remove(commerceInventoryWarehouse);
		}
	}

	/**
	 * Returns the number of commerce inventory warehouses.
	 *
	 * @return the number of commerce inventory warehouses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCEINVENTORYWAREHOUSE);

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
		return CommerceInventoryWarehouseModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce inventory warehouse persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CommerceInventoryWarehouseImpl.class.getName());
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
	private static final String _SQL_SELECT_COMMERCEINVENTORYWAREHOUSE = "SELECT commerceInventoryWarehouse FROM CommerceInventoryWarehouse commerceInventoryWarehouse";
	private static final String _SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_WHERE_PKS_IN =
		"SELECT commerceInventoryWarehouse FROM CommerceInventoryWarehouse commerceInventoryWarehouse WHERE CIWarehouseId IN (";
	private static final String _SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_WHERE = "SELECT commerceInventoryWarehouse FROM CommerceInventoryWarehouse commerceInventoryWarehouse WHERE ";
	private static final String _SQL_COUNT_COMMERCEINVENTORYWAREHOUSE = "SELECT COUNT(commerceInventoryWarehouse) FROM CommerceInventoryWarehouse commerceInventoryWarehouse";
	private static final String _SQL_COUNT_COMMERCEINVENTORYWAREHOUSE_WHERE = "SELECT COUNT(commerceInventoryWarehouse) FROM CommerceInventoryWarehouse commerceInventoryWarehouse WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "commerceInventoryWarehouse.CIWarehouseId";
	private static final String _FILTER_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_WHERE =
		"SELECT DISTINCT {commerceInventoryWarehouse.*} FROM CIWarehouse commerceInventoryWarehouse WHERE ";
	private static final String _FILTER_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {CIWarehouse.*} FROM (SELECT DISTINCT commerceInventoryWarehouse.CIWarehouseId FROM CIWarehouse commerceInventoryWarehouse WHERE ";
	private static final String _FILTER_SQL_SELECT_COMMERCEINVENTORYWAREHOUSE_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN CIWarehouse ON TEMP_TABLE.CIWarehouseId = CIWarehouse.CIWarehouseId";
	private static final String _FILTER_SQL_COUNT_COMMERCEINVENTORYWAREHOUSE_WHERE =
		"SELECT COUNT(DISTINCT commerceInventoryWarehouse.CIWarehouseId) AS COUNT_VALUE FROM CIWarehouse commerceInventoryWarehouse WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "commerceInventoryWarehouse";
	private static final String _FILTER_ENTITY_TABLE = "CIWarehouse";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceInventoryWarehouse.";
	private static final String _ORDER_BY_ENTITY_TABLE = "CIWarehouse.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommerceInventoryWarehouse exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CommerceInventoryWarehouse exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CommerceInventoryWarehousePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"commerceInventoryWarehouseId", "active", "type"
			});
}