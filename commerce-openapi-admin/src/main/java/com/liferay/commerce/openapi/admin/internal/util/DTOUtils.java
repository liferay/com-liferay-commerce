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

package com.liferay.commerce.openapi.admin.internal.util;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.model.CommerceWarehouse;
import com.liferay.commerce.model.CommerceWarehouseItem;
import com.liferay.commerce.openapi.admin.model.AccountDTO;
import com.liferay.commerce.openapi.admin.model.InventoryDTO;
import com.liferay.commerce.openapi.admin.model.PriceEntryDTO;
import com.liferay.commerce.openapi.admin.model.PriceListDTO;
import com.liferay.commerce.openapi.admin.model.ProductDTO;
import com.liferay.commerce.openapi.admin.model.ProductOptionDTO;
import com.liferay.commerce.openapi.admin.model.ProductOptionValueDTO;
import com.liferay.commerce.openapi.admin.model.SkuDTO;
import com.liferay.commerce.openapi.admin.model.UserDTO;
import com.liferay.commerce.openapi.admin.model.WebSiteDTO;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.model.CPOptionValue;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.Locale;

/**
 * @author Zoltán Takács
 */
public class DTOUtils {

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
			accountDTO.setUserIds(new long[] {commerceAccount.getUserId()});
			accountDTO.setEmailAddresses(
				new String[] {commerceAccount.getEmail()});
		}
		catch (Exception e) {
			_log.error("Cannot instantiate AccountDTO ", e);

			throw new RuntimeException(e);
		}

		return accountDTO;
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
		CommercePriceList commercePriceList, Locale locale) {

		PriceListDTO priceListDTO = new PriceListDTO();

		try {
			priceListDTO.setActive(!commercePriceList.isInactive());
			priceListDTO.setCommercePriceListId(
				commercePriceList.getCommercePriceListId());
			CommerceCurrency commerceCurrency =
				commercePriceList.getCommerceCurrency();

			priceListDTO.setCurrency(commerceCurrency.getName(locale));

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

		productDTO.setDescription(cpDefinition.getDescription());
		productDTO.setExternalReferenceCode(
			cpDefinition.getExternalReferenceCode());
		productDTO.setId(cpDefinition.getCProductId());
		productDTO.setProductTypeName(cpDefinition.getProductTypeName());
		productDTO.setShortDescription(cpDefinition.getShortDescription());
		productDTO.setName(cpDefinition.getName());

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
		CPOption cpOption, Locale locale) {

		ProductOptionDTO productOptionDTO = new ProductOptionDTO();

		productOptionDTO.setDescription(cpOption.getDescription(locale));
		productOptionDTO.setExternalReferenceCode(
			cpOption.getExternalReferenceCode());
		productOptionDTO.setFacetable(cpOption.isFacetable());
		productOptionDTO.setFieldType(cpOption.getDDMFormFieldTypeName());
		productOptionDTO.setId(cpOption.getCPOptionId());
		productOptionDTO.setKey(cpOption.getKey());
		productOptionDTO.setName(cpOption.getName(locale));
		productOptionDTO.setRequired(cpOption.isRequired());
		productOptionDTO.setSkuContributor(cpOption.isSkuContributor());

		return productOptionDTO;
	}

	public static ProductOptionValueDTO modelToDTO(
		CPOptionValue cpOptionValue, Locale locale) {

		ProductOptionValueDTO productOptionValueDTO =
			new ProductOptionValueDTO();

		productOptionValueDTO.setExternalReferenceCode(
			cpOptionValue.getExternalReferenceCode());
		productOptionValueDTO.setId(cpOptionValue.getCPOptionValueId());
		productOptionValueDTO.setKey(cpOptionValue.getKey());
		productOptionValueDTO.setName(cpOptionValue.getName(locale));
		productOptionValueDTO.setPriority(cpOptionValue.getPriority());

		return productOptionValueDTO;
	}

	public static WebSiteDTO modelToDTO(Group group, Locale locale) {
		WebSiteDTO webSiteDTO = new WebSiteDTO();

		webSiteDTO.setDescription(group.getDescription(locale));
		webSiteDTO.setId(group.getGroupId());
		webSiteDTO.setName(group.getName(locale));

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
		userDTO.setCommerceAccountIds(commerceAccountIds);
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