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
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
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

import java.sql.Timestamp;

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
 * @generated
 */
public class CommerceInventoryAuditPersistenceImpl
	extends BasePersistenceImpl<CommerceInventoryAudit>
	implements CommerceInventoryAuditPersistence {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommerceInventoryAuditUtil</code> to access the commerce inventory audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommerceInventoryAuditImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByLtCreateDate;
	private FinderPath _finderPathWithPaginationCountByLtCreateDate;

	/**
	 * Returns all the commerce inventory audits where createDate &lt; &#63;.
	 *
	 * @param createDate the create date
	 * @return the matching commerce inventory audits
	 */
	@Override
	public List<CommerceInventoryAudit> findByLtCreateDate(Date createDate) {
		return findByLtCreateDate(
			createDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory audits where createDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of commerce inventory audits
	 * @param end the upper bound of the range of commerce inventory audits (not inclusive)
	 * @return the range of matching commerce inventory audits
	 */
	@Override
	public List<CommerceInventoryAudit> findByLtCreateDate(
		Date createDate, int start, int end) {

		return findByLtCreateDate(createDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory audits where createDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of commerce inventory audits
	 * @param end the upper bound of the range of commerce inventory audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory audits
	 */
	@Override
	public List<CommerceInventoryAudit> findByLtCreateDate(
		Date createDate, int start, int end,
		OrderByComparator<CommerceInventoryAudit> orderByComparator) {

		return findByLtCreateDate(
			createDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory audits where createDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of commerce inventory audits
	 * @param end the upper bound of the range of commerce inventory audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce inventory audits
	 */
	@Override
	public List<CommerceInventoryAudit> findByLtCreateDate(
		Date createDate, int start, int end,
		OrderByComparator<CommerceInventoryAudit> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByLtCreateDate;
		finderArgs = new Object[] {
			_getTime(createDate), start, end, orderByComparator
		};

		List<CommerceInventoryAudit> list = null;

		if (useFinderCache) {
			list = (List<CommerceInventoryAudit>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceInventoryAudit commerceInventoryAudit : list) {
					if (createDate.getTime() <=
							commerceInventoryAudit.getCreateDate().getTime()) {

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

			query.append(_SQL_SELECT_COMMERCEINVENTORYAUDIT_WHERE);

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_LTCREATEDATE_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_LTCREATEDATE_CREATEDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(CommerceInventoryAuditModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
				}

				list = (List<CommerceInventoryAudit>)QueryUtil.list(
					q, getDialect(), start, end);

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
	 * Returns the first commerce inventory audit in the ordered set where createDate &lt; &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory audit
	 * @throws NoSuchInventoryAuditException if a matching commerce inventory audit could not be found
	 */
	@Override
	public CommerceInventoryAudit findByLtCreateDate_First(
			Date createDate,
			OrderByComparator<CommerceInventoryAudit> orderByComparator)
		throws NoSuchInventoryAuditException {

		CommerceInventoryAudit commerceInventoryAudit =
			fetchByLtCreateDate_First(createDate, orderByComparator);

		if (commerceInventoryAudit != null) {
			return commerceInventoryAudit;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate<");
		msg.append(createDate);

		msg.append("}");

		throw new NoSuchInventoryAuditException(msg.toString());
	}

	/**
	 * Returns the first commerce inventory audit in the ordered set where createDate &lt; &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory audit, or <code>null</code> if a matching commerce inventory audit could not be found
	 */
	@Override
	public CommerceInventoryAudit fetchByLtCreateDate_First(
		Date createDate,
		OrderByComparator<CommerceInventoryAudit> orderByComparator) {

		List<CommerceInventoryAudit> list = findByLtCreateDate(
			createDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce inventory audit in the ordered set where createDate &lt; &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory audit
	 * @throws NoSuchInventoryAuditException if a matching commerce inventory audit could not be found
	 */
	@Override
	public CommerceInventoryAudit findByLtCreateDate_Last(
			Date createDate,
			OrderByComparator<CommerceInventoryAudit> orderByComparator)
		throws NoSuchInventoryAuditException {

		CommerceInventoryAudit commerceInventoryAudit =
			fetchByLtCreateDate_Last(createDate, orderByComparator);

		if (commerceInventoryAudit != null) {
			return commerceInventoryAudit;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate<");
		msg.append(createDate);

		msg.append("}");

		throw new NoSuchInventoryAuditException(msg.toString());
	}

	/**
	 * Returns the last commerce inventory audit in the ordered set where createDate &lt; &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory audit, or <code>null</code> if a matching commerce inventory audit could not be found
	 */
	@Override
	public CommerceInventoryAudit fetchByLtCreateDate_Last(
		Date createDate,
		OrderByComparator<CommerceInventoryAudit> orderByComparator) {

		int count = countByLtCreateDate(createDate);

		if (count == 0) {
			return null;
		}

		List<CommerceInventoryAudit> list = findByLtCreateDate(
			createDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce inventory audits before and after the current commerce inventory audit in the ordered set where createDate &lt; &#63;.
	 *
	 * @param commerceInventoryAuditId the primary key of the current commerce inventory audit
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory audit
	 * @throws NoSuchInventoryAuditException if a commerce inventory audit with the primary key could not be found
	 */
	@Override
	public CommerceInventoryAudit[] findByLtCreateDate_PrevAndNext(
			long commerceInventoryAuditId, Date createDate,
			OrderByComparator<CommerceInventoryAudit> orderByComparator)
		throws NoSuchInventoryAuditException {

		CommerceInventoryAudit commerceInventoryAudit = findByPrimaryKey(
			commerceInventoryAuditId);

		Session session = null;

		try {
			session = openSession();

			CommerceInventoryAudit[] array = new CommerceInventoryAuditImpl[3];

			array[0] = getByLtCreateDate_PrevAndNext(
				session, commerceInventoryAudit, createDate, orderByComparator,
				true);

			array[1] = commerceInventoryAudit;

			array[2] = getByLtCreateDate_PrevAndNext(
				session, commerceInventoryAudit, createDate, orderByComparator,
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

	protected CommerceInventoryAudit getByLtCreateDate_PrevAndNext(
		Session session, CommerceInventoryAudit commerceInventoryAudit,
		Date createDate,
		OrderByComparator<CommerceInventoryAudit> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEINVENTORYAUDIT_WHERE);

		boolean bindCreateDate = false;

		if (createDate == null) {
			query.append(_FINDER_COLUMN_LTCREATEDATE_CREATEDATE_1);
		}
		else {
			bindCreateDate = true;

			query.append(_FINDER_COLUMN_LTCREATEDATE_CREATEDATE_2);
		}

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
			query.append(CommerceInventoryAuditModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindCreateDate) {
			qPos.add(new Timestamp(createDate.getTime()));
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceInventoryAudit)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceInventoryAudit> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce inventory audits where createDate &lt; &#63; from the database.
	 *
	 * @param createDate the create date
	 */
	@Override
	public void removeByLtCreateDate(Date createDate) {
		for (CommerceInventoryAudit commerceInventoryAudit :
				findByLtCreateDate(
					createDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commerceInventoryAudit);
		}
	}

	/**
	 * Returns the number of commerce inventory audits where createDate &lt; &#63;.
	 *
	 * @param createDate the create date
	 * @return the number of matching commerce inventory audits
	 */
	@Override
	public int countByLtCreateDate(Date createDate) {
		FinderPath finderPath = _finderPathWithPaginationCountByLtCreateDate;

		Object[] finderArgs = new Object[] {_getTime(createDate)};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEINVENTORYAUDIT_WHERE);

			boolean bindCreateDate = false;

			if (createDate == null) {
				query.append(_FINDER_COLUMN_LTCREATEDATE_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				query.append(_FINDER_COLUMN_LTCREATEDATE_CREATEDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCreateDate) {
					qPos.add(new Timestamp(createDate.getTime()));
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

	private static final String _FINDER_COLUMN_LTCREATEDATE_CREATEDATE_1 =
		"commerceInventoryAudit.createDate IS NULL";

	private static final String _FINDER_COLUMN_LTCREATEDATE_CREATEDATE_2 =
		"commerceInventoryAudit.createDate < ?";

	private FinderPath _finderPathWithPaginationFindBySku;
	private FinderPath _finderPathWithoutPaginationFindBySku;
	private FinderPath _finderPathCountBySku;

	/**
	 * Returns all the commerce inventory audits where sku = &#63;.
	 *
	 * @param sku the sku
	 * @return the matching commerce inventory audits
	 */
	@Override
	public List<CommerceInventoryAudit> findBySku(String sku) {
		return findBySku(sku, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory audits where sku = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sku the sku
	 * @param start the lower bound of the range of commerce inventory audits
	 * @param end the upper bound of the range of commerce inventory audits (not inclusive)
	 * @return the range of matching commerce inventory audits
	 */
	@Override
	public List<CommerceInventoryAudit> findBySku(
		String sku, int start, int end) {

		return findBySku(sku, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory audits where sku = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sku the sku
	 * @param start the lower bound of the range of commerce inventory audits
	 * @param end the upper bound of the range of commerce inventory audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory audits
	 */
	@Override
	public List<CommerceInventoryAudit> findBySku(
		String sku, int start, int end,
		OrderByComparator<CommerceInventoryAudit> orderByComparator) {

		return findBySku(sku, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory audits where sku = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sku the sku
	 * @param start the lower bound of the range of commerce inventory audits
	 * @param end the upper bound of the range of commerce inventory audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce inventory audits
	 */
	@Override
	public List<CommerceInventoryAudit> findBySku(
		String sku, int start, int end,
		OrderByComparator<CommerceInventoryAudit> orderByComparator,
		boolean useFinderCache) {

		sku = Objects.toString(sku, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBySku;
				finderArgs = new Object[] {sku};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBySku;
			finderArgs = new Object[] {sku, start, end, orderByComparator};
		}

		List<CommerceInventoryAudit> list = null;

		if (useFinderCache) {
			list = (List<CommerceInventoryAudit>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceInventoryAudit commerceInventoryAudit : list) {
					if (!sku.equals(commerceInventoryAudit.getSku())) {
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

			query.append(_SQL_SELECT_COMMERCEINVENTORYAUDIT_WHERE);

			boolean bindSku = false;

			if (sku.isEmpty()) {
				query.append(_FINDER_COLUMN_SKU_SKU_3);
			}
			else {
				bindSku = true;

				query.append(_FINDER_COLUMN_SKU_SKU_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(CommerceInventoryAuditModelImpl.ORDER_BY_JPQL);
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

				list = (List<CommerceInventoryAudit>)QueryUtil.list(
					q, getDialect(), start, end);

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
	 * Returns the first commerce inventory audit in the ordered set where sku = &#63;.
	 *
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory audit
	 * @throws NoSuchInventoryAuditException if a matching commerce inventory audit could not be found
	 */
	@Override
	public CommerceInventoryAudit findBySku_First(
			String sku,
			OrderByComparator<CommerceInventoryAudit> orderByComparator)
		throws NoSuchInventoryAuditException {

		CommerceInventoryAudit commerceInventoryAudit = fetchBySku_First(
			sku, orderByComparator);

		if (commerceInventoryAudit != null) {
			return commerceInventoryAudit;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sku=");
		msg.append(sku);

		msg.append("}");

		throw new NoSuchInventoryAuditException(msg.toString());
	}

	/**
	 * Returns the first commerce inventory audit in the ordered set where sku = &#63;.
	 *
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory audit, or <code>null</code> if a matching commerce inventory audit could not be found
	 */
	@Override
	public CommerceInventoryAudit fetchBySku_First(
		String sku,
		OrderByComparator<CommerceInventoryAudit> orderByComparator) {

		List<CommerceInventoryAudit> list = findBySku(
			sku, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce inventory audit in the ordered set where sku = &#63;.
	 *
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory audit
	 * @throws NoSuchInventoryAuditException if a matching commerce inventory audit could not be found
	 */
	@Override
	public CommerceInventoryAudit findBySku_Last(
			String sku,
			OrderByComparator<CommerceInventoryAudit> orderByComparator)
		throws NoSuchInventoryAuditException {

		CommerceInventoryAudit commerceInventoryAudit = fetchBySku_Last(
			sku, orderByComparator);

		if (commerceInventoryAudit != null) {
			return commerceInventoryAudit;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sku=");
		msg.append(sku);

		msg.append("}");

		throw new NoSuchInventoryAuditException(msg.toString());
	}

	/**
	 * Returns the last commerce inventory audit in the ordered set where sku = &#63;.
	 *
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory audit, or <code>null</code> if a matching commerce inventory audit could not be found
	 */
	@Override
	public CommerceInventoryAudit fetchBySku_Last(
		String sku,
		OrderByComparator<CommerceInventoryAudit> orderByComparator) {

		int count = countBySku(sku);

		if (count == 0) {
			return null;
		}

		List<CommerceInventoryAudit> list = findBySku(
			sku, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce inventory audits before and after the current commerce inventory audit in the ordered set where sku = &#63;.
	 *
	 * @param commerceInventoryAuditId the primary key of the current commerce inventory audit
	 * @param sku the sku
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory audit
	 * @throws NoSuchInventoryAuditException if a commerce inventory audit with the primary key could not be found
	 */
	@Override
	public CommerceInventoryAudit[] findBySku_PrevAndNext(
			long commerceInventoryAuditId, String sku,
			OrderByComparator<CommerceInventoryAudit> orderByComparator)
		throws NoSuchInventoryAuditException {

		sku = Objects.toString(sku, "");

		CommerceInventoryAudit commerceInventoryAudit = findByPrimaryKey(
			commerceInventoryAuditId);

		Session session = null;

		try {
			session = openSession();

			CommerceInventoryAudit[] array = new CommerceInventoryAuditImpl[3];

			array[0] = getBySku_PrevAndNext(
				session, commerceInventoryAudit, sku, orderByComparator, true);

			array[1] = commerceInventoryAudit;

			array[2] = getBySku_PrevAndNext(
				session, commerceInventoryAudit, sku, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceInventoryAudit getBySku_PrevAndNext(
		Session session, CommerceInventoryAudit commerceInventoryAudit,
		String sku, OrderByComparator<CommerceInventoryAudit> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEINVENTORYAUDIT_WHERE);

		boolean bindSku = false;

		if (sku.isEmpty()) {
			query.append(_FINDER_COLUMN_SKU_SKU_3);
		}
		else {
			bindSku = true;

			query.append(_FINDER_COLUMN_SKU_SKU_2);
		}

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
			query.append(CommerceInventoryAuditModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindSku) {
			qPos.add(sku);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceInventoryAudit)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceInventoryAudit> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce inventory audits where sku = &#63; from the database.
	 *
	 * @param sku the sku
	 */
	@Override
	public void removeBySku(String sku) {
		for (CommerceInventoryAudit commerceInventoryAudit :
				findBySku(sku, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commerceInventoryAudit);
		}
	}

	/**
	 * Returns the number of commerce inventory audits where sku = &#63;.
	 *
	 * @param sku the sku
	 * @return the number of matching commerce inventory audits
	 */
	@Override
	public int countBySku(String sku) {
		sku = Objects.toString(sku, "");

		FinderPath finderPath = _finderPathCountBySku;

		Object[] finderArgs = new Object[] {sku};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEINVENTORYAUDIT_WHERE);

			boolean bindSku = false;

			if (sku.isEmpty()) {
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

	private static final String _FINDER_COLUMN_SKU_SKU_2 =
		"commerceInventoryAudit.sku = ?";

	private static final String _FINDER_COLUMN_SKU_SKU_3 =
		"(commerceInventoryAudit.sku IS NULL OR commerceInventoryAudit.sku = '')";

	public CommerceInventoryAuditPersistenceImpl() {
		setModelClass(CommerceInventoryAudit.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("commerceInventoryAuditId", "CIAuditId");

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
	 * Caches the commerce inventory audit in the entity cache if it is enabled.
	 *
	 * @param commerceInventoryAudit the commerce inventory audit
	 */
	@Override
	public void cacheResult(CommerceInventoryAudit commerceInventoryAudit) {
		entityCache.putResult(
			CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryAuditImpl.class,
			commerceInventoryAudit.getPrimaryKey(), commerceInventoryAudit);

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

		for (CommerceInventoryAudit commerceInventoryAudit :
				commerceInventoryAudits) {

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
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
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
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceInventoryAudit commerceInventoryAudit) {
		entityCache.removeResult(
			CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryAuditImpl.class,
			commerceInventoryAudit.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<CommerceInventoryAudit> commerceInventoryAudits) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceInventoryAudit commerceInventoryAudit :
				commerceInventoryAudits) {

			entityCache.removeResult(
				CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
				CommerceInventoryAuditImpl.class,
				commerceInventoryAudit.getPrimaryKey());
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
		CommerceInventoryAudit commerceInventoryAudit =
			new CommerceInventoryAuditImpl();

		commerceInventoryAudit.setNew(true);
		commerceInventoryAudit.setPrimaryKey(commerceInventoryAuditId);

		commerceInventoryAudit.setCompanyId(CompanyThreadLocal.getCompanyId());

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

			CommerceInventoryAudit commerceInventoryAudit =
				(CommerceInventoryAudit)session.get(
					CommerceInventoryAuditImpl.class, primaryKey);

			if (commerceInventoryAudit == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchInventoryAuditException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
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
				commerceInventoryAudit = (CommerceInventoryAudit)session.get(
					CommerceInventoryAuditImpl.class,
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

		if (!(commerceInventoryAudit instanceof
				CommerceInventoryAuditModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceInventoryAudit.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					commerceInventoryAudit);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceInventoryAudit proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceInventoryAudit implementation " +
					commerceInventoryAudit.getClass());
		}

		CommerceInventoryAuditModelImpl commerceInventoryAuditModelImpl =
			(CommerceInventoryAuditModelImpl)commerceInventoryAudit;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceInventoryAudit.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceInventoryAudit.setCreateDate(now);
			}
			else {
				commerceInventoryAudit.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!commerceInventoryAuditModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceInventoryAudit.setModifiedDate(now);
			}
			else {
				commerceInventoryAudit.setModifiedDate(
					serviceContext.getModifiedDate(now));
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
				commerceInventoryAudit = (CommerceInventoryAudit)session.merge(
					commerceInventoryAudit);
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
		else if (isNew) {
			Object[] args = new Object[] {
				commerceInventoryAuditModelImpl.getSku()
			};

			finderCache.removeResult(_finderPathCountBySku, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindBySku, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((commerceInventoryAuditModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindBySku.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceInventoryAuditModelImpl.getOriginalSku()
				};

				finderCache.removeResult(_finderPathCountBySku, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindBySku, args);

				args = new Object[] {commerceInventoryAuditModelImpl.getSku()};

				finderCache.removeResult(_finderPathCountBySku, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindBySku, args);
			}
		}

		entityCache.putResult(
			CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryAuditImpl.class,
			commerceInventoryAudit.getPrimaryKey(), commerceInventoryAudit,
			false);

		commerceInventoryAudit.resetOriginalValues();

		return commerceInventoryAudit;
	}

	/**
	 * Returns the commerce inventory audit with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce inventory audit
	 * @return the commerce inventory audit
	 * @throws NoSuchInventoryAuditException if a commerce inventory audit with the primary key could not be found
	 */
	@Override
	public CommerceInventoryAudit findByPrimaryKey(Serializable primaryKey)
		throws NoSuchInventoryAuditException {

		CommerceInventoryAudit commerceInventoryAudit = fetchByPrimaryKey(
			primaryKey);

		if (commerceInventoryAudit == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchInventoryAuditException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commerceInventoryAudit;
	}

	/**
	 * Returns the commerce inventory audit with the primary key or throws a <code>NoSuchInventoryAuditException</code> if it could not be found.
	 *
	 * @param commerceInventoryAuditId the primary key of the commerce inventory audit
	 * @return the commerce inventory audit
	 * @throws NoSuchInventoryAuditException if a commerce inventory audit with the primary key could not be found
	 */
	@Override
	public CommerceInventoryAudit findByPrimaryKey(
			long commerceInventoryAuditId)
		throws NoSuchInventoryAuditException {

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
		Serializable serializable = entityCache.getResult(
			CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryAuditImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceInventoryAudit commerceInventoryAudit =
			(CommerceInventoryAudit)serializable;

		if (commerceInventoryAudit == null) {
			Session session = null;

			try {
				session = openSession();

				commerceInventoryAudit = (CommerceInventoryAudit)session.get(
					CommerceInventoryAuditImpl.class, primaryKey);

				if (commerceInventoryAudit != null) {
					cacheResult(commerceInventoryAudit);
				}
				else {
					entityCache.putResult(
						CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
						CommerceInventoryAuditImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
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

		Map<Serializable, CommerceInventoryAudit> map =
			new HashMap<Serializable, CommerceInventoryAudit>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceInventoryAudit commerceInventoryAudit = fetchByPrimaryKey(
				primaryKey);

			if (commerceInventoryAudit != null) {
				map.put(primaryKey, commerceInventoryAudit);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
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

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

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

			for (CommerceInventoryAudit commerceInventoryAudit :
					(List<CommerceInventoryAudit>)q.list()) {

				map.put(
					commerceInventoryAudit.getPrimaryKeyObj(),
					commerceInventoryAudit);

				cacheResult(commerceInventoryAudit);

				uncachedPrimaryKeys.remove(
					commerceInventoryAudit.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory audits
	 * @param end the upper bound of the range of commerce inventory audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce inventory audits
	 */
	@Override
	public List<CommerceInventoryAudit> findAll(
		int start, int end,
		OrderByComparator<CommerceInventoryAudit> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceInventoryAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory audits
	 * @param end the upper bound of the range of commerce inventory audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce inventory audits
	 */
	@Override
	public List<CommerceInventoryAudit> findAll(
		int start, int end,
		OrderByComparator<CommerceInventoryAudit> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<CommerceInventoryAudit> list = null;

		if (useFinderCache) {
			list = (List<CommerceInventoryAudit>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEINVENTORYAUDIT);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEINVENTORYAUDIT;

				sql = sql.concat(CommerceInventoryAuditModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				list = (List<CommerceInventoryAudit>)QueryUtil.list(
					q, getDialect(), start, end);

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
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(
					_SQL_COUNT_COMMERCEINVENTORYAUDIT);

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
		return CommerceInventoryAuditModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce inventory audit persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryAuditModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryAuditModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByLtCreateDate = new FinderPath(
			CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryAuditModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLtCreateDate",
			new String[] {
				Date.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByLtCreateDate = new FinderPath(
			CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByLtCreateDate",
			new String[] {Date.class.getName()});

		_finderPathWithPaginationFindBySku = new FinderPath(
			CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryAuditModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySku",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindBySku = new FinderPath(
			CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryAuditModelImpl.FINDER_CACHE_ENABLED,
			CommerceInventoryAuditImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySku",
			new String[] {String.class.getName()},
			CommerceInventoryAuditModelImpl.SKU_COLUMN_BITMASK);

		_finderPathCountBySku = new FinderPath(
			CommerceInventoryAuditModelImpl.ENTITY_CACHE_ENABLED,
			CommerceInventoryAuditModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySku",
			new String[] {String.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(CommerceInventoryAuditImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private Long _getTime(Date date) {
		if (date == null) {
			return null;
		}

		return date.getTime();
	}

	private static final String _SQL_SELECT_COMMERCEINVENTORYAUDIT =
		"SELECT commerceInventoryAudit FROM CommerceInventoryAudit commerceInventoryAudit";

	private static final String
		_SQL_SELECT_COMMERCEINVENTORYAUDIT_WHERE_PKS_IN =
			"SELECT commerceInventoryAudit FROM CommerceInventoryAudit commerceInventoryAudit WHERE CIAuditId IN (";

	private static final String _SQL_SELECT_COMMERCEINVENTORYAUDIT_WHERE =
		"SELECT commerceInventoryAudit FROM CommerceInventoryAudit commerceInventoryAudit WHERE ";

	private static final String _SQL_COUNT_COMMERCEINVENTORYAUDIT =
		"SELECT COUNT(commerceInventoryAudit) FROM CommerceInventoryAudit commerceInventoryAudit";

	private static final String _SQL_COUNT_COMMERCEINVENTORYAUDIT_WHERE =
		"SELECT COUNT(commerceInventoryAudit) FROM CommerceInventoryAudit commerceInventoryAudit WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"commerceInventoryAudit.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommerceInventoryAudit exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommerceInventoryAudit exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceInventoryAuditPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"commerceInventoryAuditId"});

}