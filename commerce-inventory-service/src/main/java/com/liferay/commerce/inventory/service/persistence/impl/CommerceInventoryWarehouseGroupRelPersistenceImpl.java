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

import com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseGroupRelException;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel;
import com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseGroupRelImpl;
import com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseGroupRelModelImpl;
import com.liferay.commerce.inventory.service.persistence.CommerceInventoryWarehouseGroupRelPersistence;

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
import java.util.Set;

/**
 * The persistence implementation for the commerce inventory warehouse group rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseGroupRelPersistence
 * @see com.liferay.commerce.inventory.service.persistence.CommerceInventoryWarehouseGroupRelUtil
 * @generated
 */
@ProviderType
public class CommerceInventoryWarehouseGroupRelPersistenceImpl
	extends BasePersistenceImpl<CommerceInventoryWarehouseGroupRel>
	implements CommerceInventoryWarehouseGroupRelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommerceInventoryWarehouseGroupRelUtil} to access the commerce inventory warehouse group rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommerceInventoryWarehouseGroupRelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommerceInventoryWarehouseGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseGroupRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryWarehouseGroupRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommerceInventoryWarehouseGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseGroupRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryWarehouseGroupRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommerceInventoryWarehouseGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseGroupRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(CommerceInventoryWarehouseGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseGroupRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryWarehouseGroupRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBygroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(CommerceInventoryWarehouseGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseGroupRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryWarehouseGroupRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBygroupId",
			new String[] { Long.class.getName() },
			CommerceInventoryWarehouseGroupRelModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(CommerceInventoryWarehouseGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseGroupRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBygroupId", new String[] { Long.class.getName() });

	/**
	 * Returns all the commerce inventory warehouse group rels where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching commerce inventory warehouse group rels
	 */
	@Override
	public List<CommerceInventoryWarehouseGroupRel> findBygroupId(long groupId) {
		return findBygroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory warehouse group rels where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce inventory warehouse group rels
	 * @param end the upper bound of the range of commerce inventory warehouse group rels (not inclusive)
	 * @return the range of matching commerce inventory warehouse group rels
	 */
	@Override
	public List<CommerceInventoryWarehouseGroupRel> findBygroupId(
		long groupId, int start, int end) {
		return findBygroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse group rels where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce inventory warehouse group rels
	 * @param end the upper bound of the range of commerce inventory warehouse group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory warehouse group rels
	 */
	@Override
	public List<CommerceInventoryWarehouseGroupRel> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator) {
		return findBygroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse group rels where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce inventory warehouse group rels
	 * @param end the upper bound of the range of commerce inventory warehouse group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce inventory warehouse group rels
	 */
	@Override
	public List<CommerceInventoryWarehouseGroupRel> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<CommerceInventoryWarehouseGroupRel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceInventoryWarehouseGroupRel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel : list) {
					if ((groupId != commerceInventoryWarehouseGroupRel.getGroupId())) {
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

			query.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEGROUPREL_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceInventoryWarehouseGroupRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<CommerceInventoryWarehouseGroupRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceInventoryWarehouseGroupRel>)QueryUtil.list(q,
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
	 * Returns the first commerce inventory warehouse group rel in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse group rel
	 * @throws NoSuchInventoryWarehouseGroupRelException if a matching commerce inventory warehouse group rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel findBygroupId_First(
		long groupId,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator)
		throws NoSuchInventoryWarehouseGroupRelException {
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel = fetchBygroupId_First(groupId,
				orderByComparator);

		if (commerceInventoryWarehouseGroupRel != null) {
			return commerceInventoryWarehouseGroupRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchInventoryWarehouseGroupRelException(msg.toString());
	}

	/**
	 * Returns the first commerce inventory warehouse group rel in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse group rel, or <code>null</code> if a matching commerce inventory warehouse group rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel fetchBygroupId_First(
		long groupId,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator) {
		List<CommerceInventoryWarehouseGroupRel> list = findBygroupId(groupId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce inventory warehouse group rel in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse group rel
	 * @throws NoSuchInventoryWarehouseGroupRelException if a matching commerce inventory warehouse group rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel findBygroupId_Last(long groupId,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator)
		throws NoSuchInventoryWarehouseGroupRelException {
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel = fetchBygroupId_Last(groupId,
				orderByComparator);

		if (commerceInventoryWarehouseGroupRel != null) {
			return commerceInventoryWarehouseGroupRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchInventoryWarehouseGroupRelException(msg.toString());
	}

	/**
	 * Returns the last commerce inventory warehouse group rel in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse group rel, or <code>null</code> if a matching commerce inventory warehouse group rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel fetchBygroupId_Last(
		long groupId,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator) {
		int count = countBygroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<CommerceInventoryWarehouseGroupRel> list = findBygroupId(groupId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce inventory warehouse group rels before and after the current commerce inventory warehouse group rel in the ordered set where groupId = &#63;.
	 *
	 * @param commerceInventoryWarehouseGroupRelId the primary key of the current commerce inventory warehouse group rel
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory warehouse group rel
	 * @throws NoSuchInventoryWarehouseGroupRelException if a commerce inventory warehouse group rel with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel[] findBygroupId_PrevAndNext(
		long commerceInventoryWarehouseGroupRelId, long groupId,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator)
		throws NoSuchInventoryWarehouseGroupRelException {
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel = findByPrimaryKey(commerceInventoryWarehouseGroupRelId);

		Session session = null;

		try {
			session = openSession();

			CommerceInventoryWarehouseGroupRel[] array = new CommerceInventoryWarehouseGroupRelImpl[3];

			array[0] = getBygroupId_PrevAndNext(session,
					commerceInventoryWarehouseGroupRel, groupId,
					orderByComparator, true);

			array[1] = commerceInventoryWarehouseGroupRel;

			array[2] = getBygroupId_PrevAndNext(session,
					commerceInventoryWarehouseGroupRel, groupId,
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

	protected CommerceInventoryWarehouseGroupRel getBygroupId_PrevAndNext(
		Session session,
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel,
		long groupId,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEGROUPREL_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			query.append(CommerceInventoryWarehouseGroupRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceInventoryWarehouseGroupRel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceInventoryWarehouseGroupRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce inventory warehouse group rels where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeBygroupId(long groupId) {
		for (CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel : findBygroupId(
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(commerceInventoryWarehouseGroupRel);
		}
	}

	/**
	 * Returns the number of commerce inventory warehouse group rels where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching commerce inventory warehouse group rels
	 */
	@Override
	public int countBygroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEINVENTORYWAREHOUSEGROUPREL_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "commerceInventoryWarehouseGroupRel.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_CWI = new FinderPath(CommerceInventoryWarehouseGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseGroupRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryWarehouseGroupRelImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_CWI",
			new String[] { Long.class.getName(), Long.class.getName() },
			CommerceInventoryWarehouseGroupRelModelImpl.GROUPID_COLUMN_BITMASK |
			CommerceInventoryWarehouseGroupRelModelImpl.COMMERCEWAREHOUSEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_CWI = new FinderPath(CommerceInventoryWarehouseGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseGroupRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByG_CWI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the commerce inventory warehouse group rel where groupId = &#63; and commerceWarehouseId = &#63; or throws a {@link NoSuchInventoryWarehouseGroupRelException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @return the matching commerce inventory warehouse group rel
	 * @throws NoSuchInventoryWarehouseGroupRelException if a matching commerce inventory warehouse group rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel findByG_CWI(long groupId,
		long commerceWarehouseId)
		throws NoSuchInventoryWarehouseGroupRelException {
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel = fetchByG_CWI(groupId,
				commerceWarehouseId);

		if (commerceInventoryWarehouseGroupRel == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", commerceWarehouseId=");
			msg.append(commerceWarehouseId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchInventoryWarehouseGroupRelException(msg.toString());
		}

		return commerceInventoryWarehouseGroupRel;
	}

	/**
	 * Returns the commerce inventory warehouse group rel where groupId = &#63; and commerceWarehouseId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @return the matching commerce inventory warehouse group rel, or <code>null</code> if a matching commerce inventory warehouse group rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel fetchByG_CWI(long groupId,
		long commerceWarehouseId) {
		return fetchByG_CWI(groupId, commerceWarehouseId, true);
	}

	/**
	 * Returns the commerce inventory warehouse group rel where groupId = &#63; and commerceWarehouseId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching commerce inventory warehouse group rel, or <code>null</code> if a matching commerce inventory warehouse group rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel fetchByG_CWI(long groupId,
		long commerceWarehouseId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, commerceWarehouseId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_CWI,
					finderArgs, this);
		}

		if (result instanceof CommerceInventoryWarehouseGroupRel) {
			CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel =
				(CommerceInventoryWarehouseGroupRel)result;

			if ((groupId != commerceInventoryWarehouseGroupRel.getGroupId()) ||
					(commerceWarehouseId != commerceInventoryWarehouseGroupRel.getCommerceWarehouseId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEGROUPREL_WHERE);

			query.append(_FINDER_COLUMN_G_CWI_GROUPID_2);

			query.append(_FINDER_COLUMN_G_CWI_COMMERCEWAREHOUSEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(commerceWarehouseId);

				List<CommerceInventoryWarehouseGroupRel> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_CWI,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"CommerceInventoryWarehouseGroupRelPersistenceImpl.fetchByG_CWI(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel =
						list.get(0);

					result = commerceInventoryWarehouseGroupRel;

					cacheResult(commerceInventoryWarehouseGroupRel);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_CWI, finderArgs);

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
			return (CommerceInventoryWarehouseGroupRel)result;
		}
	}

	/**
	 * Removes the commerce inventory warehouse group rel where groupId = &#63; and commerceWarehouseId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @return the commerce inventory warehouse group rel that was removed
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel removeByG_CWI(long groupId,
		long commerceWarehouseId)
		throws NoSuchInventoryWarehouseGroupRelException {
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel = findByG_CWI(groupId,
				commerceWarehouseId);

		return remove(commerceInventoryWarehouseGroupRel);
	}

	/**
	 * Returns the number of commerce inventory warehouse group rels where groupId = &#63; and commerceWarehouseId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @return the number of matching commerce inventory warehouse group rels
	 */
	@Override
	public int countByG_CWI(long groupId, long commerceWarehouseId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_CWI;

		Object[] finderArgs = new Object[] { groupId, commerceWarehouseId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEINVENTORYWAREHOUSEGROUPREL_WHERE);

			query.append(_FINDER_COLUMN_G_CWI_GROUPID_2);

			query.append(_FINDER_COLUMN_G_CWI_COMMERCEWAREHOUSEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(commerceWarehouseId);

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

	private static final String _FINDER_COLUMN_G_CWI_GROUPID_2 = "commerceInventoryWarehouseGroupRel.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_CWI_COMMERCEWAREHOUSEID_2 = "commerceInventoryWarehouseGroupRel.commerceWarehouseId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P = new FinderPath(CommerceInventoryWarehouseGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseGroupRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryWarehouseGroupRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_P",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P = new FinderPath(CommerceInventoryWarehouseGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseGroupRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryWarehouseGroupRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_P",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			CommerceInventoryWarehouseGroupRelModelImpl.GROUPID_COLUMN_BITMASK |
			CommerceInventoryWarehouseGroupRelModelImpl.PRIMARY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_P = new FinderPath(CommerceInventoryWarehouseGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseGroupRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByG_P",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the commerce inventory warehouse group rels where groupId = &#63; and primary = &#63;.
	 *
	 * @param groupId the group ID
	 * @param primary the primary
	 * @return the matching commerce inventory warehouse group rels
	 */
	@Override
	public List<CommerceInventoryWarehouseGroupRel> findByG_P(long groupId,
		boolean primary) {
		return findByG_P(groupId, primary, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory warehouse group rels where groupId = &#63; and primary = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param primary the primary
	 * @param start the lower bound of the range of commerce inventory warehouse group rels
	 * @param end the upper bound of the range of commerce inventory warehouse group rels (not inclusive)
	 * @return the range of matching commerce inventory warehouse group rels
	 */
	@Override
	public List<CommerceInventoryWarehouseGroupRel> findByG_P(long groupId,
		boolean primary, int start, int end) {
		return findByG_P(groupId, primary, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse group rels where groupId = &#63; and primary = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param primary the primary
	 * @param start the lower bound of the range of commerce inventory warehouse group rels
	 * @param end the upper bound of the range of commerce inventory warehouse group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory warehouse group rels
	 */
	@Override
	public List<CommerceInventoryWarehouseGroupRel> findByG_P(long groupId,
		boolean primary, int start, int end,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator) {
		return findByG_P(groupId, primary, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse group rels where groupId = &#63; and primary = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param primary the primary
	 * @param start the lower bound of the range of commerce inventory warehouse group rels
	 * @param end the upper bound of the range of commerce inventory warehouse group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce inventory warehouse group rels
	 */
	@Override
	public List<CommerceInventoryWarehouseGroupRel> findByG_P(long groupId,
		boolean primary, int start, int end,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P;
			finderArgs = new Object[] { groupId, primary };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P;
			finderArgs = new Object[] {
					groupId, primary,
					
					start, end, orderByComparator
				};
		}

		List<CommerceInventoryWarehouseGroupRel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceInventoryWarehouseGroupRel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel : list) {
					if ((groupId != commerceInventoryWarehouseGroupRel.getGroupId()) ||
							(primary != commerceInventoryWarehouseGroupRel.isPrimary())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEGROUPREL_WHERE);

			query.append(_FINDER_COLUMN_G_P_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_PRIMARY_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceInventoryWarehouseGroupRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(primary);

				if (!pagination) {
					list = (List<CommerceInventoryWarehouseGroupRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceInventoryWarehouseGroupRel>)QueryUtil.list(q,
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
	 * Returns the first commerce inventory warehouse group rel in the ordered set where groupId = &#63; and primary = &#63;.
	 *
	 * @param groupId the group ID
	 * @param primary the primary
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse group rel
	 * @throws NoSuchInventoryWarehouseGroupRelException if a matching commerce inventory warehouse group rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel findByG_P_First(long groupId,
		boolean primary,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator)
		throws NoSuchInventoryWarehouseGroupRelException {
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel = fetchByG_P_First(groupId,
				primary, orderByComparator);

		if (commerceInventoryWarehouseGroupRel != null) {
			return commerceInventoryWarehouseGroupRel;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", primary=");
		msg.append(primary);

		msg.append("}");

		throw new NoSuchInventoryWarehouseGroupRelException(msg.toString());
	}

	/**
	 * Returns the first commerce inventory warehouse group rel in the ordered set where groupId = &#63; and primary = &#63;.
	 *
	 * @param groupId the group ID
	 * @param primary the primary
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse group rel, or <code>null</code> if a matching commerce inventory warehouse group rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel fetchByG_P_First(long groupId,
		boolean primary,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator) {
		List<CommerceInventoryWarehouseGroupRel> list = findByG_P(groupId,
				primary, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce inventory warehouse group rel in the ordered set where groupId = &#63; and primary = &#63;.
	 *
	 * @param groupId the group ID
	 * @param primary the primary
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse group rel
	 * @throws NoSuchInventoryWarehouseGroupRelException if a matching commerce inventory warehouse group rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel findByG_P_Last(long groupId,
		boolean primary,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator)
		throws NoSuchInventoryWarehouseGroupRelException {
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel = fetchByG_P_Last(groupId,
				primary, orderByComparator);

		if (commerceInventoryWarehouseGroupRel != null) {
			return commerceInventoryWarehouseGroupRel;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", primary=");
		msg.append(primary);

		msg.append("}");

		throw new NoSuchInventoryWarehouseGroupRelException(msg.toString());
	}

	/**
	 * Returns the last commerce inventory warehouse group rel in the ordered set where groupId = &#63; and primary = &#63;.
	 *
	 * @param groupId the group ID
	 * @param primary the primary
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse group rel, or <code>null</code> if a matching commerce inventory warehouse group rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel fetchByG_P_Last(long groupId,
		boolean primary,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator) {
		int count = countByG_P(groupId, primary);

		if (count == 0) {
			return null;
		}

		List<CommerceInventoryWarehouseGroupRel> list = findByG_P(groupId,
				primary, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce inventory warehouse group rels before and after the current commerce inventory warehouse group rel in the ordered set where groupId = &#63; and primary = &#63;.
	 *
	 * @param commerceInventoryWarehouseGroupRelId the primary key of the current commerce inventory warehouse group rel
	 * @param groupId the group ID
	 * @param primary the primary
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory warehouse group rel
	 * @throws NoSuchInventoryWarehouseGroupRelException if a commerce inventory warehouse group rel with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel[] findByG_P_PrevAndNext(
		long commerceInventoryWarehouseGroupRelId, long groupId,
		boolean primary,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator)
		throws NoSuchInventoryWarehouseGroupRelException {
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel = findByPrimaryKey(commerceInventoryWarehouseGroupRelId);

		Session session = null;

		try {
			session = openSession();

			CommerceInventoryWarehouseGroupRel[] array = new CommerceInventoryWarehouseGroupRelImpl[3];

			array[0] = getByG_P_PrevAndNext(session,
					commerceInventoryWarehouseGroupRel, groupId, primary,
					orderByComparator, true);

			array[1] = commerceInventoryWarehouseGroupRel;

			array[2] = getByG_P_PrevAndNext(session,
					commerceInventoryWarehouseGroupRel, groupId, primary,
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

	protected CommerceInventoryWarehouseGroupRel getByG_P_PrevAndNext(
		Session session,
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel,
		long groupId, boolean primary,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEGROUPREL_WHERE);

		query.append(_FINDER_COLUMN_G_P_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_PRIMARY_2);

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
			query.append(CommerceInventoryWarehouseGroupRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(primary);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceInventoryWarehouseGroupRel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceInventoryWarehouseGroupRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce inventory warehouse group rels where groupId = &#63; and primary = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param primary the primary
	 */
	@Override
	public void removeByG_P(long groupId, boolean primary) {
		for (CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel : findByG_P(
				groupId, primary, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(commerceInventoryWarehouseGroupRel);
		}
	}

	/**
	 * Returns the number of commerce inventory warehouse group rels where groupId = &#63; and primary = &#63;.
	 *
	 * @param groupId the group ID
	 * @param primary the primary
	 * @return the number of matching commerce inventory warehouse group rels
	 */
	@Override
	public int countByG_P(long groupId, boolean primary) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_P;

		Object[] finderArgs = new Object[] { groupId, primary };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEINVENTORYWAREHOUSEGROUPREL_WHERE);

			query.append(_FINDER_COLUMN_G_P_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_PRIMARY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(primary);

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

	private static final String _FINDER_COLUMN_G_P_GROUPID_2 = "commerceInventoryWarehouseGroupRel.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_PRIMARY_2 = "commerceInventoryWarehouseGroupRel.primary = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_CWI_P = new FinderPath(CommerceInventoryWarehouseGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseGroupRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryWarehouseGroupRelImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_CWI_P",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			},
			CommerceInventoryWarehouseGroupRelModelImpl.GROUPID_COLUMN_BITMASK |
			CommerceInventoryWarehouseGroupRelModelImpl.COMMERCEWAREHOUSEID_COLUMN_BITMASK |
			CommerceInventoryWarehouseGroupRelModelImpl.PRIMARY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_CWI_P = new FinderPath(CommerceInventoryWarehouseGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseGroupRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByG_CWI_P",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns the commerce inventory warehouse group rel where groupId = &#63; and commerceWarehouseId = &#63; and primary = &#63; or throws a {@link NoSuchInventoryWarehouseGroupRelException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param primary the primary
	 * @return the matching commerce inventory warehouse group rel
	 * @throws NoSuchInventoryWarehouseGroupRelException if a matching commerce inventory warehouse group rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel findByG_CWI_P(long groupId,
		long commerceWarehouseId, boolean primary)
		throws NoSuchInventoryWarehouseGroupRelException {
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel = fetchByG_CWI_P(groupId,
				commerceWarehouseId, primary);

		if (commerceInventoryWarehouseGroupRel == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", commerceWarehouseId=");
			msg.append(commerceWarehouseId);

			msg.append(", primary=");
			msg.append(primary);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchInventoryWarehouseGroupRelException(msg.toString());
		}

		return commerceInventoryWarehouseGroupRel;
	}

	/**
	 * Returns the commerce inventory warehouse group rel where groupId = &#63; and commerceWarehouseId = &#63; and primary = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param primary the primary
	 * @return the matching commerce inventory warehouse group rel, or <code>null</code> if a matching commerce inventory warehouse group rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel fetchByG_CWI_P(long groupId,
		long commerceWarehouseId, boolean primary) {
		return fetchByG_CWI_P(groupId, commerceWarehouseId, primary, true);
	}

	/**
	 * Returns the commerce inventory warehouse group rel where groupId = &#63; and commerceWarehouseId = &#63; and primary = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param primary the primary
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching commerce inventory warehouse group rel, or <code>null</code> if a matching commerce inventory warehouse group rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel fetchByG_CWI_P(long groupId,
		long commerceWarehouseId, boolean primary, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, commerceWarehouseId, primary };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_CWI_P,
					finderArgs, this);
		}

		if (result instanceof CommerceInventoryWarehouseGroupRel) {
			CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel =
				(CommerceInventoryWarehouseGroupRel)result;

			if ((groupId != commerceInventoryWarehouseGroupRel.getGroupId()) ||
					(commerceWarehouseId != commerceInventoryWarehouseGroupRel.getCommerceWarehouseId()) ||
					(primary != commerceInventoryWarehouseGroupRel.isPrimary())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEGROUPREL_WHERE);

			query.append(_FINDER_COLUMN_G_CWI_P_GROUPID_2);

			query.append(_FINDER_COLUMN_G_CWI_P_COMMERCEWAREHOUSEID_2);

			query.append(_FINDER_COLUMN_G_CWI_P_PRIMARY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(commerceWarehouseId);

				qPos.add(primary);

				List<CommerceInventoryWarehouseGroupRel> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_CWI_P,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"CommerceInventoryWarehouseGroupRelPersistenceImpl.fetchByG_CWI_P(long, long, boolean, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel =
						list.get(0);

					result = commerceInventoryWarehouseGroupRel;

					cacheResult(commerceInventoryWarehouseGroupRel);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_CWI_P,
					finderArgs);

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
			return (CommerceInventoryWarehouseGroupRel)result;
		}
	}

	/**
	 * Removes the commerce inventory warehouse group rel where groupId = &#63; and commerceWarehouseId = &#63; and primary = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param primary the primary
	 * @return the commerce inventory warehouse group rel that was removed
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel removeByG_CWI_P(long groupId,
		long commerceWarehouseId, boolean primary)
		throws NoSuchInventoryWarehouseGroupRelException {
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel = findByG_CWI_P(groupId,
				commerceWarehouseId, primary);

		return remove(commerceInventoryWarehouseGroupRel);
	}

	/**
	 * Returns the number of commerce inventory warehouse group rels where groupId = &#63; and commerceWarehouseId = &#63; and primary = &#63;.
	 *
	 * @param groupId the group ID
	 * @param commerceWarehouseId the commerce warehouse ID
	 * @param primary the primary
	 * @return the number of matching commerce inventory warehouse group rels
	 */
	@Override
	public int countByG_CWI_P(long groupId, long commerceWarehouseId,
		boolean primary) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_CWI_P;

		Object[] finderArgs = new Object[] { groupId, commerceWarehouseId, primary };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_COMMERCEINVENTORYWAREHOUSEGROUPREL_WHERE);

			query.append(_FINDER_COLUMN_G_CWI_P_GROUPID_2);

			query.append(_FINDER_COLUMN_G_CWI_P_COMMERCEWAREHOUSEID_2);

			query.append(_FINDER_COLUMN_G_CWI_P_PRIMARY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(commerceWarehouseId);

				qPos.add(primary);

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

	private static final String _FINDER_COLUMN_G_CWI_P_GROUPID_2 = "commerceInventoryWarehouseGroupRel.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_CWI_P_COMMERCEWAREHOUSEID_2 = "commerceInventoryWarehouseGroupRel.commerceWarehouseId = ? AND ";
	private static final String _FINDER_COLUMN_G_CWI_P_PRIMARY_2 = "commerceInventoryWarehouseGroupRel.primary = ?";

	public CommerceInventoryWarehouseGroupRelPersistenceImpl() {
		setModelClass(CommerceInventoryWarehouseGroupRel.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("commerceInventoryWarehouseGroupRelId",
				"CIWarehouseGroupRelId");
			dbColumnNames.put("primary", "primary_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the commerce inventory warehouse group rel in the entity cache if it is enabled.
	 *
	 * @param commerceInventoryWarehouseGroupRel the commerce inventory warehouse group rel
	 */
	@Override
	public void cacheResult(
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel) {
		entityCache.putResult(CommerceInventoryWarehouseGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseGroupRelImpl.class,
			commerceInventoryWarehouseGroupRel.getPrimaryKey(),
			commerceInventoryWarehouseGroupRel);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_CWI,
			new Object[] {
				commerceInventoryWarehouseGroupRel.getGroupId(),
				commerceInventoryWarehouseGroupRel.getCommerceWarehouseId()
			}, commerceInventoryWarehouseGroupRel);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_CWI_P,
			new Object[] {
				commerceInventoryWarehouseGroupRel.getGroupId(),
				commerceInventoryWarehouseGroupRel.getCommerceWarehouseId(),
				commerceInventoryWarehouseGroupRel.isPrimary()
			}, commerceInventoryWarehouseGroupRel);

		commerceInventoryWarehouseGroupRel.resetOriginalValues();
	}

	/**
	 * Caches the commerce inventory warehouse group rels in the entity cache if it is enabled.
	 *
	 * @param commerceInventoryWarehouseGroupRels the commerce inventory warehouse group rels
	 */
	@Override
	public void cacheResult(
		List<CommerceInventoryWarehouseGroupRel> commerceInventoryWarehouseGroupRels) {
		for (CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel : commerceInventoryWarehouseGroupRels) {
			if (entityCache.getResult(
						CommerceInventoryWarehouseGroupRelModelImpl.ENTITY_CACHE_ENABLED,
						CommerceInventoryWarehouseGroupRelImpl.class,
						commerceInventoryWarehouseGroupRel.getPrimaryKey()) == null) {
				cacheResult(commerceInventoryWarehouseGroupRel);
			}
			else {
				commerceInventoryWarehouseGroupRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce inventory warehouse group rels.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceInventoryWarehouseGroupRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce inventory warehouse group rel.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel) {
		entityCache.removeResult(CommerceInventoryWarehouseGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseGroupRelImpl.class,
			commerceInventoryWarehouseGroupRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CommerceInventoryWarehouseGroupRelModelImpl)commerceInventoryWarehouseGroupRel,
			true);
	}

	@Override
	public void clearCache(
		List<CommerceInventoryWarehouseGroupRel> commerceInventoryWarehouseGroupRels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel : commerceInventoryWarehouseGroupRels) {
			entityCache.removeResult(CommerceInventoryWarehouseGroupRelModelImpl.ENTITY_CACHE_ENABLED,
				CommerceInventoryWarehouseGroupRelImpl.class,
				commerceInventoryWarehouseGroupRel.getPrimaryKey());

			clearUniqueFindersCache((CommerceInventoryWarehouseGroupRelModelImpl)commerceInventoryWarehouseGroupRel,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		CommerceInventoryWarehouseGroupRelModelImpl commerceInventoryWarehouseGroupRelModelImpl) {
		Object[] args = new Object[] {
				commerceInventoryWarehouseGroupRelModelImpl.getGroupId(),
				commerceInventoryWarehouseGroupRelModelImpl.getCommerceWarehouseId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_CWI, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_CWI, args,
			commerceInventoryWarehouseGroupRelModelImpl, false);

		args = new Object[] {
				commerceInventoryWarehouseGroupRelModelImpl.getGroupId(),
				commerceInventoryWarehouseGroupRelModelImpl.getCommerceWarehouseId(),
				commerceInventoryWarehouseGroupRelModelImpl.isPrimary()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_G_CWI_P, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_G_CWI_P, args,
			commerceInventoryWarehouseGroupRelModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CommerceInventoryWarehouseGroupRelModelImpl commerceInventoryWarehouseGroupRelModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					commerceInventoryWarehouseGroupRelModelImpl.getGroupId(),
					commerceInventoryWarehouseGroupRelModelImpl.getCommerceWarehouseId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_CWI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_CWI, args);
		}

		if ((commerceInventoryWarehouseGroupRelModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_CWI.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					commerceInventoryWarehouseGroupRelModelImpl.getOriginalGroupId(),
					commerceInventoryWarehouseGroupRelModelImpl.getOriginalCommerceWarehouseId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_CWI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_CWI, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					commerceInventoryWarehouseGroupRelModelImpl.getGroupId(),
					commerceInventoryWarehouseGroupRelModelImpl.getCommerceWarehouseId(),
					commerceInventoryWarehouseGroupRelModelImpl.isPrimary()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_CWI_P, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_CWI_P, args);
		}

		if ((commerceInventoryWarehouseGroupRelModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_CWI_P.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					commerceInventoryWarehouseGroupRelModelImpl.getOriginalGroupId(),
					commerceInventoryWarehouseGroupRelModelImpl.getOriginalCommerceWarehouseId(),
					commerceInventoryWarehouseGroupRelModelImpl.getOriginalPrimary()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_CWI_P, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_CWI_P, args);
		}
	}

	/**
	 * Creates a new commerce inventory warehouse group rel with the primary key. Does not add the commerce inventory warehouse group rel to the database.
	 *
	 * @param commerceInventoryWarehouseGroupRelId the primary key for the new commerce inventory warehouse group rel
	 * @return the new commerce inventory warehouse group rel
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel create(
		long commerceInventoryWarehouseGroupRelId) {
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel = new CommerceInventoryWarehouseGroupRelImpl();

		commerceInventoryWarehouseGroupRel.setNew(true);
		commerceInventoryWarehouseGroupRel.setPrimaryKey(commerceInventoryWarehouseGroupRelId);

		commerceInventoryWarehouseGroupRel.setCompanyId(companyProvider.getCompanyId());

		return commerceInventoryWarehouseGroupRel;
	}

	/**
	 * Removes the commerce inventory warehouse group rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceInventoryWarehouseGroupRelId the primary key of the commerce inventory warehouse group rel
	 * @return the commerce inventory warehouse group rel that was removed
	 * @throws NoSuchInventoryWarehouseGroupRelException if a commerce inventory warehouse group rel with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel remove(
		long commerceInventoryWarehouseGroupRelId)
		throws NoSuchInventoryWarehouseGroupRelException {
		return remove((Serializable)commerceInventoryWarehouseGroupRelId);
	}

	/**
	 * Removes the commerce inventory warehouse group rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce inventory warehouse group rel
	 * @return the commerce inventory warehouse group rel that was removed
	 * @throws NoSuchInventoryWarehouseGroupRelException if a commerce inventory warehouse group rel with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel remove(Serializable primaryKey)
		throws NoSuchInventoryWarehouseGroupRelException {
		Session session = null;

		try {
			session = openSession();

			CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel =
				(CommerceInventoryWarehouseGroupRel)session.get(CommerceInventoryWarehouseGroupRelImpl.class,
					primaryKey);

			if (commerceInventoryWarehouseGroupRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchInventoryWarehouseGroupRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commerceInventoryWarehouseGroupRel);
		}
		catch (NoSuchInventoryWarehouseGroupRelException nsee) {
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
	protected CommerceInventoryWarehouseGroupRel removeImpl(
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceInventoryWarehouseGroupRel)) {
				commerceInventoryWarehouseGroupRel = (CommerceInventoryWarehouseGroupRel)session.get(CommerceInventoryWarehouseGroupRelImpl.class,
						commerceInventoryWarehouseGroupRel.getPrimaryKeyObj());
			}

			if (commerceInventoryWarehouseGroupRel != null) {
				session.delete(commerceInventoryWarehouseGroupRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceInventoryWarehouseGroupRel != null) {
			clearCache(commerceInventoryWarehouseGroupRel);
		}

		return commerceInventoryWarehouseGroupRel;
	}

	@Override
	public CommerceInventoryWarehouseGroupRel updateImpl(
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel) {
		boolean isNew = commerceInventoryWarehouseGroupRel.isNew();

		if (!(commerceInventoryWarehouseGroupRel instanceof CommerceInventoryWarehouseGroupRelModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(
						commerceInventoryWarehouseGroupRel.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(commerceInventoryWarehouseGroupRel);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceInventoryWarehouseGroupRel proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceInventoryWarehouseGroupRel implementation " +
				commerceInventoryWarehouseGroupRel.getClass());
		}

		CommerceInventoryWarehouseGroupRelModelImpl commerceInventoryWarehouseGroupRelModelImpl =
			(CommerceInventoryWarehouseGroupRelModelImpl)commerceInventoryWarehouseGroupRel;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew &&
				(commerceInventoryWarehouseGroupRel.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceInventoryWarehouseGroupRel.setCreateDate(now);
			}
			else {
				commerceInventoryWarehouseGroupRel.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!commerceInventoryWarehouseGroupRelModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceInventoryWarehouseGroupRel.setModifiedDate(now);
			}
			else {
				commerceInventoryWarehouseGroupRel.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceInventoryWarehouseGroupRel.isNew()) {
				session.save(commerceInventoryWarehouseGroupRel);

				commerceInventoryWarehouseGroupRel.setNew(false);
			}
			else {
				commerceInventoryWarehouseGroupRel = (CommerceInventoryWarehouseGroupRel)session.merge(commerceInventoryWarehouseGroupRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceInventoryWarehouseGroupRelModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					commerceInventoryWarehouseGroupRelModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] {
					commerceInventoryWarehouseGroupRelModelImpl.getGroupId(),
					commerceInventoryWarehouseGroupRelModelImpl.isPrimary()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_P, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((commerceInventoryWarehouseGroupRelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceInventoryWarehouseGroupRelModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] {
						commerceInventoryWarehouseGroupRelModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((commerceInventoryWarehouseGroupRelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceInventoryWarehouseGroupRelModelImpl.getOriginalGroupId(),
						commerceInventoryWarehouseGroupRelModelImpl.getOriginalPrimary()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_P, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P,
					args);

				args = new Object[] {
						commerceInventoryWarehouseGroupRelModelImpl.getGroupId(),
						commerceInventoryWarehouseGroupRelModelImpl.isPrimary()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_P, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P,
					args);
			}
		}

		entityCache.putResult(CommerceInventoryWarehouseGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryWarehouseGroupRelImpl.class,
			commerceInventoryWarehouseGroupRel.getPrimaryKey(),
			commerceInventoryWarehouseGroupRel, false);

		clearUniqueFindersCache(commerceInventoryWarehouseGroupRelModelImpl,
			false);
		cacheUniqueFindersCache(commerceInventoryWarehouseGroupRelModelImpl);

		commerceInventoryWarehouseGroupRel.resetOriginalValues();

		return commerceInventoryWarehouseGroupRel;
	}

	/**
	 * Returns the commerce inventory warehouse group rel with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce inventory warehouse group rel
	 * @return the commerce inventory warehouse group rel
	 * @throws NoSuchInventoryWarehouseGroupRelException if a commerce inventory warehouse group rel with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel findByPrimaryKey(
		Serializable primaryKey)
		throws NoSuchInventoryWarehouseGroupRelException {
		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel = fetchByPrimaryKey(primaryKey);

		if (commerceInventoryWarehouseGroupRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchInventoryWarehouseGroupRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commerceInventoryWarehouseGroupRel;
	}

	/**
	 * Returns the commerce inventory warehouse group rel with the primary key or throws a {@link NoSuchInventoryWarehouseGroupRelException} if it could not be found.
	 *
	 * @param commerceInventoryWarehouseGroupRelId the primary key of the commerce inventory warehouse group rel
	 * @return the commerce inventory warehouse group rel
	 * @throws NoSuchInventoryWarehouseGroupRelException if a commerce inventory warehouse group rel with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel findByPrimaryKey(
		long commerceInventoryWarehouseGroupRelId)
		throws NoSuchInventoryWarehouseGroupRelException {
		return findByPrimaryKey((Serializable)commerceInventoryWarehouseGroupRelId);
	}

	/**
	 * Returns the commerce inventory warehouse group rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce inventory warehouse group rel
	 * @return the commerce inventory warehouse group rel, or <code>null</code> if a commerce inventory warehouse group rel with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel fetchByPrimaryKey(
		Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CommerceInventoryWarehouseGroupRelModelImpl.ENTITY_CACHE_ENABLED,
				CommerceInventoryWarehouseGroupRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel = (CommerceInventoryWarehouseGroupRel)serializable;

		if (commerceInventoryWarehouseGroupRel == null) {
			Session session = null;

			try {
				session = openSession();

				commerceInventoryWarehouseGroupRel = (CommerceInventoryWarehouseGroupRel)session.get(CommerceInventoryWarehouseGroupRelImpl.class,
						primaryKey);

				if (commerceInventoryWarehouseGroupRel != null) {
					cacheResult(commerceInventoryWarehouseGroupRel);
				}
				else {
					entityCache.putResult(CommerceInventoryWarehouseGroupRelModelImpl.ENTITY_CACHE_ENABLED,
						CommerceInventoryWarehouseGroupRelImpl.class,
						primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommerceInventoryWarehouseGroupRelModelImpl.ENTITY_CACHE_ENABLED,
					CommerceInventoryWarehouseGroupRelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceInventoryWarehouseGroupRel;
	}

	/**
	 * Returns the commerce inventory warehouse group rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceInventoryWarehouseGroupRelId the primary key of the commerce inventory warehouse group rel
	 * @return the commerce inventory warehouse group rel, or <code>null</code> if a commerce inventory warehouse group rel with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseGroupRel fetchByPrimaryKey(
		long commerceInventoryWarehouseGroupRelId) {
		return fetchByPrimaryKey((Serializable)commerceInventoryWarehouseGroupRelId);
	}

	@Override
	public Map<Serializable, CommerceInventoryWarehouseGroupRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceInventoryWarehouseGroupRel> map = new HashMap<Serializable, CommerceInventoryWarehouseGroupRel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel =
				fetchByPrimaryKey(primaryKey);

			if (commerceInventoryWarehouseGroupRel != null) {
				map.put(primaryKey, commerceInventoryWarehouseGroupRel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CommerceInventoryWarehouseGroupRelModelImpl.ENTITY_CACHE_ENABLED,
					CommerceInventoryWarehouseGroupRelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey,
						(CommerceInventoryWarehouseGroupRel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEGROUPREL_WHERE_PKS_IN);

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

			for (CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel : (List<CommerceInventoryWarehouseGroupRel>)q.list()) {
				map.put(commerceInventoryWarehouseGroupRel.getPrimaryKeyObj(),
					commerceInventoryWarehouseGroupRel);

				cacheResult(commerceInventoryWarehouseGroupRel);

				uncachedPrimaryKeys.remove(commerceInventoryWarehouseGroupRel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CommerceInventoryWarehouseGroupRelModelImpl.ENTITY_CACHE_ENABLED,
					CommerceInventoryWarehouseGroupRelImpl.class, primaryKey,
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
	 * Returns all the commerce inventory warehouse group rels.
	 *
	 * @return the commerce inventory warehouse group rels
	 */
	@Override
	public List<CommerceInventoryWarehouseGroupRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory warehouse group rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory warehouse group rels
	 * @param end the upper bound of the range of commerce inventory warehouse group rels (not inclusive)
	 * @return the range of commerce inventory warehouse group rels
	 */
	@Override
	public List<CommerceInventoryWarehouseGroupRel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse group rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory warehouse group rels
	 * @param end the upper bound of the range of commerce inventory warehouse group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce inventory warehouse group rels
	 */
	@Override
	public List<CommerceInventoryWarehouseGroupRel> findAll(int start, int end,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse group rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceInventoryWarehouseGroupRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory warehouse group rels
	 * @param end the upper bound of the range of commerce inventory warehouse group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce inventory warehouse group rels
	 */
	@Override
	public List<CommerceInventoryWarehouseGroupRel> findAll(int start, int end,
		OrderByComparator<CommerceInventoryWarehouseGroupRel> orderByComparator,
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

		List<CommerceInventoryWarehouseGroupRel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceInventoryWarehouseGroupRel>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEGROUPREL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEINVENTORYWAREHOUSEGROUPREL;

				if (pagination) {
					sql = sql.concat(CommerceInventoryWarehouseGroupRelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceInventoryWarehouseGroupRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceInventoryWarehouseGroupRel>)QueryUtil.list(q,
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
	 * Removes all the commerce inventory warehouse group rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel : findAll()) {
			remove(commerceInventoryWarehouseGroupRel);
		}
	}

	/**
	 * Returns the number of commerce inventory warehouse group rels.
	 *
	 * @return the number of commerce inventory warehouse group rels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCEINVENTORYWAREHOUSEGROUPREL);

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
		return CommerceInventoryWarehouseGroupRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce inventory warehouse group rel persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CommerceInventoryWarehouseGroupRelImpl.class.getName());
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
	private static final String _SQL_SELECT_COMMERCEINVENTORYWAREHOUSEGROUPREL = "SELECT commerceInventoryWarehouseGroupRel FROM CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel";
	private static final String _SQL_SELECT_COMMERCEINVENTORYWAREHOUSEGROUPREL_WHERE_PKS_IN =
		"SELECT commerceInventoryWarehouseGroupRel FROM CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel WHERE CIWarehouseGroupRelId IN (";
	private static final String _SQL_SELECT_COMMERCEINVENTORYWAREHOUSEGROUPREL_WHERE =
		"SELECT commerceInventoryWarehouseGroupRel FROM CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel WHERE ";
	private static final String _SQL_COUNT_COMMERCEINVENTORYWAREHOUSEGROUPREL = "SELECT COUNT(commerceInventoryWarehouseGroupRel) FROM CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel";
	private static final String _SQL_COUNT_COMMERCEINVENTORYWAREHOUSEGROUPREL_WHERE =
		"SELECT COUNT(commerceInventoryWarehouseGroupRel) FROM CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceInventoryWarehouseGroupRel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommerceInventoryWarehouseGroupRel exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CommerceInventoryWarehouseGroupRel exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CommerceInventoryWarehouseGroupRelPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"commerceInventoryWarehouseGroupRelId", "primary"
			});
}