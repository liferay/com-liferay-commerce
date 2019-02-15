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

package com.liferay.commerce.openapi.admin.internal.util.v2_0;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.model.CommerceWarehouse;
import com.liferay.commerce.model.CommerceWarehouseItem;
import com.liferay.commerce.openapi.admin.model.v2_0.AccountDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.CountryDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.CurrencyDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.InventoryDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.PriceEntryDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.PriceListDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.ProductDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.ProductOptionDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.ProductOptionValueDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.RegionDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.SkuDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.UserDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.WebSiteDTO;
import com.liferay.commerce.openapi.core.util.LanguageUtils;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.model.CPOptionValue;
import com.liferay.commerce.service.CommerceRegionLocalServiceUtil;
import com.liferay.commerce.util.comparator.CommerceRegionPriorityComparator;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author Zoltán Takács
 * @author Alessio Antonio Rendina
 */
public class DTOUtils {

	public static RegionDTO[] modelsToRegionDTOArray(
		List<CommerceRegion> commerceRegions) {

		if (commerceRegions == null) {
			return null;
		}

		List<RegionDTO> regions = new ArrayList<>();

		for (CommerceRegion commerceRegion : commerceRegions) {
			regions.add(modelToDTO(commerceRegion));
		}

		Stream<RegionDTO> stream = regions.stream();

		return stream.toArray(RegionDTO[]::new);
	}

	public static AccountDTO modelToDTO(CommerceAccount commerceAccount) {
		AccountDTO accountDTO = new AccountDTO();

		try {
			accountDTO.setId(commerceAccount.getCommerceAccountId());
			accountDTO.setRoot(commerceAccount.isRoot());
			accountDTO.setPersonal(commerceAccount.isPersonalAccount());
			accountDTO.setBusiness(commerceAccount.isBusinessAccount());
			accountDTO.setOrganizationId(
				commerceAccount.getCommerceAccountGroupId());
			accountDTO.setExternalReferenceCode(
				commerceAccount.getExternalReferenceCode());
			accountDTO.setName(commerceAccount.getName());
			accountDTO.setLogoId(commerceAccount.getLogoId());
			accountDTO.setTaxId(commerceAccount.getTaxId());
			accountDTO.setType(_getAccountType(commerceAccount.getType()));
			accountDTO.setUserIds(new Long[] {commerceAccount.getUserId()});
			accountDTO.setEmailAddresses(
				new String[] {commerceAccount.getEmail()});
		}
		catch (Exception e) {
			_log.error("Cannot instantiate AccountDTO ", e);

			throw new RuntimeException(e);
		}

		return accountDTO;
	}

	public static CountryDTO modelToDTO(CommerceCountry commerceCountry) {
		CountryDTO countryDTO = new CountryDTO();

		countryDTO.setBillingAllowed(commerceCountry.isBillingAllowed());
		countryDTO.setId(commerceCountry.getCommerceCountryId());
		countryDTO.setName(
			LanguageUtils.getLanguageIdMap(commerceCountry.getNameMap()));
		countryDTO.setNumericISOCode(commerceCountry.getNumericISOCode());

		List<CommerceRegion> commerceRegions =
			CommerceRegionLocalServiceUtil.getCommerceRegions(
				commerceCountry.getCommerceCountryId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, new CommerceRegionPriorityComparator());

		countryDTO.setRegions(modelsToRegionDTOArray(commerceRegions));

		countryDTO.setShippingAllowed(commerceCountry.isShippingAllowed());
		countryDTO.setSubjectToVAT(commerceCountry.isSubjectToVAT());
		countryDTO.setThreeLettersISOCode(
			commerceCountry.getThreeLettersISOCode());
		countryDTO.setTwoLettersISOCode(commerceCountry.getTwoLettersISOCode());

		return countryDTO;
	}

	public static CurrencyDTO modelToDTO(CommerceCurrency commerceCurrency) {
		CurrencyDTO currencyDTO = new CurrencyDTO();

		currencyDTO.setCode(commerceCurrency.getCode());

		currencyDTO.setFormatPattern(
			LanguageUtils.getLanguageIdMap(
				commerceCurrency.getFormatPatternMap()));
		currencyDTO.setId(commerceCurrency.getCommerceCurrencyId());
		currencyDTO.setMaxFractionDigits(
			commerceCurrency.getMaxFractionDigits());
		currencyDTO.setMinFractionDigits(
			commerceCurrency.getMinFractionDigits());
		currencyDTO.setName(
			LanguageUtils.getLanguageIdMap(commerceCurrency.getNameMap()));
		currencyDTO.setPrimary(commerceCurrency.isPrimary());
		currencyDTO.setRate(commerceCurrency.getRate());
		currencyDTO.setRoundingMode(commerceCurrency.getRoundingMode());

		return currencyDTO;
	}

