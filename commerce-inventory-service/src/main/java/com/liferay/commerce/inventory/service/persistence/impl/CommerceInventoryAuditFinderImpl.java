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

package com.liferay.commerce.inventory.service.persistence.impl;

import com.liferay.commerce.inventory.model.CommerceInventoryAudit;
import com.liferay.commerce.inventory.model.impl.CommerceInventoryAuditImpl;
import com.liferay.commerce.inventory.service.persistence.CommerceInventoryAuditFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Date;
import java.util.List;

/**
 * @author Luca Pellizzon
 */
public class CommerceInventoryAuditFinderImpl
	extends CommerceInventoryAuditFinderBaseImpl
	implements CommerceInventoryAuditFinder {

	public static final String FIND_OLD_INVENTORY_AUDIT =
		"findOldInventoryAudit";

	@Override
	public List<CommerceInventoryAudit> findOldInventoryAudit(Date olderThan) {
		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_OLD_INVENTORY_AUDIT);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("CIAudit", CommerceInventoryAuditImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(olderThan);

			return (List<CommerceInventoryAudit>)QueryUtil.list(
				q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
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