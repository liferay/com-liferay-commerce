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

package com.liferay.commerce.data.integration.apio.internal.resource;

import com.liferay.apio.architect.pagination.PageItems;
import com.liferay.apio.architect.pagination.Pagination;
import com.liferay.apio.architect.representor.Representor;
import com.liferay.apio.architect.resource.NestedCollectionResource;
import com.liferay.apio.architect.routes.ItemRoutes;
import com.liferay.apio.architect.routes.NestedCollectionRoutes;
import com.liferay.commerce.data.integration.apio.identifier.CommercePaymentMethodIdentifier;
import com.liferay.commerce.data.integration.headless.compat.apio.identifier.CommerceUserIdentifier;
import com.liferay.commerce.data.integration.headless.compat.apio.identifier.CommerceWebSiteIdentifier;
import com.liferay.commerce.data.integration.headless.compat.apio.util.UserHelper;
import com.liferay.commerce.model.CommercePaymentMethod;
import com.liferay.commerce.service.CommercePaymentMethodService;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 * @author Zoltán Takács
 */
@Component(immediate = true, service = NestedCollectionResource.class)
public class CommercePaymentMethodNestedCollectionResource
	implements NestedCollectionResource
		<CommercePaymentMethod, Long, CommercePaymentMethodIdentifier, Long,
		 CommerceWebSiteIdentifier> {

	@Override
	public NestedCollectionRoutes<CommercePaymentMethod, Long, Long>
		collectionRoutes(
			NestedCollectionRoutes.Builder<CommercePaymentMethod, Long, Long>
				builder) {

		return builder.addGetter(
			this::_getPageItems
		).build();
	}

	@Override
	public String getName() {
		return "commerce-payment-method";
	}

	@Override
	public ItemRoutes<CommercePaymentMethod, Long> itemRoutes(
		ItemRoutes.Builder<CommercePaymentMethod, Long> builder) {

		return builder.addGetter(
			this::_getCommercePaymentMethod
		).build();
	}

	@Override
	public Representor<CommercePaymentMethod> representor(
		Representor.Builder<CommercePaymentMethod, Long> builder) {

		return builder.types(
			"CommercePaymentMethod"
		).identifier(
			CommercePaymentMethod::getCommercePaymentMethodId
		).addBidirectionalModel(
			"commerceWebSite", "commercePaymentMethods",
			CommerceWebSiteIdentifier.class, CommercePaymentMethod::getGroupId
		).addBoolean(
			"active", CommercePaymentMethod::getActive
		).addDate(
			"dateCreated", CommercePaymentMethod::getCreateDate
		).addDate(
			"dateModified", CommercePaymentMethod::getModifiedDate
		).addLocalizedStringByLocale(
			"description", CommercePaymentMethod::getDescription
		).addLocalizedStringByLocale(
			"name", CommercePaymentMethod::getName
		).addLinkedModel(
			"author", CommerceUserIdentifier.class,
			commercePaymentMethod ->
				_userHelper.userIdToClassPKExternalReferenceCode(
					commercePaymentMethod.getUserId())
		).addString(
			"engineKey", CommercePaymentMethod::getEngineKey
		).build();
	}

	private CommercePaymentMethod _getCommercePaymentMethod(
			Long commercePaymentMethodId)
		throws PortalException {

		return _commercePaymentMethodService.getCommercePaymentMethod(
			commercePaymentMethodId);
	}

	private PageItems<CommercePaymentMethod> _getPageItems(
			Pagination pagination, Long webSiteId)
		throws PortalException {

		List<CommercePaymentMethod> commercePaymentMethods =
			_commercePaymentMethodService.getCommercePaymentMethods(
				webSiteId, true);

		int total =
			_commercePaymentMethodService.getCommercePaymentMethodsCount(
				webSiteId, true);

		return new PageItems<>(commercePaymentMethods, total);
	}

	@Reference
	private CommercePaymentMethodService _commercePaymentMethodService;

	@Reference
	private UserHelper _userHelper;

}