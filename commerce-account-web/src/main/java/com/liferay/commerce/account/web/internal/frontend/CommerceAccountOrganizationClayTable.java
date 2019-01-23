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

package com.liferay.commerce.account.web.internal.frontend;

import com.liferay.commerce.account.model.CommerceAccountOrganizationRel;
import com.liferay.commerce.account.service.CommerceAccountOrganizationRelService;
import com.liferay.commerce.account.web.internal.model.Organization;
import com.liferay.commerce.frontend.ClayTable;
import com.liferay.commerce.frontend.ClayTableSchema;
import com.liferay.commerce.frontend.ClayTableSchemaBuilder;
import com.liferay.commerce.frontend.ClayTableSchemaBuilderFactory;
import com.liferay.commerce.frontend.CommerceDataSetDataProvider;
import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.Pagination;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.data.provider.key=" + CommerceAccountOrganizationClayTable.NAME,
		"commerce.table.name=" + CommerceAccountOrganizationClayTable.NAME
	},
	service = {ClayTable.class, CommerceDataSetDataProvider.class}
)
public class CommerceAccountOrganizationClayTable
	implements CommerceDataSetDataProvider<Organization>, ClayTable {

	public static final String NAME = "commerceAccountOrganizations";

	@Override
	public int countItems(HttpServletRequest httpServletRequest, Filter filter)
		throws PortalException {

		AccountFilterImpl accountFilter = (AccountFilterImpl)filter;

		return _commerceAccountOrganizationRelService.
			getCommerceAccountOrganizationRelsCount(
				accountFilter.getAccountId());
	}

	@Override
	public ClayTableSchema getClayTableSchema() {
		ClayTableSchemaBuilder clayTableSchemaBuilder =
			_clayTableSchemaBuilderFactory.clayTableSchemaBuilder();

		clayTableSchemaBuilder.addField("name", "name");
		clayTableSchemaBuilder.addField("path", "path");

		return clayTableSchemaBuilder.build();
	}

	@Override
	public String getId() {
		return NAME;
	}

	@Override
	public List<Organization> getItems(
			HttpServletRequest httpServletRequest, Filter filter,
			Pagination pagination, Sort sort)
		throws PortalException {

		AccountFilterImpl accountFilter = (AccountFilterImpl)filter;

		List<Organization> organizations = new ArrayList<>();

		List<CommerceAccountOrganizationRel> commerceAccountOrganizationRels =
			_commerceAccountOrganizationRelService.
				getCommerceAccountOrganizationRels(
					accountFilter.getAccountId(), pagination.getStartPosition(),
					pagination.getEndPosition());

		for (CommerceAccountOrganizationRel commerceAccountOrganizationRel :
				commerceAccountOrganizationRels) {

			com.liferay.portal.kernel.model.Organization organization =
				commerceAccountOrganizationRel.getOrganization();

			organizations.add(
				new Organization(
					organization.getOrganizationId(), organization.getName(),
					getPath(organization.getTreePath())));
		}

		return organizations;
	}

	@Override
	public boolean isShowActionsMenu() {
		return true;
	}

	protected String getPath(String treePath) throws PortalException {
		String[] organizationIds = StringUtil.split(
			treePath, CharPool.FORWARD_SLASH);

		StringBundler sb = new StringBundler(organizationIds.length * 2);

		for (int i = 1; i < organizationIds.length; i++) {
			sb.append(CharPool.FORWARD_SLASH);

			com.liferay.portal.kernel.model.Organization organization =
				_organizationLocalService.getOrganization(
					GetterUtil.getLong(organizationIds[i]));

			sb.append(organization.getName());
		}

		return sb.toString();
	}

	@Reference
	private ClayTableSchemaBuilderFactory _clayTableSchemaBuilderFactory;

	@Reference
	private CommerceAccountOrganizationRelService
		_commerceAccountOrganizationRelService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

}