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

package com.liferay.commerce.initializer.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.CountryService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.users.admin.kernel.organization.types.OrganizationTypesSettings;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(service = OrganizationImporter.class)
public class OrganizationImporter {

	public List<Organization> importOrganizations(
			JSONArray jsonArray, ServiceContext serviceContext)
		throws PortalException {

		List<Organization> organizations = new ArrayList<>(jsonArray.length());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			Organization organization = _importOrganization(
				jsonObject, serviceContext);

			organizations.add(organization);
		}

		return organizations;
	}

	private Organization _importOrganization(
			JSONObject jsonObject, ServiceContext serviceContext)
		throws PortalException {

		String name = jsonObject.getString("Name");

		String parentOrganizationName = jsonObject.getString(
			"ParentOrganization", null);

		long parentOrganizationId = 0;

		if (parentOrganizationName != null) {
			Organization parentOrganization =
				_organizationLocalService.fetchOrganization(
					serviceContext.getCompanyId(), parentOrganizationName);

			parentOrganizationId = parentOrganization.getOrganizationId();
		}

		String[] types = _organizationTypesSettings.getTypes();

		String twoLetterISOCode = jsonObject.getString("TwoLetterISOCode");

		Country country = _countryService.getCountryByA2(twoLetterISOCode);

		Organization organization = _organizationLocalService.fetchOrganization(
			serviceContext.getCompanyId(), name);

		if (organization != null) {
			return organization;
		}

		organization = _organizationLocalService.addOrganization(
			serviceContext.getUserId(), parentOrganizationId, name, types[0], 0,
			country.getCountryId(),
			ListTypeConstants.ORGANIZATION_STATUS_DEFAULT, StringPool.BLANK,
			false, serviceContext);

		return organization;
	}

	@Reference
	private CountryService _countryService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private OrganizationTypesSettings _organizationTypesSettings;

}