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

package com.liferay.commerce.product.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.exception.NoSuchCPRuleCommerceAccountGroupRelException;
import com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel;
import com.liferay.commerce.product.model.impl.CPRuleCommerceAccountGroupRelImpl;
import com.liferay.commerce.product.model.impl.CPRuleCommerceAccountGroupRelModelImpl;
import com.liferay.commerce.product.service.persistence.CPRuleCommerceAccountGroupRelPersistence;

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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
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
import java.util.Set;

/**
 * The persistence implementation for the cp rule commerce account group rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CPRuleCommerceAccountGroupRelPersistence
 * @see com.liferay.commerce.product.service.persistence.CPRuleCommerceAccountGroupRelUtil
 * @generated
 */
@ProviderType
public class CPRuleCommerceAccountGroupRelPersistenceImpl
	extends BasePersistenceImpl<CPRuleCommerceAccountGroupRel>
	implements CPRuleCommerceAccountGroupRelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CPRuleCommerceAccountGroupRelUtil} to access the cp rule commerce account group rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CPRuleCommerceAccountGroupRelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CPRuleCommerceAccountGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CPRuleCommerceAccountGroupRelModelImpl.FINDER_CACHE_ENABLED,
			CPRuleCommerceAccountGroupRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CPRuleCommerceAccountGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CPRuleCommerceAccountGroupRelModelImpl.FINDER_CACHE_ENABLED,
			CPRuleCommerceAccountGroupRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CPRuleCommerceAccountGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CPRuleCommerceAccountGroupRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CPRULEID = new FinderPath(CPRuleCommerceAccountGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CPRuleCommerceAccountGroupRelModelImpl.FINDER_CACHE_ENABLED,
			CPRuleCommerceAccountGroupRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCPRuleId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPRULEID =
		new FinderPath(CPRuleCommerceAccountGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CPRuleCommerceAccountGroupRelModelImpl.FINDER_CACHE_ENABLED,
			CPRuleCommerceAccountGroupRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCPRuleId",
			new String[] { Long.class.getName() },
			CPRuleCommerceAccountGroupRelModelImpl.CPRULEID_COLUMN_BITMASK |
			CPRuleCommerceAccountGroupRelModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CPRULEID = new FinderPath(CPRuleCommerceAccountGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CPRuleCommerceAccountGroupRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCPRuleId", new String[] { Long.class.getName() });

	/**
	 * Returns all the cp rule commerce account group rels where CPRuleId = &#63;.
	 *
	 * @param CPRuleId the cp rule ID
	 * @return the matching cp rule commerce account group rels
	 */
	@Override
	public List<CPRuleCommerceAccountGroupRel> findByCPRuleId(long CPRuleId) {
		return findByCPRuleId(CPRuleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the cp rule commerce account group rels where CPRuleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleCommerceAccountGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPRuleId the cp rule ID
	 * @param start the lower bound of the range of cp rule commerce account group rels
	 * @param end the upper bound of the range of cp rule commerce account group rels (not inclusive)
	 * @return the range of matching cp rule commerce account group rels
	 */
	@Override
	public List<CPRuleCommerceAccountGroupRel> findByCPRuleId(long CPRuleId,
		int start, int end) {
		return findByCPRuleId(CPRuleId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp rule commerce account group rels where CPRuleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleCommerceAccountGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPRuleId the cp rule ID
	 * @param start the lower bound of the range of cp rule commerce account group rels
	 * @param end the upper bound of the range of cp rule commerce account group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp rule commerce account group rels
	 */
	@Override
	public List<CPRuleCommerceAccountGroupRel> findByCPRuleId(long CPRuleId,
		int start, int end,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator) {
		return findByCPRuleId(CPRuleId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp rule commerce account group rels where CPRuleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleCommerceAccountGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPRuleId the cp rule ID
	 * @param start the lower bound of the range of cp rule commerce account group rels
	 * @param end the upper bound of the range of cp rule commerce account group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp rule commerce account group rels
	 */
	@Override
	public List<CPRuleCommerceAccountGroupRel> findByCPRuleId(long CPRuleId,
		int start, int end,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPRULEID;
			finderArgs = new Object[] { CPRuleId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CPRULEID;
			finderArgs = new Object[] { CPRuleId, start, end, orderByComparator };
		}

		List<CPRuleCommerceAccountGroupRel> list = null;

		if (retrieveFromCache) {
			list = (List<CPRuleCommerceAccountGroupRel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel : list) {
					if ((CPRuleId != cpRuleCommerceAccountGroupRel.getCPRuleId())) {
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

			query.append(_SQL_SELECT_CPRULECOMMERCEACCOUNTGROUPREL_WHERE);

			query.append(_FINDER_COLUMN_CPRULEID_CPRULEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CPRuleCommerceAccountGroupRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPRuleId);

				if (!pagination) {
					list = (List<CPRuleCommerceAccountGroupRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPRuleCommerceAccountGroupRel>)QueryUtil.list(q,
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
	 * Returns the first cp rule commerce account group rel in the ordered set where CPRuleId = &#63;.
	 *
	 * @param CPRuleId the cp rule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp rule commerce account group rel
	 * @throws NoSuchCPRuleCommerceAccountGroupRelException if a matching cp rule commerce account group rel could not be found
	 */
	@Override
	public CPRuleCommerceAccountGroupRel findByCPRuleId_First(long CPRuleId,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator)
		throws NoSuchCPRuleCommerceAccountGroupRelException {
		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel = fetchByCPRuleId_First(CPRuleId,
				orderByComparator);

		if (cpRuleCommerceAccountGroupRel != null) {
			return cpRuleCommerceAccountGroupRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPRuleId=");
		msg.append(CPRuleId);

		msg.append("}");

		throw new NoSuchCPRuleCommerceAccountGroupRelException(msg.toString());
	}

	/**
	 * Returns the first cp rule commerce account group rel in the ordered set where CPRuleId = &#63;.
	 *
	 * @param CPRuleId the cp rule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp rule commerce account group rel, or <code>null</code> if a matching cp rule commerce account group rel could not be found
	 */
	@Override
	public CPRuleCommerceAccountGroupRel fetchByCPRuleId_First(long CPRuleId,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator) {
		List<CPRuleCommerceAccountGroupRel> list = findByCPRuleId(CPRuleId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp rule commerce account group rel in the ordered set where CPRuleId = &#63;.
	 *
	 * @param CPRuleId the cp rule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp rule commerce account group rel
	 * @throws NoSuchCPRuleCommerceAccountGroupRelException if a matching cp rule commerce account group rel could not be found
	 */
	@Override
	public CPRuleCommerceAccountGroupRel findByCPRuleId_Last(long CPRuleId,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator)
		throws NoSuchCPRuleCommerceAccountGroupRelException {
		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel = fetchByCPRuleId_Last(CPRuleId,
				orderByComparator);

		if (cpRuleCommerceAccountGroupRel != null) {
			return cpRuleCommerceAccountGroupRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPRuleId=");
		msg.append(CPRuleId);

		msg.append("}");

		throw new NoSuchCPRuleCommerceAccountGroupRelException(msg.toString());
	}

	/**
	 * Returns the last cp rule commerce account group rel in the ordered set where CPRuleId = &#63;.
	 *
	 * @param CPRuleId the cp rule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp rule commerce account group rel, or <code>null</code> if a matching cp rule commerce account group rel could not be found
	 */
	@Override
	public CPRuleCommerceAccountGroupRel fetchByCPRuleId_Last(long CPRuleId,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator) {
		int count = countByCPRuleId(CPRuleId);

		if (count == 0) {
			return null;
		}

		List<CPRuleCommerceAccountGroupRel> list = findByCPRuleId(CPRuleId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp rule commerce account group rels before and after the current cp rule commerce account group rel in the ordered set where CPRuleId = &#63;.
	 *
	 * @param CPRuleCommerceAccountGroupRelId the primary key of the current cp rule commerce account group rel
	 * @param CPRuleId the cp rule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp rule commerce account group rel
	 * @throws NoSuchCPRuleCommerceAccountGroupRelException if a cp rule commerce account group rel with the primary key could not be found
	 */
	@Override
	public CPRuleCommerceAccountGroupRel[] findByCPRuleId_PrevAndNext(
		long CPRuleCommerceAccountGroupRelId, long CPRuleId,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator)
		throws NoSuchCPRuleCommerceAccountGroupRelException {
		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel = findByPrimaryKey(CPRuleCommerceAccountGroupRelId);

		Session session = null;

		try {
			session = openSession();

			CPRuleCommerceAccountGroupRel[] array = new CPRuleCommerceAccountGroupRelImpl[3];

			array[0] = getByCPRuleId_PrevAndNext(session,
					cpRuleCommerceAccountGroupRel, CPRuleId, orderByComparator,
					true);

			array[1] = cpRuleCommerceAccountGroupRel;

			array[2] = getByCPRuleId_PrevAndNext(session,
					cpRuleCommerceAccountGroupRel, CPRuleId, orderByComparator,
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

	protected CPRuleCommerceAccountGroupRel getByCPRuleId_PrevAndNext(
		Session session,
		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel,
		long CPRuleId,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator,
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

		query.append(_SQL_SELECT_CPRULECOMMERCEACCOUNTGROUPREL_WHERE);

		query.append(_FINDER_COLUMN_CPRULEID_CPRULEID_2);

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
			query.append(CPRuleCommerceAccountGroupRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(CPRuleId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cpRuleCommerceAccountGroupRel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CPRuleCommerceAccountGroupRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp rule commerce account group rels where CPRuleId = &#63; from the database.
	 *
	 * @param CPRuleId the cp rule ID
	 */
	@Override
	public void removeByCPRuleId(long CPRuleId) {
		for (CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel : findByCPRuleId(
				CPRuleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(cpRuleCommerceAccountGroupRel);
		}
	}

	/**
	 * Returns the number of cp rule commerce account group rels where CPRuleId = &#63;.
	 *
	 * @param CPRuleId the cp rule ID
	 * @return the number of matching cp rule commerce account group rels
	 */
	@Override
	public int countByCPRuleId(long CPRuleId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CPRULEID;

		Object[] finderArgs = new Object[] { CPRuleId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPRULECOMMERCEACCOUNTGROUPREL_WHERE);

			query.append(_FINDER_COLUMN_CPRULEID_CPRULEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPRuleId);

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

	private static final String _FINDER_COLUMN_CPRULEID_CPRULEID_2 = "cpRuleCommerceAccountGroupRel.CPRuleId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMMERCEACCOUNTGROUPID =
		new FinderPath(CPRuleCommerceAccountGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CPRuleCommerceAccountGroupRelModelImpl.FINDER_CACHE_ENABLED,
			CPRuleCommerceAccountGroupRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCommerceAccountGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEACCOUNTGROUPID =
		new FinderPath(CPRuleCommerceAccountGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CPRuleCommerceAccountGroupRelModelImpl.FINDER_CACHE_ENABLED,
			CPRuleCommerceAccountGroupRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCommerceAccountGroupId",
			new String[] { Long.class.getName() },
			CPRuleCommerceAccountGroupRelModelImpl.COMMERCEACCOUNTGROUPID_COLUMN_BITMASK |
			CPRuleCommerceAccountGroupRelModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMMERCEACCOUNTGROUPID = new FinderPath(CPRuleCommerceAccountGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CPRuleCommerceAccountGroupRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceAccountGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the cp rule commerce account group rels where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @return the matching cp rule commerce account group rels
	 */
	@Override
	public List<CPRuleCommerceAccountGroupRel> findByCommerceAccountGroupId(
		long commerceAccountGroupId) {
		return findByCommerceAccountGroupId(commerceAccountGroupId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp rule commerce account group rels where commerceAccountGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleCommerceAccountGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param start the lower bound of the range of cp rule commerce account group rels
	 * @param end the upper bound of the range of cp rule commerce account group rels (not inclusive)
	 * @return the range of matching cp rule commerce account group rels
	 */
	@Override
	public List<CPRuleCommerceAccountGroupRel> findByCommerceAccountGroupId(
		long commerceAccountGroupId, int start, int end) {
		return findByCommerceAccountGroupId(commerceAccountGroupId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the cp rule commerce account group rels where commerceAccountGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleCommerceAccountGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param start the lower bound of the range of cp rule commerce account group rels
	 * @param end the upper bound of the range of cp rule commerce account group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp rule commerce account group rels
	 */
	@Override
	public List<CPRuleCommerceAccountGroupRel> findByCommerceAccountGroupId(
		long commerceAccountGroupId, int start, int end,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator) {
		return findByCommerceAccountGroupId(commerceAccountGroupId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp rule commerce account group rels where commerceAccountGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleCommerceAccountGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param start the lower bound of the range of cp rule commerce account group rels
	 * @param end the upper bound of the range of cp rule commerce account group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp rule commerce account group rels
	 */
	@Override
	public List<CPRuleCommerceAccountGroupRel> findByCommerceAccountGroupId(
		long commerceAccountGroupId, int start, int end,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEACCOUNTGROUPID;
			finderArgs = new Object[] { commerceAccountGroupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMMERCEACCOUNTGROUPID;
			finderArgs = new Object[] {
					commerceAccountGroupId,
					
					start, end, orderByComparator
				};
		}

		List<CPRuleCommerceAccountGroupRel> list = null;

		if (retrieveFromCache) {
			list = (List<CPRuleCommerceAccountGroupRel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel : list) {
					if ((commerceAccountGroupId != cpRuleCommerceAccountGroupRel.getCommerceAccountGroupId())) {
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

			query.append(_SQL_SELECT_CPRULECOMMERCEACCOUNTGROUPREL_WHERE);

			query.append(_FINDER_COLUMN_COMMERCEACCOUNTGROUPID_COMMERCEACCOUNTGROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CPRuleCommerceAccountGroupRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceAccountGroupId);

				if (!pagination) {
					list = (List<CPRuleCommerceAccountGroupRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPRuleCommerceAccountGroupRel>)QueryUtil.list(q,
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
	 * Returns the first cp rule commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp rule commerce account group rel
	 * @throws NoSuchCPRuleCommerceAccountGroupRelException if a matching cp rule commerce account group rel could not be found
	 */
	@Override
	public CPRuleCommerceAccountGroupRel findByCommerceAccountGroupId_First(
		long commerceAccountGroupId,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator)
		throws NoSuchCPRuleCommerceAccountGroupRelException {
		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel = fetchByCommerceAccountGroupId_First(commerceAccountGroupId,
				orderByComparator);

		if (cpRuleCommerceAccountGroupRel != null) {
			return cpRuleCommerceAccountGroupRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceAccountGroupId=");
		msg.append(commerceAccountGroupId);

		msg.append("}");

		throw new NoSuchCPRuleCommerceAccountGroupRelException(msg.toString());
	}

	/**
	 * Returns the first cp rule commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp rule commerce account group rel, or <code>null</code> if a matching cp rule commerce account group rel could not be found
	 */
	@Override
	public CPRuleCommerceAccountGroupRel fetchByCommerceAccountGroupId_First(
		long commerceAccountGroupId,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator) {
		List<CPRuleCommerceAccountGroupRel> list = findByCommerceAccountGroupId(commerceAccountGroupId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp rule commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp rule commerce account group rel
	 * @throws NoSuchCPRuleCommerceAccountGroupRelException if a matching cp rule commerce account group rel could not be found
	 */
	@Override
	public CPRuleCommerceAccountGroupRel findByCommerceAccountGroupId_Last(
		long commerceAccountGroupId,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator)
		throws NoSuchCPRuleCommerceAccountGroupRelException {
		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel = fetchByCommerceAccountGroupId_Last(commerceAccountGroupId,
				orderByComparator);

		if (cpRuleCommerceAccountGroupRel != null) {
			return cpRuleCommerceAccountGroupRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceAccountGroupId=");
		msg.append(commerceAccountGroupId);

		msg.append("}");

		throw new NoSuchCPRuleCommerceAccountGroupRelException(msg.toString());
	}

	/**
	 * Returns the last cp rule commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp rule commerce account group rel, or <code>null</code> if a matching cp rule commerce account group rel could not be found
	 */
	@Override
	public CPRuleCommerceAccountGroupRel fetchByCommerceAccountGroupId_Last(
		long commerceAccountGroupId,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator) {
		int count = countByCommerceAccountGroupId(commerceAccountGroupId);

		if (count == 0) {
			return null;
		}

		List<CPRuleCommerceAccountGroupRel> list = findByCommerceAccountGroupId(commerceAccountGroupId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp rule commerce account group rels before and after the current cp rule commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	 *
	 * @param CPRuleCommerceAccountGroupRelId the primary key of the current cp rule commerce account group rel
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp rule commerce account group rel
	 * @throws NoSuchCPRuleCommerceAccountGroupRelException if a cp rule commerce account group rel with the primary key could not be found
	 */
	@Override
	public CPRuleCommerceAccountGroupRel[] findByCommerceAccountGroupId_PrevAndNext(
		long CPRuleCommerceAccountGroupRelId, long commerceAccountGroupId,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator)
		throws NoSuchCPRuleCommerceAccountGroupRelException {
		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel = findByPrimaryKey(CPRuleCommerceAccountGroupRelId);

		Session session = null;

		try {
			session = openSession();

			CPRuleCommerceAccountGroupRel[] array = new CPRuleCommerceAccountGroupRelImpl[3];

			array[0] = getByCommerceAccountGroupId_PrevAndNext(session,
					cpRuleCommerceAccountGroupRel, commerceAccountGroupId,
					orderByComparator, true);

			array[1] = cpRuleCommerceAccountGroupRel;

			array[2] = getByCommerceAccountGroupId_PrevAndNext(session,
					cpRuleCommerceAccountGroupRel, commerceAccountGroupId,
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

	protected CPRuleCommerceAccountGroupRel getByCommerceAccountGroupId_PrevAndNext(
		Session session,
		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel,
		long commerceAccountGroupId,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator,
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

		query.append(_SQL_SELECT_CPRULECOMMERCEACCOUNTGROUPREL_WHERE);

		query.append(_FINDER_COLUMN_COMMERCEACCOUNTGROUPID_COMMERCEACCOUNTGROUPID_2);

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
			query.append(CPRuleCommerceAccountGroupRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceAccountGroupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cpRuleCommerceAccountGroupRel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CPRuleCommerceAccountGroupRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp rule commerce account group rels where commerceAccountGroupId = &#63; from the database.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 */
	@Override
	public void removeByCommerceAccountGroupId(long commerceAccountGroupId) {
		for (CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel : findByCommerceAccountGroupId(
				commerceAccountGroupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(cpRuleCommerceAccountGroupRel);
		}
	}

	/**
	 * Returns the number of cp rule commerce account group rels where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @return the number of matching cp rule commerce account group rels
	 */
	@Override
	public int countByCommerceAccountGroupId(long commerceAccountGroupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMMERCEACCOUNTGROUPID;

		Object[] finderArgs = new Object[] { commerceAccountGroupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPRULECOMMERCEACCOUNTGROUPREL_WHERE);

			query.append(_FINDER_COLUMN_COMMERCEACCOUNTGROUPID_COMMERCEACCOUNTGROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceAccountGroupId);

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

	private static final String _FINDER_COLUMN_COMMERCEACCOUNTGROUPID_COMMERCEACCOUNTGROUPID_2 =
		"cpRuleCommerceAccountGroupRel.commerceAccountGroupId = ?";

	public CPRuleCommerceAccountGroupRelPersistenceImpl() {
		setModelClass(CPRuleCommerceAccountGroupRel.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("CPRuleCommerceAccountGroupRelId",
				"CPRuleCAccountGroupRelId");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the cp rule commerce account group rel in the entity cache if it is enabled.
	 *
	 * @param cpRuleCommerceAccountGroupRel the cp rule commerce account group rel
	 */
	@Override
	public void cacheResult(
		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel) {
		entityCache.putResult(CPRuleCommerceAccountGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CPRuleCommerceAccountGroupRelImpl.class,
			cpRuleCommerceAccountGroupRel.getPrimaryKey(),
			cpRuleCommerceAccountGroupRel);

		cpRuleCommerceAccountGroupRel.resetOriginalValues();
	}

	/**
	 * Caches the cp rule commerce account group rels in the entity cache if it is enabled.
	 *
	 * @param cpRuleCommerceAccountGroupRels the cp rule commerce account group rels
	 */
	@Override
	public void cacheResult(
		List<CPRuleCommerceAccountGroupRel> cpRuleCommerceAccountGroupRels) {
		for (CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel : cpRuleCommerceAccountGroupRels) {
			if (entityCache.getResult(
						CPRuleCommerceAccountGroupRelModelImpl.ENTITY_CACHE_ENABLED,
						CPRuleCommerceAccountGroupRelImpl.class,
						cpRuleCommerceAccountGroupRel.getPrimaryKey()) == null) {
				cacheResult(cpRuleCommerceAccountGroupRel);
			}
			else {
				cpRuleCommerceAccountGroupRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cp rule commerce account group rels.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CPRuleCommerceAccountGroupRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cp rule commerce account group rel.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel) {
		entityCache.removeResult(CPRuleCommerceAccountGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CPRuleCommerceAccountGroupRelImpl.class,
			cpRuleCommerceAccountGroupRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<CPRuleCommerceAccountGroupRel> cpRuleCommerceAccountGroupRels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel : cpRuleCommerceAccountGroupRels) {
			entityCache.removeResult(CPRuleCommerceAccountGroupRelModelImpl.ENTITY_CACHE_ENABLED,
				CPRuleCommerceAccountGroupRelImpl.class,
				cpRuleCommerceAccountGroupRel.getPrimaryKey());
		}
	}

	/**
	 * Creates a new cp rule commerce account group rel with the primary key. Does not add the cp rule commerce account group rel to the database.
	 *
	 * @param CPRuleCommerceAccountGroupRelId the primary key for the new cp rule commerce account group rel
	 * @return the new cp rule commerce account group rel
	 */
	@Override
	public CPRuleCommerceAccountGroupRel create(
		long CPRuleCommerceAccountGroupRelId) {
		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel = new CPRuleCommerceAccountGroupRelImpl();

		cpRuleCommerceAccountGroupRel.setNew(true);
		cpRuleCommerceAccountGroupRel.setPrimaryKey(CPRuleCommerceAccountGroupRelId);

		cpRuleCommerceAccountGroupRel.setCompanyId(companyProvider.getCompanyId());

		return cpRuleCommerceAccountGroupRel;
	}

	/**
	 * Removes the cp rule commerce account group rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPRuleCommerceAccountGroupRelId the primary key of the cp rule commerce account group rel
	 * @return the cp rule commerce account group rel that was removed
	 * @throws NoSuchCPRuleCommerceAccountGroupRelException if a cp rule commerce account group rel with the primary key could not be found
	 */
	@Override
	public CPRuleCommerceAccountGroupRel remove(
		long CPRuleCommerceAccountGroupRelId)
		throws NoSuchCPRuleCommerceAccountGroupRelException {
		return remove((Serializable)CPRuleCommerceAccountGroupRelId);
	}

	/**
	 * Removes the cp rule commerce account group rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cp rule commerce account group rel
	 * @return the cp rule commerce account group rel that was removed
	 * @throws NoSuchCPRuleCommerceAccountGroupRelException if a cp rule commerce account group rel with the primary key could not be found
	 */
	@Override
	public CPRuleCommerceAccountGroupRel remove(Serializable primaryKey)
		throws NoSuchCPRuleCommerceAccountGroupRelException {
		Session session = null;

		try {
			session = openSession();

			CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel = (CPRuleCommerceAccountGroupRel)session.get(CPRuleCommerceAccountGroupRelImpl.class,
					primaryKey);

			if (cpRuleCommerceAccountGroupRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCPRuleCommerceAccountGroupRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(cpRuleCommerceAccountGroupRel);
		}
		catch (NoSuchCPRuleCommerceAccountGroupRelException nsee) {
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
	protected CPRuleCommerceAccountGroupRel removeImpl(
		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cpRuleCommerceAccountGroupRel)) {
				cpRuleCommerceAccountGroupRel = (CPRuleCommerceAccountGroupRel)session.get(CPRuleCommerceAccountGroupRelImpl.class,
						cpRuleCommerceAccountGroupRel.getPrimaryKeyObj());
			}

			if (cpRuleCommerceAccountGroupRel != null) {
				session.delete(cpRuleCommerceAccountGroupRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cpRuleCommerceAccountGroupRel != null) {
			clearCache(cpRuleCommerceAccountGroupRel);
		}

		return cpRuleCommerceAccountGroupRel;
	}

	@Override
	public CPRuleCommerceAccountGroupRel updateImpl(
		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel) {
		boolean isNew = cpRuleCommerceAccountGroupRel.isNew();

		if (!(cpRuleCommerceAccountGroupRel instanceof CPRuleCommerceAccountGroupRelModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(cpRuleCommerceAccountGroupRel.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(cpRuleCommerceAccountGroupRel);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in cpRuleCommerceAccountGroupRel proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CPRuleCommerceAccountGroupRel implementation " +
				cpRuleCommerceAccountGroupRel.getClass());
		}

		CPRuleCommerceAccountGroupRelModelImpl cpRuleCommerceAccountGroupRelModelImpl =
			(CPRuleCommerceAccountGroupRelModelImpl)cpRuleCommerceAccountGroupRel;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (cpRuleCommerceAccountGroupRel.getCreateDate() == null)) {
			if (serviceContext == null) {
				cpRuleCommerceAccountGroupRel.setCreateDate(now);
			}
			else {
				cpRuleCommerceAccountGroupRel.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!cpRuleCommerceAccountGroupRelModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				cpRuleCommerceAccountGroupRel.setModifiedDate(now);
			}
			else {
				cpRuleCommerceAccountGroupRel.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (cpRuleCommerceAccountGroupRel.isNew()) {
				session.save(cpRuleCommerceAccountGroupRel);

				cpRuleCommerceAccountGroupRel.setNew(false);
			}
			else {
				cpRuleCommerceAccountGroupRel = (CPRuleCommerceAccountGroupRel)session.merge(cpRuleCommerceAccountGroupRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CPRuleCommerceAccountGroupRelModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					cpRuleCommerceAccountGroupRelModelImpl.getCPRuleId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CPRULEID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPRULEID,
				args);

			args = new Object[] {
					cpRuleCommerceAccountGroupRelModelImpl.getCommerceAccountGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEACCOUNTGROUPID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEACCOUNTGROUPID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((cpRuleCommerceAccountGroupRelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPRULEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cpRuleCommerceAccountGroupRelModelImpl.getOriginalCPRuleId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CPRULEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPRULEID,
					args);

				args = new Object[] {
						cpRuleCommerceAccountGroupRelModelImpl.getCPRuleId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CPRULEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPRULEID,
					args);
			}

			if ((cpRuleCommerceAccountGroupRelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEACCOUNTGROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cpRuleCommerceAccountGroupRelModelImpl.getOriginalCommerceAccountGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEACCOUNTGROUPID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEACCOUNTGROUPID,
					args);

				args = new Object[] {
						cpRuleCommerceAccountGroupRelModelImpl.getCommerceAccountGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEACCOUNTGROUPID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEACCOUNTGROUPID,
					args);
			}
		}

		entityCache.putResult(CPRuleCommerceAccountGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CPRuleCommerceAccountGroupRelImpl.class,
			cpRuleCommerceAccountGroupRel.getPrimaryKey(),
			cpRuleCommerceAccountGroupRel, false);

		cpRuleCommerceAccountGroupRel.resetOriginalValues();

		return cpRuleCommerceAccountGroupRel;
	}

	/**
	 * Returns the cp rule commerce account group rel with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp rule commerce account group rel
	 * @return the cp rule commerce account group rel
	 * @throws NoSuchCPRuleCommerceAccountGroupRelException if a cp rule commerce account group rel with the primary key could not be found
	 */
	@Override
	public CPRuleCommerceAccountGroupRel findByPrimaryKey(
		Serializable primaryKey)
		throws NoSuchCPRuleCommerceAccountGroupRelException {
		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel = fetchByPrimaryKey(primaryKey);

		if (cpRuleCommerceAccountGroupRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCPRuleCommerceAccountGroupRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return cpRuleCommerceAccountGroupRel;
	}

	/**
	 * Returns the cp rule commerce account group rel with the primary key or throws a {@link NoSuchCPRuleCommerceAccountGroupRelException} if it could not be found.
	 *
	 * @param CPRuleCommerceAccountGroupRelId the primary key of the cp rule commerce account group rel
	 * @return the cp rule commerce account group rel
	 * @throws NoSuchCPRuleCommerceAccountGroupRelException if a cp rule commerce account group rel with the primary key could not be found
	 */
	@Override
	public CPRuleCommerceAccountGroupRel findByPrimaryKey(
		long CPRuleCommerceAccountGroupRelId)
		throws NoSuchCPRuleCommerceAccountGroupRelException {
		return findByPrimaryKey((Serializable)CPRuleCommerceAccountGroupRelId);
	}

	/**
	 * Returns the cp rule commerce account group rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp rule commerce account group rel
	 * @return the cp rule commerce account group rel, or <code>null</code> if a cp rule commerce account group rel with the primary key could not be found
	 */
	@Override
	public CPRuleCommerceAccountGroupRel fetchByPrimaryKey(
		Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CPRuleCommerceAccountGroupRelModelImpl.ENTITY_CACHE_ENABLED,
				CPRuleCommerceAccountGroupRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel = (CPRuleCommerceAccountGroupRel)serializable;

		if (cpRuleCommerceAccountGroupRel == null) {
			Session session = null;

			try {
				session = openSession();

				cpRuleCommerceAccountGroupRel = (CPRuleCommerceAccountGroupRel)session.get(CPRuleCommerceAccountGroupRelImpl.class,
						primaryKey);

				if (cpRuleCommerceAccountGroupRel != null) {
					cacheResult(cpRuleCommerceAccountGroupRel);
				}
				else {
					entityCache.putResult(CPRuleCommerceAccountGroupRelModelImpl.ENTITY_CACHE_ENABLED,
						CPRuleCommerceAccountGroupRelImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CPRuleCommerceAccountGroupRelModelImpl.ENTITY_CACHE_ENABLED,
					CPRuleCommerceAccountGroupRelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cpRuleCommerceAccountGroupRel;
	}

	/**
	 * Returns the cp rule commerce account group rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param CPRuleCommerceAccountGroupRelId the primary key of the cp rule commerce account group rel
	 * @return the cp rule commerce account group rel, or <code>null</code> if a cp rule commerce account group rel with the primary key could not be found
	 */
	@Override
	public CPRuleCommerceAccountGroupRel fetchByPrimaryKey(
		long CPRuleCommerceAccountGroupRelId) {
		return fetchByPrimaryKey((Serializable)CPRuleCommerceAccountGroupRelId);
	}

	@Override
	public Map<Serializable, CPRuleCommerceAccountGroupRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CPRuleCommerceAccountGroupRel> map = new HashMap<Serializable, CPRuleCommerceAccountGroupRel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel = fetchByPrimaryKey(primaryKey);

			if (cpRuleCommerceAccountGroupRel != null) {
				map.put(primaryKey, cpRuleCommerceAccountGroupRel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CPRuleCommerceAccountGroupRelModelImpl.ENTITY_CACHE_ENABLED,
					CPRuleCommerceAccountGroupRelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey,
						(CPRuleCommerceAccountGroupRel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CPRULECOMMERCEACCOUNTGROUPREL_WHERE_PKS_IN);

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

			for (CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel : (List<CPRuleCommerceAccountGroupRel>)q.list()) {
				map.put(cpRuleCommerceAccountGroupRel.getPrimaryKeyObj(),
					cpRuleCommerceAccountGroupRel);

				cacheResult(cpRuleCommerceAccountGroupRel);

				uncachedPrimaryKeys.remove(cpRuleCommerceAccountGroupRel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CPRuleCommerceAccountGroupRelModelImpl.ENTITY_CACHE_ENABLED,
					CPRuleCommerceAccountGroupRelImpl.class, primaryKey,
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
	 * Returns all the cp rule commerce account group rels.
	 *
	 * @return the cp rule commerce account group rels
	 */
	@Override
	public List<CPRuleCommerceAccountGroupRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp rule commerce account group rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleCommerceAccountGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp rule commerce account group rels
	 * @param end the upper bound of the range of cp rule commerce account group rels (not inclusive)
	 * @return the range of cp rule commerce account group rels
	 */
	@Override
	public List<CPRuleCommerceAccountGroupRel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp rule commerce account group rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleCommerceAccountGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp rule commerce account group rels
	 * @param end the upper bound of the range of cp rule commerce account group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cp rule commerce account group rels
	 */
	@Override
	public List<CPRuleCommerceAccountGroupRel> findAll(int start, int end,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp rule commerce account group rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleCommerceAccountGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp rule commerce account group rels
	 * @param end the upper bound of the range of cp rule commerce account group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cp rule commerce account group rels
	 */
	@Override
	public List<CPRuleCommerceAccountGroupRel> findAll(int start, int end,
		OrderByComparator<CPRuleCommerceAccountGroupRel> orderByComparator,
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

		List<CPRuleCommerceAccountGroupRel> list = null;

		if (retrieveFromCache) {
			list = (List<CPRuleCommerceAccountGroupRel>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CPRULECOMMERCEACCOUNTGROUPREL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CPRULECOMMERCEACCOUNTGROUPREL;

				if (pagination) {
					sql = sql.concat(CPRuleCommerceAccountGroupRelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CPRuleCommerceAccountGroupRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPRuleCommerceAccountGroupRel>)QueryUtil.list(q,
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
	 * Removes all the cp rule commerce account group rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel : findAll()) {
			remove(cpRuleCommerceAccountGroupRel);
		}
	}

	/**
	 * Returns the number of cp rule commerce account group rels.
	 *
	 * @return the number of cp rule commerce account group rels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CPRULECOMMERCEACCOUNTGROUPREL);

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
		return CPRuleCommerceAccountGroupRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cp rule commerce account group rel persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CPRuleCommerceAccountGroupRelImpl.class.getName());
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
	private static final String _SQL_SELECT_CPRULECOMMERCEACCOUNTGROUPREL = "SELECT cpRuleCommerceAccountGroupRel FROM CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel";
	private static final String _SQL_SELECT_CPRULECOMMERCEACCOUNTGROUPREL_WHERE_PKS_IN =
		"SELECT cpRuleCommerceAccountGroupRel FROM CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel WHERE CPRuleCAccountGroupRelId IN (";
	private static final String _SQL_SELECT_CPRULECOMMERCEACCOUNTGROUPREL_WHERE = "SELECT cpRuleCommerceAccountGroupRel FROM CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel WHERE ";
	private static final String _SQL_COUNT_CPRULECOMMERCEACCOUNTGROUPREL = "SELECT COUNT(cpRuleCommerceAccountGroupRel) FROM CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel";
	private static final String _SQL_COUNT_CPRULECOMMERCEACCOUNTGROUPREL_WHERE = "SELECT COUNT(cpRuleCommerceAccountGroupRel) FROM CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "cpRuleCommerceAccountGroupRel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CPRuleCommerceAccountGroupRel exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CPRuleCommerceAccountGroupRel exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CPRuleCommerceAccountGroupRelPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"CPRuleCommerceAccountGroupRelId"
			});
}