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

package com.liferay.commerce.account.service.persistence.impl;

import com.liferay.commerce.account.exception.NoSuchAccountGroupException;
import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.model.impl.CommerceAccountGroupImpl;
import com.liferay.commerce.account.model.impl.CommerceAccountGroupModelImpl;
import com.liferay.commerce.account.service.persistence.CommerceAccountGroupPersistence;
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
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Arrays;
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
 * The persistence implementation for the commerce account group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @generated
 */
public class CommerceAccountGroupPersistenceImpl
	extends BasePersistenceImpl<CommerceAccountGroup>
	implements CommerceAccountGroupPersistence {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommerceAccountGroupUtil</code> to access the commerce account group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommerceAccountGroupImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByCommerceAccountGroupIds;
	private FinderPath
		_finderPathWithoutPaginationFindByCommerceAccountGroupIds;
	private FinderPath _finderPathCountByCommerceAccountGroupIds;
	private FinderPath _finderPathWithPaginationCountByCommerceAccountGroupIds;

	/**
	 * Returns all the commerce account groups where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @return the matching commerce account groups
	 */
	@Override
	public List<CommerceAccountGroup> findByCommerceAccountGroupIds(
		long commerceAccountGroupId) {

		return findByCommerceAccountGroupIds(
			commerceAccountGroupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce account groups where commerceAccountGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param start the lower bound of the range of commerce account groups
	 * @param end the upper bound of the range of commerce account groups (not inclusive)
	 * @return the range of matching commerce account groups
	 */
	@Override
	public List<CommerceAccountGroup> findByCommerceAccountGroupIds(
		long commerceAccountGroupId, int start, int end) {

		return findByCommerceAccountGroupIds(
			commerceAccountGroupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce account groups where commerceAccountGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param start the lower bound of the range of commerce account groups
	 * @param end the upper bound of the range of commerce account groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce account groups
	 */
	@Override
	public List<CommerceAccountGroup> findByCommerceAccountGroupIds(
		long commerceAccountGroupId, int start, int end,
		OrderByComparator<CommerceAccountGroup> orderByComparator) {

		return findByCommerceAccountGroupIds(
			commerceAccountGroupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce account groups where commerceAccountGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param start the lower bound of the range of commerce account groups
	 * @param end the upper bound of the range of commerce account groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce account groups
	 */
	@Override
	public List<CommerceAccountGroup> findByCommerceAccountGroupIds(
		long commerceAccountGroupId, int start, int end,
		OrderByComparator<CommerceAccountGroup> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByCommerceAccountGroupIds;
				finderArgs = new Object[] {commerceAccountGroupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCommerceAccountGroupIds;
			finderArgs = new Object[] {
				commerceAccountGroupId, start, end, orderByComparator
			};
		}

		List<CommerceAccountGroup> list = null;

		if (useFinderCache) {
			list = (List<CommerceAccountGroup>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceAccountGroup commerceAccountGroup : list) {
					if ((commerceAccountGroupId !=
							commerceAccountGroup.getCommerceAccountGroupId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_COMMERCEACCOUNTGROUP_WHERE);

			query.append(
				_FINDER_COLUMN_COMMERCEACCOUNTGROUPIDS_COMMERCEACCOUNTGROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceAccountGroupModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceAccountGroupId);

				if (!pagination) {
					list = (List<CommerceAccountGroup>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceAccountGroup>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first commerce account group in the ordered set where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce account group
	 * @throws NoSuchAccountGroupException if a matching commerce account group could not be found
	 */
	@Override
	public CommerceAccountGroup findByCommerceAccountGroupIds_First(
			long commerceAccountGroupId,
			OrderByComparator<CommerceAccountGroup> orderByComparator)
		throws NoSuchAccountGroupException {

		CommerceAccountGroup commerceAccountGroup =
			fetchByCommerceAccountGroupIds_First(
				commerceAccountGroupId, orderByComparator);

		if (commerceAccountGroup != null) {
			return commerceAccountGroup;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceAccountGroupId=");
		msg.append(commerceAccountGroupId);

		msg.append("}");

		throw new NoSuchAccountGroupException(msg.toString());
	}

	/**
	 * Returns the first commerce account group in the ordered set where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce account group, or <code>null</code> if a matching commerce account group could not be found
	 */
	@Override
	public CommerceAccountGroup fetchByCommerceAccountGroupIds_First(
		long commerceAccountGroupId,
		OrderByComparator<CommerceAccountGroup> orderByComparator) {

		List<CommerceAccountGroup> list = findByCommerceAccountGroupIds(
			commerceAccountGroupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce account group in the ordered set where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce account group
	 * @throws NoSuchAccountGroupException if a matching commerce account group could not be found
	 */
	@Override
	public CommerceAccountGroup findByCommerceAccountGroupIds_Last(
			long commerceAccountGroupId,
			OrderByComparator<CommerceAccountGroup> orderByComparator)
		throws NoSuchAccountGroupException {

		CommerceAccountGroup commerceAccountGroup =
			fetchByCommerceAccountGroupIds_Last(
				commerceAccountGroupId, orderByComparator);

		if (commerceAccountGroup != null) {
			return commerceAccountGroup;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceAccountGroupId=");
		msg.append(commerceAccountGroupId);

		msg.append("}");

		throw new NoSuchAccountGroupException(msg.toString());
	}

	/**
	 * Returns the last commerce account group in the ordered set where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce account group, or <code>null</code> if a matching commerce account group could not be found
	 */
	@Override
	public CommerceAccountGroup fetchByCommerceAccountGroupIds_Last(
		long commerceAccountGroupId,
		OrderByComparator<CommerceAccountGroup> orderByComparator) {

		int count = countByCommerceAccountGroupIds(commerceAccountGroupId);

		if (count == 0) {
			return null;
		}

		List<CommerceAccountGroup> list = findByCommerceAccountGroupIds(
			commerceAccountGroupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns all the commerce account groups that the user has permission to view where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @return the matching commerce account groups that the user has permission to view
	 */
	@Override
	public List<CommerceAccountGroup> filterFindByCommerceAccountGroupIds(
		long commerceAccountGroupId) {

		return filterFindByCommerceAccountGroupIds(
			commerceAccountGroupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce account groups that the user has permission to view where commerceAccountGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param start the lower bound of the range of commerce account groups
	 * @param end the upper bound of the range of commerce account groups (not inclusive)
	 * @return the range of matching commerce account groups that the user has permission to view
	 */
	@Override
	public List<CommerceAccountGroup> filterFindByCommerceAccountGroupIds(
		long commerceAccountGroupId, int start, int end) {

		return filterFindByCommerceAccountGroupIds(
			commerceAccountGroupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce account groups that the user has permissions to view where commerceAccountGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param start the lower bound of the range of commerce account groups
	 * @param end the upper bound of the range of commerce account groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce account groups that the user has permission to view
	 */
	@Override
	public List<CommerceAccountGroup> filterFindByCommerceAccountGroupIds(
		long commerceAccountGroupId, int start, int end,
		OrderByComparator<CommerceAccountGroup> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByCommerceAccountGroupIds(
				commerceAccountGroupId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				3 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEACCOUNTGROUP_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEACCOUNTGROUP_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(
			_FINDER_COLUMN_COMMERCEACCOUNTGROUPIDS_COMMERCEACCOUNTGROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEACCOUNTGROUP_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CommerceAccountGroupModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceAccountGroupModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceAccountGroup.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(
					_FILTER_ENTITY_ALIAS, CommerceAccountGroupImpl.class);
			}
			else {
				q.addEntity(
					_FILTER_ENTITY_TABLE, CommerceAccountGroupImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(commerceAccountGroupId);

			return (List<CommerceAccountGroup>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the commerce account groups that the user has permission to view where commerceAccountGroupId = any &#63;.
	 *
	 * @param commerceAccountGroupIds the commerce account group IDs
	 * @return the matching commerce account groups that the user has permission to view
	 */
	@Override
	public List<CommerceAccountGroup> filterFindByCommerceAccountGroupIds(
		long[] commerceAccountGroupIds) {

		return filterFindByCommerceAccountGroupIds(
			commerceAccountGroupIds, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the commerce account groups that the user has permission to view where commerceAccountGroupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountGroupIds the commerce account group IDs
	 * @param start the lower bound of the range of commerce account groups
	 * @param end the upper bound of the range of commerce account groups (not inclusive)
	 * @return the range of matching commerce account groups that the user has permission to view
	 */
	@Override
	public List<CommerceAccountGroup> filterFindByCommerceAccountGroupIds(
		long[] commerceAccountGroupIds, int start, int end) {

		return filterFindByCommerceAccountGroupIds(
			commerceAccountGroupIds, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce account groups that the user has permission to view where commerceAccountGroupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountGroupIds the commerce account group IDs
	 * @param start the lower bound of the range of commerce account groups
	 * @param end the upper bound of the range of commerce account groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce account groups that the user has permission to view
	 */
	@Override
	public List<CommerceAccountGroup> filterFindByCommerceAccountGroupIds(
		long[] commerceAccountGroupIds, int start, int end,
		OrderByComparator<CommerceAccountGroup> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByCommerceAccountGroupIds(
				commerceAccountGroupIds, start, end, orderByComparator);
		}

		if (commerceAccountGroupIds == null) {
			commerceAccountGroupIds = new long[0];
		}
		else if (commerceAccountGroupIds.length > 1) {
			commerceAccountGroupIds = ArrayUtil.unique(commerceAccountGroupIds);

			Arrays.sort(commerceAccountGroupIds);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEACCOUNTGROUP_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEACCOUNTGROUP_NO_INLINE_DISTINCT_WHERE_1);
		}

		if (commerceAccountGroupIds.length > 0) {
			query.append("(");

			query.append(
				_FINDER_COLUMN_COMMERCEACCOUNTGROUPIDS_COMMERCEACCOUNTGROUPID_7);

			query.append(StringUtil.merge(commerceAccountGroupIds));

			query.append(")");

			query.append(")");
		}

		query.setStringAt(
			removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEACCOUNTGROUP_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CommerceAccountGroupModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceAccountGroupModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceAccountGroup.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(
					_FILTER_ENTITY_ALIAS, CommerceAccountGroupImpl.class);
			}
			else {
				q.addEntity(
					_FILTER_ENTITY_TABLE, CommerceAccountGroupImpl.class);
			}

			return (List<CommerceAccountGroup>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the commerce account groups where commerceAccountGroupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountGroupIds the commerce account group IDs
	 * @return the matching commerce account groups
	 */
	@Override
	public List<CommerceAccountGroup> findByCommerceAccountGroupIds(
		long[] commerceAccountGroupIds) {

		return findByCommerceAccountGroupIds(
			commerceAccountGroupIds, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the commerce account groups where commerceAccountGroupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountGroupIds the commerce account group IDs
	 * @param start the lower bound of the range of commerce account groups
	 * @param end the upper bound of the range of commerce account groups (not inclusive)
	 * @return the range of matching commerce account groups
	 */
	@Override
	public List<CommerceAccountGroup> findByCommerceAccountGroupIds(
		long[] commerceAccountGroupIds, int start, int end) {

		return findByCommerceAccountGroupIds(
			commerceAccountGroupIds, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce account groups where commerceAccountGroupId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountGroupIds the commerce account group IDs
	 * @param start the lower bound of the range of commerce account groups
	 * @param end the upper bound of the range of commerce account groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce account groups
	 */
	@Override
	public List<CommerceAccountGroup> findByCommerceAccountGroupIds(
		long[] commerceAccountGroupIds, int start, int end,
		OrderByComparator<CommerceAccountGroup> orderByComparator) {

		return findByCommerceAccountGroupIds(
			commerceAccountGroupIds, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce account groups where commerceAccountGroupId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param start the lower bound of the range of commerce account groups
	 * @param end the upper bound of the range of commerce account groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce account groups
	 */
	@Override
	public List<CommerceAccountGroup> findByCommerceAccountGroupIds(
		long[] commerceAccountGroupIds, int start, int end,
		OrderByComparator<CommerceAccountGroup> orderByComparator,
		boolean useFinderCache) {

		if (commerceAccountGroupIds == null) {
			commerceAccountGroupIds = new long[0];
		}
		else if (commerceAccountGroupIds.length > 1) {
			commerceAccountGroupIds = ArrayUtil.unique(commerceAccountGroupIds);

			Arrays.sort(commerceAccountGroupIds);
		}

		if (commerceAccountGroupIds.length == 1) {
			return findByCommerceAccountGroupIds(
				commerceAccountGroupIds[0], start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderArgs = new Object[] {
					StringUtil.merge(commerceAccountGroupIds)
				};
			}
		}
		else if (useFinderCache) {
			finderArgs = new Object[] {
				StringUtil.merge(commerceAccountGroupIds), start, end,
				orderByComparator
			};
		}

		List<CommerceAccountGroup> list = null;

		if (useFinderCache) {
			list = (List<CommerceAccountGroup>)finderCache.getResult(
				_finderPathWithPaginationFindByCommerceAccountGroupIds,
				finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceAccountGroup commerceAccountGroup : list) {
					if (!ArrayUtil.contains(
							commerceAccountGroupIds,
							commerceAccountGroup.getCommerceAccountGroupId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_COMMERCEACCOUNTGROUP_WHERE);

			if (commerceAccountGroupIds.length > 0) {
				query.append("(");

				query.append(
					_FINDER_COLUMN_COMMERCEACCOUNTGROUPIDS_COMMERCEACCOUNTGROUPID_7);

				query.append(StringUtil.merge(commerceAccountGroupIds));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(
				removeConjunction(query.stringAt(query.index() - 1)),
				query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceAccountGroupModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceAccountGroup>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceAccountGroup>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(
						_finderPathWithPaginationFindByCommerceAccountGroupIds,
						finderArgs, list);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathWithPaginationFindByCommerceAccountGroupIds,
						finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the commerce account groups where commerceAccountGroupId = &#63; from the database.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 */
	@Override
	public void removeByCommerceAccountGroupIds(long commerceAccountGroupId) {
		for (CommerceAccountGroup commerceAccountGroup :
				findByCommerceAccountGroupIds(
					commerceAccountGroupId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(commerceAccountGroup);
		}
	}

	/**
	 * Returns the number of commerce account groups where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @return the number of matching commerce account groups
	 */
	@Override
	public int countByCommerceAccountGroupIds(long commerceAccountGroupId) {
		FinderPath finderPath = _finderPathCountByCommerceAccountGroupIds;

		Object[] finderArgs = new Object[] {commerceAccountGroupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEACCOUNTGROUP_WHERE);

			query.append(
				_FINDER_COLUMN_COMMERCEACCOUNTGROUPIDS_COMMERCEACCOUNTGROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceAccountGroupId);

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
	 * Returns the number of commerce account groups where commerceAccountGroupId = any &#63;.
	 *
	 * @param commerceAccountGroupIds the commerce account group IDs
	 * @return the number of matching commerce account groups
	 */
	@Override
	public int countByCommerceAccountGroupIds(long[] commerceAccountGroupIds) {
		if (commerceAccountGroupIds == null) {
			commerceAccountGroupIds = new long[0];
		}
		else if (commerceAccountGroupIds.length > 1) {
			commerceAccountGroupIds = ArrayUtil.unique(commerceAccountGroupIds);

			Arrays.sort(commerceAccountGroupIds);
		}

		Object[] finderArgs = new Object[] {
			StringUtil.merge(commerceAccountGroupIds)
		};

		Long count = (Long)finderCache.getResult(
			_finderPathWithPaginationCountByCommerceAccountGroupIds, finderArgs,
			this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_COMMERCEACCOUNTGROUP_WHERE);

			if (commerceAccountGroupIds.length > 0) {
				query.append("(");

				query.append(
					_FINDER_COLUMN_COMMERCEACCOUNTGROUPIDS_COMMERCEACCOUNTGROUPID_7);

				query.append(StringUtil.merge(commerceAccountGroupIds));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(
				removeConjunction(query.stringAt(query.index() - 1)),
				query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathWithPaginationCountByCommerceAccountGroupIds,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathWithPaginationCountByCommerceAccountGroupIds,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of commerce account groups that the user has permission to view where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @return the number of matching commerce account groups that the user has permission to view
	 */
	@Override
	public int filterCountByCommerceAccountGroupIds(
		long commerceAccountGroupId) {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByCommerceAccountGroupIds(commerceAccountGroupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_COMMERCEACCOUNTGROUP_WHERE);

		query.append(
			_FINDER_COLUMN_COMMERCEACCOUNTGROUPIDS_COMMERCEACCOUNTGROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceAccountGroup.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(commerceAccountGroupId);

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

	/**
	 * Returns the number of commerce account groups that the user has permission to view where commerceAccountGroupId = any &#63;.
	 *
	 * @param commerceAccountGroupIds the commerce account group IDs
	 * @return the number of matching commerce account groups that the user has permission to view
	 */
	@Override
	public int filterCountByCommerceAccountGroupIds(
		long[] commerceAccountGroupIds) {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByCommerceAccountGroupIds(commerceAccountGroupIds);
		}

		if (commerceAccountGroupIds == null) {
			commerceAccountGroupIds = new long[0];
		}
		else if (commerceAccountGroupIds.length > 1) {
			commerceAccountGroupIds = ArrayUtil.unique(commerceAccountGroupIds);

			Arrays.sort(commerceAccountGroupIds);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_COMMERCEACCOUNTGROUP_WHERE);

		if (commerceAccountGroupIds.length > 0) {
			query.append("(");

			query.append(
				_FINDER_COLUMN_COMMERCEACCOUNTGROUPIDS_COMMERCEACCOUNTGROUPID_7);

			query.append(StringUtil.merge(commerceAccountGroupIds));

			query.append(")");

			query.append(")");
		}

		query.setStringAt(
			removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceAccountGroup.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

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

	private static final String
		_FINDER_COLUMN_COMMERCEACCOUNTGROUPIDS_COMMERCEACCOUNTGROUPID_2 =
			"commerceAccountGroup.commerceAccountGroupId = ?";

	private static final String
		_FINDER_COLUMN_COMMERCEACCOUNTGROUPIDS_COMMERCEACCOUNTGROUPID_7 =
			"commerceAccountGroup.commerceAccountGroupId IN (";

	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the commerce account groups where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce account groups
	 */
	@Override
	public List<CommerceAccountGroup> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce account groups where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce account groups
	 * @param end the upper bound of the range of commerce account groups (not inclusive)
	 * @return the range of matching commerce account groups
	 */
	@Override
	public List<CommerceAccountGroup> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce account groups where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce account groups
	 * @param end the upper bound of the range of commerce account groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce account groups
	 */
	@Override
	public List<CommerceAccountGroup> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceAccountGroup> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce account groups where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce account groups
	 * @param end the upper bound of the range of commerce account groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce account groups
	 */
	@Override
	public List<CommerceAccountGroup> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceAccountGroup> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByCompanyId;
				finderArgs = new Object[] {companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCompanyId;
			finderArgs = new Object[] {
				companyId, start, end, orderByComparator
			};
		}

		List<CommerceAccountGroup> list = null;

		if (useFinderCache) {
			list = (List<CommerceAccountGroup>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceAccountGroup commerceAccountGroup : list) {
					if ((companyId != commerceAccountGroup.getCompanyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_COMMERCEACCOUNTGROUP_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceAccountGroupModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<CommerceAccountGroup>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceAccountGroup>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first commerce account group in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce account group
	 * @throws NoSuchAccountGroupException if a matching commerce account group could not be found
	 */
	@Override
	public CommerceAccountGroup findByCompanyId_First(
			long companyId,
			OrderByComparator<CommerceAccountGroup> orderByComparator)
		throws NoSuchAccountGroupException {

		CommerceAccountGroup commerceAccountGroup = fetchByCompanyId_First(
			companyId, orderByComparator);

		if (commerceAccountGroup != null) {
			return commerceAccountGroup;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchAccountGroupException(msg.toString());
	}

	/**
	 * Returns the first commerce account group in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce account group, or <code>null</code> if a matching commerce account group could not be found
	 */
	@Override
	public CommerceAccountGroup fetchByCompanyId_First(
		long companyId,
		OrderByComparator<CommerceAccountGroup> orderByComparator) {

		List<CommerceAccountGroup> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce account group in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce account group
	 * @throws NoSuchAccountGroupException if a matching commerce account group could not be found
	 */
	@Override
	public CommerceAccountGroup findByCompanyId_Last(
			long companyId,
			OrderByComparator<CommerceAccountGroup> orderByComparator)
		throws NoSuchAccountGroupException {

		CommerceAccountGroup commerceAccountGroup = fetchByCompanyId_Last(
			companyId, orderByComparator);

		if (commerceAccountGroup != null) {
			return commerceAccountGroup;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchAccountGroupException(msg.toString());
	}

	/**
	 * Returns the last commerce account group in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce account group, or <code>null</code> if a matching commerce account group could not be found
	 */
	@Override
	public CommerceAccountGroup fetchByCompanyId_Last(
		long companyId,
		OrderByComparator<CommerceAccountGroup> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<CommerceAccountGroup> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce account groups before and after the current commerce account group in the ordered set where companyId = &#63;.
	 *
	 * @param commerceAccountGroupId the primary key of the current commerce account group
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce account group
	 * @throws NoSuchAccountGroupException if a commerce account group with the primary key could not be found
	 */
	@Override
	public CommerceAccountGroup[] findByCompanyId_PrevAndNext(
			long commerceAccountGroupId, long companyId,
			OrderByComparator<CommerceAccountGroup> orderByComparator)
		throws NoSuchAccountGroupException {

		CommerceAccountGroup commerceAccountGroup = findByPrimaryKey(
			commerceAccountGroupId);

		Session session = null;

		try {
			session = openSession();

			CommerceAccountGroup[] array = new CommerceAccountGroupImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, commerceAccountGroup, companyId, orderByComparator,
				true);

			array[1] = commerceAccountGroup;

			array[2] = getByCompanyId_PrevAndNext(
				session, commerceAccountGroup, companyId, orderByComparator,
				false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceAccountGroup getByCompanyId_PrevAndNext(
		Session session, CommerceAccountGroup commerceAccountGroup,
		long companyId,
		OrderByComparator<CommerceAccountGroup> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COMMERCEACCOUNTGROUP_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

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
			query.append(CommerceAccountGroupModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceAccountGroup)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceAccountGroup> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the commerce account groups that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce account groups that the user has permission to view
	 */
	@Override
	public List<CommerceAccountGroup> filterFindByCompanyId(long companyId) {
		return filterFindByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce account groups that the user has permission to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce account groups
	 * @param end the upper bound of the range of commerce account groups (not inclusive)
	 * @return the range of matching commerce account groups that the user has permission to view
	 */
	@Override
	public List<CommerceAccountGroup> filterFindByCompanyId(
		long companyId, int start, int end) {

		return filterFindByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce account groups that the user has permissions to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce account groups
	 * @param end the upper bound of the range of commerce account groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce account groups that the user has permission to view
	 */
	@Override
	public List<CommerceAccountGroup> filterFindByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceAccountGroup> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByCompanyId(companyId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				3 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEACCOUNTGROUP_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEACCOUNTGROUP_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEACCOUNTGROUP_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CommerceAccountGroupModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceAccountGroupModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceAccountGroup.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(
					_FILTER_ENTITY_ALIAS, CommerceAccountGroupImpl.class);
			}
			else {
				q.addEntity(
					_FILTER_ENTITY_TABLE, CommerceAccountGroupImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			return (List<CommerceAccountGroup>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the commerce account groups before and after the current commerce account group in the ordered set of commerce account groups that the user has permission to view where companyId = &#63;.
	 *
	 * @param commerceAccountGroupId the primary key of the current commerce account group
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce account group
	 * @throws NoSuchAccountGroupException if a commerce account group with the primary key could not be found
	 */
	@Override
	public CommerceAccountGroup[] filterFindByCompanyId_PrevAndNext(
			long commerceAccountGroupId, long companyId,
			OrderByComparator<CommerceAccountGroup> orderByComparator)
		throws NoSuchAccountGroupException {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByCompanyId_PrevAndNext(
				commerceAccountGroupId, companyId, orderByComparator);
		}

		CommerceAccountGroup commerceAccountGroup = findByPrimaryKey(
			commerceAccountGroupId);

		Session session = null;

		try {
			session = openSession();

			CommerceAccountGroup[] array = new CommerceAccountGroupImpl[3];

			array[0] = filterGetByCompanyId_PrevAndNext(
				session, commerceAccountGroup, companyId, orderByComparator,
				true);

			array[1] = commerceAccountGroup;

			array[2] = filterGetByCompanyId_PrevAndNext(
				session, commerceAccountGroup, companyId, orderByComparator,
				false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceAccountGroup filterGetByCompanyId_PrevAndNext(
		Session session, CommerceAccountGroup commerceAccountGroup,
		long companyId,
		OrderByComparator<CommerceAccountGroup> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEACCOUNTGROUP_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEACCOUNTGROUP_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEACCOUNTGROUP_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
				query.append(CommerceAccountGroupModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceAccountGroupModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceAccountGroup.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CommerceAccountGroupImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CommerceAccountGroupImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceAccountGroup)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceAccountGroup> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce account groups where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (CommerceAccountGroup commerceAccountGroup :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commerceAccountGroup);
		}
	}

	/**
	 * Returns the number of commerce account groups where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce account groups
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] {companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEACCOUNTGROUP_WHERE);

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

	/**
	 * Returns the number of commerce account groups that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce account groups that the user has permission to view
	 */
	@Override
	public int filterCountByCompanyId(long companyId) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return countByCompanyId(companyId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_COMMERCEACCOUNTGROUP_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceAccountGroup.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 =
		"commerceAccountGroup.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByC_T;
	private FinderPath _finderPathWithoutPaginationFindByC_T;
	private FinderPath _finderPathCountByC_T;

	/**
	 * Returns all the commerce account groups where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @return the matching commerce account groups
	 */
	@Override
	public List<CommerceAccountGroup> findByC_T(long companyId, int type) {
		return findByC_T(
			companyId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce account groups where companyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param start the lower bound of the range of commerce account groups
	 * @param end the upper bound of the range of commerce account groups (not inclusive)
	 * @return the range of matching commerce account groups
	 */
	@Override
	public List<CommerceAccountGroup> findByC_T(
		long companyId, int type, int start, int end) {

		return findByC_T(companyId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce account groups where companyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param start the lower bound of the range of commerce account groups
	 * @param end the upper bound of the range of commerce account groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce account groups
	 */
	@Override
	public List<CommerceAccountGroup> findByC_T(
		long companyId, int type, int start, int end,
		OrderByComparator<CommerceAccountGroup> orderByComparator) {

		return findByC_T(companyId, type, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce account groups where companyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param start the lower bound of the range of commerce account groups
	 * @param end the upper bound of the range of commerce account groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce account groups
	 */
	@Override
	public List<CommerceAccountGroup> findByC_T(
		long companyId, int type, int start, int end,
		OrderByComparator<CommerceAccountGroup> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByC_T;
				finderArgs = new Object[] {companyId, type};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByC_T;
			finderArgs = new Object[] {
				companyId, type, start, end, orderByComparator
			};
		}

		List<CommerceAccountGroup> list = null;

		if (useFinderCache) {
			list = (List<CommerceAccountGroup>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceAccountGroup commerceAccountGroup : list) {
					if ((companyId != commerceAccountGroup.getCompanyId()) ||
						(type != commerceAccountGroup.getType())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_COMMERCEACCOUNTGROUP_WHERE);

			query.append(_FINDER_COLUMN_C_T_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceAccountGroupModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(type);

				if (!pagination) {
					list = (List<CommerceAccountGroup>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceAccountGroup>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first commerce account group in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce account group
	 * @throws NoSuchAccountGroupException if a matching commerce account group could not be found
	 */
	@Override
	public CommerceAccountGroup findByC_T_First(
			long companyId, int type,
			OrderByComparator<CommerceAccountGroup> orderByComparator)
		throws NoSuchAccountGroupException {

		CommerceAccountGroup commerceAccountGroup = fetchByC_T_First(
			companyId, type, orderByComparator);

		if (commerceAccountGroup != null) {
			return commerceAccountGroup;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchAccountGroupException(msg.toString());
	}

	/**
	 * Returns the first commerce account group in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce account group, or <code>null</code> if a matching commerce account group could not be found
	 */
	@Override
	public CommerceAccountGroup fetchByC_T_First(
		long companyId, int type,
		OrderByComparator<CommerceAccountGroup> orderByComparator) {

		List<CommerceAccountGroup> list = findByC_T(
			companyId, type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce account group in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce account group
	 * @throws NoSuchAccountGroupException if a matching commerce account group could not be found
	 */
	@Override
	public CommerceAccountGroup findByC_T_Last(
			long companyId, int type,
			OrderByComparator<CommerceAccountGroup> orderByComparator)
		throws NoSuchAccountGroupException {

		CommerceAccountGroup commerceAccountGroup = fetchByC_T_Last(
			companyId, type, orderByComparator);

		if (commerceAccountGroup != null) {
			return commerceAccountGroup;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchAccountGroupException(msg.toString());
	}

	/**
	 * Returns the last commerce account group in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce account group, or <code>null</code> if a matching commerce account group could not be found
	 */
	@Override
	public CommerceAccountGroup fetchByC_T_Last(
		long companyId, int type,
		OrderByComparator<CommerceAccountGroup> orderByComparator) {

		int count = countByC_T(companyId, type);

		if (count == 0) {
			return null;
		}

		List<CommerceAccountGroup> list = findByC_T(
			companyId, type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce account groups before and after the current commerce account group in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param commerceAccountGroupId the primary key of the current commerce account group
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce account group
	 * @throws NoSuchAccountGroupException if a commerce account group with the primary key could not be found
	 */
	@Override
	public CommerceAccountGroup[] findByC_T_PrevAndNext(
			long commerceAccountGroupId, long companyId, int type,
			OrderByComparator<CommerceAccountGroup> orderByComparator)
		throws NoSuchAccountGroupException {

		CommerceAccountGroup commerceAccountGroup = findByPrimaryKey(
			commerceAccountGroupId);

		Session session = null;

		try {
			session = openSession();

			CommerceAccountGroup[] array = new CommerceAccountGroupImpl[3];

			array[0] = getByC_T_PrevAndNext(
				session, commerceAccountGroup, companyId, type,
				orderByComparator, true);

			array[1] = commerceAccountGroup;

			array[2] = getByC_T_PrevAndNext(
				session, commerceAccountGroup, companyId, type,
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

	protected CommerceAccountGroup getByC_T_PrevAndNext(
		Session session, CommerceAccountGroup commerceAccountGroup,
		long companyId, int type,
		OrderByComparator<CommerceAccountGroup> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_COMMERCEACCOUNTGROUP_WHERE);

		query.append(_FINDER_COLUMN_C_T_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_T_TYPE_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

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
			query.append(CommerceAccountGroupModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(type);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceAccountGroup)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceAccountGroup> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the commerce account groups that the user has permission to view where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @return the matching commerce account groups that the user has permission to view
	 */
	@Override
	public List<CommerceAccountGroup> filterFindByC_T(
		long companyId, int type) {

		return filterFindByC_T(
			companyId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce account groups that the user has permission to view where companyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param start the lower bound of the range of commerce account groups
	 * @param end the upper bound of the range of commerce account groups (not inclusive)
	 * @return the range of matching commerce account groups that the user has permission to view
	 */
	@Override
	public List<CommerceAccountGroup> filterFindByC_T(
		long companyId, int type, int start, int end) {

		return filterFindByC_T(companyId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce account groups that the user has permissions to view where companyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param start the lower bound of the range of commerce account groups
	 * @param end the upper bound of the range of commerce account groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce account groups that the user has permission to view
	 */
	@Override
	public List<CommerceAccountGroup> filterFindByC_T(
		long companyId, int type, int start, int end,
		OrderByComparator<CommerceAccountGroup> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByC_T(companyId, type, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEACCOUNTGROUP_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEACCOUNTGROUP_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_C_T_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_T_TYPE_2_SQL);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEACCOUNTGROUP_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CommerceAccountGroupModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceAccountGroupModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceAccountGroup.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(
					_FILTER_ENTITY_ALIAS, CommerceAccountGroupImpl.class);
			}
			else {
				q.addEntity(
					_FILTER_ENTITY_TABLE, CommerceAccountGroupImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			qPos.add(type);

			return (List<CommerceAccountGroup>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the commerce account groups before and after the current commerce account group in the ordered set of commerce account groups that the user has permission to view where companyId = &#63; and type = &#63;.
	 *
	 * @param commerceAccountGroupId the primary key of the current commerce account group
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce account group
	 * @throws NoSuchAccountGroupException if a commerce account group with the primary key could not be found
	 */
	@Override
	public CommerceAccountGroup[] filterFindByC_T_PrevAndNext(
			long commerceAccountGroupId, long companyId, int type,
			OrderByComparator<CommerceAccountGroup> orderByComparator)
		throws NoSuchAccountGroupException {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByC_T_PrevAndNext(
				commerceAccountGroupId, companyId, type, orderByComparator);
		}

		CommerceAccountGroup commerceAccountGroup = findByPrimaryKey(
			commerceAccountGroupId);

		Session session = null;

		try {
			session = openSession();

			CommerceAccountGroup[] array = new CommerceAccountGroupImpl[3];

			array[0] = filterGetByC_T_PrevAndNext(
				session, commerceAccountGroup, companyId, type,
				orderByComparator, true);

			array[1] = commerceAccountGroup;

			array[2] = filterGetByC_T_PrevAndNext(
				session, commerceAccountGroup, companyId, type,
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

	protected CommerceAccountGroup filterGetByC_T_PrevAndNext(
		Session session, CommerceAccountGroup commerceAccountGroup,
		long companyId, int type,
		OrderByComparator<CommerceAccountGroup> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEACCOUNTGROUP_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEACCOUNTGROUP_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_C_T_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_T_TYPE_2_SQL);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEACCOUNTGROUP_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
				query.append(CommerceAccountGroupModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceAccountGroupModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceAccountGroup.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CommerceAccountGroupImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CommerceAccountGroupImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(type);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceAccountGroup)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceAccountGroup> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce account groups where companyId = &#63; and type = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 */
	@Override
	public void removeByC_T(long companyId, int type) {
		for (CommerceAccountGroup commerceAccountGroup :
				findByC_T(
					companyId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceAccountGroup);
		}
	}

	/**
	 * Returns the number of commerce account groups where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @return the number of matching commerce account groups
	 */
	@Override
	public int countByC_T(long companyId, int type) {
		FinderPath finderPath = _finderPathCountByC_T;

		Object[] finderArgs = new Object[] {companyId, type};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEACCOUNTGROUP_WHERE);

			query.append(_FINDER_COLUMN_C_T_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(type);

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
	 * Returns the number of commerce account groups that the user has permission to view where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @return the number of matching commerce account groups that the user has permission to view
	 */
	@Override
	public int filterCountByC_T(long companyId, int type) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return countByC_T(companyId, type);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_COMMERCEACCOUNTGROUP_WHERE);

		query.append(_FINDER_COLUMN_C_T_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_T_TYPE_2_SQL);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceAccountGroup.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			qPos.add(type);

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

	private static final String _FINDER_COLUMN_C_T_COMPANYID_2 =
		"commerceAccountGroup.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_T_TYPE_2 =
		"commerceAccountGroup.type = ?";

	private static final String _FINDER_COLUMN_C_T_TYPE_2_SQL =
		"commerceAccountGroup.type_ = ?";

	private FinderPath _finderPathFetchByC_ERC;
	private FinderPath _finderPathCountByC_ERC;

	/**
	 * Returns the commerce account group where companyId = &#63; and externalReferenceCode = &#63; or throws a <code>NoSuchAccountGroupException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce account group
	 * @throws NoSuchAccountGroupException if a matching commerce account group could not be found
	 */
	@Override
	public CommerceAccountGroup findByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchAccountGroupException {

		CommerceAccountGroup commerceAccountGroup = fetchByC_ERC(
			companyId, externalReferenceCode);

		if (commerceAccountGroup == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", externalReferenceCode=");
			msg.append(externalReferenceCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchAccountGroupException(msg.toString());
		}

		return commerceAccountGroup;
	}

	/**
	 * Returns the commerce account group where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce account group, or <code>null</code> if a matching commerce account group could not be found
	 */
	@Override
	public CommerceAccountGroup fetchByC_ERC(
		long companyId, String externalReferenceCode) {

		return fetchByC_ERC(companyId, externalReferenceCode, true);
	}

	/**
	 * Returns the commerce account group where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce account group, or <code>null</code> if a matching commerce account group could not be found
	 */
	@Override
	public CommerceAccountGroup fetchByC_ERC(
		long companyId, String externalReferenceCode, boolean useFinderCache) {

		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {companyId, externalReferenceCode};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_ERC, finderArgs, this);
		}

		if (result instanceof CommerceAccountGroup) {
			CommerceAccountGroup commerceAccountGroup =
				(CommerceAccountGroup)result;

			if ((companyId != commerceAccountGroup.getCompanyId()) ||
				!Objects.equals(
					externalReferenceCode,
					commerceAccountGroup.getExternalReferenceCode())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COMMERCEACCOUNTGROUP_WHERE);

			query.append(_FINDER_COLUMN_C_ERC_COMPANYID_2);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode.isEmpty()) {
				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3);
			}
			else {
				bindExternalReferenceCode = true;

				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindExternalReferenceCode) {
					qPos.add(externalReferenceCode);
				}

				List<CommerceAccountGroup> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByC_ERC, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									companyId, externalReferenceCode
								};
							}

							_log.warn(
								"CommerceAccountGroupPersistenceImpl.fetchByC_ERC(long, String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CommerceAccountGroup commerceAccountGroup = list.get(0);

					result = commerceAccountGroup;

					cacheResult(commerceAccountGroup);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByC_ERC, finderArgs);
				}

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
			return (CommerceAccountGroup)result;
		}
	}

	/**
	 * Removes the commerce account group where companyId = &#63; and externalReferenceCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the commerce account group that was removed
	 */
	@Override
	public CommerceAccountGroup removeByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchAccountGroupException {

		CommerceAccountGroup commerceAccountGroup = findByC_ERC(
			companyId, externalReferenceCode);

		return remove(commerceAccountGroup);
	}

	/**
	 * Returns the number of commerce account groups where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the number of matching commerce account groups
	 */
	@Override
	public int countByC_ERC(long companyId, String externalReferenceCode) {
		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		FinderPath finderPath = _finderPathCountByC_ERC;

		Object[] finderArgs = new Object[] {companyId, externalReferenceCode};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEACCOUNTGROUP_WHERE);

			query.append(_FINDER_COLUMN_C_ERC_COMPANYID_2);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode.isEmpty()) {
				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3);
			}
			else {
				bindExternalReferenceCode = true;

				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindExternalReferenceCode) {
					qPos.add(externalReferenceCode);
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

	private static final String _FINDER_COLUMN_C_ERC_COMPANYID_2 =
		"commerceAccountGroup.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2 =
		"commerceAccountGroup.externalReferenceCode = ?";

	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3 =
		"(commerceAccountGroup.externalReferenceCode IS NULL OR commerceAccountGroup.externalReferenceCode = '')";

	public CommerceAccountGroupPersistenceImpl() {
		setModelClass(CommerceAccountGroup.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("type", "type_");

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
				"_dbColumnNames");

			field.setAccessible(true);

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the commerce account group in the entity cache if it is enabled.
	 *
	 * @param commerceAccountGroup the commerce account group
	 */
	@Override
	public void cacheResult(CommerceAccountGroup commerceAccountGroup) {
		entityCache.putResult(
			CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountGroupImpl.class,
			commerceAccountGroup.getPrimaryKey(), commerceAccountGroup);

		finderCache.putResult(
			_finderPathFetchByC_ERC,
			new Object[] {
				commerceAccountGroup.getCompanyId(),
				commerceAccountGroup.getExternalReferenceCode()
			},
			commerceAccountGroup);

		commerceAccountGroup.resetOriginalValues();
	}

	/**
	 * Caches the commerce account groups in the entity cache if it is enabled.
	 *
	 * @param commerceAccountGroups the commerce account groups
	 */
	@Override
	public void cacheResult(List<CommerceAccountGroup> commerceAccountGroups) {
		for (CommerceAccountGroup commerceAccountGroup :
				commerceAccountGroups) {

			if (entityCache.getResult(
					CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
					CommerceAccountGroupImpl.class,
					commerceAccountGroup.getPrimaryKey()) == null) {

				cacheResult(commerceAccountGroup);
			}
			else {
				commerceAccountGroup.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce account groups.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceAccountGroupImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce account group.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceAccountGroup commerceAccountGroup) {
		entityCache.removeResult(
			CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountGroupImpl.class,
			commerceAccountGroup.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(CommerceAccountGroupModelImpl)commerceAccountGroup, true);
	}

	@Override
	public void clearCache(List<CommerceAccountGroup> commerceAccountGroups) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceAccountGroup commerceAccountGroup :
				commerceAccountGroups) {

			entityCache.removeResult(
				CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
				CommerceAccountGroupImpl.class,
				commerceAccountGroup.getPrimaryKey());

			clearUniqueFindersCache(
				(CommerceAccountGroupModelImpl)commerceAccountGroup, true);
		}
	}

	protected void cacheUniqueFindersCache(
		CommerceAccountGroupModelImpl commerceAccountGroupModelImpl) {

		Object[] args = new Object[] {
			commerceAccountGroupModelImpl.getCompanyId(),
			commerceAccountGroupModelImpl.getExternalReferenceCode()
		};

		finderCache.putResult(
			_finderPathCountByC_ERC, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_ERC, args, commerceAccountGroupModelImpl,
			false);
	}

	protected void clearUniqueFindersCache(
		CommerceAccountGroupModelImpl commerceAccountGroupModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				commerceAccountGroupModelImpl.getCompanyId(),
				commerceAccountGroupModelImpl.getExternalReferenceCode()
			};

			finderCache.removeResult(_finderPathCountByC_ERC, args);
			finderCache.removeResult(_finderPathFetchByC_ERC, args);
		}

		if ((commerceAccountGroupModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_ERC.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				commerceAccountGroupModelImpl.getOriginalCompanyId(),
				commerceAccountGroupModelImpl.getOriginalExternalReferenceCode()
			};

			finderCache.removeResult(_finderPathCountByC_ERC, args);
			finderCache.removeResult(_finderPathFetchByC_ERC, args);
		}
	}

	/**
	 * Creates a new commerce account group with the primary key. Does not add the commerce account group to the database.
	 *
	 * @param commerceAccountGroupId the primary key for the new commerce account group
	 * @return the new commerce account group
	 */
	@Override
	public CommerceAccountGroup create(long commerceAccountGroupId) {
		CommerceAccountGroup commerceAccountGroup =
			new CommerceAccountGroupImpl();

		commerceAccountGroup.setNew(true);
		commerceAccountGroup.setPrimaryKey(commerceAccountGroupId);

		commerceAccountGroup.setCompanyId(CompanyThreadLocal.getCompanyId());

		return commerceAccountGroup;
	}

	/**
	 * Removes the commerce account group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceAccountGroupId the primary key of the commerce account group
	 * @return the commerce account group that was removed
	 * @throws NoSuchAccountGroupException if a commerce account group with the primary key could not be found
	 */
	@Override
	public CommerceAccountGroup remove(long commerceAccountGroupId)
		throws NoSuchAccountGroupException {

		return remove((Serializable)commerceAccountGroupId);
	}

	/**
	 * Removes the commerce account group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce account group
	 * @return the commerce account group that was removed
	 * @throws NoSuchAccountGroupException if a commerce account group with the primary key could not be found
	 */
	@Override
	public CommerceAccountGroup remove(Serializable primaryKey)
		throws NoSuchAccountGroupException {

		Session session = null;

		try {
			session = openSession();

			CommerceAccountGroup commerceAccountGroup =
				(CommerceAccountGroup)session.get(
					CommerceAccountGroupImpl.class, primaryKey);

			if (commerceAccountGroup == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountGroupException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commerceAccountGroup);
		}
		catch (NoSuchAccountGroupException nsee) {
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
	protected CommerceAccountGroup removeImpl(
		CommerceAccountGroup commerceAccountGroup) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceAccountGroup)) {
				commerceAccountGroup = (CommerceAccountGroup)session.get(
					CommerceAccountGroupImpl.class,
					commerceAccountGroup.getPrimaryKeyObj());
			}

			if (commerceAccountGroup != null) {
				session.delete(commerceAccountGroup);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceAccountGroup != null) {
			clearCache(commerceAccountGroup);
		}

		return commerceAccountGroup;
	}

	@Override
	public CommerceAccountGroup updateImpl(
		CommerceAccountGroup commerceAccountGroup) {

		boolean isNew = commerceAccountGroup.isNew();

		if (!(commerceAccountGroup instanceof CommerceAccountGroupModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceAccountGroup.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					commerceAccountGroup);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceAccountGroup proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceAccountGroup implementation " +
					commerceAccountGroup.getClass());
		}

		CommerceAccountGroupModelImpl commerceAccountGroupModelImpl =
			(CommerceAccountGroupModelImpl)commerceAccountGroup;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceAccountGroup.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceAccountGroup.setCreateDate(now);
			}
			else {
				commerceAccountGroup.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!commerceAccountGroupModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceAccountGroup.setModifiedDate(now);
			}
			else {
				commerceAccountGroup.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceAccountGroup.isNew()) {
				session.save(commerceAccountGroup);

				commerceAccountGroup.setNew(false);
			}
			else {
				commerceAccountGroup = (CommerceAccountGroup)session.merge(
					commerceAccountGroup);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceAccountGroupModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				commerceAccountGroupModelImpl.getCommerceAccountGroupId()
			};

			finderCache.removeResult(
				_finderPathCountByCommerceAccountGroupIds, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCommerceAccountGroupIds,
				args);

			args = new Object[] {commerceAccountGroupModelImpl.getCompanyId()};

			finderCache.removeResult(_finderPathCountByCompanyId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCompanyId, args);

			args = new Object[] {
				commerceAccountGroupModelImpl.getCompanyId(),
				commerceAccountGroupModelImpl.getType()
			};

			finderCache.removeResult(_finderPathCountByC_T, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_T, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((commerceAccountGroupModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCommerceAccountGroupIds.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceAccountGroupModelImpl.
						getOriginalCommerceAccountGroupId()
				};

				finderCache.removeResult(
					_finderPathCountByCommerceAccountGroupIds, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommerceAccountGroupIds,
					args);

				args = new Object[] {
					commerceAccountGroupModelImpl.getCommerceAccountGroupId()
				};

				finderCache.removeResult(
					_finderPathCountByCommerceAccountGroupIds, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommerceAccountGroupIds,
					args);
			}

			if ((commerceAccountGroupModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCompanyId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceAccountGroupModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);

				args = new Object[] {
					commerceAccountGroupModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);
			}

			if ((commerceAccountGroupModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_T.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceAccountGroupModelImpl.getOriginalCompanyId(),
					commerceAccountGroupModelImpl.getOriginalType()
				};

				finderCache.removeResult(_finderPathCountByC_T, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_T, args);

				args = new Object[] {
					commerceAccountGroupModelImpl.getCompanyId(),
					commerceAccountGroupModelImpl.getType()
				};

				finderCache.removeResult(_finderPathCountByC_T, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_T, args);
			}
		}

		entityCache.putResult(
			CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountGroupImpl.class,
			commerceAccountGroup.getPrimaryKey(), commerceAccountGroup, false);

		clearUniqueFindersCache(commerceAccountGroupModelImpl, false);
		cacheUniqueFindersCache(commerceAccountGroupModelImpl);

		commerceAccountGroup.resetOriginalValues();

		return commerceAccountGroup;
	}

	/**
	 * Returns the commerce account group with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce account group
	 * @return the commerce account group
	 * @throws NoSuchAccountGroupException if a commerce account group with the primary key could not be found
	 */
	@Override
	public CommerceAccountGroup findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAccountGroupException {

		CommerceAccountGroup commerceAccountGroup = fetchByPrimaryKey(
			primaryKey);

		if (commerceAccountGroup == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccountGroupException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commerceAccountGroup;
	}

	/**
	 * Returns the commerce account group with the primary key or throws a <code>NoSuchAccountGroupException</code> if it could not be found.
	 *
	 * @param commerceAccountGroupId the primary key of the commerce account group
	 * @return the commerce account group
	 * @throws NoSuchAccountGroupException if a commerce account group with the primary key could not be found
	 */
	@Override
	public CommerceAccountGroup findByPrimaryKey(long commerceAccountGroupId)
		throws NoSuchAccountGroupException {

		return findByPrimaryKey((Serializable)commerceAccountGroupId);
	}

	/**
	 * Returns the commerce account group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce account group
	 * @return the commerce account group, or <code>null</code> if a commerce account group with the primary key could not be found
	 */
	@Override
	public CommerceAccountGroup fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountGroupImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceAccountGroup commerceAccountGroup =
			(CommerceAccountGroup)serializable;

		if (commerceAccountGroup == null) {
			Session session = null;

			try {
				session = openSession();

				commerceAccountGroup = (CommerceAccountGroup)session.get(
					CommerceAccountGroupImpl.class, primaryKey);

				if (commerceAccountGroup != null) {
					cacheResult(commerceAccountGroup);
				}
				else {
					entityCache.putResult(
						CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
						CommerceAccountGroupImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
					CommerceAccountGroupImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceAccountGroup;
	}

	/**
	 * Returns the commerce account group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceAccountGroupId the primary key of the commerce account group
	 * @return the commerce account group, or <code>null</code> if a commerce account group with the primary key could not be found
	 */
	@Override
	public CommerceAccountGroup fetchByPrimaryKey(long commerceAccountGroupId) {
		return fetchByPrimaryKey((Serializable)commerceAccountGroupId);
	}

	@Override
	public Map<Serializable, CommerceAccountGroup> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceAccountGroup> map =
			new HashMap<Serializable, CommerceAccountGroup>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceAccountGroup commerceAccountGroup = fetchByPrimaryKey(
				primaryKey);

			if (commerceAccountGroup != null) {
				map.put(primaryKey, commerceAccountGroup);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
				CommerceAccountGroupImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceAccountGroup)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_COMMERCEACCOUNTGROUP_WHERE_PKS_IN);

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

			for (CommerceAccountGroup commerceAccountGroup :
					(List<CommerceAccountGroup>)q.list()) {

				map.put(
					commerceAccountGroup.getPrimaryKeyObj(),
					commerceAccountGroup);

				cacheResult(commerceAccountGroup);

				uncachedPrimaryKeys.remove(
					commerceAccountGroup.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
					CommerceAccountGroupImpl.class, primaryKey, nullModel);
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
	 * Returns all the commerce account groups.
	 *
	 * @return the commerce account groups
	 */
	@Override
	public List<CommerceAccountGroup> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce account groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce account groups
	 * @param end the upper bound of the range of commerce account groups (not inclusive)
	 * @return the range of commerce account groups
	 */
	@Override
	public List<CommerceAccountGroup> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce account groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce account groups
	 * @param end the upper bound of the range of commerce account groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce account groups
	 */
	@Override
	public List<CommerceAccountGroup> findAll(
		int start, int end,
		OrderByComparator<CommerceAccountGroup> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce account groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce account groups
	 * @param end the upper bound of the range of commerce account groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce account groups
	 */
	@Override
	public List<CommerceAccountGroup> findAll(
		int start, int end,
		OrderByComparator<CommerceAccountGroup> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<CommerceAccountGroup> list = null;

		if (useFinderCache) {
			list = (List<CommerceAccountGroup>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEACCOUNTGROUP);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEACCOUNTGROUP;

				if (pagination) {
					sql = sql.concat(
						CommerceAccountGroupModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceAccountGroup>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceAccountGroup>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the commerce account groups from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceAccountGroup commerceAccountGroup : findAll()) {
			remove(commerceAccountGroup);
		}
	}

	/**
	 * Returns the number of commerce account groups.
	 *
	 * @return the number of commerce account groups
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCEACCOUNTGROUP);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

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
		return CommerceAccountGroupModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce account group persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountGroupModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountGroupModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByCommerceAccountGroupIds = new FinderPath(
			CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountGroupModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCommerceAccountGroupIds",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCommerceAccountGroupIds =
			new FinderPath(
				CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
				CommerceAccountGroupModelImpl.FINDER_CACHE_ENABLED,
				CommerceAccountGroupImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByCommerceAccountGroupIds",
				new String[] {Long.class.getName()},
				CommerceAccountGroupModelImpl.
					COMMERCEACCOUNTGROUPID_COLUMN_BITMASK |
				CommerceAccountGroupModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByCommerceAccountGroupIds = new FinderPath(
			CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceAccountGroupIds",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationCountByCommerceAccountGroupIds =
			new FinderPath(
				CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
				CommerceAccountGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"countByCommerceAccountGroupIds",
				new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByCompanyId = new FinderPath(
			CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountGroupModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCompanyId = new FinderPath(
			CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountGroupModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] {Long.class.getName()},
			CommerceAccountGroupModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceAccountGroupModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByCompanyId = new FinderPath(
			CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByC_T = new FinderPath(
			CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountGroupModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_T",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_T = new FinderPath(
			CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountGroupModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_T",
			new String[] {Long.class.getName(), Integer.class.getName()},
			CommerceAccountGroupModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceAccountGroupModelImpl.TYPE_COLUMN_BITMASK |
			CommerceAccountGroupModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByC_T = new FinderPath(
			CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_T",
			new String[] {Long.class.getName(), Integer.class.getName()});

		_finderPathFetchByC_ERC = new FinderPath(
			CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountGroupModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountGroupImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_ERC",
			new String[] {Long.class.getName(), String.class.getName()},
			CommerceAccountGroupModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceAccountGroupModelImpl.EXTERNALREFERENCECODE_COLUMN_BITMASK);

		_finderPathCountByC_ERC = new FinderPath(
			CommerceAccountGroupModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_ERC",
			new String[] {Long.class.getName(), String.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(CommerceAccountGroupImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_COMMERCEACCOUNTGROUP =
		"SELECT commerceAccountGroup FROM CommerceAccountGroup commerceAccountGroup";

	private static final String _SQL_SELECT_COMMERCEACCOUNTGROUP_WHERE_PKS_IN =
		"SELECT commerceAccountGroup FROM CommerceAccountGroup commerceAccountGroup WHERE commerceAccountGroupId IN (";

	private static final String _SQL_SELECT_COMMERCEACCOUNTGROUP_WHERE =
		"SELECT commerceAccountGroup FROM CommerceAccountGroup commerceAccountGroup WHERE ";

	private static final String _SQL_COUNT_COMMERCEACCOUNTGROUP =
		"SELECT COUNT(commerceAccountGroup) FROM CommerceAccountGroup commerceAccountGroup";

	private static final String _SQL_COUNT_COMMERCEACCOUNTGROUP_WHERE =
		"SELECT COUNT(commerceAccountGroup) FROM CommerceAccountGroup commerceAccountGroup WHERE ";

	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN =
		"commerceAccountGroup.commerceAccountGroupId";

	private static final String _FILTER_SQL_SELECT_COMMERCEACCOUNTGROUP_WHERE =
		"SELECT DISTINCT {commerceAccountGroup.*} FROM CommerceAccountGroup commerceAccountGroup WHERE ";

	private static final String
		_FILTER_SQL_SELECT_COMMERCEACCOUNTGROUP_NO_INLINE_DISTINCT_WHERE_1 =
			"SELECT {CommerceAccountGroup.*} FROM (SELECT DISTINCT commerceAccountGroup.commerceAccountGroupId FROM CommerceAccountGroup commerceAccountGroup WHERE ";

	private static final String
		_FILTER_SQL_SELECT_COMMERCEACCOUNTGROUP_NO_INLINE_DISTINCT_WHERE_2 =
			") TEMP_TABLE INNER JOIN CommerceAccountGroup ON TEMP_TABLE.commerceAccountGroupId = CommerceAccountGroup.commerceAccountGroupId";

	private static final String _FILTER_SQL_COUNT_COMMERCEACCOUNTGROUP_WHERE =
		"SELECT COUNT(DISTINCT commerceAccountGroup.commerceAccountGroupId) AS COUNT_VALUE FROM CommerceAccountGroup commerceAccountGroup WHERE ";

	private static final String _FILTER_ENTITY_ALIAS = "commerceAccountGroup";

	private static final String _FILTER_ENTITY_TABLE = "CommerceAccountGroup";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"commerceAccountGroup.";

	private static final String _ORDER_BY_ENTITY_TABLE =
		"CommerceAccountGroup.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommerceAccountGroup exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommerceAccountGroup exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAccountGroupPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

}