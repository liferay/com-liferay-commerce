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

package com.liferay.commerce.bom.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.bom.exception.NoSuchBOMFolderException;
import com.liferay.commerce.bom.model.CommerceBOMFolder;
import com.liferay.commerce.bom.model.impl.CommerceBOMFolderImpl;
import com.liferay.commerce.bom.model.impl.CommerceBOMFolderModelImpl;
import com.liferay.commerce.bom.service.persistence.CommerceBOMFolderPersistence;

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
 * The persistence implementation for the commerce bom folder service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceBOMFolderPersistence
 * @see com.liferay.commerce.bom.service.persistence.CommerceBOMFolderUtil
 * @generated
 */
@ProviderType
public class CommerceBOMFolderPersistenceImpl extends BasePersistenceImpl<CommerceBOMFolder>
	implements CommerceBOMFolderPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommerceBOMFolderUtil} to access the commerce bom folder persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommerceBOMFolderImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommerceBOMFolderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMFolderModelImpl.FINDER_CACHE_ENABLED,
			CommerceBOMFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommerceBOMFolderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMFolderModelImpl.FINDER_CACHE_ENABLED,
			CommerceBOMFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommerceBOMFolderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(CommerceBOMFolderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMFolderModelImpl.FINDER_CACHE_ENABLED,
			CommerceBOMFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(CommerceBOMFolderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMFolderModelImpl.FINDER_CACHE_ENABLED,
			CommerceBOMFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			CommerceBOMFolderModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceBOMFolderModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(CommerceBOMFolderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the commerce bom folders where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce bom folders
	 */
	@Override
	public List<CommerceBOMFolder> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the commerce bom folders where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce bom folders
	 * @param end the upper bound of the range of commerce bom folders (not inclusive)
	 * @return the range of matching commerce bom folders
	 */
	@Override
	public List<CommerceBOMFolder> findByCompanyId(long companyId, int start,
		int end) {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce bom folders where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce bom folders
	 * @param end the upper bound of the range of commerce bom folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce bom folders
	 */
	@Override
	public List<CommerceBOMFolder> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<CommerceBOMFolder> orderByComparator) {
		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce bom folders where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce bom folders
	 * @param end the upper bound of the range of commerce bom folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce bom folders
	 */
	@Override
	public List<CommerceBOMFolder> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<CommerceBOMFolder> orderByComparator,
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

		List<CommerceBOMFolder> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceBOMFolder>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceBOMFolder commerceBOMFolder : list) {
					if ((companyId != commerceBOMFolder.getCompanyId())) {
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

			query.append(_SQL_SELECT_COMMERCEBOMFOLDER_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceBOMFolderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<CommerceBOMFolder>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceBOMFolder>)QueryUtil.list(q,
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
	 * Returns the first commerce bom folder in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce bom folder
	 * @throws NoSuchBOMFolderException if a matching commerce bom folder could not be found
	 */
	@Override
	public CommerceBOMFolder findByCompanyId_First(long companyId,
		OrderByComparator<CommerceBOMFolder> orderByComparator)
		throws NoSuchBOMFolderException {
		CommerceBOMFolder commerceBOMFolder = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (commerceBOMFolder != null) {
			return commerceBOMFolder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchBOMFolderException(msg.toString());
	}

	/**
	 * Returns the first commerce bom folder in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce bom folder, or <code>null</code> if a matching commerce bom folder could not be found
	 */
	@Override
	public CommerceBOMFolder fetchByCompanyId_First(long companyId,
		OrderByComparator<CommerceBOMFolder> orderByComparator) {
		List<CommerceBOMFolder> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce bom folder in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce bom folder
	 * @throws NoSuchBOMFolderException if a matching commerce bom folder could not be found
	 */
	@Override
	public CommerceBOMFolder findByCompanyId_Last(long companyId,
		OrderByComparator<CommerceBOMFolder> orderByComparator)
		throws NoSuchBOMFolderException {
		CommerceBOMFolder commerceBOMFolder = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (commerceBOMFolder != null) {
			return commerceBOMFolder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchBOMFolderException(msg.toString());
	}

	/**
	 * Returns the last commerce bom folder in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce bom folder, or <code>null</code> if a matching commerce bom folder could not be found
	 */
	@Override
	public CommerceBOMFolder fetchByCompanyId_Last(long companyId,
		OrderByComparator<CommerceBOMFolder> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<CommerceBOMFolder> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce bom folders before and after the current commerce bom folder in the ordered set where companyId = &#63;.
	 *
	 * @param commerceBOMFolderId the primary key of the current commerce bom folder
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce bom folder
	 * @throws NoSuchBOMFolderException if a commerce bom folder with the primary key could not be found
	 */
	@Override
	public CommerceBOMFolder[] findByCompanyId_PrevAndNext(
		long commerceBOMFolderId, long companyId,
		OrderByComparator<CommerceBOMFolder> orderByComparator)
		throws NoSuchBOMFolderException {
		CommerceBOMFolder commerceBOMFolder = findByPrimaryKey(commerceBOMFolderId);

		Session session = null;

		try {
			session = openSession();

			CommerceBOMFolder[] array = new CommerceBOMFolderImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, commerceBOMFolder,
					companyId, orderByComparator, true);

			array[1] = commerceBOMFolder;

			array[2] = getByCompanyId_PrevAndNext(session, commerceBOMFolder,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceBOMFolder getByCompanyId_PrevAndNext(Session session,
		CommerceBOMFolder commerceBOMFolder, long companyId,
		OrderByComparator<CommerceBOMFolder> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COMMERCEBOMFOLDER_WHERE);

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
			query.append(CommerceBOMFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceBOMFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceBOMFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the commerce bom folders that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce bom folders that the user has permission to view
	 */
	@Override
	public List<CommerceBOMFolder> filterFindByCompanyId(long companyId) {
		return filterFindByCompanyId(companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce bom folders that the user has permission to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce bom folders
	 * @param end the upper bound of the range of commerce bom folders (not inclusive)
	 * @return the range of matching commerce bom folders that the user has permission to view
	 */
	@Override
	public List<CommerceBOMFolder> filterFindByCompanyId(long companyId,
		int start, int end) {
		return filterFindByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce bom folders that the user has permissions to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce bom folders
	 * @param end the upper bound of the range of commerce bom folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce bom folders that the user has permission to view
	 */
	@Override
	public List<CommerceBOMFolder> filterFindByCompanyId(long companyId,
		int start, int end,
		OrderByComparator<CommerceBOMFolder> orderByComparator) {
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
			query.append(_FILTER_SQL_SELECT_COMMERCEBOMFOLDER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_COMMERCEBOMFOLDER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEBOMFOLDER_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CommerceBOMFolderModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceBOMFolderModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceBOMFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, CommerceBOMFolderImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, CommerceBOMFolderImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			return (List<CommerceBOMFolder>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the commerce bom folders before and after the current commerce bom folder in the ordered set of commerce bom folders that the user has permission to view where companyId = &#63;.
	 *
	 * @param commerceBOMFolderId the primary key of the current commerce bom folder
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce bom folder
	 * @throws NoSuchBOMFolderException if a commerce bom folder with the primary key could not be found
	 */
	@Override
	public CommerceBOMFolder[] filterFindByCompanyId_PrevAndNext(
		long commerceBOMFolderId, long companyId,
		OrderByComparator<CommerceBOMFolder> orderByComparator)
		throws NoSuchBOMFolderException {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByCompanyId_PrevAndNext(commerceBOMFolderId, companyId,
				orderByComparator);
		}

		CommerceBOMFolder commerceBOMFolder = findByPrimaryKey(commerceBOMFolderId);

		Session session = null;

		try {
			session = openSession();

			CommerceBOMFolder[] array = new CommerceBOMFolderImpl[3];

			array[0] = filterGetByCompanyId_PrevAndNext(session,
					commerceBOMFolder, companyId, orderByComparator, true);

			array[1] = commerceBOMFolder;

			array[2] = filterGetByCompanyId_PrevAndNext(session,
					commerceBOMFolder, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceBOMFolder filterGetByCompanyId_PrevAndNext(
		Session session, CommerceBOMFolder commerceBOMFolder, long companyId,
		OrderByComparator<CommerceBOMFolder> orderByComparator, boolean previous) {
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
			query.append(_FILTER_SQL_SELECT_COMMERCEBOMFOLDER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_COMMERCEBOMFOLDER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEBOMFOLDER_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CommerceBOMFolderModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceBOMFolderModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceBOMFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CommerceBOMFolderImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CommerceBOMFolderImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceBOMFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceBOMFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce bom folders where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (CommerceBOMFolder commerceBOMFolder : findByCompanyId(companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(commerceBOMFolder);
		}
	}

	/**
	 * Returns the number of commerce bom folders where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce bom folders
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMMERCEBOMFOLDER_WHERE);

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
	 * Returns the number of commerce bom folders that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce bom folders that the user has permission to view
	 */
	@Override
	public int filterCountByCompanyId(long companyId) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return countByCompanyId(companyId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_COMMERCEBOMFOLDER_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceBOMFolder.class.getName(),
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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "commerceBOMFolder.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_P = new FinderPath(CommerceBOMFolderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMFolderModelImpl.FINDER_CACHE_ENABLED,
			CommerceBOMFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_P",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_P = new FinderPath(CommerceBOMFolderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMFolderModelImpl.FINDER_CACHE_ENABLED,
			CommerceBOMFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_P",
			new String[] { Long.class.getName(), Long.class.getName() },
			CommerceBOMFolderModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceBOMFolderModelImpl.PARENTCOMMERCEBOMFOLDERID_COLUMN_BITMASK |
			CommerceBOMFolderModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_P = new FinderPath(CommerceBOMFolderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_P",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the commerce bom folders where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentCommerceBOMFolderId the parent commerce bom folder ID
	 * @return the matching commerce bom folders
	 */
	@Override
	public List<CommerceBOMFolder> findByC_P(long companyId,
		long parentCommerceBOMFolderId) {
		return findByC_P(companyId, parentCommerceBOMFolderId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce bom folders where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param parentCommerceBOMFolderId the parent commerce bom folder ID
	 * @param start the lower bound of the range of commerce bom folders
	 * @param end the upper bound of the range of commerce bom folders (not inclusive)
	 * @return the range of matching commerce bom folders
	 */
	@Override
	public List<CommerceBOMFolder> findByC_P(long companyId,
		long parentCommerceBOMFolderId, int start, int end) {
		return findByC_P(companyId, parentCommerceBOMFolderId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce bom folders where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param parentCommerceBOMFolderId the parent commerce bom folder ID
	 * @param start the lower bound of the range of commerce bom folders
	 * @param end the upper bound of the range of commerce bom folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce bom folders
	 */
	@Override
	public List<CommerceBOMFolder> findByC_P(long companyId,
		long parentCommerceBOMFolderId, int start, int end,
		OrderByComparator<CommerceBOMFolder> orderByComparator) {
		return findByC_P(companyId, parentCommerceBOMFolderId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce bom folders where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param parentCommerceBOMFolderId the parent commerce bom folder ID
	 * @param start the lower bound of the range of commerce bom folders
	 * @param end the upper bound of the range of commerce bom folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching commerce bom folders
	 */
	@Override
	public List<CommerceBOMFolder> findByC_P(long companyId,
		long parentCommerceBOMFolderId, int start, int end,
		OrderByComparator<CommerceBOMFolder> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_P;
			finderArgs = new Object[] { companyId, parentCommerceBOMFolderId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_P;
			finderArgs = new Object[] {
					companyId, parentCommerceBOMFolderId,
					
					start, end, orderByComparator
				};
		}

		List<CommerceBOMFolder> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceBOMFolder>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceBOMFolder commerceBOMFolder : list) {
					if ((companyId != commerceBOMFolder.getCompanyId()) ||
							(parentCommerceBOMFolderId != commerceBOMFolder.getParentCommerceBOMFolderId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_COMMERCEBOMFOLDER_WHERE);

			query.append(_FINDER_COLUMN_C_P_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_P_PARENTCOMMERCEBOMFOLDERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CommerceBOMFolderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(parentCommerceBOMFolderId);

				if (!pagination) {
					list = (List<CommerceBOMFolder>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceBOMFolder>)QueryUtil.list(q,
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
	 * Returns the first commerce bom folder in the ordered set where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentCommerceBOMFolderId the parent commerce bom folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce bom folder
	 * @throws NoSuchBOMFolderException if a matching commerce bom folder could not be found
	 */
	@Override
	public CommerceBOMFolder findByC_P_First(long companyId,
		long parentCommerceBOMFolderId,
		OrderByComparator<CommerceBOMFolder> orderByComparator)
		throws NoSuchBOMFolderException {
		CommerceBOMFolder commerceBOMFolder = fetchByC_P_First(companyId,
				parentCommerceBOMFolderId, orderByComparator);

		if (commerceBOMFolder != null) {
			return commerceBOMFolder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", parentCommerceBOMFolderId=");
		msg.append(parentCommerceBOMFolderId);

		msg.append("}");

		throw new NoSuchBOMFolderException(msg.toString());
	}

	/**
	 * Returns the first commerce bom folder in the ordered set where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentCommerceBOMFolderId the parent commerce bom folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce bom folder, or <code>null</code> if a matching commerce bom folder could not be found
	 */
	@Override
	public CommerceBOMFolder fetchByC_P_First(long companyId,
		long parentCommerceBOMFolderId,
		OrderByComparator<CommerceBOMFolder> orderByComparator) {
		List<CommerceBOMFolder> list = findByC_P(companyId,
				parentCommerceBOMFolderId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce bom folder in the ordered set where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentCommerceBOMFolderId the parent commerce bom folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce bom folder
	 * @throws NoSuchBOMFolderException if a matching commerce bom folder could not be found
	 */
	@Override
	public CommerceBOMFolder findByC_P_Last(long companyId,
		long parentCommerceBOMFolderId,
		OrderByComparator<CommerceBOMFolder> orderByComparator)
		throws NoSuchBOMFolderException {
		CommerceBOMFolder commerceBOMFolder = fetchByC_P_Last(companyId,
				parentCommerceBOMFolderId, orderByComparator);

		if (commerceBOMFolder != null) {
			return commerceBOMFolder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", parentCommerceBOMFolderId=");
		msg.append(parentCommerceBOMFolderId);

		msg.append("}");

		throw new NoSuchBOMFolderException(msg.toString());
	}

	/**
	 * Returns the last commerce bom folder in the ordered set where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentCommerceBOMFolderId the parent commerce bom folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce bom folder, or <code>null</code> if a matching commerce bom folder could not be found
	 */
	@Override
	public CommerceBOMFolder fetchByC_P_Last(long companyId,
		long parentCommerceBOMFolderId,
		OrderByComparator<CommerceBOMFolder> orderByComparator) {
		int count = countByC_P(companyId, parentCommerceBOMFolderId);

		if (count == 0) {
			return null;
		}

		List<CommerceBOMFolder> list = findByC_P(companyId,
				parentCommerceBOMFolderId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce bom folders before and after the current commerce bom folder in the ordered set where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	 *
	 * @param commerceBOMFolderId the primary key of the current commerce bom folder
	 * @param companyId the company ID
	 * @param parentCommerceBOMFolderId the parent commerce bom folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce bom folder
	 * @throws NoSuchBOMFolderException if a commerce bom folder with the primary key could not be found
	 */
	@Override
	public CommerceBOMFolder[] findByC_P_PrevAndNext(long commerceBOMFolderId,
		long companyId, long parentCommerceBOMFolderId,
		OrderByComparator<CommerceBOMFolder> orderByComparator)
		throws NoSuchBOMFolderException {
		CommerceBOMFolder commerceBOMFolder = findByPrimaryKey(commerceBOMFolderId);

		Session session = null;

		try {
			session = openSession();

			CommerceBOMFolder[] array = new CommerceBOMFolderImpl[3];

			array[0] = getByC_P_PrevAndNext(session, commerceBOMFolder,
					companyId, parentCommerceBOMFolderId, orderByComparator,
					true);

			array[1] = commerceBOMFolder;

			array[2] = getByC_P_PrevAndNext(session, commerceBOMFolder,
					companyId, parentCommerceBOMFolderId, orderByComparator,
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

	protected CommerceBOMFolder getByC_P_PrevAndNext(Session session,
		CommerceBOMFolder commerceBOMFolder, long companyId,
		long parentCommerceBOMFolderId,
		OrderByComparator<CommerceBOMFolder> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_COMMERCEBOMFOLDER_WHERE);

		query.append(_FINDER_COLUMN_C_P_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_P_PARENTCOMMERCEBOMFOLDERID_2);

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
			query.append(CommerceBOMFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(parentCommerceBOMFolderId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceBOMFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceBOMFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the commerce bom folders that the user has permission to view where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentCommerceBOMFolderId the parent commerce bom folder ID
	 * @return the matching commerce bom folders that the user has permission to view
	 */
	@Override
	public List<CommerceBOMFolder> filterFindByC_P(long companyId,
		long parentCommerceBOMFolderId) {
		return filterFindByC_P(companyId, parentCommerceBOMFolderId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce bom folders that the user has permission to view where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param parentCommerceBOMFolderId the parent commerce bom folder ID
	 * @param start the lower bound of the range of commerce bom folders
	 * @param end the upper bound of the range of commerce bom folders (not inclusive)
	 * @return the range of matching commerce bom folders that the user has permission to view
	 */
	@Override
	public List<CommerceBOMFolder> filterFindByC_P(long companyId,
		long parentCommerceBOMFolderId, int start, int end) {
		return filterFindByC_P(companyId, parentCommerceBOMFolderId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the commerce bom folders that the user has permissions to view where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param parentCommerceBOMFolderId the parent commerce bom folder ID
	 * @param start the lower bound of the range of commerce bom folders
	 * @param end the upper bound of the range of commerce bom folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce bom folders that the user has permission to view
	 */
	@Override
	public List<CommerceBOMFolder> filterFindByC_P(long companyId,
		long parentCommerceBOMFolderId, int start, int end,
		OrderByComparator<CommerceBOMFolder> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByC_P(companyId, parentCommerceBOMFolderId, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEBOMFOLDER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_COMMERCEBOMFOLDER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_C_P_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_P_PARENTCOMMERCEBOMFOLDERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEBOMFOLDER_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CommerceBOMFolderModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceBOMFolderModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceBOMFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, CommerceBOMFolderImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, CommerceBOMFolderImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			qPos.add(parentCommerceBOMFolderId);

			return (List<CommerceBOMFolder>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the commerce bom folders before and after the current commerce bom folder in the ordered set of commerce bom folders that the user has permission to view where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	 *
	 * @param commerceBOMFolderId the primary key of the current commerce bom folder
	 * @param companyId the company ID
	 * @param parentCommerceBOMFolderId the parent commerce bom folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce bom folder
	 * @throws NoSuchBOMFolderException if a commerce bom folder with the primary key could not be found
	 */
	@Override
	public CommerceBOMFolder[] filterFindByC_P_PrevAndNext(
		long commerceBOMFolderId, long companyId,
		long parentCommerceBOMFolderId,
		OrderByComparator<CommerceBOMFolder> orderByComparator)
		throws NoSuchBOMFolderException {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByC_P_PrevAndNext(commerceBOMFolderId, companyId,
				parentCommerceBOMFolderId, orderByComparator);
		}

		CommerceBOMFolder commerceBOMFolder = findByPrimaryKey(commerceBOMFolderId);

		Session session = null;

		try {
			session = openSession();

			CommerceBOMFolder[] array = new CommerceBOMFolderImpl[3];

			array[0] = filterGetByC_P_PrevAndNext(session, commerceBOMFolder,
					companyId, parentCommerceBOMFolderId, orderByComparator,
					true);

			array[1] = commerceBOMFolder;

			array[2] = filterGetByC_P_PrevAndNext(session, commerceBOMFolder,
					companyId, parentCommerceBOMFolderId, orderByComparator,
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

	protected CommerceBOMFolder filterGetByC_P_PrevAndNext(Session session,
		CommerceBOMFolder commerceBOMFolder, long companyId,
		long parentCommerceBOMFolderId,
		OrderByComparator<CommerceBOMFolder> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEBOMFOLDER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_COMMERCEBOMFOLDER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_C_P_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_P_PARENTCOMMERCEBOMFOLDERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_COMMERCEBOMFOLDER_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(CommerceBOMFolderModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CommerceBOMFolderModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceBOMFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CommerceBOMFolderImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CommerceBOMFolderImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(parentCommerceBOMFolderId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(commerceBOMFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CommerceBOMFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce bom folders where companyId = &#63; and parentCommerceBOMFolderId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param parentCommerceBOMFolderId the parent commerce bom folder ID
	 */
	@Override
	public void removeByC_P(long companyId, long parentCommerceBOMFolderId) {
		for (CommerceBOMFolder commerceBOMFolder : findByC_P(companyId,
				parentCommerceBOMFolderId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(commerceBOMFolder);
		}
	}

	/**
	 * Returns the number of commerce bom folders where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentCommerceBOMFolderId the parent commerce bom folder ID
	 * @return the number of matching commerce bom folders
	 */
	@Override
	public int countByC_P(long companyId, long parentCommerceBOMFolderId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_P;

		Object[] finderArgs = new Object[] { companyId, parentCommerceBOMFolderId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEBOMFOLDER_WHERE);

			query.append(_FINDER_COLUMN_C_P_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_P_PARENTCOMMERCEBOMFOLDERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(parentCommerceBOMFolderId);

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
	 * Returns the number of commerce bom folders that the user has permission to view where companyId = &#63; and parentCommerceBOMFolderId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentCommerceBOMFolderId the parent commerce bom folder ID
	 * @return the number of matching commerce bom folders that the user has permission to view
	 */
	@Override
	public int filterCountByC_P(long companyId, long parentCommerceBOMFolderId) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return countByC_P(companyId, parentCommerceBOMFolderId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_COMMERCEBOMFOLDER_WHERE);

		query.append(_FINDER_COLUMN_C_P_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_P_PARENTCOMMERCEBOMFOLDERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CommerceBOMFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			qPos.add(parentCommerceBOMFolderId);

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

	private static final String _FINDER_COLUMN_C_P_COMPANYID_2 = "commerceBOMFolder.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_P_PARENTCOMMERCEBOMFOLDERID_2 = "commerceBOMFolder.parentCommerceBOMFolderId = ?";

	public CommerceBOMFolderPersistenceImpl() {
		setModelClass(CommerceBOMFolder.class);
	}

	/**
	 * Caches the commerce bom folder in the entity cache if it is enabled.
	 *
	 * @param commerceBOMFolder the commerce bom folder
	 */
	@Override
	public void cacheResult(CommerceBOMFolder commerceBOMFolder) {
		entityCache.putResult(CommerceBOMFolderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMFolderImpl.class, commerceBOMFolder.getPrimaryKey(),
			commerceBOMFolder);

		commerceBOMFolder.resetOriginalValues();
	}

	/**
	 * Caches the commerce bom folders in the entity cache if it is enabled.
	 *
	 * @param commerceBOMFolders the commerce bom folders
	 */
	@Override
	public void cacheResult(List<CommerceBOMFolder> commerceBOMFolders) {
		for (CommerceBOMFolder commerceBOMFolder : commerceBOMFolders) {
			if (entityCache.getResult(
						CommerceBOMFolderModelImpl.ENTITY_CACHE_ENABLED,
						CommerceBOMFolderImpl.class,
						commerceBOMFolder.getPrimaryKey()) == null) {
				cacheResult(commerceBOMFolder);
			}
			else {
				commerceBOMFolder.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce bom folders.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceBOMFolderImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce bom folder.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceBOMFolder commerceBOMFolder) {
		entityCache.removeResult(CommerceBOMFolderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMFolderImpl.class, commerceBOMFolder.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CommerceBOMFolder> commerceBOMFolders) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceBOMFolder commerceBOMFolder : commerceBOMFolders) {
			entityCache.removeResult(CommerceBOMFolderModelImpl.ENTITY_CACHE_ENABLED,
				CommerceBOMFolderImpl.class, commerceBOMFolder.getPrimaryKey());
		}
	}

	/**
	 * Creates a new commerce bom folder with the primary key. Does not add the commerce bom folder to the database.
	 *
	 * @param commerceBOMFolderId the primary key for the new commerce bom folder
	 * @return the new commerce bom folder
	 */
	@Override
	public CommerceBOMFolder create(long commerceBOMFolderId) {
		CommerceBOMFolder commerceBOMFolder = new CommerceBOMFolderImpl();

		commerceBOMFolder.setNew(true);
		commerceBOMFolder.setPrimaryKey(commerceBOMFolderId);

		commerceBOMFolder.setCompanyId(companyProvider.getCompanyId());

		return commerceBOMFolder;
	}

	/**
	 * Removes the commerce bom folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceBOMFolderId the primary key of the commerce bom folder
	 * @return the commerce bom folder that was removed
	 * @throws NoSuchBOMFolderException if a commerce bom folder with the primary key could not be found
	 */
	@Override
	public CommerceBOMFolder remove(long commerceBOMFolderId)
		throws NoSuchBOMFolderException {
		return remove((Serializable)commerceBOMFolderId);
	}

	/**
	 * Removes the commerce bom folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce bom folder
	 * @return the commerce bom folder that was removed
	 * @throws NoSuchBOMFolderException if a commerce bom folder with the primary key could not be found
	 */
	@Override
	public CommerceBOMFolder remove(Serializable primaryKey)
		throws NoSuchBOMFolderException {
		Session session = null;

		try {
			session = openSession();

			CommerceBOMFolder commerceBOMFolder = (CommerceBOMFolder)session.get(CommerceBOMFolderImpl.class,
					primaryKey);

			if (commerceBOMFolder == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBOMFolderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commerceBOMFolder);
		}
		catch (NoSuchBOMFolderException nsee) {
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
	protected CommerceBOMFolder removeImpl(CommerceBOMFolder commerceBOMFolder) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceBOMFolder)) {
				commerceBOMFolder = (CommerceBOMFolder)session.get(CommerceBOMFolderImpl.class,
						commerceBOMFolder.getPrimaryKeyObj());
			}

			if (commerceBOMFolder != null) {
				session.delete(commerceBOMFolder);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceBOMFolder != null) {
			clearCache(commerceBOMFolder);
		}

		return commerceBOMFolder;
	}

	@Override
	public CommerceBOMFolder updateImpl(CommerceBOMFolder commerceBOMFolder) {
		boolean isNew = commerceBOMFolder.isNew();

		if (!(commerceBOMFolder instanceof CommerceBOMFolderModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceBOMFolder.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(commerceBOMFolder);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceBOMFolder proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceBOMFolder implementation " +
				commerceBOMFolder.getClass());
		}

		CommerceBOMFolderModelImpl commerceBOMFolderModelImpl = (CommerceBOMFolderModelImpl)commerceBOMFolder;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceBOMFolder.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceBOMFolder.setCreateDate(now);
			}
			else {
				commerceBOMFolder.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!commerceBOMFolderModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceBOMFolder.setModifiedDate(now);
			}
			else {
				commerceBOMFolder.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceBOMFolder.isNew()) {
				session.save(commerceBOMFolder);

				commerceBOMFolder.setNew(false);
			}
			else {
				commerceBOMFolder = (CommerceBOMFolder)session.merge(commerceBOMFolder);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceBOMFolderModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					commerceBOMFolderModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
				args);

			args = new Object[] {
					commerceBOMFolderModelImpl.getCompanyId(),
					commerceBOMFolderModelImpl.getParentCommerceBOMFolderId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_P, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_P,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((commerceBOMFolderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceBOMFolderModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { commerceBOMFolderModelImpl.getCompanyId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((commerceBOMFolderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						commerceBOMFolderModelImpl.getOriginalCompanyId(),
						commerceBOMFolderModelImpl.getOriginalParentCommerceBOMFolderId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_P, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_P,
					args);

				args = new Object[] {
						commerceBOMFolderModelImpl.getCompanyId(),
						commerceBOMFolderModelImpl.getParentCommerceBOMFolderId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_P, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_P,
					args);
			}
		}

		entityCache.putResult(CommerceBOMFolderModelImpl.ENTITY_CACHE_ENABLED,
			CommerceBOMFolderImpl.class, commerceBOMFolder.getPrimaryKey(),
			commerceBOMFolder, false);

		commerceBOMFolder.resetOriginalValues();

		return commerceBOMFolder;
	}

	/**
	 * Returns the commerce bom folder with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce bom folder
	 * @return the commerce bom folder
	 * @throws NoSuchBOMFolderException if a commerce bom folder with the primary key could not be found
	 */
	@Override
	public CommerceBOMFolder findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBOMFolderException {
		CommerceBOMFolder commerceBOMFolder = fetchByPrimaryKey(primaryKey);

		if (commerceBOMFolder == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBOMFolderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commerceBOMFolder;
	}

	/**
	 * Returns the commerce bom folder with the primary key or throws a {@link NoSuchBOMFolderException} if it could not be found.
	 *
	 * @param commerceBOMFolderId the primary key of the commerce bom folder
	 * @return the commerce bom folder
	 * @throws NoSuchBOMFolderException if a commerce bom folder with the primary key could not be found
	 */
	@Override
	public CommerceBOMFolder findByPrimaryKey(long commerceBOMFolderId)
		throws NoSuchBOMFolderException {
		return findByPrimaryKey((Serializable)commerceBOMFolderId);
	}

	/**
	 * Returns the commerce bom folder with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce bom folder
	 * @return the commerce bom folder, or <code>null</code> if a commerce bom folder with the primary key could not be found
	 */
	@Override
	public CommerceBOMFolder fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CommerceBOMFolderModelImpl.ENTITY_CACHE_ENABLED,
				CommerceBOMFolderImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceBOMFolder commerceBOMFolder = (CommerceBOMFolder)serializable;

		if (commerceBOMFolder == null) {
			Session session = null;

			try {
				session = openSession();

				commerceBOMFolder = (CommerceBOMFolder)session.get(CommerceBOMFolderImpl.class,
						primaryKey);

				if (commerceBOMFolder != null) {
					cacheResult(commerceBOMFolder);
				}
				else {
					entityCache.putResult(CommerceBOMFolderModelImpl.ENTITY_CACHE_ENABLED,
						CommerceBOMFolderImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommerceBOMFolderModelImpl.ENTITY_CACHE_ENABLED,
					CommerceBOMFolderImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceBOMFolder;
	}

	/**
	 * Returns the commerce bom folder with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceBOMFolderId the primary key of the commerce bom folder
	 * @return the commerce bom folder, or <code>null</code> if a commerce bom folder with the primary key could not be found
	 */
	@Override
	public CommerceBOMFolder fetchByPrimaryKey(long commerceBOMFolderId) {
		return fetchByPrimaryKey((Serializable)commerceBOMFolderId);
	}

	@Override
	public Map<Serializable, CommerceBOMFolder> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceBOMFolder> map = new HashMap<Serializable, CommerceBOMFolder>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceBOMFolder commerceBOMFolder = fetchByPrimaryKey(primaryKey);

			if (commerceBOMFolder != null) {
				map.put(primaryKey, commerceBOMFolder);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CommerceBOMFolderModelImpl.ENTITY_CACHE_ENABLED,
					CommerceBOMFolderImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceBOMFolder)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMMERCEBOMFOLDER_WHERE_PKS_IN);

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

			for (CommerceBOMFolder commerceBOMFolder : (List<CommerceBOMFolder>)q.list()) {
				map.put(commerceBOMFolder.getPrimaryKeyObj(), commerceBOMFolder);

				cacheResult(commerceBOMFolder);

				uncachedPrimaryKeys.remove(commerceBOMFolder.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CommerceBOMFolderModelImpl.ENTITY_CACHE_ENABLED,
					CommerceBOMFolderImpl.class, primaryKey, nullModel);
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
	 * Returns all the commerce bom folders.
	 *
	 * @return the commerce bom folders
	 */
	@Override
	public List<CommerceBOMFolder> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce bom folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce bom folders
	 * @param end the upper bound of the range of commerce bom folders (not inclusive)
	 * @return the range of commerce bom folders
	 */
	@Override
	public List<CommerceBOMFolder> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce bom folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce bom folders
	 * @param end the upper bound of the range of commerce bom folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce bom folders
	 */
	@Override
	public List<CommerceBOMFolder> findAll(int start, int end,
		OrderByComparator<CommerceBOMFolder> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce bom folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceBOMFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce bom folders
	 * @param end the upper bound of the range of commerce bom folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce bom folders
	 */
	@Override
	public List<CommerceBOMFolder> findAll(int start, int end,
		OrderByComparator<CommerceBOMFolder> orderByComparator,
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

		List<CommerceBOMFolder> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceBOMFolder>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEBOMFOLDER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEBOMFOLDER;

				if (pagination) {
					sql = sql.concat(CommerceBOMFolderModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceBOMFolder>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceBOMFolder>)QueryUtil.list(q,
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
	 * Removes all the commerce bom folders from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceBOMFolder commerceBOMFolder : findAll()) {
			remove(commerceBOMFolder);
		}
	}

	/**
	 * Returns the number of commerce bom folders.
	 *
	 * @return the number of commerce bom folders
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCEBOMFOLDER);

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
		return CommerceBOMFolderModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce bom folder persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CommerceBOMFolderImpl.class.getName());
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
	private static final String _SQL_SELECT_COMMERCEBOMFOLDER = "SELECT commerceBOMFolder FROM CommerceBOMFolder commerceBOMFolder";
	private static final String _SQL_SELECT_COMMERCEBOMFOLDER_WHERE_PKS_IN = "SELECT commerceBOMFolder FROM CommerceBOMFolder commerceBOMFolder WHERE commerceBOMFolderId IN (";
	private static final String _SQL_SELECT_COMMERCEBOMFOLDER_WHERE = "SELECT commerceBOMFolder FROM CommerceBOMFolder commerceBOMFolder WHERE ";
	private static final String _SQL_COUNT_COMMERCEBOMFOLDER = "SELECT COUNT(commerceBOMFolder) FROM CommerceBOMFolder commerceBOMFolder";
	private static final String _SQL_COUNT_COMMERCEBOMFOLDER_WHERE = "SELECT COUNT(commerceBOMFolder) FROM CommerceBOMFolder commerceBOMFolder WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "commerceBOMFolder.commerceBOMFolderId";
	private static final String _FILTER_SQL_SELECT_COMMERCEBOMFOLDER_WHERE = "SELECT DISTINCT {commerceBOMFolder.*} FROM CommerceBOMFolder commerceBOMFolder WHERE ";
	private static final String _FILTER_SQL_SELECT_COMMERCEBOMFOLDER_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {CommerceBOMFolder.*} FROM (SELECT DISTINCT commerceBOMFolder.commerceBOMFolderId FROM CommerceBOMFolder commerceBOMFolder WHERE ";
	private static final String _FILTER_SQL_SELECT_COMMERCEBOMFOLDER_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN CommerceBOMFolder ON TEMP_TABLE.commerceBOMFolderId = CommerceBOMFolder.commerceBOMFolderId";
	private static final String _FILTER_SQL_COUNT_COMMERCEBOMFOLDER_WHERE = "SELECT COUNT(DISTINCT commerceBOMFolder.commerceBOMFolderId) AS COUNT_VALUE FROM CommerceBOMFolder commerceBOMFolder WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "commerceBOMFolder";
	private static final String _FILTER_ENTITY_TABLE = "CommerceBOMFolder";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceBOMFolder.";
	private static final String _ORDER_BY_ENTITY_TABLE = "CommerceBOMFolder.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommerceBOMFolder exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CommerceBOMFolder exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CommerceBOMFolderPersistenceImpl.class);
}