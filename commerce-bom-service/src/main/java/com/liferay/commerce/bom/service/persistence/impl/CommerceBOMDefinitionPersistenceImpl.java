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

package com.liferay.commerce.bom.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.bom.exception.NoSuchBOMDefinitionException;
import com.liferay.commerce.bom.model.CommerceBOMDefinition;
import com.liferay.commerce.bom.model.impl.CommerceBOMDefinitionImpl;
import com.liferay.commerce.bom.model.impl.CommerceBOMDefinitionModelImpl;
import com.liferay.commerce.bom.service.persistence.CommerceBOMDefinitionPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
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
 * The persistence implementation for the commerce bom definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceBOMDefinitionPersistence
 * @see com.liferay.commerce.bom.service.persistence.CommerceBOMDefinitionUtil
 * @generated
 */
@ProviderType
public class CommerceBOMDefinitionPersistenceImpl extends BasePersistenceImpl<CommerceBOMDefinition>
	implements CommerceBOMDefinitionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommerceBOMDefinitionUtil} to access the commerce bom definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommerceBOMDefinitionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommerceBOMDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMDefinitionModelImpl.FINDER_CACHE_ENABLED,
			CommerceBOMDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommerceBOMDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMDefinitionModelImpl.FINDER_CACHE_ENABLED,
			CommerceBOMDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommerceBOMDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMMERCEBOMFOLDERID =
		new FinderPath(CommerceBOMDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMDefinitionModelImpl.FINDER_CACHE_ENABLED,
			CommerceBOMDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCommerceBOMFolderId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEBOMFOLDERID =
		new FinderPath(CommerceBOMDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMDefinitionModelImpl.FINDER_CACHE_ENABLED,
			CommerceBOMDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCommerceBOMFolderId", new String[] { Long.class.getName() },
			CommerceBOMDefinitionModelImpl.COMMERCEBOMFOLDERID_COLUMN_BITMASK |
			CommerceBOMDefinitionModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMMERCEBOMFOLDERID = new FinderPath(CommerceBOMDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceBOMFolderId", new String[] { Long.class.getName() });

	/**
	 * Returns all the commerce bom definitions where commerceBOMFolderId = &#63;.
	 *
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @return the matching commerce bom definitions
	 */
	@Override
	public List<CommerceBOMDefinition> findByCommerceBOMFolderId(
		long commerceBOMFolderId) {
		return findByCommerceBOMFolderId(commerceBOMFolderId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce bom definitions where commerceBOMFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @param start the lower bound of the range of commerce bom definitions
	 * @param end the upper bound of the range of commerce bom definitions (not inclusive)
	 * @return the range of matching commerce bom definitions
	 */
	@Override
	public List<CommerceBOMDefinition> findByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end) {
		return findByCommerceBOMFolderId(commerceBOMFolderId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce bom definitions where commerceBOMFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @param start the lower bound of the range of commerce bom definitions
	 * @param end the upper bound of the range of commerce bom definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce bom definitions
	 */
	@Override
	public List<CommerceBOMDefinition> findByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end,
		OrderByComparator<CommerceBOMDefinition> orderByComparator) {
		return findByCommerceBOMFolderId(commerceBOMFolderId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce bom definitions where commerceBOMFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @param start the lower bound of the range of commerce bom definitions
	 * @param end the upper bound of the range of commerce bom definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce bom definitions
	 */
	@Override
	public List<CommerceBOMDefinition> findByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end,
		OrderByComparator<CommerceBOMDefinition> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEBOMFOLDERID;
			finderArgs = new Object[] { commerceBOMFolderId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMMERCEBOMFOLDERID;
			finderArgs = new Object[] {
					commerceBOMFolderId,
					
					start, end, orderByComparator
				};
		}

		List<CommerceBOMDefinition> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceBOMDefinition>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceBOMDefinition commerceBOMDefinition : list) {
					if ((commerceBOMFolderId != commerceBOMDefinition.getCommerceBOMFolderId())) {
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

			query.append(_SQL_SELECT_COMMERCEBOMDEFINITION_WHERE);

			query.append(_FINDER_COLUMN_COMMERCEBOMFOLDERID_COMMERCEBOMFOLDERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceBOMDefinitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceBOMFolderId);

				if (!pagination) {
					list = (List<CommerceBOMDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceBOMDefinition>)QueryUtil.list(q,
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
	 * Returns the first commerce bom definition in the ordered set where commerceBOMFolderId = &#63;.
	 *
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce bom definition
	 * @throws NoSuchBOMDefinitionException if a matching commerce bom definition could not be found
	 */
	@Override
	public CommerceBOMDefinition findByCommerceBOMFolderId_First(
		long commerceBOMFolderId,
		OrderByComparator<CommerceBOMDefinition> orderByComparator)
		throws NoSuchBOMDefinitionException {
		CommerceBOMDefinition commerceBOMDefinition = fetchByCommerceBOMFolderId_First(commerceBOMFolderId,
				orderByComparator);

		if (commerceBOMDefinition != null) {
			return commerceBOMDefinition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceBOMFolderId=");
		msg.append(commerceBOMFolderId);

		msg.append("}");

		throw new NoSuchBOMDefinitionException(msg.toString());
	}

	/**
	 * Returns the first commerce bom definition in the ordered set where commerceBOMFolderId = &#63;.
	 *
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce bom definition, or <code>null</code> if a matching commerce bom definition could not be found
	 */
	@Override
	public CommerceBOMDefinition fetchByCommerceBOMFolderId_First(
		long commerceBOMFolderId,
		OrderByComparator<CommerceBOMDefinition> orderByComparator) {
		List<CommerceBOMDefinition> list = findByCommerceBOMFolderId(commerceBOMFolderId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce bom definition in the ordered set where commerceBOMFolderId = &#63;.
	 *
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce bom definition
	 * @throws NoSuchBOMDefinitionException if a matching commerce bom definition could not be found
	 */
	@Override
	public CommerceBOMDefinition findByCommerceBOMFolderId_Last(
		long commerceBOMFolderId,
		OrderByComparator<CommerceBOMDefinition> orderByComparator)
		throws NoSuchBOMDefinitionException {
		CommerceBOMDefinition commerceBOMDefinition = fetchByCommerceBOMFolderId_Last(commerceBOMFolderId,
				orderByComparator);

		if (commerceBOMDefinition != null) {
			return commerceBOMDefinition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceBOMFolderId=");
		msg.append(commerceBOMFolderId);

		msg.append("}");

		throw new NoSuchBOMDefinitionException(msg.toString());
	}

	/**
	 * Returns the last commerce bom definition in the ordered set where commerceBOMFolderId = &#63;.
	 *
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce bom definition, or <code>null</code> if a matching commerce bom definition could not be found
	 */
	@Override
	public CommerceBOMDefinition fetchByCommerceBOMFolderId_Last(
		long commerceBOMFolderId,
		OrderByComparator<CommerceBOMDefinition> orderByComparator) {
		int count = countByCommerceBOMFolderId(commerceBOMFolderId);

		if (count == 0) {
			return null;
		}

		List<CommerceBOMDefinition> list = findByCommerceBOMFolderId(commerceBOMFolderId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce bom definitions before and after the current commerce bom definition in the ordered set where commerceBOMFolderId = &#63;.
	 *
	 * @param commerceBOMDefinitionId the primary key of the current commerce bom definition
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce bom definition
	 * @throws NoSuchBOMDefinitionException if a commerce bom definition with the primary key could not be found
	 */
	@Override
	public CommerceBOMDefinition[] findByCommerceBOMFolderId_PrevAndNext(
		long commerceBOMDefinitionId, long commerceBOMFolderId,
		OrderByComparator<CommerceBOMDefinition> orderByComparator)
		throws NoSuchBOMDefinitionException {
		CommerceBOMDefinition commerceBOMDefinition = findByPrimaryKey(commerceBOMDefinitionId);

		Session session = null;

		try {
			session = openSession();

			CommerceBOMDefinition[] array = new CommerceBOMDefinitionImpl[3];

			array[0] = getByCommerceBOMFolderId_PrevAndNext(session,
					commerceBOMDefinition, commerceBOMFolderId,
					orderByComparator, true);

			array[1] = commerceBOMDefinition;

			array[2] = getByCommerceBOMFolderId_PrevAndNext(session,
					commerceBOMDefinition, commerceBOMFolderId,
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

	protected CommerceBOMDefinition getByCommerceBOMFolderId_PrevAndNext(
		Session session, CommerceBOMDefinition commerceBOMDefinition,
		long commerceBOMFolderId,
		OrderByComparator<CommerceBOMDefinition> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEBOMDEFINITION_WHERE);

		query.append(_FINDER_COLUMN_COMMERCEBOMFOLDERID_COMMERCEBOMFOLDERID_2);

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
			query.append(CommerceBOMDefinitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceBOMFolderId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceBOMDefinition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceBOMDefinition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the commerce bom definitions that the user has permission to view where commerceBOMFolderId = &#63;.
	 *
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @return the matching commerce bom definitions that the user has permission to view
	 */
	@Override
	public List<CommerceBOMDefinition> filterFindByCommerceBOMFolderId(
		long commerceBOMFolderId) {
		return filterFindByCommerceBOMFolderId(commerceBOMFolderId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce bom definitions that the user has permission to view where commerceBOMFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @param start the lower bound of the range of commerce bom definitions
	 * @param end the upper bound of the range of commerce bom definitions (not inclusive)
	 * @return the range of matching commerce bom definitions that the user has permission to view
	 */
	@Override
	public List<CommerceBOMDefinition> filterFindByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end) {
		return filterFindByCommerceBOMFolderId(commerceBOMFolderId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the commerce bom definitions that the user has permissions to view where commerceBOMFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @param start the lower bound of the range of commerce bom definitions
	 * @param end the upper bound of the range of commerce bom definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce bom definitions that the user has permission to view
	 */
	@Override
	public List<CommerceBOMDefinition> filterFindByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end,
		OrderByComparator<CommerceBOMDefinition> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByCommerceBOMFolderId(commerceBOMFolderId, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEBOMDEFINITION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_COMMERCEBOMDEFINITION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMMERCEBOMFOLDERID_COMMERCEBOMFOLDERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEBOMDEFINITION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CommerceBOMDefinitionModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceBOMDefinitionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceBOMDefinition.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS,
					CommerceBOMDefinitionImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE,
					CommerceBOMDefinitionImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(commerceBOMFolderId);

			return (List<CommerceBOMDefinition>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the commerce bom definitions before and after the current commerce bom definition in the ordered set of commerce bom definitions that the user has permission to view where commerceBOMFolderId = &#63;.
	 *
	 * @param commerceBOMDefinitionId the primary key of the current commerce bom definition
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce bom definition
	 * @throws NoSuchBOMDefinitionException if a commerce bom definition with the primary key could not be found
	 */
	@Override
	public CommerceBOMDefinition[] filterFindByCommerceBOMFolderId_PrevAndNext(
		long commerceBOMDefinitionId, long commerceBOMFolderId,
		OrderByComparator<CommerceBOMDefinition> orderByComparator)
		throws NoSuchBOMDefinitionException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByCommerceBOMFolderId_PrevAndNext(commerceBOMDefinitionId,
				commerceBOMFolderId, orderByComparator);
		}

		CommerceBOMDefinition commerceBOMDefinition = findByPrimaryKey(commerceBOMDefinitionId);

		Session session = null;

		try {
			session = openSession();

			CommerceBOMDefinition[] array = new CommerceBOMDefinitionImpl[3];

			array[0] = filterGetByCommerceBOMFolderId_PrevAndNext(session,
					commerceBOMDefinition, commerceBOMFolderId,
					orderByComparator, true);

			array[1] = commerceBOMDefinition;

			array[2] = filterGetByCommerceBOMFolderId_PrevAndNext(session,
					commerceBOMDefinition, commerceBOMFolderId,
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

	protected CommerceBOMDefinition filterGetByCommerceBOMFolderId_PrevAndNext(
		Session session, CommerceBOMDefinition commerceBOMDefinition,
		long commerceBOMFolderId,
		OrderByComparator<CommerceBOMDefinition> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEBOMDEFINITION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_COMMERCEBOMDEFINITION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMMERCEBOMFOLDERID_COMMERCEBOMFOLDERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEBOMDEFINITION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CommerceBOMDefinitionModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceBOMDefinitionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceBOMDefinition.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CommerceBOMDefinitionImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CommerceBOMDefinitionImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceBOMFolderId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceBOMDefinition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceBOMDefinition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce bom definitions where commerceBOMFolderId = &#63; from the database.
	 *
	 * @param commerceBOMFolderId the commerce bom folder ID
	 */
	@Override
	public void removeByCommerceBOMFolderId(long commerceBOMFolderId) {
		for (CommerceBOMDefinition commerceBOMDefinition : findByCommerceBOMFolderId(
				commerceBOMFolderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(commerceBOMDefinition);
		}
	}

	/**
	 * Returns the number of commerce bom definitions where commerceBOMFolderId = &#63;.
	 *
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @return the number of matching commerce bom definitions
	 */
	@Override
	public int countByCommerceBOMFolderId(long commerceBOMFolderId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMMERCEBOMFOLDERID;

		Object[] finderArgs = new Object[] { commerceBOMFolderId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEBOMDEFINITION_WHERE);

			query.append(_FINDER_COLUMN_COMMERCEBOMFOLDERID_COMMERCEBOMFOLDERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceBOMFolderId);

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

	/**
	 * Returns the number of commerce bom definitions that the user has permission to view where commerceBOMFolderId = &#63;.
	 *
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @return the number of matching commerce bom definitions that the user has permission to view
	 */
	@Override
	public int filterCountByCommerceBOMFolderId(long commerceBOMFolderId) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByCommerceBOMFolderId(commerceBOMFolderId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_COMMERCEBOMDEFINITION_WHERE);

		query.append(_FINDER_COLUMN_COMMERCEBOMFOLDERID_COMMERCEBOMFOLDERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceBOMDefinition.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(commerceBOMFolderId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_COMMERCEBOMFOLDERID_COMMERCEBOMFOLDERID_2 =
		"commerceBOMDefinition.commerceBOMFolderId = ?";

	public CommerceBOMDefinitionPersistenceImpl() {
		setModelClass(CommerceBOMDefinition.class);
	}

	/**
	 * Caches the commerce bom definition in the entity cache if it is enabled.
	 *
	 * @param commerceBOMDefinition the commerce bom definition
	 */
	@Override
	public void cacheResult(CommerceBOMDefinition commerceBOMDefinition) {
		entityCache.putResult(CommerceBOMDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMDefinitionImpl.class,
			commerceBOMDefinition.getPrimaryKey(), commerceBOMDefinition);

		commerceBOMDefinition.resetOriginalValues();
	}

	/**
	 * Caches the commerce bom definitions in the entity cache if it is enabled.
	 *
	 * @param commerceBOMDefinitions the commerce bom definitions
	 */
	@Override
	public void cacheResult(List<CommerceBOMDefinition> commerceBOMDefinitions) {
		for (CommerceBOMDefinition commerceBOMDefinition : commerceBOMDefinitions) {
			if (entityCache.getResult(
						CommerceBOMDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						CommerceBOMDefinitionImpl.class,
						commerceBOMDefinition.getPrimaryKey()) == null) {
				cacheResult(commerceBOMDefinition);
			}
			else {
				commerceBOMDefinition.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce bom definitions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceBOMDefinitionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce bom definition.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceBOMDefinition commerceBOMDefinition) {
		entityCache.removeResult(CommerceBOMDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMDefinitionImpl.class,
			commerceBOMDefinition.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CommerceBOMDefinition> commerceBOMDefinitions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceBOMDefinition commerceBOMDefinition : commerceBOMDefinitions) {
			entityCache.removeResult(CommerceBOMDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				CommerceBOMDefinitionImpl.class,
				commerceBOMDefinition.getPrimaryKey());
		}
	}

	/**
	 * Creates a new commerce bom definition with the primary key. Does not add the commerce bom definition to the database.
	 *
	 * @param commerceBOMDefinitionId the primary key for the new commerce bom definition
	 * @return the new commerce bom definition
	 */
	@Override
	public CommerceBOMDefinition create(long commerceBOMDefinitionId) {
		CommerceBOMDefinition commerceBOMDefinition = new CommerceBOMDefinitionImpl();

		commerceBOMDefinition.setNew(true);
		commerceBOMDefinition.setPrimaryKey(commerceBOMDefinitionId);

		commerceBOMDefinition.setCompanyId(companyProvider.getCompanyId());

		return commerceBOMDefinition;
	}

	/**
	 * Removes the commerce bom definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceBOMDefinitionId the primary key of the commerce bom definition
	 * @return the commerce bom definition that was removed
	 * @throws NoSuchBOMDefinitionException if a commerce bom definition with the primary key could not be found
	 */
	@Override
	public CommerceBOMDefinition remove(long commerceBOMDefinitionId)
		throws NoSuchBOMDefinitionException {
		return remove((Serializable)commerceBOMDefinitionId);
	}

	/**
	 * Removes the commerce bom definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce bom definition
	 * @return the commerce bom definition that was removed
	 * @throws NoSuchBOMDefinitionException if a commerce bom definition with the primary key could not be found
	 */
	@Override
	public CommerceBOMDefinition remove(Serializable primaryKey)
		throws NoSuchBOMDefinitionException {
		Session session = null;

		try {
			session = openSession();

			CommerceBOMDefinition commerceBOMDefinition = (CommerceBOMDefinition)session.get(CommerceBOMDefinitionImpl.class,
					primaryKey);

			if (commerceBOMDefinition == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBOMDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commerceBOMDefinition);
		}
		catch (NoSuchBOMDefinitionException nsee) {
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
	protected CommerceBOMDefinition removeImpl(
		CommerceBOMDefinition commerceBOMDefinition) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceBOMDefinition)) {
				commerceBOMDefinition = (CommerceBOMDefinition)session.get(CommerceBOMDefinitionImpl.class,
						commerceBOMDefinition.getPrimaryKeyObj());
			}

			if (commerceBOMDefinition != null) {
				session.delete(commerceBOMDefinition);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceBOMDefinition != null) {
			clearCache(commerceBOMDefinition);
		}

		return commerceBOMDefinition;
	}

	@Override
	public CommerceBOMDefinition updateImpl(
		CommerceBOMDefinition commerceBOMDefinition) {
		boolean isNew = commerceBOMDefinition.isNew();

		if (!(commerceBOMDefinition instanceof CommerceBOMDefinitionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceBOMDefinition.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(commerceBOMDefinition);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceBOMDefinition proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceBOMDefinition implementation " +
				commerceBOMDefinition.getClass());
		}

		CommerceBOMDefinitionModelImpl commerceBOMDefinitionModelImpl = (CommerceBOMDefinitionModelImpl)commerceBOMDefinition;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceBOMDefinition.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceBOMDefinition.setCreateDate(now);
			}
			else {
				commerceBOMDefinition.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!commerceBOMDefinitionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceBOMDefinition.setModifiedDate(now);
			}
			else {
				commerceBOMDefinition.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceBOMDefinition.isNew()) {
				session.save(commerceBOMDefinition);

				commerceBOMDefinition.setNew(false);
			}
			else {
				commerceBOMDefinition = (CommerceBOMDefinition)session.merge(commerceBOMDefinition);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceBOMDefinitionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					commerceBOMDefinitionModelImpl.getCommerceBOMFolderId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEBOMFOLDERID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEBOMFOLDERID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((commerceBOMDefinitionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEBOMFOLDERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceBOMDefinitionModelImpl.getOriginalCommerceBOMFolderId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEBOMFOLDERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEBOMFOLDERID,
					args);

				args = new Object[] {
						commerceBOMDefinitionModelImpl.getCommerceBOMFolderId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEBOMFOLDERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEBOMFOLDERID,
					args);
			}
		}

		entityCache.putResult(CommerceBOMDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMDefinitionImpl.class,
			commerceBOMDefinition.getPrimaryKey(), commerceBOMDefinition, false);

		commerceBOMDefinition.resetOriginalValues();

		return commerceBOMDefinition;
	}

	/**
	 * Returns the commerce bom definition with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce bom definition
	 * @return the commerce bom definition
	 * @throws NoSuchBOMDefinitionException if a commerce bom definition with the primary key could not be found
	 */
	@Override
	public CommerceBOMDefinition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBOMDefinitionException {
		CommerceBOMDefinition commerceBOMDefinition = fetchByPrimaryKey(primaryKey);

		if (commerceBOMDefinition == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBOMDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commerceBOMDefinition;
	}

	/**
	 * Returns the commerce bom definition with the primary key or throws a {@link NoSuchBOMDefinitionException} if it could not be found.
	 *
	 * @param commerceBOMDefinitionId the primary key of the commerce bom definition
	 * @return the commerce bom definition
	 * @throws NoSuchBOMDefinitionException if a commerce bom definition with the primary key could not be found
	 */
	@Override
	public CommerceBOMDefinition findByPrimaryKey(long commerceBOMDefinitionId)
		throws NoSuchBOMDefinitionException {
		return findByPrimaryKey((Serializable)commerceBOMDefinitionId);
	}

	/**
	 * Returns the commerce bom definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce bom definition
	 * @return the commerce bom definition, or <code>null</code> if a commerce bom definition with the primary key could not be found
	 */
	@Override
	public CommerceBOMDefinition fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CommerceBOMDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				CommerceBOMDefinitionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceBOMDefinition commerceBOMDefinition = (CommerceBOMDefinition)serializable;

		if (commerceBOMDefinition == null) {
			Session session = null;

			try {
				session = openSession();

				commerceBOMDefinition = (CommerceBOMDefinition)session.get(CommerceBOMDefinitionImpl.class,
						primaryKey);

				if (commerceBOMDefinition != null) {
					cacheResult(commerceBOMDefinition);
				}
				else {
					entityCache.putResult(CommerceBOMDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						CommerceBOMDefinitionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommerceBOMDefinitionModelImpl.ENTITY_CACHE_ENABLED,
					CommerceBOMDefinitionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceBOMDefinition;
	}

	/**
	 * Returns the commerce bom definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceBOMDefinitionId the primary key of the commerce bom definition
	 * @return the commerce bom definition, or <code>null</code> if a commerce bom definition with the primary key could not be found
	 */
	@Override
	public CommerceBOMDefinition fetchByPrimaryKey(long commerceBOMDefinitionId) {
		return fetchByPrimaryKey((Serializable)commerceBOMDefinitionId);
	}

	@Override
	public Map<Serializable, CommerceBOMDefinition> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceBOMDefinition> map = new HashMap<Serializable, CommerceBOMDefinition>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceBOMDefinition commerceBOMDefinition = fetchByPrimaryKey(primaryKey);

			if (commerceBOMDefinition != null) {
				map.put(primaryKey, commerceBOMDefinition);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CommerceBOMDefinitionModelImpl.ENTITY_CACHE_ENABLED,
					CommerceBOMDefinitionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceBOMDefinition)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMMERCEBOMDEFINITION_WHERE_PKS_IN);

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

			for (CommerceBOMDefinition commerceBOMDefinition : (List<CommerceBOMDefinition>)q.list()) {
				map.put(commerceBOMDefinition.getPrimaryKeyObj(),
					commerceBOMDefinition);

				cacheResult(commerceBOMDefinition);

				uncachedPrimaryKeys.remove(commerceBOMDefinition.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CommerceBOMDefinitionModelImpl.ENTITY_CACHE_ENABLED,
					CommerceBOMDefinitionImpl.class, primaryKey, nullModel);
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
	 * Returns all the commerce bom definitions.
	 *
	 * @return the commerce bom definitions
	 */
	@Override
	public List<CommerceBOMDefinition> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce bom definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce bom definitions
	 * @param end the upper bound of the range of commerce bom definitions (not inclusive)
	 * @return the range of commerce bom definitions
	 */
	@Override
	public List<CommerceBOMDefinition> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce bom definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce bom definitions
	 * @param end the upper bound of the range of commerce bom definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce bom definitions
	 */
	@Override
	public List<CommerceBOMDefinition> findAll(int start, int end,
		OrderByComparator<CommerceBOMDefinition> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce bom definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce bom definitions
	 * @param end the upper bound of the range of commerce bom definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce bom definitions
	 */
	@Override
	public List<CommerceBOMDefinition> findAll(int start, int end,
		OrderByComparator<CommerceBOMDefinition> orderByComparator,
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

		List<CommerceBOMDefinition> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceBOMDefinition>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEBOMDEFINITION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEBOMDEFINITION;

				if (pagination) {
					sql = sql.concat(CommerceBOMDefinitionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceBOMDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceBOMDefinition>)QueryUtil.list(q,
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
	 * Removes all the commerce bom definitions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceBOMDefinition commerceBOMDefinition : findAll()) {
			remove(commerceBOMDefinition);
		}
	}

	/**
	 * Returns the number of commerce bom definitions.
	 *
	 * @return the number of commerce bom definitions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCEBOMDEFINITION);

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
		return CommerceBOMDefinitionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce bom definition persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CommerceBOMDefinitionImpl.class.getName());
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
	private static final String _SQL_SELECT_COMMERCEBOMDEFINITION = "SELECT commerceBOMDefinition FROM CommerceBOMDefinition commerceBOMDefinition";
	private static final String _SQL_SELECT_COMMERCEBOMDEFINITION_WHERE_PKS_IN = "SELECT commerceBOMDefinition FROM CommerceBOMDefinition commerceBOMDefinition WHERE commerceBOMDefinitionId IN (";
	private static final String _SQL_SELECT_COMMERCEBOMDEFINITION_WHERE = "SELECT commerceBOMDefinition FROM CommerceBOMDefinition commerceBOMDefinition WHERE ";
	private static final String _SQL_COUNT_COMMERCEBOMDEFINITION = "SELECT COUNT(commerceBOMDefinition) FROM CommerceBOMDefinition commerceBOMDefinition";
	private static final String _SQL_COUNT_COMMERCEBOMDEFINITION_WHERE = "SELECT COUNT(commerceBOMDefinition) FROM CommerceBOMDefinition commerceBOMDefinition WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "commerceBOMDefinition.commerceBOMDefinitionId";
	private static final String _FILTER_SQL_SELECT_COMMERCEBOMDEFINITION_WHERE = "SELECT DISTINCT {commerceBOMDefinition.*} FROM CommerceBOMDefinition commerceBOMDefinition WHERE ";
	private static final String _FILTER_SQL_SELECT_COMMERCEBOMDEFINITION_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {CommerceBOMDefinition.*} FROM (SELECT DISTINCT commerceBOMDefinition.commerceBOMDefinitionId FROM CommerceBOMDefinition commerceBOMDefinition WHERE ";
	private static final String _FILTER_SQL_SELECT_COMMERCEBOMDEFINITION_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN CommerceBOMDefinition ON TEMP_TABLE.commerceBOMDefinitionId = CommerceBOMDefinition.commerceBOMDefinitionId";
	private static final String _FILTER_SQL_COUNT_COMMERCEBOMDEFINITION_WHERE = "SELECT COUNT(DISTINCT commerceBOMDefinition.commerceBOMDefinitionId) AS COUNT_VALUE FROM CommerceBOMDefinition commerceBOMDefinition WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "commerceBOMDefinition";
	private static final String _FILTER_ENTITY_TABLE = "CommerceBOMDefinition";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceBOMDefinition.";
	private static final String _ORDER_BY_ENTITY_TABLE = "CommerceBOMDefinition.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommerceBOMDefinition exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CommerceBOMDefinition exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CommerceBOMDefinitionPersistenceImpl.class);
}