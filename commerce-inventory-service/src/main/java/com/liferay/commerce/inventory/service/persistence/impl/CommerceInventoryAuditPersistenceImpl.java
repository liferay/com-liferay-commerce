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

package com.liferay.commerce.inventory.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.inventory.exception.NoSuchInventoryAuditException;
import com.liferay.commerce.inventory.model.CommerceInventoryAudit;
import com.liferay.commerce.inventory.model.impl.CommerceInventoryAuditImpl;
import com.liferay.commerce.inventory.model.impl.CommerceInventoryAuditModelImpl;
import com.liferay.commerce.inventory.service.persistence.CommerceInventoryAuditPersistence;

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
import com.liferay.portal.kernel.util.StringUtil;
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
 * The persistence implementation for the commerce inventory audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryAuditPersistence
 * @see com.liferay.commerce.inventory.service.persistence.CommerceInventoryAuditUtil
 * @generated
 */
@ProviderType
public class CommerceInventoryAuditPersistenceImpl extends BasePersistenceImpl<CommerceInventoryAudit>
	implements CommerceInventoryAuditPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommerceInventoryAuditUtil} to access the commerce inventory audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommerceInventoryAuditImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryAuditModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryAuditModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_SKU = new FinderPath(CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryAuditModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryAuditImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchBysku", new String[] { String.class.getName() },
			CommerceInventoryAuditModelImpl.SKU_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SKU = new FinderPath(CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBysku",
			new String[] { String.class.getName() });

	/**
	 * Returns the commerce inventory audit where sku = &#63; or throws a {@link NoSuchInventoryAuditException} if it could not be found.
	 *
	 * @param sku the sku
	 * @return the matching commerce inventory audit
	 * @throws NoSuchInventoryAuditException if a matching commerce inventory audit could not be found
	 */
	@Override
	public CommerceInventoryAudit findBysku(String sku)
		throws NoSuchInventoryAuditException {
		CommerceInventoryAudit commerceInventoryAudit = fetchBysku(sku);

		if (commerceInventoryAudit == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("sku=");
			msg.append(sku);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchInventoryAuditException(msg.toString());
		}

		return commerceInventoryAudit;
	}

	/**
	 * Returns the commerce inventory audit where sku = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param sku the sku
	 * @return the matching commerce inventory audit, or <code>null</code> if a matching commerce inventory audit could not be found
	 */
	@Override
	public CommerceInventoryAudit fetchBysku(String sku) {
		return fetchBysku(sku, true);
	}

	/**
	 * Returns the commerce inventory audit where sku = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param sku the sku
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching commerce inventory audit, or <code>null</code> if a matching commerce inventory audit could not be found
	 */
	@Override
	public CommerceInventoryAudit fetchBysku(String sku,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { sku };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_SKU,
					finderArgs, this);
		}

		if (result instanceof CommerceInventoryAudit) {
			CommerceInventoryAudit commerceInventoryAudit = (CommerceInventoryAudit)result;

			if (!Objects.equals(sku, commerceInventoryAudit.getSku())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_COMMERCEINVENTORYAUDIT_WHERE);

			boolean bindSku = false;

			if (sku == null) {
				query.append(_FINDER_COLUMN_SKU_SKU_1);
			}
			else if (sku.equals("")) {
				query.append(_FINDER_COLUMN_SKU_SKU_3);
			}
			else {
				bindSku = true;

				query.append(_FINDER_COLUMN_SKU_SKU_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSku) {
					qPos.add(sku);
				}

				List<CommerceInventoryAudit> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_SKU, finderArgs,
						list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"CommerceInventoryAuditPersistenceImpl.fetchBysku(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CommerceInventoryAudit commerceInventoryAudit = list.get(0);

					result = commerceInventoryAudit;

					cacheResult(commerceInventoryAudit);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_SKU, finderArgs);

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
			return (CommerceInventoryAudit)result;
		}
	}

	/**
	 * Removes the commerce inventory audit where sku = &#63; from the database.
	 *
	 * @param sku the sku
	 * @return the commerce inventory audit that was removed
	 */
	@Override
	public CommerceInventoryAudit removeBysku(String sku)
		throws NoSuchInventoryAuditException {
		CommerceInventoryAudit commerceInventoryAudit = findBysku(sku);

		return remove(commerceInventoryAudit);
	}

	/**
	 * Returns the number of commerce inventory audits where sku = &#63;.
	 *
	 * @param sku the sku
	 * @return the number of matching commerce inventory audits
	 */
	@Override
	public int countBysku(String sku) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SKU;

		Object[] finderArgs = new Object[] { sku };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEINVENTORYAUDIT_WHERE);

			boolean bindSku = false;

			if (sku == null) {
				query.append(_FINDER_COLUMN_SKU_SKU_1);
			}
			else if (sku.equals("")) {
				query.append(_FINDER_COLUMN_SKU_SKU_3);
			}
			else {
				bindSku = true;

				query.append(_FINDER_COLUMN_SKU_SKU_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSku) {
					qPos.add(sku);
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

	private static final String _FINDER_COLUMN_SKU_SKU_1 = "commerceInventoryAudit.sku IS NULL";
	private static final String _FINDER_COLUMN_SKU_SKU_2 = "commerceInventoryAudit.sku = ?";
	private static final String _FINDER_COLUMN_SKU_SKU_3 = "(commerceInventoryAudit.sku IS NULL OR commerceInventoryAudit.sku = '')";

	public CommerceInventoryAuditPersistenceImpl() {
		setModelClass(CommerceInventoryAudit.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("commerceInventoryAuditId", "CIAuditId");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the commerce inventory audit in the entity cache if it is enabled.
	 *
	 * @param commerceInventoryAudit the commerce inventory audit
	 */
	@Override
	public void cacheResult(CommerceInventoryAudit commerceInventoryAudit) {
		entityCache.putResult(CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryAuditImpl.class,
			commerceInventoryAudit.getPrimaryKey(), commerceInventoryAudit);

		finderCache.putResult(FINDER_PATH_FETCH_BY_SKU,
			new Object[] { commerceInventoryAudit.getSku() },
			commerceInventoryAudit);

		commerceInventoryAudit.resetOriginalValues();
	}

	/**
	 * Caches the commerce inventory audits in the entity cache if it is enabled.
	 *
	 * @param commerceInventoryAudits the commerce inventory audits
	 */
	@Override
	public void cacheResult(
		List<CommerceInventoryAudit> commerceInventoryAudits) {
		for (CommerceInventoryAudit commerceInventoryAudit : commerceInventoryAudits) {
			if (entityCache.getResult(
						CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
						CommerceInventoryAuditImpl.class,
						commerceInventoryAudit.getPrimaryKey()) == null) {
				cacheResult(commerceInventoryAudit);
			}
			else {
				commerceInventoryAudit.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce inventory audits.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceInventoryAuditImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce inventory audit.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceInventoryAudit commerceInventoryAudit) {
		entityCache.removeResult(CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryAuditImpl.class,
			commerceInventoryAudit.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CommerceInventoryAuditModelImpl)commerceInventoryAudit,
			true);
	}

	@Override
	public void clearCache(List<CommerceInventoryAudit> commerceInventoryAudits) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceInventoryAudit commerceInventoryAudit : commerceInventoryAudits) {
			entityCache.removeResult(CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
				CommerceInventoryAuditImpl.class,
				commerceInventoryAudit.getPrimaryKey());

			clearUniqueFindersCache((CommerceInventoryAuditModelImpl)commerceInventoryAudit,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		CommerceInventoryAuditModelImpl commerceInventoryAuditModelImpl) {
		Object[] args = new Object[] { commerceInventoryAuditModelImpl.getSku() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_SKU, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_SKU, args,
			commerceInventoryAuditModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CommerceInventoryAuditModelImpl commerceInventoryAuditModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					commerceInventoryAuditModelImpl.getSku()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SKU, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SKU, args);
		}

		if ((commerceInventoryAuditModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SKU.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					commerceInventoryAuditModelImpl.getOriginalSku()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SKU, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SKU, args);
		}
	}

	/**
	 * Creates a new commerce inventory audit with the primary key. Does not add the commerce inventory audit to the database.
	 *
	 * @param commerceInventoryAuditId the primary key for the new commerce inventory audit
	 * @return the new commerce inventory audit
	 */
	@Override
	public CommerceInventoryAudit create(long commerceInventoryAuditId) {
		CommerceInventoryAudit commerceInventoryAudit = new CommerceInventoryAuditImpl();

		commerceInventoryAudit.setNew(true);
		commerceInventoryAudit.setPrimaryKey(commerceInventoryAuditId);

		commerceInventoryAudit.setCompanyId(companyProvider.getCompanyId());

		return commerceInventoryAudit;
	}

	/**
	 * Removes the commerce inventory audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceInventoryAuditId the primary key of the commerce inventory audit
	 * @return the commerce inventory audit that was removed
	 * @throws NoSuchInventoryAuditException if a commerce inventory audit with the primary key could not be found
	 */
	@Override
	public CommerceInventoryAudit remove(long commerceInventoryAuditId)
		throws NoSuchInventoryAuditException {
		return remove((Serializable)commerceInventoryAuditId);
	}

	/**
	 * Removes the commerce inventory audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce inventory audit
	 * @return the commerce inventory audit that was removed
	 * @throws NoSuchInventoryAuditException if a commerce inventory audit with the primary key could not be found
	 */
	@Override
	public CommerceInventoryAudit remove(Serializable primaryKey)
		throws NoSuchInventoryAuditException {
		Session session = null;

		try {
			session = openSession();

			CommerceInventoryAudit commerceInventoryAudit = (CommerceInventoryAudit)session.get(CommerceInventoryAuditImpl.class,
					primaryKey);

			if (commerceInventoryAudit == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchInventoryAuditException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commerceInventoryAudit);
		}
		catch (NoSuchInventoryAuditException nsee) {
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
	protected CommerceInventoryAudit removeImpl(
		CommerceInventoryAudit commerceInventoryAudit) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceInventoryAudit)) {
				commerceInventoryAudit = (CommerceInventoryAudit)session.get(CommerceInventoryAuditImpl.class,
						commerceInventoryAudit.getPrimaryKeyObj());
			}

			if (commerceInventoryAudit != null) {
				session.delete(commerceInventoryAudit);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceInventoryAudit != null) {
			clearCache(commerceInventoryAudit);
		}

		return commerceInventoryAudit;
	}

	@Override
	public CommerceInventoryAudit updateImpl(
		CommerceInventoryAudit commerceInventoryAudit) {
		boolean isNew = commerceInventoryAudit.isNew();

		if (!(commerceInventoryAudit instanceof CommerceInventoryAuditModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceInventoryAudit.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(commerceInventoryAudit);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceInventoryAudit proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceInventoryAudit implementation " +
				commerceInventoryAudit.getClass());
		}

		CommerceInventoryAuditModelImpl commerceInventoryAuditModelImpl = (CommerceInventoryAuditModelImpl)commerceInventoryAudit;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceInventoryAudit.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceInventoryAudit.setCreateDate(now);
			}
			else {
				commerceInventoryAudit.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!commerceInventoryAuditModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceInventoryAudit.setModifiedDate(now);
			}
			else {
				commerceInventoryAudit.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceInventoryAudit.isNew()) {
				session.save(commerceInventoryAudit);

				commerceInventoryAudit.setNew(false);
			}
			else {
				commerceInventoryAudit = (CommerceInventoryAudit)session.merge(commerceInventoryAudit);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceInventoryAuditModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryAuditImpl.class,
			commerceInventoryAudit.getPrimaryKey(), commerceInventoryAudit,
			false);

		clearUniqueFindersCache(commerceInventoryAuditModelImpl, false);
		cacheUniqueFindersCache(commerceInventoryAuditModelImpl);

		commerceInventoryAudit.resetOriginalValues();

		return commerceInventoryAudit;
	}

	/**
	 * Returns the commerce inventory audit with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce inventory audit
	 * @return the commerce inventory audit
	 * @throws NoSuchInventoryAuditException if a commerce inventory audit with the primary key could not be found
	 */
	@Override
	public CommerceInventoryAudit findByPrimaryKey(Serializable primaryKey)
		throws NoSuchInventoryAuditException {
		CommerceInventoryAudit commerceInventoryAudit = fetchByPrimaryKey(primaryKey);

		if (commerceInventoryAudit == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchInventoryAuditException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commerceInventoryAudit;
	}

	/**
	 * Returns the commerce inventory audit with the primary key or throws a {@link NoSuchInventoryAuditException} if it could not be found.
	 *
	 * @param commerceInventoryAuditId the primary key of the commerce inventory audit
	 * @return the commerce inventory audit
	 * @throws NoSuchInventoryAuditException if a commerce inventory audit with the primary key could not be found
	 */
	@Override
	public CommerceInventoryAudit findByPrimaryKey(
		long commerceInventoryAuditId) throws NoSuchInventoryAuditException {
		return findByPrimaryKey((Serializable)commerceInventoryAuditId);
	}

	/**
	 * Returns the commerce inventory audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce inventory audit
	 * @return the commerce inventory audit, or <code>null</code> if a commerce inventory audit with the primary key could not be found
	 */
	@Override
	public CommerceInventoryAudit fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
				CommerceInventoryAuditImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceInventoryAudit commerceInventoryAudit = (CommerceInventoryAudit)serializable;

		if (commerceInventoryAudit == null) {
			Session session = null;

			try {
				session = openSession();

				commerceInventoryAudit = (CommerceInventoryAudit)session.get(CommerceInventoryAuditImpl.class,
						primaryKey);

				if (commerceInventoryAudit != null) {
					cacheResult(commerceInventoryAudit);
				}
				else {
					entityCache.putResult(CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
						CommerceInventoryAuditImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
					CommerceInventoryAuditImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceInventoryAudit;
	}

	/**
	 * Returns the commerce inventory audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceInventoryAuditId the primary key of the commerce inventory audit
	 * @return the commerce inventory audit, or <code>null</code> if a commerce inventory audit with the primary key could not be found
	 */
	@Override
	public CommerceInventoryAudit fetchByPrimaryKey(
		long commerceInventoryAuditId) {
		return fetchByPrimaryKey((Serializable)commerceInventoryAuditId);
	}

	@Override
	public Map<Serializable, CommerceInventoryAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceInventoryAudit> map = new HashMap<Serializable, CommerceInventoryAudit>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceInventoryAudit commerceInventoryAudit = fetchByPrimaryKey(primaryKey);

			if (commerceInventoryAudit != null) {
				map.put(primaryKey, commerceInventoryAudit);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
					CommerceInventoryAuditImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceInventoryAudit)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMMERCEINVENTORYAUDIT_WHERE_PKS_IN);

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

			for (CommerceInventoryAudit commerceInventoryAudit : (List<CommerceInventoryAudit>)q.list()) {
				map.put(commerceInventoryAudit.getPrimaryKeyObj(),
					commerceInventoryAudit);

				cacheResult(commerceInventoryAudit);

				uncachedPrimaryKeys.remove(commerceInventoryAudit.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
					CommerceInventoryAuditImpl.class, primaryKey, nullModel);
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
	 * Returns all the commerce inventory audits.
	 *
	 * @return the commerce inventory audits
	 */
	@Override
	public List<CommerceInventoryAudit> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory audits
	 * @param end the upper bound of the range of commerce inventory audits (not inclusive)
	 * @return the range of commerce inventory audits
	 */
	@Override
	public List<CommerceInventoryAudit> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory audits
	 * @param end the upper bound of the range of commerce inventory audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce inventory audits
	 */
	@Override
	public List<CommerceInventoryAudit> findAll(int start, int end,
		OrderByComparator<CommerceInventoryAudit> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory audits
	 * @param end the upper bound of the range of commerce inventory audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce inventory audits
	 */
	@Override
	public List<CommerceInventoryAudit> findAll(int start, int end,
		OrderByComparator<CommerceInventoryAudit> orderByComparator,
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

		List<CommerceInventoryAudit> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceInventoryAudit>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEINVENTORYAUDIT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEINVENTORYAUDIT;

				if (pagination) {
					sql = sql.concat(CommerceInventoryAuditModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceInventoryAudit>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceInventoryAudit>)QueryUtil.list(q,
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
	 * Removes all the commerce inventory audits from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceInventoryAudit commerceInventoryAudit : findAll()) {
			remove(commerceInventoryAudit);
		}
	}

	/**
	 * Returns the number of commerce inventory audits.
	 *
	 * @return the number of commerce inventory audits
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCEINVENTORYAUDIT);

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
		return CommerceInventoryAuditModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce inventory audit persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CommerceInventoryAuditImpl.class.getName());
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
	private static final String _SQL_SELECT_COMMERCEINVENTORYAUDIT = "SELECT commerceInventoryAudit FROM CommerceInventoryAudit commerceInventoryAudit";
	private static final String _SQL_SELECT_COMMERCEINVENTORYAUDIT_WHERE_PKS_IN = "SELECT commerceInventoryAudit FROM CommerceInventoryAudit commerceInventoryAudit WHERE CIAuditId IN (";
	private static final String _SQL_SELECT_COMMERCEINVENTORYAUDIT_WHERE = "SELECT commerceInventoryAudit FROM CommerceInventoryAudit commerceInventoryAudit WHERE ";
	private static final String _SQL_COUNT_COMMERCEINVENTORYAUDIT = "SELECT COUNT(commerceInventoryAudit) FROM CommerceInventoryAudit commerceInventoryAudit";
	private static final String _SQL_COUNT_COMMERCEINVENTORYAUDIT_WHERE = "SELECT COUNT(commerceInventoryAudit) FROM CommerceInventoryAudit commerceInventoryAudit WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceInventoryAudit.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommerceInventoryAudit exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CommerceInventoryAudit exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CommerceInventoryAuditPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"commerceInventoryAuditId"
			});
}