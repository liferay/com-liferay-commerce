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

package com.liferay.commerce.product.type.virtual.service.persistence.impl;

import com.liferay.commerce.product.type.virtual.exception.NoSuchCPDefinitionVirtualSettingException;
import com.liferay.commerce.product.type.virtual.model.CPDefinitionVirtualSetting;
import com.liferay.commerce.product.type.virtual.model.impl.CPDefinitionVirtualSettingImpl;
import com.liferay.commerce.product.type.virtual.model.impl.CPDefinitionVirtualSettingModelImpl;
import com.liferay.commerce.product.type.virtual.service.persistence.CPDefinitionVirtualSettingPersistence;
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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
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
 * The persistence implementation for the cp definition virtual setting service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @generated
 */
public class CPDefinitionVirtualSettingPersistenceImpl
	extends BasePersistenceImpl<CPDefinitionVirtualSetting>
	implements CPDefinitionVirtualSettingPersistence {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CPDefinitionVirtualSettingUtil</code> to access the cp definition virtual setting persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CPDefinitionVirtualSettingImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the cp definition virtual settings where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cp definition virtual settings
	 */
	@Override
	public List<CPDefinitionVirtualSetting> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp definition virtual settings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionVirtualSettingModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp definition virtual settings
	 * @param end the upper bound of the range of cp definition virtual settings (not inclusive)
	 * @return the range of matching cp definition virtual settings
	 */
	@Override
	public List<CPDefinitionVirtualSetting> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp definition virtual settings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionVirtualSettingModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp definition virtual settings
	 * @param end the upper bound of the range of cp definition virtual settings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp definition virtual settings
	 */
	@Override
	public List<CPDefinitionVirtualSetting> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPDefinitionVirtualSetting> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp definition virtual settings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionVirtualSettingModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cp definition virtual settings
	 * @param end the upper bound of the range of cp definition virtual settings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp definition virtual settings
	 */
	@Override
	public List<CPDefinitionVirtualSetting> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CPDefinitionVirtualSetting> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<CPDefinitionVirtualSetting> list = null;

		if (useFinderCache) {
			list = (List<CPDefinitionVirtualSetting>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPDefinitionVirtualSetting cpDefinitionVirtualSetting :
						list) {

					if (!uuid.equals(cpDefinitionVirtualSetting.getUuid())) {
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

			query.append(_SQL_SELECT_CPDEFINITIONVIRTUALSETTING_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CPDefinitionVirtualSettingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<CPDefinitionVirtualSetting>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPDefinitionVirtualSetting>)QueryUtil.list(
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
	 * Returns the first cp definition virtual setting in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition virtual setting
	 * @throws NoSuchCPDefinitionVirtualSettingException if a matching cp definition virtual setting could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting findByUuid_First(
			String uuid,
			OrderByComparator<CPDefinitionVirtualSetting> orderByComparator)
		throws NoSuchCPDefinitionVirtualSettingException {

		CPDefinitionVirtualSetting cpDefinitionVirtualSetting =
			fetchByUuid_First(uuid, orderByComparator);

		if (cpDefinitionVirtualSetting != null) {
			return cpDefinitionVirtualSetting;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCPDefinitionVirtualSettingException(msg.toString());
	}

	/**
	 * Returns the first cp definition virtual setting in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition virtual setting, or <code>null</code> if a matching cp definition virtual setting could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting fetchByUuid_First(
		String uuid,
		OrderByComparator<CPDefinitionVirtualSetting> orderByComparator) {

		List<CPDefinitionVirtualSetting> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp definition virtual setting in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition virtual setting
	 * @throws NoSuchCPDefinitionVirtualSettingException if a matching cp definition virtual setting could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting findByUuid_Last(
			String uuid,
			OrderByComparator<CPDefinitionVirtualSetting> orderByComparator)
		throws NoSuchCPDefinitionVirtualSettingException {

		CPDefinitionVirtualSetting cpDefinitionVirtualSetting =
			fetchByUuid_Last(uuid, orderByComparator);

		if (cpDefinitionVirtualSetting != null) {
			return cpDefinitionVirtualSetting;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchCPDefinitionVirtualSettingException(msg.toString());
	}

	/**
	 * Returns the last cp definition virtual setting in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition virtual setting, or <code>null</code> if a matching cp definition virtual setting could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting fetchByUuid_Last(
		String uuid,
		OrderByComparator<CPDefinitionVirtualSetting> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CPDefinitionVirtualSetting> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp definition virtual settings before and after the current cp definition virtual setting in the ordered set where uuid = &#63;.
	 *
	 * @param CPDefinitionVirtualSettingId the primary key of the current cp definition virtual setting
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp definition virtual setting
	 * @throws NoSuchCPDefinitionVirtualSettingException if a cp definition virtual setting with the primary key could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting[] findByUuid_PrevAndNext(
			long CPDefinitionVirtualSettingId, String uuid,
			OrderByComparator<CPDefinitionVirtualSetting> orderByComparator)
		throws NoSuchCPDefinitionVirtualSettingException {

		uuid = Objects.toString(uuid, "");

		CPDefinitionVirtualSetting cpDefinitionVirtualSetting =
			findByPrimaryKey(CPDefinitionVirtualSettingId);

		Session session = null;

		try {
			session = openSession();

			CPDefinitionVirtualSetting[] array =
				new CPDefinitionVirtualSettingImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, cpDefinitionVirtualSetting, uuid, orderByComparator,
				true);

			array[1] = cpDefinitionVirtualSetting;

			array[2] = getByUuid_PrevAndNext(
				session, cpDefinitionVirtualSetting, uuid, orderByComparator,
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

	protected CPDefinitionVirtualSetting getByUuid_PrevAndNext(
		Session session, CPDefinitionVirtualSetting cpDefinitionVirtualSetting,
		String uuid,
		OrderByComparator<CPDefinitionVirtualSetting> orderByComparator,
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

		query.append(_SQL_SELECT_CPDEFINITIONVIRTUALSETTING_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
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
			query.append(CPDefinitionVirtualSettingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpDefinitionVirtualSetting)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPDefinitionVirtualSetting> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp definition virtual settings where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CPDefinitionVirtualSetting cpDefinitionVirtualSetting :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(cpDefinitionVirtualSetting);
		}
	}

	/**
	 * Returns the number of cp definition virtual settings where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cp definition virtual settings
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPDEFINITIONVIRTUALSETTING_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
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

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"cpDefinitionVirtualSetting.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(cpDefinitionVirtualSetting.uuid IS NULL OR cpDefinitionVirtualSetting.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the cp definition virtual setting where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchCPDefinitionVirtualSettingException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp definition virtual setting
	 * @throws NoSuchCPDefinitionVirtualSettingException if a matching cp definition virtual setting could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting findByUUID_G(String uuid, long groupId)
		throws NoSuchCPDefinitionVirtualSettingException {

		CPDefinitionVirtualSetting cpDefinitionVirtualSetting = fetchByUUID_G(
			uuid, groupId);

		if (cpDefinitionVirtualSetting == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCPDefinitionVirtualSettingException(msg.toString());
		}

		return cpDefinitionVirtualSetting;
	}

	/**
	 * Returns the cp definition virtual setting where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching cp definition virtual setting, or <code>null</code> if a matching cp definition virtual setting could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the cp definition virtual setting where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching cp definition virtual setting, or <code>null</code> if a matching cp definition virtual setting could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {uuid, groupId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof CPDefinitionVirtualSetting) {
			CPDefinitionVirtualSetting cpDefinitionVirtualSetting =
				(CPDefinitionVirtualSetting)result;

			if (!Objects.equals(uuid, cpDefinitionVirtualSetting.getUuid()) ||
				(groupId != cpDefinitionVirtualSetting.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CPDEFINITIONVIRTUALSETTING_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<CPDefinitionVirtualSetting> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					CPDefinitionVirtualSetting cpDefinitionVirtualSetting =
						list.get(0);

					result = cpDefinitionVirtualSetting;

					cacheResult(cpDefinitionVirtualSetting);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByUUID_G, finderArgs);
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
			return (CPDefinitionVirtualSetting)result;
		}
	}

	/**
	 * Removes the cp definition virtual setting where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the cp definition virtual setting that was removed
	 */
	@Override
	public CPDefinitionVirtualSetting removeByUUID_G(String uuid, long groupId)
		throws NoSuchCPDefinitionVirtualSettingException {

		CPDefinitionVirtualSetting cpDefinitionVirtualSetting = findByUUID_G(
			uuid, groupId);

		return remove(cpDefinitionVirtualSetting);
	}

	/**
	 * Returns the number of cp definition virtual settings where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching cp definition virtual settings
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPDEFINITIONVIRTUALSETTING_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"cpDefinitionVirtualSetting.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(cpDefinitionVirtualSetting.uuid IS NULL OR cpDefinitionVirtualSetting.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"cpDefinitionVirtualSetting.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the cp definition virtual settings where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching cp definition virtual settings
	 */
	@Override
	public List<CPDefinitionVirtualSetting> findByUuid_C(
		String uuid, long companyId) {

		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp definition virtual settings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionVirtualSettingModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp definition virtual settings
	 * @param end the upper bound of the range of cp definition virtual settings (not inclusive)
	 * @return the range of matching cp definition virtual settings
	 */
	@Override
	public List<CPDefinitionVirtualSetting> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp definition virtual settings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionVirtualSettingModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp definition virtual settings
	 * @param end the upper bound of the range of cp definition virtual settings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp definition virtual settings
	 */
	@Override
	public List<CPDefinitionVirtualSetting> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPDefinitionVirtualSetting> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp definition virtual settings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionVirtualSettingModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of cp definition virtual settings
	 * @param end the upper bound of the range of cp definition virtual settings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp definition virtual settings
	 */
	@Override
	public List<CPDefinitionVirtualSetting> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CPDefinitionVirtualSetting> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid_C;
				finderArgs = new Object[] {uuid, companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<CPDefinitionVirtualSetting> list = null;

		if (useFinderCache) {
			list = (List<CPDefinitionVirtualSetting>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPDefinitionVirtualSetting cpDefinitionVirtualSetting :
						list) {

					if (!uuid.equals(cpDefinitionVirtualSetting.getUuid()) ||
						(companyId !=
							cpDefinitionVirtualSetting.getCompanyId())) {

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

			query.append(_SQL_SELECT_CPDEFINITIONVIRTUALSETTING_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CPDefinitionVirtualSettingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<CPDefinitionVirtualSetting>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPDefinitionVirtualSetting>)QueryUtil.list(
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
	 * Returns the first cp definition virtual setting in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition virtual setting
	 * @throws NoSuchCPDefinitionVirtualSettingException if a matching cp definition virtual setting could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CPDefinitionVirtualSetting> orderByComparator)
		throws NoSuchCPDefinitionVirtualSettingException {

		CPDefinitionVirtualSetting cpDefinitionVirtualSetting =
			fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (cpDefinitionVirtualSetting != null) {
			return cpDefinitionVirtualSetting;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPDefinitionVirtualSettingException(msg.toString());
	}

	/**
	 * Returns the first cp definition virtual setting in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp definition virtual setting, or <code>null</code> if a matching cp definition virtual setting could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CPDefinitionVirtualSetting> orderByComparator) {

		List<CPDefinitionVirtualSetting> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp definition virtual setting in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition virtual setting
	 * @throws NoSuchCPDefinitionVirtualSettingException if a matching cp definition virtual setting could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CPDefinitionVirtualSetting> orderByComparator)
		throws NoSuchCPDefinitionVirtualSettingException {

		CPDefinitionVirtualSetting cpDefinitionVirtualSetting =
			fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (cpDefinitionVirtualSetting != null) {
			return cpDefinitionVirtualSetting;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCPDefinitionVirtualSettingException(msg.toString());
	}

	/**
	 * Returns the last cp definition virtual setting in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp definition virtual setting, or <code>null</code> if a matching cp definition virtual setting could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CPDefinitionVirtualSetting> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CPDefinitionVirtualSetting> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp definition virtual settings before and after the current cp definition virtual setting in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param CPDefinitionVirtualSettingId the primary key of the current cp definition virtual setting
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp definition virtual setting
	 * @throws NoSuchCPDefinitionVirtualSettingException if a cp definition virtual setting with the primary key could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting[] findByUuid_C_PrevAndNext(
			long CPDefinitionVirtualSettingId, String uuid, long companyId,
			OrderByComparator<CPDefinitionVirtualSetting> orderByComparator)
		throws NoSuchCPDefinitionVirtualSettingException {

		uuid = Objects.toString(uuid, "");

		CPDefinitionVirtualSetting cpDefinitionVirtualSetting =
			findByPrimaryKey(CPDefinitionVirtualSettingId);

		Session session = null;

		try {
			session = openSession();

			CPDefinitionVirtualSetting[] array =
				new CPDefinitionVirtualSettingImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, cpDefinitionVirtualSetting, uuid, companyId,
				orderByComparator, true);

			array[1] = cpDefinitionVirtualSetting;

			array[2] = getByUuid_C_PrevAndNext(
				session, cpDefinitionVirtualSetting, uuid, companyId,
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

	protected CPDefinitionVirtualSetting getByUuid_C_PrevAndNext(
		Session session, CPDefinitionVirtualSetting cpDefinitionVirtualSetting,
		String uuid, long companyId,
		OrderByComparator<CPDefinitionVirtualSetting> orderByComparator,
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

		query.append(_SQL_SELECT_CPDEFINITIONVIRTUALSETTING_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

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
			query.append(CPDefinitionVirtualSettingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						cpDefinitionVirtualSetting)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CPDefinitionVirtualSetting> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp definition virtual settings where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CPDefinitionVirtualSetting cpDefinitionVirtualSetting :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(cpDefinitionVirtualSetting);
		}
	}

	/**
	 * Returns the number of cp definition virtual settings where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching cp definition virtual settings
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPDEFINITIONVIRTUALSETTING_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"cpDefinitionVirtualSetting.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(cpDefinitionVirtualSetting.uuid IS NULL OR cpDefinitionVirtualSetting.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"cpDefinitionVirtualSetting.companyId = ?";

	private FinderPath _finderPathFetchByC_C;
	private FinderPath _finderPathCountByC_C;

	/**
	 * Returns the cp definition virtual setting where classNameId = &#63; and classPK = &#63; or throws a <code>NoSuchCPDefinitionVirtualSettingException</code> if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching cp definition virtual setting
	 * @throws NoSuchCPDefinitionVirtualSettingException if a matching cp definition virtual setting could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting findByC_C(long classNameId, long classPK)
		throws NoSuchCPDefinitionVirtualSettingException {

		CPDefinitionVirtualSetting cpDefinitionVirtualSetting = fetchByC_C(
			classNameId, classPK);

		if (cpDefinitionVirtualSetting == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCPDefinitionVirtualSettingException(msg.toString());
		}

		return cpDefinitionVirtualSetting;
	}

	/**
	 * Returns the cp definition virtual setting where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching cp definition virtual setting, or <code>null</code> if a matching cp definition virtual setting could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting fetchByC_C(
		long classNameId, long classPK) {

		return fetchByC_C(classNameId, classPK, true);
	}

	/**
	 * Returns the cp definition virtual setting where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching cp definition virtual setting, or <code>null</code> if a matching cp definition virtual setting could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting fetchByC_C(
		long classNameId, long classPK, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {classNameId, classPK};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_C, finderArgs, this);
		}

		if (result instanceof CPDefinitionVirtualSetting) {
			CPDefinitionVirtualSetting cpDefinitionVirtualSetting =
				(CPDefinitionVirtualSetting)result;

			if ((classNameId != cpDefinitionVirtualSetting.getClassNameId()) ||
				(classPK != cpDefinitionVirtualSetting.getClassPK())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CPDEFINITIONVIRTUALSETTING_WHERE);

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

				List<CPDefinitionVirtualSetting> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByC_C, finderArgs, list);
					}
				}
				else {
					CPDefinitionVirtualSetting cpDefinitionVirtualSetting =
						list.get(0);

					result = cpDefinitionVirtualSetting;

					cacheResult(cpDefinitionVirtualSetting);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(_finderPathFetchByC_C, finderArgs);
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
			return (CPDefinitionVirtualSetting)result;
		}
	}

	/**
	 * Removes the cp definition virtual setting where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the cp definition virtual setting that was removed
	 */
	@Override
	public CPDefinitionVirtualSetting removeByC_C(
			long classNameId, long classPK)
		throws NoSuchCPDefinitionVirtualSettingException {

		CPDefinitionVirtualSetting cpDefinitionVirtualSetting = findByC_C(
			classNameId, classPK);

		return remove(cpDefinitionVirtualSetting);
	}

	/**
	 * Returns the number of cp definition virtual settings where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching cp definition virtual settings
	 */
	@Override
	public int countByC_C(long classNameId, long classPK) {
		FinderPath finderPath = _finderPathCountByC_C;

		Object[] finderArgs = new Object[] {classNameId, classPK};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CPDEFINITIONVIRTUALSETTING_WHERE);

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
		"cpDefinitionVirtualSetting.classNameId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 =
		"cpDefinitionVirtualSetting.classPK = ?";

	public CPDefinitionVirtualSettingPersistenceImpl() {
		setModelClass(CPDefinitionVirtualSetting.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put(
			"termsOfUseJournalArticleResourcePrimKey",
			"termsOfUseArticleResourcePK");

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
	 * Caches the cp definition virtual setting in the entity cache if it is enabled.
	 *
	 * @param cpDefinitionVirtualSetting the cp definition virtual setting
	 */
	@Override
	public void cacheResult(
		CPDefinitionVirtualSetting cpDefinitionVirtualSetting) {

		entityCache.putResult(
			CPDefinitionVirtualSettingModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionVirtualSettingImpl.class,
			cpDefinitionVirtualSetting.getPrimaryKey(),
			cpDefinitionVirtualSetting);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				cpDefinitionVirtualSetting.getUuid(),
				cpDefinitionVirtualSetting.getGroupId()
			},
			cpDefinitionVirtualSetting);

		finderCache.putResult(
			_finderPathFetchByC_C,
			new Object[] {
				cpDefinitionVirtualSetting.getClassNameId(),
				cpDefinitionVirtualSetting.getClassPK()
			},
			cpDefinitionVirtualSetting);

		cpDefinitionVirtualSetting.resetOriginalValues();
	}

	/**
	 * Caches the cp definition virtual settings in the entity cache if it is enabled.
	 *
	 * @param cpDefinitionVirtualSettings the cp definition virtual settings
	 */
	@Override
	public void cacheResult(
		List<CPDefinitionVirtualSetting> cpDefinitionVirtualSettings) {

		for (CPDefinitionVirtualSetting cpDefinitionVirtualSetting :
				cpDefinitionVirtualSettings) {

			if (entityCache.getResult(
					CPDefinitionVirtualSettingModelImpl.ENTITY_CACHE_ENABLED,
					CPDefinitionVirtualSettingImpl.class,
					cpDefinitionVirtualSetting.getPrimaryKey()) == null) {

				cacheResult(cpDefinitionVirtualSetting);
			}
			else {
				cpDefinitionVirtualSetting.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cp definition virtual settings.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CPDefinitionVirtualSettingImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cp definition virtual setting.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CPDefinitionVirtualSetting cpDefinitionVirtualSetting) {

		entityCache.removeResult(
			CPDefinitionVirtualSettingModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionVirtualSettingImpl.class,
			cpDefinitionVirtualSetting.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(CPDefinitionVirtualSettingModelImpl)cpDefinitionVirtualSetting,
			true);
	}

	@Override
	public void clearCache(
		List<CPDefinitionVirtualSetting> cpDefinitionVirtualSettings) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CPDefinitionVirtualSetting cpDefinitionVirtualSetting :
				cpDefinitionVirtualSettings) {

			entityCache.removeResult(
				CPDefinitionVirtualSettingModelImpl.ENTITY_CACHE_ENABLED,
				CPDefinitionVirtualSettingImpl.class,
				cpDefinitionVirtualSetting.getPrimaryKey());

			clearUniqueFindersCache(
				(CPDefinitionVirtualSettingModelImpl)cpDefinitionVirtualSetting,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		CPDefinitionVirtualSettingModelImpl
			cpDefinitionVirtualSettingModelImpl) {

		Object[] args = new Object[] {
			cpDefinitionVirtualSettingModelImpl.getUuid(),
			cpDefinitionVirtualSettingModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, cpDefinitionVirtualSettingModelImpl,
			false);

		args = new Object[] {
			cpDefinitionVirtualSettingModelImpl.getClassNameId(),
			cpDefinitionVirtualSettingModelImpl.getClassPK()
		};

		finderCache.putResult(
			_finderPathCountByC_C, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_C, args, cpDefinitionVirtualSettingModelImpl,
			false);
	}

	protected void clearUniqueFindersCache(
		CPDefinitionVirtualSettingModelImpl cpDefinitionVirtualSettingModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				cpDefinitionVirtualSettingModelImpl.getUuid(),
				cpDefinitionVirtualSettingModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((cpDefinitionVirtualSettingModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				cpDefinitionVirtualSettingModelImpl.getOriginalUuid(),
				cpDefinitionVirtualSettingModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
				cpDefinitionVirtualSettingModelImpl.getClassNameId(),
				cpDefinitionVirtualSettingModelImpl.getClassPK()
			};

			finderCache.removeResult(_finderPathCountByC_C, args);
			finderCache.removeResult(_finderPathFetchByC_C, args);
		}

		if ((cpDefinitionVirtualSettingModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_C.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				cpDefinitionVirtualSettingModelImpl.getOriginalClassNameId(),
				cpDefinitionVirtualSettingModelImpl.getOriginalClassPK()
			};

			finderCache.removeResult(_finderPathCountByC_C, args);
			finderCache.removeResult(_finderPathFetchByC_C, args);
		}
	}

	/**
	 * Creates a new cp definition virtual setting with the primary key. Does not add the cp definition virtual setting to the database.
	 *
	 * @param CPDefinitionVirtualSettingId the primary key for the new cp definition virtual setting
	 * @return the new cp definition virtual setting
	 */
	@Override
	public CPDefinitionVirtualSetting create(
		long CPDefinitionVirtualSettingId) {

		CPDefinitionVirtualSetting cpDefinitionVirtualSetting =
			new CPDefinitionVirtualSettingImpl();

		cpDefinitionVirtualSetting.setNew(true);
		cpDefinitionVirtualSetting.setPrimaryKey(CPDefinitionVirtualSettingId);

		String uuid = PortalUUIDUtil.generate();

		cpDefinitionVirtualSetting.setUuid(uuid);

		cpDefinitionVirtualSetting.setCompanyId(
			CompanyThreadLocal.getCompanyId());

		return cpDefinitionVirtualSetting;
	}

	/**
	 * Removes the cp definition virtual setting with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPDefinitionVirtualSettingId the primary key of the cp definition virtual setting
	 * @return the cp definition virtual setting that was removed
	 * @throws NoSuchCPDefinitionVirtualSettingException if a cp definition virtual setting with the primary key could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting remove(long CPDefinitionVirtualSettingId)
		throws NoSuchCPDefinitionVirtualSettingException {

		return remove((Serializable)CPDefinitionVirtualSettingId);
	}

	/**
	 * Removes the cp definition virtual setting with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cp definition virtual setting
	 * @return the cp definition virtual setting that was removed
	 * @throws NoSuchCPDefinitionVirtualSettingException if a cp definition virtual setting with the primary key could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting remove(Serializable primaryKey)
		throws NoSuchCPDefinitionVirtualSettingException {

		Session session = null;

		try {
			session = openSession();

			CPDefinitionVirtualSetting cpDefinitionVirtualSetting =
				(CPDefinitionVirtualSetting)session.get(
					CPDefinitionVirtualSettingImpl.class, primaryKey);

			if (cpDefinitionVirtualSetting == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCPDefinitionVirtualSettingException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(cpDefinitionVirtualSetting);
		}
		catch (NoSuchCPDefinitionVirtualSettingException nsee) {
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
	protected CPDefinitionVirtualSetting removeImpl(
		CPDefinitionVirtualSetting cpDefinitionVirtualSetting) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cpDefinitionVirtualSetting)) {
				cpDefinitionVirtualSetting =
					(CPDefinitionVirtualSetting)session.get(
						CPDefinitionVirtualSettingImpl.class,
						cpDefinitionVirtualSetting.getPrimaryKeyObj());
			}

			if (cpDefinitionVirtualSetting != null) {
				session.delete(cpDefinitionVirtualSetting);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cpDefinitionVirtualSetting != null) {
			clearCache(cpDefinitionVirtualSetting);
		}

		return cpDefinitionVirtualSetting;
	}

	@Override
	public CPDefinitionVirtualSetting updateImpl(
		CPDefinitionVirtualSetting cpDefinitionVirtualSetting) {

		boolean isNew = cpDefinitionVirtualSetting.isNew();

		if (!(cpDefinitionVirtualSetting instanceof
				CPDefinitionVirtualSettingModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(cpDefinitionVirtualSetting.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					cpDefinitionVirtualSetting);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in cpDefinitionVirtualSetting proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CPDefinitionVirtualSetting implementation " +
					cpDefinitionVirtualSetting.getClass());
		}

		CPDefinitionVirtualSettingModelImpl
			cpDefinitionVirtualSettingModelImpl =
				(CPDefinitionVirtualSettingModelImpl)cpDefinitionVirtualSetting;

		if (Validator.isNull(cpDefinitionVirtualSetting.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			cpDefinitionVirtualSetting.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (cpDefinitionVirtualSetting.getCreateDate() == null)) {
			if (serviceContext == null) {
				cpDefinitionVirtualSetting.setCreateDate(now);
			}
			else {
				cpDefinitionVirtualSetting.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!cpDefinitionVirtualSettingModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				cpDefinitionVirtualSetting.setModifiedDate(now);
			}
			else {
				cpDefinitionVirtualSetting.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (cpDefinitionVirtualSetting.isNew()) {
				session.save(cpDefinitionVirtualSetting);

				cpDefinitionVirtualSetting.setNew(false);
			}
			else {
				cpDefinitionVirtualSetting =
					(CPDefinitionVirtualSetting)session.merge(
						cpDefinitionVirtualSetting);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CPDefinitionVirtualSettingModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				cpDefinitionVirtualSettingModelImpl.getUuid()
			};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				cpDefinitionVirtualSettingModelImpl.getUuid(),
				cpDefinitionVirtualSettingModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((cpDefinitionVirtualSettingModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpDefinitionVirtualSettingModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {
					cpDefinitionVirtualSettingModelImpl.getUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((cpDefinitionVirtualSettingModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					cpDefinitionVirtualSettingModelImpl.getOriginalUuid(),
					cpDefinitionVirtualSettingModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					cpDefinitionVirtualSettingModelImpl.getUuid(),
					cpDefinitionVirtualSettingModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}
		}

		entityCache.putResult(
			CPDefinitionVirtualSettingModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionVirtualSettingImpl.class,
			cpDefinitionVirtualSetting.getPrimaryKey(),
			cpDefinitionVirtualSetting, false);

		clearUniqueFindersCache(cpDefinitionVirtualSettingModelImpl, false);
		cacheUniqueFindersCache(cpDefinitionVirtualSettingModelImpl);

		cpDefinitionVirtualSetting.resetOriginalValues();

		return cpDefinitionVirtualSetting;
	}

	/**
	 * Returns the cp definition virtual setting with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp definition virtual setting
	 * @return the cp definition virtual setting
	 * @throws NoSuchCPDefinitionVirtualSettingException if a cp definition virtual setting with the primary key could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCPDefinitionVirtualSettingException {

		CPDefinitionVirtualSetting cpDefinitionVirtualSetting =
			fetchByPrimaryKey(primaryKey);

		if (cpDefinitionVirtualSetting == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCPDefinitionVirtualSettingException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return cpDefinitionVirtualSetting;
	}

	/**
	 * Returns the cp definition virtual setting with the primary key or throws a <code>NoSuchCPDefinitionVirtualSettingException</code> if it could not be found.
	 *
	 * @param CPDefinitionVirtualSettingId the primary key of the cp definition virtual setting
	 * @return the cp definition virtual setting
	 * @throws NoSuchCPDefinitionVirtualSettingException if a cp definition virtual setting with the primary key could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting findByPrimaryKey(
			long CPDefinitionVirtualSettingId)
		throws NoSuchCPDefinitionVirtualSettingException {

		return findByPrimaryKey((Serializable)CPDefinitionVirtualSettingId);
	}

	/**
	 * Returns the cp definition virtual setting with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp definition virtual setting
	 * @return the cp definition virtual setting, or <code>null</code> if a cp definition virtual setting with the primary key could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting fetchByPrimaryKey(
		Serializable primaryKey) {

		Serializable serializable = entityCache.getResult(
			CPDefinitionVirtualSettingModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionVirtualSettingImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CPDefinitionVirtualSetting cpDefinitionVirtualSetting =
			(CPDefinitionVirtualSetting)serializable;

		if (cpDefinitionVirtualSetting == null) {
			Session session = null;

			try {
				session = openSession();

				cpDefinitionVirtualSetting =
					(CPDefinitionVirtualSetting)session.get(
						CPDefinitionVirtualSettingImpl.class, primaryKey);

				if (cpDefinitionVirtualSetting != null) {
					cacheResult(cpDefinitionVirtualSetting);
				}
				else {
					entityCache.putResult(
						CPDefinitionVirtualSettingModelImpl.
							ENTITY_CACHE_ENABLED,
						CPDefinitionVirtualSettingImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					CPDefinitionVirtualSettingModelImpl.ENTITY_CACHE_ENABLED,
					CPDefinitionVirtualSettingImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cpDefinitionVirtualSetting;
	}

	/**
	 * Returns the cp definition virtual setting with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param CPDefinitionVirtualSettingId the primary key of the cp definition virtual setting
	 * @return the cp definition virtual setting, or <code>null</code> if a cp definition virtual setting with the primary key could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting fetchByPrimaryKey(
		long CPDefinitionVirtualSettingId) {

		return fetchByPrimaryKey((Serializable)CPDefinitionVirtualSettingId);
	}

	@Override
	public Map<Serializable, CPDefinitionVirtualSetting> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CPDefinitionVirtualSetting> map =
			new HashMap<Serializable, CPDefinitionVirtualSetting>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CPDefinitionVirtualSetting cpDefinitionVirtualSetting =
				fetchByPrimaryKey(primaryKey);

			if (cpDefinitionVirtualSetting != null) {
				map.put(primaryKey, cpDefinitionVirtualSetting);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				CPDefinitionVirtualSettingModelImpl.ENTITY_CACHE_ENABLED,
				CPDefinitionVirtualSettingImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(
						primaryKey, (CPDefinitionVirtualSetting)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_CPDEFINITIONVIRTUALSETTING_WHERE_PKS_IN);

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

			for (CPDefinitionVirtualSetting cpDefinitionVirtualSetting :
					(List<CPDefinitionVirtualSetting>)q.list()) {

				map.put(
					cpDefinitionVirtualSetting.getPrimaryKeyObj(),
					cpDefinitionVirtualSetting);

				cacheResult(cpDefinitionVirtualSetting);

				uncachedPrimaryKeys.remove(
					cpDefinitionVirtualSetting.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					CPDefinitionVirtualSettingModelImpl.ENTITY_CACHE_ENABLED,
					CPDefinitionVirtualSettingImpl.class, primaryKey,
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
	 * Returns all the cp definition virtual settings.
	 *
	 * @return the cp definition virtual settings
	 */
	@Override
	public List<CPDefinitionVirtualSetting> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp definition virtual settings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionVirtualSettingModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp definition virtual settings
	 * @param end the upper bound of the range of cp definition virtual settings (not inclusive)
	 * @return the range of cp definition virtual settings
	 */
	@Override
	public List<CPDefinitionVirtualSetting> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp definition virtual settings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionVirtualSettingModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp definition virtual settings
	 * @param end the upper bound of the range of cp definition virtual settings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cp definition virtual settings
	 */
	@Override
	public List<CPDefinitionVirtualSetting> findAll(
		int start, int end,
		OrderByComparator<CPDefinitionVirtualSetting> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp definition virtual settings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CPDefinitionVirtualSettingModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp definition virtual settings
	 * @param end the upper bound of the range of cp definition virtual settings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of cp definition virtual settings
	 */
	@Override
	public List<CPDefinitionVirtualSetting> findAll(
		int start, int end,
		OrderByComparator<CPDefinitionVirtualSetting> orderByComparator,
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

		List<CPDefinitionVirtualSetting> list = null;

		if (useFinderCache) {
			list = (List<CPDefinitionVirtualSetting>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CPDEFINITIONVIRTUALSETTING);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CPDEFINITIONVIRTUALSETTING;

				if (pagination) {
					sql = sql.concat(
						CPDefinitionVirtualSettingModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CPDefinitionVirtualSetting>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CPDefinitionVirtualSetting>)QueryUtil.list(
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
	 * Removes all the cp definition virtual settings from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CPDefinitionVirtualSetting cpDefinitionVirtualSetting :
				findAll()) {

			remove(cpDefinitionVirtualSetting);
		}
	}

	/**
	 * Returns the number of cp definition virtual settings.
	 *
	 * @return the number of cp definition virtual settings
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
					_SQL_COUNT_CPDEFINITIONVIRTUALSETTING);

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
		return CPDefinitionVirtualSettingModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cp definition virtual setting persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			CPDefinitionVirtualSettingModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionVirtualSettingModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionVirtualSettingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			CPDefinitionVirtualSettingModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionVirtualSettingModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionVirtualSettingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			CPDefinitionVirtualSettingModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionVirtualSettingModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			CPDefinitionVirtualSettingModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionVirtualSettingModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionVirtualSettingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			CPDefinitionVirtualSettingModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionVirtualSettingModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionVirtualSettingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			CPDefinitionVirtualSettingModelImpl.UUID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			CPDefinitionVirtualSettingModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionVirtualSettingModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUuid", new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			CPDefinitionVirtualSettingModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionVirtualSettingModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionVirtualSettingImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			CPDefinitionVirtualSettingModelImpl.UUID_COLUMN_BITMASK |
			CPDefinitionVirtualSettingModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			CPDefinitionVirtualSettingModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionVirtualSettingModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			CPDefinitionVirtualSettingModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionVirtualSettingModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionVirtualSettingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			CPDefinitionVirtualSettingModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionVirtualSettingModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionVirtualSettingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			CPDefinitionVirtualSettingModelImpl.UUID_COLUMN_BITMASK |
			CPDefinitionVirtualSettingModelImpl.COMPANYID_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			CPDefinitionVirtualSettingModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionVirtualSettingModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathFetchByC_C = new FinderPath(
			CPDefinitionVirtualSettingModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionVirtualSettingModelImpl.FINDER_CACHE_ENABLED,
			CPDefinitionVirtualSettingImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_C",
			new String[] {Long.class.getName(), Long.class.getName()},
			CPDefinitionVirtualSettingModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			CPDefinitionVirtualSettingModelImpl.CLASSPK_COLUMN_BITMASK);

		_finderPathCountByC_C = new FinderPath(
			CPDefinitionVirtualSettingModelImpl.ENTITY_CACHE_ENABLED,
			CPDefinitionVirtualSettingModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] {Long.class.getName(), Long.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(CPDefinitionVirtualSettingImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_CPDEFINITIONVIRTUALSETTING =
		"SELECT cpDefinitionVirtualSetting FROM CPDefinitionVirtualSetting cpDefinitionVirtualSetting";

	private static final String
		_SQL_SELECT_CPDEFINITIONVIRTUALSETTING_WHERE_PKS_IN =
			"SELECT cpDefinitionVirtualSetting FROM CPDefinitionVirtualSetting cpDefinitionVirtualSetting WHERE CPDefinitionVirtualSettingId IN (";

	private static final String _SQL_SELECT_CPDEFINITIONVIRTUALSETTING_WHERE =
		"SELECT cpDefinitionVirtualSetting FROM CPDefinitionVirtualSetting cpDefinitionVirtualSetting WHERE ";

	private static final String _SQL_COUNT_CPDEFINITIONVIRTUALSETTING =
		"SELECT COUNT(cpDefinitionVirtualSetting) FROM CPDefinitionVirtualSetting cpDefinitionVirtualSetting";

	private static final String _SQL_COUNT_CPDEFINITIONVIRTUALSETTING_WHERE =
		"SELECT COUNT(cpDefinitionVirtualSetting) FROM CPDefinitionVirtualSetting cpDefinitionVirtualSetting WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"cpDefinitionVirtualSetting.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CPDefinitionVirtualSetting exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CPDefinitionVirtualSetting exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CPDefinitionVirtualSettingPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "termsOfUseJournalArticleResourcePrimKey"});

}