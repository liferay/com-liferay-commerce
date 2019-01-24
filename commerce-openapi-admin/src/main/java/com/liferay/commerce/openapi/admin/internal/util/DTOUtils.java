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

import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.openapi.admin.model.PriceEntryDTO;
import com.liferay.commerce.openapi.admin.model.PriceListDTO;
import com.liferay.commerce.openapi.admin.model.ProductDTO;
import com.liferay.commerce.openapi.admin.model.ProductOptionDTO;
import com.liferay.commerce.openapi.admin.model.ProductOptionValueDTO;
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

import java.util.Locale;

/**
 * @author Zoltán Takács
 */
public class DTOUtils {

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

	public static ProductDTO modelToDTO(
		CPDefinition cpDefinition, Locale locale) {

		ProductDTO productDTO = new ProductDTO();

		productDTO.setActive(!cpDefinition.isInactive());

		productDTO.setDescription(cpDefinition.getDescription());
		productDTO.setExternalReferenceCode(
			cpDefinition.getExternalReferenceCode());
		productDTO.setId(cpDefinition.getCProductId());
		productDTO.setProductTypeName(cpDefinition.getProductTypeName());
		productDTO.setShortDescription(cpDefinition.getShortDescription());
		productDTO.setTitle(cpDefinition.getMetaTitle(locale.getLanguage()));

		return productDTO;
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

	private DTOUtils() {
	}

	private static final Log _log = LogFactoryUtil.getLog(DTOUtils.class);

}