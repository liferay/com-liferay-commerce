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
import com.liferay.commerce.account.model.CommerceAccountOrganizationRel;
import com.liferay.commerce.account.model.CommerceAccountUserRel;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceOrderNote;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.commerce.model.CommerceWarehouse;
import com.liferay.commerce.model.CommerceWarehouseItem;
import com.liferay.commerce.openapi.admin.model.v2_0.AccountDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.AccountMemberDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.AccountOrganizationDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.AccountRoleDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.AddressDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.CountryDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.CurrencyDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.InventoryDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.OrderDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.OrderItemDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.OrderNoteDTO;
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
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import org.osgi.service.component.annotations.Component;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author Zoltán Takács
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = DTOMapper.class)
public class DTOMapper {

	public AccountMemberDTO[] modelsToAccountMemberDTOArray(
		List<CommerceAccountUserRel> commerceAccountUserRels) {

		if (commerceAccountUserRels == null) {
			return null;
		}

		List<AccountMemberDTO> accountMembers = new ArrayList<>();

		for (CommerceAccountUserRel commerceAccountUserRel :
				commerceAccountUserRels) {

			accountMembers.add(modelToDTO(commerceAccountUserRel));
		}

		Stream<AccountMemberDTO> stream = accountMembers.stream();

		return stream.toArray(AccountMemberDTO[]::new);
	}

	public AccountOrganizationDTO[] modelsToAccountOrganizationDTOArray(
		List<CommerceAccountOrganizationRel> commerceAccountOrganizationRels) {

		if (commerceAccountOrganizationRels == null) {
			return null;
		}

		List<AccountOrganizationDTO> accountOrganizations = new ArrayList<>();

		for (CommerceAccountOrganizationRel commerceAccountOrganizationRel :
				commerceAccountOrganizationRels) {

			accountOrganizations.add(
				modelToDTO(commerceAccountOrganizationRel));
		}

		Stream<AccountOrganizationDTO> stream = accountOrganizations.stream();

		return stream.toArray(AccountOrganizationDTO[]::new);
	}

	public AccountRoleDTO[] modelsToAccountRoleDTOArray(
		List<UserGroupRole> userGroupRoles) {

		if (userGroupRoles == null) {
			return null;
		}

		List<AccountRoleDTO> accountRoles = new ArrayList<>();

		for (UserGroupRole userGroupRole : userGroupRoles) {
			accountRoles.add(modelToDTO(userGroupRole));
		}

		Stream<AccountRoleDTO> stream = accountRoles.stream();

		return stream.toArray(AccountRoleDTO[]::new);
	}

