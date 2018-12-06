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

package com.liferay.commerce.organization.rest.internal.provider;

import com.liferay.commerce.organization.constants.CommerceOrganizationConstants;
import com.liferay.commerce.organization.rest.internal.model.Account;
import com.liferay.commerce.organization.rest.internal.model.AccountList;
import com.liferay.commerce.organization.rest.internal.model.Member;
import com.liferay.commerce.organization.service.CommerceOrganizationService;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.webserver.WebServerServletTokenUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CommerceOrganizationDataProvider.class)
public class CommerceOrganizationDataProvider {

	public AccountList getAccountList(
			long groupId, long userId, String keywords, int page, int pageSize,
			String imagePath)
		throws PortalException {

		List<Account> accounts = getAccounts(
			groupId, userId,
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
			CommerceOrganizationConstants.TYPE_ACCOUNT, keywords, page,
			pageSize, imagePath);

		return new AccountList(accounts, accounts.size());
	}

	protected List<Account> getAccounts(
			long groupId, long userId, long parentOrganizationId, String type,
			String keywords, int page, int pageSize, String imagePath)
		throws PortalException {

		List<Account> accounts = new ArrayList<>();

		int start = (page - 1) * pageSize;
		int end = page * pageSize;

		BaseModelSearchResult<Organization> baseModelSearchResult;

		if (parentOrganizationId > 0) {
			baseModelSearchResult =
				_commerceOrganizationService.searchOrganizations(
					userId, parentOrganizationId, type, keywords, start, end,
					null);
		}
		else {
			baseModelSearchResult =
				_commerceOrganizationService.searchOrganizationsByGroup(
					groupId, userId, type, keywords, start, end, null);
		}

		for (Organization organization :
				baseModelSearchResult.getBaseModels()) {

			List<Account> subaccounts = getAccounts(
				groupId, userId, organization.getOrganizationId(),
				CommerceOrganizationConstants.TYPE_BRANCH, keywords, page,
				pageSize, imagePath);

			StringBundler sb = new StringBundler(5);

			sb.append(imagePath);
			sb.append("/organization_logo?img_id=");
			sb.append(organization.getLogoId());
			sb.append("&t=");
			sb.append(
				WebServerServletTokenUtil.getToken(organization.getLogoId()));

			accounts.add(
				new Account(
					organization.getOrganizationId(), organization.getName(),
					getMembers(
						organization.getCompanyId(),
						organization.getOrganizationId(), keywords),
					subaccounts, sb.toString()));
		}

		return accounts;
	}

	protected List<Member> getMembers(
			long companyId, long organizationId, String keywords)
		throws PortalException {

		List<Member> members = new ArrayList<>();

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("usersOrgs", organizationId);

		BaseModelSearchResult<User> baseModelSearchResult =
			_userLocalService.searchUsers(
				companyId, keywords, WorkflowConstants.STATUS_ANY, params,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, new Sort[0]);

		for (User user : baseModelSearchResult.getBaseModels()) {
			members.add(
				new Member(
					user.getUserId(), user.getFirstName(), user.getLastName(),
					user.getEmailAddress()));
		}

		return members;
	}

	@Reference
	private CommerceOrganizationService _commerceOrganizationService;

	@Reference
	private UserLocalService _userLocalService;

}