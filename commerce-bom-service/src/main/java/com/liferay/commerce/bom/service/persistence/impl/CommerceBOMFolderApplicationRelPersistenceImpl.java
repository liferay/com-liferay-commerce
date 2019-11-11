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

import com.liferay.commerce.bom.exception.NoSuchBOMFolderApplicationRelException;
import com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel;
import com.liferay.commerce.bom.model.impl.CommerceBOMFolderApplicationRelImpl;
import com.liferay.commerce.bom.model.impl.CommerceBOMFolderApplicationRelModelImpl;
import com.liferay.commerce.bom.service.persistence.CommerceBOMFolderApplicationRelPersistence;

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
 * The persistence implementation for the commerce bom folder application rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceBOMFolderApplicationRelPersistence
 * @see com.liferay.commerce.bom.service.persistence.CommerceBOMFolderApplicationRelUtil
 * @generated
 */
@ProviderType
public class CommerceBOMFolderApplicationRelPersistenceImpl
	extends BasePersistenceImpl<CommerceBOMFolderApplicationRel>
	implements CommerceBOMFolderApplicationRelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommerceBOMFolderApplicationRelUtil} to access the commerce bom folder application rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommerceBOMFolderApplicationRelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommerceBOMFolderApplicationRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMFolderApplicationRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceBOMFolderApplicationRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommerceBOMFolderApplicationRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMFolderApplicationRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceBOMFolderApplicationRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommerceBOMFolderApplicationRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMFolderApplicationRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMMERCEBOMFOLDERID =
		new FinderPath(CommerceBOMFolderApplicationRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMFolderApplicationRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceBOMFolderApplicationRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCommerceBOMFolderId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEBOMFOLDERID =
		new FinderPath(CommerceBOMFolderApplicationRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMFolderApplicationRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceBOMFolderApplicationRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCommerceBOMFolderId", new String[] { Long.class.getName() },
			CommerceBOMFolderApplicationRelModelImpl.COMMERCEBOMFOLDERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMMERCEBOMFOLDERID = new FinderPath(CommerceBOMFolderApplicationRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMFolderApplicationRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceBOMFolderId", new String[] { Long.class.getName() });

	/**
	 * Returns all the commerce bom folder application rels where commerceBOMFolderId = &#63;.
	 *
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @return the matching commerce bom folder application rels
	 */
	@Override
	public List<CommerceBOMFolderApplicationRel> findByCommerceBOMFolderId(
		long commerceBOMFolderId) {
		return findByCommerceBOMFolderId(commerceBOMFolderId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce bom folder application rels where commerceBOMFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderApplicationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @param start the lower bound of the range of commerce bom folder application rels
	 * @param end the upper bound of the range of commerce bom folder application rels (not inclusive)
	 * @return the range of matching commerce bom folder application rels
	 */
	@Override
	public List<CommerceBOMFolderApplicationRel> findByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end) {
		return findByCommerceBOMFolderId(commerceBOMFolderId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce bom folder application rels where commerceBOMFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderApplicationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @param start the lower bound of the range of commerce bom folder application rels
	 * @param end the upper bound of the range of commerce bom folder application rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce bom folder application rels
	 */
	@Override
	public List<CommerceBOMFolderApplicationRel> findByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator) {
		return findByCommerceBOMFolderId(commerceBOMFolderId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce bom folder application rels where commerceBOMFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderApplicationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @param start the lower bound of the range of commerce bom folder application rels
	 * @param end the upper bound of the range of commerce bom folder application rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce bom folder application rels
	 */
	@Override
	public List<CommerceBOMFolderApplicationRel> findByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator,
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

		List<CommerceBOMFolderApplicationRel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceBOMFolderApplicationRel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel : list) {
					if ((commerceBOMFolderId != commerceBOMFolderApplicationRel.getCommerceBOMFolderId())) {
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

			query.append(_SQL_SELECT_COMMERCEBOMFOLDERAPPLICATIONREL_WHERE);

			query.append(_FINDER_COLUMN_COMMERCEBOMFOLDERID_COMMERCEBOMFOLDERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceBOMFolderApplicationRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceBOMFolderId);

				if (!pagination) {
					list = (List<CommerceBOMFolderApplicationRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceBOMFolderApplicationRel>)QueryUtil.list(q,
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
	 * Returns the first commerce bom folder application rel in the ordered set where commerceBOMFolderId = &#63;.
	 *
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce bom folder application rel
	 * @throws NoSuchBOMFolderApplicationRelException if a matching commerce bom folder application rel could not be found
	 */
	@Override
	public CommerceBOMFolderApplicationRel findByCommerceBOMFolderId_First(
		long commerceBOMFolderId,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator)
		throws NoSuchBOMFolderApplicationRelException {
		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel = fetchByCommerceBOMFolderId_First(commerceBOMFolderId,
				orderByComparator);

		if (commerceBOMFolderApplicationRel != null) {
			return commerceBOMFolderApplicationRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceBOMFolderId=");
		msg.append(commerceBOMFolderId);

		msg.append("}");

		throw new NoSuchBOMFolderApplicationRelException(msg.toString());
	}

	/**
	 * Returns the first commerce bom folder application rel in the ordered set where commerceBOMFolderId = &#63;.
	 *
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce bom folder application rel, or <code>null</code> if a matching commerce bom folder application rel could not be found
	 */
	@Override
	public CommerceBOMFolderApplicationRel fetchByCommerceBOMFolderId_First(
		long commerceBOMFolderId,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator) {
		List<CommerceBOMFolderApplicationRel> list = findByCommerceBOMFolderId(commerceBOMFolderId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce bom folder application rel in the ordered set where commerceBOMFolderId = &#63;.
	 *
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce bom folder application rel
	 * @throws NoSuchBOMFolderApplicationRelException if a matching commerce bom folder application rel could not be found
	 */
	@Override
	public CommerceBOMFolderApplicationRel findByCommerceBOMFolderId_Last(
		long commerceBOMFolderId,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator)
		throws NoSuchBOMFolderApplicationRelException {
		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel = fetchByCommerceBOMFolderId_Last(commerceBOMFolderId,
				orderByComparator);

		if (commerceBOMFolderApplicationRel != null) {
			return commerceBOMFolderApplicationRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceBOMFolderId=");
		msg.append(commerceBOMFolderId);

		msg.append("}");

		throw new NoSuchBOMFolderApplicationRelException(msg.toString());
	}

	/**
	 * Returns the last commerce bom folder application rel in the ordered set where commerceBOMFolderId = &#63;.
	 *
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce bom folder application rel, or <code>null</code> if a matching commerce bom folder application rel could not be found
	 */
	@Override
	public CommerceBOMFolderApplicationRel fetchByCommerceBOMFolderId_Last(
		long commerceBOMFolderId,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator) {
		int count = countByCommerceBOMFolderId(commerceBOMFolderId);

		if (count == 0) {
			return null;
		}

		List<CommerceBOMFolderApplicationRel> list = findByCommerceBOMFolderId(commerceBOMFolderId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce bom folder application rels before and after the current commerce bom folder application rel in the ordered set where commerceBOMFolderId = &#63;.
	 *
	 * @param commerceBOMFolderApplicationRelId the primary key of the current commerce bom folder application rel
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce bom folder application rel
	 * @throws NoSuchBOMFolderApplicationRelException if a commerce bom folder application rel with the primary key could not be found
	 */
	@Override
	public CommerceBOMFolderApplicationRel[] findByCommerceBOMFolderId_PrevAndNext(
		long commerceBOMFolderApplicationRelId, long commerceBOMFolderId,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator)
		throws NoSuchBOMFolderApplicationRelException {
		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel = findByPrimaryKey(commerceBOMFolderApplicationRelId);

		Session session = null;

		try {
			session = openSession();

			CommerceBOMFolderApplicationRel[] array = new CommerceBOMFolderApplicationRelImpl[3];

			array[0] = getByCommerceBOMFolderId_PrevAndNext(session,
					commerceBOMFolderApplicationRel, commerceBOMFolderId,
					orderByComparator, true);

			array[1] = commerceBOMFolderApplicationRel;

			array[2] = getByCommerceBOMFolderId_PrevAndNext(session,
					commerceBOMFolderApplicationRel, commerceBOMFolderId,
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

	protected CommerceBOMFolderApplicationRel getByCommerceBOMFolderId_PrevAndNext(
		Session session,
		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel,
		long commerceBOMFolderId,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEBOMFOLDERAPPLICATIONREL_WHERE);

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
			query.append(CommerceBOMFolderApplicationRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceBOMFolderId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceBOMFolderApplicationRel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceBOMFolderApplicationRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce bom folder application rels where commerceBOMFolderId = &#63; from the database.
	 *
	 * @param commerceBOMFolderId the commerce bom folder ID
	 */
	@Override
	public void removeByCommerceBOMFolderId(long commerceBOMFolderId) {
		for (CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel : findByCommerceBOMFolderId(
				commerceBOMFolderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(commerceBOMFolderApplicationRel);
		}
	}

	/**
	 * Returns the number of commerce bom folder application rels where commerceBOMFolderId = &#63;.
	 *
	 * @param commerceBOMFolderId the commerce bom folder ID
	 * @return the number of matching commerce bom folder application rels
	 */
	@Override
	public int countByCommerceBOMFolderId(long commerceBOMFolderId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMMERCEBOMFOLDERID;

		Object[] finderArgs = new Object[] { commerceBOMFolderId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEBOMFOLDERAPPLICATIONREL_WHERE);

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

	private static final String _FINDER_COLUMN_COMMERCEBOMFOLDERID_COMMERCEBOMFOLDERID_2 =
		"commerceBOMFolderApplicationRel.commerceBOMFolderId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMMERCEAPPLICATIONMODELID =
		new FinderPath(CommerceBOMFolderApplicationRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMFolderApplicationRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceBOMFolderApplicationRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCommerceApplicationModelId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEAPPLICATIONMODELID =
		new FinderPath(CommerceBOMFolderApplicationRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMFolderApplicationRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceBOMFolderApplicationRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCommerceApplicationModelId",
			new String[] { Long.class.getName() },
			CommerceBOMFolderApplicationRelModelImpl.COMMERCEAPPLICATIONMODELID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMMERCEAPPLICATIONMODELID =
		new FinderPath(CommerceBOMFolderApplicationRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMFolderApplicationRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceApplicationModelId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the commerce bom folder application rels where commerceApplicationModelId = &#63;.
	 *
	 * @param commerceApplicationModelId the commerce application model ID
	 * @return the matching commerce bom folder application rels
	 */
	@Override
	public List<CommerceBOMFolderApplicationRel> findByCommerceApplicationModelId(
		long commerceApplicationModelId) {
		return findByCommerceApplicationModelId(commerceApplicationModelId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce bom folder application rels where commerceApplicationModelId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderApplicationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceApplicationModelId the commerce application model ID
	 * @param start the lower bound of the range of commerce bom folder application rels
	 * @param end the upper bound of the range of commerce bom folder application rels (not inclusive)
	 * @return the range of matching commerce bom folder application rels
	 */
	@Override
	public List<CommerceBOMFolderApplicationRel> findByCommerceApplicationModelId(
		long commerceApplicationModelId, int start, int end) {
		return findByCommerceApplicationModelId(commerceApplicationModelId,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce bom folder application rels where commerceApplicationModelId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderApplicationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceApplicationModelId the commerce application model ID
	 * @param start the lower bound of the range of commerce bom folder application rels
	 * @param end the upper bound of the range of commerce bom folder application rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce bom folder application rels
	 */
	@Override
	public List<CommerceBOMFolderApplicationRel> findByCommerceApplicationModelId(
		long commerceApplicationModelId, int start, int end,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator) {
		return findByCommerceApplicationModelId(commerceApplicationModelId,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce bom folder application rels where commerceApplicationModelId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderApplicationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceApplicationModelId the commerce application model ID
	 * @param start the lower bound of the range of commerce bom folder application rels
	 * @param end the upper bound of the range of commerce bom folder application rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce bom folder application rels
	 */
	@Override
	public List<CommerceBOMFolderApplicationRel> findByCommerceApplicationModelId(
		long commerceApplicationModelId, int start, int end,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEAPPLICATIONMODELID;
			finderArgs = new Object[] { commerceApplicationModelId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMMERCEAPPLICATIONMODELID;
			finderArgs = new Object[] {
					commerceApplicationModelId,
					
					start, end, orderByComparator
				};
		}

		List<CommerceBOMFolderApplicationRel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceBOMFolderApplicationRel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel : list) {
					if ((commerceApplicationModelId != commerceBOMFolderApplicationRel.getCommerceApplicationModelId())) {
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

			query.append(_SQL_SELECT_COMMERCEBOMFOLDERAPPLICATIONREL_WHERE);

			query.append(_FINDER_COLUMN_COMMERCEAPPLICATIONMODELID_COMMERCEAPPLICATIONMODELID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceBOMFolderApplicationRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceApplicationModelId);

				if (!pagination) {
					list = (List<CommerceBOMFolderApplicationRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceBOMFolderApplicationRel>)QueryUtil.list(q,
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
	 * Returns the first commerce bom folder application rel in the ordered set where commerceApplicationModelId = &#63;.
	 *
	 * @param commerceApplicationModelId the commerce application model ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce bom folder application rel
	 * @throws NoSuchBOMFolderApplicationRelException if a matching commerce bom folder application rel could not be found
	 */
	@Override
	public CommerceBOMFolderApplicationRel findByCommerceApplicationModelId_First(
		long commerceApplicationModelId,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator)
		throws NoSuchBOMFolderApplicationRelException {
		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel = fetchByCommerceApplicationModelId_First(commerceApplicationModelId,
				orderByComparator);

		if (commerceBOMFolderApplicationRel != null) {
			return commerceBOMFolderApplicationRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceApplicationModelId=");
		msg.append(commerceApplicationModelId);

		msg.append("}");

		throw new NoSuchBOMFolderApplicationRelException(msg.toString());
	}

	/**
	 * Returns the first commerce bom folder application rel in the ordered set where commerceApplicationModelId = &#63;.
	 *
	 * @param commerceApplicationModelId the commerce application model ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce bom folder application rel, or <code>null</code> if a matching commerce bom folder application rel could not be found
	 */
	@Override
	public CommerceBOMFolderApplicationRel fetchByCommerceApplicationModelId_First(
		long commerceApplicationModelId,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator) {
		List<CommerceBOMFolderApplicationRel> list = findByCommerceApplicationModelId(commerceApplicationModelId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce bom folder application rel in the ordered set where commerceApplicationModelId = &#63;.
	 *
	 * @param commerceApplicationModelId the commerce application model ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce bom folder application rel
	 * @throws NoSuchBOMFolderApplicationRelException if a matching commerce bom folder application rel could not be found
	 */
	@Override
	public CommerceBOMFolderApplicationRel findByCommerceApplicationModelId_Last(
		long commerceApplicationModelId,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator)
		throws NoSuchBOMFolderApplicationRelException {
		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel = fetchByCommerceApplicationModelId_Last(commerceApplicationModelId,
				orderByComparator);

		if (commerceBOMFolderApplicationRel != null) {
			return commerceBOMFolderApplicationRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceApplicationModelId=");
		msg.append(commerceApplicationModelId);

		msg.append("}");

		throw new NoSuchBOMFolderApplicationRelException(msg.toString());
	}

	/**
	 * Returns the last commerce bom folder application rel in the ordered set where commerceApplicationModelId = &#63;.
	 *
	 * @param commerceApplicationModelId the commerce application model ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce bom folder application rel, or <code>null</code> if a matching commerce bom folder application rel could not be found
	 */
	@Override
	public CommerceBOMFolderApplicationRel fetchByCommerceApplicationModelId_Last(
		long commerceApplicationModelId,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator) {
		int count = countByCommerceApplicationModelId(commerceApplicationModelId);

		if (count == 0) {
			return null;
		}

		List<CommerceBOMFolderApplicationRel> list = findByCommerceApplicationModelId(commerceApplicationModelId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce bom folder application rels before and after the current commerce bom folder application rel in the ordered set where commerceApplicationModelId = &#63;.
	 *
	 * @param commerceBOMFolderApplicationRelId the primary key of the current commerce bom folder application rel
	 * @param commerceApplicationModelId the commerce application model ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce bom folder application rel
	 * @throws NoSuchBOMFolderApplicationRelException if a commerce bom folder application rel with the primary key could not be found
	 */
	@Override
	public CommerceBOMFolderApplicationRel[] findByCommerceApplicationModelId_PrevAndNext(
		long commerceBOMFolderApplicationRelId,
		long commerceApplicationModelId,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator)
		throws NoSuchBOMFolderApplicationRelException {
		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel = findByPrimaryKey(commerceBOMFolderApplicationRelId);

		Session session = null;

		try {
			session = openSession();

			CommerceBOMFolderApplicationRel[] array = new CommerceBOMFolderApplicationRelImpl[3];

			array[0] = getByCommerceApplicationModelId_PrevAndNext(session,
					commerceBOMFolderApplicationRel,
					commerceApplicationModelId, orderByComparator, true);

			array[1] = commerceBOMFolderApplicationRel;

			array[2] = getByCommerceApplicationModelId_PrevAndNext(session,
					commerceBOMFolderApplicationRel,
					commerceApplicationModelId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceBOMFolderApplicationRel getByCommerceApplicationModelId_PrevAndNext(
		Session session,
		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel,
		long commerceApplicationModelId,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEBOMFOLDERAPPLICATIONREL_WHERE);

		query.append(_FINDER_COLUMN_COMMERCEAPPLICATIONMODELID_COMMERCEAPPLICATIONMODELID_2);

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
			query.append(CommerceBOMFolderApplicationRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceApplicationModelId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceBOMFolderApplicationRel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceBOMFolderApplicationRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce bom folder application rels where commerceApplicationModelId = &#63; from the database.
	 *
	 * @param commerceApplicationModelId the commerce application model ID
	 */
	@Override
	public void removeByCommerceApplicationModelId(
		long commerceApplicationModelId) {
		for (CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel : findByCommerceApplicationModelId(
				commerceApplicationModelId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(commerceBOMFolderApplicationRel);
		}
	}

	/**
	 * Returns the number of commerce bom folder application rels where commerceApplicationModelId = &#63;.
	 *
	 * @param commerceApplicationModelId the commerce application model ID
	 * @return the number of matching commerce bom folder application rels
	 */
	@Override
	public int countByCommerceApplicationModelId(
		long commerceApplicationModelId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMMERCEAPPLICATIONMODELID;

		Object[] finderArgs = new Object[] { commerceApplicationModelId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEBOMFOLDERAPPLICATIONREL_WHERE);

			query.append(_FINDER_COLUMN_COMMERCEAPPLICATIONMODELID_COMMERCEAPPLICATIONMODELID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceApplicationModelId);

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

	private static final String _FINDER_COLUMN_COMMERCEAPPLICATIONMODELID_COMMERCEAPPLICATIONMODELID_2 =
		"commerceBOMFolderApplicationRel.commerceApplicationModelId = ?";

	public CommerceBOMFolderApplicationRelPersistenceImpl() {
		setModelClass(CommerceBOMFolderApplicationRel.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("commerceBOMFolderApplicationRelId",
				"CBOMFolderApplicationRelId");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the commerce bom folder application rel in the entity cache if it is enabled.
	 *
	 * @param commerceBOMFolderApplicationRel the commerce bom folder application rel
	 */
	@Override
	public void cacheResult(
		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel) {
		entityCache.putResult(CommerceBOMFolderApplicationRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMFolderApplicationRelImpl.class,
			commerceBOMFolderApplicationRel.getPrimaryKey(),
			commerceBOMFolderApplicationRel);

		commerceBOMFolderApplicationRel.resetOriginalValues();
	}

	/**
	 * Caches the commerce bom folder application rels in the entity cache if it is enabled.
	 *
	 * @param commerceBOMFolderApplicationRels the commerce bom folder application rels
	 */
	@Override
	public void cacheResult(
		List<CommerceBOMFolderApplicationRel> commerceBOMFolderApplicationRels) {
		for (CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel : commerceBOMFolderApplicationRels) {
			if (entityCache.getResult(
						CommerceBOMFolderApplicationRelModelImpl.ENTITY_CACHE_ENABLED,
						CommerceBOMFolderApplicationRelImpl.class,
						commerceBOMFolderApplicationRel.getPrimaryKey()) == null) {
				cacheResult(commerceBOMFolderApplicationRel);
			}
			else {
				commerceBOMFolderApplicationRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce bom folder application rels.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceBOMFolderApplicationRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce bom folder application rel.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel) {
		entityCache.removeResult(CommerceBOMFolderApplicationRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMFolderApplicationRelImpl.class,
			commerceBOMFolderApplicationRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<CommerceBOMFolderApplicationRel> commerceBOMFolderApplicationRels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel : commerceBOMFolderApplicationRels) {
			entityCache.removeResult(CommerceBOMFolderApplicationRelModelImpl.ENTITY_CACHE_ENABLED,
				CommerceBOMFolderApplicationRelImpl.class,
				commerceBOMFolderApplicationRel.getPrimaryKey());
		}
	}

	/**
	 * Creates a new commerce bom folder application rel with the primary key. Does not add the commerce bom folder application rel to the database.
	 *
	 * @param commerceBOMFolderApplicationRelId the primary key for the new commerce bom folder application rel
	 * @return the new commerce bom folder application rel
	 */
	@Override
	public CommerceBOMFolderApplicationRel create(
		long commerceBOMFolderApplicationRelId) {
		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel = new CommerceBOMFolderApplicationRelImpl();

		commerceBOMFolderApplicationRel.setNew(true);
		commerceBOMFolderApplicationRel.setPrimaryKey(commerceBOMFolderApplicationRelId);

		commerceBOMFolderApplicationRel.setCompanyId(companyProvider.getCompanyId());

		return commerceBOMFolderApplicationRel;
	}

	/**
	 * Removes the commerce bom folder application rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceBOMFolderApplicationRelId the primary key of the commerce bom folder application rel
	 * @return the commerce bom folder application rel that was removed
	 * @throws NoSuchBOMFolderApplicationRelException if a commerce bom folder application rel with the primary key could not be found
	 */
	@Override
	public CommerceBOMFolderApplicationRel remove(
		long commerceBOMFolderApplicationRelId)
		throws NoSuchBOMFolderApplicationRelException {
		return remove((Serializable)commerceBOMFolderApplicationRelId);
	}

	/**
	 * Removes the commerce bom folder application rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce bom folder application rel
	 * @return the commerce bom folder application rel that was removed
	 * @throws NoSuchBOMFolderApplicationRelException if a commerce bom folder application rel with the primary key could not be found
	 */
	@Override
	public CommerceBOMFolderApplicationRel remove(Serializable primaryKey)
		throws NoSuchBOMFolderApplicationRelException {
		Session session = null;

		try {
			session = openSession();

			CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel = (CommerceBOMFolderApplicationRel)session.get(CommerceBOMFolderApplicationRelImpl.class,
					primaryKey);

			if (commerceBOMFolderApplicationRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBOMFolderApplicationRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commerceBOMFolderApplicationRel);
		}
		catch (NoSuchBOMFolderApplicationRelException nsee) {
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
	protected CommerceBOMFolderApplicationRel removeImpl(
		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceBOMFolderApplicationRel)) {
				commerceBOMFolderApplicationRel = (CommerceBOMFolderApplicationRel)session.get(CommerceBOMFolderApplicationRelImpl.class,
						commerceBOMFolderApplicationRel.getPrimaryKeyObj());
			}

			if (commerceBOMFolderApplicationRel != null) {
				session.delete(commerceBOMFolderApplicationRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceBOMFolderApplicationRel != null) {
			clearCache(commerceBOMFolderApplicationRel);
		}

		return commerceBOMFolderApplicationRel;
	}

	@Override
	public CommerceBOMFolderApplicationRel updateImpl(
		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel) {
		boolean isNew = commerceBOMFolderApplicationRel.isNew();

		if (!(commerceBOMFolderApplicationRel instanceof CommerceBOMFolderApplicationRelModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(
						commerceBOMFolderApplicationRel.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(commerceBOMFolderApplicationRel);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceBOMFolderApplicationRel proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceBOMFolderApplicationRel implementation " +
				commerceBOMFolderApplicationRel.getClass());
		}

		CommerceBOMFolderApplicationRelModelImpl commerceBOMFolderApplicationRelModelImpl =
			(CommerceBOMFolderApplicationRelModelImpl)commerceBOMFolderApplicationRel;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceBOMFolderApplicationRel.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceBOMFolderApplicationRel.setCreateDate(now);
			}
			else {
				commerceBOMFolderApplicationRel.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!commerceBOMFolderApplicationRelModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceBOMFolderApplicationRel.setModifiedDate(now);
			}
			else {
				commerceBOMFolderApplicationRel.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceBOMFolderApplicationRel.isNew()) {
				session.save(commerceBOMFolderApplicationRel);

				commerceBOMFolderApplicationRel.setNew(false);
			}
			else {
				commerceBOMFolderApplicationRel = (CommerceBOMFolderApplicationRel)session.merge(commerceBOMFolderApplicationRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceBOMFolderApplicationRelModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					commerceBOMFolderApplicationRelModelImpl.getCommerceBOMFolderId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEBOMFOLDERID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEBOMFOLDERID,
				args);

			args = new Object[] {
					commerceBOMFolderApplicationRelModelImpl.getCommerceApplicationModelId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEAPPLICATIONMODELID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEAPPLICATIONMODELID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((commerceBOMFolderApplicationRelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEBOMFOLDERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceBOMFolderApplicationRelModelImpl.getOriginalCommerceBOMFolderId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEBOMFOLDERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEBOMFOLDERID,
					args);

				args = new Object[] {
						commerceBOMFolderApplicationRelModelImpl.getCommerceBOMFolderId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEBOMFOLDERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEBOMFOLDERID,
					args);
			}

			if ((commerceBOMFolderApplicationRelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEAPPLICATIONMODELID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceBOMFolderApplicationRelModelImpl.getOriginalCommerceApplicationModelId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEAPPLICATIONMODELID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEAPPLICATIONMODELID,
					args);

				args = new Object[] {
						commerceBOMFolderApplicationRelModelImpl.getCommerceApplicationModelId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEAPPLICATIONMODELID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEAPPLICATIONMODELID,
					args);
			}
		}

		entityCache.putResult(CommerceBOMFolderApplicationRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMFolderApplicationRelImpl.class,
			commerceBOMFolderApplicationRel.getPrimaryKey(),
			commerceBOMFolderApplicationRel, false);

		commerceBOMFolderApplicationRel.resetOriginalValues();

		return commerceBOMFolderApplicationRel;
	}

	/**
	 * Returns the commerce bom folder application rel with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce bom folder application rel
	 * @return the commerce bom folder application rel
	 * @throws NoSuchBOMFolderApplicationRelException if a commerce bom folder application rel with the primary key could not be found
	 */
	@Override
	public CommerceBOMFolderApplicationRel findByPrimaryKey(
		Serializable primaryKey) throws NoSuchBOMFolderApplicationRelException {
		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel = fetchByPrimaryKey(primaryKey);

		if (commerceBOMFolderApplicationRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBOMFolderApplicationRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commerceBOMFolderApplicationRel;
	}

	/**
	 * Returns the commerce bom folder application rel with the primary key or throws a {@link NoSuchBOMFolderApplicationRelException} if it could not be found.
	 *
	 * @param commerceBOMFolderApplicationRelId the primary key of the commerce bom folder application rel
	 * @return the commerce bom folder application rel
	 * @throws NoSuchBOMFolderApplicationRelException if a commerce bom folder application rel with the primary key could not be found
	 */
	@Override
	public CommerceBOMFolderApplicationRel findByPrimaryKey(
		long commerceBOMFolderApplicationRelId)
		throws NoSuchBOMFolderApplicationRelException {
		return findByPrimaryKey((Serializable)commerceBOMFolderApplicationRelId);
	}

	/**
	 * Returns the commerce bom folder application rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce bom folder application rel
	 * @return the commerce bom folder application rel, or <code>null</code> if a commerce bom folder application rel with the primary key could not be found
	 */
	@Override
	public CommerceBOMFolderApplicationRel fetchByPrimaryKey(
		Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CommerceBOMFolderApplicationRelModelImpl.ENTITY_CACHE_ENABLED,
				CommerceBOMFolderApplicationRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel = (CommerceBOMFolderApplicationRel)serializable;

		if (commerceBOMFolderApplicationRel == null) {
			Session session = null;

			try {
				session = openSession();

				commerceBOMFolderApplicationRel = (CommerceBOMFolderApplicationRel)session.get(CommerceBOMFolderApplicationRelImpl.class,
						primaryKey);

				if (commerceBOMFolderApplicationRel != null) {
					cacheResult(commerceBOMFolderApplicationRel);
				}
				else {
					entityCache.putResult(CommerceBOMFolderApplicationRelModelImpl.ENTITY_CACHE_ENABLED,
						CommerceBOMFolderApplicationRelImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommerceBOMFolderApplicationRelModelImpl.ENTITY_CACHE_ENABLED,
					CommerceBOMFolderApplicationRelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceBOMFolderApplicationRel;
	}

	/**
	 * Returns the commerce bom folder application rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceBOMFolderApplicationRelId the primary key of the commerce bom folder application rel
	 * @return the commerce bom folder application rel, or <code>null</code> if a commerce bom folder application rel with the primary key could not be found
	 */
	@Override
	public CommerceBOMFolderApplicationRel fetchByPrimaryKey(
		long commerceBOMFolderApplicationRelId) {
		return fetchByPrimaryKey((Serializable)commerceBOMFolderApplicationRelId);
	}

	@Override
	public Map<Serializable, CommerceBOMFolderApplicationRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceBOMFolderApplicationRel> map = new HashMap<Serializable, CommerceBOMFolderApplicationRel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel = fetchByPrimaryKey(primaryKey);

			if (commerceBOMFolderApplicationRel != null) {
				map.put(primaryKey, commerceBOMFolderApplicationRel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CommerceBOMFolderApplicationRelModelImpl.ENTITY_CACHE_ENABLED,
					CommerceBOMFolderApplicationRelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey,
						(CommerceBOMFolderApplicationRel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMMERCEBOMFOLDERAPPLICATIONREL_WHERE_PKS_IN);

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

			for (CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel : (List<CommerceBOMFolderApplicationRel>)q.list()) {
				map.put(commerceBOMFolderApplicationRel.getPrimaryKeyObj(),
					commerceBOMFolderApplicationRel);

				cacheResult(commerceBOMFolderApplicationRel);

				uncachedPrimaryKeys.remove(commerceBOMFolderApplicationRel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CommerceBOMFolderApplicationRelModelImpl.ENTITY_CACHE_ENABLED,
					CommerceBOMFolderApplicationRelImpl.class, primaryKey,
					nullModel);
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
	 * Returns all the commerce bom folder application rels.
	 *
	 * @return the commerce bom folder application rels
	 */
	@Override
	public List<CommerceBOMFolderApplicationRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce bom folder application rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderApplicationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce bom folder application rels
	 * @param end the upper bound of the range of commerce bom folder application rels (not inclusive)
	 * @return the range of commerce bom folder application rels
	 */
	@Override
	public List<CommerceBOMFolderApplicationRel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce bom folder application rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderApplicationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce bom folder application rels
	 * @param end the upper bound of the range of commerce bom folder application rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce bom folder application rels
	 */
	@Override
	public List<CommerceBOMFolderApplicationRel> findAll(int start, int end,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce bom folder application rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderApplicationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce bom folder application rels
	 * @param end the upper bound of the range of commerce bom folder application rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce bom folder application rels
	 */
	@Override
	public List<CommerceBOMFolderApplicationRel> findAll(int start, int end,
		OrderByComparator<CommerceBOMFolderApplicationRel> orderByComparator,
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

		List<CommerceBOMFolderApplicationRel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceBOMFolderApplicationRel>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEBOMFOLDERAPPLICATIONREL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEBOMFOLDERAPPLICATIONREL;

				if (pagination) {
					sql = sql.concat(CommerceBOMFolderApplicationRelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceBOMFolderApplicationRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceBOMFolderApplicationRel>)QueryUtil.list(q,
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
	 * Removes all the commerce bom folder application rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel : findAll()) {
			remove(commerceBOMFolderApplicationRel);
		}
	}

	/**
	 * Returns the number of commerce bom folder application rels.
	 *
	 * @return the number of commerce bom folder application rels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCEBOMFOLDERAPPLICATIONREL);

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
		return CommerceBOMFolderApplicationRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce bom folder application rel persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CommerceBOMFolderApplicationRelImpl.class.getName());
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
	private static final String _SQL_SELECT_COMMERCEBOMFOLDERAPPLICATIONREL = "SELECT commerceBOMFolderApplicationRel FROM CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel";
	private static final String _SQL_SELECT_COMMERCEBOMFOLDERAPPLICATIONREL_WHERE_PKS_IN =
		"SELECT commerceBOMFolderApplicationRel FROM CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel WHERE CBOMFolderApplicationRelId IN (";
	private static final String _SQL_SELECT_COMMERCEBOMFOLDERAPPLICATIONREL_WHERE =
		"SELECT commerceBOMFolderApplicationRel FROM CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel WHERE ";
	private static final String _SQL_COUNT_COMMERCEBOMFOLDERAPPLICATIONREL = "SELECT COUNT(commerceBOMFolderApplicationRel) FROM CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel";
	private static final String _SQL_COUNT_COMMERCEBOMFOLDERAPPLICATIONREL_WHERE =
		"SELECT COUNT(commerceBOMFolderApplicationRel) FROM CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceBOMFolderApplicationRel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommerceBOMFolderApplicationRel exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CommerceBOMFolderApplicationRel exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CommerceBOMFolderApplicationRelPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"commerceBOMFolderApplicationRelId"
			});
}