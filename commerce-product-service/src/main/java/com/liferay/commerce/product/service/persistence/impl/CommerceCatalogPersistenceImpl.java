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

import com.liferay.commerce.product.exception.NoSuchCatalogException;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.model.impl.CommerceCatalogImpl;
import com.liferay.commerce.product.model.impl.CommerceCatalogModelImpl;
import com.liferay.commerce.product.service.persistence.CommerceCatalogPersistence;

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
 * The persistence implementation for the commerce catalog service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CommerceCatalogPersistence
 * @see com.liferay.commerce.product.service.persistence.CommerceCatalogUtil
 * @generated
 */
@ProviderType
public class CommerceCatalogPersistenceImpl extends BasePersistenceImpl<CommerceCatalog>
	implements CommerceCatalogPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommerceCatalogUtil} to access the commerce catalog persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommerceCatalogImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCatalogModelImpl.FINDER_CACHE_ENABLED,
			CommerceCatalogImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCatalogModelImpl.FINDER_CACHE_ENABLED,
			CommerceCatalogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCatalogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCatalogModelImpl.FINDER_CACHE_ENABLED,
			CommerceCatalogImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCatalogModelImpl.FINDER_CACHE_ENABLED,
			CommerceCatalogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			CommerceCatalogModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceCatalogModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCatalogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the commerce catalogs where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce catalogs
	 */
	@Override
	public List<CommerceCatalog> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the commerce catalogs where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceCatalogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce catalogs
	 * @param end the upper bound of the range of commerce catalogs (not inclusive)
	 * @return the range of matching commerce catalogs
	 */
	@Override
	public List<CommerceCatalog> findByCompanyId(long companyId, int start,
		int end) {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce catalogs where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceCatalogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce catalogs
	 * @param end the upper bound of the range of commerce catalogs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce catalogs
	 */
	@Override
	public List<CommerceCatalog> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<CommerceCatalog> orderByComparator) {
		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce catalogs where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceCatalogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce catalogs
	 * @param end the upper bound of the range of commerce catalogs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce catalogs
	 */
	@Override
	public List<CommerceCatalog> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<CommerceCatalog> orderByComparator,
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

		List<CommerceCatalog> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceCatalog>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceCatalog commerceCatalog : list) {
					if ((companyId != commerceCatalog.getCompanyId())) {
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

			query.append(_SQL_SELECT_COMMERCECATALOG_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceCatalogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<CommerceCatalog>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceCatalog>)QueryUtil.list(q,
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
	 * Returns the first commerce catalog in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce catalog
	 * @throws NoSuchCatalogException if a matching commerce catalog could not be found
	 */
	@Override
	public CommerceCatalog findByCompanyId_First(long companyId,
		OrderByComparator<CommerceCatalog> orderByComparator)
		throws NoSuchCatalogException {
		CommerceCatalog commerceCatalog = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (commerceCatalog != null) {
			return commerceCatalog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCatalogException(msg.toString());
	}

	/**
	 * Returns the first commerce catalog in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce catalog, or <code>null</code> if a matching commerce catalog could not be found
	 */
	@Override
	public CommerceCatalog fetchByCompanyId_First(long companyId,
		OrderByComparator<CommerceCatalog> orderByComparator) {
		List<CommerceCatalog> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce catalog in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce catalog
	 * @throws NoSuchCatalogException if a matching commerce catalog could not be found
	 */
	@Override
	public CommerceCatalog findByCompanyId_Last(long companyId,
		OrderByComparator<CommerceCatalog> orderByComparator)
		throws NoSuchCatalogException {
		CommerceCatalog commerceCatalog = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (commerceCatalog != null) {
			return commerceCatalog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCatalogException(msg.toString());
	}

	/**
	 * Returns the last commerce catalog in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce catalog, or <code>null</code> if a matching commerce catalog could not be found
	 */
	@Override
	public CommerceCatalog fetchByCompanyId_Last(long companyId,
		OrderByComparator<CommerceCatalog> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<CommerceCatalog> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce catalogs before and after the current commerce catalog in the ordered set where companyId = &#63;.
	 *
	 * @param commerceCatalogId the primary key of the current commerce catalog
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce catalog
	 * @throws NoSuchCatalogException if a commerce catalog with the primary key could not be found
	 */
	@Override
	public CommerceCatalog[] findByCompanyId_PrevAndNext(
		long commerceCatalogId, long companyId,
		OrderByComparator<CommerceCatalog> orderByComparator)
		throws NoSuchCatalogException {
		CommerceCatalog commerceCatalog = findByPrimaryKey(commerceCatalogId);

		Session session = null;

		try {
			session = openSession();

			CommerceCatalog[] array = new CommerceCatalogImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, commerceCatalog,
					companyId, orderByComparator, true);

			array[1] = commerceCatalog;

			array[2] = getByCompanyId_PrevAndNext(session, commerceCatalog,
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

	protected CommerceCatalog getByCompanyId_PrevAndNext(Session session,
		CommerceCatalog commerceCatalog, long companyId,
		OrderByComparator<CommerceCatalog> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COMMERCECATALOG_WHERE);

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
			query.append(CommerceCatalogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceCatalog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceCatalog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce catalogs where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (CommerceCatalog commerceCatalog : findByCompanyId(companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(commerceCatalog);
		}
	}

	/**
	 * Returns the number of commerce catalogs where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce catalogs
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCECATALOG_WHERE);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "commerceCatalog.companyId = ?";

	public CommerceCatalogPersistenceImpl() {
		setModelClass(CommerceCatalog.class);
	}

	/**
	 * Caches the commerce catalog in the entity cache if it is enabled.
	 *
	 * @param commerceCatalog the commerce catalog
	 */
	@Override
	public void cacheResult(CommerceCatalog commerceCatalog) {
		entityCache.putResult(CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCatalogImpl.class, commerceCatalog.getPrimaryKey(),
			commerceCatalog);

		commerceCatalog.resetOriginalValues();
	}

	/**
	 * Caches the commerce catalogs in the entity cache if it is enabled.
	 *
	 * @param commerceCatalogs the commerce catalogs
	 */
	@Override
	public void cacheResult(List<CommerceCatalog> commerceCatalogs) {
		for (CommerceCatalog commerceCatalog : commerceCatalogs) {
			if (entityCache.getResult(
						CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
						CommerceCatalogImpl.class,
						commerceCatalog.getPrimaryKey()) == null) {
				cacheResult(commerceCatalog);
			}
			else {
				commerceCatalog.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce catalogs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceCatalogImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce catalog.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceCatalog commerceCatalog) {
		entityCache.removeResult(CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCatalogImpl.class, commerceCatalog.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CommerceCatalog> commerceCatalogs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceCatalog commerceCatalog : commerceCatalogs) {
			entityCache.removeResult(CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
				CommerceCatalogImpl.class, commerceCatalog.getPrimaryKey());
		}
	}

	/**
	 * Creates a new commerce catalog with the primary key. Does not add the commerce catalog to the database.
	 *
	 * @param commerceCatalogId the primary key for the new commerce catalog
	 * @return the new commerce catalog
	 */
	@Override
	public CommerceCatalog create(long commerceCatalogId) {
		CommerceCatalog commerceCatalog = new CommerceCatalogImpl();

		commerceCatalog.setNew(true);
		commerceCatalog.setPrimaryKey(commerceCatalogId);

		commerceCatalog.setCompanyId(companyProvider.getCompanyId());

		return commerceCatalog;
	}

	/**
	 * Removes the commerce catalog with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceCatalogId the primary key of the commerce catalog
	 * @return the commerce catalog that was removed
	 * @throws NoSuchCatalogException if a commerce catalog with the primary key could not be found
	 */
	@Override
	public CommerceCatalog remove(long commerceCatalogId)
		throws NoSuchCatalogException {
		return remove((Serializable)commerceCatalogId);
	}

	/**
	 * Removes the commerce catalog with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce catalog
	 * @return the commerce catalog that was removed
	 * @throws NoSuchCatalogException if a commerce catalog with the primary key could not be found
	 */
	@Override
	public CommerceCatalog remove(Serializable primaryKey)
		throws NoSuchCatalogException {
		Session session = null;

		try {
			session = openSession();

			CommerceCatalog commerceCatalog = (CommerceCatalog)session.get(CommerceCatalogImpl.class,
					primaryKey);

			if (commerceCatalog == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCatalogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commerceCatalog);
		}
		catch (NoSuchCatalogException nsee) {
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
	protected CommerceCatalog removeImpl(CommerceCatalog commerceCatalog) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceCatalog)) {
				commerceCatalog = (CommerceCatalog)session.get(CommerceCatalogImpl.class,
						commerceCatalog.getPrimaryKeyObj());
			}

			if (commerceCatalog != null) {
				session.delete(commerceCatalog);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceCatalog != null) {
			clearCache(commerceCatalog);
		}

		return commerceCatalog;
	}

	@Override
	public CommerceCatalog updateImpl(CommerceCatalog commerceCatalog) {
		boolean isNew = commerceCatalog.isNew();

		if (!(commerceCatalog instanceof CommerceCatalogModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceCatalog.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(commerceCatalog);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceCatalog proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceCatalog implementation " +
				commerceCatalog.getClass());
		}

		CommerceCatalogModelImpl commerceCatalogModelImpl = (CommerceCatalogModelImpl)commerceCatalog;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceCatalog.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceCatalog.setCreateDate(now);
			}
			else {
				commerceCatalog.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!commerceCatalogModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceCatalog.setModifiedDate(now);
			}
			else {
				commerceCatalog.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceCatalog.isNew()) {
				session.save(commerceCatalog);

				commerceCatalog.setNew(false);
			}
			else {
				commerceCatalog = (CommerceCatalog)session.merge(commerceCatalog);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceCatalogModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { commerceCatalogModelImpl.getCompanyId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((commerceCatalogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceCatalogModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { commerceCatalogModelImpl.getCompanyId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}
		}

		entityCache.putResult(CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceCatalogImpl.class, commerceCatalog.getPrimaryKey(),
			commerceCatalog, false);

		commerceCatalog.resetOriginalValues();

		return commerceCatalog;
	}

	/**
	 * Returns the commerce catalog with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce catalog
	 * @return the commerce catalog
	 * @throws NoSuchCatalogException if a commerce catalog with the primary key could not be found
	 */
	@Override
	public CommerceCatalog findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCatalogException {
		CommerceCatalog commerceCatalog = fetchByPrimaryKey(primaryKey);

		if (commerceCatalog == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCatalogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commerceCatalog;
	}

	/**
	 * Returns the commerce catalog with the primary key or throws a {@link NoSuchCatalogException} if it could not be found.
	 *
	 * @param commerceCatalogId the primary key of the commerce catalog
	 * @return the commerce catalog
	 * @throws NoSuchCatalogException if a commerce catalog with the primary key could not be found
	 */
	@Override
	public CommerceCatalog findByPrimaryKey(long commerceCatalogId)
		throws NoSuchCatalogException {
		return findByPrimaryKey((Serializable)commerceCatalogId);
	}

	/**
	 * Returns the commerce catalog with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce catalog
	 * @return the commerce catalog, or <code>null</code> if a commerce catalog with the primary key could not be found
	 */
	@Override
	public CommerceCatalog fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
				CommerceCatalogImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceCatalog commerceCatalog = (CommerceCatalog)serializable;

		if (commerceCatalog == null) {
			Session session = null;

			try {
				session = openSession();

				commerceCatalog = (CommerceCatalog)session.get(CommerceCatalogImpl.class,
						primaryKey);

				if (commerceCatalog != null) {
					cacheResult(commerceCatalog);
				}
				else {
					entityCache.putResult(CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
						CommerceCatalogImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
					CommerceCatalogImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceCatalog;
	}

	/**
	 * Returns the commerce catalog with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceCatalogId the primary key of the commerce catalog
	 * @return the commerce catalog, or <code>null</code> if a commerce catalog with the primary key could not be found
	 */
	@Override
	public CommerceCatalog fetchByPrimaryKey(long commerceCatalogId) {
		return fetchByPrimaryKey((Serializable)commerceCatalogId);
	}

	@Override
	public Map<Serializable, CommerceCatalog> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceCatalog> map = new HashMap<Serializable, CommerceCatalog>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceCatalog commerceCatalog = fetchByPrimaryKey(primaryKey);

			if (commerceCatalog != null) {
				map.put(primaryKey, commerceCatalog);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
					CommerceCatalogImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceCatalog)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMMERCECATALOG_WHERE_PKS_IN);

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

			for (CommerceCatalog commerceCatalog : (List<CommerceCatalog>)q.list()) {
				map.put(commerceCatalog.getPrimaryKeyObj(), commerceCatalog);

				cacheResult(commerceCatalog);

				uncachedPrimaryKeys.remove(commerceCatalog.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CommerceCatalogModelImpl.ENTITY_CACHE_ENABLED,
					CommerceCatalogImpl.class, primaryKey, nullModel);
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
	 * Returns all the commerce catalogs.
	 *
	 * @return the commerce catalogs
	 */
	@Override
	public List<CommerceCatalog> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce catalogs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceCatalogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce catalogs
	 * @param end the upper bound of the range of commerce catalogs (not inclusive)
	 * @return the range of commerce catalogs
	 */
	@Override
	public List<CommerceCatalog> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce catalogs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceCatalogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce catalogs
	 * @param end the upper bound of the range of commerce catalogs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce catalogs
	 */
	@Override
	public List<CommerceCatalog> findAll(int start, int end,
		OrderByComparator<CommerceCatalog> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce catalogs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceCatalogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce catalogs
	 * @param end the upper bound of the range of commerce catalogs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce catalogs
	 */
	@Override
	public List<CommerceCatalog> findAll(int start, int end,
		OrderByComparator<CommerceCatalog> orderByComparator,
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

		List<CommerceCatalog> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceCatalog>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCECATALOG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCECATALOG;

				if (pagination) {
					sql = sql.concat(CommerceCatalogModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceCatalog>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceCatalog>)QueryUtil.list(q,
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
	 * Removes all the commerce catalogs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceCatalog commerceCatalog : findAll()) {
			remove(commerceCatalog);
		}
	}

	/**
	 * Returns the number of commerce catalogs.
	 *
	 * @return the number of commerce catalogs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCECATALOG);

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
		return CommerceCatalogModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce catalog persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CommerceCatalogImpl.class.getName());
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
	private static final String _SQL_SELECT_COMMERCECATALOG = "SELECT commerceCatalog FROM CommerceCatalog commerceCatalog";
	private static final String _SQL_SELECT_COMMERCECATALOG_WHERE_PKS_IN = "SELECT commerceCatalog FROM CommerceCatalog commerceCatalog WHERE commerceCatalogId IN (";
	private static final String _SQL_SELECT_COMMERCECATALOG_WHERE = "SELECT commerceCatalog FROM CommerceCatalog commerceCatalog WHERE ";
	private static final String _SQL_COUNT_COMMERCECATALOG = "SELECT COUNT(commerceCatalog) FROM CommerceCatalog commerceCatalog";
	private static final String _SQL_COUNT_COMMERCECATALOG_WHERE = "SELECT COUNT(commerceCatalog) FROM CommerceCatalog commerceCatalog WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceCatalog.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommerceCatalog exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CommerceCatalog exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CommerceCatalogPersistenceImpl.class);
}