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

package com.liferay.commerce.discount.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.discount.exception.NoSuchDiscountCommerceAccountGroupRelException;
import com.liferay.commerce.discount.model.CommerceDiscountCommerceAccountGroupRel;
import com.liferay.commerce.discount.model.impl.CommerceDiscountCommerceAccountGroupRelImpl;
import com.liferay.commerce.discount.model.impl.CommerceDiscountCommerceAccountGroupRelModelImpl;
import com.liferay.commerce.discount.service.persistence.CommerceDiscountCommerceAccountGroupRelPersistence;
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

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the commerce discount commerce account group rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @generated
 */
@ProviderType
public class CommerceDiscountCommerceAccountGroupRelPersistenceImpl
	extends BasePersistenceImpl<CommerceDiscountCommerceAccountGroupRel>
	implements CommerceDiscountCommerceAccountGroupRelPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommerceDiscountCommerceAccountGroupRelUtil</code> to access the commerce discount commerce account group rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommerceDiscountCommerceAccountGroupRelImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByCommerceDiscountId;
	private FinderPath _finderPathWithoutPaginationFindByCommerceDiscountId;
	private FinderPath _finderPathCountByCommerceDiscountId;

	/**
	 * Returns all the commerce discount commerce account group rels where commerceDiscountId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @return the matching commerce discount commerce account group rels
	 */
	@Override
	public List<CommerceDiscountCommerceAccountGroupRel>
		findByCommerceDiscountId(long commerceDiscountId) {

		return findByCommerceDiscountId(
			commerceDiscountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discount commerce account group rels where commerceDiscountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountCommerceAccountGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param start the lower bound of the range of commerce discount commerce account group rels
	 * @param end the upper bound of the range of commerce discount commerce account group rels (not inclusive)
	 * @return the range of matching commerce discount commerce account group rels
	 */
	@Override
	public List<CommerceDiscountCommerceAccountGroupRel>
		findByCommerceDiscountId(long commerceDiscountId, int start, int end) {

		return findByCommerceDiscountId(commerceDiscountId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discount commerce account group rels where commerceDiscountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountCommerceAccountGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param start the lower bound of the range of commerce discount commerce account group rels
	 * @param end the upper bound of the range of commerce discount commerce account group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce discount commerce account group rels
	 */
	@Override
	public List<CommerceDiscountCommerceAccountGroupRel>
		findByCommerceDiscountId(
			long commerceDiscountId, int start, int end,
			OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
				orderByComparator) {

		return findByCommerceDiscountId(
			commerceDiscountId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce discount commerce account group rels where commerceDiscountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountCommerceAccountGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param start the lower bound of the range of commerce discount commerce account group rels
	 * @param end the upper bound of the range of commerce discount commerce account group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce discount commerce account group rels
	 */
	@Override
	public List<CommerceDiscountCommerceAccountGroupRel>
		findByCommerceDiscountId(
			long commerceDiscountId, int start, int end,
			OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
				orderByComparator,
			boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByCommerceDiscountId;
			finderArgs = new Object[] {commerceDiscountId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByCommerceDiscountId;
			finderArgs = new Object[] {
				commerceDiscountId, start, end, orderByComparator
			};
		}

		List<CommerceDiscountCommerceAccountGroupRel> list = null;

		if (retrieveFromCache) {
			list =
				(List<CommerceDiscountCommerceAccountGroupRel>)
					finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceDiscountCommerceAccountGroupRel
						commerceDiscountCommerceAccountGroupRel : list) {

					if ((commerceDiscountId !=
							commerceDiscountCommerceAccountGroupRel.
								getCommerceDiscountId())) {

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
				_SQL_SELECT_COMMERCEDISCOUNTCOMMERCEACCOUNTGROUPREL_WHERE);

			query.append(
				_FINDER_COLUMN_COMMERCEDISCOUNTID_COMMERCEDISCOUNTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(
					CommerceDiscountCommerceAccountGroupRelModelImpl.
						ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceDiscountId);

				if (!pagination) {
					list =
						(List<CommerceDiscountCommerceAccountGroupRel>)
							QueryUtil.list(q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list =
						(List<CommerceDiscountCommerceAccountGroupRel>)
							QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first commerce discount commerce account group rel in the ordered set where commerceDiscountId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount commerce account group rel
	 * @throws NoSuchDiscountCommerceAccountGroupRelException if a matching commerce discount commerce account group rel could not be found
	 */
	@Override
	public CommerceDiscountCommerceAccountGroupRel
			findByCommerceDiscountId_First(
				long commerceDiscountId,
				OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
					orderByComparator)
		throws NoSuchDiscountCommerceAccountGroupRelException {

		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel =
				fetchByCommerceDiscountId_First(
					commerceDiscountId, orderByComparator);

		if (commerceDiscountCommerceAccountGroupRel != null) {
			return commerceDiscountCommerceAccountGroupRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceDiscountId=");
		msg.append(commerceDiscountId);

		msg.append("}");

		throw new NoSuchDiscountCommerceAccountGroupRelException(
			msg.toString());
	}

	/**
	 * Returns the first commerce discount commerce account group rel in the ordered set where commerceDiscountId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount commerce account group rel, or <code>null</code> if a matching commerce discount commerce account group rel could not be found
	 */
	@Override
	public CommerceDiscountCommerceAccountGroupRel
		fetchByCommerceDiscountId_First(
			long commerceDiscountId,
			OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
				orderByComparator) {

		List<CommerceDiscountCommerceAccountGroupRel> list =
			findByCommerceDiscountId(
				commerceDiscountId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce discount commerce account group rel in the ordered set where commerceDiscountId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount commerce account group rel
	 * @throws NoSuchDiscountCommerceAccountGroupRelException if a matching commerce discount commerce account group rel could not be found
	 */
	@Override
	public CommerceDiscountCommerceAccountGroupRel
			findByCommerceDiscountId_Last(
				long commerceDiscountId,
				OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
					orderByComparator)
		throws NoSuchDiscountCommerceAccountGroupRelException {

		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel =
				fetchByCommerceDiscountId_Last(
					commerceDiscountId, orderByComparator);

		if (commerceDiscountCommerceAccountGroupRel != null) {
			return commerceDiscountCommerceAccountGroupRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceDiscountId=");
		msg.append(commerceDiscountId);

		msg.append("}");

		throw new NoSuchDiscountCommerceAccountGroupRelException(
			msg.toString());
	}

	/**
	 * Returns the last commerce discount commerce account group rel in the ordered set where commerceDiscountId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount commerce account group rel, or <code>null</code> if a matching commerce discount commerce account group rel could not be found
	 */
	@Override
	public CommerceDiscountCommerceAccountGroupRel
		fetchByCommerceDiscountId_Last(
			long commerceDiscountId,
			OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
				orderByComparator) {

		int count = countByCommerceDiscountId(commerceDiscountId);

		if (count == 0) {
			return null;
		}

		List<CommerceDiscountCommerceAccountGroupRel> list =
			findByCommerceDiscountId(
				commerceDiscountId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce discount commerce account group rels before and after the current commerce discount commerce account group rel in the ordered set where commerceDiscountId = &#63;.
	 *
	 * @param commerceDiscountCommerceAccountGroupRelId the primary key of the current commerce discount commerce account group rel
	 * @param commerceDiscountId the commerce discount ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce discount commerce account group rel
	 * @throws NoSuchDiscountCommerceAccountGroupRelException if a commerce discount commerce account group rel with the primary key could not be found
	 */
	@Override
	public CommerceDiscountCommerceAccountGroupRel[]
			findByCommerceDiscountId_PrevAndNext(
				long commerceDiscountCommerceAccountGroupRelId,
				long commerceDiscountId,
				OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
					orderByComparator)
		throws NoSuchDiscountCommerceAccountGroupRelException {

		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel = findByPrimaryKey(
				commerceDiscountCommerceAccountGroupRelId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscountCommerceAccountGroupRel[] array =
				new CommerceDiscountCommerceAccountGroupRelImpl[3];

			array[0] = getByCommerceDiscountId_PrevAndNext(
				session, commerceDiscountCommerceAccountGroupRel,
				commerceDiscountId, orderByComparator, true);

			array[1] = commerceDiscountCommerceAccountGroupRel;

			array[2] = getByCommerceDiscountId_PrevAndNext(
				session, commerceDiscountCommerceAccountGroupRel,
				commerceDiscountId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceDiscountCommerceAccountGroupRel
		getByCommerceDiscountId_PrevAndNext(
			Session session,
			CommerceDiscountCommerceAccountGroupRel
				commerceDiscountCommerceAccountGroupRel,
			long commerceDiscountId,
			OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
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

		query.append(_SQL_SELECT_COMMERCEDISCOUNTCOMMERCEACCOUNTGROUPREL_WHERE);

		query.append(_FINDER_COLUMN_COMMERCEDISCOUNTID_COMMERCEDISCOUNTID_2);

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
				CommerceDiscountCommerceAccountGroupRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceDiscountId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceDiscountCommerceAccountGroupRel)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceDiscountCommerceAccountGroupRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce discount commerce account group rels where commerceDiscountId = &#63; from the database.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 */
	@Override
	public void removeByCommerceDiscountId(long commerceDiscountId) {
		for (CommerceDiscountCommerceAccountGroupRel
				commerceDiscountCommerceAccountGroupRel :
					findByCommerceDiscountId(
						commerceDiscountId, QueryUtil.ALL_POS,
						QueryUtil.ALL_POS, null)) {

			remove(commerceDiscountCommerceAccountGroupRel);
		}
	}

	/**
	 * Returns the number of commerce discount commerce account group rels where commerceDiscountId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @return the number of matching commerce discount commerce account group rels
	 */
	@Override
	public int countByCommerceDiscountId(long commerceDiscountId) {
		FinderPath finderPath = _finderPathCountByCommerceDiscountId;

		Object[] finderArgs = new Object[] {commerceDiscountId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(
				_SQL_COUNT_COMMERCEDISCOUNTCOMMERCEACCOUNTGROUPREL_WHERE);

			query.append(
				_FINDER_COLUMN_COMMERCEDISCOUNTID_COMMERCEDISCOUNTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceDiscountId);

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
		_FINDER_COLUMN_COMMERCEDISCOUNTID_COMMERCEDISCOUNTID_2 =
			"commerceDiscountCommerceAccountGroupRel.commerceDiscountId = ?";

	private FinderPath _finderPathWithPaginationFindByCommerceAccountGroupId;
	private FinderPath _finderPathWithoutPaginationFindByCommerceAccountGroupId;
	private FinderPath _finderPathCountByCommerceAccountGroupId;

	/**
	 * Returns all the commerce discount commerce account group rels where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @return the matching commerce discount commerce account group rels
	 */
	@Override
	public List<CommerceDiscountCommerceAccountGroupRel>
		findByCommerceAccountGroupId(long commerceAccountGroupId) {

		return findByCommerceAccountGroupId(
			commerceAccountGroupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discount commerce account group rels where commerceAccountGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountCommerceAccountGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param start the lower bound of the range of commerce discount commerce account group rels
	 * @param end the upper bound of the range of commerce discount commerce account group rels (not inclusive)
	 * @return the range of matching commerce discount commerce account group rels
	 */
	@Override
	public List<CommerceDiscountCommerceAccountGroupRel>
		findByCommerceAccountGroupId(
			long commerceAccountGroupId, int start, int end) {

		return findByCommerceAccountGroupId(
			commerceAccountGroupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discount commerce account group rels where commerceAccountGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountCommerceAccountGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param start the lower bound of the range of commerce discount commerce account group rels
	 * @param end the upper bound of the range of commerce discount commerce account group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce discount commerce account group rels
	 */
	@Override
	public List<CommerceDiscountCommerceAccountGroupRel>
		findByCommerceAccountGroupId(
			long commerceAccountGroupId, int start, int end,
			OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
				orderByComparator) {

		return findByCommerceAccountGroupId(
			commerceAccountGroupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce discount commerce account group rels where commerceAccountGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountCommerceAccountGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param start the lower bound of the range of commerce discount commerce account group rels
	 * @param end the upper bound of the range of commerce discount commerce account group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce discount commerce account group rels
	 */
	@Override
	public List<CommerceDiscountCommerceAccountGroupRel>
		findByCommerceAccountGroupId(
			long commerceAccountGroupId, int start, int end,
			OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
				orderByComparator,
			boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath =
				_finderPathWithoutPaginationFindByCommerceAccountGroupId;
			finderArgs = new Object[] {commerceAccountGroupId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByCommerceAccountGroupId;
			finderArgs = new Object[] {
				commerceAccountGroupId, start, end, orderByComparator
			};
		}

		List<CommerceDiscountCommerceAccountGroupRel> list = null;

		if (retrieveFromCache) {
			list =
				(List<CommerceDiscountCommerceAccountGroupRel>)
					finderCache.getResult(finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceDiscountCommerceAccountGroupRel
						commerceDiscountCommerceAccountGroupRel : list) {

					if ((commerceAccountGroupId !=
							commerceDiscountCommerceAccountGroupRel.
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
				_SQL_SELECT_COMMERCEDISCOUNTCOMMERCEACCOUNTGROUPREL_WHERE);

			query.append(
				_FINDER_COLUMN_COMMERCEACCOUNTGROUPID_COMMERCEACCOUNTGROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(
					CommerceDiscountCommerceAccountGroupRelModelImpl.
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
						(List<CommerceDiscountCommerceAccountGroupRel>)
							QueryUtil.list(q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list =
						(List<CommerceDiscountCommerceAccountGroupRel>)
							QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first commerce discount commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount commerce account group rel
	 * @throws NoSuchDiscountCommerceAccountGroupRelException if a matching commerce discount commerce account group rel could not be found
	 */
	@Override
	public CommerceDiscountCommerceAccountGroupRel
			findByCommerceAccountGroupId_First(
				long commerceAccountGroupId,
				OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
					orderByComparator)
		throws NoSuchDiscountCommerceAccountGroupRelException {

		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel =
				fetchByCommerceAccountGroupId_First(
					commerceAccountGroupId, orderByComparator);

		if (commerceDiscountCommerceAccountGroupRel != null) {
			return commerceDiscountCommerceAccountGroupRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceAccountGroupId=");
		msg.append(commerceAccountGroupId);

		msg.append("}");

		throw new NoSuchDiscountCommerceAccountGroupRelException(
			msg.toString());
	}

	/**
	 * Returns the first commerce discount commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount commerce account group rel, or <code>null</code> if a matching commerce discount commerce account group rel could not be found
	 */
	@Override
	public CommerceDiscountCommerceAccountGroupRel
		fetchByCommerceAccountGroupId_First(
			long commerceAccountGroupId,
			OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
				orderByComparator) {

		List<CommerceDiscountCommerceAccountGroupRel> list =
			findByCommerceAccountGroupId(
				commerceAccountGroupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce discount commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount commerce account group rel
	 * @throws NoSuchDiscountCommerceAccountGroupRelException if a matching commerce discount commerce account group rel could not be found
	 */
	@Override
	public CommerceDiscountCommerceAccountGroupRel
			findByCommerceAccountGroupId_Last(
				long commerceAccountGroupId,
				OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
					orderByComparator)
		throws NoSuchDiscountCommerceAccountGroupRelException {

		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel =
				fetchByCommerceAccountGroupId_Last(
					commerceAccountGroupId, orderByComparator);

		if (commerceDiscountCommerceAccountGroupRel != null) {
			return commerceDiscountCommerceAccountGroupRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceAccountGroupId=");
		msg.append(commerceAccountGroupId);

		msg.append("}");

		throw new NoSuchDiscountCommerceAccountGroupRelException(
			msg.toString());
	}

	/**
	 * Returns the last commerce discount commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount commerce account group rel, or <code>null</code> if a matching commerce discount commerce account group rel could not be found
	 */
	@Override
	public CommerceDiscountCommerceAccountGroupRel
		fetchByCommerceAccountGroupId_Last(
			long commerceAccountGroupId,
			OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
				orderByComparator) {

		int count = countByCommerceAccountGroupId(commerceAccountGroupId);

		if (count == 0) {
			return null;
		}

		List<CommerceDiscountCommerceAccountGroupRel> list =
			findByCommerceAccountGroupId(
				commerceAccountGroupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce discount commerce account group rels before and after the current commerce discount commerce account group rel in the ordered set where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceDiscountCommerceAccountGroupRelId the primary key of the current commerce discount commerce account group rel
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce discount commerce account group rel
	 * @throws NoSuchDiscountCommerceAccountGroupRelException if a commerce discount commerce account group rel with the primary key could not be found
	 */
	@Override
	public CommerceDiscountCommerceAccountGroupRel[]
			findByCommerceAccountGroupId_PrevAndNext(
				long commerceDiscountCommerceAccountGroupRelId,
				long commerceAccountGroupId,
				OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
					orderByComparator)
		throws NoSuchDiscountCommerceAccountGroupRelException {

		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel = findByPrimaryKey(
				commerceDiscountCommerceAccountGroupRelId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscountCommerceAccountGroupRel[] array =
				new CommerceDiscountCommerceAccountGroupRelImpl[3];

			array[0] = getByCommerceAccountGroupId_PrevAndNext(
				session, commerceDiscountCommerceAccountGroupRel,
				commerceAccountGroupId, orderByComparator, true);

			array[1] = commerceDiscountCommerceAccountGroupRel;

			array[2] = getByCommerceAccountGroupId_PrevAndNext(
				session, commerceDiscountCommerceAccountGroupRel,
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

	protected CommerceDiscountCommerceAccountGroupRel
		getByCommerceAccountGroupId_PrevAndNext(
			Session session,
			CommerceDiscountCommerceAccountGroupRel
				commerceDiscountCommerceAccountGroupRel,
			long commerceAccountGroupId,
			OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
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

		query.append(_SQL_SELECT_COMMERCEDISCOUNTCOMMERCEACCOUNTGROUPREL_WHERE);

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
				CommerceDiscountCommerceAccountGroupRelModelImpl.ORDER_BY_JPQL);
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
						commerceDiscountCommerceAccountGroupRel)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceDiscountCommerceAccountGroupRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce discount commerce account group rels where commerceAccountGroupId = &#63; from the database.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 */
	@Override
	public void removeByCommerceAccountGroupId(long commerceAccountGroupId) {
		for (CommerceDiscountCommerceAccountGroupRel
				commerceDiscountCommerceAccountGroupRel :
					findByCommerceAccountGroupId(
						commerceAccountGroupId, QueryUtil.ALL_POS,
						QueryUtil.ALL_POS, null)) {

			remove(commerceDiscountCommerceAccountGroupRel);
		}
	}

	/**
	 * Returns the number of commerce discount commerce account group rels where commerceAccountGroupId = &#63;.
	 *
	 * @param commerceAccountGroupId the commerce account group ID
	 * @return the number of matching commerce discount commerce account group rels
	 */
	@Override
	public int countByCommerceAccountGroupId(long commerceAccountGroupId) {
		FinderPath finderPath = _finderPathCountByCommerceAccountGroupId;

		Object[] finderArgs = new Object[] {commerceAccountGroupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(
				_SQL_COUNT_COMMERCEDISCOUNTCOMMERCEACCOUNTGROUPREL_WHERE);

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
			"commerceDiscountCommerceAccountGroupRel.commerceAccountGroupId = ?";

	private FinderPath _finderPathFetchByC_C;
	private FinderPath _finderPathCountByC_C;

	/**
	 * Returns the commerce discount commerce account group rel where commerceDiscountId = &#63; and commerceAccountGroupId = &#63; or throws a <code>NoSuchDiscountCommerceAccountGroupRelException</code> if it could not be found.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param commerceAccountGroupId the commerce account group ID
	 * @return the matching commerce discount commerce account group rel
	 * @throws NoSuchDiscountCommerceAccountGroupRelException if a matching commerce discount commerce account group rel could not be found
	 */
	@Override
	public CommerceDiscountCommerceAccountGroupRel findByC_C(
			long commerceDiscountId, long commerceAccountGroupId)
		throws NoSuchDiscountCommerceAccountGroupRelException {

		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel = fetchByC_C(
				commerceDiscountId, commerceAccountGroupId);

		if (commerceDiscountCommerceAccountGroupRel == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("commerceDiscountId=");
			msg.append(commerceDiscountId);

			msg.append(", commerceAccountGroupId=");
			msg.append(commerceAccountGroupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDiscountCommerceAccountGroupRelException(
				msg.toString());
		}

		return commerceDiscountCommerceAccountGroupRel;
	}

	/**
	 * Returns the commerce discount commerce account group rel where commerceDiscountId = &#63; and commerceAccountGroupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param commerceAccountGroupId the commerce account group ID
	 * @return the matching commerce discount commerce account group rel, or <code>null</code> if a matching commerce discount commerce account group rel could not be found
	 */
	@Override
	public CommerceDiscountCommerceAccountGroupRel fetchByC_C(
		long commerceDiscountId, long commerceAccountGroupId) {

		return fetchByC_C(commerceDiscountId, commerceAccountGroupId, true);
	}

	/**
	 * Returns the commerce discount commerce account group rel where commerceDiscountId = &#63; and commerceAccountGroupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param commerceAccountGroupId the commerce account group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching commerce discount commerce account group rel, or <code>null</code> if a matching commerce discount commerce account group rel could not be found
	 */
	@Override
	public CommerceDiscountCommerceAccountGroupRel fetchByC_C(
		long commerceDiscountId, long commerceAccountGroupId,
		boolean retrieveFromCache) {

		Object[] finderArgs = new Object[] {
			commerceDiscountId, commerceAccountGroupId
		};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_C, finderArgs, this);
		}

		if (result instanceof CommerceDiscountCommerceAccountGroupRel) {
			CommerceDiscountCommerceAccountGroupRel
				commerceDiscountCommerceAccountGroupRel =
					(CommerceDiscountCommerceAccountGroupRel)result;

			if ((commerceDiscountId !=
					commerceDiscountCommerceAccountGroupRel.
						getCommerceDiscountId()) ||
				(commerceAccountGroupId !=
					commerceDiscountCommerceAccountGroupRel.
						getCommerceAccountGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(
				_SQL_SELECT_COMMERCEDISCOUNTCOMMERCEACCOUNTGROUPREL_WHERE);

			query.append(_FINDER_COLUMN_C_C_COMMERCEDISCOUNTID_2);

			query.append(_FINDER_COLUMN_C_C_COMMERCEACCOUNTGROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceDiscountId);

				qPos.add(commerceAccountGroupId);

				List<CommerceDiscountCommerceAccountGroupRel> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByC_C, finderArgs, list);
				}
				else {
					CommerceDiscountCommerceAccountGroupRel
						commerceDiscountCommerceAccountGroupRel = list.get(0);

					result = commerceDiscountCommerceAccountGroupRel;

					cacheResult(commerceDiscountCommerceAccountGroupRel);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByC_C, finderArgs);

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
			return (CommerceDiscountCommerceAccountGroupRel)result;
		}
	}

	/**
	 * Removes the commerce discount commerce account group rel where commerceDiscountId = &#63; and commerceAccountGroupId = &#63; from the database.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param commerceAccountGroupId the commerce account group ID
	 * @return the commerce discount commerce account group rel that was removed
	 */
	@Override
	public CommerceDiscountCommerceAccountGroupRel removeByC_C(
			long commerceDiscountId, long commerceAccountGroupId)
		throws NoSuchDiscountCommerceAccountGroupRelException {

		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel = findByC_C(
				commerceDiscountId, commerceAccountGroupId);

		return remove(commerceDiscountCommerceAccountGroupRel);
	}

	/**
	 * Returns the number of commerce discount commerce account group rels where commerceDiscountId = &#63; and commerceAccountGroupId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param commerceAccountGroupId the commerce account group ID
	 * @return the number of matching commerce discount commerce account group rels
	 */
	@Override
	public int countByC_C(
		long commerceDiscountId, long commerceAccountGroupId) {

		FinderPath finderPath = _finderPathCountByC_C;

		Object[] finderArgs = new Object[] {
			commerceDiscountId, commerceAccountGroupId
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(
				_SQL_COUNT_COMMERCEDISCOUNTCOMMERCEACCOUNTGROUPREL_WHERE);

			query.append(_FINDER_COLUMN_C_C_COMMERCEDISCOUNTID_2);

			query.append(_FINDER_COLUMN_C_C_COMMERCEACCOUNTGROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceDiscountId);

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

	private static final String _FINDER_COLUMN_C_C_COMMERCEDISCOUNTID_2 =
		"commerceDiscountCommerceAccountGroupRel.commerceDiscountId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_COMMERCEACCOUNTGROUPID_2 =
		"commerceDiscountCommerceAccountGroupRel.commerceAccountGroupId = ?";

	public CommerceDiscountCommerceAccountGroupRelPersistenceImpl() {
		setModelClass(CommerceDiscountCommerceAccountGroupRel.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put(
			"commerceDiscountCommerceAccountGroupRelId",
			"CDiscountCAccountGroupRelId");

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
	 * Caches the commerce discount commerce account group rel in the entity cache if it is enabled.
	 *
	 * @param commerceDiscountCommerceAccountGroupRel the commerce discount commerce account group rel
	 */
	@Override
	public void cacheResult(
		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel) {

		entityCache.putResult(
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceDiscountCommerceAccountGroupRelImpl.class,
			commerceDiscountCommerceAccountGroupRel.getPrimaryKey(),
			commerceDiscountCommerceAccountGroupRel);

		finderCache.putResult(
			_finderPathFetchByC_C,
			new Object[] {
				commerceDiscountCommerceAccountGroupRel.getCommerceDiscountId(),
				commerceDiscountCommerceAccountGroupRel.
					getCommerceAccountGroupId()
			},
			commerceDiscountCommerceAccountGroupRel);

		commerceDiscountCommerceAccountGroupRel.resetOriginalValues();
	}

	/**
	 * Caches the commerce discount commerce account group rels in the entity cache if it is enabled.
	 *
	 * @param commerceDiscountCommerceAccountGroupRels the commerce discount commerce account group rels
	 */
	@Override
	public void cacheResult(
		List<CommerceDiscountCommerceAccountGroupRel>
			commerceDiscountCommerceAccountGroupRels) {

		for (CommerceDiscountCommerceAccountGroupRel
				commerceDiscountCommerceAccountGroupRel :
					commerceDiscountCommerceAccountGroupRels) {

			if (entityCache.getResult(
					CommerceDiscountCommerceAccountGroupRelModelImpl.
						ENTITY_CACHE_ENABLED,
					CommerceDiscountCommerceAccountGroupRelImpl.class,
					commerceDiscountCommerceAccountGroupRel.getPrimaryKey()) ==
						null) {

				cacheResult(commerceDiscountCommerceAccountGroupRel);
			}
			else {
				commerceDiscountCommerceAccountGroupRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce discount commerce account group rels.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(
			CommerceDiscountCommerceAccountGroupRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce discount commerce account group rel.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel) {

		entityCache.removeResult(
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceDiscountCommerceAccountGroupRelImpl.class,
			commerceDiscountCommerceAccountGroupRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(CommerceDiscountCommerceAccountGroupRelModelImpl)
				commerceDiscountCommerceAccountGroupRel,
			true);
	}

	@Override
	public void clearCache(
		List<CommerceDiscountCommerceAccountGroupRel>
			commerceDiscountCommerceAccountGroupRels) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceDiscountCommerceAccountGroupRel
				commerceDiscountCommerceAccountGroupRel :
					commerceDiscountCommerceAccountGroupRels) {

			entityCache.removeResult(
				CommerceDiscountCommerceAccountGroupRelModelImpl.
					ENTITY_CACHE_ENABLED,
				CommerceDiscountCommerceAccountGroupRelImpl.class,
				commerceDiscountCommerceAccountGroupRel.getPrimaryKey());

			clearUniqueFindersCache(
				(CommerceDiscountCommerceAccountGroupRelModelImpl)
					commerceDiscountCommerceAccountGroupRel,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		CommerceDiscountCommerceAccountGroupRelModelImpl
			commerceDiscountCommerceAccountGroupRelModelImpl) {

		Object[] args = new Object[] {
			commerceDiscountCommerceAccountGroupRelModelImpl.
				getCommerceDiscountId(),
			commerceDiscountCommerceAccountGroupRelModelImpl.
				getCommerceAccountGroupId()
		};

		finderCache.putResult(
			_finderPathCountByC_C, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_C, args,
			commerceDiscountCommerceAccountGroupRelModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CommerceDiscountCommerceAccountGroupRelModelImpl
			commerceDiscountCommerceAccountGroupRelModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				commerceDiscountCommerceAccountGroupRelModelImpl.
					getCommerceDiscountId(),
				commerceDiscountCommerceAccountGroupRelModelImpl.
					getCommerceAccountGroupId()
			};

			finderCache.removeResult(_finderPathCountByC_C, args);
			finderCache.removeResult(_finderPathFetchByC_C, args);
		}

		if ((commerceDiscountCommerceAccountGroupRelModelImpl.
				getColumnBitmask() &
			 _finderPathFetchByC_C.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				commerceDiscountCommerceAccountGroupRelModelImpl.
					getOriginalCommerceDiscountId(),
				commerceDiscountCommerceAccountGroupRelModelImpl.
					getOriginalCommerceAccountGroupId()
			};

			finderCache.removeResult(_finderPathCountByC_C, args);
			finderCache.removeResult(_finderPathFetchByC_C, args);
		}
	}

	/**
	 * Creates a new commerce discount commerce account group rel with the primary key. Does not add the commerce discount commerce account group rel to the database.
	 *
	 * @param commerceDiscountCommerceAccountGroupRelId the primary key for the new commerce discount commerce account group rel
	 * @return the new commerce discount commerce account group rel
	 */
	@Override
	public CommerceDiscountCommerceAccountGroupRel create(
		long commerceDiscountCommerceAccountGroupRelId) {

		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel =
				new CommerceDiscountCommerceAccountGroupRelImpl();

		commerceDiscountCommerceAccountGroupRel.setNew(true);
		commerceDiscountCommerceAccountGroupRel.setPrimaryKey(
			commerceDiscountCommerceAccountGroupRelId);

		commerceDiscountCommerceAccountGroupRel.setCompanyId(
			CompanyThreadLocal.getCompanyId());

		return commerceDiscountCommerceAccountGroupRel;
	}

	/**
	 * Removes the commerce discount commerce account group rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountCommerceAccountGroupRelId the primary key of the commerce discount commerce account group rel
	 * @return the commerce discount commerce account group rel that was removed
	 * @throws NoSuchDiscountCommerceAccountGroupRelException if a commerce discount commerce account group rel with the primary key could not be found
	 */
	@Override
	public CommerceDiscountCommerceAccountGroupRel remove(
			long commerceDiscountCommerceAccountGroupRelId)
		throws NoSuchDiscountCommerceAccountGroupRelException {

		return remove((Serializable)commerceDiscountCommerceAccountGroupRelId);
	}

	/**
	 * Removes the commerce discount commerce account group rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce discount commerce account group rel
	 * @return the commerce discount commerce account group rel that was removed
	 * @throws NoSuchDiscountCommerceAccountGroupRelException if a commerce discount commerce account group rel with the primary key could not be found
	 */
	@Override
	public CommerceDiscountCommerceAccountGroupRel remove(
			Serializable primaryKey)
		throws NoSuchDiscountCommerceAccountGroupRelException {

		Session session = null;

		try {
			session = openSession();

			CommerceDiscountCommerceAccountGroupRel
				commerceDiscountCommerceAccountGroupRel =
					(CommerceDiscountCommerceAccountGroupRel)session.get(
						CommerceDiscountCommerceAccountGroupRelImpl.class,
						primaryKey);

			if (commerceDiscountCommerceAccountGroupRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDiscountCommerceAccountGroupRelException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commerceDiscountCommerceAccountGroupRel);
		}
		catch (NoSuchDiscountCommerceAccountGroupRelException nsee) {
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
	protected CommerceDiscountCommerceAccountGroupRel removeImpl(
		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceDiscountCommerceAccountGroupRel)) {
				commerceDiscountCommerceAccountGroupRel =
					(CommerceDiscountCommerceAccountGroupRel)session.get(
						CommerceDiscountCommerceAccountGroupRelImpl.class,
						commerceDiscountCommerceAccountGroupRel.
							getPrimaryKeyObj());
			}

			if (commerceDiscountCommerceAccountGroupRel != null) {
				session.delete(commerceDiscountCommerceAccountGroupRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceDiscountCommerceAccountGroupRel != null) {
			clearCache(commerceDiscountCommerceAccountGroupRel);
		}

		return commerceDiscountCommerceAccountGroupRel;
	}

	@Override
	public CommerceDiscountCommerceAccountGroupRel updateImpl(
		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel) {

		boolean isNew = commerceDiscountCommerceAccountGroupRel.isNew();

		if (!(commerceDiscountCommerceAccountGroupRel instanceof
				CommerceDiscountCommerceAccountGroupRelModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(
					commerceDiscountCommerceAccountGroupRel.getClass())) {

				invocationHandler = ProxyUtil.getInvocationHandler(
					commerceDiscountCommerceAccountGroupRel);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceDiscountCommerceAccountGroupRel proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceDiscountCommerceAccountGroupRel implementation " +
					commerceDiscountCommerceAccountGroupRel.getClass());
		}

		CommerceDiscountCommerceAccountGroupRelModelImpl
			commerceDiscountCommerceAccountGroupRelModelImpl =
				(CommerceDiscountCommerceAccountGroupRelModelImpl)
					commerceDiscountCommerceAccountGroupRel;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew &&
			(commerceDiscountCommerceAccountGroupRel.getCreateDate() == null)) {

			if (serviceContext == null) {
				commerceDiscountCommerceAccountGroupRel.setCreateDate(now);
			}
			else {
				commerceDiscountCommerceAccountGroupRel.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!commerceDiscountCommerceAccountGroupRelModelImpl.
				hasSetModifiedDate()) {

			if (serviceContext == null) {
				commerceDiscountCommerceAccountGroupRel.setModifiedDate(now);
			}
			else {
				commerceDiscountCommerceAccountGroupRel.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceDiscountCommerceAccountGroupRel.isNew()) {
				session.save(commerceDiscountCommerceAccountGroupRel);

				commerceDiscountCommerceAccountGroupRel.setNew(false);
			}
			else {
				commerceDiscountCommerceAccountGroupRel =
					(CommerceDiscountCommerceAccountGroupRel)session.merge(
						commerceDiscountCommerceAccountGroupRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceDiscountCommerceAccountGroupRelModelImpl.
				COLUMN_BITMASK_ENABLED) {

			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				commerceDiscountCommerceAccountGroupRelModelImpl.
					getCommerceDiscountId()
			};

			finderCache.removeResult(
				_finderPathCountByCommerceDiscountId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCommerceDiscountId, args);

			args = new Object[] {
				commerceDiscountCommerceAccountGroupRelModelImpl.
					getCommerceAccountGroupId()
			};

			finderCache.removeResult(
				_finderPathCountByCommerceAccountGroupId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCommerceAccountGroupId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((commerceDiscountCommerceAccountGroupRelModelImpl.
					getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCommerceDiscountId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceDiscountCommerceAccountGroupRelModelImpl.
						getOriginalCommerceDiscountId()
				};

				finderCache.removeResult(
					_finderPathCountByCommerceDiscountId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommerceDiscountId, args);

				args = new Object[] {
					commerceDiscountCommerceAccountGroupRelModelImpl.
						getCommerceDiscountId()
				};

				finderCache.removeResult(
					_finderPathCountByCommerceDiscountId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommerceDiscountId, args);
			}

			if ((commerceDiscountCommerceAccountGroupRelModelImpl.
					getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCommerceAccountGroupId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceDiscountCommerceAccountGroupRelModelImpl.
						getOriginalCommerceAccountGroupId()
				};

				finderCache.removeResult(
					_finderPathCountByCommerceAccountGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommerceAccountGroupId,
					args);

				args = new Object[] {
					commerceDiscountCommerceAccountGroupRelModelImpl.
						getCommerceAccountGroupId()
				};

				finderCache.removeResult(
					_finderPathCountByCommerceAccountGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommerceAccountGroupId,
					args);
			}
		}

		entityCache.putResult(
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceDiscountCommerceAccountGroupRelImpl.class,
			commerceDiscountCommerceAccountGroupRel.getPrimaryKey(),
			commerceDiscountCommerceAccountGroupRel, false);

		clearUniqueFindersCache(
			commerceDiscountCommerceAccountGroupRelModelImpl, false);
		cacheUniqueFindersCache(
			commerceDiscountCommerceAccountGroupRelModelImpl);

		commerceDiscountCommerceAccountGroupRel.resetOriginalValues();

		return commerceDiscountCommerceAccountGroupRel;
	}

	/**
	 * Returns the commerce discount commerce account group rel with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce discount commerce account group rel
	 * @return the commerce discount commerce account group rel
	 * @throws NoSuchDiscountCommerceAccountGroupRelException if a commerce discount commerce account group rel with the primary key could not be found
	 */
	@Override
	public CommerceDiscountCommerceAccountGroupRel findByPrimaryKey(
			Serializable primaryKey)
		throws NoSuchDiscountCommerceAccountGroupRelException {

		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel = fetchByPrimaryKey(
				primaryKey);

		if (commerceDiscountCommerceAccountGroupRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDiscountCommerceAccountGroupRelException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commerceDiscountCommerceAccountGroupRel;
	}

	/**
	 * Returns the commerce discount commerce account group rel with the primary key or throws a <code>NoSuchDiscountCommerceAccountGroupRelException</code> if it could not be found.
	 *
	 * @param commerceDiscountCommerceAccountGroupRelId the primary key of the commerce discount commerce account group rel
	 * @return the commerce discount commerce account group rel
	 * @throws NoSuchDiscountCommerceAccountGroupRelException if a commerce discount commerce account group rel with the primary key could not be found
	 */
	@Override
	public CommerceDiscountCommerceAccountGroupRel findByPrimaryKey(
			long commerceDiscountCommerceAccountGroupRelId)
		throws NoSuchDiscountCommerceAccountGroupRelException {

		return findByPrimaryKey(
			(Serializable)commerceDiscountCommerceAccountGroupRelId);
	}

	/**
	 * Returns the commerce discount commerce account group rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce discount commerce account group rel
	 * @return the commerce discount commerce account group rel, or <code>null</code> if a commerce discount commerce account group rel with the primary key could not be found
	 */
	@Override
	public CommerceDiscountCommerceAccountGroupRel fetchByPrimaryKey(
		Serializable primaryKey) {

		Serializable serializable = entityCache.getResult(
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceDiscountCommerceAccountGroupRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel =
				(CommerceDiscountCommerceAccountGroupRel)serializable;

		if (commerceDiscountCommerceAccountGroupRel == null) {
			Session session = null;

			try {
				session = openSession();

				commerceDiscountCommerceAccountGroupRel =
					(CommerceDiscountCommerceAccountGroupRel)session.get(
						CommerceDiscountCommerceAccountGroupRelImpl.class,
						primaryKey);

				if (commerceDiscountCommerceAccountGroupRel != null) {
					cacheResult(commerceDiscountCommerceAccountGroupRel);
				}
				else {
					entityCache.putResult(
						CommerceDiscountCommerceAccountGroupRelModelImpl.
							ENTITY_CACHE_ENABLED,
						CommerceDiscountCommerceAccountGroupRelImpl.class,
						primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CommerceDiscountCommerceAccountGroupRelModelImpl.
						ENTITY_CACHE_ENABLED,
					CommerceDiscountCommerceAccountGroupRelImpl.class,
					primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceDiscountCommerceAccountGroupRel;
	}

	/**
	 * Returns the commerce discount commerce account group rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceDiscountCommerceAccountGroupRelId the primary key of the commerce discount commerce account group rel
	 * @return the commerce discount commerce account group rel, or <code>null</code> if a commerce discount commerce account group rel with the primary key could not be found
	 */
	@Override
	public CommerceDiscountCommerceAccountGroupRel fetchByPrimaryKey(
		long commerceDiscountCommerceAccountGroupRelId) {

		return fetchByPrimaryKey(
			(Serializable)commerceDiscountCommerceAccountGroupRelId);
	}

	@Override
	public Map<Serializable, CommerceDiscountCommerceAccountGroupRel>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceDiscountCommerceAccountGroupRel> map =
			new HashMap
				<Serializable, CommerceDiscountCommerceAccountGroupRel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceDiscountCommerceAccountGroupRel
				commerceDiscountCommerceAccountGroupRel = fetchByPrimaryKey(
					primaryKey);

			if (commerceDiscountCommerceAccountGroupRel != null) {
				map.put(primaryKey, commerceDiscountCommerceAccountGroupRel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CommerceDiscountCommerceAccountGroupRelModelImpl.
					ENTITY_CACHE_ENABLED,
				CommerceDiscountCommerceAccountGroupRelImpl.class, primaryKey);

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
						(CommerceDiscountCommerceAccountGroupRel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(
			_SQL_SELECT_COMMERCEDISCOUNTCOMMERCEACCOUNTGROUPREL_WHERE_PKS_IN);

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

			for (CommerceDiscountCommerceAccountGroupRel
					commerceDiscountCommerceAccountGroupRel :
						(List<CommerceDiscountCommerceAccountGroupRel>)
							q.list()) {

				map.put(
					commerceDiscountCommerceAccountGroupRel.getPrimaryKeyObj(),
					commerceDiscountCommerceAccountGroupRel);

				cacheResult(commerceDiscountCommerceAccountGroupRel);

				uncachedPrimaryKeys.remove(
					commerceDiscountCommerceAccountGroupRel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CommerceDiscountCommerceAccountGroupRelModelImpl.
						ENTITY_CACHE_ENABLED,
					CommerceDiscountCommerceAccountGroupRelImpl.class,
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
	 * Returns all the commerce discount commerce account group rels.
	 *
	 * @return the commerce discount commerce account group rels
	 */
	@Override
	public List<CommerceDiscountCommerceAccountGroupRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discount commerce account group rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountCommerceAccountGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce discount commerce account group rels
	 * @param end the upper bound of the range of commerce discount commerce account group rels (not inclusive)
	 * @return the range of commerce discount commerce account group rels
	 */
	@Override
	public List<CommerceDiscountCommerceAccountGroupRel> findAll(
		int start, int end) {

		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discount commerce account group rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountCommerceAccountGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce discount commerce account group rels
	 * @param end the upper bound of the range of commerce discount commerce account group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce discount commerce account group rels
	 */
	@Override
	public List<CommerceDiscountCommerceAccountGroupRel> findAll(
		int start, int end,
		OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
			orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce discount commerce account group rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountCommerceAccountGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce discount commerce account group rels
	 * @param end the upper bound of the range of commerce discount commerce account group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce discount commerce account group rels
	 */
	@Override
	public List<CommerceDiscountCommerceAccountGroupRel> findAll(
		int start, int end,
		OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
			orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindAll;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<CommerceDiscountCommerceAccountGroupRel> list = null;

		if (retrieveFromCache) {
			list =
				(List<CommerceDiscountCommerceAccountGroupRel>)
					finderCache.getResult(finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(
					_SQL_SELECT_COMMERCEDISCOUNTCOMMERCEACCOUNTGROUPREL);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEDISCOUNTCOMMERCEACCOUNTGROUPREL;

				if (pagination) {
					sql = sql.concat(
						CommerceDiscountCommerceAccountGroupRelModelImpl.
							ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list =
						(List<CommerceDiscountCommerceAccountGroupRel>)
							QueryUtil.list(q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list =
						(List<CommerceDiscountCommerceAccountGroupRel>)
							QueryUtil.list(q, getDialect(), start, end);
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
	 * Removes all the commerce discount commerce account group rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceDiscountCommerceAccountGroupRel
				commerceDiscountCommerceAccountGroupRel : findAll()) {

			remove(commerceDiscountCommerceAccountGroupRel);
		}
	}

	/**
	 * Returns the number of commerce discount commerce account group rels.
	 *
	 * @return the number of commerce discount commerce account group rels
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
					_SQL_COUNT_COMMERCEDISCOUNTCOMMERCEACCOUNTGROUPREL);

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
		return CommerceDiscountCommerceAccountGroupRelModelImpl.
			TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce discount commerce account group rel persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				FINDER_CACHE_ENABLED,
			CommerceDiscountCommerceAccountGroupRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				FINDER_CACHE_ENABLED,
			CommerceDiscountCommerceAccountGroupRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByCommerceDiscountId = new FinderPath(
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				FINDER_CACHE_ENABLED,
			CommerceDiscountCommerceAccountGroupRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCommerceDiscountId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCommerceDiscountId = new FinderPath(
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				FINDER_CACHE_ENABLED,
			CommerceDiscountCommerceAccountGroupRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCommerceDiscountId", new String[] {Long.class.getName()},
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				COMMERCEDISCOUNTID_COLUMN_BITMASK |
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByCommerceDiscountId = new FinderPath(
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceDiscountId", new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByCommerceAccountGroupId = new FinderPath(
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				FINDER_CACHE_ENABLED,
			CommerceDiscountCommerceAccountGroupRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCommerceAccountGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCommerceAccountGroupId =
			new FinderPath(
				CommerceDiscountCommerceAccountGroupRelModelImpl.
					ENTITY_CACHE_ENABLED,
				CommerceDiscountCommerceAccountGroupRelModelImpl.
					FINDER_CACHE_ENABLED,
				CommerceDiscountCommerceAccountGroupRelImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByCommerceAccountGroupId",
				new String[] {Long.class.getName()},
				CommerceDiscountCommerceAccountGroupRelModelImpl.
					COMMERCEACCOUNTGROUPID_COLUMN_BITMASK |
				CommerceDiscountCommerceAccountGroupRelModelImpl.
					CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByCommerceAccountGroupId = new FinderPath(
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceAccountGroupId",
			new String[] {Long.class.getName()});

		_finderPathFetchByC_C = new FinderPath(
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				FINDER_CACHE_ENABLED,
			CommerceDiscountCommerceAccountGroupRelImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByC_C",
			new String[] {Long.class.getName(), Long.class.getName()},
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				COMMERCEDISCOUNTID_COLUMN_BITMASK |
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				COMMERCEACCOUNTGROUPID_COLUMN_BITMASK);

		_finderPathCountByC_C = new FinderPath(
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				ENTITY_CACHE_ENABLED,
			CommerceDiscountCommerceAccountGroupRelModelImpl.
				FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] {Long.class.getName(), Long.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(
			CommerceDiscountCommerceAccountGroupRelImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String
		_SQL_SELECT_COMMERCEDISCOUNTCOMMERCEACCOUNTGROUPREL =
			"SELECT commerceDiscountCommerceAccountGroupRel FROM CommerceDiscountCommerceAccountGroupRel commerceDiscountCommerceAccountGroupRel";

	private static final String
		_SQL_SELECT_COMMERCEDISCOUNTCOMMERCEACCOUNTGROUPREL_WHERE_PKS_IN =
			"SELECT commerceDiscountCommerceAccountGroupRel FROM CommerceDiscountCommerceAccountGroupRel commerceDiscountCommerceAccountGroupRel WHERE CDiscountCAccountGroupRelId IN (";

	private static final String
		_SQL_SELECT_COMMERCEDISCOUNTCOMMERCEACCOUNTGROUPREL_WHERE =
			"SELECT commerceDiscountCommerceAccountGroupRel FROM CommerceDiscountCommerceAccountGroupRel commerceDiscountCommerceAccountGroupRel WHERE ";

	private static final String
		_SQL_COUNT_COMMERCEDISCOUNTCOMMERCEACCOUNTGROUPREL =
			"SELECT COUNT(commerceDiscountCommerceAccountGroupRel) FROM CommerceDiscountCommerceAccountGroupRel commerceDiscountCommerceAccountGroupRel";

	private static final String
		_SQL_COUNT_COMMERCEDISCOUNTCOMMERCEACCOUNTGROUPREL_WHERE =
			"SELECT COUNT(commerceDiscountCommerceAccountGroupRel) FROM CommerceDiscountCommerceAccountGroupRel commerceDiscountCommerceAccountGroupRel WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"commerceDiscountCommerceAccountGroupRel.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommerceDiscountCommerceAccountGroupRel exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommerceDiscountCommerceAccountGroupRel exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceDiscountCommerceAccountGroupRelPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"commerceDiscountCommerceAccountGroupRelId"});

}