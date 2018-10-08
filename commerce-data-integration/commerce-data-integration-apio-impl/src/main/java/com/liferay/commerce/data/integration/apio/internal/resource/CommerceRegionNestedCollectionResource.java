/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.data.integration.apio.internal.resource;

import com.liferay.apio.architect.pagination.PageItems;
import com.liferay.apio.architect.pagination.Pagination;
import com.liferay.apio.architect.representor.Representor;
import com.liferay.apio.architect.resource.NestedCollectionResource;
import com.liferay.apio.architect.routes.ItemRoutes;
import com.liferay.apio.architect.routes.NestedCollectionRoutes;
import com.liferay.commerce.data.integration.apio.identifiers.CommerceCountryIdentifier;
import com.liferay.commerce.data.integration.apio.identifiers.CommerceRegionIdentifier;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.service.CommerceRegionService;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 */
@Component(immediate = true)
public class CommerceRegionNestedCollectionResource
	implements NestedCollectionResource
		<CommerceRegion, Long, CommerceRegionIdentifier, Long,
		 CommerceCountryIdentifier> {

	@Override
	public NestedCollectionRoutes<CommerceRegion, Long, Long> collectionRoutes(
		NestedCollectionRoutes.Builder<CommerceRegion, Long, Long> builder) {

		return builder.addGetter(
			this::_getPageItems
		).build();
	}

	@Override
	public String getName() {
		return "commerce-region";
	}

	@Override
	public ItemRoutes<CommerceRegion, Long> itemRoutes(
		ItemRoutes.Builder<CommerceRegion, Long> builder) {

		return builder.addGetter(
			this::_getCommerceRegion
		).build();
	}

	@Override
	public Representor<CommerceRegion> representor(
		Representor.Builder<CommerceRegion, Long> builder) {

		return builder.types(
			"CommerceRegion"
		).identifier(
			CommerceRegion::getCommerceRegionId
		).addBidirectionalModel(
			"commerceCountry", "commerceRegions",
			CommerceCountryIdentifier.class,
			CommerceRegion::getCommerceCountryId
		).addString(
			"name", CommerceRegion::getName
		).addString(
			"code", CommerceRegion::getCode
		).build();
	}

	private CommerceRegion _getCommerceRegion(Long commerceRegionId)
		throws PortalException {

		return _commerceRegionService.getCommerceRegion(commerceRegionId);
	}

	private PageItems<CommerceRegion> _getPageItems(
			Pagination pagination, Long commerceCountryId)
		throws PortalException {

		List<CommerceRegion> commerceRegions =
			_commerceRegionService.getCommerceRegions(
				commerceCountryId, pagination.getStartPosition(),
				pagination.getEndPosition(), null);

		int total = _commerceRegionService.getCommerceRegionsCount(
			commerceCountryId);

		return new PageItems<>(commerceRegions, total);
	}

	@Reference
	private CommerceRegionService _commerceRegionService;

}