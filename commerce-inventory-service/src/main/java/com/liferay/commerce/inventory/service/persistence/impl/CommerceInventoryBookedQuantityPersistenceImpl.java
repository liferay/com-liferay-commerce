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

import com.liferay.commerce.inventory.exception.NoSuchInventoryBookedQuantityException;
import com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity;
import com.liferay.commerce.inventory.model.impl.CommerceInventoryBookedQuantityImpl;
import com.liferay.commerce.inventory.model.impl.CommerceInventoryBookedQuantityModelImpl;
import com.liferay.commerce.inventory.service.persistence.CommerceInventoryBookedQuantityPersistence;
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
 * The persistence implementation for the commerce inventory booked quantity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @generated
 */
@ProviderType
public class CommerceInventoryBookedQuantityPersistenceImpl
	extends BasePersistenceImpl<CommerceInventoryBookedQuantity>
	implements CommerceInventoryBookedQuantityPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommerceInventoryBookedQuantityUtil</code> to access the commerce inventory booked quantity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommerceInventoryBookedQuantityImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindBySku;
	private FinderPath _finderPathWithoutPaginationFindBySku;
	private FinderPath _finderPathCountBySku;

	/**
	 * Returns all the commerce inventory booked quantities where sku = &#63;.
	 *
	 * @param sku the sku
	 * @return the matching commerce inventory booked quantities
	 */
	@Override
	public List<CommerceInventoryBookedQuantity> findBySku(String sku) {
		return findBySku(sku, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory booked quantities where sku = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryBookedQuantityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sku the sku
	 * @param start the lower bound of the range of commerce inventory booked quantities
	 * @param end the upper bound of the range of commerce inventory booked quantities (not inclusive)
	 * @return the range of matching commerce inventory booked quantities
	 */
	@Override
	public List<CommerceInventoryBookedQuantity> findBySku(
		String sku, int start, int end) {

		return findBySku(sku, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory booked quantities where sku = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryBookedQuantityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sku the sku
	 * @param start the lower bound of the range of commerce inventory booked quantities
	 * @param end the upper bound of the range of commerce inventory booked quantities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory booked quantities
	 */
	@Override
	public List<CommerceInventoryBookedQuantity> findBySku(
		String sku, int start, int end,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator) {

		return findBySku(sku, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory booked quantities where sku = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryBookedQuantityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sku the sku
	 * @param start the lower bound of the range of commerce inventory booked quantities
	 * @param end the upper bound of the range of commerce inventory booked quantities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce inventory booked quantities
	 */
	@Override
	public List<CommerceInventoryBookedQuantity> findBySku(
		String sku, int start, int end,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator,
		boolean useFinderCache) {

		sku = Objects.toString(sku, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBySku;
				finderArgs = new Object[] {sku};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBySku;
			finderArgs = new Object[] {sku, start, end, orderByComparator};
		}

		List<CommerceInventoryBookedQuantity> list = null;

		if (useFinderCache) {
			list = (List<CommerceInventoryBookedQuantity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceInventoryBookedQuantity
						commerceInventoryBookedQuantity : list) {

					if (!sku.equals(commerceInventoryBookedQuantity.getSku())) {
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

			query.append(_SQL_SELECT_COMMERCEINVENTORYBOOKEDQUANTITY_WHERE);

			boolean bindSku = false;

			if (sku.isEmpty()) {
				query.append(_FINDER_COLUMN_SKU_SKU_3);
			}
			else {
				bindSku = true;

				query.append(_FINDER_COLUMN_SKU_SKU_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(
					CommerceInventoryBookedQuantityModelImpl.ORDER_BY_JPQL);
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
					list =
						(List<CommerceInventoryBookedQuantity>)QueryUtil.list(
							q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list =
						(List<CommerceInventoryBookedQuantity>)QueryUtil.list(
							q, getDialect(), start, end);
				}

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first commerce inventory booked quantity in the ordered set where sku = &#63;.
	 *
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory booked quantity
	 * @throws NoSuchInventoryBookedQuantityException if a matching commerce inventory booked quantity could not be found
	 */
	@Override
	public CommerceInventoryBookedQuantity findBySku_First(
			String sku,
			OrderByComparator<CommerceInventoryBookedQuantity>
				orderByComparator)
		throws NoSuchInventoryBookedQuantityException {

		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity =
			fetchBySku_First(sku, orderByComparator);

		if (commerceInventoryBookedQuantity != null) {
			return commerceInventoryBookedQuantity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sku=");
		msg.append(sku);

		msg.append("}");

		throw new NoSuchInventoryBookedQuantityException(msg.toString());
	}

	/**
	 * Returns the first commerce inventory booked quantity in the ordered set where sku = &#63;.
	 *
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory booked quantity, or <code>null</code> if a matching commerce inventory booked quantity could not be found
	 */
	@Override
	public CommerceInventoryBookedQuantity fetchBySku_First(
		String sku,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator) {

		List<CommerceInventoryBookedQuantity> list = findBySku(
			sku, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce inventory booked quantity in the ordered set where sku = &#63;.
	 *
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory booked quantity
	 * @throws NoSuchInventoryBookedQuantityException if a matching commerce inventory booked quantity could not be found
	 */
	@Override
	public CommerceInventoryBookedQuantity findBySku_Last(
			String sku,
			OrderByComparator<CommerceInventoryBookedQuantity>
				orderByComparator)
		throws NoSuchInventoryBookedQuantityException {

		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity =
			fetchBySku_Last(sku, orderByComparator);

		if (commerceInventoryBookedQuantity != null) {
			return commerceInventoryBookedQuantity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sku=");
		msg.append(sku);

		msg.append("}");

		throw new NoSuchInventoryBookedQuantityException(msg.toString());
	}

	/**
	 * Returns the last commerce inventory booked quantity in the ordered set where sku = &#63;.
	 *
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory booked quantity, or <code>null</code> if a matching commerce inventory booked quantity could not be found
	 */
	@Override
	public CommerceInventoryBookedQuantity fetchBySku_Last(
		String sku,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator) {

		int count = countBySku(sku);

		if (count == 0) {
			return null;
		}

		List<CommerceInventoryBookedQuantity> list = findBySku(
			sku, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce inventory booked quantities before and after the current commerce inventory booked quantity in the ordered set where sku = &#63;.
	 *
	 * @param commerceInventoryBookedQuantityId the primary key of the current commerce inventory booked quantity
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory booked quantity
	 * @throws NoSuchInventoryBookedQuantityException if a commerce inventory booked quantity with the primary key could not be found
	 */
	@Override
	public CommerceInventoryBookedQuantity[] findBySku_PrevAndNext(
			long commerceInventoryBookedQuantityId, String sku,
			OrderByComparator<CommerceInventoryBookedQuantity>
				orderByComparator)
		throws NoSuchInventoryBookedQuantityException {

		sku = Objects.toString(sku, "");

		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity =
			findByPrimaryKey(commerceInventoryBookedQuantityId);

		Session session = null;

		try {
			session = openSession();

			CommerceInventoryBookedQuantity[] array =
				new CommerceInventoryBookedQuantityImpl[3];

			array[0] = getBySku_PrevAndNext(
				session, commerceInventoryBookedQuantity, sku,
				orderByComparator, true);

			array[1] = commerceInventoryBookedQuantity;

			array[2] = getBySku_PrevAndNext(
				session, commerceInventoryBookedQuantity, sku,
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

	protected CommerceInventoryBookedQuantity getBySku_PrevAndNext(
		Session session,
		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity,
		String sku,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEINVENTORYBOOKEDQUANTITY_WHERE);

		boolean bindSku = false;

		if (sku.isEmpty()) {
			query.append(_FINDER_COLUMN_SKU_SKU_3);
		}
		else {
			bindSku = true;

			query.append(_FINDER_COLUMN_SKU_SKU_2);
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
			query.append(
				CommerceInventoryBookedQuantityModelImpl.ORDER_BY_JPQL);
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
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceInventoryBookedQuantity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceInventoryBookedQuantity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce inventory booked quantities where sku = &#63; from the database.
	 *
	 * @param sku the sku
	 */
	@Override
	public void removeBySku(String sku) {
		for (CommerceInventoryBookedQuantity commerceInventoryBookedQuantity :
				findBySku(sku, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commerceInventoryBookedQuantity);
		}
	}

	/**
	 * Returns the number of commerce inventory booked quantities where sku = &#63;.
	 *
	 * @param sku the sku
	 * @return the number of matching commerce inventory booked quantities
	 */
	@Override
	public int countBySku(String sku) {
		sku = Objects.toString(sku, "");

		FinderPath finderPath = _finderPathCountBySku;

		Object[] finderArgs = new Object[] {sku};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEINVENTORYBOOKEDQUANTITY_WHERE);

			boolean bindSku = false;

			if (sku.isEmpty()) {
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

	private static final String _FINDER_COLUMN_SKU_SKU_2 =
		"commerceInventoryBookedQuantity.sku = ?";

	private static final String _FINDER_COLUMN_SKU_SKU_3 =
		"(commerceInventoryBookedQuantity.sku IS NULL OR commerceInventoryBookedQuantity.sku = '')";

	private FinderPath _finderPathWithPaginationFindByLtExpirationDate;
	private FinderPath _finderPathWithPaginationCountByLtExpirationDate;

	/**
	 * Returns all the commerce inventory booked quantities where expirationDate &lt; &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @return the matching commerce inventory booked quantities
	 */
	@Override
	public List<CommerceInventoryBookedQuantity> findByLtExpirationDate(
		Date expirationDate) {

		return findByLtExpirationDate(
			expirationDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory booked quantities where expirationDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryBookedQuantityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param expirationDate the expiration date
	 * @param start the lower bound of the range of commerce inventory booked quantities
	 * @param end the upper bound of the range of commerce inventory booked quantities (not inclusive)
	 * @return the range of matching commerce inventory booked quantities
	 */
	@Override
	public List<CommerceInventoryBookedQuantity> findByLtExpirationDate(
		Date expirationDate, int start, int end) {

		return findByLtExpirationDate(expirationDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory booked quantities where expirationDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryBookedQuantityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param expirationDate the expiration date
	 * @param start the lower bound of the range of commerce inventory booked quantities
	 * @param end the upper bound of the range of commerce inventory booked quantities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory booked quantities
	 */
	@Override
	public List<CommerceInventoryBookedQuantity> findByLtExpirationDate(
		Date expirationDate, int start, int end,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator) {

		return findByLtExpirationDate(
			expirationDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory booked quantities where expirationDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryBookedQuantityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param expirationDate the expiration date
	 * @param start the lower bound of the range of commerce inventory booked quantities
	 * @param end the upper bound of the range of commerce inventory booked quantities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce inventory booked quantities
	 */
	@Override
	public List<CommerceInventoryBookedQuantity> findByLtExpirationDate(
		Date expirationDate, int start, int end,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByLtExpirationDate;
		finderArgs = new Object[] {
			_getTime(expirationDate), start, end, orderByComparator
		};

		List<CommerceInventoryBookedQuantity> list = null;

		if (useFinderCache) {
			list = (List<CommerceInventoryBookedQuantity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceInventoryBookedQuantity
						commerceInventoryBookedQuantity : list) {

					if ((expirationDate.getTime() <=
							commerceInventoryBookedQuantity.
								getExpirationDate().getTime())) {

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

			query.append(_SQL_SELECT_COMMERCEINVENTORYBOOKEDQUANTITY_WHERE);

			boolean bindExpirationDate = false;

			if (expirationDate == null) {
				query.append(_FINDER_COLUMN_LTEXPIRATIONDATE_EXPIRATIONDATE_1);
			}
			else {
				bindExpirationDate = true;

				query.append(_FINDER_COLUMN_LTEXPIRATIONDATE_EXPIRATIONDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(
					CommerceInventoryBookedQuantityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindExpirationDate) {
					qPos.add(new Timestamp(expirationDate.getTime()));
				}

				if (!pagination) {
					list =
						(List<CommerceInventoryBookedQuantity>)QueryUtil.list(
							q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list =
						(List<CommerceInventoryBookedQuantity>)QueryUtil.list(
							q, getDialect(), start, end);
				}

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first commerce inventory booked quantity in the ordered set where expirationDate &lt; &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory booked quantity
	 * @throws NoSuchInventoryBookedQuantityException if a matching commerce inventory booked quantity could not be found
	 */
	@Override
	public CommerceInventoryBookedQuantity findByLtExpirationDate_First(
			Date expirationDate,
			OrderByComparator<CommerceInventoryBookedQuantity>
				orderByComparator)
		throws NoSuchInventoryBookedQuantityException {

		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity =
			fetchByLtExpirationDate_First(expirationDate, orderByComparator);

		if (commerceInventoryBookedQuantity != null) {
			return commerceInventoryBookedQuantity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("expirationDate=");
		msg.append(expirationDate);

		msg.append("}");

		throw new NoSuchInventoryBookedQuantityException(msg.toString());
	}

	/**
	 * Returns the first commerce inventory booked quantity in the ordered set where expirationDate &lt; &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory booked quantity, or <code>null</code> if a matching commerce inventory booked quantity could not be found
	 */
	@Override
	public CommerceInventoryBookedQuantity fetchByLtExpirationDate_First(
		Date expirationDate,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator) {

		List<CommerceInventoryBookedQuantity> list = findByLtExpirationDate(
			expirationDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce inventory booked quantity in the ordered set where expirationDate &lt; &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory booked quantity
	 * @throws NoSuchInventoryBookedQuantityException if a matching commerce inventory booked quantity could not be found
	 */
	@Override
	public CommerceInventoryBookedQuantity findByLtExpirationDate_Last(
			Date expirationDate,
			OrderByComparator<CommerceInventoryBookedQuantity>
				orderByComparator)
		throws NoSuchInventoryBookedQuantityException {

		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity =
			fetchByLtExpirationDate_Last(expirationDate, orderByComparator);

		if (commerceInventoryBookedQuantity != null) {
			return commerceInventoryBookedQuantity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("expirationDate=");
		msg.append(expirationDate);

		msg.append("}");

		throw new NoSuchInventoryBookedQuantityException(msg.toString());
	}

	/**
	 * Returns the last commerce inventory booked quantity in the ordered set where expirationDate &lt; &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory booked quantity, or <code>null</code> if a matching commerce inventory booked quantity could not be found
	 */
	@Override
	public CommerceInventoryBookedQuantity fetchByLtExpirationDate_Last(
		Date expirationDate,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator) {

		int count = countByLtExpirationDate(expirationDate);

		if (count == 0) {
			return null;
		}

		List<CommerceInventoryBookedQuantity> list = findByLtExpirationDate(
			expirationDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce inventory booked quantities before and after the current commerce inventory booked quantity in the ordered set where expirationDate &lt; &#63;.
	 *
	 * @param commerceInventoryBookedQuantityId the primary key of the current commerce inventory booked quantity
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory booked quantity
	 * @throws NoSuchInventoryBookedQuantityException if a commerce inventory booked quantity with the primary key could not be found
	 */
	@Override
	public CommerceInventoryBookedQuantity[] findByLtExpirationDate_PrevAndNext(
			long commerceInventoryBookedQuantityId, Date expirationDate,
			OrderByComparator<CommerceInventoryBookedQuantity>
				orderByComparator)
		throws NoSuchInventoryBookedQuantityException {

		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity =
			findByPrimaryKey(commerceInventoryBookedQuantityId);

		Session session = null;

		try {
			session = openSession();

			CommerceInventoryBookedQuantity[] array =
				new CommerceInventoryBookedQuantityImpl[3];

			array[0] = getByLtExpirationDate_PrevAndNext(
				session, commerceInventoryBookedQuantity, expirationDate,
				orderByComparator, true);

			array[1] = commerceInventoryBookedQuantity;

			array[2] = getByLtExpirationDate_PrevAndNext(
				session, commerceInventoryBookedQuantity, expirationDate,
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

	protected CommerceInventoryBookedQuantity getByLtExpirationDate_PrevAndNext(
		Session session,
		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity,
		Date expirationDate,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEINVENTORYBOOKEDQUANTITY_WHERE);

		boolean bindExpirationDate = false;

		if (expirationDate == null) {
			query.append(_FINDER_COLUMN_LTEXPIRATIONDATE_EXPIRATIONDATE_1);
		}
		else {
			bindExpirationDate = true;

			query.append(_FINDER_COLUMN_LTEXPIRATIONDATE_EXPIRATIONDATE_2);
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
			query.append(
				CommerceInventoryBookedQuantityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindExpirationDate) {
			qPos.add(new Timestamp(expirationDate.getTime()));
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceInventoryBookedQuantity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceInventoryBookedQuantity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce inventory booked quantities where expirationDate &lt; &#63; from the database.
	 *
	 * @param expirationDate the expiration date
	 */
	@Override
	public void removeByLtExpirationDate(Date expirationDate) {
		for (CommerceInventoryBookedQuantity commerceInventoryBookedQuantity :
				findByLtExpirationDate(
					expirationDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceInventoryBookedQuantity);
		}
	}

	/**
	 * Returns the number of commerce inventory booked quantities where expirationDate &lt; &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @return the number of matching commerce inventory booked quantities
	 */
	@Override
	public int countByLtExpirationDate(Date expirationDate) {
		FinderPath finderPath =
			_finderPathWithPaginationCountByLtExpirationDate;

		Object[] finderArgs = new Object[] {_getTime(expirationDate)};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEINVENTORYBOOKEDQUANTITY_WHERE);

			boolean bindExpirationDate = false;

			if (expirationDate == null) {
				query.append(_FINDER_COLUMN_LTEXPIRATIONDATE_EXPIRATIONDATE_1);
			}
			else {
				bindExpirationDate = true;

				query.append(_FINDER_COLUMN_LTEXPIRATIONDATE_EXPIRATIONDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindExpirationDate) {
					qPos.add(new Timestamp(expirationDate.getTime()));
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

	private static final String
		_FINDER_COLUMN_LTEXPIRATIONDATE_EXPIRATIONDATE_1 =
			"commerceInventoryBookedQuantity.expirationDate IS NULL";

	private static final String
		_FINDER_COLUMN_LTEXPIRATIONDATE_EXPIRATIONDATE_2 =
			"commerceInventoryBookedQuantity.expirationDate < ?";

	public CommerceInventoryBookedQuantityPersistenceImpl() {
		setModelClass(CommerceInventoryBookedQuantity.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put(
			"commerceInventoryBookedQuantityId", "CIBookedQuantityId");

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
	 * Caches the commerce inventory booked quantity in the entity cache if it is enabled.
	 *
	 * @param commerceInventoryBookedQuantity the commerce inventory booked quantity
	 */
	@Override
	public void cacheResult(
		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity) {

		entityCache.putResult(
			CommerceInventoryBookedQuantityModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryBookedQuantityImpl.class,
			commerceInventoryBookedQuantity.getPrimaryKey(),
			commerceInventoryBookedQuantity);

		commerceInventoryBookedQuantity.resetOriginalValues();
	}

	/**
	 * Caches the commerce inventory booked quantities in the entity cache if it is enabled.
	 *
	 * @param commerceInventoryBookedQuantities the commerce inventory booked quantities
	 */
	@Override
	public void cacheResult(
		List<CommerceInventoryBookedQuantity>
			commerceInventoryBookedQuantities) {

		for (CommerceInventoryBookedQuantity commerceInventoryBookedQuantity :
				commerceInventoryBookedQuantities) {

			if (entityCache.getResult(
					CommerceInventoryBookedQuantityModelImpl.
						ENTITY_CACHE_ENABLED,
					CommerceInventoryBookedQuantityImpl.class,
					commerceInventoryBookedQuantity.getPrimaryKey()) == null) {

				cacheResult(commerceInventoryBookedQuantity);
			}
			else {
				commerceInventoryBookedQuantity.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce inventory booked quantities.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceInventoryBookedQuantityImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce inventory booked quantity.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity) {

		entityCache.removeResult(
			CommerceInventoryBookedQuantityModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryBookedQuantityImpl.class,
			commerceInventoryBookedQuantity.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<CommerceInventoryBookedQuantity>
			commerceInventoryBookedQuantities) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceInventoryBookedQuantity commerceInventoryBookedQuantity :
				commerceInventoryBookedQuantities) {

			entityCache.removeResult(
				CommerceInventoryBookedQuantityModelImpl.ENTITY_CACHE_ENABLED,
				CommerceInventoryBookedQuantityImpl.class,
				commerceInventoryBookedQuantity.getPrimaryKey());
		}
	}

	/**
	 * Creates a new commerce inventory booked quantity with the primary key. Does not add the commerce inventory booked quantity to the database.
	 *
	 * @param commerceInventoryBookedQuantityId the primary key for the new commerce inventory booked quantity
	 * @return the new commerce inventory booked quantity
	 */
	@Override
	public CommerceInventoryBookedQuantity create(
		long commerceInventoryBookedQuantityId) {

		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity =
			new CommerceInventoryBookedQuantityImpl();

		commerceInventoryBookedQuantity.setNew(true);
		commerceInventoryBookedQuantity.setPrimaryKey(
			commerceInventoryBookedQuantityId);

		commerceInventoryBookedQuantity.setCompanyId(
			CompanyThreadLocal.getCompanyId());

		return commerceInventoryBookedQuantity;
	}

	/**
	 * Removes the commerce inventory booked quantity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceInventoryBookedQuantityId the primary key of the commerce inventory booked quantity
	 * @return the commerce inventory booked quantity that was removed
	 * @throws NoSuchInventoryBookedQuantityException if a commerce inventory booked quantity with the primary key could not be found
	 */
	@Override
	public CommerceInventoryBookedQuantity remove(
			long commerceInventoryBookedQuantityId)
		throws NoSuchInventoryBookedQuantityException {

		return remove((Serializable)commerceInventoryBookedQuantityId);
	}

	/**
	 * Removes the commerce inventory booked quantity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce inventory booked quantity
	 * @return the commerce inventory booked quantity that was removed
	 * @throws NoSuchInventoryBookedQuantityException if a commerce inventory booked quantity with the primary key could not be found
	 */
	@Override
	public CommerceInventoryBookedQuantity remove(Serializable primaryKey)
		throws NoSuchInventoryBookedQuantityException {

		Session session = null;

		try {
			session = openSession();

			CommerceInventoryBookedQuantity commerceInventoryBookedQuantity =
				(CommerceInventoryBookedQuantity)session.get(
					CommerceInventoryBookedQuantityImpl.class, primaryKey);

			if (commerceInventoryBookedQuantity == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchInventoryBookedQuantityException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commerceInventoryBookedQuantity);
		}
		catch (NoSuchInventoryBookedQuantityException nsee) {
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
	protected CommerceInventoryBookedQuantity removeImpl(
		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceInventoryBookedQuantity)) {
				commerceInventoryBookedQuantity =
					(CommerceInventoryBookedQuantity)session.get(
						CommerceInventoryBookedQuantityImpl.class,
						commerceInventoryBookedQuantity.getPrimaryKeyObj());
			}

			if (commerceInventoryBookedQuantity != null) {
				session.delete(commerceInventoryBookedQuantity);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceInventoryBookedQuantity != null) {
			clearCache(commerceInventoryBookedQuantity);
		}

		return commerceInventoryBookedQuantity;
	}

	@Override
	public CommerceInventoryBookedQuantity updateImpl(
		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity) {

		boolean isNew = commerceInventoryBookedQuantity.isNew();

		if (!(commerceInventoryBookedQuantity instanceof
				CommerceInventoryBookedQuantityModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(
					commerceInventoryBookedQuantity.getClass())) {

				invocationHandler = ProxyUtil.getInvocationHandler(
					commerceInventoryBookedQuantity);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceInventoryBookedQuantity proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceInventoryBookedQuantity implementation " +
					commerceInventoryBookedQuantity.getClass());
		}

		CommerceInventoryBookedQuantityModelImpl
			commerceInventoryBookedQuantityModelImpl =
				(CommerceInventoryBookedQuantityModelImpl)
					commerceInventoryBookedQuantity;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew &&
			(commerceInventoryBookedQuantity.getCreateDate() == null)) {

			if (serviceContext == null) {
				commerceInventoryBookedQuantity.setCreateDate(now);
			}
			else {
				commerceInventoryBookedQuantity.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!commerceInventoryBookedQuantityModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceInventoryBookedQuantity.setModifiedDate(now);
			}
			else {
				commerceInventoryBookedQuantity.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceInventoryBookedQuantity.isNew()) {
				session.save(commerceInventoryBookedQuantity);

				commerceInventoryBookedQuantity.setNew(false);
			}
			else {
				commerceInventoryBookedQuantity =
					(CommerceInventoryBookedQuantity)session.merge(
						commerceInventoryBookedQuantity);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceInventoryBookedQuantityModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				commerceInventoryBookedQuantityModelImpl.getSku()
			};

			finderCache.removeResult(_finderPathCountBySku, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindBySku, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((commerceInventoryBookedQuantityModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindBySku.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceInventoryBookedQuantityModelImpl.getOriginalSku()
				};

				finderCache.removeResult(_finderPathCountBySku, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindBySku, args);

				args = new Object[] {
					commerceInventoryBookedQuantityModelImpl.getSku()
				};

				finderCache.removeResult(_finderPathCountBySku, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindBySku, args);
			}
		}

		entityCache.putResult(
			CommerceInventoryBookedQuantityModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryBookedQuantityImpl.class,
			commerceInventoryBookedQuantity.getPrimaryKey(),
			commerceInventoryBookedQuantity, false);

		commerceInventoryBookedQuantity.resetOriginalValues();

		return commerceInventoryBookedQuantity;
	}

	/**
	 * Returns the commerce inventory booked quantity with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce inventory booked quantity
	 * @return the commerce inventory booked quantity
	 * @throws NoSuchInventoryBookedQuantityException if a commerce inventory booked quantity with the primary key could not be found
	 */
	@Override
	public CommerceInventoryBookedQuantity findByPrimaryKey(
			Serializable primaryKey)
		throws NoSuchInventoryBookedQuantityException {

		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity =
			fetchByPrimaryKey(primaryKey);

		if (commerceInventoryBookedQuantity == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchInventoryBookedQuantityException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commerceInventoryBookedQuantity;
	}

	/**
	 * Returns the commerce inventory booked quantity with the primary key or throws a <code>NoSuchInventoryBookedQuantityException</code> if it could not be found.
	 *
	 * @param commerceInventoryBookedQuantityId the primary key of the commerce inventory booked quantity
	 * @return the commerce inventory booked quantity
	 * @throws NoSuchInventoryBookedQuantityException if a commerce inventory booked quantity with the primary key could not be found
	 */
	@Override
	public CommerceInventoryBookedQuantity findByPrimaryKey(
			long commerceInventoryBookedQuantityId)
		throws NoSuchInventoryBookedQuantityException {

		return findByPrimaryKey(
			(Serializable)commerceInventoryBookedQuantityId);
	}

	/**
	 * Returns the commerce inventory booked quantity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce inventory booked quantity
	 * @return the commerce inventory booked quantity, or <code>null</code> if a commerce inventory booked quantity with the primary key could not be found
	 */
	@Override
	public CommerceInventoryBookedQuantity fetchByPrimaryKey(
		Serializable primaryKey) {

		Serializable serializable = entityCache.getResult(
			CommerceInventoryBookedQuantityModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryBookedQuantityImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity =
			(CommerceInventoryBookedQuantity)serializable;

		if (commerceInventoryBookedQuantity == null) {
			Session session = null;

			try {
				session = openSession();

				commerceInventoryBookedQuantity =
					(CommerceInventoryBookedQuantity)session.get(
						CommerceInventoryBookedQuantityImpl.class, primaryKey);

				if (commerceInventoryBookedQuantity != null) {
					cacheResult(commerceInventoryBookedQuantity);
				}
				else {
					entityCache.putResult(
						CommerceInventoryBookedQuantityModelImpl.
							ENTITY_CACHE_ENABLED,
						CommerceInventoryBookedQuantityImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CommerceInventoryBookedQuantityModelImpl.
						ENTITY_CACHE_ENABLED,
					CommerceInventoryBookedQuantityImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceInventoryBookedQuantity;
	}

	/**
	 * Returns the commerce inventory booked quantity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceInventoryBookedQuantityId the primary key of the commerce inventory booked quantity
	 * @return the commerce inventory booked quantity, or <code>null</code> if a commerce inventory booked quantity with the primary key could not be found
	 */
	@Override
	public CommerceInventoryBookedQuantity fetchByPrimaryKey(
		long commerceInventoryBookedQuantityId) {

		return fetchByPrimaryKey(
			(Serializable)commerceInventoryBookedQuantityId);
	}

	@Override
	public Map<Serializable, CommerceInventoryBookedQuantity>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceInventoryBookedQuantity> map =
			new HashMap<Serializable, CommerceInventoryBookedQuantity>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceInventoryBookedQuantity commerceInventoryBookedQuantity =
				fetchByPrimaryKey(primaryKey);

			if (commerceInventoryBookedQuantity != null) {
				map.put(primaryKey, commerceInventoryBookedQuantity);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CommerceInventoryBookedQuantityModelImpl.ENTITY_CACHE_ENABLED,
				CommerceInventoryBookedQuantityImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(
						primaryKey,
						(CommerceInventoryBookedQuantity)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_COMMERCEINVENTORYBOOKEDQUANTITY_WHERE_PKS_IN);

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

			for (CommerceInventoryBookedQuantity
					commerceInventoryBookedQuantity :
						(List<CommerceInventoryBookedQuantity>)q.list()) {

				map.put(
					commerceInventoryBookedQuantity.getPrimaryKeyObj(),
					commerceInventoryBookedQuantity);

				cacheResult(commerceInventoryBookedQuantity);

				uncachedPrimaryKeys.remove(
					commerceInventoryBookedQuantity.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CommerceInventoryBookedQuantityModelImpl.
						ENTITY_CACHE_ENABLED,
					CommerceInventoryBookedQuantityImpl.class, primaryKey,
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
	 * Returns all the commerce inventory booked quantities.
	 *
	 * @return the commerce inventory booked quantities
	 */
	@Override
	public List<CommerceInventoryBookedQuantity> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory booked quantities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryBookedQuantityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory booked quantities
	 * @param end the upper bound of the range of commerce inventory booked quantities (not inclusive)
	 * @return the range of commerce inventory booked quantities
	 */
	@Override
	public List<CommerceInventoryBookedQuantity> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory booked quantities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryBookedQuantityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory booked quantities
	 * @param end the upper bound of the range of commerce inventory booked quantities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce inventory booked quantities
	 */
	@Override
	public List<CommerceInventoryBookedQuantity> findAll(
		int start, int end,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory booked quantities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryBookedQuantityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory booked quantities
	 * @param end the upper bound of the range of commerce inventory booked quantities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce inventory booked quantities
	 */
	@Override
	public List<CommerceInventoryBookedQuantity> findAll(
		int start, int end,
		OrderByComparator<CommerceInventoryBookedQuantity> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<CommerceInventoryBookedQuantity> list = null;

		if (useFinderCache) {
			list = (List<CommerceInventoryBookedQuantity>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEINVENTORYBOOKEDQUANTITY);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEINVENTORYBOOKEDQUANTITY;

				if (pagination) {
					sql = sql.concat(
						CommerceInventoryBookedQuantityModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list =
						(List<CommerceInventoryBookedQuantity>)QueryUtil.list(
							q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list =
						(List<CommerceInventoryBookedQuantity>)QueryUtil.list(
							q, getDialect(), start, end);
				}

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the commerce inventory booked quantities from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceInventoryBookedQuantity commerceInventoryBookedQuantity :
				findAll()) {

			remove(commerceInventoryBookedQuantity);
		}
	}

	/**
	 * Returns the number of commerce inventory booked quantities.
	 *
	 * @return the number of commerce inventory booked quantities
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
					_SQL_COUNT_COMMERCEINVENTORYBOOKEDQUANTITY);

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
		return CommerceInventoryBookedQuantityModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce inventory booked quantity persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CommerceInventoryBookedQuantityModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryBookedQuantityModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryBookedQuantityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CommerceInventoryBookedQuantityModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryBookedQuantityModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryBookedQuantityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CommerceInventoryBookedQuantityModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryBookedQuantityModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindBySku = new FinderPath(
			CommerceInventoryBookedQuantityModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryBookedQuantityModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryBookedQuantityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySku",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindBySku = new FinderPath(
			CommerceInventoryBookedQuantityModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryBookedQuantityModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryBookedQuantityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySku",
			new String[] {String.class.getName()},
			CommerceInventoryBookedQuantityModelImpl.SKU_COLUMN_BITMASK);

		_finderPathCountBySku = new FinderPath(
			CommerceInventoryBookedQuantityModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryBookedQuantityModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySku",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByLtExpirationDate = new FinderPath(
			CommerceInventoryBookedQuantityModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryBookedQuantityModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryBookedQuantityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLtExpirationDate",
			new String[] {
				Date.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByLtExpirationDate = new FinderPath(
			CommerceInventoryBookedQuantityModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryBookedQuantityModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByLtExpirationDate", new String[] {Date.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(
			CommerceInventoryBookedQuantityImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

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

	private static final String _SQL_SELECT_COMMERCEINVENTORYBOOKEDQUANTITY =
		"SELECT commerceInventoryBookedQuantity FROM CommerceInventoryBookedQuantity commerceInventoryBookedQuantity";

	private static final String
		_SQL_SELECT_COMMERCEINVENTORYBOOKEDQUANTITY_WHERE_PKS_IN =
			"SELECT commerceInventoryBookedQuantity FROM CommerceInventoryBookedQuantity commerceInventoryBookedQuantity WHERE CIBookedQuantityId IN (";

	private static final String
		_SQL_SELECT_COMMERCEINVENTORYBOOKEDQUANTITY_WHERE =
			"SELECT commerceInventoryBookedQuantity FROM CommerceInventoryBookedQuantity commerceInventoryBookedQuantity WHERE ";

	private static final String _SQL_COUNT_COMMERCEINVENTORYBOOKEDQUANTITY =
		"SELECT COUNT(commerceInventoryBookedQuantity) FROM CommerceInventoryBookedQuantity commerceInventoryBookedQuantity";

	private static final String
		_SQL_COUNT_COMMERCEINVENTORYBOOKEDQUANTITY_WHERE =
			"SELECT COUNT(commerceInventoryBookedQuantity) FROM CommerceInventoryBookedQuantity commerceInventoryBookedQuantity WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"commerceInventoryBookedQuantity.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommerceInventoryBookedQuantity exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommerceInventoryBookedQuantity exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceInventoryBookedQuantityPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"commerceInventoryBookedQuantityId"});

}