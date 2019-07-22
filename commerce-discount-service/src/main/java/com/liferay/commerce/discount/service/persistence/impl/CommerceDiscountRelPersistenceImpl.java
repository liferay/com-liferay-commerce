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

import com.liferay.commerce.discount.exception.NoSuchDiscountRelException;
import com.liferay.commerce.discount.model.CommerceDiscountRel;
import com.liferay.commerce.discount.model.impl.CommerceDiscountRelImpl;
import com.liferay.commerce.discount.model.impl.CommerceDiscountRelModelImpl;
import com.liferay.commerce.discount.service.persistence.CommerceDiscountRelPersistence;
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
 * The persistence implementation for the commerce discount rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @generated
 */
@ProviderType
public class CommerceDiscountRelPersistenceImpl
	extends BasePersistenceImpl<CommerceDiscountRel>
	implements CommerceDiscountRelPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommerceDiscountRelUtil</code> to access the commerce discount rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommerceDiscountRelImpl.class.getName();

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
	 * Returns all the commerce discount rels where commerceDiscountId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @return the matching commerce discount rels
	 */
	@Override
	public List<CommerceDiscountRel> findByCommerceDiscountId(
		long commerceDiscountId) {

		return findByCommerceDiscountId(
			commerceDiscountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discount rels where commerceDiscountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param start the lower bound of the range of commerce discount rels
	 * @param end the upper bound of the range of commerce discount rels (not inclusive)
	 * @return the range of matching commerce discount rels
	 */
	@Override
	public List<CommerceDiscountRel> findByCommerceDiscountId(
		long commerceDiscountId, int start, int end) {

		return findByCommerceDiscountId(commerceDiscountId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discount rels where commerceDiscountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param start the lower bound of the range of commerce discount rels
	 * @param end the upper bound of the range of commerce discount rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce discount rels
	 */
	@Override
	public List<CommerceDiscountRel> findByCommerceDiscountId(
		long commerceDiscountId, int start, int end,
		OrderByComparator<CommerceDiscountRel> orderByComparator) {

		return findByCommerceDiscountId(
			commerceDiscountId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce discount rels where commerceDiscountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param start the lower bound of the range of commerce discount rels
	 * @param end the upper bound of the range of commerce discount rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce discount rels
	 */
	@Override
	public List<CommerceDiscountRel> findByCommerceDiscountId(
		long commerceDiscountId, int start, int end,
		OrderByComparator<CommerceDiscountRel> orderByComparator,
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

		List<CommerceDiscountRel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceDiscountRel>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceDiscountRel commerceDiscountRel : list) {
					if ((commerceDiscountId !=
							commerceDiscountRel.getCommerceDiscountId())) {

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

			query.append(_SQL_SELECT_COMMERCEDISCOUNTREL_WHERE);

			query.append(
				_FINDER_COLUMN_COMMERCEDISCOUNTID_COMMERCEDISCOUNTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceDiscountRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceDiscountId);

				if (!pagination) {
					list = (List<CommerceDiscountRel>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceDiscountRel>)QueryUtil.list(
						q, getDialect(), start, end);
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
	 * Returns the first commerce discount rel in the ordered set where commerceDiscountId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount rel
	 * @throws NoSuchDiscountRelException if a matching commerce discount rel could not be found
	 */
	@Override
	public CommerceDiscountRel findByCommerceDiscountId_First(
			long commerceDiscountId,
			OrderByComparator<CommerceDiscountRel> orderByComparator)
		throws NoSuchDiscountRelException {

		CommerceDiscountRel commerceDiscountRel =
			fetchByCommerceDiscountId_First(
				commerceDiscountId, orderByComparator);

		if (commerceDiscountRel != null) {
			return commerceDiscountRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceDiscountId=");
		msg.append(commerceDiscountId);

		msg.append("}");

		throw new NoSuchDiscountRelException(msg.toString());
	}

	/**
	 * Returns the first commerce discount rel in the ordered set where commerceDiscountId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount rel, or <code>null</code> if a matching commerce discount rel could not be found
	 */
	@Override
	public CommerceDiscountRel fetchByCommerceDiscountId_First(
		long commerceDiscountId,
		OrderByComparator<CommerceDiscountRel> orderByComparator) {

		List<CommerceDiscountRel> list = findByCommerceDiscountId(
			commerceDiscountId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce discount rel in the ordered set where commerceDiscountId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount rel
	 * @throws NoSuchDiscountRelException if a matching commerce discount rel could not be found
	 */
	@Override
	public CommerceDiscountRel findByCommerceDiscountId_Last(
			long commerceDiscountId,
			OrderByComparator<CommerceDiscountRel> orderByComparator)
		throws NoSuchDiscountRelException {

		CommerceDiscountRel commerceDiscountRel =
			fetchByCommerceDiscountId_Last(
				commerceDiscountId, orderByComparator);

		if (commerceDiscountRel != null) {
			return commerceDiscountRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceDiscountId=");
		msg.append(commerceDiscountId);

		msg.append("}");

		throw new NoSuchDiscountRelException(msg.toString());
	}

	/**
	 * Returns the last commerce discount rel in the ordered set where commerceDiscountId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount rel, or <code>null</code> if a matching commerce discount rel could not be found
	 */
	@Override
	public CommerceDiscountRel fetchByCommerceDiscountId_Last(
		long commerceDiscountId,
		OrderByComparator<CommerceDiscountRel> orderByComparator) {

		int count = countByCommerceDiscountId(commerceDiscountId);

		if (count == 0) {
			return null;
		}

		List<CommerceDiscountRel> list = findByCommerceDiscountId(
			commerceDiscountId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce discount rels before and after the current commerce discount rel in the ordered set where commerceDiscountId = &#63;.
	 *
	 * @param commerceDiscountRelId the primary key of the current commerce discount rel
	 * @param commerceDiscountId the commerce discount ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce discount rel
	 * @throws NoSuchDiscountRelException if a commerce discount rel with the primary key could not be found
	 */
	@Override
	public CommerceDiscountRel[] findByCommerceDiscountId_PrevAndNext(
			long commerceDiscountRelId, long commerceDiscountId,
			OrderByComparator<CommerceDiscountRel> orderByComparator)
		throws NoSuchDiscountRelException {

		CommerceDiscountRel commerceDiscountRel = findByPrimaryKey(
			commerceDiscountRelId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscountRel[] array = new CommerceDiscountRelImpl[3];

			array[0] = getByCommerceDiscountId_PrevAndNext(
				session, commerceDiscountRel, commerceDiscountId,
				orderByComparator, true);

			array[1] = commerceDiscountRel;

			array[2] = getByCommerceDiscountId_PrevAndNext(
				session, commerceDiscountRel, commerceDiscountId,
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

	protected CommerceDiscountRel getByCommerceDiscountId_PrevAndNext(
		Session session, CommerceDiscountRel commerceDiscountRel,
		long commerceDiscountId,
		OrderByComparator<CommerceDiscountRel> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEDISCOUNTREL_WHERE);

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
			query.append(CommerceDiscountRelModelImpl.ORDER_BY_JPQL);
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
						commerceDiscountRel)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceDiscountRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce discount rels where commerceDiscountId = &#63; from the database.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 */
	@Override
	public void removeByCommerceDiscountId(long commerceDiscountId) {
		for (CommerceDiscountRel commerceDiscountRel :
				findByCommerceDiscountId(
					commerceDiscountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceDiscountRel);
		}
	}

	/**
	 * Returns the number of commerce discount rels where commerceDiscountId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @return the number of matching commerce discount rels
	 */
	@Override
	public int countByCommerceDiscountId(long commerceDiscountId) {
		FinderPath finderPath = _finderPathCountByCommerceDiscountId;

		Object[] finderArgs = new Object[] {commerceDiscountId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEDISCOUNTREL_WHERE);

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
			"commerceDiscountRel.commerceDiscountId = ?";

	private FinderPath _finderPathWithPaginationFindByCD_CN;
	private FinderPath _finderPathWithoutPaginationFindByCD_CN;
	private FinderPath _finderPathCountByCD_CN;

	/**
	 * Returns all the commerce discount rels where commerceDiscountId = &#63; and classNameId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param classNameId the class name ID
	 * @return the matching commerce discount rels
	 */
	@Override
	public List<CommerceDiscountRel> findByCD_CN(
		long commerceDiscountId, long classNameId) {

		return findByCD_CN(
			commerceDiscountId, classNameId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discount rels where commerceDiscountId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of commerce discount rels
	 * @param end the upper bound of the range of commerce discount rels (not inclusive)
	 * @return the range of matching commerce discount rels
	 */
	@Override
	public List<CommerceDiscountRel> findByCD_CN(
		long commerceDiscountId, long classNameId, int start, int end) {

		return findByCD_CN(commerceDiscountId, classNameId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discount rels where commerceDiscountId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of commerce discount rels
	 * @param end the upper bound of the range of commerce discount rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce discount rels
	 */
	@Override
	public List<CommerceDiscountRel> findByCD_CN(
		long commerceDiscountId, long classNameId, int start, int end,
		OrderByComparator<CommerceDiscountRel> orderByComparator) {

		return findByCD_CN(
			commerceDiscountId, classNameId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the commerce discount rels where commerceDiscountId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of commerce discount rels
	 * @param end the upper bound of the range of commerce discount rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce discount rels
	 */
	@Override
	public List<CommerceDiscountRel> findByCD_CN(
		long commerceDiscountId, long classNameId, int start, int end,
		OrderByComparator<CommerceDiscountRel> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByCD_CN;
			finderArgs = new Object[] {commerceDiscountId, classNameId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByCD_CN;
			finderArgs = new Object[] {
				commerceDiscountId, classNameId, start, end, orderByComparator
			};
		}

		List<CommerceDiscountRel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceDiscountRel>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceDiscountRel commerceDiscountRel : list) {
					if ((commerceDiscountId !=
							commerceDiscountRel.getCommerceDiscountId()) ||
						(classNameId != commerceDiscountRel.getClassNameId())) {

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

			query.append(_SQL_SELECT_COMMERCEDISCOUNTREL_WHERE);

			query.append(_FINDER_COLUMN_CD_CN_COMMERCEDISCOUNTID_2);

			query.append(_FINDER_COLUMN_CD_CN_CLASSNAMEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceDiscountRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceDiscountId);

				qPos.add(classNameId);

				if (!pagination) {
					list = (List<CommerceDiscountRel>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceDiscountRel>)QueryUtil.list(
						q, getDialect(), start, end);
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
	 * Returns the first commerce discount rel in the ordered set where commerceDiscountId = &#63; and classNameId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount rel
	 * @throws NoSuchDiscountRelException if a matching commerce discount rel could not be found
	 */
	@Override
	public CommerceDiscountRel findByCD_CN_First(
			long commerceDiscountId, long classNameId,
			OrderByComparator<CommerceDiscountRel> orderByComparator)
		throws NoSuchDiscountRelException {

		CommerceDiscountRel commerceDiscountRel = fetchByCD_CN_First(
			commerceDiscountId, classNameId, orderByComparator);

		if (commerceDiscountRel != null) {
			return commerceDiscountRel;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceDiscountId=");
		msg.append(commerceDiscountId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append("}");

		throw new NoSuchDiscountRelException(msg.toString());
	}

	/**
	 * Returns the first commerce discount rel in the ordered set where commerceDiscountId = &#63; and classNameId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount rel, or <code>null</code> if a matching commerce discount rel could not be found
	 */
	@Override
	public CommerceDiscountRel fetchByCD_CN_First(
		long commerceDiscountId, long classNameId,
		OrderByComparator<CommerceDiscountRel> orderByComparator) {

		List<CommerceDiscountRel> list = findByCD_CN(
			commerceDiscountId, classNameId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce discount rel in the ordered set where commerceDiscountId = &#63; and classNameId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount rel
	 * @throws NoSuchDiscountRelException if a matching commerce discount rel could not be found
	 */
	@Override
	public CommerceDiscountRel findByCD_CN_Last(
			long commerceDiscountId, long classNameId,
			OrderByComparator<CommerceDiscountRel> orderByComparator)
		throws NoSuchDiscountRelException {

		CommerceDiscountRel commerceDiscountRel = fetchByCD_CN_Last(
			commerceDiscountId, classNameId, orderByComparator);

		if (commerceDiscountRel != null) {
			return commerceDiscountRel;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceDiscountId=");
		msg.append(commerceDiscountId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append("}");

		throw new NoSuchDiscountRelException(msg.toString());
	}

	/**
	 * Returns the last commerce discount rel in the ordered set where commerceDiscountId = &#63; and classNameId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount rel, or <code>null</code> if a matching commerce discount rel could not be found
	 */
	@Override
	public CommerceDiscountRel fetchByCD_CN_Last(
		long commerceDiscountId, long classNameId,
		OrderByComparator<CommerceDiscountRel> orderByComparator) {

		int count = countByCD_CN(commerceDiscountId, classNameId);

		if (count == 0) {
			return null;
		}

		List<CommerceDiscountRel> list = findByCD_CN(
			commerceDiscountId, classNameId, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce discount rels before and after the current commerce discount rel in the ordered set where commerceDiscountId = &#63; and classNameId = &#63;.
	 *
	 * @param commerceDiscountRelId the primary key of the current commerce discount rel
	 * @param commerceDiscountId the commerce discount ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce discount rel
	 * @throws NoSuchDiscountRelException if a commerce discount rel with the primary key could not be found
	 */
	@Override
	public CommerceDiscountRel[] findByCD_CN_PrevAndNext(
			long commerceDiscountRelId, long commerceDiscountId,
			long classNameId,
			OrderByComparator<CommerceDiscountRel> orderByComparator)
		throws NoSuchDiscountRelException {

		CommerceDiscountRel commerceDiscountRel = findByPrimaryKey(
			commerceDiscountRelId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscountRel[] array = new CommerceDiscountRelImpl[3];

			array[0] = getByCD_CN_PrevAndNext(
				session, commerceDiscountRel, commerceDiscountId, classNameId,
				orderByComparator, true);

			array[1] = commerceDiscountRel;

			array[2] = getByCD_CN_PrevAndNext(
				session, commerceDiscountRel, commerceDiscountId, classNameId,
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

	protected CommerceDiscountRel getByCD_CN_PrevAndNext(
		Session session, CommerceDiscountRel commerceDiscountRel,
		long commerceDiscountId, long classNameId,
		OrderByComparator<CommerceDiscountRel> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEDISCOUNTREL_WHERE);

		query.append(_FINDER_COLUMN_CD_CN_COMMERCEDISCOUNTID_2);

		query.append(_FINDER_COLUMN_CD_CN_CLASSNAMEID_2);

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
			query.append(CommerceDiscountRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceDiscountId);

		qPos.add(classNameId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceDiscountRel)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceDiscountRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce discount rels where commerceDiscountId = &#63; and classNameId = &#63; from the database.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param classNameId the class name ID
	 */
	@Override
	public void removeByCD_CN(long commerceDiscountId, long classNameId) {
		for (CommerceDiscountRel commerceDiscountRel :
				findByCD_CN(
					commerceDiscountId, classNameId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(commerceDiscountRel);
		}
	}

	/**
	 * Returns the number of commerce discount rels where commerceDiscountId = &#63; and classNameId = &#63;.
	 *
	 * @param commerceDiscountId the commerce discount ID
	 * @param classNameId the class name ID
	 * @return the number of matching commerce discount rels
	 */
	@Override
	public int countByCD_CN(long commerceDiscountId, long classNameId) {
		FinderPath finderPath = _finderPathCountByCD_CN;

		Object[] finderArgs = new Object[] {commerceDiscountId, classNameId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEDISCOUNTREL_WHERE);

			query.append(_FINDER_COLUMN_CD_CN_COMMERCEDISCOUNTID_2);

			query.append(_FINDER_COLUMN_CD_CN_CLASSNAMEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceDiscountId);

				qPos.add(classNameId);

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

	private static final String _FINDER_COLUMN_CD_CN_COMMERCEDISCOUNTID_2 =
		"commerceDiscountRel.commerceDiscountId = ? AND ";

	private static final String _FINDER_COLUMN_CD_CN_CLASSNAMEID_2 =
		"commerceDiscountRel.classNameId = ?";

	private FinderPath _finderPathWithPaginationFindByCN_CPK;
	private FinderPath _finderPathWithoutPaginationFindByCN_CPK;
	private FinderPath _finderPathCountByCN_CPK;

	/**
	 * Returns all the commerce discount rels where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching commerce discount rels
	 */
	@Override
	public List<CommerceDiscountRel> findByCN_CPK(
		long classNameId, long classPK) {

		return findByCN_CPK(
			classNameId, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discount rels where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of commerce discount rels
	 * @param end the upper bound of the range of commerce discount rels (not inclusive)
	 * @return the range of matching commerce discount rels
	 */
	@Override
	public List<CommerceDiscountRel> findByCN_CPK(
		long classNameId, long classPK, int start, int end) {

		return findByCN_CPK(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discount rels where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of commerce discount rels
	 * @param end the upper bound of the range of commerce discount rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce discount rels
	 */
	@Override
	public List<CommerceDiscountRel> findByCN_CPK(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<CommerceDiscountRel> orderByComparator) {

		return findByCN_CPK(
			classNameId, classPK, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce discount rels where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of commerce discount rels
	 * @param end the upper bound of the range of commerce discount rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce discount rels
	 */
	@Override
	public List<CommerceDiscountRel> findByCN_CPK(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<CommerceDiscountRel> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByCN_CPK;
			finderArgs = new Object[] {classNameId, classPK};
		}
		else {
			finderPath = _finderPathWithPaginationFindByCN_CPK;
			finderArgs = new Object[] {
				classNameId, classPK, start, end, orderByComparator
			};
		}

		List<CommerceDiscountRel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceDiscountRel>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceDiscountRel commerceDiscountRel : list) {
					if ((classNameId != commerceDiscountRel.getClassNameId()) ||
						(classPK != commerceDiscountRel.getClassPK())) {

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

			query.append(_SQL_SELECT_COMMERCEDISCOUNTREL_WHERE);

			query.append(_FINDER_COLUMN_CN_CPK_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CN_CPK_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceDiscountRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				if (!pagination) {
					list = (List<CommerceDiscountRel>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceDiscountRel>)QueryUtil.list(
						q, getDialect(), start, end);
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
	 * Returns the first commerce discount rel in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount rel
	 * @throws NoSuchDiscountRelException if a matching commerce discount rel could not be found
	 */
	@Override
	public CommerceDiscountRel findByCN_CPK_First(
			long classNameId, long classPK,
			OrderByComparator<CommerceDiscountRel> orderByComparator)
		throws NoSuchDiscountRelException {

		CommerceDiscountRel commerceDiscountRel = fetchByCN_CPK_First(
			classNameId, classPK, orderByComparator);

		if (commerceDiscountRel != null) {
			return commerceDiscountRel;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append("}");

		throw new NoSuchDiscountRelException(msg.toString());
	}

	/**
	 * Returns the first commerce discount rel in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce discount rel, or <code>null</code> if a matching commerce discount rel could not be found
	 */
	@Override
	public CommerceDiscountRel fetchByCN_CPK_First(
		long classNameId, long classPK,
		OrderByComparator<CommerceDiscountRel> orderByComparator) {

		List<CommerceDiscountRel> list = findByCN_CPK(
			classNameId, classPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce discount rel in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount rel
	 * @throws NoSuchDiscountRelException if a matching commerce discount rel could not be found
	 */
	@Override
	public CommerceDiscountRel findByCN_CPK_Last(
			long classNameId, long classPK,
			OrderByComparator<CommerceDiscountRel> orderByComparator)
		throws NoSuchDiscountRelException {

		CommerceDiscountRel commerceDiscountRel = fetchByCN_CPK_Last(
			classNameId, classPK, orderByComparator);

		if (commerceDiscountRel != null) {
			return commerceDiscountRel;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append("}");

		throw new NoSuchDiscountRelException(msg.toString());
	}

	/**
	 * Returns the last commerce discount rel in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce discount rel, or <code>null</code> if a matching commerce discount rel could not be found
	 */
	@Override
	public CommerceDiscountRel fetchByCN_CPK_Last(
		long classNameId, long classPK,
		OrderByComparator<CommerceDiscountRel> orderByComparator) {

		int count = countByCN_CPK(classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<CommerceDiscountRel> list = findByCN_CPK(
			classNameId, classPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce discount rels before and after the current commerce discount rel in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param commerceDiscountRelId the primary key of the current commerce discount rel
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce discount rel
	 * @throws NoSuchDiscountRelException if a commerce discount rel with the primary key could not be found
	 */
	@Override
	public CommerceDiscountRel[] findByCN_CPK_PrevAndNext(
			long commerceDiscountRelId, long classNameId, long classPK,
			OrderByComparator<CommerceDiscountRel> orderByComparator)
		throws NoSuchDiscountRelException {

		CommerceDiscountRel commerceDiscountRel = findByPrimaryKey(
			commerceDiscountRelId);

		Session session = null;

		try {
			session = openSession();

			CommerceDiscountRel[] array = new CommerceDiscountRelImpl[3];

			array[0] = getByCN_CPK_PrevAndNext(
				session, commerceDiscountRel, classNameId, classPK,
				orderByComparator, true);

			array[1] = commerceDiscountRel;

			array[2] = getByCN_CPK_PrevAndNext(
				session, commerceDiscountRel, classNameId, classPK,
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

	protected CommerceDiscountRel getByCN_CPK_PrevAndNext(
		Session session, CommerceDiscountRel commerceDiscountRel,
		long classNameId, long classPK,
		OrderByComparator<CommerceDiscountRel> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEDISCOUNTREL_WHERE);

		query.append(_FINDER_COLUMN_CN_CPK_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_CN_CPK_CLASSPK_2);

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
			query.append(CommerceDiscountRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceDiscountRel)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceDiscountRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce discount rels where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	@Override
	public void removeByCN_CPK(long classNameId, long classPK) {
		for (CommerceDiscountRel commerceDiscountRel :
				findByCN_CPK(
					classNameId, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceDiscountRel);
		}
	}

	/**
	 * Returns the number of commerce discount rels where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching commerce discount rels
	 */
	@Override
	public int countByCN_CPK(long classNameId, long classPK) {
		FinderPath finderPath = _finderPathCountByCN_CPK;

		Object[] finderArgs = new Object[] {classNameId, classPK};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEDISCOUNTREL_WHERE);

			query.append(_FINDER_COLUMN_CN_CPK_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CN_CPK_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

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

	private static final String _FINDER_COLUMN_CN_CPK_CLASSNAMEID_2 =
		"commerceDiscountRel.classNameId = ? AND ";

	private static final String _FINDER_COLUMN_CN_CPK_CLASSPK_2 =
		"commerceDiscountRel.classPK = ?";

	public CommerceDiscountRelPersistenceImpl() {
		setModelClass(CommerceDiscountRel.class);
	}

	/**
	 * Caches the commerce discount rel in the entity cache if it is enabled.
	 *
	 * @param commerceDiscountRel the commerce discount rel
	 */
	@Override
	public void cacheResult(CommerceDiscountRel commerceDiscountRel) {
		entityCache.putResult(
			CommerceDiscountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountRelImpl.class, commerceDiscountRel.getPrimaryKey(),
			commerceDiscountRel);

		commerceDiscountRel.resetOriginalValues();
	}

	/**
	 * Caches the commerce discount rels in the entity cache if it is enabled.
	 *
	 * @param commerceDiscountRels the commerce discount rels
	 */
	@Override
	public void cacheResult(List<CommerceDiscountRel> commerceDiscountRels) {
		for (CommerceDiscountRel commerceDiscountRel : commerceDiscountRels) {
			if (entityCache.getResult(
					CommerceDiscountRelModelImpl.ENTITY_CACHE_ENABLED,
					CommerceDiscountRelImpl.class,
					commerceDiscountRel.getPrimaryKey()) == null) {

				cacheResult(commerceDiscountRel);
			}
			else {
				commerceDiscountRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce discount rels.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceDiscountRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce discount rel.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceDiscountRel commerceDiscountRel) {
		entityCache.removeResult(
			CommerceDiscountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountRelImpl.class, commerceDiscountRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CommerceDiscountRel> commerceDiscountRels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceDiscountRel commerceDiscountRel : commerceDiscountRels) {
			entityCache.removeResult(
				CommerceDiscountRelModelImpl.ENTITY_CACHE_ENABLED,
				CommerceDiscountRelImpl.class,
				commerceDiscountRel.getPrimaryKey());
		}
	}

	/**
	 * Creates a new commerce discount rel with the primary key. Does not add the commerce discount rel to the database.
	 *
	 * @param commerceDiscountRelId the primary key for the new commerce discount rel
	 * @return the new commerce discount rel
	 */
	@Override
	public CommerceDiscountRel create(long commerceDiscountRelId) {
		CommerceDiscountRel commerceDiscountRel = new CommerceDiscountRelImpl();

		commerceDiscountRel.setNew(true);
		commerceDiscountRel.setPrimaryKey(commerceDiscountRelId);

		commerceDiscountRel.setCompanyId(CompanyThreadLocal.getCompanyId());

		return commerceDiscountRel;
	}

	/**
	 * Removes the commerce discount rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDiscountRelId the primary key of the commerce discount rel
	 * @return the commerce discount rel that was removed
	 * @throws NoSuchDiscountRelException if a commerce discount rel with the primary key could not be found
	 */
	@Override
	public CommerceDiscountRel remove(long commerceDiscountRelId)
		throws NoSuchDiscountRelException {

		return remove((Serializable)commerceDiscountRelId);
	}

	/**
	 * Removes the commerce discount rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce discount rel
	 * @return the commerce discount rel that was removed
	 * @throws NoSuchDiscountRelException if a commerce discount rel with the primary key could not be found
	 */
	@Override
	public CommerceDiscountRel remove(Serializable primaryKey)
		throws NoSuchDiscountRelException {

		Session session = null;

		try {
			session = openSession();

			CommerceDiscountRel commerceDiscountRel =
				(CommerceDiscountRel)session.get(
					CommerceDiscountRelImpl.class, primaryKey);

			if (commerceDiscountRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDiscountRelException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commerceDiscountRel);
		}
		catch (NoSuchDiscountRelException nsee) {
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
	protected CommerceDiscountRel removeImpl(
		CommerceDiscountRel commerceDiscountRel) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceDiscountRel)) {
				commerceDiscountRel = (CommerceDiscountRel)session.get(
					CommerceDiscountRelImpl.class,
					commerceDiscountRel.getPrimaryKeyObj());
			}

			if (commerceDiscountRel != null) {
				session.delete(commerceDiscountRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceDiscountRel != null) {
			clearCache(commerceDiscountRel);
		}

		return commerceDiscountRel;
	}

	@Override
	public CommerceDiscountRel updateImpl(
		CommerceDiscountRel commerceDiscountRel) {

		boolean isNew = commerceDiscountRel.isNew();

		if (!(commerceDiscountRel instanceof CommerceDiscountRelModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceDiscountRel.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					commerceDiscountRel);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceDiscountRel proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceDiscountRel implementation " +
					commerceDiscountRel.getClass());
		}

		CommerceDiscountRelModelImpl commerceDiscountRelModelImpl =
			(CommerceDiscountRelModelImpl)commerceDiscountRel;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceDiscountRel.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceDiscountRel.setCreateDate(now);
			}
			else {
				commerceDiscountRel.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!commerceDiscountRelModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceDiscountRel.setModifiedDate(now);
			}
			else {
				commerceDiscountRel.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceDiscountRel.isNew()) {
				session.save(commerceDiscountRel);

				commerceDiscountRel.setNew(false);
			}
			else {
				commerceDiscountRel = (CommerceDiscountRel)session.merge(
					commerceDiscountRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceDiscountRelModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				commerceDiscountRelModelImpl.getCommerceDiscountId()
			};

			finderCache.removeResult(
				_finderPathCountByCommerceDiscountId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCommerceDiscountId, args);

			args = new Object[] {
				commerceDiscountRelModelImpl.getCommerceDiscountId(),
				commerceDiscountRelModelImpl.getClassNameId()
			};

			finderCache.removeResult(_finderPathCountByCD_CN, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCD_CN, args);

			args = new Object[] {
				commerceDiscountRelModelImpl.getClassNameId(),
				commerceDiscountRelModelImpl.getClassPK()
			};

			finderCache.removeResult(_finderPathCountByCN_CPK, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCN_CPK, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((commerceDiscountRelModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCommerceDiscountId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceDiscountRelModelImpl.getOriginalCommerceDiscountId()
				};

				finderCache.removeResult(
					_finderPathCountByCommerceDiscountId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommerceDiscountId, args);

				args = new Object[] {
					commerceDiscountRelModelImpl.getCommerceDiscountId()
				};

				finderCache.removeResult(
					_finderPathCountByCommerceDiscountId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommerceDiscountId, args);
			}

			if ((commerceDiscountRelModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCD_CN.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceDiscountRelModelImpl.
						getOriginalCommerceDiscountId(),
					commerceDiscountRelModelImpl.getOriginalClassNameId()
				};

				finderCache.removeResult(_finderPathCountByCD_CN, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCD_CN, args);

				args = new Object[] {
					commerceDiscountRelModelImpl.getCommerceDiscountId(),
					commerceDiscountRelModelImpl.getClassNameId()
				};

				finderCache.removeResult(_finderPathCountByCD_CN, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCD_CN, args);
			}

			if ((commerceDiscountRelModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCN_CPK.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceDiscountRelModelImpl.getOriginalClassNameId(),
					commerceDiscountRelModelImpl.getOriginalClassPK()
				};

				finderCache.removeResult(_finderPathCountByCN_CPK, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCN_CPK, args);

				args = new Object[] {
					commerceDiscountRelModelImpl.getClassNameId(),
					commerceDiscountRelModelImpl.getClassPK()
				};

				finderCache.removeResult(_finderPathCountByCN_CPK, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCN_CPK, args);
			}
		}

		entityCache.putResult(
			CommerceDiscountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountRelImpl.class, commerceDiscountRel.getPrimaryKey(),
			commerceDiscountRel, false);

		commerceDiscountRel.resetOriginalValues();

		return commerceDiscountRel;
	}

	/**
	 * Returns the commerce discount rel with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce discount rel
	 * @return the commerce discount rel
	 * @throws NoSuchDiscountRelException if a commerce discount rel with the primary key could not be found
	 */
	@Override
	public CommerceDiscountRel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDiscountRelException {

		CommerceDiscountRel commerceDiscountRel = fetchByPrimaryKey(primaryKey);

		if (commerceDiscountRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDiscountRelException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commerceDiscountRel;
	}

	/**
	 * Returns the commerce discount rel with the primary key or throws a <code>NoSuchDiscountRelException</code> if it could not be found.
	 *
	 * @param commerceDiscountRelId the primary key of the commerce discount rel
	 * @return the commerce discount rel
	 * @throws NoSuchDiscountRelException if a commerce discount rel with the primary key could not be found
	 */
	@Override
	public CommerceDiscountRel findByPrimaryKey(long commerceDiscountRelId)
		throws NoSuchDiscountRelException {

		return findByPrimaryKey((Serializable)commerceDiscountRelId);
	}

	/**
	 * Returns the commerce discount rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce discount rel
	 * @return the commerce discount rel, or <code>null</code> if a commerce discount rel with the primary key could not be found
	 */
	@Override
	public CommerceDiscountRel fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			CommerceDiscountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceDiscountRel commerceDiscountRel =
			(CommerceDiscountRel)serializable;

		if (commerceDiscountRel == null) {
			Session session = null;

			try {
				session = openSession();

				commerceDiscountRel = (CommerceDiscountRel)session.get(
					CommerceDiscountRelImpl.class, primaryKey);

				if (commerceDiscountRel != null) {
					cacheResult(commerceDiscountRel);
				}
				else {
					entityCache.putResult(
						CommerceDiscountRelModelImpl.ENTITY_CACHE_ENABLED,
						CommerceDiscountRelImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CommerceDiscountRelModelImpl.ENTITY_CACHE_ENABLED,
					CommerceDiscountRelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceDiscountRel;
	}

	/**
	 * Returns the commerce discount rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceDiscountRelId the primary key of the commerce discount rel
	 * @return the commerce discount rel, or <code>null</code> if a commerce discount rel with the primary key could not be found
	 */
	@Override
	public CommerceDiscountRel fetchByPrimaryKey(long commerceDiscountRelId) {
		return fetchByPrimaryKey((Serializable)commerceDiscountRelId);
	}

	@Override
	public Map<Serializable, CommerceDiscountRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceDiscountRel> map =
			new HashMap<Serializable, CommerceDiscountRel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceDiscountRel commerceDiscountRel = fetchByPrimaryKey(
				primaryKey);

			if (commerceDiscountRel != null) {
				map.put(primaryKey, commerceDiscountRel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CommerceDiscountRelModelImpl.ENTITY_CACHE_ENABLED,
				CommerceDiscountRelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceDiscountRel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_COMMERCEDISCOUNTREL_WHERE_PKS_IN);

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

			for (CommerceDiscountRel commerceDiscountRel :
					(List<CommerceDiscountRel>)q.list()) {

				map.put(
					commerceDiscountRel.getPrimaryKeyObj(),
					commerceDiscountRel);

				cacheResult(commerceDiscountRel);

				uncachedPrimaryKeys.remove(
					commerceDiscountRel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CommerceDiscountRelModelImpl.ENTITY_CACHE_ENABLED,
					CommerceDiscountRelImpl.class, primaryKey, nullModel);
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
	 * Returns all the commerce discount rels.
	 *
	 * @return the commerce discount rels
	 */
	@Override
	public List<CommerceDiscountRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce discount rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce discount rels
	 * @param end the upper bound of the range of commerce discount rels (not inclusive)
	 * @return the range of commerce discount rels
	 */
	@Override
	public List<CommerceDiscountRel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce discount rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce discount rels
	 * @param end the upper bound of the range of commerce discount rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce discount rels
	 */
	@Override
	public List<CommerceDiscountRel> findAll(
		int start, int end,
		OrderByComparator<CommerceDiscountRel> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce discount rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDiscountRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce discount rels
	 * @param end the upper bound of the range of commerce discount rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce discount rels
	 */
	@Override
	public List<CommerceDiscountRel> findAll(
		int start, int end,
		OrderByComparator<CommerceDiscountRel> orderByComparator,
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

		List<CommerceDiscountRel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceDiscountRel>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEDISCOUNTREL);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEDISCOUNTREL;

				if (pagination) {
					sql = sql.concat(
						CommerceDiscountRelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceDiscountRel>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceDiscountRel>)QueryUtil.list(
						q, getDialect(), start, end);
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
	 * Removes all the commerce discount rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceDiscountRel commerceDiscountRel : findAll()) {
			remove(commerceDiscountRel);
		}
	}

	/**
	 * Returns the number of commerce discount rels.
	 *
	 * @return the number of commerce discount rels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCEDISCOUNTREL);

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
	protected Map<String, Integer> getTableColumnsMap() {
		return CommerceDiscountRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce discount rel persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CommerceDiscountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CommerceDiscountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CommerceDiscountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByCommerceDiscountId = new FinderPath(
			CommerceDiscountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCommerceDiscountId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCommerceDiscountId = new FinderPath(
			CommerceDiscountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCommerceDiscountId", new String[] {Long.class.getName()},
			CommerceDiscountRelModelImpl.COMMERCEDISCOUNTID_COLUMN_BITMASK |
			CommerceDiscountRelModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByCommerceDiscountId = new FinderPath(
			CommerceDiscountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceDiscountId", new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByCD_CN = new FinderPath(
			CommerceDiscountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCD_CN",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCD_CN = new FinderPath(
			CommerceDiscountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCD_CN",
			new String[] {Long.class.getName(), Long.class.getName()},
			CommerceDiscountRelModelImpl.COMMERCEDISCOUNTID_COLUMN_BITMASK |
			CommerceDiscountRelModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			CommerceDiscountRelModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByCD_CN = new FinderPath(
			CommerceDiscountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCD_CN",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByCN_CPK = new FinderPath(
			CommerceDiscountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCN_CPK",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCN_CPK = new FinderPath(
			CommerceDiscountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceDiscountRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCN_CPK",
			new String[] {Long.class.getName(), Long.class.getName()},
			CommerceDiscountRelModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			CommerceDiscountRelModelImpl.CLASSPK_COLUMN_BITMASK |
			CommerceDiscountRelModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByCN_CPK = new FinderPath(
			CommerceDiscountRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDiscountRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCN_CPK",
			new String[] {Long.class.getName(), Long.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(CommerceDiscountRelImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_COMMERCEDISCOUNTREL =
		"SELECT commerceDiscountRel FROM CommerceDiscountRel commerceDiscountRel";

	private static final String _SQL_SELECT_COMMERCEDISCOUNTREL_WHERE_PKS_IN =
		"SELECT commerceDiscountRel FROM CommerceDiscountRel commerceDiscountRel WHERE commerceDiscountRelId IN (";

	private static final String _SQL_SELECT_COMMERCEDISCOUNTREL_WHERE =
		"SELECT commerceDiscountRel FROM CommerceDiscountRel commerceDiscountRel WHERE ";

	private static final String _SQL_COUNT_COMMERCEDISCOUNTREL =
		"SELECT COUNT(commerceDiscountRel) FROM CommerceDiscountRel commerceDiscountRel";

	private static final String _SQL_COUNT_COMMERCEDISCOUNTREL_WHERE =
		"SELECT COUNT(commerceDiscountRel) FROM CommerceDiscountRel commerceDiscountRel WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceDiscountRel.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommerceDiscountRel exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommerceDiscountRel exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceDiscountRelPersistenceImpl.class);

}