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

import com.liferay.commerce.batch.exception.NoSuchBatchJobInstanceException;
import com.liferay.commerce.batch.model.CommerceBatchJobInstance;
import com.liferay.commerce.batch.model.impl.CommerceBatchJobInstanceImpl;
import com.liferay.commerce.batch.model.impl.CommerceBatchJobInstanceModelImpl;
import com.liferay.commerce.batch.service.persistence.CommerceBatchJobInstancePersistence;

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
 * The persistence implementation for the commerce batch job instance service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Matija Petanjek
 * @see CommerceBatchJobInstancePersistence
 * @see com.liferay.commerce.batch.service.persistence.CommerceBatchJobInstanceUtil
 * @generated
 */
@ProviderType
public class CommerceBatchJobInstancePersistenceImpl extends BasePersistenceImpl<CommerceBatchJobInstance>
	implements CommerceBatchJobInstancePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommerceBatchJobInstanceUtil} to access the commerce batch job instance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommerceBatchJobInstanceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommerceBatchJobInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBatchJobInstanceModelImpl.FINDER_CACHE_ENABLED,
			CommerceBatchJobInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommerceBatchJobInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBatchJobInstanceModelImpl.FINDER_CACHE_ENABLED,
			CommerceBatchJobInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommerceBatchJobInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBatchJobInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CommerceBatchJobInstancePersistenceImpl() {
		setModelClass(CommerceBatchJobInstance.class);

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
	 * Caches the commerce batch job instance in the entity cache if it is enabled.
	 *
	 * @param commerceBatchJobInstance the commerce batch job instance
	 */
	@Override
	public void cacheResult(CommerceBatchJobInstance commerceBatchJobInstance) {
		entityCache.putResult(CommerceBatchJobInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBatchJobInstanceImpl.class,
			commerceBatchJobInstance.getPrimaryKey(), commerceBatchJobInstance);

		commerceBatchJobInstance.resetOriginalValues();
	}

	/**
	 * Caches the commerce batch job instances in the entity cache if it is enabled.
	 *
	 * @param commerceBatchJobInstances the commerce batch job instances
	 */
	@Override
	public void cacheResult(
		List<CommerceBatchJobInstance> commerceBatchJobInstances) {
		for (CommerceBatchJobInstance commerceBatchJobInstance : commerceBatchJobInstances) {
			if (entityCache.getResult(
						CommerceBatchJobInstanceModelImpl.ENTITY_CACHE_ENABLED,
						CommerceBatchJobInstanceImpl.class,
						commerceBatchJobInstance.getPrimaryKey()) == null) {
				cacheResult(commerceBatchJobInstance);
			}
			else {
				commerceBatchJobInstance.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce batch job instances.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceBatchJobInstanceImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce batch job instance.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceBatchJobInstance commerceBatchJobInstance) {
		entityCache.removeResult(CommerceBatchJobInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBatchJobInstanceImpl.class,
			commerceBatchJobInstance.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<CommerceBatchJobInstance> commerceBatchJobInstances) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceBatchJobInstance commerceBatchJobInstance : commerceBatchJobInstances) {
			entityCache.removeResult(CommerceBatchJobInstanceModelImpl.ENTITY_CACHE_ENABLED,
				CommerceBatchJobInstanceImpl.class,
				commerceBatchJobInstance.getPrimaryKey());
		}
	}

	/**
	 * Creates a new commerce batch job instance with the primary key. Does not add the commerce batch job instance to the database.
	 *
	 * @param commerceBatchJobInstanceId the primary key for the new commerce batch job instance
	 * @return the new commerce batch job instance
	 */
	@Override
	public CommerceBatchJobInstance create(long commerceBatchJobInstanceId) {
		CommerceBatchJobInstance commerceBatchJobInstance = new CommerceBatchJobInstanceImpl();

		commerceBatchJobInstance.setNew(true);
		commerceBatchJobInstance.setPrimaryKey(commerceBatchJobInstanceId);

		return commerceBatchJobInstance;
	}

	/**
	 * Removes the commerce batch job instance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceBatchJobInstanceId the primary key of the commerce batch job instance
	 * @return the commerce batch job instance that was removed
	 * @throws NoSuchBatchJobInstanceException if a commerce batch job instance with the primary key could not be found
	 */
	@Override
	public CommerceBatchJobInstance remove(long commerceBatchJobInstanceId)
		throws NoSuchBatchJobInstanceException {
		return remove((Serializable)commerceBatchJobInstanceId);
	}

	/**
	 * Removes the commerce batch job instance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce batch job instance
	 * @return the commerce batch job instance that was removed
	 * @throws NoSuchBatchJobInstanceException if a commerce batch job instance with the primary key could not be found
	 */
	@Override
	public CommerceBatchJobInstance remove(Serializable primaryKey)
		throws NoSuchBatchJobInstanceException {
		Session session = null;

		try {
			session = openSession();

			CommerceBatchJobInstance commerceBatchJobInstance = (CommerceBatchJobInstance)session.get(CommerceBatchJobInstanceImpl.class,
					primaryKey);

			if (commerceBatchJobInstance == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBatchJobInstanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commerceBatchJobInstance);
		}
		catch (NoSuchBatchJobInstanceException nsee) {
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
	protected CommerceBatchJobInstance removeImpl(
		CommerceBatchJobInstance commerceBatchJobInstance) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceBatchJobInstance)) {
				commerceBatchJobInstance = (CommerceBatchJobInstance)session.get(CommerceBatchJobInstanceImpl.class,
						commerceBatchJobInstance.getPrimaryKeyObj());
			}

			if (commerceBatchJobInstance != null) {
				session.delete(commerceBatchJobInstance);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceBatchJobInstance != null) {
			clearCache(commerceBatchJobInstance);
		}

		return commerceBatchJobInstance;
	}

	@Override
	public CommerceBatchJobInstance updateImpl(
		CommerceBatchJobInstance commerceBatchJobInstance) {
		boolean isNew = commerceBatchJobInstance.isNew();

		if (!(commerceBatchJobInstance instanceof CommerceBatchJobInstanceModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceBatchJobInstance.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(commerceBatchJobInstance);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceBatchJobInstance proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceBatchJobInstance implementation " +
				commerceBatchJobInstance.getClass());
		}

		CommerceBatchJobInstanceModelImpl commerceBatchJobInstanceModelImpl = (CommerceBatchJobInstanceModelImpl)commerceBatchJobInstance;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceBatchJobInstance.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceBatchJobInstance.setCreateDate(now);
			}
			else {
				commerceBatchJobInstance.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!commerceBatchJobInstanceModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceBatchJobInstance.setModifiedDate(now);
			}
			else {
				commerceBatchJobInstance.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceBatchJobInstance.isNew()) {
				session.save(commerceBatchJobInstance);

				commerceBatchJobInstance.setNew(false);
			}
			else {
				commerceBatchJobInstance = (CommerceBatchJobInstance)session.merge(commerceBatchJobInstance);
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

		entityCache.putResult(CommerceBatchJobInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBatchJobInstanceImpl.class,
			commerceBatchJobInstance.getPrimaryKey(), commerceBatchJobInstance,
			false);

		commerceBatchJobInstance.resetOriginalValues();

		return commerceBatchJobInstance;
	}

	/**
	 * Returns the commerce batch job instance with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce batch job instance
	 * @return the commerce batch job instance
	 * @throws NoSuchBatchJobInstanceException if a commerce batch job instance with the primary key could not be found
	 */
	@Override
	public CommerceBatchJobInstance findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBatchJobInstanceException {
		CommerceBatchJobInstance commerceBatchJobInstance = fetchByPrimaryKey(primaryKey);

		if (commerceBatchJobInstance == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBatchJobInstanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commerceBatchJobInstance;
	}

	/**
	 * Returns the commerce batch job instance with the primary key or throws a {@link NoSuchBatchJobInstanceException} if it could not be found.
	 *
	 * @param commerceBatchJobInstanceId the primary key of the commerce batch job instance
	 * @return the commerce batch job instance
	 * @throws NoSuchBatchJobInstanceException if a commerce batch job instance with the primary key could not be found
	 */
	@Override
	public CommerceBatchJobInstance findByPrimaryKey(
		long commerceBatchJobInstanceId) throws NoSuchBatchJobInstanceException {
		return findByPrimaryKey((Serializable)commerceBatchJobInstanceId);
	}

	/**
	 * Returns the commerce batch job instance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce batch job instance
	 * @return the commerce batch job instance, or <code>null</code> if a commerce batch job instance with the primary key could not be found
	 */
	@Override
	public CommerceBatchJobInstance fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CommerceBatchJobInstanceModelImpl.ENTITY_CACHE_ENABLED,
				CommerceBatchJobInstanceImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceBatchJobInstance commerceBatchJobInstance = (CommerceBatchJobInstance)serializable;

		if (commerceBatchJobInstance == null) {
			Session session = null;

			try {
				session = openSession();

				commerceBatchJobInstance = (CommerceBatchJobInstance)session.get(CommerceBatchJobInstanceImpl.class,
						primaryKey);

				if (commerceBatchJobInstance != null) {
					cacheResult(commerceBatchJobInstance);
				}
				else {
					entityCache.putResult(CommerceBatchJobInstanceModelImpl.ENTITY_CACHE_ENABLED,
						CommerceBatchJobInstanceImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommerceBatchJobInstanceModelImpl.ENTITY_CACHE_ENABLED,
					CommerceBatchJobInstanceImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceBatchJobInstance;
	}

	/**
	 * Returns the commerce batch job instance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceBatchJobInstanceId the primary key of the commerce batch job instance
	 * @return the commerce batch job instance, or <code>null</code> if a commerce batch job instance with the primary key could not be found
	 */
	@Override
	public CommerceBatchJobInstance fetchByPrimaryKey(
		long commerceBatchJobInstanceId) {
		return fetchByPrimaryKey((Serializable)commerceBatchJobInstanceId);
	}

	@Override
	public Map<Serializable, CommerceBatchJobInstance> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceBatchJobInstance> map = new HashMap<Serializable, CommerceBatchJobInstance>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceBatchJobInstance commerceBatchJobInstance = fetchByPrimaryKey(primaryKey);

			if (commerceBatchJobInstance != null) {
				map.put(primaryKey, commerceBatchJobInstance);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CommerceBatchJobInstanceModelImpl.ENTITY_CACHE_ENABLED,
					CommerceBatchJobInstanceImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceBatchJobInstance)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMMERCEBATCHJOBINSTANCE_WHERE_PKS_IN);

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

			for (CommerceBatchJobInstance commerceBatchJobInstance : (List<CommerceBatchJobInstance>)q.list()) {
				map.put(commerceBatchJobInstance.getPrimaryKeyObj(),
					commerceBatchJobInstance);

				cacheResult(commerceBatchJobInstance);

				uncachedPrimaryKeys.remove(commerceBatchJobInstance.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CommerceBatchJobInstanceModelImpl.ENTITY_CACHE_ENABLED,
					CommerceBatchJobInstanceImpl.class, primaryKey, nullModel);
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
	 * Returns all the commerce batch job instances.
	 *
	 * @return the commerce batch job instances
	 */
	@Override
	public List<CommerceBatchJobInstance> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce batch job instances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBatchJobInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce batch job instances
	 * @param end the upper bound of the range of commerce batch job instances (not inclusive)
	 * @return the range of commerce batch job instances
	 */
	@Override
	public List<CommerceBatchJobInstance> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce batch job instances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBatchJobInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce batch job instances
	 * @param end the upper bound of the range of commerce batch job instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce batch job instances
	 */
	@Override
	public List<CommerceBatchJobInstance> findAll(int start, int end,
		OrderByComparator<CommerceBatchJobInstance> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce batch job instances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBatchJobInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce batch job instances
	 * @param end the upper bound of the range of commerce batch job instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce batch job instances
	 */
	@Override
	public List<CommerceBatchJobInstance> findAll(int start, int end,
		OrderByComparator<CommerceBatchJobInstance> orderByComparator,
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

		List<CommerceBatchJobInstance> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceBatchJobInstance>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEBATCHJOBINSTANCE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEBATCHJOBINSTANCE;

				if (pagination) {
					sql = sql.concat(CommerceBatchJobInstanceModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceBatchJobInstance>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceBatchJobInstance>)QueryUtil.list(q,
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
	 * Removes all the commerce batch job instances from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceBatchJobInstance commerceBatchJobInstance : findAll()) {
			remove(commerceBatchJobInstance);
		}
	}

	/**
	 * Returns the number of commerce batch job instances.
	 *
	 * @return the number of commerce batch job instances
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCEBATCHJOBINSTANCE);

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
		return CommerceBatchJobInstanceModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce batch job instance persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CommerceBatchJobInstanceImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_COMMERCEBATCHJOBINSTANCE = "SELECT commerceBatchJobInstance FROM CommerceBatchJobInstance commerceBatchJobInstance";
	private static final String _SQL_SELECT_COMMERCEBATCHJOBINSTANCE_WHERE_PKS_IN =
		"SELECT commerceBatchJobInstance FROM CommerceBatchJobInstance commerceBatchJobInstance WHERE commerceBatchJobInstanceId IN (";
	private static final String _SQL_COUNT_COMMERCEBATCHJOBINSTANCE = "SELECT COUNT(commerceBatchJobInstance) FROM CommerceBatchJobInstance commerceBatchJobInstance";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceBatchJobInstance.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommerceBatchJobInstance exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CommerceBatchJobInstancePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"key"
			});
}