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

import com.liferay.commerce.price.list.exception.NoSuchTierPriceEntryException;
import com.liferay.commerce.price.list.model.CommerceTierPriceEntry;
import com.liferay.commerce.price.list.model.impl.CommerceTierPriceEntryImpl;
import com.liferay.commerce.price.list.model.impl.CommerceTierPriceEntryModelImpl;
import com.liferay.commerce.price.list.service.persistence.CommerceTierPriceEntryPersistence;
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
import com.liferay.portal.kernel.util.StringUtil;
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
 * The persistence implementation for the commerce tier price entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
@ProviderType
public class CommerceTierPriceEntryPersistenceImpl
	extends BasePersistenceImpl<CommerceTierPriceEntry>
	implements CommerceTierPriceEntryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommerceTierPriceEntryUtil</code> to access the commerce tier price entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommerceTierPriceEntryImpl.class.getName();

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
	 * Returns all the commerce tier price entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce tier price entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTierPriceEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce tier price entries
	 * @param end the upper bound of the range of commerce tier price entries (not inclusive)
	 * @return the range of matching commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce tier price entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTierPriceEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce tier price entries
	 * @param end the upper bound of the range of commerce tier price entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce tier price entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTierPriceEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce tier price entries
	 * @param end the upper bound of the range of commerce tier price entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator,
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

		List<CommerceTierPriceEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceTierPriceEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceTierPriceEntry commerceTierPriceEntry : list) {
					if (!uuid.equals(commerceTierPriceEntry.getUuid())) {
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

			query.append(_SQL_SELECT_COMMERCETIERPRICEENTRY_WHERE);

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
				query.append(CommerceTierPriceEntryModelImpl.ORDER_BY_JPQL);
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
					list = (List<CommerceTierPriceEntry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceTierPriceEntry>)QueryUtil.list(
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
	 * Returns the first commerce tier price entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce tier price entry
	 * @throws NoSuchTierPriceEntryException if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry findByUuid_First(
			String uuid,
			OrderByComparator<CommerceTierPriceEntry> orderByComparator)
		throws NoSuchTierPriceEntryException {

		CommerceTierPriceEntry commerceTierPriceEntry = fetchByUuid_First(
			uuid, orderByComparator);

		if (commerceTierPriceEntry != null) {
			return commerceTierPriceEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchTierPriceEntryException(msg.toString());
	}

	/**
	 * Returns the first commerce tier price entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce tier price entry, or <code>null</code> if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry fetchByUuid_First(
		String uuid,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator) {

		List<CommerceTierPriceEntry> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce tier price entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce tier price entry
	 * @throws NoSuchTierPriceEntryException if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry findByUuid_Last(
			String uuid,
			OrderByComparator<CommerceTierPriceEntry> orderByComparator)
		throws NoSuchTierPriceEntryException {

		CommerceTierPriceEntry commerceTierPriceEntry = fetchByUuid_Last(
			uuid, orderByComparator);

		if (commerceTierPriceEntry != null) {
			return commerceTierPriceEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchTierPriceEntryException(msg.toString());
	}

	/**
	 * Returns the last commerce tier price entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce tier price entry, or <code>null</code> if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry fetchByUuid_Last(
		String uuid,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CommerceTierPriceEntry> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce tier price entries before and after the current commerce tier price entry in the ordered set where uuid = &#63;.
	 *
	 * @param commerceTierPriceEntryId the primary key of the current commerce tier price entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce tier price entry
	 * @throws NoSuchTierPriceEntryException if a commerce tier price entry with the primary key could not be found
	 */
	@Override
	public CommerceTierPriceEntry[] findByUuid_PrevAndNext(
			long commerceTierPriceEntryId, String uuid,
			OrderByComparator<CommerceTierPriceEntry> orderByComparator)
		throws NoSuchTierPriceEntryException {

		uuid = Objects.toString(uuid, "");

		CommerceTierPriceEntry commerceTierPriceEntry = findByPrimaryKey(
			commerceTierPriceEntryId);

		Session session = null;

		try {
			session = openSession();

			CommerceTierPriceEntry[] array = new CommerceTierPriceEntryImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, commerceTierPriceEntry, uuid, orderByComparator, true);

			array[1] = commerceTierPriceEntry;

			array[2] = getByUuid_PrevAndNext(
				session, commerceTierPriceEntry, uuid, orderByComparator,
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

	protected CommerceTierPriceEntry getByUuid_PrevAndNext(
		Session session, CommerceTierPriceEntry commerceTierPriceEntry,
		String uuid,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCETIERPRICEENTRY_WHERE);

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
			query.append(CommerceTierPriceEntryModelImpl.ORDER_BY_JPQL);
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
						commerceTierPriceEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceTierPriceEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce tier price entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CommerceTierPriceEntry commerceTierPriceEntry :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commerceTierPriceEntry);
		}
	}

	/**
	 * Returns the number of commerce tier price entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching commerce tier price entries
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCETIERPRICEENTRY_WHERE);

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
		"commerceTierPriceEntry.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(commerceTierPriceEntry.uuid IS NULL OR commerceTierPriceEntry.uuid = '')";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the commerce tier price entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> findByUuid_C(
		String uuid, long companyId) {

		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce tier price entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTierPriceEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce tier price entries
	 * @param end the upper bound of the range of commerce tier price entries (not inclusive)
	 * @return the range of matching commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce tier price entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTierPriceEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce tier price entries
	 * @param end the upper bound of the range of commerce tier price entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce tier price entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTierPriceEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce tier price entries
	 * @param end the upper bound of the range of commerce tier price entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator,
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

		List<CommerceTierPriceEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceTierPriceEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceTierPriceEntry commerceTierPriceEntry : list) {
					if (!uuid.equals(commerceTierPriceEntry.getUuid()) ||
						(companyId != commerceTierPriceEntry.getCompanyId())) {

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

			query.append(_SQL_SELECT_COMMERCETIERPRICEENTRY_WHERE);

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
				query.append(CommerceTierPriceEntryModelImpl.ORDER_BY_JPQL);
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
					list = (List<CommerceTierPriceEntry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceTierPriceEntry>)QueryUtil.list(
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
	 * Returns the first commerce tier price entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce tier price entry
	 * @throws NoSuchTierPriceEntryException if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CommerceTierPriceEntry> orderByComparator)
		throws NoSuchTierPriceEntryException {

		CommerceTierPriceEntry commerceTierPriceEntry = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (commerceTierPriceEntry != null) {
			return commerceTierPriceEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchTierPriceEntryException(msg.toString());
	}

	/**
	 * Returns the first commerce tier price entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce tier price entry, or <code>null</code> if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator) {

		List<CommerceTierPriceEntry> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce tier price entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce tier price entry
	 * @throws NoSuchTierPriceEntryException if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CommerceTierPriceEntry> orderByComparator)
		throws NoSuchTierPriceEntryException {

		CommerceTierPriceEntry commerceTierPriceEntry = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (commerceTierPriceEntry != null) {
			return commerceTierPriceEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchTierPriceEntryException(msg.toString());
	}

	/**
	 * Returns the last commerce tier price entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce tier price entry, or <code>null</code> if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CommerceTierPriceEntry> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce tier price entries before and after the current commerce tier price entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param commerceTierPriceEntryId the primary key of the current commerce tier price entry
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce tier price entry
	 * @throws NoSuchTierPriceEntryException if a commerce tier price entry with the primary key could not be found
	 */
	@Override
	public CommerceTierPriceEntry[] findByUuid_C_PrevAndNext(
			long commerceTierPriceEntryId, String uuid, long companyId,
			OrderByComparator<CommerceTierPriceEntry> orderByComparator)
		throws NoSuchTierPriceEntryException {

		uuid = Objects.toString(uuid, "");

		CommerceTierPriceEntry commerceTierPriceEntry = findByPrimaryKey(
			commerceTierPriceEntryId);

		Session session = null;

		try {
			session = openSession();

			CommerceTierPriceEntry[] array = new CommerceTierPriceEntryImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, commerceTierPriceEntry, uuid, companyId,
				orderByComparator, true);

			array[1] = commerceTierPriceEntry;

			array[2] = getByUuid_C_PrevAndNext(
				session, commerceTierPriceEntry, uuid, companyId,
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

	protected CommerceTierPriceEntry getByUuid_C_PrevAndNext(
		Session session, CommerceTierPriceEntry commerceTierPriceEntry,
		String uuid, long companyId,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCETIERPRICEENTRY_WHERE);

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
			query.append(CommerceTierPriceEntryModelImpl.ORDER_BY_JPQL);
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
						commerceTierPriceEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceTierPriceEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce tier price entries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CommerceTierPriceEntry commerceTierPriceEntry :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceTierPriceEntry);
		}
	}

	/**
	 * Returns the number of commerce tier price entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching commerce tier price entries
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCETIERPRICEENTRY_WHERE);

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
		"commerceTierPriceEntry.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(commerceTierPriceEntry.uuid IS NULL OR commerceTierPriceEntry.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"commerceTierPriceEntry.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the commerce tier price entries where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce tier price entries where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTierPriceEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce tier price entries
	 * @param end the upper bound of the range of commerce tier price entries (not inclusive)
	 * @return the range of matching commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce tier price entries where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTierPriceEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce tier price entries
	 * @param end the upper bound of the range of commerce tier price entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce tier price entries where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTierPriceEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce tier price entries
	 * @param end the upper bound of the range of commerce tier price entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator,
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

		List<CommerceTierPriceEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceTierPriceEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceTierPriceEntry commerceTierPriceEntry : list) {
					if ((companyId != commerceTierPriceEntry.getCompanyId())) {
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

			query.append(_SQL_SELECT_COMMERCETIERPRICEENTRY_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceTierPriceEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<CommerceTierPriceEntry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceTierPriceEntry>)QueryUtil.list(
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
	 * Returns the first commerce tier price entry in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce tier price entry
	 * @throws NoSuchTierPriceEntryException if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry findByCompanyId_First(
			long companyId,
			OrderByComparator<CommerceTierPriceEntry> orderByComparator)
		throws NoSuchTierPriceEntryException {

		CommerceTierPriceEntry commerceTierPriceEntry = fetchByCompanyId_First(
			companyId, orderByComparator);

		if (commerceTierPriceEntry != null) {
			return commerceTierPriceEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchTierPriceEntryException(msg.toString());
	}

	/**
	 * Returns the first commerce tier price entry in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce tier price entry, or <code>null</code> if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry fetchByCompanyId_First(
		long companyId,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator) {

		List<CommerceTierPriceEntry> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce tier price entry in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce tier price entry
	 * @throws NoSuchTierPriceEntryException if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry findByCompanyId_Last(
			long companyId,
			OrderByComparator<CommerceTierPriceEntry> orderByComparator)
		throws NoSuchTierPriceEntryException {

		CommerceTierPriceEntry commerceTierPriceEntry = fetchByCompanyId_Last(
			companyId, orderByComparator);

		if (commerceTierPriceEntry != null) {
			return commerceTierPriceEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchTierPriceEntryException(msg.toString());
	}

	/**
	 * Returns the last commerce tier price entry in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce tier price entry, or <code>null</code> if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry fetchByCompanyId_Last(
		long companyId,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<CommerceTierPriceEntry> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce tier price entries before and after the current commerce tier price entry in the ordered set where companyId = &#63;.
	 *
	 * @param commerceTierPriceEntryId the primary key of the current commerce tier price entry
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce tier price entry
	 * @throws NoSuchTierPriceEntryException if a commerce tier price entry with the primary key could not be found
	 */
	@Override
	public CommerceTierPriceEntry[] findByCompanyId_PrevAndNext(
			long commerceTierPriceEntryId, long companyId,
			OrderByComparator<CommerceTierPriceEntry> orderByComparator)
		throws NoSuchTierPriceEntryException {

		CommerceTierPriceEntry commerceTierPriceEntry = findByPrimaryKey(
			commerceTierPriceEntryId);

		Session session = null;

		try {
			session = openSession();

			CommerceTierPriceEntry[] array = new CommerceTierPriceEntryImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, commerceTierPriceEntry, companyId, orderByComparator,
				true);

			array[1] = commerceTierPriceEntry;

			array[2] = getByCompanyId_PrevAndNext(
				session, commerceTierPriceEntry, companyId, orderByComparator,
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

	protected CommerceTierPriceEntry getByCompanyId_PrevAndNext(
		Session session, CommerceTierPriceEntry commerceTierPriceEntry,
		long companyId,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCETIERPRICEENTRY_WHERE);

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
			query.append(CommerceTierPriceEntryModelImpl.ORDER_BY_JPQL);
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
						commerceTierPriceEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceTierPriceEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce tier price entries where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (CommerceTierPriceEntry commerceTierPriceEntry :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commerceTierPriceEntry);
		}
	}

	/**
	 * Returns the number of commerce tier price entries where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce tier price entries
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] {companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCETIERPRICEENTRY_WHERE);

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
		"commerceTierPriceEntry.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByCommercePriceEntryId;
	private FinderPath _finderPathWithoutPaginationFindByCommercePriceEntryId;
	private FinderPath _finderPathCountByCommercePriceEntryId;

	/**
	 * Returns all the commerce tier price entries where commercePriceEntryId = &#63;.
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 * @return the matching commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> findByCommercePriceEntryId(
		long commercePriceEntryId) {

		return findByCommercePriceEntryId(
			commercePriceEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce tier price entries where commercePriceEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTierPriceEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 * @param start the lower bound of the range of commerce tier price entries
	 * @param end the upper bound of the range of commerce tier price entries (not inclusive)
	 * @return the range of matching commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> findByCommercePriceEntryId(
		long commercePriceEntryId, int start, int end) {

		return findByCommercePriceEntryId(
			commercePriceEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce tier price entries where commercePriceEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTierPriceEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 * @param start the lower bound of the range of commerce tier price entries
	 * @param end the upper bound of the range of commerce tier price entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> findByCommercePriceEntryId(
		long commercePriceEntryId, int start, int end,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator) {

		return findByCommercePriceEntryId(
			commercePriceEntryId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce tier price entries where commercePriceEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTierPriceEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 * @param start the lower bound of the range of commerce tier price entries
	 * @param end the upper bound of the range of commerce tier price entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> findByCommercePriceEntryId(
		long commercePriceEntryId, int start, int end,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByCommercePriceEntryId;
			finderArgs = new Object[] {commercePriceEntryId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByCommercePriceEntryId;
			finderArgs = new Object[] {
				commercePriceEntryId, start, end, orderByComparator
			};
		}

		List<CommerceTierPriceEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceTierPriceEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceTierPriceEntry commerceTierPriceEntry : list) {
					if ((commercePriceEntryId !=
							commerceTierPriceEntry.getCommercePriceEntryId())) {

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

			query.append(_SQL_SELECT_COMMERCETIERPRICEENTRY_WHERE);

			query.append(
				_FINDER_COLUMN_COMMERCEPRICEENTRYID_COMMERCEPRICEENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceTierPriceEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commercePriceEntryId);

				if (!pagination) {
					list = (List<CommerceTierPriceEntry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceTierPriceEntry>)QueryUtil.list(
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
	 * Returns the first commerce tier price entry in the ordered set where commercePriceEntryId = &#63;.
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce tier price entry
	 * @throws NoSuchTierPriceEntryException if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry findByCommercePriceEntryId_First(
			long commercePriceEntryId,
			OrderByComparator<CommerceTierPriceEntry> orderByComparator)
		throws NoSuchTierPriceEntryException {

		CommerceTierPriceEntry commerceTierPriceEntry =
			fetchByCommercePriceEntryId_First(
				commercePriceEntryId, orderByComparator);

		if (commerceTierPriceEntry != null) {
			return commerceTierPriceEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commercePriceEntryId=");
		msg.append(commercePriceEntryId);

		msg.append("}");

		throw new NoSuchTierPriceEntryException(msg.toString());
	}

	/**
	 * Returns the first commerce tier price entry in the ordered set where commercePriceEntryId = &#63;.
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce tier price entry, or <code>null</code> if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry fetchByCommercePriceEntryId_First(
		long commercePriceEntryId,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator) {

		List<CommerceTierPriceEntry> list = findByCommercePriceEntryId(
			commercePriceEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce tier price entry in the ordered set where commercePriceEntryId = &#63;.
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce tier price entry
	 * @throws NoSuchTierPriceEntryException if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry findByCommercePriceEntryId_Last(
			long commercePriceEntryId,
			OrderByComparator<CommerceTierPriceEntry> orderByComparator)
		throws NoSuchTierPriceEntryException {

		CommerceTierPriceEntry commerceTierPriceEntry =
			fetchByCommercePriceEntryId_Last(
				commercePriceEntryId, orderByComparator);

		if (commerceTierPriceEntry != null) {
			return commerceTierPriceEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commercePriceEntryId=");
		msg.append(commercePriceEntryId);

		msg.append("}");

		throw new NoSuchTierPriceEntryException(msg.toString());
	}

	/**
	 * Returns the last commerce tier price entry in the ordered set where commercePriceEntryId = &#63;.
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce tier price entry, or <code>null</code> if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry fetchByCommercePriceEntryId_Last(
		long commercePriceEntryId,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator) {

		int count = countByCommercePriceEntryId(commercePriceEntryId);

		if (count == 0) {
			return null;
		}

		List<CommerceTierPriceEntry> list = findByCommercePriceEntryId(
			commercePriceEntryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce tier price entries before and after the current commerce tier price entry in the ordered set where commercePriceEntryId = &#63;.
	 *
	 * @param commerceTierPriceEntryId the primary key of the current commerce tier price entry
	 * @param commercePriceEntryId the commerce price entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce tier price entry
	 * @throws NoSuchTierPriceEntryException if a commerce tier price entry with the primary key could not be found
	 */
	@Override
	public CommerceTierPriceEntry[] findByCommercePriceEntryId_PrevAndNext(
			long commerceTierPriceEntryId, long commercePriceEntryId,
			OrderByComparator<CommerceTierPriceEntry> orderByComparator)
		throws NoSuchTierPriceEntryException {

		CommerceTierPriceEntry commerceTierPriceEntry = findByPrimaryKey(
			commerceTierPriceEntryId);

		Session session = null;

		try {
			session = openSession();

			CommerceTierPriceEntry[] array = new CommerceTierPriceEntryImpl[3];

			array[0] = getByCommercePriceEntryId_PrevAndNext(
				session, commerceTierPriceEntry, commercePriceEntryId,
				orderByComparator, true);

			array[1] = commerceTierPriceEntry;

			array[2] = getByCommercePriceEntryId_PrevAndNext(
				session, commerceTierPriceEntry, commercePriceEntryId,
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

	protected CommerceTierPriceEntry getByCommercePriceEntryId_PrevAndNext(
		Session session, CommerceTierPriceEntry commerceTierPriceEntry,
		long commercePriceEntryId,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCETIERPRICEENTRY_WHERE);

		query.append(
			_FINDER_COLUMN_COMMERCEPRICEENTRYID_COMMERCEPRICEENTRYID_2);

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
			query.append(CommerceTierPriceEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commercePriceEntryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceTierPriceEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceTierPriceEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce tier price entries where commercePriceEntryId = &#63; from the database.
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 */
	@Override
	public void removeByCommercePriceEntryId(long commercePriceEntryId) {
		for (CommerceTierPriceEntry commerceTierPriceEntry :
				findByCommercePriceEntryId(
					commercePriceEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceTierPriceEntry);
		}
	}

	/**
	 * Returns the number of commerce tier price entries where commercePriceEntryId = &#63;.
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 * @return the number of matching commerce tier price entries
	 */
	@Override
	public int countByCommercePriceEntryId(long commercePriceEntryId) {
		FinderPath finderPath = _finderPathCountByCommercePriceEntryId;

		Object[] finderArgs = new Object[] {commercePriceEntryId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCETIERPRICEENTRY_WHERE);

			query.append(
				_FINDER_COLUMN_COMMERCEPRICEENTRYID_COMMERCEPRICEENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commercePriceEntryId);

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
		_FINDER_COLUMN_COMMERCEPRICEENTRYID_COMMERCEPRICEENTRYID_2 =
			"commerceTierPriceEntry.commercePriceEntryId = ?";

	private FinderPath _finderPathFetchByC_M;
	private FinderPath _finderPathCountByC_M;

	/**
	 * Returns the commerce tier price entry where commercePriceEntryId = &#63; and minQuantity = &#63; or throws a <code>NoSuchTierPriceEntryException</code> if it could not be found.
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 * @param minQuantity the min quantity
	 * @return the matching commerce tier price entry
	 * @throws NoSuchTierPriceEntryException if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry findByC_M(
			long commercePriceEntryId, int minQuantity)
		throws NoSuchTierPriceEntryException {

		CommerceTierPriceEntry commerceTierPriceEntry = fetchByC_M(
			commercePriceEntryId, minQuantity);

		if (commerceTierPriceEntry == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("commercePriceEntryId=");
			msg.append(commercePriceEntryId);

			msg.append(", minQuantity=");
			msg.append(minQuantity);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchTierPriceEntryException(msg.toString());
		}

		return commerceTierPriceEntry;
	}

	/**
	 * Returns the commerce tier price entry where commercePriceEntryId = &#63; and minQuantity = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 * @param minQuantity the min quantity
	 * @return the matching commerce tier price entry, or <code>null</code> if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry fetchByC_M(
		long commercePriceEntryId, int minQuantity) {

		return fetchByC_M(commercePriceEntryId, minQuantity, true);
	}

	/**
	 * Returns the commerce tier price entry where commercePriceEntryId = &#63; and minQuantity = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 * @param minQuantity the min quantity
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching commerce tier price entry, or <code>null</code> if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry fetchByC_M(
		long commercePriceEntryId, int minQuantity, boolean retrieveFromCache) {

		Object[] finderArgs = new Object[] {commercePriceEntryId, minQuantity};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_M, finderArgs, this);
		}

		if (result instanceof CommerceTierPriceEntry) {
			CommerceTierPriceEntry commerceTierPriceEntry =
				(CommerceTierPriceEntry)result;

			if ((commercePriceEntryId !=
					commerceTierPriceEntry.getCommercePriceEntryId()) ||
				(minQuantity != commerceTierPriceEntry.getMinQuantity())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COMMERCETIERPRICEENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_M_COMMERCEPRICEENTRYID_2);

			query.append(_FINDER_COLUMN_C_M_MINQUANTITY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commercePriceEntryId);

				qPos.add(minQuantity);

				List<CommerceTierPriceEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByC_M, finderArgs, list);
				}
				else {
					CommerceTierPriceEntry commerceTierPriceEntry = list.get(0);

					result = commerceTierPriceEntry;

					cacheResult(commerceTierPriceEntry);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByC_M, finderArgs);

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
			return (CommerceTierPriceEntry)result;
		}
	}

	/**
	 * Removes the commerce tier price entry where commercePriceEntryId = &#63; and minQuantity = &#63; from the database.
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 * @param minQuantity the min quantity
	 * @return the commerce tier price entry that was removed
	 */
	@Override
	public CommerceTierPriceEntry removeByC_M(
			long commercePriceEntryId, int minQuantity)
		throws NoSuchTierPriceEntryException {

		CommerceTierPriceEntry commerceTierPriceEntry = findByC_M(
			commercePriceEntryId, minQuantity);

		return remove(commerceTierPriceEntry);
	}

	/**
	 * Returns the number of commerce tier price entries where commercePriceEntryId = &#63; and minQuantity = &#63;.
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 * @param minQuantity the min quantity
	 * @return the number of matching commerce tier price entries
	 */
	@Override
	public int countByC_M(long commercePriceEntryId, int minQuantity) {
		FinderPath finderPath = _finderPathCountByC_M;

		Object[] finderArgs = new Object[] {commercePriceEntryId, minQuantity};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCETIERPRICEENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_M_COMMERCEPRICEENTRYID_2);

			query.append(_FINDER_COLUMN_C_M_MINQUANTITY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commercePriceEntryId);

				qPos.add(minQuantity);

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

	private static final String _FINDER_COLUMN_C_M_COMMERCEPRICEENTRYID_2 =
		"commerceTierPriceEntry.commercePriceEntryId = ? AND ";

	private static final String _FINDER_COLUMN_C_M_MINQUANTITY_2 =
		"commerceTierPriceEntry.minQuantity = ?";

	private FinderPath _finderPathWithPaginationFindByC_LtM;
	private FinderPath _finderPathWithPaginationCountByC_LtM;

	/**
	 * Returns all the commerce tier price entries where commercePriceEntryId = &#63; and minQuantity &le; &#63;.
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 * @param minQuantity the min quantity
	 * @return the matching commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> findByC_LtM(
		long commercePriceEntryId, int minQuantity) {

		return findByC_LtM(
			commercePriceEntryId, minQuantity, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce tier price entries where commercePriceEntryId = &#63; and minQuantity &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTierPriceEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 * @param minQuantity the min quantity
	 * @param start the lower bound of the range of commerce tier price entries
	 * @param end the upper bound of the range of commerce tier price entries (not inclusive)
	 * @return the range of matching commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> findByC_LtM(
		long commercePriceEntryId, int minQuantity, int start, int end) {

		return findByC_LtM(commercePriceEntryId, minQuantity, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce tier price entries where commercePriceEntryId = &#63; and minQuantity &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTierPriceEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 * @param minQuantity the min quantity
	 * @param start the lower bound of the range of commerce tier price entries
	 * @param end the upper bound of the range of commerce tier price entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> findByC_LtM(
		long commercePriceEntryId, int minQuantity, int start, int end,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator) {

		return findByC_LtM(
			commercePriceEntryId, minQuantity, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the commerce tier price entries where commercePriceEntryId = &#63; and minQuantity &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTierPriceEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 * @param minQuantity the min quantity
	 * @param start the lower bound of the range of commerce tier price entries
	 * @param end the upper bound of the range of commerce tier price entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> findByC_LtM(
		long commercePriceEntryId, int minQuantity, int start, int end,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByC_LtM;
		finderArgs = new Object[] {
			commercePriceEntryId, minQuantity, start, end, orderByComparator
		};

		List<CommerceTierPriceEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceTierPriceEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceTierPriceEntry commerceTierPriceEntry : list) {
					if ((commercePriceEntryId !=
							commerceTierPriceEntry.getCommercePriceEntryId()) ||
						(minQuantity <
							commerceTierPriceEntry.getMinQuantity())) {

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

			query.append(_SQL_SELECT_COMMERCETIERPRICEENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_LTM_COMMERCEPRICEENTRYID_2);

			query.append(_FINDER_COLUMN_C_LTM_MINQUANTITY_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceTierPriceEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commercePriceEntryId);

				qPos.add(minQuantity);

				if (!pagination) {
					list = (List<CommerceTierPriceEntry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceTierPriceEntry>)QueryUtil.list(
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
	 * Returns the first commerce tier price entry in the ordered set where commercePriceEntryId = &#63; and minQuantity &le; &#63;.
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 * @param minQuantity the min quantity
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce tier price entry
	 * @throws NoSuchTierPriceEntryException if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry findByC_LtM_First(
			long commercePriceEntryId, int minQuantity,
			OrderByComparator<CommerceTierPriceEntry> orderByComparator)
		throws NoSuchTierPriceEntryException {

		CommerceTierPriceEntry commerceTierPriceEntry = fetchByC_LtM_First(
			commercePriceEntryId, minQuantity, orderByComparator);

		if (commerceTierPriceEntry != null) {
			return commerceTierPriceEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commercePriceEntryId=");
		msg.append(commercePriceEntryId);

		msg.append(", minQuantity=");
		msg.append(minQuantity);

		msg.append("}");

		throw new NoSuchTierPriceEntryException(msg.toString());
	}

	/**
	 * Returns the first commerce tier price entry in the ordered set where commercePriceEntryId = &#63; and minQuantity &le; &#63;.
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 * @param minQuantity the min quantity
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce tier price entry, or <code>null</code> if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry fetchByC_LtM_First(
		long commercePriceEntryId, int minQuantity,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator) {

		List<CommerceTierPriceEntry> list = findByC_LtM(
			commercePriceEntryId, minQuantity, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce tier price entry in the ordered set where commercePriceEntryId = &#63; and minQuantity &le; &#63;.
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 * @param minQuantity the min quantity
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce tier price entry
	 * @throws NoSuchTierPriceEntryException if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry findByC_LtM_Last(
			long commercePriceEntryId, int minQuantity,
			OrderByComparator<CommerceTierPriceEntry> orderByComparator)
		throws NoSuchTierPriceEntryException {

		CommerceTierPriceEntry commerceTierPriceEntry = fetchByC_LtM_Last(
			commercePriceEntryId, minQuantity, orderByComparator);

		if (commerceTierPriceEntry != null) {
			return commerceTierPriceEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commercePriceEntryId=");
		msg.append(commercePriceEntryId);

		msg.append(", minQuantity=");
		msg.append(minQuantity);

		msg.append("}");

		throw new NoSuchTierPriceEntryException(msg.toString());
	}

	/**
	 * Returns the last commerce tier price entry in the ordered set where commercePriceEntryId = &#63; and minQuantity &le; &#63;.
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 * @param minQuantity the min quantity
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce tier price entry, or <code>null</code> if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry fetchByC_LtM_Last(
		long commercePriceEntryId, int minQuantity,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator) {

		int count = countByC_LtM(commercePriceEntryId, minQuantity);

		if (count == 0) {
			return null;
		}

		List<CommerceTierPriceEntry> list = findByC_LtM(
			commercePriceEntryId, minQuantity, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce tier price entries before and after the current commerce tier price entry in the ordered set where commercePriceEntryId = &#63; and minQuantity &le; &#63;.
	 *
	 * @param commerceTierPriceEntryId the primary key of the current commerce tier price entry
	 * @param commercePriceEntryId the commerce price entry ID
	 * @param minQuantity the min quantity
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce tier price entry
	 * @throws NoSuchTierPriceEntryException if a commerce tier price entry with the primary key could not be found
	 */
	@Override
	public CommerceTierPriceEntry[] findByC_LtM_PrevAndNext(
			long commerceTierPriceEntryId, long commercePriceEntryId,
			int minQuantity,
			OrderByComparator<CommerceTierPriceEntry> orderByComparator)
		throws NoSuchTierPriceEntryException {

		CommerceTierPriceEntry commerceTierPriceEntry = findByPrimaryKey(
			commerceTierPriceEntryId);

		Session session = null;

		try {
			session = openSession();

			CommerceTierPriceEntry[] array = new CommerceTierPriceEntryImpl[3];

			array[0] = getByC_LtM_PrevAndNext(
				session, commerceTierPriceEntry, commercePriceEntryId,
				minQuantity, orderByComparator, true);

			array[1] = commerceTierPriceEntry;

			array[2] = getByC_LtM_PrevAndNext(
				session, commerceTierPriceEntry, commercePriceEntryId,
				minQuantity, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceTierPriceEntry getByC_LtM_PrevAndNext(
		Session session, CommerceTierPriceEntry commerceTierPriceEntry,
		long commercePriceEntryId, int minQuantity,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCETIERPRICEENTRY_WHERE);

		query.append(_FINDER_COLUMN_C_LTM_COMMERCEPRICEENTRYID_2);

		query.append(_FINDER_COLUMN_C_LTM_MINQUANTITY_2);

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
			query.append(CommerceTierPriceEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commercePriceEntryId);

		qPos.add(minQuantity);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceTierPriceEntry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceTierPriceEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce tier price entries where commercePriceEntryId = &#63; and minQuantity &le; &#63; from the database.
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 * @param minQuantity the min quantity
	 */
	@Override
	public void removeByC_LtM(long commercePriceEntryId, int minQuantity) {
		for (CommerceTierPriceEntry commerceTierPriceEntry :
				findByC_LtM(
					commercePriceEntryId, minQuantity, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(commerceTierPriceEntry);
		}
	}

	/**
	 * Returns the number of commerce tier price entries where commercePriceEntryId = &#63; and minQuantity &le; &#63;.
	 *
	 * @param commercePriceEntryId the commerce price entry ID
	 * @param minQuantity the min quantity
	 * @return the number of matching commerce tier price entries
	 */
	@Override
	public int countByC_LtM(long commercePriceEntryId, int minQuantity) {
		FinderPath finderPath = _finderPathWithPaginationCountByC_LtM;

		Object[] finderArgs = new Object[] {commercePriceEntryId, minQuantity};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCETIERPRICEENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_LTM_COMMERCEPRICEENTRYID_2);

			query.append(_FINDER_COLUMN_C_LTM_MINQUANTITY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commercePriceEntryId);

				qPos.add(minQuantity);

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

	private static final String _FINDER_COLUMN_C_LTM_COMMERCEPRICEENTRYID_2 =
		"commerceTierPriceEntry.commercePriceEntryId = ? AND ";

	private static final String _FINDER_COLUMN_C_LTM_MINQUANTITY_2 =
		"commerceTierPriceEntry.minQuantity <= ?";

	private FinderPath _finderPathFetchByC_ERC;
	private FinderPath _finderPathCountByC_ERC;

	/**
	 * Returns the commerce tier price entry where companyId = &#63; and externalReferenceCode = &#63; or throws a <code>NoSuchTierPriceEntryException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce tier price entry
	 * @throws NoSuchTierPriceEntryException if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry findByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchTierPriceEntryException {

		CommerceTierPriceEntry commerceTierPriceEntry = fetchByC_ERC(
			companyId, externalReferenceCode);

		if (commerceTierPriceEntry == null) {
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

			throw new NoSuchTierPriceEntryException(msg.toString());
		}

		return commerceTierPriceEntry;
	}

	/**
	 * Returns the commerce tier price entry where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce tier price entry, or <code>null</code> if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry fetchByC_ERC(
		long companyId, String externalReferenceCode) {

		return fetchByC_ERC(companyId, externalReferenceCode, true);
	}

	/**
	 * Returns the commerce tier price entry where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching commerce tier price entry, or <code>null</code> if a matching commerce tier price entry could not be found
	 */
	@Override
	public CommerceTierPriceEntry fetchByC_ERC(
		long companyId, String externalReferenceCode,
		boolean retrieveFromCache) {

		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		Object[] finderArgs = new Object[] {companyId, externalReferenceCode};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_ERC, finderArgs, this);
		}

		if (result instanceof CommerceTierPriceEntry) {
			CommerceTierPriceEntry commerceTierPriceEntry =
				(CommerceTierPriceEntry)result;

			if ((companyId != commerceTierPriceEntry.getCompanyId()) ||
				!Objects.equals(
					externalReferenceCode,
					commerceTierPriceEntry.getExternalReferenceCode())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COMMERCETIERPRICEENTRY_WHERE);

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

				List<CommerceTierPriceEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByC_ERC, finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"CommerceTierPriceEntryPersistenceImpl.fetchByC_ERC(long, String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CommerceTierPriceEntry commerceTierPriceEntry = list.get(0);

					result = commerceTierPriceEntry;

					cacheResult(commerceTierPriceEntry);
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
			return (CommerceTierPriceEntry)result;
		}
	}

	/**
	 * Removes the commerce tier price entry where companyId = &#63; and externalReferenceCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the commerce tier price entry that was removed
	 */
	@Override
	public CommerceTierPriceEntry removeByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchTierPriceEntryException {

		CommerceTierPriceEntry commerceTierPriceEntry = findByC_ERC(
			companyId, externalReferenceCode);

		return remove(commerceTierPriceEntry);
	}

	/**
	 * Returns the number of commerce tier price entries where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the number of matching commerce tier price entries
	 */
	@Override
	public int countByC_ERC(long companyId, String externalReferenceCode) {
		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		FinderPath finderPath = _finderPathCountByC_ERC;

		Object[] finderArgs = new Object[] {companyId, externalReferenceCode};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCETIERPRICEENTRY_WHERE);

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
		"commerceTierPriceEntry.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2 =
		"commerceTierPriceEntry.externalReferenceCode = ?";

	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3 =
		"(commerceTierPriceEntry.externalReferenceCode IS NULL OR commerceTierPriceEntry.externalReferenceCode = '')";

	public CommerceTierPriceEntryPersistenceImpl() {
		setModelClass(CommerceTierPriceEntry.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

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
	 * Caches the commerce tier price entry in the entity cache if it is enabled.
	 *
	 * @param commerceTierPriceEntry the commerce tier price entry
	 */
	@Override
	public void cacheResult(CommerceTierPriceEntry commerceTierPriceEntry) {
		entityCache.putResult(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryImpl.class,
			commerceTierPriceEntry.getPrimaryKey(), commerceTierPriceEntry);

		finderCache.putResult(
			_finderPathFetchByC_M,
			new Object[] {
				commerceTierPriceEntry.getCommercePriceEntryId(),
				commerceTierPriceEntry.getMinQuantity()
			},
			commerceTierPriceEntry);

		finderCache.putResult(
			_finderPathFetchByC_ERC,
			new Object[] {
				commerceTierPriceEntry.getCompanyId(),
				commerceTierPriceEntry.getExternalReferenceCode()
			},
			commerceTierPriceEntry);

		commerceTierPriceEntry.resetOriginalValues();
	}

	/**
	 * Caches the commerce tier price entries in the entity cache if it is enabled.
	 *
	 * @param commerceTierPriceEntries the commerce tier price entries
	 */
	@Override
	public void cacheResult(
		List<CommerceTierPriceEntry> commerceTierPriceEntries) {

		for (CommerceTierPriceEntry commerceTierPriceEntry :
				commerceTierPriceEntries) {

			if (entityCache.getResult(
					CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
					CommerceTierPriceEntryImpl.class,
					commerceTierPriceEntry.getPrimaryKey()) == null) {

				cacheResult(commerceTierPriceEntry);
			}
			else {
				commerceTierPriceEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce tier price entries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceTierPriceEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce tier price entry.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceTierPriceEntry commerceTierPriceEntry) {
		entityCache.removeResult(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryImpl.class,
			commerceTierPriceEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(CommerceTierPriceEntryModelImpl)commerceTierPriceEntry, true);
	}

	@Override
	public void clearCache(
		List<CommerceTierPriceEntry> commerceTierPriceEntries) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceTierPriceEntry commerceTierPriceEntry :
				commerceTierPriceEntries) {

			entityCache.removeResult(
				CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
				CommerceTierPriceEntryImpl.class,
				commerceTierPriceEntry.getPrimaryKey());

			clearUniqueFindersCache(
				(CommerceTierPriceEntryModelImpl)commerceTierPriceEntry, true);
		}
	}

	protected void cacheUniqueFindersCache(
		CommerceTierPriceEntryModelImpl commerceTierPriceEntryModelImpl) {

		Object[] args = new Object[] {
			commerceTierPriceEntryModelImpl.getCommercePriceEntryId(),
			commerceTierPriceEntryModelImpl.getMinQuantity()
		};

		finderCache.putResult(
			_finderPathCountByC_M, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_M, args, commerceTierPriceEntryModelImpl,
			false);

		args = new Object[] {
			commerceTierPriceEntryModelImpl.getCompanyId(),
			commerceTierPriceEntryModelImpl.getExternalReferenceCode()
		};

		finderCache.putResult(
			_finderPathCountByC_ERC, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_ERC, args, commerceTierPriceEntryModelImpl,
			false);
	}

	protected void clearUniqueFindersCache(
		CommerceTierPriceEntryModelImpl commerceTierPriceEntryModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				commerceTierPriceEntryModelImpl.getCommercePriceEntryId(),
				commerceTierPriceEntryModelImpl.getMinQuantity()
			};

			finderCache.removeResult(_finderPathCountByC_M, args);
			finderCache.removeResult(_finderPathFetchByC_M, args);
		}

		if ((commerceTierPriceEntryModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_M.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				commerceTierPriceEntryModelImpl.
					getOriginalCommercePriceEntryId(),
				commerceTierPriceEntryModelImpl.getOriginalMinQuantity()
			};

			finderCache.removeResult(_finderPathCountByC_M, args);
			finderCache.removeResult(_finderPathFetchByC_M, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
				commerceTierPriceEntryModelImpl.getCompanyId(),
				commerceTierPriceEntryModelImpl.getExternalReferenceCode()
			};

			finderCache.removeResult(_finderPathCountByC_ERC, args);
			finderCache.removeResult(_finderPathFetchByC_ERC, args);
		}

		if ((commerceTierPriceEntryModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_ERC.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				commerceTierPriceEntryModelImpl.getOriginalCompanyId(),
				commerceTierPriceEntryModelImpl.
					getOriginalExternalReferenceCode()
			};

			finderCache.removeResult(_finderPathCountByC_ERC, args);
			finderCache.removeResult(_finderPathFetchByC_ERC, args);
		}
	}

	/**
	 * Creates a new commerce tier price entry with the primary key. Does not add the commerce tier price entry to the database.
	 *
	 * @param commerceTierPriceEntryId the primary key for the new commerce tier price entry
	 * @return the new commerce tier price entry
	 */
	@Override
	public CommerceTierPriceEntry create(long commerceTierPriceEntryId) {
		CommerceTierPriceEntry commerceTierPriceEntry =
			new CommerceTierPriceEntryImpl();

		commerceTierPriceEntry.setNew(true);
		commerceTierPriceEntry.setPrimaryKey(commerceTierPriceEntryId);

		String uuid = PortalUUIDUtil.generate();

		commerceTierPriceEntry.setUuid(uuid);

		commerceTierPriceEntry.setCompanyId(CompanyThreadLocal.getCompanyId());

		return commerceTierPriceEntry;
	}

	/**
	 * Removes the commerce tier price entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceTierPriceEntryId the primary key of the commerce tier price entry
	 * @return the commerce tier price entry that was removed
	 * @throws NoSuchTierPriceEntryException if a commerce tier price entry with the primary key could not be found
	 */
	@Override
	public CommerceTierPriceEntry remove(long commerceTierPriceEntryId)
		throws NoSuchTierPriceEntryException {

		return remove((Serializable)commerceTierPriceEntryId);
	}

	/**
	 * Removes the commerce tier price entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce tier price entry
	 * @return the commerce tier price entry that was removed
	 * @throws NoSuchTierPriceEntryException if a commerce tier price entry with the primary key could not be found
	 */
	@Override
	public CommerceTierPriceEntry remove(Serializable primaryKey)
		throws NoSuchTierPriceEntryException {

		Session session = null;

		try {
			session = openSession();

			CommerceTierPriceEntry commerceTierPriceEntry =
				(CommerceTierPriceEntry)session.get(
					CommerceTierPriceEntryImpl.class, primaryKey);

			if (commerceTierPriceEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTierPriceEntryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commerceTierPriceEntry);
		}
		catch (NoSuchTierPriceEntryException nsee) {
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
	protected CommerceTierPriceEntry removeImpl(
		CommerceTierPriceEntry commerceTierPriceEntry) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceTierPriceEntry)) {
				commerceTierPriceEntry = (CommerceTierPriceEntry)session.get(
					CommerceTierPriceEntryImpl.class,
					commerceTierPriceEntry.getPrimaryKeyObj());
			}

			if (commerceTierPriceEntry != null) {
				session.delete(commerceTierPriceEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceTierPriceEntry != null) {
			clearCache(commerceTierPriceEntry);
		}

		return commerceTierPriceEntry;
	}

	@Override
	public CommerceTierPriceEntry updateImpl(
		CommerceTierPriceEntry commerceTierPriceEntry) {

		boolean isNew = commerceTierPriceEntry.isNew();

		if (!(commerceTierPriceEntry instanceof
				CommerceTierPriceEntryModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceTierPriceEntry.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					commerceTierPriceEntry);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceTierPriceEntry proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceTierPriceEntry implementation " +
					commerceTierPriceEntry.getClass());
		}

		CommerceTierPriceEntryModelImpl commerceTierPriceEntryModelImpl =
			(CommerceTierPriceEntryModelImpl)commerceTierPriceEntry;

		if (Validator.isNull(commerceTierPriceEntry.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			commerceTierPriceEntry.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceTierPriceEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceTierPriceEntry.setCreateDate(now);
			}
			else {
				commerceTierPriceEntry.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!commerceTierPriceEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceTierPriceEntry.setModifiedDate(now);
			}
			else {
				commerceTierPriceEntry.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceTierPriceEntry.isNew()) {
				session.save(commerceTierPriceEntry);

				commerceTierPriceEntry.setNew(false);
			}
			else {
				commerceTierPriceEntry = (CommerceTierPriceEntry)session.merge(
					commerceTierPriceEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceTierPriceEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				commerceTierPriceEntryModelImpl.getUuid()
			};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				commerceTierPriceEntryModelImpl.getUuid(),
				commerceTierPriceEntryModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {
				commerceTierPriceEntryModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByCompanyId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCompanyId, args);

			args = new Object[] {
				commerceTierPriceEntryModelImpl.getCommercePriceEntryId()
			};

			finderCache.removeResult(
				_finderPathCountByCommercePriceEntryId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCommercePriceEntryId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((commerceTierPriceEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceTierPriceEntryModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {commerceTierPriceEntryModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((commerceTierPriceEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceTierPriceEntryModelImpl.getOriginalUuid(),
					commerceTierPriceEntryModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					commerceTierPriceEntryModelImpl.getUuid(),
					commerceTierPriceEntryModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((commerceTierPriceEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCompanyId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceTierPriceEntryModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);

				args = new Object[] {
					commerceTierPriceEntryModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);
			}

			if ((commerceTierPriceEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCommercePriceEntryId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceTierPriceEntryModelImpl.
						getOriginalCommercePriceEntryId()
				};

				finderCache.removeResult(
					_finderPathCountByCommercePriceEntryId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommercePriceEntryId,
					args);

				args = new Object[] {
					commerceTierPriceEntryModelImpl.getCommercePriceEntryId()
				};

				finderCache.removeResult(
					_finderPathCountByCommercePriceEntryId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommercePriceEntryId,
					args);
			}
		}

		entityCache.putResult(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryImpl.class,
			commerceTierPriceEntry.getPrimaryKey(), commerceTierPriceEntry,
			false);

		clearUniqueFindersCache(commerceTierPriceEntryModelImpl, false);
		cacheUniqueFindersCache(commerceTierPriceEntryModelImpl);

		commerceTierPriceEntry.resetOriginalValues();

		return commerceTierPriceEntry;
	}

	/**
	 * Returns the commerce tier price entry with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce tier price entry
	 * @return the commerce tier price entry
	 * @throws NoSuchTierPriceEntryException if a commerce tier price entry with the primary key could not be found
	 */
	@Override
	public CommerceTierPriceEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTierPriceEntryException {

		CommerceTierPriceEntry commerceTierPriceEntry = fetchByPrimaryKey(
			primaryKey);

		if (commerceTierPriceEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTierPriceEntryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commerceTierPriceEntry;
	}

	/**
	 * Returns the commerce tier price entry with the primary key or throws a <code>NoSuchTierPriceEntryException</code> if it could not be found.
	 *
	 * @param commerceTierPriceEntryId the primary key of the commerce tier price entry
	 * @return the commerce tier price entry
	 * @throws NoSuchTierPriceEntryException if a commerce tier price entry with the primary key could not be found
	 */
	@Override
	public CommerceTierPriceEntry findByPrimaryKey(
			long commerceTierPriceEntryId)
		throws NoSuchTierPriceEntryException {

		return findByPrimaryKey((Serializable)commerceTierPriceEntryId);
	}

	/**
	 * Returns the commerce tier price entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce tier price entry
	 * @return the commerce tier price entry, or <code>null</code> if a commerce tier price entry with the primary key could not be found
	 */
	@Override
	public CommerceTierPriceEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceTierPriceEntry commerceTierPriceEntry =
			(CommerceTierPriceEntry)serializable;

		if (commerceTierPriceEntry == null) {
			Session session = null;

			try {
				session = openSession();

				commerceTierPriceEntry = (CommerceTierPriceEntry)session.get(
					CommerceTierPriceEntryImpl.class, primaryKey);

				if (commerceTierPriceEntry != null) {
					cacheResult(commerceTierPriceEntry);
				}
				else {
					entityCache.putResult(
						CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
						CommerceTierPriceEntryImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
					CommerceTierPriceEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceTierPriceEntry;
	}

	/**
	 * Returns the commerce tier price entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceTierPriceEntryId the primary key of the commerce tier price entry
	 * @return the commerce tier price entry, or <code>null</code> if a commerce tier price entry with the primary key could not be found
	 */
	@Override
	public CommerceTierPriceEntry fetchByPrimaryKey(
		long commerceTierPriceEntryId) {

		return fetchByPrimaryKey((Serializable)commerceTierPriceEntryId);
	}

	@Override
	public Map<Serializable, CommerceTierPriceEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceTierPriceEntry> map =
			new HashMap<Serializable, CommerceTierPriceEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceTierPriceEntry commerceTierPriceEntry = fetchByPrimaryKey(
				primaryKey);

			if (commerceTierPriceEntry != null) {
				map.put(primaryKey, commerceTierPriceEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
				CommerceTierPriceEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceTierPriceEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_COMMERCETIERPRICEENTRY_WHERE_PKS_IN);

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

			for (CommerceTierPriceEntry commerceTierPriceEntry :
					(List<CommerceTierPriceEntry>)q.list()) {

				map.put(
					commerceTierPriceEntry.getPrimaryKeyObj(),
					commerceTierPriceEntry);

				cacheResult(commerceTierPriceEntry);

				uncachedPrimaryKeys.remove(
					commerceTierPriceEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
					CommerceTierPriceEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the commerce tier price entries.
	 *
	 * @return the commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce tier price entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTierPriceEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce tier price entries
	 * @param end the upper bound of the range of commerce tier price entries (not inclusive)
	 * @return the range of commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce tier price entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTierPriceEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce tier price entries
	 * @param end the upper bound of the range of commerce tier price entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> findAll(
		int start, int end,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce tier price entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceTierPriceEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce tier price entries
	 * @param end the upper bound of the range of commerce tier price entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce tier price entries
	 */
	@Override
	public List<CommerceTierPriceEntry> findAll(
		int start, int end,
		OrderByComparator<CommerceTierPriceEntry> orderByComparator,
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

		List<CommerceTierPriceEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceTierPriceEntry>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCETIERPRICEENTRY);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCETIERPRICEENTRY;

				if (pagination) {
					sql = sql.concat(
						CommerceTierPriceEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceTierPriceEntry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceTierPriceEntry>)QueryUtil.list(
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
	 * Removes all the commerce tier price entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceTierPriceEntry commerceTierPriceEntry : findAll()) {
			remove(commerceTierPriceEntry);
		}
	}

	/**
	 * Returns the number of commerce tier price entries.
	 *
	 * @return the number of commerce tier price entries
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
					_SQL_COUNT_COMMERCETIERPRICEENTRY);

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
		return CommerceTierPriceEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce tier price entry persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceTierPriceEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceTierPriceEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceTierPriceEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceTierPriceEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			CommerceTierPriceEntryModelImpl.UUID_COLUMN_BITMASK |
			CommerceTierPriceEntryModelImpl.MINQUANTITY_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceTierPriceEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceTierPriceEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			CommerceTierPriceEntryModelImpl.UUID_COLUMN_BITMASK |
			CommerceTierPriceEntryModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceTierPriceEntryModelImpl.MINQUANTITY_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByCompanyId = new FinderPath(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceTierPriceEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCompanyId = new FinderPath(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceTierPriceEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] {Long.class.getName()},
			CommerceTierPriceEntryModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceTierPriceEntryModelImpl.MINQUANTITY_COLUMN_BITMASK);

		_finderPathCountByCompanyId = new FinderPath(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByCommercePriceEntryId = new FinderPath(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceTierPriceEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCommercePriceEntryId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCommercePriceEntryId = new FinderPath(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceTierPriceEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCommercePriceEntryId", new String[] {Long.class.getName()},
			CommerceTierPriceEntryModelImpl.
				COMMERCEPRICEENTRYID_COLUMN_BITMASK |
			CommerceTierPriceEntryModelImpl.MINQUANTITY_COLUMN_BITMASK);

		_finderPathCountByCommercePriceEntryId = new FinderPath(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommercePriceEntryId", new String[] {Long.class.getName()});

		_finderPathFetchByC_M = new FinderPath(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceTierPriceEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_M",
			new String[] {Long.class.getName(), Integer.class.getName()},
			CommerceTierPriceEntryModelImpl.
				COMMERCEPRICEENTRYID_COLUMN_BITMASK |
			CommerceTierPriceEntryModelImpl.MINQUANTITY_COLUMN_BITMASK);

		_finderPathCountByC_M = new FinderPath(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_M",
			new String[] {Long.class.getName(), Integer.class.getName()});

		_finderPathWithPaginationFindByC_LtM = new FinderPath(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceTierPriceEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_LtM",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByC_LtM = new FinderPath(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByC_LtM",
			new String[] {Long.class.getName(), Integer.class.getName()});

		_finderPathFetchByC_ERC = new FinderPath(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceTierPriceEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_ERC",
			new String[] {Long.class.getName(), String.class.getName()},
			CommerceTierPriceEntryModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceTierPriceEntryModelImpl.
				EXTERNALREFERENCECODE_COLUMN_BITMASK);

		_finderPathCountByC_ERC = new FinderPath(
			CommerceTierPriceEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceTierPriceEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_ERC",
			new String[] {Long.class.getName(), String.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(CommerceTierPriceEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_COMMERCETIERPRICEENTRY =
		"SELECT commerceTierPriceEntry FROM CommerceTierPriceEntry commerceTierPriceEntry";

	private static final String
		_SQL_SELECT_COMMERCETIERPRICEENTRY_WHERE_PKS_IN =
			"SELECT commerceTierPriceEntry FROM CommerceTierPriceEntry commerceTierPriceEntry WHERE commerceTierPriceEntryId IN (";

	private static final String _SQL_SELECT_COMMERCETIERPRICEENTRY_WHERE =
		"SELECT commerceTierPriceEntry FROM CommerceTierPriceEntry commerceTierPriceEntry WHERE ";

	private static final String _SQL_COUNT_COMMERCETIERPRICEENTRY =
		"SELECT COUNT(commerceTierPriceEntry) FROM CommerceTierPriceEntry commerceTierPriceEntry";

	private static final String _SQL_COUNT_COMMERCETIERPRICEENTRY_WHERE =
		"SELECT COUNT(commerceTierPriceEntry) FROM CommerceTierPriceEntry commerceTierPriceEntry WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"commerceTierPriceEntry.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommerceTierPriceEntry exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommerceTierPriceEntry exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceTierPriceEntryPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}