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

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductShippingConfiguration;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Alessio Antonio Rendina
 */
public class ProductShippingConfigurationUtil {

	public static CPDefinition updateCPDefinitionShippingInfo(
			CPDefinitionService cpDefinitionService,
			ProductShippingConfiguration productShippingConfiguration,
			CPDefinition cpDefinition, ServiceContext serviceContext)
		throws PortalException {

		return cpDefinitionService.updateShippingInfo(
			cpDefinition.getCPDefinitionId(),
			GetterUtil.get(
				productShippingConfiguration.getShippable(),
				cpDefinition.isShippable()),
			GetterUtil.get(
				productShippingConfiguration.getFreeShipping(),
				cpDefinition.isFreeShipping()),
			GetterUtil.get(
				productShippingConfiguration.getShippingSeparately(),
				cpDefinition.isShipSeparately()),
			ProductUtil.getShippingExtraPrice(
				cpDefinition, productShippingConfiguration),
			ProductUtil.getWidth(cpDefinition, productShippingConfiguration),
			ProductUtil.getHeight(cpDefinition, productShippingConfiguration),
			ProductUtil.getDepth(cpDefinition, productShippingConfiguration),
			ProductUtil.getWeight(cpDefinition, productShippingConfiguration),
			serviceContext);
	}

}