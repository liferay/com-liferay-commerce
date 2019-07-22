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

package com.liferay.commerce.discount.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.discount.exception.NoSuchDiscountException;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.model.impl.CommerceDiscountImpl;
import com.liferay.commerce.discount.model.impl.CommerceDiscountModelImpl;
import com.liferay.commerce.discount.service.persistence.CommerceDiscountPersistence;
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
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
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
 * The persistence implementation for the commerce discount service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @generated
 */
@ProviderType
public class CommerceDiscountPersistenceImpl
	extends BasePersistenceImpl<CommerceDiscount>
	implements CommerceDiscountPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommerceDiscountUtil</code> to access the commerce discount persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommerceDiscountImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the commerce discounts where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discounts where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @return the range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discounts where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce discounts where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator,
		boolean retrieveFromCache) {

		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUuid;
			finderArgs = new Object[] {uuid};
		}
		else {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<CommerceDiscount> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceDiscount>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceDiscount commerceDiscount : list) {
					if (!uuid.equals(commerceDiscount.getUuid())) {
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

			query.append(_SQL_SELECT_COMMERCEDISCOUNT_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
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
					list = (List<CommerceDiscount>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceDiscount>)QueryUtil.list(
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
	 * Returns the first commerce discount in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount
	 * @throws NoSuchDiscountException if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount findByUuid_First(
			String uuid, OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {

		CommerceDiscount commerceDiscount = fetchByUuid_First(
			uuid, orderByComparator);

		if (commerceDiscount != null) {
			return commerceDiscount;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDiscountException(msg.toString());
	}

	/**
	 * Returns the first commerce discount in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount, or <code>null</code> if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount fetchByUuid_First(
		String uuid, OrderByComparator<CommerceDiscount> orderByComparator) {

		List<CommerceDiscount> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce discount in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount
	 * @throws NoSuchDiscountException if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount findByUuid_Last(
			String uuid, OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {

		CommerceDiscount commerceDiscount = fetchByUuid_Last(
			uuid, orderByComparator);

		if (commerceDiscount != null) {
			return commerceDiscount;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDiscountException(msg.toString());
	}

	/**
	 * Returns the last commerce discount in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount, or <code>null</code> if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount fetchByUuid_Last(
		String uuid, OrderByComparator<CommerceDiscount> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CommerceDiscount> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce discounts before and after the current commerce discount in the ordered set where uuid = &#63;.
	 *
	 * @param commerceDiscountId the primary key of the current commerce discount
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce discount
	 * @throws NoSuchDiscountException if a commerce discount with the primary key could not be found
	 */
	@Override
	public CommerceDiscount[] findByUuid_PrevAndNext(
			long commerceDiscountId, String uuid,
			OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {

		uuid = Objects.toString(uuid, "");

		CommerceDiscount commerceDiscount = findByPrimaryKey(
			commerceDiscountId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscount[] array = new CommerceDiscountImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, commerceDiscount, uuid, orderByComparator, true);

			array[1] = commerceDiscount;

			array[2] = getByUuid_PrevAndNext(
				session, commerceDiscount, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceDiscount getByUuid_PrevAndNext(
		Session session, CommerceDiscount commerceDiscount, String uuid,
		OrderByComparator<CommerceDiscount> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEDISCOUNT_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
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
			query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
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
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceDiscount)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceDiscount> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the commerce discounts that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching commerce discounts that the user has permission to view
	 */
	@Override
	public List<CommerceDiscount> filterFindByUuid(String uuid) {
		return filterFindByUuid(
			uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discounts that the user has permission to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @return the range of matching commerce discounts that the user has permission to view
	 */
	@Override
	public List<CommerceDiscount> filterFindByUuid(
		String uuid, int start, int end) {

		return filterFindByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discounts that the user has permissions to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce discounts that the user has permission to view
	 */
	@Override
	public List<CommerceDiscount> filterFindByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUuid(uuid, start, end, orderByComparator);
		}

		uuid = Objects.toString(uuid, "");

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				3 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEDISCOUNT_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2_SQL);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceDiscountModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceDiscount.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, CommerceDiscountImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, CommerceDiscountImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindUuid) {
				qPos.add(uuid);
			}

			return (List<CommerceDiscount>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the commerce discounts before and after the current commerce discount in the ordered set of commerce discounts that the user has permission to view where uuid = &#63;.
	 *
	 * @param commerceDiscountId the primary key of the current commerce discount
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce discount
	 * @throws NoSuchDiscountException if a commerce discount with the primary key could not be found
	 */
	@Override
	public CommerceDiscount[] filterFindByUuid_PrevAndNext(
			long commerceDiscountId, String uuid,
			OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUuid_PrevAndNext(
				commerceDiscountId, uuid, orderByComparator);
		}

		uuid = Objects.toString(uuid, "");

		CommerceDiscount commerceDiscount = findByPrimaryKey(
			commerceDiscountId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscount[] array = new CommerceDiscountImpl[3];

			array[0] = filterGetByUuid_PrevAndNext(
				session, commerceDiscount, uuid, orderByComparator, true);

			array[1] = commerceDiscount;

			array[2] = filterGetByUuid_PrevAndNext(
				session, commerceDiscount, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceDiscount filterGetByUuid_PrevAndNext(
		Session session, CommerceDiscount commerceDiscount, String uuid,
		OrderByComparator<CommerceDiscount> orderByComparator,
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

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEDISCOUNT_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2_SQL);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
				query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceDiscountModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceDiscount.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CommerceDiscountImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CommerceDiscountImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceDiscount)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceDiscount> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce discounts where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CommerceDiscount commerceDiscount :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commerceDiscount);
		}
	}

	/**
	 * Returns the number of commerce discounts where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching commerce discounts
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEDISCOUNT_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
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

	/**
	 * Returns the number of commerce discounts that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching commerce discounts that the user has permission to view
	 */
	@Override
	public int filterCountByUuid(String uuid) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByUuid(uuid);
		}

		uuid = Objects.toString(uuid, "");

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_COMMERCEDISCOUNT_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2_SQL);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceDiscount.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindUuid) {
				qPos.add(uuid);
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

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"commerceDiscount.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(commerceDiscount.uuid IS NULL OR commerceDiscount.uuid = '')";

	private static final String _FINDER_COLUMN_UUID_UUID_2_SQL =
		"commerceDiscount.uuid_ = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3_SQL =
		"(commerceDiscount.uuid_ IS NULL OR commerceDiscount.uuid_ = '')";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the commerce discounts where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discounts where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @return the range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discounts where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce discounts where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator,
		boolean retrieveFromCache) {

		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUuid_C;
			finderArgs = new Object[] {uuid, companyId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<CommerceDiscount> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceDiscount>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceDiscount commerceDiscount : list) {
					if (!uuid.equals(commerceDiscount.getUuid()) ||
						(companyId != commerceDiscount.getCompanyId())) {

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

			query.append(_SQL_SELECT_COMMERCEDISCOUNT_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
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
					list = (List<CommerceDiscount>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceDiscount>)QueryUtil.list(
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
	 * Returns the first commerce discount in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount
	 * @throws NoSuchDiscountException if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {

		CommerceDiscount commerceDiscount = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (commerceDiscount != null) {
			return commerceDiscount;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDiscountException(msg.toString());
	}

	/**
	 * Returns the first commerce discount in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount, or <code>null</code> if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CommerceDiscount> orderByComparator) {

		List<CommerceDiscount> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce discount in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount
	 * @throws NoSuchDiscountException if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {

		CommerceDiscount commerceDiscount = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (commerceDiscount != null) {
			return commerceDiscount;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDiscountException(msg.toString());
	}

	/**
	 * Returns the last commerce discount in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount, or <code>null</code> if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CommerceDiscount> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CommerceDiscount> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce discounts before and after the current commerce discount in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param commerceDiscountId the primary key of the current commerce discount
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce discount
	 * @throws NoSuchDiscountException if a commerce discount with the primary key could not be found
	 */
	@Override
	public CommerceDiscount[] findByUuid_C_PrevAndNext(
			long commerceDiscountId, String uuid, long companyId,
			OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {

		uuid = Objects.toString(uuid, "");

		CommerceDiscount commerceDiscount = findByPrimaryKey(
			commerceDiscountId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscount[] array = new CommerceDiscountImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, commerceDiscount, uuid, companyId, orderByComparator,
				true);

			array[1] = commerceDiscount;

			array[2] = getByUuid_C_PrevAndNext(
				session, commerceDiscount, uuid, companyId, orderByComparator,
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

	protected CommerceDiscount getByUuid_C_PrevAndNext(
		Session session, CommerceDiscount commerceDiscount, String uuid,
		long companyId, OrderByComparator<CommerceDiscount> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEDISCOUNT_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

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
			query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
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
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceDiscount)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceDiscount> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the commerce discounts that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching commerce discounts that the user has permission to view
	 */
	@Override
	public List<CommerceDiscount> filterFindByUuid_C(
		String uuid, long companyId) {

		return filterFindByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discounts that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @return the range of matching commerce discounts that the user has permission to view
	 */
	@Override
	public List<CommerceDiscount> filterFindByUuid_C(
		String uuid, long companyId, int start, int end) {

		return filterFindByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discounts that the user has permissions to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce discounts that the user has permission to view
	 */
	@Override
	public List<CommerceDiscount> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByUuid_C(uuid, companyId, start, end, orderByComparator);
		}

		uuid = Objects.toString(uuid, "");

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEDISCOUNT_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2_SQL);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceDiscountModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceDiscount.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, CommerceDiscountImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, CommerceDiscountImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindUuid) {
				qPos.add(uuid);
			}

			qPos.add(companyId);

			return (List<CommerceDiscount>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the commerce discounts before and after the current commerce discount in the ordered set of commerce discounts that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param commerceDiscountId the primary key of the current commerce discount
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce discount
	 * @throws NoSuchDiscountException if a commerce discount with the primary key could not be found
	 */
	@Override
	public CommerceDiscount[] filterFindByUuid_C_PrevAndNext(
			long commerceDiscountId, String uuid, long companyId,
			OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByUuid_C_PrevAndNext(
				commerceDiscountId, uuid, companyId, orderByComparator);
		}

		uuid = Objects.toString(uuid, "");

		CommerceDiscount commerceDiscount = findByPrimaryKey(
			commerceDiscountId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscount[] array = new CommerceDiscountImpl[3];

			array[0] = filterGetByUuid_C_PrevAndNext(
				session, commerceDiscount, uuid, companyId, orderByComparator,
				true);

			array[1] = commerceDiscount;

			array[2] = filterGetByUuid_C_PrevAndNext(
				session, commerceDiscount, uuid, companyId, orderByComparator,
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

	protected CommerceDiscount filterGetByUuid_C_PrevAndNext(
		Session session, CommerceDiscount commerceDiscount, String uuid,
		long companyId, OrderByComparator<CommerceDiscount> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEDISCOUNT_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2_SQL);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
				query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceDiscountModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceDiscount.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CommerceDiscountImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CommerceDiscountImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceDiscount)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceDiscount> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce discounts where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CommerceDiscount commerceDiscount :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceDiscount);
		}
	}

	/**
	 * Returns the number of commerce discounts where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching commerce discounts
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEDISCOUNT_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
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

	/**
	 * Returns the number of commerce discounts that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching commerce discounts that the user has permission to view
	 */
	@Override
	public int filterCountByUuid_C(String uuid, long companyId) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return countByUuid_C(uuid, companyId);
		}

		uuid = Objects.toString(uuid, "");

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_COMMERCEDISCOUNT_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2_SQL);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceDiscount.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindUuid) {
				qPos.add(uuid);
			}

			qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"commerceDiscount.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(commerceDiscount.uuid IS NULL OR commerceDiscount.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_2_SQL =
		"commerceDiscount.uuid_ = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3_SQL =
		"(commerceDiscount.uuid_ IS NULL OR commerceDiscount.uuid_ = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"commerceDiscount.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the commerce discounts where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discounts where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @return the range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discounts where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce discounts where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByCompanyId;
			finderArgs = new Object[] {companyId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByCompanyId;
			finderArgs = new Object[] {
				companyId, start, end, orderByComparator
			};
		}

		List<CommerceDiscount> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceDiscount>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceDiscount commerceDiscount : list) {
					if ((companyId != commerceDiscount.getCompanyId())) {
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

			query.append(_SQL_SELECT_COMMERCEDISCOUNT_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<CommerceDiscount>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceDiscount>)QueryUtil.list(
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
	 * Returns the first commerce discount in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount
	 * @throws NoSuchDiscountException if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount findByCompanyId_First(
			long companyId,
			OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {

		CommerceDiscount commerceDiscount = fetchByCompanyId_First(
			companyId, orderByComparator);

		if (commerceDiscount != null) {
			return commerceDiscount;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDiscountException(msg.toString());
	}

	/**
	 * Returns the first commerce discount in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount, or <code>null</code> if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount fetchByCompanyId_First(
		long companyId, OrderByComparator<CommerceDiscount> orderByComparator) {

		List<CommerceDiscount> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce discount in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount
	 * @throws NoSuchDiscountException if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount findByCompanyId_Last(
			long companyId,
			OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {

		CommerceDiscount commerceDiscount = fetchByCompanyId_Last(
			companyId, orderByComparator);

		if (commerceDiscount != null) {
			return commerceDiscount;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDiscountException(msg.toString());
	}

	/**
	 * Returns the last commerce discount in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount, or <code>null</code> if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount fetchByCompanyId_Last(
		long companyId, OrderByComparator<CommerceDiscount> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<CommerceDiscount> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce discounts before and after the current commerce discount in the ordered set where companyId = &#63;.
	 *
	 * @param commerceDiscountId the primary key of the current commerce discount
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce discount
	 * @throws NoSuchDiscountException if a commerce discount with the primary key could not be found
	 */
	@Override
	public CommerceDiscount[] findByCompanyId_PrevAndNext(
			long commerceDiscountId, long companyId,
			OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {

		CommerceDiscount commerceDiscount = findByPrimaryKey(
			commerceDiscountId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscount[] array = new CommerceDiscountImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, commerceDiscount, companyId, orderByComparator, true);

			array[1] = commerceDiscount;

			array[2] = getByCompanyId_PrevAndNext(
				session, commerceDiscount, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceDiscount getByCompanyId_PrevAndNext(
		Session session, CommerceDiscount commerceDiscount, long companyId,
		OrderByComparator<CommerceDiscount> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEDISCOUNT_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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
			query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceDiscount)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceDiscount> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the commerce discounts that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce discounts that the user has permission to view
	 */
	@Override
	public List<CommerceDiscount> filterFindByCompanyId(long companyId) {
		return filterFindByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discounts that the user has permission to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @return the range of matching commerce discounts that the user has permission to view
	 */
	@Override
	public List<CommerceDiscount> filterFindByCompanyId(
		long companyId, int start, int end) {

		return filterFindByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discounts that the user has permissions to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce discounts that the user has permission to view
	 */
	@Override
	public List<CommerceDiscount> filterFindByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByCompanyId(companyId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				3 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEDISCOUNT_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceDiscountModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceDiscount.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, CommerceDiscountImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, CommerceDiscountImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			return (List<CommerceDiscount>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the commerce discounts before and after the current commerce discount in the ordered set of commerce discounts that the user has permission to view where companyId = &#63;.
	 *
	 * @param commerceDiscountId the primary key of the current commerce discount
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce discount
	 * @throws NoSuchDiscountException if a commerce discount with the primary key could not be found
	 */
	@Override
	public CommerceDiscount[] filterFindByCompanyId_PrevAndNext(
			long commerceDiscountId, long companyId,
			OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByCompanyId_PrevAndNext(
				commerceDiscountId, companyId, orderByComparator);
		}

		CommerceDiscount commerceDiscount = findByPrimaryKey(
			commerceDiscountId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscount[] array = new CommerceDiscountImpl[3];

			array[0] = filterGetByCompanyId_PrevAndNext(
				session, commerceDiscount, companyId, orderByComparator, true);

			array[1] = commerceDiscount;

			array[2] = filterGetByCompanyId_PrevAndNext(
				session, commerceDiscount, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceDiscount filterGetByCompanyId_PrevAndNext(
		Session session, CommerceDiscount commerceDiscount, long companyId,
		OrderByComparator<CommerceDiscount> orderByComparator,
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

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEDISCOUNT_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
				query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceDiscountModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceDiscount.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CommerceDiscountImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CommerceDiscountImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceDiscount)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceDiscount> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce discounts where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (CommerceDiscount commerceDiscount :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commerceDiscount);
		}
	}

	/**
	 * Returns the number of commerce discounts where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce discounts
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] {companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEDISCOUNT_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	/**
	 * Returns the number of commerce discounts that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce discounts that the user has permission to view
	 */
	@Override
	public int filterCountByCompanyId(long companyId) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return countByCompanyId(companyId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_COMMERCEDISCOUNT_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceDiscount.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 =
		"commerceDiscount.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByC_C;
	private FinderPath _finderPathWithoutPaginationFindByC_C;
	private FinderPath _finderPathCountByC_C;

	/**
	 * Returns all the commerce discounts where companyId = &#63; and couponCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param couponCode the coupon code
	 * @return the matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByC_C(long companyId, String couponCode) {
		return findByC_C(
			companyId, couponCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discounts where companyId = &#63; and couponCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param couponCode the coupon code
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @return the range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByC_C(
		long companyId, String couponCode, int start, int end) {

		return findByC_C(companyId, couponCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discounts where companyId = &#63; and couponCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param couponCode the coupon code
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByC_C(
		long companyId, String couponCode, int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator) {

		return findByC_C(
			companyId, couponCode, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce discounts where companyId = &#63; and couponCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param couponCode the coupon code
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByC_C(
		long companyId, String couponCode, int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator,
		boolean retrieveFromCache) {

		couponCode = Objects.toString(couponCode, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByC_C;
			finderArgs = new Object[] {companyId, couponCode};
		}
		else {
			finderPath = _finderPathWithPaginationFindByC_C;
			finderArgs = new Object[] {
				companyId, couponCode, start, end, orderByComparator
			};
		}

		List<CommerceDiscount> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceDiscount>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceDiscount commerceDiscount : list) {
					if ((companyId != commerceDiscount.getCompanyId()) ||
						!couponCode.equals(commerceDiscount.getCouponCode())) {

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

			query.append(_SQL_SELECT_COMMERCEDISCOUNT_WHERE);

			query.append(_FINDER_COLUMN_C_C_COMPANYID_2);

			boolean bindCouponCode = false;

			if (couponCode.isEmpty()) {
				query.append(_FINDER_COLUMN_C_C_COUPONCODE_3);
			}
			else {
				bindCouponCode = true;

				query.append(_FINDER_COLUMN_C_C_COUPONCODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindCouponCode) {
					qPos.add(couponCode);
				}

				if (!pagination) {
					list = (List<CommerceDiscount>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceDiscount>)QueryUtil.list(
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
	 * Returns the first commerce discount in the ordered set where companyId = &#63; and couponCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param couponCode the coupon code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount
	 * @throws NoSuchDiscountException if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount findByC_C_First(
			long companyId, String couponCode,
			OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {

		CommerceDiscount commerceDiscount = fetchByC_C_First(
			companyId, couponCode, orderByComparator);

		if (commerceDiscount != null) {
			return commerceDiscount;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", couponCode=");
		msg.append(couponCode);

		msg.append("}");

		throw new NoSuchDiscountException(msg.toString());
	}

	/**
	 * Returns the first commerce discount in the ordered set where companyId = &#63; and couponCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param couponCode the coupon code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount, or <code>null</code> if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount fetchByC_C_First(
		long companyId, String couponCode,
		OrderByComparator<CommerceDiscount> orderByComparator) {

		List<CommerceDiscount> list = findByC_C(
			companyId, couponCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce discount in the ordered set where companyId = &#63; and couponCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param couponCode the coupon code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount
	 * @throws NoSuchDiscountException if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount findByC_C_Last(
			long companyId, String couponCode,
			OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {

		CommerceDiscount commerceDiscount = fetchByC_C_Last(
			companyId, couponCode, orderByComparator);

		if (commerceDiscount != null) {
			return commerceDiscount;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", couponCode=");
		msg.append(couponCode);

		msg.append("}");

		throw new NoSuchDiscountException(msg.toString());
	}

	/**
	 * Returns the last commerce discount in the ordered set where companyId = &#63; and couponCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param couponCode the coupon code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount, or <code>null</code> if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount fetchByC_C_Last(
		long companyId, String couponCode,
		OrderByComparator<CommerceDiscount> orderByComparator) {

		int count = countByC_C(companyId, couponCode);

		if (count == 0) {
			return null;
		}

		List<CommerceDiscount> list = findByC_C(
			companyId, couponCode, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce discounts before and after the current commerce discount in the ordered set where companyId = &#63; and couponCode = &#63;.
	 *
	 * @param commerceDiscountId the primary key of the current commerce discount
	 * @param companyId the company ID
	 * @param couponCode the coupon code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce discount
	 * @throws NoSuchDiscountException if a commerce discount with the primary key could not be found
	 */
	@Override
	public CommerceDiscount[] findByC_C_PrevAndNext(
			long commerceDiscountId, long companyId, String couponCode,
			OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {

		couponCode = Objects.toString(couponCode, "");

		CommerceDiscount commerceDiscount = findByPrimaryKey(
			commerceDiscountId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscount[] array = new CommerceDiscountImpl[3];

			array[0] = getByC_C_PrevAndNext(
				session, commerceDiscount, companyId, couponCode,
				orderByComparator, true);

			array[1] = commerceDiscount;

			array[2] = getByC_C_PrevAndNext(
				session, commerceDiscount, companyId, couponCode,
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

	protected CommerceDiscount getByC_C_PrevAndNext(
		Session session, CommerceDiscount commerceDiscount, long companyId,
		String couponCode,
		OrderByComparator<CommerceDiscount> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEDISCOUNT_WHERE);

		query.append(_FINDER_COLUMN_C_C_COMPANYID_2);

		boolean bindCouponCode = false;

		if (couponCode.isEmpty()) {
			query.append(_FINDER_COLUMN_C_C_COUPONCODE_3);
		}
		else {
			bindCouponCode = true;

			query.append(_FINDER_COLUMN_C_C_COUPONCODE_2);
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
			query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (bindCouponCode) {
			qPos.add(couponCode);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceDiscount)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceDiscount> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the commerce discounts that the user has permission to view where companyId = &#63; and couponCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param couponCode the coupon code
	 * @return the matching commerce discounts that the user has permission to view
	 */
	@Override
	public List<CommerceDiscount> filterFindByC_C(
		long companyId, String couponCode) {

		return filterFindByC_C(
			companyId, couponCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discounts that the user has permission to view where companyId = &#63; and couponCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param couponCode the coupon code
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @return the range of matching commerce discounts that the user has permission to view
	 */
	@Override
	public List<CommerceDiscount> filterFindByC_C(
		long companyId, String couponCode, int start, int end) {

		return filterFindByC_C(companyId, couponCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discounts that the user has permissions to view where companyId = &#63; and couponCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param couponCode the coupon code
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce discounts that the user has permission to view
	 */
	@Override
	public List<CommerceDiscount> filterFindByC_C(
		long companyId, String couponCode, int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByC_C(
				companyId, couponCode, start, end, orderByComparator);
		}

		couponCode = Objects.toString(couponCode, "");

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEDISCOUNT_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_C_C_COMPANYID_2);

		boolean bindCouponCode = false;

		if (couponCode.isEmpty()) {
			query.append(_FINDER_COLUMN_C_C_COUPONCODE_3);
		}
		else {
			bindCouponCode = true;

			query.append(_FINDER_COLUMN_C_C_COUPONCODE_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceDiscountModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceDiscount.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, CommerceDiscountImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, CommerceDiscountImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (bindCouponCode) {
				qPos.add(couponCode);
			}

			return (List<CommerceDiscount>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the commerce discounts before and after the current commerce discount in the ordered set of commerce discounts that the user has permission to view where companyId = &#63; and couponCode = &#63;.
	 *
	 * @param commerceDiscountId the primary key of the current commerce discount
	 * @param companyId the company ID
	 * @param couponCode the coupon code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce discount
	 * @throws NoSuchDiscountException if a commerce discount with the primary key could not be found
	 */
	@Override
	public CommerceDiscount[] filterFindByC_C_PrevAndNext(
			long commerceDiscountId, long companyId, String couponCode,
			OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByC_C_PrevAndNext(
				commerceDiscountId, companyId, couponCode, orderByComparator);
		}

		couponCode = Objects.toString(couponCode, "");

		CommerceDiscount commerceDiscount = findByPrimaryKey(
			commerceDiscountId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscount[] array = new CommerceDiscountImpl[3];

			array[0] = filterGetByC_C_PrevAndNext(
				session, commerceDiscount, companyId, couponCode,
				orderByComparator, true);

			array[1] = commerceDiscount;

			array[2] = filterGetByC_C_PrevAndNext(
				session, commerceDiscount, companyId, couponCode,
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

	protected CommerceDiscount filterGetByC_C_PrevAndNext(
		Session session, CommerceDiscount commerceDiscount, long companyId,
		String couponCode,
		OrderByComparator<CommerceDiscount> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEDISCOUNT_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_C_C_COMPANYID_2);

		boolean bindCouponCode = false;

		if (couponCode.isEmpty()) {
			query.append(_FINDER_COLUMN_C_C_COUPONCODE_3);
		}
		else {
			bindCouponCode = true;

			query.append(_FINDER_COLUMN_C_C_COUPONCODE_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
				query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceDiscountModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceDiscount.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CommerceDiscountImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CommerceDiscountImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (bindCouponCode) {
			qPos.add(couponCode);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceDiscount)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceDiscount> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce discounts where companyId = &#63; and couponCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param couponCode the coupon code
	 */
	@Override
	public void removeByC_C(long companyId, String couponCode) {
		for (CommerceDiscount commerceDiscount :
				findByC_C(
					companyId, couponCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceDiscount);
		}
	}

	/**
	 * Returns the number of commerce discounts where companyId = &#63; and couponCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param couponCode the coupon code
	 * @return the number of matching commerce discounts
	 */
	@Override
	public int countByC_C(long companyId, String couponCode) {
		couponCode = Objects.toString(couponCode, "");

		FinderPath finderPath = _finderPathCountByC_C;

		Object[] finderArgs = new Object[] {companyId, couponCode};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEDISCOUNT_WHERE);

			query.append(_FINDER_COLUMN_C_C_COMPANYID_2);

			boolean bindCouponCode = false;

			if (couponCode.isEmpty()) {
				query.append(_FINDER_COLUMN_C_C_COUPONCODE_3);
			}
			else {
				bindCouponCode = true;

				query.append(_FINDER_COLUMN_C_C_COUPONCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindCouponCode) {
					qPos.add(couponCode);
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
	 * Returns the number of commerce discounts that the user has permission to view where companyId = &#63; and couponCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param couponCode the coupon code
	 * @return the number of matching commerce discounts that the user has permission to view
	 */
	@Override
	public int filterCountByC_C(long companyId, String couponCode) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return countByC_C(companyId, couponCode);
		}

		couponCode = Objects.toString(couponCode, "");

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_COMMERCEDISCOUNT_WHERE);

		query.append(_FINDER_COLUMN_C_C_COMPANYID_2);

		boolean bindCouponCode = false;

		if (couponCode.isEmpty()) {
			query.append(_FINDER_COLUMN_C_C_COUPONCODE_3);
		}
		else {
			bindCouponCode = true;

			query.append(_FINDER_COLUMN_C_C_COUPONCODE_2);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceDiscount.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (bindCouponCode) {
				qPos.add(couponCode);
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

	private static final String _FINDER_COLUMN_C_C_COMPANYID_2 =
		"commerceDiscount.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_COUPONCODE_2 =
		"commerceDiscount.couponCode = ?";

	private static final String _FINDER_COLUMN_C_C_COUPONCODE_3 =
		"(commerceDiscount.couponCode IS NULL OR commerceDiscount.couponCode = '')";

	private FinderPath _finderPathWithPaginationFindByLtD_S;
	private FinderPath _finderPathWithPaginationCountByLtD_S;

	/**
	 * Returns all the commerce discounts where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @return the matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByLtD_S(Date displayDate, int status) {
		return findByLtD_S(
			displayDate, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discounts where displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @return the range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByLtD_S(
		Date displayDate, int status, int start, int end) {

		return findByLtD_S(displayDate, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discounts where displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByLtD_S(
		Date displayDate, int status, int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator) {

		return findByLtD_S(
			displayDate, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce discounts where displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByLtD_S(
		Date displayDate, int status, int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByLtD_S;
		finderArgs = new Object[] {
			_getTime(displayDate), status, start, end, orderByComparator
		};

		List<CommerceDiscount> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceDiscount>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceDiscount commerceDiscount : list) {
					if ((displayDate.getTime() <=
							commerceDiscount.getDisplayDate().getTime()) ||
						(status != commerceDiscount.getStatus())) {

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

			query.append(_SQL_SELECT_COMMERCEDISCOUNT_WHERE);

			boolean bindDisplayDate = false;

			if (displayDate == null) {
				query.append(_FINDER_COLUMN_LTD_S_DISPLAYDATE_1);
			}
			else {
				bindDisplayDate = true;

				query.append(_FINDER_COLUMN_LTD_S_DISPLAYDATE_2);
			}

			query.append(_FINDER_COLUMN_LTD_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDisplayDate) {
					qPos.add(new Timestamp(displayDate.getTime()));
				}

				qPos.add(status);

				if (!pagination) {
					list = (List<CommerceDiscount>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceDiscount>)QueryUtil.list(
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
	 * Returns the first commerce discount in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount
	 * @throws NoSuchDiscountException if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount findByLtD_S_First(
			Date displayDate, int status,
			OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {

		CommerceDiscount commerceDiscount = fetchByLtD_S_First(
			displayDate, status, orderByComparator);

		if (commerceDiscount != null) {
			return commerceDiscount;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("displayDate=");
		msg.append(displayDate);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchDiscountException(msg.toString());
	}

	/**
	 * Returns the first commerce discount in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount, or <code>null</code> if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount fetchByLtD_S_First(
		Date displayDate, int status,
		OrderByComparator<CommerceDiscount> orderByComparator) {

		List<CommerceDiscount> list = findByLtD_S(
			displayDate, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce discount in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount
	 * @throws NoSuchDiscountException if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount findByLtD_S_Last(
			Date displayDate, int status,
			OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {

		CommerceDiscount commerceDiscount = fetchByLtD_S_Last(
			displayDate, status, orderByComparator);

		if (commerceDiscount != null) {
			return commerceDiscount;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("displayDate=");
		msg.append(displayDate);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchDiscountException(msg.toString());
	}

	/**
	 * Returns the last commerce discount in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount, or <code>null</code> if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount fetchByLtD_S_Last(
		Date displayDate, int status,
		OrderByComparator<CommerceDiscount> orderByComparator) {

		int count = countByLtD_S(displayDate, status);

		if (count == 0) {
			return null;
		}

		List<CommerceDiscount> list = findByLtD_S(
			displayDate, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce discounts before and after the current commerce discount in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param commerceDiscountId the primary key of the current commerce discount
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce discount
	 * @throws NoSuchDiscountException if a commerce discount with the primary key could not be found
	 */
	@Override
	public CommerceDiscount[] findByLtD_S_PrevAndNext(
			long commerceDiscountId, Date displayDate, int status,
			OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {

		CommerceDiscount commerceDiscount = findByPrimaryKey(
			commerceDiscountId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscount[] array = new CommerceDiscountImpl[3];

			array[0] = getByLtD_S_PrevAndNext(
				session, commerceDiscount, displayDate, status,
				orderByComparator, true);

			array[1] = commerceDiscount;

			array[2] = getByLtD_S_PrevAndNext(
				session, commerceDiscount, displayDate, status,
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

	protected CommerceDiscount getByLtD_S_PrevAndNext(
		Session session, CommerceDiscount commerceDiscount, Date displayDate,
		int status, OrderByComparator<CommerceDiscount> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEDISCOUNT_WHERE);

		boolean bindDisplayDate = false;

		if (displayDate == null) {
			query.append(_FINDER_COLUMN_LTD_S_DISPLAYDATE_1);
		}
		else {
			bindDisplayDate = true;

			query.append(_FINDER_COLUMN_LTD_S_DISPLAYDATE_2);
		}

		query.append(_FINDER_COLUMN_LTD_S_STATUS_2);

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
			query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindDisplayDate) {
			qPos.add(new Timestamp(displayDate.getTime()));
		}

		qPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceDiscount)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceDiscount> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the commerce discounts that the user has permission to view where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @return the matching commerce discounts that the user has permission to view
	 */
	@Override
	public List<CommerceDiscount> filterFindByLtD_S(
		Date displayDate, int status) {

		return filterFindByLtD_S(
			displayDate, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discounts that the user has permission to view where displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @return the range of matching commerce discounts that the user has permission to view
	 */
	@Override
	public List<CommerceDiscount> filterFindByLtD_S(
		Date displayDate, int status, int start, int end) {

		return filterFindByLtD_S(displayDate, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discounts that the user has permissions to view where displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce discounts that the user has permission to view
	 */
	@Override
	public List<CommerceDiscount> filterFindByLtD_S(
		Date displayDate, int status, int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByLtD_S(
				displayDate, status, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEDISCOUNT_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindDisplayDate = false;

		if (displayDate == null) {
			query.append(_FINDER_COLUMN_LTD_S_DISPLAYDATE_1);
		}
		else {
			bindDisplayDate = true;

			query.append(_FINDER_COLUMN_LTD_S_DISPLAYDATE_2);
		}

		query.append(_FINDER_COLUMN_LTD_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceDiscountModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceDiscount.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, CommerceDiscountImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, CommerceDiscountImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindDisplayDate) {
				qPos.add(new Timestamp(displayDate.getTime()));
			}

			qPos.add(status);

			return (List<CommerceDiscount>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the commerce discounts before and after the current commerce discount in the ordered set of commerce discounts that the user has permission to view where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param commerceDiscountId the primary key of the current commerce discount
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce discount
	 * @throws NoSuchDiscountException if a commerce discount with the primary key could not be found
	 */
	@Override
	public CommerceDiscount[] filterFindByLtD_S_PrevAndNext(
			long commerceDiscountId, Date displayDate, int status,
			OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByLtD_S_PrevAndNext(
				commerceDiscountId, displayDate, status, orderByComparator);
		}

		CommerceDiscount commerceDiscount = findByPrimaryKey(
			commerceDiscountId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscount[] array = new CommerceDiscountImpl[3];

			array[0] = filterGetByLtD_S_PrevAndNext(
				session, commerceDiscount, displayDate, status,
				orderByComparator, true);

			array[1] = commerceDiscount;

			array[2] = filterGetByLtD_S_PrevAndNext(
				session, commerceDiscount, displayDate, status,
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

	protected CommerceDiscount filterGetByLtD_S_PrevAndNext(
		Session session, CommerceDiscount commerceDiscount, Date displayDate,
		int status, OrderByComparator<CommerceDiscount> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEDISCOUNT_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindDisplayDate = false;

		if (displayDate == null) {
			query.append(_FINDER_COLUMN_LTD_S_DISPLAYDATE_1);
		}
		else {
			bindDisplayDate = true;

			query.append(_FINDER_COLUMN_LTD_S_DISPLAYDATE_2);
		}

		query.append(_FINDER_COLUMN_LTD_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
				query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceDiscountModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceDiscount.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CommerceDiscountImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CommerceDiscountImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindDisplayDate) {
			qPos.add(new Timestamp(displayDate.getTime()));
		}

		qPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceDiscount)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceDiscount> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce discounts where displayDate &lt; &#63; and status = &#63; from the database.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 */
	@Override
	public void removeByLtD_S(Date displayDate, int status) {
		for (CommerceDiscount commerceDiscount :
				findByLtD_S(
					displayDate, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceDiscount);
		}
	}

	/**
	 * Returns the number of commerce discounts where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @return the number of matching commerce discounts
	 */
	@Override
	public int countByLtD_S(Date displayDate, int status) {
		FinderPath finderPath = _finderPathWithPaginationCountByLtD_S;

		Object[] finderArgs = new Object[] {_getTime(displayDate), status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEDISCOUNT_WHERE);

			boolean bindDisplayDate = false;

			if (displayDate == null) {
				query.append(_FINDER_COLUMN_LTD_S_DISPLAYDATE_1);
			}
			else {
				bindDisplayDate = true;

				query.append(_FINDER_COLUMN_LTD_S_DISPLAYDATE_2);
			}

			query.append(_FINDER_COLUMN_LTD_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDisplayDate) {
					qPos.add(new Timestamp(displayDate.getTime()));
				}

				qPos.add(status);

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
	 * Returns the number of commerce discounts that the user has permission to view where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @return the number of matching commerce discounts that the user has permission to view
	 */
	@Override
	public int filterCountByLtD_S(Date displayDate, int status) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByLtD_S(displayDate, status);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_COMMERCEDISCOUNT_WHERE);

		boolean bindDisplayDate = false;

		if (displayDate == null) {
			query.append(_FINDER_COLUMN_LTD_S_DISPLAYDATE_1);
		}
		else {
			bindDisplayDate = true;

			query.append(_FINDER_COLUMN_LTD_S_DISPLAYDATE_2);
		}

		query.append(_FINDER_COLUMN_LTD_S_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceDiscount.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindDisplayDate) {
				qPos.add(new Timestamp(displayDate.getTime()));
			}

			qPos.add(status);

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

	private static final String _FINDER_COLUMN_LTD_S_DISPLAYDATE_1 =
		"commerceDiscount.displayDate IS NULL AND ";

	private static final String _FINDER_COLUMN_LTD_S_DISPLAYDATE_2 =
		"commerceDiscount.displayDate < ? AND ";

	private static final String _FINDER_COLUMN_LTD_S_STATUS_2 =
		"commerceDiscount.status = ?";

	private FinderPath _finderPathWithPaginationFindByLtE_S;
	private FinderPath _finderPathWithPaginationCountByLtE_S;

	/**
	 * Returns all the commerce discounts where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @return the matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByLtE_S(Date expirationDate, int status) {
		return findByLtE_S(
			expirationDate, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discounts where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @return the range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByLtE_S(
		Date expirationDate, int status, int start, int end) {

		return findByLtE_S(expirationDate, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discounts where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByLtE_S(
		Date expirationDate, int status, int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator) {

		return findByLtE_S(
			expirationDate, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce discounts where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByLtE_S(
		Date expirationDate, int status, int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByLtE_S;
		finderArgs = new Object[] {
			_getTime(expirationDate), status, start, end, orderByComparator
		};

		List<CommerceDiscount> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceDiscount>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceDiscount commerceDiscount : list) {
					if ((expirationDate.getTime() <=
							commerceDiscount.getExpirationDate().getTime()) ||
						(status != commerceDiscount.getStatus())) {

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

			query.append(_SQL_SELECT_COMMERCEDISCOUNT_WHERE);

			boolean bindExpirationDate = false;

			if (expirationDate == null) {
				query.append(_FINDER_COLUMN_LTE_S_EXPIRATIONDATE_1);
			}
			else {
				bindExpirationDate = true;

				query.append(_FINDER_COLUMN_LTE_S_EXPIRATIONDATE_2);
			}

			query.append(_FINDER_COLUMN_LTE_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
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

				qPos.add(status);

				if (!pagination) {
					list = (List<CommerceDiscount>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceDiscount>)QueryUtil.list(
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
	 * Returns the first commerce discount in the ordered set where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount
	 * @throws NoSuchDiscountException if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount findByLtE_S_First(
			Date expirationDate, int status,
			OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {

		CommerceDiscount commerceDiscount = fetchByLtE_S_First(
			expirationDate, status, orderByComparator);

		if (commerceDiscount != null) {
			return commerceDiscount;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("expirationDate=");
		msg.append(expirationDate);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchDiscountException(msg.toString());
	}

	/**
	 * Returns the first commerce discount in the ordered set where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount, or <code>null</code> if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount fetchByLtE_S_First(
		Date expirationDate, int status,
		OrderByComparator<CommerceDiscount> orderByComparator) {

		List<CommerceDiscount> list = findByLtE_S(
			expirationDate, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce discount in the ordered set where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount
	 * @throws NoSuchDiscountException if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount findByLtE_S_Last(
			Date expirationDate, int status,
			OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {

		CommerceDiscount commerceDiscount = fetchByLtE_S_Last(
			expirationDate, status, orderByComparator);

		if (commerceDiscount != null) {
			return commerceDiscount;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("expirationDate=");
		msg.append(expirationDate);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchDiscountException(msg.toString());
	}

	/**
	 * Returns the last commerce discount in the ordered set where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount, or <code>null</code> if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount fetchByLtE_S_Last(
		Date expirationDate, int status,
		OrderByComparator<CommerceDiscount> orderByComparator) {

		int count = countByLtE_S(expirationDate, status);

		if (count == 0) {
			return null;
		}

		List<CommerceDiscount> list = findByLtE_S(
			expirationDate, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce discounts before and after the current commerce discount in the ordered set where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * @param commerceDiscountId the primary key of the current commerce discount
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce discount
	 * @throws NoSuchDiscountException if a commerce discount with the primary key could not be found
	 */
	@Override
	public CommerceDiscount[] findByLtE_S_PrevAndNext(
			long commerceDiscountId, Date expirationDate, int status,
			OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {

		CommerceDiscount commerceDiscount = findByPrimaryKey(
			commerceDiscountId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscount[] array = new CommerceDiscountImpl[3];

			array[0] = getByLtE_S_PrevAndNext(
				session, commerceDiscount, expirationDate, status,
				orderByComparator, true);

			array[1] = commerceDiscount;

			array[2] = getByLtE_S_PrevAndNext(
				session, commerceDiscount, expirationDate, status,
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

	protected CommerceDiscount getByLtE_S_PrevAndNext(
		Session session, CommerceDiscount commerceDiscount, Date expirationDate,
		int status, OrderByComparator<CommerceDiscount> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEDISCOUNT_WHERE);

		boolean bindExpirationDate = false;

		if (expirationDate == null) {
			query.append(_FINDER_COLUMN_LTE_S_EXPIRATIONDATE_1);
		}
		else {
			bindExpirationDate = true;

			query.append(_FINDER_COLUMN_LTE_S_EXPIRATIONDATE_2);
		}

		query.append(_FINDER_COLUMN_LTE_S_STATUS_2);

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
			query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindExpirationDate) {
			qPos.add(new Timestamp(expirationDate.getTime()));
		}

		qPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceDiscount)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceDiscount> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the commerce discounts that the user has permission to view where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @return the matching commerce discounts that the user has permission to view
	 */
	@Override
	public List<CommerceDiscount> filterFindByLtE_S(
		Date expirationDate, int status) {

		return filterFindByLtE_S(
			expirationDate, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discounts that the user has permission to view where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @return the range of matching commerce discounts that the user has permission to view
	 */
	@Override
	public List<CommerceDiscount> filterFindByLtE_S(
		Date expirationDate, int status, int start, int end) {

		return filterFindByLtE_S(expirationDate, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discounts that the user has permissions to view where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce discounts that the user has permission to view
	 */
	@Override
	public List<CommerceDiscount> filterFindByLtE_S(
		Date expirationDate, int status, int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByLtE_S(
				expirationDate, status, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEDISCOUNT_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindExpirationDate = false;

		if (expirationDate == null) {
			query.append(_FINDER_COLUMN_LTE_S_EXPIRATIONDATE_1);
		}
		else {
			bindExpirationDate = true;

			query.append(_FINDER_COLUMN_LTE_S_EXPIRATIONDATE_2);
		}

		query.append(_FINDER_COLUMN_LTE_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceDiscountModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceDiscount.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, CommerceDiscountImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, CommerceDiscountImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindExpirationDate) {
				qPos.add(new Timestamp(expirationDate.getTime()));
			}

			qPos.add(status);

			return (List<CommerceDiscount>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the commerce discounts before and after the current commerce discount in the ordered set of commerce discounts that the user has permission to view where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * @param commerceDiscountId the primary key of the current commerce discount
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce discount
	 * @throws NoSuchDiscountException if a commerce discount with the primary key could not be found
	 */
	@Override
	public CommerceDiscount[] filterFindByLtE_S_PrevAndNext(
			long commerceDiscountId, Date expirationDate, int status,
			OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByLtE_S_PrevAndNext(
				commerceDiscountId, expirationDate, status, orderByComparator);
		}

		CommerceDiscount commerceDiscount = findByPrimaryKey(
			commerceDiscountId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscount[] array = new CommerceDiscountImpl[3];

			array[0] = filterGetByLtE_S_PrevAndNext(
				session, commerceDiscount, expirationDate, status,
				orderByComparator, true);

			array[1] = commerceDiscount;

			array[2] = filterGetByLtE_S_PrevAndNext(
				session, commerceDiscount, expirationDate, status,
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

	protected CommerceDiscount filterGetByLtE_S_PrevAndNext(
		Session session, CommerceDiscount commerceDiscount, Date expirationDate,
		int status, OrderByComparator<CommerceDiscount> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEDISCOUNT_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindExpirationDate = false;

		if (expirationDate == null) {
			query.append(_FINDER_COLUMN_LTE_S_EXPIRATIONDATE_1);
		}
		else {
			bindExpirationDate = true;

			query.append(_FINDER_COLUMN_LTE_S_EXPIRATIONDATE_2);
		}

		query.append(_FINDER_COLUMN_LTE_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
				query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceDiscountModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceDiscount.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CommerceDiscountImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CommerceDiscountImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindExpirationDate) {
			qPos.add(new Timestamp(expirationDate.getTime()));
		}

		qPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceDiscount)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceDiscount> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce discounts where expirationDate &lt; &#63; and status = &#63; from the database.
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 */
	@Override
	public void removeByLtE_S(Date expirationDate, int status) {
		for (CommerceDiscount commerceDiscount :
				findByLtE_S(
					expirationDate, status, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(commerceDiscount);
		}
	}

	/**
	 * Returns the number of commerce discounts where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @return the number of matching commerce discounts
	 */
	@Override
	public int countByLtE_S(Date expirationDate, int status) {
		FinderPath finderPath = _finderPathWithPaginationCountByLtE_S;

		Object[] finderArgs = new Object[] {_getTime(expirationDate), status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEDISCOUNT_WHERE);

			boolean bindExpirationDate = false;

			if (expirationDate == null) {
				query.append(_FINDER_COLUMN_LTE_S_EXPIRATIONDATE_1);
			}
			else {
				bindExpirationDate = true;

				query.append(_FINDER_COLUMN_LTE_S_EXPIRATIONDATE_2);
			}

			query.append(_FINDER_COLUMN_LTE_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindExpirationDate) {
					qPos.add(new Timestamp(expirationDate.getTime()));
				}

				qPos.add(status);

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
	 * Returns the number of commerce discounts that the user has permission to view where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @return the number of matching commerce discounts that the user has permission to view
	 */
	@Override
	public int filterCountByLtE_S(Date expirationDate, int status) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByLtE_S(expirationDate, status);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_COMMERCEDISCOUNT_WHERE);

		boolean bindExpirationDate = false;

		if (expirationDate == null) {
			query.append(_FINDER_COLUMN_LTE_S_EXPIRATIONDATE_1);
		}
		else {
			bindExpirationDate = true;

			query.append(_FINDER_COLUMN_LTE_S_EXPIRATIONDATE_2);
		}

		query.append(_FINDER_COLUMN_LTE_S_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceDiscount.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (bindExpirationDate) {
				qPos.add(new Timestamp(expirationDate.getTime()));
			}

			qPos.add(status);

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

	private static final String _FINDER_COLUMN_LTE_S_EXPIRATIONDATE_1 =
		"commerceDiscount.expirationDate IS NULL AND ";

	private static final String _FINDER_COLUMN_LTE_S_EXPIRATIONDATE_2 =
		"commerceDiscount.expirationDate < ? AND ";

	private static final String _FINDER_COLUMN_LTE_S_STATUS_2 =
		"commerceDiscount.status = ?";

	private FinderPath _finderPathFetchByC_ERC;
	private FinderPath _finderPathCountByC_ERC;

	/**
	 * Returns the commerce discount where companyId = &#63; and externalReferenceCode = &#63; or throws a <code>NoSuchDiscountException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce discount
	 * @throws NoSuchDiscountException if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount findByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchDiscountException {

		CommerceDiscount commerceDiscount = fetchByC_ERC(
			companyId, externalReferenceCode);

		if (commerceDiscount == null) {
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

			throw new NoSuchDiscountException(msg.toString());
		}

		return commerceDiscount;
	}

	/**
	 * Returns the commerce discount where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce discount, or <code>null</code> if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount fetchByC_ERC(
		long companyId, String externalReferenceCode) {

		return fetchByC_ERC(companyId, externalReferenceCode, true);
	}

	/**
	 * Returns the commerce discount where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching commerce discount, or <code>null</code> if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount fetchByC_ERC(
		long companyId, String externalReferenceCode,
		boolean retrieveFromCache) {

		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		Object[] finderArgs = new Object[] {companyId, externalReferenceCode};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_ERC, finderArgs, this);
		}

		if (result instanceof CommerceDiscount) {
			CommerceDiscount commerceDiscount = (CommerceDiscount)result;

			if ((companyId != commerceDiscount.getCompanyId()) ||
				!Objects.equals(
					externalReferenceCode,
					commerceDiscount.getExternalReferenceCode())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COMMERCEDISCOUNT_WHERE);

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

				List<CommerceDiscount> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByC_ERC, finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"CommerceDiscountPersistenceImpl.fetchByC_ERC(long, String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CommerceDiscount commerceDiscount = list.get(0);

					result = commerceDiscount;

					cacheResult(commerceDiscount);
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
			return (CommerceDiscount)result;
		}
	}

	/**
	 * Removes the commerce discount where companyId = &#63; and externalReferenceCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the commerce discount that was removed
	 */
	@Override
	public CommerceDiscount removeByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchDiscountException {

		CommerceDiscount commerceDiscount = findByC_ERC(
			companyId, externalReferenceCode);

		return remove(commerceDiscount);
	}

	/**
	 * Returns the number of commerce discounts where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the number of matching commerce discounts
	 */
	@Override
	public int countByC_ERC(long companyId, String externalReferenceCode) {
		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		FinderPath finderPath = _finderPathCountByC_ERC;

		Object[] finderArgs = new Object[] {companyId, externalReferenceCode};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEDISCOUNT_WHERE);

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
		"commerceDiscount.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2 =
		"commerceDiscount.externalReferenceCode = ?";

	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3 =
		"(commerceDiscount.externalReferenceCode IS NULL OR commerceDiscount.externalReferenceCode = '')";

	public CommerceDiscountPersistenceImpl() {
		setModelClass(CommerceDiscount.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("active", "active_");

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
	 * Caches the commerce discount in the entity cache if it is enabled.
	 *
	 * @param commerceDiscount the commerce discount
	 */
	@Override
	public void cacheResult(CommerceDiscount commerceDiscount) {
		entityCache.putResult(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountImpl.class, commerceDiscount.getPrimaryKey(),
			commerceDiscount);

		finderCache.putResult(
			_finderPathFetchByC_ERC,
			new Object[] {
				commerceDiscount.getCompanyId(),
				commerceDiscount.getExternalReferenceCode()
			},
			commerceDiscount);

		commerceDiscount.resetOriginalValues();
	}

	/**
	 * Caches the commerce discounts in the entity cache if it is enabled.
	 *
	 * @param commerceDiscounts the commerce discounts
	 */
	@Override
	public void cacheResult(List<CommerceDiscount> commerceDiscounts) {
		for (CommerceDiscount commerceDiscount : commerceDiscounts) {
			if (entityCache.getResult(
					CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
					CommerceDiscountImpl.class,
					commerceDiscount.getPrimaryKey()) == null) {

				cacheResult(commerceDiscount);
			}
			else {
				commerceDiscount.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce discounts.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceDiscountImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce discount.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceDiscount commerceDiscount) {
		entityCache.removeResult(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountImpl.class, commerceDiscount.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(CommerceDiscountModelImpl)commerceDiscount, true);
	}

	@Override
	public void clearCache(List<CommerceDiscount> commerceDiscounts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceDiscount commerceDiscount : commerceDiscounts) {
			entityCache.removeResult(
				CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
				CommerceDiscountImpl.class, commerceDiscount.getPrimaryKey());

			clearUniqueFindersCache(
				(CommerceDiscountModelImpl)commerceDiscount, true);
		}
	}

	protected void cacheUniqueFindersCache(
		CommerceDiscountModelImpl commerceDiscountModelImpl) {

		Object[] args = new Object[] {
			commerceDiscountModelImpl.getCompanyId(),
			commerceDiscountModelImpl.getExternalReferenceCode()
		};

		finderCache.putResult(
			_finderPathCountByC_ERC, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_ERC, args, commerceDiscountModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CommerceDiscountModelImpl commerceDiscountModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				commerceDiscountModelImpl.getCompanyId(),
				commerceDiscountModelImpl.getExternalReferenceCode()
			};

			finderCache.removeResult(_finderPathCountByC_ERC, args);
			finderCache.removeResult(_finderPathFetchByC_ERC, args);
		}

		if ((commerceDiscountModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_ERC.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				commerceDiscountModelImpl.getOriginalCompanyId(),
				commerceDiscountModelImpl.getOriginalExternalReferenceCode()
			};

			finderCache.removeResult(_finderPathCountByC_ERC, args);
			finderCache.removeResult(_finderPathFetchByC_ERC, args);
		}
	}

	/**
	 * Creates a new commerce discount with the primary key. Does not add the commerce discount to the database.
	 *
	 * @param commerceDiscountId the primary key for the new commerce discount
	 * @return the new commerce discount
	 */
	@Override
	public CommerceDiscount create(long commerceDiscountId) {
		CommerceDiscount commerceDiscount = new CommerceDiscountImpl();

		commerceDiscount.setNew(true);
		commerceDiscount.setPrimaryKey(commerceDiscountId);

		String uuid = PortalUUIDUtil.generate();

		commerceDiscount.setUuid(uuid);

		commerceDiscount.setCompanyId(CompanyThreadLocal.getCompanyId());

		return commerceDiscount;
	}

	/**
	 * Removes the commerce discount with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountId the primary key of the commerce discount
	 * @return the commerce discount that was removed
	 * @throws NoSuchDiscountException if a commerce discount with the primary key could not be found
	 */
	@Override
	public CommerceDiscount remove(long commerceDiscountId)
		throws NoSuchDiscountException {

		return remove((Serializable)commerceDiscountId);
	}

	/**
	 * Removes the commerce discount with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce discount
	 * @return the commerce discount that was removed
	 * @throws NoSuchDiscountException if a commerce discount with the primary key could not be found
	 */
	@Override
	public CommerceDiscount remove(Serializable primaryKey)
		throws NoSuchDiscountException {

		Session session = null;

		try {
			session = openSession();

			CommerceDiscount commerceDiscount = (CommerceDiscount)session.get(
				CommerceDiscountImpl.class, primaryKey);

			if (commerceDiscount == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDiscountException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commerceDiscount);
		}
		catch (NoSuchDiscountException nsee) {
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
	protected CommerceDiscount removeImpl(CommerceDiscount commerceDiscount) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceDiscount)) {
				commerceDiscount = (CommerceDiscount)session.get(
					CommerceDiscountImpl.class,
					commerceDiscount.getPrimaryKeyObj());
			}

			if (commerceDiscount != null) {
				session.delete(commerceDiscount);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceDiscount != null) {
			clearCache(commerceDiscount);
		}

		return commerceDiscount;
	}

	@Override
	public CommerceDiscount updateImpl(CommerceDiscount commerceDiscount) {
		boolean isNew = commerceDiscount.isNew();

		if (!(commerceDiscount instanceof CommerceDiscountModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceDiscount.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					commerceDiscount);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceDiscount proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceDiscount implementation " +
					commerceDiscount.getClass());
		}

		CommerceDiscountModelImpl commerceDiscountModelImpl =
			(CommerceDiscountModelImpl)commerceDiscount;

		if (Validator.isNull(commerceDiscount.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			commerceDiscount.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceDiscount.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceDiscount.setCreateDate(now);
			}
			else {
				commerceDiscount.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!commerceDiscountModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceDiscount.setModifiedDate(now);
			}
			else {
				commerceDiscount.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceDiscount.isNew()) {
				session.save(commerceDiscount);

				commerceDiscount.setNew(false);
			}
			else {
				commerceDiscount = (CommerceDiscount)session.merge(
					commerceDiscount);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceDiscountModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {commerceDiscountModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				commerceDiscountModelImpl.getUuid(),
				commerceDiscountModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {commerceDiscountModelImpl.getCompanyId()};

			finderCache.removeResult(_finderPathCountByCompanyId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCompanyId, args);

			args = new Object[] {
				commerceDiscountModelImpl.getCompanyId(),
				commerceDiscountModelImpl.getCouponCode()
			};

			finderCache.removeResult(_finderPathCountByC_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_C, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((commerceDiscountModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceDiscountModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {commerceDiscountModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((commerceDiscountModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceDiscountModelImpl.getOriginalUuid(),
					commerceDiscountModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					commerceDiscountModelImpl.getUuid(),
					commerceDiscountModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((commerceDiscountModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCompanyId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceDiscountModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);

				args = new Object[] {commerceDiscountModelImpl.getCompanyId()};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);
			}

			if ((commerceDiscountModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceDiscountModelImpl.getOriginalCompanyId(),
					commerceDiscountModelImpl.getOriginalCouponCode()
				};

				finderCache.removeResult(_finderPathCountByC_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_C, args);

				args = new Object[] {
					commerceDiscountModelImpl.getCompanyId(),
					commerceDiscountModelImpl.getCouponCode()
				};

				finderCache.removeResult(_finderPathCountByC_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_C, args);
			}
		}

		entityCache.putResult(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountImpl.class, commerceDiscount.getPrimaryKey(),
			commerceDiscount, false);

		clearUniqueFindersCache(commerceDiscountModelImpl, false);
		cacheUniqueFindersCache(commerceDiscountModelImpl);

		commerceDiscount.resetOriginalValues();

		return commerceDiscount;
	}

	/**
	 * Returns the commerce discount with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce discount
	 * @return the commerce discount
	 * @throws NoSuchDiscountException if a commerce discount with the primary key could not be found
	 */
	@Override
	public CommerceDiscount findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDiscountException {

		CommerceDiscount commerceDiscount = fetchByPrimaryKey(primaryKey);

		if (commerceDiscount == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDiscountException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commerceDiscount;
	}

	/**
	 * Returns the commerce discount with the primary key or throws a <code>NoSuchDiscountException</code> if it could not be found.
	 *
	 * @param commerceDiscountId the primary key of the commerce discount
	 * @return the commerce discount
	 * @throws NoSuchDiscountException if a commerce discount with the primary key could not be found
	 */
	@Override
	public CommerceDiscount findByPrimaryKey(long commerceDiscountId)
		throws NoSuchDiscountException {

		return findByPrimaryKey((Serializable)commerceDiscountId);
	}

	/**
	 * Returns the commerce discount with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce discount
	 * @return the commerce discount, or <code>null</code> if a commerce discount with the primary key could not be found
	 */
	@Override
	public CommerceDiscount fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceDiscount commerceDiscount = (CommerceDiscount)serializable;

		if (commerceDiscount == null) {
			Session session = null;

			try {
				session = openSession();

				commerceDiscount = (CommerceDiscount)session.get(
					CommerceDiscountImpl.class, primaryKey);

				if (commerceDiscount != null) {
					cacheResult(commerceDiscount);
				}
				else {
					entityCache.putResult(
						CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
						CommerceDiscountImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
					CommerceDiscountImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceDiscount;
	}

	/**
	 * Returns the commerce discount with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceDiscountId the primary key of the commerce discount
	 * @return the commerce discount, or <code>null</code> if a commerce discount with the primary key could not be found
	 */
	@Override
	public CommerceDiscount fetchByPrimaryKey(long commerceDiscountId) {
		return fetchByPrimaryKey((Serializable)commerceDiscountId);
	}

	@Override
	public Map<Serializable, CommerceDiscount> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceDiscount> map =
			new HashMap<Serializable, CommerceDiscount>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceDiscount commerceDiscount = fetchByPrimaryKey(primaryKey);

			if (commerceDiscount != null) {
				map.put(primaryKey, commerceDiscount);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
				CommerceDiscountImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceDiscount)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_COMMERCEDISCOUNT_WHERE_PKS_IN);

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

			for (CommerceDiscount commerceDiscount :
					(List<CommerceDiscount>)q.list()) {

				map.put(commerceDiscount.getPrimaryKeyObj(), commerceDiscount);

				cacheResult(commerceDiscount);

				uncachedPrimaryKeys.remove(commerceDiscount.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
					CommerceDiscountImpl.class, primaryKey, nullModel);
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
	 * Returns all the commerce discounts.
	 *
	 * @return the commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @return the range of commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findAll(
		int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce discounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findAll(
		int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator,
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

		List<CommerceDiscount> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceDiscount>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEDISCOUNT);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEDISCOUNT;

				if (pagination) {
					sql = sql.concat(CommerceDiscountModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceDiscount>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceDiscount>)QueryUtil.list(
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
	 * Removes all the commerce discounts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceDiscount commerceDiscount : findAll()) {
			remove(commerceDiscount);
		}
	}

	/**
	 * Returns the number of commerce discounts.
	 *
	 * @return the number of commerce discounts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCEDISCOUNT);

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
		return CommerceDiscountModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce discount persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			CommerceDiscountModelImpl.UUID_COLUMN_BITMASK |
			CommerceDiscountModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			CommerceDiscountModelImpl.UUID_COLUMN_BITMASK |
			CommerceDiscountModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceDiscountModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByCompanyId = new FinderPath(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCompanyId = new FinderPath(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] {Long.class.getName()},
			CommerceDiscountModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceDiscountModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByCompanyId = new FinderPath(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByC_C = new FinderPath(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_C",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_C = new FinderPath(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] {Long.class.getName(), String.class.getName()},
			CommerceDiscountModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceDiscountModelImpl.COUPONCODE_COLUMN_BITMASK |
			CommerceDiscountModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByC_C = new FinderPath(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] {Long.class.getName(), String.class.getName()});

		_finderPathWithPaginationFindByLtD_S = new FinderPath(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLtD_S",
			new String[] {
				Date.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByLtD_S = new FinderPath(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByLtD_S",
			new String[] {Date.class.getName(), Integer.class.getName()});

		_finderPathWithPaginationFindByLtE_S = new FinderPath(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLtE_S",
			new String[] {
				Date.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByLtE_S = new FinderPath(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByLtE_S",
			new String[] {Date.class.getName(), Integer.class.getName()});

		_finderPathFetchByC_ERC = new FinderPath(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_ERC",
			new String[] {Long.class.getName(), String.class.getName()},
			CommerceDiscountModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceDiscountModelImpl.EXTERNALREFERENCECODE_COLUMN_BITMASK);

		_finderPathCountByC_ERC = new FinderPath(
			CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_ERC",
			new String[] {Long.class.getName(), String.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(CommerceDiscountImpl.class.getName());
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

	private static final String _SQL_SELECT_COMMERCEDISCOUNT =
		"SELECT commerceDiscount FROM CommerceDiscount commerceDiscount";

	private static final String _SQL_SELECT_COMMERCEDISCOUNT_WHERE_PKS_IN =
		"SELECT commerceDiscount FROM CommerceDiscount commerceDiscount WHERE commerceDiscountId IN (";

	private static final String _SQL_SELECT_COMMERCEDISCOUNT_WHERE =
		"SELECT commerceDiscount FROM CommerceDiscount commerceDiscount WHERE ";

	private static final String _SQL_COUNT_COMMERCEDISCOUNT =
		"SELECT COUNT(commerceDiscount) FROM CommerceDiscount commerceDiscount";

	private static final String _SQL_COUNT_COMMERCEDISCOUNT_WHERE =
		"SELECT COUNT(commerceDiscount) FROM CommerceDiscount commerceDiscount WHERE ";

	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN =
		"commerceDiscount.commerceDiscountId";

	private static final String _FILTER_SQL_SELECT_COMMERCEDISCOUNT_WHERE =
		"SELECT DISTINCT {commerceDiscount.*} FROM CommerceDiscount commerceDiscount WHERE ";

	private static final String
		_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_1 =
			"SELECT {CommerceDiscount.*} FROM (SELECT DISTINCT commerceDiscount.commerceDiscountId FROM CommerceDiscount commerceDiscount WHERE ";

	private static final String
		_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_2 =
			") TEMP_TABLE INNER JOIN CommerceDiscount ON TEMP_TABLE.commerceDiscountId = CommerceDiscount.commerceDiscountId";

	private static final String _FILTER_SQL_COUNT_COMMERCEDISCOUNT_WHERE =
		"SELECT COUNT(DISTINCT commerceDiscount.commerceDiscountId) AS COUNT_VALUE FROM CommerceDiscount commerceDiscount WHERE ";

	private static final String _FILTER_ENTITY_ALIAS = "commerceDiscount";

	private static final String _FILTER_ENTITY_TABLE = "CommerceDiscount";

	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceDiscount.";

	private static final String _ORDER_BY_ENTITY_TABLE = "CommerceDiscount.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommerceDiscount exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommerceDiscount exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceDiscountPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "active"});

}