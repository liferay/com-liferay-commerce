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

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.account.service.CommerceAccountOrganizationRelLocalService;
import com.liferay.commerce.exception.NoSuchCountryException;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.service.CommerceAddressLocalService;
import com.liferay.commerce.service.CommerceCountryLocalService;
import com.liferay.commerce.service.CommerceRegionLocalService;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(service = CommerceAccountsImporter.class)
public class CommerceAccountsImporter {

	public List<CommerceAccount> importCommerceAccounts(
			JSONArray jsonArray, ClassLoader classLoader,
			Map<String, Organization> organizations, String dependenciesPath,
			ServiceContext serviceContext)
		throws Exception {

		List<CommerceAccount> commerceAccounts = new ArrayList<>(
			jsonArray.length());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			CommerceAccount commerceAccount = _importCommerceAccount(
				jsonObject, classLoader, organizations, dependenciesPath,
				serviceContext);

			commerceAccounts.add(commerceAccount);
		}

		return commerceAccounts;
	}

	protected CommerceCountry getCommerceCountry(String twoLetterISOCode)
		throws PortalException {

		DynamicQuery dynamicQuery = _commerceCountryLocalService.dynamicQuery();

		Property nameProperty = PropertyFactoryUtil.forName(
			"twoLettersISOCode");

		dynamicQuery.add(nameProperty.eq(twoLetterISOCode));

		List<CommerceCountry> commerceCountries =
			_commerceCountryLocalService.dynamicQuery(dynamicQuery, 0, 1);

		if (commerceCountries.isEmpty()) {
			throw new NoSuchCountryException(
				"No country exists with two-letter ISO " + twoLetterISOCode);
		}

		return commerceCountries.get(0);
	}

	private CommerceAccount _importCommerceAccount(
			JSONObject jsonObject, ClassLoader classLoader,
			Map<String, Organization> organizations, String dependenciesPath,
			ServiceContext serviceContext)
		throws IOException, PortalException {

		String name = jsonObject.getString("Name");
		String taxId = jsonObject.getString("TaxId");

		// Add Commerce Account

		CommerceAccount commerceAccount =
			_commerceAccountLocalService.addCommerceAccount(
				name, CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID, null,
				taxId, CommerceAccountConstants.ACCOUNT_TYPE_BUSINESS, true,
				null, serviceContext);

		String twoLetterISOCode = jsonObject.getString("Country");

		CommerceCountry commerceCountry = getCommerceCountry(twoLetterISOCode);

		long commerceRegionId = 0;

		String regionCode = jsonObject.getString("Region");

		try {
			CommerceRegion commerceRegion =
				_commerceRegionLocalService.getCommerceRegion(
					commerceCountry.getCommerceCountryId(), regionCode);

			commerceRegionId = commerceRegion.getCommerceRegionId();
		}
		catch (PortalException pe) {
			_log.error(pe, pe);
		}

		String street1 = jsonObject.getString("Street1");
		String city = jsonObject.getString("City");
		String zip = jsonObject.getString("Zip");

		// Add Commerce Address

		_commerceAddressLocalService.addCommerceAddress(
			commerceAccount.getModelClassName(),
			commerceAccount.getCommerceAccountId(), commerceAccount.getName(),
			StringPool.BLANK, street1, StringPool.BLANK, StringPool.BLANK, city,
			zip, commerceRegionId, commerceCountry.getCommerceCountryId(),
			StringPool.BLANK, true, false, serviceContext);

		// Add Company Logo

		String companyLogo = jsonObject.getString("CompanyLogo");

		if (!Validator.isBlank(companyLogo)) {
			String filePath = dependenciesPath + "images/" + companyLogo;

			try (InputStream inputStream = classLoader.getResourceAsStream(
					filePath)) {

				if (inputStream == null) {
					throw new FileNotFoundException(
						"No file found at " + filePath);
				}

				_commerceAccountLocalService.updateCommerceAccount(
					commerceAccount.getCommerceAccountId(),
					commerceAccount.getName(), true,
					FileUtil.getBytes(inputStream), commerceAccount.getEmail(),
					commerceAccount.getTaxId(), true, serviceContext);
			}
		}

		// Add Related Organization

		String relatedOrganization = jsonObject.getString(
			"RelatedOrganization");

		if (!Validator.isBlank(relatedOrganization)) {
			Organization organization = organizations.get(relatedOrganization);

			_commerceAccountOrganizationRelLocalService.
				addCommerceAccountOrganizationRel(
					commerceAccount.getCommerceAccountId(),
					organization.getOrganizationId(), serviceContext);
		}

		// Add Account Administrator

		String administrator = jsonObject.getString("Administrator");

		if (!Validator.isBlank(administrator)) {
			User user = _userLocalService.getUserByScreenName(
				serviceContext.getCompanyId(), administrator);

			Role role = _roleLocalService.getRole(
				serviceContext.getCompanyId(), RoleConstants.ADMINISTRATOR);

			_userGroupRoleLocalService.addUserGroupRoles(
				user.getUserId(), commerceAccount.getCommerceAccountGroupId(),
				new long[] {role.getRoleId()});
		}

		return commerceAccount;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAccountsImporter.class);

	@Reference
	private CommerceAccountLocalService _commerceAccountLocalService;

	@Reference
	private CommerceAccountOrganizationRelLocalService
		_commerceAccountOrganizationRelLocalService;

	@Reference
	private CommerceAddressLocalService _commerceAddressLocalService;

	@Reference
	private CommerceCountryLocalService _commerceCountryLocalService;

	@Reference
	private CommerceRegionLocalService _commerceRegionLocalService;

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private DLFileEntryLocalService _dlFileEntryLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserGroupRoleLocalService _userGroupRoleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}