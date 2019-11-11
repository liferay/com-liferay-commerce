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

package com.liferay.headless.commerce.bom.internal.resource.v1_0;

import com.liferay.commerce.bom.model.CommerceBOMEntry;
import com.liferay.commerce.bom.service.CommerceBOMEntryService;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.service.CProductLocalService;
import com.liferay.headless.commerce.bom.dto.v1_0.Position;
import com.liferay.headless.commerce.bom.dto.v1_0.Spot;
import com.liferay.headless.commerce.bom.resource.v1_0.SpotResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.GetterUtil;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/spot.properties",
	scope = ServiceScope.PROTOTYPE, service = SpotResource.class
)
public class SpotResourceImpl extends BaseSpotResourceImpl {

	@Override
	public Response deleteAreaIdSpot(Long id, Long spotId) throws Exception {
		_commerceBOMEntryService.deleteCommerceBOMEntry(spotId);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Spot postAreaIdSpot(Long id, Spot spot) throws Exception {
		CProduct cProduct = _cProductLocalService.getCProductByCPInstanceUuid(
			spot.getProductId());

		Position position = spot.getPosition();

		CommerceBOMEntry commerceBOMEntry =
			_commerceBOMEntryService.addCommerceBOMEntry(
				_user.getUserId(), spot.getNumber(), spot.getProductId(),
				cProduct.getCProductId(), id, position.getX(), position.getY(),
				0D);

		DTOConverter spotDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CommerceBOMEntry.class.getName());

		return (Spot)spotDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceBOMEntry.getCommerceBOMEntryId()));
	}

	@Override
	public Response putAreaIdSpot(Long id, Long spotId, Spot spot)
		throws Exception {

		CommerceBOMEntry commerceBOMEntry =
			_commerceBOMEntryService.getCommerceBOMEntry(spotId);

		CProduct cProduct = _cProductLocalService.getCProductByCPInstanceUuid(
			spot.getProductId());

		Position position = spot.getPosition();

		_commerceBOMEntryService.updateCommerceBOMEntry(
			commerceBOMEntry.getCommerceBOMEntryId(),
			GetterUtil.get(spot.getNumber(), commerceBOMEntry.getNumber()),
			spot.getProductId(), cProduct.getCProductId(),
			GetterUtil.get(position.getX(), commerceBOMEntry.getPositionX()),
			GetterUtil.get(position.getY(), commerceBOMEntry.getPositionY()),
			0D);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Reference
	private CommerceBOMEntryService _commerceBOMEntryService;

	@Reference
	private CProductLocalService _cProductLocalService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Context
	private User _user;

}