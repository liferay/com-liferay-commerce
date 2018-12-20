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
 * @see CommerceAccountUserRelPersistence
 * @see com.liferay.commerce.account.service.persistence.CommerceAccountUserRelUtil
 * @generated
 */
@ProviderType
public class CommerceAccountUserRelPersistenceImpl extends BasePersistenceImpl<CommerceAccountUserRel>
	implements CommerceAccountUserRelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommerceAccountUserRelUtil} to access the commerce account user rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommerceAccountUserRelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountUserRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountUserRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountUserRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountUserRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountUserRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountUserRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountUserRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByuserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountUserRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountUserRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByuserId",
			new String[] { Long.class.getName() },
			CommerceAccountUserRelModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountUserRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByuserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the commerce account user rels where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching commerce account user rels
	 */
	@Override
	public List<CommerceAccountUserRel> findByuserId(long userId) {
		return findByuserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce account user rels where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of commerce account user rels
	 * @param end the upper bound of the range of commerce account user rels (not inclusive)
	 * @return the range of matching commerce account user rels
	 */
	@Override
	public List<CommerceAccountUserRel> findByuserId(long userId, int start,
		int end) {
		return findByuserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce account user rels where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of commerce account user rels
	 * @param end the upper bound of the range of commerce account user rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce account user rels
	 */
	@Override
	public List<CommerceAccountUserRel> findByuserId(long userId, int start,
		int end, OrderByComparator<CommerceAccountUserRel> orderByComparator) {
		return findByuserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce account user rels where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of commerce account user rels
	 * @param end the upper bound of the range of commerce account user rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce account user rels
	 */
	@Override
	public List<CommerceAccountUserRel> findByuserId(long userId, int start,
		int end, OrderByComparator<CommerceAccountUserRel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<CommerceAccountUserRel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceAccountUserRel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceAccountUserRel commerceAccountUserRel : list) {
					if ((userId != commerceAccountUserRel.getUserId())) {
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

			query.append(_SQL_SELECT_COMMERCEACCOUNTUSERREL_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceAccountUserRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<CommerceAccountUserRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceAccountUserRel>)QueryUtil.list(q,
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
	 * Returns the first commerce account user rel in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce account user rel
	 * @throws NoSuchAccountUserRelException if a matching commerce account user rel could not be found
	 */
	@Override
	public CommerceAccountUserRel findByuserId_First(long userId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator)
		throws NoSuchAccountUserRelException {
		CommerceAccountUserRel commerceAccountUserRel = fetchByuserId_First(userId,
				orderByComparator);

		if (commerceAccountUserRel != null) {
			return commerceAccountUserRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchAccountUserRelException(msg.toString());
	}

	/**
	 * Returns the first commerce account user rel in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce account user rel, or <code>null</code> if a matching commerce account user rel could not be found
	 */
	@Override
	public CommerceAccountUserRel fetchByuserId_First(long userId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator) {
		List<CommerceAccountUserRel> list = findByuserId(userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce account user rel in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce account user rel
	 * @throws NoSuchAccountUserRelException if a matching commerce account user rel could not be found
	 */
	@Override
	public CommerceAccountUserRel findByuserId_Last(long userId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator)
		throws NoSuchAccountUserRelException {
		CommerceAccountUserRel commerceAccountUserRel = fetchByuserId_Last(userId,
				orderByComparator);

		if (commerceAccountUserRel != null) {
			return commerceAccountUserRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchAccountUserRelException(msg.toString());
	}

	/**
	 * Returns the last commerce account user rel in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce account user rel, or <code>null</code> if a matching commerce account user rel could not be found
	 */
	@Override
	public CommerceAccountUserRel fetchByuserId_Last(long userId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator) {
		int count = countByuserId(userId);

		if (count == 0) {
			return null;
		}

		List<CommerceAccountUserRel> list = findByuserId(userId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce account user rels before and after the current commerce account user rel in the ordered set where userId = &#63;.
	 *
	 * @param commerceAccountUserRelPK the primary key of the current commerce account user rel
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce account user rel
	 * @throws NoSuchAccountUserRelException if a commerce account user rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountUserRel[] findByuserId_PrevAndNext(
		CommerceAccountUserRelPK commerceAccountUserRelPK, long userId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator)
		throws NoSuchAccountUserRelException {
		CommerceAccountUserRel commerceAccountUserRel = findByPrimaryKey(commerceAccountUserRelPK);

		Session session = null;

		try {
			session = openSession();

			CommerceAccountUserRel[] array = new CommerceAccountUserRelImpl[3];

			array[0] = getByuserId_PrevAndNext(session, commerceAccountUserRel,
					userId, orderByComparator, true);

			array[1] = commerceAccountUserRel;

			array[2] = getByuserId_PrevAndNext(session, commerceAccountUserRel,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceAccountUserRel getByuserId_PrevAndNext(Session session,
		CommerceAccountUserRel commerceAccountUserRel, long userId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEACCOUNTUSERREL_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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
			query.append(CommerceAccountUserRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceAccountUserRel);

			for (Object value : values) {
				qPos.add(value);
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
	 * Removes all the commerce account user rels where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByuserId(long userId) {
		for (CommerceAccountUserRel commerceAccountUserRel : findByuserId(
				userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(commerceAccountUserRel);
		}
	}

	/**
	 * Returns the number of commerce account user rels where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching commerce account user rels
	 */
	@Override
	public int countByuserId(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEACCOUNTUSERREL_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "commerceAccountUserRel.id.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMMERCEACCOUNTID =
		new FinderPath(CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountUserRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountUserRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBycommerceAccountId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEACCOUNTID =
		new FinderPath(CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountUserRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountUserRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBycommerceAccountId", new String[] { Long.class.getName() },
			CommerceAccountUserRelModelImpl.COMMERCEACCOUNTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMMERCEACCOUNTID = new FinderPath(CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountUserRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBycommerceAccountId", new String[] { Long.class.getName() });

	/**
	 * Returns all the commerce account user rels where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @return the matching commerce account user rels
	 */
	@Override
	public List<CommerceAccountUserRel> findBycommerceAccountId(
		long commerceAccountId) {
		return findBycommerceAccountId(commerceAccountId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce account user rels where commerceAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param start the lower bound of the range of commerce account user rels
	 * @param end the upper bound of the range of commerce account user rels (not inclusive)
	 * @return the range of matching commerce account user rels
	 */
	@Override
	public List<CommerceAccountUserRel> findBycommerceAccountId(
		long commerceAccountId, int start, int end) {
		return findBycommerceAccountId(commerceAccountId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce account user rels where commerceAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param start the lower bound of the range of commerce account user rels
	 * @param end the upper bound of the range of commerce account user rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce account user rels
	 */
	@Override
	public List<CommerceAccountUserRel> findBycommerceAccountId(
		long commerceAccountId, int start, int end,
		OrderByComparator<CommerceAccountUserRel> orderByComparator) {
		return findBycommerceAccountId(commerceAccountId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce account user rels where commerceAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param start the lower bound of the range of commerce account user rels
	 * @param end the upper bound of the range of commerce account user rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce account user rels
	 */
	@Override
	public List<CommerceAccountUserRel> findBycommerceAccountId(
		long commerceAccountId, int start, int end,
		OrderByComparator<CommerceAccountUserRel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEACCOUNTID;
			finderArgs = new Object[] { commerceAccountId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMMERCEACCOUNTID;
			finderArgs = new Object[] {
					commerceAccountId,
					
					start, end, orderByComparator
				};
		}

		List<CommerceAccountUserRel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceAccountUserRel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceAccountUserRel commerceAccountUserRel : list) {
					if ((commerceAccountId != commerceAccountUserRel.getCommerceAccountId())) {
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

			query.append(_SQL_SELECT_COMMERCEACCOUNTUSERREL_WHERE);

			query.append(_FINDER_COLUMN_COMMERCEACCOUNTID_COMMERCEACCOUNTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
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
					list = (List<CommerceAccountUserRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceAccountUserRel>)QueryUtil.list(q,
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
	 * Returns the first commerce account user rel in the ordered set where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce account user rel
	 * @throws NoSuchAccountUserRelException if a matching commerce account user rel could not be found
	 */
	@Override
	public CommerceAccountUserRel findBycommerceAccountId_First(
		long commerceAccountId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator)
		throws NoSuchAccountUserRelException {
		CommerceAccountUserRel commerceAccountUserRel = fetchBycommerceAccountId_First(commerceAccountId,
				orderByComparator);

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
	public CommerceAccountUserRel fetchBycommerceAccountId_First(
		long commerceAccountId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator) {
		List<CommerceAccountUserRel> list = findBycommerceAccountId(commerceAccountId,
				0, 1, orderByComparator);

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
	public CommerceAccountUserRel findBycommerceAccountId_Last(
		long commerceAccountId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator)
		throws NoSuchAccountUserRelException {
		CommerceAccountUserRel commerceAccountUserRel = fetchBycommerceAccountId_Last(commerceAccountId,
				orderByComparator);

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
	public CommerceAccountUserRel fetchBycommerceAccountId_Last(
		long commerceAccountId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator) {
		int count = countBycommerceAccountId(commerceAccountId);

		if (count == 0) {
			return null;
		}

		List<CommerceAccountUserRel> list = findBycommerceAccountId(commerceAccountId,
				count - 1, count, orderByComparator);

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
	public CommerceAccountUserRel[] findBycommerceAccountId_PrevAndNext(
		CommerceAccountUserRelPK commerceAccountUserRelPK,
		long commerceAccountId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator)
		throws NoSuchAccountUserRelException {
		CommerceAccountUserRel commerceAccountUserRel = findByPrimaryKey(commerceAccountUserRelPK);

		Session session = null;

		try {
			session = openSession();

			CommerceAccountUserRel[] array = new CommerceAccountUserRelImpl[3];

			array[0] = getBycommerceAccountId_PrevAndNext(session,
					commerceAccountUserRel, commerceAccountId,
					orderByComparator, true);

			array[1] = commerceAccountUserRel;

			array[2] = getBycommerceAccountId_PrevAndNext(session,
					commerceAccountUserRel, commerceAccountId,
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

	protected CommerceAccountUserRel getBycommerceAccountId_PrevAndNext(
		Session session, CommerceAccountUserRel commerceAccountUserRel,
		long commerceAccountId,
		OrderByComparator<CommerceAccountUserRel> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEACCOUNTUSERREL_WHERE);

		query.append(_FINDER_COLUMN_COMMERCEACCOUNTID_COMMERCEACCOUNTID_2);

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
			query.append(CommerceAccountUserRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceAccountId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceAccountUserRel);

			for (Object value : values) {
				qPos.add(value);
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
	public void removeBycommerceAccountId(long commerceAccountId) {
		for (CommerceAccountUserRel commerceAccountUserRel : findBycommerceAccountId(
				commerceAccountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
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
	public int countBycommerceAccountId(long commerceAccountId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMMERCEACCOUNTID;

		Object[] finderArgs = new Object[] { commerceAccountId };

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

	private static final String _FINDER_COLUMN_COMMERCEACCOUNTID_COMMERCEACCOUNTID_2 =
		"commerceAccountUserRel.id.commerceAccountId = ?";

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
		entityCache.putResult(CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
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
		for (CommerceAccountUserRel commerceAccountUserRel : commerceAccountUserRels) {
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceAccountUserRel commerceAccountUserRel) {
		entityCache.removeResult(CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountUserRelImpl.class,
			commerceAccountUserRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CommerceAccountUserRel> commerceAccountUserRels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceAccountUserRel commerceAccountUserRel : commerceAccountUserRels) {
			entityCache.removeResult(CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
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
		CommerceAccountUserRel commerceAccountUserRel = new CommerceAccountUserRelImpl();

		commerceAccountUserRel.setNew(true);
		commerceAccountUserRel.setPrimaryKey(commerceAccountUserRelPK);

		commerceAccountUserRel.setCompanyId(companyProvider.getCompanyId());

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

			CommerceAccountUserRel commerceAccountUserRel = (CommerceAccountUserRel)session.get(CommerceAccountUserRelImpl.class,
					primaryKey);

			if (commerceAccountUserRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountUserRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
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
				commerceAccountUserRel = (CommerceAccountUserRel)session.get(CommerceAccountUserRelImpl.class,
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

		if (!(commerceAccountUserRel instanceof CommerceAccountUserRelModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceAccountUserRel.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(commerceAccountUserRel);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceAccountUserRel proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceAccountUserRel implementation " +
				commerceAccountUserRel.getClass());
		}

		CommerceAccountUserRelModelImpl commerceAccountUserRelModelImpl = (CommerceAccountUserRelModelImpl)commerceAccountUserRel;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceAccountUserRel.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceAccountUserRel.setCreateDate(now);
			}
			else {
				commerceAccountUserRel.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!commerceAccountUserRelModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceAccountUserRel.setModifiedDate(now);
			}
			else {
				commerceAccountUserRel.setModifiedDate(serviceContext.getModifiedDate(
						now));
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
				commerceAccountUserRel = (CommerceAccountUserRel)session.merge(commerceAccountUserRel);
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
		else
		 if (isNew) {
			Object[] args = new Object[] {
					commerceAccountUserRelModelImpl.getUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
				args);

			args = new Object[] {
					commerceAccountUserRelModelImpl.getCommerceAccountId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEACCOUNTID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEACCOUNTID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((commerceAccountUserRelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceAccountUserRelModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { commerceAccountUserRelModelImpl.getUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((commerceAccountUserRelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEACCOUNTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceAccountUserRelModelImpl.getOriginalCommerceAccountId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEACCOUNTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEACCOUNTID,
					args);

				args = new Object[] {
						commerceAccountUserRelModelImpl.getCommerceAccountId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEACCOUNTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEACCOUNTID,
					args);
			}
		}

		entityCache.putResult(CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountUserRelImpl.class,
			commerceAccountUserRel.getPrimaryKey(), commerceAccountUserRel,
			false);

		commerceAccountUserRel.resetOriginalValues();

		return commerceAccountUserRel;
	}

	/**
	 * Returns the commerce account user rel with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce account user rel
	 * @return the commerce account user rel
	 * @throws NoSuchAccountUserRelException if a commerce account user rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountUserRel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAccountUserRelException {
		CommerceAccountUserRel commerceAccountUserRel = fetchByPrimaryKey(primaryKey);

		if (commerceAccountUserRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccountUserRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commerceAccountUserRel;
	}

	/**
	 * Returns the commerce account user rel with the primary key or throws a {@link NoSuchAccountUserRelException} if it could not be found.
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
		Serializable serializable = entityCache.getResult(CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
				CommerceAccountUserRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceAccountUserRel commerceAccountUserRel = (CommerceAccountUserRel)serializable;

		if (commerceAccountUserRel == null) {
			Session session = null;

			try {
				session = openSession();

				commerceAccountUserRel = (CommerceAccountUserRel)session.get(CommerceAccountUserRelImpl.class,
						primaryKey);

				if (commerceAccountUserRel != null) {
					cacheResult(commerceAccountUserRel);
				}
				else {
					entityCache.putResult(CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
						CommerceAccountUserRelImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommerceAccountUserRelModelImpl.ENTITY_CACHE_ENABLED,
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

		Map<Serializable, CommerceAccountUserRel> map = new HashMap<Serializable, CommerceAccountUserRel>();

		for (Serializable primaryKey : primaryKeys) {
			CommerceAccountUserRel commerceAccountUserRel = fetchByPrimaryKey(primaryKey);

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce account user rels
	 * @param end the upper bound of the range of commerce account user rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce account user rels
	 */
	@Override
	public List<CommerceAccountUserRel> findAll(int start, int end,
		OrderByComparator<CommerceAccountUserRel> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce account user rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountUserRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce account user rels
	 * @param end the upper bound of the range of commerce account user rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce account user rels
	 */
	@Override
	public List<CommerceAccountUserRel> findAll(int start, int end,
		OrderByComparator<CommerceAccountUserRel> orderByComparator,
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

		List<CommerceAccountUserRel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceAccountUserRel>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEACCOUNTUSERREL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEACCOUNTUSERREL;

				if (pagination) {
					sql = sql.concat(CommerceAccountUserRelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceAccountUserRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceAccountUserRel>)QueryUtil.list(q,
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
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCEACCOUNTUSERREL);

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
	}

	public void destroy() {
		entityCache.removeCache(CommerceAccountUserRelImpl.class.getName());
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
	private static final String _SQL_SELECT_COMMERCEACCOUNTUSERREL = "SELECT commerceAccountUserRel FROM CommerceAccountUserRel commerceAccountUserRel";
	private static final String _SQL_SELECT_COMMERCEACCOUNTUSERREL_WHERE = "SELECT commerceAccountUserRel FROM CommerceAccountUserRel commerceAccountUserRel WHERE ";
	private static final String _SQL_COUNT_COMMERCEACCOUNTUSERREL = "SELECT COUNT(commerceAccountUserRel) FROM CommerceAccountUserRel commerceAccountUserRel";
	private static final String _SQL_COUNT_COMMERCEACCOUNTUSERREL_WHERE = "SELECT COUNT(commerceAccountUserRel) FROM CommerceAccountUserRel commerceAccountUserRel WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceAccountUserRel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommerceAccountUserRel exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CommerceAccountUserRel exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CommerceAccountUserRelPersistenceImpl.class);
	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(new String[] {
				"commerceAccountId", "userId"
			});
}