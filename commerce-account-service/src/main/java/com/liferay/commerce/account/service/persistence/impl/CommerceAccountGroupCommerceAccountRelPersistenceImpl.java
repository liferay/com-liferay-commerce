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

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.account.exception.NoSuchAccountGroupCommerceAccountRelException;
import com.liferay.commerce.account.model.CommerceAccountGroupCommerceAccountRel;
import com.liferay.commerce.account.model.impl.CommerceAccountGroupCommerceAccountRelImpl;
import com.liferay.commerce.account.model.impl.CommerceAccountGroupCommerceAccountRelModelImpl;
import com.liferay.commerce.account.service.persistence.CommerceAccountGroupCommerceAccountRelPersistence;
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
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the commerce account group commerce account rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @generated
 */
@ProviderType
public class CommerceAccountGroupCommerceAccountRelPersistenceImpl
	extends BasePersistenceImpl<CommerceAccountGroupCommerceAccountRel>
	implements CommerceAccountGroupCommerceAccountRelPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommerceAccountGroupCommerceAccountRelUtil</code> to access the commerce account group commerce account rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommerceAccountGroupCommerceAccountRelImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByCommerceAccountGroupId;
	private FinderPath _finderPathWithoutPaginationFindByCommerceAccountGroupId;
	private FinderPath _finderPathCountByCommerceAccountGroupId;

	/**
	 * Returns all the commerce account group commerce account rels where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @return the matching commerce account group commerce account rels
	 */
	@Override
	public List<CommerceAccountGroupCommerceAccountRel>
		findByCommerceAccountGroupId(long commerceAccountGroupId) {

		return findByCommerceAccountGroupId(
			commerceAccountGroupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce account group commerce account rels where commerceAccountGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupCommerceAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param start the lower bound of the range of commerce account group commerce account rels
	 * @param end the upper bound of the range of commerce account group commerce account rels (not inclusive)
	 * @return the range of matching commerce account group commerce account rels
	 */
	@Override
	public List<CommerceAccountGroupCommerceAccountRel>
		findByCommerceAccountGroupId(
			long commerceAccountGroupId, int start, int end) {

		return findByCommerceAccountGroupId(
			commerceAccountGroupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce account group commerce account rels where commerceAccountGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupCommerceAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param start the lower bound of the range of commerce account group commerce account rels
	 * @param end the upper bound of the range of commerce account group commerce account rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce account group commerce account rels
	 */
	@Override
	public List<CommerceAccountGroupCommerceAccountRel>
		findByCommerceAccountGroupId(
			long commerceAccountGroupId, int start, int end,
			OrderByComparator<CommerceAccountGroupCommerceAccountRel>
				orderByComparator) {

		return findByCommerceAccountGroupId(
			commerceAccountGroupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce account group commerce account rels where commerceAccountGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupCommerceAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param start the lower bound of the range of commerce account group commerce account rels
	 * @param end the upper bound of the range of commerce account group commerce account rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce account group commerce account rels
	 */
	@Override
	public List<CommerceAccountGroupCommerceAccountRel>
		findByCommerceAccountGroupId(
			long commerceAccountGroupId, int start, int end,
			OrderByComparator<CommerceAccountGroupCommerceAccountRel>
				orderByComparator,
			boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByCommerceAccountGroupId;
				finderArgs = new Object[] {commerceAccountGroupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCommerceAccountGroupId;
			finderArgs = new Object[] {
				commerceAccountGroupId, start, end, orderByComparator
			};
		}

		List<CommerceAccountGroupCommerceAccountRel> list = null;

		if (useFinderCache) {
			list =
				(List<CommerceAccountGroupCommerceAccountRel>)
					finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceAccountGroupCommerceAccountRel
						commerceAccountGroupCommerceAccountRel : list) {

					if ((commerceAccountGroupId !=
							commerceAccountGroupCommerceAccountRel.
								getCommerceAccountGroupId())) {

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

			query.append(
				_SQL_SELECT_COMMERCEACCOUNTGROUPCOMMERCEACCOUNTREL_WHERE);

			query.append(
				_FINDER_COLUMN_COMMERCEACCOUNTGROUPID_COMMERCEACCOUNTGROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(
					CommerceAccountGroupCommerceAccountRelModelImpl.
						ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceAccountGroupId);

				if (!pagination) {
					list =
						(List<CommerceAccountGroupCommerceAccountRel>)
							QueryUtil.list(q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list =
						(List<CommerceAccountGroupCommerceAccountRel>)
							QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first commerce account group commerce account rel in the ordered set where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce account group commerce account rel
	 * @throws NoSuchAccountGroupCommerceAccountRelException if a matching commerce account group commerce account rel could not be found
	 */
	@Override
	public CommerceAccountGroupCommerceAccountRel
			findByCommerceAccountGroupId_First(
				long commerceAccountGroupId,
				OrderByComparator<CommerceAccountGroupCommerceAccountRel>
					orderByComparator)
		throws NoSuchAccountGroupCommerceAccountRelException {

		CommerceAccountGroupCommerceAccountRel
			commerceAccountGroupCommerceAccountRel =
				fetchByCommerceAccountGroupId_First(
					commerceAccountGroupId, orderByComparator);

		if (commerceAccountGroupCommerceAccountRel != null) {
			return commerceAccountGroupCommerceAccountRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceAccountGroupId=");
		msg.append(commerceAccountGroupId);

		msg.append("}");

		throw new NoSuchAccountGroupCommerceAccountRelException(msg.toString());
	}

	/**
	 * Returns the first commerce account group commerce account rel in the ordered set where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce account group commerce account rel, or <code>null</code> if a matching commerce account group commerce account rel could not be found
	 */
	@Override
	public CommerceAccountGroupCommerceAccountRel
		fetchByCommerceAccountGroupId_First(
			long commerceAccountGroupId,
			OrderByComparator<CommerceAccountGroupCommerceAccountRel>
				orderByComparator) {

		List<CommerceAccountGroupCommerceAccountRel> list =
			findByCommerceAccountGroupId(
				commerceAccountGroupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce account group commerce account rel in the ordered set where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce account group commerce account rel
	 * @throws NoSuchAccountGroupCommerceAccountRelException if a matching commerce account group commerce account rel could not be found
	 */
	@Override
	public CommerceAccountGroupCommerceAccountRel
			findByCommerceAccountGroupId_Last(
				long commerceAccountGroupId,
				OrderByComparator<CommerceAccountGroupCommerceAccountRel>
					orderByComparator)
		throws NoSuchAccountGroupCommerceAccountRelException {

		CommerceAccountGroupCommerceAccountRel
			commerceAccountGroupCommerceAccountRel =
				fetchByCommerceAccountGroupId_Last(
					commerceAccountGroupId, orderByComparator);

		if (commerceAccountGroupCommerceAccountRel != null) {
			return commerceAccountGroupCommerceAccountRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceAccountGroupId=");
		msg.append(commerceAccountGroupId);

		msg.append("}");

		throw new NoSuchAccountGroupCommerceAccountRelException(msg.toString());
	}

	/**
	 * Returns the last commerce account group commerce account rel in the ordered set where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce account group commerce account rel, or <code>null</code> if a matching commerce account group commerce account rel could not be found
	 */
	@Override
	public CommerceAccountGroupCommerceAccountRel
		fetchByCommerceAccountGroupId_Last(
			long commerceAccountGroupId,
			OrderByComparator<CommerceAccountGroupCommerceAccountRel>
				orderByComparator) {

		int count = countByCommerceAccountGroupId(commerceAccountGroupId);

		if (count == 0) {
			return null;
		}

		List<CommerceAccountGroupCommerceAccountRel> list =
			findByCommerceAccountGroupId(
				commerceAccountGroupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce account group commerce account rels before and after the current commerce account group commerce account rel in the ordered set where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupCommerceAccountRelId the primary key of the current commerce account group commerce account rel
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce account group commerce account rel
	 * @throws NoSuchAccountGroupCommerceAccountRelException if a commerce account group commerce account rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountGroupCommerceAccountRel[]
			findByCommerceAccountGroupId_PrevAndNext(
				long commerceAccountGroupCommerceAccountRelId,
				long commerceAccountGroupId,
				OrderByComparator<CommerceAccountGroupCommerceAccountRel>
					orderByComparator)
		throws NoSuchAccountGroupCommerceAccountRelException {

		CommerceAccountGroupCommerceAccountRel
			commerceAccountGroupCommerceAccountRel = findByPrimaryKey(
				commerceAccountGroupCommerceAccountRelId);

		Session session = null;

		try {
			session = openSession();

			CommerceAccountGroupCommerceAccountRel[] array =
				new CommerceAccountGroupCommerceAccountRelImpl[3];

			array[0] = getByCommerceAccountGroupId_PrevAndNext(
				session, commerceAccountGroupCommerceAccountRel,
				commerceAccountGroupId, orderByComparator, true);

			array[1] = commerceAccountGroupCommerceAccountRel;

			array[2] = getByCommerceAccountGroupId_PrevAndNext(
				session, commerceAccountGroupCommerceAccountRel,
				commerceAccountGroupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceAccountGroupCommerceAccountRel
		getByCommerceAccountGroupId_PrevAndNext(
			Session session,
			CommerceAccountGroupCommerceAccountRel
				commerceAccountGroupCommerceAccountRel,
			long commerceAccountGroupId,
			OrderByComparator<CommerceAccountGroupCommerceAccountRel>
				orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEACCOUNTGROUPCOMMERCEACCOUNTREL_WHERE);

		query.append(
			_FINDER_COLUMN_COMMERCEACCOUNTGROUPID_COMMERCEACCOUNTGROUPID_2);

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
			query.append(
				CommerceAccountGroupCommerceAccountRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceAccountGroupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceAccountGroupCommerceAccountRel)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceAccountGroupCommerceAccountRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce account group commerce account rels where commerceAccountGroupId = &#63; from the database.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 */
	@Override
	public void removeByCommerceAccountGroupId(long commerceAccountGroupId) {
		for (CommerceAccountGroupCommerceAccountRel
				commerceAccountGroupCommerceAccountRel :
					findByCommerceAccountGroupId(
						commerceAccountGroupId, QueryUtil.ALL_POS,
						QueryUtil.ALL_POS, null)) {

			remove(commerceAccountGroupCommerceAccountRel);
		}
	}

	/**
	 * Returns the number of commerce account group commerce account rels where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @return the number of matching commerce account group commerce account rels
	 */
	@Override
	public int countByCommerceAccountGroupId(long commerceAccountGroupId) {
		FinderPath finderPath = _finderPathCountByCommerceAccountGroupId;

		Object[] finderArgs = new Object[] {commerceAccountGroupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(
				_SQL_COUNT_COMMERCEACCOUNTGROUPCOMMERCEACCOUNTREL_WHERE);

			query.append(
				_FINDER_COLUMN_COMMERCEACCOUNTGROUPID_COMMERCEACCOUNTGROUPID_2);

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

	private static final String
		_FINDER_COLUMN_COMMERCEACCOUNTGROUPID_COMMERCEACCOUNTGROUPID_2 =
			"commerceAccountGroupCommerceAccountRel.commerceAccountGroupId = ?";

	private FinderPath _finderPathWithPaginationFindByCommerceAccountId;
	private FinderPath _finderPathWithoutPaginationFindByCommerceAccountId;
	private FinderPath _finderPathCountByCommerceAccountId;

	/**
	 * Returns all the commerce account group commerce account rels where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @return the matching commerce account group commerce account rels
	 */
	@Override
	public List<CommerceAccountGroupCommerceAccountRel> findByCommerceAccountId(
		long commerceAccountId) {

		return findByCommerceAccountId(
			commerceAccountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce account group commerce account rels where commerceAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupCommerceAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param start the lower bound of the range of commerce account group commerce account rels
	 * @param end the upper bound of the range of commerce account group commerce account rels (not inclusive)
	 * @return the range of matching commerce account group commerce account rels
	 */
	@Override
	public List<CommerceAccountGroupCommerceAccountRel> findByCommerceAccountId(
		long commerceAccountId, int start, int end) {

		return findByCommerceAccountId(commerceAccountId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce account group commerce account rels where commerceAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupCommerceAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param start the lower bound of the range of commerce account group commerce account rels
	 * @param end the upper bound of the range of commerce account group commerce account rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce account group commerce account rels
	 */
	@Override
	public List<CommerceAccountGroupCommerceAccountRel> findByCommerceAccountId(
		long commerceAccountId, int start, int end,
		OrderByComparator<CommerceAccountGroupCommerceAccountRel>
			orderByComparator) {

		return findByCommerceAccountId(
			commerceAccountId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce account group commerce account rels where commerceAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupCommerceAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param start the lower bound of the range of commerce account group commerce account rels
	 * @param end the upper bound of the range of commerce account group commerce account rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce account group commerce account rels
	 */
	@Override
	public List<CommerceAccountGroupCommerceAccountRel> findByCommerceAccountId(
		long commerceAccountId, int start, int end,
		OrderByComparator<CommerceAccountGroupCommerceAccountRel>
			orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByCommerceAccountId;
				finderArgs = new Object[] {commerceAccountId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCommerceAccountId;
			finderArgs = new Object[] {
				commerceAccountId, start, end, orderByComparator
			};
		}

		List<CommerceAccountGroupCommerceAccountRel> list = null;

		if (useFinderCache) {
			list =
				(List<CommerceAccountGroupCommerceAccountRel>)
					finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceAccountGroupCommerceAccountRel
						commerceAccountGroupCommerceAccountRel : list) {

					if ((commerceAccountId !=
							commerceAccountGroupCommerceAccountRel.
								getCommerceAccountId())) {

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

			query.append(
				_SQL_SELECT_COMMERCEACCOUNTGROUPCOMMERCEACCOUNTREL_WHERE);

			query.append(_FINDER_COLUMN_COMMERCEACCOUNTID_COMMERCEACCOUNTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(
					CommerceAccountGroupCommerceAccountRelModelImpl.
						ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceAccountId);

				if (!pagination) {
					list =
						(List<CommerceAccountGroupCommerceAccountRel>)
							QueryUtil.list(q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list =
						(List<CommerceAccountGroupCommerceAccountRel>)
							QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first commerce account group commerce account rel in the ordered set where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce account group commerce account rel
	 * @throws NoSuchAccountGroupCommerceAccountRelException if a matching commerce account group commerce account rel could not be found
	 */
	@Override
	public CommerceAccountGroupCommerceAccountRel findByCommerceAccountId_First(
			long commerceAccountId,
			OrderByComparator<CommerceAccountGroupCommerceAccountRel>
				orderByComparator)
		throws NoSuchAccountGroupCommerceAccountRelException {

		CommerceAccountGroupCommerceAccountRel
			commerceAccountGroupCommerceAccountRel =
				fetchByCommerceAccountId_First(
					commerceAccountId, orderByComparator);

		if (commerceAccountGroupCommerceAccountRel != null) {
			return commerceAccountGroupCommerceAccountRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceAccountId=");
		msg.append(commerceAccountId);

		msg.append("}");

		throw new NoSuchAccountGroupCommerceAccountRelException(msg.toString());
	}

	/**
	 * Returns the first commerce account group commerce account rel in the ordered set where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce account group commerce account rel, or <code>null</code> if a matching commerce account group commerce account rel could not be found
	 */
	@Override
	public CommerceAccountGroupCommerceAccountRel
		fetchByCommerceAccountId_First(
			long commerceAccountId,
			OrderByComparator<CommerceAccountGroupCommerceAccountRel>
				orderByComparator) {

		List<CommerceAccountGroupCommerceAccountRel> list =
			findByCommerceAccountId(commerceAccountId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce account group commerce account rel in the ordered set where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce account group commerce account rel
	 * @throws NoSuchAccountGroupCommerceAccountRelException if a matching commerce account group commerce account rel could not be found
	 */
	@Override
	public CommerceAccountGroupCommerceAccountRel findByCommerceAccountId_Last(
			long commerceAccountId,
			OrderByComparator<CommerceAccountGroupCommerceAccountRel>
				orderByComparator)
		throws NoSuchAccountGroupCommerceAccountRelException {

		CommerceAccountGroupCommerceAccountRel
			commerceAccountGroupCommerceAccountRel =
				fetchByCommerceAccountId_Last(
					commerceAccountId, orderByComparator);

		if (commerceAccountGroupCommerceAccountRel != null) {
			return commerceAccountGroupCommerceAccountRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceAccountId=");
		msg.append(commerceAccountId);

		msg.append("}");

		throw new NoSuchAccountGroupCommerceAccountRelException(msg.toString());
	}

	/**
	 * Returns the last commerce account group commerce account rel in the ordered set where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce account group commerce account rel, or <code>null</code> if a matching commerce account group commerce account rel could not be found
	 */
	@Override
	public CommerceAccountGroupCommerceAccountRel fetchByCommerceAccountId_Last(
		long commerceAccountId,
		OrderByComparator<CommerceAccountGroupCommerceAccountRel>
			orderByComparator) {

		int count = countByCommerceAccountId(commerceAccountId);

		if (count == 0) {
			return null;
		}

		List<CommerceAccountGroupCommerceAccountRel> list =
			findByCommerceAccountId(
				commerceAccountId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce account group commerce account rels before and after the current commerce account group commerce account rel in the ordered set where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountGroupCommerceAccountRelId the primary key of the current commerce account group commerce account rel
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce account group commerce account rel
	 * @throws NoSuchAccountGroupCommerceAccountRelException if a commerce account group commerce account rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountGroupCommerceAccountRel[]
			findByCommerceAccountId_PrevAndNext(
				long commerceAccountGroupCommerceAccountRelId,
				long commerceAccountId,
				OrderByComparator<CommerceAccountGroupCommerceAccountRel>
					orderByComparator)
		throws NoSuchAccountGroupCommerceAccountRelException {

		CommerceAccountGroupCommerceAccountRel
			commerceAccountGroupCommerceAccountRel = findByPrimaryKey(
				commerceAccountGroupCommerceAccountRelId);

		Session session = null;

		try {
			session = openSession();

			CommerceAccountGroupCommerceAccountRel[] array =
				new CommerceAccountGroupCommerceAccountRelImpl[3];

			array[0] = getByCommerceAccountId_PrevAndNext(
				session, commerceAccountGroupCommerceAccountRel,
				commerceAccountId, orderByComparator, true);

			array[1] = commerceAccountGroupCommerceAccountRel;

			array[2] = getByCommerceAccountId_PrevAndNext(
				session, commerceAccountGroupCommerceAccountRel,
				commerceAccountId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceAccountGroupCommerceAccountRel
		getByCommerceAccountId_PrevAndNext(
			Session session,
			CommerceAccountGroupCommerceAccountRel
				commerceAccountGroupCommerceAccountRel,
			long commerceAccountId,
			OrderByComparator<CommerceAccountGroupCommerceAccountRel>
				orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEACCOUNTGROUPCOMMERCEACCOUNTREL_WHERE);

		query.append(_FINDER_COLUMN_COMMERCEACCOUNTID_COMMERCEACCOUNTID_2);

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
			query.append(
				CommerceAccountGroupCommerceAccountRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceAccountId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceAccountGroupCommerceAccountRel)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceAccountGroupCommerceAccountRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce account group commerce account rels where commerceAccountId = &#63; from the database.
	 *
	 * @param commerceAccountId the commerce account ID
	 */
	@Override
	public void removeByCommerceAccountId(long commerceAccountId) {
		for (CommerceAccountGroupCommerceAccountRel
				commerceAccountGroupCommerceAccountRel :
					findByCommerceAccountId(
						commerceAccountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
						null)) {

			remove(commerceAccountGroupCommerceAccountRel);
		}
	}

	/**
	 * Returns the number of commerce account group commerce account rels where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @return the number of matching commerce account group commerce account rels
	 */
	@Override
	public int countByCommerceAccountId(long commerceAccountId) {
		FinderPath finderPath = _finderPathCountByCommerceAccountId;

		Object[] finderArgs = new Object[] {commerceAccountId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(
				_SQL_COUNT_COMMERCEACCOUNTGROUPCOMMERCEACCOUNTREL_WHERE);

			query.append(_FINDER_COLUMN_COMMERCEACCOUNTID_COMMERCEACCOUNTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceAccountId);

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

	private static final String
		_FINDER_COLUMN_COMMERCEACCOUNTID_COMMERCEACCOUNTID_2 =
			"commerceAccountGroupCommerceAccountRel.commerceAccountId = ?";

	private FinderPath _finderPathFetchByC_ERC;
	private FinderPath _finderPathCountByC_ERC;

	/**
	 * Returns the commerce account group commerce account rel where companyId = &#63; and externalReferenceCode = &#63; or throws a <code>NoSuchAccountGroupCommerceAccountRelException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce account group commerce account rel
	 * @throws NoSuchAccountGroupCommerceAccountRelException if a matching commerce account group commerce account rel could not be found
	 */
	@Override
	public CommerceAccountGroupCommerceAccountRel findByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchAccountGroupCommerceAccountRelException {

		CommerceAccountGroupCommerceAccountRel
			commerceAccountGroupCommerceAccountRel = fetchByC_ERC(
				companyId, externalReferenceCode);

		if (commerceAccountGroupCommerceAccountRel == null) {
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

			throw new NoSuchAccountGroupCommerceAccountRelException(
				msg.toString());
		}

		return commerceAccountGroupCommerceAccountRel;
	}

	/**
	 * Returns the commerce account group commerce account rel where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce account group commerce account rel, or <code>null</code> if a matching commerce account group commerce account rel could not be found
	 */
	@Override
	public CommerceAccountGroupCommerceAccountRel fetchByC_ERC(
		long companyId, String externalReferenceCode) {

		return fetchByC_ERC(companyId, externalReferenceCode, true);
	}

	/**
	 * Returns the commerce account group commerce account rel where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce account group commerce account rel, or <code>null</code> if a matching commerce account group commerce account rel could not be found
	 */
	@Override
	public CommerceAccountGroupCommerceAccountRel fetchByC_ERC(
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

		if (result instanceof CommerceAccountGroupCommerceAccountRel) {
			CommerceAccountGroupCommerceAccountRel
				commerceAccountGroupCommerceAccountRel =
					(CommerceAccountGroupCommerceAccountRel)result;

			if ((companyId !=
					commerceAccountGroupCommerceAccountRel.getCompanyId()) ||
				!Objects.equals(
					externalReferenceCode,
					commerceAccountGroupCommerceAccountRel.
						getExternalReferenceCode())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(
				_SQL_SELECT_COMMERCEACCOUNTGROUPCOMMERCEACCOUNTREL_WHERE);

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

				List<CommerceAccountGroupCommerceAccountRel> list = q.list();

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
								"CommerceAccountGroupCommerceAccountRelPersistenceImpl.fetchByC_ERC(long, String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CommerceAccountGroupCommerceAccountRel
						commerceAccountGroupCommerceAccountRel = list.get(0);

					result = commerceAccountGroupCommerceAccountRel;

					cacheResult(commerceAccountGroupCommerceAccountRel);
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
			return (CommerceAccountGroupCommerceAccountRel)result;
		}
	}

	/**
	 * Removes the commerce account group commerce account rel where companyId = &#63; and externalReferenceCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the commerce account group commerce account rel that was removed
	 */
	@Override
	public CommerceAccountGroupCommerceAccountRel removeByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchAccountGroupCommerceAccountRelException {

		CommerceAccountGroupCommerceAccountRel
			commerceAccountGroupCommerceAccountRel = findByC_ERC(
				companyId, externalReferenceCode);

		return remove(commerceAccountGroupCommerceAccountRel);
	}

	/**
	 * Returns the number of commerce account group commerce account rels where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the number of matching commerce account group commerce account rels
	 */
	@Override
	public int countByC_ERC(long companyId, String externalReferenceCode) {
		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		FinderPath finderPath = _finderPathCountByC_ERC;

		Object[] finderArgs = new Object[] {companyId, externalReferenceCode};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(
				_SQL_COUNT_COMMERCEACCOUNTGROUPCOMMERCEACCOUNTREL_WHERE);

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
		"commerceAccountGroupCommerceAccountRel.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2 =
		"commerceAccountGroupCommerceAccountRel.externalReferenceCode = ?";

	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3 =
		"(commerceAccountGroupCommerceAccountRel.externalReferenceCode IS NULL OR commerceAccountGroupCommerceAccountRel.externalReferenceCode = '')";

	public CommerceAccountGroupCommerceAccountRelPersistenceImpl() {
		setModelClass(CommerceAccountGroupCommerceAccountRel.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put(
			"commerceAccountGroupCommerceAccountRelId",
			"CAccountGroupCAccountRelId");

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
	 * Caches the commerce account group commerce account rel in the entity cache if it is enabled.
	 *
	 * @param commerceAccountGroupCommerceAccountRel the commerce account group commerce account rel
	 */
	@Override
	public void cacheResult(
		CommerceAccountGroupCommerceAccountRel
			commerceAccountGroupCommerceAccountRel) {

		entityCache.putResult(
			CommerceAccountGroupCommerceAccountRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceAccountGroupCommerceAccountRelImpl.class,
			commerceAccountGroupCommerceAccountRel.getPrimaryKey(),
			commerceAccountGroupCommerceAccountRel);

		finderCache.putResult(
			_finderPathFetchByC_ERC,
			new Object[] {
				commerceAccountGroupCommerceAccountRel.getCompanyId(),
				commerceAccountGroupCommerceAccountRel.
					getExternalReferenceCode()
			},
			commerceAccountGroupCommerceAccountRel);

		commerceAccountGroupCommerceAccountRel.resetOriginalValues();
	}

	/**
	 * Caches the commerce account group commerce account rels in the entity cache if it is enabled.
	 *
	 * @param commerceAccountGroupCommerceAccountRels the commerce account group commerce account rels
	 */
	@Override
	public void cacheResult(
		List<CommerceAccountGroupCommerceAccountRel>
			commerceAccountGroupCommerceAccountRels) {

		for (CommerceAccountGroupCommerceAccountRel
				commerceAccountGroupCommerceAccountRel :
					commerceAccountGroupCommerceAccountRels) {

			if (entityCache.getResult(
					CommerceAccountGroupCommerceAccountRelModelImpl.
						ENTITY_CACHE_ENABLED,
					CommerceAccountGroupCommerceAccountRelImpl.class,
					commerceAccountGroupCommerceAccountRel.getPrimaryKey()) ==
						null) {

				cacheResult(commerceAccountGroupCommerceAccountRel);
			}
			else {
				commerceAccountGroupCommerceAccountRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce account group commerce account rels.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(
			CommerceAccountGroupCommerceAccountRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce account group commerce account rel.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CommerceAccountGroupCommerceAccountRel
			commerceAccountGroupCommerceAccountRel) {

		entityCache.removeResult(
			CommerceAccountGroupCommerceAccountRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceAccountGroupCommerceAccountRelImpl.class,
			commerceAccountGroupCommerceAccountRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(CommerceAccountGroupCommerceAccountRelModelImpl)
				commerceAccountGroupCommerceAccountRel,
			true);
	}

	@Override
	public void clearCache(
		List<CommerceAccountGroupCommerceAccountRel>
			commerceAccountGroupCommerceAccountRels) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceAccountGroupCommerceAccountRel
				commerceAccountGroupCommerceAccountRel :
					commerceAccountGroupCommerceAccountRels) {

			entityCache.removeResult(
				CommerceAccountGroupCommerceAccountRelModelImpl.
					ENTITY_CACHE_ENABLED,
				CommerceAccountGroupCommerceAccountRelImpl.class,
				commerceAccountGroupCommerceAccountRel.getPrimaryKey());

			clearUniqueFindersCache(
				(CommerceAccountGroupCommerceAccountRelModelImpl)
					commerceAccountGroupCommerceAccountRel,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		CommerceAccountGroupCommerceAccountRelModelImpl
			commerceAccountGroupCommerceAccountRelModelImpl) {

		Object[] args = new Object[] {
			commerceAccountGroupCommerceAccountRelModelImpl.getCompanyId(),
			commerceAccountGroupCommerceAccountRelModelImpl.
				getExternalReferenceCode()
		};

		finderCache.putResult(
			_finderPathCountByC_ERC, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_ERC, args,
			commerceAccountGroupCommerceAccountRelModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CommerceAccountGroupCommerceAccountRelModelImpl
			commerceAccountGroupCommerceAccountRelModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				commerceAccountGroupCommerceAccountRelModelImpl.getCompanyId(),
				commerceAccountGroupCommerceAccountRelModelImpl.
					getExternalReferenceCode()
			};

			finderCache.removeResult(_finderPathCountByC_ERC, args);
			finderCache.removeResult(_finderPathFetchByC_ERC, args);
		}

		if ((commerceAccountGroupCommerceAccountRelModelImpl.
				getColumnBitmask() &
			 _finderPathFetchByC_ERC.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				commerceAccountGroupCommerceAccountRelModelImpl.
					getOriginalCompanyId(),
				commerceAccountGroupCommerceAccountRelModelImpl.
					getOriginalExternalReferenceCode()
			};

			finderCache.removeResult(_finderPathCountByC_ERC, args);
			finderCache.removeResult(_finderPathFetchByC_ERC, args);
		}
	}

	/**
	 * Creates a new commerce account group commerce account rel with the primary key. Does not add the commerce account group commerce account rel to the database.
	 *
	 * @param commerceAccountGroupCommerceAccountRelId the primary key for the new commerce account group commerce account rel
	 * @return the new commerce account group commerce account rel
	 */
	@Override
	public CommerceAccountGroupCommerceAccountRel create(
		long commerceAccountGroupCommerceAccountRelId) {

		CommerceAccountGroupCommerceAccountRel
			commerceAccountGroupCommerceAccountRel =
				new CommerceAccountGroupCommerceAccountRelImpl();

		commerceAccountGroupCommerceAccountRel.setNew(true);
		commerceAccountGroupCommerceAccountRel.setPrimaryKey(
			commerceAccountGroupCommerceAccountRelId);

		commerceAccountGroupCommerceAccountRel.setCompanyId(
			CompanyThreadLocal.getCompanyId());

		return commerceAccountGroupCommerceAccountRel;
	}

	/**
	 * Removes the commerce account group commerce account rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceAccountGroupCommerceAccountRelId the primary key of the commerce account group commerce account rel
	 * @return the commerce account group commerce account rel that was removed
	 * @throws NoSuchAccountGroupCommerceAccountRelException if a commerce account group commerce account rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountGroupCommerceAccountRel remove(
			long commerceAccountGroupCommerceAccountRelId)
		throws NoSuchAccountGroupCommerceAccountRelException {

		return remove((Serializable)commerceAccountGroupCommerceAccountRelId);
	}

	/**
	 * Removes the commerce account group commerce account rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce account group commerce account rel
	 * @return the commerce account group commerce account rel that was removed
	 * @throws NoSuchAccountGroupCommerceAccountRelException if a commerce account group commerce account rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountGroupCommerceAccountRel remove(
			Serializable primaryKey)
		throws NoSuchAccountGroupCommerceAccountRelException {

		Session session = null;

		try {
			session = openSession();

			CommerceAccountGroupCommerceAccountRel
				commerceAccountGroupCommerceAccountRel =
					(CommerceAccountGroupCommerceAccountRel)session.get(
						CommerceAccountGroupCommerceAccountRelImpl.class,
						primaryKey);

			if (commerceAccountGroupCommerceAccountRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountGroupCommerceAccountRelException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commerceAccountGroupCommerceAccountRel);
		}
		catch (NoSuchAccountGroupCommerceAccountRelException nsee) {
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
	protected CommerceAccountGroupCommerceAccountRel removeImpl(
		CommerceAccountGroupCommerceAccountRel
			commerceAccountGroupCommerceAccountRel) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceAccountGroupCommerceAccountRel)) {
				commerceAccountGroupCommerceAccountRel =
					(CommerceAccountGroupCommerceAccountRel)session.get(
						CommerceAccountGroupCommerceAccountRelImpl.class,
						commerceAccountGroupCommerceAccountRel.
							getPrimaryKeyObj());
			}

			if (commerceAccountGroupCommerceAccountRel != null) {
				session.delete(commerceAccountGroupCommerceAccountRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceAccountGroupCommerceAccountRel != null) {
			clearCache(commerceAccountGroupCommerceAccountRel);
		}

		return commerceAccountGroupCommerceAccountRel;
	}

	@Override
	public CommerceAccountGroupCommerceAccountRel updateImpl(
		CommerceAccountGroupCommerceAccountRel
			commerceAccountGroupCommerceAccountRel) {

		boolean isNew = commerceAccountGroupCommerceAccountRel.isNew();

		if (!(commerceAccountGroupCommerceAccountRel instanceof
				CommerceAccountGroupCommerceAccountRelModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(
					commerceAccountGroupCommerceAccountRel.getClass())) {

				invocationHandler = ProxyUtil.getInvocationHandler(
					commerceAccountGroupCommerceAccountRel);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceAccountGroupCommerceAccountRel proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceAccountGroupCommerceAccountRel implementation " +
					commerceAccountGroupCommerceAccountRel.getClass());
		}

		CommerceAccountGroupCommerceAccountRelModelImpl
			commerceAccountGroupCommerceAccountRelModelImpl =
				(CommerceAccountGroupCommerceAccountRelModelImpl)
					commerceAccountGroupCommerceAccountRel;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew &&
			(commerceAccountGroupCommerceAccountRel.getCreateDate() == null)) {

			if (serviceContext == null) {
				commerceAccountGroupCommerceAccountRel.setCreateDate(now);
			}
			else {
				commerceAccountGroupCommerceAccountRel.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!commerceAccountGroupCommerceAccountRelModelImpl.
				hasSetModifiedDate()) {

			if (serviceContext == null) {
				commerceAccountGroupCommerceAccountRel.setModifiedDate(now);
			}
			else {
				commerceAccountGroupCommerceAccountRel.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceAccountGroupCommerceAccountRel.isNew()) {
				session.save(commerceAccountGroupCommerceAccountRel);

				commerceAccountGroupCommerceAccountRel.setNew(false);
			}
			else {
				commerceAccountGroupCommerceAccountRel =
					(CommerceAccountGroupCommerceAccountRel)session.merge(
						commerceAccountGroupCommerceAccountRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceAccountGroupCommerceAccountRelModelImpl.
				COLUMN_BITMASK_ENABLED) {

			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				commerceAccountGroupCommerceAccountRelModelImpl.
					getCommerceAccountGroupId()
			};

			finderCache.removeResult(
				_finderPathCountByCommerceAccountGroupId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCommerceAccountGroupId, args);

			args = new Object[] {
				commerceAccountGroupCommerceAccountRelModelImpl.
					getCommerceAccountId()
			};

			finderCache.removeResult(_finderPathCountByCommerceAccountId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCommerceAccountId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((commerceAccountGroupCommerceAccountRelModelImpl.
					getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCommerceAccountGroupId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceAccountGroupCommerceAccountRelModelImpl.
						getOriginalCommerceAccountGroupId()
				};

				finderCache.removeResult(
					_finderPathCountByCommerceAccountGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommerceAccountGroupId,
					args);

				args = new Object[] {
					commerceAccountGroupCommerceAccountRelModelImpl.
						getCommerceAccountGroupId()
				};

				finderCache.removeResult(
					_finderPathCountByCommerceAccountGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommerceAccountGroupId,
					args);
			}

			if ((commerceAccountGroupCommerceAccountRelModelImpl.
					getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCommerceAccountId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceAccountGroupCommerceAccountRelModelImpl.
						getOriginalCommerceAccountId()
				};

				finderCache.removeResult(
					_finderPathCountByCommerceAccountId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommerceAccountId, args);

				args = new Object[] {
					commerceAccountGroupCommerceAccountRelModelImpl.
						getCommerceAccountId()
				};

				finderCache.removeResult(
					_finderPathCountByCommerceAccountId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommerceAccountId, args);
			}
		}

		entityCache.putResult(
			CommerceAccountGroupCommerceAccountRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceAccountGroupCommerceAccountRelImpl.class,
			commerceAccountGroupCommerceAccountRel.getPrimaryKey(),
			commerceAccountGroupCommerceAccountRel, false);

		clearUniqueFindersCache(
			commerceAccountGroupCommerceAccountRelModelImpl, false);
		cacheUniqueFindersCache(
			commerceAccountGroupCommerceAccountRelModelImpl);

		commerceAccountGroupCommerceAccountRel.resetOriginalValues();

		return commerceAccountGroupCommerceAccountRel;
	}

	/**
	 * Returns the commerce account group commerce account rel with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce account group commerce account rel
	 * @return the commerce account group commerce account rel
	 * @throws NoSuchAccountGroupCommerceAccountRelException if a commerce account group commerce account rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountGroupCommerceAccountRel findByPrimaryKey(
			Serializable primaryKey)
		throws NoSuchAccountGroupCommerceAccountRelException {

		CommerceAccountGroupCommerceAccountRel
			commerceAccountGroupCommerceAccountRel = fetchByPrimaryKey(
				primaryKey);

		if (commerceAccountGroupCommerceAccountRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccountGroupCommerceAccountRelException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commerceAccountGroupCommerceAccountRel;
	}

	/**
	 * Returns the commerce account group commerce account rel with the primary key or throws a <code>NoSuchAccountGroupCommerceAccountRelException</code> if it could not be found.
	 *
	 * @param commerceAccountGroupCommerceAccountRelId the primary key of the commerce account group commerce account rel
	 * @return the commerce account group commerce account rel
	 * @throws NoSuchAccountGroupCommerceAccountRelException if a commerce account group commerce account rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountGroupCommerceAccountRel findByPrimaryKey(
			long commerceAccountGroupCommerceAccountRelId)
		throws NoSuchAccountGroupCommerceAccountRelException {

		return findByPrimaryKey(
			(Serializable)commerceAccountGroupCommerceAccountRelId);
	}

	/**
	 * Returns the commerce account group commerce account rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce account group commerce account rel
	 * @return the commerce account group commerce account rel, or <code>null</code> if a commerce account group commerce account rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountGroupCommerceAccountRel fetchByPrimaryKey(
		Serializable primaryKey) {

		Serializable serializable = entityCache.getResult(
			CommerceAccountGroupCommerceAccountRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceAccountGroupCommerceAccountRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceAccountGroupCommerceAccountRel
			commerceAccountGroupCommerceAccountRel =
				(CommerceAccountGroupCommerceAccountRel)serializable;

		if (commerceAccountGroupCommerceAccountRel == null) {
			Session session = null;

			try {
				session = openSession();

				commerceAccountGroupCommerceAccountRel =
					(CommerceAccountGroupCommerceAccountRel)session.get(
						CommerceAccountGroupCommerceAccountRelImpl.class,
						primaryKey);

				if (commerceAccountGroupCommerceAccountRel != null) {
					cacheResult(commerceAccountGroupCommerceAccountRel);
				}
				else {
					entityCache.putResult(
						CommerceAccountGroupCommerceAccountRelModelImpl.
							ENTITY_CACHE_ENABLED,
						CommerceAccountGroupCommerceAccountRelImpl.class,
						primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CommerceAccountGroupCommerceAccountRelModelImpl.
						ENTITY_CACHE_ENABLED,
					CommerceAccountGroupCommerceAccountRelImpl.class,
					primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceAccountGroupCommerceAccountRel;
	}

	/**
	 * Returns the commerce account group commerce account rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceAccountGroupCommerceAccountRelId the primary key of the commerce account group commerce account rel
	 * @return the commerce account group commerce account rel, or <code>null</code> if a commerce account group commerce account rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountGroupCommerceAccountRel fetchByPrimaryKey(
		long commerceAccountGroupCommerceAccountRelId) {

		return fetchByPrimaryKey(
			(Serializable)commerceAccountGroupCommerceAccountRelId);
	}

	@Override
	public Map<Serializable, CommerceAccountGroupCommerceAccountRel>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceAccountGroupCommerceAccountRel> map =
			new HashMap<Serializable, CommerceAccountGroupCommerceAccountRel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceAccountGroupCommerceAccountRel
				commerceAccountGroupCommerceAccountRel = fetchByPrimaryKey(
					primaryKey);

			if (commerceAccountGroupCommerceAccountRel != null) {
				map.put(primaryKey, commerceAccountGroupCommerceAccountRel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CommerceAccountGroupCommerceAccountRelModelImpl.
					ENTITY_CACHE_ENABLED,
				CommerceAccountGroupCommerceAccountRelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(
						primaryKey,
						(CommerceAccountGroupCommerceAccountRel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(
			_SQL_SELECT_COMMERCEACCOUNTGROUPCOMMERCEACCOUNTREL_WHERE_PKS_IN);

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

			for (CommerceAccountGroupCommerceAccountRel
					commerceAccountGroupCommerceAccountRel :
						(List<CommerceAccountGroupCommerceAccountRel>)
							q.list()) {

				map.put(
					commerceAccountGroupCommerceAccountRel.getPrimaryKeyObj(),
					commerceAccountGroupCommerceAccountRel);

				cacheResult(commerceAccountGroupCommerceAccountRel);

				uncachedPrimaryKeys.remove(
					commerceAccountGroupCommerceAccountRel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CommerceAccountGroupCommerceAccountRelModelImpl.
						ENTITY_CACHE_ENABLED,
					CommerceAccountGroupCommerceAccountRelImpl.class,
					primaryKey, nullModel);
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
	 * Returns all the commerce account group commerce account rels.
	 *
	 * @return the commerce account group commerce account rels
	 */
	@Override
	public List<CommerceAccountGroupCommerceAccountRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce account group commerce account rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupCommerceAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce account group commerce account rels
	 * @param end the upper bound of the range of commerce account group commerce account rels (not inclusive)
	 * @return the range of commerce account group commerce account rels
	 */
	@Override
	public List<CommerceAccountGroupCommerceAccountRel> findAll(
		int start, int end) {

		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce account group commerce account rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupCommerceAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce account group commerce account rels
	 * @param end the upper bound of the range of commerce account group commerce account rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce account group commerce account rels
	 */
	@Override
	public List<CommerceAccountGroupCommerceAccountRel> findAll(
		int start, int end,
		OrderByComparator<CommerceAccountGroupCommerceAccountRel>
			orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce account group commerce account rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountGroupCommerceAccountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce account group commerce account rels
	 * @param end the upper bound of the range of commerce account group commerce account rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce account group commerce account rels
	 */
	@Override
	public List<CommerceAccountGroupCommerceAccountRel> findAll(
		int start, int end,
		OrderByComparator<CommerceAccountGroupCommerceAccountRel>
			orderByComparator,
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

		List<CommerceAccountGroupCommerceAccountRel> list = null;

		if (useFinderCache) {
			list =
				(List<CommerceAccountGroupCommerceAccountRel>)
					finderCache.getResult(finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(
					_SQL_SELECT_COMMERCEACCOUNTGROUPCOMMERCEACCOUNTREL);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEACCOUNTGROUPCOMMERCEACCOUNTREL;

				if (pagination) {
					sql = sql.concat(
						CommerceAccountGroupCommerceAccountRelModelImpl.
							ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list =
						(List<CommerceAccountGroupCommerceAccountRel>)
							QueryUtil.list(q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list =
						(List<CommerceAccountGroupCommerceAccountRel>)
							QueryUtil.list(q, getDialect(), start, end);
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
	 * Removes all the commerce account group commerce account rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceAccountGroupCommerceAccountRel
				commerceAccountGroupCommerceAccountRel : findAll()) {

			remove(commerceAccountGroupCommerceAccountRel);
		}
	}

	/**
	 * Returns the number of commerce account group commerce account rels.
	 *
	 * @return the number of commerce account group commerce account rels
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
					_SQL_COUNT_COMMERCEACCOUNTGROUPCOMMERCEACCOUNTREL);

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
		return CommerceAccountGroupCommerceAccountRelModelImpl.
			TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce account group commerce account rel persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CommerceAccountGroupCommerceAccountRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceAccountGroupCommerceAccountRelModelImpl.
				FINDER_CACHE_ENABLED,
			CommerceAccountGroupCommerceAccountRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CommerceAccountGroupCommerceAccountRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceAccountGroupCommerceAccountRelModelImpl.
				FINDER_CACHE_ENABLED,
			CommerceAccountGroupCommerceAccountRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CommerceAccountGroupCommerceAccountRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceAccountGroupCommerceAccountRelModelImpl.
				FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByCommerceAccountGroupId = new FinderPath(
			CommerceAccountGroupCommerceAccountRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceAccountGroupCommerceAccountRelModelImpl.
				FINDER_CACHE_ENABLED,
			CommerceAccountGroupCommerceAccountRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCommerceAccountGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCommerceAccountGroupId =
			new FinderPath(
				CommerceAccountGroupCommerceAccountRelModelImpl.
					ENTITY_CACHE_ENABLED,
				CommerceAccountGroupCommerceAccountRelModelImpl.
					FINDER_CACHE_ENABLED,
				CommerceAccountGroupCommerceAccountRelImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByCommerceAccountGroupId",
				new String[] {Long.class.getName()},
				CommerceAccountGroupCommerceAccountRelModelImpl.
					COMMERCEACCOUNTGROUPID_COLUMN_BITMASK);

		_finderPathCountByCommerceAccountGroupId = new FinderPath(
			CommerceAccountGroupCommerceAccountRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceAccountGroupCommerceAccountRelModelImpl.
				FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceAccountGroupId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByCommerceAccountId = new FinderPath(
			CommerceAccountGroupCommerceAccountRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceAccountGroupCommerceAccountRelModelImpl.
				FINDER_CACHE_ENABLED,
			CommerceAccountGroupCommerceAccountRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCommerceAccountId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCommerceAccountId = new FinderPath(
			CommerceAccountGroupCommerceAccountRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceAccountGroupCommerceAccountRelModelImpl.
				FINDER_CACHE_ENABLED,
			CommerceAccountGroupCommerceAccountRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCommerceAccountId", new String[] {Long.class.getName()},
			CommerceAccountGroupCommerceAccountRelModelImpl.
				COMMERCEACCOUNTID_COLUMN_BITMASK);

		_finderPathCountByCommerceAccountId = new FinderPath(
			CommerceAccountGroupCommerceAccountRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceAccountGroupCommerceAccountRelModelImpl.
				FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceAccountId", new String[] {Long.class.getName()});

		_finderPathFetchByC_ERC = new FinderPath(
			CommerceAccountGroupCommerceAccountRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceAccountGroupCommerceAccountRelModelImpl.
				FINDER_CACHE_ENABLED,
			CommerceAccountGroupCommerceAccountRelImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByC_ERC",
			new String[] {Long.class.getName(), String.class.getName()},
			CommerceAccountGroupCommerceAccountRelModelImpl.
				COMPANYID_COLUMN_BITMASK |
			CommerceAccountGroupCommerceAccountRelModelImpl.
				EXTERNALREFERENCECODE_COLUMN_BITMASK);

		_finderPathCountByC_ERC = new FinderPath(
			CommerceAccountGroupCommerceAccountRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceAccountGroupCommerceAccountRelModelImpl.
				FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByC_ERC",
			new String[] {Long.class.getName(), String.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(
			CommerceAccountGroupCommerceAccountRelImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String
		_SQL_SELECT_COMMERCEACCOUNTGROUPCOMMERCEACCOUNTREL =
			"SELECT commerceAccountGroupCommerceAccountRel FROM CommerceAccountGroupCommerceAccountRel commerceAccountGroupCommerceAccountRel";

	private static final String
		_SQL_SELECT_COMMERCEACCOUNTGROUPCOMMERCEACCOUNTREL_WHERE_PKS_IN =
			"SELECT commerceAccountGroupCommerceAccountRel FROM CommerceAccountGroupCommerceAccountRel commerceAccountGroupCommerceAccountRel WHERE CAccountGroupCAccountRelId IN (";

	private static final String
		_SQL_SELECT_COMMERCEACCOUNTGROUPCOMMERCEACCOUNTREL_WHERE =
			"SELECT commerceAccountGroupCommerceAccountRel FROM CommerceAccountGroupCommerceAccountRel commerceAccountGroupCommerceAccountRel WHERE ";

	private static final String
		_SQL_COUNT_COMMERCEACCOUNTGROUPCOMMERCEACCOUNTREL =
			"SELECT COUNT(commerceAccountGroupCommerceAccountRel) FROM CommerceAccountGroupCommerceAccountRel commerceAccountGroupCommerceAccountRel";

	private static final String
		_SQL_COUNT_COMMERCEACCOUNTGROUPCOMMERCEACCOUNTREL_WHERE =
			"SELECT COUNT(commerceAccountGroupCommerceAccountRel) FROM CommerceAccountGroupCommerceAccountRel commerceAccountGroupCommerceAccountRel WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"commerceAccountGroupCommerceAccountRel.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommerceAccountGroupCommerceAccountRel exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommerceAccountGroupCommerceAccountRel exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAccountGroupCommerceAccountRelPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"commerceAccountGroupCommerceAccountRelId"});

}