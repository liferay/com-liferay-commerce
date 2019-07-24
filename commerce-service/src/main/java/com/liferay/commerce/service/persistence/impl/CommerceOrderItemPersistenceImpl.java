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

import com.liferay.commerce.exception.NoSuchOrderItemException;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.impl.CommerceOrderItemImpl;
import com.liferay.commerce.model.impl.CommerceOrderItemModelImpl;
import com.liferay.commerce.service.persistence.CommerceOrderItemPersistence;
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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

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
 * The persistence implementation for the commerce order item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
@ProviderType
public class CommerceOrderItemPersistenceImpl
	extends BasePersistenceImpl<CommerceOrderItem>
	implements CommerceOrderItemPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommerceOrderItemUtil</code> to access the commerce order item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommerceOrderItemImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByCommerceOrderId;
	private FinderPath _finderPathWithoutPaginationFindByCommerceOrderId;
	private FinderPath _finderPathCountByCommerceOrderId;

	/**
	 * Returns all the commerce order items where commerceOrderId = &#63;.
	 *
	 * @param commerceOrderId the commerce order ID
	 * @return the matching commerce order items
	 */
	@Override
	public List<CommerceOrderItem> findByCommerceOrderId(long commerceOrderId) {
		return findByCommerceOrderId(
			commerceOrderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce order items where commerceOrderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param start the lower bound of the range of commerce order items
	 * @param end the upper bound of the range of commerce order items (not inclusive)
	 * @return the range of matching commerce order items
	 */
	@Override
	public List<CommerceOrderItem> findByCommerceOrderId(
		long commerceOrderId, int start, int end) {

		return findByCommerceOrderId(commerceOrderId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce order items where commerceOrderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param start the lower bound of the range of commerce order items
	 * @param end the upper bound of the range of commerce order items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce order items
	 */
	@Override
	public List<CommerceOrderItem> findByCommerceOrderId(
		long commerceOrderId, int start, int end,
		OrderByComparator<CommerceOrderItem> orderByComparator) {

		return findByCommerceOrderId(
			commerceOrderId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce order items where commerceOrderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param start the lower bound of the range of commerce order items
	 * @param end the upper bound of the range of commerce order items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce order items
	 */
	@Override
	public List<CommerceOrderItem> findByCommerceOrderId(
		long commerceOrderId, int start, int end,
		OrderByComparator<CommerceOrderItem> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByCommerceOrderId;
			finderArgs = new Object[] {commerceOrderId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByCommerceOrderId;
			finderArgs = new Object[] {
				commerceOrderId, start, end, orderByComparator
			};
		}

		List<CommerceOrderItem> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceOrderItem>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceOrderItem commerceOrderItem : list) {
					if ((commerceOrderId !=
							commerceOrderItem.getCommerceOrderId())) {

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

			query.append(_SQL_SELECT_COMMERCEORDERITEM_WHERE);

			query.append(_FINDER_COLUMN_COMMERCEORDERID_COMMERCEORDERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceOrderItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceOrderId);

				if (!pagination) {
					list = (List<CommerceOrderItem>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceOrderItem>)QueryUtil.list(
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
	 * Returns the first commerce order item in the ordered set where commerceOrderId = &#63;.
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order item
	 * @throws NoSuchOrderItemException if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem findByCommerceOrderId_First(
			long commerceOrderId,
			OrderByComparator<CommerceOrderItem> orderByComparator)
		throws NoSuchOrderItemException {

		CommerceOrderItem commerceOrderItem = fetchByCommerceOrderId_First(
			commerceOrderId, orderByComparator);

		if (commerceOrderItem != null) {
			return commerceOrderItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceOrderId=");
		msg.append(commerceOrderId);

		msg.append("}");

		throw new NoSuchOrderItemException(msg.toString());
	}

	/**
	 * Returns the first commerce order item in the ordered set where commerceOrderId = &#63;.
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order item, or <code>null</code> if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem fetchByCommerceOrderId_First(
		long commerceOrderId,
		OrderByComparator<CommerceOrderItem> orderByComparator) {

		List<CommerceOrderItem> list = findByCommerceOrderId(
			commerceOrderId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce order item in the ordered set where commerceOrderId = &#63;.
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order item
	 * @throws NoSuchOrderItemException if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem findByCommerceOrderId_Last(
			long commerceOrderId,
			OrderByComparator<CommerceOrderItem> orderByComparator)
		throws NoSuchOrderItemException {

		CommerceOrderItem commerceOrderItem = fetchByCommerceOrderId_Last(
			commerceOrderId, orderByComparator);

		if (commerceOrderItem != null) {
			return commerceOrderItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceOrderId=");
		msg.append(commerceOrderId);

		msg.append("}");

		throw new NoSuchOrderItemException(msg.toString());
	}

	/**
	 * Returns the last commerce order item in the ordered set where commerceOrderId = &#63;.
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order item, or <code>null</code> if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem fetchByCommerceOrderId_Last(
		long commerceOrderId,
		OrderByComparator<CommerceOrderItem> orderByComparator) {

		int count = countByCommerceOrderId(commerceOrderId);

		if (count == 0) {
			return null;
		}

		List<CommerceOrderItem> list = findByCommerceOrderId(
			commerceOrderId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce order items before and after the current commerce order item in the ordered set where commerceOrderId = &#63;.
	 *
	 * @param commerceOrderItemId the primary key of the current commerce order item
	 * @param commerceOrderId the commerce order ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order item
	 * @throws NoSuchOrderItemException if a commerce order item with the primary key could not be found
	 */
	@Override
	public CommerceOrderItem[] findByCommerceOrderId_PrevAndNext(
			long commerceOrderItemId, long commerceOrderId,
			OrderByComparator<CommerceOrderItem> orderByComparator)
		throws NoSuchOrderItemException {

		CommerceOrderItem commerceOrderItem = findByPrimaryKey(
			commerceOrderItemId);

		Session session = null;

		try {
			session = openSession();

			CommerceOrderItem[] array = new CommerceOrderItemImpl[3];

			array[0] = getByCommerceOrderId_PrevAndNext(
				session, commerceOrderItem, commerceOrderId, orderByComparator,
				true);

			array[1] = commerceOrderItem;

			array[2] = getByCommerceOrderId_PrevAndNext(
				session, commerceOrderItem, commerceOrderId, orderByComparator,
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

	protected CommerceOrderItem getByCommerceOrderId_PrevAndNext(
		Session session, CommerceOrderItem commerceOrderItem,
		long commerceOrderId,
		OrderByComparator<CommerceOrderItem> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEORDERITEM_WHERE);

		query.append(_FINDER_COLUMN_COMMERCEORDERID_COMMERCEORDERID_2);

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
			query.append(CommerceOrderItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceOrderId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceOrderItem)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceOrderItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce order items where commerceOrderId = &#63; from the database.
	 *
	 * @param commerceOrderId the commerce order ID
	 */
	@Override
	public void removeByCommerceOrderId(long commerceOrderId) {
		for (CommerceOrderItem commerceOrderItem :
				findByCommerceOrderId(
					commerceOrderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceOrderItem);
		}
	}

	/**
	 * Returns the number of commerce order items where commerceOrderId = &#63;.
	 *
	 * @param commerceOrderId the commerce order ID
	 * @return the number of matching commerce order items
	 */
	@Override
	public int countByCommerceOrderId(long commerceOrderId) {
		FinderPath finderPath = _finderPathCountByCommerceOrderId;

		Object[] finderArgs = new Object[] {commerceOrderId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEORDERITEM_WHERE);

			query.append(_FINDER_COLUMN_COMMERCEORDERID_COMMERCEORDERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceOrderId);

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
		_FINDER_COLUMN_COMMERCEORDERID_COMMERCEORDERID_2 =
			"commerceOrderItem.commerceOrderId = ?";

	private FinderPath _finderPathWithPaginationFindByCProductId;
	private FinderPath _finderPathWithoutPaginationFindByCProductId;
	private FinderPath _finderPathCountByCProductId;

	/**
	 * Returns all the commerce order items where CProductId = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @return the matching commerce order items
	 */
	@Override
	public List<CommerceOrderItem> findByCProductId(long CProductId) {
		return findByCProductId(
			CProductId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce order items where CProductId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CProductId the c product ID
	 * @param start the lower bound of the range of commerce order items
	 * @param end the upper bound of the range of commerce order items (not inclusive)
	 * @return the range of matching commerce order items
	 */
	@Override
	public List<CommerceOrderItem> findByCProductId(
		long CProductId, int start, int end) {

		return findByCProductId(CProductId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce order items where CProductId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CProductId the c product ID
	 * @param start the lower bound of the range of commerce order items
	 * @param end the upper bound of the range of commerce order items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce order items
	 */
	@Override
	public List<CommerceOrderItem> findByCProductId(
		long CProductId, int start, int end,
		OrderByComparator<CommerceOrderItem> orderByComparator) {

		return findByCProductId(
			CProductId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce order items where CProductId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CProductId the c product ID
	 * @param start the lower bound of the range of commerce order items
	 * @param end the upper bound of the range of commerce order items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce order items
	 */
	@Override
	public List<CommerceOrderItem> findByCProductId(
		long CProductId, int start, int end,
		OrderByComparator<CommerceOrderItem> orderByComparator,
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

		List<CommerceOrderItem> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceOrderItem>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceOrderItem commerceOrderItem : list) {
					if ((CProductId != commerceOrderItem.getCProductId())) {
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

			query.append(_SQL_SELECT_COMMERCEORDERITEM_WHERE);

			query.append(_FINDER_COLUMN_CPRODUCTID_CPRODUCTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceOrderItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CProductId);

				if (!pagination) {
					list = (List<CommerceOrderItem>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceOrderItem>)QueryUtil.list(
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
	 * Returns the first commerce order item in the ordered set where CProductId = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order item
	 * @throws NoSuchOrderItemException if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem findByCProductId_First(
			long CProductId,
			OrderByComparator<CommerceOrderItem> orderByComparator)
		throws NoSuchOrderItemException {

		CommerceOrderItem commerceOrderItem = fetchByCProductId_First(
			CProductId, orderByComparator);

		if (commerceOrderItem != null) {
			return commerceOrderItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CProductId=");
		msg.append(CProductId);

		msg.append("}");

		throw new NoSuchOrderItemException(msg.toString());
	}

	/**
	 * Returns the first commerce order item in the ordered set where CProductId = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order item, or <code>null</code> if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem fetchByCProductId_First(
		long CProductId,
		OrderByComparator<CommerceOrderItem> orderByComparator) {

		List<CommerceOrderItem> list = findByCProductId(
			CProductId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce order item in the ordered set where CProductId = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order item
	 * @throws NoSuchOrderItemException if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem findByCProductId_Last(
			long CProductId,
			OrderByComparator<CommerceOrderItem> orderByComparator)
		throws NoSuchOrderItemException {

		CommerceOrderItem commerceOrderItem = fetchByCProductId_Last(
			CProductId, orderByComparator);

		if (commerceOrderItem != null) {
			return commerceOrderItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CProductId=");
		msg.append(CProductId);

		msg.append("}");

		throw new NoSuchOrderItemException(msg.toString());
	}

	/**
	 * Returns the last commerce order item in the ordered set where CProductId = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order item, or <code>null</code> if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem fetchByCProductId_Last(
		long CProductId,
		OrderByComparator<CommerceOrderItem> orderByComparator) {

		int count = countByCProductId(CProductId);

		if (count == 0) {
			return null;
		}

		List<CommerceOrderItem> list = findByCProductId(
			CProductId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce order items before and after the current commerce order item in the ordered set where CProductId = &#63;.
	 *
	 * @param commerceOrderItemId the primary key of the current commerce order item
	 * @param CProductId the c product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order item
	 * @throws NoSuchOrderItemException if a commerce order item with the primary key could not be found
	 */
	@Override
	public CommerceOrderItem[] findByCProductId_PrevAndNext(
			long commerceOrderItemId, long CProductId,
			OrderByComparator<CommerceOrderItem> orderByComparator)
		throws NoSuchOrderItemException {

		CommerceOrderItem commerceOrderItem = findByPrimaryKey(
			commerceOrderItemId);

		Session session = null;

		try {
			session = openSession();

			CommerceOrderItem[] array = new CommerceOrderItemImpl[3];

			array[0] = getByCProductId_PrevAndNext(
				session, commerceOrderItem, CProductId, orderByComparator,
				true);

			array[1] = commerceOrderItem;

			array[2] = getByCProductId_PrevAndNext(
				session, commerceOrderItem, CProductId, orderByComparator,
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

	protected CommerceOrderItem getByCProductId_PrevAndNext(
		Session session, CommerceOrderItem commerceOrderItem, long CProductId,
		OrderByComparator<CommerceOrderItem> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEORDERITEM_WHERE);

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
			query.append(CommerceOrderItemModelImpl.ORDER_BY_JPQL);
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
						commerceOrderItem)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceOrderItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce order items where CProductId = &#63; from the database.
	 *
	 * @param CProductId the c product ID
	 */
	@Override
	public void removeByCProductId(long CProductId) {
		for (CommerceOrderItem commerceOrderItem :
				findByCProductId(
					CProductId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commerceOrderItem);
		}
	}

	/**
	 * Returns the number of commerce order items where CProductId = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @return the number of matching commerce order items
	 */
	@Override
	public int countByCProductId(long CProductId) {
		FinderPath finderPath = _finderPathCountByCProductId;

		Object[] finderArgs = new Object[] {CProductId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEORDERITEM_WHERE);

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
		"commerceOrderItem.CProductId = ?";

	private FinderPath _finderPathWithPaginationFindByCPInstanceId;
	private FinderPath _finderPathWithoutPaginationFindByCPInstanceId;
	private FinderPath _finderPathCountByCPInstanceId;

	/**
	 * Returns all the commerce order items where CPInstanceId = &#63;.
	 *
	 * @param CPInstanceId the cp instance ID
	 * @return the matching commerce order items
	 */
	@Override
	public List<CommerceOrderItem> findByCPInstanceId(long CPInstanceId) {
		return findByCPInstanceId(
			CPInstanceId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce order items where CPInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPInstanceId the cp instance ID
	 * @param start the lower bound of the range of commerce order items
	 * @param end the upper bound of the range of commerce order items (not inclusive)
	 * @return the range of matching commerce order items
	 */
	@Override
	public List<CommerceOrderItem> findByCPInstanceId(
		long CPInstanceId, int start, int end) {

		return findByCPInstanceId(CPInstanceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce order items where CPInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPInstanceId the cp instance ID
	 * @param start the lower bound of the range of commerce order items
	 * @param end the upper bound of the range of commerce order items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce order items
	 */
	@Override
	public List<CommerceOrderItem> findByCPInstanceId(
		long CPInstanceId, int start, int end,
		OrderByComparator<CommerceOrderItem> orderByComparator) {

		return findByCPInstanceId(
			CPInstanceId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce order items where CPInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPInstanceId the cp instance ID
	 * @param start the lower bound of the range of commerce order items
	 * @param end the upper bound of the range of commerce order items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce order items
	 */
	@Override
	public List<CommerceOrderItem> findByCPInstanceId(
		long CPInstanceId, int start, int end,
		OrderByComparator<CommerceOrderItem> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByCPInstanceId;
			finderArgs = new Object[] {CPInstanceId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByCPInstanceId;
			finderArgs = new Object[] {
				CPInstanceId, start, end, orderByComparator
			};
		}

		List<CommerceOrderItem> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceOrderItem>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceOrderItem commerceOrderItem : list) {
					if ((CPInstanceId != commerceOrderItem.getCPInstanceId())) {
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

			query.append(_SQL_SELECT_COMMERCEORDERITEM_WHERE);

			query.append(_FINDER_COLUMN_CPINSTANCEID_CPINSTANCEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceOrderItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPInstanceId);

				if (!pagination) {
					list = (List<CommerceOrderItem>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceOrderItem>)QueryUtil.list(
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
	 * Returns the first commerce order item in the ordered set where CPInstanceId = &#63;.
	 *
	 * @param CPInstanceId the cp instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order item
	 * @throws NoSuchOrderItemException if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem findByCPInstanceId_First(
			long CPInstanceId,
			OrderByComparator<CommerceOrderItem> orderByComparator)
		throws NoSuchOrderItemException {

		CommerceOrderItem commerceOrderItem = fetchByCPInstanceId_First(
			CPInstanceId, orderByComparator);

		if (commerceOrderItem != null) {
			return commerceOrderItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPInstanceId=");
		msg.append(CPInstanceId);

		msg.append("}");

		throw new NoSuchOrderItemException(msg.toString());
	}

	/**
	 * Returns the first commerce order item in the ordered set where CPInstanceId = &#63;.
	 *
	 * @param CPInstanceId the cp instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order item, or <code>null</code> if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem fetchByCPInstanceId_First(
		long CPInstanceId,
		OrderByComparator<CommerceOrderItem> orderByComparator) {

		List<CommerceOrderItem> list = findByCPInstanceId(
			CPInstanceId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce order item in the ordered set where CPInstanceId = &#63;.
	 *
	 * @param CPInstanceId the cp instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order item
	 * @throws NoSuchOrderItemException if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem findByCPInstanceId_Last(
			long CPInstanceId,
			OrderByComparator<CommerceOrderItem> orderByComparator)
		throws NoSuchOrderItemException {

		CommerceOrderItem commerceOrderItem = fetchByCPInstanceId_Last(
			CPInstanceId, orderByComparator);

		if (commerceOrderItem != null) {
			return commerceOrderItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPInstanceId=");
		msg.append(CPInstanceId);

		msg.append("}");

		throw new NoSuchOrderItemException(msg.toString());
	}

	/**
	 * Returns the last commerce order item in the ordered set where CPInstanceId = &#63;.
	 *
	 * @param CPInstanceId the cp instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order item, or <code>null</code> if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem fetchByCPInstanceId_Last(
		long CPInstanceId,
		OrderByComparator<CommerceOrderItem> orderByComparator) {

		int count = countByCPInstanceId(CPInstanceId);

		if (count == 0) {
			return null;
		}

		List<CommerceOrderItem> list = findByCPInstanceId(
			CPInstanceId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce order items before and after the current commerce order item in the ordered set where CPInstanceId = &#63;.
	 *
	 * @param commerceOrderItemId the primary key of the current commerce order item
	 * @param CPInstanceId the cp instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order item
	 * @throws NoSuchOrderItemException if a commerce order item with the primary key could not be found
	 */
	@Override
	public CommerceOrderItem[] findByCPInstanceId_PrevAndNext(
			long commerceOrderItemId, long CPInstanceId,
			OrderByComparator<CommerceOrderItem> orderByComparator)
		throws NoSuchOrderItemException {

		CommerceOrderItem commerceOrderItem = findByPrimaryKey(
			commerceOrderItemId);

		Session session = null;

		try {
			session = openSession();

			CommerceOrderItem[] array = new CommerceOrderItemImpl[3];

			array[0] = getByCPInstanceId_PrevAndNext(
				session, commerceOrderItem, CPInstanceId, orderByComparator,
				true);

			array[1] = commerceOrderItem;

			array[2] = getByCPInstanceId_PrevAndNext(
				session, commerceOrderItem, CPInstanceId, orderByComparator,
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

	protected CommerceOrderItem getByCPInstanceId_PrevAndNext(
		Session session, CommerceOrderItem commerceOrderItem, long CPInstanceId,
		OrderByComparator<CommerceOrderItem> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEORDERITEM_WHERE);

		query.append(_FINDER_COLUMN_CPINSTANCEID_CPINSTANCEID_2);

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
			query.append(CommerceOrderItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(CPInstanceId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceOrderItem)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceOrderItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce order items where CPInstanceId = &#63; from the database.
	 *
	 * @param CPInstanceId the cp instance ID
	 */
	@Override
	public void removeByCPInstanceId(long CPInstanceId) {
		for (CommerceOrderItem commerceOrderItem :
				findByCPInstanceId(
					CPInstanceId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commerceOrderItem);
		}
	}

	/**
	 * Returns the number of commerce order items where CPInstanceId = &#63;.
	 *
	 * @param CPInstanceId the cp instance ID
	 * @return the number of matching commerce order items
	 */
	@Override
	public int countByCPInstanceId(long CPInstanceId) {
		FinderPath finderPath = _finderPathCountByCPInstanceId;

		Object[] finderArgs = new Object[] {CPInstanceId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEORDERITEM_WHERE);

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

	private static final String _FINDER_COLUMN_CPINSTANCEID_CPINSTANCEID_2 =
		"commerceOrderItem.CPInstanceId = ?";

	private FinderPath _finderPathWithPaginationFindByC_I;
	private FinderPath _finderPathWithoutPaginationFindByC_I;
	private FinderPath _finderPathCountByC_I;

	/**
	 * Returns all the commerce order items where commerceOrderId = &#63; and CPInstanceId = &#63;.
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param CPInstanceId the cp instance ID
	 * @return the matching commerce order items
	 */
	@Override
	public List<CommerceOrderItem> findByC_I(
		long commerceOrderId, long CPInstanceId) {

		return findByC_I(
			commerceOrderId, CPInstanceId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the commerce order items where commerceOrderId = &#63; and CPInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param CPInstanceId the cp instance ID
	 * @param start the lower bound of the range of commerce order items
	 * @param end the upper bound of the range of commerce order items (not inclusive)
	 * @return the range of matching commerce order items
	 */
	@Override
	public List<CommerceOrderItem> findByC_I(
		long commerceOrderId, long CPInstanceId, int start, int end) {

		return findByC_I(commerceOrderId, CPInstanceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce order items where commerceOrderId = &#63; and CPInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param CPInstanceId the cp instance ID
	 * @param start the lower bound of the range of commerce order items
	 * @param end the upper bound of the range of commerce order items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce order items
	 */
	@Override
	public List<CommerceOrderItem> findByC_I(
		long commerceOrderId, long CPInstanceId, int start, int end,
		OrderByComparator<CommerceOrderItem> orderByComparator) {

		return findByC_I(
			commerceOrderId, CPInstanceId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce order items where commerceOrderId = &#63; and CPInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param CPInstanceId the cp instance ID
	 * @param start the lower bound of the range of commerce order items
	 * @param end the upper bound of the range of commerce order items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce order items
	 */
	@Override
	public List<CommerceOrderItem> findByC_I(
		long commerceOrderId, long CPInstanceId, int start, int end,
		OrderByComparator<CommerceOrderItem> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByC_I;
			finderArgs = new Object[] {commerceOrderId, CPInstanceId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByC_I;
			finderArgs = new Object[] {
				commerceOrderId, CPInstanceId, start, end, orderByComparator
			};
		}

		List<CommerceOrderItem> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceOrderItem>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceOrderItem commerceOrderItem : list) {
					if ((commerceOrderId !=
							commerceOrderItem.getCommerceOrderId()) ||
						(CPInstanceId != commerceOrderItem.getCPInstanceId())) {

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

			query.append(_SQL_SELECT_COMMERCEORDERITEM_WHERE);

			query.append(_FINDER_COLUMN_C_I_COMMERCEORDERID_2);

			query.append(_FINDER_COLUMN_C_I_CPINSTANCEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceOrderItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceOrderId);

				qPos.add(CPInstanceId);

				if (!pagination) {
					list = (List<CommerceOrderItem>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceOrderItem>)QueryUtil.list(
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
	 * Returns the first commerce order item in the ordered set where commerceOrderId = &#63; and CPInstanceId = &#63;.
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param CPInstanceId the cp instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order item
	 * @throws NoSuchOrderItemException if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem findByC_I_First(
			long commerceOrderId, long CPInstanceId,
			OrderByComparator<CommerceOrderItem> orderByComparator)
		throws NoSuchOrderItemException {

		CommerceOrderItem commerceOrderItem = fetchByC_I_First(
			commerceOrderId, CPInstanceId, orderByComparator);

		if (commerceOrderItem != null) {
			return commerceOrderItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceOrderId=");
		msg.append(commerceOrderId);

		msg.append(", CPInstanceId=");
		msg.append(CPInstanceId);

		msg.append("}");

		throw new NoSuchOrderItemException(msg.toString());
	}

	/**
	 * Returns the first commerce order item in the ordered set where commerceOrderId = &#63; and CPInstanceId = &#63;.
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param CPInstanceId the cp instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order item, or <code>null</code> if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem fetchByC_I_First(
		long commerceOrderId, long CPInstanceId,
		OrderByComparator<CommerceOrderItem> orderByComparator) {

		List<CommerceOrderItem> list = findByC_I(
			commerceOrderId, CPInstanceId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce order item in the ordered set where commerceOrderId = &#63; and CPInstanceId = &#63;.
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param CPInstanceId the cp instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order item
	 * @throws NoSuchOrderItemException if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem findByC_I_Last(
			long commerceOrderId, long CPInstanceId,
			OrderByComparator<CommerceOrderItem> orderByComparator)
		throws NoSuchOrderItemException {

		CommerceOrderItem commerceOrderItem = fetchByC_I_Last(
			commerceOrderId, CPInstanceId, orderByComparator);

		if (commerceOrderItem != null) {
			return commerceOrderItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceOrderId=");
		msg.append(commerceOrderId);

		msg.append(", CPInstanceId=");
		msg.append(CPInstanceId);

		msg.append("}");

		throw new NoSuchOrderItemException(msg.toString());
	}

	/**
	 * Returns the last commerce order item in the ordered set where commerceOrderId = &#63; and CPInstanceId = &#63;.
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param CPInstanceId the cp instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order item, or <code>null</code> if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem fetchByC_I_Last(
		long commerceOrderId, long CPInstanceId,
		OrderByComparator<CommerceOrderItem> orderByComparator) {

		int count = countByC_I(commerceOrderId, CPInstanceId);

		if (count == 0) {
			return null;
		}

		List<CommerceOrderItem> list = findByC_I(
			commerceOrderId, CPInstanceId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce order items before and after the current commerce order item in the ordered set where commerceOrderId = &#63; and CPInstanceId = &#63;.
	 *
	 * @param commerceOrderItemId the primary key of the current commerce order item
	 * @param commerceOrderId the commerce order ID
	 * @param CPInstanceId the cp instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order item
	 * @throws NoSuchOrderItemException if a commerce order item with the primary key could not be found
	 */
	@Override
	public CommerceOrderItem[] findByC_I_PrevAndNext(
			long commerceOrderItemId, long commerceOrderId, long CPInstanceId,
			OrderByComparator<CommerceOrderItem> orderByComparator)
		throws NoSuchOrderItemException {

		CommerceOrderItem commerceOrderItem = findByPrimaryKey(
			commerceOrderItemId);

		Session session = null;

		try {
			session = openSession();

			CommerceOrderItem[] array = new CommerceOrderItemImpl[3];

			array[0] = getByC_I_PrevAndNext(
				session, commerceOrderItem, commerceOrderId, CPInstanceId,
				orderByComparator, true);

			array[1] = commerceOrderItem;

			array[2] = getByC_I_PrevAndNext(
				session, commerceOrderItem, commerceOrderId, CPInstanceId,
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

	protected CommerceOrderItem getByC_I_PrevAndNext(
		Session session, CommerceOrderItem commerceOrderItem,
		long commerceOrderId, long CPInstanceId,
		OrderByComparator<CommerceOrderItem> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEORDERITEM_WHERE);

		query.append(_FINDER_COLUMN_C_I_COMMERCEORDERID_2);

		query.append(_FINDER_COLUMN_C_I_CPINSTANCEID_2);

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
			query.append(CommerceOrderItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceOrderId);

		qPos.add(CPInstanceId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceOrderItem)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceOrderItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce order items where commerceOrderId = &#63; and CPInstanceId = &#63; from the database.
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param CPInstanceId the cp instance ID
	 */
	@Override
	public void removeByC_I(long commerceOrderId, long CPInstanceId) {
		for (CommerceOrderItem commerceOrderItem :
				findByC_I(
					commerceOrderId, CPInstanceId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(commerceOrderItem);
		}
	}

	/**
	 * Returns the number of commerce order items where commerceOrderId = &#63; and CPInstanceId = &#63;.
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param CPInstanceId the cp instance ID
	 * @return the number of matching commerce order items
	 */
	@Override
	public int countByC_I(long commerceOrderId, long CPInstanceId) {
		FinderPath finderPath = _finderPathCountByC_I;

		Object[] finderArgs = new Object[] {commerceOrderId, CPInstanceId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEORDERITEM_WHERE);

			query.append(_FINDER_COLUMN_C_I_COMMERCEORDERID_2);

			query.append(_FINDER_COLUMN_C_I_CPINSTANCEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceOrderId);

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

	private static final String _FINDER_COLUMN_C_I_COMMERCEORDERID_2 =
		"commerceOrderItem.commerceOrderId = ? AND ";

	private static final String _FINDER_COLUMN_C_I_CPINSTANCEID_2 =
		"commerceOrderItem.CPInstanceId = ?";

	private FinderPath _finderPathWithPaginationFindByC_S;
	private FinderPath _finderPathWithoutPaginationFindByC_S;
	private FinderPath _finderPathCountByC_S;

	/**
	 * Returns all the commerce order items where commerceOrderId = &#63; and subscription = &#63;.
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param subscription the subscription
	 * @return the matching commerce order items
	 */
	@Override
	public List<CommerceOrderItem> findByC_S(
		long commerceOrderId, boolean subscription) {

		return findByC_S(
			commerceOrderId, subscription, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the commerce order items where commerceOrderId = &#63; and subscription = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param subscription the subscription
	 * @param start the lower bound of the range of commerce order items
	 * @param end the upper bound of the range of commerce order items (not inclusive)
	 * @return the range of matching commerce order items
	 */
	@Override
	public List<CommerceOrderItem> findByC_S(
		long commerceOrderId, boolean subscription, int start, int end) {

		return findByC_S(commerceOrderId, subscription, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce order items where commerceOrderId = &#63; and subscription = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param subscription the subscription
	 * @param start the lower bound of the range of commerce order items
	 * @param end the upper bound of the range of commerce order items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce order items
	 */
	@Override
	public List<CommerceOrderItem> findByC_S(
		long commerceOrderId, boolean subscription, int start, int end,
		OrderByComparator<CommerceOrderItem> orderByComparator) {

		return findByC_S(
			commerceOrderId, subscription, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce order items where commerceOrderId = &#63; and subscription = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param subscription the subscription
	 * @param start the lower bound of the range of commerce order items
	 * @param end the upper bound of the range of commerce order items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce order items
	 */
	@Override
	public List<CommerceOrderItem> findByC_S(
		long commerceOrderId, boolean subscription, int start, int end,
		OrderByComparator<CommerceOrderItem> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByC_S;
			finderArgs = new Object[] {commerceOrderId, subscription};
		}
		else {
			finderPath = _finderPathWithPaginationFindByC_S;
			finderArgs = new Object[] {
				commerceOrderId, subscription, start, end, orderByComparator
			};
		}

		List<CommerceOrderItem> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceOrderItem>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceOrderItem commerceOrderItem : list) {
					if ((commerceOrderId !=
							commerceOrderItem.getCommerceOrderId()) ||
						(subscription != commerceOrderItem.isSubscription())) {

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

			query.append(_SQL_SELECT_COMMERCEORDERITEM_WHERE);

			query.append(_FINDER_COLUMN_C_S_COMMERCEORDERID_2);

			query.append(_FINDER_COLUMN_C_S_SUBSCRIPTION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceOrderItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceOrderId);

				qPos.add(subscription);

				if (!pagination) {
					list = (List<CommerceOrderItem>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceOrderItem>)QueryUtil.list(
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
	 * Returns the first commerce order item in the ordered set where commerceOrderId = &#63; and subscription = &#63;.
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param subscription the subscription
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order item
	 * @throws NoSuchOrderItemException if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem findByC_S_First(
			long commerceOrderId, boolean subscription,
			OrderByComparator<CommerceOrderItem> orderByComparator)
		throws NoSuchOrderItemException {

		CommerceOrderItem commerceOrderItem = fetchByC_S_First(
			commerceOrderId, subscription, orderByComparator);

		if (commerceOrderItem != null) {
			return commerceOrderItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceOrderId=");
		msg.append(commerceOrderId);

		msg.append(", subscription=");
		msg.append(subscription);

		msg.append("}");

		throw new NoSuchOrderItemException(msg.toString());
	}

	/**
	 * Returns the first commerce order item in the ordered set where commerceOrderId = &#63; and subscription = &#63;.
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param subscription the subscription
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce order item, or <code>null</code> if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem fetchByC_S_First(
		long commerceOrderId, boolean subscription,
		OrderByComparator<CommerceOrderItem> orderByComparator) {

		List<CommerceOrderItem> list = findByC_S(
			commerceOrderId, subscription, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce order item in the ordered set where commerceOrderId = &#63; and subscription = &#63;.
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param subscription the subscription
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order item
	 * @throws NoSuchOrderItemException if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem findByC_S_Last(
			long commerceOrderId, boolean subscription,
			OrderByComparator<CommerceOrderItem> orderByComparator)
		throws NoSuchOrderItemException {

		CommerceOrderItem commerceOrderItem = fetchByC_S_Last(
			commerceOrderId, subscription, orderByComparator);

		if (commerceOrderItem != null) {
			return commerceOrderItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceOrderId=");
		msg.append(commerceOrderId);

		msg.append(", subscription=");
		msg.append(subscription);

		msg.append("}");

		throw new NoSuchOrderItemException(msg.toString());
	}

	/**
	 * Returns the last commerce order item in the ordered set where commerceOrderId = &#63; and subscription = &#63;.
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param subscription the subscription
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce order item, or <code>null</code> if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem fetchByC_S_Last(
		long commerceOrderId, boolean subscription,
		OrderByComparator<CommerceOrderItem> orderByComparator) {

		int count = countByC_S(commerceOrderId, subscription);

		if (count == 0) {
			return null;
		}

		List<CommerceOrderItem> list = findByC_S(
			commerceOrderId, subscription, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce order items before and after the current commerce order item in the ordered set where commerceOrderId = &#63; and subscription = &#63;.
	 *
	 * @param commerceOrderItemId the primary key of the current commerce order item
	 * @param commerceOrderId the commerce order ID
	 * @param subscription the subscription
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce order item
	 * @throws NoSuchOrderItemException if a commerce order item with the primary key could not be found
	 */
	@Override
	public CommerceOrderItem[] findByC_S_PrevAndNext(
			long commerceOrderItemId, long commerceOrderId,
			boolean subscription,
			OrderByComparator<CommerceOrderItem> orderByComparator)
		throws NoSuchOrderItemException {

		CommerceOrderItem commerceOrderItem = findByPrimaryKey(
			commerceOrderItemId);

		Session session = null;

		try {
			session = openSession();

			CommerceOrderItem[] array = new CommerceOrderItemImpl[3];

			array[0] = getByC_S_PrevAndNext(
				session, commerceOrderItem, commerceOrderId, subscription,
				orderByComparator, true);

			array[1] = commerceOrderItem;

			array[2] = getByC_S_PrevAndNext(
				session, commerceOrderItem, commerceOrderId, subscription,
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

	protected CommerceOrderItem getByC_S_PrevAndNext(
		Session session, CommerceOrderItem commerceOrderItem,
		long commerceOrderId, boolean subscription,
		OrderByComparator<CommerceOrderItem> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEORDERITEM_WHERE);

		query.append(_FINDER_COLUMN_C_S_COMMERCEORDERID_2);

		query.append(_FINDER_COLUMN_C_S_SUBSCRIPTION_2);

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
			query.append(CommerceOrderItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceOrderId);

		qPos.add(subscription);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceOrderItem)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceOrderItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce order items where commerceOrderId = &#63; and subscription = &#63; from the database.
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param subscription the subscription
	 */
	@Override
	public void removeByC_S(long commerceOrderId, boolean subscription) {
		for (CommerceOrderItem commerceOrderItem :
				findByC_S(
					commerceOrderId, subscription, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(commerceOrderItem);
		}
	}

	/**
	 * Returns the number of commerce order items where commerceOrderId = &#63; and subscription = &#63;.
	 *
	 * @param commerceOrderId the commerce order ID
	 * @param subscription the subscription
	 * @return the number of matching commerce order items
	 */
	@Override
	public int countByC_S(long commerceOrderId, boolean subscription) {
		FinderPath finderPath = _finderPathCountByC_S;

		Object[] finderArgs = new Object[] {commerceOrderId, subscription};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEORDERITEM_WHERE);

			query.append(_FINDER_COLUMN_C_S_COMMERCEORDERID_2);

			query.append(_FINDER_COLUMN_C_S_SUBSCRIPTION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceOrderId);

				qPos.add(subscription);

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

	private static final String _FINDER_COLUMN_C_S_COMMERCEORDERID_2 =
		"commerceOrderItem.commerceOrderId = ? AND ";

	private static final String _FINDER_COLUMN_C_S_SUBSCRIPTION_2 =
		"commerceOrderItem.subscription = ?";

	private FinderPath _finderPathFetchByC_ERC;
	private FinderPath _finderPathCountByC_ERC;

	/**
	 * Returns the commerce order item where companyId = &#63; and externalReferenceCode = &#63; or throws a <code>NoSuchOrderItemException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce order item
	 * @throws NoSuchOrderItemException if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem findByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchOrderItemException {

		CommerceOrderItem commerceOrderItem = fetchByC_ERC(
			companyId, externalReferenceCode);

		if (commerceOrderItem == null) {
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

			throw new NoSuchOrderItemException(msg.toString());
		}

		return commerceOrderItem;
	}

	/**
	 * Returns the commerce order item where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce order item, or <code>null</code> if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem fetchByC_ERC(
		long companyId, String externalReferenceCode) {

		return fetchByC_ERC(companyId, externalReferenceCode, true);
	}

	/**
	 * Returns the commerce order item where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching commerce order item, or <code>null</code> if a matching commerce order item could not be found
	 */
	@Override
	public CommerceOrderItem fetchByC_ERC(
		long companyId, String externalReferenceCode,
		boolean retrieveFromCache) {

		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		Object[] finderArgs = new Object[] {companyId, externalReferenceCode};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_ERC, finderArgs, this);
		}

		if (result instanceof CommerceOrderItem) {
			CommerceOrderItem commerceOrderItem = (CommerceOrderItem)result;

			if ((companyId != commerceOrderItem.getCompanyId()) ||
				!Objects.equals(
					externalReferenceCode,
					commerceOrderItem.getExternalReferenceCode())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COMMERCEORDERITEM_WHERE);

			query.append(_FINDER_COLUMN_C_ERC_COMPANYID_2);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode.isEmpty()) {
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

				List<CommerceOrderItem> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByC_ERC, finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"CommerceOrderItemPersistenceImpl.fetchByC_ERC(long, String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CommerceOrderItem commerceOrderItem = list.get(0);

					result = commerceOrderItem;

					cacheResult(commerceOrderItem);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByC_ERC, finderArgs);

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
			return (CommerceOrderItem)result;
		}
	}

	/**
	 * Removes the commerce order item where companyId = &#63; and externalReferenceCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the commerce order item that was removed
	 */
	@Override
	public CommerceOrderItem removeByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchOrderItemException {

		CommerceOrderItem commerceOrderItem = findByC_ERC(
			companyId, externalReferenceCode);

		return remove(commerceOrderItem);
	}

	/**
	 * Returns the number of commerce order items where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the number of matching commerce order items
	 */
	@Override
	public int countByC_ERC(long companyId, String externalReferenceCode) {
		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		FinderPath finderPath = _finderPathCountByC_ERC;

		Object[] finderArgs = new Object[] {companyId, externalReferenceCode};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEORDERITEM_WHERE);

			query.append(_FINDER_COLUMN_C_ERC_COMPANYID_2);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode.isEmpty()) {
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

	private static final String _FINDER_COLUMN_C_ERC_COMPANYID_2 =
		"commerceOrderItem.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2 =
		"commerceOrderItem.externalReferenceCode = ?";

	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3 =
		"(commerceOrderItem.externalReferenceCode IS NULL OR commerceOrderItem.externalReferenceCode = '')";

	public CommerceOrderItemPersistenceImpl() {
		setModelClass(CommerceOrderItem.class);
	}

	/**
	 * Caches the commerce order item in the entity cache if it is enabled.
	 *
	 * @param commerceOrderItem the commerce order item
	 */
	@Override
	public void cacheResult(CommerceOrderItem commerceOrderItem) {
		entityCache.putResult(
			CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderItemImpl.class, commerceOrderItem.getPrimaryKey(),
			commerceOrderItem);

		finderCache.putResult(
			_finderPathFetchByC_ERC,
			new Object[] {
				commerceOrderItem.getCompanyId(),
				commerceOrderItem.getExternalReferenceCode()
			},
			commerceOrderItem);

		commerceOrderItem.resetOriginalValues();
	}

	/**
	 * Caches the commerce order items in the entity cache if it is enabled.
	 *
	 * @param commerceOrderItems the commerce order items
	 */
	@Override
	public void cacheResult(List<CommerceOrderItem> commerceOrderItems) {
		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			if (entityCache.getResult(
					CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
					CommerceOrderItemImpl.class,
					commerceOrderItem.getPrimaryKey()) == null) {

				cacheResult(commerceOrderItem);
			}
			else {
				commerceOrderItem.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce order items.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceOrderItemImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce order item.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceOrderItem commerceOrderItem) {
		entityCache.removeResult(
			CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderItemImpl.class, commerceOrderItem.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(CommerceOrderItemModelImpl)commerceOrderItem, true);
	}

	@Override
	public void clearCache(List<CommerceOrderItem> commerceOrderItems) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			entityCache.removeResult(
				CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
				CommerceOrderItemImpl.class, commerceOrderItem.getPrimaryKey());

			clearUniqueFindersCache(
				(CommerceOrderItemModelImpl)commerceOrderItem, true);
		}
	}

	protected void cacheUniqueFindersCache(
		CommerceOrderItemModelImpl commerceOrderItemModelImpl) {

		Object[] args = new Object[] {
			commerceOrderItemModelImpl.getCompanyId(),
			commerceOrderItemModelImpl.getExternalReferenceCode()
		};

		finderCache.putResult(
			_finderPathCountByC_ERC, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_ERC, args, commerceOrderItemModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CommerceOrderItemModelImpl commerceOrderItemModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				commerceOrderItemModelImpl.getCompanyId(),
				commerceOrderItemModelImpl.getExternalReferenceCode()
			};

			finderCache.removeResult(_finderPathCountByC_ERC, args);
			finderCache.removeResult(_finderPathFetchByC_ERC, args);
		}

		if ((commerceOrderItemModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_ERC.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				commerceOrderItemModelImpl.getOriginalCompanyId(),
				commerceOrderItemModelImpl.getOriginalExternalReferenceCode()
			};

			finderCache.removeResult(_finderPathCountByC_ERC, args);
			finderCache.removeResult(_finderPathFetchByC_ERC, args);
		}
	}

	/**
	 * Creates a new commerce order item with the primary key. Does not add the commerce order item to the database.
	 *
	 * @param commerceOrderItemId the primary key for the new commerce order item
	 * @return the new commerce order item
	 */
	@Override
	public CommerceOrderItem create(long commerceOrderItemId) {
		CommerceOrderItem commerceOrderItem = new CommerceOrderItemImpl();

		commerceOrderItem.setNew(true);
		commerceOrderItem.setPrimaryKey(commerceOrderItemId);

		commerceOrderItem.setCompanyId(CompanyThreadLocal.getCompanyId());

		return commerceOrderItem;
	}

	/**
	 * Removes the commerce order item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceOrderItemId the primary key of the commerce order item
	 * @return the commerce order item that was removed
	 * @throws NoSuchOrderItemException if a commerce order item with the primary key could not be found
	 */
	@Override
	public CommerceOrderItem remove(long commerceOrderItemId)
		throws NoSuchOrderItemException {

		return remove((Serializable)commerceOrderItemId);
	}

	/**
	 * Removes the commerce order item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce order item
	 * @return the commerce order item that was removed
	 * @throws NoSuchOrderItemException if a commerce order item with the primary key could not be found
	 */
	@Override
	public CommerceOrderItem remove(Serializable primaryKey)
		throws NoSuchOrderItemException {

		Session session = null;

		try {
			session = openSession();

			CommerceOrderItem commerceOrderItem =
				(CommerceOrderItem)session.get(
					CommerceOrderItemImpl.class, primaryKey);

			if (commerceOrderItem == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOrderItemException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commerceOrderItem);
		}
		catch (NoSuchOrderItemException nsee) {
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
	protected CommerceOrderItem removeImpl(
		CommerceOrderItem commerceOrderItem) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceOrderItem)) {
				commerceOrderItem = (CommerceOrderItem)session.get(
					CommerceOrderItemImpl.class,
					commerceOrderItem.getPrimaryKeyObj());
			}

			if (commerceOrderItem != null) {
				session.delete(commerceOrderItem);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceOrderItem != null) {
			clearCache(commerceOrderItem);
		}

		return commerceOrderItem;
	}

	@Override
	public CommerceOrderItem updateImpl(CommerceOrderItem commerceOrderItem) {
		boolean isNew = commerceOrderItem.isNew();

		if (!(commerceOrderItem instanceof CommerceOrderItemModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceOrderItem.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					commerceOrderItem);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceOrderItem proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceOrderItem implementation " +
					commerceOrderItem.getClass());
		}

		CommerceOrderItemModelImpl commerceOrderItemModelImpl =
			(CommerceOrderItemModelImpl)commerceOrderItem;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceOrderItem.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceOrderItem.setCreateDate(now);
			}
			else {
				commerceOrderItem.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!commerceOrderItemModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceOrderItem.setModifiedDate(now);
			}
			else {
				commerceOrderItem.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceOrderItem.isNew()) {
				session.save(commerceOrderItem);

				commerceOrderItem.setNew(false);
			}
			else {
				commerceOrderItem = (CommerceOrderItem)session.merge(
					commerceOrderItem);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceOrderItemModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				commerceOrderItemModelImpl.getCommerceOrderId()
			};

			finderCache.removeResult(_finderPathCountByCommerceOrderId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCommerceOrderId, args);

			args = new Object[] {commerceOrderItemModelImpl.getCProductId()};

			finderCache.removeResult(_finderPathCountByCProductId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCProductId, args);

			args = new Object[] {commerceOrderItemModelImpl.getCPInstanceId()};

			finderCache.removeResult(_finderPathCountByCPInstanceId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCPInstanceId, args);

			args = new Object[] {
				commerceOrderItemModelImpl.getCommerceOrderId(),
				commerceOrderItemModelImpl.getCPInstanceId()
			};

			finderCache.removeResult(_finderPathCountByC_I, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_I, args);

			args = new Object[] {
				commerceOrderItemModelImpl.getCommerceOrderId(),
				commerceOrderItemModelImpl.isSubscription()
			};

			finderCache.removeResult(_finderPathCountByC_S, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_S, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((commerceOrderItemModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCommerceOrderId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceOrderItemModelImpl.getOriginalCommerceOrderId()
				};

				finderCache.removeResult(
					_finderPathCountByCommerceOrderId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommerceOrderId, args);

				args = new Object[] {
					commerceOrderItemModelImpl.getCommerceOrderId()
				};

				finderCache.removeResult(
					_finderPathCountByCommerceOrderId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommerceOrderId, args);
			}

			if ((commerceOrderItemModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCProductId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceOrderItemModelImpl.getOriginalCProductId()
				};

				finderCache.removeResult(_finderPathCountByCProductId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCProductId, args);

				args = new Object[] {
					commerceOrderItemModelImpl.getCProductId()
				};

				finderCache.removeResult(_finderPathCountByCProductId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCProductId, args);
			}

			if ((commerceOrderItemModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCPInstanceId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceOrderItemModelImpl.getOriginalCPInstanceId()
				};

				finderCache.removeResult(_finderPathCountByCPInstanceId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCPInstanceId, args);

				args = new Object[] {
					commerceOrderItemModelImpl.getCPInstanceId()
				};

				finderCache.removeResult(_finderPathCountByCPInstanceId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCPInstanceId, args);
			}

			if ((commerceOrderItemModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_I.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceOrderItemModelImpl.getOriginalCommerceOrderId(),
					commerceOrderItemModelImpl.getOriginalCPInstanceId()
				};

				finderCache.removeResult(_finderPathCountByC_I, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_I, args);

				args = new Object[] {
					commerceOrderItemModelImpl.getCommerceOrderId(),
					commerceOrderItemModelImpl.getCPInstanceId()
				};

				finderCache.removeResult(_finderPathCountByC_I, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_I, args);
			}

			if ((commerceOrderItemModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_S.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceOrderItemModelImpl.getOriginalCommerceOrderId(),
					commerceOrderItemModelImpl.getOriginalSubscription()
				};

				finderCache.removeResult(_finderPathCountByC_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_S, args);

				args = new Object[] {
					commerceOrderItemModelImpl.getCommerceOrderId(),
					commerceOrderItemModelImpl.isSubscription()
				};

				finderCache.removeResult(_finderPathCountByC_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_S, args);
			}
		}

		entityCache.putResult(
			CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderItemImpl.class, commerceOrderItem.getPrimaryKey(),
			commerceOrderItem, false);

		clearUniqueFindersCache(commerceOrderItemModelImpl, false);
		cacheUniqueFindersCache(commerceOrderItemModelImpl);

		commerceOrderItem.resetOriginalValues();

		return commerceOrderItem;
	}

	/**
	 * Returns the commerce order item with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce order item
	 * @return the commerce order item
	 * @throws NoSuchOrderItemException if a commerce order item with the primary key could not be found
	 */
	@Override
	public CommerceOrderItem findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOrderItemException {

		CommerceOrderItem commerceOrderItem = fetchByPrimaryKey(primaryKey);

		if (commerceOrderItem == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOrderItemException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commerceOrderItem;
	}

	/**
	 * Returns the commerce order item with the primary key or throws a <code>NoSuchOrderItemException</code> if it could not be found.
	 *
	 * @param commerceOrderItemId the primary key of the commerce order item
	 * @return the commerce order item
	 * @throws NoSuchOrderItemException if a commerce order item with the primary key could not be found
	 */
	@Override
	public CommerceOrderItem findByPrimaryKey(long commerceOrderItemId)
		throws NoSuchOrderItemException {

		return findByPrimaryKey((Serializable)commerceOrderItemId);
	}

	/**
	 * Returns the commerce order item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce order item
	 * @return the commerce order item, or <code>null</code> if a commerce order item with the primary key could not be found
	 */
	@Override
	public CommerceOrderItem fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderItemImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceOrderItem commerceOrderItem = (CommerceOrderItem)serializable;

		if (commerceOrderItem == null) {
			Session session = null;

			try {
				session = openSession();

				commerceOrderItem = (CommerceOrderItem)session.get(
					CommerceOrderItemImpl.class, primaryKey);

				if (commerceOrderItem != null) {
					cacheResult(commerceOrderItem);
				}
				else {
					entityCache.putResult(
						CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
						CommerceOrderItemImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
					CommerceOrderItemImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceOrderItem;
	}

	/**
	 * Returns the commerce order item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceOrderItemId the primary key of the commerce order item
	 * @return the commerce order item, or <code>null</code> if a commerce order item with the primary key could not be found
	 */
	@Override
	public CommerceOrderItem fetchByPrimaryKey(long commerceOrderItemId) {
		return fetchByPrimaryKey((Serializable)commerceOrderItemId);
	}

	@Override
	public Map<Serializable, CommerceOrderItem> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceOrderItem> map =
			new HashMap<Serializable, CommerceOrderItem>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceOrderItem commerceOrderItem = fetchByPrimaryKey(primaryKey);

			if (commerceOrderItem != null) {
				map.put(primaryKey, commerceOrderItem);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
				CommerceOrderItemImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceOrderItem)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_COMMERCEORDERITEM_WHERE_PKS_IN);

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

			for (CommerceOrderItem commerceOrderItem :
					(List<CommerceOrderItem>)q.list()) {

				map.put(
					commerceOrderItem.getPrimaryKeyObj(), commerceOrderItem);

				cacheResult(commerceOrderItem);

				uncachedPrimaryKeys.remove(
					commerceOrderItem.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
					CommerceOrderItemImpl.class, primaryKey, nullModel);
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
	 * Returns all the commerce order items.
	 *
	 * @return the commerce order items
	 */
	@Override
	public List<CommerceOrderItem> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce order items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce order items
	 * @param end the upper bound of the range of commerce order items (not inclusive)
	 * @return the range of commerce order items
	 */
	@Override
	public List<CommerceOrderItem> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce order items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce order items
	 * @param end the upper bound of the range of commerce order items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce order items
	 */
	@Override
	public List<CommerceOrderItem> findAll(
		int start, int end,
		OrderByComparator<CommerceOrderItem> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce order items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceOrderItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce order items
	 * @param end the upper bound of the range of commerce order items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce order items
	 */
	@Override
	public List<CommerceOrderItem> findAll(
		int start, int end,
		OrderByComparator<CommerceOrderItem> orderByComparator,
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

		List<CommerceOrderItem> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceOrderItem>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEORDERITEM);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEORDERITEM;

				if (pagination) {
					sql = sql.concat(CommerceOrderItemModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceOrderItem>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceOrderItem>)QueryUtil.list(
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
	 * Removes all the commerce order items from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceOrderItem commerceOrderItem : findAll()) {
			remove(commerceOrderItem);
		}
	}

	/**
	 * Returns the number of commerce order items.
	 *
	 * @return the number of commerce order items
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCEORDERITEM);

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
	protected Map<String, Integer> getTableColumnsMap() {
		return CommerceOrderItemModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce order item persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderItemImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByCommerceOrderId = new FinderPath(
			CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderItemImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCommerceOrderId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCommerceOrderId = new FinderPath(
			CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCommerceOrderId",
			new String[] {Long.class.getName()},
			CommerceOrderItemModelImpl.COMMERCEORDERID_COLUMN_BITMASK |
			CommerceOrderItemModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByCommerceOrderId = new FinderPath(
			CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCommerceOrderId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByCProductId = new FinderPath(
			CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderItemImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCProductId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCProductId = new FinderPath(
			CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCProductId",
			new String[] {Long.class.getName()},
			CommerceOrderItemModelImpl.CPRODUCTID_COLUMN_BITMASK |
			CommerceOrderItemModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByCProductId = new FinderPath(
			CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCProductId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByCPInstanceId = new FinderPath(
			CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderItemImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCPInstanceId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCPInstanceId = new FinderPath(
			CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCPInstanceId",
			new String[] {Long.class.getName()},
			CommerceOrderItemModelImpl.CPINSTANCEID_COLUMN_BITMASK |
			CommerceOrderItemModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByCPInstanceId = new FinderPath(
			CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCPInstanceId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByC_I = new FinderPath(
			CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderItemImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_I",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_I = new FinderPath(
			CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_I",
			new String[] {Long.class.getName(), Long.class.getName()},
			CommerceOrderItemModelImpl.COMMERCEORDERID_COLUMN_BITMASK |
			CommerceOrderItemModelImpl.CPINSTANCEID_COLUMN_BITMASK |
			CommerceOrderItemModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByC_I = new FinderPath(
			CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_I",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByC_S = new FinderPath(
			CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderItemImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_S",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_S = new FinderPath(
			CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_S",
			new String[] {Long.class.getName(), Boolean.class.getName()},
			CommerceOrderItemModelImpl.COMMERCEORDERID_COLUMN_BITMASK |
			CommerceOrderItemModelImpl.SUBSCRIPTION_COLUMN_BITMASK |
			CommerceOrderItemModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByC_S = new FinderPath(
			CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_S",
			new String[] {Long.class.getName(), Boolean.class.getName()});

		_finderPathFetchByC_ERC = new FinderPath(
			CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceOrderItemImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_ERC",
			new String[] {Long.class.getName(), String.class.getName()},
			CommerceOrderItemModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceOrderItemModelImpl.EXTERNALREFERENCECODE_COLUMN_BITMASK);

		_finderPathCountByC_ERC = new FinderPath(
			CommerceOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceOrderItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_ERC",
			new String[] {Long.class.getName(), String.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(CommerceOrderItemImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_COMMERCEORDERITEM =
		"SELECT commerceOrderItem FROM CommerceOrderItem commerceOrderItem";

	private static final String _SQL_SELECT_COMMERCEORDERITEM_WHERE_PKS_IN =
		"SELECT commerceOrderItem FROM CommerceOrderItem commerceOrderItem WHERE commerceOrderItemId IN (";

	private static final String _SQL_SELECT_COMMERCEORDERITEM_WHERE =
		"SELECT commerceOrderItem FROM CommerceOrderItem commerceOrderItem WHERE ";

	private static final String _SQL_COUNT_COMMERCEORDERITEM =
		"SELECT COUNT(commerceOrderItem) FROM CommerceOrderItem commerceOrderItem";

	private static final String _SQL_COUNT_COMMERCEORDERITEM_WHERE =
		"SELECT COUNT(commerceOrderItem) FROM CommerceOrderItem commerceOrderItem WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceOrderItem.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommerceOrderItem exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommerceOrderItem exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceOrderItemPersistenceImpl.class);

}