	public static PriceEntryDTO modelToDTO(
			CommercePriceEntry commercePriceEntry)
		throws PortalException {

		PriceEntryDTO priceEntryDTO = new PriceEntryDTO();

		priceEntryDTO.setCommercePriceListId(
			commercePriceEntry.getCommercePriceListId());
		priceEntryDTO.setExternalReferenceCode(
			commercePriceEntry.getExternalReferenceCode());
		priceEntryDTO.setHasTierPrice(commercePriceEntry.isHasTierPrice());
		priceEntryDTO.setId(commercePriceEntry.getCommercePriceEntryId());
		priceEntryDTO.setPrice(commercePriceEntry.getPrice());
		priceEntryDTO.setPromoPrice(commercePriceEntry.getPromoPrice());

		CPInstance cpInstance = commercePriceEntry.getCPInstance();

		priceEntryDTO.setSku(cpInstance.getSku());
		priceEntryDTO.setSkuExternalReferenceCode(
			cpInstance.getExternalReferenceCode());

		return priceEntryDTO;
	}

	public static PriceListDTO modelToDTO(
		CommercePriceList commercePriceList, String languageId) {

		PriceListDTO priceListDTO = new PriceListDTO();

		try {
			priceListDTO.setActive(!commercePriceList.isInactive());
			priceListDTO.setCommercePriceListId(
				commercePriceList.getCommercePriceListId());
			CommerceCurrency commerceCurrency =
				commercePriceList.getCommerceCurrency();

			priceListDTO.setCurrency(commerceCurrency.getName(languageId));

			priceListDTO.setDisplayDate(commercePriceList.getDisplayDate());
			priceListDTO.setExpirationDate(
				commercePriceList.getExpirationDate());
			priceListDTO.setExternalReferenceCode(
				commercePriceList.getExternalReferenceCode());
			priceListDTO.setId(commercePriceList.getCommercePriceListId());
			priceListDTO.setName(commercePriceList.getName());
			priceListDTO.setPriority(commercePriceList.getPriority());
		}
		catch (Exception e) {
			_log.error("Cannot instantiate PriceListDTO ", e);

			throw new RuntimeException(e);
		}

		return priceListDTO;
	}

	public static RegionDTO modelToDTO(CommerceRegion commerceRegion) {
		RegionDTO regionDTO = new RegionDTO();

		if (commerceRegion == null) {
			return regionDTO;
		}

		regionDTO.setActive(commerceRegion.isActive());
		regionDTO.setCode(commerceRegion.getCode());
		regionDTO.setCommerceCountryId(commerceRegion.getCommerceCountryId());
		regionDTO.setId(commerceRegion.getCommerceRegionId());
		regionDTO.setName(commerceRegion.getName());
		regionDTO.setPriority(commerceRegion.getPriority());

		return regionDTO;
	}

	public static InventoryDTO modelToDTO(
			CommerceWarehouseItem commerceWarehouseItem)
		throws PortalException {

		InventoryDTO inventoryDTO = new InventoryDTO();

		inventoryDTO.setId(commerceWarehouseItem.getCommerceWarehouseId());
		inventoryDTO.setQuantity(commerceWarehouseItem.getQuantity());

		CPInstance cpInstance = commerceWarehouseItem.getCPInstance();

		inventoryDTO.setSkuExternalReferenceCode(
			cpInstance.getExternalReferenceCode());
		inventoryDTO.setSkuId(cpInstance.getCPInstanceId());

		CommerceWarehouse commerceWarehouse =
			commerceWarehouseItem.getCommerceWarehouse();

		inventoryDTO.setWarehouseId(commerceWarehouse.getCommerceWarehouseId());
		inventoryDTO.setWarehouseName(commerceWarehouse.getName());

		return inventoryDTO;
	}

	public static ProductDTO modelToDTO(CPDefinition cpDefinition) {
		ProductDTO productDTO = new ProductDTO();

		productDTO.setActive(!cpDefinition.isInactive());

		productDTO.setDescription(
			LanguageUtils.getLanguageIdMap(cpDefinition.getDescriptionMap()));
		productDTO.setExternalReferenceCode(
			cpDefinition.getExternalReferenceCode());
		productDTO.setId(cpDefinition.getCPDefinitionId());
		productDTO.setProductTypeName(cpDefinition.getProductTypeName());
		productDTO.setShortDescription(
			LanguageUtils.getLanguageIdMap(
				cpDefinition.getShortDescriptionMap()));
		productDTO.setName(
			LanguageUtils.getLanguageIdMap(cpDefinition.getNameMap()));

		ExpandoBridge expandoBridge = cpDefinition.getExpandoBridge();

		Map<String, Serializable> attributes = expandoBridge.getAttributes();

		productDTO.setExpando(attributes);

		return productDTO;
	}

