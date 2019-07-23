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

import com.liferay.commerce.exception.NoSuchCPDAvailabilityEstimateException;
import com.liferay.commerce.model.CPDAvailabilityEstimate;
import com.liferay.commerce.model.impl.CPDAvailabilityEstimateImpl;
import com.liferay.commerce.model.impl.CPDAvailabilityEstimateModelImpl;
import com.liferay.commerce.service.persistence.CPDAvailabilityEstimatePersistence;
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
 * The persistence implementation for the cpd availability estimate service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
@ProviderType
public class CPDAvailabilityEstimatePersistenceImpl
	extends BasePersistenceImpl<CPDAvailabilityEstimate>
	implements CPDAvailabilityEstimatePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CPDAvailabilityEstimateUtil</code> to access the cpd availability estimate persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CPDAvailabilityEstimateImpl.class.getName();

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
	 * Returns all the cpd availability estimates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cpd availability estimates
	 */
	@Override
	public List<CPDAvailabilityEstimate> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cpd availability estimates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDAvailabilityEstimateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cpd availability estimates
	 * @param end the upper bound of the range of cpd availability estimates (not inclusive)
	 * @return the range of matching cpd availability estimates
	 */
	@Override
	public List<CPDAvailabilityEstimate> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cpd availability estimates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDAvailabilityEstimateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cpd availability estimates
	 * @param end the upper bound of the range of cpd availability estimates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cpd availability estimates
	 */
	@Override
	public List<CPDAvailabilityEstimate> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPDAvailabilityEstimate> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cpd availability estimates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDAvailabilityEstimateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cpd availability estimates
	 * @param end the upper bound of the range of cpd availability estimates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cpd availability estimates
	 */
	@Override
	public List<CPDAvailabilityEstimate> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPDAvailabilityEstimate> orderByComparator,
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

		List<CPDAvailabilityEstimate> list = null;

		if (retrieveFromCache) {
			list = (List<CPDAvailabilityEstimate>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPDAvailabilityEstimate cpdAvailabilityEstimate : list) {
					if (!uuid.equals(cpdAvailabilityEstimate.getUuid())) {
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

			query.append(_SQL_SELECT_CPDAVAILABILITYESTIMATE_WHERE);

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
				query.append(CPDAvailabilityEstimateModelImpl.ORDER_BY_JPQL);
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
					list = (List<CPDAvailabilityEstimate>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPDAvailabilityEstimate>)QueryUtil.list(
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
	 * Returns the first cpd availability estimate in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cpd availability estimate
	 * @throws NoSuchCPDAvailabilityEstimateException if a matching cpd availability estimate could not be found
	 */
	@Override
	public CPDAvailabilityEstimate findByUuid_First(
			String uuid,
			OrderByComparator<CPDAvailabilityEstimate> orderByComparator)
		throws NoSuchCPDAvailabilityEstimateException {

		CPDAvailabilityEstimate cpdAvailabilityEstimate = fetchByUuid_First(
			uuid, orderByComparator);

		if (cpdAvailabilityEstimate != null) {
			return cpdAvailabilityEstimate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCPDAvailabilityEstimateException(msg.toString());
	}

	/**
	 * Returns the first cpd availability estimate in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cpd availability estimate, or <code>null</code> if a matching cpd availability estimate could not be found
	 */
	@Override
	public CPDAvailabilityEstimate fetchByUuid_First(
		String uuid,
		OrderByComparator<CPDAvailabilityEstimate> orderByComparator) {

		List<CPDAvailabilityEstimate> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cpd availability estimate in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cpd availability estimate
	 * @throws NoSuchCPDAvailabilityEstimateException if a matching cpd availability estimate could not be found
	 */
	@Override
	public CPDAvailabilityEstimate findByUuid_Last(
			String uuid,
			OrderByComparator<CPDAvailabilityEstimate> orderByComparator)
		throws NoSuchCPDAvailabilityEstimateException {

		CPDAvailabilityEstimate cpdAvailabilityEstimate = fetchByUuid_Last(
			uuid, orderByComparator);

		if (cpdAvailabilityEstimate != null) {
			return cpdAvailabilityEstimate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCPDAvailabilityEstimateException(msg.toString());
	}

	/**
	 * Returns the last cpd availability estimate in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cpd availability estimate, or <code>null</code> if a matching cpd availability estimate could not be found
	 */
	@Override
	public CPDAvailabilityEstimate fetchByUuid_Last(
		String uuid,
		OrderByComparator<CPDAvailabilityEstimate> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CPDAvailabilityEstimate> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cpd availability estimates before and after the current cpd availability estimate in the ordered set where uuid = &#63;.
	 *
	 * @param CPDAvailabilityEstimateId the primary key of the current cpd availability estimate
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cpd availability estimate
	 * @throws NoSuchCPDAvailabilityEstimateException if a cpd availability estimate with the primary key could not be found
	 */
	@Override
	public CPDAvailabilityEstimate[] findByUuid_PrevAndNext(
			long CPDAvailabilityEstimateId, String uuid,
			OrderByComparator<CPDAvailabilityEstimate> orderByComparator)
		throws NoSuchCPDAvailabilityEstimateException {

		uuid = Objects.toString(uuid, "");

		CPDAvailabilityEstimate cpdAvailabilityEstimate = findByPrimaryKey(
			CPDAvailabilityEstimateId);

		Session session = null;

		try {
			session = openSession();

			CPDAvailabilityEstimate[] array =
				new CPDAvailabilityEstimateImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, cpdAvailabilityEstimate, uuid, orderByComparator,
				true);

			array[1] = cpdAvailabilityEstimate;

			array[2] = getByUuid_PrevAndNext(
				session, cpdAvailabilityEstimate, uuid, orderByComparator,
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

	protected CPDAvailabilityEstimate getByUuid_PrevAndNext(
		Session session, CPDAvailabilityEstimate cpdAvailabilityEstimate,
		String uuid,
		OrderByComparator<CPDAvailabilityEstimate> orderByComparator,
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

		query.append(_SQL_SELECT_CPDAVAILABILITYESTIMATE_WHERE);

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
			query.append(CPDAvailabilityEstimateModelImpl.ORDER_BY_JPQL);
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
						cpdAvailabilityEstimate)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPDAvailabilityEstimate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cpd availability estimates where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CPDAvailabilityEstimate cpdAvailabilityEstimate :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cpdAvailabilityEstimate);
		}
	}

	/**
	 * Returns the number of cpd availability estimates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cpd availability estimates
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPDAVAILABILITYESTIMATE_WHERE);

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
		"cpdAvailabilityEstimate.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(cpdAvailabilityEstimate.uuid IS NULL OR cpdAvailabilityEstimate.uuid = '')";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the cpd availability estimates where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching cpd availability estimates
	 */
	@Override
	public List<CPDAvailabilityEstimate> findByUuid_C(
		String uuid, long companyId) {

		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cpd availability estimates where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDAvailabilityEstimateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cpd availability estimates
	 * @param end the upper bound of the range of cpd availability estimates (not inclusive)
	 * @return the range of matching cpd availability estimates
	 */
	@Override
	public List<CPDAvailabilityEstimate> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cpd availability estimates where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDAvailabilityEstimateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cpd availability estimates
	 * @param end the upper bound of the range of cpd availability estimates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cpd availability estimates
	 */
	@Override
	public List<CPDAvailabilityEstimate> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPDAvailabilityEstimate> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cpd availability estimates where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDAvailabilityEstimateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cpd availability estimates
	 * @param end the upper bound of the range of cpd availability estimates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cpd availability estimates
	 */
	@Override
	public List<CPDAvailabilityEstimate> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPDAvailabilityEstimate> orderByComparator,
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

		List<CPDAvailabilityEstimate> list = null;

		if (retrieveFromCache) {
			list = (List<CPDAvailabilityEstimate>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPDAvailabilityEstimate cpdAvailabilityEstimate : list) {
					if (!uuid.equals(cpdAvailabilityEstimate.getUuid()) ||
						(companyId != cpdAvailabilityEstimate.getCompanyId())) {

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

			query.append(_SQL_SELECT_CPDAVAILABILITYESTIMATE_WHERE);

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
				query.append(CPDAvailabilityEstimateModelImpl.ORDER_BY_JPQL);
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
					list = (List<CPDAvailabilityEstimate>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPDAvailabilityEstimate>)QueryUtil.list(
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
	 * Returns the first cpd availability estimate in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cpd availability estimate
	 * @throws NoSuchCPDAvailabilityEstimateException if a matching cpd availability estimate could not be found
	 */
	@Override
	public CPDAvailabilityEstimate findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CPDAvailabilityEstimate> orderByComparator)
		throws NoSuchCPDAvailabilityEstimateException {

		CPDAvailabilityEstimate cpdAvailabilityEstimate = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (cpdAvailabilityEstimate != null) {
			return cpdAvailabilityEstimate;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPDAvailabilityEstimateException(msg.toString());
	}

	/**
	 * Returns the first cpd availability estimate in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cpd availability estimate, or <code>null</code> if a matching cpd availability estimate could not be found
	 */
	@Override
	public CPDAvailabilityEstimate fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CPDAvailabilityEstimate> orderByComparator) {

		List<CPDAvailabilityEstimate> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cpd availability estimate in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cpd availability estimate
	 * @throws NoSuchCPDAvailabilityEstimateException if a matching cpd availability estimate could not be found
	 */
	@Override
	public CPDAvailabilityEstimate findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CPDAvailabilityEstimate> orderByComparator)
		throws NoSuchCPDAvailabilityEstimateException {

		CPDAvailabilityEstimate cpdAvailabilityEstimate = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (cpdAvailabilityEstimate != null) {
			return cpdAvailabilityEstimate;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPDAvailabilityEstimateException(msg.toString());
	}

	/**
	 * Returns the last cpd availability estimate in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cpd availability estimate, or <code>null</code> if a matching cpd availability estimate could not be found
	 */
	@Override
	public CPDAvailabilityEstimate fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CPDAvailabilityEstimate> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CPDAvailabilityEstimate> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cpd availability estimates before and after the current cpd availability estimate in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param CPDAvailabilityEstimateId the primary key of the current cpd availability estimate
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cpd availability estimate
	 * @throws NoSuchCPDAvailabilityEstimateException if a cpd availability estimate with the primary key could not be found
	 */
	@Override
	public CPDAvailabilityEstimate[] findByUuid_C_PrevAndNext(
			long CPDAvailabilityEstimateId, String uuid, long companyId,
			OrderByComparator<CPDAvailabilityEstimate> orderByComparator)
		throws NoSuchCPDAvailabilityEstimateException {

		uuid = Objects.toString(uuid, "");

		CPDAvailabilityEstimate cpdAvailabilityEstimate = findByPrimaryKey(
			CPDAvailabilityEstimateId);

		Session session = null;

		try {
			session = openSession();

			CPDAvailabilityEstimate[] array =
				new CPDAvailabilityEstimateImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, cpdAvailabilityEstimate, uuid, companyId,
				orderByComparator, true);

			array[1] = cpdAvailabilityEstimate;

			array[2] = getByUuid_C_PrevAndNext(
				session, cpdAvailabilityEstimate, uuid, companyId,
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

	protected CPDAvailabilityEstimate getByUuid_C_PrevAndNext(
		Session session, CPDAvailabilityEstimate cpdAvailabilityEstimate,
		String uuid, long companyId,
		OrderByComparator<CPDAvailabilityEstimate> orderByComparator,
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

		query.append(_SQL_SELECT_CPDAVAILABILITYESTIMATE_WHERE);

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
			query.append(CPDAvailabilityEstimateModelImpl.ORDER_BY_JPQL);
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
						cpdAvailabilityEstimate)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPDAvailabilityEstimate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cpd availability estimates where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CPDAvailabilityEstimate cpdAvailabilityEstimate :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(cpdAvailabilityEstimate);
		}
	}

	/**
	 * Returns the number of cpd availability estimates where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching cpd availability estimates
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPDAVAILABILITYESTIMATE_WHERE);

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
		"cpdAvailabilityEstimate.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(cpdAvailabilityEstimate.uuid IS NULL OR cpdAvailabilityEstimate.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"cpdAvailabilityEstimate.companyId = ?";

	private FinderPath
		_finderPathWithPaginationFindByCommerceAvailabilityEstimateId;
	private FinderPath
		_finderPathWithoutPaginationFindByCommerceAvailabilityEstimateId;
	private FinderPath _finderPathCountByCommerceAvailabilityEstimateId;

	/**
	 * Returns all the cpd availability estimates where commerceAvailabilityEstimateId = &#63;.
	 *
	 * @param commerceAvailabilityEstimateId the commerce availability estimate ID
	 * @return the matching cpd availability estimates
	 */
	@Override
	public List<CPDAvailabilityEstimate> findByCommerceAvailabilityEstimateId(
		long commerceAvailabilityEstimateId) {

		return findByCommerceAvailabilityEstimateId(
			commerceAvailabilityEstimateId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cpd availability estimates where commerceAvailabilityEstimateId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDAvailabilityEstimateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAvailabilityEstimateId the commerce availability estimate ID
	 * @param start the lower bound of the range of cpd availability estimates
	 * @param end the upper bound of the range of cpd availability estimates (not inclusive)
	 * @return the range of matching cpd availability estimates
	 */
	@Override
	public List<CPDAvailabilityEstimate> findByCommerceAvailabilityEstimateId(
		long commerceAvailabilityEstimateId, int start, int end) {

		return findByCommerceAvailabilityEstimateId(
			commerceAvailabilityEstimateId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cpd availability estimates where commerceAvailabilityEstimateId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDAvailabilityEstimateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAvailabilityEstimateId the commerce availability estimate ID
	 * @param start the lower bound of the range of cpd availability estimates
	 * @param end the upper bound of the range of cpd availability estimates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cpd availability estimates
	 */
	@Override
	public List<CPDAvailabilityEstimate> findByCommerceAvailabilityEstimateId(
		long commerceAvailabilityEstimateId, int start, int end,
		OrderByComparator<CPDAvailabilityEstimate> orderByComparator) {

		return findByCommerceAvailabilityEstimateId(
			commerceAvailabilityEstimateId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the cpd availability estimates where commerceAvailabilityEstimateId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDAvailabilityEstimateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAvailabilityEstimateId the commerce availability estimate ID
	 * @param start the lower bound of the range of cpd availability estimates
	 * @param end the upper bound of the range of cpd availability estimates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cpd availability estimates
	 */
	@Override
	public List<CPDAvailabilityEstimate> findByCommerceAvailabilityEstimateId(
		long commerceAvailabilityEstimateId, int start, int end,
		OrderByComparator<CPDAvailabilityEstimate> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath =
				_finderPathWithoutPaginationFindByCommerceAvailabilityEstimateId;
			finderArgs = new Object[] {commerceAvailabilityEstimateId};
		}
		else {
			finderPath =
				_finderPathWithPaginationFindByCommerceAvailabilityEstimateId;
			finderArgs = new Object[] {
				commerceAvailabilityEstimateId, start, end, orderByComparator
			};
		}

		List<CPDAvailabilityEstimate> list = null;

		if (retrieveFromCache) {
			list = (List<CPDAvailabilityEstimate>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPDAvailabilityEstimate cpdAvailabilityEstimate : list) {
					if ((commerceAvailabilityEstimateId !=
							cpdAvailabilityEstimate.
								getCommerceAvailabilityEstimateId())) {

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

			query.append(_SQL_SELECT_CPDAVAILABILITYESTIMATE_WHERE);

			query.append(
				_FINDER_COLUMN_COMMERCEAVAILABILITYESTIMATEID_COMMERCEAVAILABILITYESTIMATEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CPDAvailabilityEstimateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceAvailabilityEstimateId);

				if (!pagination) {
					list = (List<CPDAvailabilityEstimate>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPDAvailabilityEstimate>)QueryUtil.list(
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
	 * Returns the first cpd availability estimate in the ordered set where commerceAvailabilityEstimateId = &#63;.
	 *
	 * @param commerceAvailabilityEstimateId the commerce availability estimate ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cpd availability estimate
	 * @throws NoSuchCPDAvailabilityEstimateException if a matching cpd availability estimate could not be found
	 */
	@Override
	public CPDAvailabilityEstimate findByCommerceAvailabilityEstimateId_First(
			long commerceAvailabilityEstimateId,
			OrderByComparator<CPDAvailabilityEstimate> orderByComparator)
		throws NoSuchCPDAvailabilityEstimateException {

		CPDAvailabilityEstimate cpdAvailabilityEstimate =
			fetchByCommerceAvailabilityEstimateId_First(
				commerceAvailabilityEstimateId, orderByComparator);

		if (cpdAvailabilityEstimate != null) {
			return cpdAvailabilityEstimate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceAvailabilityEstimateId=");
		msg.append(commerceAvailabilityEstimateId);

		msg.append("}");

		throw new NoSuchCPDAvailabilityEstimateException(msg.toString());
	}

	/**
	 * Returns the first cpd availability estimate in the ordered set where commerceAvailabilityEstimateId = &#63;.
	 *
	 * @param commerceAvailabilityEstimateId the commerce availability estimate ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cpd availability estimate, or <code>null</code> if a matching cpd availability estimate could not be found
	 */
	@Override
	public CPDAvailabilityEstimate fetchByCommerceAvailabilityEstimateId_First(
		long commerceAvailabilityEstimateId,
		OrderByComparator<CPDAvailabilityEstimate> orderByComparator) {

		List<CPDAvailabilityEstimate> list =
			findByCommerceAvailabilityEstimateId(
				commerceAvailabilityEstimateId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cpd availability estimate in the ordered set where commerceAvailabilityEstimateId = &#63;.
	 *
	 * @param commerceAvailabilityEstimateId the commerce availability estimate ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cpd availability estimate
	 * @throws NoSuchCPDAvailabilityEstimateException if a matching cpd availability estimate could not be found
	 */
	@Override
	public CPDAvailabilityEstimate findByCommerceAvailabilityEstimateId_Last(
			long commerceAvailabilityEstimateId,
			OrderByComparator<CPDAvailabilityEstimate> orderByComparator)
		throws NoSuchCPDAvailabilityEstimateException {

		CPDAvailabilityEstimate cpdAvailabilityEstimate =
			fetchByCommerceAvailabilityEstimateId_Last(
				commerceAvailabilityEstimateId, orderByComparator);

		if (cpdAvailabilityEstimate != null) {
			return cpdAvailabilityEstimate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceAvailabilityEstimateId=");
		msg.append(commerceAvailabilityEstimateId);

		msg.append("}");

		throw new NoSuchCPDAvailabilityEstimateException(msg.toString());
	}

	/**
	 * Returns the last cpd availability estimate in the ordered set where commerceAvailabilityEstimateId = &#63;.
	 *
	 * @param commerceAvailabilityEstimateId the commerce availability estimate ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cpd availability estimate, or <code>null</code> if a matching cpd availability estimate could not be found
	 */
	@Override
	public CPDAvailabilityEstimate fetchByCommerceAvailabilityEstimateId_Last(
		long commerceAvailabilityEstimateId,
		OrderByComparator<CPDAvailabilityEstimate> orderByComparator) {

		int count = countByCommerceAvailabilityEstimateId(
			commerceAvailabilityEstimateId);

		if (count == 0) {
			return null;
		}

		List<CPDAvailabilityEstimate> list =
			findByCommerceAvailabilityEstimateId(
				commerceAvailabilityEstimateId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cpd availability estimates before and after the current cpd availability estimate in the ordered set where commerceAvailabilityEstimateId = &#63;.
	 *
	 * @param CPDAvailabilityEstimateId the primary key of the current cpd availability estimate
	 * @param commerceAvailabilityEstimateId the commerce availability estimate ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cpd availability estimate
	 * @throws NoSuchCPDAvailabilityEstimateException if a cpd availability estimate with the primary key could not be found
	 */
	@Override
	public CPDAvailabilityEstimate[]
			findByCommerceAvailabilityEstimateId_PrevAndNext(
				long CPDAvailabilityEstimateId,
				long commerceAvailabilityEstimateId,
				OrderByComparator<CPDAvailabilityEstimate> orderByComparator)
		throws NoSuchCPDAvailabilityEstimateException {

		CPDAvailabilityEstimate cpdAvailabilityEstimate = findByPrimaryKey(
			CPDAvailabilityEstimateId);

		Session session = null;

		try {
			session = openSession();

			CPDAvailabilityEstimate[] array =
				new CPDAvailabilityEstimateImpl[3];

			array[0] = getByCommerceAvailabilityEstimateId_PrevAndNext(
				session, cpdAvailabilityEstimate,
				commerceAvailabilityEstimateId, orderByComparator, true);

			array[1] = cpdAvailabilityEstimate;

			array[2] = getByCommerceAvailabilityEstimateId_PrevAndNext(
				session, cpdAvailabilityEstimate,
				commerceAvailabilityEstimateId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPDAvailabilityEstimate
		getByCommerceAvailabilityEstimateId_PrevAndNext(
			Session session, CPDAvailabilityEstimate cpdAvailabilityEstimate,
			long commerceAvailabilityEstimateId,
			OrderByComparator<CPDAvailabilityEstimate> orderByComparator,
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

		query.append(_SQL_SELECT_CPDAVAILABILITYESTIMATE_WHERE);

		query.append(
			_FINDER_COLUMN_COMMERCEAVAILABILITYESTIMATEID_COMMERCEAVAILABILITYESTIMATEID_2);

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
			query.append(CPDAvailabilityEstimateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceAvailabilityEstimateId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpdAvailabilityEstimate)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPDAvailabilityEstimate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cpd availability estimates where commerceAvailabilityEstimateId = &#63; from the database.
	 *
	 * @param commerceAvailabilityEstimateId the commerce availability estimate ID
	 */
	@Override
	public void removeByCommerceAvailabilityEstimateId(
		long commerceAvailabilityEstimateId) {

		for (CPDAvailabilityEstimate cpdAvailabilityEstimate :
				findByCommerceAvailabilityEstimateId(
					commerceAvailabilityEstimateId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(cpdAvailabilityEstimate);
		}
	}

	/**
	 * Returns the number of cpd availability estimates where commerceAvailabilityEstimateId = &#63;.
	 *
	 * @param commerceAvailabilityEstimateId the commerce availability estimate ID
	 * @return the number of matching cpd availability estimates
	 */
	@Override
	public int countByCommerceAvailabilityEstimateId(
		long commerceAvailabilityEstimateId) {

		FinderPath finderPath =
			_finderPathCountByCommerceAvailabilityEstimateId;

		Object[] finderArgs = new Object[] {commerceAvailabilityEstimateId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPDAVAILABILITYESTIMATE_WHERE);

			query.append(
				_FINDER_COLUMN_COMMERCEAVAILABILITYESTIMATEID_COMMERCEAVAILABILITYESTIMATEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceAvailabilityEstimateId);

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
		_FINDER_COLUMN_COMMERCEAVAILABILITYESTIMATEID_COMMERCEAVAILABILITYESTIMATEID_2 =
			"cpdAvailabilityEstimate.commerceAvailabilityEstimateId = ?";

	private FinderPath _finderPathFetchByCProductId;
	private FinderPath _finderPathCountByCProductId;

	/**
	 * Returns the cpd availability estimate where CProductId = &#63; or throws a <code>NoSuchCPDAvailabilityEstimateException</code> if it could not be found.
	 *
	 * @param CProductId the c product ID
	 * @return the matching cpd availability estimate
	 * @throws NoSuchCPDAvailabilityEstimateException if a matching cpd availability estimate could not be found
	 */
	@Override
	public CPDAvailabilityEstimate findByCProductId(long CProductId)
		throws NoSuchCPDAvailabilityEstimateException {

		CPDAvailabilityEstimate cpdAvailabilityEstimate = fetchByCProductId(
			CProductId);

		if (cpdAvailabilityEstimate == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("CProductId=");
			msg.append(CProductId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCPDAvailabilityEstimateException(msg.toString());
		}

		return cpdAvailabilityEstimate;
	}

	/**
	 * Returns the cpd availability estimate where CProductId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param CProductId the c product ID
	 * @return the matching cpd availability estimate, or <code>null</code> if a matching cpd availability estimate could not be found
	 */
	@Override
	public CPDAvailabilityEstimate fetchByCProductId(long CProductId) {
		return fetchByCProductId(CProductId, true);
	}

	/**
	 * Returns the cpd availability estimate where CProductId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param CProductId the c product ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching cpd availability estimate, or <code>null</code> if a matching cpd availability estimate could not be found
	 */
	@Override
	public CPDAvailabilityEstimate fetchByCProductId(
		long CProductId, boolean retrieveFromCache) {

		Object[] finderArgs = new Object[] {CProductId};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByCProductId, finderArgs, this);
		}

		if (result instanceof CPDAvailabilityEstimate) {
			CPDAvailabilityEstimate cpdAvailabilityEstimate =
				(CPDAvailabilityEstimate)result;

			if ((CProductId != cpdAvailabilityEstimate.getCProductId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_CPDAVAILABILITYESTIMATE_WHERE);

			query.append(_FINDER_COLUMN_CPRODUCTID_CPRODUCTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CProductId);

				List<CPDAvailabilityEstimate> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByCProductId, finderArgs, list);
				}
				else {
					CPDAvailabilityEstimate cpdAvailabilityEstimate = list.get(
						0);

					result = cpdAvailabilityEstimate;

					cacheResult(cpdAvailabilityEstimate);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathFetchByCProductId, finderArgs);

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
			return (CPDAvailabilityEstimate)result;
		}
	}

	/**
	 * Removes the cpd availability estimate where CProductId = &#63; from the database.
	 *
	 * @param CProductId the c product ID
	 * @return the cpd availability estimate that was removed
	 */
	@Override
	public CPDAvailabilityEstimate removeByCProductId(long CProductId)
		throws NoSuchCPDAvailabilityEstimateException {

		CPDAvailabilityEstimate cpdAvailabilityEstimate = findByCProductId(
			CProductId);

		return remove(cpdAvailabilityEstimate);
	}

	/**
	 * Returns the number of cpd availability estimates where CProductId = &#63;.
	 *
	 * @param CProductId the c product ID
	 * @return the number of matching cpd availability estimates
	 */
	@Override
	public int countByCProductId(long CProductId) {
		FinderPath finderPath = _finderPathCountByCProductId;

		Object[] finderArgs = new Object[] {CProductId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPDAVAILABILITYESTIMATE_WHERE);

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
		"cpdAvailabilityEstimate.CProductId = ?";

	public CPDAvailabilityEstimatePersistenceImpl() {
		setModelClass(CPDAvailabilityEstimate.class);

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
	 * Caches the cpd availability estimate in the entity cache if it is enabled.
	 *
	 * @param cpdAvailabilityEstimate the cpd availability estimate
	 */
	@Override
	public void cacheResult(CPDAvailabilityEstimate cpdAvailabilityEstimate) {
		entityCache.putResult(
			CPDAvailabilityEstimateModelImpl.ENTITY_CACHE_ENABLED,
			CPDAvailabilityEstimateImpl.class,
			cpdAvailabilityEstimate.getPrimaryKey(), cpdAvailabilityEstimate);

		finderCache.putResult(
			_finderPathFetchByCProductId,
			new Object[] {cpdAvailabilityEstimate.getCProductId()},
			cpdAvailabilityEstimate);

		cpdAvailabilityEstimate.resetOriginalValues();
	}

	/**
	 * Caches the cpd availability estimates in the entity cache if it is enabled.
	 *
	 * @param cpdAvailabilityEstimates the cpd availability estimates
	 */
	@Override
	public void cacheResult(
		List<CPDAvailabilityEstimate> cpdAvailabilityEstimates) {

		for (CPDAvailabilityEstimate cpdAvailabilityEstimate :
				cpdAvailabilityEstimates) {

			if (entityCache.getResult(
					CPDAvailabilityEstimateModelImpl.ENTITY_CACHE_ENABLED,
					CPDAvailabilityEstimateImpl.class,
					cpdAvailabilityEstimate.getPrimaryKey()) == null) {

				cacheResult(cpdAvailabilityEstimate);
			}
			else {
				cpdAvailabilityEstimate.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cpd availability estimates.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CPDAvailabilityEstimateImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cpd availability estimate.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CPDAvailabilityEstimate cpdAvailabilityEstimate) {
		entityCache.removeResult(
			CPDAvailabilityEstimateModelImpl.ENTITY_CACHE_ENABLED,
			CPDAvailabilityEstimateImpl.class,
			cpdAvailabilityEstimate.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(CPDAvailabilityEstimateModelImpl)cpdAvailabilityEstimate, true);
	}

	@Override
	public void clearCache(
		List<CPDAvailabilityEstimate> cpdAvailabilityEstimates) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CPDAvailabilityEstimate cpdAvailabilityEstimate :
				cpdAvailabilityEstimates) {

			entityCache.removeResult(
				CPDAvailabilityEstimateModelImpl.ENTITY_CACHE_ENABLED,
				CPDAvailabilityEstimateImpl.class,
				cpdAvailabilityEstimate.getPrimaryKey());

			clearUniqueFindersCache(
				(CPDAvailabilityEstimateModelImpl)cpdAvailabilityEstimate,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		CPDAvailabilityEstimateModelImpl cpdAvailabilityEstimateModelImpl) {

		Object[] args = new Object[] {
			cpdAvailabilityEstimateModelImpl.getCProductId()
		};

		finderCache.putResult(
			_finderPathCountByCProductId, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByCProductId, args,
			cpdAvailabilityEstimateModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CPDAvailabilityEstimateModelImpl cpdAvailabilityEstimateModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				cpdAvailabilityEstimateModelImpl.getCProductId()
			};

			finderCache.removeResult(_finderPathCountByCProductId, args);
			finderCache.removeResult(_finderPathFetchByCProductId, args);
		}

		if ((cpdAvailabilityEstimateModelImpl.getColumnBitmask() &
			 _finderPathFetchByCProductId.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				cpdAvailabilityEstimateModelImpl.getOriginalCProductId()
			};

			finderCache.removeResult(_finderPathCountByCProductId, args);
			finderCache.removeResult(_finderPathFetchByCProductId, args);
		}
	}

	/**
	 * Creates a new cpd availability estimate with the primary key. Does not add the cpd availability estimate to the database.
	 *
	 * @param CPDAvailabilityEstimateId the primary key for the new cpd availability estimate
	 * @return the new cpd availability estimate
	 */
	@Override
	public CPDAvailabilityEstimate create(long CPDAvailabilityEstimateId) {
		CPDAvailabilityEstimate cpdAvailabilityEstimate =
			new CPDAvailabilityEstimateImpl();

		cpdAvailabilityEstimate.setNew(true);
		cpdAvailabilityEstimate.setPrimaryKey(CPDAvailabilityEstimateId);

		String uuid = PortalUUIDUtil.generate();

		cpdAvailabilityEstimate.setUuid(uuid);

		cpdAvailabilityEstimate.setCompanyId(CompanyThreadLocal.getCompanyId());

		return cpdAvailabilityEstimate;
	}

	/**
	 * Removes the cpd availability estimate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPDAvailabilityEstimateId the primary key of the cpd availability estimate
	 * @return the cpd availability estimate that was removed
	 * @throws NoSuchCPDAvailabilityEstimateException if a cpd availability estimate with the primary key could not be found
	 */
	@Override
	public CPDAvailabilityEstimate remove(long CPDAvailabilityEstimateId)
		throws NoSuchCPDAvailabilityEstimateException {

		return remove((Serializable)CPDAvailabilityEstimateId);
	}

	/**
	 * Removes the cpd availability estimate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cpd availability estimate
	 * @return the cpd availability estimate that was removed
	 * @throws NoSuchCPDAvailabilityEstimateException if a cpd availability estimate with the primary key could not be found
	 */
	@Override
	public CPDAvailabilityEstimate remove(Serializable primaryKey)
		throws NoSuchCPDAvailabilityEstimateException {

		Session session = null;

		try {
			session = openSession();

			CPDAvailabilityEstimate cpdAvailabilityEstimate =
				(CPDAvailabilityEstimate)session.get(
					CPDAvailabilityEstimateImpl.class, primaryKey);

			if (cpdAvailabilityEstimate == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCPDAvailabilityEstimateException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(cpdAvailabilityEstimate);
		}
		catch (NoSuchCPDAvailabilityEstimateException nsee) {
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
	protected CPDAvailabilityEstimate removeImpl(
		CPDAvailabilityEstimate cpdAvailabilityEstimate) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cpdAvailabilityEstimate)) {
				cpdAvailabilityEstimate = (CPDAvailabilityEstimate)session.get(
					CPDAvailabilityEstimateImpl.class,
					cpdAvailabilityEstimate.getPrimaryKeyObj());
			}

			if (cpdAvailabilityEstimate != null) {
				session.delete(cpdAvailabilityEstimate);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cpdAvailabilityEstimate != null) {
			clearCache(cpdAvailabilityEstimate);
		}

		return cpdAvailabilityEstimate;
	}

	@Override
	public CPDAvailabilityEstimate updateImpl(
		CPDAvailabilityEstimate cpdAvailabilityEstimate) {

		boolean isNew = cpdAvailabilityEstimate.isNew();

		if (!(cpdAvailabilityEstimate instanceof
				CPDAvailabilityEstimateModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(cpdAvailabilityEstimate.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					cpdAvailabilityEstimate);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in cpdAvailabilityEstimate proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CPDAvailabilityEstimate implementation " +
					cpdAvailabilityEstimate.getClass());
		}

		CPDAvailabilityEstimateModelImpl cpdAvailabilityEstimateModelImpl =
			(CPDAvailabilityEstimateModelImpl)cpdAvailabilityEstimate;

		if (Validator.isNull(cpdAvailabilityEstimate.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			cpdAvailabilityEstimate.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (cpdAvailabilityEstimate.getCreateDate() == null)) {
			if (serviceContext == null) {
				cpdAvailabilityEstimate.setCreateDate(now);
			}
			else {
				cpdAvailabilityEstimate.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!cpdAvailabilityEstimateModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				cpdAvailabilityEstimate.setModifiedDate(now);
			}
			else {
				cpdAvailabilityEstimate.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (cpdAvailabilityEstimate.isNew()) {
				session.save(cpdAvailabilityEstimate);

				cpdAvailabilityEstimate.setNew(false);
			}
			else {
				cpdAvailabilityEstimate =
					(CPDAvailabilityEstimate)session.merge(
						cpdAvailabilityEstimate);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CPDAvailabilityEstimateModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				cpdAvailabilityEstimateModelImpl.getUuid()
			};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				cpdAvailabilityEstimateModelImpl.getUuid(),
				cpdAvailabilityEstimateModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {
				cpdAvailabilityEstimateModelImpl.
					getCommerceAvailabilityEstimateId()
			};

			finderCache.removeResult(
				_finderPathCountByCommerceAvailabilityEstimateId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCommerceAvailabilityEstimateId,
				args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((cpdAvailabilityEstimateModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpdAvailabilityEstimateModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {
					cpdAvailabilityEstimateModelImpl.getUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((cpdAvailabilityEstimateModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpdAvailabilityEstimateModelImpl.getOriginalUuid(),
					cpdAvailabilityEstimateModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					cpdAvailabilityEstimateModelImpl.getUuid(),
					cpdAvailabilityEstimateModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((cpdAvailabilityEstimateModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCommerceAvailabilityEstimateId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					cpdAvailabilityEstimateModelImpl.
						getOriginalCommerceAvailabilityEstimateId()
				};

				finderCache.removeResult(
					_finderPathCountByCommerceAvailabilityEstimateId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommerceAvailabilityEstimateId,
					args);

				args = new Object[] {
					cpdAvailabilityEstimateModelImpl.
						getCommerceAvailabilityEstimateId()
				};

				finderCache.removeResult(
					_finderPathCountByCommerceAvailabilityEstimateId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommerceAvailabilityEstimateId,
					args);
			}
		}

		entityCache.putResult(
			CPDAvailabilityEstimateModelImpl.ENTITY_CACHE_ENABLED,
			CPDAvailabilityEstimateImpl.class,
			cpdAvailabilityEstimate.getPrimaryKey(), cpdAvailabilityEstimate,
			false);

		clearUniqueFindersCache(cpdAvailabilityEstimateModelImpl, false);
		cacheUniqueFindersCache(cpdAvailabilityEstimateModelImpl);

		cpdAvailabilityEstimate.resetOriginalValues();

		return cpdAvailabilityEstimate;
	}

	/**
	 * Returns the cpd availability estimate with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cpd availability estimate
	 * @return the cpd availability estimate
	 * @throws NoSuchCPDAvailabilityEstimateException if a cpd availability estimate with the primary key could not be found
	 */
	@Override
	public CPDAvailabilityEstimate findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCPDAvailabilityEstimateException {

		CPDAvailabilityEstimate cpdAvailabilityEstimate = fetchByPrimaryKey(
			primaryKey);

		if (cpdAvailabilityEstimate == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCPDAvailabilityEstimateException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return cpdAvailabilityEstimate;
	}

	/**
	 * Returns the cpd availability estimate with the primary key or throws a <code>NoSuchCPDAvailabilityEstimateException</code> if it could not be found.
	 *
	 * @param CPDAvailabilityEstimateId the primary key of the cpd availability estimate
	 * @return the cpd availability estimate
	 * @throws NoSuchCPDAvailabilityEstimateException if a cpd availability estimate with the primary key could not be found
	 */
	@Override
	public CPDAvailabilityEstimate findByPrimaryKey(
			long CPDAvailabilityEstimateId)
		throws NoSuchCPDAvailabilityEstimateException {

		return findByPrimaryKey((Serializable)CPDAvailabilityEstimateId);
	}

	/**
	 * Returns the cpd availability estimate with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cpd availability estimate
	 * @return the cpd availability estimate, or <code>null</code> if a cpd availability estimate with the primary key could not be found
	 */
	@Override
	public CPDAvailabilityEstimate fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			CPDAvailabilityEstimateModelImpl.ENTITY_CACHE_ENABLED,
			CPDAvailabilityEstimateImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CPDAvailabilityEstimate cpdAvailabilityEstimate =
			(CPDAvailabilityEstimate)serializable;

		if (cpdAvailabilityEstimate == null) {
			Session session = null;

			try {
				session = openSession();

				cpdAvailabilityEstimate = (CPDAvailabilityEstimate)session.get(
					CPDAvailabilityEstimateImpl.class, primaryKey);

				if (cpdAvailabilityEstimate != null) {
					cacheResult(cpdAvailabilityEstimate);
				}
				else {
					entityCache.putResult(
						CPDAvailabilityEstimateModelImpl.ENTITY_CACHE_ENABLED,
						CPDAvailabilityEstimateImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CPDAvailabilityEstimateModelImpl.ENTITY_CACHE_ENABLED,
					CPDAvailabilityEstimateImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cpdAvailabilityEstimate;
	}

	/**
	 * Returns the cpd availability estimate with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param CPDAvailabilityEstimateId the primary key of the cpd availability estimate
	 * @return the cpd availability estimate, or <code>null</code> if a cpd availability estimate with the primary key could not be found
	 */
	@Override
	public CPDAvailabilityEstimate fetchByPrimaryKey(
		long CPDAvailabilityEstimateId) {

		return fetchByPrimaryKey((Serializable)CPDAvailabilityEstimateId);
	}

	@Override
	public Map<Serializable, CPDAvailabilityEstimate> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CPDAvailabilityEstimate> map =
			new HashMap<Serializable, CPDAvailabilityEstimate>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CPDAvailabilityEstimate cpdAvailabilityEstimate = fetchByPrimaryKey(
				primaryKey);

			if (cpdAvailabilityEstimate != null) {
				map.put(primaryKey, cpdAvailabilityEstimate);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CPDAvailabilityEstimateModelImpl.ENTITY_CACHE_ENABLED,
				CPDAvailabilityEstimateImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CPDAvailabilityEstimate)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_CPDAVAILABILITYESTIMATE_WHERE_PKS_IN);

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

			for (CPDAvailabilityEstimate cpdAvailabilityEstimate :
					(List<CPDAvailabilityEstimate>)q.list()) {

				map.put(
					cpdAvailabilityEstimate.getPrimaryKeyObj(),
					cpdAvailabilityEstimate);

				cacheResult(cpdAvailabilityEstimate);

				uncachedPrimaryKeys.remove(
					cpdAvailabilityEstimate.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CPDAvailabilityEstimateModelImpl.ENTITY_CACHE_ENABLED,
					CPDAvailabilityEstimateImpl.class, primaryKey, nullModel);
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
	 * Returns all the cpd availability estimates.
	 *
	 * @return the cpd availability estimates
	 */
	@Override
	public List<CPDAvailabilityEstimate> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cpd availability estimates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDAvailabilityEstimateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cpd availability estimates
	 * @param end the upper bound of the range of cpd availability estimates (not inclusive)
	 * @return the range of cpd availability estimates
	 */
	@Override
	public List<CPDAvailabilityEstimate> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cpd availability estimates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDAvailabilityEstimateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cpd availability estimates
	 * @param end the upper bound of the range of cpd availability estimates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cpd availability estimates
	 */
	@Override
	public List<CPDAvailabilityEstimate> findAll(
		int start, int end,
		OrderByComparator<CPDAvailabilityEstimate> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cpd availability estimates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDAvailabilityEstimateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cpd availability estimates
	 * @param end the upper bound of the range of cpd availability estimates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cpd availability estimates
	 */
	@Override
	public List<CPDAvailabilityEstimate> findAll(
		int start, int end,
		OrderByComparator<CPDAvailabilityEstimate> orderByComparator,
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

		List<CPDAvailabilityEstimate> list = null;

		if (retrieveFromCache) {
			list = (List<CPDAvailabilityEstimate>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CPDAVAILABILITYESTIMATE);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CPDAVAILABILITYESTIMATE;

				if (pagination) {
					sql = sql.concat(
						CPDAvailabilityEstimateModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CPDAvailabilityEstimate>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPDAvailabilityEstimate>)QueryUtil.list(
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
	 * Removes all the cpd availability estimates from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CPDAvailabilityEstimate cpdAvailabilityEstimate : findAll()) {
			remove(cpdAvailabilityEstimate);
		}
	}

	/**
	 * Returns the number of cpd availability estimates.
	 *
	 * @return the number of cpd availability estimates
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
					_SQL_COUNT_CPDAVAILABILITYESTIMATE);

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
		return CPDAvailabilityEstimateModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cpd availability estimate persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CPDAvailabilityEstimateModelImpl.ENTITY_CACHE_ENABLED,
			CPDAvailabilityEstimateModelImpl.FINDER_CACHE_ENABLED,
			CPDAvailabilityEstimateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CPDAvailabilityEstimateModelImpl.ENTITY_CACHE_ENABLED,
			CPDAvailabilityEstimateModelImpl.FINDER_CACHE_ENABLED,
			CPDAvailabilityEstimateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CPDAvailabilityEstimateModelImpl.ENTITY_CACHE_ENABLED,
			CPDAvailabilityEstimateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			CPDAvailabilityEstimateModelImpl.ENTITY_CACHE_ENABLED,
			CPDAvailabilityEstimateModelImpl.FINDER_CACHE_ENABLED,
			CPDAvailabilityEstimateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			CPDAvailabilityEstimateModelImpl.ENTITY_CACHE_ENABLED,
			CPDAvailabilityEstimateModelImpl.FINDER_CACHE_ENABLED,
			CPDAvailabilityEstimateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			CPDAvailabilityEstimateModelImpl.UUID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			CPDAvailabilityEstimateModelImpl.ENTITY_CACHE_ENABLED,
			CPDAvailabilityEstimateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			CPDAvailabilityEstimateModelImpl.ENTITY_CACHE_ENABLED,
			CPDAvailabilityEstimateModelImpl.FINDER_CACHE_ENABLED,
			CPDAvailabilityEstimateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			CPDAvailabilityEstimateModelImpl.ENTITY_CACHE_ENABLED,
			CPDAvailabilityEstimateModelImpl.FINDER_CACHE_ENABLED,
			CPDAvailabilityEstimateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			CPDAvailabilityEstimateModelImpl.UUID_COLUMN_BITMASK |
			CPDAvailabilityEstimateModelImpl.COMPANYID_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			CPDAvailabilityEstimateModelImpl.ENTITY_CACHE_ENABLED,
			CPDAvailabilityEstimateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByCommerceAvailabilityEstimateId =
			new FinderPath(
				CPDAvailabilityEstimateModelImpl.ENTITY_CACHE_ENABLED,
				CPDAvailabilityEstimateModelImpl.FINDER_CACHE_ENABLED,
				CPDAvailabilityEstimateImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByCommerceAvailabilityEstimateId",
				new String[] {
					Long.class.getName(), Integer.class.getName(),
					Integer.class.getName(), OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByCommerceAvailabilityEstimateId =
			new FinderPath(
				CPDAvailabilityEstimateModelImpl.ENTITY_CACHE_ENABLED,
				CPDAvailabilityEstimateModelImpl.FINDER_CACHE_ENABLED,
				CPDAvailabilityEstimateImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByCommerceAvailabilityEstimateId",
				new String[] {Long.class.getName()},
				CPDAvailabilityEstimateModelImpl.
					COMMERCEAVAILABILITYESTIMATEID_COLUMN_BITMASK);

		_finderPathCountByCommerceAvailabilityEstimateId = new FinderPath(
			CPDAvailabilityEstimateModelImpl.ENTITY_CACHE_ENABLED,
			CPDAvailabilityEstimateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceAvailabilityEstimateId",
			new String[] {Long.class.getName()});

		_finderPathFetchByCProductId = new FinderPath(
			CPDAvailabilityEstimateModelImpl.ENTITY_CACHE_ENABLED,
			CPDAvailabilityEstimateModelImpl.FINDER_CACHE_ENABLED,
			CPDAvailabilityEstimateImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByCProductId", new String[] {Long.class.getName()},
			CPDAvailabilityEstimateModelImpl.CPRODUCTID_COLUMN_BITMASK);

		_finderPathCountByCProductId = new FinderPath(
			CPDAvailabilityEstimateModelImpl.ENTITY_CACHE_ENABLED,
			CPDAvailabilityEstimateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCProductId",
			new String[] {Long.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(CPDAvailabilityEstimateImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_CPDAVAILABILITYESTIMATE =
		"SELECT cpdAvailabilityEstimate FROM CPDAvailabilityEstimate cpdAvailabilityEstimate";

	private static final String
		_SQL_SELECT_CPDAVAILABILITYESTIMATE_WHERE_PKS_IN =
			"SELECT cpdAvailabilityEstimate FROM CPDAvailabilityEstimate cpdAvailabilityEstimate WHERE CPDAvailabilityEstimateId IN (";

	private static final String _SQL_SELECT_CPDAVAILABILITYESTIMATE_WHERE =
		"SELECT cpdAvailabilityEstimate FROM CPDAvailabilityEstimate cpdAvailabilityEstimate WHERE ";

	private static final String _SQL_COUNT_CPDAVAILABILITYESTIMATE =
		"SELECT COUNT(cpdAvailabilityEstimate) FROM CPDAvailabilityEstimate cpdAvailabilityEstimate";

	private static final String _SQL_COUNT_CPDAVAILABILITYESTIMATE_WHERE =
		"SELECT COUNT(cpdAvailabilityEstimate) FROM CPDAvailabilityEstimate cpdAvailabilityEstimate WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"cpdAvailabilityEstimate.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CPDAvailabilityEstimate exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CPDAvailabilityEstimate exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CPDAvailabilityEstimatePersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}