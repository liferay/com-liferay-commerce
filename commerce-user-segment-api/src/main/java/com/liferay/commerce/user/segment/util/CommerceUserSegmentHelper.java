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

package com.liferay.commerce.user.segment.util;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.PortalException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marco Leo
 */
@ProviderType
public interface CommerceUserSegmentHelper {

	public long[] getCommerceUserSegmentIds(
			HttpServletRequest httpServletRequest)
		throws PortalException;

	public long[] getCommerceUserSegmentIds(
			long groupId, long commerceAccountId, long userId)
		throws PortalException;

	public long[] getUserIds(
			long groupId, long organizationId, long[] commerceAccountId,
			int start, int end)
		throws PortalException;

}