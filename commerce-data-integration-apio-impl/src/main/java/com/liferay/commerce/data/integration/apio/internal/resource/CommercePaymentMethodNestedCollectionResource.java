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
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelService;
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
		<CommercePaymentMethodGroupRel, Long, CommercePaymentMethodIdentifier,
		 Long, CommerceWebSiteIdentifier> {

	@Override
	public NestedCollectionRoutes<CommercePaymentMethodGroupRel, Long, Long>
		collectionRoutes(
			NestedCollectionRoutes.Builder
				<CommercePaymentMethodGroupRel, Long, Long>
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
	public ItemRoutes<CommercePaymentMethodGroupRel, Long> itemRoutes(
		ItemRoutes.Builder<CommercePaymentMethodGroupRel, Long> builder) {

		return builder.addGetter(
			this::_getCommercePaymentMethod
		).build();
	}

	@Override
	public Representor<CommercePaymentMethodGroupRel> representor(
		Representor.Builder<CommercePaymentMethodGroupRel, Long> builder) {

		return builder.types(
			"CommercePaymentMethodGroupRel"
		).identifier(
			CommercePaymentMethodGroupRel::getCommercePaymentMethodGroupRelId
		).addBidirectionalModel(
			"commerceWebSite", "commercePaymentMethods",
			CommerceWebSiteIdentifier.class,
			CommercePaymentMethodGroupRel::getGroupId
		).addBoolean(
			"active", CommercePaymentMethodGroupRel::getActive
		).addDate(
			"dateCreated", CommercePaymentMethodGroupRel::getCreateDate
		).addDate(
			"dateModified", CommercePaymentMethodGroupRel::getModifiedDate
		).addLocalizedStringByLocale(
			"description", CommercePaymentMethodGroupRel::getDescription
		).addLocalizedStringByLocale(
			"name", CommercePaymentMethodGroupRel::getName
		).addLinkedModel(
			"author", CommerceUserIdentifier.class,
			commercePaymentMethod ->
				_userHelper.userIdToClassPKExternalReferenceCode(
					commercePaymentMethod.getUserId())
		).addString(
			"engineKey", CommercePaymentMethodGroupRel::getEngineKey
		).build();
	}

	private CommercePaymentMethodGroupRel _getCommercePaymentMethod(
			Long commercePaymentMethodId)
		throws PortalException {

		return _commercePaymentMethodGroupRelService.
			getCommercePaymentMethodGroupRel(commercePaymentMethodId);
	}

	private PageItems<CommercePaymentMethodGroupRel> _getPageItems(
			Pagination pagination, Long webSiteId)
		throws PortalException {

		List<CommercePaymentMethodGroupRel> commercePaymentMethods =
			_commercePaymentMethodGroupRelService.
				getCommercePaymentMethodGroupRels(webSiteId, true);

		int total =
			_commercePaymentMethodGroupRelService.
				getCommercePaymentMethodGroupRelsCount(webSiteId, true);

		return new PageItems<>(commercePaymentMethods, total);
	}

	@Reference
	private CommercePaymentMethodGroupRelService
		_commercePaymentMethodGroupRelService;

	@Reference
	private UserHelper _userHelper;

}