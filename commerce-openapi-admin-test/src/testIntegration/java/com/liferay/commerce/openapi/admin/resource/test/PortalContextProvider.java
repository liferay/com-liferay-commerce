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

package com.liferay.commerce.openapi.admin.resource.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.external.reference.service.EROrganizationLocalService;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Zoltán Takács
 */
@RunWith(Arquillian.class)
public abstract class PortalContextProvider {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	public Company getCompany() {
		try {
			return companyLocalService.getCompanyByWebId("liferay.com");
		}
		catch (Exception e) {
			throw new RuntimeException(
				"Unexpected error: " + e.getMessage(), e);
		}
	}

	public String getPortalURL() {
		try {
			Company company = getCompany();

			Group companyGroup = groupLocalService.getCompanyGroup(
				company.getCompanyId());

			return company.getPortalURL(companyGroup.getGroupId());
		}
		catch (Exception e) {
			throw new RuntimeException(
				"Unexpected error: " + e.getMessage(), e);
		}
	}

	public String getRootEndpointURL() {
		return getPortalURL().concat(_ROOT_END_POINT_SUFFIX);
	}

	@Inject
	protected static CompanyLocalService companyLocalService;

	@Inject
	protected static EROrganizationLocalService erOrganizationLocalService;

	@Inject
	protected static GroupLocalService groupLocalService;

	@Inject
	protected static OrganizationLocalService organizationLocalService;

	@Inject
	protected static RoleLocalService roleLocalService;

	@Inject
	protected static UserLocalService userLocalService;

	private static final String _ROOT_END_POINT_SUFFIX = "/o/commerce-admin/";

}