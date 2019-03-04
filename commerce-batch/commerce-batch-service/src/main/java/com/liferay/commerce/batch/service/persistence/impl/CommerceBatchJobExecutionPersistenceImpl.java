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

package com.liferay.commerce.batch.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.batch.exception.NoSuchBatchJobExecutionException;
import com.liferay.commerce.batch.model.CommerceBatchJobExecution;
import com.liferay.commerce.batch.model.impl.CommerceBatchJobExecutionImpl;
import com.liferay.commerce.batch.model.impl.CommerceBatchJobExecutionModelImpl;
import com.liferay.commerce.batch.service.persistence.CommerceBatchJobExecutionPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
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
 * The persistence implementation for the commerce batch job execution service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Matija Petanjek
 * @see CommerceBatchJobExecutionPersistence
 * @see com.liferay.commerce.batch.service.persistence.CommerceBatchJobExecutionUtil
 * @generated
 */
@ProviderType
public class CommerceBatchJobExecutionPersistenceImpl
	extends BasePersistenceImpl<CommerceBatchJobExecution>
	implements CommerceBatchJobExecutionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommerceBatchJobExecutionUtil} to access the commerce batch job execution persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommerceBatchJobExecutionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommerceBatchJobExecutionModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBatchJobExecutionModelImpl.FINDER_CACHE_ENABLED,
			CommerceBatchJobExecutionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommerceBatchJobExecutionModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBatchJobExecutionModelImpl.FINDER_CACHE_ENABLED,
			CommerceBatchJobExecutionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommerceBatchJobExecutionModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBatchJobExecutionModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

	public CommerceBatchJobExecutionPersistenceImpl() {
		setModelClass(CommerceBatchJobExecution.class);
	}

	/**
	 * Caches the commerce batch job execution in the entity cache if it is enabled.
	 *
	 * @param commerceBatchJobExecution the commerce batch job execution
	 */
	@Override
	public void cacheResult(CommerceBatchJobExecution commerceBatchJobExecution) {
		entityCache.putResult(CommerceBatchJobExecutionModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBatchJobExecutionImpl.class,
			commerceBatchJobExecution.getPrimaryKey(), commerceBatchJobExecution);

		commerceBatchJobExecution.resetOriginalValues();
	}

	/**
	 * Caches the commerce batch job executions in the entity cache if it is enabled.
	 *
	 * @param commerceBatchJobExecutions the commerce batch job executions
	 */
	@Override
	public void cacheResult(
		List<CommerceBatchJobExecution> commerceBatchJobExecutions) {
		for (CommerceBatchJobExecution commerceBatchJobExecution : commerceBatchJobExecutions) {
			if (entityCache.getResult(
						CommerceBatchJobExecutionModelImpl.ENTITY_CACHE_ENABLED,
						CommerceBatchJobExecutionImpl.class,
						commerceBatchJobExecution.getPrimaryKey()) == null) {
				cacheResult(commerceBatchJobExecution);
			}
			else {
				commerceBatchJobExecution.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce batch job executions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceBatchJobExecutionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce batch job execution.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceBatchJobExecution commerceBatchJobExecution) {
		entityCache.removeResult(CommerceBatchJobExecutionModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBatchJobExecutionImpl.class,
			commerceBatchJobExecution.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<CommerceBatchJobExecution> commerceBatchJobExecutions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceBatchJobExecution commerceBatchJobExecution : commerceBatchJobExecutions) {
			entityCache.removeResult(CommerceBatchJobExecutionModelImpl.ENTITY_CACHE_ENABLED,
				CommerceBatchJobExecutionImpl.class,
				commerceBatchJobExecution.getPrimaryKey());
		}
	}

	/**
	 * Creates a new commerce batch job execution with the primary key. Does not add the commerce batch job execution to the database.
	 *
	 * @param commerceBatchJobExecutionId the primary key for the new commerce batch job execution
	 * @return the new commerce batch job execution
	 */
	@Override
	public CommerceBatchJobExecution create(long commerceBatchJobExecutionId) {
		CommerceBatchJobExecution commerceBatchJobExecution = new CommerceBatchJobExecutionImpl();

		commerceBatchJobExecution.setNew(true);
		commerceBatchJobExecution.setPrimaryKey(commerceBatchJobExecutionId);

		return commerceBatchJobExecution;
	}

	/**
	 * Removes the commerce batch job execution with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceBatchJobExecutionId the primary key of the commerce batch job execution
	 * @return the commerce batch job execution that was removed
	 * @throws NoSuchBatchJobExecutionException if a commerce batch job execution with the primary key could not be found
	 */
	@Override
	public CommerceBatchJobExecution remove(long commerceBatchJobExecutionId)
		throws NoSuchBatchJobExecutionException {
		return remove((Serializable)commerceBatchJobExecutionId);
	}

	/**
	 * Removes the commerce batch job execution with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce batch job execution
	 * @return the commerce batch job execution that was removed
	 * @throws NoSuchBatchJobExecutionException if a commerce batch job execution with the primary key could not be found
	 */
	@Override
	public CommerceBatchJobExecution remove(Serializable primaryKey)
		throws NoSuchBatchJobExecutionException {
		Session session = null;

		try {
			session = openSession();

			CommerceBatchJobExecution commerceBatchJobExecution = (CommerceBatchJobExecution)session.get(CommerceBatchJobExecutionImpl.class,
					primaryKey);

			if (commerceBatchJobExecution == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBatchJobExecutionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commerceBatchJobExecution);
		}
		catch (NoSuchBatchJobExecutionException nsee) {
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
	protected CommerceBatchJobExecution removeImpl(
		CommerceBatchJobExecution commerceBatchJobExecution) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceBatchJobExecution)) {
				commerceBatchJobExecution = (CommerceBatchJobExecution)session.get(CommerceBatchJobExecutionImpl.class,
						commerceBatchJobExecution.getPrimaryKeyObj());
			}

			if (commerceBatchJobExecution != null) {
				session.delete(commerceBatchJobExecution);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceBatchJobExecution != null) {
			clearCache(commerceBatchJobExecution);
		}

		return commerceBatchJobExecution;
	}

	@Override
	public CommerceBatchJobExecution updateImpl(
		CommerceBatchJobExecution commerceBatchJobExecution) {
		boolean isNew = commerceBatchJobExecution.isNew();

		if (!(commerceBatchJobExecution instanceof CommerceBatchJobExecutionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceBatchJobExecution.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(commerceBatchJobExecution);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceBatchJobExecution proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceBatchJobExecution implementation " +
				commerceBatchJobExecution.getClass());
		}

		CommerceBatchJobExecutionModelImpl commerceBatchJobExecutionModelImpl = (CommerceBatchJobExecutionModelImpl)commerceBatchJobExecution;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceBatchJobExecution.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceBatchJobExecution.setCreateDate(now);
			}
			else {
				commerceBatchJobExecution.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!commerceBatchJobExecutionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceBatchJobExecution.setModifiedDate(now);
			}
			else {
				commerceBatchJobExecution.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceBatchJobExecution.isNew()) {
				session.save(commerceBatchJobExecution);

				commerceBatchJobExecution.setNew(false);
			}
			else {
				commerceBatchJobExecution = (CommerceBatchJobExecution)session.merge(commerceBatchJobExecution);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(CommerceBatchJobExecutionModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBatchJobExecutionImpl.class,
			commerceBatchJobExecution.getPrimaryKey(),
			commerceBatchJobExecution, false);

		commerceBatchJobExecution.resetOriginalValues();

		return commerceBatchJobExecution;
	}

	/**
	 * Returns the commerce batch job execution with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce batch job execution
	 * @return the commerce batch job execution
	 * @throws NoSuchBatchJobExecutionException if a commerce batch job execution with the primary key could not be found
	 */
	@Override
	public CommerceBatchJobExecution findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBatchJobExecutionException {
		CommerceBatchJobExecution commerceBatchJobExecution = fetchByPrimaryKey(primaryKey);

		if (commerceBatchJobExecution == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBatchJobExecutionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commerceBatchJobExecution;
	}

	/**
	 * Returns the commerce batch job execution with the primary key or throws a {@link NoSuchBatchJobExecutionException} if it could not be found.
	 *
	 * @param commerceBatchJobExecutionId the primary key of the commerce batch job execution
	 * @return the commerce batch job execution
	 * @throws NoSuchBatchJobExecutionException if a commerce batch job execution with the primary key could not be found
	 */
	@Override
	public CommerceBatchJobExecution findByPrimaryKey(
		long commerceBatchJobExecutionId)
		throws NoSuchBatchJobExecutionException {
		return findByPrimaryKey((Serializable)commerceBatchJobExecutionId);
	}

	/**
	 * Returns the commerce batch job execution with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce batch job execution
	 * @return the commerce batch job execution, or <code>null</code> if a commerce batch job execution with the primary key could not be found
	 */
	@Override
	public CommerceBatchJobExecution fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CommerceBatchJobExecutionModelImpl.ENTITY_CACHE_ENABLED,
				CommerceBatchJobExecutionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceBatchJobExecution commerceBatchJobExecution = (CommerceBatchJobExecution)serializable;

		if (commerceBatchJobExecution == null) {
			Session session = null;

			try {
				session = openSession();

				commerceBatchJobExecution = (CommerceBatchJobExecution)session.get(CommerceBatchJobExecutionImpl.class,
						primaryKey);

				if (commerceBatchJobExecution != null) {
					cacheResult(commerceBatchJobExecution);
				}
				else {
					entityCache.putResult(CommerceBatchJobExecutionModelImpl.ENTITY_CACHE_ENABLED,
						CommerceBatchJobExecutionImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommerceBatchJobExecutionModelImpl.ENTITY_CACHE_ENABLED,
					CommerceBatchJobExecutionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceBatchJobExecution;
	}

	/**
	 * Returns the commerce batch job execution with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceBatchJobExecutionId the primary key of the commerce batch job execution
	 * @return the commerce batch job execution, or <code>null</code> if a commerce batch job execution with the primary key could not be found
	 */
	@Override
	public CommerceBatchJobExecution fetchByPrimaryKey(
		long commerceBatchJobExecutionId) {
		return fetchByPrimaryKey((Serializable)commerceBatchJobExecutionId);
	}

	@Override
	public Map<Serializable, CommerceBatchJobExecution> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceBatchJobExecution> map = new HashMap<Serializable, CommerceBatchJobExecution>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceBatchJobExecution commerceBatchJobExecution = fetchByPrimaryKey(primaryKey);

			if (commerceBatchJobExecution != null) {
				map.put(primaryKey, commerceBatchJobExecution);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CommerceBatchJobExecutionModelImpl.ENTITY_CACHE_ENABLED,
					CommerceBatchJobExecutionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceBatchJobExecution)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMMERCEBATCHJOBEXECUTION_WHERE_PKS_IN);

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

			for (CommerceBatchJobExecution commerceBatchJobExecution : (List<CommerceBatchJobExecution>)q.list()) {
				map.put(commerceBatchJobExecution.getPrimaryKeyObj(),
					commerceBatchJobExecution);

				cacheResult(commerceBatchJobExecution);

				uncachedPrimaryKeys.remove(commerceBatchJobExecution.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CommerceBatchJobExecutionModelImpl.ENTITY_CACHE_ENABLED,
					CommerceBatchJobExecutionImpl.class, primaryKey, nullModel);
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
	 * Returns all the commerce batch job executions.
	 *
	 * @return the commerce batch job executions
	 */
	@Override
	public List<CommerceBatchJobExecution> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce batch job executions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBatchJobExecutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce batch job executions
	 * @param end the upper bound of the range of commerce batch job executions (not inclusive)
	 * @return the range of commerce batch job executions
	 */
	@Override
	public List<CommerceBatchJobExecution> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce batch job executions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBatchJobExecutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce batch job executions
	 * @param end the upper bound of the range of commerce batch job executions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce batch job executions
	 */
	@Override
	public List<CommerceBatchJobExecution> findAll(int start, int end,
		OrderByComparator<CommerceBatchJobExecution> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce batch job executions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBatchJobExecutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce batch job executions
	 * @param end the upper bound of the range of commerce batch job executions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce batch job executions
	 */
	@Override
	public List<CommerceBatchJobExecution> findAll(int start, int end,
		OrderByComparator<CommerceBatchJobExecution> orderByComparator,
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

		List<CommerceBatchJobExecution> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceBatchJobExecution>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEBATCHJOBEXECUTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEBATCHJOBEXECUTION;

				if (pagination) {
					sql = sql.concat(CommerceBatchJobExecutionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceBatchJobExecution>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceBatchJobExecution>)QueryUtil.list(q,
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
	 * Removes all the commerce batch job executions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceBatchJobExecution commerceBatchJobExecution : findAll()) {
			remove(commerceBatchJobExecution);
		}
	}

	/**
	 * Returns the number of commerce batch job executions.
	 *
	 * @return the number of commerce batch job executions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCEBATCHJOBEXECUTION);

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
		return CommerceBatchJobExecutionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce batch job execution persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CommerceBatchJobExecutionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_COMMERCEBATCHJOBEXECUTION = "SELECT commerceBatchJobExecution FROM CommerceBatchJobExecution commerceBatchJobExecution";
	private static final String _SQL_SELECT_COMMERCEBATCHJOBEXECUTION_WHERE_PKS_IN =
		"SELECT commerceBatchJobExecution FROM CommerceBatchJobExecution commerceBatchJobExecution WHERE commerceBatchJobExecutionId IN (";
	private static final String _SQL_COUNT_COMMERCEBATCHJOBEXECUTION = "SELECT COUNT(commerceBatchJobExecution) FROM CommerceBatchJobExecution commerceBatchJobExecution";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceBatchJobExecution.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommerceBatchJobExecution exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CommerceBatchJobExecutionPersistenceImpl.class);
}