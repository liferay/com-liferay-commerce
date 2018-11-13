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

import static com.liferay.portal.apio.idempotent.Idempotent.idempotent;

import com.liferay.apio.architect.pagination.PageItems;
import com.liferay.apio.architect.pagination.Pagination;
import com.liferay.apio.architect.representor.Representor;
import com.liferay.apio.architect.resource.NestedCollectionResource;
import com.liferay.apio.architect.routes.ItemRoutes;
import com.liferay.apio.architect.routes.NestedCollectionRoutes;
import com.liferay.commerce.data.integration.apio.identifier.CommerceCountryIdentifier;
import com.liferay.commerce.data.integration.apio.internal.form.CommerceCountryUpserterForm;
import com.liferay.commerce.data.integration.apio.internal.util.ServiceContextHelper;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.service.CommerceCountryService;
import com.liferay.portal.apio.permission.HasPermission;
import com.liferay.portal.apio.user.CurrentUser;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.site.apio.architect.identifier.WebSiteIdentifier;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 * @author Zoltán Takács
 */
@Component(immediate = true, service = NestedCollectionResource.class)
public class CommerceCountryNestedCollectionResource
	implements NestedCollectionResource
		<CommerceCountry, Long, CommerceCountryIdentifier, Long,
		 WebSiteIdentifier> {

	@Override
	public NestedCollectionRoutes<CommerceCountry, Long, Long> collectionRoutes(
		NestedCollectionRoutes.Builder<CommerceCountry, Long, Long> builder) {

		return builder.addGetter(
			this::_getPageItems
		).addCreator(
			this::_addCommerceCountry, CurrentUser.class,
			_hasPermission.forAddingIn(WebSiteIdentifier.class),
			CommerceCountryUpserterForm::buildForm
		).build();
	}

	@Override
	public String getName() {
		return "commerce-country";
	}

	@Override
	public ItemRoutes<CommerceCountry, Long> itemRoutes(
		ItemRoutes.Builder<CommerceCountry, Long> builder) {

		return builder.addGetter(
			this::_getCommerceCountry
		).addUpdater(
			this::_updateCommerceCountry, CurrentUser.class,
			_hasPermission::forUpdating, CommerceCountryUpserterForm::buildForm
		).addRemover(
			idempotent(_commerceCountryService::deleteCommerceCountry),
			_hasPermission::forDeleting
		).build();
	}

	@Override
	public Representor<CommerceCountry> representor(
		Representor.Builder<CommerceCountry, Long> builder) {

		return builder.types(
			"CommerceCountry"
		).identifier(
			CommerceCountry::getCommerceCountryId
		).addBidirectionalModel(
			"webSite", "commerceCountries", WebSiteIdentifier.class,
			CommerceCountry::getGroupId
		).addBoolean(
			"billingAllowed", CommerceCountry::isBillingAllowed
		).addBoolean(
			"shippingAllowed", CommerceCountry::isShippingAllowed
		).addBoolean(
			"subjectToVAT", CommerceCountry::isSubjectToVAT
		).addLocalizedStringByLocale(
			"name", CommerceCountry::getName
		).addNumber(
			"numericISOCode", CommerceCountry::getNumericISOCode
		).addString(
			"threeLettersISOCode", CommerceCountry::getThreeLettersISOCode
		).addString(
			"twoLettersISOCode", CommerceCountry::getTwoLettersISOCode
		).build();
	}

	private CommerceCountry _getCommerceCountry(Long commerceCountryId)
		throws PortalException {

		return _commerceCountryService.getCommerceCountry(commerceCountryId);
	}

	private PageItems<CommerceCountry> _getPageItems(
			Pagination pagination, Long webSiteId)
		throws PortalException {

		List<CommerceCountry> commerceCountries =
			_commerceCountryService.getCommerceCountries(
				webSiteId, pagination.getStartPosition(),
				pagination.getEndPosition(), null);

		int total = _commerceCountryService.getCommerceCountriesCount(
			webSiteId);

		return new PageItems<>(commerceCountries, total);
	}

	private CommerceCountry _updateCommerceCountry(
			long commerceCountryId,
			CommerceCountryUpserterForm commerceCountryUpserterForm,
			User currentUser)
		throws PortalException {

		CommerceCountry commerceCountry =
			_commerceCountryService.getCommerceCountry(commerceCountryId);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commerceCountry.getGroupId(), new long[0], currentUser, true);

		return _commerceCountryService.updateCommerceCountry(
			commerceCountryId, commerceCountryUpserterForm.getNameMap(),
			commerceCountryUpserterForm.isBillingAllowed(),
			commerceCountryUpserterForm.isShippingAllowed(),
			commerceCountryUpserterForm.getTwoLettersISOCode(),
			commerceCountryUpserterForm.getThreeLettersISOCode(),
			commerceCountryUpserterForm.getNumericISOCode(),
			commerceCountryUpserterForm.isSubjectToVAT(), 0D, true,
			serviceContext);
	}

	private CommerceCountry _addCommerceCountry(
			Long groupId,
			CommerceCountryUpserterForm commerceCountryUpserterForm,
			User currentUser)
		throws PortalException {

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId, new long[0], currentUser, true);

		return _commerceCountryService.addCommerceCountry(
			commerceCountryUpserterForm.getNameMap(),
			commerceCountryUpserterForm.isBillingAllowed(),
			commerceCountryUpserterForm.isShippingAllowed(),
			commerceCountryUpserterForm.getTwoLettersISOCode(),
			commerceCountryUpserterForm.getThreeLettersISOCode(),
			commerceCountryUpserterForm.getNumericISOCode(),
			commerceCountryUpserterForm.isSubjectToVAT(), 0D, true,
			serviceContext);
	}

	@Reference
	private CommerceCountryService _commerceCountryService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.model.CommerceCountry)"
	)
	private HasPermission<Long> _hasPermission;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}