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

package com.liferay.commerce.price.list.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.price.list.exception.NoSuchPriceListAccountRelException;
import com.liferay.commerce.price.list.model.CommercePriceListAccountRel;
import com.liferay.commerce.price.list.model.impl.CommercePriceListAccountRelImpl;
import com.liferay.commerce.price.list.model.impl.CommercePriceListAccountRelModelImpl;
import com.liferay.commerce.price.list.service.persistence.CommercePriceListAccountRelPersistence;
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
 * The persistence implementation for the commerce price list account rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
@ProviderType
public class CommercePriceListAccountRelPersistenceImpl
	extends BasePersistenceImpl<CommercePriceListAccountRel>
	implements CommercePriceListAccountRelPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommercePriceListAccountRelUtil</code> to access the commerce price list account rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommercePriceListAccountRelImpl.class.getName();

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
	 * Returns all the commerce price list account rels where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching commerce price list account rels
	 */
	@Override
	public List<CommercePriceListAccountRel> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce price list account rels where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePriceListAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce price list account rels
	 * @param end the upper bound of the range of commerce price list account rels (not inclusive)
	 * @return the range of matching commerce price list account rels
	 */
	@Override
	public List<CommercePriceListAccountRel> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce price list account rels where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePriceListAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce price list account rels
	 * @param end the upper bound of the range of commerce price list account rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce price list account rels
	 */
	@Override
	public List<CommercePriceListAccountRel> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommercePriceListAccountRel> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce price list account rels where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePriceListAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce price list account rels
	 * @param end the upper bound of the range of commerce price list account rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce price list account rels
	 */
	@Override
	public List<CommercePriceListAccountRel> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommercePriceListAccountRel> orderByComparator,
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

		List<CommercePriceListAccountRel> list = null;

		if (retrieveFromCache) {
			list = (List<CommercePriceListAccountRel>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommercePriceListAccountRel commercePriceListAccountRel :
						list) {

					if (!uuid.equals(commercePriceListAccountRel.getUuid())) {
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

			query.append(_SQL_SELECT_COMMERCEPRICELISTACCOUNTREL_WHERE);

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
				query.append(
					CommercePriceListAccountRelModelImpl.ORDER_BY_JPQL);
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
					list = (List<CommercePriceListAccountRel>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommercePriceListAccountRel>)QueryUtil.list(
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
	 * Returns the first commerce price list account rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price list account rel
	 * @throws NoSuchPriceListAccountRelException if a matching commerce price list account rel could not be found
	 */
	@Override
	public CommercePriceListAccountRel findByUuid_First(
			String uuid,
			OrderByComparator<CommercePriceListAccountRel> orderByComparator)
		throws NoSuchPriceListAccountRelException {

		CommercePriceListAccountRel commercePriceListAccountRel =
			fetchByUuid_First(uuid, orderByComparator);

		if (commercePriceListAccountRel != null) {
			return commercePriceListAccountRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPriceListAccountRelException(msg.toString());
	}

	/**
	 * Returns the first commerce price list account rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price list account rel, or <code>null</code> if a matching commerce price list account rel could not be found
	 */
	@Override
	public CommercePriceListAccountRel fetchByUuid_First(
		String uuid,
		OrderByComparator<CommercePriceListAccountRel> orderByComparator) {

		List<CommercePriceListAccountRel> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce price list account rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price list account rel
	 * @throws NoSuchPriceListAccountRelException if a matching commerce price list account rel could not be found
	 */
	@Override
	public CommercePriceListAccountRel findByUuid_Last(
			String uuid,
			OrderByComparator<CommercePriceListAccountRel> orderByComparator)
		throws NoSuchPriceListAccountRelException {

		CommercePriceListAccountRel commercePriceListAccountRel =
			fetchByUuid_Last(uuid, orderByComparator);

		if (commercePriceListAccountRel != null) {
			return commercePriceListAccountRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPriceListAccountRelException(msg.toString());
	}

	/**
	 * Returns the last commerce price list account rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price list account rel, or <code>null</code> if a matching commerce price list account rel could not be found
	 */
	@Override
	public CommercePriceListAccountRel fetchByUuid_Last(
		String uuid,
		OrderByComparator<CommercePriceListAccountRel> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CommercePriceListAccountRel> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce price list account rels before and after the current commerce price list account rel in the ordered set where uuid = &#63;.
	 *
	 * @param commercePriceListAccountRelId the primary key of the current commerce price list account rel
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce price list account rel
	 * @throws NoSuchPriceListAccountRelException if a commerce price list account rel with the primary key could not be found
	 */
	@Override
	public CommercePriceListAccountRel[] findByUuid_PrevAndNext(
			long commercePriceListAccountRelId, String uuid,
			OrderByComparator<CommercePriceListAccountRel> orderByComparator)
		throws NoSuchPriceListAccountRelException {

		uuid = Objects.toString(uuid, "");

		CommercePriceListAccountRel commercePriceListAccountRel =
			findByPrimaryKey(commercePriceListAccountRelId);

		Session session = null;

		try {
			session = openSession();

			CommercePriceListAccountRel[] array =
				new CommercePriceListAccountRelImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, commercePriceListAccountRel, uuid, orderByComparator,
				true);

			array[1] = commercePriceListAccountRel;

			array[2] = getByUuid_PrevAndNext(
				session, commercePriceListAccountRel, uuid, orderByComparator,
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

	protected CommercePriceListAccountRel getByUuid_PrevAndNext(
		Session session,
		CommercePriceListAccountRel commercePriceListAccountRel, String uuid,
		OrderByComparator<CommercePriceListAccountRel> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEPRICELISTACCOUNTREL_WHERE);

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
			query.append(CommercePriceListAccountRelModelImpl.ORDER_BY_JPQL);
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
						commercePriceListAccountRel)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommercePriceListAccountRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce price list account rels where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CommercePriceListAccountRel commercePriceListAccountRel :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commercePriceListAccountRel);
		}
	}

	/**
	 * Returns the number of commerce price list account rels where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching commerce price list account rels
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEPRICELISTACCOUNTREL_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"commercePriceListAccountRel.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(commercePriceListAccountRel.uuid IS NULL OR commercePriceListAccountRel.uuid = '')";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the commerce price list account rels where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching commerce price list account rels
	 */
	@Override
	public List<CommercePriceListAccountRel> findByUuid_C(
		String uuid, long companyId) {

		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce price list account rels where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePriceListAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce price list account rels
	 * @param end the upper bound of the range of commerce price list account rels (not inclusive)
	 * @return the range of matching commerce price list account rels
	 */
	@Override
	public List<CommercePriceListAccountRel> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce price list account rels where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePriceListAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce price list account rels
	 * @param end the upper bound of the range of commerce price list account rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce price list account rels
	 */
	@Override
	public List<CommercePriceListAccountRel> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommercePriceListAccountRel> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce price list account rels where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePriceListAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce price list account rels
	 * @param end the upper bound of the range of commerce price list account rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce price list account rels
	 */
	@Override
	public List<CommercePriceListAccountRel> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommercePriceListAccountRel> orderByComparator,
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

		List<CommercePriceListAccountRel> list = null;

		if (retrieveFromCache) {
			list = (List<CommercePriceListAccountRel>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommercePriceListAccountRel commercePriceListAccountRel :
						list) {

					if (!uuid.equals(commercePriceListAccountRel.getUuid()) ||
						(companyId !=
							commercePriceListAccountRel.getCompanyId())) {

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

			query.append(_SQL_SELECT_COMMERCEPRICELISTACCOUNTREL_WHERE);

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
				query.append(
					CommercePriceListAccountRelModelImpl.ORDER_BY_JPQL);
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
					list = (List<CommercePriceListAccountRel>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommercePriceListAccountRel>)QueryUtil.list(
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
	 * Returns the first commerce price list account rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price list account rel
	 * @throws NoSuchPriceListAccountRelException if a matching commerce price list account rel could not be found
	 */
	@Override
	public CommercePriceListAccountRel findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CommercePriceListAccountRel> orderByComparator)
		throws NoSuchPriceListAccountRelException {

		CommercePriceListAccountRel commercePriceListAccountRel =
			fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (commercePriceListAccountRel != null) {
			return commercePriceListAccountRel;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchPriceListAccountRelException(msg.toString());
	}

	/**
	 * Returns the first commerce price list account rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price list account rel, or <code>null</code> if a matching commerce price list account rel could not be found
	 */
	@Override
	public CommercePriceListAccountRel fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CommercePriceListAccountRel> orderByComparator) {

		List<CommercePriceListAccountRel> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce price list account rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price list account rel
	 * @throws NoSuchPriceListAccountRelException if a matching commerce price list account rel could not be found
	 */
	@Override
	public CommercePriceListAccountRel findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CommercePriceListAccountRel> orderByComparator)
		throws NoSuchPriceListAccountRelException {

		CommercePriceListAccountRel commercePriceListAccountRel =
			fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (commercePriceListAccountRel != null) {
			return commercePriceListAccountRel;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchPriceListAccountRelException(msg.toString());
	}

	/**
	 * Returns the last commerce price list account rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price list account rel, or <code>null</code> if a matching commerce price list account rel could not be found
	 */
	@Override
	public CommercePriceListAccountRel fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CommercePriceListAccountRel> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CommercePriceListAccountRel> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce price list account rels before and after the current commerce price list account rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param commercePriceListAccountRelId the primary key of the current commerce price list account rel
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce price list account rel
	 * @throws NoSuchPriceListAccountRelException if a commerce price list account rel with the primary key could not be found
	 */
	@Override
	public CommercePriceListAccountRel[] findByUuid_C_PrevAndNext(
			long commercePriceListAccountRelId, String uuid, long companyId,
			OrderByComparator<CommercePriceListAccountRel> orderByComparator)
		throws NoSuchPriceListAccountRelException {

		uuid = Objects.toString(uuid, "");

		CommercePriceListAccountRel commercePriceListAccountRel =
			findByPrimaryKey(commercePriceListAccountRelId);

		Session session = null;

		try {
			session = openSession();

			CommercePriceListAccountRel[] array =
				new CommercePriceListAccountRelImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, commercePriceListAccountRel, uuid, companyId,
				orderByComparator, true);

			array[1] = commercePriceListAccountRel;

			array[2] = getByUuid_C_PrevAndNext(
				session, commercePriceListAccountRel, uuid, companyId,
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

	protected CommercePriceListAccountRel getByUuid_C_PrevAndNext(
		Session session,
		CommercePriceListAccountRel commercePriceListAccountRel, String uuid,
		long companyId,
		OrderByComparator<CommercePriceListAccountRel> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEPRICELISTACCOUNTREL_WHERE);

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
			query.append(CommercePriceListAccountRelModelImpl.ORDER_BY_JPQL);
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
						commercePriceListAccountRel)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommercePriceListAccountRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce price list account rels where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CommercePriceListAccountRel commercePriceListAccountRel :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commercePriceListAccountRel);
		}
	}

	/**
	 * Returns the number of commerce price list account rels where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching commerce price list account rels
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEPRICELISTACCOUNTREL_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"commercePriceListAccountRel.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(commercePriceListAccountRel.uuid IS NULL OR commercePriceListAccountRel.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"commercePriceListAccountRel.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByCommercePriceListId;
	private FinderPath _finderPathWithoutPaginationFindByCommercePriceListId;
	private FinderPath _finderPathCountByCommercePriceListId;

	/**
	 * Returns all the commerce price list account rels where commercePriceListId = &#63;.
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @return the matching commerce price list account rels
	 */
	@Override
	public List<CommercePriceListAccountRel> findByCommercePriceListId(
		long commercePriceListId) {

		return findByCommercePriceListId(
			commercePriceListId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce price list account rels where commercePriceListId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePriceListAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @param start the lower bound of the range of commerce price list account rels
	 * @param end the upper bound of the range of commerce price list account rels (not inclusive)
	 * @return the range of matching commerce price list account rels
	 */
	@Override
	public List<CommercePriceListAccountRel> findByCommercePriceListId(
		long commercePriceListId, int start, int end) {

		return findByCommercePriceListId(commercePriceListId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce price list account rels where commercePriceListId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePriceListAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @param start the lower bound of the range of commerce price list account rels
	 * @param end the upper bound of the range of commerce price list account rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce price list account rels
	 */
	@Override
	public List<CommercePriceListAccountRel> findByCommercePriceListId(
		long commercePriceListId, int start, int end,
		OrderByComparator<CommercePriceListAccountRel> orderByComparator) {

		return findByCommercePriceListId(
			commercePriceListId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce price list account rels where commercePriceListId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePriceListAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @param start the lower bound of the range of commerce price list account rels
	 * @param end the upper bound of the range of commerce price list account rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce price list account rels
	 */
	@Override
	public List<CommercePriceListAccountRel> findByCommercePriceListId(
		long commercePriceListId, int start, int end,
		OrderByComparator<CommercePriceListAccountRel> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByCommercePriceListId;
			finderArgs = new Object[] {commercePriceListId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByCommercePriceListId;
			finderArgs = new Object[] {
				commercePriceListId, start, end, orderByComparator
			};
		}

		List<CommercePriceListAccountRel> list = null;

		if (retrieveFromCache) {
			list = (List<CommercePriceListAccountRel>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommercePriceListAccountRel commercePriceListAccountRel :
						list) {

					if ((commercePriceListId !=
							commercePriceListAccountRel.
								getCommercePriceListId())) {

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

			query.append(_SQL_SELECT_COMMERCEPRICELISTACCOUNTREL_WHERE);

			query.append(
				_FINDER_COLUMN_COMMERCEPRICELISTID_COMMERCEPRICELISTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(
					CommercePriceListAccountRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commercePriceListId);

				if (!pagination) {
					list = (List<CommercePriceListAccountRel>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommercePriceListAccountRel>)QueryUtil.list(
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
	 * Returns the first commerce price list account rel in the ordered set where commercePriceListId = &#63;.
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price list account rel
	 * @throws NoSuchPriceListAccountRelException if a matching commerce price list account rel could not be found
	 */
	@Override
	public CommercePriceListAccountRel findByCommercePriceListId_First(
			long commercePriceListId,
			OrderByComparator<CommercePriceListAccountRel> orderByComparator)
		throws NoSuchPriceListAccountRelException {

		CommercePriceListAccountRel commercePriceListAccountRel =
			fetchByCommercePriceListId_First(
				commercePriceListId, orderByComparator);

		if (commercePriceListAccountRel != null) {
			return commercePriceListAccountRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commercePriceListId=");
		msg.append(commercePriceListId);

		msg.append("}");

		throw new NoSuchPriceListAccountRelException(msg.toString());
	}

	/**
	 * Returns the first commerce price list account rel in the ordered set where commercePriceListId = &#63;.
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce price list account rel, or <code>null</code> if a matching commerce price list account rel could not be found
	 */
	@Override
	public CommercePriceListAccountRel fetchByCommercePriceListId_First(
		long commercePriceListId,
		OrderByComparator<CommercePriceListAccountRel> orderByComparator) {

		List<CommercePriceListAccountRel> list = findByCommercePriceListId(
			commercePriceListId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce price list account rel in the ordered set where commercePriceListId = &#63;.
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price list account rel
	 * @throws NoSuchPriceListAccountRelException if a matching commerce price list account rel could not be found
	 */
	@Override
	public CommercePriceListAccountRel findByCommercePriceListId_Last(
			long commercePriceListId,
			OrderByComparator<CommercePriceListAccountRel> orderByComparator)
		throws NoSuchPriceListAccountRelException {

		CommercePriceListAccountRel commercePriceListAccountRel =
			fetchByCommercePriceListId_Last(
				commercePriceListId, orderByComparator);

		if (commercePriceListAccountRel != null) {
			return commercePriceListAccountRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commercePriceListId=");
		msg.append(commercePriceListId);

		msg.append("}");

		throw new NoSuchPriceListAccountRelException(msg.toString());
	}

	/**
	 * Returns the last commerce price list account rel in the ordered set where commercePriceListId = &#63;.
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce price list account rel, or <code>null</code> if a matching commerce price list account rel could not be found
	 */
	@Override
	public CommercePriceListAccountRel fetchByCommercePriceListId_Last(
		long commercePriceListId,
		OrderByComparator<CommercePriceListAccountRel> orderByComparator) {

		int count = countByCommercePriceListId(commercePriceListId);

		if (count == 0) {
			return null;
		}

		List<CommercePriceListAccountRel> list = findByCommercePriceListId(
			commercePriceListId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce price list account rels before and after the current commerce price list account rel in the ordered set where commercePriceListId = &#63;.
	 *
	 * @param commercePriceListAccountRelId the primary key of the current commerce price list account rel
	 * @param commercePriceListId the commerce price list ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce price list account rel
	 * @throws NoSuchPriceListAccountRelException if a commerce price list account rel with the primary key could not be found
	 */
	@Override
	public CommercePriceListAccountRel[] findByCommercePriceListId_PrevAndNext(
			long commercePriceListAccountRelId, long commercePriceListId,
			OrderByComparator<CommercePriceListAccountRel> orderByComparator)
		throws NoSuchPriceListAccountRelException {

		CommercePriceListAccountRel commercePriceListAccountRel =
			findByPrimaryKey(commercePriceListAccountRelId);

		Session session = null;

		try {
			session = openSession();

			CommercePriceListAccountRel[] array =
				new CommercePriceListAccountRelImpl[3];

			array[0] = getByCommercePriceListId_PrevAndNext(
				session, commercePriceListAccountRel, commercePriceListId,
				orderByComparator, true);

			array[1] = commercePriceListAccountRel;

			array[2] = getByCommercePriceListId_PrevAndNext(
				session, commercePriceListAccountRel, commercePriceListId,
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

	protected CommercePriceListAccountRel getByCommercePriceListId_PrevAndNext(
		Session session,
		CommercePriceListAccountRel commercePriceListAccountRel,
		long commercePriceListId,
		OrderByComparator<CommercePriceListAccountRel> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEPRICELISTACCOUNTREL_WHERE);

		query.append(_FINDER_COLUMN_COMMERCEPRICELISTID_COMMERCEPRICELISTID_2);

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
			query.append(CommercePriceListAccountRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commercePriceListId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commercePriceListAccountRel)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommercePriceListAccountRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce price list account rels where commercePriceListId = &#63; from the database.
	 *
	 * @param commercePriceListId the commerce price list ID
	 */
	@Override
	public void removeByCommercePriceListId(long commercePriceListId) {
		for (CommercePriceListAccountRel commercePriceListAccountRel :
				findByCommercePriceListId(
					commercePriceListId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commercePriceListAccountRel);
		}
	}

	/**
	 * Returns the number of commerce price list account rels where commercePriceListId = &#63;.
	 *
	 * @param commercePriceListId the commerce price list ID
	 * @return the number of matching commerce price list account rels
	 */
	@Override
	public int countByCommercePriceListId(long commercePriceListId) {
		FinderPath finderPath = _finderPathCountByCommercePriceListId;

		Object[] finderArgs = new Object[] {commercePriceListId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEPRICELISTACCOUNTREL_WHERE);

			query.append(
				_FINDER_COLUMN_COMMERCEPRICELISTID_COMMERCEPRICELISTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commercePriceListId);

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
		_FINDER_COLUMN_COMMERCEPRICELISTID_COMMERCEPRICELISTID_2 =
			"commercePriceListAccountRel.commercePriceListId = ?";

	private FinderPath _finderPathFetchByC_C;
	private FinderPath _finderPathCountByC_C;

	/**
	 * Returns the commerce price list account rel where commerceAccountId = &#63; and commercePriceListId = &#63; or throws a <code>NoSuchPriceListAccountRelException</code> if it could not be found.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param commercePriceListId the commerce price list ID
	 * @return the matching commerce price list account rel
	 * @throws NoSuchPriceListAccountRelException if a matching commerce price list account rel could not be found
	 */
	@Override
	public CommercePriceListAccountRel findByC_C(
			long commerceAccountId, long commercePriceListId)
		throws NoSuchPriceListAccountRelException {

		CommercePriceListAccountRel commercePriceListAccountRel = fetchByC_C(
			commerceAccountId, commercePriceListId);

		if (commercePriceListAccountRel == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("commerceAccountId=");
			msg.append(commerceAccountId);

			msg.append(", commercePriceListId=");
			msg.append(commercePriceListId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchPriceListAccountRelException(msg.toString());
		}

		return commercePriceListAccountRel;
	}

	/**
	 * Returns the commerce price list account rel where commerceAccountId = &#63; and commercePriceListId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param commercePriceListId the commerce price list ID
	 * @return the matching commerce price list account rel, or <code>null</code> if a matching commerce price list account rel could not be found
	 */
	@Override
	public CommercePriceListAccountRel fetchByC_C(
		long commerceAccountId, long commercePriceListId) {

		return fetchByC_C(commerceAccountId, commercePriceListId, true);
	}

	/**
	 * Returns the commerce price list account rel where commerceAccountId = &#63; and commercePriceListId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param commercePriceListId the commerce price list ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching commerce price list account rel, or <code>null</code> if a matching commerce price list account rel could not be found
	 */
	@Override
	public CommercePriceListAccountRel fetchByC_C(
		long commerceAccountId, long commercePriceListId,
		boolean retrieveFromCache) {

		Object[] finderArgs = new Object[] {
			commerceAccountId, commercePriceListId
		};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_C, finderArgs, this);
		}

		if (result instanceof CommercePriceListAccountRel) {
			CommercePriceListAccountRel commercePriceListAccountRel =
				(CommercePriceListAccountRel)result;

			if ((commerceAccountId !=
					commercePriceListAccountRel.getCommerceAccountId()) ||
				(commercePriceListId !=
					commercePriceListAccountRel.getCommercePriceListId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COMMERCEPRICELISTACCOUNTREL_WHERE);

			query.append(_FINDER_COLUMN_C_C_COMMERCEACCOUNTID_2);

			query.append(_FINDER_COLUMN_C_C_COMMERCEPRICELISTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceAccountId);

				qPos.add(commercePriceListId);

				List<CommercePriceListAccountRel> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByC_C, finderArgs, list);
				}
				else {
					CommercePriceListAccountRel commercePriceListAccountRel =
						list.get(0);

					result = commercePriceListAccountRel;

					cacheResult(commercePriceListAccountRel);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByC_C, finderArgs);

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
			return (CommercePriceListAccountRel)result;
		}
	}

	/**
	 * Removes the commerce price list account rel where commerceAccountId = &#63; and commercePriceListId = &#63; from the database.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param commercePriceListId the commerce price list ID
	 * @return the commerce price list account rel that was removed
	 */
	@Override
	public CommercePriceListAccountRel removeByC_C(
			long commerceAccountId, long commercePriceListId)
		throws NoSuchPriceListAccountRelException {

		CommercePriceListAccountRel commercePriceListAccountRel = findByC_C(
			commerceAccountId, commercePriceListId);

		return remove(commercePriceListAccountRel);
	}

	/**
	 * Returns the number of commerce price list account rels where commerceAccountId = &#63; and commercePriceListId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param commercePriceListId the commerce price list ID
	 * @return the number of matching commerce price list account rels
	 */
	@Override
	public int countByC_C(long commerceAccountId, long commercePriceListId) {
		FinderPath finderPath = _finderPathCountByC_C;

		Object[] finderArgs = new Object[] {
			commerceAccountId, commercePriceListId
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEPRICELISTACCOUNTREL_WHERE);

			query.append(_FINDER_COLUMN_C_C_COMMERCEACCOUNTID_2);

			query.append(_FINDER_COLUMN_C_C_COMMERCEPRICELISTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceAccountId);

				qPos.add(commercePriceListId);

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

	private static final String _FINDER_COLUMN_C_C_COMMERCEACCOUNTID_2 =
		"commercePriceListAccountRel.commerceAccountId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_COMMERCEPRICELISTID_2 =
		"commercePriceListAccountRel.commercePriceListId = ?";

	public CommercePriceListAccountRelPersistenceImpl() {
		setModelClass(CommercePriceListAccountRel.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("order", "order_");

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
	 * Caches the commerce price list account rel in the entity cache if it is enabled.
	 *
	 * @param commercePriceListAccountRel the commerce price list account rel
	 */
	@Override
	public void cacheResult(
		CommercePriceListAccountRel commercePriceListAccountRel) {

		entityCache.putResult(
			CommercePriceListAccountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePriceListAccountRelImpl.class,
			commercePriceListAccountRel.getPrimaryKey(),
			commercePriceListAccountRel);

		finderCache.putResult(
			_finderPathFetchByC_C,
			new Object[] {
				commercePriceListAccountRel.getCommerceAccountId(),
				commercePriceListAccountRel.getCommercePriceListId()
			},
			commercePriceListAccountRel);

		commercePriceListAccountRel.resetOriginalValues();
	}

	/**
	 * Caches the commerce price list account rels in the entity cache if it is enabled.
	 *
	 * @param commercePriceListAccountRels the commerce price list account rels
	 */
	@Override
	public void cacheResult(
		List<CommercePriceListAccountRel> commercePriceListAccountRels) {

		for (CommercePriceListAccountRel commercePriceListAccountRel :
				commercePriceListAccountRels) {

			if (entityCache.getResult(
					CommercePriceListAccountRelModelImpl.ENTITY_CACHE_ENABLED,
					CommercePriceListAccountRelImpl.class,
					commercePriceListAccountRel.getPrimaryKey()) == null) {

				cacheResult(commercePriceListAccountRel);
			}
			else {
				commercePriceListAccountRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce price list account rels.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommercePriceListAccountRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce price list account rel.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CommercePriceListAccountRel commercePriceListAccountRel) {

		entityCache.removeResult(
			CommercePriceListAccountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePriceListAccountRelImpl.class,
			commercePriceListAccountRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(CommercePriceListAccountRelModelImpl)commercePriceListAccountRel,
			true);
	}

	@Override
	public void clearCache(
		List<CommercePriceListAccountRel> commercePriceListAccountRels) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommercePriceListAccountRel commercePriceListAccountRel :
				commercePriceListAccountRels) {

			entityCache.removeResult(
				CommercePriceListAccountRelModelImpl.ENTITY_CACHE_ENABLED,
				CommercePriceListAccountRelImpl.class,
				commercePriceListAccountRel.getPrimaryKey());

			clearUniqueFindersCache(
				(CommercePriceListAccountRelModelImpl)
					commercePriceListAccountRel,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		CommercePriceListAccountRelModelImpl
			commercePriceListAccountRelModelImpl) {

		Object[] args = new Object[] {
			commercePriceListAccountRelModelImpl.getCommerceAccountId(),
			commercePriceListAccountRelModelImpl.getCommercePriceListId()
		};

		finderCache.putResult(
			_finderPathCountByC_C, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_C, args, commercePriceListAccountRelModelImpl,
			false);
	}

	protected void clearUniqueFindersCache(
		CommercePriceListAccountRelModelImpl
			commercePriceListAccountRelModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				commercePriceListAccountRelModelImpl.getCommerceAccountId(),
				commercePriceListAccountRelModelImpl.getCommercePriceListId()
			};

			finderCache.removeResult(_finderPathCountByC_C, args);
			finderCache.removeResult(_finderPathFetchByC_C, args);
		}

		if ((commercePriceListAccountRelModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_C.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				commercePriceListAccountRelModelImpl.
					getOriginalCommerceAccountId(),
				commercePriceListAccountRelModelImpl.
					getOriginalCommercePriceListId()
			};

			finderCache.removeResult(_finderPathCountByC_C, args);
			finderCache.removeResult(_finderPathFetchByC_C, args);
		}
	}

	/**
	 * Creates a new commerce price list account rel with the primary key. Does not add the commerce price list account rel to the database.
	 *
	 * @param commercePriceListAccountRelId the primary key for the new commerce price list account rel
	 * @return the new commerce price list account rel
	 */
	@Override
	public CommercePriceListAccountRel create(
		long commercePriceListAccountRelId) {

		CommercePriceListAccountRel commercePriceListAccountRel =
			new CommercePriceListAccountRelImpl();

		commercePriceListAccountRel.setNew(true);
		commercePriceListAccountRel.setPrimaryKey(
			commercePriceListAccountRelId);

		String uuid = PortalUUIDUtil.generate();

		commercePriceListAccountRel.setUuid(uuid);

		commercePriceListAccountRel.setCompanyId(
			CompanyThreadLocal.getCompanyId());

		return commercePriceListAccountRel;
	}

	/**
	 * Removes the commerce price list account rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commercePriceListAccountRelId the primary key of the commerce price list account rel
	 * @return the commerce price list account rel that was removed
	 * @throws NoSuchPriceListAccountRelException if a commerce price list account rel with the primary key could not be found
	 */
	@Override
	public CommercePriceListAccountRel remove(
			long commercePriceListAccountRelId)
		throws NoSuchPriceListAccountRelException {

		return remove((Serializable)commercePriceListAccountRelId);
	}

	/**
	 * Removes the commerce price list account rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce price list account rel
	 * @return the commerce price list account rel that was removed
	 * @throws NoSuchPriceListAccountRelException if a commerce price list account rel with the primary key could not be found
	 */
	@Override
	public CommercePriceListAccountRel remove(Serializable primaryKey)
		throws NoSuchPriceListAccountRelException {

		Session session = null;

		try {
			session = openSession();

			CommercePriceListAccountRel commercePriceListAccountRel =
				(CommercePriceListAccountRel)session.get(
					CommercePriceListAccountRelImpl.class, primaryKey);

			if (commercePriceListAccountRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPriceListAccountRelException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commercePriceListAccountRel);
		}
		catch (NoSuchPriceListAccountRelException nsee) {
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
	protected CommercePriceListAccountRel removeImpl(
		CommercePriceListAccountRel commercePriceListAccountRel) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commercePriceListAccountRel)) {
				commercePriceListAccountRel =
					(CommercePriceListAccountRel)session.get(
						CommercePriceListAccountRelImpl.class,
						commercePriceListAccountRel.getPrimaryKeyObj());
			}

			if (commercePriceListAccountRel != null) {
				session.delete(commercePriceListAccountRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commercePriceListAccountRel != null) {
			clearCache(commercePriceListAccountRel);
		}

		return commercePriceListAccountRel;
	}

	@Override
	public CommercePriceListAccountRel updateImpl(
		CommercePriceListAccountRel commercePriceListAccountRel) {

		boolean isNew = commercePriceListAccountRel.isNew();

		if (!(commercePriceListAccountRel instanceof
				CommercePriceListAccountRelModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(
					commercePriceListAccountRel.getClass())) {

				invocationHandler = ProxyUtil.getInvocationHandler(
					commercePriceListAccountRel);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commercePriceListAccountRel proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommercePriceListAccountRel implementation " +
					commercePriceListAccountRel.getClass());
		}

		CommercePriceListAccountRelModelImpl
			commercePriceListAccountRelModelImpl =
				(CommercePriceListAccountRelModelImpl)
					commercePriceListAccountRel;

		if (Validator.isNull(commercePriceListAccountRel.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			commercePriceListAccountRel.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commercePriceListAccountRel.getCreateDate() == null)) {
			if (serviceContext == null) {
				commercePriceListAccountRel.setCreateDate(now);
			}
			else {
				commercePriceListAccountRel.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!commercePriceListAccountRelModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commercePriceListAccountRel.setModifiedDate(now);
			}
			else {
				commercePriceListAccountRel.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commercePriceListAccountRel.isNew()) {
				session.save(commercePriceListAccountRel);

				commercePriceListAccountRel.setNew(false);
			}
			else {
				commercePriceListAccountRel =
					(CommercePriceListAccountRel)session.merge(
						commercePriceListAccountRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommercePriceListAccountRelModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				commercePriceListAccountRelModelImpl.getUuid()
			};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				commercePriceListAccountRelModelImpl.getUuid(),
				commercePriceListAccountRelModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {
				commercePriceListAccountRelModelImpl.getCommercePriceListId()
			};

			finderCache.removeResult(
				_finderPathCountByCommercePriceListId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCommercePriceListId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((commercePriceListAccountRelModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commercePriceListAccountRelModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {
					commercePriceListAccountRelModelImpl.getUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((commercePriceListAccountRelModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commercePriceListAccountRelModelImpl.getOriginalUuid(),
					commercePriceListAccountRelModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					commercePriceListAccountRelModelImpl.getUuid(),
					commercePriceListAccountRelModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((commercePriceListAccountRelModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCommercePriceListId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commercePriceListAccountRelModelImpl.
						getOriginalCommercePriceListId()
				};

				finderCache.removeResult(
					_finderPathCountByCommercePriceListId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommercePriceListId,
					args);

				args = new Object[] {
					commercePriceListAccountRelModelImpl.
						getCommercePriceListId()
				};

				finderCache.removeResult(
					_finderPathCountByCommercePriceListId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommercePriceListId,
					args);
			}
		}

		entityCache.putResult(
			CommercePriceListAccountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePriceListAccountRelImpl.class,
			commercePriceListAccountRel.getPrimaryKey(),
			commercePriceListAccountRel, false);

		clearUniqueFindersCache(commercePriceListAccountRelModelImpl, false);
		cacheUniqueFindersCache(commercePriceListAccountRelModelImpl);

		commercePriceListAccountRel.resetOriginalValues();

		return commercePriceListAccountRel;
	}

	/**
	 * Returns the commerce price list account rel with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce price list account rel
	 * @return the commerce price list account rel
	 * @throws NoSuchPriceListAccountRelException if a commerce price list account rel with the primary key could not be found
	 */
	@Override
	public CommercePriceListAccountRel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPriceListAccountRelException {

		CommercePriceListAccountRel commercePriceListAccountRel =
			fetchByPrimaryKey(primaryKey);

		if (commercePriceListAccountRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPriceListAccountRelException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commercePriceListAccountRel;
	}

	/**
	 * Returns the commerce price list account rel with the primary key or throws a <code>NoSuchPriceListAccountRelException</code> if it could not be found.
	 *
	 * @param commercePriceListAccountRelId the primary key of the commerce price list account rel
	 * @return the commerce price list account rel
	 * @throws NoSuchPriceListAccountRelException if a commerce price list account rel with the primary key could not be found
	 */
	@Override
	public CommercePriceListAccountRel findByPrimaryKey(
			long commercePriceListAccountRelId)
		throws NoSuchPriceListAccountRelException {

		return findByPrimaryKey((Serializable)commercePriceListAccountRelId);
	}

	/**
	 * Returns the commerce price list account rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce price list account rel
	 * @return the commerce price list account rel, or <code>null</code> if a commerce price list account rel with the primary key could not be found
	 */
	@Override
	public CommercePriceListAccountRel fetchByPrimaryKey(
		Serializable primaryKey) {

		Serializable serializable = entityCache.getResult(
			CommercePriceListAccountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePriceListAccountRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommercePriceListAccountRel commercePriceListAccountRel =
			(CommercePriceListAccountRel)serializable;

		if (commercePriceListAccountRel == null) {
			Session session = null;

			try {
				session = openSession();

				commercePriceListAccountRel =
					(CommercePriceListAccountRel)session.get(
						CommercePriceListAccountRelImpl.class, primaryKey);

				if (commercePriceListAccountRel != null) {
					cacheResult(commercePriceListAccountRel);
				}
				else {
					entityCache.putResult(
						CommercePriceListAccountRelModelImpl.
							ENTITY_CACHE_ENABLED,
						CommercePriceListAccountRelImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CommercePriceListAccountRelModelImpl.ENTITY_CACHE_ENABLED,
					CommercePriceListAccountRelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commercePriceListAccountRel;
	}

	/**
	 * Returns the commerce price list account rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commercePriceListAccountRelId the primary key of the commerce price list account rel
	 * @return the commerce price list account rel, or <code>null</code> if a commerce price list account rel with the primary key could not be found
	 */
	@Override
	public CommercePriceListAccountRel fetchByPrimaryKey(
		long commercePriceListAccountRelId) {

		return fetchByPrimaryKey((Serializable)commercePriceListAccountRelId);
	}

	@Override
	public Map<Serializable, CommercePriceListAccountRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommercePriceListAccountRel> map =
			new HashMap<Serializable, CommercePriceListAccountRel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommercePriceListAccountRel commercePriceListAccountRel =
				fetchByPrimaryKey(primaryKey);

			if (commercePriceListAccountRel != null) {
				map.put(primaryKey, commercePriceListAccountRel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CommercePriceListAccountRelModelImpl.ENTITY_CACHE_ENABLED,
				CommercePriceListAccountRelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(
						primaryKey, (CommercePriceListAccountRel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_COMMERCEPRICELISTACCOUNTREL_WHERE_PKS_IN);

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

			for (CommercePriceListAccountRel commercePriceListAccountRel :
					(List<CommercePriceListAccountRel>)q.list()) {

				map.put(
					commercePriceListAccountRel.getPrimaryKeyObj(),
					commercePriceListAccountRel);

				cacheResult(commercePriceListAccountRel);

				uncachedPrimaryKeys.remove(
					commercePriceListAccountRel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CommercePriceListAccountRelModelImpl.ENTITY_CACHE_ENABLED,
					CommercePriceListAccountRelImpl.class, primaryKey,
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
	 * Returns all the commerce price list account rels.
	 *
	 * @return the commerce price list account rels
	 */
	@Override
	public List<CommercePriceListAccountRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce price list account rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePriceListAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce price list account rels
	 * @param end the upper bound of the range of commerce price list account rels (not inclusive)
	 * @return the range of commerce price list account rels
	 */
	@Override
	public List<CommercePriceListAccountRel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce price list account rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePriceListAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce price list account rels
	 * @param end the upper bound of the range of commerce price list account rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce price list account rels
	 */
	@Override
	public List<CommercePriceListAccountRel> findAll(
		int start, int end,
		OrderByComparator<CommercePriceListAccountRel> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce price list account rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePriceListAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce price list account rels
	 * @param end the upper bound of the range of commerce price list account rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce price list account rels
	 */
	@Override
	public List<CommercePriceListAccountRel> findAll(
		int start, int end,
		OrderByComparator<CommercePriceListAccountRel> orderByComparator,
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

		List<CommercePriceListAccountRel> list = null;

		if (retrieveFromCache) {
			list = (List<CommercePriceListAccountRel>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEPRICELISTACCOUNTREL);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEPRICELISTACCOUNTREL;

				if (pagination) {
					sql = sql.concat(
						CommercePriceListAccountRelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommercePriceListAccountRel>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommercePriceListAccountRel>)QueryUtil.list(
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
	 * Removes all the commerce price list account rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommercePriceListAccountRel commercePriceListAccountRel :
				findAll()) {

			remove(commercePriceListAccountRel);
		}
	}

	/**
	 * Returns the number of commerce price list account rels.
	 *
	 * @return the number of commerce price list account rels
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
					_SQL_COUNT_COMMERCEPRICELISTACCOUNTREL);

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
		return CommercePriceListAccountRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce price list account rel persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CommercePriceListAccountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePriceListAccountRelModelImpl.FINDER_CACHE_ENABLED,
			CommercePriceListAccountRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CommercePriceListAccountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePriceListAccountRelModelImpl.FINDER_CACHE_ENABLED,
			CommercePriceListAccountRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CommercePriceListAccountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePriceListAccountRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			CommercePriceListAccountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePriceListAccountRelModelImpl.FINDER_CACHE_ENABLED,
			CommercePriceListAccountRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			CommercePriceListAccountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePriceListAccountRelModelImpl.FINDER_CACHE_ENABLED,
			CommercePriceListAccountRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			CommercePriceListAccountRelModelImpl.UUID_COLUMN_BITMASK |
			CommercePriceListAccountRelModelImpl.ORDER_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			CommercePriceListAccountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePriceListAccountRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUuid", new String[] {String.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			CommercePriceListAccountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePriceListAccountRelModelImpl.FINDER_CACHE_ENABLED,
			CommercePriceListAccountRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			CommercePriceListAccountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePriceListAccountRelModelImpl.FINDER_CACHE_ENABLED,
			CommercePriceListAccountRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			CommercePriceListAccountRelModelImpl.UUID_COLUMN_BITMASK |
			CommercePriceListAccountRelModelImpl.COMPANYID_COLUMN_BITMASK |
			CommercePriceListAccountRelModelImpl.ORDER_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			CommercePriceListAccountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePriceListAccountRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByCommercePriceListId = new FinderPath(
			CommercePriceListAccountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePriceListAccountRelModelImpl.FINDER_CACHE_ENABLED,
			CommercePriceListAccountRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCommercePriceListId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCommercePriceListId = new FinderPath(
			CommercePriceListAccountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePriceListAccountRelModelImpl.FINDER_CACHE_ENABLED,
			CommercePriceListAccountRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCommercePriceListId", new String[] {Long.class.getName()},
			CommercePriceListAccountRelModelImpl.
				COMMERCEPRICELISTID_COLUMN_BITMASK |
			CommercePriceListAccountRelModelImpl.ORDER_COLUMN_BITMASK);

		_finderPathCountByCommercePriceListId = new FinderPath(
			CommercePriceListAccountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePriceListAccountRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommercePriceListId", new String[] {Long.class.getName()});

		_finderPathFetchByC_C = new FinderPath(
			CommercePriceListAccountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePriceListAccountRelModelImpl.FINDER_CACHE_ENABLED,
			CommercePriceListAccountRelImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_C",
			new String[] {Long.class.getName(), Long.class.getName()},
			CommercePriceListAccountRelModelImpl.
				COMMERCEACCOUNTID_COLUMN_BITMASK |
			CommercePriceListAccountRelModelImpl.
				COMMERCEPRICELISTID_COLUMN_BITMASK);

		_finderPathCountByC_C = new FinderPath(
			CommercePriceListAccountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePriceListAccountRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] {Long.class.getName(), Long.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(
			CommercePriceListAccountRelImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_COMMERCEPRICELISTACCOUNTREL =
		"SELECT commercePriceListAccountRel FROM CommercePriceListAccountRel commercePriceListAccountRel";

	private static final String
		_SQL_SELECT_COMMERCEPRICELISTACCOUNTREL_WHERE_PKS_IN =
			"SELECT commercePriceListAccountRel FROM CommercePriceListAccountRel commercePriceListAccountRel WHERE commercePriceListAccountRelId IN (";

	private static final String _SQL_SELECT_COMMERCEPRICELISTACCOUNTREL_WHERE =
		"SELECT commercePriceListAccountRel FROM CommercePriceListAccountRel commercePriceListAccountRel WHERE ";

	private static final String _SQL_COUNT_COMMERCEPRICELISTACCOUNTREL =
		"SELECT COUNT(commercePriceListAccountRel) FROM CommercePriceListAccountRel commercePriceListAccountRel";

	private static final String _SQL_COUNT_COMMERCEPRICELISTACCOUNTREL_WHERE =
		"SELECT COUNT(commercePriceListAccountRel) FROM CommercePriceListAccountRel commercePriceListAccountRel WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"commercePriceListAccountRel.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommercePriceListAccountRel exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommercePriceListAccountRel exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommercePriceListAccountRelPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "order"});

}