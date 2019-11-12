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

import com.liferay.commerce.data.integration.exception.NoSuchDataIntegrationProcessLogException;
import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcessLog;
import com.liferay.commerce.data.integration.model.impl.CommerceDataIntegrationProcessLogImpl;
import com.liferay.commerce.data.integration.model.impl.CommerceDataIntegrationProcessLogModelImpl;
import com.liferay.commerce.data.integration.service.persistence.CommerceDataIntegrationProcessLogPersistence;
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
 * The persistence implementation for the commerce data integration process log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
public class CommerceDataIntegrationProcessLogPersistenceImpl
	extends BasePersistenceImpl<CommerceDataIntegrationProcessLog>
	implements CommerceDataIntegrationProcessLogPersistence {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommerceDataIntegrationProcessLogUtil</code> to access the commerce data integration process log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommerceDataIntegrationProcessLogImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByCDataIntegrationProcessId;
	private FinderPath
		_finderPathWithoutPaginationFindByCDataIntegrationProcessId;
	private FinderPath _finderPathCountByCDataIntegrationProcessId;

	/**
	 * Returns all the commerce data integration process logs where CDataIntegrationProcessId = &#63;.
	 *
	 * @param CDataIntegrationProcessId the c data integration process ID
	 * @return the matching commerce data integration process logs
	 */
	@Override
	public List<CommerceDataIntegrationProcessLog>
		findByCDataIntegrationProcessId(long CDataIntegrationProcessId) {

		return findByCDataIntegrationProcessId(
			CDataIntegrationProcessId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the commerce data integration process logs where CDataIntegrationProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceDataIntegrationProcessLogModelImpl</code>.
	 * </p>
	 *
	 * @param CDataIntegrationProcessId the c data integration process ID
	 * @param start the lower bound of the range of commerce data integration process logs
	 * @param end the upper bound of the range of commerce data integration process logs (not inclusive)
	 * @return the range of matching commerce data integration process logs
	 */
	@Override
	public List<CommerceDataIntegrationProcessLog>
		findByCDataIntegrationProcessId(
			long CDataIntegrationProcessId, int start, int end) {

		return findByCDataIntegrationProcessId(
			CDataIntegrationProcessId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce data integration process logs where CDataIntegrationProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceDataIntegrationProcessLogModelImpl</code>.
	 * </p>
	 *
	 * @param CDataIntegrationProcessId the c data integration process ID
	 * @param start the lower bound of the range of commerce data integration process logs
	 * @param end the upper bound of the range of commerce data integration process logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce data integration process logs
	 */
	@Override
	public List<CommerceDataIntegrationProcessLog>
		findByCDataIntegrationProcessId(
			long CDataIntegrationProcessId, int start, int end,
			OrderByComparator<CommerceDataIntegrationProcessLog>
				orderByComparator) {

		return findByCDataIntegrationProcessId(
			CDataIntegrationProcessId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce data integration process logs where CDataIntegrationProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceDataIntegrationProcessLogModelImpl</code>.
	 * </p>
	 *
	 * @param CDataIntegrationProcessId the c data integration process ID
	 * @param start the lower bound of the range of commerce data integration process logs
	 * @param end the upper bound of the range of commerce data integration process logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce data integration process logs
	 */
	@Override
	public List<CommerceDataIntegrationProcessLog>
		findByCDataIntegrationProcessId(
			long CDataIntegrationProcessId, int start, int end,
			OrderByComparator<CommerceDataIntegrationProcessLog>
				orderByComparator,
			boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByCDataIntegrationProcessId;
				finderArgs = new Object[] {CDataIntegrationProcessId};
			}
		}
		else if (useFinderCache) {
			finderPath =
				_finderPathWithPaginationFindByCDataIntegrationProcessId;
			finderArgs = new Object[] {
				CDataIntegrationProcessId, start, end, orderByComparator
			};
		}

		List<CommerceDataIntegrationProcessLog> list = null;

		if (useFinderCache) {
			list =
				(List<CommerceDataIntegrationProcessLog>)finderCache.getResult(
					finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceDataIntegrationProcessLog
						commerceDataIntegrationProcessLog : list) {

					if (CDataIntegrationProcessId !=
							commerceDataIntegrationProcessLog.
								getCDataIntegrationProcessId()) {

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

			query.append(_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESSLOG_WHERE);

			query.append(
				_FINDER_COLUMN_CDATAINTEGRATIONPROCESSID_CDATAINTEGRATIONPROCESSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(
					CommerceDataIntegrationProcessLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CDataIntegrationProcessId);

				list = (List<CommerceDataIntegrationProcessLog>)QueryUtil.list(
					q, getDialect(), start, end);

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
	 * Returns the first commerce data integration process log in the ordered set where CDataIntegrationProcessId = &#63;.
	 *
	 * @param CDataIntegrationProcessId the c data integration process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce data integration process log
	 * @throws NoSuchDataIntegrationProcessLogException if a matching commerce data integration process log could not be found
	 */
	@Override
	public CommerceDataIntegrationProcessLog
			findByCDataIntegrationProcessId_First(
				long CDataIntegrationProcessId,
				OrderByComparator<CommerceDataIntegrationProcessLog>
					orderByComparator)
		throws NoSuchDataIntegrationProcessLogException {

		CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog =
			fetchByCDataIntegrationProcessId_First(
				CDataIntegrationProcessId, orderByComparator);

		if (commerceDataIntegrationProcessLog != null) {
			return commerceDataIntegrationProcessLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CDataIntegrationProcessId=");
		msg.append(CDataIntegrationProcessId);

		msg.append("}");

		throw new NoSuchDataIntegrationProcessLogException(msg.toString());
	}

	/**
	 * Returns the first commerce data integration process log in the ordered set where CDataIntegrationProcessId = &#63;.
	 *
	 * @param CDataIntegrationProcessId the c data integration process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce data integration process log, or <code>null</code> if a matching commerce data integration process log could not be found
	 */
	@Override
	public CommerceDataIntegrationProcessLog
		fetchByCDataIntegrationProcessId_First(
			long CDataIntegrationProcessId,
			OrderByComparator<CommerceDataIntegrationProcessLog>
				orderByComparator) {

		List<CommerceDataIntegrationProcessLog> list =
			findByCDataIntegrationProcessId(
				CDataIntegrationProcessId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce data integration process log in the ordered set where CDataIntegrationProcessId = &#63;.
	 *
	 * @param CDataIntegrationProcessId the c data integration process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce data integration process log
	 * @throws NoSuchDataIntegrationProcessLogException if a matching commerce data integration process log could not be found
	 */
	@Override
	public CommerceDataIntegrationProcessLog
			findByCDataIntegrationProcessId_Last(
				long CDataIntegrationProcessId,
				OrderByComparator<CommerceDataIntegrationProcessLog>
					orderByComparator)
		throws NoSuchDataIntegrationProcessLogException {

		CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog =
			fetchByCDataIntegrationProcessId_Last(
				CDataIntegrationProcessId, orderByComparator);

		if (commerceDataIntegrationProcessLog != null) {
			return commerceDataIntegrationProcessLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CDataIntegrationProcessId=");
		msg.append(CDataIntegrationProcessId);

		msg.append("}");

		throw new NoSuchDataIntegrationProcessLogException(msg.toString());
	}

	/**
	 * Returns the last commerce data integration process log in the ordered set where CDataIntegrationProcessId = &#63;.
	 *
	 * @param CDataIntegrationProcessId the c data integration process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce data integration process log, or <code>null</code> if a matching commerce data integration process log could not be found
	 */
	@Override
	public CommerceDataIntegrationProcessLog
		fetchByCDataIntegrationProcessId_Last(
			long CDataIntegrationProcessId,
			OrderByComparator<CommerceDataIntegrationProcessLog>
				orderByComparator) {

		int count = countByCDataIntegrationProcessId(CDataIntegrationProcessId);

		if (count == 0) {
			return null;
		}

		List<CommerceDataIntegrationProcessLog> list =
			findByCDataIntegrationProcessId(
				CDataIntegrationProcessId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce data integration process logs before and after the current commerce data integration process log in the ordered set where CDataIntegrationProcessId = &#63;.
	 *
	 * @param commerceDataIntegrationProcessLogId the primary key of the current commerce data integration process log
	 * @param CDataIntegrationProcessId the c data integration process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce data integration process log
	 * @throws NoSuchDataIntegrationProcessLogException if a commerce data integration process log with the primary key could not be found
	 */
	@Override
	public CommerceDataIntegrationProcessLog[]
			findByCDataIntegrationProcessId_PrevAndNext(
				long commerceDataIntegrationProcessLogId,
				long CDataIntegrationProcessId,
				OrderByComparator<CommerceDataIntegrationProcessLog>
					orderByComparator)
		throws NoSuchDataIntegrationProcessLogException {

		CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog =
			findByPrimaryKey(commerceDataIntegrationProcessLogId);

		Session session = null;

		try {
			session = openSession();

			CommerceDataIntegrationProcessLog[] array =
				new CommerceDataIntegrationProcessLogImpl[3];

			array[0] = getByCDataIntegrationProcessId_PrevAndNext(
				session, commerceDataIntegrationProcessLog,
				CDataIntegrationProcessId, orderByComparator, true);

			array[1] = commerceDataIntegrationProcessLog;

			array[2] = getByCDataIntegrationProcessId_PrevAndNext(
				session, commerceDataIntegrationProcessLog,
				CDataIntegrationProcessId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceDataIntegrationProcessLog
		getByCDataIntegrationProcessId_PrevAndNext(
			Session session,
			CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog,
			long CDataIntegrationProcessId,
			OrderByComparator<CommerceDataIntegrationProcessLog>
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

		query.append(_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESSLOG_WHERE);

		query.append(
			_FINDER_COLUMN_CDATAINTEGRATIONPROCESSID_CDATAINTEGRATIONPROCESSID_2);

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
				CommerceDataIntegrationProcessLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(CDataIntegrationProcessId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceDataIntegrationProcessLog)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceDataIntegrationProcessLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce data integration process logs where CDataIntegrationProcessId = &#63; from the database.
	 *
	 * @param CDataIntegrationProcessId the c data integration process ID
	 */
	@Override
	public void removeByCDataIntegrationProcessId(
		long CDataIntegrationProcessId) {

		for (CommerceDataIntegrationProcessLog
				commerceDataIntegrationProcessLog :
					findByCDataIntegrationProcessId(
						CDataIntegrationProcessId, QueryUtil.ALL_POS,
						QueryUtil.ALL_POS, null)) {

			remove(commerceDataIntegrationProcessLog);
		}
	}

	/**
	 * Returns the number of commerce data integration process logs where CDataIntegrationProcessId = &#63;.
	 *
	 * @param CDataIntegrationProcessId the c data integration process ID
	 * @return the number of matching commerce data integration process logs
	 */
	@Override
	public int countByCDataIntegrationProcessId(
		long CDataIntegrationProcessId) {

		FinderPath finderPath = _finderPathCountByCDataIntegrationProcessId;

		Object[] finderArgs = new Object[] {CDataIntegrationProcessId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEDATAINTEGRATIONPROCESSLOG_WHERE);

			query.append(
				_FINDER_COLUMN_CDATAINTEGRATIONPROCESSID_CDATAINTEGRATIONPROCESSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CDataIntegrationProcessId);

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
		_FINDER_COLUMN_CDATAINTEGRATIONPROCESSID_CDATAINTEGRATIONPROCESSID_2 =
			"commerceDataIntegrationProcessLog.CDataIntegrationProcessId = ?";

	private FinderPath _finderPathWithPaginationFindByC_S;
	private FinderPath _finderPathWithoutPaginationFindByC_S;
	private FinderPath _finderPathCountByC_S;

	/**
	 * Returns all the commerce data integration process logs where CDataIntegrationProcessId = &#63; and status = &#63;.
	 *
	 * @param CDataIntegrationProcessId the c data integration process ID
	 * @param status the status
	 * @return the matching commerce data integration process logs
	 */
	@Override
	public List<CommerceDataIntegrationProcessLog> findByC_S(
		long CDataIntegrationProcessId, int status) {

		return findByC_S(
			CDataIntegrationProcessId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce data integration process logs where CDataIntegrationProcessId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceDataIntegrationProcessLogModelImpl</code>.
	 * </p>
	 *
	 * @param CDataIntegrationProcessId the c data integration process ID
	 * @param status the status
	 * @param start the lower bound of the range of commerce data integration process logs
	 * @param end the upper bound of the range of commerce data integration process logs (not inclusive)
	 * @return the range of matching commerce data integration process logs
	 */
	@Override
	public List<CommerceDataIntegrationProcessLog> findByC_S(
		long CDataIntegrationProcessId, int status, int start, int end) {

		return findByC_S(CDataIntegrationProcessId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce data integration process logs where CDataIntegrationProcessId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceDataIntegrationProcessLogModelImpl</code>.
	 * </p>
	 *
	 * @param CDataIntegrationProcessId the c data integration process ID
	 * @param status the status
	 * @param start the lower bound of the range of commerce data integration process logs
	 * @param end the upper bound of the range of commerce data integration process logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce data integration process logs
	 */
	@Override
	public List<CommerceDataIntegrationProcessLog> findByC_S(
		long CDataIntegrationProcessId, int status, int start, int end,
		OrderByComparator<CommerceDataIntegrationProcessLog>
			orderByComparator) {

		return findByC_S(
			CDataIntegrationProcessId, status, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the commerce data integration process logs where CDataIntegrationProcessId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceDataIntegrationProcessLogModelImpl</code>.
	 * </p>
	 *
	 * @param CDataIntegrationProcessId the c data integration process ID
	 * @param status the status
	 * @param start the lower bound of the range of commerce data integration process logs
	 * @param end the upper bound of the range of commerce data integration process logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce data integration process logs
	 */
	@Override
	public List<CommerceDataIntegrationProcessLog> findByC_S(
		long CDataIntegrationProcessId, int status, int start, int end,
		OrderByComparator<CommerceDataIntegrationProcessLog> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByC_S;
				finderArgs = new Object[] {CDataIntegrationProcessId, status};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByC_S;
			finderArgs = new Object[] {
				CDataIntegrationProcessId, status, start, end, orderByComparator
			};
		}

		List<CommerceDataIntegrationProcessLog> list = null;

		if (useFinderCache) {
			list =
				(List<CommerceDataIntegrationProcessLog>)finderCache.getResult(
					finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceDataIntegrationProcessLog
						commerceDataIntegrationProcessLog : list) {

					if ((CDataIntegrationProcessId !=
							commerceDataIntegrationProcessLog.
								getCDataIntegrationProcessId()) ||
						(status !=
							commerceDataIntegrationProcessLog.getStatus())) {

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

			query.append(_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESSLOG_WHERE);

			query.append(_FINDER_COLUMN_C_S_CDATAINTEGRATIONPROCESSID_2);

			query.append(_FINDER_COLUMN_C_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(
					CommerceDataIntegrationProcessLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CDataIntegrationProcessId);

				qPos.add(status);

				list = (List<CommerceDataIntegrationProcessLog>)QueryUtil.list(
					q, getDialect(), start, end);

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
	 * Returns the first commerce data integration process log in the ordered set where CDataIntegrationProcessId = &#63; and status = &#63;.
	 *
	 * @param CDataIntegrationProcessId the c data integration process ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce data integration process log
	 * @throws NoSuchDataIntegrationProcessLogException if a matching commerce data integration process log could not be found
	 */
	@Override
	public CommerceDataIntegrationProcessLog findByC_S_First(
			long CDataIntegrationProcessId, int status,
			OrderByComparator<CommerceDataIntegrationProcessLog>
				orderByComparator)
		throws NoSuchDataIntegrationProcessLogException {

		CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog =
			fetchByC_S_First(
				CDataIntegrationProcessId, status, orderByComparator);

		if (commerceDataIntegrationProcessLog != null) {
			return commerceDataIntegrationProcessLog;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CDataIntegrationProcessId=");
		msg.append(CDataIntegrationProcessId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchDataIntegrationProcessLogException(msg.toString());
	}

	/**
	 * Returns the first commerce data integration process log in the ordered set where CDataIntegrationProcessId = &#63; and status = &#63;.
	 *
	 * @param CDataIntegrationProcessId the c data integration process ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce data integration process log, or <code>null</code> if a matching commerce data integration process log could not be found
	 */
	@Override
	public CommerceDataIntegrationProcessLog fetchByC_S_First(
		long CDataIntegrationProcessId, int status,
		OrderByComparator<CommerceDataIntegrationProcessLog>
			orderByComparator) {

		List<CommerceDataIntegrationProcessLog> list = findByC_S(
			CDataIntegrationProcessId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce data integration process log in the ordered set where CDataIntegrationProcessId = &#63; and status = &#63;.
	 *
	 * @param CDataIntegrationProcessId the c data integration process ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce data integration process log
	 * @throws NoSuchDataIntegrationProcessLogException if a matching commerce data integration process log could not be found
	 */
	@Override
	public CommerceDataIntegrationProcessLog findByC_S_Last(
			long CDataIntegrationProcessId, int status,
			OrderByComparator<CommerceDataIntegrationProcessLog>
				orderByComparator)
		throws NoSuchDataIntegrationProcessLogException {

		CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog =
			fetchByC_S_Last(
				CDataIntegrationProcessId, status, orderByComparator);

		if (commerceDataIntegrationProcessLog != null) {
			return commerceDataIntegrationProcessLog;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("CDataIntegrationProcessId=");
		msg.append(CDataIntegrationProcessId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchDataIntegrationProcessLogException(msg.toString());
	}

	/**
	 * Returns the last commerce data integration process log in the ordered set where CDataIntegrationProcessId = &#63; and status = &#63;.
	 *
	 * @param CDataIntegrationProcessId the c data integration process ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce data integration process log, or <code>null</code> if a matching commerce data integration process log could not be found
	 */
	@Override
	public CommerceDataIntegrationProcessLog fetchByC_S_Last(
		long CDataIntegrationProcessId, int status,
		OrderByComparator<CommerceDataIntegrationProcessLog>
			orderByComparator) {

		int count = countByC_S(CDataIntegrationProcessId, status);

		if (count == 0) {
			return null;
		}

		List<CommerceDataIntegrationProcessLog> list = findByC_S(
			CDataIntegrationProcessId, status, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce data integration process logs before and after the current commerce data integration process log in the ordered set where CDataIntegrationProcessId = &#63; and status = &#63;.
	 *
	 * @param commerceDataIntegrationProcessLogId the primary key of the current commerce data integration process log
	 * @param CDataIntegrationProcessId the c data integration process ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce data integration process log
	 * @throws NoSuchDataIntegrationProcessLogException if a commerce data integration process log with the primary key could not be found
	 */
	@Override
	public CommerceDataIntegrationProcessLog[] findByC_S_PrevAndNext(
			long commerceDataIntegrationProcessLogId,
			long CDataIntegrationProcessId, int status,
			OrderByComparator<CommerceDataIntegrationProcessLog>
				orderByComparator)
		throws NoSuchDataIntegrationProcessLogException {

		CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog =
			findByPrimaryKey(commerceDataIntegrationProcessLogId);

		Session session = null;

		try {
			session = openSession();

			CommerceDataIntegrationProcessLog[] array =
				new CommerceDataIntegrationProcessLogImpl[3];

			array[0] = getByC_S_PrevAndNext(
				session, commerceDataIntegrationProcessLog,
				CDataIntegrationProcessId, status, orderByComparator, true);

			array[1] = commerceDataIntegrationProcessLog;

			array[2] = getByC_S_PrevAndNext(
				session, commerceDataIntegrationProcessLog,
				CDataIntegrationProcessId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceDataIntegrationProcessLog getByC_S_PrevAndNext(
		Session session,
		CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog,
		long CDataIntegrationProcessId, int status,
		OrderByComparator<CommerceDataIntegrationProcessLog> orderByComparator,
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

		query.append(_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESSLOG_WHERE);

		query.append(_FINDER_COLUMN_C_S_CDATAINTEGRATIONPROCESSID_2);

		query.append(_FINDER_COLUMN_C_S_STATUS_2);

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
				CommerceDataIntegrationProcessLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(CDataIntegrationProcessId);

		qPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceDataIntegrationProcessLog)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CommerceDataIntegrationProcessLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce data integration process logs where CDataIntegrationProcessId = &#63; and status = &#63; from the database.
	 *
	 * @param CDataIntegrationProcessId the c data integration process ID
	 * @param status the status
	 */
	@Override
	public void removeByC_S(long CDataIntegrationProcessId, int status) {
		for (CommerceDataIntegrationProcessLog
				commerceDataIntegrationProcessLog :
					findByC_S(
						CDataIntegrationProcessId, status, QueryUtil.ALL_POS,
						QueryUtil.ALL_POS, null)) {

			remove(commerceDataIntegrationProcessLog);
		}
	}

	/**
	 * Returns the number of commerce data integration process logs where CDataIntegrationProcessId = &#63; and status = &#63;.
	 *
	 * @param CDataIntegrationProcessId the c data integration process ID
	 * @param status the status
	 * @return the number of matching commerce data integration process logs
	 */
	@Override
	public int countByC_S(long CDataIntegrationProcessId, int status) {
		FinderPath finderPath = _finderPathCountByC_S;

		Object[] finderArgs = new Object[] {CDataIntegrationProcessId, status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEDATAINTEGRATIONPROCESSLOG_WHERE);

			query.append(_FINDER_COLUMN_C_S_CDATAINTEGRATIONPROCESSID_2);

			query.append(_FINDER_COLUMN_C_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(CDataIntegrationProcessId);

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_C_S_CDATAINTEGRATIONPROCESSID_2 =
		"commerceDataIntegrationProcessLog.CDataIntegrationProcessId = ? AND ";

	private static final String _FINDER_COLUMN_C_S_STATUS_2 =
		"commerceDataIntegrationProcessLog.status = ?";

	public CommerceDataIntegrationProcessLogPersistenceImpl() {
		setModelClass(CommerceDataIntegrationProcessLog.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put(
			"commerceDataIntegrationProcessLogId",
			"CDataIntegrationProcessLogId");
		dbColumnNames.put("output", "output_");

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
	 * Caches the commerce data integration process log in the entity cache if it is enabled.
	 *
	 * @param commerceDataIntegrationProcessLog the commerce data integration process log
	 */
	@Override
	public void cacheResult(
		CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog) {

		entityCache.putResult(
			CommerceDataIntegrationProcessLogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessLogImpl.class,
			commerceDataIntegrationProcessLog.getPrimaryKey(),
			commerceDataIntegrationProcessLog);

		commerceDataIntegrationProcessLog.resetOriginalValues();
	}

	/**
	 * Caches the commerce data integration process logs in the entity cache if it is enabled.
	 *
	 * @param commerceDataIntegrationProcessLogs the commerce data integration process logs
	 */
	@Override
	public void cacheResult(
		List<CommerceDataIntegrationProcessLog>
			commerceDataIntegrationProcessLogs) {

		for (CommerceDataIntegrationProcessLog
				commerceDataIntegrationProcessLog :
					commerceDataIntegrationProcessLogs) {

			if (entityCache.getResult(
					CommerceDataIntegrationProcessLogModelImpl.
						ENTITY_CACHE_ENABLED,
					CommerceDataIntegrationProcessLogImpl.class,
					commerceDataIntegrationProcessLog.getPrimaryKey()) ==
						null) {

				cacheResult(commerceDataIntegrationProcessLog);
			}
			else {
				commerceDataIntegrationProcessLog.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce data integration process logs.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceDataIntegrationProcessLogImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce data integration process log.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog) {

		entityCache.removeResult(
			CommerceDataIntegrationProcessLogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessLogImpl.class,
			commerceDataIntegrationProcessLog.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<CommerceDataIntegrationProcessLog>
			commerceDataIntegrationProcessLogs) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceDataIntegrationProcessLog
				commerceDataIntegrationProcessLog :
					commerceDataIntegrationProcessLogs) {

			entityCache.removeResult(
				CommerceDataIntegrationProcessLogModelImpl.ENTITY_CACHE_ENABLED,
				CommerceDataIntegrationProcessLogImpl.class,
				commerceDataIntegrationProcessLog.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				CommerceDataIntegrationProcessLogModelImpl.ENTITY_CACHE_ENABLED,
				CommerceDataIntegrationProcessLogImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new commerce data integration process log with the primary key. Does not add the commerce data integration process log to the database.
	 *
	 * @param commerceDataIntegrationProcessLogId the primary key for the new commerce data integration process log
	 * @return the new commerce data integration process log
	 */
	@Override
	public CommerceDataIntegrationProcessLog create(
		long commerceDataIntegrationProcessLogId) {

		CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog =
			new CommerceDataIntegrationProcessLogImpl();

		commerceDataIntegrationProcessLog.setNew(true);
		commerceDataIntegrationProcessLog.setPrimaryKey(
			commerceDataIntegrationProcessLogId);

		commerceDataIntegrationProcessLog.setCompanyId(
			CompanyThreadLocal.getCompanyId());

		return commerceDataIntegrationProcessLog;
	}

	/**
	 * Removes the commerce data integration process log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceDataIntegrationProcessLogId the primary key of the commerce data integration process log
	 * @return the commerce data integration process log that was removed
	 * @throws NoSuchDataIntegrationProcessLogException if a commerce data integration process log with the primary key could not be found
	 */
	@Override
	public CommerceDataIntegrationProcessLog remove(
			long commerceDataIntegrationProcessLogId)
		throws NoSuchDataIntegrationProcessLogException {

		return remove((Serializable)commerceDataIntegrationProcessLogId);
	}

	/**
	 * Removes the commerce data integration process log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce data integration process log
	 * @return the commerce data integration process log that was removed
	 * @throws NoSuchDataIntegrationProcessLogException if a commerce data integration process log with the primary key could not be found
	 */
	@Override
	public CommerceDataIntegrationProcessLog remove(Serializable primaryKey)
		throws NoSuchDataIntegrationProcessLogException {

		Session session = null;

		try {
			session = openSession();

			CommerceDataIntegrationProcessLog
				commerceDataIntegrationProcessLog =
					(CommerceDataIntegrationProcessLog)session.get(
						CommerceDataIntegrationProcessLogImpl.class,
						primaryKey);

			if (commerceDataIntegrationProcessLog == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDataIntegrationProcessLogException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commerceDataIntegrationProcessLog);
		}
		catch (NoSuchDataIntegrationProcessLogException nsee) {
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
	protected CommerceDataIntegrationProcessLog removeImpl(
		CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceDataIntegrationProcessLog)) {
				commerceDataIntegrationProcessLog =
					(CommerceDataIntegrationProcessLog)session.get(
						CommerceDataIntegrationProcessLogImpl.class,
						commerceDataIntegrationProcessLog.getPrimaryKeyObj());
			}

			if (commerceDataIntegrationProcessLog != null) {
				session.delete(commerceDataIntegrationProcessLog);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceDataIntegrationProcessLog != null) {
			clearCache(commerceDataIntegrationProcessLog);
		}

		return commerceDataIntegrationProcessLog;
	}

	@Override
	public CommerceDataIntegrationProcessLog updateImpl(
		CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog) {

		boolean isNew = commerceDataIntegrationProcessLog.isNew();

		if (!(commerceDataIntegrationProcessLog instanceof
				CommerceDataIntegrationProcessLogModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(
					commerceDataIntegrationProcessLog.getClass())) {

				invocationHandler = ProxyUtil.getInvocationHandler(
					commerceDataIntegrationProcessLog);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceDataIntegrationProcessLog proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceDataIntegrationProcessLog implementation " +
					commerceDataIntegrationProcessLog.getClass());
		}

		CommerceDataIntegrationProcessLogModelImpl
			commerceDataIntegrationProcessLogModelImpl =
				(CommerceDataIntegrationProcessLogModelImpl)
					commerceDataIntegrationProcessLog;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew &&
			(commerceDataIntegrationProcessLog.getCreateDate() == null)) {

			if (serviceContext == null) {
				commerceDataIntegrationProcessLog.setCreateDate(now);
			}
			else {
				commerceDataIntegrationProcessLog.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!commerceDataIntegrationProcessLogModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceDataIntegrationProcessLog.setModifiedDate(now);
			}
			else {
				commerceDataIntegrationProcessLog.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceDataIntegrationProcessLog.isNew()) {
				session.save(commerceDataIntegrationProcessLog);

				commerceDataIntegrationProcessLog.setNew(false);
			}
			else {
				commerceDataIntegrationProcessLog =
					(CommerceDataIntegrationProcessLog)session.merge(
						commerceDataIntegrationProcessLog);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceDataIntegrationProcessLogModelImpl.
				COLUMN_BITMASK_ENABLED) {

			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				commerceDataIntegrationProcessLogModelImpl.
					getCDataIntegrationProcessId()
			};

			finderCache.removeResult(
				_finderPathCountByCDataIntegrationProcessId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCDataIntegrationProcessId,
				args);

			args = new Object[] {
				commerceDataIntegrationProcessLogModelImpl.
					getCDataIntegrationProcessId(),
				commerceDataIntegrationProcessLogModelImpl.getStatus()
			};

			finderCache.removeResult(_finderPathCountByC_S, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_S, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((commerceDataIntegrationProcessLogModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCDataIntegrationProcessId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commerceDataIntegrationProcessLogModelImpl.
						getOriginalCDataIntegrationProcessId()
				};

				finderCache.removeResult(
					_finderPathCountByCDataIntegrationProcessId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCDataIntegrationProcessId,
					args);

				args = new Object[] {
					commerceDataIntegrationProcessLogModelImpl.
						getCDataIntegrationProcessId()
				};

				finderCache.removeResult(
					_finderPathCountByCDataIntegrationProcessId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCDataIntegrationProcessId,
					args);
			}

			if ((commerceDataIntegrationProcessLogModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_S.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commerceDataIntegrationProcessLogModelImpl.
						getOriginalCDataIntegrationProcessId(),
					commerceDataIntegrationProcessLogModelImpl.
						getOriginalStatus()
				};

				finderCache.removeResult(_finderPathCountByC_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_S, args);

				args = new Object[] {
					commerceDataIntegrationProcessLogModelImpl.
						getCDataIntegrationProcessId(),
					commerceDataIntegrationProcessLogModelImpl.getStatus()
				};

				finderCache.removeResult(_finderPathCountByC_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_S, args);
			}
		}

		entityCache.putResult(
			CommerceDataIntegrationProcessLogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessLogImpl.class,
			commerceDataIntegrationProcessLog.getPrimaryKey(),
			commerceDataIntegrationProcessLog, false);

		commerceDataIntegrationProcessLog.resetOriginalValues();

		return commerceDataIntegrationProcessLog;
	}

	/**
	 * Returns the commerce data integration process log with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce data integration process log
	 * @return the commerce data integration process log
	 * @throws NoSuchDataIntegrationProcessLogException if a commerce data integration process log with the primary key could not be found
	 */
	@Override
	public CommerceDataIntegrationProcessLog findByPrimaryKey(
			Serializable primaryKey)
		throws NoSuchDataIntegrationProcessLogException {

		CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog =
			fetchByPrimaryKey(primaryKey);

		if (commerceDataIntegrationProcessLog == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDataIntegrationProcessLogException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commerceDataIntegrationProcessLog;
	}

	/**
	 * Returns the commerce data integration process log with the primary key or throws a <code>NoSuchDataIntegrationProcessLogException</code> if it could not be found.
	 *
	 * @param commerceDataIntegrationProcessLogId the primary key of the commerce data integration process log
	 * @return the commerce data integration process log
	 * @throws NoSuchDataIntegrationProcessLogException if a commerce data integration process log with the primary key could not be found
	 */
	@Override
	public CommerceDataIntegrationProcessLog findByPrimaryKey(
			long commerceDataIntegrationProcessLogId)
		throws NoSuchDataIntegrationProcessLogException {

		return findByPrimaryKey(
			(Serializable)commerceDataIntegrationProcessLogId);
	}

	/**
	 * Returns the commerce data integration process log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce data integration process log
	 * @return the commerce data integration process log, or <code>null</code> if a commerce data integration process log with the primary key could not be found
	 */
	@Override
	public CommerceDataIntegrationProcessLog fetchByPrimaryKey(
		Serializable primaryKey) {

		Serializable serializable = entityCache.getResult(
			CommerceDataIntegrationProcessLogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessLogImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog =
			(CommerceDataIntegrationProcessLog)serializable;

		if (commerceDataIntegrationProcessLog == null) {
			Session session = null;

			try {
				session = openSession();

				commerceDataIntegrationProcessLog =
					(CommerceDataIntegrationProcessLog)session.get(
						CommerceDataIntegrationProcessLogImpl.class,
						primaryKey);

				if (commerceDataIntegrationProcessLog != null) {
					cacheResult(commerceDataIntegrationProcessLog);
				}
				else {
					entityCache.putResult(
						CommerceDataIntegrationProcessLogModelImpl.
							ENTITY_CACHE_ENABLED,
						CommerceDataIntegrationProcessLogImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CommerceDataIntegrationProcessLogModelImpl.
						ENTITY_CACHE_ENABLED,
					CommerceDataIntegrationProcessLogImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceDataIntegrationProcessLog;
	}

	/**
	 * Returns the commerce data integration process log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceDataIntegrationProcessLogId the primary key of the commerce data integration process log
	 * @return the commerce data integration process log, or <code>null</code> if a commerce data integration process log with the primary key could not be found
	 */
	@Override
	public CommerceDataIntegrationProcessLog fetchByPrimaryKey(
		long commerceDataIntegrationProcessLogId) {

		return fetchByPrimaryKey(
			(Serializable)commerceDataIntegrationProcessLogId);
	}

	@Override
	public Map<Serializable, CommerceDataIntegrationProcessLog>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceDataIntegrationProcessLog> map =
			new HashMap<Serializable, CommerceDataIntegrationProcessLog>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceDataIntegrationProcessLog
				commerceDataIntegrationProcessLog = fetchByPrimaryKey(
					primaryKey);

			if (commerceDataIntegrationProcessLog != null) {
				map.put(primaryKey, commerceDataIntegrationProcessLog);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CommerceDataIntegrationProcessLogModelImpl.ENTITY_CACHE_ENABLED,
				CommerceDataIntegrationProcessLogImpl.class, primaryKey);

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
						(CommerceDataIntegrationProcessLog)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(
			_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESSLOG_WHERE_PKS_IN);

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

			for (CommerceDataIntegrationProcessLog
					commerceDataIntegrationProcessLog :
						(List<CommerceDataIntegrationProcessLog>)q.list()) {

				map.put(
					commerceDataIntegrationProcessLog.getPrimaryKeyObj(),
					commerceDataIntegrationProcessLog);

				cacheResult(commerceDataIntegrationProcessLog);

				uncachedPrimaryKeys.remove(
					commerceDataIntegrationProcessLog.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CommerceDataIntegrationProcessLogModelImpl.
						ENTITY_CACHE_ENABLED,
					CommerceDataIntegrationProcessLogImpl.class, primaryKey,
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
	 * Returns all the commerce data integration process logs.
	 *
	 * @return the commerce data integration process logs
	 */
	@Override
	public List<CommerceDataIntegrationProcessLog> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce data integration process logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceDataIntegrationProcessLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce data integration process logs
	 * @param end the upper bound of the range of commerce data integration process logs (not inclusive)
	 * @return the range of commerce data integration process logs
	 */
	@Override
	public List<CommerceDataIntegrationProcessLog> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce data integration process logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceDataIntegrationProcessLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce data integration process logs
	 * @param end the upper bound of the range of commerce data integration process logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce data integration process logs
	 */
	@Override
	public List<CommerceDataIntegrationProcessLog> findAll(
		int start, int end,
		OrderByComparator<CommerceDataIntegrationProcessLog>
			orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce data integration process logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceDataIntegrationProcessLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce data integration process logs
	 * @param end the upper bound of the range of commerce data integration process logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce data integration process logs
	 */
	@Override
	public List<CommerceDataIntegrationProcessLog> findAll(
		int start, int end,
		OrderByComparator<CommerceDataIntegrationProcessLog> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<CommerceDataIntegrationProcessLog> list = null;

		if (useFinderCache) {
			list =
				(List<CommerceDataIntegrationProcessLog>)finderCache.getResult(
					finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESSLOG);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESSLOG;

				sql = sql.concat(
					CommerceDataIntegrationProcessLogModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				list = (List<CommerceDataIntegrationProcessLog>)QueryUtil.list(
					q, getDialect(), start, end);

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
	 * Removes all the commerce data integration process logs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceDataIntegrationProcessLog
				commerceDataIntegrationProcessLog : findAll()) {

			remove(commerceDataIntegrationProcessLog);
		}
	}

	/**
	 * Returns the number of commerce data integration process logs.
	 *
	 * @return the number of commerce data integration process logs
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
					_SQL_COUNT_COMMERCEDATAINTEGRATIONPROCESSLOG);

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
		return CommerceDataIntegrationProcessLogModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce data integration process log persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CommerceDataIntegrationProcessLogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessLogModelImpl.FINDER_CACHE_ENABLED,
			CommerceDataIntegrationProcessLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CommerceDataIntegrationProcessLogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessLogModelImpl.FINDER_CACHE_ENABLED,
			CommerceDataIntegrationProcessLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CommerceDataIntegrationProcessLogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessLogModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByCDataIntegrationProcessId =
			new FinderPath(
				CommerceDataIntegrationProcessLogModelImpl.ENTITY_CACHE_ENABLED,
				CommerceDataIntegrationProcessLogModelImpl.FINDER_CACHE_ENABLED,
				CommerceDataIntegrationProcessLogImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByCDataIntegrationProcessId",
				new String[] {
					Long.class.getName(), Integer.class.getName(),
					Integer.class.getName(), OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByCDataIntegrationProcessId =
			new FinderPath(
				CommerceDataIntegrationProcessLogModelImpl.ENTITY_CACHE_ENABLED,
				CommerceDataIntegrationProcessLogModelImpl.FINDER_CACHE_ENABLED,
				CommerceDataIntegrationProcessLogImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByCDataIntegrationProcessId",
				new String[] {Long.class.getName()},
				CommerceDataIntegrationProcessLogModelImpl.
					CDATAINTEGRATIONPROCESSID_COLUMN_BITMASK |
				CommerceDataIntegrationProcessLogModelImpl.
					MODIFIEDDATE_COLUMN_BITMASK);

		_finderPathCountByCDataIntegrationProcessId = new FinderPath(
			CommerceDataIntegrationProcessLogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessLogModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCDataIntegrationProcessId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByC_S = new FinderPath(
			CommerceDataIntegrationProcessLogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessLogModelImpl.FINDER_CACHE_ENABLED,
			CommerceDataIntegrationProcessLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_S = new FinderPath(
			CommerceDataIntegrationProcessLogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessLogModelImpl.FINDER_CACHE_ENABLED,
			CommerceDataIntegrationProcessLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_S",
			new String[] {Long.class.getName(), Integer.class.getName()},
			CommerceDataIntegrationProcessLogModelImpl.
				CDATAINTEGRATIONPROCESSID_COLUMN_BITMASK |
			CommerceDataIntegrationProcessLogModelImpl.STATUS_COLUMN_BITMASK |
			CommerceDataIntegrationProcessLogModelImpl.
				MODIFIEDDATE_COLUMN_BITMASK);

		_finderPathCountByC_S = new FinderPath(
			CommerceDataIntegrationProcessLogModelImpl.ENTITY_CACHE_ENABLED,
			CommerceDataIntegrationProcessLogModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_S",
			new String[] {Long.class.getName(), Integer.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(
			CommerceDataIntegrationProcessLogImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESSLOG =
		"SELECT commerceDataIntegrationProcessLog FROM CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog";

	private static final String
		_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESSLOG_WHERE_PKS_IN =
			"SELECT commerceDataIntegrationProcessLog FROM CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog WHERE CDataIntegrationProcessLogId IN (";

	private static final String
		_SQL_SELECT_COMMERCEDATAINTEGRATIONPROCESSLOG_WHERE =
			"SELECT commerceDataIntegrationProcessLog FROM CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog WHERE ";

	private static final String _SQL_COUNT_COMMERCEDATAINTEGRATIONPROCESSLOG =
		"SELECT COUNT(commerceDataIntegrationProcessLog) FROM CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog";

	private static final String
		_SQL_COUNT_COMMERCEDATAINTEGRATIONPROCESSLOG_WHERE =
			"SELECT COUNT(commerceDataIntegrationProcessLog) FROM CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"commerceDataIntegrationProcessLog.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommerceDataIntegrationProcessLog exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommerceDataIntegrationProcessLog exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceDataIntegrationProcessLogPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"commerceDataIntegrationProcessLogId", "output"});

}