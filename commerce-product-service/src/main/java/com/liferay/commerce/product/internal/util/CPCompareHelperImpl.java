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

package com.liferay.commerce.product.internal.util;

import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.util.CPCompareHelper;
import com.liferay.commerce.product.util.CPDefinitionHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(immediate = true, service = CPCompareHelper.class)
public class CPCompareHelperImpl implements CPCompareHelper {

	public void addCompareProduct(
			long groupId, long commerceAccountId, long cpDefinitionId,
			HttpSession httpSession)
		throws PortalException {

		List<Long> cpDefinitionIds = getCPDefinitionIds(
			groupId, commerceAccountId, httpSession);

		if (!cpDefinitionIds.contains(cpDefinitionId)) {
			cpDefinitionIds.add(cpDefinitionId);
		}

		setCPDefinitionIds(groupId, cpDefinitionIds, httpSession);
	}

	public List<Long> getCPDefinitionIds(
			long groupId, long commerceAccountId, HttpSession httpSession)
		throws PortalException {

		List<Long> cpDefinitionIds = (List<Long>)httpSession.getAttribute(
			_getSessionAttributeKey(groupId));

		if (cpDefinitionIds == null) {
			return new ArrayList<>();
		}

		List<Long> activeCPDefinitionIds = new ArrayList<>();

		for (long cpDefinitionId : cpDefinitionIds) {
			CPCatalogEntry cpCatalogEntry =
				_cpDefinitionHelper.getCPCatalogEntry(
					commerceAccountId, groupId, cpDefinitionId,
					LocaleUtil.getDefault());

			if (cpCatalogEntry != null) {
				activeCPDefinitionIds.add(cpDefinitionId);
			}
		}

		setCPDefinitionIds(groupId, activeCPDefinitionIds, httpSession);

		return activeCPDefinitionIds;
	}

	public void removeCompareProduct(
			long groupId, long commerceAccountId, long cpDefinitionId,
			HttpSession httpSession)
		throws PortalException {

		List<Long> cpDefinitionIds = getCPDefinitionIds(
			groupId, commerceAccountId, httpSession);

		if (cpDefinitionIds.contains(cpDefinitionId)) {
			cpDefinitionIds.remove(cpDefinitionId);
		}

		setCPDefinitionIds(groupId, cpDefinitionIds, httpSession);
	}

	public void setCPDefinitionIds(
		long groupId, List<Long> cpDefinitionIds, HttpSession httpSession) {

		httpSession.setAttribute(
			_getSessionAttributeKey(groupId), cpDefinitionIds);
	}

	private String _getSessionAttributeKey(long groupId) {
		return "LIFERAY_SHARED_CP_DEFINITION_IDS_" + groupId;
	}

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private CPDefinitionHelper _cpDefinitionHelper;

}