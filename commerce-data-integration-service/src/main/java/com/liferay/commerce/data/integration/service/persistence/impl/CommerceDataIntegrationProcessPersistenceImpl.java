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

package com.liferay.commerce.data.integration.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.data.integration.exception.NoSuchDataIntegrationProcessException;
import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcess;
import com.liferay.commerce.data.integration.model.impl.CommerceDataIntegrationProcessImpl;
import com.liferay.commerce.data.integration.model.impl.CommerceDataIntegrationProcessModelImpl;
import com.liferay.commerce.data.integration.service.persistence.CommerceDataIntegrationProcessPersistence;
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
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
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
 * The persistence implementation for the commerce data integration process service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
@ProviderType
public class CommerceDataIntegrationProcessPersistenceImpl
	extends BasePersistenceImpl<CommerceDataIntegrationProcess>
	implements CommerceDataIntegrationProcessPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommerceDataIntegrationProcessUtil</code> to access the commerce data integration process persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommerceDataIntegrationProcessImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the commerce data integration processes where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce data integration processes
	 */
	@Override
	public List<CommerceDataIntegrationProcess> findByCompanyId(
		long companyId) {

		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce data integration processes where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDataIntegrationProcessModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce data integration processes
	 * @param end the upper bound of the range of commerce data integration processes (not inclusive)
	 * @return the range of matching commerce data integration processes
	 */
	@Override
	public List<CommerceDataIntegrationProcess> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce data integration processes where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDataIntegrationProcessModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce data integration processes
	 * @param end the upper bound of the range of commerce data integration processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce data integration processes
	 */
	@Override
	public List<CommerceDataIntegrationProcess> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceDataIntegrationProcess> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce data integration processes where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDataIntegrationProcessModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce data integration processes
	 * @param end the upper bound of the range of commerce data integration processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce data integration processes
	 */
	@Override
	public List<CommerceDataIntegrationProcess> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceDataIntegrationProcess> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByCompanyId;
				finderArgs = new Object[] {companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCompanyId;
			finderArgs = new Object[] {
				companyId, start, end, orderByComparator
			};
		}

		List<CommerceDataIntegrationProcess> list = null;

		if (useFinderCache) {
			list = (List<CommerceDataIntegrationProcess>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceDataIntegrationProcess
						commerceDataIntegrationProcess : list) {

					if ((companyId !=
							commerceDataIntegrationProcess.getCompanyId())) {

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

			query.append(_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(
					CommerceDataIntegrationProcessModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<CommerceDataIntegrationProcess>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceDataIntegrationProcess>)QueryUtil.list(
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
	 * Returns the first commerce data integration process in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce data integration process
	 * @throws NoSuchDataIntegrationProcessException if a matching commerce data integration process could not be found
	 */
	@Override
	public CommerceDataIntegrationProcess findByCompanyId_First(
			long companyId,
			OrderByComparator<CommerceDataIntegrationProcess> orderByComparator)
		throws NoSuchDataIntegrationProcessException {

		CommerceDataIntegrationProcess commerceDataIntegrationProcess =
			fetchByCompanyId_First(companyId, orderByComparator);

		if (commerceDataIntegrationProcess != null) {
			return commerceDataIntegrationProcess;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDataIntegrationProcessException(msg.toString());
	}

	/**
	 * Returns the first commerce data integration process in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce data integration process, or <code>null</code> if a matching commerce data integration process could not be found
	 */
	@Override
	public CommerceDataIntegrationProcess fetchByCompanyId_First(
		long companyId,
		OrderByComparator<CommerceDataIntegrationProcess> orderByComparator) {

		List<CommerceDataIntegrationProcess> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce data integration process in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce data integration process
	 * @throws NoSuchDataIntegrationProcessException if a matching commerce data integration process could not be found
	 */
	@Override
	public CommerceDataIntegrationProcess findByCompanyId_Last(
			long companyId,
			OrderByComparator<CommerceDataIntegrationProcess> orderByComparator)
		throws NoSuchDataIntegrationProcessException {

		CommerceDataIntegrationProcess commerceDataIntegrationProcess =
			fetchByCompanyId_Last(companyId, orderByComparator);

		if (commerceDataIntegrationProcess != null) {
			return commerceDataIntegrationProcess;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDataIntegrationProcessException(msg.toString());
	}

	/**
	 * Returns the last commerce data integration process in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce data integration process, or <code>null</code> if a matching commerce data integration process could not be found
	 */
	@Override
	public CommerceDataIntegrationProcess fetchByCompanyId_Last(
		long companyId,
		OrderByComparator<CommerceDataIntegrationProcess> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<CommerceDataIntegrationProcess> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce data integration processes before and after the current commerce data integration process in the ordered set where companyId = &#63;.
	 *
	 * @param commerceDataIntegrationProcessId the primary key of the current commerce data integration process
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce data integration process
	 * @throws NoSuchDataIntegrationProcessException if a commerce data integration process with the primary key could not be found
	 */
	@Override
	public CommerceDataIntegrationProcess[] findByCompanyId_PrevAndNext(
			long commerceDataIntegrationProcessId, long companyId,
			OrderByComparator<CommerceDataIntegrationProcess> orderByComparator)
		throws NoSuchDataIntegrationProcessException {

		CommerceDataIntegrationProcess commerceDataIntegrationProcess =
			findByPrimaryKey(commerceDataIntegrationProcessId);

		Session session = null;

		try {
			session = openSession();

			CommerceDataIntegrationProcess[] array =
				new CommerceDataIntegrationProcessImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, commerceDataIntegrationProcess, companyId,
				orderByComparator, true);

			array[1] = commerceDataIntegrationProcess;

			array[2] = getByCompanyId_PrevAndNext(
				session, commerceDataIntegrationProcess, companyId,
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

	protected CommerceDataIntegrationProcess getByCompanyId_PrevAndNext(
		Session session,
		CommerceDataIntegrationProcess commerceDataIntegrationProcess,
		long companyId,
		OrderByComparator<CommerceDataIntegrationProcess> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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
			query.append(CommerceDataIntegrationProcessModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceDataIntegrationProcess)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceDataIntegrationProcess> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the commerce data integration processes that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce data integration processes that the user has permission to view
	 */
	@Override
	public List<CommerceDataIntegrationProcess> filterFindByCompanyId(
		long companyId) {

		return filterFindByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce data integration processes that the user has permission to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDataIntegrationProcessModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce data integration processes
	 * @param end the upper bound of the range of commerce data integration processes (not inclusive)
	 * @return the range of matching commerce data integration processes that the user has permission to view
	 */
	@Override
	public List<CommerceDataIntegrationProcess> filterFindByCompanyId(
		long companyId, int start, int end) {

		return filterFindByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce data integration processes that the user has permissions to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDataIntegrationProcessModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce data integration processes
	 * @param end the upper bound of the range of commerce data integration processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce data integration processes that the user has permission to view
	 */
	@Override
	public List<CommerceDataIntegrationProcess> filterFindByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceDataIntegrationProcess> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByCompanyId(companyId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				3 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(
					CommerceDataIntegrationProcessModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(
					CommerceDataIntegrationProcessModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceDataIntegrationProcess.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(
					_FILTER_ENTITY_ALIAS,
					CommerceDataIntegrationProcessImpl.class);
			}
			else {
				q.addEntity(
					_FILTER_ENTITY_TABLE,
					CommerceDataIntegrationProcessImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			return (List<CommerceDataIntegrationProcess>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the commerce data integration processes before and after the current commerce data integration process in the ordered set of commerce data integration processes that the user has permission to view where companyId = &#63;.
	 *
	 * @param commerceDataIntegrationProcessId the primary key of the current commerce data integration process
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce data integration process
	 * @throws NoSuchDataIntegrationProcessException if a commerce data integration process with the primary key could not be found
	 */
	@Override
	public CommerceDataIntegrationProcess[] filterFindByCompanyId_PrevAndNext(
			long commerceDataIntegrationProcessId, long companyId,
			OrderByComparator<CommerceDataIntegrationProcess> orderByComparator)
		throws NoSuchDataIntegrationProcessException {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByCompanyId_PrevAndNext(
				commerceDataIntegrationProcessId, companyId, orderByComparator);
		}

		CommerceDataIntegrationProcess commerceDataIntegrationProcess =
			findByPrimaryKey(commerceDataIntegrationProcessId);

		Session session = null;

		try {
			session = openSession();

			CommerceDataIntegrationProcess[] array =
				new CommerceDataIntegrationProcessImpl[3];

			array[0] = filterGetByCompanyId_PrevAndNext(
				session, commerceDataIntegrationProcess, companyId,
				orderByComparator, true);

			array[1] = commerceDataIntegrationProcess;

			array[2] = filterGetByCompanyId_PrevAndNext(
				session, commerceDataIntegrationProcess, companyId,
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

	protected CommerceDataIntegrationProcess filterGetByCompanyId_PrevAndNext(
		Session session,
		CommerceDataIntegrationProcess commerceDataIntegrationProcess,
		long companyId,
		OrderByComparator<CommerceDataIntegrationProcess> orderByComparator,
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

		if (getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
				query.append(
					CommerceDataIntegrationProcessModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(
					CommerceDataIntegrationProcessModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceDataIntegrationProcess.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(
				_FILTER_ENTITY_ALIAS, CommerceDataIntegrationProcessImpl.class);
		}
		else {
			q.addEntity(
				_FILTER_ENTITY_TABLE, CommerceDataIntegrationProcessImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceDataIntegrationProcess)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceDataIntegrationProcess> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce data integration processes where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (CommerceDataIntegrationProcess commerceDataIntegrationProcess :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commerceDataIntegrationProcess);
		}
	}

	/**
	 * Returns the number of commerce data integration processes where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce data integration processes
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] {companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEDATAINTEGRATIONPROCESS_WHERE);

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
	 * Returns the number of commerce data integration processes that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce data integration processes that the user has permission to view
	 */
	@Override
	public int filterCountByCompanyId(long companyId) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return countByCompanyId(companyId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_COMMERCEDATAINTEGRATIONPROCESS_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceDataIntegrationProcess.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 =
		"commerceDataIntegrationProcess.companyId = ?";

	private FinderPath _finderPathFetchByC_N;
	private FinderPath _finderPathCountByC_N;

	/**
	 * Returns the commerce data integration process where companyId = &#63; and name = &#63; or throws a <code>NoSuchDataIntegrationProcessException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching commerce data integration process
	 * @throws NoSuchDataIntegrationProcessException if a matching commerce data integration process could not be found
	 */
	@Override
	public CommerceDataIntegrationProcess findByC_N(long companyId, String name)
		throws NoSuchDataIntegrationProcessException {

		CommerceDataIntegrationProcess commerceDataIntegrationProcess =
			fetchByC_N(companyId, name);

		if (commerceDataIntegrationProcess == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", name=");
			msg.append(name);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDataIntegrationProcessException(msg.toString());
		}

		return commerceDataIntegrationProcess;
	}

	/**
	 * Returns the commerce data integration process where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching commerce data integration process, or <code>null</code> if a matching commerce data integration process could not be found
	 */
	@Override
	public CommerceDataIntegrationProcess fetchByC_N(
		long companyId, String name) {

		return fetchByC_N(companyId, name, true);
	}

	/**
	 * Returns the commerce data integration process where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce data integration process, or <code>null</code> if a matching commerce data integration process could not be found
	 */
	@Override
	public CommerceDataIntegrationProcess fetchByC_N(
		long companyId, String name, boolean useFinderCache) {

		name = Objects.toString(name, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {companyId, name};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_N, finderArgs, this);
		}

		if (result instanceof CommerceDataIntegrationProcess) {
			CommerceDataIntegrationProcess commerceDataIntegrationProcess =
				(CommerceDataIntegrationProcess)result;

			if ((companyId != commerceDataIntegrationProcess.getCompanyId()) ||
				!Objects.equals(
					name, commerceDataIntegrationProcess.getName())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS_WHERE);

			query.append(_FINDER_COLUMN_C_N_COMPANYID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_C_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				List<CommerceDataIntegrationProcess> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByC_N, finderArgs, list);
					}
				}
				else {
					CommerceDataIntegrationProcess
						commerceDataIntegrationProcess = list.get(0);

					result = commerceDataIntegrationProcess;

					cacheResult(commerceDataIntegrationProcess);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(_finderPathFetchByC_N, finderArgs);
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
			return (CommerceDataIntegrationProcess)result;
		}
	}

	/**
	 * Removes the commerce data integration process where companyId = &#63; and name = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the commerce data integration process that was removed
	 */
	@Override
	public CommerceDataIntegrationProcess removeByC_N(
			long companyId, String name)
		throws NoSuchDataIntegrationProcessException {

		CommerceDataIntegrationProcess commerceDataIntegrationProcess =
			findByC_N(companyId, name);

		return remove(commerceDataIntegrationProcess);
	}

	/**
	 * Returns the number of commerce data integration processes where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the number of matching commerce data integration processes
	 */
	@Override
	public int countByC_N(long companyId, String name) {
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathCountByC_N;

		Object[] finderArgs = new Object[] {companyId, name};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEDATAINTEGRATIONPROCESS_WHERE);

			query.append(_FINDER_COLUMN_C_N_COMPANYID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_C_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
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

	private static final String _FINDER_COLUMN_C_N_COMPANYID_2 =
		"commerceDataIntegrationProcess.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_N_NAME_2 =
		"commerceDataIntegrationProcess.name = ?";

	private static final String _FINDER_COLUMN_C_N_NAME_3 =
		"(commerceDataIntegrationProcess.name IS NULL OR commerceDataIntegrationProcess.name = '')";

	private FinderPath _finderPathWithPaginationFindByC_T;
	private FinderPath _finderPathWithoutPaginationFindByC_T;
	private FinderPath _finderPathCountByC_T;

	/**
	 * Returns all the commerce data integration processes where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @return the matching commerce data integration processes
	 */
	@Override
	public List<CommerceDataIntegrationProcess> findByC_T(
		long companyId, String type) {

		return findByC_T(
			companyId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce data integration processes where companyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDataIntegrationProcessModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param start the lower bound of the range of commerce data integration processes
	 * @param end the upper bound of the range of commerce data integration processes (not inclusive)
	 * @return the range of matching commerce data integration processes
	 */
	@Override
	public List<CommerceDataIntegrationProcess> findByC_T(
		long companyId, String type, int start, int end) {

		return findByC_T(companyId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce data integration processes where companyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDataIntegrationProcessModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param start the lower bound of the range of commerce data integration processes
	 * @param end the upper bound of the range of commerce data integration processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce data integration processes
	 */
	@Override
	public List<CommerceDataIntegrationProcess> findByC_T(
		long companyId, String type, int start, int end,
		OrderByComparator<CommerceDataIntegrationProcess> orderByComparator) {

		return findByC_T(companyId, type, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce data integration processes where companyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDataIntegrationProcessModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param start the lower bound of the range of commerce data integration processes
	 * @param end the upper bound of the range of commerce data integration processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce data integration processes
	 */
	@Override
	public List<CommerceDataIntegrationProcess> findByC_T(
		long companyId, String type, int start, int end,
		OrderByComparator<CommerceDataIntegrationProcess> orderByComparator,
		boolean useFinderCache) {

		type = Objects.toString(type, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByC_T;
				finderArgs = new Object[] {companyId, type};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByC_T;
			finderArgs = new Object[] {
				companyId, type, start, end, orderByComparator
			};
		}

		List<CommerceDataIntegrationProcess> list = null;

		if (useFinderCache) {
			list = (List<CommerceDataIntegrationProcess>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceDataIntegrationProcess
						commerceDataIntegrationProcess : list) {

					if ((companyId !=
							commerceDataIntegrationProcess.getCompanyId()) ||
						!type.equals(
							commerceDataIntegrationProcess.getType())) {

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

			query.append(_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS_WHERE);

			query.append(_FINDER_COLUMN_C_T_COMPANYID_2);

			boolean bindType = false;

			if (type.isEmpty()) {
				query.append(_FINDER_COLUMN_C_T_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_C_T_TYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(
					CommerceDataIntegrationProcessModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindType) {
					qPos.add(type);
				}

				if (!pagination) {
					list = (List<CommerceDataIntegrationProcess>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceDataIntegrationProcess>)QueryUtil.list(
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
	 * Returns the first commerce data integration process in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce data integration process
	 * @throws NoSuchDataIntegrationProcessException if a matching commerce data integration process could not be found
	 */
	@Override
	public CommerceDataIntegrationProcess findByC_T_First(
			long companyId, String type,
			OrderByComparator<CommerceDataIntegrationProcess> orderByComparator)
		throws NoSuchDataIntegrationProcessException {

		CommerceDataIntegrationProcess commerceDataIntegrationProcess =
			fetchByC_T_First(companyId, type, orderByComparator);

		if (commerceDataIntegrationProcess != null) {
			return commerceDataIntegrationProcess;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchDataIntegrationProcessException(msg.toString());
	}

	/**
	 * Returns the first commerce data integration process in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce data integration process, or <code>null</code> if a matching commerce data integration process could not be found
	 */
	@Override
	public CommerceDataIntegrationProcess fetchByC_T_First(
		long companyId, String type,
		OrderByComparator<CommerceDataIntegrationProcess> orderByComparator) {

		List<CommerceDataIntegrationProcess> list = findByC_T(
			companyId, type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce data integration process in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce data integration process
	 * @throws NoSuchDataIntegrationProcessException if a matching commerce data integration process could not be found
	 */
	@Override
	public CommerceDataIntegrationProcess findByC_T_Last(
			long companyId, String type,
			OrderByComparator<CommerceDataIntegrationProcess> orderByComparator)
		throws NoSuchDataIntegrationProcessException {

		CommerceDataIntegrationProcess commerceDataIntegrationProcess =
			fetchByC_T_Last(companyId, type, orderByComparator);

		if (commerceDataIntegrationProcess != null) {
			return commerceDataIntegrationProcess;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchDataIntegrationProcessException(msg.toString());
	}

	/**
	 * Returns the last commerce data integration process in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce data integration process, or <code>null</code> if a matching commerce data integration process could not be found
	 */
	@Override
	public CommerceDataIntegrationProcess fetchByC_T_Last(
		long companyId, String type,
		OrderByComparator<CommerceDataIntegrationProcess> orderByComparator) {

		int count = countByC_T(companyId, type);

		if (count == 0) {
			return null;
		}

		List<CommerceDataIntegrationProcess> list = findByC_T(
			companyId, type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce data integration processes before and after the current commerce data integration process in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param commerceDataIntegrationProcessId the primary key of the current commerce data integration process
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce data integration process
	 * @throws NoSuchDataIntegrationProcessException if a commerce data integration process with the primary key could not be found
	 */
	@Override
	public CommerceDataIntegrationProcess[] findByC_T_PrevAndNext(
			long commerceDataIntegrationProcessId, long companyId, String type,
			OrderByComparator<CommerceDataIntegrationProcess> orderByComparator)
		throws NoSuchDataIntegrationProcessException {

		type = Objects.toString(type, "");

		CommerceDataIntegrationProcess commerceDataIntegrationProcess =
			findByPrimaryKey(commerceDataIntegrationProcessId);

		Session session = null;

		try {
			session = openSession();

			CommerceDataIntegrationProcess[] array =
				new CommerceDataIntegrationProcessImpl[3];

			array[0] = getByC_T_PrevAndNext(
				session, commerceDataIntegrationProcess, companyId, type,
				orderByComparator, true);

			array[1] = commerceDataIntegrationProcess;

			array[2] = getByC_T_PrevAndNext(
				session, commerceDataIntegrationProcess, companyId, type,
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

	protected CommerceDataIntegrationProcess getByC_T_PrevAndNext(
		Session session,
		CommerceDataIntegrationProcess commerceDataIntegrationProcess,
		long companyId, String type,
		OrderByComparator<CommerceDataIntegrationProcess> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS_WHERE);

		query.append(_FINDER_COLUMN_C_T_COMPANYID_2);

		boolean bindType = false;

		if (type.isEmpty()) {
			query.append(_FINDER_COLUMN_C_T_TYPE_3);
		}
		else {
			bindType = true;

			query.append(_FINDER_COLUMN_C_T_TYPE_2);
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
			query.append(CommerceDataIntegrationProcessModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (bindType) {
			qPos.add(type);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceDataIntegrationProcess)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceDataIntegrationProcess> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the commerce data integration processes that the user has permission to view where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @return the matching commerce data integration processes that the user has permission to view
	 */
	@Override
	public List<CommerceDataIntegrationProcess> filterFindByC_T(
		long companyId, String type) {

		return filterFindByC_T(
			companyId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce data integration processes that the user has permission to view where companyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDataIntegrationProcessModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param start the lower bound of the range of commerce data integration processes
	 * @param end the upper bound of the range of commerce data integration processes (not inclusive)
	 * @return the range of matching commerce data integration processes that the user has permission to view
	 */
	@Override
	public List<CommerceDataIntegrationProcess> filterFindByC_T(
		long companyId, String type, int start, int end) {

		return filterFindByC_T(companyId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce data integration processes that the user has permissions to view where companyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDataIntegrationProcessModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param start the lower bound of the range of commerce data integration processes
	 * @param end the upper bound of the range of commerce data integration processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce data integration processes that the user has permission to view
	 */
	@Override
	public List<CommerceDataIntegrationProcess> filterFindByC_T(
		long companyId, String type, int start, int end,
		OrderByComparator<CommerceDataIntegrationProcess> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByC_T(companyId, type, start, end, orderByComparator);
		}

		type = Objects.toString(type, "");

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_C_T_COMPANYID_2);

		boolean bindType = false;

		if (type.isEmpty()) {
			query.append(_FINDER_COLUMN_C_T_TYPE_3_SQL);
		}
		else {
			bindType = true;

			query.append(_FINDER_COLUMN_C_T_TYPE_2_SQL);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(
					CommerceDataIntegrationProcessModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(
					CommerceDataIntegrationProcessModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceDataIntegrationProcess.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(
					_FILTER_ENTITY_ALIAS,
					CommerceDataIntegrationProcessImpl.class);
			}
			else {
				q.addEntity(
					_FILTER_ENTITY_TABLE,
					CommerceDataIntegrationProcessImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (bindType) {
				qPos.add(type);
			}

			return (List<CommerceDataIntegrationProcess>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the commerce data integration processes before and after the current commerce data integration process in the ordered set of commerce data integration processes that the user has permission to view where companyId = &#63; and type = &#63;.
	 *
	 * @param commerceDataIntegrationProcessId the primary key of the current commerce data integration process
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce data integration process
	 * @throws NoSuchDataIntegrationProcessException if a commerce data integration process with the primary key could not be found
	 */
	@Override
	public CommerceDataIntegrationProcess[] filterFindByC_T_PrevAndNext(
			long commerceDataIntegrationProcessId, long companyId, String type,
			OrderByComparator<CommerceDataIntegrationProcess> orderByComparator)
		throws NoSuchDataIntegrationProcessException {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByC_T_PrevAndNext(
				commerceDataIntegrationProcessId, companyId, type,
				orderByComparator);
		}

		type = Objects.toString(type, "");

		CommerceDataIntegrationProcess commerceDataIntegrationProcess =
			findByPrimaryKey(commerceDataIntegrationProcessId);

		Session session = null;

		try {
			session = openSession();

			CommerceDataIntegrationProcess[] array =
				new CommerceDataIntegrationProcessImpl[3];

			array[0] = filterGetByC_T_PrevAndNext(
				session, commerceDataIntegrationProcess, companyId, type,
				orderByComparator, true);

			array[1] = commerceDataIntegrationProcess;

			array[2] = filterGetByC_T_PrevAndNext(
				session, commerceDataIntegrationProcess, companyId, type,
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

	protected CommerceDataIntegrationProcess filterGetByC_T_PrevAndNext(
		Session session,
		CommerceDataIntegrationProcess commerceDataIntegrationProcess,
		long companyId, String type,
		OrderByComparator<CommerceDataIntegrationProcess> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_C_T_COMPANYID_2);

		boolean bindType = false;

		if (type.isEmpty()) {
			query.append(_FINDER_COLUMN_C_T_TYPE_3_SQL);
		}
		else {
			bindType = true;

			query.append(_FINDER_COLUMN_C_T_TYPE_2_SQL);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
				query.append(
					CommerceDataIntegrationProcessModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(
					CommerceDataIntegrationProcessModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceDataIntegrationProcess.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(
				_FILTER_ENTITY_ALIAS, CommerceDataIntegrationProcessImpl.class);
		}
		else {
			q.addEntity(
				_FILTER_ENTITY_TABLE, CommerceDataIntegrationProcessImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (bindType) {
			qPos.add(type);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceDataIntegrationProcess)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceDataIntegrationProcess> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce data integration processes where companyId = &#63; and type = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 */
	@Override
	public void removeByC_T(long companyId, String type) {
		for (CommerceDataIntegrationProcess commerceDataIntegrationProcess :
				findByC_T(
					companyId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commerceDataIntegrationProcess);
		}
	}

	/**
	 * Returns the number of commerce data integration processes where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @return the number of matching commerce data integration processes
	 */
	@Override
	public int countByC_T(long companyId, String type) {
		type = Objects.toString(type, "");

		FinderPath finderPath = _finderPathCountByC_T;

		Object[] finderArgs = new Object[] {companyId, type};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEDATAINTEGRATIONPROCESS_WHERE);

			query.append(_FINDER_COLUMN_C_T_COMPANYID_2);

			boolean bindType = false;

			if (type.isEmpty()) {
				query.append(_FINDER_COLUMN_C_T_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_C_T_TYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindType) {
					qPos.add(type);
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

	/**
	 * Returns the number of commerce data integration processes that the user has permission to view where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @return the number of matching commerce data integration processes that the user has permission to view
	 */
	@Override
	public int filterCountByC_T(long companyId, String type) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return countByC_T(companyId, type);
		}

		type = Objects.toString(type, "");

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_COMMERCEDATAINTEGRATIONPROCESS_WHERE);

		query.append(_FINDER_COLUMN_C_T_COMPANYID_2);

		boolean bindType = false;

		if (type.isEmpty()) {
			query.append(_FINDER_COLUMN_C_T_TYPE_3_SQL);
		}
		else {
			bindType = true;

			query.append(_FINDER_COLUMN_C_T_TYPE_2_SQL);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), CommerceDataIntegrationProcess.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (bindType) {
				qPos.add(type);
			}

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

	private static final String _FINDER_COLUMN_C_T_COMPANYID_2 =
		"commerceDataIntegrationProcess.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_T_TYPE_2 =
		"commerceDataIntegrationProcess.type = ?";

	private static final String _FINDER_COLUMN_C_T_TYPE_3 =
		"(commerceDataIntegrationProcess.type IS NULL OR commerceDataIntegrationProcess.type = '')";

	private static final String _FINDER_COLUMN_C_T_TYPE_2_SQL =
		"commerceDataIntegrationProcess.type_ = ?";

	private static final String _FINDER_COLUMN_C_T_TYPE_3_SQL =
		"(commerceDataIntegrationProcess.type_ IS NULL OR commerceDataIntegrationProcess.type_ = '')";

	public CommerceDataIntegrationProcessPersistenceImpl() {
		setModelClass(CommerceDataIntegrationProcess.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put(
			"commerceDataIntegrationProcessId", "CDataIntegrationProcessId");
		dbColumnNames.put("type", "type_");
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
	 * Caches the commerce data integration process in the entity cache if it is enabled.
	 *
	 * @param commerceDataIntegrationProcess the commerce data integration process
	 */
	@Override
	public void cacheResult(
		CommerceDataIntegrationProcess commerceDataIntegrationProcess) {

		entityCache.putResult(
			CommerceDataIntegrationProcessModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessImpl.class,
			commerceDataIntegrationProcess.getPrimaryKey(),
			commerceDataIntegrationProcess);

		finderCache.putResult(
			_finderPathFetchByC_N,
			new Object[] {
				commerceDataIntegrationProcess.getCompanyId(),
				commerceDataIntegrationProcess.getName()
			},
			commerceDataIntegrationProcess);

		commerceDataIntegrationProcess.resetOriginalValues();
	}

	/**
	 * Caches the commerce data integration processes in the entity cache if it is enabled.
	 *
	 * @param commerceDataIntegrationProcesses the commerce data integration processes
	 */
	@Override
	public void cacheResult(
		List<CommerceDataIntegrationProcess> commerceDataIntegrationProcesses) {

		for (CommerceDataIntegrationProcess commerceDataIntegrationProcess :
				commerceDataIntegrationProcesses) {

			if (entityCache.getResult(
					CommerceDataIntegrationProcessModelImpl.
						ENTITY_CACHE_ENABLED,
					CommerceDataIntegrationProcessImpl.class,
					commerceDataIntegrationProcess.getPrimaryKey()) == null) {

				cacheResult(commerceDataIntegrationProcess);
			}
			else {
				commerceDataIntegrationProcess.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce data integration processes.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceDataIntegrationProcessImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce data integration process.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CommerceDataIntegrationProcess commerceDataIntegrationProcess) {

		entityCache.removeResult(
			CommerceDataIntegrationProcessModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessImpl.class,
			commerceDataIntegrationProcess.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(CommerceDataIntegrationProcessModelImpl)
				commerceDataIntegrationProcess,
			true);
	}

	@Override
	public void clearCache(
		List<CommerceDataIntegrationProcess> commerceDataIntegrationProcesses) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceDataIntegrationProcess commerceDataIntegrationProcess :
				commerceDataIntegrationProcesses) {

			entityCache.removeResult(
				CommerceDataIntegrationProcessModelImpl.ENTITY_CACHE_ENABLED,
				CommerceDataIntegrationProcessImpl.class,
				commerceDataIntegrationProcess.getPrimaryKey());

			clearUniqueFindersCache(
				(CommerceDataIntegrationProcessModelImpl)
					commerceDataIntegrationProcess,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		CommerceDataIntegrationProcessModelImpl
			commerceDataIntegrationProcessModelImpl) {

		Object[] args = new Object[] {
			commerceDataIntegrationProcessModelImpl.getCompanyId(),
			commerceDataIntegrationProcessModelImpl.getName()
		};

		finderCache.putResult(
			_finderPathCountByC_N, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_N, args,
			commerceDataIntegrationProcessModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CommerceDataIntegrationProcessModelImpl
			commerceDataIntegrationProcessModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				commerceDataIntegrationProcessModelImpl.getCompanyId(),
				commerceDataIntegrationProcessModelImpl.getName()
			};

			finderCache.removeResult(_finderPathCountByC_N, args);
			finderCache.removeResult(_finderPathFetchByC_N, args);
		}

		if ((commerceDataIntegrationProcessModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_N.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				commerceDataIntegrationProcessModelImpl.getOriginalCompanyId(),
				commerceDataIntegrationProcessModelImpl.getOriginalName()
			};

			finderCache.removeResult(_finderPathCountByC_N, args);
			finderCache.removeResult(_finderPathFetchByC_N, args);
		}
	}

	/**
	 * Creates a new commerce data integration process with the primary key. Does not add the commerce data integration process to the database.
	 *
	 * @param commerceDataIntegrationProcessId the primary key for the new commerce data integration process
	 * @return the new commerce data integration process
	 */
	@Override
	public CommerceDataIntegrationProcess create(
		long commerceDataIntegrationProcessId) {

		CommerceDataIntegrationProcess commerceDataIntegrationProcess =
			new CommerceDataIntegrationProcessImpl();

		commerceDataIntegrationProcess.setNew(true);
		commerceDataIntegrationProcess.setPrimaryKey(
			commerceDataIntegrationProcessId);

		commerceDataIntegrationProcess.setCompanyId(
			CompanyThreadLocal.getCompanyId());

		return commerceDataIntegrationProcess;
	}

	/**
	 * Removes the commerce data integration process with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDataIntegrationProcessId the primary key of the commerce data integration process
	 * @return the commerce data integration process that was removed
	 * @throws NoSuchDataIntegrationProcessException if a commerce data integration process with the primary key could not be found
	 */
	@Override
	public CommerceDataIntegrationProcess remove(
			long commerceDataIntegrationProcessId)
		throws NoSuchDataIntegrationProcessException {

		return remove((Serializable)commerceDataIntegrationProcessId);
	}

	/**
	 * Removes the commerce data integration process with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce data integration process
	 * @return the commerce data integration process that was removed
	 * @throws NoSuchDataIntegrationProcessException if a commerce data integration process with the primary key could not be found
	 */
	@Override
	public CommerceDataIntegrationProcess remove(Serializable primaryKey)
		throws NoSuchDataIntegrationProcessException {

		Session session = null;

		try {
			session = openSession();

			CommerceDataIntegrationProcess commerceDataIntegrationProcess =
				(CommerceDataIntegrationProcess)session.get(
					CommerceDataIntegrationProcessImpl.class, primaryKey);

			if (commerceDataIntegrationProcess == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDataIntegrationProcessException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commerceDataIntegrationProcess);
		}
		catch (NoSuchDataIntegrationProcessException nsee) {
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
	protected CommerceDataIntegrationProcess removeImpl(
		CommerceDataIntegrationProcess commerceDataIntegrationProcess) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceDataIntegrationProcess)) {
				commerceDataIntegrationProcess =
					(CommerceDataIntegrationProcess)session.get(
						CommerceDataIntegrationProcessImpl.class,
						commerceDataIntegrationProcess.getPrimaryKeyObj());
			}

			if (commerceDataIntegrationProcess != null) {
				session.delete(commerceDataIntegrationProcess);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceDataIntegrationProcess != null) {
			clearCache(commerceDataIntegrationProcess);
		}

		return commerceDataIntegrationProcess;
	}

	@Override
	public CommerceDataIntegrationProcess updateImpl(
		CommerceDataIntegrationProcess commerceDataIntegrationProcess) {

		boolean isNew = commerceDataIntegrationProcess.isNew();

		if (!(commerceDataIntegrationProcess instanceof
				CommerceDataIntegrationProcessModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(
					commerceDataIntegrationProcess.getClass())) {

				invocationHandler = ProxyUtil.getInvocationHandler(
					commerceDataIntegrationProcess);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceDataIntegrationProcess proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceDataIntegrationProcess implementation " +
					commerceDataIntegrationProcess.getClass());
		}

		CommerceDataIntegrationProcessModelImpl
			commerceDataIntegrationProcessModelImpl =
				(CommerceDataIntegrationProcessModelImpl)
					commerceDataIntegrationProcess;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceDataIntegrationProcess.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceDataIntegrationProcess.setCreateDate(now);
			}
			else {
				commerceDataIntegrationProcess.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!commerceDataIntegrationProcessModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceDataIntegrationProcess.setModifiedDate(now);
			}
			else {
				commerceDataIntegrationProcess.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceDataIntegrationProcess.isNew()) {
				session.save(commerceDataIntegrationProcess);

				commerceDataIntegrationProcess.setNew(false);
			}
			else {
				commerceDataIntegrationProcess =
					(CommerceDataIntegrationProcess)session.merge(
						commerceDataIntegrationProcess);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceDataIntegrationProcessModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				commerceDataIntegrationProcessModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByCompanyId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCompanyId, args);

			args = new Object[] {
				commerceDataIntegrationProcessModelImpl.getCompanyId(),
				commerceDataIntegrationProcessModelImpl.getType()
			};

			finderCache.removeResult(_finderPathCountByC_T, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_T, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((commerceDataIntegrationProcessModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCompanyId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceDataIntegrationProcessModelImpl.
						getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);

				args = new Object[] {
					commerceDataIntegrationProcessModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);
			}

			if ((commerceDataIntegrationProcessModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_T.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceDataIntegrationProcessModelImpl.
						getOriginalCompanyId(),
					commerceDataIntegrationProcessModelImpl.getOriginalType()
				};

				finderCache.removeResult(_finderPathCountByC_T, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_T, args);

				args = new Object[] {
					commerceDataIntegrationProcessModelImpl.getCompanyId(),
					commerceDataIntegrationProcessModelImpl.getType()
				};

				finderCache.removeResult(_finderPathCountByC_T, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_T, args);
			}
		}

		entityCache.putResult(
			CommerceDataIntegrationProcessModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessImpl.class,
			commerceDataIntegrationProcess.getPrimaryKey(),
			commerceDataIntegrationProcess, false);

		clearUniqueFindersCache(commerceDataIntegrationProcessModelImpl, false);
		cacheUniqueFindersCache(commerceDataIntegrationProcessModelImpl);

		commerceDataIntegrationProcess.resetOriginalValues();

		return commerceDataIntegrationProcess;
	}

	/**
	 * Returns the commerce data integration process with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce data integration process
	 * @return the commerce data integration process
	 * @throws NoSuchDataIntegrationProcessException if a commerce data integration process with the primary key could not be found
	 */
	@Override
	public CommerceDataIntegrationProcess findByPrimaryKey(
			Serializable primaryKey)
		throws NoSuchDataIntegrationProcessException {

		CommerceDataIntegrationProcess commerceDataIntegrationProcess =
			fetchByPrimaryKey(primaryKey);

		if (commerceDataIntegrationProcess == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDataIntegrationProcessException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commerceDataIntegrationProcess;
	}

	/**
	 * Returns the commerce data integration process with the primary key or throws a <code>NoSuchDataIntegrationProcessException</code> if it could not be found.
	 *
	 * @param commerceDataIntegrationProcessId the primary key of the commerce data integration process
	 * @return the commerce data integration process
	 * @throws NoSuchDataIntegrationProcessException if a commerce data integration process with the primary key could not be found
	 */
	@Override
	public CommerceDataIntegrationProcess findByPrimaryKey(
			long commerceDataIntegrationProcessId)
		throws NoSuchDataIntegrationProcessException {

		return findByPrimaryKey((Serializable)commerceDataIntegrationProcessId);
	}

	/**
	 * Returns the commerce data integration process with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce data integration process
	 * @return the commerce data integration process, or <code>null</code> if a commerce data integration process with the primary key could not be found
	 */
	@Override
	public CommerceDataIntegrationProcess fetchByPrimaryKey(
		Serializable primaryKey) {

		Serializable serializable = entityCache.getResult(
			CommerceDataIntegrationProcessModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceDataIntegrationProcess commerceDataIntegrationProcess =
			(CommerceDataIntegrationProcess)serializable;

		if (commerceDataIntegrationProcess == null) {
			Session session = null;

			try {
				session = openSession();

				commerceDataIntegrationProcess =
					(CommerceDataIntegrationProcess)session.get(
						CommerceDataIntegrationProcessImpl.class, primaryKey);

				if (commerceDataIntegrationProcess != null) {
					cacheResult(commerceDataIntegrationProcess);
				}
				else {
					entityCache.putResult(
						CommerceDataIntegrationProcessModelImpl.
							ENTITY_CACHE_ENABLED,
						CommerceDataIntegrationProcessImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CommerceDataIntegrationProcessModelImpl.
						ENTITY_CACHE_ENABLED,
					CommerceDataIntegrationProcessImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceDataIntegrationProcess;
	}

	/**
	 * Returns the commerce data integration process with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceDataIntegrationProcessId the primary key of the commerce data integration process
	 * @return the commerce data integration process, or <code>null</code> if a commerce data integration process with the primary key could not be found
	 */
	@Override
	public CommerceDataIntegrationProcess fetchByPrimaryKey(
		long commerceDataIntegrationProcessId) {

		return fetchByPrimaryKey(
			(Serializable)commerceDataIntegrationProcessId);
	}

	@Override
	public Map<Serializable, CommerceDataIntegrationProcess> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceDataIntegrationProcess> map =
			new HashMap<Serializable, CommerceDataIntegrationProcess>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceDataIntegrationProcess commerceDataIntegrationProcess =
				fetchByPrimaryKey(primaryKey);

			if (commerceDataIntegrationProcess != null) {
				map.put(primaryKey, commerceDataIntegrationProcess);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CommerceDataIntegrationProcessModelImpl.ENTITY_CACHE_ENABLED,
				CommerceDataIntegrationProcessImpl.class, primaryKey);

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
						(CommerceDataIntegrationProcess)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS_WHERE_PKS_IN);

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

			for (CommerceDataIntegrationProcess commerceDataIntegrationProcess :
					(List<CommerceDataIntegrationProcess>)q.list()) {

				map.put(
					commerceDataIntegrationProcess.getPrimaryKeyObj(),
					commerceDataIntegrationProcess);

				cacheResult(commerceDataIntegrationProcess);

				uncachedPrimaryKeys.remove(
					commerceDataIntegrationProcess.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CommerceDataIntegrationProcessModelImpl.
						ENTITY_CACHE_ENABLED,
					CommerceDataIntegrationProcessImpl.class, primaryKey,
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
	 * Returns all the commerce data integration processes.
	 *
	 * @return the commerce data integration processes
	 */
	@Override
	public List<CommerceDataIntegrationProcess> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce data integration processes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDataIntegrationProcessModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce data integration processes
	 * @param end the upper bound of the range of commerce data integration processes (not inclusive)
	 * @return the range of commerce data integration processes
	 */
	@Override
	public List<CommerceDataIntegrationProcess> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce data integration processes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDataIntegrationProcessModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce data integration processes
	 * @param end the upper bound of the range of commerce data integration processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce data integration processes
	 */
	@Override
	public List<CommerceDataIntegrationProcess> findAll(
		int start, int end,
		OrderByComparator<CommerceDataIntegrationProcess> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce data integration processes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CommerceDataIntegrationProcessModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce data integration processes
	 * @param end the upper bound of the range of commerce data integration processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce data integration processes
	 */
	@Override
	public List<CommerceDataIntegrationProcess> findAll(
		int start, int end,
		OrderByComparator<CommerceDataIntegrationProcess> orderByComparator,
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

		List<CommerceDataIntegrationProcess> list = null;

		if (useFinderCache) {
			list = (List<CommerceDataIntegrationProcess>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS;

				if (pagination) {
					sql = sql.concat(
						CommerceDataIntegrationProcessModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceDataIntegrationProcess>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceDataIntegrationProcess>)QueryUtil.list(
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
	 * Removes all the commerce data integration processes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceDataIntegrationProcess commerceDataIntegrationProcess :
				findAll()) {

			remove(commerceDataIntegrationProcess);
		}
	}

	/**
	 * Returns the number of commerce data integration processes.
	 *
	 * @return the number of commerce data integration processes
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
					_SQL_COUNT_COMMERCEDATAINTEGRATIONPROCESS);

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
		return CommerceDataIntegrationProcessModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce data integration process persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CommerceDataIntegrationProcessModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessModelImpl.FINDER_CACHE_ENABLED,
			CommerceDataIntegrationProcessImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CommerceDataIntegrationProcessModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessModelImpl.FINDER_CACHE_ENABLED,
			CommerceDataIntegrationProcessImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CommerceDataIntegrationProcessModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByCompanyId = new FinderPath(
			CommerceDataIntegrationProcessModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessModelImpl.FINDER_CACHE_ENABLED,
			CommerceDataIntegrationProcessImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCompanyId = new FinderPath(
			CommerceDataIntegrationProcessModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessModelImpl.FINDER_CACHE_ENABLED,
			CommerceDataIntegrationProcessImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] {Long.class.getName()},
			CommerceDataIntegrationProcessModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceDataIntegrationProcessModelImpl.
				MODIFIEDDATE_COLUMN_BITMASK);

		_finderPathCountByCompanyId = new FinderPath(
			CommerceDataIntegrationProcessModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCompanyId", new String[] {Long.class.getName()});

		_finderPathFetchByC_N = new FinderPath(
			CommerceDataIntegrationProcessModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessModelImpl.FINDER_CACHE_ENABLED,
			CommerceDataIntegrationProcessImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_N",
			new String[] {Long.class.getName(), String.class.getName()},
			CommerceDataIntegrationProcessModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceDataIntegrationProcessModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByC_N = new FinderPath(
			CommerceDataIntegrationProcessModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_N",
			new String[] {Long.class.getName(), String.class.getName()});

		_finderPathWithPaginationFindByC_T = new FinderPath(
			CommerceDataIntegrationProcessModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessModelImpl.FINDER_CACHE_ENABLED,
			CommerceDataIntegrationProcessImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_T",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_T = new FinderPath(
			CommerceDataIntegrationProcessModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessModelImpl.FINDER_CACHE_ENABLED,
			CommerceDataIntegrationProcessImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_T",
			new String[] {Long.class.getName(), String.class.getName()},
			CommerceDataIntegrationProcessModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceDataIntegrationProcessModelImpl.TYPE_COLUMN_BITMASK |
			CommerceDataIntegrationProcessModelImpl.
				MODIFIEDDATE_COLUMN_BITMASK);

		_finderPathCountByC_T = new FinderPath(
			CommerceDataIntegrationProcessModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_T",
			new String[] {Long.class.getName(), String.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(
			CommerceDataIntegrationProcessImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS =
		"SELECT commerceDataIntegrationProcess FROM CommerceDataIntegrationProcess commerceDataIntegrationProcess";

	private static final String
		_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS_WHERE_PKS_IN =
			"SELECT commerceDataIntegrationProcess FROM CommerceDataIntegrationProcess commerceDataIntegrationProcess WHERE CDataIntegrationProcessId IN (";

	private static final String
		_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS_WHERE =
			"SELECT commerceDataIntegrationProcess FROM CommerceDataIntegrationProcess commerceDataIntegrationProcess WHERE ";

	private static final String _SQL_COUNT_COMMERCEDATAINTEGRATIONPROCESS =
		"SELECT COUNT(commerceDataIntegrationProcess) FROM CommerceDataIntegrationProcess commerceDataIntegrationProcess";

	private static final String
		_SQL_COUNT_COMMERCEDATAINTEGRATIONPROCESS_WHERE =
			"SELECT COUNT(commerceDataIntegrationProcess) FROM CommerceDataIntegrationProcess commerceDataIntegrationProcess WHERE ";

	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN =
		"commerceDataIntegrationProcess.CDataIntegrationProcessId";

	private static final String
		_FILTER_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS_WHERE =
			"SELECT DISTINCT {commerceDataIntegrationProcess.*} FROM CDataIntegrationProcess commerceDataIntegrationProcess WHERE ";

	private static final String
		_FILTER_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS_NO_INLINE_DISTINCT_WHERE_1 =
			"SELECT {CDataIntegrationProcess.*} FROM (SELECT DISTINCT commerceDataIntegrationProcess.CDataIntegrationProcessId FROM CDataIntegrationProcess commerceDataIntegrationProcess WHERE ";

	private static final String
		_FILTER_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESS_NO_INLINE_DISTINCT_WHERE_2 =
			") TEMP_TABLE INNER JOIN CDataIntegrationProcess ON TEMP_TABLE.CDataIntegrationProcessId = CDataIntegrationProcess.CDataIntegrationProcessId";

	private static final String
		_FILTER_SQL_COUNT_COMMERCEDATAINTEGRATIONPROCESS_WHERE =
			"SELECT COUNT(DISTINCT commerceDataIntegrationProcess.CDataIntegrationProcessId) AS COUNT_VALUE FROM CDataIntegrationProcess commerceDataIntegrationProcess WHERE ";

	private static final String _FILTER_ENTITY_ALIAS =
		"commerceDataIntegrationProcess";

	private static final String _FILTER_ENTITY_TABLE =
		"CDataIntegrationProcess";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"commerceDataIntegrationProcess.";

	private static final String _ORDER_BY_ENTITY_TABLE =
		"CDataIntegrationProcess.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommerceDataIntegrationProcess exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommerceDataIntegrationProcess exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceDataIntegrationProcessPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"commerceDataIntegrationProcessId", "type", "active"});

}