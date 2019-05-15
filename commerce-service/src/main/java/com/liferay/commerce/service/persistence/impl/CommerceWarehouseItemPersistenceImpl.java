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

import com.liferay.commerce.exception.NoSuchWarehouseItemException;
import com.liferay.commerce.model.CommerceWarehouseItem;
import com.liferay.commerce.model.impl.CommerceWarehouseItemImpl;
import com.liferay.commerce.model.impl.CommerceWarehouseItemModelImpl;
import com.liferay.commerce.service.persistence.CommerceWarehouseItemPersistence;
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
import com.liferay.portal.kernel.util.StringBundler;
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
 * The persistence implementation for the commerce warehouse item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
@ProviderType
public class CommerceWarehouseItemPersistenceImpl
	extends BasePersistenceImpl<CommerceWarehouseItem>
	implements CommerceWarehouseItemPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommerceWarehouseItemUtil</code> to access the commerce warehouse item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommerceWarehouseItemImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByCommerceWarehouseId;
	private FinderPath _finderPathWithoutPaginationFindByCommerceWarehouseId;
	private FinderPath _finderPathCountByCommerceWarehouseId;

	/**
	 * Returns all the commerce warehouse items where commerceWarehouseId = &#63;.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @return the matching commerce warehouse items
	 */
	@Override
	public List<CommerceWarehouseItem> findByCommerceWarehouseId(
		long commerceWarehouseId) {

		return findByCommerceWarehouseId(
			commerceWarehouseId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce warehouse items where commerceWarehouseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param start the lower bound of the range of commerce warehouse items
	 * @param end the upper bound of the range of commerce warehouse items (not inclusive)
	 * @return the range of matching commerce warehouse items
	 */
	@Override
	public List<CommerceWarehouseItem> findByCommerceWarehouseId(
		long commerceWarehouseId, int start, int end) {

		return findByCommerceWarehouseId(commerceWarehouseId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce warehouse items where commerceWarehouseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param start the lower bound of the range of commerce warehouse items
	 * @param end the upper bound of the range of commerce warehouse items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce warehouse items
	 */
	@Override
	public List<CommerceWarehouseItem> findByCommerceWarehouseId(
		long commerceWarehouseId, int start, int end,
		OrderByComparator<CommerceWarehouseItem> orderByComparator) {

		return findByCommerceWarehouseId(
			commerceWarehouseId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce warehouse items where commerceWarehouseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param start the lower bound of the range of commerce warehouse items
	 * @param end the upper bound of the range of commerce warehouse items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce warehouse items
	 */
	@Override
	public List<CommerceWarehouseItem> findByCommerceWarehouseId(
		long commerceWarehouseId, int start, int end,
		OrderByComparator<CommerceWarehouseItem> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByCommerceWarehouseId;
			finderArgs = new Object[] {commerceWarehouseId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByCommerceWarehouseId;
			finderArgs = new Object[] {
				commerceWarehouseId, start, end, orderByComparator
			};
		}

		List<CommerceWarehouseItem> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceWarehouseItem>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceWarehouseItem commerceWarehouseItem : list) {
					if ((commerceWarehouseId !=
							commerceWarehouseItem.getCommerceWarehouseId())) {

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

			query.append(_SQL_SELECT_COMMERCEWAREHOUSEITEM_WHERE);

			query.append(
				_FINDER_COLUMN_COMMERCEWAREHOUSEID_COMMERCEWAREHOUSEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceWarehouseItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceWarehouseId);

				if (!pagination) {
					list = (List<CommerceWarehouseItem>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceWarehouseItem>)QueryUtil.list(
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
	 * Returns the first commerce warehouse item in the ordered set where commerceWarehouseId = &#63;.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce warehouse item
	 * @throws NoSuchWarehouseItemException if a matching commerce warehouse item could not be found
	 */
	@Override
	public CommerceWarehouseItem findByCommerceWarehouseId_First(
			long commerceWarehouseId,
			OrderByComparator<CommerceWarehouseItem> orderByComparator)
		throws NoSuchWarehouseItemException {

		CommerceWarehouseItem commerceWarehouseItem =
			fetchByCommerceWarehouseId_First(
				commerceWarehouseId, orderByComparator);

		if (commerceWarehouseItem != null) {
			return commerceWarehouseItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceWarehouseId=");
		msg.append(commerceWarehouseId);

		msg.append("}");

		throw new NoSuchWarehouseItemException(msg.toString());
	}

	/**
	 * Returns the first commerce warehouse item in the ordered set where commerceWarehouseId = &#63;.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce warehouse item, or <code>null</code> if a matching commerce warehouse item could not be found
	 */
	@Override
	public CommerceWarehouseItem fetchByCommerceWarehouseId_First(
		long commerceWarehouseId,
		OrderByComparator<CommerceWarehouseItem> orderByComparator) {

		List<CommerceWarehouseItem> list = findByCommerceWarehouseId(
			commerceWarehouseId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce warehouse item in the ordered set where commerceWarehouseId = &#63;.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce warehouse item
	 * @throws NoSuchWarehouseItemException if a matching commerce warehouse item could not be found
	 */
	@Override
	public CommerceWarehouseItem findByCommerceWarehouseId_Last(
			long commerceWarehouseId,
			OrderByComparator<CommerceWarehouseItem> orderByComparator)
		throws NoSuchWarehouseItemException {

		CommerceWarehouseItem commerceWarehouseItem =
			fetchByCommerceWarehouseId_Last(
				commerceWarehouseId, orderByComparator);

		if (commerceWarehouseItem != null) {
			return commerceWarehouseItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceWarehouseId=");
		msg.append(commerceWarehouseId);

		msg.append("}");

		throw new NoSuchWarehouseItemException(msg.toString());
	}

	/**
	 * Returns the last commerce warehouse item in the ordered set where commerceWarehouseId = &#63;.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce warehouse item, or <code>null</code> if a matching commerce warehouse item could not be found
	 */
	@Override
	public CommerceWarehouseItem fetchByCommerceWarehouseId_Last(
		long commerceWarehouseId,
		OrderByComparator<CommerceWarehouseItem> orderByComparator) {

		int count = countByCommerceWarehouseId(commerceWarehouseId);

		if (count == 0) {
			return null;
		}

		List<CommerceWarehouseItem> list = findByCommerceWarehouseId(
			commerceWarehouseId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce warehouse items before and after the current commerce warehouse item in the ordered set where commerceWarehouseId = &#63;.
	 *
	 * @param commerceWarehouseItemId the primary key of the current commerce warehouse item
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce warehouse item
	 * @throws NoSuchWarehouseItemException if a commerce warehouse item with the primary key could not be found
	 */
	@Override
	public CommerceWarehouseItem[] findByCommerceWarehouseId_PrevAndNext(
			long commerceWarehouseItemId, long commerceWarehouseId,
			OrderByComparator<CommerceWarehouseItem> orderByComparator)
		throws NoSuchWarehouseItemException {

		CommerceWarehouseItem commerceWarehouseItem = findByPrimaryKey(
			commerceWarehouseItemId);

		Session session = null;

		try {
			session = openSession();

			CommerceWarehouseItem[] array = new CommerceWarehouseItemImpl[3];

			array[0] = getByCommerceWarehouseId_PrevAndNext(
				session, commerceWarehouseItem, commerceWarehouseId,
				orderByComparator, true);

			array[1] = commerceWarehouseItem;

			array[2] = getByCommerceWarehouseId_PrevAndNext(
				session, commerceWarehouseItem, commerceWarehouseId,
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

	protected CommerceWarehouseItem getByCommerceWarehouseId_PrevAndNext(
		Session session, CommerceWarehouseItem commerceWarehouseItem,
		long commerceWarehouseId,
		OrderByComparator<CommerceWarehouseItem> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEWAREHOUSEITEM_WHERE);

		query.append(_FINDER_COLUMN_COMMERCEWAREHOUSEID_COMMERCEWAREHOUSEID_2);

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
			query.append(CommerceWarehouseItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceWarehouseId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceWarehouseItem)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceWarehouseItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce warehouse items where commerceWarehouseId = &#63; from the database.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 */
	@Override
	public void removeByCommerceWarehouseId(long commerceWarehouseId) {
		for (CommerceWarehouseItem commerceWarehouseItem :
				findByCommerceWarehouseId(
					commerceWarehouseId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceWarehouseItem);
		}
	}

	/**
	 * Returns the number of commerce warehouse items where commerceWarehouseId = &#63;.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @return the number of matching commerce warehouse items
	 */
	@Override
	public int countByCommerceWarehouseId(long commerceWarehouseId) {
		FinderPath finderPath = _finderPathCountByCommerceWarehouseId;

		Object[] finderArgs = new Object[] {commerceWarehouseId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEWAREHOUSEITEM_WHERE);

			query.append(
				_FINDER_COLUMN_COMMERCEWAREHOUSEID_COMMERCEWAREHOUSEID_2);

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

	private static final String
		_FINDER_COLUMN_COMMERCEWAREHOUSEID_COMMERCEWAREHOUSEID_2 =
			"commerceWarehouseItem.commerceWarehouseId = ?";

	private FinderPath _finderPathFetchByCWI_CPIU;
	private FinderPath _finderPathCountByCWI_CPIU;

	/**
	 * Returns the commerce warehouse item where commerceWarehouseId = &#63; and CPInstanceUuid = &#63; or throws a <code>NoSuchWarehouseItemException</code> if it could not be found.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @return the matching commerce warehouse item
	 * @throws NoSuchWarehouseItemException if a matching commerce warehouse item could not be found
	 */
	@Override
	public CommerceWarehouseItem findByCWI_CPIU(
			long commerceWarehouseId, String CPInstanceUuid)
		throws NoSuchWarehouseItemException {

		CommerceWarehouseItem commerceWarehouseItem = fetchByCWI_CPIU(
			commerceWarehouseId, CPInstanceUuid);

		if (commerceWarehouseItem == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("commerceWarehouseId=");
			msg.append(commerceWarehouseId);

			msg.append(", CPInstanceUuid=");
			msg.append(CPInstanceUuid);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchWarehouseItemException(msg.toString());
		}

		return commerceWarehouseItem;
	}

	/**
	 * Returns the commerce warehouse item where commerceWarehouseId = &#63; and CPInstanceUuid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @return the matching commerce warehouse item, or <code>null</code> if a matching commerce warehouse item could not be found
	 */
	@Override
	public CommerceWarehouseItem fetchByCWI_CPIU(
		long commerceWarehouseId, String CPInstanceUuid) {

		return fetchByCWI_CPIU(commerceWarehouseId, CPInstanceUuid, true);
	}

	/**
	 * Returns the commerce warehouse item where commerceWarehouseId = &#63; and CPInstanceUuid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching commerce warehouse item, or <code>null</code> if a matching commerce warehouse item could not be found
	 */
	@Override
	public CommerceWarehouseItem fetchByCWI_CPIU(
		long commerceWarehouseId, String CPInstanceUuid,
		boolean retrieveFromCache) {

		CPInstanceUuid = Objects.toString(CPInstanceUuid, "");

		Object[] finderArgs = new Object[] {
			commerceWarehouseId, CPInstanceUuid
		};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByCWI_CPIU, finderArgs, this);
		}

		if (result instanceof CommerceWarehouseItem) {
			CommerceWarehouseItem commerceWarehouseItem =
				(CommerceWarehouseItem)result;

			if ((commerceWarehouseId !=
					commerceWarehouseItem.getCommerceWarehouseId()) ||
				!Objects.equals(
					CPInstanceUuid,
					commerceWarehouseItem.getCPInstanceUuid())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COMMERCEWAREHOUSEITEM_WHERE);

			query.append(_FINDER_COLUMN_CWI_CPIU_COMMERCEWAREHOUSEID_2);

			boolean bindCPInstanceUuid = false;

			if (CPInstanceUuid.isEmpty()) {
				query.append(_FINDER_COLUMN_CWI_CPIU_CPINSTANCEUUID_3);
			}
			else {
				bindCPInstanceUuid = true;

				query.append(_FINDER_COLUMN_CWI_CPIU_CPINSTANCEUUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceWarehouseId);

				if (bindCPInstanceUuid) {
					qPos.add(CPInstanceUuid);
				}

				List<CommerceWarehouseItem> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByCWI_CPIU, finderArgs, list);
				}
				else {
					CommerceWarehouseItem commerceWarehouseItem = list.get(0);

					result = commerceWarehouseItem;

					cacheResult(commerceWarehouseItem);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathFetchByCWI_CPIU, finderArgs);

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
			return (CommerceWarehouseItem)result;
		}
	}

	/**
	 * Removes the commerce warehouse item where commerceWarehouseId = &#63; and CPInstanceUuid = &#63; from the database.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @return the commerce warehouse item that was removed
	 */
	@Override
	public CommerceWarehouseItem removeByCWI_CPIU(
			long commerceWarehouseId, String CPInstanceUuid)
		throws NoSuchWarehouseItemException {

		CommerceWarehouseItem commerceWarehouseItem = findByCWI_CPIU(
			commerceWarehouseId, CPInstanceUuid);

		return remove(commerceWarehouseItem);
	}

	/**
	 * Returns the number of commerce warehouse items where commerceWarehouseId = &#63; and CPInstanceUuid = &#63;.
	 *
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @return the number of matching commerce warehouse items
	 */
	@Override
	public int countByCWI_CPIU(
		long commerceWarehouseId, String CPInstanceUuid) {

		CPInstanceUuid = Objects.toString(CPInstanceUuid, "");

		FinderPath finderPath = _finderPathCountByCWI_CPIU;

		Object[] finderArgs = new Object[] {
			commerceWarehouseId, CPInstanceUuid
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEWAREHOUSEITEM_WHERE);

			query.append(_FINDER_COLUMN_CWI_CPIU_COMMERCEWAREHOUSEID_2);

			boolean bindCPInstanceUuid = false;

			if (CPInstanceUuid.isEmpty()) {
				query.append(_FINDER_COLUMN_CWI_CPIU_CPINSTANCEUUID_3);
			}
			else {
				bindCPInstanceUuid = true;

				query.append(_FINDER_COLUMN_CWI_CPIU_CPINSTANCEUUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceWarehouseId);

				if (bindCPInstanceUuid) {
					qPos.add(CPInstanceUuid);
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

	private static final String _FINDER_COLUMN_CWI_CPIU_COMMERCEWAREHOUSEID_2 =
		"commerceWarehouseItem.commerceWarehouseId = ? AND ";

	private static final String _FINDER_COLUMN_CWI_CPIU_CPINSTANCEUUID_2 =
		"commerceWarehouseItem.CPInstanceUuid = ?";

	private static final String _FINDER_COLUMN_CWI_CPIU_CPINSTANCEUUID_3 =
		"(commerceWarehouseItem.CPInstanceUuid IS NULL OR commerceWarehouseItem.CPInstanceUuid = '')";

	private FinderPath _finderPathWithPaginationFindByCPI_CPIU;
	private FinderPath _finderPathWithoutPaginationFindByCPI_CPIU;
	private FinderPath _finderPathCountByCPI_CPIU;

	/**
	 * Returns all the commerce warehouse items where CProductId = &#63; and CPInstanceUuid = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @return the matching commerce warehouse items
	 */
	@Override
	public List<CommerceWarehouseItem> findByCPI_CPIU(
		long CProductId, String CPInstanceUuid) {

		return findByCPI_CPIU(
			CProductId, CPInstanceUuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the commerce warehouse items where CProductId = &#63; and CPInstanceUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CProductId the c product ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @param start the lower bound of the range of commerce warehouse items
	 * @param end the upper bound of the range of commerce warehouse items (not inclusive)
	 * @return the range of matching commerce warehouse items
	 */
	@Override
	public List<CommerceWarehouseItem> findByCPI_CPIU(
		long CProductId, String CPInstanceUuid, int start, int end) {

		return findByCPI_CPIU(CProductId, CPInstanceUuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce warehouse items where CProductId = &#63; and CPInstanceUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CProductId the c product ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @param start the lower bound of the range of commerce warehouse items
	 * @param end the upper bound of the range of commerce warehouse items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce warehouse items
	 */
	@Override
	public List<CommerceWarehouseItem> findByCPI_CPIU(
		long CProductId, String CPInstanceUuid, int start, int end,
		OrderByComparator<CommerceWarehouseItem> orderByComparator) {

		return findByCPI_CPIU(
			CProductId, CPInstanceUuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce warehouse items where CProductId = &#63; and CPInstanceUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CProductId the c product ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @param start the lower bound of the range of commerce warehouse items
	 * @param end the upper bound of the range of commerce warehouse items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce warehouse items
	 */
	@Override
	public List<CommerceWarehouseItem> findByCPI_CPIU(
		long CProductId, String CPInstanceUuid, int start, int end,
		OrderByComparator<CommerceWarehouseItem> orderByComparator,
		boolean retrieveFromCache) {

		CPInstanceUuid = Objects.toString(CPInstanceUuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByCPI_CPIU;
			finderArgs = new Object[] {CProductId, CPInstanceUuid};
		}
		else {
			finderPath = _finderPathWithPaginationFindByCPI_CPIU;
			finderArgs = new Object[] {
				CProductId, CPInstanceUuid, start, end, orderByComparator
			};
		}

		List<CommerceWarehouseItem> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceWarehouseItem>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceWarehouseItem commerceWarehouseItem : list) {
					if ((CProductId != commerceWarehouseItem.getCProductId()) ||
						!CPInstanceUuid.equals(
							commerceWarehouseItem.getCPInstanceUuid())) {

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

			query.append(_SQL_SELECT_COMMERCEWAREHOUSEITEM_WHERE);

			query.append(_FINDER_COLUMN_CPI_CPIU_CPRODUCTID_2);

			boolean bindCPInstanceUuid = false;

			if (CPInstanceUuid.isEmpty()) {
				query.append(_FINDER_COLUMN_CPI_CPIU_CPINSTANCEUUID_3);
			}
			else {
				bindCPInstanceUuid = true;

				query.append(_FINDER_COLUMN_CPI_CPIU_CPINSTANCEUUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceWarehouseItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CProductId);

				if (bindCPInstanceUuid) {
					qPos.add(CPInstanceUuid);
				}

				if (!pagination) {
					list = (List<CommerceWarehouseItem>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceWarehouseItem>)QueryUtil.list(
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
	 * Returns the first commerce warehouse item in the ordered set where CProductId = &#63; and CPInstanceUuid = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce warehouse item
	 * @throws NoSuchWarehouseItemException if a matching commerce warehouse item could not be found
	 */
	@Override
	public CommerceWarehouseItem findByCPI_CPIU_First(
			long CProductId, String CPInstanceUuid,
			OrderByComparator<CommerceWarehouseItem> orderByComparator)
		throws NoSuchWarehouseItemException {

		CommerceWarehouseItem commerceWarehouseItem = fetchByCPI_CPIU_First(
			CProductId, CPInstanceUuid, orderByComparator);

		if (commerceWarehouseItem != null) {
			return commerceWarehouseItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CProductId=");
		msg.append(CProductId);

		msg.append(", CPInstanceUuid=");
		msg.append(CPInstanceUuid);

		msg.append("}");

		throw new NoSuchWarehouseItemException(msg.toString());
	}

	/**
	 * Returns the first commerce warehouse item in the ordered set where CProductId = &#63; and CPInstanceUuid = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce warehouse item, or <code>null</code> if a matching commerce warehouse item could not be found
	 */
	@Override
	public CommerceWarehouseItem fetchByCPI_CPIU_First(
		long CProductId, String CPInstanceUuid,
		OrderByComparator<CommerceWarehouseItem> orderByComparator) {

		List<CommerceWarehouseItem> list = findByCPI_CPIU(
			CProductId, CPInstanceUuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce warehouse item in the ordered set where CProductId = &#63; and CPInstanceUuid = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce warehouse item
	 * @throws NoSuchWarehouseItemException if a matching commerce warehouse item could not be found
	 */
	@Override
	public CommerceWarehouseItem findByCPI_CPIU_Last(
			long CProductId, String CPInstanceUuid,
			OrderByComparator<CommerceWarehouseItem> orderByComparator)
		throws NoSuchWarehouseItemException {

		CommerceWarehouseItem commerceWarehouseItem = fetchByCPI_CPIU_Last(
			CProductId, CPInstanceUuid, orderByComparator);

		if (commerceWarehouseItem != null) {
			return commerceWarehouseItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CProductId=");
		msg.append(CProductId);

		msg.append(", CPInstanceUuid=");
		msg.append(CPInstanceUuid);

		msg.append("}");

		throw new NoSuchWarehouseItemException(msg.toString());
	}

	/**
	 * Returns the last commerce warehouse item in the ordered set where CProductId = &#63; and CPInstanceUuid = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce warehouse item, or <code>null</code> if a matching commerce warehouse item could not be found
	 */
	@Override
	public CommerceWarehouseItem fetchByCPI_CPIU_Last(
		long CProductId, String CPInstanceUuid,
		OrderByComparator<CommerceWarehouseItem> orderByComparator) {

		int count = countByCPI_CPIU(CProductId, CPInstanceUuid);

		if (count == 0) {
			return null;
		}

		List<CommerceWarehouseItem> list = findByCPI_CPIU(
			CProductId, CPInstanceUuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce warehouse items before and after the current commerce warehouse item in the ordered set where CProductId = &#63; and CPInstanceUuid = &#63;.
	 *
	 * @param commerceWarehouseItemId the primary key of the current commerce warehouse item
	 * @param CProductId the c product ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce warehouse item
	 * @throws NoSuchWarehouseItemException if a commerce warehouse item with the primary key could not be found
	 */
	@Override
	public CommerceWarehouseItem[] findByCPI_CPIU_PrevAndNext(
			long commerceWarehouseItemId, long CProductId,
			String CPInstanceUuid,
			OrderByComparator<CommerceWarehouseItem> orderByComparator)
		throws NoSuchWarehouseItemException {

		CPInstanceUuid = Objects.toString(CPInstanceUuid, "");

		CommerceWarehouseItem commerceWarehouseItem = findByPrimaryKey(
			commerceWarehouseItemId);

		Session session = null;

		try {
			session = openSession();

			CommerceWarehouseItem[] array = new CommerceWarehouseItemImpl[3];

			array[0] = getByCPI_CPIU_PrevAndNext(
				session, commerceWarehouseItem, CProductId, CPInstanceUuid,
				orderByComparator, true);

			array[1] = commerceWarehouseItem;

			array[2] = getByCPI_CPIU_PrevAndNext(
				session, commerceWarehouseItem, CProductId, CPInstanceUuid,
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

	protected CommerceWarehouseItem getByCPI_CPIU_PrevAndNext(
		Session session, CommerceWarehouseItem commerceWarehouseItem,
		long CProductId, String CPInstanceUuid,
		OrderByComparator<CommerceWarehouseItem> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEWAREHOUSEITEM_WHERE);

		query.append(_FINDER_COLUMN_CPI_CPIU_CPRODUCTID_2);

		boolean bindCPInstanceUuid = false;

		if (CPInstanceUuid.isEmpty()) {
			query.append(_FINDER_COLUMN_CPI_CPIU_CPINSTANCEUUID_3);
		}
		else {
			bindCPInstanceUuid = true;

			query.append(_FINDER_COLUMN_CPI_CPIU_CPINSTANCEUUID_2);
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
			query.append(CommerceWarehouseItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(CProductId);

		if (bindCPInstanceUuid) {
			qPos.add(CPInstanceUuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceWarehouseItem)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceWarehouseItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce warehouse items where CProductId = &#63; and CPInstanceUuid = &#63; from the database.
	 *
	 * @param CProductId the c product ID
	 * @param CPInstanceUuid the cp instance uuid
	 */
	@Override
	public void removeByCPI_CPIU(long CProductId, String CPInstanceUuid) {
		for (CommerceWarehouseItem commerceWarehouseItem :
				findByCPI_CPIU(
					CProductId, CPInstanceUuid, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(commerceWarehouseItem);
		}
	}

	/**
	 * Returns the number of commerce warehouse items where CProductId = &#63; and CPInstanceUuid = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @param CPInstanceUuid the cp instance uuid
	 * @return the number of matching commerce warehouse items
	 */
	@Override
	public int countByCPI_CPIU(long CProductId, String CPInstanceUuid) {
		CPInstanceUuid = Objects.toString(CPInstanceUuid, "");

		FinderPath finderPath = _finderPathCountByCPI_CPIU;

		Object[] finderArgs = new Object[] {CProductId, CPInstanceUuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEWAREHOUSEITEM_WHERE);

			query.append(_FINDER_COLUMN_CPI_CPIU_CPRODUCTID_2);

			boolean bindCPInstanceUuid = false;

			if (CPInstanceUuid.isEmpty()) {
				query.append(_FINDER_COLUMN_CPI_CPIU_CPINSTANCEUUID_3);
			}
			else {
				bindCPInstanceUuid = true;

				query.append(_FINDER_COLUMN_CPI_CPIU_CPINSTANCEUUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CProductId);

				if (bindCPInstanceUuid) {
					qPos.add(CPInstanceUuid);
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

	private static final String _FINDER_COLUMN_CPI_CPIU_CPRODUCTID_2 =
		"commerceWarehouseItem.CProductId = ? AND ";

	private static final String _FINDER_COLUMN_CPI_CPIU_CPINSTANCEUUID_2 =
		"commerceWarehouseItem.CPInstanceUuid = ?";

	private static final String _FINDER_COLUMN_CPI_CPIU_CPINSTANCEUUID_3 =
		"(commerceWarehouseItem.CPInstanceUuid IS NULL OR commerceWarehouseItem.CPInstanceUuid = '')";

	public CommerceWarehouseItemPersistenceImpl() {
		setModelClass(CommerceWarehouseItem.class);
	}

	/**
	 * Caches the commerce warehouse item in the entity cache if it is enabled.
	 *
	 * @param commerceWarehouseItem the commerce warehouse item
	 */
	@Override
	public void cacheResult(CommerceWarehouseItem commerceWarehouseItem) {
		entityCache.putResult(
			CommerceWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceWarehouseItemImpl.class,
			commerceWarehouseItem.getPrimaryKey(), commerceWarehouseItem);

		finderCache.putResult(
			_finderPathFetchByCWI_CPIU,
			new Object[] {
				commerceWarehouseItem.getCommerceWarehouseId(),
				commerceWarehouseItem.getCPInstanceUuid()
			},
			commerceWarehouseItem);

		commerceWarehouseItem.resetOriginalValues();
	}

	/**
	 * Caches the commerce warehouse items in the entity cache if it is enabled.
	 *
	 * @param commerceWarehouseItems the commerce warehouse items
	 */
	@Override
	public void cacheResult(
		List<CommerceWarehouseItem> commerceWarehouseItems) {

		for (CommerceWarehouseItem commerceWarehouseItem :
				commerceWarehouseItems) {

			if (entityCache.getResult(
					CommerceWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
					CommerceWarehouseItemImpl.class,
					commerceWarehouseItem.getPrimaryKey()) == null) {

				cacheResult(commerceWarehouseItem);
			}
			else {
				commerceWarehouseItem.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce warehouse items.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceWarehouseItemImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce warehouse item.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceWarehouseItem commerceWarehouseItem) {
		entityCache.removeResult(
			CommerceWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceWarehouseItemImpl.class,
			commerceWarehouseItem.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(CommerceWarehouseItemModelImpl)commerceWarehouseItem, true);
	}

	@Override
	public void clearCache(List<CommerceWarehouseItem> commerceWarehouseItems) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceWarehouseItem commerceWarehouseItem :
				commerceWarehouseItems) {

			entityCache.removeResult(
				CommerceWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
				CommerceWarehouseItemImpl.class,
				commerceWarehouseItem.getPrimaryKey());

			clearUniqueFindersCache(
				(CommerceWarehouseItemModelImpl)commerceWarehouseItem, true);
		}
	}

	protected void cacheUniqueFindersCache(
		CommerceWarehouseItemModelImpl commerceWarehouseItemModelImpl) {

		Object[] args = new Object[] {
			commerceWarehouseItemModelImpl.getCommerceWarehouseId(),
			commerceWarehouseItemModelImpl.getCPInstanceUuid()
		};

		finderCache.putResult(
			_finderPathCountByCWI_CPIU, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByCWI_CPIU, args, commerceWarehouseItemModelImpl,
			false);
	}

	protected void clearUniqueFindersCache(
		CommerceWarehouseItemModelImpl commerceWarehouseItemModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				commerceWarehouseItemModelImpl.getCommerceWarehouseId(),
				commerceWarehouseItemModelImpl.getCPInstanceUuid()
			};

			finderCache.removeResult(_finderPathCountByCWI_CPIU, args);
			finderCache.removeResult(_finderPathFetchByCWI_CPIU, args);
		}

		if ((commerceWarehouseItemModelImpl.getColumnBitmask() &
			 _finderPathFetchByCWI_CPIU.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				commerceWarehouseItemModelImpl.getOriginalCommerceWarehouseId(),
				commerceWarehouseItemModelImpl.getOriginalCPInstanceUuid()
			};

			finderCache.removeResult(_finderPathCountByCWI_CPIU, args);
			finderCache.removeResult(_finderPathFetchByCWI_CPIU, args);
		}
	}

	/**
	 * Creates a new commerce warehouse item with the primary key. Does not add the commerce warehouse item to the database.
	 *
	 * @param commerceWarehouseItemId the primary key for the new commerce warehouse item
	 * @return the new commerce warehouse item
	 */
	@Override
	public CommerceWarehouseItem create(long commerceWarehouseItemId) {
		CommerceWarehouseItem commerceWarehouseItem =
			new CommerceWarehouseItemImpl();

		commerceWarehouseItem.setNew(true);
		commerceWarehouseItem.setPrimaryKey(commerceWarehouseItemId);

		commerceWarehouseItem.setCompanyId(companyProvider.getCompanyId());

		return commerceWarehouseItem;
	}

	/**
	 * Removes the commerce warehouse item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceWarehouseItemId the primary key of the commerce warehouse item
	 * @return the commerce warehouse item that was removed
	 * @throws NoSuchWarehouseItemException if a commerce warehouse item with the primary key could not be found
	 */
	@Override
	public CommerceWarehouseItem remove(long commerceWarehouseItemId)
		throws NoSuchWarehouseItemException {

		return remove((Serializable)commerceWarehouseItemId);
	}

	/**
	 * Removes the commerce warehouse item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce warehouse item
	 * @return the commerce warehouse item that was removed
	 * @throws NoSuchWarehouseItemException if a commerce warehouse item with the primary key could not be found
	 */
	@Override
	public CommerceWarehouseItem remove(Serializable primaryKey)
		throws NoSuchWarehouseItemException {

		Session session = null;

		try {
			session = openSession();

			CommerceWarehouseItem commerceWarehouseItem =
				(CommerceWarehouseItem)session.get(
					CommerceWarehouseItemImpl.class, primaryKey);

			if (commerceWarehouseItem == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchWarehouseItemException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commerceWarehouseItem);
		}
		catch (NoSuchWarehouseItemException nsee) {
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
	protected CommerceWarehouseItem removeImpl(
		CommerceWarehouseItem commerceWarehouseItem) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceWarehouseItem)) {
				commerceWarehouseItem = (CommerceWarehouseItem)session.get(
					CommerceWarehouseItemImpl.class,
					commerceWarehouseItem.getPrimaryKeyObj());
			}

			if (commerceWarehouseItem != null) {
				session.delete(commerceWarehouseItem);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceWarehouseItem != null) {
			clearCache(commerceWarehouseItem);
		}

		return commerceWarehouseItem;
	}

	@Override
	public CommerceWarehouseItem updateImpl(
		CommerceWarehouseItem commerceWarehouseItem) {

		boolean isNew = commerceWarehouseItem.isNew();

		if (!(commerceWarehouseItem instanceof
				CommerceWarehouseItemModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceWarehouseItem.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					commerceWarehouseItem);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceWarehouseItem proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceWarehouseItem implementation " +
					commerceWarehouseItem.getClass());
		}

		CommerceWarehouseItemModelImpl commerceWarehouseItemModelImpl =
			(CommerceWarehouseItemModelImpl)commerceWarehouseItem;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceWarehouseItem.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceWarehouseItem.setCreateDate(now);
			}
			else {
				commerceWarehouseItem.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!commerceWarehouseItemModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceWarehouseItem.setModifiedDate(now);
			}
			else {
				commerceWarehouseItem.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceWarehouseItem.isNew()) {
				session.save(commerceWarehouseItem);

				commerceWarehouseItem.setNew(false);
			}
			else {
				commerceWarehouseItem = (CommerceWarehouseItem)session.merge(
					commerceWarehouseItem);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceWarehouseItemModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				commerceWarehouseItemModelImpl.getCommerceWarehouseId()
			};

			finderCache.removeResult(
				_finderPathCountByCommerceWarehouseId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCommerceWarehouseId, args);

			args = new Object[] {
				commerceWarehouseItemModelImpl.getCProductId(),
				commerceWarehouseItemModelImpl.getCPInstanceUuid()
			};

			finderCache.removeResult(_finderPathCountByCPI_CPIU, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCPI_CPIU, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((commerceWarehouseItemModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCommerceWarehouseId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceWarehouseItemModelImpl.
						getOriginalCommerceWarehouseId()
				};

				finderCache.removeResult(
					_finderPathCountByCommerceWarehouseId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommerceWarehouseId,
					args);

				args = new Object[] {
					commerceWarehouseItemModelImpl.getCommerceWarehouseId()
				};

				finderCache.removeResult(
					_finderPathCountByCommerceWarehouseId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommerceWarehouseId,
					args);
			}

			if ((commerceWarehouseItemModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCPI_CPIU.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceWarehouseItemModelImpl.getOriginalCProductId(),
					commerceWarehouseItemModelImpl.getOriginalCPInstanceUuid()
				};

				finderCache.removeResult(_finderPathCountByCPI_CPIU, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCPI_CPIU, args);

				args = new Object[] {
					commerceWarehouseItemModelImpl.getCProductId(),
					commerceWarehouseItemModelImpl.getCPInstanceUuid()
				};

				finderCache.removeResult(_finderPathCountByCPI_CPIU, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCPI_CPIU, args);
			}
		}

		entityCache.putResult(
			CommerceWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceWarehouseItemImpl.class,
			commerceWarehouseItem.getPrimaryKey(), commerceWarehouseItem,
			false);

		clearUniqueFindersCache(commerceWarehouseItemModelImpl, false);
		cacheUniqueFindersCache(commerceWarehouseItemModelImpl);

		commerceWarehouseItem.resetOriginalValues();

		return commerceWarehouseItem;
	}

	/**
	 * Returns the commerce warehouse item with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce warehouse item
	 * @return the commerce warehouse item
	 * @throws NoSuchWarehouseItemException if a commerce warehouse item with the primary key could not be found
	 */
	@Override
	public CommerceWarehouseItem findByPrimaryKey(Serializable primaryKey)
		throws NoSuchWarehouseItemException {

		CommerceWarehouseItem commerceWarehouseItem = fetchByPrimaryKey(
			primaryKey);

		if (commerceWarehouseItem == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchWarehouseItemException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commerceWarehouseItem;
	}

	/**
	 * Returns the commerce warehouse item with the primary key or throws a <code>NoSuchWarehouseItemException</code> if it could not be found.
	 *
	 * @param commerceWarehouseItemId the primary key of the commerce warehouse item
	 * @return the commerce warehouse item
	 * @throws NoSuchWarehouseItemException if a commerce warehouse item with the primary key could not be found
	 */
	@Override
	public CommerceWarehouseItem findByPrimaryKey(long commerceWarehouseItemId)
		throws NoSuchWarehouseItemException {

		return findByPrimaryKey((Serializable)commerceWarehouseItemId);
	}

	/**
	 * Returns the commerce warehouse item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce warehouse item
	 * @return the commerce warehouse item, or <code>null</code> if a commerce warehouse item with the primary key could not be found
	 */
	@Override
	public CommerceWarehouseItem fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			CommerceWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceWarehouseItemImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceWarehouseItem commerceWarehouseItem =
			(CommerceWarehouseItem)serializable;

		if (commerceWarehouseItem == null) {
			Session session = null;

			try {
				session = openSession();

				commerceWarehouseItem = (CommerceWarehouseItem)session.get(
					CommerceWarehouseItemImpl.class, primaryKey);

				if (commerceWarehouseItem != null) {
					cacheResult(commerceWarehouseItem);
				}
				else {
					entityCache.putResult(
						CommerceWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
						CommerceWarehouseItemImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CommerceWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
					CommerceWarehouseItemImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceWarehouseItem;
	}

	/**
	 * Returns the commerce warehouse item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceWarehouseItemId the primary key of the commerce warehouse item
	 * @return the commerce warehouse item, or <code>null</code> if a commerce warehouse item with the primary key could not be found
	 */
	@Override
	public CommerceWarehouseItem fetchByPrimaryKey(
		long commerceWarehouseItemId) {

		return fetchByPrimaryKey((Serializable)commerceWarehouseItemId);
	}

	@Override
	public Map<Serializable, CommerceWarehouseItem> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceWarehouseItem> map =
			new HashMap<Serializable, CommerceWarehouseItem>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceWarehouseItem commerceWarehouseItem = fetchByPrimaryKey(
				primaryKey);

			if (commerceWarehouseItem != null) {
				map.put(primaryKey, commerceWarehouseItem);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CommerceWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
				CommerceWarehouseItemImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceWarehouseItem)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_COMMERCEWAREHOUSEITEM_WHERE_PKS_IN);

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

			for (CommerceWarehouseItem commerceWarehouseItem :
					(List<CommerceWarehouseItem>)q.list()) {

				map.put(
					commerceWarehouseItem.getPrimaryKeyObj(),
					commerceWarehouseItem);

				cacheResult(commerceWarehouseItem);

				uncachedPrimaryKeys.remove(
					commerceWarehouseItem.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CommerceWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
					CommerceWarehouseItemImpl.class, primaryKey, nullModel);
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
	 * Returns all the commerce warehouse items.
	 *
	 * @return the commerce warehouse items
	 */
	@Override
	public List<CommerceWarehouseItem> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce warehouse items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce warehouse items
	 * @param end the upper bound of the range of commerce warehouse items (not inclusive)
	 * @return the range of commerce warehouse items
	 */
	@Override
	public List<CommerceWarehouseItem> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce warehouse items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce warehouse items
	 * @param end the upper bound of the range of commerce warehouse items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce warehouse items
	 */
	@Override
	public List<CommerceWarehouseItem> findAll(
		int start, int end,
		OrderByComparator<CommerceWarehouseItem> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce warehouse items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceWarehouseItemModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce warehouse items
	 * @param end the upper bound of the range of commerce warehouse items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce warehouse items
	 */
	@Override
	public List<CommerceWarehouseItem> findAll(
		int start, int end,
		OrderByComparator<CommerceWarehouseItem> orderByComparator,
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

		List<CommerceWarehouseItem> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceWarehouseItem>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEWAREHOUSEITEM);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEWAREHOUSEITEM;

				if (pagination) {
					sql = sql.concat(
						CommerceWarehouseItemModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceWarehouseItem>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceWarehouseItem>)QueryUtil.list(
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
	 * Removes all the commerce warehouse items from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceWarehouseItem commerceWarehouseItem : findAll()) {
			remove(commerceWarehouseItem);
		}
	}

	/**
	 * Returns the number of commerce warehouse items.
	 *
	 * @return the number of commerce warehouse items
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCEWAREHOUSEITEM);

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
		return CommerceWarehouseItemModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce warehouse item persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CommerceWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceWarehouseItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceWarehouseItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CommerceWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceWarehouseItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceWarehouseItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CommerceWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceWarehouseItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByCommerceWarehouseId = new FinderPath(
			CommerceWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceWarehouseItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceWarehouseItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCommerceWarehouseId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCommerceWarehouseId = new FinderPath(
			CommerceWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceWarehouseItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceWarehouseItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCommerceWarehouseId", new String[] {Long.class.getName()},
			CommerceWarehouseItemModelImpl.COMMERCEWAREHOUSEID_COLUMN_BITMASK);

		_finderPathCountByCommerceWarehouseId = new FinderPath(
			CommerceWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceWarehouseItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceWarehouseId", new String[] {Long.class.getName()});

		_finderPathFetchByCWI_CPIU = new FinderPath(
			CommerceWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceWarehouseItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceWarehouseItemImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByCWI_CPIU",
			new String[] {Long.class.getName(), String.class.getName()},
			CommerceWarehouseItemModelImpl.COMMERCEWAREHOUSEID_COLUMN_BITMASK |
			CommerceWarehouseItemModelImpl.CPINSTANCEUUID_COLUMN_BITMASK);

		_finderPathCountByCWI_CPIU = new FinderPath(
			CommerceWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceWarehouseItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCWI_CPIU",
			new String[] {Long.class.getName(), String.class.getName()});

		_finderPathWithPaginationFindByCPI_CPIU = new FinderPath(
			CommerceWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceWarehouseItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceWarehouseItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCPI_CPIU",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCPI_CPIU = new FinderPath(
			CommerceWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceWarehouseItemModelImpl.FINDER_CACHE_ENABLED,
			CommerceWarehouseItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCPI_CPIU",
			new String[] {Long.class.getName(), String.class.getName()},
			CommerceWarehouseItemModelImpl.CPRODUCTID_COLUMN_BITMASK |
			CommerceWarehouseItemModelImpl.CPINSTANCEUUID_COLUMN_BITMASK);

		_finderPathCountByCPI_CPIU = new FinderPath(
			CommerceWarehouseItemModelImpl.ENTITY_CACHE_ENABLED,
			CommerceWarehouseItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCPI_CPIU",
			new String[] {Long.class.getName(), String.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(CommerceWarehouseItemImpl.class.getName());
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

	private static final String _SQL_SELECT_COMMERCEWAREHOUSEITEM =
		"SELECT commerceWarehouseItem FROM CommerceWarehouseItem commerceWarehouseItem";

	private static final String _SQL_SELECT_COMMERCEWAREHOUSEITEM_WHERE_PKS_IN =
		"SELECT commerceWarehouseItem FROM CommerceWarehouseItem commerceWarehouseItem WHERE commerceWarehouseItemId IN (";

	private static final String _SQL_SELECT_COMMERCEWAREHOUSEITEM_WHERE =
		"SELECT commerceWarehouseItem FROM CommerceWarehouseItem commerceWarehouseItem WHERE ";

	private static final String _SQL_COUNT_COMMERCEWAREHOUSEITEM =
		"SELECT COUNT(commerceWarehouseItem) FROM CommerceWarehouseItem commerceWarehouseItem";

	private static final String _SQL_COUNT_COMMERCEWAREHOUSEITEM_WHERE =
		"SELECT COUNT(commerceWarehouseItem) FROM CommerceWarehouseItem commerceWarehouseItem WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"commerceWarehouseItem.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommerceWarehouseItem exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommerceWarehouseItem exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceWarehouseItemPersistenceImpl.class);

}