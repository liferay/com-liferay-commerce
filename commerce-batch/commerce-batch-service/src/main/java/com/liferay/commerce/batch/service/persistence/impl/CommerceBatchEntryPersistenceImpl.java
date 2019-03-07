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

import com.liferay.commerce.batch.exception.NoSuchBatchEntryException;
import com.liferay.commerce.batch.model.CommerceBatchEntry;
import com.liferay.commerce.batch.model.impl.CommerceBatchEntryImpl;
import com.liferay.commerce.batch.model.impl.CommerceBatchEntryModelImpl;
import com.liferay.commerce.batch.service.persistence.CommerceBatchEntryPersistence;

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
 * The persistence implementation for the commerce batch entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Matija Petanjek
 * @see CommerceBatchEntryPersistence
 * @see com.liferay.commerce.batch.service.persistence.CommerceBatchEntryUtil
 * @generated
 */
@ProviderType
public class CommerceBatchEntryPersistenceImpl extends BasePersistenceImpl<CommerceBatchEntry>
	implements CommerceBatchEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommerceBatchEntryUtil} to access the commerce batch entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommerceBatchEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommerceBatchEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBatchEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceBatchEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommerceBatchEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBatchEntryModelImpl.FINDER_CACHE_ENABLED,
			CommerceBatchEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommerceBatchEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBatchEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CommerceBatchEntryPersistenceImpl() {
		setModelClass(CommerceBatchEntry.class);

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
	 * Caches the commerce batch entry in the entity cache if it is enabled.
	 *
	 * @param commerceBatchEntry the commerce batch entry
	 */
	@Override
	public void cacheResult(CommerceBatchEntry commerceBatchEntry) {
		entityCache.putResult(CommerceBatchEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBatchEntryImpl.class, commerceBatchEntry.getPrimaryKey(),
			commerceBatchEntry);

		commerceBatchEntry.resetOriginalValues();
	}

	/**
	 * Caches the commerce batch entries in the entity cache if it is enabled.
	 *
	 * @param commerceBatchEntries the commerce batch entries
	 */
	@Override
	public void cacheResult(List<CommerceBatchEntry> commerceBatchEntries) {
		for (CommerceBatchEntry commerceBatchEntry : commerceBatchEntries) {
			if (entityCache.getResult(
						CommerceBatchEntryModelImpl.ENTITY_CACHE_ENABLED,
						CommerceBatchEntryImpl.class,
						commerceBatchEntry.getPrimaryKey()) == null) {
				cacheResult(commerceBatchEntry);
			}
			else {
				commerceBatchEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce batch entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceBatchEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce batch entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceBatchEntry commerceBatchEntry) {
		entityCache.removeResult(CommerceBatchEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBatchEntryImpl.class, commerceBatchEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CommerceBatchEntry> commerceBatchEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceBatchEntry commerceBatchEntry : commerceBatchEntries) {
			entityCache.removeResult(CommerceBatchEntryModelImpl.ENTITY_CACHE_ENABLED,
				CommerceBatchEntryImpl.class, commerceBatchEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new commerce batch entry with the primary key. Does not add the commerce batch entry to the database.
	 *
	 * @param commerceBatchEntryId the primary key for the new commerce batch entry
	 * @return the new commerce batch entry
	 */
	@Override
	public CommerceBatchEntry create(long commerceBatchEntryId) {
		CommerceBatchEntry commerceBatchEntry = new CommerceBatchEntryImpl();

		commerceBatchEntry.setNew(true);
		commerceBatchEntry.setPrimaryKey(commerceBatchEntryId);

		return commerceBatchEntry;
	}

	/**
	 * Removes the commerce batch entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceBatchEntryId the primary key of the commerce batch entry
	 * @return the commerce batch entry that was removed
	 * @throws NoSuchBatchEntryException if a commerce batch entry with the primary key could not be found
	 */
	@Override
	public CommerceBatchEntry remove(long commerceBatchEntryId)
		throws NoSuchBatchEntryException {
		return remove((Serializable)commerceBatchEntryId);
	}

	/**
	 * Removes the commerce batch entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce batch entry
	 * @return the commerce batch entry that was removed
	 * @throws NoSuchBatchEntryException if a commerce batch entry with the primary key could not be found
	 */
	@Override
	public CommerceBatchEntry remove(Serializable primaryKey)
		throws NoSuchBatchEntryException {
		Session session = null;

		try {
			session = openSession();

			CommerceBatchEntry commerceBatchEntry = (CommerceBatchEntry)session.get(CommerceBatchEntryImpl.class,
					primaryKey);

			if (commerceBatchEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBatchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commerceBatchEntry);
		}
		catch (NoSuchBatchEntryException nsee) {
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
	protected CommerceBatchEntry removeImpl(
		CommerceBatchEntry commerceBatchEntry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceBatchEntry)) {
				commerceBatchEntry = (CommerceBatchEntry)session.get(CommerceBatchEntryImpl.class,
						commerceBatchEntry.getPrimaryKeyObj());
			}

			if (commerceBatchEntry != null) {
				session.delete(commerceBatchEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceBatchEntry != null) {
			clearCache(commerceBatchEntry);
		}

		return commerceBatchEntry;
	}

	@Override
	public CommerceBatchEntry updateImpl(CommerceBatchEntry commerceBatchEntry) {
		boolean isNew = commerceBatchEntry.isNew();

		if (!(commerceBatchEntry instanceof CommerceBatchEntryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceBatchEntry.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(commerceBatchEntry);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceBatchEntry proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceBatchEntry implementation " +
				commerceBatchEntry.getClass());
		}

		CommerceBatchEntryModelImpl commerceBatchEntryModelImpl = (CommerceBatchEntryModelImpl)commerceBatchEntry;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceBatchEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceBatchEntry.setCreateDate(now);
			}
			else {
				commerceBatchEntry.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!commerceBatchEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceBatchEntry.setModifiedDate(now);
			}
			else {
				commerceBatchEntry.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceBatchEntry.isNew()) {
				session.save(commerceBatchEntry);

				commerceBatchEntry.setNew(false);
			}
			else {
				commerceBatchEntry = (CommerceBatchEntry)session.merge(commerceBatchEntry);
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

		entityCache.putResult(CommerceBatchEntryModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBatchEntryImpl.class, commerceBatchEntry.getPrimaryKey(),
			commerceBatchEntry, false);

		commerceBatchEntry.resetOriginalValues();

		return commerceBatchEntry;
	}

	/**
	 * Returns the commerce batch entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce batch entry
	 * @return the commerce batch entry
	 * @throws NoSuchBatchEntryException if a commerce batch entry with the primary key could not be found
	 */
	@Override
	public CommerceBatchEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBatchEntryException {
		CommerceBatchEntry commerceBatchEntry = fetchByPrimaryKey(primaryKey);

		if (commerceBatchEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBatchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commerceBatchEntry;
	}

	/**
	 * Returns the commerce batch entry with the primary key or throws a {@link NoSuchBatchEntryException} if it could not be found.
	 *
	 * @param commerceBatchEntryId the primary key of the commerce batch entry
	 * @return the commerce batch entry
	 * @throws NoSuchBatchEntryException if a commerce batch entry with the primary key could not be found
	 */
	@Override
	public CommerceBatchEntry findByPrimaryKey(long commerceBatchEntryId)
		throws NoSuchBatchEntryException {
		return findByPrimaryKey((Serializable)commerceBatchEntryId);
	}

	/**
	 * Returns the commerce batch entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce batch entry
	 * @return the commerce batch entry, or <code>null</code> if a commerce batch entry with the primary key could not be found
	 */
	@Override
	public CommerceBatchEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CommerceBatchEntryModelImpl.ENTITY_CACHE_ENABLED,
				CommerceBatchEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceBatchEntry commerceBatchEntry = (CommerceBatchEntry)serializable;

		if (commerceBatchEntry == null) {
			Session session = null;

			try {
				session = openSession();

				commerceBatchEntry = (CommerceBatchEntry)session.get(CommerceBatchEntryImpl.class,
						primaryKey);

				if (commerceBatchEntry != null) {
					cacheResult(commerceBatchEntry);
				}
				else {
					entityCache.putResult(CommerceBatchEntryModelImpl.ENTITY_CACHE_ENABLED,
						CommerceBatchEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommerceBatchEntryModelImpl.ENTITY_CACHE_ENABLED,
					CommerceBatchEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceBatchEntry;
	}

	/**
	 * Returns the commerce batch entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceBatchEntryId the primary key of the commerce batch entry
	 * @return the commerce batch entry, or <code>null</code> if a commerce batch entry with the primary key could not be found
	 */
	@Override
	public CommerceBatchEntry fetchByPrimaryKey(long commerceBatchEntryId) {
		return fetchByPrimaryKey((Serializable)commerceBatchEntryId);
	}

	@Override
	public Map<Serializable, CommerceBatchEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceBatchEntry> map = new HashMap<Serializable, CommerceBatchEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceBatchEntry commerceBatchEntry = fetchByPrimaryKey(primaryKey);

			if (commerceBatchEntry != null) {
				map.put(primaryKey, commerceBatchEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CommerceBatchEntryModelImpl.ENTITY_CACHE_ENABLED,
					CommerceBatchEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceBatchEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMMERCEBATCHENTRY_WHERE_PKS_IN);

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

			for (CommerceBatchEntry commerceBatchEntry : (List<CommerceBatchEntry>)q.list()) {
				map.put(commerceBatchEntry.getPrimaryKeyObj(),
					commerceBatchEntry);

				cacheResult(commerceBatchEntry);

				uncachedPrimaryKeys.remove(commerceBatchEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CommerceBatchEntryModelImpl.ENTITY_CACHE_ENABLED,
					CommerceBatchEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the commerce batch entries.
	 *
	 * @return the commerce batch entries
	 */
	@Override
	public List<CommerceBatchEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce batch entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce batch entries
	 * @param end the upper bound of the range of commerce batch entries (not inclusive)
	 * @return the range of commerce batch entries
	 */
	@Override
	public List<CommerceBatchEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce batch entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce batch entries
	 * @param end the upper bound of the range of commerce batch entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce batch entries
	 */
	@Override
	public List<CommerceBatchEntry> findAll(int start, int end,
		OrderByComparator<CommerceBatchEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce batch entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce batch entries
	 * @param end the upper bound of the range of commerce batch entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce batch entries
	 */
	@Override
	public List<CommerceBatchEntry> findAll(int start, int end,
		OrderByComparator<CommerceBatchEntry> orderByComparator,
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

		List<CommerceBatchEntry> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceBatchEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEBATCHENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEBATCHENTRY;

				if (pagination) {
					sql = sql.concat(CommerceBatchEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceBatchEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceBatchEntry>)QueryUtil.list(q,
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
	 * Removes all the commerce batch entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceBatchEntry commerceBatchEntry : findAll()) {
			remove(commerceBatchEntry);
		}
	}

	/**
	 * Returns the number of commerce batch entries.
	 *
	 * @return the number of commerce batch entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCEBATCHENTRY);

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
		return CommerceBatchEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce batch entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CommerceBatchEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_COMMERCEBATCHENTRY = "SELECT commerceBatchEntry FROM CommerceBatchEntry commerceBatchEntry";
	private static final String _SQL_SELECT_COMMERCEBATCHENTRY_WHERE_PKS_IN = "SELECT commerceBatchEntry FROM CommerceBatchEntry commerceBatchEntry WHERE commerceBatchEntryId IN (";
	private static final String _SQL_COUNT_COMMERCEBATCHENTRY = "SELECT COUNT(commerceBatchEntry) FROM CommerceBatchEntry commerceBatchEntry";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceBatchEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommerceBatchEntry exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CommerceBatchEntryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"key"
			});
}