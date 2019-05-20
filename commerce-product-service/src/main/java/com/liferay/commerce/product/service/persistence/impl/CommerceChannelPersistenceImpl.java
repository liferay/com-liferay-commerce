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

import com.liferay.commerce.product.exception.NoSuchChannelException;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.model.impl.CommerceChannelImpl;
import com.liferay.commerce.product.model.impl.CommerceChannelModelImpl;
import com.liferay.commerce.product.service.persistence.CommerceChannelPersistence;

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
 * The persistence implementation for the commerce channel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CommerceChannelPersistence
 * @see com.liferay.commerce.product.service.persistence.CommerceChannelUtil
 * @generated
 */
@ProviderType
public class CommerceChannelPersistenceImpl extends BasePersistenceImpl<CommerceChannel>
	implements CommerceChannelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommerceChannelUtil} to access the commerce channel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommerceChannelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelModelImpl.FINDER_CACHE_ENABLED,
			CommerceChannelImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelModelImpl.FINDER_CACHE_ENABLED,
			CommerceChannelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CommerceChannelPersistenceImpl() {
		setModelClass(CommerceChannel.class);

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
	 * Caches the commerce channel in the entity cache if it is enabled.
	 *
	 * @param commerceChannel the commerce channel
	 */
	@Override
	public void cacheResult(CommerceChannel commerceChannel) {
		entityCache.putResult(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelImpl.class, commerceChannel.getPrimaryKey(),
			commerceChannel);

		commerceChannel.resetOriginalValues();
	}

	/**
	 * Caches the commerce channels in the entity cache if it is enabled.
	 *
	 * @param commerceChannels the commerce channels
	 */
	@Override
	public void cacheResult(List<CommerceChannel> commerceChannels) {
		for (CommerceChannel commerceChannel : commerceChannels) {
			if (entityCache.getResult(
						CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
						CommerceChannelImpl.class,
						commerceChannel.getPrimaryKey()) == null) {
				cacheResult(commerceChannel);
			}
			else {
				commerceChannel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce channels.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceChannelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce channel.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceChannel commerceChannel) {
		entityCache.removeResult(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelImpl.class, commerceChannel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CommerceChannel> commerceChannels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceChannel commerceChannel : commerceChannels) {
			entityCache.removeResult(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
				CommerceChannelImpl.class, commerceChannel.getPrimaryKey());
		}
	}

	/**
	 * Creates a new commerce channel with the primary key. Does not add the commerce channel to the database.
	 *
	 * @param commerceChannelId the primary key for the new commerce channel
	 * @return the new commerce channel
	 */
	@Override
	public CommerceChannel create(long commerceChannelId) {
		CommerceChannel commerceChannel = new CommerceChannelImpl();

		commerceChannel.setNew(true);
		commerceChannel.setPrimaryKey(commerceChannelId);

		commerceChannel.setCompanyId(companyProvider.getCompanyId());

		return commerceChannel;
	}

	/**
	 * Removes the commerce channel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceChannelId the primary key of the commerce channel
	 * @return the commerce channel that was removed
	 * @throws NoSuchChannelException if a commerce channel with the primary key could not be found
	 */
	@Override
	public CommerceChannel remove(long commerceChannelId)
		throws NoSuchChannelException {
		return remove((Serializable)commerceChannelId);
	}

	/**
	 * Removes the commerce channel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce channel
	 * @return the commerce channel that was removed
	 * @throws NoSuchChannelException if a commerce channel with the primary key could not be found
	 */
	@Override
	public CommerceChannel remove(Serializable primaryKey)
		throws NoSuchChannelException {
		Session session = null;

		try {
			session = openSession();

			CommerceChannel commerceChannel = (CommerceChannel)session.get(CommerceChannelImpl.class,
					primaryKey);

			if (commerceChannel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChannelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commerceChannel);
		}
		catch (NoSuchChannelException nsee) {
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
	protected CommerceChannel removeImpl(CommerceChannel commerceChannel) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceChannel)) {
				commerceChannel = (CommerceChannel)session.get(CommerceChannelImpl.class,
						commerceChannel.getPrimaryKeyObj());
			}

			if (commerceChannel != null) {
				session.delete(commerceChannel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceChannel != null) {
			clearCache(commerceChannel);
		}

		return commerceChannel;
	}

	@Override
	public CommerceChannel updateImpl(CommerceChannel commerceChannel) {
		boolean isNew = commerceChannel.isNew();

		if (!(commerceChannel instanceof CommerceChannelModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceChannel.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(commerceChannel);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceChannel proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceChannel implementation " +
				commerceChannel.getClass());
		}

		CommerceChannelModelImpl commerceChannelModelImpl = (CommerceChannelModelImpl)commerceChannel;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceChannel.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceChannel.setCreateDate(now);
			}
			else {
				commerceChannel.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!commerceChannelModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceChannel.setModifiedDate(now);
			}
			else {
				commerceChannel.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceChannel.isNew()) {
				session.save(commerceChannel);

				commerceChannel.setNew(false);
			}
			else {
				commerceChannel = (CommerceChannel)session.merge(commerceChannel);
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

		entityCache.putResult(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelImpl.class, commerceChannel.getPrimaryKey(),
			commerceChannel, false);

		commerceChannel.resetOriginalValues();

		return commerceChannel;
	}

	/**
	 * Returns the commerce channel with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce channel
	 * @return the commerce channel
	 * @throws NoSuchChannelException if a commerce channel with the primary key could not be found
	 */
	@Override
	public CommerceChannel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChannelException {
		CommerceChannel commerceChannel = fetchByPrimaryKey(primaryKey);

		if (commerceChannel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChannelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commerceChannel;
	}

	/**
	 * Returns the commerce channel with the primary key or throws a {@link NoSuchChannelException} if it could not be found.
	 *
	 * @param commerceChannelId the primary key of the commerce channel
	 * @return the commerce channel
	 * @throws NoSuchChannelException if a commerce channel with the primary key could not be found
	 */
	@Override
	public CommerceChannel findByPrimaryKey(long commerceChannelId)
		throws NoSuchChannelException {
		return findByPrimaryKey((Serializable)commerceChannelId);
	}

	/**
	 * Returns the commerce channel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce channel
	 * @return the commerce channel, or <code>null</code> if a commerce channel with the primary key could not be found
	 */
	@Override
	public CommerceChannel fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
				CommerceChannelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceChannel commerceChannel = (CommerceChannel)serializable;

		if (commerceChannel == null) {
			Session session = null;

			try {
				session = openSession();

				commerceChannel = (CommerceChannel)session.get(CommerceChannelImpl.class,
						primaryKey);

				if (commerceChannel != null) {
					cacheResult(commerceChannel);
				}
				else {
					entityCache.putResult(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
						CommerceChannelImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
					CommerceChannelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceChannel;
	}

	/**
	 * Returns the commerce channel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceChannelId the primary key of the commerce channel
	 * @return the commerce channel, or <code>null</code> if a commerce channel with the primary key could not be found
	 */
	@Override
	public CommerceChannel fetchByPrimaryKey(long commerceChannelId) {
		return fetchByPrimaryKey((Serializable)commerceChannelId);
	}

	@Override
	public Map<Serializable, CommerceChannel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceChannel> map = new HashMap<Serializable, CommerceChannel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceChannel commerceChannel = fetchByPrimaryKey(primaryKey);

			if (commerceChannel != null) {
				map.put(primaryKey, commerceChannel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
					CommerceChannelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceChannel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMMERCECHANNEL_WHERE_PKS_IN);

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

			for (CommerceChannel commerceChannel : (List<CommerceChannel>)q.list()) {
				map.put(commerceChannel.getPrimaryKeyObj(), commerceChannel);

				cacheResult(commerceChannel);

				uncachedPrimaryKeys.remove(commerceChannel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CommerceChannelModelImpl.ENTITY_CACHE_ENABLED,
					CommerceChannelImpl.class, primaryKey, nullModel);
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
	 * Returns all the commerce channels.
	 *
	 * @return the commerce channels
	 */
	@Override
	public List<CommerceChannel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce channels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @return the range of commerce channels
	 */
	@Override
	public List<CommerceChannel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce channels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce channels
	 */
	@Override
	public List<CommerceChannel> findAll(int start, int end,
		OrderByComparator<CommerceChannel> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce channels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce channels
	 */
	@Override
	public List<CommerceChannel> findAll(int start, int end,
		OrderByComparator<CommerceChannel> orderByComparator,
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

		List<CommerceChannel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceChannel>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCECHANNEL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCECHANNEL;

				if (pagination) {
					sql = sql.concat(CommerceChannelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceChannel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceChannel>)QueryUtil.list(q,
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
	 * Removes all the commerce channels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceChannel commerceChannel : findAll()) {
			remove(commerceChannel);
		}
	}

	/**
	 * Returns the number of commerce channels.
	 *
	 * @return the number of commerce channels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCECHANNEL);

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
		return CommerceChannelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce channel persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CommerceChannelImpl.class.getName());
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
	private static final String _SQL_SELECT_COMMERCECHANNEL = "SELECT commerceChannel FROM CommerceChannel commerceChannel";
	private static final String _SQL_SELECT_COMMERCECHANNEL_WHERE_PKS_IN = "SELECT commerceChannel FROM CommerceChannel commerceChannel WHERE commerceChannelId IN (";
	private static final String _SQL_COUNT_COMMERCECHANNEL = "SELECT COUNT(commerceChannel) FROM CommerceChannel commerceChannel";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceChannel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommerceChannel exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CommerceChannelPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}