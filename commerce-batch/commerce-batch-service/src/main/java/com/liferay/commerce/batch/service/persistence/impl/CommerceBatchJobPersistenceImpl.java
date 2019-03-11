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

import com.liferay.commerce.batch.exception.NoSuchBatchJobException;
import com.liferay.commerce.batch.model.CommerceBatchJob;
import com.liferay.commerce.batch.model.impl.CommerceBatchJobImpl;
import com.liferay.commerce.batch.model.impl.CommerceBatchJobModelImpl;
import com.liferay.commerce.batch.service.persistence.CommerceBatchJobPersistence;

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
 * The persistence implementation for the commerce batch job service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Matija Petanjek
 * @see CommerceBatchJobPersistence
 * @see com.liferay.commerce.batch.service.persistence.CommerceBatchJobUtil
 * @generated
 */
@ProviderType
public class CommerceBatchJobPersistenceImpl extends BasePersistenceImpl<CommerceBatchJob>
	implements CommerceBatchJobPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommerceBatchJobUtil} to access the commerce batch job persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommerceBatchJobImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommerceBatchJobModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBatchJobModelImpl.FINDER_CACHE_ENABLED,
			CommerceBatchJobImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommerceBatchJobModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBatchJobModelImpl.FINDER_CACHE_ENABLED,
			CommerceBatchJobImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommerceBatchJobModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBatchJobModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CommerceBatchJobPersistenceImpl() {
		setModelClass(CommerceBatchJob.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("key", "key_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the commerce batch job in the entity cache if it is enabled.
	 *
	 * @param commerceBatchJob the commerce batch job
	 */
	@Override
	public void cacheResult(CommerceBatchJob commerceBatchJob) {
		entityCache.putResult(CommerceBatchJobModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBatchJobImpl.class, commerceBatchJob.getPrimaryKey(),
			commerceBatchJob);

		commerceBatchJob.resetOriginalValues();
	}

	/**
	 * Caches the commerce batch jobs in the entity cache if it is enabled.
	 *
	 * @param commerceBatchJobs the commerce batch jobs
	 */
	@Override
	public void cacheResult(List<CommerceBatchJob> commerceBatchJobs) {
		for (CommerceBatchJob commerceBatchJob : commerceBatchJobs) {
			if (entityCache.getResult(
						CommerceBatchJobModelImpl.ENTITY_CACHE_ENABLED,
						CommerceBatchJobImpl.class,
						commerceBatchJob.getPrimaryKey()) == null) {
				cacheResult(commerceBatchJob);
			}
			else {
				commerceBatchJob.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce batch jobs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceBatchJobImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce batch job.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceBatchJob commerceBatchJob) {
		entityCache.removeResult(CommerceBatchJobModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBatchJobImpl.class, commerceBatchJob.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CommerceBatchJob> commerceBatchJobs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceBatchJob commerceBatchJob : commerceBatchJobs) {
			entityCache.removeResult(CommerceBatchJobModelImpl.ENTITY_CACHE_ENABLED,
				CommerceBatchJobImpl.class, commerceBatchJob.getPrimaryKey());
		}
	}

	/**
	 * Creates a new commerce batch job with the primary key. Does not add the commerce batch job to the database.
	 *
	 * @param commerceBatchJobId the primary key for the new commerce batch job
	 * @return the new commerce batch job
	 */
	@Override
	public CommerceBatchJob create(long commerceBatchJobId) {
		CommerceBatchJob commerceBatchJob = new CommerceBatchJobImpl();

		commerceBatchJob.setNew(true);
		commerceBatchJob.setPrimaryKey(commerceBatchJobId);

		return commerceBatchJob;
	}

	/**
	 * Removes the commerce batch job with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceBatchJobId the primary key of the commerce batch job
	 * @return the commerce batch job that was removed
	 * @throws NoSuchBatchJobException if a commerce batch job with the primary key could not be found
	 */
	@Override
	public CommerceBatchJob remove(long commerceBatchJobId)
		throws NoSuchBatchJobException {
		return remove((Serializable)commerceBatchJobId);
	}

	/**
	 * Removes the commerce batch job with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce batch job
	 * @return the commerce batch job that was removed
	 * @throws NoSuchBatchJobException if a commerce batch job with the primary key could not be found
	 */
	@Override
	public CommerceBatchJob remove(Serializable primaryKey)
		throws NoSuchBatchJobException {
		Session session = null;

		try {
			session = openSession();

			CommerceBatchJob commerceBatchJob = (CommerceBatchJob)session.get(CommerceBatchJobImpl.class,
					primaryKey);

			if (commerceBatchJob == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBatchJobException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commerceBatchJob);
		}
		catch (NoSuchBatchJobException nsee) {
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
	protected CommerceBatchJob removeImpl(CommerceBatchJob commerceBatchJob) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceBatchJob)) {
				commerceBatchJob = (CommerceBatchJob)session.get(CommerceBatchJobImpl.class,
						commerceBatchJob.getPrimaryKeyObj());
			}

			if (commerceBatchJob != null) {
				session.delete(commerceBatchJob);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceBatchJob != null) {
			clearCache(commerceBatchJob);
		}

		return commerceBatchJob;
	}

	@Override
	public CommerceBatchJob updateImpl(CommerceBatchJob commerceBatchJob) {
		boolean isNew = commerceBatchJob.isNew();

		if (!(commerceBatchJob instanceof CommerceBatchJobModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceBatchJob.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(commerceBatchJob);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceBatchJob proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceBatchJob implementation " +
				commerceBatchJob.getClass());
		}

		CommerceBatchJobModelImpl commerceBatchJobModelImpl = (CommerceBatchJobModelImpl)commerceBatchJob;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceBatchJob.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceBatchJob.setCreateDate(now);
			}
			else {
				commerceBatchJob.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!commerceBatchJobModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceBatchJob.setModifiedDate(now);
			}
			else {
				commerceBatchJob.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceBatchJob.isNew()) {
				session.save(commerceBatchJob);

				commerceBatchJob.setNew(false);
			}
			else {
				commerceBatchJob = (CommerceBatchJob)session.merge(commerceBatchJob);
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

		entityCache.putResult(CommerceBatchJobModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBatchJobImpl.class, commerceBatchJob.getPrimaryKey(),
			commerceBatchJob, false);

		commerceBatchJob.resetOriginalValues();

		return commerceBatchJob;
	}

	/**
	 * Returns the commerce batch job with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce batch job
	 * @return the commerce batch job
	 * @throws NoSuchBatchJobException if a commerce batch job with the primary key could not be found
	 */
	@Override
	public CommerceBatchJob findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBatchJobException {
		CommerceBatchJob commerceBatchJob = fetchByPrimaryKey(primaryKey);

		if (commerceBatchJob == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBatchJobException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commerceBatchJob;
	}

	/**
	 * Returns the commerce batch job with the primary key or throws a {@link NoSuchBatchJobException} if it could not be found.
	 *
	 * @param commerceBatchJobId the primary key of the commerce batch job
	 * @return the commerce batch job
	 * @throws NoSuchBatchJobException if a commerce batch job with the primary key could not be found
	 */
	@Override
	public CommerceBatchJob findByPrimaryKey(long commerceBatchJobId)
		throws NoSuchBatchJobException {
		return findByPrimaryKey((Serializable)commerceBatchJobId);
	}

	/**
	 * Returns the commerce batch job with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce batch job
	 * @return the commerce batch job, or <code>null</code> if a commerce batch job with the primary key could not be found
	 */
	@Override
	public CommerceBatchJob fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CommerceBatchJobModelImpl.ENTITY_CACHE_ENABLED,
				CommerceBatchJobImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceBatchJob commerceBatchJob = (CommerceBatchJob)serializable;

		if (commerceBatchJob == null) {
			Session session = null;

			try {
				session = openSession();

				commerceBatchJob = (CommerceBatchJob)session.get(CommerceBatchJobImpl.class,
						primaryKey);

				if (commerceBatchJob != null) {
					cacheResult(commerceBatchJob);
				}
				else {
					entityCache.putResult(CommerceBatchJobModelImpl.ENTITY_CACHE_ENABLED,
						CommerceBatchJobImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommerceBatchJobModelImpl.ENTITY_CACHE_ENABLED,
					CommerceBatchJobImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceBatchJob;
	}

	/**
	 * Returns the commerce batch job with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceBatchJobId the primary key of the commerce batch job
	 * @return the commerce batch job, or <code>null</code> if a commerce batch job with the primary key could not be found
	 */
	@Override
	public CommerceBatchJob fetchByPrimaryKey(long commerceBatchJobId) {
		return fetchByPrimaryKey((Serializable)commerceBatchJobId);
	}

	@Override
	public Map<Serializable, CommerceBatchJob> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceBatchJob> map = new HashMap<Serializable, CommerceBatchJob>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceBatchJob commerceBatchJob = fetchByPrimaryKey(primaryKey);

			if (commerceBatchJob != null) {
				map.put(primaryKey, commerceBatchJob);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CommerceBatchJobModelImpl.ENTITY_CACHE_ENABLED,
					CommerceBatchJobImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceBatchJob)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMMERCEBATCHJOB_WHERE_PKS_IN);

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

			for (CommerceBatchJob commerceBatchJob : (List<CommerceBatchJob>)q.list()) {
				map.put(commerceBatchJob.getPrimaryKeyObj(), commerceBatchJob);

				cacheResult(commerceBatchJob);

				uncachedPrimaryKeys.remove(commerceBatchJob.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CommerceBatchJobModelImpl.ENTITY_CACHE_ENABLED,
					CommerceBatchJobImpl.class, primaryKey, nullModel);
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
	 * Returns all the commerce batch jobs.
	 *
	 * @return the commerce batch jobs
	 */
	@Override
	public List<CommerceBatchJob> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce batch jobs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBatchJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce batch jobs
	 * @param end the upper bound of the range of commerce batch jobs (not inclusive)
	 * @return the range of commerce batch jobs
	 */
	@Override
	public List<CommerceBatchJob> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce batch jobs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBatchJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce batch jobs
	 * @param end the upper bound of the range of commerce batch jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce batch jobs
	 */
	@Override
	public List<CommerceBatchJob> findAll(int start, int end,
		OrderByComparator<CommerceBatchJob> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce batch jobs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBatchJobModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce batch jobs
	 * @param end the upper bound of the range of commerce batch jobs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce batch jobs
	 */
	@Override
	public List<CommerceBatchJob> findAll(int start, int end,
		OrderByComparator<CommerceBatchJob> orderByComparator,
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

		List<CommerceBatchJob> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceBatchJob>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEBATCHJOB);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEBATCHJOB;

				if (pagination) {
					sql = sql.concat(CommerceBatchJobModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceBatchJob>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceBatchJob>)QueryUtil.list(q,
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
	 * Removes all the commerce batch jobs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceBatchJob commerceBatchJob : findAll()) {
			remove(commerceBatchJob);
		}
	}

	/**
	 * Returns the number of commerce batch jobs.
	 *
	 * @return the number of commerce batch jobs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCEBATCHJOB);

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
		return CommerceBatchJobModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce batch job persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CommerceBatchJobImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_COMMERCEBATCHJOB = "SELECT commerceBatchJob FROM CommerceBatchJob commerceBatchJob";
	private static final String _SQL_SELECT_COMMERCEBATCHJOB_WHERE_PKS_IN = "SELECT commerceBatchJob FROM CommerceBatchJob commerceBatchJob WHERE commerceBatchJobId IN (";
	private static final String _SQL_COUNT_COMMERCEBATCHJOB = "SELECT COUNT(commerceBatchJob) FROM CommerceBatchJob commerceBatchJob";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceBatchJob.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommerceBatchJob exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CommerceBatchJobPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"key"
			});
}