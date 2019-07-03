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

import com.liferay.commerce.product.exception.NoSuchChannelRelException;
import com.liferay.commerce.product.model.CommerceChannelRel;
import com.liferay.commerce.product.model.impl.CommerceChannelRelImpl;
import com.liferay.commerce.product.model.impl.CommerceChannelRelModelImpl;
import com.liferay.commerce.product.service.persistence.CommerceChannelRelPersistence;
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
 * The persistence implementation for the commerce channel rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @generated
 */
@ProviderType
public class CommerceChannelRelPersistenceImpl
	extends BasePersistenceImpl<CommerceChannelRel>
	implements CommerceChannelRelPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommerceChannelRelUtil</code> to access the commerce channel rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommerceChannelRelImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByCommerceChannelId;
	private FinderPath _finderPathWithoutPaginationFindByCommerceChannelId;
	private FinderPath _finderPathCountByCommerceChannelId;

	/**
	 * Returns all the commerce channel rels where commerceChannelId = &#63;.
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @return the matching commerce channel rels
	 */
	@Override
	public List<CommerceChannelRel> findByCommerceChannelId(
		long commerceChannelId) {

		return findByCommerceChannelId(
			commerceChannelId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce channel rels where commerceChannelId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceChannelRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @param start the lower bound of the range of commerce channel rels
	 * @param end the upper bound of the range of commerce channel rels (not inclusive)
	 * @return the range of matching commerce channel rels
	 */
	@Override
	public List<CommerceChannelRel> findByCommerceChannelId(
		long commerceChannelId, int start, int end) {

		return findByCommerceChannelId(commerceChannelId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce channel rels where commerceChannelId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceChannelRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @param start the lower bound of the range of commerce channel rels
	 * @param end the upper bound of the range of commerce channel rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce channel rels
	 */
	@Override
	public List<CommerceChannelRel> findByCommerceChannelId(
		long commerceChannelId, int start, int end,
		OrderByComparator<CommerceChannelRel> orderByComparator) {

		return findByCommerceChannelId(
			commerceChannelId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce channel rels where commerceChannelId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceChannelRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @param start the lower bound of the range of commerce channel rels
	 * @param end the upper bound of the range of commerce channel rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce channel rels
	 */
	@Override
	public List<CommerceChannelRel> findByCommerceChannelId(
		long commerceChannelId, int start, int end,
		OrderByComparator<CommerceChannelRel> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByCommerceChannelId;
			finderArgs = new Object[] {commerceChannelId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByCommerceChannelId;
			finderArgs = new Object[] {
				commerceChannelId, start, end, orderByComparator
			};
		}

		List<CommerceChannelRel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceChannelRel>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceChannelRel commerceChannelRel : list) {
					if ((commerceChannelId !=
							commerceChannelRel.getCommerceChannelId())) {

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

			query.append(_SQL_SELECT_COMMERCECHANNELREL_WHERE);

			query.append(_FINDER_COLUMN_COMMERCECHANNELID_COMMERCECHANNELID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceChannelRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceChannelId);

				if (!pagination) {
					list = (List<CommerceChannelRel>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceChannelRel>)QueryUtil.list(
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
	 * Returns the first commerce channel rel in the ordered set where commerceChannelId = &#63;.
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce channel rel
	 * @throws NoSuchChannelRelException if a matching commerce channel rel could not be found
	 */
	@Override
	public CommerceChannelRel findByCommerceChannelId_First(
			long commerceChannelId,
			OrderByComparator<CommerceChannelRel> orderByComparator)
		throws NoSuchChannelRelException {

		CommerceChannelRel commerceChannelRel = fetchByCommerceChannelId_First(
			commerceChannelId, orderByComparator);

		if (commerceChannelRel != null) {
			return commerceChannelRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceChannelId=");
		msg.append(commerceChannelId);

		msg.append("}");

		throw new NoSuchChannelRelException(msg.toString());
	}

	/**
	 * Returns the first commerce channel rel in the ordered set where commerceChannelId = &#63;.
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce channel rel, or <code>null</code> if a matching commerce channel rel could not be found
	 */
	@Override
	public CommerceChannelRel fetchByCommerceChannelId_First(
		long commerceChannelId,
		OrderByComparator<CommerceChannelRel> orderByComparator) {

		List<CommerceChannelRel> list = findByCommerceChannelId(
			commerceChannelId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce channel rel in the ordered set where commerceChannelId = &#63;.
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce channel rel
	 * @throws NoSuchChannelRelException if a matching commerce channel rel could not be found
	 */
	@Override
	public CommerceChannelRel findByCommerceChannelId_Last(
			long commerceChannelId,
			OrderByComparator<CommerceChannelRel> orderByComparator)
		throws NoSuchChannelRelException {

		CommerceChannelRel commerceChannelRel = fetchByCommerceChannelId_Last(
			commerceChannelId, orderByComparator);

		if (commerceChannelRel != null) {
			return commerceChannelRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceChannelId=");
		msg.append(commerceChannelId);

		msg.append("}");

		throw new NoSuchChannelRelException(msg.toString());
	}

	/**
	 * Returns the last commerce channel rel in the ordered set where commerceChannelId = &#63;.
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce channel rel, or <code>null</code> if a matching commerce channel rel could not be found
	 */
	@Override
	public CommerceChannelRel fetchByCommerceChannelId_Last(
		long commerceChannelId,
		OrderByComparator<CommerceChannelRel> orderByComparator) {

		int count = countByCommerceChannelId(commerceChannelId);

		if (count == 0) {
			return null;
		}

		List<CommerceChannelRel> list = findByCommerceChannelId(
			commerceChannelId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce channel rels before and after the current commerce channel rel in the ordered set where commerceChannelId = &#63;.
	 *
	 * @param commerceChannelRelId the primary key of the current commerce channel rel
	 * @param commerceChannelId the commerce channel ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce channel rel
	 * @throws NoSuchChannelRelException if a commerce channel rel with the primary key could not be found
	 */
	@Override
	public CommerceChannelRel[] findByCommerceChannelId_PrevAndNext(
			long commerceChannelRelId, long commerceChannelId,
			OrderByComparator<CommerceChannelRel> orderByComparator)
		throws NoSuchChannelRelException {

		CommerceChannelRel commerceChannelRel = findByPrimaryKey(
			commerceChannelRelId);

		Session session = null;

		try {
			session = openSession();

			CommerceChannelRel[] array = new CommerceChannelRelImpl[3];

			array[0] = getByCommerceChannelId_PrevAndNext(
				session, commerceChannelRel, commerceChannelId,
				orderByComparator, true);

			array[1] = commerceChannelRel;

			array[2] = getByCommerceChannelId_PrevAndNext(
				session, commerceChannelRel, commerceChannelId,
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

	protected CommerceChannelRel getByCommerceChannelId_PrevAndNext(
		Session session, CommerceChannelRel commerceChannelRel,
		long commerceChannelId,
		OrderByComparator<CommerceChannelRel> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCECHANNELREL_WHERE);

		query.append(_FINDER_COLUMN_COMMERCECHANNELID_COMMERCECHANNELID_2);

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
			query.append(CommerceChannelRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceChannelId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceChannelRel)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceChannelRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce channel rels where commerceChannelId = &#63; from the database.
	 *
	 * @param commerceChannelId the commerce channel ID
	 */
	@Override
	public void removeByCommerceChannelId(long commerceChannelId) {
		for (CommerceChannelRel commerceChannelRel :
				findByCommerceChannelId(
					commerceChannelId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceChannelRel);
		}
	}

	/**
	 * Returns the number of commerce channel rels where commerceChannelId = &#63;.
	 *
	 * @param commerceChannelId the commerce channel ID
	 * @return the number of matching commerce channel rels
	 */
	@Override
	public int countByCommerceChannelId(long commerceChannelId) {
		FinderPath finderPath = _finderPathCountByCommerceChannelId;

		Object[] finderArgs = new Object[] {commerceChannelId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCECHANNELREL_WHERE);

			query.append(_FINDER_COLUMN_COMMERCECHANNELID_COMMERCECHANNELID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceChannelId);

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
		_FINDER_COLUMN_COMMERCECHANNELID_COMMERCECHANNELID_2 =
			"commerceChannelRel.commerceChannelId = ?";

	private FinderPath _finderPathWithPaginationFindByC_C;
	private FinderPath _finderPathWithoutPaginationFindByC_C;
	private FinderPath _finderPathCountByC_C;

	/**
	 * Returns all the commerce channel rels where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching commerce channel rels
	 */
	@Override
	public List<CommerceChannelRel> findByC_C(long classNameId, long classPK) {
		return findByC_C(
			classNameId, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce channel rels where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceChannelRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of commerce channel rels
	 * @param end the upper bound of the range of commerce channel rels (not inclusive)
	 * @return the range of matching commerce channel rels
	 */
	@Override
	public List<CommerceChannelRel> findByC_C(
		long classNameId, long classPK, int start, int end) {

		return findByC_C(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce channel rels where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceChannelRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of commerce channel rels
	 * @param end the upper bound of the range of commerce channel rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce channel rels
	 */
	@Override
	public List<CommerceChannelRel> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<CommerceChannelRel> orderByComparator) {

		return findByC_C(
			classNameId, classPK, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce channel rels where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceChannelRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of commerce channel rels
	 * @param end the upper bound of the range of commerce channel rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce channel rels
	 */
	@Override
	public List<CommerceChannelRel> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<CommerceChannelRel> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByC_C;
			finderArgs = new Object[] {classNameId, classPK};
		}
		else {
			finderPath = _finderPathWithPaginationFindByC_C;
			finderArgs = new Object[] {
				classNameId, classPK, start, end, orderByComparator
			};
		}

		List<CommerceChannelRel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceChannelRel>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceChannelRel commerceChannelRel : list) {
					if ((classNameId != commerceChannelRel.getClassNameId()) ||
						(classPK != commerceChannelRel.getClassPK())) {

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

			query.append(_SQL_SELECT_COMMERCECHANNELREL_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceChannelRelModelImpl.ORDER_BY_JPQL);
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
					list = (List<CommerceChannelRel>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceChannelRel>)QueryUtil.list(
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
	 * Returns the first commerce channel rel in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce channel rel
	 * @throws NoSuchChannelRelException if a matching commerce channel rel could not be found
	 */
	@Override
	public CommerceChannelRel findByC_C_First(
			long classNameId, long classPK,
			OrderByComparator<CommerceChannelRel> orderByComparator)
		throws NoSuchChannelRelException {

		CommerceChannelRel commerceChannelRel = fetchByC_C_First(
			classNameId, classPK, orderByComparator);

		if (commerceChannelRel != null) {
			return commerceChannelRel;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append("}");

		throw new NoSuchChannelRelException(msg.toString());
	}

	/**
	 * Returns the first commerce channel rel in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce channel rel, or <code>null</code> if a matching commerce channel rel could not be found
	 */
	@Override
	public CommerceChannelRel fetchByC_C_First(
		long classNameId, long classPK,
		OrderByComparator<CommerceChannelRel> orderByComparator) {

		List<CommerceChannelRel> list = findByC_C(
			classNameId, classPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce channel rel in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce channel rel
	 * @throws NoSuchChannelRelException if a matching commerce channel rel could not be found
	 */
	@Override
	public CommerceChannelRel findByC_C_Last(
			long classNameId, long classPK,
			OrderByComparator<CommerceChannelRel> orderByComparator)
		throws NoSuchChannelRelException {

		CommerceChannelRel commerceChannelRel = fetchByC_C_Last(
			classNameId, classPK, orderByComparator);

		if (commerceChannelRel != null) {
			return commerceChannelRel;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append("}");

		throw new NoSuchChannelRelException(msg.toString());
	}

	/**
	 * Returns the last commerce channel rel in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce channel rel, or <code>null</code> if a matching commerce channel rel could not be found
	 */
	@Override
	public CommerceChannelRel fetchByC_C_Last(
		long classNameId, long classPK,
		OrderByComparator<CommerceChannelRel> orderByComparator) {

		int count = countByC_C(classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<CommerceChannelRel> list = findByC_C(
			classNameId, classPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce channel rels before and after the current commerce channel rel in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param commerceChannelRelId the primary key of the current commerce channel rel
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce channel rel
	 * @throws NoSuchChannelRelException if a commerce channel rel with the primary key could not be found
	 */
	@Override
	public CommerceChannelRel[] findByC_C_PrevAndNext(
			long commerceChannelRelId, long classNameId, long classPK,
			OrderByComparator<CommerceChannelRel> orderByComparator)
		throws NoSuchChannelRelException {

		CommerceChannelRel commerceChannelRel = findByPrimaryKey(
			commerceChannelRelId);

		Session session = null;

		try {
			session = openSession();

			CommerceChannelRel[] array = new CommerceChannelRelImpl[3];

			array[0] = getByC_C_PrevAndNext(
				session, commerceChannelRel, classNameId, classPK,
				orderByComparator, true);

			array[1] = commerceChannelRel;

			array[2] = getByC_C_PrevAndNext(
				session, commerceChannelRel, classNameId, classPK,
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

	protected CommerceChannelRel getByC_C_PrevAndNext(
		Session session, CommerceChannelRel commerceChannelRel,
		long classNameId, long classPK,
		OrderByComparator<CommerceChannelRel> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCECHANNELREL_WHERE);

		query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

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
			query.append(CommerceChannelRelModelImpl.ORDER_BY_JPQL);
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
						commerceChannelRel)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceChannelRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce channel rels where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	@Override
	public void removeByC_C(long classNameId, long classPK) {
		for (CommerceChannelRel commerceChannelRel :
				findByC_C(
					classNameId, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceChannelRel);
		}
	}

	/**
	 * Returns the number of commerce channel rels where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching commerce channel rels
	 */
	@Override
	public int countByC_C(long classNameId, long classPK) {
		FinderPath finderPath = _finderPathCountByC_C;

		Object[] finderArgs = new Object[] {classNameId, classPK};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCECHANNELREL_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

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

	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 =
		"commerceChannelRel.classNameId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 =
		"commerceChannelRel.classPK = ?";

	private FinderPath _finderPathFetchByC_C_C;
	private FinderPath _finderPathCountByC_C_C;

	/**
	 * Returns the commerce channel rel where classNameId = &#63; and classPK = &#63; and commerceChannelId = &#63; or throws a <code>NoSuchChannelRelException</code> if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param commerceChannelId the commerce channel ID
	 * @return the matching commerce channel rel
	 * @throws NoSuchChannelRelException if a matching commerce channel rel could not be found
	 */
	@Override
	public CommerceChannelRel findByC_C_C(
			long classNameId, long classPK, long commerceChannelId)
		throws NoSuchChannelRelException {

		CommerceChannelRel commerceChannelRel = fetchByC_C_C(
			classNameId, classPK, commerceChannelId);

		if (commerceChannelRel == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(", commerceChannelId=");
			msg.append(commerceChannelId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchChannelRelException(msg.toString());
		}

		return commerceChannelRel;
	}

	/**
	 * Returns the commerce channel rel where classNameId = &#63; and classPK = &#63; and commerceChannelId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param commerceChannelId the commerce channel ID
	 * @return the matching commerce channel rel, or <code>null</code> if a matching commerce channel rel could not be found
	 */
	@Override
	public CommerceChannelRel fetchByC_C_C(
		long classNameId, long classPK, long commerceChannelId) {

		return fetchByC_C_C(classNameId, classPK, commerceChannelId, true);
	}

	/**
	 * Returns the commerce channel rel where classNameId = &#63; and classPK = &#63; and commerceChannelId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param commerceChannelId the commerce channel ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching commerce channel rel, or <code>null</code> if a matching commerce channel rel could not be found
	 */
	@Override
	public CommerceChannelRel fetchByC_C_C(
		long classNameId, long classPK, long commerceChannelId,
		boolean retrieveFromCache) {

		Object[] finderArgs = new Object[] {
			classNameId, classPK, commerceChannelId
		};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_C_C, finderArgs, this);
		}

		if (result instanceof CommerceChannelRel) {
			CommerceChannelRel commerceChannelRel = (CommerceChannelRel)result;

			if ((classNameId != commerceChannelRel.getClassNameId()) ||
				(classPK != commerceChannelRel.getClassPK()) ||
				(commerceChannelId !=
					commerceChannelRel.getCommerceChannelId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_COMMERCECHANNELREL_WHERE);

			query.append(_FINDER_COLUMN_C_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_C_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_C_COMMERCECHANNELID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(commerceChannelId);

				List<CommerceChannelRel> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByC_C_C, finderArgs, list);
				}
				else {
					CommerceChannelRel commerceChannelRel = list.get(0);

					result = commerceChannelRel;

					cacheResult(commerceChannelRel);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByC_C_C, finderArgs);

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
			return (CommerceChannelRel)result;
		}
	}

	/**
	 * Removes the commerce channel rel where classNameId = &#63; and classPK = &#63; and commerceChannelId = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param commerceChannelId the commerce channel ID
	 * @return the commerce channel rel that was removed
	 */
	@Override
	public CommerceChannelRel removeByC_C_C(
			long classNameId, long classPK, long commerceChannelId)
		throws NoSuchChannelRelException {

		CommerceChannelRel commerceChannelRel = findByC_C_C(
			classNameId, classPK, commerceChannelId);

		return remove(commerceChannelRel);
	}

	/**
	 * Returns the number of commerce channel rels where classNameId = &#63; and classPK = &#63; and commerceChannelId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param commerceChannelId the commerce channel ID
	 * @return the number of matching commerce channel rels
	 */
	@Override
	public int countByC_C_C(
		long classNameId, long classPK, long commerceChannelId) {

		FinderPath finderPath = _finderPathCountByC_C_C;

		Object[] finderArgs = new Object[] {
			classNameId, classPK, commerceChannelId
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_COMMERCECHANNELREL_WHERE);

			query.append(_FINDER_COLUMN_C_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_C_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_C_COMMERCECHANNELID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(commerceChannelId);

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

	private static final String _FINDER_COLUMN_C_C_C_CLASSNAMEID_2 =
		"commerceChannelRel.classNameId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_C_CLASSPK_2 =
		"commerceChannelRel.classPK = ? AND ";

	private static final String _FINDER_COLUMN_C_C_C_COMMERCECHANNELID_2 =
		"commerceChannelRel.commerceChannelId = ?";

	public CommerceChannelRelPersistenceImpl() {
		setModelClass(CommerceChannelRel.class);
	}

	/**
	 * Caches the commerce channel rel in the entity cache if it is enabled.
	 *
	 * @param commerceChannelRel the commerce channel rel
	 */
	@Override
	public void cacheResult(CommerceChannelRel commerceChannelRel) {
		entityCache.putResult(
			CommerceChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelRelImpl.class, commerceChannelRel.getPrimaryKey(),
			commerceChannelRel);

		finderCache.putResult(
			_finderPathFetchByC_C_C,
			new Object[] {
				commerceChannelRel.getClassNameId(),
				commerceChannelRel.getClassPK(),
				commerceChannelRel.getCommerceChannelId()
			},
			commerceChannelRel);

		commerceChannelRel.resetOriginalValues();
	}

	/**
	 * Caches the commerce channel rels in the entity cache if it is enabled.
	 *
	 * @param commerceChannelRels the commerce channel rels
	 */
	@Override
	public void cacheResult(List<CommerceChannelRel> commerceChannelRels) {
		for (CommerceChannelRel commerceChannelRel : commerceChannelRels) {
			if (entityCache.getResult(
					CommerceChannelRelModelImpl.ENTITY_CACHE_ENABLED,
					CommerceChannelRelImpl.class,
					commerceChannelRel.getPrimaryKey()) == null) {

				cacheResult(commerceChannelRel);
			}
			else {
				commerceChannelRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce channel rels.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceChannelRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce channel rel.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceChannelRel commerceChannelRel) {
		entityCache.removeResult(
			CommerceChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelRelImpl.class, commerceChannelRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(CommerceChannelRelModelImpl)commerceChannelRel, true);
	}

	@Override
	public void clearCache(List<CommerceChannelRel> commerceChannelRels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceChannelRel commerceChannelRel : commerceChannelRels) {
			entityCache.removeResult(
				CommerceChannelRelModelImpl.ENTITY_CACHE_ENABLED,
				CommerceChannelRelImpl.class,
				commerceChannelRel.getPrimaryKey());

			clearUniqueFindersCache(
				(CommerceChannelRelModelImpl)commerceChannelRel, true);
		}
	}

	protected void cacheUniqueFindersCache(
		CommerceChannelRelModelImpl commerceChannelRelModelImpl) {

		Object[] args = new Object[] {
			commerceChannelRelModelImpl.getClassNameId(),
			commerceChannelRelModelImpl.getClassPK(),
			commerceChannelRelModelImpl.getCommerceChannelId()
		};

		finderCache.putResult(
			_finderPathCountByC_C_C, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_C_C, args, commerceChannelRelModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CommerceChannelRelModelImpl commerceChannelRelModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				commerceChannelRelModelImpl.getClassNameId(),
				commerceChannelRelModelImpl.getClassPK(),
				commerceChannelRelModelImpl.getCommerceChannelId()
			};

			finderCache.removeResult(_finderPathCountByC_C_C, args);
			finderCache.removeResult(_finderPathFetchByC_C_C, args);
		}

		if ((commerceChannelRelModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_C_C.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				commerceChannelRelModelImpl.getOriginalClassNameId(),
				commerceChannelRelModelImpl.getOriginalClassPK(),
				commerceChannelRelModelImpl.getOriginalCommerceChannelId()
			};

			finderCache.removeResult(_finderPathCountByC_C_C, args);
			finderCache.removeResult(_finderPathFetchByC_C_C, args);
		}
	}

	/**
	 * Creates a new commerce channel rel with the primary key. Does not add the commerce channel rel to the database.
	 *
	 * @param commerceChannelRelId the primary key for the new commerce channel rel
	 * @return the new commerce channel rel
	 */
	@Override
	public CommerceChannelRel create(long commerceChannelRelId) {
		CommerceChannelRel commerceChannelRel = new CommerceChannelRelImpl();

		commerceChannelRel.setNew(true);
		commerceChannelRel.setPrimaryKey(commerceChannelRelId);

		commerceChannelRel.setCompanyId(CompanyThreadLocal.getCompanyId());

		return commerceChannelRel;
	}

	/**
	 * Removes the commerce channel rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceChannelRelId the primary key of the commerce channel rel
	 * @return the commerce channel rel that was removed
	 * @throws NoSuchChannelRelException if a commerce channel rel with the primary key could not be found
	 */
	@Override
	public CommerceChannelRel remove(long commerceChannelRelId)
		throws NoSuchChannelRelException {

		return remove((Serializable)commerceChannelRelId);
	}

	/**
	 * Removes the commerce channel rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce channel rel
	 * @return the commerce channel rel that was removed
	 * @throws NoSuchChannelRelException if a commerce channel rel with the primary key could not be found
	 */
	@Override
	public CommerceChannelRel remove(Serializable primaryKey)
		throws NoSuchChannelRelException {

		Session session = null;

		try {
			session = openSession();

			CommerceChannelRel commerceChannelRel =
				(CommerceChannelRel)session.get(
					CommerceChannelRelImpl.class, primaryKey);

			if (commerceChannelRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChannelRelException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commerceChannelRel);
		}
		catch (NoSuchChannelRelException nsee) {
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
	protected CommerceChannelRel removeImpl(
		CommerceChannelRel commerceChannelRel) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceChannelRel)) {
				commerceChannelRel = (CommerceChannelRel)session.get(
					CommerceChannelRelImpl.class,
					commerceChannelRel.getPrimaryKeyObj());
			}

			if (commerceChannelRel != null) {
				session.delete(commerceChannelRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceChannelRel != null) {
			clearCache(commerceChannelRel);
		}

		return commerceChannelRel;
	}

	@Override
	public CommerceChannelRel updateImpl(
		CommerceChannelRel commerceChannelRel) {

		boolean isNew = commerceChannelRel.isNew();

		if (!(commerceChannelRel instanceof CommerceChannelRelModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceChannelRel.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					commerceChannelRel);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceChannelRel proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceChannelRel implementation " +
					commerceChannelRel.getClass());
		}

		CommerceChannelRelModelImpl commerceChannelRelModelImpl =
			(CommerceChannelRelModelImpl)commerceChannelRel;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceChannelRel.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceChannelRel.setCreateDate(now);
			}
			else {
				commerceChannelRel.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!commerceChannelRelModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceChannelRel.setModifiedDate(now);
			}
			else {
				commerceChannelRel.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceChannelRel.isNew()) {
				session.save(commerceChannelRel);

				commerceChannelRel.setNew(false);
			}
			else {
				commerceChannelRel = (CommerceChannelRel)session.merge(
					commerceChannelRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceChannelRelModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				commerceChannelRelModelImpl.getCommerceChannelId()
			};

			finderCache.removeResult(_finderPathCountByCommerceChannelId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCommerceChannelId, args);

			args = new Object[] {
				commerceChannelRelModelImpl.getClassNameId(),
				commerceChannelRelModelImpl.getClassPK()
			};

			finderCache.removeResult(_finderPathCountByC_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_C, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((commerceChannelRelModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCommerceChannelId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceChannelRelModelImpl.getOriginalCommerceChannelId()
				};

				finderCache.removeResult(
					_finderPathCountByCommerceChannelId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommerceChannelId, args);

				args = new Object[] {
					commerceChannelRelModelImpl.getCommerceChannelId()
				};

				finderCache.removeResult(
					_finderPathCountByCommerceChannelId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommerceChannelId, args);
			}

			if ((commerceChannelRelModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceChannelRelModelImpl.getOriginalClassNameId(),
					commerceChannelRelModelImpl.getOriginalClassPK()
				};

				finderCache.removeResult(_finderPathCountByC_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_C, args);

				args = new Object[] {
					commerceChannelRelModelImpl.getClassNameId(),
					commerceChannelRelModelImpl.getClassPK()
				};

				finderCache.removeResult(_finderPathCountByC_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_C, args);
			}
		}

		entityCache.putResult(
			CommerceChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelRelImpl.class, commerceChannelRel.getPrimaryKey(),
			commerceChannelRel, false);

		clearUniqueFindersCache(commerceChannelRelModelImpl, false);
		cacheUniqueFindersCache(commerceChannelRelModelImpl);

		commerceChannelRel.resetOriginalValues();

		return commerceChannelRel;
	}

	/**
	 * Returns the commerce channel rel with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce channel rel
	 * @return the commerce channel rel
	 * @throws NoSuchChannelRelException if a commerce channel rel with the primary key could not be found
	 */
	@Override
	public CommerceChannelRel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChannelRelException {

		CommerceChannelRel commerceChannelRel = fetchByPrimaryKey(primaryKey);

		if (commerceChannelRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChannelRelException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commerceChannelRel;
	}

	/**
	 * Returns the commerce channel rel with the primary key or throws a <code>NoSuchChannelRelException</code> if it could not be found.
	 *
	 * @param commerceChannelRelId the primary key of the commerce channel rel
	 * @return the commerce channel rel
	 * @throws NoSuchChannelRelException if a commerce channel rel with the primary key could not be found
	 */
	@Override
	public CommerceChannelRel findByPrimaryKey(long commerceChannelRelId)
		throws NoSuchChannelRelException {

		return findByPrimaryKey((Serializable)commerceChannelRelId);
	}

	/**
	 * Returns the commerce channel rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce channel rel
	 * @return the commerce channel rel, or <code>null</code> if a commerce channel rel with the primary key could not be found
	 */
	@Override
	public CommerceChannelRel fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			CommerceChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceChannelRel commerceChannelRel =
			(CommerceChannelRel)serializable;

		if (commerceChannelRel == null) {
			Session session = null;

			try {
				session = openSession();

				commerceChannelRel = (CommerceChannelRel)session.get(
					CommerceChannelRelImpl.class, primaryKey);

				if (commerceChannelRel != null) {
					cacheResult(commerceChannelRel);
				}
				else {
					entityCache.putResult(
						CommerceChannelRelModelImpl.ENTITY_CACHE_ENABLED,
						CommerceChannelRelImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CommerceChannelRelModelImpl.ENTITY_CACHE_ENABLED,
					CommerceChannelRelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceChannelRel;
	}

	/**
	 * Returns the commerce channel rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceChannelRelId the primary key of the commerce channel rel
	 * @return the commerce channel rel, or <code>null</code> if a commerce channel rel with the primary key could not be found
	 */
	@Override
	public CommerceChannelRel fetchByPrimaryKey(long commerceChannelRelId) {
		return fetchByPrimaryKey((Serializable)commerceChannelRelId);
	}

	@Override
	public Map<Serializable, CommerceChannelRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceChannelRel> map =
			new HashMap<Serializable, CommerceChannelRel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceChannelRel commerceChannelRel = fetchByPrimaryKey(
				primaryKey);

			if (commerceChannelRel != null) {
				map.put(primaryKey, commerceChannelRel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CommerceChannelRelModelImpl.ENTITY_CACHE_ENABLED,
				CommerceChannelRelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceChannelRel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_COMMERCECHANNELREL_WHERE_PKS_IN);

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

			for (CommerceChannelRel commerceChannelRel :
					(List<CommerceChannelRel>)q.list()) {

				map.put(
					commerceChannelRel.getPrimaryKeyObj(), commerceChannelRel);

				cacheResult(commerceChannelRel);

				uncachedPrimaryKeys.remove(
					commerceChannelRel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CommerceChannelRelModelImpl.ENTITY_CACHE_ENABLED,
					CommerceChannelRelImpl.class, primaryKey, nullModel);
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
	 * Returns all the commerce channel rels.
	 *
	 * @return the commerce channel rels
	 */
	@Override
	public List<CommerceChannelRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce channel rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceChannelRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce channel rels
	 * @param end the upper bound of the range of commerce channel rels (not inclusive)
	 * @return the range of commerce channel rels
	 */
	@Override
	public List<CommerceChannelRel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce channel rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceChannelRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce channel rels
	 * @param end the upper bound of the range of commerce channel rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce channel rels
	 */
	@Override
	public List<CommerceChannelRel> findAll(
		int start, int end,
		OrderByComparator<CommerceChannelRel> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce channel rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceChannelRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce channel rels
	 * @param end the upper bound of the range of commerce channel rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce channel rels
	 */
	@Override
	public List<CommerceChannelRel> findAll(
		int start, int end,
		OrderByComparator<CommerceChannelRel> orderByComparator,
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

		List<CommerceChannelRel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceChannelRel>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCECHANNELREL);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCECHANNELREL;

				if (pagination) {
					sql = sql.concat(CommerceChannelRelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceChannelRel>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceChannelRel>)QueryUtil.list(
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
	 * Removes all the commerce channel rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceChannelRel commerceChannelRel : findAll()) {
			remove(commerceChannelRel);
		}
	}

	/**
	 * Returns the number of commerce channel rels.
	 *
	 * @return the number of commerce channel rels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCECHANNELREL);

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
		return CommerceChannelRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce channel rel persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CommerceChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceChannelRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CommerceChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceChannelRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CommerceChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByCommerceChannelId = new FinderPath(
			CommerceChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceChannelRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCommerceChannelId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCommerceChannelId = new FinderPath(
			CommerceChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceChannelRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCommerceChannelId", new String[] {Long.class.getName()},
			CommerceChannelRelModelImpl.COMMERCECHANNELID_COLUMN_BITMASK |
			CommerceChannelRelModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByCommerceChannelId = new FinderPath(
			CommerceChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceChannelId", new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByC_C = new FinderPath(
			CommerceChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceChannelRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_C = new FinderPath(
			CommerceChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceChannelRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] {Long.class.getName(), Long.class.getName()},
			CommerceChannelRelModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			CommerceChannelRelModelImpl.CLASSPK_COLUMN_BITMASK |
			CommerceChannelRelModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByC_C = new FinderPath(
			CommerceChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathFetchByC_C_C = new FinderPath(
			CommerceChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceChannelRelImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			CommerceChannelRelModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			CommerceChannelRelModelImpl.CLASSPK_COLUMN_BITMASK |
			CommerceChannelRelModelImpl.COMMERCECHANNELID_COLUMN_BITMASK);

		_finderPathCountByC_C_C = new FinderPath(
			CommerceChannelRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceChannelRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});
	}

	public void destroy() {
		entityCache.removeCache(CommerceChannelRelImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_COMMERCECHANNELREL =
		"SELECT commerceChannelRel FROM CommerceChannelRel commerceChannelRel";

	private static final String _SQL_SELECT_COMMERCECHANNELREL_WHERE_PKS_IN =
		"SELECT commerceChannelRel FROM CommerceChannelRel commerceChannelRel WHERE commerceChannelRelId IN (";

	private static final String _SQL_SELECT_COMMERCECHANNELREL_WHERE =
		"SELECT commerceChannelRel FROM CommerceChannelRel commerceChannelRel WHERE ";

	private static final String _SQL_COUNT_COMMERCECHANNELREL =
		"SELECT COUNT(commerceChannelRel) FROM CommerceChannelRel commerceChannelRel";

	private static final String _SQL_COUNT_COMMERCECHANNELREL_WHERE =
		"SELECT COUNT(commerceChannelRel) FROM CommerceChannelRel commerceChannelRel WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceChannelRel.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommerceChannelRel exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommerceChannelRel exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceChannelRelPersistenceImpl.class);

}