	public RegionDTO[] modelsToRegionDTOArray(
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

	public AccountDTO modelToDTO(CommerceAccount commerceAccount) {
		AccountDTO accountDTO = new AccountDTO();

		if (commerceAccount == null) {
			return accountDTO;
		}

		try {
			accountDTO.setEmailAddresses(
				new String[] {commerceAccount.getEmail()});
			accountDTO.setId(commerceAccount.getCommerceAccountId());
			accountDTO.setExternalReferenceCode(
				commerceAccount.getExternalReferenceCode());
			accountDTO.setLogoId(commerceAccount.getLogoId());
			accountDTO.setName(commerceAccount.getName());
			accountDTO.setOrganizations(
				modelsToAccountOrganizationDTOArray(
					commerceAccount.getCommerceAccountOrganizationRels()));
			accountDTO.setRoot(commerceAccount.isRoot());
			accountDTO.setTaxId(commerceAccount.getTaxId());
			accountDTO.setType(commerceAccount.getType());
			accountDTO.setUsers(
				modelsToAccountMemberDTOArray(
					commerceAccount.getCommerceAccountUserRels()));
		}
		catch (Exception e) {
			_log.error("Cannot instantiate AccountDTO ", e);

			throw new RuntimeException(e);
		}

		return accountDTO;
	}

	public AccountOrganizationDTO modelToDTO(
		CommerceAccountOrganizationRel commerceAccountOrganizationRel) {

		AccountOrganizationDTO accountOrganizationDTO =
			new AccountOrganizationDTO();

		if (commerceAccountOrganizationRel == null) {
			return accountOrganizationDTO;
		}

		try {
			accountOrganizationDTO.setCommerceAccountId(
				commerceAccountOrganizationRel.getCommerceAccountId());

			Organization organization =
				commerceAccountOrganizationRel.getOrganization();

			accountOrganizationDTO.setName(organization.getName());
			accountOrganizationDTO.setOrganizationId(
				organization.getOrganizationId());
			accountOrganizationDTO.setTreePath(organization.getTreePath());
		}
		catch (Exception e) {
			_log.error("Cannot instantiate AccountOrganizationDTO ", e);

			throw new RuntimeException(e);
		}

		return accountOrganizationDTO;
	}

	public AccountMemberDTO modelToDTO(
		CommerceAccountUserRel commerceAccountUserRel) {

		AccountMemberDTO accountMemberDTO = new AccountMemberDTO();

		if (commerceAccountUserRel == null) {
			return accountMemberDTO;
		}

		try {
			accountMemberDTO.setCommerceAccountId(
				commerceAccountUserRel.getCommerceAccountId());

			User user = commerceAccountUserRel.getUser();

			accountMemberDTO.setName(user.getFullName());
			accountMemberDTO.setUserId(user.getUserId());

			accountMemberDTO.setRoles(
				modelsToAccountRoleDTOArray(
					commerceAccountUserRel.getUserGroupRoles()));
		}
		catch (Exception e) {
			_log.error("Cannot instantiate AccountOrganizationDTO ", e);

			throw new RuntimeException(e);
		}

		return accountMemberDTO;
	}

	public AddressDTO modelToDTO(CommerceAddress commerceAddress) {
		AddressDTO addressDTO = new AddressDTO();

		if (commerceAddress == null) {
			return addressDTO;
		}

		addressDTO.setCity(commerceAddress.getCity());
		addressDTO.setCommerceCountryId(commerceAddress.getCommerceCountryId());
		addressDTO.setCommerceRegionId(commerceAddress.getCommerceRegionId());
		addressDTO.setDefaultBilling(commerceAddress.isDefaultBilling());
		addressDTO.setDefaultShipping(commerceAddress.isDefaultShipping());
		addressDTO.setDescription(commerceAddress.getDescription());
		addressDTO.setId(commerceAddress.getCommerceAddressId());
		addressDTO.setLatitude(commerceAddress.getLatitude());
		addressDTO.setLongitude(commerceAddress.getLongitude());
		addressDTO.setName(commerceAddress.getName());
		addressDTO.setPhoneNumber(commerceAddress.getPhoneNumber());
		addressDTO.setStreet1(commerceAddress.getStreet1());
		addressDTO.setStreet2(commerceAddress.getStreet2());
		addressDTO.setStreet3(commerceAddress.getStreet3());
		addressDTO.setZip(commerceAddress.getZip());

		return addressDTO;
	}

	public CountryDTO modelToDTO(CommerceCountry commerceCountry) {
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

	public CurrencyDTO modelToDTO(CommerceCurrency commerceCurrency) {
		CurrencyDTO currencyDTO = new CurrencyDTO();

		if (commerceCurrency == null) {
			return currencyDTO;
		}

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

	public OrderDTO modelToDTO(
		CommerceOrder commerceOrder, String languageId) {

		OrderDTO orderDTO = new OrderDTO();

		if (commerceOrder == null) {
			return orderDTO;
		}

		try {
			orderDTO.setAdvanceStatus(commerceOrder.getAdvanceStatus());
			orderDTO.setBillingAddressDTO(
				modelToDTO(commerceOrder.getBillingAddress()));
			orderDTO.setBillingAddressId(commerceOrder.getBillingAddressId());
			orderDTO.setCommerceAccountId(commerceOrder.getCommerceAccountId());

			CommerceCurrency commerceCurrency =
				commerceOrder.getCommerceCurrency();

			orderDTO.setCurrency(commerceCurrency.getName(languageId));

			orderDTO.setExternalReferenceCode(
				commerceOrder.getExternalReferenceCode());
			orderDTO.setId(commerceOrder.getCommerceOrderId());
			orderDTO.setOrderStatus(commerceOrder.getOrderStatus());
			orderDTO.setPaymentMethod(
				commerceOrder.getCommercePaymentMethodKey());
			orderDTO.setPaymentStatus(commerceOrder.getPaymentStatus());
			orderDTO.setPurchaseOrderNumber(
				commerceOrder.getPurchaseOrderNumber());
			orderDTO.setShippingAddressDTO(
				modelToDTO(commerceOrder.getShippingAddress()));
			orderDTO.setShippingAddressId(commerceOrder.getShippingAddressId());
			orderDTO.setShippingAmount(commerceOrder.getShippingAmount());

			CommerceShippingMethod commerceShippingMethod =
				commerceOrder.getCommerceShippingMethod();

			if (commerceShippingMethod != null) {
				orderDTO.setShippingMethod(
					commerceShippingMethod.getEngineKey());
			}

			orderDTO.setShippingOption(commerceOrder.getShippingOptionName());
			orderDTO.setSubtotal(commerceOrder.getSubtotal());
			orderDTO.setTotal(commerceOrder.getTotal());
		}
		catch (Exception e) {
			_log.error("Cannot instantiate OrderDTO ", e);

			throw new RuntimeException(e);
		}

		return orderDTO;
	}

	public OrderItemDTO modelToDTO(CommerceOrderItem commerceOrderItem) {
		OrderItemDTO orderItemDTO = new OrderItemDTO();

		if (commerceOrderItem == null) {
			return orderItemDTO;
		}

		orderItemDTO.setCommerceOrderId(commerceOrderItem.getCommerceOrderId());
		orderItemDTO.setDiscountAmount(commerceOrderItem.getDiscountAmount());
		orderItemDTO.setExternalReferenceCode(
			commerceOrderItem.getExternalReferenceCode());
		orderItemDTO.setFinalPrice(commerceOrderItem.getFinalPrice());
		orderItemDTO.setId(commerceOrderItem.getCommerceOrderItemId());
		orderItemDTO.setName(
			LanguageUtils.getLanguageIdMap(commerceOrderItem.getNameMap()));
		orderItemDTO.setQuantity(commerceOrderItem.getQuantity());
		orderItemDTO.setShippedQuantity(commerceOrderItem.getShippedQuantity());
		orderItemDTO.setSku(commerceOrderItem.getSku());
		orderItemDTO.setSkuId(commerceOrderItem.getCPInstanceId());
		orderItemDTO.setSubscription(commerceOrderItem.isSubscription());
		orderItemDTO.setUnitPrice(commerceOrderItem.getUnitPrice());

		return orderItemDTO;
	}

	public OrderNoteDTO modelToDTO(CommerceOrderNote commerceOrderNote) {
		OrderNoteDTO orderNoteDTO = new OrderNoteDTO();

		if (commerceOrderNote == null) {
			return orderNoteDTO;
		}

		orderNoteDTO.setAuthor(commerceOrderNote.getUserName());
		orderNoteDTO.setCommerceOrderId(commerceOrderNote.getCommerceOrderId());
		orderNoteDTO.setContent(commerceOrderNote.getContent());
		orderNoteDTO.setExternalReferenceCode(
			commerceOrderNote.getExternalReferenceCode());
		orderNoteDTO.setId(commerceOrderNote.getCommerceOrderNoteId());
		orderNoteDTO.setRestricted(commerceOrderNote.isRestricted());

		return orderNoteDTO;
	}

	public PriceEntryDTO modelToDTO(
			CommercePriceEntry commercePriceEntry)
		throws PortalException {

		PriceEntryDTO priceEntryDTO = new PriceEntryDTO();

		if (commercePriceEntry == null) {
			return priceEntryDTO;
		}

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

	public PriceListDTO modelToDTO(
		CommercePriceList commercePriceList, String languageId) {

		PriceListDTO priceListDTO = new PriceListDTO();

		if (commercePriceList == null) {
			return priceListDTO;
		}

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

	public RegionDTO modelToDTO(CommerceRegion commerceRegion) {
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

	public InventoryDTO modelToDTO(
			CommerceWarehouseItem commerceWarehouseItem)
		throws PortalException {

		InventoryDTO inventoryDTO = new InventoryDTO();

		if (commerceWarehouseItem == null) {
			return inventoryDTO;
		}

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

	public ProductDTO modelToDTO(CPDefinition cpDefinition) {
		ProductDTO productDTO = new ProductDTO();

		if (cpDefinition == null) {
			return productDTO;
		}

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

	public SkuDTO modelToDTO(CPInstance cpInstance) {
		SkuDTO skuDTO = new SkuDTO();

		if (cpInstance == null) {
			return skuDTO;
		}

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

	public ProductOptionDTO modelToDTO(
		CPOption cpOption, String languageId) {

		ProductOptionDTO productOptionDTO = new ProductOptionDTO();

		if (cpOption == null) {
			return productOptionDTO;
		}

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

	public ProductOptionValueDTO modelToDTO(
		CPOptionValue cpOptionValue, String languageId) {

		ProductOptionValueDTO productOptionValueDTO =
			new ProductOptionValueDTO();

		if (cpOptionValue == null) {
			return productOptionValueDTO;
		}

		productOptionValueDTO.setExternalReferenceCode(
			cpOptionValue.getExternalReferenceCode());
		productOptionValueDTO.setId(cpOptionValue.getCPOptionValueId());
		productOptionValueDTO.setKey(cpOptionValue.getKey());
		productOptionValueDTO.setName(cpOptionValue.getName(languageId));
		productOptionValueDTO.setPriority(cpOptionValue.getPriority());

		return productOptionValueDTO;
	}

	public WebSiteDTO modelToDTO(Group group, String languageId) {
		WebSiteDTO webSiteDTO = new WebSiteDTO();

		if (group == null) {
			return webSiteDTO;
		}

		webSiteDTO.setDescription(group.getDescription(languageId));
		webSiteDTO.setId(group.getGroupId());
		webSiteDTO.setName(group.getName(languageId));

		return webSiteDTO;
	}

	public UserDTO modelToDTO(
			User user, long[] commerceAccountIds, String dashboardURL,
			String profileURL, String[] roleNames, ThemeDisplay themeDisplay)
		throws PortalException {

		UserDTO userDTO = new UserDTO();

		if (user == null) {
			return userDTO;
		}

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

	public AccountRoleDTO modelToDTO(UserGroupRole userGroupRole) {
		AccountRoleDTO accountRoleDTO = new AccountRoleDTO();

		if (userGroupRole == null) {
			return accountRoleDTO;
		}

		try {
			Role role = userGroupRole.getRole();

			accountRoleDTO.setDescription(
				LanguageUtils.getLanguageIdMap(role.getDescriptionMap()));
			accountRoleDTO.setName(role.getName());
			accountRoleDTO.setRoleId(role.getRoleId());
			accountRoleDTO.setTitle(
				LanguageUtils.getLanguageIdMap(role.getTitleMap()));
		}
		catch (Exception e) {
			_log.error("Cannot instantiate AccountRoleDTO ", e);

			throw new RuntimeException(e);
		}

		return accountRoleDTO;
	}

	private static final Log _log = LogFactoryUtil.getLog(DTOMapper.class);

}