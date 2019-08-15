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

package com.liferay.commerce.currency.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.currency.exception.NoSuchCurrencyException;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.impl.CommerceCurrencyImpl;
import com.liferay.commerce.currency.model.impl.CommerceCurrencyModelImpl;
import com.liferay.commerce.currency.service.persistence.CommerceCurrencyPersistence;
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
 * The persistence implementation for the commerce currency service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Andrea Di Giorgi
 * @generated
 */
@ProviderType
public class CommerceCurrencyPersistenceImpl
	extends BasePersistenceImpl<CommerceCurrency>
	implements CommerceCurrencyPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommerceCurrencyUtil</code> to access the commerce currency persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommerceCurrencyImpl.class.getName();

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
	 * Returns all the commerce currencies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce currencies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceCurrencyModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce currencies
	 * @param end the upper bound of the range of commerce currencies (not inclusive)
	 * @return the range of matching commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce currencies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceCurrencyModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce currencies
	 * @param end the upper bound of the range of commerce currencies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommerceCurrency> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce currencies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceCurrencyModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce currencies
	 * @param end the upper bound of the range of commerce currencies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommerceCurrency> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<CommerceCurrency> list = null;

		if (useFinderCache) {
			list = (List<CommerceCurrency>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceCurrency commerceCurrency : list) {
					if (!uuid.equals(commerceCurrency.getUuid())) {
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

			query.append(_SQL_SELECT_COMMERCECURRENCY_WHERE);

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
				query.append(CommerceCurrencyModelImpl.ORDER_BY_JPQL);
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
					list = (List<CommerceCurrency>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceCurrency>)QueryUtil.list(
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
	 * Returns the first commerce currency in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce currency
	 * @throws NoSuchCurrencyException if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency findByUuid_First(
			String uuid, OrderByComparator<CommerceCurrency> orderByComparator)
		throws NoSuchCurrencyException {

		CommerceCurrency commerceCurrency = fetchByUuid_First(
			uuid, orderByComparator);

		if (commerceCurrency != null) {
			return commerceCurrency;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCurrencyException(msg.toString());
	}

	/**
	 * Returns the first commerce currency in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce currency, or <code>null</code> if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency fetchByUuid_First(
		String uuid, OrderByComparator<CommerceCurrency> orderByComparator) {

		List<CommerceCurrency> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce currency in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce currency
	 * @throws NoSuchCurrencyException if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency findByUuid_Last(
			String uuid, OrderByComparator<CommerceCurrency> orderByComparator)
		throws NoSuchCurrencyException {

		CommerceCurrency commerceCurrency = fetchByUuid_Last(
			uuid, orderByComparator);

		if (commerceCurrency != null) {
			return commerceCurrency;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCurrencyException(msg.toString());
	}

	/**
	 * Returns the last commerce currency in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce currency, or <code>null</code> if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency fetchByUuid_Last(
		String uuid, OrderByComparator<CommerceCurrency> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CommerceCurrency> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce currencies before and after the current commerce currency in the ordered set where uuid = &#63;.
	 *
	 * @param commerceCurrencyId the primary key of the current commerce currency
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce currency
	 * @throws NoSuchCurrencyException if a commerce currency with the primary key could not be found
	 */
	@Override
	public CommerceCurrency[] findByUuid_PrevAndNext(
			long commerceCurrencyId, String uuid,
			OrderByComparator<CommerceCurrency> orderByComparator)
		throws NoSuchCurrencyException {

		uuid = Objects.toString(uuid, "");

		CommerceCurrency commerceCurrency = findByPrimaryKey(
			commerceCurrencyId);

		Session session = null;

		try {
			session = openSession();

			CommerceCurrency[] array = new CommerceCurrencyImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, commerceCurrency, uuid, orderByComparator, true);

			array[1] = commerceCurrency;

			array[2] = getByUuid_PrevAndNext(
				session, commerceCurrency, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceCurrency getByUuid_PrevAndNext(
		Session session, CommerceCurrency commerceCurrency, String uuid,
		OrderByComparator<CommerceCurrency> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCECURRENCY_WHERE);

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
			query.append(CommerceCurrencyModelImpl.ORDER_BY_JPQL);
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
						commerceCurrency)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceCurrency> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce currencies where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CommerceCurrency commerceCurrency :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commerceCurrency);
		}
	}

	/**
	 * Returns the number of commerce currencies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching commerce currencies
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCECURRENCY_WHERE);

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
		"commerceCurrency.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(commerceCurrency.uuid IS NULL OR commerceCurrency.uuid = '')";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the commerce currencies where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce currencies where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceCurrencyModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce currencies
	 * @param end the upper bound of the range of commerce currencies (not inclusive)
	 * @return the range of matching commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce currencies where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceCurrencyModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce currencies
	 * @param end the upper bound of the range of commerce currencies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceCurrency> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce currencies where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceCurrencyModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce currencies
	 * @param end the upper bound of the range of commerce currencies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceCurrency> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid_C;
				finderArgs = new Object[] {uuid, companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<CommerceCurrency> list = null;

		if (useFinderCache) {
			list = (List<CommerceCurrency>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceCurrency commerceCurrency : list) {
					if (!uuid.equals(commerceCurrency.getUuid()) ||
						(companyId != commerceCurrency.getCompanyId())) {

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

			query.append(_SQL_SELECT_COMMERCECURRENCY_WHERE);

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
				query.append(CommerceCurrencyModelImpl.ORDER_BY_JPQL);
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
					list = (List<CommerceCurrency>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceCurrency>)QueryUtil.list(
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
	 * Returns the first commerce currency in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce currency
	 * @throws NoSuchCurrencyException if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CommerceCurrency> orderByComparator)
		throws NoSuchCurrencyException {

		CommerceCurrency commerceCurrency = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (commerceCurrency != null) {
			return commerceCurrency;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCurrencyException(msg.toString());
	}

	/**
	 * Returns the first commerce currency in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce currency, or <code>null</code> if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CommerceCurrency> orderByComparator) {

		List<CommerceCurrency> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce currency in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce currency
	 * @throws NoSuchCurrencyException if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CommerceCurrency> orderByComparator)
		throws NoSuchCurrencyException {

		CommerceCurrency commerceCurrency = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (commerceCurrency != null) {
			return commerceCurrency;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCurrencyException(msg.toString());
	}

	/**
	 * Returns the last commerce currency in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce currency, or <code>null</code> if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CommerceCurrency> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CommerceCurrency> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce currencies before and after the current commerce currency in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param commerceCurrencyId the primary key of the current commerce currency
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce currency
	 * @throws NoSuchCurrencyException if a commerce currency with the primary key could not be found
	 */
	@Override
	public CommerceCurrency[] findByUuid_C_PrevAndNext(
			long commerceCurrencyId, String uuid, long companyId,
			OrderByComparator<CommerceCurrency> orderByComparator)
		throws NoSuchCurrencyException {

		uuid = Objects.toString(uuid, "");

		CommerceCurrency commerceCurrency = findByPrimaryKey(
			commerceCurrencyId);

		Session session = null;

		try {
			session = openSession();

			CommerceCurrency[] array = new CommerceCurrencyImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, commerceCurrency, uuid, companyId, orderByComparator,
				true);

			array[1] = commerceCurrency;

			array[2] = getByUuid_C_PrevAndNext(
				session, commerceCurrency, uuid, companyId, orderByComparator,
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

	protected CommerceCurrency getByUuid_C_PrevAndNext(
		Session session, CommerceCurrency commerceCurrency, String uuid,
		long companyId, OrderByComparator<CommerceCurrency> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCECURRENCY_WHERE);

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
			query.append(CommerceCurrencyModelImpl.ORDER_BY_JPQL);
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
						commerceCurrency)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceCurrency> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce currencies where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CommerceCurrency commerceCurrency :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceCurrency);
		}
	}

	/**
	 * Returns the number of commerce currencies where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching commerce currencies
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCECURRENCY_WHERE);

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
		"commerceCurrency.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(commerceCurrency.uuid IS NULL OR commerceCurrency.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"commerceCurrency.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the commerce currencies where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce currencies where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceCurrencyModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce currencies
	 * @param end the upper bound of the range of commerce currencies (not inclusive)
	 * @return the range of matching commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce currencies where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceCurrencyModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce currencies
	 * @param end the upper bound of the range of commerce currencies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceCurrency> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce currencies where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceCurrencyModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce currencies
	 * @param end the upper bound of the range of commerce currencies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceCurrency> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByCompanyId;
				finderArgs = new Object[] {companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCompanyId;
			finderArgs = new Object[] {
				companyId, start, end, orderByComparator
			};
		}

		List<CommerceCurrency> list = null;

		if (useFinderCache) {
			list = (List<CommerceCurrency>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceCurrency commerceCurrency : list) {
					if ((companyId != commerceCurrency.getCompanyId())) {
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

			query.append(_SQL_SELECT_COMMERCECURRENCY_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceCurrencyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<CommerceCurrency>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceCurrency>)QueryUtil.list(
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
	 * Returns the first commerce currency in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce currency
	 * @throws NoSuchCurrencyException if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency findByCompanyId_First(
			long companyId,
			OrderByComparator<CommerceCurrency> orderByComparator)
		throws NoSuchCurrencyException {

		CommerceCurrency commerceCurrency = fetchByCompanyId_First(
			companyId, orderByComparator);

		if (commerceCurrency != null) {
			return commerceCurrency;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCurrencyException(msg.toString());
	}

	/**
	 * Returns the first commerce currency in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce currency, or <code>null</code> if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency fetchByCompanyId_First(
		long companyId, OrderByComparator<CommerceCurrency> orderByComparator) {

		List<CommerceCurrency> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce currency in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce currency
	 * @throws NoSuchCurrencyException if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency findByCompanyId_Last(
			long companyId,
			OrderByComparator<CommerceCurrency> orderByComparator)
		throws NoSuchCurrencyException {

		CommerceCurrency commerceCurrency = fetchByCompanyId_Last(
			companyId, orderByComparator);

		if (commerceCurrency != null) {
			return commerceCurrency;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCurrencyException(msg.toString());
	}

	/**
	 * Returns the last commerce currency in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce currency, or <code>null</code> if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency fetchByCompanyId_Last(
		long companyId, OrderByComparator<CommerceCurrency> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<CommerceCurrency> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce currencies before and after the current commerce currency in the ordered set where companyId = &#63;.
	 *
	 * @param commerceCurrencyId the primary key of the current commerce currency
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce currency
	 * @throws NoSuchCurrencyException if a commerce currency with the primary key could not be found
	 */
	@Override
	public CommerceCurrency[] findByCompanyId_PrevAndNext(
			long commerceCurrencyId, long companyId,
			OrderByComparator<CommerceCurrency> orderByComparator)
		throws NoSuchCurrencyException {

		CommerceCurrency commerceCurrency = findByPrimaryKey(
			commerceCurrencyId);

		Session session = null;

		try {
			session = openSession();

			CommerceCurrency[] array = new CommerceCurrencyImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, commerceCurrency, companyId, orderByComparator, true);

			array[1] = commerceCurrency;

			array[2] = getByCompanyId_PrevAndNext(
				session, commerceCurrency, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceCurrency getByCompanyId_PrevAndNext(
		Session session, CommerceCurrency commerceCurrency, long companyId,
		OrderByComparator<CommerceCurrency> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCECURRENCY_WHERE);

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
			query.append(CommerceCurrencyModelImpl.ORDER_BY_JPQL);
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
						commerceCurrency)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceCurrency> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce currencies where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (CommerceCurrency commerceCurrency :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commerceCurrency);
		}
	}

	/**
	 * Returns the number of commerce currencies where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce currencies
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] {companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCECURRENCY_WHERE);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 =
		"commerceCurrency.companyId = ?";

	private FinderPath _finderPathFetchByC_C;
	private FinderPath _finderPathCountByC_C;

	/**
	 * Returns the commerce currency where companyId = &#63; and code = &#63; or throws a <code>NoSuchCurrencyException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param code the code
	 * @return the matching commerce currency
	 * @throws NoSuchCurrencyException if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency findByC_C(long companyId, String code)
		throws NoSuchCurrencyException {

		CommerceCurrency commerceCurrency = fetchByC_C(companyId, code);

		if (commerceCurrency == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", code=");
			msg.append(code);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCurrencyException(msg.toString());
		}

		return commerceCurrency;
	}

	/**
	 * Returns the commerce currency where companyId = &#63; and code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param code the code
	 * @return the matching commerce currency, or <code>null</code> if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency fetchByC_C(long companyId, String code) {
		return fetchByC_C(companyId, code, true);
	}

	/**
	 * Returns the commerce currency where companyId = &#63; and code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param code the code
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce currency, or <code>null</code> if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency fetchByC_C(
		long companyId, String code, boolean useFinderCache) {

		code = Objects.toString(code, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {companyId, code};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_C, finderArgs, this);
		}

		if (result instanceof CommerceCurrency) {
			CommerceCurrency commerceCurrency = (CommerceCurrency)result;

			if ((companyId != commerceCurrency.getCompanyId()) ||
				!Objects.equals(code, commerceCurrency.getCode())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COMMERCECURRENCY_WHERE);

			query.append(_FINDER_COLUMN_C_C_COMPANYID_2);

			boolean bindCode = false;

			if (code.isEmpty()) {
				query.append(_FINDER_COLUMN_C_C_CODE_3);
			}
			else {
				bindCode = true;

				query.append(_FINDER_COLUMN_C_C_CODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindCode) {
					qPos.add(code);
				}

				List<CommerceCurrency> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByC_C, finderArgs, list);
					}
				}
				else {
					CommerceCurrency commerceCurrency = list.get(0);

					result = commerceCurrency;

					cacheResult(commerceCurrency);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(_finderPathFetchByC_C, finderArgs);
				}

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
			return (CommerceCurrency)result;
		}
	}

	/**
	 * Removes the commerce currency where companyId = &#63; and code = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param code the code
	 * @return the commerce currency that was removed
	 */
	@Override
	public CommerceCurrency removeByC_C(long companyId, String code)
		throws NoSuchCurrencyException {

		CommerceCurrency commerceCurrency = findByC_C(companyId, code);

		return remove(commerceCurrency);
	}

	/**
	 * Returns the number of commerce currencies where companyId = &#63; and code = &#63;.
	 *
	 * @param companyId the company ID
	 * @param code the code
	 * @return the number of matching commerce currencies
	 */
	@Override
	public int countByC_C(long companyId, String code) {
		code = Objects.toString(code, "");

		FinderPath finderPath = _finderPathCountByC_C;

		Object[] finderArgs = new Object[] {companyId, code};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCECURRENCY_WHERE);

			query.append(_FINDER_COLUMN_C_C_COMPANYID_2);

			boolean bindCode = false;

			if (code.isEmpty()) {
				query.append(_FINDER_COLUMN_C_C_CODE_3);
			}
			else {
				bindCode = true;

				query.append(_FINDER_COLUMN_C_C_CODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindCode) {
					qPos.add(code);
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

	private static final String _FINDER_COLUMN_C_C_COMPANYID_2 =
		"commerceCurrency.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_CODE_2 =
		"commerceCurrency.code = ?";

	private static final String _FINDER_COLUMN_C_C_CODE_3 =
		"(commerceCurrency.code IS NULL OR commerceCurrency.code = '')";

	private FinderPath _finderPathWithPaginationFindByC_P;
	private FinderPath _finderPathWithoutPaginationFindByC_P;
	private FinderPath _finderPathCountByC_P;

	/**
	 * Returns all the commerce currencies where companyId = &#63; and primary = &#63;.
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @return the matching commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findByC_P(long companyId, boolean primary) {
		return findByC_P(
			companyId, primary, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce currencies where companyId = &#63; and primary = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceCurrencyModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param start the lower bound of the range of commerce currencies
	 * @param end the upper bound of the range of commerce currencies (not inclusive)
	 * @return the range of matching commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findByC_P(
		long companyId, boolean primary, int start, int end) {

		return findByC_P(companyId, primary, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce currencies where companyId = &#63; and primary = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceCurrencyModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param start the lower bound of the range of commerce currencies
	 * @param end the upper bound of the range of commerce currencies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findByC_P(
		long companyId, boolean primary, int start, int end,
		OrderByComparator<CommerceCurrency> orderByComparator) {

		return findByC_P(
			companyId, primary, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce currencies where companyId = &#63; and primary = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceCurrencyModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param start the lower bound of the range of commerce currencies
	 * @param end the upper bound of the range of commerce currencies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findByC_P(
		long companyId, boolean primary, int start, int end,
		OrderByComparator<CommerceCurrency> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByC_P;
				finderArgs = new Object[] {companyId, primary};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByC_P;
			finderArgs = new Object[] {
				companyId, primary, start, end, orderByComparator
			};
		}

		List<CommerceCurrency> list = null;

		if (useFinderCache) {
			list = (List<CommerceCurrency>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceCurrency commerceCurrency : list) {
					if ((companyId != commerceCurrency.getCompanyId()) ||
						(primary != commerceCurrency.isPrimary())) {

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

			query.append(_SQL_SELECT_COMMERCECURRENCY_WHERE);

			query.append(_FINDER_COLUMN_C_P_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_P_PRIMARY_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceCurrencyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(primary);

				if (!pagination) {
					list = (List<CommerceCurrency>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceCurrency>)QueryUtil.list(
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
	 * Returns the first commerce currency in the ordered set where companyId = &#63; and primary = &#63;.
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce currency
	 * @throws NoSuchCurrencyException if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency findByC_P_First(
			long companyId, boolean primary,
			OrderByComparator<CommerceCurrency> orderByComparator)
		throws NoSuchCurrencyException {

		CommerceCurrency commerceCurrency = fetchByC_P_First(
			companyId, primary, orderByComparator);

		if (commerceCurrency != null) {
			return commerceCurrency;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", primary=");
		msg.append(primary);

		msg.append("}");

		throw new NoSuchCurrencyException(msg.toString());
	}

	/**
	 * Returns the first commerce currency in the ordered set where companyId = &#63; and primary = &#63;.
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce currency, or <code>null</code> if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency fetchByC_P_First(
		long companyId, boolean primary,
		OrderByComparator<CommerceCurrency> orderByComparator) {

		List<CommerceCurrency> list = findByC_P(
			companyId, primary, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce currency in the ordered set where companyId = &#63; and primary = &#63;.
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce currency
	 * @throws NoSuchCurrencyException if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency findByC_P_Last(
			long companyId, boolean primary,
			OrderByComparator<CommerceCurrency> orderByComparator)
		throws NoSuchCurrencyException {

		CommerceCurrency commerceCurrency = fetchByC_P_Last(
			companyId, primary, orderByComparator);

		if (commerceCurrency != null) {
			return commerceCurrency;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", primary=");
		msg.append(primary);

		msg.append("}");

		throw new NoSuchCurrencyException(msg.toString());
	}

	/**
	 * Returns the last commerce currency in the ordered set where companyId = &#63; and primary = &#63;.
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce currency, or <code>null</code> if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency fetchByC_P_Last(
		long companyId, boolean primary,
		OrderByComparator<CommerceCurrency> orderByComparator) {

		int count = countByC_P(companyId, primary);

		if (count == 0) {
			return null;
		}

		List<CommerceCurrency> list = findByC_P(
			companyId, primary, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce currencies before and after the current commerce currency in the ordered set where companyId = &#63; and primary = &#63;.
	 *
	 * @param commerceCurrencyId the primary key of the current commerce currency
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce currency
	 * @throws NoSuchCurrencyException if a commerce currency with the primary key could not be found
	 */
	@Override
	public CommerceCurrency[] findByC_P_PrevAndNext(
			long commerceCurrencyId, long companyId, boolean primary,
			OrderByComparator<CommerceCurrency> orderByComparator)
		throws NoSuchCurrencyException {

		CommerceCurrency commerceCurrency = findByPrimaryKey(
			commerceCurrencyId);

		Session session = null;

		try {
			session = openSession();

			CommerceCurrency[] array = new CommerceCurrencyImpl[3];

			array[0] = getByC_P_PrevAndNext(
				session, commerceCurrency, companyId, primary,
				orderByComparator, true);

			array[1] = commerceCurrency;

			array[2] = getByC_P_PrevAndNext(
				session, commerceCurrency, companyId, primary,
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

	protected CommerceCurrency getByC_P_PrevAndNext(
		Session session, CommerceCurrency commerceCurrency, long companyId,
		boolean primary, OrderByComparator<CommerceCurrency> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCECURRENCY_WHERE);

		query.append(_FINDER_COLUMN_C_P_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_P_PRIMARY_2);

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
			query.append(CommerceCurrencyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(primary);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceCurrency)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceCurrency> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce currencies where companyId = &#63; and primary = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 */
	@Override
	public void removeByC_P(long companyId, boolean primary) {
		for (CommerceCurrency commerceCurrency :
				findByC_P(
					companyId, primary, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceCurrency);
		}
	}

	/**
	 * Returns the number of commerce currencies where companyId = &#63; and primary = &#63;.
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @return the number of matching commerce currencies
	 */
	@Override
	public int countByC_P(long companyId, boolean primary) {
		FinderPath finderPath = _finderPathCountByC_P;

		Object[] finderArgs = new Object[] {companyId, primary};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCECURRENCY_WHERE);

			query.append(_FINDER_COLUMN_C_P_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_P_PRIMARY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(primary);

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

	private static final String _FINDER_COLUMN_C_P_COMPANYID_2 =
		"commerceCurrency.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_P_PRIMARY_2 =
		"commerceCurrency.primary = ?";

	private FinderPath _finderPathWithPaginationFindByC_A;
	private FinderPath _finderPathWithoutPaginationFindByC_A;
	private FinderPath _finderPathCountByC_A;

	/**
	 * Returns all the commerce currencies where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @return the matching commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findByC_A(long companyId, boolean active) {
		return findByC_A(
			companyId, active, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce currencies where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceCurrencyModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param start the lower bound of the range of commerce currencies
	 * @param end the upper bound of the range of commerce currencies (not inclusive)
	 * @return the range of matching commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findByC_A(
		long companyId, boolean active, int start, int end) {

		return findByC_A(companyId, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce currencies where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceCurrencyModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param start the lower bound of the range of commerce currencies
	 * @param end the upper bound of the range of commerce currencies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findByC_A(
		long companyId, boolean active, int start, int end,
		OrderByComparator<CommerceCurrency> orderByComparator) {

		return findByC_A(
			companyId, active, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce currencies where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceCurrencyModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param start the lower bound of the range of commerce currencies
	 * @param end the upper bound of the range of commerce currencies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findByC_A(
		long companyId, boolean active, int start, int end,
		OrderByComparator<CommerceCurrency> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByC_A;
				finderArgs = new Object[] {companyId, active};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByC_A;
			finderArgs = new Object[] {
				companyId, active, start, end, orderByComparator
			};
		}

		List<CommerceCurrency> list = null;

		if (useFinderCache) {
			list = (List<CommerceCurrency>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceCurrency commerceCurrency : list) {
					if ((companyId != commerceCurrency.getCompanyId()) ||
						(active != commerceCurrency.isActive())) {

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

			query.append(_SQL_SELECT_COMMERCECURRENCY_WHERE);

			query.append(_FINDER_COLUMN_C_A_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceCurrencyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(active);

				if (!pagination) {
					list = (List<CommerceCurrency>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceCurrency>)QueryUtil.list(
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
	 * Returns the first commerce currency in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce currency
	 * @throws NoSuchCurrencyException if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency findByC_A_First(
			long companyId, boolean active,
			OrderByComparator<CommerceCurrency> orderByComparator)
		throws NoSuchCurrencyException {

		CommerceCurrency commerceCurrency = fetchByC_A_First(
			companyId, active, orderByComparator);

		if (commerceCurrency != null) {
			return commerceCurrency;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", active=");
		msg.append(active);

		msg.append("}");

		throw new NoSuchCurrencyException(msg.toString());
	}

	/**
	 * Returns the first commerce currency in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce currency, or <code>null</code> if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency fetchByC_A_First(
		long companyId, boolean active,
		OrderByComparator<CommerceCurrency> orderByComparator) {

		List<CommerceCurrency> list = findByC_A(
			companyId, active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce currency in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce currency
	 * @throws NoSuchCurrencyException if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency findByC_A_Last(
			long companyId, boolean active,
			OrderByComparator<CommerceCurrency> orderByComparator)
		throws NoSuchCurrencyException {

		CommerceCurrency commerceCurrency = fetchByC_A_Last(
			companyId, active, orderByComparator);

		if (commerceCurrency != null) {
			return commerceCurrency;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", active=");
		msg.append(active);

		msg.append("}");

		throw new NoSuchCurrencyException(msg.toString());
	}

	/**
	 * Returns the last commerce currency in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce currency, or <code>null</code> if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency fetchByC_A_Last(
		long companyId, boolean active,
		OrderByComparator<CommerceCurrency> orderByComparator) {

		int count = countByC_A(companyId, active);

		if (count == 0) {
			return null;
		}

		List<CommerceCurrency> list = findByC_A(
			companyId, active, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce currencies before and after the current commerce currency in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * @param commerceCurrencyId the primary key of the current commerce currency
	 * @param companyId the company ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce currency
	 * @throws NoSuchCurrencyException if a commerce currency with the primary key could not be found
	 */
	@Override
	public CommerceCurrency[] findByC_A_PrevAndNext(
			long commerceCurrencyId, long companyId, boolean active,
			OrderByComparator<CommerceCurrency> orderByComparator)
		throws NoSuchCurrencyException {

		CommerceCurrency commerceCurrency = findByPrimaryKey(
			commerceCurrencyId);

		Session session = null;

		try {
			session = openSession();

			CommerceCurrency[] array = new CommerceCurrencyImpl[3];

			array[0] = getByC_A_PrevAndNext(
				session, commerceCurrency, companyId, active, orderByComparator,
				true);

			array[1] = commerceCurrency;

			array[2] = getByC_A_PrevAndNext(
				session, commerceCurrency, companyId, active, orderByComparator,
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

	protected CommerceCurrency getByC_A_PrevAndNext(
		Session session, CommerceCurrency commerceCurrency, long companyId,
		boolean active, OrderByComparator<CommerceCurrency> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCECURRENCY_WHERE);

		query.append(_FINDER_COLUMN_C_A_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_A_ACTIVE_2);

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
			query.append(CommerceCurrencyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(active);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceCurrency)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceCurrency> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce currencies where companyId = &#63; and active = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 */
	@Override
	public void removeByC_A(long companyId, boolean active) {
		for (CommerceCurrency commerceCurrency :
				findByC_A(
					companyId, active, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceCurrency);
		}
	}

	/**
	 * Returns the number of commerce currencies where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @return the number of matching commerce currencies
	 */
	@Override
	public int countByC_A(long companyId, boolean active) {
		FinderPath finderPath = _finderPathCountByC_A;

		Object[] finderArgs = new Object[] {companyId, active};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCECURRENCY_WHERE);

			query.append(_FINDER_COLUMN_C_A_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_C_A_COMPANYID_2 =
		"commerceCurrency.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_A_ACTIVE_2 =
		"commerceCurrency.active = ?";

	private FinderPath _finderPathWithPaginationFindByC_P_A;
	private FinderPath _finderPathWithoutPaginationFindByC_P_A;
	private FinderPath _finderPathCountByC_P_A;

	/**
	 * Returns all the commerce currencies where companyId = &#63; and primary = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param active the active
	 * @return the matching commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findByC_P_A(
		long companyId, boolean primary, boolean active) {

		return findByC_P_A(
			companyId, primary, active, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the commerce currencies where companyId = &#63; and primary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceCurrencyModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param active the active
	 * @param start the lower bound of the range of commerce currencies
	 * @param end the upper bound of the range of commerce currencies (not inclusive)
	 * @return the range of matching commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findByC_P_A(
		long companyId, boolean primary, boolean active, int start, int end) {

		return findByC_P_A(companyId, primary, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce currencies where companyId = &#63; and primary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceCurrencyModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param active the active
	 * @param start the lower bound of the range of commerce currencies
	 * @param end the upper bound of the range of commerce currencies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findByC_P_A(
		long companyId, boolean primary, boolean active, int start, int end,
		OrderByComparator<CommerceCurrency> orderByComparator) {

		return findByC_P_A(
			companyId, primary, active, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce currencies where companyId = &#63; and primary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceCurrencyModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param active the active
	 * @param start the lower bound of the range of commerce currencies
	 * @param end the upper bound of the range of commerce currencies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findByC_P_A(
		long companyId, boolean primary, boolean active, int start, int end,
		OrderByComparator<CommerceCurrency> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByC_P_A;
				finderArgs = new Object[] {companyId, primary, active};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByC_P_A;
			finderArgs = new Object[] {
				companyId, primary, active, start, end, orderByComparator
			};
		}

		List<CommerceCurrency> list = null;

		if (useFinderCache) {
			list = (List<CommerceCurrency>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceCurrency commerceCurrency : list) {
					if ((companyId != commerceCurrency.getCompanyId()) ||
						(primary != commerceCurrency.isPrimary()) ||
						(active != commerceCurrency.isActive())) {

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
					5 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_COMMERCECURRENCY_WHERE);

			query.append(_FINDER_COLUMN_C_P_A_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_P_A_PRIMARY_2);

			query.append(_FINDER_COLUMN_C_P_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceCurrencyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(primary);

				qPos.add(active);

				if (!pagination) {
					list = (List<CommerceCurrency>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceCurrency>)QueryUtil.list(
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
	 * Returns the first commerce currency in the ordered set where companyId = &#63; and primary = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce currency
	 * @throws NoSuchCurrencyException if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency findByC_P_A_First(
			long companyId, boolean primary, boolean active,
			OrderByComparator<CommerceCurrency> orderByComparator)
		throws NoSuchCurrencyException {

		CommerceCurrency commerceCurrency = fetchByC_P_A_First(
			companyId, primary, active, orderByComparator);

		if (commerceCurrency != null) {
			return commerceCurrency;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", primary=");
		msg.append(primary);

		msg.append(", active=");
		msg.append(active);

		msg.append("}");

		throw new NoSuchCurrencyException(msg.toString());
	}

	/**
	 * Returns the first commerce currency in the ordered set where companyId = &#63; and primary = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce currency, or <code>null</code> if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency fetchByC_P_A_First(
		long companyId, boolean primary, boolean active,
		OrderByComparator<CommerceCurrency> orderByComparator) {

		List<CommerceCurrency> list = findByC_P_A(
			companyId, primary, active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce currency in the ordered set where companyId = &#63; and primary = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce currency
	 * @throws NoSuchCurrencyException if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency findByC_P_A_Last(
			long companyId, boolean primary, boolean active,
			OrderByComparator<CommerceCurrency> orderByComparator)
		throws NoSuchCurrencyException {

		CommerceCurrency commerceCurrency = fetchByC_P_A_Last(
			companyId, primary, active, orderByComparator);

		if (commerceCurrency != null) {
			return commerceCurrency;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", primary=");
		msg.append(primary);

		msg.append(", active=");
		msg.append(active);

		msg.append("}");

		throw new NoSuchCurrencyException(msg.toString());
	}

	/**
	 * Returns the last commerce currency in the ordered set where companyId = &#63; and primary = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce currency, or <code>null</code> if a matching commerce currency could not be found
	 */
	@Override
	public CommerceCurrency fetchByC_P_A_Last(
		long companyId, boolean primary, boolean active,
		OrderByComparator<CommerceCurrency> orderByComparator) {

		int count = countByC_P_A(companyId, primary, active);

		if (count == 0) {
			return null;
		}

		List<CommerceCurrency> list = findByC_P_A(
			companyId, primary, active, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce currencies before and after the current commerce currency in the ordered set where companyId = &#63; and primary = &#63; and active = &#63;.
	 *
	 * @param commerceCurrencyId the primary key of the current commerce currency
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce currency
	 * @throws NoSuchCurrencyException if a commerce currency with the primary key could not be found
	 */
	@Override
	public CommerceCurrency[] findByC_P_A_PrevAndNext(
			long commerceCurrencyId, long companyId, boolean primary,
			boolean active,
			OrderByComparator<CommerceCurrency> orderByComparator)
		throws NoSuchCurrencyException {

		CommerceCurrency commerceCurrency = findByPrimaryKey(
			commerceCurrencyId);

		Session session = null;

		try {
			session = openSession();

			CommerceCurrency[] array = new CommerceCurrencyImpl[3];

			array[0] = getByC_P_A_PrevAndNext(
				session, commerceCurrency, companyId, primary, active,
				orderByComparator, true);

			array[1] = commerceCurrency;

			array[2] = getByC_P_A_PrevAndNext(
				session, commerceCurrency, companyId, primary, active,
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

	protected CommerceCurrency getByC_P_A_PrevAndNext(
		Session session, CommerceCurrency commerceCurrency, long companyId,
		boolean primary, boolean active,
		OrderByComparator<CommerceCurrency> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCECURRENCY_WHERE);

		query.append(_FINDER_COLUMN_C_P_A_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_P_A_PRIMARY_2);

		query.append(_FINDER_COLUMN_C_P_A_ACTIVE_2);

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
			query.append(CommerceCurrencyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(primary);

		qPos.add(active);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceCurrency)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceCurrency> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce currencies where companyId = &#63; and primary = &#63; and active = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param active the active
	 */
	@Override
	public void removeByC_P_A(long companyId, boolean primary, boolean active) {
		for (CommerceCurrency commerceCurrency :
				findByC_P_A(
					companyId, primary, active, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(commerceCurrency);
		}
	}

	/**
	 * Returns the number of commerce currencies where companyId = &#63; and primary = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param primary the primary
	 * @param active the active
	 * @return the number of matching commerce currencies
	 */
	@Override
	public int countByC_P_A(long companyId, boolean primary, boolean active) {
		FinderPath finderPath = _finderPathCountByC_P_A;

		Object[] finderArgs = new Object[] {companyId, primary, active};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_COMMERCECURRENCY_WHERE);

			query.append(_FINDER_COLUMN_C_P_A_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_P_A_PRIMARY_2);

			query.append(_FINDER_COLUMN_C_P_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(primary);

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

	private static final String _FINDER_COLUMN_C_P_A_COMPANYID_2 =
		"commerceCurrency.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_P_A_PRIMARY_2 =
		"commerceCurrency.primary = ? AND ";

	private static final String _FINDER_COLUMN_C_P_A_ACTIVE_2 =
		"commerceCurrency.active = ?";

	public CommerceCurrencyPersistenceImpl() {
		setModelClass(CommerceCurrency.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("code", "code_");
		dbColumnNames.put("primary", "primary_");
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
	 * Caches the commerce currency in the entity cache if it is enabled.
	 *
	 * @param commerceCurrency the commerce currency
	 */
	@Override
	public void cacheResult(CommerceCurrency commerceCurrency) {
		entityCache.putResult(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyImpl.class, commerceCurrency.getPrimaryKey(),
			commerceCurrency);

		finderCache.putResult(
			_finderPathFetchByC_C,
			new Object[] {
				commerceCurrency.getCompanyId(), commerceCurrency.getCode()
			},
			commerceCurrency);

		commerceCurrency.resetOriginalValues();
	}

	/**
	 * Caches the commerce currencies in the entity cache if it is enabled.
	 *
	 * @param commerceCurrencies the commerce currencies
	 */
	@Override
	public void cacheResult(List<CommerceCurrency> commerceCurrencies) {
		for (CommerceCurrency commerceCurrency : commerceCurrencies) {
			if (entityCache.getResult(
					CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
					CommerceCurrencyImpl.class,
					commerceCurrency.getPrimaryKey()) == null) {

				cacheResult(commerceCurrency);
			}
			else {
				commerceCurrency.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce currencies.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceCurrencyImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce currency.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceCurrency commerceCurrency) {
		entityCache.removeResult(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyImpl.class, commerceCurrency.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(CommerceCurrencyModelImpl)commerceCurrency, true);
	}

	@Override
	public void clearCache(List<CommerceCurrency> commerceCurrencies) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceCurrency commerceCurrency : commerceCurrencies) {
			entityCache.removeResult(
				CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
				CommerceCurrencyImpl.class, commerceCurrency.getPrimaryKey());

			clearUniqueFindersCache(
				(CommerceCurrencyModelImpl)commerceCurrency, true);
		}
	}

	protected void cacheUniqueFindersCache(
		CommerceCurrencyModelImpl commerceCurrencyModelImpl) {

		Object[] args = new Object[] {
			commerceCurrencyModelImpl.getCompanyId(),
			commerceCurrencyModelImpl.getCode()
		};

		finderCache.putResult(
			_finderPathCountByC_C, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_C, args, commerceCurrencyModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CommerceCurrencyModelImpl commerceCurrencyModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				commerceCurrencyModelImpl.getCompanyId(),
				commerceCurrencyModelImpl.getCode()
			};

			finderCache.removeResult(_finderPathCountByC_C, args);
			finderCache.removeResult(_finderPathFetchByC_C, args);
		}

		if ((commerceCurrencyModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_C.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				commerceCurrencyModelImpl.getOriginalCompanyId(),
				commerceCurrencyModelImpl.getOriginalCode()
			};

			finderCache.removeResult(_finderPathCountByC_C, args);
			finderCache.removeResult(_finderPathFetchByC_C, args);
		}
	}

	/**
	 * Creates a new commerce currency with the primary key. Does not add the commerce currency to the database.
	 *
	 * @param commerceCurrencyId the primary key for the new commerce currency
	 * @return the new commerce currency
	 */
	@Override
	public CommerceCurrency create(long commerceCurrencyId) {
		CommerceCurrency commerceCurrency = new CommerceCurrencyImpl();

		commerceCurrency.setNew(true);
		commerceCurrency.setPrimaryKey(commerceCurrencyId);

		String uuid = PortalUUIDUtil.generate();

		commerceCurrency.setUuid(uuid);

		commerceCurrency.setCompanyId(CompanyThreadLocal.getCompanyId());

		return commerceCurrency;
	}

	/**
	 * Removes the commerce currency with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceCurrencyId the primary key of the commerce currency
	 * @return the commerce currency that was removed
	 * @throws NoSuchCurrencyException if a commerce currency with the primary key could not be found
	 */
	@Override
	public CommerceCurrency remove(long commerceCurrencyId)
		throws NoSuchCurrencyException {

		return remove((Serializable)commerceCurrencyId);
	}

	/**
	 * Removes the commerce currency with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce currency
	 * @return the commerce currency that was removed
	 * @throws NoSuchCurrencyException if a commerce currency with the primary key could not be found
	 */
	@Override
	public CommerceCurrency remove(Serializable primaryKey)
		throws NoSuchCurrencyException {

		Session session = null;

		try {
			session = openSession();

			CommerceCurrency commerceCurrency = (CommerceCurrency)session.get(
				CommerceCurrencyImpl.class, primaryKey);

			if (commerceCurrency == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCurrencyException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commerceCurrency);
		}
		catch (NoSuchCurrencyException nsee) {
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
	protected CommerceCurrency removeImpl(CommerceCurrency commerceCurrency) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceCurrency)) {
				commerceCurrency = (CommerceCurrency)session.get(
					CommerceCurrencyImpl.class,
					commerceCurrency.getPrimaryKeyObj());
			}

			if (commerceCurrency != null) {
				session.delete(commerceCurrency);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceCurrency != null) {
			clearCache(commerceCurrency);
		}

		return commerceCurrency;
	}

	@Override
	public CommerceCurrency updateImpl(CommerceCurrency commerceCurrency) {
		boolean isNew = commerceCurrency.isNew();

		if (!(commerceCurrency instanceof CommerceCurrencyModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceCurrency.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					commerceCurrency);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceCurrency proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceCurrency implementation " +
					commerceCurrency.getClass());
		}

		CommerceCurrencyModelImpl commerceCurrencyModelImpl =
			(CommerceCurrencyModelImpl)commerceCurrency;

		if (Validator.isNull(commerceCurrency.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			commerceCurrency.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceCurrency.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceCurrency.setCreateDate(now);
			}
			else {
				commerceCurrency.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!commerceCurrencyModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceCurrency.setModifiedDate(now);
			}
			else {
				commerceCurrency.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceCurrency.isNew()) {
				session.save(commerceCurrency);

				commerceCurrency.setNew(false);
			}
			else {
				commerceCurrency = (CommerceCurrency)session.merge(
					commerceCurrency);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceCurrencyModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {commerceCurrencyModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				commerceCurrencyModelImpl.getUuid(),
				commerceCurrencyModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {commerceCurrencyModelImpl.getCompanyId()};

			finderCache.removeResult(_finderPathCountByCompanyId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCompanyId, args);

			args = new Object[] {
				commerceCurrencyModelImpl.getCompanyId(),
				commerceCurrencyModelImpl.isPrimary()
			};

			finderCache.removeResult(_finderPathCountByC_P, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_P, args);

			args = new Object[] {
				commerceCurrencyModelImpl.getCompanyId(),
				commerceCurrencyModelImpl.isActive()
			};

			finderCache.removeResult(_finderPathCountByC_A, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_A, args);

			args = new Object[] {
				commerceCurrencyModelImpl.getCompanyId(),
				commerceCurrencyModelImpl.isPrimary(),
				commerceCurrencyModelImpl.isActive()
			};

			finderCache.removeResult(_finderPathCountByC_P_A, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_P_A, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((commerceCurrencyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceCurrencyModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {commerceCurrencyModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((commerceCurrencyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceCurrencyModelImpl.getOriginalUuid(),
					commerceCurrencyModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					commerceCurrencyModelImpl.getUuid(),
					commerceCurrencyModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((commerceCurrencyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCompanyId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceCurrencyModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);

				args = new Object[] {commerceCurrencyModelImpl.getCompanyId()};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);
			}

			if ((commerceCurrencyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_P.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceCurrencyModelImpl.getOriginalCompanyId(),
					commerceCurrencyModelImpl.getOriginalPrimary()
				};

				finderCache.removeResult(_finderPathCountByC_P, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_P, args);

				args = new Object[] {
					commerceCurrencyModelImpl.getCompanyId(),
					commerceCurrencyModelImpl.isPrimary()
				};

				finderCache.removeResult(_finderPathCountByC_P, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_P, args);
			}

			if ((commerceCurrencyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_A.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceCurrencyModelImpl.getOriginalCompanyId(),
					commerceCurrencyModelImpl.getOriginalActive()
				};

				finderCache.removeResult(_finderPathCountByC_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_A, args);

				args = new Object[] {
					commerceCurrencyModelImpl.getCompanyId(),
					commerceCurrencyModelImpl.isActive()
				};

				finderCache.removeResult(_finderPathCountByC_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_A, args);
			}

			if ((commerceCurrencyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_P_A.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceCurrencyModelImpl.getOriginalCompanyId(),
					commerceCurrencyModelImpl.getOriginalPrimary(),
					commerceCurrencyModelImpl.getOriginalActive()
				};

				finderCache.removeResult(_finderPathCountByC_P_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_P_A, args);

				args = new Object[] {
					commerceCurrencyModelImpl.getCompanyId(),
					commerceCurrencyModelImpl.isPrimary(),
					commerceCurrencyModelImpl.isActive()
				};

				finderCache.removeResult(_finderPathCountByC_P_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_P_A, args);
			}
		}

		entityCache.putResult(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyImpl.class, commerceCurrency.getPrimaryKey(),
			commerceCurrency, false);

		clearUniqueFindersCache(commerceCurrencyModelImpl, false);
		cacheUniqueFindersCache(commerceCurrencyModelImpl);

		commerceCurrency.resetOriginalValues();

		return commerceCurrency;
	}

	/**
	 * Returns the commerce currency with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce currency
	 * @return the commerce currency
	 * @throws NoSuchCurrencyException if a commerce currency with the primary key could not be found
	 */
	@Override
	public CommerceCurrency findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCurrencyException {

		CommerceCurrency commerceCurrency = fetchByPrimaryKey(primaryKey);

		if (commerceCurrency == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCurrencyException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commerceCurrency;
	}

	/**
	 * Returns the commerce currency with the primary key or throws a <code>NoSuchCurrencyException</code> if it could not be found.
	 *
	 * @param commerceCurrencyId the primary key of the commerce currency
	 * @return the commerce currency
	 * @throws NoSuchCurrencyException if a commerce currency with the primary key could not be found
	 */
	@Override
	public CommerceCurrency findByPrimaryKey(long commerceCurrencyId)
		throws NoSuchCurrencyException {

		return findByPrimaryKey((Serializable)commerceCurrencyId);
	}

	/**
	 * Returns the commerce currency with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce currency
	 * @return the commerce currency, or <code>null</code> if a commerce currency with the primary key could not be found
	 */
	@Override
	public CommerceCurrency fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceCurrency commerceCurrency = (CommerceCurrency)serializable;

		if (commerceCurrency == null) {
			Session session = null;

			try {
				session = openSession();

				commerceCurrency = (CommerceCurrency)session.get(
					CommerceCurrencyImpl.class, primaryKey);

				if (commerceCurrency != null) {
					cacheResult(commerceCurrency);
				}
				else {
					entityCache.putResult(
						CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
						CommerceCurrencyImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
					CommerceCurrencyImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceCurrency;
	}

	/**
	 * Returns the commerce currency with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceCurrencyId the primary key of the commerce currency
	 * @return the commerce currency, or <code>null</code> if a commerce currency with the primary key could not be found
	 */
	@Override
	public CommerceCurrency fetchByPrimaryKey(long commerceCurrencyId) {
		return fetchByPrimaryKey((Serializable)commerceCurrencyId);
	}

	@Override
	public Map<Serializable, CommerceCurrency> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceCurrency> map =
			new HashMap<Serializable, CommerceCurrency>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceCurrency commerceCurrency = fetchByPrimaryKey(primaryKey);

			if (commerceCurrency != null) {
				map.put(primaryKey, commerceCurrency);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
				CommerceCurrencyImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceCurrency)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_COMMERCECURRENCY_WHERE_PKS_IN);

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

			for (CommerceCurrency commerceCurrency :
					(List<CommerceCurrency>)q.list()) {

				map.put(commerceCurrency.getPrimaryKeyObj(), commerceCurrency);

				cacheResult(commerceCurrency);

				uncachedPrimaryKeys.remove(commerceCurrency.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
					CommerceCurrencyImpl.class, primaryKey, nullModel);
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
	 * Returns all the commerce currencies.
	 *
	 * @return the commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce currencies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceCurrencyModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce currencies
	 * @param end the upper bound of the range of commerce currencies (not inclusive)
	 * @return the range of commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce currencies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceCurrencyModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce currencies
	 * @param end the upper bound of the range of commerce currencies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findAll(
		int start, int end,
		OrderByComparator<CommerceCurrency> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce currencies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceCurrencyModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce currencies
	 * @param end the upper bound of the range of commerce currencies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce currencies
	 */
	@Override
	public List<CommerceCurrency> findAll(
		int start, int end,
		OrderByComparator<CommerceCurrency> orderByComparator,
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

		List<CommerceCurrency> list = null;

		if (useFinderCache) {
			list = (List<CommerceCurrency>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCECURRENCY);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCECURRENCY;

				if (pagination) {
					sql = sql.concat(CommerceCurrencyModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceCurrency>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceCurrency>)QueryUtil.list(
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
	 * Removes all the commerce currencies from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceCurrency commerceCurrency : findAll()) {
			remove(commerceCurrency);
		}
	}

	/**
	 * Returns the number of commerce currencies.
	 *
	 * @return the number of commerce currencies
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCECURRENCY);

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
		return CommerceCurrencyModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce currency persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyModelImpl.FINDER_CACHE_ENABLED,
			CommerceCurrencyImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyModelImpl.FINDER_CACHE_ENABLED,
			CommerceCurrencyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyModelImpl.FINDER_CACHE_ENABLED,
			CommerceCurrencyImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyModelImpl.FINDER_CACHE_ENABLED,
			CommerceCurrencyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			CommerceCurrencyModelImpl.UUID_COLUMN_BITMASK |
			CommerceCurrencyModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyModelImpl.FINDER_CACHE_ENABLED,
			CommerceCurrencyImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyModelImpl.FINDER_CACHE_ENABLED,
			CommerceCurrencyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			CommerceCurrencyModelImpl.UUID_COLUMN_BITMASK |
			CommerceCurrencyModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceCurrencyModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByCompanyId = new FinderPath(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyModelImpl.FINDER_CACHE_ENABLED,
			CommerceCurrencyImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCompanyId = new FinderPath(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyModelImpl.FINDER_CACHE_ENABLED,
			CommerceCurrencyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] {Long.class.getName()},
			CommerceCurrencyModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceCurrencyModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByCompanyId = new FinderPath(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] {Long.class.getName()});

		_finderPathFetchByC_C = new FinderPath(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyModelImpl.FINDER_CACHE_ENABLED,
			CommerceCurrencyImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByC_C",
			new String[] {Long.class.getName(), String.class.getName()},
			CommerceCurrencyModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceCurrencyModelImpl.CODE_COLUMN_BITMASK);

		_finderPathCountByC_C = new FinderPath(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] {Long.class.getName(), String.class.getName()});

		_finderPathWithPaginationFindByC_P = new FinderPath(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyModelImpl.FINDER_CACHE_ENABLED,
			CommerceCurrencyImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_P",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_P = new FinderPath(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyModelImpl.FINDER_CACHE_ENABLED,
			CommerceCurrencyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_P",
			new String[] {Long.class.getName(), Boolean.class.getName()},
			CommerceCurrencyModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceCurrencyModelImpl.PRIMARY_COLUMN_BITMASK |
			CommerceCurrencyModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByC_P = new FinderPath(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_P",
			new String[] {Long.class.getName(), Boolean.class.getName()});

		_finderPathWithPaginationFindByC_A = new FinderPath(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyModelImpl.FINDER_CACHE_ENABLED,
			CommerceCurrencyImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_A = new FinderPath(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyModelImpl.FINDER_CACHE_ENABLED,
			CommerceCurrencyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_A",
			new String[] {Long.class.getName(), Boolean.class.getName()},
			CommerceCurrencyModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceCurrencyModelImpl.ACTIVE_COLUMN_BITMASK |
			CommerceCurrencyModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByC_A = new FinderPath(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_A",
			new String[] {Long.class.getName(), Boolean.class.getName()});

		_finderPathWithPaginationFindByC_P_A = new FinderPath(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyModelImpl.FINDER_CACHE_ENABLED,
			CommerceCurrencyImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_P_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_P_A = new FinderPath(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyModelImpl.FINDER_CACHE_ENABLED,
			CommerceCurrencyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_P_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			},
			CommerceCurrencyModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceCurrencyModelImpl.PRIMARY_COLUMN_BITMASK |
			CommerceCurrencyModelImpl.ACTIVE_COLUMN_BITMASK |
			CommerceCurrencyModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByC_P_A = new FinderPath(
			CommerceCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCurrencyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_P_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			});
	}

	public void destroy() {
		entityCache.removeCache(CommerceCurrencyImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_COMMERCECURRENCY =
		"SELECT commerceCurrency FROM CommerceCurrency commerceCurrency";

	private static final String _SQL_SELECT_COMMERCECURRENCY_WHERE_PKS_IN =
		"SELECT commerceCurrency FROM CommerceCurrency commerceCurrency WHERE commerceCurrencyId IN (";

	private static final String _SQL_SELECT_COMMERCECURRENCY_WHERE =
		"SELECT commerceCurrency FROM CommerceCurrency commerceCurrency WHERE ";

	private static final String _SQL_COUNT_COMMERCECURRENCY =
		"SELECT COUNT(commerceCurrency) FROM CommerceCurrency commerceCurrency";

	private static final String _SQL_COUNT_COMMERCECURRENCY_WHERE =
		"SELECT COUNT(commerceCurrency) FROM CommerceCurrency commerceCurrency WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceCurrency.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommerceCurrency exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommerceCurrency exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceCurrencyPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "code", "primary", "active"});

}