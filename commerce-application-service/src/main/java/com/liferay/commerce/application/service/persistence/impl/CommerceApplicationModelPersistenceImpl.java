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

package com.liferay.commerce.application.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.application.exception.NoSuchApplicationModelException;
import com.liferay.commerce.application.model.CommerceApplicationModel;
import com.liferay.commerce.application.model.impl.CommerceApplicationModelImpl;
import com.liferay.commerce.application.model.impl.CommerceApplicationModelModelImpl;
import com.liferay.commerce.application.service.persistence.CommerceApplicationModelPersistence;

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
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
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
 * The persistence implementation for the commerce application model service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceApplicationModelPersistence
 * @see com.liferay.commerce.application.service.persistence.CommerceApplicationModelUtil
 * @generated
 */
@ProviderType
public class CommerceApplicationModelPersistenceImpl extends BasePersistenceImpl<CommerceApplicationModel>
	implements CommerceApplicationModelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommerceApplicationModelUtil} to access the commerce application model persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommerceApplicationModelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommerceApplicationModelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceApplicationModelModelImpl.FINDER_CACHE_ENABLED,
			CommerceApplicationModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommerceApplicationModelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceApplicationModelModelImpl.FINDER_CACHE_ENABLED,
			CommerceApplicationModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommerceApplicationModelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceApplicationModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(CommerceApplicationModelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceApplicationModelModelImpl.FINDER_CACHE_ENABLED,
			CommerceApplicationModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(CommerceApplicationModelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceApplicationModelModelImpl.FINDER_CACHE_ENABLED,
			CommerceApplicationModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			CommerceApplicationModelModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceApplicationModelModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(CommerceApplicationModelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceApplicationModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the commerce application models where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce application models
	 */
	@Override
	public List<CommerceApplicationModel> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the commerce application models where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce application models
	 * @param end the upper bound of the range of commerce application models (not inclusive)
	 * @return the range of matching commerce application models
	 */
	@Override
	public List<CommerceApplicationModel> findByCompanyId(long companyId,
		int start, int end) {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce application models where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce application models
	 * @param end the upper bound of the range of commerce application models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce application models
	 */
	@Override
	public List<CommerceApplicationModel> findByCompanyId(long companyId,
		int start, int end,
		OrderByComparator<CommerceApplicationModel> orderByComparator) {
		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce application models where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce application models
	 * @param end the upper bound of the range of commerce application models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce application models
	 */
	@Override
	public List<CommerceApplicationModel> findByCompanyId(long companyId,
		int start, int end,
		OrderByComparator<CommerceApplicationModel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<CommerceApplicationModel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceApplicationModel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceApplicationModel commerceApplicationModel : list) {
					if ((companyId != commerceApplicationModel.getCompanyId())) {
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

			query.append(_SQL_SELECT_COMMERCEAPPLICATIONMODEL_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceApplicationModelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<CommerceApplicationModel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceApplicationModel>)QueryUtil.list(q,
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
	 * Returns the first commerce application model in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce application model
	 * @throws NoSuchApplicationModelException if a matching commerce application model could not be found
	 */
	@Override
	public CommerceApplicationModel findByCompanyId_First(long companyId,
		OrderByComparator<CommerceApplicationModel> orderByComparator)
		throws NoSuchApplicationModelException {
		CommerceApplicationModel commerceApplicationModel = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (commerceApplicationModel != null) {
			return commerceApplicationModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchApplicationModelException(msg.toString());
	}

	/**
	 * Returns the first commerce application model in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce application model, or <code>null</code> if a matching commerce application model could not be found
	 */
	@Override
	public CommerceApplicationModel fetchByCompanyId_First(long companyId,
		OrderByComparator<CommerceApplicationModel> orderByComparator) {
		List<CommerceApplicationModel> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce application model in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce application model
	 * @throws NoSuchApplicationModelException if a matching commerce application model could not be found
	 */
	@Override
	public CommerceApplicationModel findByCompanyId_Last(long companyId,
		OrderByComparator<CommerceApplicationModel> orderByComparator)
		throws NoSuchApplicationModelException {
		CommerceApplicationModel commerceApplicationModel = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (commerceApplicationModel != null) {
			return commerceApplicationModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchApplicationModelException(msg.toString());
	}

	/**
	 * Returns the last commerce application model in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce application model, or <code>null</code> if a matching commerce application model could not be found
	 */
	@Override
	public CommerceApplicationModel fetchByCompanyId_Last(long companyId,
		OrderByComparator<CommerceApplicationModel> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<CommerceApplicationModel> list = findByCompanyId(companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce application models before and after the current commerce application model in the ordered set where companyId = &#63;.
	 *
	 * @param commerceApplicationModelId the primary key of the current commerce application model
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce application model
	 * @throws NoSuchApplicationModelException if a commerce application model with the primary key could not be found
	 */
	@Override
	public CommerceApplicationModel[] findByCompanyId_PrevAndNext(
		long commerceApplicationModelId, long companyId,
		OrderByComparator<CommerceApplicationModel> orderByComparator)
		throws NoSuchApplicationModelException {
		CommerceApplicationModel commerceApplicationModel = findByPrimaryKey(commerceApplicationModelId);

		Session session = null;

		try {
			session = openSession();

			CommerceApplicationModel[] array = new CommerceApplicationModelImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session,
					commerceApplicationModel, companyId, orderByComparator, true);

			array[1] = commerceApplicationModel;

			array[2] = getByCompanyId_PrevAndNext(session,
					commerceApplicationModel, companyId, orderByComparator,
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

	protected CommerceApplicationModel getByCompanyId_PrevAndNext(
		Session session, CommerceApplicationModel commerceApplicationModel,
		long companyId,
		OrderByComparator<CommerceApplicationModel> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEAPPLICATIONMODEL_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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
			query.append(CommerceApplicationModelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceApplicationModel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceApplicationModel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the commerce application models that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce application models that the user has permission to view
	 */
	@Override
	public List<CommerceApplicationModel> filterFindByCompanyId(long companyId) {
		return filterFindByCompanyId(companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce application models that the user has permission to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce application models
	 * @param end the upper bound of the range of commerce application models (not inclusive)
	 * @return the range of matching commerce application models that the user has permission to view
	 */
	@Override
	public List<CommerceApplicationModel> filterFindByCompanyId(
		long companyId, int start, int end) {
		return filterFindByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce application models that the user has permissions to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce application models
	 * @param end the upper bound of the range of commerce application models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce application models that the user has permission to view
	 */
	@Override
	public List<CommerceApplicationModel> filterFindByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceApplicationModel> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByCompanyId(companyId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEAPPLICATIONMODEL_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_COMMERCEAPPLICATIONMODEL_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEAPPLICATIONMODEL_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CommerceApplicationModelModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceApplicationModelModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceApplicationModel.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS,
					CommerceApplicationModelImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE,
					CommerceApplicationModelImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			return (List<CommerceApplicationModel>)QueryUtil.list(q,
				getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the commerce application models before and after the current commerce application model in the ordered set of commerce application models that the user has permission to view where companyId = &#63;.
	 *
	 * @param commerceApplicationModelId the primary key of the current commerce application model
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce application model
	 * @throws NoSuchApplicationModelException if a commerce application model with the primary key could not be found
	 */
	@Override
	public CommerceApplicationModel[] filterFindByCompanyId_PrevAndNext(
		long commerceApplicationModelId, long companyId,
		OrderByComparator<CommerceApplicationModel> orderByComparator)
		throws NoSuchApplicationModelException {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByCompanyId_PrevAndNext(commerceApplicationModelId,
				companyId, orderByComparator);
		}

		CommerceApplicationModel commerceApplicationModel = findByPrimaryKey(commerceApplicationModelId);

		Session session = null;

		try {
			session = openSession();

			CommerceApplicationModel[] array = new CommerceApplicationModelImpl[3];

			array[0] = filterGetByCompanyId_PrevAndNext(session,
					commerceApplicationModel, companyId, orderByComparator, true);

			array[1] = commerceApplicationModel;

			array[2] = filterGetByCompanyId_PrevAndNext(session,
					commerceApplicationModel, companyId, orderByComparator,
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

	protected CommerceApplicationModel filterGetByCompanyId_PrevAndNext(
		Session session, CommerceApplicationModel commerceApplicationModel,
		long companyId,
		OrderByComparator<CommerceApplicationModel> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEAPPLICATIONMODEL_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_COMMERCEAPPLICATIONMODEL_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEAPPLICATIONMODEL_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CommerceApplicationModelModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceApplicationModelModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceApplicationModel.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CommerceApplicationModelImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CommerceApplicationModelImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceApplicationModel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceApplicationModel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce application models where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (CommerceApplicationModel commerceApplicationModel : findByCompanyId(
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(commerceApplicationModel);
		}
	}

	/**
	 * Returns the number of commerce application models where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce application models
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEAPPLICATIONMODEL_WHERE);

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
	 * Returns the number of commerce application models that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce application models that the user has permission to view
	 */
	@Override
	public int filterCountByCompanyId(long companyId) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return countByCompanyId(companyId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_COMMERCEAPPLICATIONMODEL_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceApplicationModel.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "commerceApplicationModel.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMMERCEAPPLICATIONBRANDID =
		new FinderPath(CommerceApplicationModelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceApplicationModelModelImpl.FINDER_CACHE_ENABLED,
			CommerceApplicationModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCommerceApplicationBrandId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEAPPLICATIONBRANDID =
		new FinderPath(CommerceApplicationModelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceApplicationModelModelImpl.FINDER_CACHE_ENABLED,
			CommerceApplicationModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCommerceApplicationBrandId",
			new String[] { Long.class.getName() },
			CommerceApplicationModelModelImpl.COMMERCEAPPLICATIONBRANDID_COLUMN_BITMASK |
			CommerceApplicationModelModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMMERCEAPPLICATIONBRANDID =
		new FinderPath(CommerceApplicationModelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceApplicationModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceApplicationBrandId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the commerce application models where commerceApplicationBrandId = &#63;.
	 *
	 * @param commerceApplicationBrandId the commerce application brand ID
	 * @return the matching commerce application models
	 */
	@Override
	public List<CommerceApplicationModel> findByCommerceApplicationBrandId(
		long commerceApplicationBrandId) {
		return findByCommerceApplicationBrandId(commerceApplicationBrandId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce application models where commerceApplicationBrandId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceApplicationBrandId the commerce application brand ID
	 * @param start the lower bound of the range of commerce application models
	 * @param end the upper bound of the range of commerce application models (not inclusive)
	 * @return the range of matching commerce application models
	 */
	@Override
	public List<CommerceApplicationModel> findByCommerceApplicationBrandId(
		long commerceApplicationBrandId, int start, int end) {
		return findByCommerceApplicationBrandId(commerceApplicationBrandId,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce application models where commerceApplicationBrandId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceApplicationBrandId the commerce application brand ID
	 * @param start the lower bound of the range of commerce application models
	 * @param end the upper bound of the range of commerce application models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce application models
	 */
	@Override
	public List<CommerceApplicationModel> findByCommerceApplicationBrandId(
		long commerceApplicationBrandId, int start, int end,
		OrderByComparator<CommerceApplicationModel> orderByComparator) {
		return findByCommerceApplicationBrandId(commerceApplicationBrandId,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce application models where commerceApplicationBrandId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceApplicationBrandId the commerce application brand ID
	 * @param start the lower bound of the range of commerce application models
	 * @param end the upper bound of the range of commerce application models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce application models
	 */
	@Override
	public List<CommerceApplicationModel> findByCommerceApplicationBrandId(
		long commerceApplicationBrandId, int start, int end,
		OrderByComparator<CommerceApplicationModel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEAPPLICATIONBRANDID;
			finderArgs = new Object[] { commerceApplicationBrandId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMMERCEAPPLICATIONBRANDID;
			finderArgs = new Object[] {
					commerceApplicationBrandId,
					
					start, end, orderByComparator
				};
		}

		List<CommerceApplicationModel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceApplicationModel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceApplicationModel commerceApplicationModel : list) {
					if ((commerceApplicationBrandId != commerceApplicationModel.getCommerceApplicationBrandId())) {
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

			query.append(_SQL_SELECT_COMMERCEAPPLICATIONMODEL_WHERE);

			query.append(_FINDER_COLUMN_COMMERCEAPPLICATIONBRANDID_COMMERCEAPPLICATIONBRANDID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceApplicationModelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceApplicationBrandId);

				if (!pagination) {
					list = (List<CommerceApplicationModel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceApplicationModel>)QueryUtil.list(q,
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
	 * Returns the first commerce application model in the ordered set where commerceApplicationBrandId = &#63;.
	 *
	 * @param commerceApplicationBrandId the commerce application brand ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce application model
	 * @throws NoSuchApplicationModelException if a matching commerce application model could not be found
	 */
	@Override
	public CommerceApplicationModel findByCommerceApplicationBrandId_First(
		long commerceApplicationBrandId,
		OrderByComparator<CommerceApplicationModel> orderByComparator)
		throws NoSuchApplicationModelException {
		CommerceApplicationModel commerceApplicationModel = fetchByCommerceApplicationBrandId_First(commerceApplicationBrandId,
				orderByComparator);

		if (commerceApplicationModel != null) {
			return commerceApplicationModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceApplicationBrandId=");
		msg.append(commerceApplicationBrandId);

		msg.append("}");

		throw new NoSuchApplicationModelException(msg.toString());
	}

	/**
	 * Returns the first commerce application model in the ordered set where commerceApplicationBrandId = &#63;.
	 *
	 * @param commerceApplicationBrandId the commerce application brand ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce application model, or <code>null</code> if a matching commerce application model could not be found
	 */
	@Override
	public CommerceApplicationModel fetchByCommerceApplicationBrandId_First(
		long commerceApplicationBrandId,
		OrderByComparator<CommerceApplicationModel> orderByComparator) {
		List<CommerceApplicationModel> list = findByCommerceApplicationBrandId(commerceApplicationBrandId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce application model in the ordered set where commerceApplicationBrandId = &#63;.
	 *
	 * @param commerceApplicationBrandId the commerce application brand ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce application model
	 * @throws NoSuchApplicationModelException if a matching commerce application model could not be found
	 */
	@Override
	public CommerceApplicationModel findByCommerceApplicationBrandId_Last(
		long commerceApplicationBrandId,
		OrderByComparator<CommerceApplicationModel> orderByComparator)
		throws NoSuchApplicationModelException {
		CommerceApplicationModel commerceApplicationModel = fetchByCommerceApplicationBrandId_Last(commerceApplicationBrandId,
				orderByComparator);

		if (commerceApplicationModel != null) {
			return commerceApplicationModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("commerceApplicationBrandId=");
		msg.append(commerceApplicationBrandId);

		msg.append("}");

		throw new NoSuchApplicationModelException(msg.toString());
	}

	/**
	 * Returns the last commerce application model in the ordered set where commerceApplicationBrandId = &#63;.
	 *
	 * @param commerceApplicationBrandId the commerce application brand ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce application model, or <code>null</code> if a matching commerce application model could not be found
	 */
	@Override
	public CommerceApplicationModel fetchByCommerceApplicationBrandId_Last(
		long commerceApplicationBrandId,
		OrderByComparator<CommerceApplicationModel> orderByComparator) {
		int count = countByCommerceApplicationBrandId(commerceApplicationBrandId);

		if (count == 0) {
			return null;
		}

		List<CommerceApplicationModel> list = findByCommerceApplicationBrandId(commerceApplicationBrandId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce application models before and after the current commerce application model in the ordered set where commerceApplicationBrandId = &#63;.
	 *
	 * @param commerceApplicationModelId the primary key of the current commerce application model
	 * @param commerceApplicationBrandId the commerce application brand ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce application model
	 * @throws NoSuchApplicationModelException if a commerce application model with the primary key could not be found
	 */
	@Override
	public CommerceApplicationModel[] findByCommerceApplicationBrandId_PrevAndNext(
		long commerceApplicationModelId, long commerceApplicationBrandId,
		OrderByComparator<CommerceApplicationModel> orderByComparator)
		throws NoSuchApplicationModelException {
		CommerceApplicationModel commerceApplicationModel = findByPrimaryKey(commerceApplicationModelId);

		Session session = null;

		try {
			session = openSession();

			CommerceApplicationModel[] array = new CommerceApplicationModelImpl[3];

			array[0] = getByCommerceApplicationBrandId_PrevAndNext(session,
					commerceApplicationModel, commerceApplicationBrandId,
					orderByComparator, true);

			array[1] = commerceApplicationModel;

			array[2] = getByCommerceApplicationBrandId_PrevAndNext(session,
					commerceApplicationModel, commerceApplicationBrandId,
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

	protected CommerceApplicationModel getByCommerceApplicationBrandId_PrevAndNext(
		Session session, CommerceApplicationModel commerceApplicationModel,
		long commerceApplicationBrandId,
		OrderByComparator<CommerceApplicationModel> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEAPPLICATIONMODEL_WHERE);

		query.append(_FINDER_COLUMN_COMMERCEAPPLICATIONBRANDID_COMMERCEAPPLICATIONBRANDID_2);

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
			query.append(CommerceApplicationModelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceApplicationBrandId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceApplicationModel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceApplicationModel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the commerce application models that the user has permission to view where commerceApplicationBrandId = &#63;.
	 *
	 * @param commerceApplicationBrandId the commerce application brand ID
	 * @return the matching commerce application models that the user has permission to view
	 */
	@Override
	public List<CommerceApplicationModel> filterFindByCommerceApplicationBrandId(
		long commerceApplicationBrandId) {
		return filterFindByCommerceApplicationBrandId(commerceApplicationBrandId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce application models that the user has permission to view where commerceApplicationBrandId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceApplicationBrandId the commerce application brand ID
	 * @param start the lower bound of the range of commerce application models
	 * @param end the upper bound of the range of commerce application models (not inclusive)
	 * @return the range of matching commerce application models that the user has permission to view
	 */
	@Override
	public List<CommerceApplicationModel> filterFindByCommerceApplicationBrandId(
		long commerceApplicationBrandId, int start, int end) {
		return filterFindByCommerceApplicationBrandId(commerceApplicationBrandId,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce application models that the user has permissions to view where commerceApplicationBrandId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param commerceApplicationBrandId the commerce application brand ID
	 * @param start the lower bound of the range of commerce application models
	 * @param end the upper bound of the range of commerce application models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce application models that the user has permission to view
	 */
	@Override
	public List<CommerceApplicationModel> filterFindByCommerceApplicationBrandId(
		long commerceApplicationBrandId, int start, int end,
		OrderByComparator<CommerceApplicationModel> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByCommerceApplicationBrandId(commerceApplicationBrandId,
				start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEAPPLICATIONMODEL_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_COMMERCEAPPLICATIONMODEL_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMMERCEAPPLICATIONBRANDID_COMMERCEAPPLICATIONBRANDID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEAPPLICATIONMODEL_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CommerceApplicationModelModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceApplicationModelModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceApplicationModel.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS,
					CommerceApplicationModelImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE,
					CommerceApplicationModelImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(commerceApplicationBrandId);

			return (List<CommerceApplicationModel>)QueryUtil.list(q,
				getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the commerce application models before and after the current commerce application model in the ordered set of commerce application models that the user has permission to view where commerceApplicationBrandId = &#63;.
	 *
	 * @param commerceApplicationModelId the primary key of the current commerce application model
	 * @param commerceApplicationBrandId the commerce application brand ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce application model
	 * @throws NoSuchApplicationModelException if a commerce application model with the primary key could not be found
	 */
	@Override
	public CommerceApplicationModel[] filterFindByCommerceApplicationBrandId_PrevAndNext(
		long commerceApplicationModelId, long commerceApplicationBrandId,
		OrderByComparator<CommerceApplicationModel> orderByComparator)
		throws NoSuchApplicationModelException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByCommerceApplicationBrandId_PrevAndNext(commerceApplicationModelId,
				commerceApplicationBrandId, orderByComparator);
		}

		CommerceApplicationModel commerceApplicationModel = findByPrimaryKey(commerceApplicationModelId);

		Session session = null;

		try {
			session = openSession();

			CommerceApplicationModel[] array = new CommerceApplicationModelImpl[3];

			array[0] = filterGetByCommerceApplicationBrandId_PrevAndNext(session,
					commerceApplicationModel, commerceApplicationBrandId,
					orderByComparator, true);

			array[1] = commerceApplicationModel;

			array[2] = filterGetByCommerceApplicationBrandId_PrevAndNext(session,
					commerceApplicationModel, commerceApplicationBrandId,
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

	protected CommerceApplicationModel filterGetByCommerceApplicationBrandId_PrevAndNext(
		Session session, CommerceApplicationModel commerceApplicationModel,
		long commerceApplicationBrandId,
		OrderByComparator<CommerceApplicationModel> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEAPPLICATIONMODEL_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_COMMERCEAPPLICATIONMODEL_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMMERCEAPPLICATIONBRANDID_COMMERCEAPPLICATIONBRANDID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEAPPLICATIONMODEL_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CommerceApplicationModelModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceApplicationModelModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceApplicationModel.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CommerceApplicationModelImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CommerceApplicationModelImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(commerceApplicationBrandId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceApplicationModel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceApplicationModel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce application models where commerceApplicationBrandId = &#63; from the database.
	 *
	 * @param commerceApplicationBrandId the commerce application brand ID
	 */
	@Override
	public void removeByCommerceApplicationBrandId(
		long commerceApplicationBrandId) {
		for (CommerceApplicationModel commerceApplicationModel : findByCommerceApplicationBrandId(
				commerceApplicationBrandId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(commerceApplicationModel);
		}
	}

	/**
	 * Returns the number of commerce application models where commerceApplicationBrandId = &#63;.
	 *
	 * @param commerceApplicationBrandId the commerce application brand ID
	 * @return the number of matching commerce application models
	 */
	@Override
	public int countByCommerceApplicationBrandId(
		long commerceApplicationBrandId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMMERCEAPPLICATIONBRANDID;

		Object[] finderArgs = new Object[] { commerceApplicationBrandId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEAPPLICATIONMODEL_WHERE);

			query.append(_FINDER_COLUMN_COMMERCEAPPLICATIONBRANDID_COMMERCEAPPLICATIONBRANDID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(commerceApplicationBrandId);

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
	 * Returns the number of commerce application models that the user has permission to view where commerceApplicationBrandId = &#63;.
	 *
	 * @param commerceApplicationBrandId the commerce application brand ID
	 * @return the number of matching commerce application models that the user has permission to view
	 */
	@Override
	public int filterCountByCommerceApplicationBrandId(
		long commerceApplicationBrandId) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByCommerceApplicationBrandId(commerceApplicationBrandId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_COMMERCEAPPLICATIONMODEL_WHERE);

		query.append(_FINDER_COLUMN_COMMERCEAPPLICATIONBRANDID_COMMERCEAPPLICATIONBRANDID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceApplicationModel.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(commerceApplicationBrandId);

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

	private static final String _FINDER_COLUMN_COMMERCEAPPLICATIONBRANDID_COMMERCEAPPLICATIONBRANDID_2 =
		"commerceApplicationModel.commerceApplicationBrandId = ?";

	public CommerceApplicationModelPersistenceImpl() {
		setModelClass(CommerceApplicationModel.class);
	}

	/**
	 * Caches the commerce application model in the entity cache if it is enabled.
	 *
	 * @param commerceApplicationModel the commerce application model
	 */
	@Override
	public void cacheResult(CommerceApplicationModel commerceApplicationModel) {
		entityCache.putResult(CommerceApplicationModelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceApplicationModelImpl.class,
			commerceApplicationModel.getPrimaryKey(), commerceApplicationModel);

		commerceApplicationModel.resetOriginalValues();
	}

	/**
	 * Caches the commerce application models in the entity cache if it is enabled.
	 *
	 * @param commerceApplicationModels the commerce application models
	 */
	@Override
	public void cacheResult(
		List<CommerceApplicationModel> commerceApplicationModels) {
		for (CommerceApplicationModel commerceApplicationModel : commerceApplicationModels) {
			if (entityCache.getResult(
						CommerceApplicationModelModelImpl.ENTITY_CACHE_ENABLED,
						CommerceApplicationModelImpl.class,
						commerceApplicationModel.getPrimaryKey()) == null) {
				cacheResult(commerceApplicationModel);
			}
			else {
				commerceApplicationModel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce application models.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceApplicationModelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce application model.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceApplicationModel commerceApplicationModel) {
		entityCache.removeResult(CommerceApplicationModelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceApplicationModelImpl.class,
			commerceApplicationModel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<CommerceApplicationModel> commerceApplicationModels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceApplicationModel commerceApplicationModel : commerceApplicationModels) {
			entityCache.removeResult(CommerceApplicationModelModelImpl.ENTITY_CACHE_ENABLED,
				CommerceApplicationModelImpl.class,
				commerceApplicationModel.getPrimaryKey());
		}
	}

	/**
	 * Creates a new commerce application model with the primary key. Does not add the commerce application model to the database.
	 *
	 * @param commerceApplicationModelId the primary key for the new commerce application model
	 * @return the new commerce application model
	 */
	@Override
	public CommerceApplicationModel create(long commerceApplicationModelId) {
		CommerceApplicationModel commerceApplicationModel = new CommerceApplicationModelImpl();

		commerceApplicationModel.setNew(true);
		commerceApplicationModel.setPrimaryKey(commerceApplicationModelId);

		commerceApplicationModel.setCompanyId(companyProvider.getCompanyId());

		return commerceApplicationModel;
	}

	/**
	 * Removes the commerce application model with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceApplicationModelId the primary key of the commerce application model
	 * @return the commerce application model that was removed
	 * @throws NoSuchApplicationModelException if a commerce application model with the primary key could not be found
	 */
	@Override
	public CommerceApplicationModel remove(long commerceApplicationModelId)
		throws NoSuchApplicationModelException {
		return remove((Serializable)commerceApplicationModelId);
	}

	/**
	 * Removes the commerce application model with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce application model
	 * @return the commerce application model that was removed
	 * @throws NoSuchApplicationModelException if a commerce application model with the primary key could not be found
	 */
	@Override
	public CommerceApplicationModel remove(Serializable primaryKey)
		throws NoSuchApplicationModelException {
		Session session = null;

		try {
			session = openSession();

			CommerceApplicationModel commerceApplicationModel = (CommerceApplicationModel)session.get(CommerceApplicationModelImpl.class,
					primaryKey);

			if (commerceApplicationModel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchApplicationModelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commerceApplicationModel);
		}
		catch (NoSuchApplicationModelException nsee) {
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
	protected CommerceApplicationModel removeImpl(
		CommerceApplicationModel commerceApplicationModel) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceApplicationModel)) {
				commerceApplicationModel = (CommerceApplicationModel)session.get(CommerceApplicationModelImpl.class,
						commerceApplicationModel.getPrimaryKeyObj());
			}

			if (commerceApplicationModel != null) {
				session.delete(commerceApplicationModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceApplicationModel != null) {
			clearCache(commerceApplicationModel);
		}

		return commerceApplicationModel;
	}

	@Override
	public CommerceApplicationModel updateImpl(
		CommerceApplicationModel commerceApplicationModel) {
		boolean isNew = commerceApplicationModel.isNew();

		if (!(commerceApplicationModel instanceof CommerceApplicationModelModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceApplicationModel.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(commerceApplicationModel);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceApplicationModel proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceApplicationModel implementation " +
				commerceApplicationModel.getClass());
		}

		CommerceApplicationModelModelImpl commerceApplicationModelModelImpl = (CommerceApplicationModelModelImpl)commerceApplicationModel;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceApplicationModel.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceApplicationModel.setCreateDate(now);
			}
			else {
				commerceApplicationModel.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!commerceApplicationModelModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceApplicationModel.setModifiedDate(now);
			}
			else {
				commerceApplicationModel.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceApplicationModel.isNew()) {
				session.save(commerceApplicationModel);

				commerceApplicationModel.setNew(false);
			}
			else {
				commerceApplicationModel = (CommerceApplicationModel)session.merge(commerceApplicationModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceApplicationModelModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					commerceApplicationModelModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
				args);

			args = new Object[] {
					commerceApplicationModelModelImpl.getCommerceApplicationBrandId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEAPPLICATIONBRANDID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEAPPLICATIONBRANDID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((commerceApplicationModelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceApplicationModelModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] {
						commerceApplicationModelModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((commerceApplicationModelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEAPPLICATIONBRANDID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceApplicationModelModelImpl.getOriginalCommerceApplicationBrandId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEAPPLICATIONBRANDID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEAPPLICATIONBRANDID,
					args);

				args = new Object[] {
						commerceApplicationModelModelImpl.getCommerceApplicationBrandId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMMERCEAPPLICATIONBRANDID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMMERCEAPPLICATIONBRANDID,
					args);
			}
		}

		entityCache.putResult(CommerceApplicationModelModelImpl.ENTITY_CACHE_ENABLED,
			CommerceApplicationModelImpl.class,
			commerceApplicationModel.getPrimaryKey(), commerceApplicationModel,
			false);

		commerceApplicationModel.resetOriginalValues();

		return commerceApplicationModel;
	}

	/**
	 * Returns the commerce application model with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce application model
	 * @return the commerce application model
	 * @throws NoSuchApplicationModelException if a commerce application model with the primary key could not be found
	 */
	@Override
	public CommerceApplicationModel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchApplicationModelException {
		CommerceApplicationModel commerceApplicationModel = fetchByPrimaryKey(primaryKey);

		if (commerceApplicationModel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchApplicationModelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commerceApplicationModel;
	}

	/**
	 * Returns the commerce application model with the primary key or throws a {@link NoSuchApplicationModelException} if it could not be found.
	 *
	 * @param commerceApplicationModelId the primary key of the commerce application model
	 * @return the commerce application model
	 * @throws NoSuchApplicationModelException if a commerce application model with the primary key could not be found
	 */
	@Override
	public CommerceApplicationModel findByPrimaryKey(
		long commerceApplicationModelId) throws NoSuchApplicationModelException {
		return findByPrimaryKey((Serializable)commerceApplicationModelId);
	}

	/**
	 * Returns the commerce application model with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce application model
	 * @return the commerce application model, or <code>null</code> if a commerce application model with the primary key could not be found
	 */
	@Override
	public CommerceApplicationModel fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CommerceApplicationModelModelImpl.ENTITY_CACHE_ENABLED,
				CommerceApplicationModelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceApplicationModel commerceApplicationModel = (CommerceApplicationModel)serializable;

		if (commerceApplicationModel == null) {
			Session session = null;

			try {
				session = openSession();

				commerceApplicationModel = (CommerceApplicationModel)session.get(CommerceApplicationModelImpl.class,
						primaryKey);

				if (commerceApplicationModel != null) {
					cacheResult(commerceApplicationModel);
				}
				else {
					entityCache.putResult(CommerceApplicationModelModelImpl.ENTITY_CACHE_ENABLED,
						CommerceApplicationModelImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommerceApplicationModelModelImpl.ENTITY_CACHE_ENABLED,
					CommerceApplicationModelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceApplicationModel;
	}

	/**
	 * Returns the commerce application model with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceApplicationModelId the primary key of the commerce application model
	 * @return the commerce application model, or <code>null</code> if a commerce application model with the primary key could not be found
	 */
	@Override
	public CommerceApplicationModel fetchByPrimaryKey(
		long commerceApplicationModelId) {
		return fetchByPrimaryKey((Serializable)commerceApplicationModelId);
	}

	@Override
	public Map<Serializable, CommerceApplicationModel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceApplicationModel> map = new HashMap<Serializable, CommerceApplicationModel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceApplicationModel commerceApplicationModel = fetchByPrimaryKey(primaryKey);

			if (commerceApplicationModel != null) {
				map.put(primaryKey, commerceApplicationModel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CommerceApplicationModelModelImpl.ENTITY_CACHE_ENABLED,
					CommerceApplicationModelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceApplicationModel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMMERCEAPPLICATIONMODEL_WHERE_PKS_IN);

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

			for (CommerceApplicationModel commerceApplicationModel : (List<CommerceApplicationModel>)q.list()) {
				map.put(commerceApplicationModel.getPrimaryKeyObj(),
					commerceApplicationModel);

				cacheResult(commerceApplicationModel);

				uncachedPrimaryKeys.remove(commerceApplicationModel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CommerceApplicationModelModelImpl.ENTITY_CACHE_ENABLED,
					CommerceApplicationModelImpl.class, primaryKey, nullModel);
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
	 * Returns all the commerce application models.
	 *
	 * @return the commerce application models
	 */
	@Override
	public List<CommerceApplicationModel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce application models.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce application models
	 * @param end the upper bound of the range of commerce application models (not inclusive)
	 * @return the range of commerce application models
	 */
	@Override
	public List<CommerceApplicationModel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce application models.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce application models
	 * @param end the upper bound of the range of commerce application models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce application models
	 */
	@Override
	public List<CommerceApplicationModel> findAll(int start, int end,
		OrderByComparator<CommerceApplicationModel> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce application models.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceApplicationModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce application models
	 * @param end the upper bound of the range of commerce application models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce application models
	 */
	@Override
	public List<CommerceApplicationModel> findAll(int start, int end,
		OrderByComparator<CommerceApplicationModel> orderByComparator,
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

		List<CommerceApplicationModel> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceApplicationModel>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEAPPLICATIONMODEL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEAPPLICATIONMODEL;

				if (pagination) {
					sql = sql.concat(CommerceApplicationModelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceApplicationModel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceApplicationModel>)QueryUtil.list(q,
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
	 * Removes all the commerce application models from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceApplicationModel commerceApplicationModel : findAll()) {
			remove(commerceApplicationModel);
		}
	}

	/**
	 * Returns the number of commerce application models.
	 *
	 * @return the number of commerce application models
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCEAPPLICATIONMODEL);

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
	protected Map<String, Integer> getTableColumnsMap() {
		return CommerceApplicationModelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce application model persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CommerceApplicationModelImpl.class.getName());
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
	private static final String _SQL_SELECT_COMMERCEAPPLICATIONMODEL = "SELECT commerceApplicationModel FROM CommerceApplicationModel commerceApplicationModel";
	private static final String _SQL_SELECT_COMMERCEAPPLICATIONMODEL_WHERE_PKS_IN =
		"SELECT commerceApplicationModel FROM CommerceApplicationModel commerceApplicationModel WHERE commerceApplicationModelId IN (";
	private static final String _SQL_SELECT_COMMERCEAPPLICATIONMODEL_WHERE = "SELECT commerceApplicationModel FROM CommerceApplicationModel commerceApplicationModel WHERE ";
	private static final String _SQL_COUNT_COMMERCEAPPLICATIONMODEL = "SELECT COUNT(commerceApplicationModel) FROM CommerceApplicationModel commerceApplicationModel";
	private static final String _SQL_COUNT_COMMERCEAPPLICATIONMODEL_WHERE = "SELECT COUNT(commerceApplicationModel) FROM CommerceApplicationModel commerceApplicationModel WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "commerceApplicationModel.commerceApplicationModelId";
	private static final String _FILTER_SQL_SELECT_COMMERCEAPPLICATIONMODEL_WHERE =
		"SELECT DISTINCT {commerceApplicationModel.*} FROM CommerceApplicationModel commerceApplicationModel WHERE ";
	private static final String _FILTER_SQL_SELECT_COMMERCEAPPLICATIONMODEL_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {CommerceApplicationModel.*} FROM (SELECT DISTINCT commerceApplicationModel.commerceApplicationModelId FROM CommerceApplicationModel commerceApplicationModel WHERE ";
	private static final String _FILTER_SQL_SELECT_COMMERCEAPPLICATIONMODEL_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN CommerceApplicationModel ON TEMP_TABLE.commerceApplicationModelId = CommerceApplicationModel.commerceApplicationModelId";
	private static final String _FILTER_SQL_COUNT_COMMERCEAPPLICATIONMODEL_WHERE =
		"SELECT COUNT(DISTINCT commerceApplicationModel.commerceApplicationModelId) AS COUNT_VALUE FROM CommerceApplicationModel commerceApplicationModel WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "commerceApplicationModel";
	private static final String _FILTER_ENTITY_TABLE = "CommerceApplicationModel";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceApplicationModel.";
	private static final String _ORDER_BY_ENTITY_TABLE = "CommerceApplicationModel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommerceApplicationModel exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CommerceApplicationModel exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CommerceApplicationModelPersistenceImpl.class);
}