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

import com.liferay.commerce.product.service.persistence.CommerceChannelFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

/**
 * @author Alec Sloan
 */
public class CommerceChannelFinderImpl
	extends CommerceChannelFinderBaseImpl implements CommerceChannelFinder {

	public static final String GET_USED_SITE_GROUP_IDS =
		CommerceChannelFinder.class.getName() + ".getUsedSiteGroupIds";

	@Override
	public List<Long> getUsedSiteGroupIds() {
		return doGetUsedSiteGroupIds();
	}

	protected List<Long> doGetUsedSiteGroupIds() {
		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), GET_USED_SITE_GROUP_IDS);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			return (List<Long>)QueryUtil.list(
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