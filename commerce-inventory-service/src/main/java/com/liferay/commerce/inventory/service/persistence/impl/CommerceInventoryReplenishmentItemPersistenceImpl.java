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

import com.liferay.commerce.inventory.exception.NoSuchInventoryReplenishmentItemException;
import com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem;
import com.liferay.commerce.inventory.model.impl.CommerceInventoryReplenishmentItemImpl;
import com.liferay.commerce.inventory.model.impl.CommerceInventoryReplenishmentItemModelImpl;
import com.liferay.commerce.inventory.service.persistence.CommerceInventoryReplenishmentItemPersistence;

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
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.sql.Timestamp;

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
 * The persistence implementation for the commerce inventory replenishment item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryReplenishmentItemPersistence
 * @see com.liferay.commerce.inventory.service.persistence.CommerceInventoryReplenishmentItemUtil
 * @generated
 */
@ProviderType
public class CommerceInventoryReplenishmentItemPersistenceImpl
	extends BasePersistenceImpl<CommerceInventoryReplenishmentItem>
	implements CommerceInventoryReplenishmentItemPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommerceInventoryReplenishmentItemUtil} to access the commerce inventory replenishment item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommerceInventoryReplenishmentItemImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMMERCEWAREHOUSEID =
		new FinderPath(CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCommerceWarehouseId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEWAREHOUSEID =
		new FinderPath(CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCommerceWarehouseId", new String[] { Long.class.getName() },
			CommerceInventoryReplenishmentItemModelImpl.COMMERCEWAREHOUSEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMMERCEWAREHOUSEID = new FinderPath(CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceWarehouseId", new String[] { Long.class.getName() });

	/**
	 * Returns all the commerce inventory replenishment items where commerceWarehouseId = &#63;.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @return the matching commerce inventory replenishment items
	 */
	@Override
	public List<CommerceInventoryReplenishmentItem> findByCommerceWarehouseId(
		long commerceWarehouseId) {
		return findByCommerceWarehouseId(commerceWarehouseId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory replenishment items where commerceWarehouseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @return the range of matching commerce inventory replenishment items
	 */
	@Override
	public List<CommerceInventoryReplenishmentItem> findByCommerceWarehouseId(
		long commerceWarehouseId, int start, int end) {
		return findByCommerceWarehouseId(commerceWarehouseId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory replenishment items where commerceWarehouseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory replenishment items
	 */
	@Override
	public List<CommerceInventoryReplenishmentItem> findByCommerceWarehouseId(
		long commerceWarehouseId, int start, int end,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator) {
		return findByCommerceWarehouseId(commerceWarehouseId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory replenishment items where commerceWarehouseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce inventory replenishment items
	 */
	@Override
	public List<CommerceInventoryReplenishmentItem> findByCommerceWarehouseId(
		long commerceWarehouseId, int start, int end,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEWAREHOUSEID;
			finderArgs = new Object[] { commerceWarehouseId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMMERCEWAREHOUSEID;
			finderArgs = new Object[] {
					commerceWarehouseId,
					
					start, end, orderByComparator
				};
		}

		List<CommerceInventoryReplenishmentItem> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceInventoryReplenishmentItem>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem : list) {
					if ((commerceWarehouseId != commerceInventoryReplenishmentItem.getCommerceWarehouseId())) {
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

			query.append(_SQL_SELECT_COMMERCEINVENTORYREPLENISHMENTITEM_WHERE);

			query.append(_FINDER_COLUMN_COMMERCEWAREHOUSEID_COMMERCEWAREHOUSEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceInventoryReplenishmentItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceWarehouseId);

				if (!pagination) {
					list = (List<CommerceInventoryReplenishmentItem>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceInventoryReplenishmentItem>)QueryUtil.list(q,
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
	 * Returns the first commerce inventory replenishment item in the ordered set where commerceWarehouseId = &#63;.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a matching commerce inventory replenishment item could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem findByCommerceWarehouseId_First(
		long commerceWarehouseId,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator)
		throws NoSuchInventoryReplenishmentItemException {
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem = fetchByCommerceWarehouseId_First(commerceWarehouseId,
				orderByComparator);

		if (commerceInventoryReplenishmentItem != null) {
			return commerceInventoryReplenishmentItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceWarehouseId=");
		msg.append(commerceWarehouseId);

		msg.append("}");

		throw new NoSuchInventoryReplenishmentItemException(msg.toString());
	}

	/**
	 * Returns the first commerce inventory replenishment item in the ordered set where commerceWarehouseId = &#63;.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem fetchByCommerceWarehouseId_First(
		long commerceWarehouseId,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator) {
		List<CommerceInventoryReplenishmentItem> list = findByCommerceWarehouseId(commerceWarehouseId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce inventory replenishment item in the ordered set where commerceWarehouseId = &#63;.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a matching commerce inventory replenishment item could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem findByCommerceWarehouseId_Last(
		long commerceWarehouseId,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator)
		throws NoSuchInventoryReplenishmentItemException {
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem = fetchByCommerceWarehouseId_Last(commerceWarehouseId,
				orderByComparator);

		if (commerceInventoryReplenishmentItem != null) {
			return commerceInventoryReplenishmentItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceWarehouseId=");
		msg.append(commerceWarehouseId);

		msg.append("}");

		throw new NoSuchInventoryReplenishmentItemException(msg.toString());
	}

	/**
	 * Returns the last commerce inventory replenishment item in the ordered set where commerceWarehouseId = &#63;.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem fetchByCommerceWarehouseId_Last(
		long commerceWarehouseId,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator) {
		int count = countByCommerceWarehouseId(commerceWarehouseId);

		if (count == 0) {
			return null;
		}

		List<CommerceInventoryReplenishmentItem> list = findByCommerceWarehouseId(commerceWarehouseId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce inventory replenishment items before and after the current commerce inventory replenishment item in the ordered set where commerceWarehouseId = &#63;.
	 *
	 * @param commerceInventoryReplenishmentItemId the primary key of the current commerce inventory replenishment item
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a commerce inventory replenishment item with the primary key could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem[] findByCommerceWarehouseId_PrevAndNext(
		long commerceInventoryReplenishmentItemId, long commerceWarehouseId,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator)
		throws NoSuchInventoryReplenishmentItemException {
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem = findByPrimaryKey(commerceInventoryReplenishmentItemId);

		Session session = null;

		try {
			session = openSession();

			CommerceInventoryReplenishmentItem[] array = new CommerceInventoryReplenishmentItemImpl[3];

			array[0] = getByCommerceWarehouseId_PrevAndNext(session,
					commerceInventoryReplenishmentItem, commerceWarehouseId,
					orderByComparator, true);

			array[1] = commerceInventoryReplenishmentItem;

			array[2] = getByCommerceWarehouseId_PrevAndNext(session,
					commerceInventoryReplenishmentItem, commerceWarehouseId,
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

	protected CommerceInventoryReplenishmentItem getByCommerceWarehouseId_PrevAndNext(
		Session session,
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem,
		long commerceWarehouseId,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEINVENTORYREPLENISHMENTITEM_WHERE);

		query.append(_FINDER_COLUMN_COMMERCEWAREHOUSEID_COMMERCEWAREHOUSEID_2);

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
			query.append(CommerceInventoryReplenishmentItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceWarehouseId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceInventoryReplenishmentItem);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceInventoryReplenishmentItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce inventory replenishment items where commerceWarehouseId = &#63; from the database.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 */
	@Override
	public void removeByCommerceWarehouseId(long commerceWarehouseId) {
		for (CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem : findByCommerceWarehouseId(
				commerceWarehouseId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(commerceInventoryReplenishmentItem);
		}
	}

	/**
	 * Returns the number of commerce inventory replenishment items where commerceWarehouseId = &#63;.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @return the number of matching commerce inventory replenishment items
	 */
	@Override
	public int countByCommerceWarehouseId(long commerceWarehouseId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMMERCEWAREHOUSEID;

		Object[] finderArgs = new Object[] { commerceWarehouseId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEINVENTORYREPLENISHMENTITEM_WHERE);

			query.append(_FINDER_COLUMN_COMMERCEWAREHOUSEID_COMMERCEWAREHOUSEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceWarehouseId);

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

	private static final String _FINDER_COLUMN_COMMERCEWAREHOUSEID_COMMERCEWAREHOUSEID_2 =
		"commerceInventoryReplenishmentItem.commerceWarehouseId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SKU = new FinderPath(CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBysku",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SKU = new FinderPath(CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBysku",
			new String[] { String.class.getName() },
			CommerceInventoryReplenishmentItemModelImpl.SKU_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SKU = new FinderPath(CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBysku", new String[] { String.class.getName() });

	/**
	 * Returns all the commerce inventory replenishment items where sku = &#63;.
	 *
	 * @param sku the sku
	 * @return the matching commerce inventory replenishment items
	 */
	@Override
	public List<CommerceInventoryReplenishmentItem> findBysku(String sku) {
		return findBysku(sku, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory replenishment items where sku = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sku the sku
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @return the range of matching commerce inventory replenishment items
	 */
	@Override
	public List<CommerceInventoryReplenishmentItem> findBysku(String sku,
		int start, int end) {
		return findBysku(sku, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory replenishment items where sku = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sku the sku
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory replenishment items
	 */
	@Override
	public List<CommerceInventoryReplenishmentItem> findBysku(String sku,
		int start, int end,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator) {
		return findBysku(sku, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory replenishment items where sku = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sku the sku
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce inventory replenishment items
	 */
	@Override
	public List<CommerceInventoryReplenishmentItem> findBysku(String sku,
		int start, int end,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SKU;
			finderArgs = new Object[] { sku };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SKU;
			finderArgs = new Object[] { sku, start, end, orderByComparator };
		}

		List<CommerceInventoryReplenishmentItem> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceInventoryReplenishmentItem>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem : list) {
					if (!Objects.equals(sku,
								commerceInventoryReplenishmentItem.getSku())) {
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

			query.append(_SQL_SELECT_COMMERCEINVENTORYREPLENISHMENTITEM_WHERE);

			boolean bindSku = false;

			if (sku == null) {
				query.append(_FINDER_COLUMN_SKU_SKU_1);
			}
			else if (sku.equals("")) {
				query.append(_FINDER_COLUMN_SKU_SKU_3);
			}
			else {
				bindSku = true;

				query.append(_FINDER_COLUMN_SKU_SKU_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceInventoryReplenishmentItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSku) {
					qPos.add(sku);
				}

				if (!pagination) {
					list = (List<CommerceInventoryReplenishmentItem>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceInventoryReplenishmentItem>)QueryUtil.list(q,
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
	 * Returns the first commerce inventory replenishment item in the ordered set where sku = &#63;.
	 *
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a matching commerce inventory replenishment item could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem findBysku_First(String sku,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator)
		throws NoSuchInventoryReplenishmentItemException {
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem = fetchBysku_First(sku,
				orderByComparator);

		if (commerceInventoryReplenishmentItem != null) {
			return commerceInventoryReplenishmentItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sku=");
		msg.append(sku);

		msg.append("}");

		throw new NoSuchInventoryReplenishmentItemException(msg.toString());
	}

	/**
	 * Returns the first commerce inventory replenishment item in the ordered set where sku = &#63;.
	 *
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem fetchBysku_First(String sku,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator) {
		List<CommerceInventoryReplenishmentItem> list = findBysku(sku, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce inventory replenishment item in the ordered set where sku = &#63;.
	 *
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a matching commerce inventory replenishment item could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem findBysku_Last(String sku,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator)
		throws NoSuchInventoryReplenishmentItemException {
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem = fetchBysku_Last(sku,
				orderByComparator);

		if (commerceInventoryReplenishmentItem != null) {
			return commerceInventoryReplenishmentItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sku=");
		msg.append(sku);

		msg.append("}");

		throw new NoSuchInventoryReplenishmentItemException(msg.toString());
	}

	/**
	 * Returns the last commerce inventory replenishment item in the ordered set where sku = &#63;.
	 *
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem fetchBysku_Last(String sku,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator) {
		int count = countBysku(sku);

		if (count == 0) {
			return null;
		}

		List<CommerceInventoryReplenishmentItem> list = findBysku(sku,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce inventory replenishment items before and after the current commerce inventory replenishment item in the ordered set where sku = &#63;.
	 *
	 * @param commerceInventoryReplenishmentItemId the primary key of the current commerce inventory replenishment item
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a commerce inventory replenishment item with the primary key could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem[] findBysku_PrevAndNext(
		long commerceInventoryReplenishmentItemId, String sku,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator)
		throws NoSuchInventoryReplenishmentItemException {
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem = findByPrimaryKey(commerceInventoryReplenishmentItemId);

		Session session = null;

		try {
			session = openSession();

			CommerceInventoryReplenishmentItem[] array = new CommerceInventoryReplenishmentItemImpl[3];

			array[0] = getBysku_PrevAndNext(session,
					commerceInventoryReplenishmentItem, sku, orderByComparator,
					true);

			array[1] = commerceInventoryReplenishmentItem;

			array[2] = getBysku_PrevAndNext(session,
					commerceInventoryReplenishmentItem, sku, orderByComparator,
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

	protected CommerceInventoryReplenishmentItem getBysku_PrevAndNext(
		Session session,
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem,
		String sku,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEINVENTORYREPLENISHMENTITEM_WHERE);

		boolean bindSku = false;

		if (sku == null) {
			query.append(_FINDER_COLUMN_SKU_SKU_1);
		}
		else if (sku.equals("")) {
			query.append(_FINDER_COLUMN_SKU_SKU_3);
		}
		else {
			bindSku = true;

			query.append(_FINDER_COLUMN_SKU_SKU_2);
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
			query.append(CommerceInventoryReplenishmentItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindSku) {
			qPos.add(sku);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceInventoryReplenishmentItem);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceInventoryReplenishmentItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce inventory replenishment items where sku = &#63; from the database.
	 *
	 * @param sku the sku
	 */
	@Override
	public void removeBysku(String sku) {
		for (CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem : findBysku(
				sku, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(commerceInventoryReplenishmentItem);
		}
	}

	/**
	 * Returns the number of commerce inventory replenishment items where sku = &#63;.
	 *
	 * @param sku the sku
	 * @return the number of matching commerce inventory replenishment items
	 */
	@Override
	public int countBysku(String sku) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SKU;

		Object[] finderArgs = new Object[] { sku };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEINVENTORYREPLENISHMENTITEM_WHERE);

			boolean bindSku = false;

			if (sku == null) {
				query.append(_FINDER_COLUMN_SKU_SKU_1);
			}
			else if (sku.equals("")) {
				query.append(_FINDER_COLUMN_SKU_SKU_3);
			}
			else {
				bindSku = true;

				query.append(_FINDER_COLUMN_SKU_SKU_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSku) {
					qPos.add(sku);
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

	private static final String _FINDER_COLUMN_SKU_SKU_1 = "commerceInventoryReplenishmentItem.sku IS NULL";
	private static final String _FINDER_COLUMN_SKU_SKU_2 = "commerceInventoryReplenishmentItem.sku = ?";
	private static final String _FINDER_COLUMN_SKU_SKU_3 = "(commerceInventoryReplenishmentItem.sku IS NULL OR commerceInventoryReplenishmentItem.sku = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AVAILABILITYDATE =
		new FinderPath(CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByavailabilityDate",
			new String[] {
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AVAILABILITYDATE =
		new FinderPath(CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByavailabilityDate", new String[] { Date.class.getName() },
			CommerceInventoryReplenishmentItemModelImpl.AVAILABILITYDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AVAILABILITYDATE = new FinderPath(CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByavailabilityDate", new String[] { Date.class.getName() });

	/**
	 * Returns all the commerce inventory replenishment items where availabilityDate = &#63;.
	 *
	 * @param availabilityDate the availability date
	 * @return the matching commerce inventory replenishment items
	 */
	@Override
	public List<CommerceInventoryReplenishmentItem> findByavailabilityDate(
		Date availabilityDate) {
		return findByavailabilityDate(availabilityDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory replenishment items where availabilityDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param availabilityDate the availability date
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @return the range of matching commerce inventory replenishment items
	 */
	@Override
	public List<CommerceInventoryReplenishmentItem> findByavailabilityDate(
		Date availabilityDate, int start, int end) {
		return findByavailabilityDate(availabilityDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory replenishment items where availabilityDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param availabilityDate the availability date
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory replenishment items
	 */
	@Override
	public List<CommerceInventoryReplenishmentItem> findByavailabilityDate(
		Date availabilityDate, int start, int end,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator) {
		return findByavailabilityDate(availabilityDate, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory replenishment items where availabilityDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param availabilityDate the availability date
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce inventory replenishment items
	 */
	@Override
	public List<CommerceInventoryReplenishmentItem> findByavailabilityDate(
		Date availabilityDate, int start, int end,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AVAILABILITYDATE;
			finderArgs = new Object[] { _getTime(availabilityDate) };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AVAILABILITYDATE;
			finderArgs = new Object[] {
					_getTime(availabilityDate),
					
					start, end, orderByComparator
				};
		}

		List<CommerceInventoryReplenishmentItem> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceInventoryReplenishmentItem>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem : list) {
					if (!Objects.equals(availabilityDate,
								commerceInventoryReplenishmentItem.getAvailabilityDate())) {
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

			query.append(_SQL_SELECT_COMMERCEINVENTORYREPLENISHMENTITEM_WHERE);

			boolean bindAvailabilityDate = false;

			if (availabilityDate == null) {
				query.append(_FINDER_COLUMN_AVAILABILITYDATE_AVAILABILITYDATE_1);
			}
			else {
				bindAvailabilityDate = true;

				query.append(_FINDER_COLUMN_AVAILABILITYDATE_AVAILABILITYDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceInventoryReplenishmentItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindAvailabilityDate) {
					qPos.add(new Timestamp(availabilityDate.getTime()));
				}

				if (!pagination) {
					list = (List<CommerceInventoryReplenishmentItem>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceInventoryReplenishmentItem>)QueryUtil.list(q,
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
	 * Returns the first commerce inventory replenishment item in the ordered set where availabilityDate = &#63;.
	 *
	 * @param availabilityDate the availability date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a matching commerce inventory replenishment item could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem findByavailabilityDate_First(
		Date availabilityDate,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator)
		throws NoSuchInventoryReplenishmentItemException {
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem = fetchByavailabilityDate_First(availabilityDate,
				orderByComparator);

		if (commerceInventoryReplenishmentItem != null) {
			return commerceInventoryReplenishmentItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("availabilityDate=");
		msg.append(availabilityDate);

		msg.append("}");

		throw new NoSuchInventoryReplenishmentItemException(msg.toString());
	}

	/**
	 * Returns the first commerce inventory replenishment item in the ordered set where availabilityDate = &#63;.
	 *
	 * @param availabilityDate the availability date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem fetchByavailabilityDate_First(
		Date availabilityDate,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator) {
		List<CommerceInventoryReplenishmentItem> list = findByavailabilityDate(availabilityDate,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce inventory replenishment item in the ordered set where availabilityDate = &#63;.
	 *
	 * @param availabilityDate the availability date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a matching commerce inventory replenishment item could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem findByavailabilityDate_Last(
		Date availabilityDate,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator)
		throws NoSuchInventoryReplenishmentItemException {
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem = fetchByavailabilityDate_Last(availabilityDate,
				orderByComparator);

		if (commerceInventoryReplenishmentItem != null) {
			return commerceInventoryReplenishmentItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("availabilityDate=");
		msg.append(availabilityDate);

		msg.append("}");

		throw new NoSuchInventoryReplenishmentItemException(msg.toString());
	}

	/**
	 * Returns the last commerce inventory replenishment item in the ordered set where availabilityDate = &#63;.
	 *
	 * @param availabilityDate the availability date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem fetchByavailabilityDate_Last(
		Date availabilityDate,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator) {
		int count = countByavailabilityDate(availabilityDate);

		if (count == 0) {
			return null;
		}

		List<CommerceInventoryReplenishmentItem> list = findByavailabilityDate(availabilityDate,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce inventory replenishment items before and after the current commerce inventory replenishment item in the ordered set where availabilityDate = &#63;.
	 *
	 * @param commerceInventoryReplenishmentItemId the primary key of the current commerce inventory replenishment item
	 * @param availabilityDate the availability date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a commerce inventory replenishment item with the primary key could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem[] findByavailabilityDate_PrevAndNext(
		long commerceInventoryReplenishmentItemId, Date availabilityDate,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator)
		throws NoSuchInventoryReplenishmentItemException {
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem = findByPrimaryKey(commerceInventoryReplenishmentItemId);

		Session session = null;

		try {
			session = openSession();

			CommerceInventoryReplenishmentItem[] array = new CommerceInventoryReplenishmentItemImpl[3];

			array[0] = getByavailabilityDate_PrevAndNext(session,
					commerceInventoryReplenishmentItem, availabilityDate,
					orderByComparator, true);

			array[1] = commerceInventoryReplenishmentItem;

			array[2] = getByavailabilityDate_PrevAndNext(session,
					commerceInventoryReplenishmentItem, availabilityDate,
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

	protected CommerceInventoryReplenishmentItem getByavailabilityDate_PrevAndNext(
		Session session,
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem,
		Date availabilityDate,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEINVENTORYREPLENISHMENTITEM_WHERE);

		boolean bindAvailabilityDate = false;

		if (availabilityDate == null) {
			query.append(_FINDER_COLUMN_AVAILABILITYDATE_AVAILABILITYDATE_1);
		}
		else {
			bindAvailabilityDate = true;

			query.append(_FINDER_COLUMN_AVAILABILITYDATE_AVAILABILITYDATE_2);
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
			query.append(CommerceInventoryReplenishmentItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindAvailabilityDate) {
			qPos.add(new Timestamp(availabilityDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceInventoryReplenishmentItem);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceInventoryReplenishmentItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce inventory replenishment items where availabilityDate = &#63; from the database.
	 *
	 * @param availabilityDate the availability date
	 */
	@Override
	public void removeByavailabilityDate(Date availabilityDate) {
		for (CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem : findByavailabilityDate(
				availabilityDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(commerceInventoryReplenishmentItem);
		}
	}

	/**
	 * Returns the number of commerce inventory replenishment items where availabilityDate = &#63;.
	 *
	 * @param availabilityDate the availability date
	 * @return the number of matching commerce inventory replenishment items
	 */
	@Override
	public int countByavailabilityDate(Date availabilityDate) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_AVAILABILITYDATE;

		Object[] finderArgs = new Object[] { _getTime(availabilityDate) };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEINVENTORYREPLENISHMENTITEM_WHERE);

			boolean bindAvailabilityDate = false;

			if (availabilityDate == null) {
				query.append(_FINDER_COLUMN_AVAILABILITYDATE_AVAILABILITYDATE_1);
			}
			else {
				bindAvailabilityDate = true;

				query.append(_FINDER_COLUMN_AVAILABILITYDATE_AVAILABILITYDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindAvailabilityDate) {
					qPos.add(new Timestamp(availabilityDate.getTime()));
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

	private static final String _FINDER_COLUMN_AVAILABILITYDATE_AVAILABILITYDATE_1 =
		"commerceInventoryReplenishmentItem.availabilityDate IS NULL";
	private static final String _FINDER_COLUMN_AVAILABILITYDATE_AVAILABILITYDATE_2 =
		"commerceInventoryReplenishmentItem.availabilityDate = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_S_AD = new FinderPath(CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByS_AD",
			new String[] {
				String.class.getName(), Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_AD = new FinderPath(CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByS_AD",
			new String[] { String.class.getName(), Date.class.getName() },
			CommerceInventoryReplenishmentItemModelImpl.SKU_COLUMN_BITMASK |
			CommerceInventoryReplenishmentItemModelImpl.AVAILABILITYDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_S_AD = new FinderPath(CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByS_AD",
			new String[] { String.class.getName(), Date.class.getName() });

	/**
	 * Returns all the commerce inventory replenishment items where sku = &#63; and availabilityDate = &#63;.
	 *
	 * @param sku the sku
	 * @param availabilityDate the availability date
	 * @return the matching commerce inventory replenishment items
	 */
	@Override
	public List<CommerceInventoryReplenishmentItem> findByS_AD(String sku,
		Date availabilityDate) {
		return findByS_AD(sku, availabilityDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory replenishment items where sku = &#63; and availabilityDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sku the sku
	 * @param availabilityDate the availability date
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @return the range of matching commerce inventory replenishment items
	 */
	@Override
	public List<CommerceInventoryReplenishmentItem> findByS_AD(String sku,
		Date availabilityDate, int start, int end) {
		return findByS_AD(sku, availabilityDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory replenishment items where sku = &#63; and availabilityDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sku the sku
	 * @param availabilityDate the availability date
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory replenishment items
	 */
	@Override
	public List<CommerceInventoryReplenishmentItem> findByS_AD(String sku,
		Date availabilityDate, int start, int end,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator) {
		return findByS_AD(sku, availabilityDate, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory replenishment items where sku = &#63; and availabilityDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sku the sku
	 * @param availabilityDate the availability date
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce inventory replenishment items
	 */
	@Override
	public List<CommerceInventoryReplenishmentItem> findByS_AD(String sku,
		Date availabilityDate, int start, int end,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_AD;
			finderArgs = new Object[] { sku, _getTime(availabilityDate) };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_S_AD;
			finderArgs = new Object[] {
					sku, _getTime(availabilityDate),
					
					start, end, orderByComparator
				};
		}

		List<CommerceInventoryReplenishmentItem> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceInventoryReplenishmentItem>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem : list) {
					if (!Objects.equals(sku,
								commerceInventoryReplenishmentItem.getSku()) ||
							!Objects.equals(availabilityDate,
								commerceInventoryReplenishmentItem.getAvailabilityDate())) {
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

			query.append(_SQL_SELECT_COMMERCEINVENTORYREPLENISHMENTITEM_WHERE);

			boolean bindSku = false;

			if (sku == null) {
				query.append(_FINDER_COLUMN_S_AD_SKU_1);
			}
			else if (sku.equals("")) {
				query.append(_FINDER_COLUMN_S_AD_SKU_3);
			}
			else {
				bindSku = true;

				query.append(_FINDER_COLUMN_S_AD_SKU_2);
			}

			boolean bindAvailabilityDate = false;

			if (availabilityDate == null) {
				query.append(_FINDER_COLUMN_S_AD_AVAILABILITYDATE_1);
			}
			else {
				bindAvailabilityDate = true;

				query.append(_FINDER_COLUMN_S_AD_AVAILABILITYDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceInventoryReplenishmentItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSku) {
					qPos.add(sku);
				}

				if (bindAvailabilityDate) {
					qPos.add(new Timestamp(availabilityDate.getTime()));
				}

				if (!pagination) {
					list = (List<CommerceInventoryReplenishmentItem>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceInventoryReplenishmentItem>)QueryUtil.list(q,
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
	 * Returns the first commerce inventory replenishment item in the ordered set where sku = &#63; and availabilityDate = &#63;.
	 *
	 * @param sku the sku
	 * @param availabilityDate the availability date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a matching commerce inventory replenishment item could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem findByS_AD_First(String sku,
		Date availabilityDate,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator)
		throws NoSuchInventoryReplenishmentItemException {
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem = fetchByS_AD_First(sku,
				availabilityDate, orderByComparator);

		if (commerceInventoryReplenishmentItem != null) {
			return commerceInventoryReplenishmentItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sku=");
		msg.append(sku);

		msg.append(", availabilityDate=");
		msg.append(availabilityDate);

		msg.append("}");

		throw new NoSuchInventoryReplenishmentItemException(msg.toString());
	}

	/**
	 * Returns the first commerce inventory replenishment item in the ordered set where sku = &#63; and availabilityDate = &#63;.
	 *
	 * @param sku the sku
	 * @param availabilityDate the availability date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem fetchByS_AD_First(String sku,
		Date availabilityDate,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator) {
		List<CommerceInventoryReplenishmentItem> list = findByS_AD(sku,
				availabilityDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce inventory replenishment item in the ordered set where sku = &#63; and availabilityDate = &#63;.
	 *
	 * @param sku the sku
	 * @param availabilityDate the availability date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a matching commerce inventory replenishment item could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem findByS_AD_Last(String sku,
		Date availabilityDate,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator)
		throws NoSuchInventoryReplenishmentItemException {
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem = fetchByS_AD_Last(sku,
				availabilityDate, orderByComparator);

		if (commerceInventoryReplenishmentItem != null) {
			return commerceInventoryReplenishmentItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sku=");
		msg.append(sku);

		msg.append(", availabilityDate=");
		msg.append(availabilityDate);

		msg.append("}");

		throw new NoSuchInventoryReplenishmentItemException(msg.toString());
	}

	/**
	 * Returns the last commerce inventory replenishment item in the ordered set where sku = &#63; and availabilityDate = &#63;.
	 *
	 * @param sku the sku
	 * @param availabilityDate the availability date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory replenishment item, or <code>null</code> if a matching commerce inventory replenishment item could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem fetchByS_AD_Last(String sku,
		Date availabilityDate,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator) {
		int count = countByS_AD(sku, availabilityDate);

		if (count == 0) {
			return null;
		}

		List<CommerceInventoryReplenishmentItem> list = findByS_AD(sku,
				availabilityDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce inventory replenishment items before and after the current commerce inventory replenishment item in the ordered set where sku = &#63; and availabilityDate = &#63;.
	 *
	 * @param commerceInventoryReplenishmentItemId the primary key of the current commerce inventory replenishment item
	 * @param sku the sku
	 * @param availabilityDate the availability date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a commerce inventory replenishment item with the primary key could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem[] findByS_AD_PrevAndNext(
		long commerceInventoryReplenishmentItemId, String sku,
		Date availabilityDate,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator)
		throws NoSuchInventoryReplenishmentItemException {
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem = findByPrimaryKey(commerceInventoryReplenishmentItemId);

		Session session = null;

		try {
			session = openSession();

			CommerceInventoryReplenishmentItem[] array = new CommerceInventoryReplenishmentItemImpl[3];

			array[0] = getByS_AD_PrevAndNext(session,
					commerceInventoryReplenishmentItem, sku, availabilityDate,
					orderByComparator, true);

			array[1] = commerceInventoryReplenishmentItem;

			array[2] = getByS_AD_PrevAndNext(session,
					commerceInventoryReplenishmentItem, sku, availabilityDate,
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

	protected CommerceInventoryReplenishmentItem getByS_AD_PrevAndNext(
		Session session,
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem,
		String sku, Date availabilityDate,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEINVENTORYREPLENISHMENTITEM_WHERE);

		boolean bindSku = false;

		if (sku == null) {
			query.append(_FINDER_COLUMN_S_AD_SKU_1);
		}
		else if (sku.equals("")) {
			query.append(_FINDER_COLUMN_S_AD_SKU_3);
		}
		else {
			bindSku = true;

			query.append(_FINDER_COLUMN_S_AD_SKU_2);
		}

		boolean bindAvailabilityDate = false;

		if (availabilityDate == null) {
			query.append(_FINDER_COLUMN_S_AD_AVAILABILITYDATE_1);
		}
		else {
			bindAvailabilityDate = true;

			query.append(_FINDER_COLUMN_S_AD_AVAILABILITYDATE_2);
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
			query.append(CommerceInventoryReplenishmentItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindSku) {
			qPos.add(sku);
		}

		if (bindAvailabilityDate) {
			qPos.add(new Timestamp(availabilityDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceInventoryReplenishmentItem);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceInventoryReplenishmentItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce inventory replenishment items where sku = &#63; and availabilityDate = &#63; from the database.
	 *
	 * @param sku the sku
	 * @param availabilityDate the availability date
	 */
	@Override
	public void removeByS_AD(String sku, Date availabilityDate) {
		for (CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem : findByS_AD(
				sku, availabilityDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(commerceInventoryReplenishmentItem);
		}
	}

	/**
	 * Returns the number of commerce inventory replenishment items where sku = &#63; and availabilityDate = &#63;.
	 *
	 * @param sku the sku
	 * @param availabilityDate the availability date
	 * @return the number of matching commerce inventory replenishment items
	 */
	@Override
	public int countByS_AD(String sku, Date availabilityDate) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_S_AD;

		Object[] finderArgs = new Object[] { sku, _getTime(availabilityDate) };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEINVENTORYREPLENISHMENTITEM_WHERE);

			boolean bindSku = false;

			if (sku == null) {
				query.append(_FINDER_COLUMN_S_AD_SKU_1);
			}
			else if (sku.equals("")) {
				query.append(_FINDER_COLUMN_S_AD_SKU_3);
			}
			else {
				bindSku = true;

				query.append(_FINDER_COLUMN_S_AD_SKU_2);
			}

			boolean bindAvailabilityDate = false;

			if (availabilityDate == null) {
				query.append(_FINDER_COLUMN_S_AD_AVAILABILITYDATE_1);
			}
			else {
				bindAvailabilityDate = true;

				query.append(_FINDER_COLUMN_S_AD_AVAILABILITYDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSku) {
					qPos.add(sku);
				}

				if (bindAvailabilityDate) {
					qPos.add(new Timestamp(availabilityDate.getTime()));
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

	private static final String _FINDER_COLUMN_S_AD_SKU_1 = "commerceInventoryReplenishmentItem.sku IS NULL AND ";
	private static final String _FINDER_COLUMN_S_AD_SKU_2 = "commerceInventoryReplenishmentItem.sku = ? AND ";
	private static final String _FINDER_COLUMN_S_AD_SKU_3 = "(commerceInventoryReplenishmentItem.sku IS NULL OR commerceInventoryReplenishmentItem.sku = '') AND ";
	private static final String _FINDER_COLUMN_S_AD_AVAILABILITYDATE_1 = "commerceInventoryReplenishmentItem.availabilityDate IS NULL";
	private static final String _FINDER_COLUMN_S_AD_AVAILABILITYDATE_2 = "commerceInventoryReplenishmentItem.availabilityDate = ?";

	public CommerceInventoryReplenishmentItemPersistenceImpl() {
		setModelClass(CommerceInventoryReplenishmentItem.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("commerceInventoryReplenishmentItemId",
				"CIReplenishmentItemId");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the commerce inventory replenishment item in the entity cache if it is enabled.
	 *
	 * @param commerceInventoryReplenishmentItem the commerce inventory replenishment item
	 */
	@Override
	public void cacheResult(
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem) {
		entityCache.putResult(CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemImpl.class,
			commerceInventoryReplenishmentItem.getPrimaryKey(),
			commerceInventoryReplenishmentItem);

		commerceInventoryReplenishmentItem.resetOriginalValues();
	}

	/**
	 * Caches the commerce inventory replenishment items in the entity cache if it is enabled.
	 *
	 * @param commerceInventoryReplenishmentItems the commerce inventory replenishment items
	 */
	@Override
	public void cacheResult(
		List<CommerceInventoryReplenishmentItem> commerceInventoryReplenishmentItems) {
		for (CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem : commerceInventoryReplenishmentItems) {
			if (entityCache.getResult(
						CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
						CommerceInventoryReplenishmentItemImpl.class,
						commerceInventoryReplenishmentItem.getPrimaryKey()) == null) {
				cacheResult(commerceInventoryReplenishmentItem);
			}
			else {
				commerceInventoryReplenishmentItem.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce inventory replenishment items.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceInventoryReplenishmentItemImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce inventory replenishment item.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem) {
		entityCache.removeResult(CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemImpl.class,
			commerceInventoryReplenishmentItem.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<CommerceInventoryReplenishmentItem> commerceInventoryReplenishmentItems) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem : commerceInventoryReplenishmentItems) {
			entityCache.removeResult(CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
				CommerceInventoryReplenishmentItemImpl.class,
				commerceInventoryReplenishmentItem.getPrimaryKey());
		}
	}

	/**
	 * Creates a new commerce inventory replenishment item with the primary key. Does not add the commerce inventory replenishment item to the database.
	 *
	 * @param commerceInventoryReplenishmentItemId the primary key for the new commerce inventory replenishment item
	 * @return the new commerce inventory replenishment item
	 */
	@Override
	public CommerceInventoryReplenishmentItem create(
		long commerceInventoryReplenishmentItemId) {
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem = new CommerceInventoryReplenishmentItemImpl();

		commerceInventoryReplenishmentItem.setNew(true);
		commerceInventoryReplenishmentItem.setPrimaryKey(commerceInventoryReplenishmentItemId);

		commerceInventoryReplenishmentItem.setCompanyId(companyProvider.getCompanyId());

		return commerceInventoryReplenishmentItem;
	}

	/**
	 * Removes the commerce inventory replenishment item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceInventoryReplenishmentItemId the primary key of the commerce inventory replenishment item
	 * @return the commerce inventory replenishment item that was removed
	 * @throws NoSuchInventoryReplenishmentItemException if a commerce inventory replenishment item with the primary key could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem remove(
		long commerceInventoryReplenishmentItemId)
		throws NoSuchInventoryReplenishmentItemException {
		return remove((Serializable)commerceInventoryReplenishmentItemId);
	}

	/**
	 * Removes the commerce inventory replenishment item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce inventory replenishment item
	 * @return the commerce inventory replenishment item that was removed
	 * @throws NoSuchInventoryReplenishmentItemException if a commerce inventory replenishment item with the primary key could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem remove(Serializable primaryKey)
		throws NoSuchInventoryReplenishmentItemException {
		Session session = null;

		try {
			session = openSession();

			CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem =
				(CommerceInventoryReplenishmentItem)session.get(CommerceInventoryReplenishmentItemImpl.class,
					primaryKey);

			if (commerceInventoryReplenishmentItem == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchInventoryReplenishmentItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commerceInventoryReplenishmentItem);
		}
		catch (NoSuchInventoryReplenishmentItemException nsee) {
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
	protected CommerceInventoryReplenishmentItem removeImpl(
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceInventoryReplenishmentItem)) {
				commerceInventoryReplenishmentItem = (CommerceInventoryReplenishmentItem)session.get(CommerceInventoryReplenishmentItemImpl.class,
						commerceInventoryReplenishmentItem.getPrimaryKeyObj());
			}

			if (commerceInventoryReplenishmentItem != null) {
				session.delete(commerceInventoryReplenishmentItem);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceInventoryReplenishmentItem != null) {
			clearCache(commerceInventoryReplenishmentItem);
		}

		return commerceInventoryReplenishmentItem;
	}

	@Override
	public CommerceInventoryReplenishmentItem updateImpl(
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem) {
		boolean isNew = commerceInventoryReplenishmentItem.isNew();

		if (!(commerceInventoryReplenishmentItem instanceof CommerceInventoryReplenishmentItemModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(
						commerceInventoryReplenishmentItem.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(commerceInventoryReplenishmentItem);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceInventoryReplenishmentItem proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceInventoryReplenishmentItem implementation " +
				commerceInventoryReplenishmentItem.getClass());
		}

		CommerceInventoryReplenishmentItemModelImpl commerceInventoryReplenishmentItemModelImpl =
			(CommerceInventoryReplenishmentItemModelImpl)commerceInventoryReplenishmentItem;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew &&
				(commerceInventoryReplenishmentItem.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceInventoryReplenishmentItem.setCreateDate(now);
			}
			else {
				commerceInventoryReplenishmentItem.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!commerceInventoryReplenishmentItemModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceInventoryReplenishmentItem.setModifiedDate(now);
			}
			else {
				commerceInventoryReplenishmentItem.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceInventoryReplenishmentItem.isNew()) {
				session.save(commerceInventoryReplenishmentItem);

				commerceInventoryReplenishmentItem.setNew(false);
			}
			else {
				commerceInventoryReplenishmentItem = (CommerceInventoryReplenishmentItem)session.merge(commerceInventoryReplenishmentItem);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceInventoryReplenishmentItemModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					commerceInventoryReplenishmentItemModelImpl.getCommerceWarehouseId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEWAREHOUSEID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEWAREHOUSEID,
				args);

			args = new Object[] {
					commerceInventoryReplenishmentItemModelImpl.getSku()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SKU, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SKU,
				args);

			args = new Object[] {
					commerceInventoryReplenishmentItemModelImpl.getAvailabilityDate()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_AVAILABILITYDATE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AVAILABILITYDATE,
				args);

			args = new Object[] {
					commerceInventoryReplenishmentItemModelImpl.getSku(),
					commerceInventoryReplenishmentItemModelImpl.getAvailabilityDate()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_S_AD, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_AD,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((commerceInventoryReplenishmentItemModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEWAREHOUSEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceInventoryReplenishmentItemModelImpl.getOriginalCommerceWarehouseId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEWAREHOUSEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEWAREHOUSEID,
					args);

				args = new Object[] {
						commerceInventoryReplenishmentItemModelImpl.getCommerceWarehouseId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEWAREHOUSEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEWAREHOUSEID,
					args);
			}

			if ((commerceInventoryReplenishmentItemModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SKU.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceInventoryReplenishmentItemModelImpl.getOriginalSku()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SKU, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SKU,
					args);

				args = new Object[] {
						commerceInventoryReplenishmentItemModelImpl.getSku()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SKU, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SKU,
					args);
			}

			if ((commerceInventoryReplenishmentItemModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AVAILABILITYDATE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceInventoryReplenishmentItemModelImpl.getOriginalAvailabilityDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_AVAILABILITYDATE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AVAILABILITYDATE,
					args);

				args = new Object[] {
						commerceInventoryReplenishmentItemModelImpl.getAvailabilityDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_AVAILABILITYDATE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AVAILABILITYDATE,
					args);
			}

			if ((commerceInventoryReplenishmentItemModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_AD.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceInventoryReplenishmentItemModelImpl.getOriginalSku(),
						commerceInventoryReplenishmentItemModelImpl.getOriginalAvailabilityDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_S_AD, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_AD,
					args);

				args = new Object[] {
						commerceInventoryReplenishmentItemModelImpl.getSku(),
						commerceInventoryReplenishmentItemModelImpl.getAvailabilityDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_S_AD, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_S_AD,
					args);
			}
		}

		entityCache.putResult(CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryReplenishmentItemImpl.class,
			commerceInventoryReplenishmentItem.getPrimaryKey(),
			commerceInventoryReplenishmentItem, false);

		commerceInventoryReplenishmentItem.resetOriginalValues();

		return commerceInventoryReplenishmentItem;
	}

	/**
	 * Returns the commerce inventory replenishment item with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce inventory replenishment item
	 * @return the commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a commerce inventory replenishment item with the primary key could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem findByPrimaryKey(
		Serializable primaryKey)
		throws NoSuchInventoryReplenishmentItemException {
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem = fetchByPrimaryKey(primaryKey);

		if (commerceInventoryReplenishmentItem == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchInventoryReplenishmentItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commerceInventoryReplenishmentItem;
	}

	/**
	 * Returns the commerce inventory replenishment item with the primary key or throws a {@link NoSuchInventoryReplenishmentItemException} if it could not be found.
	 *
	 * @param commerceInventoryReplenishmentItemId the primary key of the commerce inventory replenishment item
	 * @return the commerce inventory replenishment item
	 * @throws NoSuchInventoryReplenishmentItemException if a commerce inventory replenishment item with the primary key could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem findByPrimaryKey(
		long commerceInventoryReplenishmentItemId)
		throws NoSuchInventoryReplenishmentItemException {
		return findByPrimaryKey((Serializable)commerceInventoryReplenishmentItemId);
	}

	/**
	 * Returns the commerce inventory replenishment item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce inventory replenishment item
	 * @return the commerce inventory replenishment item, or <code>null</code> if a commerce inventory replenishment item with the primary key could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem fetchByPrimaryKey(
		Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
				CommerceInventoryReplenishmentItemImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem = (CommerceInventoryReplenishmentItem)serializable;

		if (commerceInventoryReplenishmentItem == null) {
			Session session = null;

			try {
				session = openSession();

				commerceInventoryReplenishmentItem = (CommerceInventoryReplenishmentItem)session.get(CommerceInventoryReplenishmentItemImpl.class,
						primaryKey);

				if (commerceInventoryReplenishmentItem != null) {
					cacheResult(commerceInventoryReplenishmentItem);
				}
				else {
					entityCache.putResult(CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
						CommerceInventoryReplenishmentItemImpl.class,
						primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
					CommerceInventoryReplenishmentItemImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceInventoryReplenishmentItem;
	}

	/**
	 * Returns the commerce inventory replenishment item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceInventoryReplenishmentItemId the primary key of the commerce inventory replenishment item
	 * @return the commerce inventory replenishment item, or <code>null</code> if a commerce inventory replenishment item with the primary key could not be found
	 */
	@Override
	public CommerceInventoryReplenishmentItem fetchByPrimaryKey(
		long commerceInventoryReplenishmentItemId) {
		return fetchByPrimaryKey((Serializable)commerceInventoryReplenishmentItemId);
	}

	@Override
	public Map<Serializable, CommerceInventoryReplenishmentItem> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceInventoryReplenishmentItem> map = new HashMap<Serializable, CommerceInventoryReplenishmentItem>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem =
				fetchByPrimaryKey(primaryKey);

			if (commerceInventoryReplenishmentItem != null) {
				map.put(primaryKey, commerceInventoryReplenishmentItem);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
					CommerceInventoryReplenishmentItemImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey,
						(CommerceInventoryReplenishmentItem)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMMERCEINVENTORYREPLENISHMENTITEM_WHERE_PKS_IN);

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

			for (CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem : (List<CommerceInventoryReplenishmentItem>)q.list()) {
				map.put(commerceInventoryReplenishmentItem.getPrimaryKeyObj(),
					commerceInventoryReplenishmentItem);

				cacheResult(commerceInventoryReplenishmentItem);

				uncachedPrimaryKeys.remove(commerceInventoryReplenishmentItem.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CommerceInventoryReplenishmentItemModelImpl.ENTITY_CACHE_ENABLED,
					CommerceInventoryReplenishmentItemImpl.class, primaryKey,
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
	 * Returns all the commerce inventory replenishment items.
	 *
	 * @return the commerce inventory replenishment items
	 */
	@Override
	public List<CommerceInventoryReplenishmentItem> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory replenishment items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @return the range of commerce inventory replenishment items
	 */
	@Override
	public List<CommerceInventoryReplenishmentItem> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory replenishment items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce inventory replenishment items
	 */
	@Override
	public List<CommerceInventoryReplenishmentItem> findAll(int start, int end,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory replenishment items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryReplenishmentItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory replenishment items
	 * @param end the upper bound of the range of commerce inventory replenishment items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce inventory replenishment items
	 */
	@Override
	public List<CommerceInventoryReplenishmentItem> findAll(int start, int end,
		OrderByComparator<CommerceInventoryReplenishmentItem> orderByComparator,
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

		List<CommerceInventoryReplenishmentItem> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceInventoryReplenishmentItem>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEINVENTORYREPLENISHMENTITEM);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEINVENTORYREPLENISHMENTITEM;

				if (pagination) {
					sql = sql.concat(CommerceInventoryReplenishmentItemModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceInventoryReplenishmentItem>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceInventoryReplenishmentItem>)QueryUtil.list(q,
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
	 * Removes all the commerce inventory replenishment items from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem : findAll()) {
			remove(commerceInventoryReplenishmentItem);
		}
	}

	/**
	 * Returns the number of commerce inventory replenishment items.
	 *
	 * @return the number of commerce inventory replenishment items
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCEINVENTORYREPLENISHMENTITEM);

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
		return CommerceInventoryReplenishmentItemModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce inventory replenishment item persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CommerceInventoryReplenishmentItemImpl.class.getName());
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

	private Long _getTime(Date date) {
		if (date == null) {
			return null;
		}

		return date.getTime();
	}

	private static final String _SQL_SELECT_COMMERCEINVENTORYREPLENISHMENTITEM = "SELECT commerceInventoryReplenishmentItem FROM CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem";
	private static final String _SQL_SELECT_COMMERCEINVENTORYREPLENISHMENTITEM_WHERE_PKS_IN =
		"SELECT commerceInventoryReplenishmentItem FROM CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem WHERE CIReplenishmentItemId IN (";
	private static final String _SQL_SELECT_COMMERCEINVENTORYREPLENISHMENTITEM_WHERE =
		"SELECT commerceInventoryReplenishmentItem FROM CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem WHERE ";
	private static final String _SQL_COUNT_COMMERCEINVENTORYREPLENISHMENTITEM = "SELECT COUNT(commerceInventoryReplenishmentItem) FROM CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem";
	private static final String _SQL_COUNT_COMMERCEINVENTORYREPLENISHMENTITEM_WHERE =
		"SELECT COUNT(commerceInventoryReplenishmentItem) FROM CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceInventoryReplenishmentItem.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommerceInventoryReplenishmentItem exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CommerceInventoryReplenishmentItem exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CommerceInventoryReplenishmentItemPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"commerceInventoryReplenishmentItemId"
			});
}