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

package com.liferay.commerce.product.service.persistence.impl;

import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.impl.CPInstanceImpl;
import com.liferay.commerce.product.service.persistence.CPInstanceFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CPInstanceFinderImpl
	extends CPInstanceFinderBaseImpl implements CPInstanceFinder {

	public static final String FIND_BY_EXPIRATION_DATE =
		CPInstanceFinder.class.getName() + ".findByExpirationDate";

	public static final String FIND_BY_C_NOT_CST_NOT_ST =
		CPInstanceFinder.class.getName() + ".findByC_NotCST_NotST";

	public static final String FIND_BY_C_NOT_CST_ST =
		CPInstanceFinder.class.getName() + ".findByC_NotCST_ST";

	@Override
	public int countByC_NotCST_NotST(
		long cpDefinitionId, int cpDefinitionStatus, int cpInstanceStatus) {

		List<CPInstance> cpInstances = doFindByCPDefinitionIdAndStatuses(
			FIND_BY_C_NOT_CST_NOT_ST, cpDefinitionId, cpDefinitionStatus,
			cpInstanceStatus, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		return cpInstances.size();
	}

	@Override
	public int countByC_NotCST_ST(
		long cpDefinitionId, int cpDefinitionStatus, int cpInstanceStatus) {

		List<CPInstance> cpInstances = doFindByCPDefinitionIdAndStatuses(
			FIND_BY_C_NOT_CST_ST, cpDefinitionId, cpDefinitionStatus,
			cpInstanceStatus, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		return cpInstances.size();
	}

	@Override
	public List<CPInstance> findByExpirationDate(
		Date expirationDate, QueryDefinition<CPInstance> queryDefinition) {

		return doFindByExpirationDate(expirationDate, queryDefinition);
	}

	@Override
	public List<CPInstance> findByC_NotCST_NotST(
		long cpDefinitionId, int cpDefinitionStatus, int cpInstanceStatus,
		int start, int end, OrderByComparator<CPInstance> orderByComparator) {

		return doFindByCPDefinitionIdAndStatuses(
			FIND_BY_C_NOT_CST_NOT_ST, cpDefinitionId, cpDefinitionStatus,
			cpInstanceStatus, start, end, orderByComparator);
	}

	@Override
	public List<CPInstance> findByC_NotCST_ST(
		long cpDefinitionId, int cpDefinitionStatus, int cpInstanceStatus,
		int start, int end, OrderByComparator<CPInstance> orderByComparator) {

		return doFindByCPDefinitionIdAndStatuses(
			FIND_BY_C_NOT_CST_ST, cpDefinitionId, cpDefinitionStatus,
			cpInstanceStatus, start, end, orderByComparator);
	}

	protected List<CPInstance> doFindByCPDefinitionIdAndStatuses(
		String id, long cpDefinitionId, int cpDefinitionStatus,
		int cpInstanceStatus, int start, int end,
		OrderByComparator<CPInstance> orderByComparator) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), id);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity(CPInstanceImpl.TABLE_NAME, CPInstanceImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(cpDefinitionId);
			qPos.add(cpDefinitionStatus);
			qPos.add(cpInstanceStatus);

			List<CPInstance> list = (List<CPInstance>)QueryUtil.list(
				q, getDialect(), start, end);

			if (orderByComparator != null) {
				Collections.sort(list, orderByComparator);
			}

			return list;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected List<CPInstance> doFindByExpirationDate(
		Date expirationDate, QueryDefinition<CPInstance> queryDefinition) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(
				getClass(), FIND_BY_EXPIRATION_DATE, queryDefinition,
				CPInstanceImpl.TABLE_NAME);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity(CPInstanceImpl.TABLE_NAME, CPInstanceImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			if (expirationDate != null) {
				qPos.add(expirationDate);
			}

			qPos.add(queryDefinition.getStatus());

			return (List<CPInstance>)QueryUtil.list(
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

	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;

}