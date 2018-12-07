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

package com.liferay.commerce.data.provider;

import aQute.bnd.annotation.ConsumerType;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Sort;

import java.util.List;

/**
 * @author Marco Leo
 */
@ConsumerType
public interface CommerceDataProvider<T> {

	public int countItems(long groupId) throws PortalException;

	public List<T> getItems(long groupId, Pagination pagination, Sort sort)
		throws PortalException;

	public List<T> getItems(
			long groupId, int itemPerPage, int pageNumber, Sort sort)
		throws PortalException;

}