	public static SkuDTO modelToDTO(CPInstance cpInstance) {
		SkuDTO skuDTO = new SkuDTO();

		skuDTO.setCost(cpInstance.getCost());
		skuDTO.setDepth(cpInstance.getDepth());
		skuDTO.setDisplayDate(cpInstance.getDisplayDate());
		skuDTO.setExpirationDate(cpInstance.getExpirationDate());
		skuDTO.setExternalReferenceCode(cpInstance.getExternalReferenceCode());
		skuDTO.setGtin(cpInstance.getGtin());
		skuDTO.setHeight(cpInstance.getHeight());
		skuDTO.setId(cpInstance.getCPInstanceId());
		skuDTO.setManufacturerPartNumber(
			cpInstance.getManufacturerPartNumber());
		skuDTO.setNeverExpire(cpInstance.isExpired());
		skuDTO.setPrice(cpInstance.getPrice());
		skuDTO.setPromoPrice(cpInstance.getPromoPrice());
		skuDTO.setPublished(cpInstance.isPublished());
		skuDTO.setPurchasable(cpInstance.isPurchasable());
		skuDTO.setSku(cpInstance.getSku());
		skuDTO.setWeight(cpInstance.getWeight());
		skuDTO.setWidth(cpInstance.getWidth());

		return skuDTO;
	}

	public static ProductOptionDTO modelToDTO(
		CPOption cpOption, String languageId) {

		ProductOptionDTO productOptionDTO = new ProductOptionDTO();

		productOptionDTO.setDescription(cpOption.getDescription(languageId));
		productOptionDTO.setExternalReferenceCode(
			cpOption.getExternalReferenceCode());
		productOptionDTO.setFacetable(cpOption.isFacetable());
		productOptionDTO.setFieldType(cpOption.getDDMFormFieldTypeName());
		productOptionDTO.setId(cpOption.getCPOptionId());
		productOptionDTO.setKey(cpOption.getKey());
		productOptionDTO.setName(cpOption.getName(languageId));
		productOptionDTO.setRequired(cpOption.isRequired());
		productOptionDTO.setSkuContributor(cpOption.isSkuContributor());

		return productOptionDTO;
	}

	public static ProductOptionValueDTO modelToDTO(
		CPOptionValue cpOptionValue, String languageId) {

		ProductOptionValueDTO productOptionValueDTO =
			new ProductOptionValueDTO();

		productOptionValueDTO.setExternalReferenceCode(
			cpOptionValue.getExternalReferenceCode());
		productOptionValueDTO.setId(cpOptionValue.getCPOptionValueId());
		productOptionValueDTO.setKey(cpOptionValue.getKey());
		productOptionValueDTO.setName(cpOptionValue.getName(languageId));
		productOptionValueDTO.setPriority(cpOptionValue.getPriority());

		return productOptionValueDTO;
	}

	public static WebSiteDTO modelToDTO(Group group, String languageId) {
		WebSiteDTO webSiteDTO = new WebSiteDTO();

		webSiteDTO.setDescription(group.getDescription(languageId));
		webSiteDTO.setId(group.getGroupId());
		webSiteDTO.setName(group.getName(languageId));

		return webSiteDTO;
	}

	public static UserDTO modelToDTO(
			User user, long[] commerceAccountIds, String dashboardURL,
			String profileURL, String[] roleNames, ThemeDisplay themeDisplay)
		throws PortalException {

		UserDTO userDTO = new UserDTO();

		userDTO.setAdditionalName(user.getMiddleName());
		userDTO.setAlternateName(user.getScreenName());
		userDTO.setBirthDate(user.getBirthday());
		userDTO.setCommerceAccountIds(ArrayUtil.toArray(commerceAccountIds));
		userDTO.setDashboardURL(dashboardURL);
		userDTO.setEmail(user.getEmailAddress());
		userDTO.setExternalReferenceCode(user.getExternalReferenceCode());
		userDTO.setFamilyName(user.getLastName());
		userDTO.setGender(user.isMale() ? "male" : "female");
		userDTO.setGivenName(user.getFirstName());
		userDTO.setId(user.getUserId());
		userDTO.setImage(user.getPortraitURL(themeDisplay));
		userDTO.setJobTitle(user.getJobTitle());
		userDTO.setName(user.getFullName());
		userDTO.setProfileURL(profileURL);
		userDTO.setRoleNames(roleNames);

		return userDTO;
	}

	private static String _getAccountType(int type) {
		if (type == 1) {
			return "Personal";
		}
		else if (type == 2) {
			return "Business";
		}

		return "Guest";
	}

	private DTOUtils() {
	}

	private static final Log _log = LogFactoryUtil.getLog(DTOUtils.class);

}