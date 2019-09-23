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
import com.liferay.commerce.account.exception.NoSuchAccountGroupException;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.model.CommerceAccountGroupCommerceAccountRel;
import com.liferay.commerce.account.model.CommerceAccountOrganizationRel;
import com.liferay.commerce.account.service.CommerceAccountGroupCommerceAccountRelLocalService;
import com.liferay.commerce.account.service.CommerceAccountGroupLocalService;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.account.service.CommerceAccountOrganizationRelLocalService;
import com.liferay.commerce.account.service.persistence.CommerceAccountOrganizationRelPK;
import com.liferay.commerce.exception.NoSuchCountryException;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.price.list.exception.NoSuchPriceListException;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListAccountRelLocalService;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.service.CommerceAddressLocalService;
import com.liferay.commerce.service.CommerceCountryLocalService;
import com.liferay.commerce.service.CommerceRegionLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(service = CommerceAccountsImporter.class)
public class CommerceAccountsImporter {

	public void importCommerceAccounts(
			JSONArray jsonArray, ClassLoader classLoader,
			String dependenciesPath, long scopeGroupId, long userId)
		throws Exception {

		User user = _userLocalService.getUser(userId);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(scopeGroupId);
		serviceContext.setUserId(userId);
		serviceContext.setCompanyId(user.getCompanyId());

		for (int i = 0; i < jsonArray.length(); i++) {
			_importCommerceAccount(
				jsonArray.getJSONObject(i), classLoader, dependenciesPath,
				serviceContext);
		}
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

	private void _importCommerceAccount(
			JSONObject jsonObject, ClassLoader classLoader,
			String dependenciesPath, ServiceContext serviceContext)
		throws IOException, PortalException {

		String name = jsonObject.getString("Name");

		CommerceAccount commerceAccount =
			_commerceAccountLocalService.fetchCommerceAccountByReferenceCode(
				serviceContext.getCompanyId(),
				FriendlyURLNormalizerUtil.normalize(name));

		if (commerceAccount != null) {
			return;
		}

		String accountType = jsonObject.getString("AccountType");
		String email = jsonObject.getString("Email");
		String taxId = jsonObject.getString("TaxId");

		// Add Commerce Account

		int commerceAccountType = 1;

		if (accountType.equals("Business")) {
			commerceAccountType =
				CommerceAccountConstants.ACCOUNT_TYPE_BUSINESS;
		}

		commerceAccount = _commerceAccountLocalService.addCommerceAccount(
			name, CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID, email,
			taxId, commerceAccountType, true,
			FriendlyURLNormalizerUtil.normalize(name), serviceContext);

		String twoLetterISOCode = jsonObject.getString("Country");

		CommerceCountry commerceCountry = getCommerceCountry(twoLetterISOCode);

		long commerceRegionId = 0;

		String regionCode = jsonObject.getString("Region");

		if (!Validator.isBlank(regionCode)) {
			try {
				CommerceRegion commerceRegion =
					_commerceRegionLocalService.getCommerceRegion(
						commerceCountry.getCommerceCountryId(), regionCode);

				commerceRegionId = commerceRegion.getCommerceRegionId();
			}
			catch (PortalException pe) {
				_log.error(pe, pe);
			}
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
			Organization organization =
				_organizationLocalService.fetchOrganization(
					serviceContext.getCompanyId(), relatedOrganization);

			if (organization == null) {
				organization = _organizationLocalService.addOrganization(
					serviceContext.getUserId(), 0, name,
					OrganizationConstants.TYPE_ORGANIZATION, 0, 0,
					ListTypeConstants.ORGANIZATION_STATUS_DEFAULT,
					StringPool.BLANK, false, serviceContext);
			}

			CommerceAccountOrganizationRelPK commerceAccountOrganizationRelPK =
				new CommerceAccountOrganizationRelPK(
					commerceAccount.getCommerceAccountId(),
					organization.getOrganizationId());

			CommerceAccountOrganizationRel commerceAccountOrganizationRel =
				_commerceAccountOrganizationRelLocalService.
					fetchCommerceAccountOrganizationRel(
						commerceAccountOrganizationRelPK);

			if (commerceAccountOrganizationRel == null) {
				_commerceAccountOrganizationRelLocalService.
					addCommerceAccountOrganizationRel(
						commerceAccount.getCommerceAccountId(),
						organization.getOrganizationId(), serviceContext);
			}
		}

		// Add Price List Account Rel

		JSONArray priceListsJSONArray = jsonObject.getJSONArray("PriceLists");

		if (priceListsJSONArray != null) {
			for (int i = 0; i < priceListsJSONArray.length(); i++) {
				try {
					String externalReferenceCode = StringBundler.concat(
						String.valueOf(serviceContext.getScopeGroupId()), "_",
						FriendlyURLNormalizerUtil.normalize(
							priceListsJSONArray.getString(i)));

					CommercePriceList commercePriceList =
						_commercePriceListLocalService.
							fetchByExternalReferenceCode(
								serviceContext.getCompanyId(),
								externalReferenceCode);

					if (commercePriceList != null) {
						_commercePriceListAccountRelLocalService.
							addCommercePriceListAccountRel(
								commercePriceList.getCommercePriceListId(),
								commerceAccount.getCommerceAccountId(), 0,
								serviceContext);
					}
				}
				catch (NoSuchPriceListException nsple) {
					_log.error(nsple, nsple);
				}
			}
		}

		// Add/Find Account Group and Add Rel

		JSONArray accountGroupsJSONArray = jsonObject.getJSONArray(
			"AccountGroups");

		if (accountGroupsJSONArray != null) {
			for (int i = 0; i < accountGroupsJSONArray.length(); i++) {
				try {
					String accountGroupName = accountGroupsJSONArray.getString(
						i);

					String externalReferenceCode = StringBundler.concat(
						String.valueOf(serviceContext.getCompanyId()), "_",
						FriendlyURLNormalizerUtil.normalize(accountGroupName));

					CommerceAccountGroup commerceAccountGroup =
						_commerceAccountGroupLocalService.
							fetchCommerceAccountGroupByReferenceCode(
								serviceContext.getCompanyId(),
								externalReferenceCode);

					if (commerceAccountGroup == null) {
						commerceAccountGroup =
							_commerceAccountGroupLocalService.
								addCommerceAccountGroup(
									serviceContext.getCompanyId(),
									accountGroupName,
									CommerceAccountConstants.
										ACCOUNT_GROUP_TYPE_GUEST,
									false, externalReferenceCode,
									serviceContext);
					}

					String relExternalReferenceCode = StringBundler.concat(
						String.valueOf(serviceContext.getCompanyId()), "_",
						FriendlyURLNormalizerUtil.normalize(accountGroupName),
						"_",
						FriendlyURLNormalizerUtil.normalize(
							commerceAccount.getName()));

					CommerceAccountGroupCommerceAccountRel
						commerceAccountGroupCommerceAccountRel =
							_commerceAccountGroupCommerceAccountRelLocalService.
								fetchCommerceAccountGroupCommerceAccountRelByReferenceCode(
									serviceContext.getCompanyId(),
									relExternalReferenceCode);

					if (commerceAccountGroupCommerceAccountRel == null) {
						_commerceAccountGroupCommerceAccountRelLocalService.
							addCommerceAccountGroupCommerceAccountRel(
								commerceAccountGroup.
									getCommerceAccountGroupId(),
								commerceAccount.getCommerceAccountId(),
								relExternalReferenceCode, serviceContext);
					}
				}
				catch (NoSuchAccountGroupException nsage) {
					_log.error(nsage, nsage);
				}
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAccountsImporter.class);

	@Reference
	private CommerceAccountGroupCommerceAccountRelLocalService
		_commerceAccountGroupCommerceAccountRelLocalService;

	@Reference
	private CommerceAccountGroupLocalService _commerceAccountGroupLocalService;

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
	private CommercePriceListAccountRelLocalService
		_commercePriceListAccountRelLocalService;

	@Reference
	private CommercePriceListLocalService _commercePriceListLocalService;

	@Reference
	private CommerceRegionLocalService _commerceRegionLocalService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private UserLocalService _userLocalService;

}