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

package com.liferay.headless.commerce.admin.catalog.internal.util.v1_0;

import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.product.exception.NoSuchCPDefinitionException;
import com.liferay.commerce.product.exception.NoSuchCProductException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.service.CPDefinitionLocalServiceUtil;
import com.liferay.commerce.product.service.CProductLocalServiceUtil;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductShippingConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductTaxConfiguration;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.StringUtil;

import java.math.BigDecimal;

/**
 * @author Alessio Antonio Rendina
 */
public class ProductUtil {

	public static String getAllowedOrderQuantities(
		CPDefinitionInventory cpDefinitionInventory,
		ProductConfiguration productConfiguration) {

		if (productConfiguration.getAllowedOrderQuantities() != null) {
			return StringUtil.merge(
				productConfiguration.getAllowedOrderQuantities());
		}

		if (cpDefinitionInventory == null) {
			return null;
		}

		return cpDefinitionInventory.getAllowedOrderQuantities();
	}

	public static double getDepth(
		CPDefinition cpDefinition,
		ProductShippingConfiguration productShippingConfiguration) {

		BigDecimal depth = productShippingConfiguration.getDepth();

		if (depth == null) {
			return cpDefinition.getDepth();
		}

		return depth.doubleValue();
	}

	public static double getHeight(
		CPDefinition cpDefinition,
		ProductShippingConfiguration productShippingConfiguration) {

		BigDecimal height = productShippingConfiguration.getHeight();

		if (height == null) {
			return cpDefinition.getHeight();
		}

		return height.doubleValue();
	}

	public static CPDefinition getProductByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		CProduct cProduct =
			CProductLocalServiceUtil.fetchCProductByReferenceCode(
				companyId, externalReferenceCode);

		if (cProduct == null) {
			throw new NoSuchCProductException(
				"Unable to find Product with externalReferenceCode: " +
					externalReferenceCode);
		}

		CPDefinition cpDefinition =
			CPDefinitionLocalServiceUtil.fetchCPDefinition(
				cProduct.getPublishedCPDefinitionId());

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find Product with externalReferenceCode: " +
					externalReferenceCode);
		}

		return cpDefinition;
	}

	public static CPDefinition getProductById(long id) throws PortalException {
		CProduct cProduct = CProductLocalServiceUtil.fetchCProduct(id);

		if (cProduct == null) {
			throw new NoSuchCProductException(
				"Unable to find Product with ID: " + id);
		}

		CPDefinition cpDefinition =
			CPDefinitionLocalServiceUtil.fetchCPDefinition(
				cProduct.getPublishedCPDefinitionId());

		if (cpDefinition == null) {
			throw new NoSuchCPDefinitionException(
				"Unable to find Product with ID: " + id);
		}

		return cpDefinition;
	}

	public static double getShippingExtraPrice(
		CPDefinition cpDefinition,
		ProductShippingConfiguration productShippingConfiguration) {

		BigDecimal shippingExtraPrice =
			productShippingConfiguration.getShippingExtraPrice();

		if (shippingExtraPrice == null) {
			return cpDefinition.getShippingExtraPrice();
		}

		return shippingExtraPrice.doubleValue();
	}

	public static double getWeight(
		CPDefinition cpDefinition,
		ProductShippingConfiguration productShippingConfiguration) {

		BigDecimal weight = productShippingConfiguration.getWeight();

		if (weight == null) {
			return cpDefinition.getWeight();
		}

		return weight.doubleValue();
	}

	public static double getWidth(
		CPDefinition cpDefinition,
		ProductShippingConfiguration productShippingConfiguration) {

		BigDecimal width = productShippingConfiguration.getWidth();

		if (width == null) {
			return cpDefinition.getWidth();
		}

		return width.doubleValue();
	}

	public static boolean isTaxExempt(
		CPDefinition cpDefinition,
		ProductTaxConfiguration productTaxConfiguration) {

		if (productTaxConfiguration.getTaxable() != null) {
			return !productTaxConfiguration.getTaxable();
		}

		if (cpDefinition != null) {
			return cpDefinition.isTaxExempt();
		}

		return false;
	}

}