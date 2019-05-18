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

import com.liferay.commerce.product.exception.NoSuchCPRuleChannelRelException;
import com.liferay.commerce.product.model.CPRuleChannelRel;
import com.liferay.commerce.product.model.impl.CPRuleChannelRelImpl;
import com.liferay.commerce.product.model.impl.CPRuleChannelRelModelImpl;
import com.liferay.commerce.product.service.persistence.CPRuleChannelRelPersistence;

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
import java.util.Set;

/**
 * The persistence implementation for the cp rule channel rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CPRuleChannelRelPersistence
 * @see com.liferay.commerce.product.service.persistence.CPRuleChannelRelUtil
 * @generated
 */
@ProviderType
public class CPRuleChannelRelPersistenceImpl extends BasePersistenceImpl<CPRuleChannelRel>
	implements CPRuleChannelRelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CPRuleChannelRelUtil} to access the cp rule channel rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CPRuleChannelRelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CPRuleChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CPRuleChannelRelModelImpl.FINDER_CACHE_ENABLED,
			CPRuleChannelRelImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CPRuleChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CPRuleChannelRelModelImpl.FINDER_CACHE_ENABLED,
			CPRuleChannelRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CPRuleChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CPRuleChannelRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CPRULEID = new FinderPath(CPRuleChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CPRuleChannelRelModelImpl.FINDER_CACHE_ENABLED,
			CPRuleChannelRelImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCPRuleId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPRULEID =
		new FinderPath(CPRuleChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CPRuleChannelRelModelImpl.FINDER_CACHE_ENABLED,
			CPRuleChannelRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCPRuleId",
			new String[] { Long.class.getName() },
			CPRuleChannelRelModelImpl.CPRULEID_COLUMN_BITMASK |
			CPRuleChannelRelModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CPRULEID = new FinderPath(CPRuleChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CPRuleChannelRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCPRuleId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the cp rule channel rels where CPRuleId = &#63;.
	 *
	 * @param CPRuleId the cp rule ID
	 * @return the matching cp rule channel rels
	 */
	@Override
	public List<CPRuleChannelRel> findByCPRuleId(long CPRuleId) {
		return findByCPRuleId(CPRuleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the cp rule channel rels where CPRuleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleChannelRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPRuleId the cp rule ID
	 * @param start the lower bound of the range of cp rule channel rels
	 * @param end the upper bound of the range of cp rule channel rels (not inclusive)
	 * @return the range of matching cp rule channel rels
	 */
	@Override
	public List<CPRuleChannelRel> findByCPRuleId(long CPRuleId, int start,
		int end) {
		return findByCPRuleId(CPRuleId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp rule channel rels where CPRuleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleChannelRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPRuleId the cp rule ID
	 * @param start the lower bound of the range of cp rule channel rels
	 * @param end the upper bound of the range of cp rule channel rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp rule channel rels
	 */
	@Override
	public List<CPRuleChannelRel> findByCPRuleId(long CPRuleId, int start,
		int end, OrderByComparator<CPRuleChannelRel> orderByComparator) {
		return findByCPRuleId(CPRuleId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp rule channel rels where CPRuleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleChannelRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param CPRuleId the cp rule ID
	 * @param start the lower bound of the range of cp rule channel rels
	 * @param end the upper bound of the range of cp rule channel rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp rule channel rels
	 */
	@Override
	public List<CPRuleChannelRel> findByCPRuleId(long CPRuleId, int start,
		int end, OrderByComparator<CPRuleChannelRel> orderByComparator,
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

		List<CPRuleChannelRel> list = null;

		if (retrieveFromCache) {
			list = (List<CPRuleChannelRel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPRuleChannelRel cpRuleChannelRel : list) {
					if ((CPRuleId != cpRuleChannelRel.getCPRuleId())) {
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

			query.append(_SQL_SELECT_CPRULECHANNELREL_WHERE);

			query.append(_FINDER_COLUMN_CPRULEID_CPRULEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CPRuleChannelRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CPRuleId);

				if (!pagination) {
					list = (List<CPRuleChannelRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPRuleChannelRel>)QueryUtil.list(q,
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
	 * Returns the first cp rule channel rel in the ordered set where CPRuleId = &#63;.
	 *
	 * @param CPRuleId the cp rule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp rule channel rel
	 * @throws NoSuchCPRuleChannelRelException if a matching cp rule channel rel could not be found
	 */
	@Override
	public CPRuleChannelRel findByCPRuleId_First(long CPRuleId,
		OrderByComparator<CPRuleChannelRel> orderByComparator)
		throws NoSuchCPRuleChannelRelException {
		CPRuleChannelRel cpRuleChannelRel = fetchByCPRuleId_First(CPRuleId,
				orderByComparator);

		if (cpRuleChannelRel != null) {
			return cpRuleChannelRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPRuleId=");
		msg.append(CPRuleId);

		msg.append("}");

		throw new NoSuchCPRuleChannelRelException(msg.toString());
	}

	/**
	 * Returns the first cp rule channel rel in the ordered set where CPRuleId = &#63;.
	 *
	 * @param CPRuleId the cp rule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp rule channel rel, or <code>null</code> if a matching cp rule channel rel could not be found
	 */
	@Override
	public CPRuleChannelRel fetchByCPRuleId_First(long CPRuleId,
		OrderByComparator<CPRuleChannelRel> orderByComparator) {
		List<CPRuleChannelRel> list = findByCPRuleId(CPRuleId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp rule channel rel in the ordered set where CPRuleId = &#63;.
	 *
	 * @param CPRuleId the cp rule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp rule channel rel
	 * @throws NoSuchCPRuleChannelRelException if a matching cp rule channel rel could not be found
	 */
	@Override
	public CPRuleChannelRel findByCPRuleId_Last(long CPRuleId,
		OrderByComparator<CPRuleChannelRel> orderByComparator)
		throws NoSuchCPRuleChannelRelException {
		CPRuleChannelRel cpRuleChannelRel = fetchByCPRuleId_Last(CPRuleId,
				orderByComparator);

		if (cpRuleChannelRel != null) {
			return cpRuleChannelRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CPRuleId=");
		msg.append(CPRuleId);

		msg.append("}");

		throw new NoSuchCPRuleChannelRelException(msg.toString());
	}

	/**
	 * Returns the last cp rule channel rel in the ordered set where CPRuleId = &#63;.
	 *
	 * @param CPRuleId the cp rule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp rule channel rel, or <code>null</code> if a matching cp rule channel rel could not be found
	 */
	@Override
	public CPRuleChannelRel fetchByCPRuleId_Last(long CPRuleId,
		OrderByComparator<CPRuleChannelRel> orderByComparator) {
		int count = countByCPRuleId(CPRuleId);

		if (count == 0) {
			return null;
		}

		List<CPRuleChannelRel> list = findByCPRuleId(CPRuleId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp rule channel rels before and after the current cp rule channel rel in the ordered set where CPRuleId = &#63;.
	 *
	 * @param CPRuleChannelRelId the primary key of the current cp rule channel rel
	 * @param CPRuleId the cp rule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp rule channel rel
	 * @throws NoSuchCPRuleChannelRelException if a cp rule channel rel with the primary key could not be found
	 */
	@Override
	public CPRuleChannelRel[] findByCPRuleId_PrevAndNext(
		long CPRuleChannelRelId, long CPRuleId,
		OrderByComparator<CPRuleChannelRel> orderByComparator)
		throws NoSuchCPRuleChannelRelException {
		CPRuleChannelRel cpRuleChannelRel = findByPrimaryKey(CPRuleChannelRelId);

		Session session = null;

		try {
			session = openSession();

			CPRuleChannelRel[] array = new CPRuleChannelRelImpl[3];

			array[0] = getByCPRuleId_PrevAndNext(session, cpRuleChannelRel,
					CPRuleId, orderByComparator, true);

			array[1] = cpRuleChannelRel;

			array[2] = getByCPRuleId_PrevAndNext(session, cpRuleChannelRel,
					CPRuleId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPRuleChannelRel getByCPRuleId_PrevAndNext(Session session,
		CPRuleChannelRel cpRuleChannelRel, long CPRuleId,
		OrderByComparator<CPRuleChannelRel> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CPRULECHANNELREL_WHERE);

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
			query.append(CPRuleChannelRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(CPRuleId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cpRuleChannelRel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CPRuleChannelRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp rule channel rels where CPRuleId = &#63; from the database.
	 *
	 * @param CPRuleId the cp rule ID
	 */
	@Override
	public void removeByCPRuleId(long CPRuleId) {
		for (CPRuleChannelRel cpRuleChannelRel : findByCPRuleId(CPRuleId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(cpRuleChannelRel);
		}
	}

	/**
	 * Returns the number of cp rule channel rels where CPRuleId = &#63;.
	 *
	 * @param CPRuleId the cp rule ID
	 * @return the number of matching cp rule channel rels
	 */
	@Override
	public int countByCPRuleId(long CPRuleId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CPRULEID;

		Object[] finderArgs = new Object[] { CPRuleId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPRULECHANNELREL_WHERE);

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

	private static final String _FINDER_COLUMN_CPRULEID_CPRULEID_2 = "cpRuleChannelRel.CPRuleId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMMERCECHANNELID =
		new FinderPath(CPRuleChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CPRuleChannelRelModelImpl.FINDER_CACHE_ENABLED,
			CPRuleChannelRelImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCommerceChannelId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCECHANNELID =
		new FinderPath(CPRuleChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CPRuleChannelRelModelImpl.FINDER_CACHE_ENABLED,
			CPRuleChannelRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCommerceChannelId", new String[] { Long.class.getName() },
			CPRuleChannelRelModelImpl.COMMERCECHANNELID_COLUMN_BITMASK |
			CPRuleChannelRelModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMMERCECHANNELID = new FinderPath(CPRuleChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CPRuleChannelRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceChannelId", new String[] { Long.class.getName() });

	/**
	 * Returns all the cp rule channel rels where commerceChannelId = &#63;.
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @return the matching cp rule channel rels
	 */
	@Override
	public List<CPRuleChannelRel> findByCommerceChannelId(
		long commerceChannelId) {
		return findByCommerceChannelId(commerceChannelId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp rule channel rels where commerceChannelId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleChannelRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @param start the lower bound of the range of cp rule channel rels
	 * @param end the upper bound of the range of cp rule channel rels (not inclusive)
	 * @return the range of matching cp rule channel rels
	 */
	@Override
	public List<CPRuleChannelRel> findByCommerceChannelId(
		long commerceChannelId, int start, int end) {
		return findByCommerceChannelId(commerceChannelId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp rule channel rels where commerceChannelId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleChannelRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @param start the lower bound of the range of cp rule channel rels
	 * @param end the upper bound of the range of cp rule channel rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp rule channel rels
	 */
	@Override
	public List<CPRuleChannelRel> findByCommerceChannelId(
		long commerceChannelId, int start, int end,
		OrderByComparator<CPRuleChannelRel> orderByComparator) {
		return findByCommerceChannelId(commerceChannelId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp rule channel rels where commerceChannelId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleChannelRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @param start the lower bound of the range of cp rule channel rels
	 * @param end the upper bound of the range of cp rule channel rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cp rule channel rels
	 */
	@Override
	public List<CPRuleChannelRel> findByCommerceChannelId(
		long commerceChannelId, int start, int end,
		OrderByComparator<CPRuleChannelRel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCECHANNELID;
			finderArgs = new Object[] { commerceChannelId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMMERCECHANNELID;
			finderArgs = new Object[] {
					commerceChannelId,
					
					start, end, orderByComparator
				};
		}

		List<CPRuleChannelRel> list = null;

		if (retrieveFromCache) {
			list = (List<CPRuleChannelRel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPRuleChannelRel cpRuleChannelRel : list) {
					if ((commerceChannelId != cpRuleChannelRel.getCommerceChannelId())) {
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

			query.append(_SQL_SELECT_CPRULECHANNELREL_WHERE);

			query.append(_FINDER_COLUMN_COMMERCECHANNELID_COMMERCECHANNELID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CPRuleChannelRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceChannelId);

				if (!pagination) {
					list = (List<CPRuleChannelRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPRuleChannelRel>)QueryUtil.list(q,
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
	 * Returns the first cp rule channel rel in the ordered set where commerceChannelId = &#63;.
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp rule channel rel
	 * @throws NoSuchCPRuleChannelRelException if a matching cp rule channel rel could not be found
	 */
	@Override
	public CPRuleChannelRel findByCommerceChannelId_First(
		long commerceChannelId,
		OrderByComparator<CPRuleChannelRel> orderByComparator)
		throws NoSuchCPRuleChannelRelException {
		CPRuleChannelRel cpRuleChannelRel = fetchByCommerceChannelId_First(commerceChannelId,
				orderByComparator);

		if (cpRuleChannelRel != null) {
			return cpRuleChannelRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceChannelId=");
		msg.append(commerceChannelId);

		msg.append("}");

		throw new NoSuchCPRuleChannelRelException(msg.toString());
	}

	/**
	 * Returns the first cp rule channel rel in the ordered set where commerceChannelId = &#63;.
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp rule channel rel, or <code>null</code> if a matching cp rule channel rel could not be found
	 */
	@Override
	public CPRuleChannelRel fetchByCommerceChannelId_First(
		long commerceChannelId,
		OrderByComparator<CPRuleChannelRel> orderByComparator) {
		List<CPRuleChannelRel> list = findByCommerceChannelId(commerceChannelId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp rule channel rel in the ordered set where commerceChannelId = &#63;.
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp rule channel rel
	 * @throws NoSuchCPRuleChannelRelException if a matching cp rule channel rel could not be found
	 */
	@Override
	public CPRuleChannelRel findByCommerceChannelId_Last(
		long commerceChannelId,
		OrderByComparator<CPRuleChannelRel> orderByComparator)
		throws NoSuchCPRuleChannelRelException {
		CPRuleChannelRel cpRuleChannelRel = fetchByCommerceChannelId_Last(commerceChannelId,
				orderByComparator);

		if (cpRuleChannelRel != null) {
			return cpRuleChannelRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceChannelId=");
		msg.append(commerceChannelId);

		msg.append("}");

		throw new NoSuchCPRuleChannelRelException(msg.toString());
	}

	/**
	 * Returns the last cp rule channel rel in the ordered set where commerceChannelId = &#63;.
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp rule channel rel, or <code>null</code> if a matching cp rule channel rel could not be found
	 */
	@Override
	public CPRuleChannelRel fetchByCommerceChannelId_Last(
		long commerceChannelId,
		OrderByComparator<CPRuleChannelRel> orderByComparator) {
		int count = countByCommerceChannelId(commerceChannelId);

		if (count == 0) {
			return null;
		}

		List<CPRuleChannelRel> list = findByCommerceChannelId(commerceChannelId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp rule channel rels before and after the current cp rule channel rel in the ordered set where commerceChannelId = &#63;.
	 *
	 * @param CPRuleChannelRelId the primary key of the current cp rule channel rel
	 * @param commerceChannelId the commerce channel ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp rule channel rel
	 * @throws NoSuchCPRuleChannelRelException if a cp rule channel rel with the primary key could not be found
	 */
	@Override
	public CPRuleChannelRel[] findByCommerceChannelId_PrevAndNext(
		long CPRuleChannelRelId, long commerceChannelId,
		OrderByComparator<CPRuleChannelRel> orderByComparator)
		throws NoSuchCPRuleChannelRelException {
		CPRuleChannelRel cpRuleChannelRel = findByPrimaryKey(CPRuleChannelRelId);

		Session session = null;

		try {
			session = openSession();

			CPRuleChannelRel[] array = new CPRuleChannelRelImpl[3];

			array[0] = getByCommerceChannelId_PrevAndNext(session,
					cpRuleChannelRel, commerceChannelId, orderByComparator, true);

			array[1] = cpRuleChannelRel;

			array[2] = getByCommerceChannelId_PrevAndNext(session,
					cpRuleChannelRel, commerceChannelId, orderByComparator,
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

	protected CPRuleChannelRel getByCommerceChannelId_PrevAndNext(
		Session session, CPRuleChannelRel cpRuleChannelRel,
		long commerceChannelId,
		OrderByComparator<CPRuleChannelRel> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CPRULECHANNELREL_WHERE);

		query.append(_FINDER_COLUMN_COMMERCECHANNELID_COMMERCECHANNELID_2);

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
			query.append(CPRuleChannelRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceChannelId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cpRuleChannelRel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CPRuleChannelRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp rule channel rels where commerceChannelId = &#63; from the database.
	 *
	 * @param commerceChannelId the commerce channel ID
	 */
	@Override
	public void removeByCommerceChannelId(long commerceChannelId) {
		for (CPRuleChannelRel cpRuleChannelRel : findByCommerceChannelId(
				commerceChannelId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(cpRuleChannelRel);
		}
	}

	/**
	 * Returns the number of cp rule channel rels where commerceChannelId = &#63;.
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @return the number of matching cp rule channel rels
	 */
	@Override
	public int countByCommerceChannelId(long commerceChannelId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMMERCECHANNELID;

		Object[] finderArgs = new Object[] { commerceChannelId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPRULECHANNELREL_WHERE);

			query.append(_FINDER_COLUMN_COMMERCECHANNELID_COMMERCECHANNELID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceChannelId);

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

	private static final String _FINDER_COLUMN_COMMERCECHANNELID_COMMERCECHANNELID_2 =
		"cpRuleChannelRel.commerceChannelId = ?";

	public CPRuleChannelRelPersistenceImpl() {
		setModelClass(CPRuleChannelRel.class);
	}

	/**
	 * Caches the cp rule channel rel in the entity cache if it is enabled.
	 *
	 * @param cpRuleChannelRel the cp rule channel rel
	 */
	@Override
	public void cacheResult(CPRuleChannelRel cpRuleChannelRel) {
		entityCache.putResult(CPRuleChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CPRuleChannelRelImpl.class, cpRuleChannelRel.getPrimaryKey(),
			cpRuleChannelRel);

		cpRuleChannelRel.resetOriginalValues();
	}

	/**
	 * Caches the cp rule channel rels in the entity cache if it is enabled.
	 *
	 * @param cpRuleChannelRels the cp rule channel rels
	 */
	@Override
	public void cacheResult(List<CPRuleChannelRel> cpRuleChannelRels) {
		for (CPRuleChannelRel cpRuleChannelRel : cpRuleChannelRels) {
			if (entityCache.getResult(
						CPRuleChannelRelModelImpl.ENTITY_CACHE_ENABLED,
						CPRuleChannelRelImpl.class,
						cpRuleChannelRel.getPrimaryKey()) == null) {
				cacheResult(cpRuleChannelRel);
			}
			else {
				cpRuleChannelRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cp rule channel rels.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CPRuleChannelRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cp rule channel rel.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CPRuleChannelRel cpRuleChannelRel) {
		entityCache.removeResult(CPRuleChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CPRuleChannelRelImpl.class, cpRuleChannelRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CPRuleChannelRel> cpRuleChannelRels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CPRuleChannelRel cpRuleChannelRel : cpRuleChannelRels) {
			entityCache.removeResult(CPRuleChannelRelModelImpl.ENTITY_CACHE_ENABLED,
				CPRuleChannelRelImpl.class, cpRuleChannelRel.getPrimaryKey());
		}
	}

	/**
	 * Creates a new cp rule channel rel with the primary key. Does not add the cp rule channel rel to the database.
	 *
	 * @param CPRuleChannelRelId the primary key for the new cp rule channel rel
	 * @return the new cp rule channel rel
	 */
	@Override
	public CPRuleChannelRel create(long CPRuleChannelRelId) {
		CPRuleChannelRel cpRuleChannelRel = new CPRuleChannelRelImpl();

		cpRuleChannelRel.setNew(true);
		cpRuleChannelRel.setPrimaryKey(CPRuleChannelRelId);

		cpRuleChannelRel.setCompanyId(companyProvider.getCompanyId());

		return cpRuleChannelRel;
	}

	/**
	 * Removes the cp rule channel rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPRuleChannelRelId the primary key of the cp rule channel rel
	 * @return the cp rule channel rel that was removed
	 * @throws NoSuchCPRuleChannelRelException if a cp rule channel rel with the primary key could not be found
	 */
	@Override
	public CPRuleChannelRel remove(long CPRuleChannelRelId)
		throws NoSuchCPRuleChannelRelException {
		return remove((Serializable)CPRuleChannelRelId);
	}

	/**
	 * Removes the cp rule channel rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cp rule channel rel
	 * @return the cp rule channel rel that was removed
	 * @throws NoSuchCPRuleChannelRelException if a cp rule channel rel with the primary key could not be found
	 */
	@Override
	public CPRuleChannelRel remove(Serializable primaryKey)
		throws NoSuchCPRuleChannelRelException {
		Session session = null;

		try {
			session = openSession();

			CPRuleChannelRel cpRuleChannelRel = (CPRuleChannelRel)session.get(CPRuleChannelRelImpl.class,
					primaryKey);

			if (cpRuleChannelRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCPRuleChannelRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(cpRuleChannelRel);
		}
		catch (NoSuchCPRuleChannelRelException nsee) {
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
	protected CPRuleChannelRel removeImpl(CPRuleChannelRel cpRuleChannelRel) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cpRuleChannelRel)) {
				cpRuleChannelRel = (CPRuleChannelRel)session.get(CPRuleChannelRelImpl.class,
						cpRuleChannelRel.getPrimaryKeyObj());
			}

			if (cpRuleChannelRel != null) {
				session.delete(cpRuleChannelRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cpRuleChannelRel != null) {
			clearCache(cpRuleChannelRel);
		}

		return cpRuleChannelRel;
	}

	@Override
	public CPRuleChannelRel updateImpl(CPRuleChannelRel cpRuleChannelRel) {
		boolean isNew = cpRuleChannelRel.isNew();

		if (!(cpRuleChannelRel instanceof CPRuleChannelRelModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(cpRuleChannelRel.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(cpRuleChannelRel);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in cpRuleChannelRel proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CPRuleChannelRel implementation " +
				cpRuleChannelRel.getClass());
		}

		CPRuleChannelRelModelImpl cpRuleChannelRelModelImpl = (CPRuleChannelRelModelImpl)cpRuleChannelRel;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (cpRuleChannelRel.getCreateDate() == null)) {
			if (serviceContext == null) {
				cpRuleChannelRel.setCreateDate(now);
			}
			else {
				cpRuleChannelRel.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!cpRuleChannelRelModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				cpRuleChannelRel.setModifiedDate(now);
			}
			else {
				cpRuleChannelRel.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (cpRuleChannelRel.isNew()) {
				session.save(cpRuleChannelRel);

				cpRuleChannelRel.setNew(false);
			}
			else {
				cpRuleChannelRel = (CPRuleChannelRel)session.merge(cpRuleChannelRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CPRuleChannelRelModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { cpRuleChannelRelModelImpl.getCPRuleId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CPRULEID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPRULEID,
				args);

			args = new Object[] { cpRuleChannelRelModelImpl.getCommerceChannelId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCECHANNELID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCECHANNELID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((cpRuleChannelRelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPRULEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cpRuleChannelRelModelImpl.getOriginalCPRuleId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CPRULEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPRULEID,
					args);

				args = new Object[] { cpRuleChannelRelModelImpl.getCPRuleId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CPRULEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPRULEID,
					args);
			}

			if ((cpRuleChannelRelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCECHANNELID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cpRuleChannelRelModelImpl.getOriginalCommerceChannelId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCECHANNELID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCECHANNELID,
					args);

				args = new Object[] {
						cpRuleChannelRelModelImpl.getCommerceChannelId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCECHANNELID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCECHANNELID,
					args);
			}
		}

		entityCache.putResult(CPRuleChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CPRuleChannelRelImpl.class, cpRuleChannelRel.getPrimaryKey(),
			cpRuleChannelRel, false);

		cpRuleChannelRel.resetOriginalValues();

		return cpRuleChannelRel;
	}

	/**
	 * Returns the cp rule channel rel with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp rule channel rel
	 * @return the cp rule channel rel
	 * @throws NoSuchCPRuleChannelRelException if a cp rule channel rel with the primary key could not be found
	 */
	@Override
	public CPRuleChannelRel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCPRuleChannelRelException {
		CPRuleChannelRel cpRuleChannelRel = fetchByPrimaryKey(primaryKey);

		if (cpRuleChannelRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCPRuleChannelRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return cpRuleChannelRel;
	}

	/**
	 * Returns the cp rule channel rel with the primary key or throws a {@link NoSuchCPRuleChannelRelException} if it could not be found.
	 *
	 * @param CPRuleChannelRelId the primary key of the cp rule channel rel
	 * @return the cp rule channel rel
	 * @throws NoSuchCPRuleChannelRelException if a cp rule channel rel with the primary key could not be found
	 */
	@Override
	public CPRuleChannelRel findByPrimaryKey(long CPRuleChannelRelId)
		throws NoSuchCPRuleChannelRelException {
		return findByPrimaryKey((Serializable)CPRuleChannelRelId);
	}

	/**
	 * Returns the cp rule channel rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp rule channel rel
	 * @return the cp rule channel rel, or <code>null</code> if a cp rule channel rel with the primary key could not be found
	 */
	@Override
	public CPRuleChannelRel fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CPRuleChannelRelModelImpl.ENTITY_CACHE_ENABLED,
				CPRuleChannelRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CPRuleChannelRel cpRuleChannelRel = (CPRuleChannelRel)serializable;

		if (cpRuleChannelRel == null) {
			Session session = null;

			try {
				session = openSession();

				cpRuleChannelRel = (CPRuleChannelRel)session.get(CPRuleChannelRelImpl.class,
						primaryKey);

				if (cpRuleChannelRel != null) {
					cacheResult(cpRuleChannelRel);
				}
				else {
					entityCache.putResult(CPRuleChannelRelModelImpl.ENTITY_CACHE_ENABLED,
						CPRuleChannelRelImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CPRuleChannelRelModelImpl.ENTITY_CACHE_ENABLED,
					CPRuleChannelRelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cpRuleChannelRel;
	}

	/**
	 * Returns the cp rule channel rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param CPRuleChannelRelId the primary key of the cp rule channel rel
	 * @return the cp rule channel rel, or <code>null</code> if a cp rule channel rel with the primary key could not be found
	 */
	@Override
	public CPRuleChannelRel fetchByPrimaryKey(long CPRuleChannelRelId) {
		return fetchByPrimaryKey((Serializable)CPRuleChannelRelId);
	}

	@Override
	public Map<Serializable, CPRuleChannelRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CPRuleChannelRel> map = new HashMap<Serializable, CPRuleChannelRel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CPRuleChannelRel cpRuleChannelRel = fetchByPrimaryKey(primaryKey);

			if (cpRuleChannelRel != null) {
				map.put(primaryKey, cpRuleChannelRel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CPRuleChannelRelModelImpl.ENTITY_CACHE_ENABLED,
					CPRuleChannelRelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CPRuleChannelRel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CPRULECHANNELREL_WHERE_PKS_IN);

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

			for (CPRuleChannelRel cpRuleChannelRel : (List<CPRuleChannelRel>)q.list()) {
				map.put(cpRuleChannelRel.getPrimaryKeyObj(), cpRuleChannelRel);

				cacheResult(cpRuleChannelRel);

				uncachedPrimaryKeys.remove(cpRuleChannelRel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CPRuleChannelRelModelImpl.ENTITY_CACHE_ENABLED,
					CPRuleChannelRelImpl.class, primaryKey, nullModel);
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
	 * Returns all the cp rule channel rels.
	 *
	 * @return the cp rule channel rels
	 */
	@Override
	public List<CPRuleChannelRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp rule channel rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleChannelRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp rule channel rels
	 * @param end the upper bound of the range of cp rule channel rels (not inclusive)
	 * @return the range of cp rule channel rels
	 */
	@Override
	public List<CPRuleChannelRel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp rule channel rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleChannelRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp rule channel rels
	 * @param end the upper bound of the range of cp rule channel rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cp rule channel rels
	 */
	@Override
	public List<CPRuleChannelRel> findAll(int start, int end,
		OrderByComparator<CPRuleChannelRel> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp rule channel rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CPRuleChannelRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp rule channel rels
	 * @param end the upper bound of the range of cp rule channel rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cp rule channel rels
	 */
	@Override
	public List<CPRuleChannelRel> findAll(int start, int end,
		OrderByComparator<CPRuleChannelRel> orderByComparator,
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

		List<CPRuleChannelRel> list = null;

		if (retrieveFromCache) {
			list = (List<CPRuleChannelRel>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CPRULECHANNELREL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CPRULECHANNELREL;

				if (pagination) {
					sql = sql.concat(CPRuleChannelRelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CPRuleChannelRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPRuleChannelRel>)QueryUtil.list(q,
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
	 * Removes all the cp rule channel rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CPRuleChannelRel cpRuleChannelRel : findAll()) {
			remove(cpRuleChannelRel);
		}
	}

	/**
	 * Returns the number of cp rule channel rels.
	 *
	 * @return the number of cp rule channel rels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CPRULECHANNELREL);

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
		return CPRuleChannelRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cp rule channel rel persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CPRuleChannelRelImpl.class.getName());
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
	private static final String _SQL_SELECT_CPRULECHANNELREL = "SELECT cpRuleChannelRel FROM CPRuleChannelRel cpRuleChannelRel";
	private static final String _SQL_SELECT_CPRULECHANNELREL_WHERE_PKS_IN = "SELECT cpRuleChannelRel FROM CPRuleChannelRel cpRuleChannelRel WHERE CPRuleChannelRelId IN (";
	private static final String _SQL_SELECT_CPRULECHANNELREL_WHERE = "SELECT cpRuleChannelRel FROM CPRuleChannelRel cpRuleChannelRel WHERE ";
	private static final String _SQL_COUNT_CPRULECHANNELREL = "SELECT COUNT(cpRuleChannelRel) FROM CPRuleChannelRel cpRuleChannelRel";
	private static final String _SQL_COUNT_CPRULECHANNELREL_WHERE = "SELECT COUNT(cpRuleChannelRel) FROM CPRuleChannelRel cpRuleChannelRel WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "cpRuleChannelRel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CPRuleChannelRel exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CPRuleChannelRel exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CPRuleChannelRelPersistenceImpl.class);
}