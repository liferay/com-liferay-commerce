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

import com.liferay.commerce.account.exception.NoSuchAccountUserRelException;
import com.liferay.commerce.account.model.CommerceAccountUserRel;
import com.liferay.commerce.account.model.impl.CommerceAccountUserRelImpl;
import com.liferay.commerce.account.model.impl.CommerceAccountUserRelModelImpl;
import com.liferay.commerce.account.service.persistence.CommerceAccountUserRelPK;
import com.liferay.commerce.account.service.persistence.CommerceAccountUserRelPersistence;
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

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the commerce account user rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @generated
 */
@ProviderType
public class CommerceAccountUserRelPersistenceImpl
	extends BasePersistenceImpl<CommerceAccountUserRel>
	implements CommerceAccountUserRelPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommerceAccountUserRelUtil</code> to access the commerce account user rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommerceAccountUserRelImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByCommerceAccountId;
	private FinderPath _finderPathWithoutPaginationFindByCommerceAccountId;
	private FinderPath _finderPathCountByCommerceAccountId;

	/**
	 * Returns all the commerce account user rels where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @return the matching commerce account user rels
	 */
	@Override
	public List<CommerceAccountUserRel> findByCommerceAccountId(
		long commerceAccountId) {

		return findByCommerceAccountId(
			commerceAccountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce account user rels where commerceAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountUserRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param start the lower bound of the range of commerce account user rels
	 * @param end the upper bound of the range of commerce account user rels (not inclusive)
	 * @return the range of matching commerce account user rels
	 */
	@Override
	public List<CommerceAccountUserRel> findByCommerceAccountId(
		long commerceAccountId, int start, int end) {

		return findByCommerceAccountId(commerceAccountId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce account user rels where commerceAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountUserRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param start the lower bound of the range of commerce account user rels
	 * @param end the upper bound of the range of commerce account user rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce account user rels
	 */
	@Override
	public List<CommerceAccountUserRel> findByCommerceAccountId(
		long commerceAccountId, int start, int end,
		OrderByComparator<CommerceAccountUserRel> orderByComparator) {

		return findByCommerceAccountId(
			commerceAccountId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce account user rels where commerceAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountUserRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param start the lower bound of the range of commerce account user rels
	 * @param end the upper bound of the range of commerce account user rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce account user rels
	 */
	@Override
	public List<CommerceAccountUserRel> findByCommerceAccountId(
		long commerceAccountId, int start, int end,
		OrderByComparator<CommerceAccountUserRel> orderByComparator,
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

		List<CommerceAccountUserRel> list = null;

		if (useFinderCache) {
			list = (List<CommerceAccountUserRel>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceAccountUserRel commerceAccountUserRel : list) {
					if ((commerceAccountId !=
							commerceAccountUserRel.getCommerceAccountId())) {

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

			query.append(_SQL_SELECT_COMMERCEACCOUNTUSERREL_WHERE);

			query.append(_FINDER_COLUMN_COMMERCEACCOUNTID_COMMERCEACCOUNTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceAccountUserRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceAccountId);

				if (!pagination) {
					list = (List<CommerceAccountUserRel>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceAccountUserRel>)QueryUtil.list(
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
	 * Returns the first commerce account user rel in the ordered set where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce account user rel
	 * @throws NoSuchAccountUserRelException if a matching commerce account user rel could not be found
	 */
	@Override
	public CommerceAccountUserRel findByCommerceAccountId_First(
			long commerceAccountId,
			OrderByComparator<CommerceAccountUserRel> orderByComparator)
		throws NoSuchAccountUserRelException {

		CommerceAccountUserRel commerceAccountUserRel =
			fetchByCommerceAccountId_First(
				commerceAccountId, orderByComparator);

		if (commerceAccountUserRel != null) {
			return commerceAccountUserRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceAccountId=");
		msg.append(commerceAccountId);

		msg.append("}");

		throw new NoSuchAccountUserRelException(msg.toString());
	}

	/**
	 * Returns the first commerce account user rel in the ordered set where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce account user rel, or <code>null</code> if a matching commerce account user rel could not be found
	 */
	@Override
	public CommerceAccountUserRel fetchByCommerceAccountId_First(
		long commerceAccountId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator) {

		List<CommerceAccountUserRel> list = findByCommerceAccountId(
			commerceAccountId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce account user rel in the ordered set where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce account user rel
	 * @throws NoSuchAccountUserRelException if a matching commerce account user rel could not be found
	 */
	@Override
	public CommerceAccountUserRel findByCommerceAccountId_Last(
			long commerceAccountId,
			OrderByComparator<CommerceAccountUserRel> orderByComparator)
		throws NoSuchAccountUserRelException {

		CommerceAccountUserRel commerceAccountUserRel =
			fetchByCommerceAccountId_Last(commerceAccountId, orderByComparator);

		if (commerceAccountUserRel != null) {
			return commerceAccountUserRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceAccountId=");
		msg.append(commerceAccountId);

		msg.append("}");

		throw new NoSuchAccountUserRelException(msg.toString());
	}

	/**
	 * Returns the last commerce account user rel in the ordered set where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce account user rel, or <code>null</code> if a matching commerce account user rel could not be found
	 */
	@Override
	public CommerceAccountUserRel fetchByCommerceAccountId_Last(
		long commerceAccountId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator) {

		int count = countByCommerceAccountId(commerceAccountId);

		if (count == 0) {
			return null;
		}

		List<CommerceAccountUserRel> list = findByCommerceAccountId(
			commerceAccountId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce account user rels before and after the current commerce account user rel in the ordered set where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountUserRelPK the primary key of the current commerce account user rel
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce account user rel
	 * @throws NoSuchAccountUserRelException if a commerce account user rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountUserRel[] findByCommerceAccountId_PrevAndNext(
			CommerceAccountUserRelPK commerceAccountUserRelPK,
			long commerceAccountId,
			OrderByComparator<CommerceAccountUserRel> orderByComparator)
		throws NoSuchAccountUserRelException {

		CommerceAccountUserRel commerceAccountUserRel = findByPrimaryKey(
			commerceAccountUserRelPK);

		Session session = null;

		try {
			session = openSession();

			CommerceAccountUserRel[] array = new CommerceAccountUserRelImpl[3];

			array[0] = getByCommerceAccountId_PrevAndNext(
				session, commerceAccountUserRel, commerceAccountId,
				orderByComparator, true);

			array[1] = commerceAccountUserRel;

			array[2] = getByCommerceAccountId_PrevAndNext(
				session, commerceAccountUserRel, commerceAccountId,
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

	protected CommerceAccountUserRel getByCommerceAccountId_PrevAndNext(
		Session session, CommerceAccountUserRel commerceAccountUserRel,
		long commerceAccountId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEACCOUNTUSERREL_WHERE);

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
			query.append(CommerceAccountUserRelModelImpl.ORDER_BY_JPQL);
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
						commerceAccountUserRel)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceAccountUserRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce account user rels where commerceAccountId = &#63; from the database.
	 *
	 * @param commerceAccountId the commerce account ID
	 */
	@Override
	public void removeByCommerceAccountId(long commerceAccountId) {
		for (CommerceAccountUserRel commerceAccountUserRel :
				findByCommerceAccountId(
					commerceAccountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceAccountUserRel);
		}
	}

	/**
	 * Returns the number of commerce account user rels where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @return the number of matching commerce account user rels
	 */
	@Override
	public int countByCommerceAccountId(long commerceAccountId) {
		FinderPath finderPath = _finderPathCountByCommerceAccountId;

		Object[] finderArgs = new Object[] {commerceAccountId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEACCOUNTUSERREL_WHERE);

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
			"commerceAccountUserRel.id.commerceAccountId = ?";

	private FinderPath _finderPathWithPaginationFindByCommerceAccountUserId;
	private FinderPath _finderPathWithoutPaginationFindByCommerceAccountUserId;
	private FinderPath _finderPathCountByCommerceAccountUserId;

	/**
	 * Returns all the commerce account user rels where commerceAccountUserId = &#63;.
	 *
	 * @param commerceAccountUserId the commerce account user ID
	 * @return the matching commerce account user rels
	 */
	@Override
	public List<CommerceAccountUserRel> findByCommerceAccountUserId(
		long commerceAccountUserId) {

		return findByCommerceAccountUserId(
			commerceAccountUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce account user rels where commerceAccountUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountUserRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountUserId the commerce account user ID
	 * @param start the lower bound of the range of commerce account user rels
	 * @param end the upper bound of the range of commerce account user rels (not inclusive)
	 * @return the range of matching commerce account user rels
	 */
	@Override
	public List<CommerceAccountUserRel> findByCommerceAccountUserId(
		long commerceAccountUserId, int start, int end) {

		return findByCommerceAccountUserId(
			commerceAccountUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce account user rels where commerceAccountUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountUserRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountUserId the commerce account user ID
	 * @param start the lower bound of the range of commerce account user rels
	 * @param end the upper bound of the range of commerce account user rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce account user rels
	 */
	@Override
	public List<CommerceAccountUserRel> findByCommerceAccountUserId(
		long commerceAccountUserId, int start, int end,
		OrderByComparator<CommerceAccountUserRel> orderByComparator) {

		return findByCommerceAccountUserId(
			commerceAccountUserId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce account user rels where commerceAccountUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountUserRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountUserId the commerce account user ID
	 * @param start the lower bound of the range of commerce account user rels
	 * @param end the upper bound of the range of commerce account user rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce account user rels
	 */
	@Override
	public List<CommerceAccountUserRel> findByCommerceAccountUserId(
		long commerceAccountUserId, int start, int end,
		OrderByComparator<CommerceAccountUserRel> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByCommerceAccountUserId;
				finderArgs = new Object[] {commerceAccountUserId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCommerceAccountUserId;
			finderArgs = new Object[] {
				commerceAccountUserId, start, end, orderByComparator
			};
		}

		List<CommerceAccountUserRel> list = null;

		if (useFinderCache) {
			list = (List<CommerceAccountUserRel>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceAccountUserRel commerceAccountUserRel : list) {
					if ((commerceAccountUserId !=
							commerceAccountUserRel.
								getCommerceAccountUserId())) {

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

			query.append(_SQL_SELECT_COMMERCEACCOUNTUSERREL_WHERE);

			query.append(
				_FINDER_COLUMN_COMMERCEACCOUNTUSERID_COMMERCEACCOUNTUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CommerceAccountUserRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceAccountUserId);

				if (!pagination) {
					list = (List<CommerceAccountUserRel>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceAccountUserRel>)QueryUtil.list(
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
	 * Returns the first commerce account user rel in the ordered set where commerceAccountUserId = &#63;.
	 *
	 * @param commerceAccountUserId the commerce account user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce account user rel
	 * @throws NoSuchAccountUserRelException if a matching commerce account user rel could not be found
	 */
	@Override
	public CommerceAccountUserRel findByCommerceAccountUserId_First(
			long commerceAccountUserId,
			OrderByComparator<CommerceAccountUserRel> orderByComparator)
		throws NoSuchAccountUserRelException {

		CommerceAccountUserRel commerceAccountUserRel =
			fetchByCommerceAccountUserId_First(
				commerceAccountUserId, orderByComparator);

		if (commerceAccountUserRel != null) {
			return commerceAccountUserRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceAccountUserId=");
		msg.append(commerceAccountUserId);

		msg.append("}");

		throw new NoSuchAccountUserRelException(msg.toString());
	}

	/**
	 * Returns the first commerce account user rel in the ordered set where commerceAccountUserId = &#63;.
	 *
	 * @param commerceAccountUserId the commerce account user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce account user rel, or <code>null</code> if a matching commerce account user rel could not be found
	 */
	@Override
	public CommerceAccountUserRel fetchByCommerceAccountUserId_First(
		long commerceAccountUserId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator) {

		List<CommerceAccountUserRel> list = findByCommerceAccountUserId(
			commerceAccountUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce account user rel in the ordered set where commerceAccountUserId = &#63;.
	 *
	 * @param commerceAccountUserId the commerce account user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce account user rel
	 * @throws NoSuchAccountUserRelException if a matching commerce account user rel could not be found
	 */
	@Override
	public CommerceAccountUserRel findByCommerceAccountUserId_Last(
			long commerceAccountUserId,
			OrderByComparator<CommerceAccountUserRel> orderByComparator)
		throws NoSuchAccountUserRelException {

		CommerceAccountUserRel commerceAccountUserRel =
			fetchByCommerceAccountUserId_Last(
				commerceAccountUserId, orderByComparator);

		if (commerceAccountUserRel != null) {
			return commerceAccountUserRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceAccountUserId=");
		msg.append(commerceAccountUserId);

		msg.append("}");

		throw new NoSuchAccountUserRelException(msg.toString());
	}

	/**
	 * Returns the last commerce account user rel in the ordered set where commerceAccountUserId = &#63;.
	 *
	 * @param commerceAccountUserId the commerce account user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce account user rel, or <code>null</code> if a matching commerce account user rel could not be found
	 */
	@Override
	public CommerceAccountUserRel fetchByCommerceAccountUserId_Last(
		long commerceAccountUserId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator) {

		int count = countByCommerceAccountUserId(commerceAccountUserId);

		if (count == 0) {
			return null;
		}

		List<CommerceAccountUserRel> list = findByCommerceAccountUserId(
			commerceAccountUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce account user rels before and after the current commerce account user rel in the ordered set where commerceAccountUserId = &#63;.
	 *
	 * @param commerceAccountUserRelPK the primary key of the current commerce account user rel
	 * @param commerceAccountUserId the commerce account user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce account user rel
	 * @throws NoSuchAccountUserRelException if a commerce account user rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountUserRel[] findByCommerceAccountUserId_PrevAndNext(
			CommerceAccountUserRelPK commerceAccountUserRelPK,
			long commerceAccountUserId,
			OrderByComparator<CommerceAccountUserRel> orderByComparator)
		throws NoSuchAccountUserRelException {

		CommerceAccountUserRel commerceAccountUserRel = findByPrimaryKey(
			commerceAccountUserRelPK);

		Session session = null;

		try {
			session = openSession();

			CommerceAccountUserRel[] array = new CommerceAccountUserRelImpl[3];

			array[0] = getByCommerceAccountUserId_PrevAndNext(
				session, commerceAccountUserRel, commerceAccountUserId,
				orderByComparator, true);

			array[1] = commerceAccountUserRel;

			array[2] = getByCommerceAccountUserId_PrevAndNext(
				session, commerceAccountUserRel, commerceAccountUserId,
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

	protected CommerceAccountUserRel getByCommerceAccountUserId_PrevAndNext(
		Session session, CommerceAccountUserRel commerceAccountUserRel,
		long commerceAccountUserId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEACCOUNTUSERREL_WHERE);

		query.append(
			_FINDER_COLUMN_COMMERCEACCOUNTUSERID_COMMERCEACCOUNTUSERID_2);

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
			query.append(CommerceAccountUserRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceAccountUserId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceAccountUserRel)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceAccountUserRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce account user rels where commerceAccountUserId = &#63; from the database.
	 *
	 * @param commerceAccountUserId the commerce account user ID
	 */
	@Override
	public void removeByCommerceAccountUserId(long commerceAccountUserId) {
		for (CommerceAccountUserRel commerceAccountUserRel :
				findByCommerceAccountUserId(
					commerceAccountUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceAccountUserRel);
		}
	}

	/**
	 * Returns the number of commerce account user rels where commerceAccountUserId = &#63;.
	 *
	 * @param commerceAccountUserId the commerce account user ID
	 * @return the number of matching commerce account user rels
	 */
	@Override
	public int countByCommerceAccountUserId(long commerceAccountUserId) {
		FinderPath finderPath = _finderPathCountByCommerceAccountUserId;

		Object[] finderArgs = new Object[] {commerceAccountUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEACCOUNTUSERREL_WHERE);

			query.append(
				_FINDER_COLUMN_COMMERCEACCOUNTUSERID_COMMERCEACCOUNTUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceAccountUserId);

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
		_FINDER_COLUMN_COMMERCEACCOUNTUSERID_COMMERCEACCOUNTUSERID_2 =
			"commerceAccountUserRel.id.commerceAccountUserId = ?";

	public CommerceAccountUserRelPersistenceImpl() {
		setModelClass(CommerceAccountUserRel.class);
	}

	/**
	 * Caches the commerce account user rel in the entity cache if it is enabled.
	 *
	 * @param commerceAccountUserRel the commerce account user rel
	 */
	@Override
	public void cacheResult(CommerceAccountUserRel commerceAccountUserRel) {
		entityCache.putResult(
			CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountUserRelImpl.class,
			commerceAccountUserRel.getPrimaryKey(), commerceAccountUserRel);

		commerceAccountUserRel.resetOriginalValues();
	}

	/**
	 * Caches the commerce account user rels in the entity cache if it is enabled.
	 *
	 * @param commerceAccountUserRels the commerce account user rels
	 */
	@Override
	public void cacheResult(
		List<CommerceAccountUserRel> commerceAccountUserRels) {

		for (CommerceAccountUserRel commerceAccountUserRel :
				commerceAccountUserRels) {

			if (entityCache.getResult(
					CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
					CommerceAccountUserRelImpl.class,
					commerceAccountUserRel.getPrimaryKey()) == null) {

				cacheResult(commerceAccountUserRel);
			}
			else {
				commerceAccountUserRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce account user rels.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceAccountUserRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce account user rel.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceAccountUserRel commerceAccountUserRel) {
		entityCache.removeResult(
			CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountUserRelImpl.class,
			commerceAccountUserRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<CommerceAccountUserRel> commerceAccountUserRels) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceAccountUserRel commerceAccountUserRel :
				commerceAccountUserRels) {

			entityCache.removeResult(
				CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
				CommerceAccountUserRelImpl.class,
				commerceAccountUserRel.getPrimaryKey());
		}
	}

	/**
	 * Creates a new commerce account user rel with the primary key. Does not add the commerce account user rel to the database.
	 *
	 * @param commerceAccountUserRelPK the primary key for the new commerce account user rel
	 * @return the new commerce account user rel
	 */
	@Override
	public CommerceAccountUserRel create(
		CommerceAccountUserRelPK commerceAccountUserRelPK) {

		CommerceAccountUserRel commerceAccountUserRel =
			new CommerceAccountUserRelImpl();

		commerceAccountUserRel.setNew(true);
		commerceAccountUserRel.setPrimaryKey(commerceAccountUserRelPK);

		commerceAccountUserRel.setCompanyId(CompanyThreadLocal.getCompanyId());

		return commerceAccountUserRel;
	}

	/**
	 * Removes the commerce account user rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceAccountUserRelPK the primary key of the commerce account user rel
	 * @return the commerce account user rel that was removed
	 * @throws NoSuchAccountUserRelException if a commerce account user rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountUserRel remove(
			CommerceAccountUserRelPK commerceAccountUserRelPK)
		throws NoSuchAccountUserRelException {

		return remove((Serializable)commerceAccountUserRelPK);
	}

	/**
	 * Removes the commerce account user rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce account user rel
	 * @return the commerce account user rel that was removed
	 * @throws NoSuchAccountUserRelException if a commerce account user rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountUserRel remove(Serializable primaryKey)
		throws NoSuchAccountUserRelException {

		Session session = null;

		try {
			session = openSession();

			CommerceAccountUserRel commerceAccountUserRel =
				(CommerceAccountUserRel)session.get(
					CommerceAccountUserRelImpl.class, primaryKey);

			if (commerceAccountUserRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountUserRelException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commerceAccountUserRel);
		}
		catch (NoSuchAccountUserRelException nsee) {
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
	protected CommerceAccountUserRel removeImpl(
		CommerceAccountUserRel commerceAccountUserRel) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceAccountUserRel)) {
				commerceAccountUserRel = (CommerceAccountUserRel)session.get(
					CommerceAccountUserRelImpl.class,
					commerceAccountUserRel.getPrimaryKeyObj());
			}

			if (commerceAccountUserRel != null) {
				session.delete(commerceAccountUserRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceAccountUserRel != null) {
			clearCache(commerceAccountUserRel);
		}

		return commerceAccountUserRel;
	}

	@Override
	public CommerceAccountUserRel updateImpl(
		CommerceAccountUserRel commerceAccountUserRel) {

		boolean isNew = commerceAccountUserRel.isNew();

		if (!(commerceAccountUserRel instanceof
				CommerceAccountUserRelModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceAccountUserRel.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					commerceAccountUserRel);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceAccountUserRel proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceAccountUserRel implementation " +
					commerceAccountUserRel.getClass());
		}

		CommerceAccountUserRelModelImpl commerceAccountUserRelModelImpl =
			(CommerceAccountUserRelModelImpl)commerceAccountUserRel;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceAccountUserRel.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceAccountUserRel.setCreateDate(now);
			}
			else {
				commerceAccountUserRel.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!commerceAccountUserRelModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceAccountUserRel.setModifiedDate(now);
			}
			else {
				commerceAccountUserRel.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceAccountUserRel.isNew()) {
				session.save(commerceAccountUserRel);

				commerceAccountUserRel.setNew(false);
			}
			else {
				commerceAccountUserRel = (CommerceAccountUserRel)session.merge(
					commerceAccountUserRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceAccountUserRelModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				commerceAccountUserRelModelImpl.getCommerceAccountId()
			};

			finderCache.removeResult(_finderPathCountByCommerceAccountId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCommerceAccountId, args);

			args = new Object[] {
				commerceAccountUserRelModelImpl.getCommerceAccountUserId()
			};

			finderCache.removeResult(
				_finderPathCountByCommerceAccountUserId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCommerceAccountUserId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((commerceAccountUserRelModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCommerceAccountId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceAccountUserRelModelImpl.
						getOriginalCommerceAccountId()
				};

				finderCache.removeResult(
					_finderPathCountByCommerceAccountId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommerceAccountId, args);

				args = new Object[] {
					commerceAccountUserRelModelImpl.getCommerceAccountId()
				};

				finderCache.removeResult(
					_finderPathCountByCommerceAccountId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommerceAccountId, args);
			}

			if ((commerceAccountUserRelModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCommerceAccountUserId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceAccountUserRelModelImpl.
						getOriginalCommerceAccountUserId()
				};

				finderCache.removeResult(
					_finderPathCountByCommerceAccountUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommerceAccountUserId,
					args);

				args = new Object[] {
					commerceAccountUserRelModelImpl.getCommerceAccountUserId()
				};

				finderCache.removeResult(
					_finderPathCountByCommerceAccountUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCommerceAccountUserId,
					args);
			}
		}

		entityCache.putResult(
			CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountUserRelImpl.class,
			commerceAccountUserRel.getPrimaryKey(), commerceAccountUserRel,
			false);

		commerceAccountUserRel.resetOriginalValues();

		return commerceAccountUserRel;
	}

	/**
	 * Returns the commerce account user rel with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce account user rel
	 * @return the commerce account user rel
	 * @throws NoSuchAccountUserRelException if a commerce account user rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountUserRel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAccountUserRelException {

		CommerceAccountUserRel commerceAccountUserRel = fetchByPrimaryKey(
			primaryKey);

		if (commerceAccountUserRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccountUserRelException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commerceAccountUserRel;
	}

	/**
	 * Returns the commerce account user rel with the primary key or throws a <code>NoSuchAccountUserRelException</code> if it could not be found.
	 *
	 * @param commerceAccountUserRelPK the primary key of the commerce account user rel
	 * @return the commerce account user rel
	 * @throws NoSuchAccountUserRelException if a commerce account user rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountUserRel findByPrimaryKey(
			CommerceAccountUserRelPK commerceAccountUserRelPK)
		throws NoSuchAccountUserRelException {

		return findByPrimaryKey((Serializable)commerceAccountUserRelPK);
	}

	/**
	 * Returns the commerce account user rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce account user rel
	 * @return the commerce account user rel, or <code>null</code> if a commerce account user rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountUserRel fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountUserRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceAccountUserRel commerceAccountUserRel =
			(CommerceAccountUserRel)serializable;

		if (commerceAccountUserRel == null) {
			Session session = null;

			try {
				session = openSession();

				commerceAccountUserRel = (CommerceAccountUserRel)session.get(
					CommerceAccountUserRelImpl.class, primaryKey);

				if (commerceAccountUserRel != null) {
					cacheResult(commerceAccountUserRel);
				}
				else {
					entityCache.putResult(
						CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
						CommerceAccountUserRelImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
					CommerceAccountUserRelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceAccountUserRel;
	}

	/**
	 * Returns the commerce account user rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceAccountUserRelPK the primary key of the commerce account user rel
	 * @return the commerce account user rel, or <code>null</code> if a commerce account user rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountUserRel fetchByPrimaryKey(
		CommerceAccountUserRelPK commerceAccountUserRelPK) {

		return fetchByPrimaryKey((Serializable)commerceAccountUserRelPK);
	}

	@Override
	public Map<Serializable, CommerceAccountUserRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceAccountUserRel> map =
			new HashMap<Serializable, CommerceAccountUserRel>();

		for (Serializable primaryKey : primaryKeys) {
			CommerceAccountUserRel commerceAccountUserRel = fetchByPrimaryKey(
				primaryKey);

			if (commerceAccountUserRel != null) {
				map.put(primaryKey, commerceAccountUserRel);
			}
		}

		return map;
	}

	/**
	 * Returns all the commerce account user rels.
	 *
	 * @return the commerce account user rels
	 */
	@Override
	public List<CommerceAccountUserRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce account user rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountUserRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce account user rels
	 * @param end the upper bound of the range of commerce account user rels (not inclusive)
	 * @return the range of commerce account user rels
	 */
	@Override
	public List<CommerceAccountUserRel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce account user rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountUserRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce account user rels
	 * @param end the upper bound of the range of commerce account user rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce account user rels
	 */
	@Override
	public List<CommerceAccountUserRel> findAll(
		int start, int end,
		OrderByComparator<CommerceAccountUserRel> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce account user rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceAccountUserRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce account user rels
	 * @param end the upper bound of the range of commerce account user rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce account user rels
	 */
	@Override
	public List<CommerceAccountUserRel> findAll(
		int start, int end,
		OrderByComparator<CommerceAccountUserRel> orderByComparator,
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

		List<CommerceAccountUserRel> list = null;

		if (useFinderCache) {
			list = (List<CommerceAccountUserRel>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEACCOUNTUSERREL);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEACCOUNTUSERREL;

				if (pagination) {
					sql = sql.concat(
						CommerceAccountUserRelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceAccountUserRel>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceAccountUserRel>)QueryUtil.list(
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
	 * Removes all the commerce account user rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceAccountUserRel commerceAccountUserRel : findAll()) {
			remove(commerceAccountUserRel);
		}
	}

	/**
	 * Returns the number of commerce account user rels.
	 *
	 * @return the number of commerce account user rels
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
					_SQL_COUNT_COMMERCEACCOUNTUSERREL);

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
	public Set<String> getCompoundPKColumnNames() {
		return _compoundPKColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CommerceAccountUserRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce account user rel persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountUserRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountUserRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountUserRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountUserRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountUserRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByCommerceAccountId = new FinderPath(
			CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountUserRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountUserRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCommerceAccountId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCommerceAccountId = new FinderPath(
			CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountUserRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountUserRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCommerceAccountId", new String[] {Long.class.getName()},
			CommerceAccountUserRelModelImpl.COMMERCEACCOUNTID_COLUMN_BITMASK |
			CommerceAccountUserRelModelImpl.USERID_COLUMN_BITMASK);

		_finderPathCountByCommerceAccountId = new FinderPath(
			CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountUserRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceAccountId", new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByCommerceAccountUserId = new FinderPath(
			CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountUserRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountUserRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCommerceAccountUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCommerceAccountUserId =
			new FinderPath(
				CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
				CommerceAccountUserRelModelImpl.FINDER_CACHE_ENABLED,
				CommerceAccountUserRelImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByCommerceAccountUserId",
				new String[] {Long.class.getName()},
				CommerceAccountUserRelModelImpl.
					COMMERCEACCOUNTUSERID_COLUMN_BITMASK |
				CommerceAccountUserRelModelImpl.USERID_COLUMN_BITMASK);

		_finderPathCountByCommerceAccountUserId = new FinderPath(
			CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountUserRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceAccountUserId",
			new String[] {Long.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(CommerceAccountUserRelImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_COMMERCEACCOUNTUSERREL =
		"SELECT commerceAccountUserRel FROM CommerceAccountUserRel commerceAccountUserRel";

	private static final String _SQL_SELECT_COMMERCEACCOUNTUSERREL_WHERE =
		"SELECT commerceAccountUserRel FROM CommerceAccountUserRel commerceAccountUserRel WHERE ";

	private static final String _SQL_COUNT_COMMERCEACCOUNTUSERREL =
		"SELECT COUNT(commerceAccountUserRel) FROM CommerceAccountUserRel commerceAccountUserRel";

	private static final String _SQL_COUNT_COMMERCEACCOUNTUSERREL_WHERE =
		"SELECT COUNT(commerceAccountUserRel) FROM CommerceAccountUserRel commerceAccountUserRel WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"commerceAccountUserRel.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommerceAccountUserRel exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommerceAccountUserRel exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAccountUserRelPersistenceImpl.class);

	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(
		new String[] {"commerceAccountId", "commerceAccountUserId"});

}