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

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.model.impl.CommerceAccountImpl;
import com.liferay.commerce.account.service.persistence.CommerceAccountFinder;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Iterator;
import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountFinderImpl
	extends CommerceAccountFinderBaseImpl implements CommerceAccountFinder {

	public static final String COUNT_BY_U_P =
		CommerceAccountFinder.class.getName() + ".countByU_P";

	public static final String FIND_BY_U_P =
		CommerceAccountFinder.class.getName() + ".findByU_P";

	public static final String FIND_BY_U_C =
		CommerceAccountFinder.class.getName() + ".findByU_C";

	@Override
	public int countByU_P(
		long userId, QueryDefinition<CommerceAccount> queryDefinition) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), COUNT_BY_U_P);

			sql = StringUtil.replace(
				sql, "[$USER_ID$]", String.valueOf(userId));

			Long parentCommerceAccountId = (Long)queryDefinition.getAttribute(
				"parentCommerceAccountId");

			if (parentCommerceAccountId != null) {
				sql = StringUtil.replace(
					sql, "[$PARENT_ACCOUNT_ID$]",
					_getParentCommerceAccountClause(parentCommerceAccountId));
			}
			else {
				sql = StringUtil.replace(
					sql, "[$PARENT_ACCOUNT_ID$]", StringPool.BLANK);
			}

			String keywords = (String)queryDefinition.getAttribute("keywords");

			String[] names = _customSQL.keywords(keywords, true);

			sql = _customSQL.replaceKeywords(
				sql, "LOWER(CommerceAccount.name)", StringPool.LIKE, false,
				names);

			boolean b2b = (Boolean)queryDefinition.getAttribute("B2B");
			boolean b2c = (Boolean)queryDefinition.getAttribute("B2C");

			if (b2b && !b2c) {
				sql = StringUtil.add(
					sql,
					" AND (CommerceAccount.type_ = " +
						CommerceAccountConstants.ACCOUNT_TYPE_BUSINESS + ")",
					StringPool.BLANK);
			}
			else if (!b2b && b2c) {
				sql = StringUtil.add(
					sql,
					" AND (CommerceAccount.type_ = " +
						CommerceAccountConstants.ACCOUNT_TYPE_PERSONAL + ")",
					StringPool.BLANK);
			}

			sql = _customSQL.replaceAndOperator(sql, true);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(names, 2);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			int count = 0;

			Iterator<Long> itr = q.iterate();

			while (itr.hasNext()) {
				Long l = itr.next();

				if (l != null) {
					count += l.intValue();
				}
			}

			return count;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<CommerceAccount> findByU_P(
		long userId, QueryDefinition<CommerceAccount> queryDefinition) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_U_P);

			sql = StringUtil.replace(
				sql, "[$USER_ID$]", String.valueOf(userId));

			Long parentCommerceAccountId = (Long)queryDefinition.getAttribute(
				"parentCommerceAccountId");

			if (parentCommerceAccountId != null) {
				sql = StringUtil.replace(
					sql, "[$PARENT_ACCOUNT_ID$]",
					_getParentCommerceAccountClause(parentCommerceAccountId));
			}
			else {
				sql = StringUtil.replace(
					sql, "[$PARENT_ACCOUNT_ID$]", StringPool.BLANK);
			}

			String keywords = (String)queryDefinition.getAttribute("keywords");

			if (Validator.isBlank(keywords)) {
				keywords = null;
			}

			String[] names = _customSQL.keywords(keywords, true);

			sql = _customSQL.replaceKeywords(
				sql, "LOWER(CommerceAccount.name)", StringPool.LIKE, false,
				names);

			boolean b2b = (Boolean)queryDefinition.getAttribute("B2B");
			boolean b2c = (Boolean)queryDefinition.getAttribute("B2C");

			if (b2b && !b2c) {
				sql = StringUtil.add(
					sql,
					" AND (CommerceAccount.type_ = " +
						CommerceAccountConstants.ACCOUNT_TYPE_BUSINESS + ")",
					StringPool.BLANK);
			}
			else if (!b2b && b2c) {
				sql = StringUtil.add(
					sql,
					" AND (CommerceAccount.type_ = " +
						CommerceAccountConstants.ACCOUNT_TYPE_PERSONAL + ")",
					StringPool.BLANK);
			}

			sql = _customSQL.replaceAndOperator(sql, true);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity(
				CommerceAccountImpl.TABLE_NAME, CommerceAccountImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(names, 2);

			return (List<CommerceAccount>)QueryUtil.list(
				q, getDialect(), queryDefinition.getStart(),
				queryDefinition.getEnd());
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public CommerceAccount findByU_C(long userId, long commerceAccountId) {
		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_U_C);

			sql = StringUtil.replace(
				sql, "[$USER_ID$]", String.valueOf(userId));

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity(
				CommerceAccountImpl.TABLE_NAME, CommerceAccountImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(commerceAccountId);

			List<CommerceAccount> list = (List<CommerceAccount>)QueryUtil.list(
				q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			if (list.isEmpty()) {
				return list.get(0);
			}

			return null;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private String _getParentCommerceAccountClause(
		long parentCommerceAccountId) {

		return "(CommerceAccount.parentCommerceAccountId = " +
			parentCommerceAccountId + ") AND";
	}

	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;

}