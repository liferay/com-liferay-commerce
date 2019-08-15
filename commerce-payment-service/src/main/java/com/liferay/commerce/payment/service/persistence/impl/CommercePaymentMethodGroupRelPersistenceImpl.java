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

package com.liferay.commerce.payment.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.payment.exception.NoSuchPaymentMethodGroupRelException;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.model.impl.CommercePaymentMethodGroupRelImpl;
import com.liferay.commerce.payment.model.impl.CommercePaymentMethodGroupRelModelImpl;
import com.liferay.commerce.payment.service.persistence.CommercePaymentMethodGroupRelPersistence;
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
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the commerce payment method group rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @generated
 */
@ProviderType
public class CommercePaymentMethodGroupRelPersistenceImpl
	extends BasePersistenceImpl<CommercePaymentMethodGroupRel>
	implements CommercePaymentMethodGroupRelPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommercePaymentMethodGroupRelUtil</code> to access the commerce payment method group rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommercePaymentMethodGroupRelImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the commerce payment method group rels where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching commerce payment method group rels
	 */
	@Override
	public List<CommercePaymentMethodGroupRel> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce payment method group rels where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePaymentMethodGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce payment method group rels
	 * @param end the upper bound of the range of commerce payment method group rels (not inclusive)
	 * @return the range of matching commerce payment method group rels
	 */
	@Override
	public List<CommercePaymentMethodGroupRel> findByGroupId(
		long groupId, int start, int end) {

		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce payment method group rels where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePaymentMethodGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce payment method group rels
	 * @param end the upper bound of the range of commerce payment method group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce payment method group rels
	 */
	@Override
	public List<CommercePaymentMethodGroupRel> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<CommercePaymentMethodGroupRel> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce payment method group rels where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePaymentMethodGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of commerce payment method group rels
	 * @param end the upper bound of the range of commerce payment method group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce payment method group rels
	 */
	@Override
	public List<CommercePaymentMethodGroupRel> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<CommercePaymentMethodGroupRel> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByGroupId;
				finderArgs = new Object[] {groupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByGroupId;
			finderArgs = new Object[] {groupId, start, end, orderByComparator};
		}

		List<CommercePaymentMethodGroupRel> list = null;

		if (useFinderCache) {
			list = (List<CommercePaymentMethodGroupRel>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommercePaymentMethodGroupRel
						commercePaymentMethodGroupRel : list) {

					if ((groupId !=
							commercePaymentMethodGroupRel.getGroupId())) {

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

			query.append(_SQL_SELECT_COMMERCEPAYMENTMETHODGROUPREL_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(
					CommercePaymentMethodGroupRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<CommercePaymentMethodGroupRel>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommercePaymentMethodGroupRel>)QueryUtil.list(
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
	 * Returns the first commerce payment method group rel in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce payment method group rel
	 * @throws NoSuchPaymentMethodGroupRelException if a matching commerce payment method group rel could not be found
	 */
	@Override
	public CommercePaymentMethodGroupRel findByGroupId_First(
			long groupId,
			OrderByComparator<CommercePaymentMethodGroupRel> orderByComparator)
		throws NoSuchPaymentMethodGroupRelException {

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			fetchByGroupId_First(groupId, orderByComparator);

		if (commercePaymentMethodGroupRel != null) {
			return commercePaymentMethodGroupRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchPaymentMethodGroupRelException(msg.toString());
	}

	/**
	 * Returns the first commerce payment method group rel in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce payment method group rel, or <code>null</code> if a matching commerce payment method group rel could not be found
	 */
	@Override
	public CommercePaymentMethodGroupRel fetchByGroupId_First(
		long groupId,
		OrderByComparator<CommercePaymentMethodGroupRel> orderByComparator) {

		List<CommercePaymentMethodGroupRel> list = findByGroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce payment method group rel in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce payment method group rel
	 * @throws NoSuchPaymentMethodGroupRelException if a matching commerce payment method group rel could not be found
	 */
	@Override
	public CommercePaymentMethodGroupRel findByGroupId_Last(
			long groupId,
			OrderByComparator<CommercePaymentMethodGroupRel> orderByComparator)
		throws NoSuchPaymentMethodGroupRelException {

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			fetchByGroupId_Last(groupId, orderByComparator);

		if (commercePaymentMethodGroupRel != null) {
			return commercePaymentMethodGroupRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchPaymentMethodGroupRelException(msg.toString());
	}

	/**
	 * Returns the last commerce payment method group rel in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce payment method group rel, or <code>null</code> if a matching commerce payment method group rel could not be found
	 */
	@Override
	public CommercePaymentMethodGroupRel fetchByGroupId_Last(
		long groupId,
		OrderByComparator<CommercePaymentMethodGroupRel> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<CommercePaymentMethodGroupRel> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce payment method group rels before and after the current commerce payment method group rel in the ordered set where groupId = &#63;.
	 *
	 * @param commercePaymentMethodGroupRelId the primary key of the current commerce payment method group rel
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce payment method group rel
	 * @throws NoSuchPaymentMethodGroupRelException if a commerce payment method group rel with the primary key could not be found
	 */
	@Override
	public CommercePaymentMethodGroupRel[] findByGroupId_PrevAndNext(
			long commercePaymentMethodGroupRelId, long groupId,
			OrderByComparator<CommercePaymentMethodGroupRel> orderByComparator)
		throws NoSuchPaymentMethodGroupRelException {

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			findByPrimaryKey(commercePaymentMethodGroupRelId);

		Session session = null;

		try {
			session = openSession();

			CommercePaymentMethodGroupRel[] array =
				new CommercePaymentMethodGroupRelImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, commercePaymentMethodGroupRel, groupId,
				orderByComparator, true);

			array[1] = commercePaymentMethodGroupRel;

			array[2] = getByGroupId_PrevAndNext(
				session, commercePaymentMethodGroupRel, groupId,
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

	protected CommercePaymentMethodGroupRel getByGroupId_PrevAndNext(
		Session session,
		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel,
		long groupId,
		OrderByComparator<CommercePaymentMethodGroupRel> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEPAYMENTMETHODGROUPREL_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			query.append(CommercePaymentMethodGroupRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commercePaymentMethodGroupRel)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommercePaymentMethodGroupRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce payment method group rels where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (CommercePaymentMethodGroupRel commercePaymentMethodGroupRel :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commercePaymentMethodGroupRel);
		}
	}

	/**
	 * Returns the number of commerce payment method group rels where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching commerce payment method group rels
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEPAYMENTMETHODGROUPREL_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 =
		"commercePaymentMethodGroupRel.groupId = ?";

	private FinderPath _finderPathFetchByG_E;
	private FinderPath _finderPathCountByG_E;

	/**
	 * Returns the commerce payment method group rel where groupId = &#63; and engineKey = &#63; or throws a <code>NoSuchPaymentMethodGroupRelException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param engineKey the engine key
	 * @return the matching commerce payment method group rel
	 * @throws NoSuchPaymentMethodGroupRelException if a matching commerce payment method group rel could not be found
	 */
	@Override
	public CommercePaymentMethodGroupRel findByG_E(
			long groupId, String engineKey)
		throws NoSuchPaymentMethodGroupRelException {

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			fetchByG_E(groupId, engineKey);

		if (commercePaymentMethodGroupRel == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", engineKey=");
			msg.append(engineKey);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchPaymentMethodGroupRelException(msg.toString());
		}

		return commercePaymentMethodGroupRel;
	}

	/**
	 * Returns the commerce payment method group rel where groupId = &#63; and engineKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param engineKey the engine key
	 * @return the matching commerce payment method group rel, or <code>null</code> if a matching commerce payment method group rel could not be found
	 */
	@Override
	public CommercePaymentMethodGroupRel fetchByG_E(
		long groupId, String engineKey) {

		return fetchByG_E(groupId, engineKey, true);
	}

	/**
	 * Returns the commerce payment method group rel where groupId = &#63; and engineKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param engineKey the engine key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce payment method group rel, or <code>null</code> if a matching commerce payment method group rel could not be found
	 */
	@Override
	public CommercePaymentMethodGroupRel fetchByG_E(
		long groupId, String engineKey, boolean useFinderCache) {

		engineKey = Objects.toString(engineKey, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {groupId, engineKey};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByG_E, finderArgs, this);
		}

		if (result instanceof CommercePaymentMethodGroupRel) {
			CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
				(CommercePaymentMethodGroupRel)result;

			if ((groupId != commercePaymentMethodGroupRel.getGroupId()) ||
				!Objects.equals(
					engineKey, commercePaymentMethodGroupRel.getEngineKey())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COMMERCEPAYMENTMETHODGROUPREL_WHERE);

			query.append(_FINDER_COLUMN_G_E_GROUPID_2);

			boolean bindEngineKey = false;

			if (engineKey.isEmpty()) {
				query.append(_FINDER_COLUMN_G_E_ENGINEKEY_3);
			}
			else {
				bindEngineKey = true;

				query.append(_FINDER_COLUMN_G_E_ENGINEKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindEngineKey) {
					qPos.add(engineKey);
				}

				List<CommercePaymentMethodGroupRel> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByG_E, finderArgs, list);
					}
				}
				else {
					CommercePaymentMethodGroupRel
						commercePaymentMethodGroupRel = list.get(0);

					result = commercePaymentMethodGroupRel;

					cacheResult(commercePaymentMethodGroupRel);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(_finderPathFetchByG_E, finderArgs);
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
			return (CommercePaymentMethodGroupRel)result;
		}
	}

	/**
	 * Removes the commerce payment method group rel where groupId = &#63; and engineKey = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param engineKey the engine key
	 * @return the commerce payment method group rel that was removed
	 */
	@Override
	public CommercePaymentMethodGroupRel removeByG_E(
			long groupId, String engineKey)
		throws NoSuchPaymentMethodGroupRelException {

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel = findByG_E(
			groupId, engineKey);

		return remove(commercePaymentMethodGroupRel);
	}

	/**
	 * Returns the number of commerce payment method group rels where groupId = &#63; and engineKey = &#63;.
	 *
	 * @param groupId the group ID
	 * @param engineKey the engine key
	 * @return the number of matching commerce payment method group rels
	 */
	@Override
	public int countByG_E(long groupId, String engineKey) {
		engineKey = Objects.toString(engineKey, "");

		FinderPath finderPath = _finderPathCountByG_E;

		Object[] finderArgs = new Object[] {groupId, engineKey};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEPAYMENTMETHODGROUPREL_WHERE);

			query.append(_FINDER_COLUMN_G_E_GROUPID_2);

			boolean bindEngineKey = false;

			if (engineKey.isEmpty()) {
				query.append(_FINDER_COLUMN_G_E_ENGINEKEY_3);
			}
			else {
				bindEngineKey = true;

				query.append(_FINDER_COLUMN_G_E_ENGINEKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindEngineKey) {
					qPos.add(engineKey);
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

	private static final String _FINDER_COLUMN_G_E_GROUPID_2 =
		"commercePaymentMethodGroupRel.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_E_ENGINEKEY_2 =
		"commercePaymentMethodGroupRel.engineKey = ?";

	private static final String _FINDER_COLUMN_G_E_ENGINEKEY_3 =
		"(commercePaymentMethodGroupRel.engineKey IS NULL OR commercePaymentMethodGroupRel.engineKey = '')";

	private FinderPath _finderPathWithPaginationFindByG_A;
	private FinderPath _finderPathWithoutPaginationFindByG_A;
	private FinderPath _finderPathCountByG_A;

	/**
	 * Returns all the commerce payment method group rels where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @return the matching commerce payment method group rels
	 */
	@Override
	public List<CommercePaymentMethodGroupRel> findByG_A(
		long groupId, boolean active) {

		return findByG_A(
			groupId, active, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce payment method group rels where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePaymentMethodGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of commerce payment method group rels
	 * @param end the upper bound of the range of commerce payment method group rels (not inclusive)
	 * @return the range of matching commerce payment method group rels
	 */
	@Override
	public List<CommercePaymentMethodGroupRel> findByG_A(
		long groupId, boolean active, int start, int end) {

		return findByG_A(groupId, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce payment method group rels where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePaymentMethodGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of commerce payment method group rels
	 * @param end the upper bound of the range of commerce payment method group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce payment method group rels
	 */
	@Override
	public List<CommercePaymentMethodGroupRel> findByG_A(
		long groupId, boolean active, int start, int end,
		OrderByComparator<CommercePaymentMethodGroupRel> orderByComparator) {

		return findByG_A(groupId, active, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce payment method group rels where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePaymentMethodGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of commerce payment method group rels
	 * @param end the upper bound of the range of commerce payment method group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce payment method group rels
	 */
	@Override
	public List<CommercePaymentMethodGroupRel> findByG_A(
		long groupId, boolean active, int start, int end,
		OrderByComparator<CommercePaymentMethodGroupRel> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByG_A;
				finderArgs = new Object[] {groupId, active};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByG_A;
			finderArgs = new Object[] {
				groupId, active, start, end, orderByComparator
			};
		}

		List<CommercePaymentMethodGroupRel> list = null;

		if (useFinderCache) {
			list = (List<CommercePaymentMethodGroupRel>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommercePaymentMethodGroupRel
						commercePaymentMethodGroupRel : list) {

					if ((groupId !=
							commercePaymentMethodGroupRel.getGroupId()) ||
						(active != commercePaymentMethodGroupRel.isActive())) {

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

			query.append(_SQL_SELECT_COMMERCEPAYMENTMETHODGROUPREL_WHERE);

			query.append(_FINDER_COLUMN_G_A_GROUPID_2);

			query.append(_FINDER_COLUMN_G_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(
					CommercePaymentMethodGroupRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(active);

				if (!pagination) {
					list = (List<CommercePaymentMethodGroupRel>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommercePaymentMethodGroupRel>)QueryUtil.list(
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
	 * Returns the first commerce payment method group rel in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce payment method group rel
	 * @throws NoSuchPaymentMethodGroupRelException if a matching commerce payment method group rel could not be found
	 */
	@Override
	public CommercePaymentMethodGroupRel findByG_A_First(
			long groupId, boolean active,
			OrderByComparator<CommercePaymentMethodGroupRel> orderByComparator)
		throws NoSuchPaymentMethodGroupRelException {

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			fetchByG_A_First(groupId, active, orderByComparator);

		if (commercePaymentMethodGroupRel != null) {
			return commercePaymentMethodGroupRel;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", active=");
		msg.append(active);

		msg.append("}");

		throw new NoSuchPaymentMethodGroupRelException(msg.toString());
	}

	/**
	 * Returns the first commerce payment method group rel in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce payment method group rel, or <code>null</code> if a matching commerce payment method group rel could not be found
	 */
	@Override
	public CommercePaymentMethodGroupRel fetchByG_A_First(
		long groupId, boolean active,
		OrderByComparator<CommercePaymentMethodGroupRel> orderByComparator) {

		List<CommercePaymentMethodGroupRel> list = findByG_A(
			groupId, active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce payment method group rel in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce payment method group rel
	 * @throws NoSuchPaymentMethodGroupRelException if a matching commerce payment method group rel could not be found
	 */
	@Override
	public CommercePaymentMethodGroupRel findByG_A_Last(
			long groupId, boolean active,
			OrderByComparator<CommercePaymentMethodGroupRel> orderByComparator)
		throws NoSuchPaymentMethodGroupRelException {

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			fetchByG_A_Last(groupId, active, orderByComparator);

		if (commercePaymentMethodGroupRel != null) {
			return commercePaymentMethodGroupRel;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", active=");
		msg.append(active);

		msg.append("}");

		throw new NoSuchPaymentMethodGroupRelException(msg.toString());
	}

	/**
	 * Returns the last commerce payment method group rel in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce payment method group rel, or <code>null</code> if a matching commerce payment method group rel could not be found
	 */
	@Override
	public CommercePaymentMethodGroupRel fetchByG_A_Last(
		long groupId, boolean active,
		OrderByComparator<CommercePaymentMethodGroupRel> orderByComparator) {

		int count = countByG_A(groupId, active);

		if (count == 0) {
			return null;
		}

		List<CommercePaymentMethodGroupRel> list = findByG_A(
			groupId, active, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce payment method group rels before and after the current commerce payment method group rel in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param commercePaymentMethodGroupRelId the primary key of the current commerce payment method group rel
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce payment method group rel
	 * @throws NoSuchPaymentMethodGroupRelException if a commerce payment method group rel with the primary key could not be found
	 */
	@Override
	public CommercePaymentMethodGroupRel[] findByG_A_PrevAndNext(
			long commercePaymentMethodGroupRelId, long groupId, boolean active,
			OrderByComparator<CommercePaymentMethodGroupRel> orderByComparator)
		throws NoSuchPaymentMethodGroupRelException {

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			findByPrimaryKey(commercePaymentMethodGroupRelId);

		Session session = null;

		try {
			session = openSession();

			CommercePaymentMethodGroupRel[] array =
				new CommercePaymentMethodGroupRelImpl[3];

			array[0] = getByG_A_PrevAndNext(
				session, commercePaymentMethodGroupRel, groupId, active,
				orderByComparator, true);

			array[1] = commercePaymentMethodGroupRel;

			array[2] = getByG_A_PrevAndNext(
				session, commercePaymentMethodGroupRel, groupId, active,
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

	protected CommercePaymentMethodGroupRel getByG_A_PrevAndNext(
		Session session,
		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel,
		long groupId, boolean active,
		OrderByComparator<CommercePaymentMethodGroupRel> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEPAYMENTMETHODGROUPREL_WHERE);

		query.append(_FINDER_COLUMN_G_A_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_ACTIVE_2);

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
			query.append(CommercePaymentMethodGroupRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(active);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commercePaymentMethodGroupRel)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommercePaymentMethodGroupRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce payment method group rels where groupId = &#63; and active = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 */
	@Override
	public void removeByG_A(long groupId, boolean active) {
		for (CommercePaymentMethodGroupRel commercePaymentMethodGroupRel :
				findByG_A(
					groupId, active, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commercePaymentMethodGroupRel);
		}
	}

	/**
	 * Returns the number of commerce payment method group rels where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @return the number of matching commerce payment method group rels
	 */
	@Override
	public int countByG_A(long groupId, boolean active) {
		FinderPath finderPath = _finderPathCountByG_A;

		Object[] finderArgs = new Object[] {groupId, active};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEPAYMENTMETHODGROUPREL_WHERE);

			query.append(_FINDER_COLUMN_G_A_GROUPID_2);

			query.append(_FINDER_COLUMN_G_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(active);

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

	private static final String _FINDER_COLUMN_G_A_GROUPID_2 =
		"commercePaymentMethodGroupRel.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_A_ACTIVE_2 =
		"commercePaymentMethodGroupRel.active = ?";

	public CommercePaymentMethodGroupRelPersistenceImpl() {
		setModelClass(CommercePaymentMethodGroupRel.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put(
			"commercePaymentMethodGroupRelId", "CPaymentMethodGroupRelId");
		dbColumnNames.put("active", "active_");

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
	 * Caches the commerce payment method group rel in the entity cache if it is enabled.
	 *
	 * @param commercePaymentMethodGroupRel the commerce payment method group rel
	 */
	@Override
	public void cacheResult(
		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel) {

		entityCache.putResult(
			CommercePaymentMethodGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePaymentMethodGroupRelImpl.class,
			commercePaymentMethodGroupRel.getPrimaryKey(),
			commercePaymentMethodGroupRel);

		finderCache.putResult(
			_finderPathFetchByG_E,
			new Object[] {
				commercePaymentMethodGroupRel.getGroupId(),
				commercePaymentMethodGroupRel.getEngineKey()
			},
			commercePaymentMethodGroupRel);

		commercePaymentMethodGroupRel.resetOriginalValues();
	}

	/**
	 * Caches the commerce payment method group rels in the entity cache if it is enabled.
	 *
	 * @param commercePaymentMethodGroupRels the commerce payment method group rels
	 */
	@Override
	public void cacheResult(
		List<CommercePaymentMethodGroupRel> commercePaymentMethodGroupRels) {

		for (CommercePaymentMethodGroupRel commercePaymentMethodGroupRel :
				commercePaymentMethodGroupRels) {

			if (entityCache.getResult(
					CommercePaymentMethodGroupRelModelImpl.ENTITY_CACHE_ENABLED,
					CommercePaymentMethodGroupRelImpl.class,
					commercePaymentMethodGroupRel.getPrimaryKey()) == null) {

				cacheResult(commercePaymentMethodGroupRel);
			}
			else {
				commercePaymentMethodGroupRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce payment method group rels.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommercePaymentMethodGroupRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce payment method group rel.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel) {

		entityCache.removeResult(
			CommercePaymentMethodGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePaymentMethodGroupRelImpl.class,
			commercePaymentMethodGroupRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(CommercePaymentMethodGroupRelModelImpl)
				commercePaymentMethodGroupRel,
			true);
	}

	@Override
	public void clearCache(
		List<CommercePaymentMethodGroupRel> commercePaymentMethodGroupRels) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommercePaymentMethodGroupRel commercePaymentMethodGroupRel :
				commercePaymentMethodGroupRels) {

			entityCache.removeResult(
				CommercePaymentMethodGroupRelModelImpl.ENTITY_CACHE_ENABLED,
				CommercePaymentMethodGroupRelImpl.class,
				commercePaymentMethodGroupRel.getPrimaryKey());

			clearUniqueFindersCache(
				(CommercePaymentMethodGroupRelModelImpl)
					commercePaymentMethodGroupRel,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		CommercePaymentMethodGroupRelModelImpl
			commercePaymentMethodGroupRelModelImpl) {

		Object[] args = new Object[] {
			commercePaymentMethodGroupRelModelImpl.getGroupId(),
			commercePaymentMethodGroupRelModelImpl.getEngineKey()
		};

		finderCache.putResult(
			_finderPathCountByG_E, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByG_E, args, commercePaymentMethodGroupRelModelImpl,
			false);
	}

	protected void clearUniqueFindersCache(
		CommercePaymentMethodGroupRelModelImpl
			commercePaymentMethodGroupRelModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				commercePaymentMethodGroupRelModelImpl.getGroupId(),
				commercePaymentMethodGroupRelModelImpl.getEngineKey()
			};

			finderCache.removeResult(_finderPathCountByG_E, args);
			finderCache.removeResult(_finderPathFetchByG_E, args);
		}

		if ((commercePaymentMethodGroupRelModelImpl.getColumnBitmask() &
			 _finderPathFetchByG_E.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				commercePaymentMethodGroupRelModelImpl.getOriginalGroupId(),
				commercePaymentMethodGroupRelModelImpl.getOriginalEngineKey()
			};

			finderCache.removeResult(_finderPathCountByG_E, args);
			finderCache.removeResult(_finderPathFetchByG_E, args);
		}
	}

	/**
	 * Creates a new commerce payment method group rel with the primary key. Does not add the commerce payment method group rel to the database.
	 *
	 * @param commercePaymentMethodGroupRelId the primary key for the new commerce payment method group rel
	 * @return the new commerce payment method group rel
	 */
	@Override
	public CommercePaymentMethodGroupRel create(
		long commercePaymentMethodGroupRelId) {

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			new CommercePaymentMethodGroupRelImpl();

		commercePaymentMethodGroupRel.setNew(true);
		commercePaymentMethodGroupRel.setPrimaryKey(
			commercePaymentMethodGroupRelId);

		commercePaymentMethodGroupRel.setCompanyId(
			CompanyThreadLocal.getCompanyId());

		return commercePaymentMethodGroupRel;
	}

	/**
	 * Removes the commerce payment method group rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commercePaymentMethodGroupRelId the primary key of the commerce payment method group rel
	 * @return the commerce payment method group rel that was removed
	 * @throws NoSuchPaymentMethodGroupRelException if a commerce payment method group rel with the primary key could not be found
	 */
	@Override
	public CommercePaymentMethodGroupRel remove(
			long commercePaymentMethodGroupRelId)
		throws NoSuchPaymentMethodGroupRelException {

		return remove((Serializable)commercePaymentMethodGroupRelId);
	}

	/**
	 * Removes the commerce payment method group rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce payment method group rel
	 * @return the commerce payment method group rel that was removed
	 * @throws NoSuchPaymentMethodGroupRelException if a commerce payment method group rel with the primary key could not be found
	 */
	@Override
	public CommercePaymentMethodGroupRel remove(Serializable primaryKey)
		throws NoSuchPaymentMethodGroupRelException {

		Session session = null;

		try {
			session = openSession();

			CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
				(CommercePaymentMethodGroupRel)session.get(
					CommercePaymentMethodGroupRelImpl.class, primaryKey);

			if (commercePaymentMethodGroupRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPaymentMethodGroupRelException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commercePaymentMethodGroupRel);
		}
		catch (NoSuchPaymentMethodGroupRelException nsee) {
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
	protected CommercePaymentMethodGroupRel removeImpl(
		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commercePaymentMethodGroupRel)) {
				commercePaymentMethodGroupRel =
					(CommercePaymentMethodGroupRel)session.get(
						CommercePaymentMethodGroupRelImpl.class,
						commercePaymentMethodGroupRel.getPrimaryKeyObj());
			}

			if (commercePaymentMethodGroupRel != null) {
				session.delete(commercePaymentMethodGroupRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commercePaymentMethodGroupRel != null) {
			clearCache(commercePaymentMethodGroupRel);
		}

		return commercePaymentMethodGroupRel;
	}

	@Override
	public CommercePaymentMethodGroupRel updateImpl(
		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel) {

		boolean isNew = commercePaymentMethodGroupRel.isNew();

		if (!(commercePaymentMethodGroupRel instanceof
				CommercePaymentMethodGroupRelModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(
					commercePaymentMethodGroupRel.getClass())) {

				invocationHandler = ProxyUtil.getInvocationHandler(
					commercePaymentMethodGroupRel);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commercePaymentMethodGroupRel proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommercePaymentMethodGroupRel implementation " +
					commercePaymentMethodGroupRel.getClass());
		}

		CommercePaymentMethodGroupRelModelImpl
			commercePaymentMethodGroupRelModelImpl =
				(CommercePaymentMethodGroupRelModelImpl)
					commercePaymentMethodGroupRel;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commercePaymentMethodGroupRel.getCreateDate() == null)) {
			if (serviceContext == null) {
				commercePaymentMethodGroupRel.setCreateDate(now);
			}
			else {
				commercePaymentMethodGroupRel.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!commercePaymentMethodGroupRelModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commercePaymentMethodGroupRel.setModifiedDate(now);
			}
			else {
				commercePaymentMethodGroupRel.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commercePaymentMethodGroupRel.isNew()) {
				session.save(commercePaymentMethodGroupRel);

				commercePaymentMethodGroupRel.setNew(false);
			}
			else {
				commercePaymentMethodGroupRel =
					(CommercePaymentMethodGroupRel)session.merge(
						commercePaymentMethodGroupRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommercePaymentMethodGroupRelModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				commercePaymentMethodGroupRelModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByGroupId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByGroupId, args);

			args = new Object[] {
				commercePaymentMethodGroupRelModelImpl.getGroupId(),
				commercePaymentMethodGroupRelModelImpl.isActive()
			};

			finderCache.removeResult(_finderPathCountByG_A, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByG_A, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((commercePaymentMethodGroupRelModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByGroupId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commercePaymentMethodGroupRelModelImpl.getOriginalGroupId()
				};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);

				args = new Object[] {
					commercePaymentMethodGroupRelModelImpl.getGroupId()
				};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);
			}

			if ((commercePaymentMethodGroupRelModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByG_A.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commercePaymentMethodGroupRelModelImpl.getOriginalGroupId(),
					commercePaymentMethodGroupRelModelImpl.getOriginalActive()
				};

				finderCache.removeResult(_finderPathCountByG_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_A, args);

				args = new Object[] {
					commercePaymentMethodGroupRelModelImpl.getGroupId(),
					commercePaymentMethodGroupRelModelImpl.isActive()
				};

				finderCache.removeResult(_finderPathCountByG_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByG_A, args);
			}
		}

		entityCache.putResult(
			CommercePaymentMethodGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePaymentMethodGroupRelImpl.class,
			commercePaymentMethodGroupRel.getPrimaryKey(),
			commercePaymentMethodGroupRel, false);

		clearUniqueFindersCache(commercePaymentMethodGroupRelModelImpl, false);
		cacheUniqueFindersCache(commercePaymentMethodGroupRelModelImpl);

		commercePaymentMethodGroupRel.resetOriginalValues();

		return commercePaymentMethodGroupRel;
	}

	/**
	 * Returns the commerce payment method group rel with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce payment method group rel
	 * @return the commerce payment method group rel
	 * @throws NoSuchPaymentMethodGroupRelException if a commerce payment method group rel with the primary key could not be found
	 */
	@Override
	public CommercePaymentMethodGroupRel findByPrimaryKey(
			Serializable primaryKey)
		throws NoSuchPaymentMethodGroupRelException {

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			fetchByPrimaryKey(primaryKey);

		if (commercePaymentMethodGroupRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPaymentMethodGroupRelException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commercePaymentMethodGroupRel;
	}

	/**
	 * Returns the commerce payment method group rel with the primary key or throws a <code>NoSuchPaymentMethodGroupRelException</code> if it could not be found.
	 *
	 * @param commercePaymentMethodGroupRelId the primary key of the commerce payment method group rel
	 * @return the commerce payment method group rel
	 * @throws NoSuchPaymentMethodGroupRelException if a commerce payment method group rel with the primary key could not be found
	 */
	@Override
	public CommercePaymentMethodGroupRel findByPrimaryKey(
			long commercePaymentMethodGroupRelId)
		throws NoSuchPaymentMethodGroupRelException {

		return findByPrimaryKey((Serializable)commercePaymentMethodGroupRelId);
	}

	/**
	 * Returns the commerce payment method group rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce payment method group rel
	 * @return the commerce payment method group rel, or <code>null</code> if a commerce payment method group rel with the primary key could not be found
	 */
	@Override
	public CommercePaymentMethodGroupRel fetchByPrimaryKey(
		Serializable primaryKey) {

		Serializable serializable = entityCache.getResult(
			CommercePaymentMethodGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePaymentMethodGroupRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			(CommercePaymentMethodGroupRel)serializable;

		if (commercePaymentMethodGroupRel == null) {
			Session session = null;

			try {
				session = openSession();

				commercePaymentMethodGroupRel =
					(CommercePaymentMethodGroupRel)session.get(
						CommercePaymentMethodGroupRelImpl.class, primaryKey);

				if (commercePaymentMethodGroupRel != null) {
					cacheResult(commercePaymentMethodGroupRel);
				}
				else {
					entityCache.putResult(
						CommercePaymentMethodGroupRelModelImpl.
							ENTITY_CACHE_ENABLED,
						CommercePaymentMethodGroupRelImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CommercePaymentMethodGroupRelModelImpl.ENTITY_CACHE_ENABLED,
					CommercePaymentMethodGroupRelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commercePaymentMethodGroupRel;
	}

	/**
	 * Returns the commerce payment method group rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commercePaymentMethodGroupRelId the primary key of the commerce payment method group rel
	 * @return the commerce payment method group rel, or <code>null</code> if a commerce payment method group rel with the primary key could not be found
	 */
	@Override
	public CommercePaymentMethodGroupRel fetchByPrimaryKey(
		long commercePaymentMethodGroupRelId) {

		return fetchByPrimaryKey((Serializable)commercePaymentMethodGroupRelId);
	}

	@Override
	public Map<Serializable, CommercePaymentMethodGroupRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommercePaymentMethodGroupRel> map =
			new HashMap<Serializable, CommercePaymentMethodGroupRel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
				fetchByPrimaryKey(primaryKey);

			if (commercePaymentMethodGroupRel != null) {
				map.put(primaryKey, commercePaymentMethodGroupRel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CommercePaymentMethodGroupRelModelImpl.ENTITY_CACHE_ENABLED,
				CommercePaymentMethodGroupRelImpl.class, primaryKey);

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
						(CommercePaymentMethodGroupRel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_COMMERCEPAYMENTMETHODGROUPREL_WHERE_PKS_IN);

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

			for (CommercePaymentMethodGroupRel commercePaymentMethodGroupRel :
					(List<CommercePaymentMethodGroupRel>)q.list()) {

				map.put(
					commercePaymentMethodGroupRel.getPrimaryKeyObj(),
					commercePaymentMethodGroupRel);

				cacheResult(commercePaymentMethodGroupRel);

				uncachedPrimaryKeys.remove(
					commercePaymentMethodGroupRel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CommercePaymentMethodGroupRelModelImpl.ENTITY_CACHE_ENABLED,
					CommercePaymentMethodGroupRelImpl.class, primaryKey,
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
	 * Returns all the commerce payment method group rels.
	 *
	 * @return the commerce payment method group rels
	 */
	@Override
	public List<CommercePaymentMethodGroupRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce payment method group rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePaymentMethodGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce payment method group rels
	 * @param end the upper bound of the range of commerce payment method group rels (not inclusive)
	 * @return the range of commerce payment method group rels
	 */
	@Override
	public List<CommercePaymentMethodGroupRel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce payment method group rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePaymentMethodGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce payment method group rels
	 * @param end the upper bound of the range of commerce payment method group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce payment method group rels
	 */
	@Override
	public List<CommercePaymentMethodGroupRel> findAll(
		int start, int end,
		OrderByComparator<CommercePaymentMethodGroupRel> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce payment method group rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommercePaymentMethodGroupRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce payment method group rels
	 * @param end the upper bound of the range of commerce payment method group rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce payment method group rels
	 */
	@Override
	public List<CommercePaymentMethodGroupRel> findAll(
		int start, int end,
		OrderByComparator<CommercePaymentMethodGroupRel> orderByComparator,
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

		List<CommercePaymentMethodGroupRel> list = null;

		if (useFinderCache) {
			list = (List<CommercePaymentMethodGroupRel>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEPAYMENTMETHODGROUPREL);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEPAYMENTMETHODGROUPREL;

				if (pagination) {
					sql = sql.concat(
						CommercePaymentMethodGroupRelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommercePaymentMethodGroupRel>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommercePaymentMethodGroupRel>)QueryUtil.list(
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
	 * Removes all the commerce payment method group rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommercePaymentMethodGroupRel commercePaymentMethodGroupRel :
				findAll()) {

			remove(commercePaymentMethodGroupRel);
		}
	}

	/**
	 * Returns the number of commerce payment method group rels.
	 *
	 * @return the number of commerce payment method group rels
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
					_SQL_COUNT_COMMERCEPAYMENTMETHODGROUPREL);

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
		return CommercePaymentMethodGroupRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce payment method group rel persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CommercePaymentMethodGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePaymentMethodGroupRelModelImpl.FINDER_CACHE_ENABLED,
			CommercePaymentMethodGroupRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CommercePaymentMethodGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePaymentMethodGroupRelModelImpl.FINDER_CACHE_ENABLED,
			CommercePaymentMethodGroupRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CommercePaymentMethodGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePaymentMethodGroupRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			CommercePaymentMethodGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePaymentMethodGroupRelModelImpl.FINDER_CACHE_ENABLED,
			CommercePaymentMethodGroupRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			CommercePaymentMethodGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePaymentMethodGroupRelModelImpl.FINDER_CACHE_ENABLED,
			CommercePaymentMethodGroupRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()},
			CommercePaymentMethodGroupRelModelImpl.GROUPID_COLUMN_BITMASK |
			CommercePaymentMethodGroupRelModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByGroupId = new FinderPath(
			CommercePaymentMethodGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePaymentMethodGroupRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupId", new String[] {Long.class.getName()});

		_finderPathFetchByG_E = new FinderPath(
			CommercePaymentMethodGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePaymentMethodGroupRelModelImpl.FINDER_CACHE_ENABLED,
			CommercePaymentMethodGroupRelImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_E",
			new String[] {Long.class.getName(), String.class.getName()},
			CommercePaymentMethodGroupRelModelImpl.GROUPID_COLUMN_BITMASK |
			CommercePaymentMethodGroupRelModelImpl.ENGINEKEY_COLUMN_BITMASK);

		_finderPathCountByG_E = new FinderPath(
			CommercePaymentMethodGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePaymentMethodGroupRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_E",
			new String[] {Long.class.getName(), String.class.getName()});

		_finderPathWithPaginationFindByG_A = new FinderPath(
			CommercePaymentMethodGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePaymentMethodGroupRelModelImpl.FINDER_CACHE_ENABLED,
			CommercePaymentMethodGroupRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByG_A = new FinderPath(
			CommercePaymentMethodGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePaymentMethodGroupRelModelImpl.FINDER_CACHE_ENABLED,
			CommercePaymentMethodGroupRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_A",
			new String[] {Long.class.getName(), Boolean.class.getName()},
			CommercePaymentMethodGroupRelModelImpl.GROUPID_COLUMN_BITMASK |
			CommercePaymentMethodGroupRelModelImpl.ACTIVE_COLUMN_BITMASK |
			CommercePaymentMethodGroupRelModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByG_A = new FinderPath(
			CommercePaymentMethodGroupRelModelImpl.ENTITY_CACHE_ENABLED,
			CommercePaymentMethodGroupRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_A",
			new String[] {Long.class.getName(), Boolean.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(
			CommercePaymentMethodGroupRelImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_COMMERCEPAYMENTMETHODGROUPREL =
		"SELECT commercePaymentMethodGroupRel FROM CommercePaymentMethodGroupRel commercePaymentMethodGroupRel";

	private static final String
		_SQL_SELECT_COMMERCEPAYMENTMETHODGROUPREL_WHERE_PKS_IN =
			"SELECT commercePaymentMethodGroupRel FROM CommercePaymentMethodGroupRel commercePaymentMethodGroupRel WHERE CPaymentMethodGroupRelId IN (";

	private static final String
		_SQL_SELECT_COMMERCEPAYMENTMETHODGROUPREL_WHERE =
			"SELECT commercePaymentMethodGroupRel FROM CommercePaymentMethodGroupRel commercePaymentMethodGroupRel WHERE ";

	private static final String _SQL_COUNT_COMMERCEPAYMENTMETHODGROUPREL =
		"SELECT COUNT(commercePaymentMethodGroupRel) FROM CommercePaymentMethodGroupRel commercePaymentMethodGroupRel";

	private static final String _SQL_COUNT_COMMERCEPAYMENTMETHODGROUPREL_WHERE =
		"SELECT COUNT(commercePaymentMethodGroupRel) FROM CommercePaymentMethodGroupRel commercePaymentMethodGroupRel WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"commercePaymentMethodGroupRel.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommercePaymentMethodGroupRel exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommercePaymentMethodGroupRel exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommercePaymentMethodGroupRelPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"commercePaymentMethodGroupRelId", "active"});

}