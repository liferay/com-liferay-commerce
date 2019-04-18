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

import com.liferay.commerce.product.exception.NoSuchChannelFilterException;
import com.liferay.commerce.product.model.CommerceChannelFilter;
import com.liferay.commerce.product.model.impl.CommerceChannelFilterImpl;
import com.liferay.commerce.product.model.impl.CommerceChannelFilterModelImpl;
import com.liferay.commerce.product.service.persistence.CommerceChannelFilterPersistence;

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
 * The persistence implementation for the commerce channel filter service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CommerceChannelFilterPersistence
 * @see com.liferay.commerce.product.service.persistence.CommerceChannelFilterUtil
 * @generated
 */
@ProviderType
public class CommerceChannelFilterPersistenceImpl extends BasePersistenceImpl<CommerceChannelFilter>
	implements CommerceChannelFilterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommerceChannelFilterUtil} to access the commerce channel filter persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommerceChannelFilterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommerceChannelFilterModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelFilterModelImpl.FINDER_CACHE_ENABLED,
			CommerceChannelFilterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommerceChannelFilterModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelFilterModelImpl.FINDER_CACHE_ENABLED,
			CommerceChannelFilterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommerceChannelFilterModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelFilterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMMERCECHANNELID =
		new FinderPath(CommerceChannelFilterModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelFilterModelImpl.FINDER_CACHE_ENABLED,
			CommerceChannelFilterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCommerceChannelId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCECHANNELID =
		new FinderPath(CommerceChannelFilterModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelFilterModelImpl.FINDER_CACHE_ENABLED,
			CommerceChannelFilterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCommerceChannelId", new String[] { Long.class.getName() },
			CommerceChannelFilterModelImpl.COMMERCECHANNELID_COLUMN_BITMASK |
			CommerceChannelFilterModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMMERCECHANNELID = new FinderPath(CommerceChannelFilterModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelFilterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceChannelId", new String[] { Long.class.getName() });

	/**
	 * Returns all the commerce channel filters where commerceChannelId = &#63;.
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @return the matching commerce channel filters
	 */
	@Override
	public List<CommerceChannelFilter> findByCommerceChannelId(
		long commerceChannelId) {
		return findByCommerceChannelId(commerceChannelId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce channel filters where commerceChannelId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @param start the lower bound of the range of commerce channel filters
	 * @param end the upper bound of the range of commerce channel filters (not inclusive)
	 * @return the range of matching commerce channel filters
	 */
	@Override
	public List<CommerceChannelFilter> findByCommerceChannelId(
		long commerceChannelId, int start, int end) {
		return findByCommerceChannelId(commerceChannelId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce channel filters where commerceChannelId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @param start the lower bound of the range of commerce channel filters
	 * @param end the upper bound of the range of commerce channel filters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce channel filters
	 */
	@Override
	public List<CommerceChannelFilter> findByCommerceChannelId(
		long commerceChannelId, int start, int end,
		OrderByComparator<CommerceChannelFilter> orderByComparator) {
		return findByCommerceChannelId(commerceChannelId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce channel filters where commerceChannelId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @param start the lower bound of the range of commerce channel filters
	 * @param end the upper bound of the range of commerce channel filters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce channel filters
	 */
	@Override
	public List<CommerceChannelFilter> findByCommerceChannelId(
		long commerceChannelId, int start, int end,
		OrderByComparator<CommerceChannelFilter> orderByComparator,
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

		List<CommerceChannelFilter> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceChannelFilter>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceChannelFilter commerceChannelFilter : list) {
					if ((commerceChannelId != commerceChannelFilter.getCommerceChannelId())) {
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

			query.append(_SQL_SELECT_COMMERCECHANNELFILTER_WHERE);

			query.append(_FINDER_COLUMN_COMMERCECHANNELID_COMMERCECHANNELID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceChannelFilterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceChannelId);

				if (!pagination) {
					list = (List<CommerceChannelFilter>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceChannelFilter>)QueryUtil.list(q,
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
	 * Returns the first commerce channel filter in the ordered set where commerceChannelId = &#63;.
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce channel filter
	 * @throws NoSuchChannelFilterException if a matching commerce channel filter could not be found
	 */
	@Override
	public CommerceChannelFilter findByCommerceChannelId_First(
		long commerceChannelId,
		OrderByComparator<CommerceChannelFilter> orderByComparator)
		throws NoSuchChannelFilterException {
		CommerceChannelFilter commerceChannelFilter = fetchByCommerceChannelId_First(commerceChannelId,
				orderByComparator);

		if (commerceChannelFilter != null) {
			return commerceChannelFilter;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceChannelId=");
		msg.append(commerceChannelId);

		msg.append("}");

		throw new NoSuchChannelFilterException(msg.toString());
	}

	/**
	 * Returns the first commerce channel filter in the ordered set where commerceChannelId = &#63;.
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce channel filter, or <code>null</code> if a matching commerce channel filter could not be found
	 */
	@Override
	public CommerceChannelFilter fetchByCommerceChannelId_First(
		long commerceChannelId,
		OrderByComparator<CommerceChannelFilter> orderByComparator) {
		List<CommerceChannelFilter> list = findByCommerceChannelId(commerceChannelId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce channel filter in the ordered set where commerceChannelId = &#63;.
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce channel filter
	 * @throws NoSuchChannelFilterException if a matching commerce channel filter could not be found
	 */
	@Override
	public CommerceChannelFilter findByCommerceChannelId_Last(
		long commerceChannelId,
		OrderByComparator<CommerceChannelFilter> orderByComparator)
		throws NoSuchChannelFilterException {
		CommerceChannelFilter commerceChannelFilter = fetchByCommerceChannelId_Last(commerceChannelId,
				orderByComparator);

		if (commerceChannelFilter != null) {
			return commerceChannelFilter;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceChannelId=");
		msg.append(commerceChannelId);

		msg.append("}");

		throw new NoSuchChannelFilterException(msg.toString());
	}

	/**
	 * Returns the last commerce channel filter in the ordered set where commerceChannelId = &#63;.
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce channel filter, or <code>null</code> if a matching commerce channel filter could not be found
	 */
	@Override
	public CommerceChannelFilter fetchByCommerceChannelId_Last(
		long commerceChannelId,
		OrderByComparator<CommerceChannelFilter> orderByComparator) {
		int count = countByCommerceChannelId(commerceChannelId);

		if (count == 0) {
			return null;
		}

		List<CommerceChannelFilter> list = findByCommerceChannelId(commerceChannelId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce channel filters before and after the current commerce channel filter in the ordered set where commerceChannelId = &#63;.
	 *
	 * @param commerceChannelFilterId the primary key of the current commerce channel filter
	 * @param commerceChannelId the commerce channel ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce channel filter
	 * @throws NoSuchChannelFilterException if a commerce channel filter with the primary key could not be found
	 */
	@Override
	public CommerceChannelFilter[] findByCommerceChannelId_PrevAndNext(
		long commerceChannelFilterId, long commerceChannelId,
		OrderByComparator<CommerceChannelFilter> orderByComparator)
		throws NoSuchChannelFilterException {
		CommerceChannelFilter commerceChannelFilter = findByPrimaryKey(commerceChannelFilterId);

		Session session = null;

		try {
			session = openSession();

			CommerceChannelFilter[] array = new CommerceChannelFilterImpl[3];

			array[0] = getByCommerceChannelId_PrevAndNext(session,
					commerceChannelFilter, commerceChannelId,
					orderByComparator, true);

			array[1] = commerceChannelFilter;

			array[2] = getByCommerceChannelId_PrevAndNext(session,
					commerceChannelFilter, commerceChannelId,
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

	protected CommerceChannelFilter getByCommerceChannelId_PrevAndNext(
		Session session, CommerceChannelFilter commerceChannelFilter,
		long commerceChannelId,
		OrderByComparator<CommerceChannelFilter> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCECHANNELFILTER_WHERE);

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
			query.append(CommerceChannelFilterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceChannelId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceChannelFilter);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceChannelFilter> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce channel filters where commerceChannelId = &#63; from the database.
	 *
	 * @param commerceChannelId the commerce channel ID
	 */
	@Override
	public void removeByCommerceChannelId(long commerceChannelId) {
		for (CommerceChannelFilter commerceChannelFilter : findByCommerceChannelId(
				commerceChannelId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(commerceChannelFilter);
		}
	}

	/**
	 * Returns the number of commerce channel filters where commerceChannelId = &#63;.
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @return the number of matching commerce channel filters
	 */
	@Override
	public int countByCommerceChannelId(long commerceChannelId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMMERCECHANNELID;

		Object[] finderArgs = new Object[] { commerceChannelId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCECHANNELFILTER_WHERE);

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
		"commerceChannelFilter.commerceChannelId = ?";

	public CommerceChannelFilterPersistenceImpl() {
		setModelClass(CommerceChannelFilter.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("type", "type_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the commerce channel filter in the entity cache if it is enabled.
	 *
	 * @param commerceChannelFilter the commerce channel filter
	 */
	@Override
	public void cacheResult(CommerceChannelFilter commerceChannelFilter) {
		entityCache.putResult(CommerceChannelFilterModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelFilterImpl.class,
			commerceChannelFilter.getPrimaryKey(), commerceChannelFilter);

		commerceChannelFilter.resetOriginalValues();
	}

	/**
	 * Caches the commerce channel filters in the entity cache if it is enabled.
	 *
	 * @param commerceChannelFilters the commerce channel filters
	 */
	@Override
	public void cacheResult(List<CommerceChannelFilter> commerceChannelFilters) {
		for (CommerceChannelFilter commerceChannelFilter : commerceChannelFilters) {
			if (entityCache.getResult(
						CommerceChannelFilterModelImpl.ENTITY_CACHE_ENABLED,
						CommerceChannelFilterImpl.class,
						commerceChannelFilter.getPrimaryKey()) == null) {
				cacheResult(commerceChannelFilter);
			}
			else {
				commerceChannelFilter.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce channel filters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceChannelFilterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce channel filter.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceChannelFilter commerceChannelFilter) {
		entityCache.removeResult(CommerceChannelFilterModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelFilterImpl.class,
			commerceChannelFilter.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CommerceChannelFilter> commerceChannelFilters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceChannelFilter commerceChannelFilter : commerceChannelFilters) {
			entityCache.removeResult(CommerceChannelFilterModelImpl.ENTITY_CACHE_ENABLED,
				CommerceChannelFilterImpl.class,
				commerceChannelFilter.getPrimaryKey());
		}
	}

	/**
	 * Creates a new commerce channel filter with the primary key. Does not add the commerce channel filter to the database.
	 *
	 * @param commerceChannelFilterId the primary key for the new commerce channel filter
	 * @return the new commerce channel filter
	 */
	@Override
	public CommerceChannelFilter create(long commerceChannelFilterId) {
		CommerceChannelFilter commerceChannelFilter = new CommerceChannelFilterImpl();

		commerceChannelFilter.setNew(true);
		commerceChannelFilter.setPrimaryKey(commerceChannelFilterId);

		commerceChannelFilter.setCompanyId(companyProvider.getCompanyId());

		return commerceChannelFilter;
	}

	/**
	 * Removes the commerce channel filter with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceChannelFilterId the primary key of the commerce channel filter
	 * @return the commerce channel filter that was removed
	 * @throws NoSuchChannelFilterException if a commerce channel filter with the primary key could not be found
	 */
	@Override
	public CommerceChannelFilter remove(long commerceChannelFilterId)
		throws NoSuchChannelFilterException {
		return remove((Serializable)commerceChannelFilterId);
	}

	/**
	 * Removes the commerce channel filter with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce channel filter
	 * @return the commerce channel filter that was removed
	 * @throws NoSuchChannelFilterException if a commerce channel filter with the primary key could not be found
	 */
	@Override
	public CommerceChannelFilter remove(Serializable primaryKey)
		throws NoSuchChannelFilterException {
		Session session = null;

		try {
			session = openSession();

			CommerceChannelFilter commerceChannelFilter = (CommerceChannelFilter)session.get(CommerceChannelFilterImpl.class,
					primaryKey);

			if (commerceChannelFilter == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChannelFilterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commerceChannelFilter);
		}
		catch (NoSuchChannelFilterException nsee) {
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
	protected CommerceChannelFilter removeImpl(
		CommerceChannelFilter commerceChannelFilter) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceChannelFilter)) {
				commerceChannelFilter = (CommerceChannelFilter)session.get(CommerceChannelFilterImpl.class,
						commerceChannelFilter.getPrimaryKeyObj());
			}

			if (commerceChannelFilter != null) {
				session.delete(commerceChannelFilter);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceChannelFilter != null) {
			clearCache(commerceChannelFilter);
		}

		return commerceChannelFilter;
	}

	@Override
	public CommerceChannelFilter updateImpl(
		CommerceChannelFilter commerceChannelFilter) {
		boolean isNew = commerceChannelFilter.isNew();

		if (!(commerceChannelFilter instanceof CommerceChannelFilterModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceChannelFilter.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(commerceChannelFilter);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceChannelFilter proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceChannelFilter implementation " +
				commerceChannelFilter.getClass());
		}

		CommerceChannelFilterModelImpl commerceChannelFilterModelImpl = (CommerceChannelFilterModelImpl)commerceChannelFilter;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceChannelFilter.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceChannelFilter.setCreateDate(now);
			}
			else {
				commerceChannelFilter.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!commerceChannelFilterModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceChannelFilter.setModifiedDate(now);
			}
			else {
				commerceChannelFilter.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceChannelFilter.isNew()) {
				session.save(commerceChannelFilter);

				commerceChannelFilter.setNew(false);
			}
			else {
				commerceChannelFilter = (CommerceChannelFilter)session.merge(commerceChannelFilter);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceChannelFilterModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					commerceChannelFilterModelImpl.getCommerceChannelId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCECHANNELID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCECHANNELID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((commerceChannelFilterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCECHANNELID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceChannelFilterModelImpl.getOriginalCommerceChannelId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCECHANNELID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCECHANNELID,
					args);

				args = new Object[] {
						commerceChannelFilterModelImpl.getCommerceChannelId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCECHANNELID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCECHANNELID,
					args);
			}
		}

		entityCache.putResult(CommerceChannelFilterModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelFilterImpl.class,
			commerceChannelFilter.getPrimaryKey(), commerceChannelFilter, false);

		commerceChannelFilter.resetOriginalValues();

		return commerceChannelFilter;
	}

	/**
	 * Returns the commerce channel filter with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce channel filter
	 * @return the commerce channel filter
	 * @throws NoSuchChannelFilterException if a commerce channel filter with the primary key could not be found
	 */
	@Override
	public CommerceChannelFilter findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChannelFilterException {
		CommerceChannelFilter commerceChannelFilter = fetchByPrimaryKey(primaryKey);

		if (commerceChannelFilter == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChannelFilterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commerceChannelFilter;
	}

	/**
	 * Returns the commerce channel filter with the primary key or throws a {@link NoSuchChannelFilterException} if it could not be found.
	 *
	 * @param commerceChannelFilterId the primary key of the commerce channel filter
	 * @return the commerce channel filter
	 * @throws NoSuchChannelFilterException if a commerce channel filter with the primary key could not be found
	 */
	@Override
	public CommerceChannelFilter findByPrimaryKey(long commerceChannelFilterId)
		throws NoSuchChannelFilterException {
		return findByPrimaryKey((Serializable)commerceChannelFilterId);
	}

	/**
	 * Returns the commerce channel filter with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce channel filter
	 * @return the commerce channel filter, or <code>null</code> if a commerce channel filter with the primary key could not be found
	 */
	@Override
	public CommerceChannelFilter fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CommerceChannelFilterModelImpl.ENTITY_CACHE_ENABLED,
				CommerceChannelFilterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceChannelFilter commerceChannelFilter = (CommerceChannelFilter)serializable;

		if (commerceChannelFilter == null) {
			Session session = null;

			try {
				session = openSession();

				commerceChannelFilter = (CommerceChannelFilter)session.get(CommerceChannelFilterImpl.class,
						primaryKey);

				if (commerceChannelFilter != null) {
					cacheResult(commerceChannelFilter);
				}
				else {
					entityCache.putResult(CommerceChannelFilterModelImpl.ENTITY_CACHE_ENABLED,
						CommerceChannelFilterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommerceChannelFilterModelImpl.ENTITY_CACHE_ENABLED,
					CommerceChannelFilterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceChannelFilter;
	}

	/**
	 * Returns the commerce channel filter with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceChannelFilterId the primary key of the commerce channel filter
	 * @return the commerce channel filter, or <code>null</code> if a commerce channel filter with the primary key could not be found
	 */
	@Override
	public CommerceChannelFilter fetchByPrimaryKey(long commerceChannelFilterId) {
		return fetchByPrimaryKey((Serializable)commerceChannelFilterId);
	}

	@Override
	public Map<Serializable, CommerceChannelFilter> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceChannelFilter> map = new HashMap<Serializable, CommerceChannelFilter>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceChannelFilter commerceChannelFilter = fetchByPrimaryKey(primaryKey);

			if (commerceChannelFilter != null) {
				map.put(primaryKey, commerceChannelFilter);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CommerceChannelFilterModelImpl.ENTITY_CACHE_ENABLED,
					CommerceChannelFilterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceChannelFilter)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMMERCECHANNELFILTER_WHERE_PKS_IN);

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

			for (CommerceChannelFilter commerceChannelFilter : (List<CommerceChannelFilter>)q.list()) {
				map.put(commerceChannelFilter.getPrimaryKeyObj(),
					commerceChannelFilter);

				cacheResult(commerceChannelFilter);

				uncachedPrimaryKeys.remove(commerceChannelFilter.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CommerceChannelFilterModelImpl.ENTITY_CACHE_ENABLED,
					CommerceChannelFilterImpl.class, primaryKey, nullModel);
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
	 * Returns all the commerce channel filters.
	 *
	 * @return the commerce channel filters
	 */
	@Override
	public List<CommerceChannelFilter> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce channel filters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce channel filters
	 * @param end the upper bound of the range of commerce channel filters (not inclusive)
	 * @return the range of commerce channel filters
	 */
	@Override
	public List<CommerceChannelFilter> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce channel filters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce channel filters
	 * @param end the upper bound of the range of commerce channel filters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce channel filters
	 */
	@Override
	public List<CommerceChannelFilter> findAll(int start, int end,
		OrderByComparator<CommerceChannelFilter> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce channel filters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce channel filters
	 * @param end the upper bound of the range of commerce channel filters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce channel filters
	 */
	@Override
	public List<CommerceChannelFilter> findAll(int start, int end,
		OrderByComparator<CommerceChannelFilter> orderByComparator,
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

		List<CommerceChannelFilter> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceChannelFilter>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCECHANNELFILTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCECHANNELFILTER;

				if (pagination) {
					sql = sql.concat(CommerceChannelFilterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceChannelFilter>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceChannelFilter>)QueryUtil.list(q,
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
	 * Removes all the commerce channel filters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceChannelFilter commerceChannelFilter : findAll()) {
			remove(commerceChannelFilter);
		}
	}

	/**
	 * Returns the number of commerce channel filters.
	 *
	 * @return the number of commerce channel filters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCECHANNELFILTER);

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
		return CommerceChannelFilterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce channel filter persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CommerceChannelFilterImpl.class.getName());
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
	private static final String _SQL_SELECT_COMMERCECHANNELFILTER = "SELECT commerceChannelFilter FROM CommerceChannelFilter commerceChannelFilter";
	private static final String _SQL_SELECT_COMMERCECHANNELFILTER_WHERE_PKS_IN = "SELECT commerceChannelFilter FROM CommerceChannelFilter commerceChannelFilter WHERE commerceChannelFilterId IN (";
	private static final String _SQL_SELECT_COMMERCECHANNELFILTER_WHERE = "SELECT commerceChannelFilter FROM CommerceChannelFilter commerceChannelFilter WHERE ";
	private static final String _SQL_COUNT_COMMERCECHANNELFILTER = "SELECT COUNT(commerceChannelFilter) FROM CommerceChannelFilter commerceChannelFilter";
	private static final String _SQL_COUNT_COMMERCECHANNELFILTER_WHERE = "SELECT COUNT(commerceChannelFilter) FROM CommerceChannelFilter commerceChannelFilter WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceChannelFilter.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommerceChannelFilter exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CommerceChannelFilter exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CommerceChannelFilterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}