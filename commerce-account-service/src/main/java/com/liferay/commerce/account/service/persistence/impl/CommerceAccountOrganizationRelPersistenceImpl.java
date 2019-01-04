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

import com.liferay.commerce.account.exception.NoSuchAccountOrganizationRelException;
import com.liferay.commerce.account.model.CommerceAccountOrganizationRel;
import com.liferay.commerce.account.model.impl.CommerceAccountOrganizationRelImpl;
import com.liferay.commerce.account.model.impl.CommerceAccountOrganizationRelModelImpl;
import com.liferay.commerce.account.service.persistence.CommerceAccountOrganizationRelPK;
import com.liferay.commerce.account.service.persistence.CommerceAccountOrganizationRelPersistence;

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
 * The persistence implementation for the commerce account organization rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CommerceAccountOrganizationRelPersistence
 * @see com.liferay.commerce.account.service.persistence.CommerceAccountOrganizationRelUtil
 * @generated
 */
@ProviderType
public class CommerceAccountOrganizationRelPersistenceImpl
	extends BasePersistenceImpl<CommerceAccountOrganizationRel>
	implements CommerceAccountOrganizationRelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommerceAccountOrganizationRelUtil} to access the commerce account organization rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommerceAccountOrganizationRelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommerceAccountOrganizationRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountOrganizationRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountOrganizationRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommerceAccountOrganizationRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountOrganizationRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountOrganizationRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommerceAccountOrganizationRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountOrganizationRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMMERCEACCOUNTID =
		new FinderPath(CommerceAccountOrganizationRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountOrganizationRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountOrganizationRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCommerceAccountId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEACCOUNTID =
		new FinderPath(CommerceAccountOrganizationRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountOrganizationRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountOrganizationRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCommerceAccountId", new String[] { Long.class.getName() },
			CommerceAccountOrganizationRelModelImpl.COMMERCEACCOUNTID_COLUMN_BITMASK |
			CommerceAccountOrganizationRelModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMMERCEACCOUNTID = new FinderPath(CommerceAccountOrganizationRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountOrganizationRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceAccountId", new String[] { Long.class.getName() });

	/**
	 * Returns all the commerce account organization rels where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @return the matching commerce account organization rels
	 */
	@Override
	public List<CommerceAccountOrganizationRel> findByCommerceAccountId(
		long commerceAccountId) {
		return findByCommerceAccountId(commerceAccountId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce account organization rels where commerceAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountOrganizationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param start the lower bound of the range of commerce account organization rels
	 * @param end the upper bound of the range of commerce account organization rels (not inclusive)
	 * @return the range of matching commerce account organization rels
	 */
	@Override
	public List<CommerceAccountOrganizationRel> findByCommerceAccountId(
		long commerceAccountId, int start, int end) {
		return findByCommerceAccountId(commerceAccountId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce account organization rels where commerceAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountOrganizationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param start the lower bound of the range of commerce account organization rels
	 * @param end the upper bound of the range of commerce account organization rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce account organization rels
	 */
	@Override
	public List<CommerceAccountOrganizationRel> findByCommerceAccountId(
		long commerceAccountId, int start, int end,
		OrderByComparator<CommerceAccountOrganizationRel> orderByComparator) {
		return findByCommerceAccountId(commerceAccountId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce account organization rels where commerceAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountOrganizationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param start the lower bound of the range of commerce account organization rels
	 * @param end the upper bound of the range of commerce account organization rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce account organization rels
	 */
	@Override
	public List<CommerceAccountOrganizationRel> findByCommerceAccountId(
		long commerceAccountId, int start, int end,
		OrderByComparator<CommerceAccountOrganizationRel> orderByComparator,
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

		List<CommerceAccountOrganizationRel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceAccountOrganizationRel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceAccountOrganizationRel commerceAccountOrganizationRel : list) {
					if ((commerceAccountId != commerceAccountOrganizationRel.getCommerceAccountId())) {
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

			query.append(_SQL_SELECT_COMMERCEACCOUNTORGANIZATIONREL_WHERE);

			query.append(_FINDER_COLUMN_COMMERCEACCOUNTID_COMMERCEACCOUNTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceAccountOrganizationRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceAccountId);

				if (!pagination) {
					list = (List<CommerceAccountOrganizationRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceAccountOrganizationRel>)QueryUtil.list(q,
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
	 * Returns the first commerce account organization rel in the ordered set where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce account organization rel
	 * @throws NoSuchAccountOrganizationRelException if a matching commerce account organization rel could not be found
	 */
	@Override
	public CommerceAccountOrganizationRel findByCommerceAccountId_First(
		long commerceAccountId,
		OrderByComparator<CommerceAccountOrganizationRel> orderByComparator)
		throws NoSuchAccountOrganizationRelException {
		CommerceAccountOrganizationRel commerceAccountOrganizationRel = fetchByCommerceAccountId_First(commerceAccountId,
				orderByComparator);

		if (commerceAccountOrganizationRel != null) {
			return commerceAccountOrganizationRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceAccountId=");
		msg.append(commerceAccountId);

		msg.append("}");

		throw new NoSuchAccountOrganizationRelException(msg.toString());
	}

	/**
	 * Returns the first commerce account organization rel in the ordered set where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce account organization rel, or <code>null</code> if a matching commerce account organization rel could not be found
	 */
	@Override
	public CommerceAccountOrganizationRel fetchByCommerceAccountId_First(
		long commerceAccountId,
		OrderByComparator<CommerceAccountOrganizationRel> orderByComparator) {
		List<CommerceAccountOrganizationRel> list = findByCommerceAccountId(commerceAccountId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce account organization rel in the ordered set where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce account organization rel
	 * @throws NoSuchAccountOrganizationRelException if a matching commerce account organization rel could not be found
	 */
	@Override
	public CommerceAccountOrganizationRel findByCommerceAccountId_Last(
		long commerceAccountId,
		OrderByComparator<CommerceAccountOrganizationRel> orderByComparator)
		throws NoSuchAccountOrganizationRelException {
		CommerceAccountOrganizationRel commerceAccountOrganizationRel = fetchByCommerceAccountId_Last(commerceAccountId,
				orderByComparator);

		if (commerceAccountOrganizationRel != null) {
			return commerceAccountOrganizationRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceAccountId=");
		msg.append(commerceAccountId);

		msg.append("}");

		throw new NoSuchAccountOrganizationRelException(msg.toString());
	}

	/**
	 * Returns the last commerce account organization rel in the ordered set where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce account organization rel, or <code>null</code> if a matching commerce account organization rel could not be found
	 */
	@Override
	public CommerceAccountOrganizationRel fetchByCommerceAccountId_Last(
		long commerceAccountId,
		OrderByComparator<CommerceAccountOrganizationRel> orderByComparator) {
		int count = countByCommerceAccountId(commerceAccountId);

		if (count == 0) {
			return null;
		}

		List<CommerceAccountOrganizationRel> list = findByCommerceAccountId(commerceAccountId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce account organization rels before and after the current commerce account organization rel in the ordered set where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountOrganizationRelPK the primary key of the current commerce account organization rel
	 * @param commerceAccountId the commerce account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce account organization rel
	 * @throws NoSuchAccountOrganizationRelException if a commerce account organization rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountOrganizationRel[] findByCommerceAccountId_PrevAndNext(
		CommerceAccountOrganizationRelPK commerceAccountOrganizationRelPK,
		long commerceAccountId,
		OrderByComparator<CommerceAccountOrganizationRel> orderByComparator)
		throws NoSuchAccountOrganizationRelException {
		CommerceAccountOrganizationRel commerceAccountOrganizationRel = findByPrimaryKey(commerceAccountOrganizationRelPK);

		Session session = null;

		try {
			session = openSession();

			CommerceAccountOrganizationRel[] array = new CommerceAccountOrganizationRelImpl[3];

			array[0] = getByCommerceAccountId_PrevAndNext(session,
					commerceAccountOrganizationRel, commerceAccountId,
					orderByComparator, true);

			array[1] = commerceAccountOrganizationRel;

			array[2] = getByCommerceAccountId_PrevAndNext(session,
					commerceAccountOrganizationRel, commerceAccountId,
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

	protected CommerceAccountOrganizationRel getByCommerceAccountId_PrevAndNext(
		Session session,
		CommerceAccountOrganizationRel commerceAccountOrganizationRel,
		long commerceAccountId,
		OrderByComparator<CommerceAccountOrganizationRel> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEACCOUNTORGANIZATIONREL_WHERE);

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
			query.append(CommerceAccountOrganizationRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceAccountId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceAccountOrganizationRel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceAccountOrganizationRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce account organization rels where commerceAccountId = &#63; from the database.
	 *
	 * @param commerceAccountId the commerce account ID
	 */
	@Override
	public void removeByCommerceAccountId(long commerceAccountId) {
		for (CommerceAccountOrganizationRel commerceAccountOrganizationRel : findByCommerceAccountId(
				commerceAccountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(commerceAccountOrganizationRel);
		}
	}

	/**
	 * Returns the number of commerce account organization rels where commerceAccountId = &#63;.
	 *
	 * @param commerceAccountId the commerce account ID
	 * @return the number of matching commerce account organization rels
	 */
	@Override
	public int countByCommerceAccountId(long commerceAccountId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMMERCEACCOUNTID;

		Object[] finderArgs = new Object[] { commerceAccountId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEACCOUNTORGANIZATIONREL_WHERE);

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
		"commerceAccountOrganizationRel.id.commerceAccountId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ORGANIZATIONID =
		new FinderPath(CommerceAccountOrganizationRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountOrganizationRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountOrganizationRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOrganizationId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORGANIZATIONID =
		new FinderPath(CommerceAccountOrganizationRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountOrganizationRelModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountOrganizationRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOrganizationId",
			new String[] { Long.class.getName() },
			CommerceAccountOrganizationRelModelImpl.ORGANIZATIONID_COLUMN_BITMASK |
			CommerceAccountOrganizationRelModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ORGANIZATIONID = new FinderPath(CommerceAccountOrganizationRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountOrganizationRelModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByOrganizationId", new String[] { Long.class.getName() });

	/**
	 * Returns all the commerce account organization rels where organizationId = &#63;.
	 *
	 * @param organizationId the organization ID
	 * @return the matching commerce account organization rels
	 */
	@Override
	public List<CommerceAccountOrganizationRel> findByOrganizationId(
		long organizationId) {
		return findByOrganizationId(organizationId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce account organization rels where organizationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountOrganizationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param organizationId the organization ID
	 * @param start the lower bound of the range of commerce account organization rels
	 * @param end the upper bound of the range of commerce account organization rels (not inclusive)
	 * @return the range of matching commerce account organization rels
	 */
	@Override
	public List<CommerceAccountOrganizationRel> findByOrganizationId(
		long organizationId, int start, int end) {
		return findByOrganizationId(organizationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce account organization rels where organizationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountOrganizationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param organizationId the organization ID
	 * @param start the lower bound of the range of commerce account organization rels
	 * @param end the upper bound of the range of commerce account organization rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce account organization rels
	 */
	@Override
	public List<CommerceAccountOrganizationRel> findByOrganizationId(
		long organizationId, int start, int end,
		OrderByComparator<CommerceAccountOrganizationRel> orderByComparator) {
		return findByOrganizationId(organizationId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce account organization rels where organizationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountOrganizationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param organizationId the organization ID
	 * @param start the lower bound of the range of commerce account organization rels
	 * @param end the upper bound of the range of commerce account organization rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce account organization rels
	 */
	@Override
	public List<CommerceAccountOrganizationRel> findByOrganizationId(
		long organizationId, int start, int end,
		OrderByComparator<CommerceAccountOrganizationRel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORGANIZATIONID;
			finderArgs = new Object[] { organizationId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ORGANIZATIONID;
			finderArgs = new Object[] {
					organizationId,
					
					start, end, orderByComparator
				};
		}

		List<CommerceAccountOrganizationRel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceAccountOrganizationRel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceAccountOrganizationRel commerceAccountOrganizationRel : list) {
					if ((organizationId != commerceAccountOrganizationRel.getOrganizationId())) {
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

			query.append(_SQL_SELECT_COMMERCEACCOUNTORGANIZATIONREL_WHERE);

			query.append(_FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceAccountOrganizationRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(organizationId);

				if (!pagination) {
					list = (List<CommerceAccountOrganizationRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceAccountOrganizationRel>)QueryUtil.list(q,
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
	 * Returns the first commerce account organization rel in the ordered set where organizationId = &#63;.
	 *
	 * @param organizationId the organization ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce account organization rel
	 * @throws NoSuchAccountOrganizationRelException if a matching commerce account organization rel could not be found
	 */
	@Override
	public CommerceAccountOrganizationRel findByOrganizationId_First(
		long organizationId,
		OrderByComparator<CommerceAccountOrganizationRel> orderByComparator)
		throws NoSuchAccountOrganizationRelException {
		CommerceAccountOrganizationRel commerceAccountOrganizationRel = fetchByOrganizationId_First(organizationId,
				orderByComparator);

		if (commerceAccountOrganizationRel != null) {
			return commerceAccountOrganizationRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("organizationId=");
		msg.append(organizationId);

		msg.append("}");

		throw new NoSuchAccountOrganizationRelException(msg.toString());
	}

	/**
	 * Returns the first commerce account organization rel in the ordered set where organizationId = &#63;.
	 *
	 * @param organizationId the organization ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce account organization rel, or <code>null</code> if a matching commerce account organization rel could not be found
	 */
	@Override
	public CommerceAccountOrganizationRel fetchByOrganizationId_First(
		long organizationId,
		OrderByComparator<CommerceAccountOrganizationRel> orderByComparator) {
		List<CommerceAccountOrganizationRel> list = findByOrganizationId(organizationId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce account organization rel in the ordered set where organizationId = &#63;.
	 *
	 * @param organizationId the organization ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce account organization rel
	 * @throws NoSuchAccountOrganizationRelException if a matching commerce account organization rel could not be found
	 */
	@Override
	public CommerceAccountOrganizationRel findByOrganizationId_Last(
		long organizationId,
		OrderByComparator<CommerceAccountOrganizationRel> orderByComparator)
		throws NoSuchAccountOrganizationRelException {
		CommerceAccountOrganizationRel commerceAccountOrganizationRel = fetchByOrganizationId_Last(organizationId,
				orderByComparator);

		if (commerceAccountOrganizationRel != null) {
			return commerceAccountOrganizationRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("organizationId=");
		msg.append(organizationId);

		msg.append("}");

		throw new NoSuchAccountOrganizationRelException(msg.toString());
	}

	/**
	 * Returns the last commerce account organization rel in the ordered set where organizationId = &#63;.
	 *
	 * @param organizationId the organization ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce account organization rel, or <code>null</code> if a matching commerce account organization rel could not be found
	 */
	@Override
	public CommerceAccountOrganizationRel fetchByOrganizationId_Last(
		long organizationId,
		OrderByComparator<CommerceAccountOrganizationRel> orderByComparator) {
		int count = countByOrganizationId(organizationId);

		if (count == 0) {
			return null;
		}

		List<CommerceAccountOrganizationRel> list = findByOrganizationId(organizationId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce account organization rels before and after the current commerce account organization rel in the ordered set where organizationId = &#63;.
	 *
	 * @param commerceAccountOrganizationRelPK the primary key of the current commerce account organization rel
	 * @param organizationId the organization ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce account organization rel
	 * @throws NoSuchAccountOrganizationRelException if a commerce account organization rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountOrganizationRel[] findByOrganizationId_PrevAndNext(
		CommerceAccountOrganizationRelPK commerceAccountOrganizationRelPK,
		long organizationId,
		OrderByComparator<CommerceAccountOrganizationRel> orderByComparator)
		throws NoSuchAccountOrganizationRelException {
		CommerceAccountOrganizationRel commerceAccountOrganizationRel = findByPrimaryKey(commerceAccountOrganizationRelPK);

		Session session = null;

		try {
			session = openSession();

			CommerceAccountOrganizationRel[] array = new CommerceAccountOrganizationRelImpl[3];

			array[0] = getByOrganizationId_PrevAndNext(session,
					commerceAccountOrganizationRel, organizationId,
					orderByComparator, true);

			array[1] = commerceAccountOrganizationRel;

			array[2] = getByOrganizationId_PrevAndNext(session,
					commerceAccountOrganizationRel, organizationId,
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

	protected CommerceAccountOrganizationRel getByOrganizationId_PrevAndNext(
		Session session,
		CommerceAccountOrganizationRel commerceAccountOrganizationRel,
		long organizationId,
		OrderByComparator<CommerceAccountOrganizationRel> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEACCOUNTORGANIZATIONREL_WHERE);

		query.append(_FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2);

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
			query.append(CommerceAccountOrganizationRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(organizationId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceAccountOrganizationRel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceAccountOrganizationRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce account organization rels where organizationId = &#63; from the database.
	 *
	 * @param organizationId the organization ID
	 */
	@Override
	public void removeByOrganizationId(long organizationId) {
		for (CommerceAccountOrganizationRel commerceAccountOrganizationRel : findByOrganizationId(
				organizationId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(commerceAccountOrganizationRel);
		}
	}

	/**
	 * Returns the number of commerce account organization rels where organizationId = &#63;.
	 *
	 * @param organizationId the organization ID
	 * @return the number of matching commerce account organization rels
	 */
	@Override
	public int countByOrganizationId(long organizationId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ORGANIZATIONID;

		Object[] finderArgs = new Object[] { organizationId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEACCOUNTORGANIZATIONREL_WHERE);

			query.append(_FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(organizationId);

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

	private static final String _FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2 = "commerceAccountOrganizationRel.id.organizationId = ?";

	public CommerceAccountOrganizationRelPersistenceImpl() {
		setModelClass(CommerceAccountOrganizationRel.class);
	}

	/**
	 * Caches the commerce account organization rel in the entity cache if it is enabled.
	 *
	 * @param commerceAccountOrganizationRel the commerce account organization rel
	 */
	@Override
	public void cacheResult(
		CommerceAccountOrganizationRel commerceAccountOrganizationRel) {
		entityCache.putResult(CommerceAccountOrganizationRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountOrganizationRelImpl.class,
			commerceAccountOrganizationRel.getPrimaryKey(),
			commerceAccountOrganizationRel);

		commerceAccountOrganizationRel.resetOriginalValues();
	}

	/**
	 * Caches the commerce account organization rels in the entity cache if it is enabled.
	 *
	 * @param commerceAccountOrganizationRels the commerce account organization rels
	 */
	@Override
	public void cacheResult(
		List<CommerceAccountOrganizationRel> commerceAccountOrganizationRels) {
		for (CommerceAccountOrganizationRel commerceAccountOrganizationRel : commerceAccountOrganizationRels) {
			if (entityCache.getResult(
						CommerceAccountOrganizationRelModelImpl.ENTITY_CACHE_ENABLED,
						CommerceAccountOrganizationRelImpl.class,
						commerceAccountOrganizationRel.getPrimaryKey()) == null) {
				cacheResult(commerceAccountOrganizationRel);
			}
			else {
				commerceAccountOrganizationRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce account organization rels.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceAccountOrganizationRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce account organization rel.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CommerceAccountOrganizationRel commerceAccountOrganizationRel) {
		entityCache.removeResult(CommerceAccountOrganizationRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountOrganizationRelImpl.class,
			commerceAccountOrganizationRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<CommerceAccountOrganizationRel> commerceAccountOrganizationRels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceAccountOrganizationRel commerceAccountOrganizationRel : commerceAccountOrganizationRels) {
			entityCache.removeResult(CommerceAccountOrganizationRelModelImpl.ENTITY_CACHE_ENABLED,
				CommerceAccountOrganizationRelImpl.class,
				commerceAccountOrganizationRel.getPrimaryKey());
		}
	}

	/**
	 * Creates a new commerce account organization rel with the primary key. Does not add the commerce account organization rel to the database.
	 *
	 * @param commerceAccountOrganizationRelPK the primary key for the new commerce account organization rel
	 * @return the new commerce account organization rel
	 */
	@Override
	public CommerceAccountOrganizationRel create(
		CommerceAccountOrganizationRelPK commerceAccountOrganizationRelPK) {
		CommerceAccountOrganizationRel commerceAccountOrganizationRel = new CommerceAccountOrganizationRelImpl();

		commerceAccountOrganizationRel.setNew(true);
		commerceAccountOrganizationRel.setPrimaryKey(commerceAccountOrganizationRelPK);

		commerceAccountOrganizationRel.setCompanyId(companyProvider.getCompanyId());

		return commerceAccountOrganizationRel;
	}

	/**
	 * Removes the commerce account organization rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceAccountOrganizationRelPK the primary key of the commerce account organization rel
	 * @return the commerce account organization rel that was removed
	 * @throws NoSuchAccountOrganizationRelException if a commerce account organization rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountOrganizationRel remove(
		CommerceAccountOrganizationRelPK commerceAccountOrganizationRelPK)
		throws NoSuchAccountOrganizationRelException {
		return remove((Serializable)commerceAccountOrganizationRelPK);
	}

	/**
	 * Removes the commerce account organization rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce account organization rel
	 * @return the commerce account organization rel that was removed
	 * @throws NoSuchAccountOrganizationRelException if a commerce account organization rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountOrganizationRel remove(Serializable primaryKey)
		throws NoSuchAccountOrganizationRelException {
		Session session = null;

		try {
			session = openSession();

			CommerceAccountOrganizationRel commerceAccountOrganizationRel = (CommerceAccountOrganizationRel)session.get(CommerceAccountOrganizationRelImpl.class,
					primaryKey);

			if (commerceAccountOrganizationRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountOrganizationRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commerceAccountOrganizationRel);
		}
		catch (NoSuchAccountOrganizationRelException nsee) {
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
	protected CommerceAccountOrganizationRel removeImpl(
		CommerceAccountOrganizationRel commerceAccountOrganizationRel) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceAccountOrganizationRel)) {
				commerceAccountOrganizationRel = (CommerceAccountOrganizationRel)session.get(CommerceAccountOrganizationRelImpl.class,
						commerceAccountOrganizationRel.getPrimaryKeyObj());
			}

			if (commerceAccountOrganizationRel != null) {
				session.delete(commerceAccountOrganizationRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceAccountOrganizationRel != null) {
			clearCache(commerceAccountOrganizationRel);
		}

		return commerceAccountOrganizationRel;
	}

	@Override
	public CommerceAccountOrganizationRel updateImpl(
		CommerceAccountOrganizationRel commerceAccountOrganizationRel) {
		boolean isNew = commerceAccountOrganizationRel.isNew();

		if (!(commerceAccountOrganizationRel instanceof CommerceAccountOrganizationRelModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(
						commerceAccountOrganizationRel.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(commerceAccountOrganizationRel);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceAccountOrganizationRel proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceAccountOrganizationRel implementation " +
				commerceAccountOrganizationRel.getClass());
		}

		CommerceAccountOrganizationRelModelImpl commerceAccountOrganizationRelModelImpl =
			(CommerceAccountOrganizationRelModelImpl)commerceAccountOrganizationRel;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceAccountOrganizationRel.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceAccountOrganizationRel.setCreateDate(now);
			}
			else {
				commerceAccountOrganizationRel.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!commerceAccountOrganizationRelModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceAccountOrganizationRel.setModifiedDate(now);
			}
			else {
				commerceAccountOrganizationRel.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceAccountOrganizationRel.isNew()) {
				session.save(commerceAccountOrganizationRel);

				commerceAccountOrganizationRel.setNew(false);
			}
			else {
				commerceAccountOrganizationRel = (CommerceAccountOrganizationRel)session.merge(commerceAccountOrganizationRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceAccountOrganizationRelModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					commerceAccountOrganizationRelModelImpl.getCommerceAccountId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEACCOUNTID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEACCOUNTID,
				args);

			args = new Object[] {
					commerceAccountOrganizationRelModelImpl.getOrganizationId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORGANIZATIONID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((commerceAccountOrganizationRelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEACCOUNTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceAccountOrganizationRelModelImpl.getOriginalCommerceAccountId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEACCOUNTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEACCOUNTID,
					args);

				args = new Object[] {
						commerceAccountOrganizationRelModelImpl.getCommerceAccountId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEACCOUNTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEACCOUNTID,
					args);
			}

			if ((commerceAccountOrganizationRelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORGANIZATIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceAccountOrganizationRelModelImpl.getOriginalOrganizationId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORGANIZATIONID,
					args);

				args = new Object[] {
						commerceAccountOrganizationRelModelImpl.getOrganizationId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORGANIZATIONID,
					args);
			}
		}

		entityCache.putResult(CommerceAccountOrganizationRelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountOrganizationRelImpl.class,
			commerceAccountOrganizationRel.getPrimaryKey(),
			commerceAccountOrganizationRel, false);

		commerceAccountOrganizationRel.resetOriginalValues();

		return commerceAccountOrganizationRel;
	}

	/**
	 * Returns the commerce account organization rel with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce account organization rel
	 * @return the commerce account organization rel
	 * @throws NoSuchAccountOrganizationRelException if a commerce account organization rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountOrganizationRel findByPrimaryKey(
		Serializable primaryKey) throws NoSuchAccountOrganizationRelException {
		CommerceAccountOrganizationRel commerceAccountOrganizationRel = fetchByPrimaryKey(primaryKey);

		if (commerceAccountOrganizationRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccountOrganizationRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commerceAccountOrganizationRel;
	}

	/**
	 * Returns the commerce account organization rel with the primary key or throws a {@link NoSuchAccountOrganizationRelException} if it could not be found.
	 *
	 * @param commerceAccountOrganizationRelPK the primary key of the commerce account organization rel
	 * @return the commerce account organization rel
	 * @throws NoSuchAccountOrganizationRelException if a commerce account organization rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountOrganizationRel findByPrimaryKey(
		CommerceAccountOrganizationRelPK commerceAccountOrganizationRelPK)
		throws NoSuchAccountOrganizationRelException {
		return findByPrimaryKey((Serializable)commerceAccountOrganizationRelPK);
	}

	/**
	 * Returns the commerce account organization rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce account organization rel
	 * @return the commerce account organization rel, or <code>null</code> if a commerce account organization rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountOrganizationRel fetchByPrimaryKey(
		Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CommerceAccountOrganizationRelModelImpl.ENTITY_CACHE_ENABLED,
				CommerceAccountOrganizationRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceAccountOrganizationRel commerceAccountOrganizationRel = (CommerceAccountOrganizationRel)serializable;

		if (commerceAccountOrganizationRel == null) {
			Session session = null;

			try {
				session = openSession();

				commerceAccountOrganizationRel = (CommerceAccountOrganizationRel)session.get(CommerceAccountOrganizationRelImpl.class,
						primaryKey);

				if (commerceAccountOrganizationRel != null) {
					cacheResult(commerceAccountOrganizationRel);
				}
				else {
					entityCache.putResult(CommerceAccountOrganizationRelModelImpl.ENTITY_CACHE_ENABLED,
						CommerceAccountOrganizationRelImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommerceAccountOrganizationRelModelImpl.ENTITY_CACHE_ENABLED,
					CommerceAccountOrganizationRelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceAccountOrganizationRel;
	}

	/**
	 * Returns the commerce account organization rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceAccountOrganizationRelPK the primary key of the commerce account organization rel
	 * @return the commerce account organization rel, or <code>null</code> if a commerce account organization rel with the primary key could not be found
	 */
	@Override
	public CommerceAccountOrganizationRel fetchByPrimaryKey(
		CommerceAccountOrganizationRelPK commerceAccountOrganizationRelPK) {
		return fetchByPrimaryKey((Serializable)commerceAccountOrganizationRelPK);
	}

	@Override
	public Map<Serializable, CommerceAccountOrganizationRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceAccountOrganizationRel> map = new HashMap<Serializable, CommerceAccountOrganizationRel>();

		for (Serializable primaryKey : primaryKeys) {
			CommerceAccountOrganizationRel commerceAccountOrganizationRel = fetchByPrimaryKey(primaryKey);

			if (commerceAccountOrganizationRel != null) {
				map.put(primaryKey, commerceAccountOrganizationRel);
			}
		}

		return map;
	}

	/**
	 * Returns all the commerce account organization rels.
	 *
	 * @return the commerce account organization rels
	 */
	@Override
	public List<CommerceAccountOrganizationRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce account organization rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountOrganizationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce account organization rels
	 * @param end the upper bound of the range of commerce account organization rels (not inclusive)
	 * @return the range of commerce account organization rels
	 */
	@Override
	public List<CommerceAccountOrganizationRel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce account organization rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountOrganizationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce account organization rels
	 * @param end the upper bound of the range of commerce account organization rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce account organization rels
	 */
	@Override
	public List<CommerceAccountOrganizationRel> findAll(int start, int end,
		OrderByComparator<CommerceAccountOrganizationRel> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce account organization rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountOrganizationRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce account organization rels
	 * @param end the upper bound of the range of commerce account organization rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce account organization rels
	 */
	@Override
	public List<CommerceAccountOrganizationRel> findAll(int start, int end,
		OrderByComparator<CommerceAccountOrganizationRel> orderByComparator,
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

		List<CommerceAccountOrganizationRel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceAccountOrganizationRel>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEACCOUNTORGANIZATIONREL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEACCOUNTORGANIZATIONREL;

				if (pagination) {
					sql = sql.concat(CommerceAccountOrganizationRelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceAccountOrganizationRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceAccountOrganizationRel>)QueryUtil.list(q,
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
	 * Removes all the commerce account organization rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceAccountOrganizationRel commerceAccountOrganizationRel : findAll()) {
			remove(commerceAccountOrganizationRel);
		}
	}

	/**
	 * Returns the number of commerce account organization rels.
	 *
	 * @return the number of commerce account organization rels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCEACCOUNTORGANIZATIONREL);

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
		return CommerceAccountOrganizationRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce account organization rel persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CommerceAccountOrganizationRelImpl.class.getName());
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
	private static final String _SQL_SELECT_COMMERCEACCOUNTORGANIZATIONREL = "SELECT commerceAccountOrganizationRel FROM CommerceAccountOrganizationRel commerceAccountOrganizationRel";
	private static final String _SQL_SELECT_COMMERCEACCOUNTORGANIZATIONREL_WHERE =
		"SELECT commerceAccountOrganizationRel FROM CommerceAccountOrganizationRel commerceAccountOrganizationRel WHERE ";
	private static final String _SQL_COUNT_COMMERCEACCOUNTORGANIZATIONREL = "SELECT COUNT(commerceAccountOrganizationRel) FROM CommerceAccountOrganizationRel commerceAccountOrganizationRel";
	private static final String _SQL_COUNT_COMMERCEACCOUNTORGANIZATIONREL_WHERE = "SELECT COUNT(commerceAccountOrganizationRel) FROM CommerceAccountOrganizationRel commerceAccountOrganizationRel WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceAccountOrganizationRel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommerceAccountOrganizationRel exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CommerceAccountOrganizationRel exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CommerceAccountOrganizationRelPersistenceImpl.class);
	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(new String[] {
				"commerceAccountId", "organizationId"
			});
}