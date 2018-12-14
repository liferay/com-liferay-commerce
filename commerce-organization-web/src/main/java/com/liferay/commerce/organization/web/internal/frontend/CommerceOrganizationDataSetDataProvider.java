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

package com.liferay.commerce.organization.web.internal.frontend;

import com.liferay.commerce.frontend.CommerceDataSetDataProvider;
import com.liferay.commerce.frontend.Pagination;
import com.liferay.commerce.organization.constants.CommerceOrganizationConstants;
import com.liferay.commerce.organization.service.CommerceOrganizationService;
import com.liferay.commerce.organization.web.internal.model.Account;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "commerce.data.provider.key=" + CommerceOrganizationDataSetDataProvider.KEY,
	service = CommerceDataSetDataProvider.class
)
public class CommerceOrganizationDataSetDataProvider
	implements CommerceDataSetDataProvider<Account> {

	public static final String KEY = "organizations";

	@Override
	public int countItems(long groupId) throws PortalException {
		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		BaseModelSearchResult<Organization> baseModelSearchResult =
			_commerceOrganizationService.searchOrganizationsByGroup(
				groupId, permissionChecker.getUserId(),
				CommerceOrganizationConstants.TYPE_ACCOUNT, StringPool.BLANK,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		return baseModelSearchResult.getLength();
	}

	@Override
	public List<Account> getItems(
			long groupId, Pagination pagination, Sort sort)
		throws PortalException {

		List<Account> accounts = new ArrayList<>();

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		BaseModelSearchResult<Organization> baseModelSearchResult =
			_commerceOrganizationService.searchOrganizationsByGroup(
				groupId, permissionChecker.getUserId(),
				CommerceOrganizationConstants.TYPE_ACCOUNT, StringPool.BLANK,
				pagination.getStartPosition(), pagination.getEndPosition(),
				new Sort[] {sort});

		for (Organization organization :
				baseModelSearchResult.getBaseModels()) {

			accounts.add(
				new Account(
					organization.getOrganizationId(), organization.getName(),
					StringPool.BLANK, StringPool.BLANK));
		}

		return accounts;
	}

	@Reference
	private CommerceOrganizationService _commerceOrganizationService;

}