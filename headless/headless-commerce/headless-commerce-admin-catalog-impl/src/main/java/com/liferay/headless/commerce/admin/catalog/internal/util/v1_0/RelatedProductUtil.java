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

import com.liferay.commerce.product.exception.NoSuchCPDefinitionLinkException;
import com.liferay.commerce.product.model.CPDefinitionLink;
import com.liferay.commerce.product.service.CPDefinitionLinkService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.RelatedProduct;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Alessio Antonio Rendina
 */
public class RelatedProductUtil {

	public static CPDefinitionLink upsertCPDefinitionLink(
			CPDefinitionLinkService cpDefinitionLinkService,
			RelatedProduct relatedProduct, long cpDefinitionId,
			ServiceContext serviceContext)
		throws PortalException {

		Product product = relatedProduct.getProduct();

		try {
			CPDefinitionLink cpDefinitionLink =
				cpDefinitionLinkService.getCPDefinitionLink(
					relatedProduct.getId());

			return cpDefinitionLinkService.updateCPDefinitionLink(
				relatedProduct.getId(),
				GetterUtil.get(
					relatedProduct.getPriority(),
					cpDefinitionLink.getPriority()),
				serviceContext);
		}
		catch (NoSuchCPDefinitionLinkException nscpdle) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find relatedProduct with ID: " +
						relatedProduct.getId());
			}
		}

		return cpDefinitionLinkService.addCPDefinitionLink(
			cpDefinitionId, product.getId(),
			GetterUtil.get(relatedProduct.getPriority(), 0D),
			relatedProduct.getType(), serviceContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RelatedProductUtil.class);

}