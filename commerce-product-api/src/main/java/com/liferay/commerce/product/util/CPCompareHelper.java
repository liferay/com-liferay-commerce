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

package com.liferay.commerce.product.util;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @author Alec Sloan
 */
@ProviderType
public interface CPCompareHelper {

	public void addCompareProduct(
			long groupId, long commerceAccountId, long cpDefinitionId,
			HttpSession httpSession)
		throws PortalException;

	public List<Long> getCPDefinitionIds(
			long groupId, long commerceAccountId, HttpSession httpSession)
		throws PortalException;

	public void removeCompareProduct(
			long groupId, long commerceAccountId, long cpDefinitionId,
			HttpSession httpSession)
		throws PortalException;

	public void setCPDefinitionIds(
		long groupId, List<Long> cpDefinitionIds, HttpSession httpSession);

}