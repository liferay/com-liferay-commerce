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

import com.liferay.petra.string.StringBundler;

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
 * @see CommerceDiscountPersistence
 * @see com.liferay.commerce.discount.service.persistence.CommerceDiscountUtil
 * @generated
 */
@ProviderType
public class CommerceDiscountPersistenceImpl extends BasePersistenceImpl<CommerceDiscount>
	implements CommerceDiscountPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommerceDiscountUtil} to access the commerce discount persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommerceDiscountImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			CommerceDiscountModelImpl.UUID_COLUMN_BITMASK |
			CommerceDiscountModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByUuid(String uuid, int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce discounts where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<CommerceDiscount> findByUuid(String uuid, int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator,
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

		List<CommerceDiscount> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceDiscount>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceDiscount commerceDiscount : list) {
					if (!Objects.equals(uuid, commerceDiscount.getUuid())) {
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

			query.append(_SQL_SELECT_COMMERCEDISCOUNT_WHERE);

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
					list = (List<CommerceDiscount>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceDiscount>)QueryUtil.list(q,
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
	 * Returns the first commerce discount in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount
	 * @throws NoSuchDiscountException if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount findByUuid_First(String uuid,
		OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {
		CommerceDiscount commerceDiscount = fetchByUuid_First(uuid,
				orderByComparator);

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
	public CommerceDiscount fetchByUuid_First(String uuid,
		OrderByComparator<CommerceDiscount> orderByComparator) {
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
	public CommerceDiscount findByUuid_Last(String uuid,
		OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {
		CommerceDiscount commerceDiscount = fetchByUuid_Last(uuid,
				orderByComparator);

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
	public CommerceDiscount fetchByUuid_Last(String uuid,
		OrderByComparator<CommerceDiscount> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CommerceDiscount> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

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
	public CommerceDiscount[] findByUuid_PrevAndNext(long commerceDiscountId,
		String uuid, OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {
		CommerceDiscount commerceDiscount = findByPrimaryKey(commerceDiscountId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscount[] array = new CommerceDiscountImpl[3];

			array[0] = getByUuid_PrevAndNext(session, commerceDiscount, uuid,
					orderByComparator, true);

			array[1] = commerceDiscount;

			array[2] = getByUuid_PrevAndNext(session, commerceDiscount, uuid,
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

	protected CommerceDiscount getByUuid_PrevAndNext(Session session,
		CommerceDiscount commerceDiscount, String uuid,
		OrderByComparator<CommerceDiscount> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COMMERCEDISCOUNT_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(commerceDiscount);

			for (Object value : values) {
				qPos.add(value);
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
		for (CommerceDiscount commerceDiscount : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
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
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEDISCOUNT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "commerceDiscount.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "commerceDiscount.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(commerceDiscount.uuid IS NULL OR commerceDiscount.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			CommerceDiscountModelImpl.UUID_COLUMN_BITMASK |
			CommerceDiscountModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the commerce discount where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDiscountException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching commerce discount
	 * @throws NoSuchDiscountException if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount findByUUID_G(String uuid, long groupId)
		throws NoSuchDiscountException {
		CommerceDiscount commerceDiscount = fetchByUUID_G(uuid, groupId);

		if (commerceDiscount == null) {
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

			throw new NoSuchDiscountException(msg.toString());
		}

		return commerceDiscount;
	}

	/**
	 * Returns the commerce discount where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching commerce discount, or <code>null</code> if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the commerce discount where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching commerce discount, or <code>null</code> if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof CommerceDiscount) {
			CommerceDiscount commerceDiscount = (CommerceDiscount)result;

			if (!Objects.equals(uuid, commerceDiscount.getUuid()) ||
					(groupId != commerceDiscount.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COMMERCEDISCOUNT_WHERE);

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

				List<CommerceDiscount> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					CommerceDiscount commerceDiscount = list.get(0);

					result = commerceDiscount;

					cacheResult(commerceDiscount);
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
			return (CommerceDiscount)result;
		}
	}

	/**
	 * Removes the commerce discount where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the commerce discount that was removed
	 */
	@Override
	public CommerceDiscount removeByUUID_G(String uuid, long groupId)
		throws NoSuchDiscountException {
		CommerceDiscount commerceDiscount = findByUUID_G(uuid, groupId);

		return remove(commerceDiscount);
	}

	/**
	 * Returns the number of commerce discounts where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching commerce discounts
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEDISCOUNT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "commerceDiscount.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "commerceDiscount.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(commerceDiscount.uuid IS NULL OR commerceDiscount.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "commerceDiscount.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			CommerceDiscountModelImpl.UUID_COLUMN_BITMASK |
			CommerceDiscountModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceDiscountModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the commerce discounts where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discounts where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @return the range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discounts where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<CommerceDiscount> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce discounts where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<CommerceDiscount> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator,
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

		List<CommerceDiscount> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceDiscount>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceDiscount commerceDiscount : list) {
					if (!Objects.equals(uuid, commerceDiscount.getUuid()) ||
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
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_COMMERCEDISCOUNT_WHERE);

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
					list = (List<CommerceDiscount>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceDiscount>)QueryUtil.list(q,
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
	 * Returns the first commerce discount in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount
	 * @throws NoSuchDiscountException if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {
		CommerceDiscount commerceDiscount = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

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
	public CommerceDiscount fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<CommerceDiscount> orderByComparator) {
		List<CommerceDiscount> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

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
	public CommerceDiscount findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {
		CommerceDiscount commerceDiscount = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

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
	public CommerceDiscount fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<CommerceDiscount> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CommerceDiscount> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

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
		CommerceDiscount commerceDiscount = findByPrimaryKey(commerceDiscountId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscount[] array = new CommerceDiscountImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, commerceDiscount, uuid,
					companyId, orderByComparator, true);

			array[1] = commerceDiscount;

			array[2] = getByUuid_C_PrevAndNext(session, commerceDiscount, uuid,
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

	protected CommerceDiscount getByUuid_C_PrevAndNext(Session session,
		CommerceDiscount commerceDiscount, String uuid, long companyId,
		OrderByComparator<CommerceDiscount> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_COMMERCEDISCOUNT_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(commerceDiscount);

			for (Object value : values) {
				qPos.add(value);
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
		for (CommerceDiscount commerceDiscount : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
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
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEDISCOUNT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "commerceDiscount.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "commerceDiscount.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(commerceDiscount.uuid IS NULL OR commerceDiscount.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "commerceDiscount.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			CommerceDiscountModelImpl.GROUPID_COLUMN_BITMASK |
			CommerceDiscountModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the commerce discounts where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discounts where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @return the range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discounts where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByGroupId(long groupId, int start,
		int end, OrderByComparator<CommerceDiscount> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce discounts where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByGroupId(long groupId, int start,
		int end, OrderByComparator<CommerceDiscount> orderByComparator,
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

		List<CommerceDiscount> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceDiscount>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceDiscount commerceDiscount : list) {
					if ((groupId != commerceDiscount.getGroupId())) {
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

			query.append(_SQL_SELECT_COMMERCEDISCOUNT_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<CommerceDiscount>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceDiscount>)QueryUtil.list(q,
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
	 * Returns the first commerce discount in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount
	 * @throws NoSuchDiscountException if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount findByGroupId_First(long groupId,
		OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {
		CommerceDiscount commerceDiscount = fetchByGroupId_First(groupId,
				orderByComparator);

		if (commerceDiscount != null) {
			return commerceDiscount;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDiscountException(msg.toString());
	}

	/**
	 * Returns the first commerce discount in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount, or <code>null</code> if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount fetchByGroupId_First(long groupId,
		OrderByComparator<CommerceDiscount> orderByComparator) {
		List<CommerceDiscount> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce discount in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount
	 * @throws NoSuchDiscountException if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount findByGroupId_Last(long groupId,
		OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {
		CommerceDiscount commerceDiscount = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (commerceDiscount != null) {
			return commerceDiscount;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchDiscountException(msg.toString());
	}

	/**
	 * Returns the last commerce discount in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount, or <code>null</code> if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount fetchByGroupId_Last(long groupId,
		OrderByComparator<CommerceDiscount> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<CommerceDiscount> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce discounts before and after the current commerce discount in the ordered set where groupId = &#63;.
	 *
	 * @param commerceDiscountId the primary key of the current commerce discount
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce discount
	 * @throws NoSuchDiscountException if a commerce discount with the primary key could not be found
	 */
	@Override
	public CommerceDiscount[] findByGroupId_PrevAndNext(
		long commerceDiscountId, long groupId,
		OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {
		CommerceDiscount commerceDiscount = findByPrimaryKey(commerceDiscountId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscount[] array = new CommerceDiscountImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, commerceDiscount,
					groupId, orderByComparator, true);

			array[1] = commerceDiscount;

			array[2] = getByGroupId_PrevAndNext(session, commerceDiscount,
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

	protected CommerceDiscount getByGroupId_PrevAndNext(Session session,
		CommerceDiscount commerceDiscount, long groupId,
		OrderByComparator<CommerceDiscount> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COMMERCEDISCOUNT_WHERE);

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
			query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceDiscount);

			for (Object value : values) {
				qPos.add(value);
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
	 * Returns all the commerce discounts that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching commerce discounts that the user has permission to view
	 */
	@Override
	public List<CommerceDiscount> filterFindByGroupId(long groupId) {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discounts that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @return the range of matching commerce discounts that the user has permission to view
	 */
	@Override
	public List<CommerceDiscount> filterFindByGroupId(long groupId, int start,
		int end) {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discounts that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce discounts that the user has permission to view
	 */
	@Override
	public List<CommerceDiscount> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator<CommerceDiscount> orderByComparator) {
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
			query.append(_FILTER_SQL_SELECT_COMMERCEDISCOUNT_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceDiscountModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceDiscount.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

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

			qPos.add(groupId);

			return (List<CommerceDiscount>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the commerce discounts before and after the current commerce discount in the ordered set of commerce discounts that the user has permission to view where groupId = &#63;.
	 *
	 * @param commerceDiscountId the primary key of the current commerce discount
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce discount
	 * @throws NoSuchDiscountException if a commerce discount with the primary key could not be found
	 */
	@Override
	public CommerceDiscount[] filterFindByGroupId_PrevAndNext(
		long commerceDiscountId, long groupId,
		OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(commerceDiscountId, groupId,
				orderByComparator);
		}

		CommerceDiscount commerceDiscount = findByPrimaryKey(commerceDiscountId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscount[] array = new CommerceDiscountImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session,
					commerceDiscount, groupId, orderByComparator, true);

			array[1] = commerceDiscount;

			array[2] = filterGetByGroupId_PrevAndNext(session,
					commerceDiscount, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceDiscount filterGetByGroupId_PrevAndNext(Session session,
		CommerceDiscount commerceDiscount, long groupId,
		OrderByComparator<CommerceDiscount> orderByComparator, boolean previous) {
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
			query.append(_FILTER_SQL_SELECT_COMMERCEDISCOUNT_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceDiscountModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceDiscount.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

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

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceDiscount);

			for (Object value : values) {
				qPos.add(value);
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
	 * Removes all the commerce discounts where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (CommerceDiscount commerceDiscount : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(commerceDiscount);
		}
	}

	/**
	 * Returns the number of commerce discounts where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching commerce discounts
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEDISCOUNT_WHERE);

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
	 * Returns the number of commerce discounts that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching commerce discounts that the user has permission to view
	 */
	@Override
	public int filterCountByGroupId(long groupId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_COMMERCEDISCOUNT_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceDiscount.class.getName(),
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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "commerceDiscount.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_C = new FinderPath(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_C",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C = new FinderPath(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_C",
			new String[] { Long.class.getName(), String.class.getName() },
			CommerceDiscountModelImpl.GROUPID_COLUMN_BITMASK |
			CommerceDiscountModelImpl.COUPONCODE_COLUMN_BITMASK |
			CommerceDiscountModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_C = new FinderPath(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_C",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the commerce discounts where groupId = &#63; and couponCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param couponCode the coupon code
	 * @return the matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByG_C(long groupId, String couponCode) {
		return findByG_C(groupId, couponCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discounts where groupId = &#63; and couponCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param couponCode the coupon code
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @return the range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByG_C(long groupId, String couponCode,
		int start, int end) {
		return findByG_C(groupId, couponCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discounts where groupId = &#63; and couponCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param couponCode the coupon code
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByG_C(long groupId, String couponCode,
		int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator) {
		return findByG_C(groupId, couponCode, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the commerce discounts where groupId = &#63; and couponCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param couponCode the coupon code
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByG_C(long groupId, String couponCode,
		int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C;
			finderArgs = new Object[] { groupId, couponCode };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_C;
			finderArgs = new Object[] {
					groupId, couponCode,
					
					start, end, orderByComparator
				};
		}

		List<CommerceDiscount> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceDiscount>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceDiscount commerceDiscount : list) {
					if ((groupId != commerceDiscount.getGroupId()) ||
							!Objects.equals(couponCode,
								commerceDiscount.getCouponCode())) {
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

			query.append(_SQL_SELECT_COMMERCEDISCOUNT_WHERE);

			query.append(_FINDER_COLUMN_G_C_GROUPID_2);

			boolean bindCouponCode = false;

			if (couponCode == null) {
				query.append(_FINDER_COLUMN_G_C_COUPONCODE_1);
			}
			else if (couponCode.equals("")) {
				query.append(_FINDER_COLUMN_G_C_COUPONCODE_3);
			}
			else {
				bindCouponCode = true;

				query.append(_FINDER_COLUMN_G_C_COUPONCODE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindCouponCode) {
					qPos.add(couponCode);
				}

				if (!pagination) {
					list = (List<CommerceDiscount>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceDiscount>)QueryUtil.list(q,
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
	 * Returns the first commerce discount in the ordered set where groupId = &#63; and couponCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param couponCode the coupon code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount
	 * @throws NoSuchDiscountException if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount findByG_C_First(long groupId, String couponCode,
		OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {
		CommerceDiscount commerceDiscount = fetchByG_C_First(groupId,
				couponCode, orderByComparator);

		if (commerceDiscount != null) {
			return commerceDiscount;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", couponCode=");
		msg.append(couponCode);

		msg.append("}");

		throw new NoSuchDiscountException(msg.toString());
	}

	/**
	 * Returns the first commerce discount in the ordered set where groupId = &#63; and couponCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param couponCode the coupon code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount, or <code>null</code> if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount fetchByG_C_First(long groupId, String couponCode,
		OrderByComparator<CommerceDiscount> orderByComparator) {
		List<CommerceDiscount> list = findByG_C(groupId, couponCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce discount in the ordered set where groupId = &#63; and couponCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param couponCode the coupon code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount
	 * @throws NoSuchDiscountException if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount findByG_C_Last(long groupId, String couponCode,
		OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {
		CommerceDiscount commerceDiscount = fetchByG_C_Last(groupId,
				couponCode, orderByComparator);

		if (commerceDiscount != null) {
			return commerceDiscount;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", couponCode=");
		msg.append(couponCode);

		msg.append("}");

		throw new NoSuchDiscountException(msg.toString());
	}

	/**
	 * Returns the last commerce discount in the ordered set where groupId = &#63; and couponCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param couponCode the coupon code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount, or <code>null</code> if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount fetchByG_C_Last(long groupId, String couponCode,
		OrderByComparator<CommerceDiscount> orderByComparator) {
		int count = countByG_C(groupId, couponCode);

		if (count == 0) {
			return null;
		}

		List<CommerceDiscount> list = findByG_C(groupId, couponCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce discounts before and after the current commerce discount in the ordered set where groupId = &#63; and couponCode = &#63;.
	 *
	 * @param commerceDiscountId the primary key of the current commerce discount
	 * @param groupId the group ID
	 * @param couponCode the coupon code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce discount
	 * @throws NoSuchDiscountException if a commerce discount with the primary key could not be found
	 */
	@Override
	public CommerceDiscount[] findByG_C_PrevAndNext(long commerceDiscountId,
		long groupId, String couponCode,
		OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {
		CommerceDiscount commerceDiscount = findByPrimaryKey(commerceDiscountId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscount[] array = new CommerceDiscountImpl[3];

			array[0] = getByG_C_PrevAndNext(session, commerceDiscount, groupId,
					couponCode, orderByComparator, true);

			array[1] = commerceDiscount;

			array[2] = getByG_C_PrevAndNext(session, commerceDiscount, groupId,
					couponCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceDiscount getByG_C_PrevAndNext(Session session,
		CommerceDiscount commerceDiscount, long groupId, String couponCode,
		OrderByComparator<CommerceDiscount> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_COMMERCEDISCOUNT_WHERE);

		query.append(_FINDER_COLUMN_G_C_GROUPID_2);

		boolean bindCouponCode = false;

		if (couponCode == null) {
			query.append(_FINDER_COLUMN_G_C_COUPONCODE_1);
		}
		else if (couponCode.equals("")) {
			query.append(_FINDER_COLUMN_G_C_COUPONCODE_3);
		}
		else {
			bindCouponCode = true;

			query.append(_FINDER_COLUMN_G_C_COUPONCODE_2);
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
			query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindCouponCode) {
			qPos.add(couponCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceDiscount);

			for (Object value : values) {
				qPos.add(value);
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
	 * Returns all the commerce discounts that the user has permission to view where groupId = &#63; and couponCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param couponCode the coupon code
	 * @return the matching commerce discounts that the user has permission to view
	 */
	@Override
	public List<CommerceDiscount> filterFindByG_C(long groupId,
		String couponCode) {
		return filterFindByG_C(groupId, couponCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discounts that the user has permission to view where groupId = &#63; and couponCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param couponCode the coupon code
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @return the range of matching commerce discounts that the user has permission to view
	 */
	@Override
	public List<CommerceDiscount> filterFindByG_C(long groupId,
		String couponCode, int start, int end) {
		return filterFindByG_C(groupId, couponCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discounts that the user has permissions to view where groupId = &#63; and couponCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param couponCode the coupon code
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce discounts that the user has permission to view
	 */
	@Override
	public List<CommerceDiscount> filterFindByG_C(long groupId,
		String couponCode, int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_C(groupId, couponCode, start, end, orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_COMMERCEDISCOUNT_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_C_GROUPID_2);

		boolean bindCouponCode = false;

		if (couponCode == null) {
			query.append(_FINDER_COLUMN_G_C_COUPONCODE_1);
		}
		else if (couponCode.equals("")) {
			query.append(_FINDER_COLUMN_G_C_COUPONCODE_3);
		}
		else {
			bindCouponCode = true;

			query.append(_FINDER_COLUMN_G_C_COUPONCODE_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceDiscountModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceDiscount.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

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

			qPos.add(groupId);

			if (bindCouponCode) {
				qPos.add(couponCode);
			}

			return (List<CommerceDiscount>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the commerce discounts before and after the current commerce discount in the ordered set of commerce discounts that the user has permission to view where groupId = &#63; and couponCode = &#63;.
	 *
	 * @param commerceDiscountId the primary key of the current commerce discount
	 * @param groupId the group ID
	 * @param couponCode the coupon code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce discount
	 * @throws NoSuchDiscountException if a commerce discount with the primary key could not be found
	 */
	@Override
	public CommerceDiscount[] filterFindByG_C_PrevAndNext(
		long commerceDiscountId, long groupId, String couponCode,
		OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_C_PrevAndNext(commerceDiscountId, groupId,
				couponCode, orderByComparator);
		}

		CommerceDiscount commerceDiscount = findByPrimaryKey(commerceDiscountId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscount[] array = new CommerceDiscountImpl[3];

			array[0] = filterGetByG_C_PrevAndNext(session, commerceDiscount,
					groupId, couponCode, orderByComparator, true);

			array[1] = commerceDiscount;

			array[2] = filterGetByG_C_PrevAndNext(session, commerceDiscount,
					groupId, couponCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceDiscount filterGetByG_C_PrevAndNext(Session session,
		CommerceDiscount commerceDiscount, long groupId, String couponCode,
		OrderByComparator<CommerceDiscount> orderByComparator, boolean previous) {
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
			query.append(_FILTER_SQL_SELECT_COMMERCEDISCOUNT_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_C_GROUPID_2);

		boolean bindCouponCode = false;

		if (couponCode == null) {
			query.append(_FINDER_COLUMN_G_C_COUPONCODE_1);
		}
		else if (couponCode.equals("")) {
			query.append(_FINDER_COLUMN_G_C_COUPONCODE_3);
		}
		else {
			bindCouponCode = true;

			query.append(_FINDER_COLUMN_G_C_COUPONCODE_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CommerceDiscountModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceDiscountModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceDiscount.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

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

		qPos.add(groupId);

		if (bindCouponCode) {
			qPos.add(couponCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceDiscount);

			for (Object value : values) {
				qPos.add(value);
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
	 * Removes all the commerce discounts where groupId = &#63; and couponCode = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param couponCode the coupon code
	 */
	@Override
	public void removeByG_C(long groupId, String couponCode) {
		for (CommerceDiscount commerceDiscount : findByG_C(groupId, couponCode,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(commerceDiscount);
		}
	}

	/**
	 * Returns the number of commerce discounts where groupId = &#63; and couponCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param couponCode the coupon code
	 * @return the number of matching commerce discounts
	 */
	@Override
	public int countByG_C(long groupId, String couponCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_C;

		Object[] finderArgs = new Object[] { groupId, couponCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEDISCOUNT_WHERE);

			query.append(_FINDER_COLUMN_G_C_GROUPID_2);

			boolean bindCouponCode = false;

			if (couponCode == null) {
				query.append(_FINDER_COLUMN_G_C_COUPONCODE_1);
			}
			else if (couponCode.equals("")) {
				query.append(_FINDER_COLUMN_G_C_COUPONCODE_3);
			}
			else {
				bindCouponCode = true;

				query.append(_FINDER_COLUMN_G_C_COUPONCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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
	 * Returns the number of commerce discounts that the user has permission to view where groupId = &#63; and couponCode = &#63;.
	 *
	 * @param groupId the group ID
	 * @param couponCode the coupon code
	 * @return the number of matching commerce discounts that the user has permission to view
	 */
	@Override
	public int filterCountByG_C(long groupId, String couponCode) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_C(groupId, couponCode);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_COMMERCEDISCOUNT_WHERE);

		query.append(_FINDER_COLUMN_G_C_GROUPID_2);

		boolean bindCouponCode = false;

		if (couponCode == null) {
			query.append(_FINDER_COLUMN_G_C_COUPONCODE_1);
		}
		else if (couponCode.equals("")) {
			query.append(_FINDER_COLUMN_G_C_COUPONCODE_3);
		}
		else {
			bindCouponCode = true;

			query.append(_FINDER_COLUMN_G_C_COUPONCODE_2);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceDiscount.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_G_C_GROUPID_2 = "commerceDiscount.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_COUPONCODE_1 = "commerceDiscount.couponCode IS NULL";
	private static final String _FINDER_COLUMN_G_C_COUPONCODE_2 = "commerceDiscount.couponCode = ?";
	private static final String _FINDER_COLUMN_G_C_COUPONCODE_3 = "(commerceDiscount.couponCode IS NULL OR commerceDiscount.couponCode = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LTD_S = new FinderPath(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLtD_S",
			new String[] {
				Date.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_LTD_S = new FinderPath(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByLtD_S",
			new String[] { Date.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the commerce discounts where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @return the matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByLtD_S(Date displayDate, int status) {
		return findByLtD_S(displayDate, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discounts where displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @return the range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByLtD_S(Date displayDate, int status,
		int start, int end) {
		return findByLtD_S(displayDate, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discounts where displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<CommerceDiscount> findByLtD_S(Date displayDate, int status,
		int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator) {
		return findByLtD_S(displayDate, status, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the commerce discounts where displayDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<CommerceDiscount> findByLtD_S(Date displayDate, int status,
		int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LTD_S;
		finderArgs = new Object[] {
				_getTime(displayDate), status,
				
				start, end, orderByComparator
			};

		List<CommerceDiscount> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceDiscount>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceDiscount commerceDiscount : list) {
					if ((displayDate.getTime() <= commerceDiscount.getDisplayDate()
																	  .getTime()) ||
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
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
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
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
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
					list = (List<CommerceDiscount>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceDiscount>)QueryUtil.list(q,
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
	 * Returns the first commerce discount in the ordered set where displayDate &lt; &#63; and status = &#63;.
	 *
	 * @param displayDate the display date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount
	 * @throws NoSuchDiscountException if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount findByLtD_S_First(Date displayDate, int status,
		OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {
		CommerceDiscount commerceDiscount = fetchByLtD_S_First(displayDate,
				status, orderByComparator);

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
	public CommerceDiscount fetchByLtD_S_First(Date displayDate, int status,
		OrderByComparator<CommerceDiscount> orderByComparator) {
		List<CommerceDiscount> list = findByLtD_S(displayDate, status, 0, 1,
				orderByComparator);

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
	public CommerceDiscount findByLtD_S_Last(Date displayDate, int status,
		OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {
		CommerceDiscount commerceDiscount = fetchByLtD_S_Last(displayDate,
				status, orderByComparator);

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
	public CommerceDiscount fetchByLtD_S_Last(Date displayDate, int status,
		OrderByComparator<CommerceDiscount> orderByComparator) {
		int count = countByLtD_S(displayDate, status);

		if (count == 0) {
			return null;
		}

		List<CommerceDiscount> list = findByLtD_S(displayDate, status,
				count - 1, count, orderByComparator);

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
	public CommerceDiscount[] findByLtD_S_PrevAndNext(long commerceDiscountId,
		Date displayDate, int status,
		OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {
		CommerceDiscount commerceDiscount = findByPrimaryKey(commerceDiscountId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscount[] array = new CommerceDiscountImpl[3];

			array[0] = getByLtD_S_PrevAndNext(session, commerceDiscount,
					displayDate, status, orderByComparator, true);

			array[1] = commerceDiscount;

			array[2] = getByLtD_S_PrevAndNext(session, commerceDiscount,
					displayDate, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceDiscount getByLtD_S_PrevAndNext(Session session,
		CommerceDiscount commerceDiscount, Date displayDate, int status,
		OrderByComparator<CommerceDiscount> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
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
			Object[] values = orderByComparator.getOrderByConditionValues(commerceDiscount);

			for (Object value : values) {
				qPos.add(value);
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
		for (CommerceDiscount commerceDiscount : findByLtD_S(displayDate,
				status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
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
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_LTD_S;

		Object[] finderArgs = new Object[] { _getTime(displayDate), status };

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

	private static final String _FINDER_COLUMN_LTD_S_DISPLAYDATE_1 = "commerceDiscount.displayDate IS NULL AND ";
	private static final String _FINDER_COLUMN_LTD_S_DISPLAYDATE_2 = "commerceDiscount.displayDate < ? AND ";
	private static final String _FINDER_COLUMN_LTD_S_STATUS_2 = "commerceDiscount.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LTE_S = new FinderPath(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLtE_S",
			new String[] {
				Date.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_LTE_S = new FinderPath(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByLtE_S",
			new String[] { Date.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the commerce discounts where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @return the matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByLtE_S(Date expirationDate, int status) {
		return findByLtE_S(expirationDate, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discounts where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @return the range of matching commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findByLtE_S(Date expirationDate, int status,
		int start, int end) {
		return findByLtE_S(expirationDate, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discounts where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<CommerceDiscount> findByLtE_S(Date expirationDate, int status,
		int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator) {
		return findByLtE_S(expirationDate, status, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce discounts where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<CommerceDiscount> findByLtE_S(Date expirationDate, int status,
		int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LTE_S;
		finderArgs = new Object[] {
				_getTime(expirationDate), status,
				
				start, end, orderByComparator
			};

		List<CommerceDiscount> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceDiscount>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceDiscount commerceDiscount : list) {
					if ((expirationDate.getTime() <= commerceDiscount.getExpirationDate()
																		 .getTime()) ||
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
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
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
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
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
					list = (List<CommerceDiscount>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceDiscount>)QueryUtil.list(q,
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
	 * Returns the first commerce discount in the ordered set where expirationDate &lt; &#63; and status = &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount
	 * @throws NoSuchDiscountException if a matching commerce discount could not be found
	 */
	@Override
	public CommerceDiscount findByLtE_S_First(Date expirationDate, int status,
		OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {
		CommerceDiscount commerceDiscount = fetchByLtE_S_First(expirationDate,
				status, orderByComparator);

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
	public CommerceDiscount fetchByLtE_S_First(Date expirationDate, int status,
		OrderByComparator<CommerceDiscount> orderByComparator) {
		List<CommerceDiscount> list = findByLtE_S(expirationDate, status, 0, 1,
				orderByComparator);

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
	public CommerceDiscount findByLtE_S_Last(Date expirationDate, int status,
		OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {
		CommerceDiscount commerceDiscount = fetchByLtE_S_Last(expirationDate,
				status, orderByComparator);

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
	public CommerceDiscount fetchByLtE_S_Last(Date expirationDate, int status,
		OrderByComparator<CommerceDiscount> orderByComparator) {
		int count = countByLtE_S(expirationDate, status);

		if (count == 0) {
			return null;
		}

		List<CommerceDiscount> list = findByLtE_S(expirationDate, status,
				count - 1, count, orderByComparator);

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
	public CommerceDiscount[] findByLtE_S_PrevAndNext(long commerceDiscountId,
		Date expirationDate, int status,
		OrderByComparator<CommerceDiscount> orderByComparator)
		throws NoSuchDiscountException {
		CommerceDiscount commerceDiscount = findByPrimaryKey(commerceDiscountId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscount[] array = new CommerceDiscountImpl[3];

			array[0] = getByLtE_S_PrevAndNext(session, commerceDiscount,
					expirationDate, status, orderByComparator, true);

			array[1] = commerceDiscount;

			array[2] = getByLtE_S_PrevAndNext(session, commerceDiscount,
					expirationDate, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceDiscount getByLtE_S_PrevAndNext(Session session,
		CommerceDiscount commerceDiscount, Date expirationDate, int status,
		OrderByComparator<CommerceDiscount> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
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
			Object[] values = orderByComparator.getOrderByConditionValues(commerceDiscount);

			for (Object value : values) {
				qPos.add(value);
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
		for (CommerceDiscount commerceDiscount : findByLtE_S(expirationDate,
				status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
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
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_LTE_S;

		Object[] finderArgs = new Object[] { _getTime(expirationDate), status };

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

	private static final String _FINDER_COLUMN_LTE_S_EXPIRATIONDATE_1 = "commerceDiscount.expirationDate IS NULL AND ";
	private static final String _FINDER_COLUMN_LTE_S_EXPIRATIONDATE_2 = "commerceDiscount.expirationDate < ? AND ";
	private static final String _FINDER_COLUMN_LTE_S_STATUS_2 = "commerceDiscount.status = ?";

	public CommerceDiscountPersistenceImpl() {
		setModelClass(CommerceDiscount.class);

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
	 * Caches the commerce discount in the entity cache if it is enabled.
	 *
	 * @param commerceDiscount the commerce discount
	 */
	@Override
	public void cacheResult(CommerceDiscount commerceDiscount) {
		entityCache.putResult(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountImpl.class, commerceDiscount.getPrimaryKey(),
			commerceDiscount);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				commerceDiscount.getUuid(), commerceDiscount.getGroupId()
			}, commerceDiscount);

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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceDiscount commerceDiscount) {
		entityCache.removeResult(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountImpl.class, commerceDiscount.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CommerceDiscountModelImpl)commerceDiscount,
			true);
	}

	@Override
	public void clearCache(List<CommerceDiscount> commerceDiscounts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceDiscount commerceDiscount : commerceDiscounts) {
			entityCache.removeResult(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
				CommerceDiscountImpl.class, commerceDiscount.getPrimaryKey());

			clearUniqueFindersCache((CommerceDiscountModelImpl)commerceDiscount,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		CommerceDiscountModelImpl commerceDiscountModelImpl) {
		Object[] args = new Object[] {
				commerceDiscountModelImpl.getUuid(),
				commerceDiscountModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			commerceDiscountModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CommerceDiscountModelImpl commerceDiscountModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					commerceDiscountModelImpl.getUuid(),
					commerceDiscountModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((commerceDiscountModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					commerceDiscountModelImpl.getOriginalUuid(),
					commerceDiscountModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
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

		commerceDiscount.setCompanyId(companyProvider.getCompanyId());

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

			CommerceDiscount commerceDiscount = (CommerceDiscount)session.get(CommerceDiscountImpl.class,
					primaryKey);

			if (commerceDiscount == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
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
				commerceDiscount = (CommerceDiscount)session.get(CommerceDiscountImpl.class,
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
				invocationHandler = ProxyUtil.getInvocationHandler(commerceDiscount);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceDiscount proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceDiscount implementation " +
				commerceDiscount.getClass());
		}

		CommerceDiscountModelImpl commerceDiscountModelImpl = (CommerceDiscountModelImpl)commerceDiscount;

		if (Validator.isNull(commerceDiscount.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			commerceDiscount.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceDiscount.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceDiscount.setCreateDate(now);
			}
			else {
				commerceDiscount.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!commerceDiscountModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceDiscount.setModifiedDate(now);
			}
			else {
				commerceDiscount.setModifiedDate(serviceContext.getModifiedDate(
						now));
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
				commerceDiscount = (CommerceDiscount)session.merge(commerceDiscount);
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
		else
		 if (isNew) {
			Object[] args = new Object[] { commerceDiscountModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					commerceDiscountModelImpl.getUuid(),
					commerceDiscountModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { commerceDiscountModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] {
					commerceDiscountModelImpl.getGroupId(),
					commerceDiscountModelImpl.getCouponCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((commerceDiscountModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceDiscountModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { commerceDiscountModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((commerceDiscountModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceDiscountModelImpl.getOriginalUuid(),
						commerceDiscountModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						commerceDiscountModelImpl.getUuid(),
						commerceDiscountModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((commerceDiscountModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceDiscountModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { commerceDiscountModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((commerceDiscountModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceDiscountModelImpl.getOriginalGroupId(),
						commerceDiscountModelImpl.getOriginalCouponCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C,
					args);

				args = new Object[] {
						commerceDiscountModelImpl.getGroupId(),
						commerceDiscountModelImpl.getCouponCode()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C,
					args);
			}
		}

		entityCache.putResult(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountImpl.class, commerceDiscount.getPrimaryKey(),
			commerceDiscount, false);

		clearUniqueFindersCache(commerceDiscountModelImpl, false);
		cacheUniqueFindersCache(commerceDiscountModelImpl);

		commerceDiscount.resetOriginalValues();

		return commerceDiscount;
	}

	/**
	 * Returns the commerce discount with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
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

			throw new NoSuchDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commerceDiscount;
	}

	/**
	 * Returns the commerce discount with the primary key or throws a {@link NoSuchDiscountException} if it could not be found.
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
		Serializable serializable = entityCache.getResult(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
				CommerceDiscountImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceDiscount commerceDiscount = (CommerceDiscount)serializable;

		if (commerceDiscount == null) {
			Session session = null;

			try {
				session = openSession();

				commerceDiscount = (CommerceDiscount)session.get(CommerceDiscountImpl.class,
						primaryKey);

				if (commerceDiscount != null) {
					cacheResult(commerceDiscount);
				}
				else {
					entityCache.putResult(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
						CommerceDiscountImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
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

		Map<Serializable, CommerceDiscount> map = new HashMap<Serializable, CommerceDiscount>();

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
			Serializable serializable = entityCache.getResult(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
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

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

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

			for (CommerceDiscount commerceDiscount : (List<CommerceDiscount>)q.list()) {
				map.put(commerceDiscount.getPrimaryKeyObj(), commerceDiscount);

				cacheResult(commerceDiscount);

				uncachedPrimaryKeys.remove(commerceDiscount.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CommerceDiscountModelImpl.ENTITY_CACHE_ENABLED,
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findAll(int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce discounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce discounts
	 * @param end the upper bound of the range of commerce discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce discounts
	 */
	@Override
	public List<CommerceDiscount> findAll(int start, int end,
		OrderByComparator<CommerceDiscount> orderByComparator,
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

		List<CommerceDiscount> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceDiscount>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEDISCOUNT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

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
					list = (List<CommerceDiscount>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceDiscount>)QueryUtil.list(q,
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
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCEDISCOUNT);

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
		return CommerceDiscountModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce discount persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CommerceDiscountImpl.class.getName());
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

	private static final String _SQL_SELECT_COMMERCEDISCOUNT = "SELECT commerceDiscount FROM CommerceDiscount commerceDiscount";
	private static final String _SQL_SELECT_COMMERCEDISCOUNT_WHERE_PKS_IN = "SELECT commerceDiscount FROM CommerceDiscount commerceDiscount WHERE commerceDiscountId IN (";
	private static final String _SQL_SELECT_COMMERCEDISCOUNT_WHERE = "SELECT commerceDiscount FROM CommerceDiscount commerceDiscount WHERE ";
	private static final String _SQL_COUNT_COMMERCEDISCOUNT = "SELECT COUNT(commerceDiscount) FROM CommerceDiscount commerceDiscount";
	private static final String _SQL_COUNT_COMMERCEDISCOUNT_WHERE = "SELECT COUNT(commerceDiscount) FROM CommerceDiscount commerceDiscount WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "commerceDiscount.commerceDiscountId";
	private static final String _FILTER_SQL_SELECT_COMMERCEDISCOUNT_WHERE = "SELECT DISTINCT {commerceDiscount.*} FROM CommerceDiscount commerceDiscount WHERE ";
	private static final String _FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {CommerceDiscount.*} FROM (SELECT DISTINCT commerceDiscount.commerceDiscountId FROM CommerceDiscount commerceDiscount WHERE ";
	private static final String _FILTER_SQL_SELECT_COMMERCEDISCOUNT_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN CommerceDiscount ON TEMP_TABLE.commerceDiscountId = CommerceDiscount.commerceDiscountId";
	private static final String _FILTER_SQL_COUNT_COMMERCEDISCOUNT_WHERE = "SELECT COUNT(DISTINCT commerceDiscount.commerceDiscountId) AS COUNT_VALUE FROM CommerceDiscount commerceDiscount WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "commerceDiscount";
	private static final String _FILTER_ENTITY_TABLE = "CommerceDiscount";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceDiscount.";
	private static final String _ORDER_BY_ENTITY_TABLE = "CommerceDiscount.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommerceDiscount exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CommerceDiscount exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CommerceDiscountPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "active"
			});
}