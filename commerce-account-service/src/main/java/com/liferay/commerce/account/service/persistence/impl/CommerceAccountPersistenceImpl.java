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

import com.liferay.commerce.account.exception.NoSuchAccountException;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.model.impl.CommerceAccountImpl;
import com.liferay.commerce.account.model.impl.CommerceAccountModelImpl;
import com.liferay.commerce.account.service.persistence.CommerceAccountPersistence;

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
import com.liferay.portal.kernel.util.StringUtil;
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
 * The persistence implementation for the commerce account service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see CommerceAccountPersistence
 * @see com.liferay.commerce.account.service.persistence.CommerceAccountUtil
 * @generated
 */
@ProviderType
public class CommerceAccountPersistenceImpl extends BasePersistenceImpl<CommerceAccount>
	implements CommerceAccountPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommerceAccountUtil} to access the commerce account persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommerceAccountImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommerceAccountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommerceAccountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommerceAccountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_C_N = new FinderPath(CommerceAccountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByC_N",
			new String[] { Long.class.getName(), String.class.getName() },
			CommerceAccountModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceAccountModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_N = new FinderPath(CommerceAccountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_N",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the commerce account where companyId = &#63; and name = &#63; or throws a {@link NoSuchAccountException} if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching commerce account
	 * @throws NoSuchAccountException if a matching commerce account could not be found
	 */
	@Override
	public CommerceAccount findByC_N(long companyId, String name)
		throws NoSuchAccountException {
		CommerceAccount commerceAccount = fetchByC_N(companyId, name);

		if (commerceAccount == null) {
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

			throw new NoSuchAccountException(msg.toString());
		}

		return commerceAccount;
	}

	/**
	 * Returns the commerce account where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching commerce account, or <code>null</code> if a matching commerce account could not be found
	 */
	@Override
	public CommerceAccount fetchByC_N(long companyId, String name) {
		return fetchByC_N(companyId, name, true);
	}

	/**
	 * Returns the commerce account where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching commerce account, or <code>null</code> if a matching commerce account could not be found
	 */
	@Override
	public CommerceAccount fetchByC_N(long companyId, String name,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { companyId, name };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_C_N,
					finderArgs, this);
		}

		if (result instanceof CommerceAccount) {
			CommerceAccount commerceAccount = (CommerceAccount)result;

			if ((companyId != commerceAccount.getCompanyId()) ||
					!Objects.equals(name, commerceAccount.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COMMERCEACCOUNT_WHERE);

			query.append(_FINDER_COLUMN_C_N_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_NAME_1);
			}
			else if (name.equals("")) {
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

				List<CommerceAccount> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_C_N, finderArgs,
						list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"CommerceAccountPersistenceImpl.fetchByC_N(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CommerceAccount commerceAccount = list.get(0);

					result = commerceAccount;

					cacheResult(commerceAccount);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_C_N, finderArgs);

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
			return (CommerceAccount)result;
		}
	}

	/**
	 * Removes the commerce account where companyId = &#63; and name = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the commerce account that was removed
	 */
	@Override
	public CommerceAccount removeByC_N(long companyId, String name)
		throws NoSuchAccountException {
		CommerceAccount commerceAccount = findByC_N(companyId, name);

		return remove(commerceAccount);
	}

	/**
	 * Returns the number of commerce accounts where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the number of matching commerce accounts
	 */
	@Override
	public int countByC_N(long companyId, String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_N;

		Object[] finderArgs = new Object[] { companyId, name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEACCOUNT_WHERE);

			query.append(_FINDER_COLUMN_C_N_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_NAME_1);
			}
			else if (name.equals("")) {
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

	private static final String _FINDER_COLUMN_C_N_COMPANYID_2 = "commerceAccount.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_N_NAME_1 = "commerceAccount.name IS NULL";
	private static final String _FINDER_COLUMN_C_N_NAME_2 = "commerceAccount.name = ?";
	private static final String _FINDER_COLUMN_C_N_NAME_3 = "(commerceAccount.name IS NULL OR commerceAccount.name = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_C_ERC = new FinderPath(CommerceAccountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountModelImpl.FINDER_CACHE_ENABLED,
			CommerceAccountImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_ERC",
			new String[] { Long.class.getName(), String.class.getName() },
			CommerceAccountModelImpl.COMPANYID_COLUMN_BITMASK |
			CommerceAccountModelImpl.EXTERNALREFERENCECODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_ERC = new FinderPath(CommerceAccountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_ERC",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the commerce account where companyId = &#63; and externalReferenceCode = &#63; or throws a {@link NoSuchAccountException} if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce account
	 * @throws NoSuchAccountException if a matching commerce account could not be found
	 */
	@Override
	public CommerceAccount findByC_ERC(long companyId,
		String externalReferenceCode) throws NoSuchAccountException {
		CommerceAccount commerceAccount = fetchByC_ERC(companyId,
				externalReferenceCode);

		if (commerceAccount == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", externalReferenceCode=");
			msg.append(externalReferenceCode);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchAccountException(msg.toString());
		}

		return commerceAccount;
	}

	/**
	 * Returns the commerce account where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce account, or <code>null</code> if a matching commerce account could not be found
	 */
	@Override
	public CommerceAccount fetchByC_ERC(long companyId,
		String externalReferenceCode) {
		return fetchByC_ERC(companyId, externalReferenceCode, true);
	}

	/**
	 * Returns the commerce account where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching commerce account, or <code>null</code> if a matching commerce account could not be found
	 */
	@Override
	public CommerceAccount fetchByC_ERC(long companyId,
		String externalReferenceCode, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { companyId, externalReferenceCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_C_ERC,
					finderArgs, this);
		}

		if (result instanceof CommerceAccount) {
			CommerceAccount commerceAccount = (CommerceAccount)result;

			if ((companyId != commerceAccount.getCompanyId()) ||
					!Objects.equals(externalReferenceCode,
						commerceAccount.getExternalReferenceCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COMMERCEACCOUNT_WHERE);

			query.append(_FINDER_COLUMN_C_ERC_COMPANYID_2);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode == null) {
				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_1);
			}
			else if (externalReferenceCode.equals("")) {
				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3);
			}
			else {
				bindExternalReferenceCode = true;

				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindExternalReferenceCode) {
					qPos.add(externalReferenceCode);
				}

				List<CommerceAccount> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_C_ERC,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"CommerceAccountPersistenceImpl.fetchByC_ERC(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CommerceAccount commerceAccount = list.get(0);

					result = commerceAccount;

					cacheResult(commerceAccount);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_C_ERC, finderArgs);

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
			return (CommerceAccount)result;
		}
	}

	/**
	 * Removes the commerce account where companyId = &#63; and externalReferenceCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the commerce account that was removed
	 */
	@Override
	public CommerceAccount removeByC_ERC(long companyId,
		String externalReferenceCode) throws NoSuchAccountException {
		CommerceAccount commerceAccount = findByC_ERC(companyId,
				externalReferenceCode);

		return remove(commerceAccount);
	}

	/**
	 * Returns the number of commerce accounts where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the number of matching commerce accounts
	 */
	@Override
	public int countByC_ERC(long companyId, String externalReferenceCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_ERC;

		Object[] finderArgs = new Object[] { companyId, externalReferenceCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COMMERCEACCOUNT_WHERE);

			query.append(_FINDER_COLUMN_C_ERC_COMPANYID_2);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode == null) {
				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_1);
			}
			else if (externalReferenceCode.equals("")) {
				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3);
			}
			else {
				bindExternalReferenceCode = true;

				query.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindExternalReferenceCode) {
					qPos.add(externalReferenceCode);
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

	private static final String _FINDER_COLUMN_C_ERC_COMPANYID_2 = "commerceAccount.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_1 = "commerceAccount.externalReferenceCode IS NULL";
	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2 = "commerceAccount.externalReferenceCode = ?";
	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3 = "(commerceAccount.externalReferenceCode IS NULL OR commerceAccount.externalReferenceCode = '')";

	public CommerceAccountPersistenceImpl() {
		setModelClass(CommerceAccount.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("active", "active_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the commerce account in the entity cache if it is enabled.
	 *
	 * @param commerceAccount the commerce account
	 */
	@Override
	public void cacheResult(CommerceAccount commerceAccount) {
		entityCache.putResult(CommerceAccountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountImpl.class, commerceAccount.getPrimaryKey(),
			commerceAccount);

		finderCache.putResult(FINDER_PATH_FETCH_BY_C_N,
			new Object[] {
				commerceAccount.getCompanyId(), commerceAccount.getName()
			}, commerceAccount);

		finderCache.putResult(FINDER_PATH_FETCH_BY_C_ERC,
			new Object[] {
				commerceAccount.getCompanyId(),
				commerceAccount.getExternalReferenceCode()
			}, commerceAccount);

		commerceAccount.resetOriginalValues();
	}

	/**
	 * Caches the commerce accounts in the entity cache if it is enabled.
	 *
	 * @param commerceAccounts the commerce accounts
	 */
	@Override
	public void cacheResult(List<CommerceAccount> commerceAccounts) {
		for (CommerceAccount commerceAccount : commerceAccounts) {
			if (entityCache.getResult(
						CommerceAccountModelImpl.ENTITY_CACHE_ENABLED,
						CommerceAccountImpl.class,
						commerceAccount.getPrimaryKey()) == null) {
				cacheResult(commerceAccount);
			}
			else {
				commerceAccount.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commerce accounts.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceAccountImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commerce account.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceAccount commerceAccount) {
		entityCache.removeResult(CommerceAccountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountImpl.class, commerceAccount.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CommerceAccountModelImpl)commerceAccount, true);
	}

	@Override
	public void clearCache(List<CommerceAccount> commerceAccounts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommerceAccount commerceAccount : commerceAccounts) {
			entityCache.removeResult(CommerceAccountModelImpl.ENTITY_CACHE_ENABLED,
				CommerceAccountImpl.class, commerceAccount.getPrimaryKey());

			clearUniqueFindersCache((CommerceAccountModelImpl)commerceAccount,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		CommerceAccountModelImpl commerceAccountModelImpl) {
		Object[] args = new Object[] {
				commerceAccountModelImpl.getCompanyId(),
				commerceAccountModelImpl.getName()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_C_N, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_C_N, args,
			commerceAccountModelImpl, false);

		args = new Object[] {
				commerceAccountModelImpl.getCompanyId(),
				commerceAccountModelImpl.getExternalReferenceCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_C_ERC, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_C_ERC, args,
			commerceAccountModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CommerceAccountModelImpl commerceAccountModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					commerceAccountModelImpl.getCompanyId(),
					commerceAccountModelImpl.getName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_N, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_N, args);
		}

		if ((commerceAccountModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_N.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					commerceAccountModelImpl.getOriginalCompanyId(),
					commerceAccountModelImpl.getOriginalName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_N, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_N, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					commerceAccountModelImpl.getCompanyId(),
					commerceAccountModelImpl.getExternalReferenceCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_ERC, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_ERC, args);
		}

		if ((commerceAccountModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_ERC.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					commerceAccountModelImpl.getOriginalCompanyId(),
					commerceAccountModelImpl.getOriginalExternalReferenceCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_ERC, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_ERC, args);
		}
	}

	/**
	 * Creates a new commerce account with the primary key. Does not add the commerce account to the database.
	 *
	 * @param commerceAccountId the primary key for the new commerce account
	 * @return the new commerce account
	 */
	@Override
	public CommerceAccount create(long commerceAccountId) {
		CommerceAccount commerceAccount = new CommerceAccountImpl();

		commerceAccount.setNew(true);
		commerceAccount.setPrimaryKey(commerceAccountId);

		commerceAccount.setCompanyId(companyProvider.getCompanyId());

		return commerceAccount;
	}

	/**
	 * Removes the commerce account with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceAccountId the primary key of the commerce account
	 * @return the commerce account that was removed
	 * @throws NoSuchAccountException if a commerce account with the primary key could not be found
	 */
	@Override
	public CommerceAccount remove(long commerceAccountId)
		throws NoSuchAccountException {
		return remove((Serializable)commerceAccountId);
	}

	/**
	 * Removes the commerce account with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce account
	 * @return the commerce account that was removed
	 * @throws NoSuchAccountException if a commerce account with the primary key could not be found
	 */
	@Override
	public CommerceAccount remove(Serializable primaryKey)
		throws NoSuchAccountException {
		Session session = null;

		try {
			session = openSession();

			CommerceAccount commerceAccount = (CommerceAccount)session.get(CommerceAccountImpl.class,
					primaryKey);

			if (commerceAccount == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commerceAccount);
		}
		catch (NoSuchAccountException nsee) {
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
	protected CommerceAccount removeImpl(CommerceAccount commerceAccount) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceAccount)) {
				commerceAccount = (CommerceAccount)session.get(CommerceAccountImpl.class,
						commerceAccount.getPrimaryKeyObj());
			}

			if (commerceAccount != null) {
				session.delete(commerceAccount);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commerceAccount != null) {
			clearCache(commerceAccount);
		}

		return commerceAccount;
	}

	@Override
	public CommerceAccount updateImpl(CommerceAccount commerceAccount) {
		boolean isNew = commerceAccount.isNew();

		if (!(commerceAccount instanceof CommerceAccountModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceAccount.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(commerceAccount);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceAccount proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceAccount implementation " +
				commerceAccount.getClass());
		}

		CommerceAccountModelImpl commerceAccountModelImpl = (CommerceAccountModelImpl)commerceAccount;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceAccount.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceAccount.setCreateDate(now);
			}
			else {
				commerceAccount.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!commerceAccountModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceAccount.setModifiedDate(now);
			}
			else {
				commerceAccount.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commerceAccount.isNew()) {
				session.save(commerceAccount);

				commerceAccount.setNew(false);
			}
			else {
				commerceAccount = (CommerceAccount)session.merge(commerceAccount);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CommerceAccountModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(CommerceAccountModelImpl.ENTITY_CACHE_ENABLED,
			CommerceAccountImpl.class, commerceAccount.getPrimaryKey(),
			commerceAccount, false);

		clearUniqueFindersCache(commerceAccountModelImpl, false);
		cacheUniqueFindersCache(commerceAccountModelImpl);

		commerceAccount.resetOriginalValues();

		return commerceAccount;
	}

	/**
	 * Returns the commerce account with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce account
	 * @return the commerce account
	 * @throws NoSuchAccountException if a commerce account with the primary key could not be found
	 */
	@Override
	public CommerceAccount findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAccountException {
		CommerceAccount commerceAccount = fetchByPrimaryKey(primaryKey);

		if (commerceAccount == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commerceAccount;
	}

	/**
	 * Returns the commerce account with the primary key or throws a {@link NoSuchAccountException} if it could not be found.
	 *
	 * @param commerceAccountId the primary key of the commerce account
	 * @return the commerce account
	 * @throws NoSuchAccountException if a commerce account with the primary key could not be found
	 */
	@Override
	public CommerceAccount findByPrimaryKey(long commerceAccountId)
		throws NoSuchAccountException {
		return findByPrimaryKey((Serializable)commerceAccountId);
	}

	/**
	 * Returns the commerce account with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce account
	 * @return the commerce account, or <code>null</code> if a commerce account with the primary key could not be found
	 */
	@Override
	public CommerceAccount fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CommerceAccountModelImpl.ENTITY_CACHE_ENABLED,
				CommerceAccountImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommerceAccount commerceAccount = (CommerceAccount)serializable;

		if (commerceAccount == null) {
			Session session = null;

			try {
				session = openSession();

				commerceAccount = (CommerceAccount)session.get(CommerceAccountImpl.class,
						primaryKey);

				if (commerceAccount != null) {
					cacheResult(commerceAccount);
				}
				else {
					entityCache.putResult(CommerceAccountModelImpl.ENTITY_CACHE_ENABLED,
						CommerceAccountImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommerceAccountModelImpl.ENTITY_CACHE_ENABLED,
					CommerceAccountImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commerceAccount;
	}

	/**
	 * Returns the commerce account with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceAccountId the primary key of the commerce account
	 * @return the commerce account, or <code>null</code> if a commerce account with the primary key could not be found
	 */
	@Override
	public CommerceAccount fetchByPrimaryKey(long commerceAccountId) {
		return fetchByPrimaryKey((Serializable)commerceAccountId);
	}

	@Override
	public Map<Serializable, CommerceAccount> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommerceAccount> map = new HashMap<Serializable, CommerceAccount>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommerceAccount commerceAccount = fetchByPrimaryKey(primaryKey);

			if (commerceAccount != null) {
				map.put(primaryKey, commerceAccount);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CommerceAccountModelImpl.ENTITY_CACHE_ENABLED,
					CommerceAccountImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommerceAccount)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMMERCEACCOUNT_WHERE_PKS_IN);

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

			for (CommerceAccount commerceAccount : (List<CommerceAccount>)q.list()) {
				map.put(commerceAccount.getPrimaryKeyObj(), commerceAccount);

				cacheResult(commerceAccount);

				uncachedPrimaryKeys.remove(commerceAccount.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CommerceAccountModelImpl.ENTITY_CACHE_ENABLED,
					CommerceAccountImpl.class, primaryKey, nullModel);
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
	 * Returns all the commerce accounts.
	 *
	 * @return the commerce accounts
	 */
	@Override
	public List<CommerceAccount> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce accounts
	 * @param end the upper bound of the range of commerce accounts (not inclusive)
	 * @return the range of commerce accounts
	 */
	@Override
	public List<CommerceAccount> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce accounts
	 * @param end the upper bound of the range of commerce accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce accounts
	 */
	@Override
	public List<CommerceAccount> findAll(int start, int end,
		OrderByComparator<CommerceAccount> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommerceAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce accounts
	 * @param end the upper bound of the range of commerce accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of commerce accounts
	 */
	@Override
	public List<CommerceAccount> findAll(int start, int end,
		OrderByComparator<CommerceAccount> orderByComparator,
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

		List<CommerceAccount> list = null;

		if (retrieveFromCache) {
			list = (List<CommerceAccount>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMERCEACCOUNT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEACCOUNT;

				if (pagination) {
					sql = sql.concat(CommerceAccountModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommerceAccount>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommerceAccount>)QueryUtil.list(q,
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
	 * Removes all the commerce accounts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceAccount commerceAccount : findAll()) {
			remove(commerceAccount);
		}
	}

	/**
	 * Returns the number of commerce accounts.
	 *
	 * @return the number of commerce accounts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMERCEACCOUNT);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CommerceAccountModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce account persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CommerceAccountImpl.class.getName());
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
	private static final String _SQL_SELECT_COMMERCEACCOUNT = "SELECT commerceAccount FROM CommerceAccount commerceAccount";
	private static final String _SQL_SELECT_COMMERCEACCOUNT_WHERE_PKS_IN = "SELECT commerceAccount FROM CommerceAccount commerceAccount WHERE commerceAccountId IN (";
	private static final String _SQL_SELECT_COMMERCEACCOUNT_WHERE = "SELECT commerceAccount FROM CommerceAccount commerceAccount WHERE ";
	private static final String _SQL_COUNT_COMMERCEACCOUNT = "SELECT COUNT(commerceAccount) FROM CommerceAccount commerceAccount";
	private static final String _SQL_COUNT_COMMERCEACCOUNT_WHERE = "SELECT COUNT(commerceAccount) FROM CommerceAccount commerceAccount WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceAccount.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommerceAccount exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CommerceAccount exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CommerceAccountPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"active"
			});
}