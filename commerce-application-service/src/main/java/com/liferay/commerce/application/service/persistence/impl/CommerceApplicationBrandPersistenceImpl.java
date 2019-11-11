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

package com.liferay.commerce.application.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.application.exception.NoSuchApplicationBrandException;
import com.liferay.commerce.application.model.CommerceApplicationBrand;
import com.liferay.commerce.application.model.impl.CommerceApplicationBrandImpl;
import com.liferay.commerce.application.model.impl.CommerceApplicationBrandModelImpl;
import com.liferay.commerce.application.service.persistence.CommerceApplicationBrandPersistence;

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
import java.util.Set;

/**
 * The persistence implementation for the commerce application brand service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceApplicationBrandPersistence
 * @see com.liferay.commerce.application.service.persistence.CommerceApplicationBrandUtil
 * @generated
 */
@ProviderType
public class CommerceApplicationBrandPersistenceImpl extends BasePersistenceImpl<CommerceApplicationBrand>
	implements CommerceApplicationBrandPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommerceApplicationBrandUtil} to access the commerce application brand persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommerceApplicationBrandImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommerceApplicationBrandModelImpl.ENTITY_CACHE_ENABLED,
			CommerceApplicationBrandModelImpl.FINDER_CACHE_ENABLED,
			CommerceApplicationBrandImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommerceApplicationBrandModelImpl.ENTITY_CACHE_ENABLED,
			CommerceApplicationBrandModelImpl.FINDER_CACHE_ENABLED,
			CommerceApplicationBrandImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommerceApplicationBrandModelImpl.ENTITY_CACHE_ENABLED,
			CommerceApplicationBrandModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(CommerceApplicationBrandModelImpl.ENTITY_CACHE_ENABLED,
			CommerceApplicationBrandModelImpl.FINDER_CACHE_ENABLED,
			CommerceApplicationBrandImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(CommerceApplicationBrandModelImpl.ENTITY_CACHE_ENABLED,
			CommerceApplicationBrandModelImpl.FINDER_CACHE_ENABLED,
			CommerceApplicationBrandImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			CommerceApplicationBrandModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceApplicationBrandModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(CommerceApplicationBrandModelImpl.ENTITY_CACHE_ENABLED,
			CommerceApplicationBrandModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the commerce application brands where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce application brands
	 */
	@Override
	public List<CommerceApplicationBrand> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the commerce application brands where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationBrandModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce application brands
	 * @param end the upper bound of the range of commerce application brands (not inclusive)
	 * @return the range of matching commerce application brands
	 */
	@Override
	public List<CommerceApplicationBrand> findByCompanyId(long companyId,
		int start, int end) {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce application brands where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationBrandModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce application brands
	 * @param end the upper bound of the range of commerce application brands (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce application brands
	 */
	@Override
	public List<CommerceApplicationBrand> findByCompanyId(long companyId,
		int start, int end,
		OrderByComparator<CommerceApplicationBrand> orderByComparator) {
		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce application brands where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationBrandModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce application brands
	 * @param end the upper bound of the range of commerce application brands (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce application brands
	 */
	@Override
	public List<CommerceApplicationBrand> findByCompanyId(long companyId,
		int start, int end,
		OrderByComparator<CommerceApplicationBrand> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<CommerceApplicationBrand> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceApplicationBrand>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceApplicationBrand commerceApplicationBrand : list) {
					if ((companyId != commerceApplicationBrand.getCompanyId())) {
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

			query.append(_SQL_SELECT_COMMERCEAPPLICATIONBRAND_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceApplicationBrandModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<CommerceApplicationBrand>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceApplicationBrand>)QueryUtil.list(q,
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
	 * Returns the first commerce application brand in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce application brand
	 * @throws NoSuchApplicationBrandException if a matching commerce application brand could not be found
	 */
	@Override
	public CommerceApplicationBrand findByCompanyId_First(long companyId,
		OrderByComparator<CommerceApplicationBrand> orderByComparator)
		throws NoSuchApplicationBrandException {
		CommerceApplicationBrand commerceApplicationBrand = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (commerceApplicationBrand != null) {
			return commerceApplicationBrand;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchApplicationBrandException(msg.toString());
	}

	/**
	 * Returns the first commerce application brand in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce application brand, or <code>null</code> if a matching commerce application brand could not be found
	 */
	@Override
	public CommerceApplicationBrand fetchByCompanyId_First(long companyId,
		OrderByComparator<CommerceApplicationBrand> orderByComparator) {
		List<CommerceApplicationBrand> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce application brand in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce application brand
	 * @throws NoSuchApplicationBrandException if a matching commerce application brand could not be found
	 */
	@Override
	public CommerceApplicationBrand findByCompanyId_Last(long companyId,
		OrderByComparator<CommerceApplicationBrand> orderByComparator)
		throws NoSuchApplicationBrandException {
		CommerceApplicationBrand commerceApplicationBrand = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (commerceApplicationBrand != null) {
			return commerceApplicationBrand;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchApplicationBrandException(msg.toString());
	}

	/**
	 * Returns the last commerce application brand in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce application brand, or <code>null</code> if a matching commerce application brand could not be found
	 */
	@Override
	public CommerceApplicationBrand fetchByCompanyId_Last(long companyId,
		OrderByComparator<CommerceApplicationBrand> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<CommerceApplicationBrand> list = findByCompanyId(companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce application brands before and after the current commerce application brand in the ordered set where companyId = &#63;.
	 *
	 * @param commerceApplicationBrandId the primary key of the current commerce application brand
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce application brand
	 * @throws NoSuchApplicationBrandException if a commerce application brand with the primary key could not be found
	 */
	@Override
	public CommerceApplicationBrand[] findByCompanyId_PrevAndNext(
		long commerceApplicationBrandId, long companyId,
		OrderByComparator<CommerceApplicationBrand> orderByComparator)
		throws NoSuchApplicationBrandException {
		CommerceApplicationBrand commerceApplicationBrand = findByPrimaryKey(commerceApplicationBrandId);

		Session session = null;

		try {
			session = openSession();

			CommerceApplicationBrand[] array = new CommerceApplicationBrandImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session,
					commerceApplicationBrand, companyId, orderByComparator, true);

			array[1] = commerceApplicationBrand;

			array[2] = getByCompanyId_PrevAndNext(session,
					commerceApplicationBrand, companyId, orderByComparator,
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

	protected CommerceApplicationBrand getByCompanyId_PrevAndNext(
		Session session, CommerceApplicationBrand commerceApplicationBrand,
		long companyId,
		OrderByComparator<CommerceApplicationBrand> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEAPPLICATIONBRAND_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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
			query.append(CommerceApplicationBrandModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceApplicationBrand);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceApplicationBrand> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the commerce application brands that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce application brands that the user has permission to view
	 */
	@Override
	public List<CommerceApplicationBrand> filterFindByCompanyId(long companyId) {
		return filterFindByCompanyId(companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce application brands that the user has permission to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationBrandModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce application brands
	 * @param end the upper bound of the range of commerce application brands (not inclusive)
	 * @return the range of matching commerce application brands that the user has permission to view
	 */
	@Override
	public List<CommerceApplicationBrand> filterFindByCompanyId(
		long companyId, int start, int end) {
		return filterFindByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce application brands that the user has permissions to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationBrandModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce application brands
	 * @param end the upper bound of the range of commerce application brands (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce application brands that the user has permission to view
	 */
	@Override
	public List<CommerceApplicationBrand> filterFindByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceApplicationBrand> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByCompanyId(companyId, start, end, orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_COMMERCEAPPLICATIONBRAND_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_COMMERCEAPPLICATIONBRAND_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEAPPLICATIONBRAND_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CommerceApplicationBrandModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceApplicationBrandModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceApplicationBrand.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS,
					CommerceApplicationBrandImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE,
					CommerceApplicationBrandImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			return (List<CommerceApplicationBrand>)QueryUtil.list(q,
				getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the commerce application brands before and after the current commerce application brand in the ordered set of commerce application brands that the user has permission to view where companyId = &#63;.
	 *
	 * @param commerceApplicationBrandId the primary key of the current commerce application brand
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce application brand
	 * @throws NoSuchApplicationBrandException if a commerce application brand with the primary key could not be found
	 */
	@Override
	public CommerceApplicationBrand[] filterFindByCompanyId_PrevAndNext(
		long commerceApplicationBrandId, long companyId,
		OrderByComparator<CommerceApplicationBrand> orderByComparator)
		throws NoSuchApplicationBrandException {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByCompanyId_PrevAndNext(commerceApplicationBrandId,
				companyId, orderByComparator);
		}

		CommerceApplicationBrand commerceApplicationBrand = findByPrimaryKey(commerceApplicationBrandId);

		Session session = null;

		try {
			session = openSession();

			CommerceApplicationBrand[] array = new CommerceApplicationBrandImpl[3];

			array[0] = filterGetByCompanyId_PrevAndNext(session,
					commerceApplicationBrand, companyId, orderByComparator, true);

			array[1] = commerceApplicationBrand;

			array[2] = filterGetByCompanyId_PrevAndNext(session,
					commerceApplicationBrand, companyId, orderByComparator,
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

	protected CommerceApplicationBrand filterGetByCompanyId_PrevAndNext(
		Session session, CommerceApplicationBrand commerceApplicationBrand,
		long companyId,
		OrderByComparator<CommerceApplicationBrand> orderByComparator,
		boolean previous) {
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
			query.append(_FILTER_SQL_SELECT_COMMERCEAPPLICATIONBRAND_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_COMMERCEAPPLICATIONBRAND_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEAPPLICATIONBRAND_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CommerceApplicationBrandModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceApplicationBrandModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceApplicationBrand.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CommerceApplicationBrandImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CommerceApplicationBrandImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceApplicationBrand);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceApplicationBrand> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce application brands where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (CommerceApplicationBrand commerceApplicationBrand : findByCompanyId(
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(commerceApplicationBrand);
		}
	}

	/**
	 * Returns the number of commerce application brands where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce application brands
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEAPPLICATIONBRAND_WHERE);

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
	 * Returns the number of commerce application brands that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce application brands that the user has permission to view
	 */
	@Override
	public int filterCountByCompanyId(long companyId) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return countByCompanyId(companyId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_COMMERCEAPPLICATIONBRAND_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceApplicationBrand.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "commerceApplicationBrand.companyId = ?";

	public CommerceApplicationBrandPersistenceImpl() {
		setModelClass(CommerceApplicationBrand.class);
	}

	/**
	 * Caches the commerce application brand in the entity cache if it is enabled.
	 *
	 * @param commerceApplicationBrand the commerce application brand
	 */
	@Override
	public void cacheResult(CommerceApplicationBrand commerceApplicationBrand) {
		entityCache.putResult(CommerceApplicationBrandModelImpl.ENTITY_CACHE_ENABLED,
			CommerceApplicationBrandImpl.class,
			commerceApplicationBrand.getPrimaryKey(), commerceApplicationBrand);

		commerceApplicationBrand.resetOriginalValues();
	}

	/**
	 * Caches the commerce application brands in the entity cache if it is enabled.
	 *
	 * @param commerceApplicationBrands the commerce application brands
	 */
	@Override
	public void cacheResult(
		List<CommerceApplicationBrand> commerceApplicationBrands) {
		for (CommerceApplicationBrand commerceApplicationBrand : commerceApplicationBrands) {
			if (entityCache.getResult(
						CommerceApplicationBrandModelImpl.ENTITY_CACHE_ENABLED,
						CommerceApplicationBrandImpl.class,
						commerceApplicationBrand.getPrimaryKey()) == null) {
				cacheResult(commerceApplicationBrand);
			}
			else {
				commerceApplicationBrand.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce application brands.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceApplicationBrandImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce application brand.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceApplicationBrand commerceApplicationBrand) {
		entityCache.removeResult(CommerceApplicationBrandModelImpl.ENTITY_CACHE_ENABLED,
			CommerceApplicationBrandImpl.class,
			commerceApplicationBrand.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<CommerceApplicationBrand> commerceApplicationBrands) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceApplicationBrand commerceApplicationBrand : commerceApplicationBrands) {
			entityCache.removeResult(CommerceApplicationBrandModelImpl.ENTITY_CACHE_ENABLED,
				CommerceApplicationBrandImpl.class,
				commerceApplicationBrand.getPrimaryKey());
		}
	}

	/**
	 * Creates a new commerce application brand with the primary key. Does not add the commerce application brand to the database.
	 *
	 * @param commerceApplicationBrandId the primary key for the new commerce application brand
	 * @return the new commerce application brand
	 */
	@Override
	public CommerceApplicationBrand create(long commerceApplicationBrandId) {
		CommerceApplicationBrand commerceApplicationBrand = new CommerceApplicationBrandImpl();

		commerceApplicationBrand.setNew(true);
		commerceApplicationBrand.setPrimaryKey(commerceApplicationBrandId);

		commerceApplicationBrand.setCompanyId(companyProvider.getCompanyId());

		return commerceApplicationBrand;
	}

	/**
	 * Removes the commerce application brand with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceApplicationBrandId the primary key of the commerce application brand
	 * @return the commerce application brand that was removed
	 * @throws NoSuchApplicationBrandException if a commerce application brand with the primary key could not be found
	 */
	@Override
	public CommerceApplicationBrand remove(long commerceApplicationBrandId)
		throws NoSuchApplicationBrandException {
		return remove((Serializable)commerceApplicationBrandId);
	}

	/**
	 * Removes the commerce application brand with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce application brand
	 * @return the commerce application brand that was removed
	 * @throws NoSuchApplicationBrandException if a commerce application brand with the primary key could not be found
	 */
	@Override
	public CommerceApplicationBrand remove(Serializable primaryKey)
		throws NoSuchApplicationBrandException {
		Session session = null;

		try {
			session = openSession();

			CommerceApplicationBrand commerceApplicationBrand = (CommerceApplicationBrand)session.get(CommerceApplicationBrandImpl.class,
					primaryKey);

			if (commerceApplicationBrand == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchApplicationBrandException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commerceApplicationBrand);
		}
		catch (NoSuchApplicationBrandException nsee) {
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
	protected CommerceApplicationBrand removeImpl(
		CommerceApplicationBrand commerceApplicationBrand) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceApplicationBrand)) {
				commerceApplicationBrand = (CommerceApplicationBrand)session.get(CommerceApplicationBrandImpl.class,
						commerceApplicationBrand.getPrimaryKeyObj());
			}

			if (commerceApplicationBrand != null) {
				session.delete(commerceApplicationBrand);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceApplicationBrand != null) {
			clearCache(commerceApplicationBrand);
		}

		return commerceApplicationBrand;
	}

	@Override
	public CommerceApplicationBrand updateImpl(
		CommerceApplicationBrand commerceApplicationBrand) {
		boolean isNew = commerceApplicationBrand.isNew();

		if (!(commerceApplicationBrand instanceof CommerceApplicationBrandModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceApplicationBrand.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(commerceApplicationBrand);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceApplicationBrand proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceApplicationBrand implementation " +
				commerceApplicationBrand.getClass());
		}

		CommerceApplicationBrandModelImpl commerceApplicationBrandModelImpl = (CommerceApplicationBrandModelImpl)commerceApplicationBrand;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceApplicationBrand.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceApplicationBrand.setCreateDate(now);
			}
			else {
				commerceApplicationBrand.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!commerceApplicationBrandModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceApplicationBrand.setModifiedDate(now);
			}
			else {
				commerceApplicationBrand.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceApplicationBrand.isNew()) {
				session.save(commerceApplicationBrand);

				commerceApplicationBrand.setNew(false);
			}
			else {
				commerceApplicationBrand = (CommerceApplicationBrand)session.merge(commerceApplicationBrand);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceApplicationBrandModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					commerceApplicationBrandModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((commerceApplicationBrandModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceApplicationBrandModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] {
						commerceApplicationBrandModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}
		}

		entityCache.putResult(CommerceApplicationBrandModelImpl.ENTITY_CACHE_ENABLED,
			CommerceApplicationBrandImpl.class,
			commerceApplicationBrand.getPrimaryKey(), commerceApplicationBrand,
			false);

		commerceApplicationBrand.resetOriginalValues();

		return commerceApplicationBrand;
	}

	/**
	 * Returns the commerce application brand with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce application brand
	 * @return the commerce application brand
	 * @throws NoSuchApplicationBrandException if a commerce application brand with the primary key could not be found
	 */
	@Override
	public CommerceApplicationBrand findByPrimaryKey(Serializable primaryKey)
		throws NoSuchApplicationBrandException {
		CommerceApplicationBrand commerceApplicationBrand = fetchByPrimaryKey(primaryKey);

		if (commerceApplicationBrand == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchApplicationBrandException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commerceApplicationBrand;
	}

	/**
	 * Returns the commerce application brand with the primary key or throws a {@link NoSuchApplicationBrandException} if it could not be found.
	 *
	 * @param commerceApplicationBrandId the primary key of the commerce application brand
	 * @return the commerce application brand
	 * @throws NoSuchApplicationBrandException if a commerce application brand with the primary key could not be found
	 */
	@Override
	public CommerceApplicationBrand findByPrimaryKey(
		long commerceApplicationBrandId) throws NoSuchApplicationBrandException {
		return findByPrimaryKey((Serializable)commerceApplicationBrandId);
	}

	/**
	 * Returns the commerce application brand with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce application brand
	 * @return the commerce application brand, or <code>null</code> if a commerce application brand with the primary key could not be found
	 */
	@Override
	public CommerceApplicationBrand fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CommerceApplicationBrandModelImpl.ENTITY_CACHE_ENABLED,
				CommerceApplicationBrandImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceApplicationBrand commerceApplicationBrand = (CommerceApplicationBrand)serializable;

		if (commerceApplicationBrand == null) {
			Session session = null;

			try {
				session = openSession();

				commerceApplicationBrand = (CommerceApplicationBrand)session.get(CommerceApplicationBrandImpl.class,
						primaryKey);

				if (commerceApplicationBrand != null) {
					cacheResult(commerceApplicationBrand);
				}
				else {
					entityCache.putResult(CommerceApplicationBrandModelImpl.ENTITY_CACHE_ENABLED,
						CommerceApplicationBrandImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommerceApplicationBrandModelImpl.ENTITY_CACHE_ENABLED,
					CommerceApplicationBrandImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceApplicationBrand;
	}

	/**
	 * Returns the commerce application brand with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceApplicationBrandId the primary key of the commerce application brand
	 * @return the commerce application brand, or <code>null</code> if a commerce application brand with the primary key could not be found
	 */
	@Override
	public CommerceApplicationBrand fetchByPrimaryKey(
		long commerceApplicationBrandId) {
		return fetchByPrimaryKey((Serializable)commerceApplicationBrandId);
	}

	@Override
	public Map<Serializable, CommerceApplicationBrand> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceApplicationBrand> map = new HashMap<Serializable, CommerceApplicationBrand>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceApplicationBrand commerceApplicationBrand = fetchByPrimaryKey(primaryKey);

			if (commerceApplicationBrand != null) {
				map.put(primaryKey, commerceApplicationBrand);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CommerceApplicationBrandModelImpl.ENTITY_CACHE_ENABLED,
					CommerceApplicationBrandImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceApplicationBrand)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMMERCEAPPLICATIONBRAND_WHERE_PKS_IN);

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

			for (CommerceApplicationBrand commerceApplicationBrand : (List<CommerceApplicationBrand>)q.list()) {
				map.put(commerceApplicationBrand.getPrimaryKeyObj(),
					commerceApplicationBrand);

				cacheResult(commerceApplicationBrand);

				uncachedPrimaryKeys.remove(commerceApplicationBrand.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CommerceApplicationBrandModelImpl.ENTITY_CACHE_ENABLED,
					CommerceApplicationBrandImpl.class, primaryKey, nullModel);
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
	 * Returns all the commerce application brands.
	 *
	 * @return the commerce application brands
	 */
	@Override
	public List<CommerceApplicationBrand> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce application brands.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationBrandModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce application brands
	 * @param end the upper bound of the range of commerce application brands (not inclusive)
	 * @return the range of commerce application brands
	 */
	@Override
	public List<CommerceApplicationBrand> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce application brands.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationBrandModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce application brands
	 * @param end the upper bound of the range of commerce application brands (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce application brands
	 */
	@Override
	public List<CommerceApplicationBrand> findAll(int start, int end,
		OrderByComparator<CommerceApplicationBrand> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce application brands.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationBrandModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce application brands
	 * @param end the upper bound of the range of commerce application brands (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce application brands
	 */
	@Override
	public List<CommerceApplicationBrand> findAll(int start, int end,
		OrderByComparator<CommerceApplicationBrand> orderByComparator,
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

		List<CommerceApplicationBrand> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceApplicationBrand>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEAPPLICATIONBRAND);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEAPPLICATIONBRAND;

				if (pagination) {
					sql = sql.concat(CommerceApplicationBrandModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceApplicationBrand>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceApplicationBrand>)QueryUtil.list(q,
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
	 * Removes all the commerce application brands from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceApplicationBrand commerceApplicationBrand : findAll()) {
			remove(commerceApplicationBrand);
		}
	}

	/**
	 * Returns the number of commerce application brands.
	 *
	 * @return the number of commerce application brands
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCEAPPLICATIONBRAND);

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
	protected Map<String, Integer> getTableColumnsMap() {
		return CommerceApplicationBrandModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce application brand persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CommerceApplicationBrandImpl.class.getName());
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
	private static final String _SQL_SELECT_COMMERCEAPPLICATIONBRAND = "SELECT commerceApplicationBrand FROM CommerceApplicationBrand commerceApplicationBrand";
	private static final String _SQL_SELECT_COMMERCEAPPLICATIONBRAND_WHERE_PKS_IN =
		"SELECT commerceApplicationBrand FROM CommerceApplicationBrand commerceApplicationBrand WHERE commerceApplicationBrandId IN (";
	private static final String _SQL_SELECT_COMMERCEAPPLICATIONBRAND_WHERE = "SELECT commerceApplicationBrand FROM CommerceApplicationBrand commerceApplicationBrand WHERE ";
	private static final String _SQL_COUNT_COMMERCEAPPLICATIONBRAND = "SELECT COUNT(commerceApplicationBrand) FROM CommerceApplicationBrand commerceApplicationBrand";
	private static final String _SQL_COUNT_COMMERCEAPPLICATIONBRAND_WHERE = "SELECT COUNT(commerceApplicationBrand) FROM CommerceApplicationBrand commerceApplicationBrand WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "commerceApplicationBrand.commerceApplicationBrandId";
	private static final String _FILTER_SQL_SELECT_COMMERCEAPPLICATIONBRAND_WHERE =
		"SELECT DISTINCT {commerceApplicationBrand.*} FROM CommerceApplicationBrand commerceApplicationBrand WHERE ";
	private static final String _FILTER_SQL_SELECT_COMMERCEAPPLICATIONBRAND_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {CommerceApplicationBrand.*} FROM (SELECT DISTINCT commerceApplicationBrand.commerceApplicationBrandId FROM CommerceApplicationBrand commerceApplicationBrand WHERE ";
	private static final String _FILTER_SQL_SELECT_COMMERCEAPPLICATIONBRAND_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN CommerceApplicationBrand ON TEMP_TABLE.commerceApplicationBrandId = CommerceApplicationBrand.commerceApplicationBrandId";
	private static final String _FILTER_SQL_COUNT_COMMERCEAPPLICATIONBRAND_WHERE =
		"SELECT COUNT(DISTINCT commerceApplicationBrand.commerceApplicationBrandId) AS COUNT_VALUE FROM CommerceApplicationBrand commerceApplicationBrand WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "commerceApplicationBrand";
	private static final String _FILTER_ENTITY_TABLE = "CommerceApplicationBrand";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceApplicationBrand.";
	private static final String _ORDER_BY_ENTITY_TABLE = "CommerceApplicationBrand.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommerceApplicationBrand exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CommerceApplicationBrand exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CommerceApplicationBrandPersistenceImpl.class);
}