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

import com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseItemException;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem;
import com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseItemImpl;
import com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseItemModelImpl;
import com.liferay.commerce.inventory.service.persistence.CommerceInventoryWarehouseItemPersistence;

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
 * The persistence implementation for the commerce inventory warehouse item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseItemPersistence
 * @see com.liferay.commerce.inventory.service.persistence.CommerceInventoryWarehouseItemUtil
 * @generated
 */
@ProviderType
public class CommerceInventoryWarehouseItemPersistenceImpl
	extends BasePersistenceImpl<CommerceInventoryWarehouseItem>
	implements CommerceInventoryWarehouseItemPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommerceInventoryWarehouseItemUtil} to access the commerce inventory warehouse item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommerceInventoryWarehouseItemImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommerceInventoryWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryWarehouseItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommerceInventoryWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryWarehouseItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommerceInventoryWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseItemModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMMERCEWAREHOUSEID =
		new FinderPath(CommerceInventoryWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryWarehouseItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCommerceWarehouseId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEWAREHOUSEID =
		new FinderPath(CommerceInventoryWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryWarehouseItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCommerceWarehouseId", new String[] { Long.class.getName() },
			CommerceInventoryWarehouseItemModelImpl.COMMERCEWAREHOUSEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMMERCEWAREHOUSEID = new FinderPath(CommerceInventoryWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseItemModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceWarehouseId", new String[] { Long.class.getName() });

	/**
	 * Returns all the commerce inventory warehouse items where commerceWarehouseId = &#63;.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @return the matching commerce inventory warehouse items
	 */
	@Override
	public List<CommerceInventoryWarehouseItem> findByCommerceWarehouseId(
		long commerceWarehouseId) {
		return findByCommerceWarehouseId(commerceWarehouseId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory warehouse items where commerceWarehouseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param start the lower bound of the range of commerce inventory warehouse items
	 * @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	 * @return the range of matching commerce inventory warehouse items
	 */
	@Override
	public List<CommerceInventoryWarehouseItem> findByCommerceWarehouseId(
		long commerceWarehouseId, int start, int end) {
		return findByCommerceWarehouseId(commerceWarehouseId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse items where commerceWarehouseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param start the lower bound of the range of commerce inventory warehouse items
	 * @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory warehouse items
	 */
	@Override
	public List<CommerceInventoryWarehouseItem> findByCommerceWarehouseId(
		long commerceWarehouseId, int start, int end,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator) {
		return findByCommerceWarehouseId(commerceWarehouseId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse items where commerceWarehouseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param start the lower bound of the range of commerce inventory warehouse items
	 * @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce inventory warehouse items
	 */
	@Override
	public List<CommerceInventoryWarehouseItem> findByCommerceWarehouseId(
		long commerceWarehouseId, int start, int end,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator,
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

		List<CommerceInventoryWarehouseItem> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceInventoryWarehouseItem>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceInventoryWarehouseItem commerceInventoryWarehouseItem : list) {
					if ((commerceWarehouseId != commerceInventoryWarehouseItem.getCommerceWarehouseId())) {
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

			query.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEITEM_WHERE);

			query.append(_FINDER_COLUMN_COMMERCEWAREHOUSEID_COMMERCEWAREHOUSEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceInventoryWarehouseItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceWarehouseId);

				if (!pagination) {
					list = (List<CommerceInventoryWarehouseItem>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceInventoryWarehouseItem>)QueryUtil.list(q,
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
	 * Returns the first commerce inventory warehouse item in the ordered set where commerceWarehouseId = &#63;.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a matching commerce inventory warehouse item could not be found
	 */
	@Override
	public CommerceInventoryWarehouseItem findByCommerceWarehouseId_First(
		long commerceWarehouseId,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator)
		throws NoSuchInventoryWarehouseItemException {
		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem = fetchByCommerceWarehouseId_First(commerceWarehouseId,
				orderByComparator);

		if (commerceInventoryWarehouseItem != null) {
			return commerceInventoryWarehouseItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceWarehouseId=");
		msg.append(commerceWarehouseId);

		msg.append("}");

		throw new NoSuchInventoryWarehouseItemException(msg.toString());
	}

	/**
	 * Returns the first commerce inventory warehouse item in the ordered set where commerceWarehouseId = &#63;.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	 */
	@Override
	public CommerceInventoryWarehouseItem fetchByCommerceWarehouseId_First(
		long commerceWarehouseId,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator) {
		List<CommerceInventoryWarehouseItem> list = findByCommerceWarehouseId(commerceWarehouseId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce inventory warehouse item in the ordered set where commerceWarehouseId = &#63;.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a matching commerce inventory warehouse item could not be found
	 */
	@Override
	public CommerceInventoryWarehouseItem findByCommerceWarehouseId_Last(
		long commerceWarehouseId,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator)
		throws NoSuchInventoryWarehouseItemException {
		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem = fetchByCommerceWarehouseId_Last(commerceWarehouseId,
				orderByComparator);

		if (commerceInventoryWarehouseItem != null) {
			return commerceInventoryWarehouseItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceWarehouseId=");
		msg.append(commerceWarehouseId);

		msg.append("}");

		throw new NoSuchInventoryWarehouseItemException(msg.toString());
	}

	/**
	 * Returns the last commerce inventory warehouse item in the ordered set where commerceWarehouseId = &#63;.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	 */
	@Override
	public CommerceInventoryWarehouseItem fetchByCommerceWarehouseId_Last(
		long commerceWarehouseId,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator) {
		int count = countByCommerceWarehouseId(commerceWarehouseId);

		if (count == 0) {
			return null;
		}

		List<CommerceInventoryWarehouseItem> list = findByCommerceWarehouseId(commerceWarehouseId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce inventory warehouse items before and after the current commerce inventory warehouse item in the ordered set where commerceWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseItemId the primary key of the current commerce inventory warehouse item
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a commerce inventory warehouse item with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseItem[] findByCommerceWarehouseId_PrevAndNext(
		long commerceInventoryWarehouseItemId, long commerceWarehouseId,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator)
		throws NoSuchInventoryWarehouseItemException {
		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem = findByPrimaryKey(commerceInventoryWarehouseItemId);

		Session session = null;

		try {
			session = openSession();

			CommerceInventoryWarehouseItem[] array = new CommerceInventoryWarehouseItemImpl[3];

			array[0] = getByCommerceWarehouseId_PrevAndNext(session,
					commerceInventoryWarehouseItem, commerceWarehouseId,
					orderByComparator, true);

			array[1] = commerceInventoryWarehouseItem;

			array[2] = getByCommerceWarehouseId_PrevAndNext(session,
					commerceInventoryWarehouseItem, commerceWarehouseId,
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

	protected CommerceInventoryWarehouseItem getByCommerceWarehouseId_PrevAndNext(
		Session session,
		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem,
		long commerceWarehouseId,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEITEM_WHERE);

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
			query.append(CommerceInventoryWarehouseItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceWarehouseId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceInventoryWarehouseItem);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceInventoryWarehouseItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce inventory warehouse items where commerceWarehouseId = &#63; from the database.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 */
	@Override
	public void removeByCommerceWarehouseId(long commerceWarehouseId) {
		for (CommerceInventoryWarehouseItem commerceInventoryWarehouseItem : findByCommerceWarehouseId(
				commerceWarehouseId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(commerceInventoryWarehouseItem);
		}
	}

	/**
	 * Returns the number of commerce inventory warehouse items where commerceWarehouseId = &#63;.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @return the number of matching commerce inventory warehouse items
	 */
	@Override
	public int countByCommerceWarehouseId(long commerceWarehouseId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMMERCEWAREHOUSEID;

		Object[] finderArgs = new Object[] { commerceWarehouseId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEINVENTORYWAREHOUSEITEM_WHERE);

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
		"commerceInventoryWarehouseItem.commerceWarehouseId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SKU = new FinderPath(CommerceInventoryWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryWarehouseItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBysku",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SKU = new FinderPath(CommerceInventoryWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryWarehouseItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBysku",
			new String[] { String.class.getName() },
			CommerceInventoryWarehouseItemModelImpl.SKU_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SKU = new FinderPath(CommerceInventoryWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseItemModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBysku", new String[] { String.class.getName() });

	/**
	 * Returns all the commerce inventory warehouse items where sku = &#63;.
	 *
	 * @param sku the sku
	 * @return the matching commerce inventory warehouse items
	 */
	@Override
	public List<CommerceInventoryWarehouseItem> findBysku(String sku) {
		return findBysku(sku, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory warehouse items where sku = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sku the sku
	 * @param start the lower bound of the range of commerce inventory warehouse items
	 * @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	 * @return the range of matching commerce inventory warehouse items
	 */
	@Override
	public List<CommerceInventoryWarehouseItem> findBysku(String sku,
		int start, int end) {
		return findBysku(sku, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse items where sku = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sku the sku
	 * @param start the lower bound of the range of commerce inventory warehouse items
	 * @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory warehouse items
	 */
	@Override
	public List<CommerceInventoryWarehouseItem> findBysku(String sku,
		int start, int end,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator) {
		return findBysku(sku, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse items where sku = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sku the sku
	 * @param start the lower bound of the range of commerce inventory warehouse items
	 * @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce inventory warehouse items
	 */
	@Override
	public List<CommerceInventoryWarehouseItem> findBysku(String sku,
		int start, int end,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator,
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

		List<CommerceInventoryWarehouseItem> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceInventoryWarehouseItem>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceInventoryWarehouseItem commerceInventoryWarehouseItem : list) {
					if (!Objects.equals(sku,
								commerceInventoryWarehouseItem.getSku())) {
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

			query.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEITEM_WHERE);

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
				query.append(CommerceInventoryWarehouseItemModelImpl.ORDER_BY_JPQL);
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
					list = (List<CommerceInventoryWarehouseItem>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceInventoryWarehouseItem>)QueryUtil.list(q,
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
	 * Returns the first commerce inventory warehouse item in the ordered set where sku = &#63;.
	 *
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a matching commerce inventory warehouse item could not be found
	 */
	@Override
	public CommerceInventoryWarehouseItem findBysku_First(String sku,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator)
		throws NoSuchInventoryWarehouseItemException {
		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem = fetchBysku_First(sku,
				orderByComparator);

		if (commerceInventoryWarehouseItem != null) {
			return commerceInventoryWarehouseItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sku=");
		msg.append(sku);

		msg.append("}");

		throw new NoSuchInventoryWarehouseItemException(msg.toString());
	}

	/**
	 * Returns the first commerce inventory warehouse item in the ordered set where sku = &#63;.
	 *
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	 */
	@Override
	public CommerceInventoryWarehouseItem fetchBysku_First(String sku,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator) {
		List<CommerceInventoryWarehouseItem> list = findBysku(sku, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce inventory warehouse item in the ordered set where sku = &#63;.
	 *
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a matching commerce inventory warehouse item could not be found
	 */
	@Override
	public CommerceInventoryWarehouseItem findBysku_Last(String sku,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator)
		throws NoSuchInventoryWarehouseItemException {
		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem = fetchBysku_Last(sku,
				orderByComparator);

		if (commerceInventoryWarehouseItem != null) {
			return commerceInventoryWarehouseItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sku=");
		msg.append(sku);

		msg.append("}");

		throw new NoSuchInventoryWarehouseItemException(msg.toString());
	}

	/**
	 * Returns the last commerce inventory warehouse item in the ordered set where sku = &#63;.
	 *
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	 */
	@Override
	public CommerceInventoryWarehouseItem fetchBysku_Last(String sku,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator) {
		int count = countBysku(sku);

		if (count == 0) {
			return null;
		}

		List<CommerceInventoryWarehouseItem> list = findBysku(sku, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce inventory warehouse items before and after the current commerce inventory warehouse item in the ordered set where sku = &#63;.
	 *
	 * @param commerceInventoryWarehouseItemId the primary key of the current commerce inventory warehouse item
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a commerce inventory warehouse item with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseItem[] findBysku_PrevAndNext(
		long commerceInventoryWarehouseItemId, String sku,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator)
		throws NoSuchInventoryWarehouseItemException {
		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem = findByPrimaryKey(commerceInventoryWarehouseItemId);

		Session session = null;

		try {
			session = openSession();

			CommerceInventoryWarehouseItem[] array = new CommerceInventoryWarehouseItemImpl[3];

			array[0] = getBysku_PrevAndNext(session,
					commerceInventoryWarehouseItem, sku, orderByComparator, true);

			array[1] = commerceInventoryWarehouseItem;

			array[2] = getBysku_PrevAndNext(session,
					commerceInventoryWarehouseItem, sku, orderByComparator,
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

	protected CommerceInventoryWarehouseItem getBysku_PrevAndNext(
		Session session,
		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem,
		String sku,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEITEM_WHERE);

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
			query.append(CommerceInventoryWarehouseItemModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(commerceInventoryWarehouseItem);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceInventoryWarehouseItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce inventory warehouse items where sku = &#63; from the database.
	 *
	 * @param sku the sku
	 */
	@Override
	public void removeBysku(String sku) {
		for (CommerceInventoryWarehouseItem commerceInventoryWarehouseItem : findBysku(
				sku, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(commerceInventoryWarehouseItem);
		}
	}

	/**
	 * Returns the number of commerce inventory warehouse items where sku = &#63;.
	 *
	 * @param sku the sku
	 * @return the number of matching commerce inventory warehouse items
	 */
	@Override
	public int countBysku(String sku) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SKU;

		Object[] finderArgs = new Object[] { sku };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEINVENTORYWAREHOUSEITEM_WHERE);

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

	private static final String _FINDER_COLUMN_SKU_SKU_1 = "commerceInventoryWarehouseItem.sku IS NULL";
	private static final String _FINDER_COLUMN_SKU_SKU_2 = "commerceInventoryWarehouseItem.sku = ?";
	private static final String _FINDER_COLUMN_SKU_SKU_3 = "(commerceInventoryWarehouseItem.sku IS NULL OR commerceInventoryWarehouseItem.sku = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_C_S = new FinderPath(CommerceInventoryWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryWarehouseItemImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_S",
			new String[] { Long.class.getName(), String.class.getName() },
			CommerceInventoryWarehouseItemModelImpl.COMMERCEWAREHOUSEID_COLUMN_BITMASK |
			CommerceInventoryWarehouseItemModelImpl.SKU_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_S = new FinderPath(CommerceInventoryWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseItemModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByC_S",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the commerce inventory warehouse item where commerceWarehouseId = &#63; and sku = &#63; or throws a {@link NoSuchInventoryWarehouseItemException} if it could not be found.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param sku the sku
	 * @return the matching commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a matching commerce inventory warehouse item could not be found
	 */
	@Override
	public CommerceInventoryWarehouseItem findByC_S(long commerceWarehouseId,
		String sku) throws NoSuchInventoryWarehouseItemException {
		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem = fetchByC_S(commerceWarehouseId,
				sku);

		if (commerceInventoryWarehouseItem == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("commerceWarehouseId=");
			msg.append(commerceWarehouseId);

			msg.append(", sku=");
			msg.append(sku);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchInventoryWarehouseItemException(msg.toString());
		}

		return commerceInventoryWarehouseItem;
	}

	/**
	 * Returns the commerce inventory warehouse item where commerceWarehouseId = &#63; and sku = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param sku the sku
	 * @return the matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	 */
	@Override
	public CommerceInventoryWarehouseItem fetchByC_S(long commerceWarehouseId,
		String sku) {
		return fetchByC_S(commerceWarehouseId, sku, true);
	}

	/**
	 * Returns the commerce inventory warehouse item where commerceWarehouseId = &#63; and sku = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param sku the sku
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching commerce inventory warehouse item, or <code>null</code> if a matching commerce inventory warehouse item could not be found
	 */
	@Override
	public CommerceInventoryWarehouseItem fetchByC_S(long commerceWarehouseId,
		String sku, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { commerceWarehouseId, sku };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_C_S,
					finderArgs, this);
		}

		if (result instanceof CommerceInventoryWarehouseItem) {
			CommerceInventoryWarehouseItem commerceInventoryWarehouseItem = (CommerceInventoryWarehouseItem)result;

			if ((commerceWarehouseId != commerceInventoryWarehouseItem.getCommerceWarehouseId()) ||
					!Objects.equals(sku, commerceInventoryWarehouseItem.getSku())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEITEM_WHERE);

			query.append(_FINDER_COLUMN_C_S_COMMERCEWAREHOUSEID_2);

			boolean bindSku = false;

			if (sku == null) {
				query.append(_FINDER_COLUMN_C_S_SKU_1);
			}
			else if (sku.equals("")) {
				query.append(_FINDER_COLUMN_C_S_SKU_3);
			}
			else {
				bindSku = true;

				query.append(_FINDER_COLUMN_C_S_SKU_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceWarehouseId);

				if (bindSku) {
					qPos.add(sku);
				}

				List<CommerceInventoryWarehouseItem> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_C_S, finderArgs,
						list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"CommerceInventoryWarehouseItemPersistenceImpl.fetchByC_S(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CommerceInventoryWarehouseItem commerceInventoryWarehouseItem =
						list.get(0);

					result = commerceInventoryWarehouseItem;

					cacheResult(commerceInventoryWarehouseItem);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_C_S, finderArgs);

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
			return (CommerceInventoryWarehouseItem)result;
		}
	}

	/**
	 * Removes the commerce inventory warehouse item where commerceWarehouseId = &#63; and sku = &#63; from the database.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param sku the sku
	 * @return the commerce inventory warehouse item that was removed
	 */
	@Override
	public CommerceInventoryWarehouseItem removeByC_S(
		long commerceWarehouseId, String sku)
		throws NoSuchInventoryWarehouseItemException {
		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem = findByC_S(commerceWarehouseId,
				sku);

		return remove(commerceInventoryWarehouseItem);
	}

	/**
	 * Returns the number of commerce inventory warehouse items where commerceWarehouseId = &#63; and sku = &#63;.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param sku the sku
	 * @return the number of matching commerce inventory warehouse items
	 */
	@Override
	public int countByC_S(long commerceWarehouseId, String sku) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_S;

		Object[] finderArgs = new Object[] { commerceWarehouseId, sku };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEINVENTORYWAREHOUSEITEM_WHERE);

			query.append(_FINDER_COLUMN_C_S_COMMERCEWAREHOUSEID_2);

			boolean bindSku = false;

			if (sku == null) {
				query.append(_FINDER_COLUMN_C_S_SKU_1);
			}
			else if (sku.equals("")) {
				query.append(_FINDER_COLUMN_C_S_SKU_3);
			}
			else {
				bindSku = true;

				query.append(_FINDER_COLUMN_C_S_SKU_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceWarehouseId);

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

	private static final String _FINDER_COLUMN_C_S_COMMERCEWAREHOUSEID_2 = "commerceInventoryWarehouseItem.commerceWarehouseId = ? AND ";
	private static final String _FINDER_COLUMN_C_S_SKU_1 = "commerceInventoryWarehouseItem.sku IS NULL";
	private static final String _FINDER_COLUMN_C_S_SKU_2 = "commerceInventoryWarehouseItem.sku = ?";
	private static final String _FINDER_COLUMN_C_S_SKU_3 = "(commerceInventoryWarehouseItem.sku IS NULL OR commerceInventoryWarehouseItem.sku = '')";

	public CommerceInventoryWarehouseItemPersistenceImpl() {
		setModelClass(CommerceInventoryWarehouseItem.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("commerceInventoryWarehouseItemId",
				"CIWarehouseItemId");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the commerce inventory warehouse item in the entity cache if it is enabled.
	 *
	 * @param commerceInventoryWarehouseItem the commerce inventory warehouse item
	 */
	@Override
	public void cacheResult(
		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem) {
		entityCache.putResult(CommerceInventoryWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseItemImpl.class,
			commerceInventoryWarehouseItem.getPrimaryKey(),
			commerceInventoryWarehouseItem);

		finderCache.putResult(FINDER_PATH_FETCH_BY_C_S,
			new Object[] {
				commerceInventoryWarehouseItem.getCommerceWarehouseId(),
				commerceInventoryWarehouseItem.getSku()
			}, commerceInventoryWarehouseItem);

		commerceInventoryWarehouseItem.resetOriginalValues();
	}

	/**
	 * Caches the commerce inventory warehouse items in the entity cache if it is enabled.
	 *
	 * @param commerceInventoryWarehouseItems the commerce inventory warehouse items
	 */
	@Override
	public void cacheResult(
		List<CommerceInventoryWarehouseItem> commerceInventoryWarehouseItems) {
		for (CommerceInventoryWarehouseItem commerceInventoryWarehouseItem : commerceInventoryWarehouseItems) {
			if (entityCache.getResult(
						CommerceInventoryWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
						CommerceInventoryWarehouseItemImpl.class,
						commerceInventoryWarehouseItem.getPrimaryKey()) == null) {
				cacheResult(commerceInventoryWarehouseItem);
			}
			else {
				commerceInventoryWarehouseItem.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce inventory warehouse items.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceInventoryWarehouseItemImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce inventory warehouse item.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem) {
		entityCache.removeResult(CommerceInventoryWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseItemImpl.class,
			commerceInventoryWarehouseItem.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CommerceInventoryWarehouseItemModelImpl)commerceInventoryWarehouseItem,
			true);
	}

	@Override
	public void clearCache(
		List<CommerceInventoryWarehouseItem> commerceInventoryWarehouseItems) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceInventoryWarehouseItem commerceInventoryWarehouseItem : commerceInventoryWarehouseItems) {
			entityCache.removeResult(CommerceInventoryWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
				CommerceInventoryWarehouseItemImpl.class,
				commerceInventoryWarehouseItem.getPrimaryKey());

			clearUniqueFindersCache((CommerceInventoryWarehouseItemModelImpl)commerceInventoryWarehouseItem,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		CommerceInventoryWarehouseItemModelImpl commerceInventoryWarehouseItemModelImpl) {
		Object[] args = new Object[] {
				commerceInventoryWarehouseItemModelImpl.getCommerceWarehouseId(),
				commerceInventoryWarehouseItemModelImpl.getSku()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_C_S, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_C_S, args,
			commerceInventoryWarehouseItemModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CommerceInventoryWarehouseItemModelImpl commerceInventoryWarehouseItemModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					commerceInventoryWarehouseItemModelImpl.getCommerceWarehouseId(),
					commerceInventoryWarehouseItemModelImpl.getSku()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_S, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_S, args);
		}

		if ((commerceInventoryWarehouseItemModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_S.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					commerceInventoryWarehouseItemModelImpl.getOriginalCommerceWarehouseId(),
					commerceInventoryWarehouseItemModelImpl.getOriginalSku()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_S, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_S, args);
		}
	}

	/**
	 * Creates a new commerce inventory warehouse item with the primary key. Does not add the commerce inventory warehouse item to the database.
	 *
	 * @param commerceInventoryWarehouseItemId the primary key for the new commerce inventory warehouse item
	 * @return the new commerce inventory warehouse item
	 */
	@Override
	public CommerceInventoryWarehouseItem create(
		long commerceInventoryWarehouseItemId) {
		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem = new CommerceInventoryWarehouseItemImpl();

		commerceInventoryWarehouseItem.setNew(true);
		commerceInventoryWarehouseItem.setPrimaryKey(commerceInventoryWarehouseItemId);

		commerceInventoryWarehouseItem.setCompanyId(companyProvider.getCompanyId());

		return commerceInventoryWarehouseItem;
	}

	/**
	 * Removes the commerce inventory warehouse item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceInventoryWarehouseItemId the primary key of the commerce inventory warehouse item
	 * @return the commerce inventory warehouse item that was removed
	 * @throws NoSuchInventoryWarehouseItemException if a commerce inventory warehouse item with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseItem remove(
		long commerceInventoryWarehouseItemId)
		throws NoSuchInventoryWarehouseItemException {
		return remove((Serializable)commerceInventoryWarehouseItemId);
	}

	/**
	 * Removes the commerce inventory warehouse item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce inventory warehouse item
	 * @return the commerce inventory warehouse item that was removed
	 * @throws NoSuchInventoryWarehouseItemException if a commerce inventory warehouse item with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseItem remove(Serializable primaryKey)
		throws NoSuchInventoryWarehouseItemException {
		Session session = null;

		try {
			session = openSession();

			CommerceInventoryWarehouseItem commerceInventoryWarehouseItem = (CommerceInventoryWarehouseItem)session.get(CommerceInventoryWarehouseItemImpl.class,
					primaryKey);

			if (commerceInventoryWarehouseItem == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchInventoryWarehouseItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commerceInventoryWarehouseItem);
		}
		catch (NoSuchInventoryWarehouseItemException nsee) {
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
	protected CommerceInventoryWarehouseItem removeImpl(
		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceInventoryWarehouseItem)) {
				commerceInventoryWarehouseItem = (CommerceInventoryWarehouseItem)session.get(CommerceInventoryWarehouseItemImpl.class,
						commerceInventoryWarehouseItem.getPrimaryKeyObj());
			}

			if (commerceInventoryWarehouseItem != null) {
				session.delete(commerceInventoryWarehouseItem);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceInventoryWarehouseItem != null) {
			clearCache(commerceInventoryWarehouseItem);
		}

		return commerceInventoryWarehouseItem;
	}

	@Override
	public CommerceInventoryWarehouseItem updateImpl(
		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem) {
		boolean isNew = commerceInventoryWarehouseItem.isNew();

		if (!(commerceInventoryWarehouseItem instanceof CommerceInventoryWarehouseItemModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(
						commerceInventoryWarehouseItem.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(commerceInventoryWarehouseItem);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceInventoryWarehouseItem proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceInventoryWarehouseItem implementation " +
				commerceInventoryWarehouseItem.getClass());
		}

		CommerceInventoryWarehouseItemModelImpl commerceInventoryWarehouseItemModelImpl =
			(CommerceInventoryWarehouseItemModelImpl)commerceInventoryWarehouseItem;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceInventoryWarehouseItem.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceInventoryWarehouseItem.setCreateDate(now);
			}
			else {
				commerceInventoryWarehouseItem.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!commerceInventoryWarehouseItemModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceInventoryWarehouseItem.setModifiedDate(now);
			}
			else {
				commerceInventoryWarehouseItem.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceInventoryWarehouseItem.isNew()) {
				session.save(commerceInventoryWarehouseItem);

				commerceInventoryWarehouseItem.setNew(false);
			}
			else {
				commerceInventoryWarehouseItem = (CommerceInventoryWarehouseItem)session.merge(commerceInventoryWarehouseItem);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceInventoryWarehouseItemModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					commerceInventoryWarehouseItemModelImpl.getCommerceWarehouseId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEWAREHOUSEID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEWAREHOUSEID,
				args);

			args = new Object[] { commerceInventoryWarehouseItemModelImpl.getSku() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SKU, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SKU,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((commerceInventoryWarehouseItemModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEWAREHOUSEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceInventoryWarehouseItemModelImpl.getOriginalCommerceWarehouseId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEWAREHOUSEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEWAREHOUSEID,
					args);

				args = new Object[] {
						commerceInventoryWarehouseItemModelImpl.getCommerceWarehouseId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEWAREHOUSEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEWAREHOUSEID,
					args);
			}

			if ((commerceInventoryWarehouseItemModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SKU.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceInventoryWarehouseItemModelImpl.getOriginalSku()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SKU, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SKU,
					args);

				args = new Object[] {
						commerceInventoryWarehouseItemModelImpl.getSku()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SKU, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SKU,
					args);
			}
		}

		entityCache.putResult(CommerceInventoryWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseItemImpl.class,
			commerceInventoryWarehouseItem.getPrimaryKey(),
			commerceInventoryWarehouseItem, false);

		clearUniqueFindersCache(commerceInventoryWarehouseItemModelImpl, false);
		cacheUniqueFindersCache(commerceInventoryWarehouseItemModelImpl);

		commerceInventoryWarehouseItem.resetOriginalValues();

		return commerceInventoryWarehouseItem;
	}

	/**
	 * Returns the commerce inventory warehouse item with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce inventory warehouse item
	 * @return the commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a commerce inventory warehouse item with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseItem findByPrimaryKey(
		Serializable primaryKey) throws NoSuchInventoryWarehouseItemException {
		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem = fetchByPrimaryKey(primaryKey);

		if (commerceInventoryWarehouseItem == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchInventoryWarehouseItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commerceInventoryWarehouseItem;
	}

	/**
	 * Returns the commerce inventory warehouse item with the primary key or throws a {@link NoSuchInventoryWarehouseItemException} if it could not be found.
	 *
	 * @param commerceInventoryWarehouseItemId the primary key of the commerce inventory warehouse item
	 * @return the commerce inventory warehouse item
	 * @throws NoSuchInventoryWarehouseItemException if a commerce inventory warehouse item with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseItem findByPrimaryKey(
		long commerceInventoryWarehouseItemId)
		throws NoSuchInventoryWarehouseItemException {
		return findByPrimaryKey((Serializable)commerceInventoryWarehouseItemId);
	}

	/**
	 * Returns the commerce inventory warehouse item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce inventory warehouse item
	 * @return the commerce inventory warehouse item, or <code>null</code> if a commerce inventory warehouse item with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseItem fetchByPrimaryKey(
		Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CommerceInventoryWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
				CommerceInventoryWarehouseItemImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem = (CommerceInventoryWarehouseItem)serializable;

		if (commerceInventoryWarehouseItem == null) {
			Session session = null;

			try {
				session = openSession();

				commerceInventoryWarehouseItem = (CommerceInventoryWarehouseItem)session.get(CommerceInventoryWarehouseItemImpl.class,
						primaryKey);

				if (commerceInventoryWarehouseItem != null) {
					cacheResult(commerceInventoryWarehouseItem);
				}
				else {
					entityCache.putResult(CommerceInventoryWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
						CommerceInventoryWarehouseItemImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommerceInventoryWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
					CommerceInventoryWarehouseItemImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceInventoryWarehouseItem;
	}

	/**
	 * Returns the commerce inventory warehouse item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceInventoryWarehouseItemId the primary key of the commerce inventory warehouse item
	 * @return the commerce inventory warehouse item, or <code>null</code> if a commerce inventory warehouse item with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseItem fetchByPrimaryKey(
		long commerceInventoryWarehouseItemId) {
		return fetchByPrimaryKey((Serializable)commerceInventoryWarehouseItemId);
	}

	@Override
	public Map<Serializable, CommerceInventoryWarehouseItem> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceInventoryWarehouseItem> map = new HashMap<Serializable, CommerceInventoryWarehouseItem>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceInventoryWarehouseItem commerceInventoryWarehouseItem = fetchByPrimaryKey(primaryKey);

			if (commerceInventoryWarehouseItem != null) {
				map.put(primaryKey, commerceInventoryWarehouseItem);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CommerceInventoryWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
					CommerceInventoryWarehouseItemImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey,
						(CommerceInventoryWarehouseItem)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEITEM_WHERE_PKS_IN);

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

			for (CommerceInventoryWarehouseItem commerceInventoryWarehouseItem : (List<CommerceInventoryWarehouseItem>)q.list()) {
				map.put(commerceInventoryWarehouseItem.getPrimaryKeyObj(),
					commerceInventoryWarehouseItem);

				cacheResult(commerceInventoryWarehouseItem);

				uncachedPrimaryKeys.remove(commerceInventoryWarehouseItem.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CommerceInventoryWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
					CommerceInventoryWarehouseItemImpl.class, primaryKey,
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
	 * Returns all the commerce inventory warehouse items.
	 *
	 * @return the commerce inventory warehouse items
	 */
	@Override
	public List<CommerceInventoryWarehouseItem> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory warehouse items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory warehouse items
	 * @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	 * @return the range of commerce inventory warehouse items
	 */
	@Override
	public List<CommerceInventoryWarehouseItem> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory warehouse items
	 * @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce inventory warehouse items
	 */
	@Override
	public List<CommerceInventoryWarehouseItem> findAll(int start, int end,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory warehouse items
	 * @param end the upper bound of the range of commerce inventory warehouse items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce inventory warehouse items
	 */
	@Override
	public List<CommerceInventoryWarehouseItem> findAll(int start, int end,
		OrderByComparator<CommerceInventoryWarehouseItem> orderByComparator,
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

		List<CommerceInventoryWarehouseItem> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceInventoryWarehouseItem>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEITEM);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEINVENTORYWAREHOUSEITEM;

				if (pagination) {
					sql = sql.concat(CommerceInventoryWarehouseItemModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceInventoryWarehouseItem>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceInventoryWarehouseItem>)QueryUtil.list(q,
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
	 * Removes all the commerce inventory warehouse items from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceInventoryWarehouseItem commerceInventoryWarehouseItem : findAll()) {
			remove(commerceInventoryWarehouseItem);
		}
	}

	/**
	 * Returns the number of commerce inventory warehouse items.
	 *
	 * @return the number of commerce inventory warehouse items
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCEINVENTORYWAREHOUSEITEM);

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
		return CommerceInventoryWarehouseItemModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce inventory warehouse item persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CommerceInventoryWarehouseItemImpl.class.getName());
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
	private static final String _SQL_SELECT_COMMERCEINVENTORYWAREHOUSEITEM = "SELECT commerceInventoryWarehouseItem FROM CommerceInventoryWarehouseItem commerceInventoryWarehouseItem";
	private static final String _SQL_SELECT_COMMERCEINVENTORYWAREHOUSEITEM_WHERE_PKS_IN =
		"SELECT commerceInventoryWarehouseItem FROM CommerceInventoryWarehouseItem commerceInventoryWarehouseItem WHERE CIWarehouseItemId IN (";
	private static final String _SQL_SELECT_COMMERCEINVENTORYWAREHOUSEITEM_WHERE =
		"SELECT commerceInventoryWarehouseItem FROM CommerceInventoryWarehouseItem commerceInventoryWarehouseItem WHERE ";
	private static final String _SQL_COUNT_COMMERCEINVENTORYWAREHOUSEITEM = "SELECT COUNT(commerceInventoryWarehouseItem) FROM CommerceInventoryWarehouseItem commerceInventoryWarehouseItem";
	private static final String _SQL_COUNT_COMMERCEINVENTORYWAREHOUSEITEM_WHERE = "SELECT COUNT(commerceInventoryWarehouseItem) FROM CommerceInventoryWarehouseItem commerceInventoryWarehouseItem WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceInventoryWarehouseItem.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommerceInventoryWarehouseItem exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CommerceInventoryWarehouseItem exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CommerceInventoryWarehouseItemPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"commerceInventoryWarehouseItemId"
			});
}